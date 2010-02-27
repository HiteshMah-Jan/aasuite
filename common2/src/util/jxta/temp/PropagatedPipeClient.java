/*
 * PropagatedPipeClient.java
 * 
 * Created on Feb 25, 2008, 10:50:01 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.jxta.temp;

/**
 *
 * @author Entokwaa
 */
import component.remote.RemoteScreenDisplayer;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jxta.endpoint.Message;
import net.jxta.endpoint.MessageElement;
import net.jxta.endpoint.StringMessageElement;
import net.jxta.endpoint.TextDocumentMessageElement;
import net.jxta.endpoint.MessageTransport;
import net.jxta.peergroup.PeerGroup;
import net.jxta.pipe.InputPipe;
import net.jxta.pipe.OutputPipe;
import net.jxta.pipe.PipeMsgEvent;
import net.jxta.pipe.PipeMsgListener;
import net.jxta.pipe.PipeService;
import net.jxta.platform.NetworkManager;
import net.jxta.protocol.PipeAdvertisement;
import net.jxta.protocol.RouteAdvertisement;
import net.jxta.impl.endpoint.router.EndpointRouter;
import net.jxta.impl.endpoint.router.RouteControl;
import net.jxta.document.XMLDocument;
import net.jxta.document.MimeMediaType;

import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import util.DataUtil;
import util.ImageUtil;


/**
 * Simple example to illustrate the use of propagated pipes
 */
public class PropagatedPipeClient implements PipeMsgListener {
    NetworkManager manager = null;
    private InputPipe inputPipe;
    private MessageElement routeAdvElement = null;
    private RouteControl routeControl = null;
    public static final String ROUTEADV = "ROUTE";

    static PropagatedPipeClient client;
    RemoteScreenDisplayer pnl;
    String meetingName;

    public static PropagatedPipeClient getInstance(RemoteScreenDisplayer pnl, String meetingName) {
        if (client==null) {
            client = new PropagatedPipeClient(pnl, meetingName);
        }
        return client;
    }
    
    private String lastTime = "0";
    /**
     * {@inheritDoc}
     */
    public void pipeMsgEvent(PipeMsgEvent event) {
        try {
            net.jxta.endpoint.Message message = event.getMessage();

            if (message == null) {
                return;
            }
//            net.jxta.endpoint.MessageElement sel = message.getMessageElement(util.jxta.temp.PropagatedPipeServer.NAMESPACE, util.jxta.temp.PropagatedPipeServer.PONGTAG);
//            net.jxta.endpoint.MessageElement nel = message.getMessageElement(util.jxta.temp.PropagatedPipeServer.NAMESPACE, util.jxta.temp.PropagatedPipeServer.SRCNAMETAG);
            MessageElement screen = message.getMessageElement(meetingName, PropagatedPipeServer.SCREENTAG);
            MessageElement resize = message.getMessageElement(meetingName, PropagatedPipeServer.ServerCommand.RESIZE);
            MessageElement time = message.getMessageElement(meetingName, PropagatedPipeServer.TIMETAG);

            if (screen == null) {
                return;
            }
            // Since propagation relies on ip multicast whenever possible, it is to
            // to be expected that a unicasted message can be intercepted through ip
            // multicast
            if (isOkPublish(time.toString())) {
                byte[] b = screen.getBytes(true);
                java.awt.Image img = util.ImageUtil.getImageFromBytes(b);
                img = ImageUtil.scaleImage(img, pnl);
                pnl.setImg(img);
                pnl.repaint();
                pnl.resizeHolder(resize.toString());
                lastTime = time.toString();
                if (resize.toString().equals(PropagatedPipeServer.ServerCommand.END)) {
                    stopNetwork();
                    client = null;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }

    private boolean isOkPublish(String time) {
        System.out.println("TIME "+time+":"+lastTime);
        int iTime = Integer.parseInt(time);
        int iLastTime = Integer.parseInt(lastTime);
        return iTime > iLastTime;
    }
    
    public void startNetwork() {
        try {
            manager = new NetworkManager(NetworkManager.ConfigMode.ADHOC, "PropagatedPipeClient",
                    new File(new File(".cache"), "PropagatedPipeClient").toURI());
            manager.startNetwork();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PeerGroup netPeerGroup = manager.getNetPeerGroup();
        PipeAdvertisement pipeAdv = PropagatedPipeServer.getPipeAdvertisement();
        PipeService pipeService = netPeerGroup.getPipeService();

        System.out.println("Creating Propagated InputPipe for " + pipeAdv.getPipeID());
        try {
            inputPipe = pipeService.createInputPipe(pipeAdv, this);
        } catch (IOException e) {
            e.printStackTrace();
//            System.exit(-1);
        }
        MessageTransport endpointRouter = (netPeerGroup.getEndpointService()).getMessageTransport("jxta");

        if (endpointRouter != null) {
            routeControl = (RouteControl) endpointRouter.transportControl(EndpointRouter.GET_ROUTE_CONTROL, null);
            RouteAdvertisement route = routeControl.getMyLocalRoute();

            if (route != null) {
                routeAdvElement = new TextDocumentMessageElement(ROUTEADV, (XMLDocument) route.getDocument(MimeMediaType.XMLUTF8), null);
            }
        }

        System.out.println("Creating Propagated OutputPipe for " + pipeAdv.getPipeID());
        OutputPipe output = null;

        try {
            output = pipeService.createOutputPipe(pipeAdv, 1);
        } catch (IOException e) {
            e.printStackTrace();
//            System.exit(-1);
        }
        int i = 0;

        try {
            while (i < 10000000) {
                Message ping = new Message();
                ping.addMessageElement(meetingName,
                        new StringMessageElement(PropagatedPipeServer.SRCIDTAG, netPeerGroup.getPeerID().toString(), null));
                if (routeAdvElement != null && routeControl != null) {
                    ping.addMessageElement(meetingName, routeAdvElement);
                }
                boolean sucess = output.send(ping);
                Thread.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private PropagatedPipeClient(RemoteScreenDisplayer pnl, String meetingName) {
        this.pnl = pnl;
        this.meetingName = meetingName;
    }
    
    public void stopNetwork() {
        try {
            manager.stopNetwork();
        }
        catch (Exception e) {
        }
    }
    /**
     * main
     *
     * @param args command line args
     */
    public static void main(String args[]) {
        JFrame frame = new JFrame(); 
        frame.setAlwaysOnTop(true);
        RemoteScreenDisplayer pnl = new RemoteScreenDisplayer(); 
        frame.getContentPane().setLayout(new GridLayout());
        frame.getContentPane().add(pnl);
        frame.pack();
        frame.setVisible(true);

        PropagatedPipeClient client = new PropagatedPipeClient(pnl, PropagatedPipeServer.TEST_NAMESPACE);
    }
}

