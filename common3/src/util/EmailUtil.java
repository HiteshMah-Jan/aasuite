/*
 * EmailUtil.java
 *
 * Created on Jan 5, 2008, 12:49:02 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Provider;
import javax.mail.Session;
import javax.swing.SwingUtilities;

/**
 *
 * @author Budoy Entokwa
 */
public class EmailUtil {

    public static String host;
    public static String hostUsername;
    public static String hostPassword;

    public static void sendEmail(final String subject, final String body, final String to, final String from, final File f) {
        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    // Get system properties
                    java.util.Properties props = java.lang.System.getProperties();

                    // Setup mail server
                    props.put("mail.smtp.host", host);

                    // Get session
                    Session session = Session.getInstance(props, new Authenticator() {

                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(hostUsername, hostPassword);
                        }
                    });
                    session.setDebug(true);

                    // Define message
                    javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(session);
                    message.setFrom(new javax.mail.internet.InternetAddress(from));
                    message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(to.toLowerCase()));
                    message.setSubject(subject);

                    // create the message part
                    javax.mail.internet.MimeBodyPart messageBodyPart = new javax.mail.internet.MimeBodyPart();

                    //fill message
                    messageBodyPart.setText(body);

                    javax.mail.Multipart multipart = new javax.mail.internet.MimeMultipart();
                    multipart.addBodyPart(messageBodyPart);

                    Log.info("FILE SIZE == ",f.length());
                    // Part two is attachment
                    messageBodyPart = new javax.mail.internet.MimeBodyPart();
                    javax.activation.DataSource source = new javax.activation.FileDataSource(f);
                    messageBodyPart.setDataHandler(new javax.activation.DataHandler(source));
                    messageBodyPart.setFileName(f.getName());
                    multipart.addBodyPart(messageBodyPart);

                    // Put parts in message
                    message.setContent(multipart);

                    // Send the message
                    javax.mail.Transport trans = session.getTransport("smtp");
                    trans.send(message);
                } catch (Exception ex) {
                    Logger.getLogger("global").log(Level.SEVERE, "FAILED SENDING EMAIL", ex);
                }
            }
        });
        t.start();
    }

    public static void sendEmail(final String subject, final String body, final String to, final String from) {
        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    // Get system properties
                    java.util.Properties props = java.lang.System.getProperties();

                    // Setup mail server
                    props.put("mail.smtp.host", host);

                    // Get session
                    Session session = Session.getInstance(props, new Authenticator() {

                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(hostUsername, hostPassword);
                        }
                    });
                    session.setDebug(true);

                    // Define message
                    javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(session);
                    message.setFrom(new javax.mail.internet.InternetAddress(from));
                    message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(to.toLowerCase()));
                    message.setSubject(subject);
                    message.setText(body);

                    // Send the message
                    javax.mail.Transport trans = session.getTransport("smtp");
                    trans.connect();
                    trans.send(message);
                    trans.close();
                } catch (Exception ex) {
                    Logger.getLogger("global").log(Level.SEVERE, "FAILED SENDING EMAIL", ex);
                }
            }
        });
        t.start();
    }
}
