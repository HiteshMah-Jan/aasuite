/*
 * Patientbillingitem.java
 * 
 * Created on Oct 29, 2007, 6:58:00 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "PatientBillingItem")
@UITemplate(template = TemplateSinglePage.class, columnSearch = {"patientBillingItemId", "patientBillingId", "amount"})
@Displays({
        @Display(name="patientBillingItemId"),
        @Display(name="amount"),
        @Display(name="discount"),
        @Display(name="discountReason"),
        @Display(name="netAmount"),
        @Display(name="reason"),
        @Display(name="patientBillingId"),
        @Display(name="useDate")
})
public class PatientBillingItem extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "amount")
    public double amount;
    @Column(name = "discount")
    public double discount;
    @Column(name = "discountReason", length = 150)
    public String discountReason;
    @Column(name = "netAmount")
    public double netAmount;
    @Column(name = "reason", length = 150)
    public String reason;
    @Column(name = "outPatientId")
    public int outPatientId;
    @Column(name = "patientId")
    public int patientId;
    @Column(name = "useDate")
    @Temporal(TemporalType.DATE)
    public Date useDate;

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

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public java.lang.String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(java.lang.String discountReason) {
        this.discountReason = discountReason;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
