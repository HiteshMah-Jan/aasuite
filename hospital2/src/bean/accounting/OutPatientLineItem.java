/*
 * Paymentmethod.java
 *
 * Created on Nov 23, 2007, 8:40:57 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import bean.OutPatient;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import template.screen.TemplateSinglePage;
import template.screen.TemplateTabPage;
import util.DBClient;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "OutPatientLineItem")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, 
		columnSearch = {"useDate", "billingCode", "reason", "amount", "discountReason", "discount", "totalAmount"},
		criteriaSearch = {"useDate", "billingCode", "reason", "discountReason"},
		sumFooter="3,5")
@Displays({
//        @Display(name="seq"),
        @Display(name="useDate",type="Label"),
        @Display(name="billingCode", type="PopSearch", linktoBean=BillingCodeReference.class),
        @Display(name="reason"),
        @Display(name="amount"),
        @Display(name="discountReason"),
        @Display(name="discount")
        })
public class OutPatientLineItem extends AbstractIBean implements Serializable {
	public OutPatientLineItem() {
		useDate = new Date();
	}
	
	@Override
	public void save() {
		if (physician==null && physicianId>0) {
			try {
				physician = DBClient.getFirstRecord("SELECT a FROM Person a WHERE a.personId="+physicianId).toString();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		totalAmount = amount - discount;
		super.save();
		if (parentBean instanceof OutPatient) {
			parentBean.save();
		}
	}

	public static void main(String[] args) {
		view(OutPatientLineItem.class);
	}
	
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "outPatientId")
    public int outPatientId;
    @Column(name = "patientId")
    public int patientId;
    @Column(name = "physicianId")
    public int physicianId;
    @Column(name = "billingCode")
    public String billingCode;
    @Column(name = "reason")
    public String reason;
    @Column(name = "amount")
    public double amount;
    @Column(name = "discountReason")
    public String discountReason;
    @Column(name = "discount")
    public double discount;
    @Column(name = "useDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date useDate;
    @Column(name = "useTime")
    public String useTime;
    @Column(name = "physician")
    public String physician;
    @Column(name = "totalAmount")
    public double totalAmount;

    public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getPhysician() {
		return physician;
	}

	public void setPhysician(String physician) {
		this.physician = physician;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getOutPatientId() {
		return outPatientId;
	}

	public void setOutPatientId(int outPatientId) {
		this.outPatientId = outPatientId;
	}

	public String getBillingCode() {
		return billingCode;
	}

	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDiscountReason() {
		return discountReason;
	}

	public void setDiscountReason(String discountReason) {
		this.discountReason = discountReason;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "outPatientId");
	}
}
