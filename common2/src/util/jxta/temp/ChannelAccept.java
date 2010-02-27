/*
 * ChannelAccept.java
 *
 * Created on Feb 27, 2008, 3:01:15 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.jxta.temp;

import bean.admin.RemoteMeeting;
import java.io.ObjectOutputStream;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;
import service.util.AbstractIBean;
import util.DataUtil;
import util.NetworkUtil;

public class ChannelAccept {

    public static int port = 1234; // default
    static boolean started = false;
    public static ChannelCommand command = new ChannelCommand();
    
    public static void startMeeting(String meetingName) throws Exception {
        command.setCommand(ChannelCommand.MINIMIZE_WINDOW);
        if (started) return;
        started = true;
        
        RemoteMeeting meeting = (RemoteMeeting) AbstractIBean.firstRecord("SELECT a FROM RemoteMeeting a WHERE a.meetingName='"+meetingName+"'");
        meeting.setServingIp(NetworkUtil.getIpAddress());
        meeting.save();
        
        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);
        command.setCommand(0);

        while (true) {
            try {
                System.out.println("Waiting for connections");
                SocketChannel sc = ssc.accept();
                if (sc == null) {
                    // no connections, snooze a while
                    Thread.sleep(2000);
                } else {
                    byte[] b = DataUtil.getScreenBytes(100);
                    command.setCommandObj(b);

                    ObjectOutputStream oos = new ObjectOutputStream(sc.socket().getOutputStream());
                    oos.writeObject(command);
                    oos.close();
                    sc.close();
                    if (command.getCommand()==ChannelCommand.END_MEETING) {
                        DataUtil.endCapture();
                    }
                    System.out.println("SENDING COMMAND === "+command.getCommand());
                }
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] argv) throws Exception {
    }
}