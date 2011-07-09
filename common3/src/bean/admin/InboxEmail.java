/*
 * InboxEmail.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

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
@Table(name = "InboxEmail")
@UITemplate(template = TemplateDefault.class, columnSearch = {"seq", "emailId", "sender"})
@Displays({
        @Display(name="seq"),
        @Display(name="emailId"),
        @Display(name="sender"),
        @Display(name="carbonCopy"),
        @Display(name="subject"),
        @Display(name="content"),
        @Display(name="receivedDate"),
        @Display(name="extractedDate"),
        @Display(name="extractedTime")
})
public class InboxEmail extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "emailId")
    public int emailId;
    @Column(name = "sender", length = 250)
    public String sender;
    @Column(name = "carbonCopy", length = 250)
    public String carbonCopy;
    @Column(name = "subject", length = 250)
    public String subject;
    @Lob
    @Column(name = "content")
    public byte[] content;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "receivedDate")
    public Date receivedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "extractedDate")
    public Date extractedDate;
    @Column(name = "extractedTime", length = 4)
    public String extractedTime;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    @Override
    public String toString() {
        return subject;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentString() {
        if (content == null) {
            return "";
        }
        return new String(content);
    }

    public void setContentString(String content) {
        if (content == null) {
            this.content = null;
        }
        this.content = content.getBytes();
    }

    public String getCarbonCopy() {
        return carbonCopy;
    }

    public void setCarbonCopy(String carbonCopy) {
        this.carbonCopy = carbonCopy;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public Date getExtractedDate() {
        return extractedDate;
    }

    public void setExtractedDate(Date extractedDate) {
        this.extractedDate = extractedDate;
    }

    public String getExtractedTime() {
        return extractedTime;
    }

    public void setExtractedTime(String extractedTime) {
        this.extractedTime = extractedTime;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
