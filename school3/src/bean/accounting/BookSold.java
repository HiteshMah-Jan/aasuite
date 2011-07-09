/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import service.util.AbstractIBean;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.report.AbstractReportTemplate;
import template.screen.TemplateTabSinglePage;
import util.BeanUtil;
import util.DataUtil;
import util.NumberToWordConverter;
import bean.Person;
import bean.Student;
import bean.admin.AppConfig;
import bean.reference.BookSaleRef;
import bean.reference.OtherPaymentReference;
import constants.UserInfo;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "BookSold")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, 
    columnSearch = {"payer","cashier","schoolYear","completeSet","totalAmount"},
    criteriaSearch = {"personId","cashier","schoolYear","completeSet","totalAmount"}, 
    orderBy="a.schoolYear DESC, a.cashier", autoResizeTable=false)
@Displays({
        @Display(name="personId", type="PopSearch", linktoBean=Student.class, width=410, gridFieldWidth=3, label="Student"),
        @Display(name = "payer",label="Payor Name",gridFieldWidth=3,width=-1),
        @Display(name = "payerCategory",label="Category",gridFieldWidth=3,width=-1, type="Combo",
        		modelCombo={"STUDENT","EMPLOYEE","CLIENT","LIBRARY","BOOKSTORE","PARENT","JVS","EVA","ESS MAIN","ESS SOUTH","OTHERS"}),
        @Display(name="totalAmount",width=122),
        @Display(name="completeSet", type="Label"),
        @Display(name="cashier",width=170, type="Label"),
        @Display(name="schoolYear",width=122, type="Label"),
        @Display(name="orNumber",width=170, type="Label"),
        @Display(name="orDate", type="Label"),
        
        @Display(name="book1", label="1", type="PopSearch", linktoBean=BookSaleRef.class,hideLabel=true,width=200,mergeFields={"bookAmount1"}),
        @Display(name = "bookAmount1", label = "Amount",hideLabel=true,width=60),
        @Display(name="book2", label="2", type="PopSearch", linktoBean=BookSaleRef.class,hideLabel=true,width=200,mergeFields={"bookAmount2"}),
        @Display(name = "bookAmount2", label = "Amount",hideLabel=true,width=60),
        @Display(name="book3", label="3", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount3"}),
        @Display(name = "bookAmount3", label = "Amount", hideLabel = true,width=60),
        @Display(name="book4", label="4", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount4"}),
        @Display(name = "bookAmount4", label = "Amount", hideLabel = true,width=60),
        @Display(name="book5", label="5", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount5"}),
        @Display(name = "bookAmount5", label = "Amount", hideLabel = true,width=60),
        @Display(name="book6", label="6", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount6"}),
        @Display(name = "bookAmount6", label = "Amount", hideLabel = true,width=60),
        @Display(name="book7", label="7", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount7"}),
        @Display(name = "bookAmount7", label = "Amount", hideLabel = true,width=60),
        @Display(name="book8", label="8", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount8"}),
        @Display(name = "bookAmount8", label = "Amount", hideLabel = true,width=60),
        @Display(name="book9", label="9", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount9"}),
        @Display(name = "bookAmount9", label = "Amount", hideLabel = true,width=60),
        @Display(name="book10", label="10", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount10"}),
        @Display(name = "bookAmount10", label = "Amount", hideLabel = true,width=60),
        @Display(name="book11", label="11", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount11"}),
        @Display(name = "bookAmount11", label = "Amount", hideLabel = true,width=60),
        @Display(name="book12", label="12", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount12"}),
        @Display(name = "bookAmount12", label = "Amount", hideLabel = true,width=60),
        @Display(name="book13", label="13", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount13"}),
        @Display(name = "bookAmount13", label = "Amount", hideLabel = true,width=60),
        @Display(name="book14", label="14", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount14"}),
        @Display(name = "bookAmount14", label = "Amount", hideLabel = true,width=60),
        @Display(name="book15", label="15", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount15"}),
        @Display(name = "bookAmount15", label = "Amount", hideLabel = true,width=60),
        @Display(name="book16", label="16", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount16"}),
        @Display(name = "bookAmount16", label = "Amount", hideLabel = true,width=60),
        @Display(name="book17", label="17", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount17"}),
        @Display(name = "bookAmount17", label = "Amount", hideLabel = true,width=60),
        @Display(name="book18", label="18", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount18"}),
        @Display(name = "bookAmount18", label = "Amount", hideLabel = true,width=60),
        @Display(name="book19", label="19", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount19"}),
        @Display(name = "bookAmount19", label = "Amount", hideLabel = true,width=60),
        @Display(name="book20", label="20", type="PopSearch", linktoBean=BookSaleRef.class, hideLabel = true,width=200,mergeFields={"bookAmount20"}),
        @Display(name = "bookAmount20", label = "Amount", hideLabel = true,width=60)
})
@DisplayGroups({
    @DisplayGroup(gridCount=4, title="Books", fields={
    "book1","bookAmount1",
    "book2","bookAmount2",
    "book3","bookAmount3",
    "book4","bookAmount4",
    "book5","bookAmount5",
    "book6","bookAmount6",
    "book7","bookAmount7",
    "book8","bookAmount8",
    "book9","bookAmount9",
    "book10","bookAmount10",
    "book11","bookAmount11",
    "book12","bookAmount12",
    "book13","bookAmount13",
    "book14","bookAmount14",
    "book15","bookAmount15",
    "book16","bookAmount16",
    "book17","bookAmount17",
    "book18","bookAmount18",
    "book19","bookAmount19",
    "book20","bookAmount20"    
    })
     
})
public class BookSold extends AbstractIBean {
 
    public static void main(String[] args) {
        view(BookSold.class);
    }
    
    public BookSold() {
        cashier = UserInfo.getUserName();
        orDate = constants.Constants.useDate;
//        orNumber = new CashierDailyBooklet().extractNextOR();
        orType = "N";
        schoolYear = springbean.SchoolConfig.getSchoolYear();
    }


    @Override
    public void save() {
//        setup complete description
        StringBuffer sb = new StringBuffer();
        for (int i=1; i<=20; i++) {
            Object obj = BeanUtil.getPropertyValue(this, BeanUtil.concat("book",i));
            BookSaleRef ref = (BookSaleRef) BookSaleRef.extractObject(BookSaleRef.class.getSimpleName(), obj.toString());
            if (ref!=null && !ref.isEmptyKey() && ref.title!=null && !ref.title.isEmpty()) {
                sb.append(ref.title).append(", ");
            }
        }
        if (this.isCompleteSet()) {
        	completeDesc = BeanUtil.concat("1 SET BOOK - ",gradeLevelDesc());
        }
        else {
            completeDesc = sb.toString().trim();
            completeDesc = completeDesc.substring(0, completeDesc.length()-1);
        }
        try {
            Student stud = extractStudent();
            payer = stud.toString();
            studentNumber = stud.studentNumber;
        }
        catch (Exception e) {
        }
        super.save();
    }

    protected String gradeLevelDesc() {
    	if (gradeLevel!=null) {
    		return Invoice.extractGradeLevelDescription(gradeLevel);
    	}
    	return "";
    }
    @Id
    @Column(name="seq", nullable=false)
    public Integer seq;
    @Column(name="personId")
    public int personId;
    @Column(name="invoiceId")
    public int invoiceId;
    @Column(name="schoolYear")
    public String schoolYear;
    @Column(name="cashier")
    public String cashier;
    @Column(name="completeSet")
    public boolean completeSet;
    @Column(name="orDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date orDate;
    @Column(name="orNumber")
    public String orNumber;
    @Column(name="accountNumber")
    public String accountNumber;
    @Column(name="orType")
    public String orType;
    @Column(name="allBooks")
    public String allBooks;
    @Column(name="totalAmount")
    public double totalAmount;

    @Column(name = "completeDesc", length=500)
    public String completeDesc;
    @Column(name="payer")
    public String payer;
    @Column(name="payerCategory")
    public String payerCategory;
    @Column(name="gradeLevel")
    public String gradeLevel;

    @Column(name="book1")
    public int book1;
    @Column(name="book2")
    public int book2;
    @Column(name="book3")
    public int book3;
    @Column(name="book4")
    public int book4;
    @Column(name="book5")
    public int book5;
    @Column(name="book6")
    public int book6;
    @Column(name="book7")
    public int book7;
    @Column(name="book8")
    public int book8;
    @Column(name="book9")
    public int book9;
    @Column(name="book10")
    public int book10;

    @Column(name="book11")
    public int book11;
    @Column(name="book12")
    public int book12;
    @Column(name="book13")
    public int book13;
    @Column(name="book14")
    public int book14;
    @Column(name="book15")
    public int book15;
    @Column(name="book16")
    public int book16;
    @Column(name="book17")
    public int book17;
    @Column(name="book18")
    public int book18;
    @Column(name="book19")
    public int book19;
    @Column(name="book20")
    public int book20;

    @Column(name="bookAmount1")
    public double bookAmount1;
    @Column(name="bookAmount2")
    public double bookAmount2;
    @Column(name="bookAmount3")
    public double bookAmount3;
    @Column(name="bookAmount4")
    public double bookAmount4;
    @Column(name="bookAmount5")
    public double bookAmount5;
    @Column(name="bookAmount6")
    public double bookAmount6;
    @Column(name="bookAmount7")
    public double bookAmount7;
    @Column(name="bookAmount8")
    public double bookAmount8;
    @Column(name="bookAmount9")
    public double bookAmount9;
    @Column(name="bookAmount10")
    public double bookAmount10;

    @Column(name="bookAmount11")
    public double bookAmount11;
    @Column(name="bookAmount12")
    public double bookAmount12;
    @Column(name="bookAmount13")
    public double bookAmount13;
    @Column(name="bookAmount14")
    public double bookAmount14;
    @Column(name="bookAmount15")
    public double bookAmount15;
    @Column(name="bookAmount16")
    public double bookAmount16;
    @Column(name="bookAmount17")
    public double bookAmount17;
    @Column(name="bookAmount18")
    public double bookAmount18;
    @Column(name="bookAmount19")
    public double bookAmount19;
    @Column(name="bookAmount20")
    public double bookAmount20;

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

    @Column(name = "checkNumber")
    public String checkNumber;
    @Column(name = "checkAmtNumber")
    public String checkAmtNumber;
    @Column(name = "studentNumber")
    public String studentNumber;

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

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getPayerCategory() {
		return payerCategory;
	}

	public void setPayerCategory(String payerCategory) {
		this.payerCategory = payerCategory;
	}

	public String getPayer() {
        return payer;
    }

    public String getAccountNumber1() {
		return accountNumber1;
	}

	public void setAccountNumber1(String accountNumber1) {
		this.accountNumber1 = accountNumber1;
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

	public String getAccountNumber10() {
		return accountNumber10;
	}

	public void setAccountNumber10(String accountNumber10) {
		this.accountNumber10 = accountNumber10;
	}

	public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getCompleteDesc() {
        return completeDesc;
    }

    public void setCompleteDesc(String completeDesc) {
        this.completeDesc = completeDesc;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(String allBooks) {
        this.allBooks = allBooks;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "schoolYear","orNumber", "cashier");
    }

    public String getOrType() {
        return orType;
    }

    public void setOrType(String orType) {
        this.orType = orType;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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

    public int getBook1() {
        return book1;
    }

    public void setBook1(int book1) {
        this.book1 = book1;
    }

    public int getBook10() {
        return book10;
    }

    public void setBook10(int book10) {
        this.book10 = book10;
    }

    public int getBook11() {
        return book11;
    }

    public void setBook11(int book11) {
        this.book11 = book11;
    }

    public int getBook12() {
        return book12;
    }

    public void setBook12(int book12) {
        this.book12 = book12;
    }

    public int getBook13() {
        return book13;
    }

    public void setBook13(int book13) {
        this.book13 = book13;
    }

    public int getBook14() {
        return book14;
    }

    public void setBook14(int book14) {
        this.book14 = book14;
    }

    public int getBook15() {
        return book15;
    }

    public void setBook15(int book15) {
        this.book15 = book15;
    }

    public int getBook16() {
        return book16;
    }

    public void setBook16(int book16) {
        this.book16 = book16;
    }

    public int getBook17() {
        return book17;
    }

    public void setBook17(int book17) {
        this.book17 = book17;
    }

    public int getBook18() {
        return book18;
    }

    public void setBook18(int book18) {
        this.book18 = book18;
    }

    public int getBook19() {
        return book19;
    }

    public void setBook19(int book19) {
        this.book19 = book19;
    }

    public int getBook2() {
        return book2;
    }

    public void setBook2(int book2) {
        this.book2 = book2;
    }

    public int getBook20() {
        return book20;
    }

    public void setBook20(int book20) {
        this.book20 = book20;
    }

    public int getBook3() {
        return book3;
    }

    public void setBook3(int book3) {
        this.book3 = book3;
    }

    public int getBook4() {
        return book4;
    }

    public void setBook4(int book4) {
        this.book4 = book4;
    }

    public int getBook5() {
        return book5;
    }

    public void setBook5(int book5) {
        this.book5 = book5;
    }

    public int getBook6() {
        return book6;
    }

    public void setBook6(int book6) {
        this.book6 = book6;
    }

    public int getBook7() {
        return book7;
    }

    public void setBook7(int book7) {
        this.book7 = book7;
    }

    public int getBook8() {
        return book8;
    }

    public void setBook8(int book8) {
        this.book8 = book8;
    }

    public int getBook9() {
        return book9;
    }

    public void setBook9(int book9) {
        this.book9 = book9;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public boolean isCompleteSet() {
        return completeSet;
    }

    public void setCompleteSet(boolean completeSet) {
        this.completeSet = completeSet;
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

    public double getBookAmount1() {
		return bookAmount1;
	}

	public void setBookAmount1(double bookAmount1) {
		this.bookAmount1 = bookAmount1;
	}

	public double getBookAmount2() {
		return bookAmount2;
	}

	public void setBookAmount2(double bookAmount2) {
		this.bookAmount2 = bookAmount2;
	}

	public double getBookAmount3() {
		return bookAmount3;
	}

	public void setBookAmount3(double bookAmount3) {
		this.bookAmount3 = bookAmount3;
	}

	public double getBookAmount4() {
		return bookAmount4;
	}

	public void setBookAmount4(double bookAmount4) {
		this.bookAmount4 = bookAmount4;
	}

	public double getBookAmount5() {
		return bookAmount5;
	}

	public void setBookAmount5(double bookAmount5) {
		this.bookAmount5 = bookAmount5;
	}

	public double getBookAmount6() {
		return bookAmount6;
	}

	public void setBookAmount6(double bookAmount6) {
		this.bookAmount6 = bookAmount6;
	}

	public double getBookAmount7() {
		return bookAmount7;
	}

	public void setBookAmount7(double bookAmount7) {
		this.bookAmount7 = bookAmount7;
	}

	public double getBookAmount8() {
		return bookAmount8;
	}

	public void setBookAmount8(double bookAmount8) {
		this.bookAmount8 = bookAmount8;
	}

	public double getBookAmount9() {
		return bookAmount9;
	}

	public void setBookAmount9(double bookAmount9) {
		this.bookAmount9 = bookAmount9;
	}

	public double getBookAmount10() {
		return bookAmount10;
	}

	public void setBookAmount10(double bookAmount10) {
		this.bookAmount10 = bookAmount10;
	}

	public double getBookAmount11() {
		return bookAmount11;
	}

	public void setBookAmount11(double bookAmount11) {
		this.bookAmount11 = bookAmount11;
	}

	public double getBookAmount12() {
		return bookAmount12;
	}

	public void setBookAmount12(double bookAmount12) {
		this.bookAmount12 = bookAmount12;
	}

	public double getBookAmount13() {
		return bookAmount13;
	}

	public void setBookAmount13(double bookAmount13) {
		this.bookAmount13 = bookAmount13;
	}

	public double getBookAmount14() {
		return bookAmount14;
	}

	public void setBookAmount14(double bookAmount14) {
		this.bookAmount14 = bookAmount14;
	}

	public double getBookAmount15() {
		return bookAmount15;
	}

	public void setBookAmount15(double bookAmount15) {
		this.bookAmount15 = bookAmount15;
	}

	public double getBookAmount16() {
		return bookAmount16;
	}

	public void setBookAmount16(double bookAmount16) {
		this.bookAmount16 = bookAmount16;
	}

	public double getBookAmount17() {
		return bookAmount17;
	}

	public void setBookAmount17(double bookAmount17) {
		this.bookAmount17 = bookAmount17;
	}

	public double getBookAmount18() {
		return bookAmount18;
	}

	public void setBookAmount18(double bookAmount18) {
		this.bookAmount18 = bookAmount18;
	}

	public double getBookAmount19() {
		return bookAmount19;
	}

	public void setBookAmount19(double bookAmount19) {
		this.bookAmount19 = bookAmount19;
	}

	public double getBookAmount20() {
		return bookAmount20;
	}

	public void setBookAmount20(double bookAmount20) {
		this.bookAmount20 = bookAmount20;
	}

	public Invoice extractInvoice() {
        Invoice inv = null;
        if (this.invoiceId == 0) {
            inv = new Invoice();
            inv.schoolYear = this.schoolYear;
            inv.invoiceDate = constants.Constants.useDate;
            inv.accountNumber = inv.accountType = "BOOKSOLD";
            inv.checkAmount = extractTotalCheckAmount();
            inv.totalAmount = inv.allAmount = totalAmount;
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
            for (int i=1; i<=20; i++) {
                Object obj = BeanUtil.getPropertyValue(this, BeanUtil.concat("book",i));
                double d = BeanUtil.getDoubleValue(this, BeanUtil.concat("bookAmount",i));
                if (obj==null) continue;
                OtherPaymentReference ref = (OtherPaymentReference) OtherPaymentReference.extractObject(OtherPaymentReference.class.getSimpleName(), obj.toString());
                if (ref!=null && !ref.isEmptyKey() && d>0) {
                	BookSoldLineItem itm = new BookSoldLineItem();
                	itm.bookSoldId = seq;
                	itm.book = ref.code;
                	itm.amount = d;
                	itm.save();
                }
            }
        }
        else {
            inv = (Invoice) AbstractIBean.firstRecord("SELECT a FROM Invoice a WHERE a.seq=" , this.invoiceId);
        }
        return inv;
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

    public double extractTotalAmount() {
        return
        	bookAmount1+
        	bookAmount2+
        	bookAmount3+
        	bookAmount4+
        	bookAmount5+
        	bookAmount6+
        	bookAmount7+
        	bookAmount8+
        	bookAmount9+
        	bookAmount10+
        	bookAmount11+
        	bookAmount12+
        	bookAmount13+
        	bookAmount14+
        	bookAmount15+
        	bookAmount16+
        	bookAmount17+
        	bookAmount18+
        	bookAmount19+
        	bookAmount20;
    }

    public Student extractStudent() {
        return (Student) AbstractIBean.firstRecord("SELECT a FROM Student a WHERE a.personId=",personId);
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
		runIndex(1, "invoiceId");
	}
}
