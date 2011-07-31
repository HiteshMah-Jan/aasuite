/*
 * Purchaseorder.java
 *
 * Created on Nov 22, 2007, 6:07:49 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.purchasing;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateDefault;
import bean.Customer;
import bean.embedded.EmbeddedAccounting;
import bean.purchasing.embedded.EmbeddedPurchaseCreditMemoLogistics;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "PurchaseCreditMemo")
@UITemplate(template = TemplateDefault.class, gridCount = 6, columnSearch = {"accountType", "deposit", "logistics.billToContact"})
@Displays({
    	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="logistics.", 
    			mergeFields={"customerId","contactPerson","customerRefNo","localCurrency","purchaseEmployeeId","owner","remarks","itemOrServiceType"}, verticalMerge=true),
        @Display(name="customerId", label="Customer", type = "PopSearch", linktoBean=Customer.class),
        @Display(name="contactPerson"),
        @Display(name="customerRefNo"),
        @Display(name="localCurrency"),

//    	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="logistics.", 
//    			mergeFields={"purchaseEmployeeId","owner","remarks","itemOrServiceType"}, verticalMerge=true),
        @Display(name="purchaseEmployeeId", label="Purchase Employee", type="Combo", sqlCombo="SELECT a FROM Employee a"),
        @Display(name="owner"),
        @Display(name="remarks"),
        @Display(name="itemOrServiceType", type="Combo", modelCombo={"Item", "Service"}),
        
    	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="logistics.", 
    			mergeFields={"status","postingDate","deliveryDate","documentDate"}, verticalMerge=true),
        @Display(name="status"),
        @Display(name="postingDate"),
        @Display(name="deliveryDate"),
        @Display(name="documentDate"),
        
//    	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="logistics.", 
//    			mergeFields={"totalBeforeDiscount","discountPercentage","discountAmount","freightAmount","rounding","tax","totalDownpayment","totalAmount","appliedAmount","balanceDue"}, verticalMerge=true),
        @Display(name="totalBeforeDiscount", addInfoOnly=true),
        @Display(name="discountPercentage", addInfoOnly=true),
        @Display(name="discountAmount", addInfoOnly=true),
        @Display(name="freightAmount", addInfoOnly=true),
        @Display(name="rounding", addInfoOnly=true),
        @Display(name="tax", addInfoOnly=true),
        @Display(name="totalDownpayment", addInfoOnly=true),
        @Display(name="totalAmount", addInfoOnly=true),
        @Display(name="appliedAmount", addInfoOnly=true),
        @Display(name="balanceDue", addInfoOnly=true),

        @Display(name="logistics", addInfoOnly=true, type="Embedded", gridFieldWidth=2, noLabel=true),
        @Display(name="accounting", addInfoOnly=true, type="Embedded", gridFieldWidth=2, noLabel=true)
})
@ChildRecords(
		value={
				@ChildRecord(template=ChildTemplateListPopupDownButton.class,entity=PurchaseCreditMemoItem.class, fieldMapping={"seq","purchaseCreditMemoId"}, sql="SELECT a FROM PurchaseCreditMemoItem a WHERE a.purchaseCreditMemoId=${seq}", title="Contents")
		},
		info={
				@ParentAddInfo(title = "Calculation Details", gridCount = 4, displayFields = {"totalBeforeDiscount","discountPercentage","discountAmount","freightAmount","rounding","tax","totalDownpayment","totalAmount","appliedAmount","balanceDue"}),
				@ParentAddInfo(title = "Logistics", gridCount = 2, displayFields = {"logistics"}),
				@ParentAddInfo(title = "Accounting", gridCount = 2, displayFields = {"accounting"})
		})
@ActionButtons({
    @ActionButton(name="btnDeliver", label="Deliver", parentOnly=true),
    @ActionButton(name="btnInvoice", label="Invoice", parentOnly=true)
})
public class PurchaseCreditMemo extends AbstractIBean implements Serializable {
	public static void main(String[] args) {
		view(PurchaseCreditMemo.class);
	}
	
	@Override
    public void save() {
		this.logistics = ((PurchaseCreditMemo)myClone).logistics;
		this.accounting = ((PurchaseCreditMemo)myClone).accounting;
        super.save();
    }

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    public int customerId;
    public String contactPerson;
    public String customerRefNo;
    public String localCurrency;
    public String status;
    @Temporal(value = TemporalType.DATE)
    public Date postingDate;
    @Temporal(value = TemporalType.DATE)
    public Date deliveryDate;
    @Temporal(value = TemporalType.DATE)
    public Date documentDate;
    public String itemOrServiceType;
    
    public int purchaseEmployeeId;
    public String owner;
    public String remarks;
    public double totalBeforeDiscount;
    public double discountPercentage;
    public double discountAmount;
    public double freightAmount;
    public int rounding;
    public double tax;
    public double totalAmount;
    public double totalDownpayment;
    public double appliedAmount;
    public double balanceDue;
   
    
//    below are for logistics
    @Embedded
    public EmbeddedPurchaseCreditMemoLogistics logistics = new EmbeddedPurchaseCreditMemoLogistics();
    @Embedded
    public EmbeddedAccounting accounting = new EmbeddedAccounting();
    
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public double getTotalDownpayment() {
		return totalDownpayment;
	}

	public void setTotalDownpayment(double totalDownpayment) {
		this.totalDownpayment = totalDownpayment;
	}

	public double getAppliedAmount() {
		return appliedAmount;
	}

	public void setAppliedAmount(double appliedAmount) {
		this.appliedAmount = appliedAmount;
	}

	public double getBalanceDue() {
		return balanceDue;
	}

	public void setBalanceDue(double balanceDue) {
		this.balanceDue = balanceDue;
	}

	public double getFreightAmount() {
		return freightAmount;
	}

	public void setFreightAmount(double freightAmount) {
		this.freightAmount = freightAmount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCustomerRefNo() {
		return customerRefNo;
	}

	public void setCustomerRefNo(String customerRefNo) {
		this.customerRefNo = customerRefNo;
	}

	public String getLocalCurrency() {
		return localCurrency;
	}

	public void setLocalCurrency(String localCurrency) {
		this.localCurrency = localCurrency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getItemOrServiceType() {
		return itemOrServiceType;
	}

	public void setItemOrServiceType(String itemOrServiceType) {
		this.itemOrServiceType = itemOrServiceType;
	}

	public int getPurchaseEmployeeId() {
		return purchaseEmployeeId;
	}

	public void setPurchaseEmployeeId(int purchaseEmployeeId) {
		this.purchaseEmployeeId = purchaseEmployeeId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getTotalBeforeDiscount() {
		return totalBeforeDiscount;
	}

	public void setTotalBeforeDiscount(double totalBeforeDiscount) {
		this.totalBeforeDiscount = totalBeforeDiscount;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public int getRounding() {
		return rounding;
	}

	public void setRounding(int rounding) {
		this.rounding = rounding;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public EmbeddedPurchaseCreditMemoLogistics getLogistics() {
		return logistics;
	}

	public void setLogistics(EmbeddedPurchaseCreditMemoLogistics logistics) {
		this.logistics = logistics;
	}

	public EmbeddedAccounting getAccounting() {
		return accounting;
	}

	public void setAccounting(EmbeddedAccounting accounting) {
		this.accounting = accounting;
	}

	@Override	
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
