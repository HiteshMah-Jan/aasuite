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
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import service.util.ChartBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Person")
@DiscriminatorValue("CUSTOMER CONTACT")
@UITemplate(template=TemplateTabPage.class, gridCount = 4, 
    criteriaSearch = {"companyName","location","customerClass"}, 
    columnSearch = {"companyName","personType","callCount","location","customerClass","customerType"})
@Displays({
    @Display(name="companyName",mandatory=true,gridFieldWidth=3,width=-1),
    @Display(name="customerClass", type="Combo", modelCombo={"SCHOOL","HOSPITAL","WATER DISTRICT"},width=250),
    @Display(name="customerType", type="Combo", modelCombo={"INITIAL","FOLLOW UP","PRESENTATION","SALES ACCOUNT","NEGATIVE"},width=250),
    @Display(name="willingness", type="NumberCombo", startCount=1, endCount=5,width=250),
    @Display(name="location", type="Combo", sqlCombo="SELECT a FROM Location a ORDER BY a.location",width=250),
    @Display(name="contactPerson",width=-1),
    @Display(name="contactNumber",width=-1),
    @Display(name="email",upCase=false,width=-1),
    @Display(name="customerHeadcount"),
    @Display(name="address",gridFieldWidth=3,width=-1)
})
@ChildRecords({
    @ChildRecord(fieldMapping={"personId","personId"}, title="Call History", entity = CustomerCallHistory.class, sql = "SELECT a FROM CustomerCallHistory a WHERE a.customerId=${personId}")
})
@ActionButtons({
    @ActionButton(name="btnSendProposal", label="Send Proposal")
})
public class CustomerContact extends Customer implements Serializable {
    @Column(name = "callCount")
    public int callCount;
    @Column(name = "location")
    public String location;
    @Column(name = "sendProposal")
    public boolean sendProposal;
    @Column(name = "customerType")
    public String customerType;
    @Column(name = "willingness")
    public int willingness;
    @Column(name = "priority")
    public int priority;
    @Column(name = "possibleRevenue")
    public double possibleRevenue;
    @Column(name = "revenue")
    public double revenue;
    @Column(name = "industry")
    public String industry;
    @Column(name = "probabilityPercent")
    public int probabilityPercent;
    @Column(name = "stage")
    public String stage;
    @Column(name = "projectDuration")
    public int projectDuration;
    @Column(name = "projectStartDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date projectStartDate;
    @Column(name = "projectEndDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date projectEndDate;
    @Column(name = "campaign")
    public String campaign;
    @Column(name = "nextCallDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date nextCallDate;
    @Column(name = "note", length=4000)
    public String note;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "companyName", "location", "customerType");
    }

    public Date getNextCallDate() {
        return nextCallDate;
    }

    public void setNextCallDate(Date nextCallDate) {
        this.nextCallDate = nextCallDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static void main(String[] args) {
        view(CustomerContact.class);
    }
    
    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public double getPossibleRevenue() {
        return possibleRevenue;
    }

    public void setPossibleRevenue(double possibleRevenue) {
        this.possibleRevenue = possibleRevenue;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getProbabilityPercent() {
        return probabilityPercent;
    }

    public void setProbabilityPercent(int probabilityPercent) {
        this.probabilityPercent = probabilityPercent;
    }

    public int getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(int projectDuration) {
        this.projectDuration = projectDuration;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getWillingness() {
        return willingness;
    }

    public void setWillingness(int willingness) {
        this.willingness = willingness;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public boolean isSendProposal() {
        return sendProposal;
    }

    public void setSendProposal(boolean sendProposal) {
        this.sendProposal = sendProposal;
    }

    public int getCallCount() {
        return callCount;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }

    public java.lang.String getLocation() {
        return location;
    }

    public void setLocation(java.lang.String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return companyName;
    }
    
    @Override
    public Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        int i = 0;
        if (personId!=null) {
            i = personId;
        }
        vec.add(ChartBean.getPieInstance(this, "Call Type Made", "SELECT a.callType, COUNT(a.callType) FROM CustomerCallHistory a WHERE a.customerId="+i+" GROUP BY a.callType"));
        vec.add(ChartBean.getPieInstance(this, "Customer By Class","SELECT a.customerClass, COUNT(a.personId) FROM CustomerContact a WHERE a.location='"+location+"' GROUP BY a.customerClass"));
        return vec;
    }  
}
