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
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import util.DBClient;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CustomerCallHistory")
@UITemplate(gridCount = 6, columnSearch = {"callDate","subject","callType","notesBytes"})
@Displays({
   
    @Display(name="callDate", mandatory=true),
    @Display(name="callTime",type="Time",width=80),
    @Display(name="callType", type="Combo", modelCombo={"INITIAL CALL","FOLLOW UP","DEMO","INSTALLATION"}),
    @Display(name="notesBytes", label="Notes", type="TextArea2",gridFieldWidth=5,width=-1,height=200)
})
public class CustomerCallHistory extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "customerCallId", nullable = false)
    public int customerCallId;
    @Column(name = "customerId", nullable = false)
    public int customerId;
    @Column(name = "callDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date callDate;
    @Column(name = "callTime")
    public String callTime;
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
    @Column(name = "notesBytes", length=4000)
    public String notesBytes;
    @Column(name = "location")
    public String location;

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public String getCallResultNotes() {
        return notesBytes;
    }

    public void setCallResultNotes(String notesBytes) {
        this.notesBytes = notesBytes;
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

    public int getCustomerCallId() {
        return customerCallId;
    }

    public void setCustomerCallId(int customerCallId) {
        this.customerCallId = customerCallId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getNotesBytes() {
        return notesBytes;
    }

    public void setNotesBytes(String notesBytes) {
        this.notesBytes = notesBytes;
    }
    
    public static void createCallHistory(CustomerCall call) {
        CustomerCallHistory history = new CustomerCallHistory();
        history.setCustomerCallId(call.getSeq());
        history.setCallDate(call.getCallDate());
        history.setNotesBytes(call.getNotesBytes());
        history.setCallTime(call.getCallTime());
        history.setCallType(call.getCallType());
        history.setContactPerson(call.getContactPerson());
        history.setCustomerId(call.getCustomerId());
        history.setPhoneNumber(call.getPhoneNumber());
        history.setSubject(call.getSubject());
        history.setCallResult(call.getCallResult());
        DBClient.persistBean(history);

        Object obj = DBClient.getSingleColumn("SELECT COUNT(*) FROM Customer_Call WHERE customerId="+call.getCustomerId());
        try {
            CustomerContact contact = (CustomerContact) DBClient.getFirstRecord("SELECT a FROM CustomerContact a WHERE a.personId="+call.getCustomerId());
            contact.setCallCount(Integer.parseInt(obj.toString()));
            DBClient.persistBean(contact);
        } catch (Exception e) {
        }
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
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "phoneNumber");
	}
	
	@Override
	public void setupIndex() {
		runIndex(1, "customerId");
	}
}
