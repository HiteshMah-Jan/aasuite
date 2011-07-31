package bean.production.embedded;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateEmbedded;

import com.sun.star.bridge.oleautomation.Date;

@Embeddable
@UITemplate(template= TemplateEmbedded.class, gridCount = 4, columnSearch = {"actualComponentCost"})
@Displays({
	@Display(name="dummyField", type="MergePanel", label="Cost", fieldPrefix="production.", 
			mergeFields={"costActualComponent","costActualAdditional","costActualProduct","costTotalVariance","costVariancePerProduct","costVariancePercent","journalRemark"}, verticalMerge=true),
	@Display(name="costActualComponent", fieldPrefix="production.", label="Actual Component Cost"),
	@Display(name="costActualAdditional", fieldPrefix="production.",label="Actual Additional Cost"),
	@Display(name="costActualProduct", fieldPrefix="production.", label="Actual Product Cost"),
	@Display(name="costTotalVariance", fieldPrefix="production.", label="Total Variance"),
	@Display(name="costVariancePerProduct", fieldPrefix="production.",label="Variance Per Product"),
	@Display(name="costVariancePercent", fieldPrefix="production.", label="Variance %"),
	@Display(name="journalRemark", fieldPrefix="production.", label="Journal Remark"),
	
	@Display(name="dummyField", type="MergePanel", label="Quantity", fieldPrefix="prodcution.", mergeFields={"quantityPlanned","quantityCompleted","quantityRejected"}, verticalMerge=true),
	@Display(name="quantityPlanned", fieldPrefix="production.", label="Planned Quantity"),
	@Display(name="quantityCompleted", fieldPrefix="production.", label="Completed Quantity"),
	@Display(name="quantityRejected", fieldPrefix="production.", label="Rejected Quantity"),
	
	@Display(name="dummyField", type="MergePanel", label="Dates", fieldPrefix="production.", mergeFields={"dueDate","actualClosingDate","overDue"}, verticalMerge=true),
	@Display(name="dueDate", fieldPrefix="production.", label="Due Date"),
	@Display(name="actualClosingDate", fieldPrefix="production.", label="Actual Closing Date"),
	@Display(name="overDue", fieldPrefix="production."),
})
public class EmbeddedProductionOrderSummary extends AbstractIBean {	
	public static void main(String[] args) {
		viewEmbedded(EmbeddedProductionOrderSummary.class);
	}
	public String costActualComponent;
	public String costActualAdditional;
	public String costActualProduct;
	public String costTotalVariance;
	public String costVariancePerProduct;
	public String costVariancePercent;

	public String journalRemark;

	public double quantityPlanned;
	public double quantityCompleted;
	public double quantityRejected;
	@Temporal(value = TemporalType.DATE)
	public Date dueDate;
	@Temporal(value = TemporalType.DATE)
	public Date actualClosingDate;
	@Temporal(value = TemporalType.DATE)
	public Date overDue;
	
	public String getCostActualComponent() {
		return costActualComponent;
	}

	public void setCostActualComponent(String costActualComponent) {
		this.costActualComponent = costActualComponent;
	}

	public String getCostActualAdditional() {
		return costActualAdditional;
	}

	public void setCostActualAdditional(String costActualAdditional) {
		this.costActualAdditional = costActualAdditional;
	}

	public String getCostActualProduct() {
		return costActualProduct;
	}

	public void setCostActualProduct(String costActualProduct) {
		this.costActualProduct = costActualProduct;
	}

	public String getCostTotalVariance() {
		return costTotalVariance;
	}

	public void setCostTotalVariance(String cosTotalVariance) {
		this.costTotalVariance = cosTotalVariance;
	}

	public String getCostVariancePerProduct() {
		return costVariancePerProduct;
	}

	public void setCostVariancePerProduct(String costVariancePerProduct) {
		this.costVariancePerProduct = costVariancePerProduct;
	}

	public String getCostVariancePercent() {
		return costVariancePercent;
	}

	public void setVariancePercent(String costVariancePercent) {
		this.costVariancePercent = costVariancePercent;
	}

	public String getJournalRemark() {
		return journalRemark;
	}

	public void setJournalRemark(String journalRemark) {
		this.journalRemark = journalRemark;
	}

	public Double getquantityPlanned() {
		return quantityPlanned;
	}

	public void setpQuantityPlanned(Double quantityPlanned) {
		this.quantityPlanned = quantityPlanned;
	}

	public Double getQuantityCompleted() {
		return quantityCompleted;
	}

	public void setCompletedQuantity(Double quantityCompleted) {
		this.quantityCompleted = quantityCompleted;
	}

	public Double getQuantityRejected() {
		return quantityRejected;
	}

	public void setquantityRejected(Double quantityRejected) {
		this.quantityRejected = quantityRejected;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getActualClosingDate() {
		return actualClosingDate;
	}

	public void setActualClosingDate(Date actualClosingDate) {
		this.actualClosingDate = actualClosingDate;
	}

	public Date getOverDue() {
		return overDue;
	}

	public void setOverDue(Date overDue) {
		this.overDue = overDue;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
