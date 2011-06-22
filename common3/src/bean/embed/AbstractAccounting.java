package bean.embed;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class AbstractAccounting  implements Serializable{
	public String journalRemarks;
	public String paymentTerms;
	public String paymentMethod;
	public String centralBankInd;
	public String bpProject;
    @Temporal(value = TemporalType.DATE)
	public Date cancellationDate;
    @Temporal(value = TemporalType.DATE)
	public Date requiredDate;
	public String indicator;
	public String federalTaxId;
	public String orderNumber;
	public String getJournalRemarks() {
		return journalRemarks;
	}
	public void setJournalRemarks(String journalRemarks) {
		this.journalRemarks = journalRemarks;
	}
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getCentralBankInd() {
		return centralBankInd;
	}
	public void setCentralBankInd(String centralBankInd) {
		this.centralBankInd = centralBankInd;
	}
	public String getBpProject() {
		return bpProject;
	}
	public void setBpProject(String bpProject) {
		this.bpProject = bpProject;
	}
	public Date getCancellationDate() {
		return cancellationDate;
	}
	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}
	public Date getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public String getFederalTaxId() {
		return federalTaxId;
	}
	public void setFederalTaxId(String federalTaxId) {
		this.federalTaxId = federalTaxId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


}
