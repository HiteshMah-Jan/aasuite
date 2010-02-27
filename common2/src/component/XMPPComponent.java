/*
 * XMPPComponent.java
 *
 * Created on Feb 8, 2008, 8:07:11 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import bean.Person;
import bean.admin.SendMessageAccount;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

/**
 *
 * @author Entokwaa
 */
public class XMPPComponent {

    public static String errorMessage;
    List<Person> lstPerson;
    String message;
    List<String> mobileList = new ArrayList<String>();

    public static XMPPComponent getInstance() {
        return new XMPPComponent();
    }

    /** Creates a new instance of XMPPComponent */
    public void sendMessage(List<Person> lstPerson, String message) {
        errorMessage = null;
        this.lstPerson = lstPerson;
        this.message = message;
        setup();
        sendToMobile();
    }

    private void setup() {
        for (Person per : lstPerson) {
            String mobile = formatMobile(per.getMobilePhone());
            if (mobile != null) {
                mobileList.add(mobile);
            }
        }
    }

    private String formatMobile(String mobile) {
        if (mobile == null || mobile.isEmpty()) {
            return mobile;
        }
        if (mobile.endsWith("@chikkatalk.com")) {
            return mobile;
        }
        if (mobile.startsWith("0")) {
            mobile = "63" + mobile.substring(1) + "@chikkatalk.com";
        } else if (!mobile.startsWith("63")) {
            mobile = "63" + mobile + "@chikkatalk.com";
        } else if (mobile.startsWith("+")) {
            mobile = mobile.substring(1) + "@chikkatalk.com";
        }
        return mobile;
    }
    private SendMessageAccount acc;
    private XMPPConnection conn;

    private XMPPConnection getConnection() {
        if (conn == null || !conn.isConnected()) {
            try {
                acc = SendMessageAccount.getGoogleAccount();
                ConnectionConfiguration conf = new ConnectionConfiguration(acc.getXmppHost(), acc.getXmppPort(), acc.getXmppDns());
                conn = new XMPPConnection(conf);
                conn.connect();
                conn.login(acc.getXmppUsername(), acc.getXmppPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    private void sendToMobile() {
        if (mobileList == null && mobileList.isEmpty()) {
            return;
        }
        for (String mobile : mobileList) {
            sendToMobile(mobile, acc.getPreMessage());
        }
    }

    public static void sendToMobile(String mobileNumber, String message) {
        try {
            XMPPComponent comp = XMPPComponent.getInstance();
            XMPPConnection connection = comp.getConnection();
            String mobile = comp.formatMobile(mobileNumber);
            try {
                if (!connection.getRoster().contains(mobile.toLowerCase())) {
                    connection.getRoster().createEntry(mobile.toLowerCase(), mobile.toLowerCase(), null);
                }
            } catch (Exception e) {
            }
            Chat chat = connection.getChatManager().createChat(mobile, new MessageListener() {
                public void processMessage(Chat chat, Message message) {
                    java.lang.System.out.println("Received message: " + message);
                }
            });
            chat.sendMessage(message);
            Logger.getLogger("global").info("Send to " + mobile);
        } catch (XMPPException ex) {
            errorMessage += "\n" + ex.getMessage();
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }

//    private void sendEmail() {
//        try {
//            if (emailList == null || emailList.isEmpty()) {
//                return;
//            }
//            SendMessageAccount acc = SendMessageAccount.getEmailAccount();
//            Properties props = new Properties();
//            props.setProperty("mail.transport.protocol", "smtp");
//            props.setProperty("mail.host", acc.getEmailHost().toLowerCase());
//            props.setProperty("mail.user", acc.getEmailUser().toLowerCase());
//            props.setProperty("mail.password", acc.getEmailPassword().toLowerCase());
//
//            javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, null);
//            Transport transport = mailSession.getTransport();
//
//            MimeMessage mimeMessage = new MimeMessage(mailSession);
//            mimeMessage.setSubject(acc.getPreMessage());
//            mimeMessage.setContent(message, "text/plain");
//            mimeMessage.setFrom(new InternetAddress(acc.getEmailAccountSender().toLowerCase()));
//            for (String email : emailList) {
//                mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(email.toLowerCase()));
//                Logger.getLogger("global").info("Send to "+email.toLowerCase());
//            }       
//            transport.connect();
//            transport.sendMessage(mimeMessage, mimeMessage.getRecipients(javax.mail.Message.RecipientType.TO));
//            transport.close();
//        } catch (Exception ex) {
//            errorMessage += "\n"+ex.getMessage();
//            Logger.getLogger("global").log(Level.SEVERE, null, ex);
//        }
//    }
    public static void main(String[] args) {
    }
}
