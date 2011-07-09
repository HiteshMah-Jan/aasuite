package bean.embedded;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bean.reference.BPProject;
import bean.reference.PaymentIndicator;
import bean.reference.PaymentMethod;
import bean.reference.PaymentTerms;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;

@Embeddable
@UITemplate(template = TemplateEmbedded.class, gridCount = 4, columnSearch = { "journalRemarks", "paymentTerms", "paymentMethod" })
@Displays({
    	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="accounting.", mergeFields={"journalRemarks","paymentTerms","paymentMethod","centralBankId"}, verticalMerge=true),
        @Display(name="journalRemarks", fieldPrefix="accounting."),
        @Display(name="paymentTerms", type="PopSearch", linktoBean=PaymentTerms.class, fieldPrefix="accounting."),
        @Display(name="paymentMethod", type="PopSearch", linktoBean=PaymentMethod.class, fieldPrefix="accounting."),
        @Display(name="centralBankId", fieldPrefix="accounting."),

        @Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="accounting.", mergeFields={"bpProject","cancellationDate","requiredDate","indicator","taxId","orderNumber"}, verticalMerge=true),
        @Display(name="bpProject", type="PopSearch", linktoBean=BPProject.class, fieldPrefix="accounting."),
        @Display(name="cancellationDate", fieldPrefix="accounting."),
        @Display(name="requiredDate", fieldPrefix="accounting."),
        @Display(name="indicator", type="PopSearch", linktoBean=PaymentIndicator.class, fieldPrefix="accounting."),
        @Display(name="taxId", fieldPrefix="accounting."),
        @Display(name="orderNumber", fieldPrefix="accounting."),
        @Display(name="startFromDate", fieldPrefix="accounting.", label="Start From", hideLabel=true, mergeFields={"startFromMonth","startFromDay"}, gridFieldWidth=3),
        @Display(name="startFromMonth", fieldPrefix="accounting.", label="  Month"),
        @Display(name="startFromDay", fieldPrefix="accounting.", label="   Days")
})
public class EmbeddedAccounting extends AbstractIBean {
	public static void main(String[] args) {
		viewEmbedded(EmbeddedAccounting.class);
	}
	
	public String journalRemarks;
	public String paymentTerms;
	public String paymentMethod;
	public String centralBankId;
    @Temporal(value = TemporalType.DATE)
	public Date startFromDate;
	public int startFromMonth;
	public int startFromDay;
	public String bpProject;
    @Temporal(value = TemporalType.DATE)
	public Date cancellationDate;
    @Temporal(value = TemporalType.DATE)
	public Date requiredDate;
	public String indicator;
	public String taxId;
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

	public String getCentralBankId() {
		return centralBankId;
	}

	public void setCentralBankId(String centralBankId) {
		this.centralBankId = centralBankId;
	}

	public Date getStartFromDate() {
		return startFromDate;
	}

	public void setStartFromDate(Date startFromDate) {
		this.startFromDate = startFromDate;
	}

	public int getStartFromMonth() {
		return startFromMonth;
	}

	public void setStartFromMonth(int startFromMonth) {
		this.startFromMonth = startFromMonth;
	}

	public int getStartFromDay() {
		return startFromDay;
	}

	public void setStartFromDay(int startFromDay) {
		this.startFromDay = startFromDay;
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

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String popupSearch(String criteria) {
		return null;
	}
	
	
}
