package sms;

import bean.admin.SMSMessageBean;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import sms.service.AbstractSMSProcessor;
import util.BeanUtil;
import util.Log;

/**
 *
 * @author Charliemagne Mark
 */
public class SMSServer extends TimerTask implements SerialPortEventListener {

    OutputStream os;
    InputStream is;
    protected int counter = 0;
    public Map<String, String> returnMessages = new HashMap<String, String>();
    boolean ignoreCallback;

    public void callbackReadMessage(SMSMessageBean bean) {
        if (ignoreCallback) return;
        Log.out("COUNTER==",(++counter));
        Log.out("PHONE==",bean.getPhoneNumber());
        Log.out("MESSAGE==",bean.getMessage());
        String[] arr = bean.message.split(" ");
        String code = arr[0];
        AbstractSMSProcessor.getInstance(this, code, bean);
    }
    
    public void serialEvent(SerialPortEvent e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int newData = 0;
        switch (e.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE:
                while (newData != -1) {
                    try {
                        newData = is.read();
                        if (newData == -1) {
                            break;
                        }
//                        Log.out("new data == ",newData);
                        baos.write(newData);
                    } catch (IOException ex) {
                        System.err.println(ex);
                        return;
                    }
                }
                String receptionString = new String(baos.toByteArray());
                if (receptionString!=null && receptionString.trim().length()>10) {
                    String[] strArr = receptionString.split("(\\+CMGL: )", 1000);
                    for (String string : strArr) {
                        string = string.trim();
                        if (string.isEmpty() || string.length()<15 || !string.contains("\n")) continue;
                        SMSMessageBean bean = SMSMessageBean.extractBean(string);
                        callbackReadMessage(bean);
                    }
                }
                break;
            case SerialPortEvent.BI:
                Log.out("\n--- BREAK RECEIVED ---\n");
        }
    }

    public SMSServer(String port) {
        CommPortIdentifier portId;
        SerialPort sPort = null;
        try {
            portId = CommPortIdentifier.getPortIdentifier(port);
            sPort = (SerialPort) portId.open("SMSConnector", 30000);
            sPort.sendBreak(1000);
            sPort.setSerialPortParams(9600, 8, 1, 0);
            sPort.setFlowControlMode(0 | 0);
            os = sPort.getOutputStream();
            is = sPort.getInputStream();
            sPort.addEventListener((SerialPortEventListener) this);
            sPort.notifyOnDataAvailable(true);
            sPort.notifyOnBreakInterrupt(true);
            sPort.enableReceiveTimeout(30);
            send("AT");
//            send("AT+CNMI=1,2,0,0,0");
        } catch (Exception ex) {
            if (sPort != null) {
                sPort.close();
            }
            Logger.getLogger(SMSServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void send(String message) {
        byte[] theBytes = (message + "\n").getBytes();
        for (int i = 0; i < theBytes.length; i++) {
            char newCharacter = (char) theBytes[i];
            if ((int) newCharacter == 10) {
                newCharacter = '\r';
            }
            try {
                os.write((int) newCharacter);
            } catch (IOException e) {
                Log.out("OutputStream write error: ",e);
            }
        }
    }

    protected void sendControlZ() {
        try {
            os.write((char)26);
        } catch (IOException e) {
            Log.out("OutputStream write error: ",e);
        }
    }

    public void start(int minutes) {
        Log.out("Start SERVER");
        Timer t = new Timer();
        t.schedule(this, 500, minutes*60*1000);
    }
    
    @Override
    public void run() {
        ignoreCallback = false;
        counter = 0;
        send("AT+CMGF=1");
        Log.out("RUN SERVICE");
        send("AT+CMGL=\"ALL\"");
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    //return messages
                    Thread.currentThread().sleep(10000);
                    ignoreCallback = true;
                    Iterator iter = returnMessages.keySet().iterator();
                    while(iter.hasNext()) {
                        String phone = iter.next().toString();
                        String message = returnMessages.get(phone);
                        sendMessage(phone, message);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(SMSServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t.start();
    }
    
    private void sendMessage(String phone, String message) {
        send(BeanUtil.concat("AT+CMGS=\"",phone,"\"\n",message));
        sendControlZ();
    }
    
    public void addtoReply(String phone, String message) {
        returnMessages.put(phone, message);
    }
}
