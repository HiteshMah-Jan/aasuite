/*
 * Invoice.java
 *
 * Created on Nov 29, 2007, 6:17:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import bean.Customer;
import bean.Employee;
import bean.admin.AppConfig;
import bean.reference.AccountType;
import bean.reference.PaymentMethod;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.report.AbstractReportTemplate;
import template.screen.TemplateDefault;
import template.screen.TemplateSinglePage;
import template.screen.TemplateTabPage;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.NumberToWordConverter;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "Invoice")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6, columnSearch = {"seq", "salesOrderId", "salesPerson"})
@Displays({    
        @Display(name="schoolYear"),
        @Display(name="invoiceDate"),
        @Display(name="cashier"),
        @Display(name="billTo", type = "PopSearch", linktoBean=Customer.class),
        @Display(name="discount"),
        @Display(name="balance"),
        @Display(name="overAllBalance"),
        @Display(name="totalAmount"),
        @Display(name="paymentTerms", type = "PopSearch", linktoBean=PaymentMethod.class),
        @Display(name="arId"),
        @Display(name="balancePaymentDate"),
        @Display(name="accountType", type = "Combo", linktoBean=AccountType.class),
        @Display(name="description"),
        @Display(name="invoiceNotes"),
        @Display(name="taxCode1"),
        @Display(name="tax1"),
        @Display(name="taxCode2"),
        @Display(name="tax2"),
        @Display(name="taxCode3"),
        @Display(name="tax3"),
        @Display(name="taxCode4"),
        @Display(name="tax4")
})
@template.ChildRecords({
    @template.ChildRecord(title="Detail", entity = Payment.class, sql = "SELECT a FROM Payment a WHERE a.invoiceId=${seq}", fieldMapping={"seq","invoiceId"})
})
public class Invoice extends AbstractIBean implements Serializable, IGL {
	public static void main(String[] args) {
		view(Invoice.class);
	}
	
	@Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "invoiceNumber")
    public String invoiceNumber;
    @Column(name = "recordId")
    public Integer recordId;
    @Column(name = "accountNumber")
    public String accountNumber;
    @Column(name = "salesOrderId")
    public int salesOrderId;
    @Column(name = "invoiceDate")
    @Temporal(value = TemporalType.DATE)
    public Date invoiceDate;
    @Column(name = "salesPerson")
    public String salesPerson;
    @Column(name = "billTo")
    public String billTo;
    @Column(name = "billToAddress")
    public String billToAddress;
    @Column(name = "billToZipCode")
    public String billToZipCode;
    @Column(name = "billToTel")
    public String billToTel;
    @Column(name = "billToCity")
    public String billToCity;
    @Column(name = "billToProvince")
    public String billToProvince;
    @Column(name = "shipTo")
    public String shipTo;
    @Column(name = "shipToAddress")
    public String shipToAddress;
    @Column(name = "shipping")
    public double shipping;
    @Column(name = "subTotal")
    public double subTotal;
    @Column(name = "deposit")
    public double deposit;

    @Column(name = "surchargePaid")
    public double surchargePaid;
    @Column(name = "surchargeDiscount")
    public double surchargeDiscount;
    
    @Column(name = "totalAmount")
    public double totalAmount;
    @Column(name = "discount")
    public double discount;
    
    @Column(name = "creditAmount")
    public double creditAmount;
    @Column(name = "checkNumber")
    public String checkNumber;
    @Column(name = "checkAmount")
    public double checkAmount;
    @Column(name = "checkAmtNumber")
    public String checkAmtNumber;

    @Column(name = "allAmount")
    public double allAmount;
    
    @Column(name = "paymentTerms")
    public String paymentTerms;
    @Column(name = "arId")
    public int arId;
    @Column(name = "accountType")
    public String accountType;
    @Column(name = "description")
    public String description;
    @Column(name = "gradeLevel")
    public String gradeLevel;
    @Column(name = "studentNumber")
    public String studentNumber;
    @Column(name = "section")
    public String section;
    @Column(name = "invoiceNotes")
    public String invoiceNotes;
    @Column(name = "taxCode1")
    public String taxCode1;
    @Column(name = "tax1")
    public double tax1;
    @Column(name = "taxCode2")
    public String taxCode2;
    @Column(name = "tax2")
    public double tax2;
    @Column(name = "taxCode3")
    public String taxCode3;
    @Column(name = "tax3")
    public double tax3;
    @Column(name = "taxCode4")
    public String taxCode4;
    @Column(name = "tax4")
    public double tax4;
    @Column(name = "posted")
    public boolean posted;
    @Column(name = "cashier")
    public String cashier;
    @Column(name = "orNumber")
    public String orNumber;
    @Column(name = "orType")
    public String orType;
    @Column(name = "orDate")
    @Temporal(value = TemporalType.DATE)
    public Date orDate;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "amountInWord")
    public String amountInWord;
    @Column(name = "payer")
    public String payer;
    @Column(name = "locked")
    public boolean locked;
    @Column(name = "cancelled")
    public boolean cancelled;
    @Column(name = "plan")
    public String plan;

    @Column(name = "bank1")
    public String bank1;
    @Column(name = "accountNumber1")
    public String accountNumber1;
    @Column(name = "amount1")
    public double amount1;
    @Column(name = "bank2")
    public String bank2;
    @Column(name = "accountNumber2")
    public String accountNumber2;
    @Column(name = "amount2")
    public double amount2;
    @Column(name = "bank3")
    public String bank3;
    @Column(name = "accountNumber3")
    public String accountNumber3;
    @Column(name = "amount3")
    public double amount3;
    @Column(name = "bank4")
    public String bank4;
    @Column(name = "accountNumber4")
    public String accountNumber4;
    @Column(name = "amount4")
    public double amount4;
    @Column(name = "bank5")
    public String bank5;
    @Column(name = "accountNumber5")
    public String accountNumber5;
    @Column(name = "amount5")
    public double amount5;
    @Column(name = "bank6")
    public String bank6;
    @Column(name = "accountNumber6")
    public String accountNumber6;
    @Column(name = "amount6")
    public double amount6;
    @Column(name = "bank7")
    public String bank7;
    @Column(name = "accountNumber7")
    public String accountNumber7;
    @Column(name = "amount7")
    public double amount7;
    @Column(name = "bank8")
    public String bank8;
    @Column(name = "accountNumber8")
    public String accountNumber8;
    @Column(name = "amount8")
    public double amount8;
    @Column(name = "bank9")
    public String bank9;
    @Column(name = "accountNumber9")
    public String accountNumber9;
    @Column(name = "amount9")
    public double amount9;
    @Column(name = "bank10")
    public String bank10;
    @Column(name = "accountNumber10")
    public String accountNumber10;
    @Column(name = "amount10")
    public double amount10;

    @Column(name = "bounceCheck1")
    public boolean bounceCheck1;
    @Column(name = "bounceCheck2")
    public boolean bounceCheck2;
    @Column(name = "bounceCheck3")
    public boolean bounceCheck3;
    @Column(name = "bounceCheck4")
    public boolean bounceCheck4;
    @Column(name = "bounceCheck5")
    public boolean bounceCheck5;
    @Column(name = "bounceCheck6")
    public boolean bounceCheck6;
    @Column(name = "bounceCheck7")
    public boolean bounceCheck7;
    @Column(name = "bounceCheck8")
    public boolean bounceCheck8;
    @Column(name = "bounceCheck9")
    public boolean bounceCheck9;
    @Column(name = "bounceCheck10")
    public boolean bounceCheck10;


    @Column(name = "adjustedBy")
    public String adjustedBy;
    @Column(name = "adjustedDate")
    @Temporal(value = TemporalType.DATE)
    public Date adjustedDate;
    @Column(name = "adjustedReason")
    public String adjustedReason;
    @Column(name = "discountReason")
    public String discountReason;

    @Column(name = "gradeLevelDesc")
    public String gradeLevelDesc;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "orNumber", "accountNumber");
    }

    public String getGradeLevelDesc() {
		return gradeLevelDesc;
	}

	public void setGradeLevelDesc(String gradeLevelDesc) {
		this.gradeLevelDesc = gradeLevelDesc;
	}

	public String getDiscountReason() {
		return discountReason;
	}

	public void setDiscountReason(String discountReason) {
		this.discountReason = discountReason;
	}

	public String getCheckAmtNumber() {
		return checkAmtNumber;
	}

	public void setCheckAmtNumber(String checkAmtNumber) {
		this.checkAmtNumber = checkAmtNumber;
	}

	public String getAdjustedBy() {
		return adjustedBy;
	}

	public void setAdjustedBy(String adjustedBy) {
		this.adjustedBy = adjustedBy;
	}

	public Date getAdjustedDate() {
		return adjustedDate;
	}

	public void setAdjustedDate(Date adjustedDate) {
		this.adjustedDate = adjustedDate;
	}

	public String getAdjustedReason() {
		return adjustedReason;
	}

	public void setAdjustedReason(String adjustedReason) {
		this.adjustedReason = adjustedReason;
	}

	public boolean isBounceCheck1() {
		return bounceCheck1;
	}

	public void setBounceCheck1(boolean bounceCheck1) {
		this.bounceCheck1 = bounceCheck1;
	}

	public boolean isBounceCheck2() {
		return bounceCheck2;
	}

	public void setBounceCheck2(boolean bounceCheck2) {
		this.bounceCheck2 = bounceCheck2;
	}

	public boolean isBounceCheck3() {
		return bounceCheck3;
	}

	public void setBounceCheck3(boolean bounceCheck3) {
		this.bounceCheck3 = bounceCheck3;
	}

	public boolean isBounceCheck4() {
		return bounceCheck4;
	}

	public void setBounceCheck4(boolean bounceCheck4) {
		this.bounceCheck4 = bounceCheck4;
	}

	public boolean isBounceCheck5() {
		return bounceCheck5;
	}

	public void setBounceCheck5(boolean bounceCheck5) {
		this.bounceCheck5 = bounceCheck5;
	}

	public boolean isBounceCheck6() {
		return bounceCheck6;
	}

	public void setBounceCheck6(boolean bounceCheck6) {
		this.bounceCheck6 = bounceCheck6;
	}

	public boolean isBounceCheck7() {
		return bounceCheck7;
	}

	public void setBounceCheck7(boolean bounceCheck7) {
		this.bounceCheck7 = bounceCheck7;
	}

	public boolean isBounceCheck8() {
		return bounceCheck8;
	}

	public void setBounceCheck8(boolean bounceCheck8) {
		this.bounceCheck8 = bounceCheck8;
	}

	public boolean isBounceCheck9() {
		return bounceCheck9;
	}

	public void setBounceCheck9(boolean bounceCheck9) {
		this.bounceCheck9 = bounceCheck9;
	}

	public boolean isBounceCheck10() {
		return bounceCheck10;
	}

	public void setBounceCheck10(boolean bounceCheck10) {
		this.bounceCheck10 = bounceCheck10;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getBank1() {
		return bank1;
	}

	public void setBank1(String bank1) {
		this.bank1 = bank1;
	}

	public String getAccountNumber1() {
		return accountNumber1;
	}

	public void setAccountNumber1(String accountNumber1) {
		this.accountNumber1 = accountNumber1;
	}

	public double getAmount1() {
		return amount1;
	}

	public void setAmount1(double amount1) {
		this.amount1 = amount1;
	}

	public String getBank2() {
		return bank2;
	}

	public void setBank2(String bank2) {
		this.bank2 = bank2;
	}

	public String getAccountNumber2() {
		return accountNumber2;
	}

	public void setAccountNumber2(String accountNumber2) {
		this.accountNumber2 = accountNumber2;
	}

	public double getAmount2() {
		return amount2;
	}

	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}

	public String getBank3() {
		return bank3;
	}

	public void setBank3(String bank3) {
		this.bank3 = bank3;
	}

	public String getAccountNumber3() {
		return accountNumber3;
	}

	public void setAccountNumber3(String accountNumber3) {
		this.accountNumber3 = accountNumber3;
	}

	public double getAmount3() {
		return amount3;
	}

	public void setAmount3(double amount3) {
		this.amount3 = amount3;
	}

	public String getBank4() {
		return bank4;
	}

	public void setBank4(String bank4) {
		this.bank4 = bank4;
	}

	public String getAccountNumber4() {
		return accountNumber4;
	}

	public void setAccountNumber4(String accountNumber4) {
		this.accountNumber4 = accountNumber4;
	}

	public double getAmount4() {
		return amount4;
	}

	public void setAmount4(double amount4) {
		this.amount4 = amount4;
	}

	public String getBank5() {
		return bank5;
	}

	public void setBank5(String bank5) {
		this.bank5 = bank5;
	}

	public String getAccountNumber5() {
		return accountNumber5;
	}

	public void setAccountNumber5(String accountNumber5) {
		this.accountNumber5 = accountNumber5;
	}

	public double getAmount5() {
		return amount5;
	}

	public void setAmount5(double amount5) {
		this.amount5 = amount5;
	}

	public String getBank6() {
		return bank6;
	}

	public void setBank6(String bank6) {
		this.bank6 = bank6;
	}

	public String getAccountNumber6() {
		return accountNumber6;
	}

	public void setAccountNumber6(String accountNumber6) {
		this.accountNumber6 = accountNumber6;
	}

	public double getAmount6() {
		return amount6;
	}

	public void setAmount6(double amount6) {
		this.amount6 = amount6;
	}

	public String getBank7() {
		return bank7;
	}

	public void setBank7(String bank7) {
		this.bank7 = bank7;
	}

	public String getAccountNumber7() {
		return accountNumber7;
	}

	public void setAccountNumber7(String accountNumber7) {
		this.accountNumber7 = accountNumber7;
	}

	public double getAmount7() {
		return amount7;
	}

	public void setAmount7(double amount7) {
		this.amount7 = amount7;
	}

	public String getBank8() {
		return bank8;
	}

	public void setBank8(String bank8) {
		this.bank8 = bank8;
	}

	public String getAccountNumber8() {
		return accountNumber8;
	}

	public void setAccountNumber8(String accountNumber8) {
		this.accountNumber8 = accountNumber8;
	}

	public double getAmount8() {
		return amount8;
	}

	public void setAmount8(double amount8) {
		this.amount8 = amount8;
	}

	public String getBank9() {
		return bank9;
	}

	public void setBank9(String bank9) {
		this.bank9 = bank9;
	}

	public String getAccountNumber9() {
		return accountNumber9;
	}

	public void setAccountNumber9(String accountNumber9) {
		this.accountNumber9 = accountNumber9;
	}

	public double getAmount9() {
		return amount9;
	}

	public void setAmount9(double amount9) {
		this.amount9 = amount9;
	}

	public String getBank10() {
		return bank10;
	}

	public void setBank10(String bank10) {
		this.bank10 = bank10;
	}

	public String getAccountNumber10() {
		return accountNumber10;
	}

	public void setAccountNumber10(String accountNumber10) {
		this.accountNumber10 = accountNumber10;
	}

	public double getAmount10() {
		return amount10;
	}

	public void setAmount10(double amount10) {
		this.amount10 = amount10;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public double getSurchargePaid() {
		return surchargePaid;
	}

	public void setSurchargePaid(double surchargePaid) {
		this.surchargePaid = surchargePaid;
	}

	public double getSurchargeDiscount() {
		return surchargeDiscount;
	}

	public void setSurchargeDiscount(double surchargeDiscount) {
		this.surchargeDiscount = surchargeDiscount;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public double getCheckAmount() {
		return checkAmount;
	}

	public void setCheckAmount(double checkAmount) {
		this.checkAmount = checkAmount;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getOrType() {
        return orType;
    }

    public void setOrType(String orType) {
        this.orType = orType;
    }

    public String getAmountInWord() {
        return amountInWord;
    }

    public void setAmountInWord(String amountInWord) {
        this.amountInWord = amountInWord;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
    

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public Date getOrDate() {
        return orDate;
    }

    public void setOrDate(Date orDate) {
        this.orDate = orDate;
    }

    public String getOrNumber() {
        return orNumber;
    }

    public void setOrNumber(String orNumber) {
        this.orNumber = orNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(double allAmount) {
        this.allAmount = allAmount;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getArId() {
        return arId;
    }

    public void setArId(int arId) {
        this.arId = arId;
    }

    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    public String getBillToAddress() {
        return billToAddress;
    }

    public void setBillToAddress(String billToAddress) {
        this.billToAddress = billToAddress;
    }

    public String getBillToCity() {
        return billToCity;
    }

    public void setBillToCity(String billToCity) {
        this.billToCity = billToCity;
    }

    public String getBillToProvince() {
        return billToProvince;
    }

    public void setBillToProvince(String billToProvince) {
        this.billToProvince = billToProvince;
    }

    public String getBillToTel() {
        return billToTel;
    }

    public void setBillToTel(String billToTel) {
        this.billToTel = billToTel;
    }

    public String getBillToZipCode() {
        return billToZipCode;
    }

    public void setBillToZipCode(String billToZipCode) {
        this.billToZipCode = billToZipCode;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNotes() {
        return invoiceNotes;
    }

    public void setInvoiceNotes(String invoiceNotes) {
        this.invoiceNotes = invoiceNotes;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public int getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(int salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(String shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
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

    public double getTax3() {
        return tax3;
    }

    public void setTax3(double tax3) {
        this.tax3 = tax3;
    }

    public double getTax4() {
        return tax4;
    }

    public void setTax4(double tax4) {
        this.tax4 = tax4;
    }

    public String getTaxCode1() {
        return taxCode1;
    }

    public void setTaxCode1(String taxCode1) {
        this.taxCode1 = taxCode1;
    }

    public String getTaxCode2() {
        return taxCode2;
    }

    public void setTaxCode2(String taxCode2) {
        this.taxCode2 = taxCode2;
    }

    public String getTaxCode3() {
        return taxCode3;
    }

    public void setTaxCode3(String taxCode3) {
        this.taxCode3 = taxCode3;
    }

    public String getTaxCode4() {
        return taxCode4;
    }

    public void setTaxCode4(String taxCode4) {
        this.taxCode4 = taxCode4;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return BeanUtil.concat("Invoice[",seq,"]");
    }

    public String extractGLSubType() {
        return accountType;
    }
    
    public boolean hardcodePosting() {
        return false;
    }    

    public String extractChargeDepartment() {
        return "";
    }

    public void extractCheck(AbstractIBean bean) {
    	for (int i=1; i<=10; i++) {
    		try {
            	Object objChk = BeanUtil.getPropertyValue(bean, BeanUtil.concat("accountNumber",i));
            	BeanUtil.setPropertyValue(this, BeanUtil.concat("accountNumber",i), objChk);
    		}
    		catch (Exception e) {
    		}
    		try {
            	Object objAmount = BeanUtil.getPropertyValue(bean, BeanUtil.concat("amount",i));
            	BeanUtil.setPropertyValue(this, BeanUtil.concat("amount",i), objAmount);
    		}
    		catch (Exception e) {
    		}
    	}
        checkAmount = extractTotalCheckAmount();
    	if (checkAmount>0) {
    		checkNumber = checkNumber = checkDisplay();
    		checkAmtNumber = checkAmtNumber = checkAmtDisplay();
    	}
    }
    
    public double extractTotalCheckAmount() {
        return
                amount1+
                amount2+
                amount3+
                amount4+
                amount5+
                amount6+
                amount7+
                amount8+
                amount9+
                amount10;
    }
    
    public String extractDefaultFormula() {
        if ("ENROLLMENT".equals(accountType)) {
            return BeanUtil.concat(
                    "//Note: change the account numbers",
                    "\nif (INVOICE.paymentTerms.equals(\"CASH\")) {",
                    "\n GL.debit INVOICE, now, INVOICE.accountNumber, INVOICE.totalAmount, \"ENROLLMENT\";",
                    "\n GL.credit INVOICE, now, \"401\", INVOICE.totalAmount, \"TUITION FEES\";",
                    "\n}",
                    "\nelse { ",
                    "\n if (INVOICE.balance>0 && INVOICE.totalAmount+INVOICE.balance==INVOICE.allAmount) {",
                    "\n //this is the first payment",
                    "\n     GL.debit INVOICE, now, INVOICE.accountNumber, INVOICE.totalAmount, \"ENROLLMENT\";",
                    "\n     GL.debit INVOICE, now, \"121\", INVOICE.balance, \"TUITION AND OTHER FEES RECEIVABLE\";",
                    "\n     GL.credit INVOICE, now, \"401\", INVOICE.allAmount, \"TUITION FEES\";",
                    "\n }",
                    "\n else {",
                    "\n     GL.debit INVOICE, now, INVOICE.accountNumber, INVOICE.totalAmount, \"ENROLLMENT\";",
                    "\n     GL.credit INVOICE, now, \"401\", INVOICE.totalAmount, \"TUITION AND OTHER FEES RECEIVABLE\";",
                    "\n }",
                    "\n}",
                    "\n");
        }
        else if ("ADMISSION".equals(accountType)) {
            return BeanUtil.concat(
                    "GL.debit INVOICE, now, INVOICE.accountNumber, INVOICE.totalAmount, \"ADMISSION\";",
                    "\nGL.credit INVOICE, now, \"402.1\", INVOICE.totalAmount, \"REGISTRATION FEES\";");
        }
        return BeanUtil.concat(
                "GL.debit INVOICE, now, INVOICE.accountNumber, INVOICE.totalAmount, INVOICE.description;",
                "\nGL.credit INVOICE, now, \"411.4\", INVOICE.totalAmount, INVOICE.description;");
    }
    
    
	@Override
	public void save() {
    	if (payer.contains("CANCELLED")) {
    		amount1 = amount2 = amount3 = amount4 = amount5 = 0;
    		amount6 = amount7 = amount8 = amount9 = amount10 = 0;
    		surchargePaid = surchargeDiscount = 0;
    		checkAmount = 0;
    		totalAmount = 0;
    	}

    	gradeLevelDesc = extractGradeLevelDescription(gradeLevel);
		checkNumber = checkDisplay();
		checkAmtNumber = checkAmtDisplay();
        checkAmount = extractTotalCheckAmount();
        String currencyWord = AppConfig.getCurrencyWord();
        String centavoWord = AppConfig.getCentWord();
        totalAmount = DataUtil.getMoneyFormat(totalAmount);
        amountInWord = NumberToWordConverter.convertMoney(totalAmount, currencyWord, centavoWord);
		super.save();
		if (orNumber.equals("-1")) {
			DBClient.runBatchNative(BeanUtil.concat("DELETE FROM Payment WHERE invoiceId=",seq), BeanUtil.concat("DELETE FROM OtherPayment WHERE invoiceId=",seq), BeanUtil.concat("DELETE FROM BookSold WHERE invoiceId=",seq), BeanUtil.concat("DELETE FROM Invoice WHERE seq=",seq));
		}
	}

	@Override
	public void setupIndex() {
		runIndex(1, "cashier", "orType", "orNumber");
		runIndex(2, "cashier", "orDate");
		runIndex(3, "orNumber","accountNumber1","accountNumber2","accountNumber3","accountNumber4","accountNumber5","accountNumber6","accountNumber7","accountNumber8","accountNumber9","accountNumber10");
		runIndex(4, "orNumber","checkNumber");
		runIndex(5, "orDate");
		runIndex(6, "orNumber");
	}
    
    public static String extractGradeLevelDescription(String lvl) {
    	if (lvl==null) return "--";
    	String ret = "--";
		if (lvl.contains("H1")) ret = lvl.replaceFirst("H1", "1st Year ");
		if (lvl.contains("H2")) ret = lvl.replaceFirst("H2", "2nd Year ");
		if (lvl.contains("H3")) ret = lvl.replaceFirst("H3", "3rd Year ");
		if (lvl.contains("H4")) ret = lvl.replaceFirst("H4", "4th Year ");
		if (lvl.contains("G1")) ret = lvl.replaceFirst("G1", "Grade I ");
		if (lvl.contains("G2")) ret = lvl.replaceFirst("G2", "Grade II ");
		if (lvl.contains("G3")) ret = lvl.replaceFirst("G3", "Grade III ");
		if (lvl.contains("G4")) ret = lvl.replaceFirst("G4", "Grade IV ");
		if (lvl.contains("G5")) ret = lvl.replaceFirst("G5", "Grade V ");
		if (lvl.contains("G6")) ret = lvl.replaceFirst("G6", "Grade VI ");

		if (lvl.contains("K1")) ret = lvl.replaceFirst("K1", "Kinder ");
		if (lvl.contains("K2")) ret = lvl.replaceFirst("K2", "Kinder ");

		if (lvl.contains("N1")) ret = lvl.replaceFirst("N1", "Nursery ");
		if (lvl.contains("N2")) ret = lvl.replaceFirst("N2", "Nursery ");

		if (lvl.contains("P1")) ret = lvl.replaceFirst("N1", "Prep ");
		if (lvl.contains("P2")) ret = lvl.replaceFirst("N2", "Prep ");
		
		return ret;
    }

    private String checkDisplay() {
    	StringBuffer sb = new StringBuffer();
    	if (amount1>0) sb.append(accountNumber1).append("\n");
    	if (amount2>0) sb.append(accountNumber2).append("\n");
    	if (amount3>0) sb.append(accountNumber3).append("\n");
    	if (amount4>0) sb.append(accountNumber4).append("\n");
    	if (amount5>0) sb.append(accountNumber5).append("\n");
    	if (amount6>0) sb.append(accountNumber6).append("\n");
    	if (amount7>0) sb.append(accountNumber7).append("\n");
    	if (amount8>0) sb.append(accountNumber8).append("\n");
    	if (amount9>0) sb.append(accountNumber9).append("\n");
    	if (amount10>0) sb.append(accountNumber10).append("\n");
    	return sb.toString();
    }
    
    private String checkAmtDisplay() {
    	StringBuffer sb = new StringBuffer();
    	if (amount1>0) sb.append(DataUtil.getCurrencyFormat(amount1)).append("\n");
    	if (amount2>0) sb.append(DataUtil.getCurrencyFormat(amount2)).append("\n");
    	if (amount3>0) sb.append(DataUtil.getCurrencyFormat(amount3)).append("\n");
    	if (amount4>0) sb.append(DataUtil.getCurrencyFormat(amount4)).append("\n");
    	if (amount5>0) sb.append(DataUtil.getCurrencyFormat(amount5)).append("\n");
    	if (amount6>0) sb.append(DataUtil.getCurrencyFormat(amount6)).append("\n");
    	if (amount7>0) sb.append(DataUtil.getCurrencyFormat(amount7)).append("\n");
    	if (amount8>0) sb.append(DataUtil.getCurrencyFormat(amount8)).append("\n");
    	if (amount9>0) sb.append(DataUtil.getCurrencyFormat(amount9)).append("\n");
    	if (amount10>0) sb.append(DataUtil.getCurrencyFormat(amount10)).append("\n");
    	return sb.toString();
    }

    public void printOR(String title) {
        AbstractReportTemplate ins = AbstractReportTemplate.getInstance();
        JasperReport rep = ins.getJasperReport("OfficialReceipt");
        Map map = new HashMap();
        ins.getReportParameter(BeanUtil.concat(seq), map);
        JasperPrint print = ins.getJasperPrint(rep, map);
        ins.showReportFromFileTemplateDialog(print, title, null);
    }

    public void printOR(String title, Payment check) {
        AbstractReportTemplate ins = AbstractReportTemplate.getInstance();
    	extractCheck(check);
    	save();
        JasperReport rep = ins.getJasperReport("OfficialReceipt");
        Map map = new HashMap();
        ins.getReportParameter(BeanUtil.concat(seq), map);
        JasperPrint print = ins.getJasperPrint(rep, map);
        ins.showReportFromFileTemplateDialog(print, title, null);
    }
}
