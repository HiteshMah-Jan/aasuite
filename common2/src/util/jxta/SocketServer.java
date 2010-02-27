/*
 * SocketServer.java
 *
 * Created on Feb 24, 2008, 10:37:28 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.jxta;

/**
 *
 * @author Entokwaa
 */
import java.awt.Robot;
import net.jxta.document.AdvertisementFactory;
import net.jxta.id.IDFactory;
import net.jxta.peergroup.PeerGroup;
import net.jxta.pipe.PipeID;
import net.jxta.pipe.PipeService;
import net.jxta.platform.NetworkManager;
import net.jxta.protocol.PipeAdvertisement;
import net.jxta.socket.JxtaServerSocket;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import util.DataUtil;


/**
 * This tutorial illustrates the use JxtaServerSocket It creates a
 * JxtaServerSocket with a back log of 10. it also blocks indefinitely, until a
 * connection is established.
 * <p/>
 * Once a connection is established data is exchanged with the initiator.
 * The initiator will provide an iteration count and buffer size. The peers will
 * then read and write buffers. (or write and read for the initiator).
 */
public class SocketServer {

    Robot robot;
    private transient PeerGroup netPeerGroup = null;
    public static final String SOCKETIDSTR = "urn:jxta:uuid-59616261646162614E5047205032503393B5C2F6CA7A41FBB0F890173088E79404";

    public SocketServer() throws Exception {
        robot = new Robot();
        NetworkManager manager = new NetworkManager(NetworkManager.ConfigMode.ADHOC, "SocketServer", new File(new File(".cache"), "SocketServer").toURI());
        manager.startNetwork();
        netPeerGroup = manager.getNetPeerGroup();
    }

    public static PipeAdvertisement createSocketAdvertisement() {
        PipeID socketID = null;

        try {
            socketID = (PipeID) IDFactory.fromURI(new URI(SOCKETIDSTR));
        } catch (URISyntaxException use) {
            use.printStackTrace();
        }
        PipeAdvertisement advertisement = (PipeAdvertisement) AdvertisementFactory.newAdvertisement(PipeAdvertisement.getAdvertisementType());

        advertisement.setPipeID(socketID);
        advertisement.setType(PipeService.UnicastType);
        advertisement.setName("Socket tutorial");
        return advertisement;
    }

    /**
     * Wait for connections
     */
    public void run() {
        JxtaServerSocket serverSocket = null;
        try {
            serverSocket = new JxtaServerSocket(netPeerGroup, createSocketAdvertisement(), 10);
            serverSocket.setSoTimeout(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                if (socket != null) {
                    Thread thread = new Thread(new ConnectionHandler(socket), "Connection Handler Thread");
                    thread.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class ConnectionHandler implements Runnable {
        Socket socket = null;

        ConnectionHandler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Sends data over socket
         *
         * @param socket the socket
         */
        private void sendAndReceiveData(Socket socket) {
            try {
                // get the socket input stream
                long startTime = (new Date()).getTime();
                InputStream in = socket.getInputStream();
                int i = in.read();
                in.close();

                if (i==1) {
                    byte[] out_buf = DataUtil.getScreenBytes(100);
                    OutputStream out = socket.getOutputStream();
                    out.write(out_buf);
                    out.flush();
                    out.close();
                }
                socket.close();
                int usedTime = (int) ((new Date()).getTime() - startTime);
                System.out.println("Connection closed. "+usedTime);
            } catch (Exception ie) {
                ie.printStackTrace();
            }
        }

        public void run() {
            sendAndReceiveData(socket);
        }
    }

    /**
     * main
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        try {
            Thread.currentThread().setName(SocketServer.class.getName() + ".main()");
            SocketServer socEx = new SocketServer();
            socEx.run();
        } catch (Throwable e) {
            System.err.println("Failed : " + e);
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }
}
