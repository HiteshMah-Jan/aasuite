/*
 * Bug.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;
import javax.persistence.*;

import service.util.AbstractIBean;
import service.util.ChartBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import util.DBClient;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CustomerCall")
@UITemplate(template=TemplateTabPage.class, showChart=true, gridCount = 6, criteriaSearch={"customerId","callType","contactPerson"}, columnSearch = {"customerName","contactPerson","callDate","subject","callType"})
@Displays({    
    @Display(name="customerId", label="Customer", type="PopSearch", linktoBean=CustomerContact.class, linktoColumns={"companyName","location"}),
    @Display(name="phoneNumber"),
    @Display(name="callDate", mandatory=true),
    @Display(name="contactPerson"),
    @Display(name="subject"),
    @Display(name="callType", type="Combo", modelCombo={"INITIAL CALL","FOLLOW UP","DEMO","INSTALLATION"}),
    @Display(name="notesBytes", label="Notes", type="TextArea2",gridFieldWidth=5,width=-1,height=200)
})
@ChildRecords({
    @ChildRecord(canSave=false, title="Call History", entity = CustomerCallHistory.class, sql = "SELECT a FROM CustomerCallHistory a WHERE a.customerId=${customerId}")
})
public class CustomerCall extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "customerId", nullable = false)
    public int customerId;
    @Column(name = "callDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date callDate;
    @Column(name = "callTime")
    public String callTime;
    @Column(name = "callDuration")
    public int callDuration;
    @Column(name = "subject")
    public String subject;
    @Column(name = "callType")
    public String callType;
    @Column(name = "callResult")
    public String callResult;
    @Column(name = "phoneNumber")
    public String phoneNumber;
    @Column(name = "contactPerson")
    public String contactPerson;
    @Column(name = "productCode")
    public String productCode;
    @Column(name = "notesBytes", length=4000)
    public String notesBytes;
    @Column(name = "nextCallDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date nextCallDate;
    @Column(name = "nextStartTime")
    public String nextStartTime;
    @Column(name = "nextCallType")
    public String nextCallType;
    @Column(name = "nextDuration")
    public int nextDuration;
    @Column(name = "location")
    public String location;

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "phoneNumber");
	}
	
    public String getNotesBytes() {
        return notesBytes;
    }

    public void setNotesBytes(String notesBytes) {
        this.notesBytes = notesBytes;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getNextCallDate() {
        return nextCallDate;
    }

    public void setNextCallDate(Date nextCallDate) {
        this.nextCallDate = nextCallDate;
    }

    public String getNextCallType() {
        return nextCallType;
    }

    public void setNextCallType(String nextCallType) {
        this.nextCallType = nextCallType;
    }

    public int getNextDuration() {
        return nextDuration;
    }

    public void setNextDuration(int nextDuration) {
        this.nextDuration = nextDuration;
    }

    public String getNextStartTime() {
        return nextStartTime;
    }

    public void setNextStartTime(String nextStartTime) {
        this.nextStartTime = nextStartTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public java.lang.String getProductCode() {
        return productCode;
    }

    public void setProductCode(java.lang.String productCode) {
        this.productCode = productCode;
    }
    
    @Transient
    Customer p;
    
    public String getCustomerName() {
        if (customerId==0) return "";
        p = (Customer) selectFirstCache("SELECT a FROM CustomerContact a WHERE a.personId="+customerId);
        if (p==null) return "";
        return p.getCompanyName();
    }

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }

    public java.lang.String getCallResult() {
        return callResult;
    }

    public void setCallResult(java.lang.String callResult) {
        this.callResult = callResult;
    }

    public java.lang.String getLocation() {
        return location;
    }

    public void setLocation(java.lang.String location) {
        this.location = location;
    }

    @Override
    public Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        CustomerContact cc = (CustomerContact) selectFirstCache("SELECT a FROM CustomerContact a WHERE a.personId="+customerId);
        String loc = cc==null?"":cc.location;
        vec.add(ChartBean.getPieInstance(this, "Call Type Made", "SELECT a.callType, COUNT(a.callType) FROM CustomerCallHistory a WHERE a.customerId="+customerId+" GROUP BY a.callType"));
        vec.add(ChartBean.getPieInstance(this, "Customer By Class","SELECT a.customerClass, COUNT(a.personId) FROM CustomerContact a WHERE a.location='"+loc+"' GROUP BY a.customerClass"));
        return vec;
    } 
    
    public String getEmailSubject() {
        return "TO: "+toString();
    }
    public String getEmailContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("Dear ").append(toString()).append(", \n");
        sb.append("\n\n");
        sb.append("Sincerely Yours,\n");
        return sb.toString();
    }
    public int getEmailCustomerId() {
        return customerId;
    }
    public String getEmailRecipient() {
        getCustomerName();
        if (p!=null) return p.email;
        return "";
    }
}
