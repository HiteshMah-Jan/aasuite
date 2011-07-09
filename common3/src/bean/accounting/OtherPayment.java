/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

import bean.Customer;
import bean.Person;
import bean.admin.AppConfig;
import bean.reference.OtherPaymentReference;
import constants.UserInfo;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.report.AbstractReportTemplate;
import template.screen.TemplateSinglePage;
import util.DataUtil;
import util.NumberToWordConverter;
import util.DBClient;
import util.BeanUtil;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "OtherPayment")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, 
    columnSearch = {"payer","cashier","schoolYear","cashier","totalAmount"}, 
    orderBy="a.schoolYear DESC, a.cashier")
@Displays({
    @Display(name = "personId", type = "PopSearch", linktoBean = Customer.class, width = -1, gridFieldWidth = 3, label = "Student"),
    @Display(name = "payer",label="Payor Name",gridFieldWidth=3,width=-1),
    @Display(name = "payerCategory",label="Category",gridFieldWidth=3,width=-1, type="Combo",modelCombo={"STUDENT","EMPLOYEE","OTHERS"}),
    @Display(name = "totalAmount"),
    @Display(name = "orType", label = "Acc", type="Label"),
    @Display(name = "cashier", type="Label"),
    @Display(name = "schoolYear", type="Label"),
    @Display(name = "orNumber"),
    @Display(name = "orDate", type="Label"),

    @Display(name = "paymentCode1",width=300, type = "PopSearch", linktoBean = OtherPaymentReference.class, label = "Payment Description", labelTop = true),
    @Display(name = "paymentAmount1", label = "Amount Paid", labelTop = true),
    @Display(name = "paymentCode2",width=300, type = "PopSearch", linktoBean = OtherPaymentReference.class, hideLabel = true),
    @Display(name = "paymentAmount2", hideLabel = true),
    @Display(name = "paymentCode3",width=300, type = "PopSearch", linktoBean = OtherPaymentReference.class, hideLabel = true),
    @Display(name = "paymentAmount3", hideLabel = true),
    @Display(name = "paymentCode4",width=300, type = "PopSearch", linktoBean = OtherPaymentReference.class, hideLabel = true),
    @Display(name = "paymentAmount4", hideLabel = true),
    @Display(name = "paymentCode5",width=300, type = "PopSearch", linktoBean = OtherPaymentReference.class, hideLabel = true),
    @Display(name = "paymentAmount5", hideLabel = true),
    @Display(name = "paymentCode6",width=300, type = "PopSearch", linktoBean = OtherPaymentReference.class, hideLabel = true),
    @Display(name = "paymentAmount6", hideLabel = true),
    @Display(name = "paymentCode7",width=300, type = "PopSearch", linktoBean = OtherPaymentReference.class, hideLabel = true),
    @Display(name = "paymentAmount7", hideLabel = true),
    @Display(name = "paymentCode8",width=300, type = "PopSearch", linktoBean = OtherPaymentReference.class, hideLabel = true),
    @Display(name = "paymentAmount8", hideLabel = true),
    @Display(name = "paymentCode9",width=300, type = "PopSearch", linktoBean = OtherPaymentReference.class, hideLabel = true),
    @Display(name = "paymentAmount9", hideLabel = true),
    @Display(name = "paymentCode10",width=300, type = "PopSearch", linktoBean = OtherPaymentReference.class, hideLabel = true),
    @Display(name = "paymentAmount10", hideLabel = true)
})
@DisplayGroups({
    @DisplayGroup(gridCount=4, title="Other Payments", fields={
        "paymentCode1", "paymentAmount1",
        "paymentCode2", "paymentAmount2",
        "paymentCode3", "paymentAmount3",
        "paymentCode4", "paymentAmount4",
        "paymentCode5", "paymentAmount5",
        "paymentCode6", "paymentAmount6",
        "paymentCode7", "paymentAmount7",
        "paymentCode8", "paymentAmount8",
        "paymentCode9", "paymentAmount9",
        "paymentCode10", "paymentAmount10"
    })
})
public class OtherPayment extends AbstractIBean {
 
    public static void main(String[] args) {
        view(OtherPayment.class);
    }
    
    public OtherPayment() {
        cashier = UserInfo.getUserName();
        orDate = constants.Constants.useDate;
        schoolYear = AppConfig.getSchoolYear();
    }

    @Override
    public void save() {
//        setup complete description
        StringBuffer sb = new StringBuffer();
        for (int i=1; i<=10; i++) {
            Object obj = BeanUtil.getPropertyValue(this, BeanUtil.concat("paymentCode",i));
            if (obj==null) continue;
            OtherPaymentReference ref = (OtherPaymentReference) OtherPaymentReference.extractObject(OtherPaymentReference.class.getSimpleName(), obj.toString());
            if (ref!=null && !ref.isEmptyKey()) {
                sb.append(ref.description).append(", ");
            }
        }
        if (isEmptyKey()) {
            String str = sb.toString().trim();
            String strC = completeDesc==null?"":completeDesc;
            completeDesc = BeanUtil.concat(str," ",strC);
        }
        try {
        	Person stud = extractPerson(personId);
        	if (stud!=null) {
                payer = stud.toString();
                studentNumber = stud.studentNumber;
                gradeLevel = stud.gradeLevel;
        	}
        }
        catch (Exception e) {
        }
        super.save();
    }
    
    @Id
    @Column(name="seq", nullable=false)
    public Integer seq;
    @Column(name="personId")
    public int personId;
    @Column(name="invoiceId")
    public int invoiceId;
    @Column(name="payer")
    public String payer;
    @Column(name="schoolYear")
    public String schoolYear;
    @Column(name="cashier")
    public String cashier;
    @Column(name="orDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date orDate;
    @Column(name="orNumber")
    public String orNumber;
    @Column(name="orType")
    public String orType;
    @Column(name="gradeLevel")
    public String gradeLevel;
    @Column(name="totalAmount")
    public double totalAmount;
    
    @Column(name="payerCategory")
    public String payerCategory;

    @Column(name="paymentCode1")
    public String paymentCode1;
    @Column(name="paymentCode2")
    public String paymentCode2;
    @Column(name="paymentCode3")
    public String paymentCode3;
    @Column(name="paymentCode4")
    public String paymentCode4;
    @Column(name="paymentCode5")
    public String paymentCode5;
    @Column(name="paymentCode6")
    public String paymentCode6;
    @Column(name="paymentCode7")
    public String paymentCode7;
    @Column(name="paymentCode8")
    public String paymentCode8;
    @Column(name="paymentCode9")
    public String paymentCode9;
    @Column(name="paymentCode10")
    public String paymentCode10;

    
    @Column(name="paymentAmount1")
    public double paymentAmount1;
    @Column(name="paymentAmount2")
    public double paymentAmount2;
    @Column(name="paymentAmount3")
    public double paymentAmount3;
    @Column(name="paymentAmount4")
    public double paymentAmount4;
    @Column(name="paymentAmount5")
    public double paymentAmount5;
    @Column(name="paymentAmount6")
    public double paymentAmount6;
    @Column(name="paymentAmount7")
    public double paymentAmount7;
    @Column(name="paymentAmount8")
    public double paymentAmount8;
    @Column(name="paymentAmount9")
    public double paymentAmount9;
    @Column(name="paymentAmount10")
    public double paymentAmount10;
    
    
    @Column(name = "accountNumber1")
    public String accountNumber1;
    @Column(name = "accountNumber2")
    public String accountNumber2;
    @Column(name = "accountNumber3")
    public String accountNumber3;
    @Column(name = "accountNumber4")
    public String accountNumber4;
    @Column(name = "accountNumber5")
    public String accountNumber5;
    @Column(name = "accountNumber6")
    public String accountNumber6;
    @Column(name = "accountNumber7")
    public String accountNumber7;
    @Column(name = "accountNumber8")
    public String accountNumber8;
    @Column(name = "accountNumber9")
    public String accountNumber9;
    @Column(name = "accountNumber10")
    public String accountNumber10;

    @Column(name = "completeDesc", length=500)
    public String completeDesc;

    @Column(name="amount1")
    public double amount1;
    @Column(name="amount2")
    public double amount2;
    @Column(name="amount3")
    public double amount3;
    @Column(name="amount4")
    public double amount4;
    @Column(name="amount5")
    public double amount5;
    @Column(name="amount6")
    public double amount6;
    @Column(name="amount7")
    public double amount7;
    @Column(name="amount8")
    public double amount8;
    @Column(name="amount9")
    public double amount9;
    @Column(name="amount10")
    public double amount10;

    @Column(name = "checkNumber")
    public String checkNumber;
    @Column(name = "checkAmtNumber")
    public String checkAmtNumber;
    @Column(name = "studentNumber")
    public String studentNumber;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "payer","schoolYear", "cashier","orNumber");
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

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getCheckAmtNumber() {
		return checkAmtNumber;
	}

	public void setCheckAmtNumber(String checkAmtNumber) {
		this.checkAmtNumber = checkAmtNumber;
	}

	public String getPayerCategory() {
		return payerCategory;
	}

	public void setPayerCategory(String payerCategory) {
		this.payerCategory = payerCategory;
	}

	public String getCompleteDesc() {
        return completeDesc;
    }

    public void setCompleteDesc(String completeDesc) {
        this.completeDesc = completeDesc;
    }

    public String getOrType() {
        return orType;
    }

    public void setOrType(String orType) {
        this.orType = orType;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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

    public double getPaymentAmount1() {
        return paymentAmount1;
    }

    public void setPaymentAmount1(double paymentAmount1) {
        this.paymentAmount1 = paymentAmount1;
    }

    public double getPaymentAmount10() {
        return paymentAmount10;
    }

    public void setPaymentAmount10(double paymentAmount10) {
        this.paymentAmount10 = paymentAmount10;
    }

    public double getPaymentAmount2() {
        return paymentAmount2;
    }

    public void setPaymentAmount2(double paymentAmount2) {
        this.paymentAmount2 = paymentAmount2;
    }

    public double getPaymentAmount3() {
        return paymentAmount3;
    }

    public void setPaymentAmount3(double paymentAmount3) {
        this.paymentAmount3 = paymentAmount3;
    }

    public double getPaymentAmount4() {
        return paymentAmount4;
    }

    public void setPaymentAmount4(double paymentAmount4) {
        this.paymentAmount4 = paymentAmount4;
    }

    public double getPaymentAmount5() {
        return paymentAmount5;
    }

    public void setPaymentAmount5(double paymentAmount5) {
        this.paymentAmount5 = paymentAmount5;
    }

    public double getPaymentAmount6() {
        return paymentAmount6;
    }

    public void setPaymentAmount6(double paymentAmount6) {
        this.paymentAmount6 = paymentAmount6;
    }

    public double getPaymentAmount7() {
        return paymentAmount7;
    }

    public void setPaymentAmount7(double paymentAmount7) {
        this.paymentAmount7 = paymentAmount7;
    }

    public double getPaymentAmount8() {
        return paymentAmount8;
    }

    public void setPaymentAmount8(double paymentAmount8) {
        this.paymentAmount8 = paymentAmount8;
    }

    public double getPaymentAmount9() {
        return paymentAmount9;
    }

    public void setPaymentAmount9(double paymentAmount9) {
        this.paymentAmount9 = paymentAmount9;
    }

    public String getPaymentCode1() {
        return paymentCode1;
    }

    public void setPaymentCode1(String paymentCode1) {
        this.paymentCode1 = paymentCode1;
    }

    public String getPaymentCode10() {
        return paymentCode10;
    }

    public void setPaymentCode10(String paymentCode10) {
        this.paymentCode10 = paymentCode10;
    }

    public String getPaymentCode2() {
        return paymentCode2;
    }

    public void setPaymentCode2(String paymentCode2) {
        this.paymentCode2 = paymentCode2;
    }

    public String getPaymentCode3() {
        return paymentCode3;
    }

    public void setPaymentCode3(String paymentCode3) {
        this.paymentCode3 = paymentCode3;
    }

    public String getPaymentCode4() {
        return paymentCode4;
    }

    public void setPaymentCode4(String paymentCode4) {
        this.paymentCode4 = paymentCode4;
    }

    public String getPaymentCode5() {
        return paymentCode5;
    }

    public void setPaymentCode5(String paymentCode5) {
        this.paymentCode5 = paymentCode5;
    }

    public String getPaymentCode6() {
        return paymentCode6;
    }

    public void setPaymentCode6(String paymentCode6) {
        this.paymentCode6 = paymentCode6;
    }

    public String getPaymentCode7() {
        return paymentCode7;
    }

    public void setPaymentCode7(String paymentCode7) {
        this.paymentCode7 = paymentCode7;
    }

    public String getPaymentCode8() {
        return paymentCode8;
    }

    public void setPaymentCode8(String paymentCode8) {
        this.paymentCode8 = paymentCode8;
    }

    public String getPaymentCode9() {
        return paymentCode9;
    }

    public void setPaymentCode9(String paymentCode9) {
        this.paymentCode9 = paymentCode9;
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

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public void createInvoice() {
        
    }
    public Invoice extractInvoice() {
        Invoice inv = null;
        if (this.invoiceId == 0) {
            inv = new Invoice();
            inv.schoolYear = this.schoolYear;
            inv.invoiceDate = constants.Constants.useDate;
            inv.accountNumber = inv.accountType = "OTHERPAYMENT";
            inv.checkAmount = extractTotalCheckAmount();
            inv.totalAmount = inv.allAmount = totalAmount;
            inv.accountType = "OTHERPAYMENT";
            inv.recordId = seq;
            inv.description = completeDesc;
            inv.paymentTerms = "CASH";
            inv.orDate = orDate;
            inv.orNumber = orNumber;
            inv.orType = orType;
            inv.cashier = cashier;
            String currencyWord = AppConfig.getCurrencyWord();
            String centavoWord = AppConfig.getCentWord();
            inv.totalAmount = DataUtil.getMoneyFormat(inv.totalAmount);
            inv.amountInWord = NumberToWordConverter.convertMoney(inv.totalAmount, currencyWord, centavoWord);
            inv.payer = payer;
            Person cust = Person.extractObject(personId);
            if (cust != null) {
                inv.billTo = BeanUtil.concat(cust.personId);
                inv.shipTo=(cust.getAddress());
                inv.shipToAddress=(cust.getAddress());
                inv.studentNumber = cust.studentNumber;
                inv.gradeLevel = cust.gradeLevel;
                inv.section = cust.section;
            }
            else {
                inv.billTo = payer;
                inv.studentNumber = payerCategory;
            }
            inv.extractCheck(this);
        	if (inv.checkAmount>0) {
        		inv.checkNumber = checkNumber = checkDisplay();
        		inv.checkAmtNumber = checkAmtNumber = checkAmtDisplay();
        	}
            inv.save();
            invoiceId = inv.seq;
            save();
            for (int i=1; i<=10; i++) {
                Object obj = BeanUtil.getPropertyValue(this, BeanUtil.concat("paymentCode",i));
                double d = BeanUtil.getDoubleValue(this, BeanUtil.concat("paymentAmount",i));
                if (obj==null) continue;
                OtherPaymentReference ref = (OtherPaymentReference) OtherPaymentReference.extractObject(OtherPaymentReference.class.getSimpleName(), obj.toString());
                if (ref!=null && !ref.isEmptyKey() && d>0) {
                	OtherPaymentLineItem itm = new OtherPaymentLineItem();
                	itm.otherPaymentId = seq;
                	itm.otherPayment = ref.code;
                	itm.paymentAmount = d;
                	itm.save();
                }
            }
        }
        else {
            inv = (Invoice) AbstractIBean.firstRecord("SELECT a FROM Invoice a WHERE a.seq=",this.invoiceId);
        }
        return inv;
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
        save();
    	extractInvoice();

        AbstractReportTemplate ins = AbstractReportTemplate.getInstance();
        JasperReport rep = ins.getJasperReport("OfficialReceipt");
        Map map = new HashMap();
        ins.getReportParameter(BeanUtil.concat(invoiceId), map);
        JasperPrint print = ins.getJasperPrint(rep, map);
        ins.showReportFromFileTemplateDialog(print, title, null);
    }
    
    public Person extractStudent() {
        return (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.personId=",personId);
    }
     
    public double extractTotalAmount() {
        return
                paymentAmount1+paymentAmount2+paymentAmount3+paymentAmount4+paymentAmount5+paymentAmount6+paymentAmount7+paymentAmount8+paymentAmount9+paymentAmount10;
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
    
	@Override
	public void setupIndex() {
		List lst = DBClient.getList("SELECT a FROM OtherPayment a WHERE a.personId>0 AND a.gradeLevel IS NULL ORDER BY a.orDate DESC", 0, 10000);
		DBClient.persistBean(lst);
		runIndex(1, "invoiceId");
		runIndex(2, "orType");
		runIndex(3, "cashier", "orDate");
		runIndex(3, "seq","cashier", "orDate");
	}
}
