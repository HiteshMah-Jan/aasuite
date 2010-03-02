/*
 * Airport.java
 *
 * Created on Sep 30, 2007, 8:02:06 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import bean.reference.TraceStatus;
import service.util.AbstractIBean;
import template.*;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CargoMessageInbox")
@UITemplate(template = template.screen.TemplateTabSinglePage.class, gridCount = 6, 
    columnSearch = {"messageType","sendToAddress","receiveFromAddress","processBy","processDate"}, showChart=true)
@Displays({
    @Display(name = "messageType", width=50, type="PopSearch", linktoBean=TraceStatus.class),
    @Display(name = "sendToAddress", width=70, label="Send to"),
    @Display(name = "receiveFromAddress", width=70, label="Rcv from"),
    @Display(name = "processBy", width=70),
    @Display(name = "processDate", gridFieldWidth=5),
    @Display(name = "messageContent", type="TextArea", gridFieldWidth=9, height=100, width=-1),
    @Display(name = "exceptionText", gridFieldWidth=9, type="TextArea", height=250, width=500, addInfoOnly=true, hideLabel=true)
})
@ChildRecords(
    value={
        @ChildRecord(template=ChildTemplateListOnly.class, entity=CargoMessageTransactionDetail.class, sql="SELECT a FROM CargoMessageTransactionDetail a WHERE a.cargoMessageInboxSeq=${seq}", title="Transaction Detail", fieldMapping={"seq","cargoMessageInboxSeq"})
    },
    info={
        @ParentAddInfo(gridCount=10, title="Exeption Info", displayFields={"exceptionText"})
    }
)
@template.ActionButtons({
    @template.ActionButton(name="btnSendMessage", label="Send Message"),
    @template.ActionButton(name="btnProcessIncoming", label="Process Incoming")
})
public class CargoMessageInbox extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "messageType", length=100)
    public String messageType;
    @Column(name = "sendToAddress", length=100)
    public String sendToAddress;
    @Column(name = "receiveFromAddress", length=100)
    public String receiveFromAddress;
    @Column(name = "processBy", length=100)
    public String processBy;
    @Column(name = "processDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date processDate;
    @Column(name = "active")
    public boolean active;
    @Column(name = "transmitCount")
    public int transmitCount;
    @Column(name = "messageContent", nullable = false, length=4000)
    public String messageContent;
    @Column(name = "exceptionText", length=4000)
    public String exceptionText;
    @Column(name = "acknowledge")
    public boolean acknowledge;

    public boolean isAcknowledge() {
        return acknowledge;
    }

    public void setAcknowledge(boolean acknowledge) {
        this.acknowledge = acknowledge;
    }

    public String getExceptionText() {
        return exceptionText;
    }

    public void setExceptionText(String exceptionText) {
        this.exceptionText = exceptionText;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getProcessBy() {
        return processBy;
    }

    public void setProcessBy(String processBy) {
        this.processBy = processBy;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public String getReceiveFromAddress() {
        return receiveFromAddress;
    }

    public void setReceiveFromAddress(String receiveFromAddress) {
        this.receiveFromAddress = receiveFromAddress;
    }

    public String getSendToAddress() {
        return sendToAddress;
    }

    public void setSendToAddress(String sendToAddress) {
        this.sendToAddress = sendToAddress;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getTransmitCount() {
        return transmitCount;
    }

    public void setTransmitCount(int transmitCount) {
        this.transmitCount = transmitCount;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
