/*
 * Bug.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.util.Date;
import javax.persistence.*;

import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CustomerContactLead")
@UITemplate(showChart=true, template=TemplateTabPage.class, gridCount = 4, 
    criteriaSearch = {"customerId","willingness","priority"}, 
    columnSearch = {"companyName","willingness","priority","possibleRevenue","revenue","projectDuration"})
@Displays({
    @Display(name="customerId", linktoBean=CustomerContact.class, label="Customer", gridFieldWidth=3, width=-1, type="PopSearch", linktoColumns={"companyName"}),
    @Display(name="willingness", type="NumberCombo", startCount=1, endCount=5),
    @Display(name="priority", type="NumberCombo", startCount=1, endCount=5),
    @Display(name="possibleRevenue"),
    @Display(name="revenue"),
    @Display(name="probabilityPercent"),
    @Display(name="projectDuration"),
    @Display(name="projectStartDate"),
    @Display(name="projectEndDate"),

    @Display(name="companyName", type="Label"),
    @Display(name="customerClass", type="Label"),
    @Display(name="customerType", type="Label"),
    @Display(name="location", type="Label"),
    @Display(name="contactPerson", type="Label"),
    @Display(name="contactNumber", type="Label"),
    @Display(name="email", type="Label"),
    @Display(name="customerHeadcount", type="Label", label="Head Count"),
    @Display(name="address", type="Label")
})
@ActionButtons({
    @ActionButton(name="btnSendProposal", label="Send Proposal")
})
@DisplayGroups({
    @DisplayGroup(title="Detail", gridCount=6, fields={"companyName", "customerClass", "customerType", "location", "contactPerson", "contactNumber", "email", "customerHeadcount", "address"})
})
public class CustomerContactLead extends AbstractCustomerContactLink {
    @Id
    @Column(name = "seq")
    public int seq;
    @Column(name = "customerId")
    public int customerId;
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

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
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

    public static void main(String[] args) {
        view(CustomerContactLead.class);
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "contactName");
	}
}
