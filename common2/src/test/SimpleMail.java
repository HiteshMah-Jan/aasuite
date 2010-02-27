/*
 * SimpleMail.java
 *
 * Created on May 22, 2008, 2:18:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import bean.admin.InboxEmail;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class SimpleMail {

    private static String POP3_HOST_NAME = "pop.secureserver.net";
    private static String PROVIDER = "pop3";
    private static int pop3_port = 110;
//    private static String SMTP_HOST_NAME = "smtpout.secureserver.net";
//    private static String USERNAME = "sales@soft-labs.com";
//    private static String PASSWORD = "salespassword";
//    private static int smtp_port = 80;

    private static String SMTP_HOST_NAME = "smtp.gmail.com";
    private static String USERNAME = "sales.softlabs@gmail.com";
    private static String PASSWORD = "pass1word";
    private static int smtp_port = 587;
    private static String DEFAULT_EMAIL = "niq77avie@yahoo.com";
    private static String DEFAULT_SENDER_NAME = "Miranda Workbench Softwares<sales.softlabs@gmail.com>";

    public static void main(String[] args) throws Exception {
        sendEmail("Softlabs Auto email", "Please check your email", "niq77avie@yahoo.com", "d:/screen.png");
//        getInbox();
    }

    public static List<InboxEmail> getInbox() {
        try {
            javax.mail.Session session = javax.mail.Session.getDefaultInstance(getPOPProperties(), null);
//            session.setDebug(true);
            javax.mail.Store store = session.getStore(PROVIDER);
            store.connect(POP3_HOST_NAME, USERNAME, PASSWORD);

            // Open the folder
            javax.mail.Folder inbox = store.getFolder("INBOX");
            if (inbox == null) {
                java.lang.System.out.println("No INBOX");
                java.lang.System.exit(1);
            }
            inbox.open(javax.mail.Folder.READ_ONLY);

            // Get the messages from the server
            javax.mail.Message[] messages = inbox.getMessages();
            List<InboxEmail> emails = new ArrayList<InboxEmail>();
            for (Message message : messages) {
                InboxEmail email = new InboxEmail();
                email.setCarbonCopy(getAddress(message.getAllRecipients()));
                email.setContent(getMessageByte(message));
                email.setEmailId(message.getMessageNumber());
                email.setExtractedDate(constants.Constants.useDate);
                email.setSender(getAddress(message.getFrom()));
                email.setSubject(message.getSubject());
                emails.add(email);
            }
            inbox.close(false);
            store.close();
            return emails;
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static byte[] getMessageByte(Message message) {
        try {
            javax.mail.Part messagePart = message;
            java.lang.Object content = messagePart.getContent();
            // -- or its first body part if it is a multipart message --
            if (content instanceof javax.mail.Multipart) {
                messagePart = ((javax.mail.Multipart) content).getBodyPart(0);
            }
            // -- Get the content type --
            java.lang.String contentType = messagePart.getContentType();
            // -- If the content is plain text, we can print it --
            if (contentType.startsWith("text/plain") || contentType.startsWith("text/html")) {
                java.io.InputStream is = messagePart.getInputStream();
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(is));
                java.lang.String thisLine = reader.readLine();
                StringBuffer sb = new StringBuffer();
                while (thisLine != null) {
                    thisLine = reader.readLine();
                    sb.append(thisLine).append("\n");
                }
                System.out.println("MESSAGE CONTENT = " + sb.toString());
                return sb.toString().getBytes();
            }
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String getAddress(Address[] addrs) {
        StringBuffer sb = new StringBuffer();
        for (Address address : addrs) {
            sb.append(address.toString()).append(";");
        }
        System.out.println("ADDRESS == " + sb.toString());
        return sb.toString();
    }

    private static Properties getSMTPProperties() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", smtp_port + "");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
    }

    private static Properties getPOPProperties() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "pop3");
        props.setProperty("mail.host", POP3_HOST_NAME);
        props.put("mail.pop3.auth", "true");
        props.put("mail.pop3.port", pop3_port + "");
        return props;
    }

    static Transport transport;    
    static Properties props;
    static Session mailSession;
    
    public static synchronized void sendEmail(String subject, String content, String recipient, String attachment) throws Exception {
        if (transport==null) {
            props = getSMTPProperties();
            mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);
            transport = mailSession.getTransport("smtp");
            transport.connect(SMTP_HOST_NAME, smtp_port, USERNAME, PASSWORD);
        }
        MimeMessage message = constructMessage(mailSession, subject, content, recipient, attachment);
        if (transport.isConnected()) {
            System.err.println("REUSE EMAIL CONNECTION...");
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        }
        else {
            System.err.println("RECONNECTING EMAIL...");
            transport.close();
            transport.connect(SMTP_HOST_NAME, smtp_port, USERNAME, PASSWORD);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        }
    }
    
    private static MimeMessage constructMessage(Session mailSession, String subject, String content, String recipient, String attachment) throws Exception {
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(DEFAULT_SENDER_NAME));
        message.setSubject(subject);

        if (attachment != null && !attachment.isEmpty()) {
            Multipart mp = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(content, "text/plain");
            mp.addBodyPart(textPart);

            String[] fArr = attachment.split(";");
            for (String string : fArr) {
                if (string==null || string.trim().isEmpty()) continue;
                MimeBodyPart attachFilePart = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(string);
                attachFilePart.setDataHandler(new DataHandler(fds));
                attachFilePart.setFileName(fds.getName());

                mp.addBodyPart(attachFilePart);
            }
            message.setContent(mp);
        } else {
            message.setText(content);
        }
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.addRecipient(Message.RecipientType.BCC, new InternetAddress(DEFAULT_EMAIL));
        return message;
    }
}