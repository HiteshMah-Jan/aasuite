/*
 * NetworkUtil.java
 *
 * Created on Feb 23, 2008, 6:17:53 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import component.remote.RemoteScreenDisplayer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.NetworkInterface;
import java.net.InetAddress;

import util.jxta.SocketClient;
import util.jxta.temp.ChannelAccept;
import util.jxta.temp.ChannelClient;
import util.jxta.temp.ChannelCommand;
import util.jxta.temp.PropagatedPipeClient;
import util.jxta.temp.PropagatedPipeServer;

/**
 *
 * @author Entokwaa
 */
public class NetworkUtil {
    private static String macAddress;
    private static String hostName;
    private static String ipAddress;

    public static void main(String[] args) {
        System.out.println("HOST = "+getHostname());
        System.out.println("MAC = "+macAddress());
    }

    public static String macAddress() {
        if (macAddress!=null) return macAddress;
        StringBuffer sb = new StringBuffer();
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            for (int i = 0; mac!=null && i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        macAddress = sb.toString();
        return sb.toString();
    }

    public static String getIpAddress() {
        if (ipAddress!=null) return ipAddress;
        try {
            java.net.InetAddress addr = java.net.InetAddress.getLocalHost(); 
            ipAddress = addr.getHostAddress();
        } catch (Exception e) {
        }
        return ipAddress;
    }

    public static String getHostname() {
        if (hostName!=null) return hostName;
        try {
            java.net.InetAddress addr = java.net.InetAddress.getLocalHost(); 
            hostName = addr.getHostName();
        } catch (Exception e) {
        }
        return hostName;
    }

    public static void hideAllScreen() {
        ChannelAccept.command.setCommand(ChannelCommand.HIDE_WINDOW);
//        PropagatedPipeServer server = util.jxta.temp.PropagatedPipeServer.getMyInstance();
//        server.setResize(PropagatedPipeServer.ServerCommand.HIDE);
    }
    
    public static void endMeeting() {
        ChannelAccept.command.setCommand(ChannelCommand.END_MEETING);
//        PropagatedPipeServer server = util.jxta.temp.PropagatedPipeServer.getMyInstance();
//        server.setResize(PropagatedPipeServer.ServerCommand.END);
    }

    public static void minimizeAllScreen() {
        ChannelAccept.command.setCommand(ChannelCommand.MINIMIZE_WINDOW);
//        PropagatedPipeServer server = util.jxta.temp.PropagatedPipeServer.getMyInstance();
//        server.setResize(PropagatedPipeServer.ServerCommand.MINIMIZE);
    }
    
    public static void maximizeAllScreen() {
        ChannelAccept.command.setCommand(ChannelCommand.MAXIMIZE_WINDOW);
//        PropagatedPipeServer server = util.jxta.temp.PropagatedPipeServer.getMyInstance();
//        server.setResize(PropagatedPipeServer.ServerCommand.MAXIMIZE);
    }
    
    public static void streamMyScreen(final String meetingName) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    ChannelAccept.startMeeting(meetingName); 
                } catch (Exception ex) {
                    Logger.getLogger("global").log(Level.SEVERE, null, ex);
                }
            }
        });
        t.start();
    }
    
    public static void usePanelForStreaming(final RemoteScreenDisplayer pnl, final String meetingName) {
        try {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    ChannelClient.streamServer(pnl, meetingName);
//                    Thread.currentThread().setName(PropagatedPipeClient.class.getName() + ".main()");
//                    PropagatedPipeClient client = PropagatedPipeClient.getInstance(pnl, meetingName);
//                    client.stopNetwork();
//                    client.startNetwork();
                }
            }); 
            t.start();
            Thread.currentThread().sleep(1000);
        } catch (Throwable e) {
            e.printStackTrace(System.err);
        }
    }
}
