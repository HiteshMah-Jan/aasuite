/*
 * ChannelClient.java
 *
 * Created on Feb 27, 2008, 3:04:55 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.jxta.temp;

import bean.admin.RemoteMeeting;
import component.remote.RemoteScreenDisplayer;
import java.awt.GridLayout;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import service.util.AbstractIBean;
import util.DataUtil;
import util.ImageUtil;

/**
 *
 * @author Entokwaa
 */
public class ChannelClient {
    public static int timePeriod = 500;
    
    public static void streamServer(RemoteScreenDisplayer pnl, String meetingName) {
        RemoteMeeting meeting = (RemoteMeeting) AbstractIBean.firstRecord("SELECT a FROM RemoteMeeting a WHERE a.meetingName='"+meetingName+"'"); 
        if (meeting==null) return;
        
        Timer t = new Timer(); 
        MyTimerTask task = new MyTimerTask(pnl, meeting.getServingIp(), t);
        t.schedule(task, 1000, timePeriod);
    }

    private static class MyTimerTask extends TimerTask {
        RemoteScreenDisplayer pnl;   
        String meetingHost;
        Timer t;
        
        public MyTimerTask(RemoteScreenDisplayer pnl, String meetingHost, Timer t) {
            this.pnl = pnl;
            this.meetingHost = meetingHost;
            this.t = t;
        }
        
        public void run() {
            try {
                System.out.println("TEST1");
                InetSocketAddress addr = new InetSocketAddress(meetingHost, ChannelAccept.port);
                SocketChannel ch = SocketChannel.open(addr);
                InputStream is = ch.socket().getInputStream();
                
                ObjectInputStream ois = new ObjectInputStream(is);
                ChannelCommand command = (ChannelCommand) ois.readObject();
                ois.close();
                is.close();
                ch.close();
                
                pnl.setImg(ImageUtil.getImageFromBytes((byte[])command.getCommandObj()));
                pnl.repaint();
                pnl.setCommand(command.getCommand());
                System.out.println("COMMAND RECEIVED ==== "+command.getCommand());
                if (command.getCommand()==ChannelCommand.END_MEETING) {
                    t.cancel();
                }
                System.out.println("TEST2");
            } catch (Exception ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame(); 
        frame.setAlwaysOnTop(true);
        RemoteScreenDisplayer pnl = new RemoteScreenDisplayer(); 
        frame.getContentPane().setLayout(new GridLayout());
        frame.getContentPane().add(pnl);
        frame.pack();
        frame.setVisible(true);
        
        streamServer(pnl, "localhost");
    }
}
