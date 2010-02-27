/*
 * PropagatedPipeServer.java
 *
 * Created on Feb 25, 2008, 10:50:29 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.jxta.temp;

/**
 *
 * @author Entokwaa
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.XMLDocument;
import net.jxta.document.StructuredDocumentFactory;
import net.jxta.document.MimeMediaType;
import net.jxta.endpoint.Message;
import net.jxta.endpoint.MessageElement;
import net.jxta.endpoint.MessageTransport;
import net.jxta.endpoint.TextDocumentMessageElement;
import net.jxta.id.IDFactory;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.pipe.InputPipe;
import net.jxta.pipe.OutputPipe;
import net.jxta.pipe.PipeID;
import net.jxta.pipe.PipeMsgEvent;
import net.jxta.pipe.PipeMsgListener;
import net.jxta.pipe.PipeService;
import net.jxta.platform.NetworkManager;
import net.jxta.protocol.PipeAdvertisement;
import net.jxta.protocol.RouteAdvertisement;
import net.jxta.impl.endpoint.router.RouteControl;
import net.jxta.impl.endpoint.router.EndpointRouter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.Hashtable;
import net.jxta.endpoint.ByteArrayMessageElement;
import net.jxta.endpoint.StringMessageElement;
import util.DataUtil;
import util.DateUtil;

/**
 * Simple example to illustrate the use of propagated pipes
 */

public class PropagatedPipeServer implements PipeMsgListener {
    public static class ServerCommand {
        public static String RESIZE = "RESIZE";
        public static String MAXIMIZE = "MAX";
        public static String MINIMIZE = "MIN";
        public static String HIDE = "HIDE";
        public static String END = "END";
    }
    
    static PropagatedPipeServer server;
    
    public static PropagatedPipeServer getMyInstance() {
        return server;
    }
    
    public static PropagatedPipeServer getInstance(String meetingName) {
        if (server==null) {
            server = new PropagatedPipeServer(meetingName);
        }
        return server;
    }
    public final static String SRCIDTAG = "SRCID";
    public final static String SCREENTAG = "SCREEN";
    public final static String TIMETAG = "TIME";
    public final static String TEST_NAMESPACE = "PROPTUT";
    private static String NAMESPACE = "PROPTUT";
    private PeerGroup netPeerGroup = null;

    /**
     * Common propagated pipe id
     */
    public final static String PIPEIDSTR = "urn:jxta:uuid-59616261646162614E504720503250336FA944D18E8A4131AA74CE6F4BF85DEF04";
    private final static String completeLock = "completeLock";
    private static PipeAdvertisement pipeAdv = null;
    private static PipeService pipeService = null;
    InputPipe inputPipe = null;
    private transient Map<PeerID, OutputPipe> pipeCache = new Hashtable<PeerID, OutputPipe>();
    public static final String ROUTEADV = "ROUTE";
    private RouteControl routeControl = null;
    private MessageElement routeAdvElement;
    NetworkManager manager = null;
    String resize = ServerCommand.MAXIMIZE;

    public String getResize() {
        return resize;
    }

    public void setResize(String resize) {
        this.resize = resize;
    }    
    
    public void startNetwork() {
        routeAdvElement = null;

        pipeAdv = getPipeAdvertisement();

        try {
            manager = new NetworkManager(NetworkManager.ConfigMode.ADHOC, "PropagatedPipeServer", new File(new File(".cache"), "PropagatedPipeServer").toURI());
            manager.startNetwork();
        } catch (Exception e) {
            e.printStackTrace();
//            System.exit(-1);
        }
        netPeerGroup = manager.getNetPeerGroup();
        pipeService = netPeerGroup.getPipeService();

        MessageTransport endpointRouter = (netPeerGroup.getEndpointService()).getMessageTransport("jxta");

        if (endpointRouter != null) {
            routeControl = (RouteControl) endpointRouter.transportControl(EndpointRouter.GET_ROUTE_CONTROL, null);
            RouteAdvertisement route = routeControl.getMyLocalRoute();
            if (route != null) {
                routeAdvElement = new TextDocumentMessageElement(ROUTEADV, (XMLDocument) route.getDocument(MimeMediaType.XMLUTF8), null);
            }
        }

        System.out.println("Creating Propagated InputPipe for " + pipeAdv.getPipeID());
        try {
            inputPipe = pipeService.createInputPipe(pipeAdv, this);
        } catch (IOException e) {
            e.printStackTrace();
//            System.exit(-1);
        }
        waitForever();
    }

    public void stopNetwork() {
        try {
            inputPipe.close();
            manager.stopNetwork();
        }
        catch (Exception e) {
        }
    }
    
    private PropagatedPipeServer(String meetingName) {
        NAMESPACE = meetingName;
    }

    /**
     * Gets the pipeAdvertisement attribute of the PropagatedPipeServer class
     *
     * @return The pipeAdvertisement value
     */
    public static PipeAdvertisement getPipeAdvertisement() {
        PipeID pipeID = null;

        try {
            pipeID = (PipeID) IDFactory.fromURI(new URI(PIPEIDSTR));
        } catch (URISyntaxException use) {
            use.printStackTrace();
        }
        PipeAdvertisement advertisement = (PipeAdvertisement)
                AdvertisementFactory.newAdvertisement(PipeAdvertisement.getAdvertisementType());

        advertisement.setPipeID(pipeID);
        advertisement.setType(PipeService.PropagateType);
        advertisement.setName("Propagated Pipe Tutorial");
        return advertisement;
    }

    OutputPipe outputPipe = null;
    
    private synchronized void sendMessage(Message pong) {
        try {
            boolean sucess = outputPipe.send(pong);
        } catch (IOException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void pipeMsgEvent(PipeMsgEvent event) {

        Message message = event.getMessage();

        if (message == null) {
            return;
        }
        MessageElement sel = message.getMessageElement(NAMESPACE, SRCIDTAG);
        processRoute(message);
        if (sel == null) {
            return;
        }
        Message pong = new Message();

        byte[] b = DataUtil.getScreenBytes(100);
        ByteArrayMessageElement ba = new ByteArrayMessageElement(SCREENTAG, MimeMediaType.AOS, b, 0, b.length, null);
        pong.addMessageElement(NAMESPACE, ba);
        pong.addMessageElement(NAMESPACE, new StringMessageElement(ServerCommand.RESIZE, resize, null));
        pong.addMessageElement(NAMESPACE, new StringMessageElement(TIMETAG, DateUtil.getTimeSecond(), null));

        PeerID pid = null;

        try {
            pid = (PeerID) IDFactory.fromURI(new URI(sel.toString()));
            if (pid != null) {
                // Unicast the Message back. One should expect this to be unicast
                // in Rendezvous only propagation mode.
                // create a op pipe to the destination peer
                if (!pipeCache.containsKey(pid)) {
                    // Unicast datagram
                    // create a op pipe to the destination peer
                    outputPipe = pipeService.createOutputPipe(pipeAdv, Collections.singleton(pid), 1);
                    pipeCache.put(pid, outputPipe);
                } else {
                    outputPipe = pipeCache.get(pid);
                }
                boolean sucess = outputPipe.send(pong);
                System.out.println("Send pong message status :"+sucess);
            } else {
                // send it to all
                System.out.println("unable to create a peerID from :" + sel.toString());
                outputPipe = pipeService.createOutputPipe(pipeAdv, 1000);
                sendMessage(pong);
//                boolean sucess = outputPipe.send(pong);
//                System.out.println("Send pong message status :"+sucess);

            }
        } catch (IOException ex) {
            if (pid != null && outputPipe != null) {
                outputPipe.close();
                outputPipe = null;
                pipeCache.remove(pid);
            }
            ex.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Keep running, avoids existing
     */
    private void waitForever() {
        try {
            System.out.println("Waiting for Messages.");
            synchronized (completeLock) {
                completeLock.wait();
            }
            System.out.println("Done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processRoute(final Message msg) {
        try {
            final MessageElement routeElement = msg.getMessageElement(NAMESPACE, ROUTEADV);
            if (routeElement != null && routeControl != null) {
                XMLDocument asDoc = (XMLDocument) StructuredDocumentFactory.newStructuredDocument(routeElement.getMimeType(), routeElement.getStream());
                final RouteAdvertisement route = (RouteAdvertisement) AdvertisementFactory.newAdvertisement(asDoc);
                routeControl.addRoute(route);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * main
     *
     * @param args command line args
     */
    public static void main(String args[]) {
        PropagatedPipeServer server = PropagatedPipeServer.getInstance(TEST_NAMESPACE);
        server.stopNetwork();
        server.startNetwork();
    }
}

