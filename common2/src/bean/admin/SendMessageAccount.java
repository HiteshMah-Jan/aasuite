/*
 * AclDuty.java
 *
 * Created on Sep 30, 2007, 8:02:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "SendMessageAccount")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"code"})
@Displays({
        @Display(name="code", type="Combo", modelCombo={"GOOGLE","YAHOO","EMAIL"}),
        @Display(name="xmppHost"),
        @Display(name="xmppPort"),
        @Display(name="xmppDns"),
        @Display(name="xmppUsername"),
        @Display(name="xmppPassword"),
        @Display(name="emailHost"),
        @Display(name="emailUser"),
        @Display(name="emailPassword"),
        @Display(name="emailAccountSender"),
        @Display(name="preMessage"),
        @Display(name="description")
})
public class SendMessageAccount extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length = 50)
    public String code;
    @Column(name = "xmppHost")
    public String xmppHost;
    @Column(name = "xmppPort")
    public int xmppPort;
    @Column(name = "xmppDns")
    public String xmppDns;
    @Column(name = "xmppUsername")
    public String xmppUsername;
    @Column(name = "xmppPassword")
    public String xmppPassword;
    @Column(name = "emailHost")
    public String emailHost;
    @Column(name = "emailUser")
    public String emailUser;
    @Column(name = "emailPassword")
    public String emailPassword;
    @Column(name = "emailAccountSender")
    public String emailAccountSender;
    @Column(name = "preMessage", length = 10)
    public String preMessage;
    @Column(name = "description", length = 100)
    public String description;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return code;
    }

    public java.lang.String getXmppHost() {
        return xmppHost;
    }

    public void setXmppHost(java.lang.String xmppHost) {
        this.xmppHost = xmppHost;
    }

    public int getXmppPort() {
        return xmppPort;
    }

    public void setXmppPort(int xmppPort) {
        this.xmppPort = xmppPort;
    }

    public java.lang.String getXmppDns() {
        return xmppDns;
    }

    public void setXmppDns(java.lang.String xmppDns) {
        this.xmppDns = xmppDns;
    }

    public java.lang.String getXmppUsername() {
        return xmppUsername;
    }

    public void setXmppUsername(java.lang.String xmppUsername) {
        this.xmppUsername = xmppUsername;
    }

    public java.lang.String getXmppPassword() {
        return xmppPassword;
    }

    public void setXmppPassword(java.lang.String xmppPassword) {
        this.xmppPassword = xmppPassword;
    }

    public java.lang.String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(java.lang.String emailHost) {
        this.emailHost = emailHost;
    }

    public java.lang.String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(java.lang.String emailUser) {
        this.emailUser = emailUser;
    }

    public java.lang.String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(java.lang.String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public java.lang.String getEmailAccountSender() {
        return emailAccountSender;
    }

    public void setEmailAccountSender(java.lang.String emailAccountSender) {
        this.emailAccountSender = emailAccountSender;
    }

    public static AbstractIBean getObject(String code) {
        return (SendMessageAccount) AbstractIBean.firstRecord("SELECT a FROM SendMessageAccount a WHERE a.code='" , code , "'");
    }

    public static SendMessageAccount getGoogleAccount() {
        return (SendMessageAccount) getObject("GOOGLE");
    }

    public static SendMessageAccount getYahooAccount() {
        return (SendMessageAccount) getObject("YAHOO");
    }

    public static SendMessageAccount getEmailAccount() {
        return (SendMessageAccount) getObject("EMAIL");
    }

    public java.lang.String getPreMessage() {
        return preMessage;
    }

    public void setPreMessage(java.lang.String preMessage) {
        this.preMessage = preMessage;
    }

    public static SendMessageAccount createSendMessageAccountObj(String code, String host, int port, String login, String password) {
        SendMessageAccount conf = new SendMessageAccount();
        conf.code = code;
        conf.xmppHost = host;
        conf.xmppPort = port;
        conf.xmppUsername = login;
        conf.xmppPassword = password;
        return conf;
    }
    
    @Override
    public void runSetup() {
        createSendMessageAccountObj("GOOGLE", "talk.google.com", 5222, "sales.softlabs@gmail.com", "pass1word").save();
    }    
}

