package bean.purchasing;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "LandedCost")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="broker"),
	@Display(name="vendor"),
	@Display(name="postingDate"),
	@Display(name="dueDate"),
	@Display(name="reference"),
	@Display(name="fileNo"),
	@Display(name="projectedCustoms"),
	@Display(name="actualCustoms"),
	@Display(name="totalFreightCharges"),
	@Display(name="amountToBalance"),
	@Display(name="beforeTax"),
	@Display(name="tax1"),
	@Display(name="tax2"),
	@Display(name="totalAmount"),
	@Display(name="customsAffectsInventory"),
	@Display(name="remarks")
})

public class LandedCost extends AbstractIBean {
	@Id
	public Integer seq;
	public String broker;
	public String vendor;
    @Temporal(value = TemporalType.DATE)
	public Date postingDate;
    @Temporal(value = TemporalType.DATE)
	public Date dueDate;
	public String reference;
	public String fileNo;
	public double projectedCustoms;
	public double actualCustoms;
	public double totalFreightCharges;
	public double amountToBalance;
	public double beforeTax;
	public double tax1;
	public double tax2;
	public double totalAmount;
	public boolean customsAffectsInventory;
	public String remarks;
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public double getProjectedCustoms() {
		return projectedCustoms;
	}

	public void setProjectedCustoms(double projectedCustoms) {
		this.projectedCustoms = projectedCustoms;
	}

	public double getActualCustoms() {
		return actualCustoms;
	}

	public void setActualCustoms(double actualCustoms) {
		this.actualCustoms = actualCustoms;
	}

	public double getTotalFreightCharges() {
		return totalFreightCharges;
	}

	public void setTotalFreightCharges(double totalFreightCharges) {
		this.totalFreightCharges = totalFreightCharges;
	}

	public double getAmountToBalance() {
		return amountToBalance;
	}

	public void setAmountToBalance(double amountToBalance) {
		this.amountToBalance = amountToBalance;
	}

	public double getBeforeTax() {
		return beforeTax;
	}

	public void setBeforeTax(double beforeTax) {
		this.beforeTax = beforeTax;
	}

	public double getTax1() {
		return tax1;
	}

	public void setTax1(double tax1) {
		this.tax1 = tax1;
	}

	public double getTax2() {
		return tax2;
	}

	public void setTax2(double tax2) {
		this.tax2 = tax2;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public boolean isCustomsAffectsInventory() {
		return customsAffectsInventory;
	}

	public void setCustomsAffectsInventory(boolean customsAffectsInventory) {
		this.customsAffectsInventory = customsAffectsInventory;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static void main(String[] args) {
		view(LandedCost.class);
	}
	
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
