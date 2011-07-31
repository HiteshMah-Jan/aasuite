package bean.purchasing;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bean.BusinessPartner;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabSinglePage;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "LandedCost")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 6, columnSearch = {"seq"})
@Displays({
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="landed.", 
			mergeFields={"seq","broker","vendor","reference","fileNo","customsAffectsInventory"}, verticalMerge=true),
	@Display(name="seq", type="Label", label="Landed Id"),
	@Display(name="broker", type="PopSearch", linktoBean=BusinessPartner.class),
	@Display(name="vendor", type="PopSearch", linktoBean=BusinessPartner.class),
	@Display(name="reference"),
	@Display(name="fileNo"),
	@Display(name="customsAffectsInventory"),

	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="landed.", 
			mergeFields={"postingDate","dueDate"}, verticalMerge=true),
	@Display(name="postingDate"),
	@Display(name="dueDate"),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="landed.", 
			mergeFields={"projectedCustoms","actualCustoms","totalFreightCharges","amountToBalance","beforeTax","tax1","tax2","totalAmount"}, verticalMerge=true),
	@Display(name="projectedCustoms"),
	@Display(name="actualCustoms"),
	@Display(name="totalFreightCharges"),
	@Display(name="amountToBalance"),
	@Display(name="beforeTax"),
	@Display(name="tax1"),
	@Display(name="tax2"),
	@Display(name="totalAmount"),

	@Display(name="remarks", gridFieldWidth=5, width=-1)
})
@ChildRecords(
		value={
				@ChildRecord(template=ChildTemplateListPopupDownButton.class,entity=LandedCostDetail.class, fieldMapping={"seq","landedCostId"}, sql="SELECT a FROM LandedCostDetail a WHERE a.landedCostId=${seq}", title="Details"),
				@ChildRecord(template=ChildTemplateListPopupDownButton.class,entity=LandedCostItem.class, fieldMapping={"seq","landedCostId"}, sql="SELECT a FROM LandedCostItem a WHERE a.landedCostId=${seq}", title="Items"),
				@ChildRecord(template=ChildTemplateListPopupDownButton.class,entity=LandedCostOtherCostItem.class, fieldMapping={"seq","landedCostId"}, sql="SELECT a FROM LandedCostOtherCostItem a WHERE a.landedCostId=${seq}", title="Other Items"),
				@ChildRecord(template=ChildTemplateListPopupDownButton.class,entity=LandedCostVendor.class, fieldMapping={"seq","landedCostId"}, sql="SELECT a FROM LandedCostVendor a WHERE a.landedCostId=${seq}", title="Vendors")
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
