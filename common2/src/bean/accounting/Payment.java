/*
 * Payment.java
 *
 * Created on Jan 17, 2008, 9:27:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import bean.*;
import bean.admin.AppConfig;
import bean.admin.TempTbl;
import constants.UserInfo;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;


import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import service.util.AbstractIBean;
import util.DateUtil;
import template.*;
import template.report.AbstractReportTemplate;
import template.screen.TemplateTabPage;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.Log;
import util.NumberToWordConverter;
import util.PanelUtil;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "Payment")
@DiscriminatorColumn(name = "form", discriminatorType = DiscriminatorType.STRING)
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"seq", "paymentDate", "amount", "paid"})
@ChildRecords({ 
    @ChildRecord(entity=PaymentDetail.class, fieldMapping={"seq","paymentId"}, sql="SELECT a FROM PaymentDetail a WHERE a.paymentId=${seq}", title="Payment Detail"),
    @ChildRecord(entity=PaymentLineItem.class, fieldMapping={"seq","paymentId"}, sql="SELECT a FROM PaymentLineItem a WHERE a.paymentId=${seq}", title="Payment Detail"),
    @ChildRecord(entity=PaymentPartialDetail.class, fieldMapping={"seq","paymentId"}, sql="SELECT a FROM PartialPaymentDetail a WHERE a.paymentId=${seq}", title="Partial Payment Detail")
})
@Displays({
        @Display(name="seq", label="TrxNo"),
        @Display(name="paidBy"),
        @Display(name="amount", mergeFields={"paid","invoiceId"}),
        @Display(name="paid"),
        @Display(name="invoiceId", type="Label"),
        @Display(name="paymentDate"),
        @Display(name="dueDate"),
        @Display(name="accountNumber", type = "Combo", modelCombo={"102","103.1", "103.2"}, width=100, mandatory=true),
        @Display(name="description", gridFieldWidth=7)
})
@ActionButtons({
    @ActionButton(name="btnPrintInvoice", label="Print Invoice", parentOnly=false),
    @ActionButton(name="btnNextPaymentDue", label="Next Payment", parentOnly=false),
    @ActionButton(name="btnViewGL", label="View GL", parentOnly=false)
})
public class Payment extends AbstractIBean implements Serializable, IGL {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "outstandingSurcharge")
    public double outstandingSurcharge;
    @Column(name = "paidTo")
    public int paidTo;
    @Column(name = "paymentDate")
    @Temporal(value = TemporalType.DATE)
    public Date paymentDate = constants.Constants.useDate;
    @Column(name = "dueDate")
    @Temporal(value = TemporalType.DATE)
    public Date dueDate;
    @Column(name = "paid")
    public boolean paid;
    @Column(name = "partialPaidOnly")
    public boolean partialPaidOnly;
    @Column(name = "paidBy")
    public int paidBy;
    @Column(name = "recordId")
    public int recordId;
    @Column(name = "form", length = 50)
    public String form;
    @Column(name = "line")
    public int line;
    @Column(name = "description", length = 200)
    public String description;
    @Column(name = "invoiceId")
    public int invoiceId;
    @Column(name = "paymentTerms")
    public String paymentTerms;
    @Column(name = "isActive")
    public boolean isActive = true;
    @Column(name = "accountNumber")
    public String accountNumber;
    @Column(name = "forApproval1")
    public boolean forApproval1;
    @Column(name = "forApproval2")
    public boolean forApproval2;
    @Column(name = "approvalStatus1")
    public String approvalStatus1;
    @Column(name = "approvalStatus2")
    public String approvalStatus2;

    @Column(name = "amount")
    public double amount;
    @Column(name = "amountPaid")
    public double amountPaid;
    @Column(name = "discount")
    public double discount;
    @Column(name = "balance")
    public double balance;


    @Column(name = "surcharge")
    public double surcharge;
    @Column(name = "discountSurcharge")
    public double discountSurcharge;
    @Column(name = "surchargePaid")
    public double surchargePaid;
    @Column(name = "surchargeBalance")
    public double surchargeBalance;

    @Column(name = "discountReason")
    public String discountReason;
    
    @Column(name = "overallAmountDue")
    public double overallAmountDue;
    @Column(name = "overallAmountPaid")
    public double overallAmountPaid;
    @Column(name = "overallDiscount")
    public double overallDiscount;
    @Column(name = "overallBalance")
    public double overallBalance;

    @Column(name = "paymentFor", length = 50)
    public String paymentFor;
    @Column(name = "oldPaymentFor")
    public String oldPaymentFor;
    @Column(name = "orAmount")
    public double orAmount;
    @Column(name = "orNumber")
    public String orNumber;
    @Column(name = "orType")
    public String orType;
    @Column(name = "orDate")
    @Temporal(value = TemporalType.DATE)
    public Date orDate;
   
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

    @Column(name = "info1")
    public String info1;
    @Column(name = "info2")
    public String info2;
    @Column(name = "info3")
    public String info3;
    @Column(name = "info4")
    public String info4;
    @Column(name = "info5")
    public String info5;
    @Column(name = "cashier")
    public String cashier;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "creditCard")
    public String creditCard;
    @Column(name = "creditCardAmount")
    public double creditCardAmount;
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
    @Column(name = "allCheck")
    public String allCheck;
    @Column(name = "allBounceCheck")
    public String allBounceCheck;
    @Column(name = "bounceCheckRemarks")
    public String bounceCheckRemarks;
    @Column(name = "bounceCheckPaymentLink")
    public int bounceCheckPaymentLink;
    @Column(name = "payer")
    public String payer;

    @Column(name = "section")
    public String section;
    @Column(name = "plan")
    public String plan;

    @Column(name = "adjustedBy")
    public String adjustedBy;
    @Column(name = "adjustedDate")
    @Temporal(value = TemporalType.DATE)
    public Date adjustedDate;
    @Column(name = "adjustedReason")
    public String adjustedReason;
    @Column(name = "posted")
    public boolean posted;

    public double getOutstandingSurcharge() {
		return outstandingSurcharge;
	}

	public void setOutstandingSurcharge(double outstandingSurcharge) {
		this.outstandingSurcharge = outstandingSurcharge;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Payment() {
        cashier = UserInfo.getUserName();
        orDate = constants.Constants.useDate;
//        orNumber = new CashierDailyBooklet().extractNextOR()+"";
        orNumber = "";
        orType = "N";
        schoolYear = AppConfig.getSchoolYear();
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

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getOldPaymentFor() {
		return oldPaymentFor;
	}

	public void setOldPaymentFor(String oldPaymentFor) {
		this.oldPaymentFor = oldPaymentFor;
	}

	public double getOverallDiscount() {
		return overallDiscount;
	}

	public void setOverallDiscount(double overallDiscount) {
		this.overallDiscount = overallDiscount;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public double getSurchargePaid() {
		return surchargePaid;
	}

	public void setSurchargePaid(double surchargePaid) {
		this.surchargePaid = surchargePaid;
	}

	public double getSurchargeBalance() {
		return surchargeBalance;
	}

	public void setSurchargeBalance(double surchargeBalance) {
		this.surchargeBalance = surchargeBalance;
	}

	public double getOverallAmountDue() {
		return overallAmountDue;
	}

	public void setOverallAmountDue(double overallAmountDue) {
		this.overallAmountDue = overallAmountDue;
	}

	public double getOrAmount() {
		return orAmount;
	}

	public void setOrAmount(double orAmount) {
		this.orAmount = orAmount;
	}

	public String getDiscountReason() {
		return discountReason;
	}

	public void setDiscountReason(String discountReason) {
		this.discountReason = discountReason;
	}

	public String getBounceCheckRemarks() {
		return bounceCheckRemarks;
	}

	public void setBounceCheckRemarks(String bounceCheckRemarks) {
		this.bounceCheckRemarks = bounceCheckRemarks;
	}

	public int getBounceCheckPaymentLink() {
		return bounceCheckPaymentLink;
	}

	public void setBounceCheckPaymentLink(int bounceCheckPaymentLink) {
		this.bounceCheckPaymentLink = bounceCheckPaymentLink;
	}

	public double getDiscountSurcharge() {
		return discountSurcharge;
	}

	public void setDiscountSurcharge(double discountSurcharge) {
		this.discountSurcharge = discountSurcharge;
	}

	public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getAllBounceCheck() {
        return allBounceCheck;
    }

    public void setAllBounceCheck(String allBounceCheck) {
        this.allBounceCheck = allBounceCheck;
    }

    public String getAllCheck() {
        return allCheck;
    }

    public void setAllCheck(String allCheck) {
        this.allCheck = allCheck;
    }

    public boolean isBounceCheck1() {
        return bounceCheck1;
    }

    public void setBounceCheck1(boolean bounceCheck1) {
        this.bounceCheck1 = bounceCheck1;
    }

    public boolean isBounceCheck10() {
        return bounceCheck10;
    }

    public void setBounceCheck10(boolean bounceCheck10) {
        this.bounceCheck10 = bounceCheck10;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getOrType() {
        return orType;
    }

    public void setOrType(String orType) {
        this.orType = orType;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public double getCreditCardAmount() {
        return creditCardAmount;
    }

    public void setCreditCardAmount(double creditCardAmount) {
        this.creditCardAmount = creditCardAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public void save() {
    	double chk = extractCheckAmount();
    	if (chk>0) {
            if (accountNumber1!=null) this.allCheck = BeanUtil.concat(accountNumber1,",");
            if (accountNumber2!=null) this.allCheck += BeanUtil.concat(accountNumber2,",");
            if (accountNumber3!=null) this.allCheck += BeanUtil.concat(accountNumber3,",");
            if (accountNumber4!=null) this.allCheck += BeanUtil.concat(accountNumber4,",");
            if (accountNumber5!=null) this.allCheck += BeanUtil.concat(accountNumber5,",");
            if (accountNumber6!=null) this.allCheck += BeanUtil.concat(accountNumber6,",");
            if (accountNumber7!=null) this.allCheck += BeanUtil.concat(accountNumber7,",");
            if (accountNumber8!=null) this.allCheck += BeanUtil.concat(accountNumber8,",");
            if (accountNumber9!=null) this.allCheck += BeanUtil.concat(accountNumber9,",");
            if (accountNumber10!=null) this.allCheck += BeanUtil.concat(accountNumber10,",");
    	}
        this.allBounceCheck = "";
        if (this.bounceCheck1) {
            this.allBounceCheck += BeanUtil.concat(this.accountNumber1,",");
        }
        if (this.bounceCheck2) {
            this.allBounceCheck += BeanUtil.concat(this.accountNumber2,",");
        }
        if (this.bounceCheck3) {
            this.allBounceCheck += BeanUtil.concat(this.accountNumber3,",");
        }
        if (this.bounceCheck4) {
            this.allBounceCheck += BeanUtil.concat(this.accountNumber4,",");
        }
        if (this.bounceCheck5) {
            this.allBounceCheck += BeanUtil.concat(this.accountNumber5,",");
        }
        if (this.bounceCheck6) {
            this.allBounceCheck += BeanUtil.concat(this.accountNumber6,",");
        }
        if (this.bounceCheck7) {
            this.allBounceCheck += BeanUtil.concat(this.accountNumber7,",");
        }
        if (this.bounceCheck8) {
            this.allBounceCheck += BeanUtil.concat(this.accountNumber8,",");
        }
        if (this.bounceCheck9) {
            this.allBounceCheck += BeanUtil.concat(this.accountNumber9,",");
        }
        if (this.bounceCheck10) {
            this.allBounceCheck += BeanUtil.concat(this.accountNumber10,",");
        }
        payer = extractPersonName(paidBy);
        if (paid) {
//        	this is for payment
            overallAmountDue = amount+surcharge;
            overallDiscount = discount;
            overallAmountPaid = amountPaid;
            balance = amount-discount-amountPaid;
            surchargeBalance = surcharge-discountSurcharge-surchargePaid;
            overallBalance = balance;
        	orAmount = overallAmountPaid + surchargePaid - discount-discountSurcharge;
        	if (orDate==null) orDate=paymentDate;

        	if (overallBalance<0) overallBalance = 0;
        	if (surchargeBalance<0) surchargeBalance = 0;
        	if (balance<0) balance = 0;
        }
        else {
//        	this is for assessment
            overallAmountPaid = surchargePaid = 0;
        	List<Payment> lst = DBClient.getList(BeanUtil.concat("SELECT a FROM Payment a WHERE a.schoolYear='",schoolYear,"' AND a.paidBy=",paidBy," AND a.paid=true ORDER BY a.paymentDate DESC"));
        	for (int i=0; i<lst.size(); i++) {
        		Payment p = lst.get(i);
        		if (p.paymentFor.equals(paymentFor)) {
            		if (p.overallAmountPaid==0) {
                		overallAmountPaid += p.amountPaid+p.discount;
            		}
            		else {
                		overallAmountPaid += p.overallAmountPaid;
            		}
        		}
        	}
        	balance = overallBalance = amount - overallAmountPaid - discount;
        	surchargeBalance = surcharge - discountSurcharge + outstandingSurcharge;
        	if (overallBalance<0) overallBalance = 0;
        	if (surchargeBalance<0) surchargeBalance = 0;
        	if (balance<0) balance = 0;
        	overallAmountDue = overallBalance + surchargeBalance;
        	if (dueDate==null) dueDate=paymentDate;
        	if (surcharge<0) surcharge=0;
        	if (surchargeBalance<0) surchargeBalance=0;
        	if (balance<=0) {
        		balance = 0;
            	surchargeBalance = surcharge = 0;
            	overallAmountDue = 0;
        	}
        }
//        if not paid, must need to setup the overall values
        orAmount = DataUtil.getMoneyFormat(orAmount);
        amountPaid = DataUtil.getMoneyFormat(amountPaid);
        super.save();
    }

    public boolean isForApproval1() {
        return forApproval1;
    }

    public void setForApproval1(boolean forApproval1) {
        this.forApproval1 = forApproval1;
    }

    public boolean isForApproval2() {
        return forApproval2;
    }

    public void setForApproval2(boolean forApproval2) {
        this.forApproval2 = forApproval2;
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
    
    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public String getInfo4() {
        return info4;
    }

    public void setInfo4(String info4) {
        this.info4 = info4;
    }

    public String getInfo5() {
        return info5;
    }

    public void setInfo5(String info5) {
        this.info5 = info5;
    }
    
    
    public String getAccountNumber1() {
        return accountNumber1;
    }

    public void setAccountNumber1(String accountNumber1) {
        this.accountNumber1 = accountNumber1;
    }

    public String getAccountNumber10() {
        return accountNumber10;
    }

    public void setAccountNumber10(String accountNumber10) {
        this.accountNumber10 = accountNumber10;
    }

    public String getAccountNumber2() {
        return accountNumber2;
    }

    public void setAccountNumber2(String accountNumber2) {
        this.accountNumber2 = accountNumber2;
    }

    public String getAccountNumber3() {
        return accountNumber3;
    }

    public void setAccountNumber3(String accountNumber3) {
        this.accountNumber3 = accountNumber3;
    }

    public String getAccountNumber4() {
        return accountNumber4;
    }

    public void setAccountNumber4(String accountNumber4) {
        this.accountNumber4 = accountNumber4;
    }

    public String getAccountNumber5() {
        return accountNumber5;
    }

    public void setAccountNumber5(String accountNumber5) {
        this.accountNumber5 = accountNumber5;
    }

    public String getAccountNumber6() {
        return accountNumber6;
    }

    public void setAccountNumber6(String accountNumber6) {
        this.accountNumber6 = accountNumber6;
    }

    public String getAccountNumber7() {
        return accountNumber7;
    }

    public void setAccountNumber7(String accountNumber7) {
        this.accountNumber7 = accountNumber7;
    }

    public String getAccountNumber8() {
        return accountNumber8;
    }

    public void setAccountNumber8(String accountNumber8) {
        this.accountNumber8 = accountNumber8;
    }

    public String getAccountNumber9() {
        return accountNumber9;
    }

    public void setAccountNumber9(String accountNumber9) {
        this.accountNumber9 = accountNumber9;
    }

    public double getAmount1() {
        return amount1;
    }

    public void setAmount1(double amount1) {
        this.amount1 = amount1;
    }

    public double getAmount10() {
        return amount10;
    }

    public void setAmount10(double amount10) {
        this.amount10 = amount10;
    }

    public double getAmount2() {
        return amount2;
    }

    public void setAmount2(double amount2) {
        this.amount2 = amount2;
    }

    public double getAmount3() {
        return amount3;
    }

    public void setAmount3(double amount3) {
        this.amount3 = amount3;
    }

    public double getAmount4() {
        return amount4;
    }

    public void setAmount4(double amount4) {
        this.amount4 = amount4;
    }

    public double getAmount5() {
        return amount5;
    }

    public void setAmount5(double amount5) {
        this.amount5 = amount5;
    }

    public double getAmount6() {
        return amount6;
    }

    public void setAmount6(double amount6) {
        this.amount6 = amount6;
    }

    public double getAmount7() {
        return amount7;
    }

    public void setAmount7(double amount7) {
        this.amount7 = amount7;
    }

    public double getAmount8() {
        return amount8;
    }

    public void setAmount8(double amount8) {
        this.amount8 = amount8;
    }

    public double getAmount9() {
        return amount9;
    }

    public void setAmount9(double amount9) {
        this.amount9 = amount9;
    }

    public String getBank1() {
        return bank1;
    }

    public void setBank1(String bank1) {
        this.bank1 = bank1;
    }

    public String getBank10() {
        return bank10;
    }

    public void setBank10(String bank10) {
        this.bank10 = bank10;
    }

    public String getBank2() {
        return bank2;
    }

    public void setBank2(String bank2) {
        this.bank2 = bank2;
    }

    public String getBank3() {
        return bank3;
    }

    public void setBank3(String bank3) {
        this.bank3 = bank3;
    }

    public String getBank4() {
        return bank4;
    }

    public void setBank4(String bank4) {
        this.bank4 = bank4;
    }

    public String getBank5() {
        return bank5;
    }

    public void setBank5(String bank5) {
        this.bank5 = bank5;
    }

    public String getBank6() {
        return bank6;
    }

    public void setBank6(String bank6) {
        this.bank6 = bank6;
    }

    public String getBank7() {
        return bank7;
    }

    public void setBank7(String bank7) {
        this.bank7 = bank7;
    }

    public String getBank8() {
        return bank8;
    }

    public void setBank8(String bank8) {
        this.bank8 = bank8;
    }

    public String getBank9() {
        return bank9;
    }

    public void setBank9(String bank9) {
        this.bank9 = bank9;
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
    
    public double getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(double surcharge) {
        this.surcharge = surcharge;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOverallAmountPaid() {
        return overallAmountPaid;
    }

    public void setOverallAmountPaid(double overallAmountPaid) {
        this.overallAmountPaid = overallAmountPaid;
    }

    public double getOverallBalance() {
        return overallBalance;
    }

    public void setOverallBalance(double overallBalance) {
        this.overallBalance = overallBalance;
    }

    public String getPaymentFor() {
        return paymentFor;
    }

    public void setPaymentFor(String paymentFor) {
        this.paymentFor = paymentFor;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        if (form!=null) form = form.toUpperCase();
        this.form = form;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(int paidBy) {
        this.paidBy = paidBy;
    }

    public int getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(int paidTo) {
        this.paidTo = paidTo;
    }

    public boolean isPartialPaidOnly() {
        return partialPaidOnly;
    }

    public void setPartialPaidOnly(boolean partialPaidOnly) {
        this.partialPaidOnly = partialPaidOnly;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Person extractCustomer() {
        if (paidBy==0) return null;
        return (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.personId=",paidBy);
    }
    public Person extractSeller() {
        if (paidTo==0) return null;
        return (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.personId=",paidTo);
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

    public Invoice extractInvoice(Payment checkPayment) {
        Invoice inv = null;
        if (this.invoiceId == 0) {
            inv = new Invoice();
            inv.schoolYear = this.schoolYear;
            inv.invoiceDate = constants.Constants.useDate;
            inv.accountNumber = accountNumber;
            StringBuffer desc = new StringBuffer();
            StringBuffer check = new StringBuffer();
            StringBuffer checkAmtDis = new StringBuffer();
            double totalAmount = 0;
            double totalCheck = 0;
            double totalSurchargePaid = 0;
            double totalSurchargeDiscount = 0;
            List<Payment> lst = DBClient.getList("SELECT a FROM PaymentEnrollment a WHERE a.orNumber='",orNumber,"' AND a.orType='",orType,"'");
            for (Payment p:lst) {
            	totalAmount += DataUtil.getMoneyFormat(p.orAmount);
            	totalSurchargePaid += DataUtil.getMoneyFormat(p.surchargePaid);
            	totalSurchargeDiscount += DataUtil.getMoneyFormat(p.discountSurcharge);
            	Log.out("p.overallAmountPaid = ",p.overallAmountPaid);
            	p.overallAmountPaid = DataUtil.getMoneyFormat(p.overallAmountPaid);
            	if (p.overallAmountPaid > 0.2) {
                	desc.append(p.paymentForLongDesc()).append(" - ").append(p.overallAmountPaid).append("   ");
            	}
            	if (p.discount>0 && p.discountReason!=null) {
            		inv.discountReason = p.discountReason;
            	}
            }
            if (totalSurchargePaid-totalSurchargeDiscount > 0) {
            	desc.append(" surcharges - ").append(DataUtil.getCurrencyFormat(totalSurchargePaid-totalSurchargeDiscount));
            }
//            for check only
            inv.totalAmount = inv.allAmount = DataUtil.getMoneyFormat(totalAmount);
//            change check amount
            Payment cloneCheck = new Payment();
            if (checkPayment!=null) cloneCheck = (Payment) checkPayment.clone();
            if (cloneCheck.extractCheckAmount()>0) {
                if (inv.totalAmount<=cloneCheck.extractCheckAmount()) {
                    totalCheck = inv.totalAmount;
                    if (cloneCheck.amount1>inv.totalAmount) {
                    	cloneCheck.amount1 = inv.totalAmount;
                    	cloneCheck.amount2 = cloneCheck.amount3 = cloneCheck.amount4 = 0;
                    }
                    else if (cloneCheck.amount1+cloneCheck.amount2>inv.totalAmount) {
                    	cloneCheck.amount2 = inv.totalAmount-cloneCheck.amount1;
                    	cloneCheck.amount3 = cloneCheck.amount4 = 0;
                    }
                }
                else {
                    totalCheck = cloneCheck.extractCheckAmount();
                }
        		check.append(cloneCheck.checkDisplay());
        		checkAmtDis.append(cloneCheck.checkAmtDisplay());
                checkPayment.amount1 -= cloneCheck.amount1;
                checkPayment.amount2 -= cloneCheck.amount2;
                checkPayment.amount3 -= cloneCheck.amount3;
                checkPayment.amount4 -= cloneCheck.amount4;
            }
        	
            inv.section = section;
            inv.accountType = form;
            inv.recordId = recordId;
            inv.plan = plan;
            if ("A".equals(orType)) {
                inv.description = BeanUtil.concat("MISCELLANEOUS FEE ",desc.toString());
            }
            else {
                inv.description = BeanUtil.concat("TUITION FEE ",desc.toString());
            }
            inv.description = inv.description.replace("- -", "-");
            inv.discount = discount+discountSurcharge;
            if (discount>0 && inv.discountReason!=null && !inv.discountReason.trim().isEmpty()) {
            	inv.description += BeanUtil.concat(" less ",inv.discountReason.toLowerCase()," discount of ",DataUtil.getCurrencyFormat(inv.discount));
            }
            try {
        		inv.description = PanelUtil.showPromptDefaultMessage(null, "Change OR Description. [Note: This cannot cancel transaction.]", inv.description);
            }
            catch (Exception e) {
            }
            inv.paymentTerms = paymentTerms;
            inv.orDate = orDate;
            inv.orNumber = orNumber;
            inv.orType = orType;
            inv.cashier = cashier;
            inv.schoolYear = schoolYear;
            inv.checkAmount = totalCheck;
            inv.checkNumber = check.toString();
            inv.checkAmtNumber = checkAmtDis.toString();
            inv.surchargePaid = totalSurchargePaid;
            inv.surchargeDiscount = totalSurchargeDiscount;
            String currencyWord = AppConfig.getCurrencyWord();
            String centavoWord = AppConfig.getCentWord();
            inv.totalAmount = DataUtil.getMoneyFormat(inv.totalAmount);
            inv.amountInWord = NumberToWordConverter.convertMoney(inv.totalAmount, currencyWord, centavoWord);
            inv.payer = payer;
            if (extractSeller() != null) {
                inv.setSalesPerson(extractSeller().getFormattedTitle());
            }
            Person cust = extractCustomer();
            if (cust != null) {
                inv.billTo = BeanUtil.concat(cust.personId,"");
                inv.shipTo=(cust.getAddress());
                inv.shipToAddress=(cust.getAddress());
                inv.studentNumber = cust.studentNumber;
                inv.gradeLevel = cust.gradeLevel;
                inv.section = cust.section;
            }
            inv.extractCheck(cloneCheck);
            inv.save();
            invoiceId = inv.seq;
            paid = true;
            save();
            DBClient.runSQLNative("UPDATE Payment SET invoiceId=",invoiceId," WHERE orNumber='",orNumber,"' AND orType='",orType,"'");
        }
        else {
            inv = (Invoice) AbstractIBean.firstRecord("SELECT a FROM Invoice a WHERE a.seq=",this.invoiceId);
        }
        return inv;
    }

    private String paymentForLongDesc() {
    	if (paymentFor==null || paymentFor.isEmpty()) return "";
    	String str = paymentFor;
//    	str = str.replace("G1", "Grade 1");
//    	str = str.replace("G2", "Grade 2");
//    	str = str.replace("G3", "Grade 3");
//    	str = str.replace("G4", "Grade 4");
//    	str = str.replace("G5", "Grade 5");
//    	str = str.replace("G6", "Grade 6");
//    	str = str.replace("H1", "1st Year");
//    	str = str.replace("H2", "2nd Year");
//    	str = str.replace("H3", "3rd Year");
//    	str = str.replace("H4", "4th Year");
//    	str = str.replace("K1", "Kinder");
//    	str = str.replace("N1", "Nursery");
//    	str = str.replace("P1", "Prep");

    	str = str.replace("G1", "");
    	str = str.replace("G2", "");
    	str = str.replace("G3", "");
    	str = str.replace("G4", "");
    	str = str.replace("G5", "");
    	str = str.replace("G6", "");
    	str = str.replace("H1", "");
    	str = str.replace("H2", "");
    	str = str.replace("H3", "");
    	str = str.replace("H4", "");
    	str = str.replace("K1", "");
    	str = str.replace("N1", "");
    	str = str.replace("P1", "");
    	str = str.replace("MISC", "");
    	str = str.replace("TFEE", " FULL PAYMENT ");
    	

    	str = str.replace("Q1", "1ST QTR.");
    	str = str.replace("Q2", "2ND QTR.");
    	str = str.replace("Q3", "3RD QTR.");
    	str = str.replace("Q4", "4TH QTR.");

    	str = str.replace("S1", "1ST SEM.");
    	str = str.replace("S2", "2ND SEM.");
    	return str;
    }
    
    public String getApprovalStatus1() {
        return approvalStatus1;
    }

    public void setApprovalStatus1(String approvalStatus1) {
        this.approvalStatus1 = approvalStatus1;
    }

    public String getApprovalStatus2() {
        return approvalStatus2;
    }

    public void setApprovalStatus2(String approvalStatus2) {
        this.approvalStatus2 = approvalStatus2;
    }

    public void setAdjustmentStr(String adjustmentStr) {
		this.adjustmentStr = adjustmentStr;
	}

	public double extractTotalDue() {
    	return this.balance+this.surchargeBalance;
    }
    
    public double extractCheckAmount() {
        return amount1+amount2+amount3+amount4+amount5+amount6+amount7+amount8+amount9+amount10;
    }

    public double extractCashAmount() {
        return overallAmountPaid-extractCheckAmount()-creditCardAmount;
    }
    
    public boolean hasCheck() {
        if (accountNumber1!=null && !accountNumber1.trim().isEmpty()) return true;
        if (accountNumber2!=null && !accountNumber2.trim().isEmpty()) return true;
        if (accountNumber3!=null && !accountNumber3.trim().isEmpty()) return true;
        if (accountNumber4!=null && !accountNumber4.trim().isEmpty()) return true;
        if (accountNumber5!=null && !accountNumber5.trim().isEmpty()) return true;
        if (accountNumber6!=null && !accountNumber6.trim().isEmpty()) return true;
        if (accountNumber7!=null && !accountNumber7.trim().isEmpty()) return true;
        if (accountNumber8!=null && !accountNumber8.trim().isEmpty()) return true;
        if (accountNumber9!=null && !accountNumber9.trim().isEmpty()) return true;
        if (accountNumber10!=null && !accountNumber10.trim().isEmpty()) return true;
        return false;
    }
    
    private void dynamicPutCheck(int index, Payment check) {
    	if (check==null) return;
    	double chkamt = BeanUtil.getDoubleValue(check, BeanUtil.concat("amount",index));
    	if (chkamt<=0) return;
    	
//    	if (this.paymentFor.contains("MISC")) {
//        	BeanUtil.setPropertyValue(this, "amount"+index, orAmount);
//        	if (chkamt<orAmount) {
//            	BeanUtil.setPropertyValue(check, "amount"+index, 0);
//        	}
//        	else {
//            	BeanUtil.setPropertyValue(check, "amount"+index, chkamt-orAmount);
//        	}
//	    }
//    	else {
        	BeanUtil.setPropertyValue(this, BeanUtil.concat("amount",index), chkamt);
//        	BeanUtil.setPropertyValue(check, "amount"+index, 0);
//    	}
    }
    
    public void putCheck(Payment check) {
        if (check==null) return;
        for (int i=1; i<=10; i++) {
            dynamicPutCheck(i, check);
        }

        this.accountNumber1 = check.accountNumber1;
        this.accountNumber2 = check.accountNumber2;
        this.accountNumber3 = check.accountNumber3;
        this.accountNumber4 = check.accountNumber4;
        this.accountNumber5 = check.accountNumber5;
        this.accountNumber6 = check.accountNumber6;
        this.accountNumber7 = check.accountNumber7;
        this.accountNumber8 = check.accountNumber8;
        this.accountNumber9 = check.accountNumber9;
        this.accountNumber10 = check.accountNumber10;
    }

    public void printOR(String title, Payment check) {
        save();
        extractInvoice(check);
        AbstractReportTemplate ins = AbstractReportTemplate.getInstance();
        JasperReport rep = ins.getJasperReport("OfficialReceipt");
        Map map = new HashMap();
        ins.getReportParameter(BeanUtil.concat(invoiceId,""), map);
        JasperPrint print = ins.getJasperPrint(rep, map);
        ins.showReportFromFileTemplateDialog(print, title, null);
    }

	@Transient
	public int countDays;
	@Transient
	public double calculatedSurcharge;
	
	public int getCountDays() {
		return countDays;
	}

	public void setCountDays(int countDays) {
		this.countDays = countDays;
	}

	public double getCalculatedSurcharge() {
		return calculatedSurcharge;
	}

	public void setCalculatedSurcharge(double calculatedSurcharge) {
		this.calculatedSurcharge = calculatedSurcharge;
	}

	public void useTentativePaymentDate(Date d) {
//		change the countDays and calculated surcharge here
		if (paymentDate==null) {
			paymentDate = dueDate;
		}
		Date tmp = paymentDate;
		if (paymentDate.getTime()<dueDate.getTime()) {
			tmp = dueDate;
		}
		int count = DateUtil.countDaySpan(tmp, d);
		double sur = DataUtil.getMoneyFormat((balance*.1*count)/100);
		if (count>0) {
			changeValue("countDays", count);
			changeValue("calculatedSurcharge", sur);
			changeValue("countDays", count);
			changeValue("calculatedSurcharge", sur);
		}
		else {
			changeValue("countDays", 0);
			changeValue("calculatedSurcharge", 0);
			changeValue("countDays", 0);
			changeValue("calculatedSurcharge", 0);
		}
	}
	
	@Transient
	public String adjustmentStr;
	
	public String getAdjustmentStr() {
		if (adjustedBy!=null && !adjustedBy.isEmpty()) {
			return "Y";
		}
		return "";
	}
	
	@Override
	public void setupIndex() {
		runIndex(1, "schoolYear","paidBy","form","dueDate");
		runIndex(2, "invoiceId");
		runIndex(3, "orNumber","orType","form");
		runIndex(4, "paidBy","paymentFor","form");
		runIndex(5, "paidBy","paymentFor","description","form");
		runIndex(6, "paidBy","description","form");
		runIndex(7, "form");
		runIndex(8, "schoolYear","paidBy","paymentFor");
		runIndex(9, "schoolYear","paidBy","paymentFor","paid","balance");
		runIndex(10, "schoolYear","paidBy","paymentFor","paid");
		runUniqueIndex(11, "paidBy","paymentFor","paid","dueDate");
    	boolean b = AppConfig.isRunFWB();
    	if (!b) {
//    		delete all FWB
    		DBClient.runSQLNative("DELETE FROM Payment WHERE paymentFor LIKE 'FWB'");
    	}

		
//		setup temptable
//		be sure that this is run on payment only, to save time
		if (isSuperBean()) {
			new TempTbl().setupIndex();
			
//			String tmp = "DELETE FROM temptbl";
//	    	String tmp2 = "INSERT INTO temptbl(g1,g2,g3,g4,g5) SELECT schoolYear, paidBy, paymentFor, SUM(amountPaid), SUM(surchargePaid) FROM Payment WHERE paid=1 GROUP BY schoolYear, paidBy, paymentFor";
//	    	String str = "UPDATE Payment p, temptbl t SET p.overallAmountPaid=t.g4, p.surchargePaid=t.g5 WHERE t.g1=p.schoolYear AND t.g2=p.paidBy AND t.g3=p.paymentFor AND p.paid=0 AND (p.balance=0 OR p.balance IS NULL)";
//	    	String str2 = "UPDATE Payment SET " +
//	    			"balance=(amount-overallAmountPaid-discount), " +
//	    			"overallBalance=(amount-overallAmountPaid-discount), " +
//	    			"surchargeBalance=(surcharge - surchargePaid - discountSurcharge), " +
//	    			"overallAmountDue = (overallBalance + surchargeBalance) WHERE paid=0 AND (balance=0 OR balance IS NULL)";
//	    	String str3 = "UPDATE Payment SET overallBalance=0 WHERE paid=0 AND overallBalance<0 OR overallBalance IS NULL";
//	    	String str4 = "UPDATE Payment SET surchargeBalance=0 WHERE paid=0 AND surchargeBalance<0 OR surchargeBalance IS NULL";
//	    	String str5 = "UPDATE Payment SET balance=amount, overallBalance=amount WHERE paid=0 AND overallAmountPaid=0 OR overallAmountPaid IS NULL";
//	    	String str6 = "UPDATE Payment SET overallAmountPaid=0 WHERE paid=0 AND overallAmountPaid=0 OR overallAmountPaid IS NULL";
//	    	DBClient.runBatchNative(tmp, tmp2, str, str2, str3, str4, str5, str6);
		}
	}

	@Override
	public String extractChargeDepartment() {
		return "";
	}

	@Override
	public String extractDefaultFormula() {
        return BeanUtil.concat("GL.debit PAYMENT, now, \"102\", PAYMENT.amount, PAYMENT.paymentFor;",
        "\nGL.credit PAYMENT, now, \"402.1\", PAYMENT.amount, PAYMENT.paymentFor;");
	}

	@Override
	public String extractGLSubType() {
		return this.paymentFor;
	}

	@Override
	public boolean hardcodePosting() {
		if (paid) {
//			since posting will be for invoice
            Invoice inv = (Invoice) extractInvoice(this);
            if (invoiceId==0) {
                invoiceId = inv.seq;
                save();
            }
            GLPostingScript.post(inv);
			return true;
		}
		return false;
	}

	@Override
	public boolean isPosted() {
		return posted;
	}

	@Override
	public void setPosted(boolean posted) {
		this.posted = posted;
	}
}
