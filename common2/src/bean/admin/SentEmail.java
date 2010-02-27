/*
 * SentEmail.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import bean.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "SentEmail")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, orderBy="a.sentDate DESC",
	criteriaSearch = {"customerId","recipient","sendDate"},
	columnSearch = {"customer","recipient","subject","sendDate"})
@Displays({
    @Display(name="content", type="TextArea", width=600, gridFieldWidth=3)
})
public class SentEmail extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "customerId")
    public int customerId;
    @Column(name = "recipient", length = 250)
    public String recipient;
    @Column(name = "subject", length = 250)
    public String subject;
    @Column(name = "content", length = 4000)
    public String content;
    @Column(name = "attachment", length = 200)
    public String attachment;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "sentDate")
    public Date sentDate;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
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

    @Override
    public String toString() {
        return subject;
    }

    @Transient
    Customer customer;

    public String getCustomer() {
        if (customer == null) {
            customer = (Customer) AbstractIBean.firstRecord("SELECT a FROM Customer a WHERE a.personId=" + customerId);
        }
        if (customer == null) {
            return "";
        }
        return customer.getCompanyName();
    }
}
