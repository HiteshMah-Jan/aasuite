/*
 * SocketClient.java
 *
 * Created on Feb 24, 2008, 10:38:10 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.jxta;

/**
 *
 * @author Entokwaa
 */
import component.remote.RemoteScreenDisplayer;
import java.awt.GridLayout;
import java.awt.Image;
import net.jxta.peergroup.PeerGroup;
import net.jxta.platform.NetworkManager;
import net.jxta.protocol.PipeAdvertisement;
import net.jxta.socket.JxtaSocket;
import java.io.*;
import java.util.Date;
import javax.swing.JFrame;
import util.DataUtil;
import util.ImageUtil;

/**
 * This tutorial illustrates the use JxtaSocket. It attempts to bind a
 * JxtaSocket to an instance of JxtaServerSocket bound socket.adv.
 * <p/>
 * Once a connection is established data is exchanged with the server.
 * The client will identify how many ITERATIONS of PAYLOADSIZE buffers will be
 * exchanged with the server and then write and read those buffers.
 */
public class SocketClient {
    /**
     * number of runs to make
     */
    RemoteScreenDisplayer pnl;
    private transient NetworkManager manager = null;

    private transient PeerGroup netPeerGroup = null;
    private transient PipeAdvertisement pipeAdv;
    private transient boolean waitForRendezvous = false;

    public SocketClient(RemoteScreenDisplayer pnl, boolean waitForRendezvous) {
        this.pnl = pnl;
        try {
            manager = new NetworkManager(NetworkManager.ConfigMode.ADHOC, "SocketClient", new File(new File(".cache"), "SocketClient").toURI());
            manager.startNetwork();
        } catch (Exception e) {
            e.printStackTrace();
        }

        netPeerGroup = manager.getNetPeerGroup();
        pipeAdv = SocketServer.createSocketAdvertisement();
        if (waitForRendezvous) {
            manager.waitForRendezvousConnection(0);
        }
    }

    /**
     * Interact with the server.
     */
    public void run() {
        try {
            if (waitForRendezvous) {
                manager.waitForRendezvousConnection(0);
            }

            JxtaSocket socket = new JxtaSocket(netPeerGroup, null, pipeAdv, 5000, true);
            boolean alwaysRun = true;
            while(alwaysRun) {
                try {
                    Thread.currentThread().yield();
                }
                catch (Exception e) {
                }
                long startTime = (new Date()).getTime();
                // get the socket output stream
                OutputStream out = socket.getOutputStream();
                out.write(1);
                out.flush();
                out.close();

                // get the socket input stream
                InputStream in = socket.getInputStream();
                Image img = ImageUtil.getImageFromStream(in);
                in.close();

                if (img==null) return;
                img = ImageUtil.scaleImage(img, pnl);
                pnl.setImg(img);
                pnl.repaint();
                int usedTime = (int) ((new Date()).getTime() - startTime);
                System.out.println("Connection closed. "+usedTime);
            }
            socket.close();

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void stop() {
        manager.stopNetwork();
    }

    /**
     * If the java property RDVWAIT set to true then this demo
     * will wait until a rendezvous connection is established before
     * initiating a connection
     *
     * @param args none recognized.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame(); 
        frame.setAlwaysOnTop(true);
        RemoteScreenDisplayer pnl = new RemoteScreenDisplayer(); 
        frame.getContentPane().setLayout(new GridLayout());
        frame.getContentPane().add(pnl);
        frame.pack();
        frame.setVisible(true);
        try {
            Thread.currentThread().setName(SocketClient.class.getName() + ".main()");
            String value = System.getProperty("RDVWAIT", "false");
            boolean waitForRendezvous = Boolean.valueOf(value);
            SocketClient socEx = new SocketClient(pnl, waitForRendezvous);

            boolean b = true;
            while(b) {
                socEx.run();
            }
            socEx.stop();
        } catch (Throwable e) {
            e.printStackTrace(System.err);
        }
    }
}
