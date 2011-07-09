/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting.payroll;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;
import service.util.AbstractIBean;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author asmiranda
 */
@Entity
@Table(name = "AlphaListEmployee")
@UITemplate(template=TemplateTabPage.class,
		columnSearch={"employeeId","tin","lastName","firstName","middleName"},
                criteriaSearch={"employeeId","lastName","firstName","middleName"}, 
		gridCount=10, title="Alpha List Employee")		
@DisplayGroups({
    @DisplayGroup(title = "Employee Information", gridCount =6, 
    	fields = {
    		"lastName","firstName","middleName","address",
                "tin","status","atc","incomeNature","incomeAmount","taxRate","taxWithheld","fringeBenefitAmount","grossedUpMonetaryValue"}),           
   @DisplayGroup(title = "Previous Employment", gridCount =6, 
    	fields = {
    		"prevSss","prevGsis","prevPhic","prevPagibig","prevUnionAmount","prevNonTaxBenefitAnd13Month",
                "prevNonTaxCompensation","prevNonTaxSalaries","prevTaxBenefitAnd13Month","prevTaxCompensation","prevTaxSalaries"}),
   @DisplayGroup(title = "Present Employment", gridCount =6, 
    	fields = {
    		"sss","gsis","phic","pagibig","unionAmount","nonTaxBenefitAnd13Month",
                "nonTaxCompensation","nonTaxSalaries","taxBenefitAnd13Month","taxCompensation","taxSalaries","exemptionAmount",
                "premPaidOnHealthIns","taxDueJantoDec","taxDueJanToNov","yearEndTax","yearEndTaxRefund","yearEndTaxAdjusted"})
                })
                
@Displays({
       // @Display(name="alphalistId"),
       // @Display(name="employeeId",width=60,type="Label"),
        @Display(name="isSubjectToFinalWTax"),
        @Display(name="isIncludeInFringeBenefit"),
        @Display(name="isTerminatedBeforeDec31"),
        @Display(name="isWithPrevEmployer"),
        @Display(name="isWithExempt"),
        
        
        @Display(name="lastName",width=150,labelTop=true,leftLabel="Name"),
        @Display(name="firstName",width=150,labelTop=true),
        @Display(name="middleName",width=150,labelTop=true),
        @Display(name="address",gridFieldWidth=5,width=-1),
        
        @Display(name="tin",labelTop=true,width=150),
        @Display(name="status",labelTop=true,width=150),
        @Display(name="atc",labelTop=true,width=150),
        @Display(name="incomeNature",labelTop=true,width=150),
        @Display(name="incomeAmount",labelTop=true,width=150),
        @Display(name="taxRate",labelTop=true,width=150),
        @Display(name="taxWithheld",labelTop=true,width=150),
        @Display(name="fringeBenefitAmount",labelTop=true,width=150),
        @Display(name="grossedUpMonetaryValue",labelTop=true,width=150),
        
        @Display(name="prevSss",width=120,label="SSS"),
        @Display(name="prevGsis",width=120,label="GSIS"),
        @Display(name="prevPhic",width=120,label="Phic"),
        @Display(name="prevPagibig",width=120,label="Pagibig"),
        @Display(name="prevUnionAmount",width=120,label="Union Amount"),
        @Display(name="prevNonTaxBenefitAnd13Month",width=120,label="Non Tax Benefit and 13 Month"),
        @Display(name="prevNonTaxCompensation",width=120,label="Non Tax Compensation"),
        @Display(name="prevNonTaxSalaries",width=120,label="Non Tax Salaries       "),
        @Display(name="prevTaxBenefitAnd13Month",width=120,label="Tax Benefit and 13 Month"),
        @Display(name="prevTaxCompensation",width=120,label="Tax Compensation"),
        @Display(name="prevTaxSalaries",width=120,label="Tax Salaries"),
        
        @Display(name="sss",width=120,label="SSS"),
        @Display(name="gsis",width=120,label="GSIS"),
        @Display(name="phic",width=120,label="Phic"),
        @Display(name="pagibig",width=120,label="Pagibig"),
        @Display(name="unionAmount",width=120,label="Union Amount"),
        @Display(name="nonTaxBenefitAnd13Month",width=120,label="Non Tax Benefit and 13 Month"),
        @Display(name="nonTaxCompensation",width=120,label="Non Tax Compensation"),
        @Display(name="nonTaxSalaries",width=120,label="Non Tax Salaries"),
        @Display(name="taxBenefitAnd13Month",width=120,label="Tax Benefit and 13 Month"),
        @Display(name="taxCompensation",width=120,label="Tax Compensation"),
        @Display(name="taxSalaries",width=120,label="Tax Salaries"),
        @Display(name="exemptionAmount",width=120),
        @Display(name="premPaidOnHealthIns",width=120,label="Prem Paid on Health Ins"),
        @Display(name="taxDueJantoDec",width=120),
        @Display(name="taxDueJanToNov",width=120),
        @Display(name="yearEndTax",width=120),
        @Display(name="yearEndTaxRefund",width=120),
        @Display(name="yearEndTaxAdjusted",width=120)        
})
public class AlphaListEmployee extends AbstractIBean {
    public static void main(String[] args) {
        view(AlphaListEmployee.class);
    }
    @Id
    @Column(name="seq", nullable = false)
    public Integer seq;
    @Column(name="alphalistId")
    public int alphalistId;
    @Column(name="employeeId")
    public int employeeId;

    @Column(name="isSubjectToFinalWTax")
    public boolean isSubjectToFinalWTax;
    @Column(name="isIncludeInFringeBenefit")
    public boolean isIncludeInFringeBenefit;
    @Column(name="isTerminatedBeforeDec31")
    public boolean isTerminatedBeforeDec31;
    @Column(name="isWithPrevEmployer")
    public boolean isWithPrevEmployer;
    @Column(name="isWithExempt")
    public boolean isWithExempt;

    @Column(name="tin")
    public String tin;
    @Column(name="lastName")
    public String lastName;
    @Column(name="firstName")
    public String firstName;
    @Column(name="middleName")
    public String middleName;
    @Column(name="address")
    public String address;
    @Column(name="status")
    public String status;
    @Column(name="atc")
    public String atc;
    @Column(name="incomeNature")
    public String incomeNature;
    @Column(name="incomeAmount")
    public double incomeAmount;
    @Column(name="taxRate")
    public double taxRate;
    @Column(name="taxWithheld")
    public double taxWithheld;

    //for employee with fringe benefit
    @Column(name="fringeBenefitAmount")
    public double fringeBenefitAmount;
    @Column(name="grossedUpMonetaryValue")
    public double grossedUpMonetaryValue;

    //non taxable previous
    @Column(name="prevSss")
    public double prevSss;
    @Column(name="prevGsis")
    public double prevGsis;
    @Column(name="prevPhic")
    public double prevPhic;
    @Column(name="prevPagibig")
    public double prevPagibig;
    @Column(name="prevUnionAmount")
    public double prevUnionAmount;
    @Column(name="prevNonTaxBenefitAnd13Month")
    public double prevNonTaxBenefitAnd13Month;
    @Column(name="prevNonTaxCompensation")
    public double prevNonTaxCompensation;
    @Column(name="prevNonTaxSalaries")
    public double prevNonTaxSalaries;

    @Column(name="prevTaxBenefitAnd13Month")
    public double prevTaxBenefitAnd13Month;
    @Column(name="prevTaxCompensation")
    public double prevTaxCompensation;
    @Column(name="prevTaxSalaries")
    public double prevTaxSalaries;

    //non taxable current
    @Column(name="sss")
    public double sss;
    @Column(name="gsis")
    public double gsis;
    @Column(name="phic")
    public double phic;
    @Column(name="pagibig")
    public double pagibig;
    @Column(name="unionAmount")
    public double unionAmount;
    @Column(name="nonTaxBenefitAnd13Month")
    public double nonTaxBenefitAnd13Month;
    @Column(name="nonTaxCompensation")
    public double nonTaxCompensation;
    @Column(name="nonTaxSalaries")
    public double nonTaxSalaries;

    @Column(name="taxBenefitAnd13Month")
    public double taxBenefitAnd13Month;
    @Column(name="taxCompensation")
    public double taxCompensation;
    @Column(name="taxSalaries")
    public double taxSalaries;

    @Column(name="exemptionAmount")
    public double exemptionAmount;
    @Column(name="premPaidOnHealthIns")
    public double premPaidOnHealthIns;

    @Column(name="taxDueJantoDec")
    public double taxDueJantoDec;
    @Column(name="taxDueJanToNov")
    public double taxDueJanToNov;
    @Column(name="yearEndTax")
    public double yearEndTax;
    @Column(name="yearEndTaxRefund")
    public double yearEndTaxRefund;
    @Column(name="yearEndTaxAdjusted")
    public double yearEndTaxAdjusted;

    public boolean isSubjectToFinalWTax() {
		return isSubjectToFinalWTax;
	}

	public void setSubjectToFinalWTax(boolean isSubjectToFinalWTax) {
		this.isSubjectToFinalWTax = isSubjectToFinalWTax;
	}

	public boolean isIncludeInFringeBenefit() {
		return isIncludeInFringeBenefit;
	}

	public void setIncludeInFringeBenefit(boolean isIncludeInFringeBenefit) {
		this.isIncludeInFringeBenefit = isIncludeInFringeBenefit;
	}

	public boolean isTerminatedBeforeDec31() {
		return isTerminatedBeforeDec31;
	}

	public void setTerminatedBeforeDec31(boolean isTerminatedBeforeDec31) {
		this.isTerminatedBeforeDec31 = isTerminatedBeforeDec31;
	}

	public boolean isWithPrevEmployer() {
		return isWithPrevEmployer;
	}

	public void setWithPrevEmployer(boolean isWithPrevEmployer) {
		this.isWithPrevEmployer = isWithPrevEmployer;
	}

	public boolean isWithExempt() {
		return isWithExempt;
	}

	public void setWithExempt(boolean isWithExempt) {
		this.isWithExempt = isWithExempt;
	}

	public boolean isIsSubjectToFinalWTax() {
        return isSubjectToFinalWTax;
    }

    public void setIsSubjectToFinalWTax(boolean isSubjectToFinalWTax) {
        this.isSubjectToFinalWTax = isSubjectToFinalWTax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAlphalistId() {
        return alphalistId;
    }

    public void setAlphalistId(int alphalistId) {
        this.alphalistId = alphalistId;
    }

    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getExemptionAmount() {
        return exemptionAmount;
    }

    public void setExemptionAmount(double exemptionAmount) {
        this.exemptionAmount = exemptionAmount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getFringeBenefitAmount() {
        return fringeBenefitAmount;
    }

    public void setFringeBenefitAmount(double fringeBenefitAmount) {
        this.fringeBenefitAmount = fringeBenefitAmount;
    }

    public double getGrossedUpMonetaryValue() {
        return grossedUpMonetaryValue;
    }

    public void setGrossedUpMonetaryValue(double grossedUpMonetaryValue) {
        this.grossedUpMonetaryValue = grossedUpMonetaryValue;
    }

    public double getGsis() {
        return gsis;
    }

    public void setGsis(double gsis) {
        this.gsis = gsis;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getIncomeNature() {
        return incomeNature;
    }

    public void setIncomeNature(String incomeNature) {
        this.incomeNature = incomeNature;
    }

    public boolean isIsIncludeInFringeBenefit() {
        return isIncludeInFringeBenefit;
    }

    public void setIsIncludeInFringeBenefit(boolean isIncludeInFringeBenefit) {
        this.isIncludeInFringeBenefit = isIncludeInFringeBenefit;
    }

    public boolean isIsTerminatedBeforeDec31() {
        return isTerminatedBeforeDec31;
    }

    public void setIsTerminatedBeforeDec31(boolean isTerminatedBeforeDec31) {
        this.isTerminatedBeforeDec31 = isTerminatedBeforeDec31;
    }

    public boolean isIsWithExempt() {
        return isWithExempt;
    }

    public void setIsWithExempt(boolean isWithExempt) {
        this.isWithExempt = isWithExempt;
    }

    public boolean isIsWithPrevEmployer() {
        return isWithPrevEmployer;
    }

    public void setIsWithPrevEmployer(boolean isWithPrevEmployer) {
        this.isWithPrevEmployer = isWithPrevEmployer;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public double getNonTaxBenefitAnd13Month() {
        return nonTaxBenefitAnd13Month;
    }

    public void setNonTaxBenefitAnd13Month(double nonTaxBenefitAnd13Month) {
        this.nonTaxBenefitAnd13Month = nonTaxBenefitAnd13Month;
    }

    public double getNonTaxCompensation() {
        return nonTaxCompensation;
    }

    public void setNonTaxCompensation(double nonTaxCompensation) {
        this.nonTaxCompensation = nonTaxCompensation;
    }

    public double getNonTaxSalaries() {
        return nonTaxSalaries;
    }

    public void setNonTaxSalaries(double nonTaxSalaries) {
        this.nonTaxSalaries = nonTaxSalaries;
    }

    public double getPagibig() {
        return pagibig;
    }

    public void setPagibig(double pagibig) {
        this.pagibig = pagibig;
    }

    public double getPhic() {
        return phic;
    }

    public void setPhic(double phic) {
        this.phic = phic;
    }

    public double getPremPaidOnHealthIns() {
        return premPaidOnHealthIns;
    }

    public void setPremPaidOnHealthIns(double premPaidOnHealthIns) {
        this.premPaidOnHealthIns = premPaidOnHealthIns;
    }

    public double getPrevGsis() {
        return prevGsis;
    }

    public void setPrevGsis(double prevGsis) {
        this.prevGsis = prevGsis;
    }

    public double getPrevNonTaxBenefitAnd13Month() {
        return prevNonTaxBenefitAnd13Month;
    }

    public void setPrevNonTaxBenefitAnd13Month(double prevNonTaxBenefitAnd13Month) {
        this.prevNonTaxBenefitAnd13Month = prevNonTaxBenefitAnd13Month;
    }

    public double getPrevNonTaxCompensation() {
        return prevNonTaxCompensation;
    }

    public void setPrevNonTaxCompensation(double prevNonTaxCompensation) {
        this.prevNonTaxCompensation = prevNonTaxCompensation;
    }

    public double getPrevNonTaxSalaries() {
        return prevNonTaxSalaries;
    }

    public void setPrevNonTaxSalaries(double prevNonTaxSalaries) {
        this.prevNonTaxSalaries = prevNonTaxSalaries;
    }

    public double getPrevPagibig() {
        return prevPagibig;
    }

    public void setPrevPagibig(double prevPagibig) {
        this.prevPagibig = prevPagibig;
    }

    public double getPrevPhic() {
        return prevPhic;
    }

    public void setPrevPhic(double prevPhic) {
        this.prevPhic = prevPhic;
    }

    public double getPrevSss() {
        return prevSss;
    }

    public void setPrevSss(double prevSss) {
        this.prevSss = prevSss;
    }

    public double getPrevTaxBenefitAnd13Month() {
        return prevTaxBenefitAnd13Month;
    }

    public void setPrevTaxBenefitAnd13Month(double prevTaxBenefitAnd13Month) {
        this.prevTaxBenefitAnd13Month = prevTaxBenefitAnd13Month;
    }

    public double getPrevTaxCompensation() {
        return prevTaxCompensation;
    }

    public void setPrevTaxCompensation(double prevTaxCompensation) {
        this.prevTaxCompensation = prevTaxCompensation;
    }

    public double getPrevTaxSalaries() {
        return prevTaxSalaries;
    }

    public void setPrevTaxSalaries(double prevTaxSalaries) {
        this.prevTaxSalaries = prevTaxSalaries;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getSss() {
        return sss;
    }

    public void setSss(double sss) {
        this.sss = sss;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTaxBenefitAnd13Month() {
        return taxBenefitAnd13Month;
    }

    public void setTaxBenefitAnd13Month(double taxBenefitAnd13Month) {
        this.taxBenefitAnd13Month = taxBenefitAnd13Month;
    }

    public double getTaxCompensation() {
        return taxCompensation;
    }

    public void setTaxCompensation(double taxCompensation) {
        this.taxCompensation = taxCompensation;
    }

    public double getTaxDueJanToNov() {
        return taxDueJanToNov;
    }

    public void setTaxDueJanToNov(double taxDueJanToNov) {
        this.taxDueJanToNov = taxDueJanToNov;
    }

    public double getTaxDueJantoDec() {
        return taxDueJantoDec;
    }

    public void setTaxDueJantoDec(double taxDueJantoDec) {
        this.taxDueJantoDec = taxDueJantoDec;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxSalaries() {
        return taxSalaries;
    }

    public void setTaxSalaries(double taxSalaries) {
        this.taxSalaries = taxSalaries;
    }

    public double getTaxWithheld() {
        return taxWithheld;
    }

    public void setTaxWithheld(double taxWithheld) {
        this.taxWithheld = taxWithheld;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public double getPrevUnionAmount() {
        return prevUnionAmount;
    }

    public void setPrevUnionAmount(double prevUnionAmount) {
        this.prevUnionAmount = prevUnionAmount;
    }

    public double getUnionAmount() {
        return unionAmount;
    }

    public void setUnionAmount(double unionAmount) {
        this.unionAmount = unionAmount;
    }

    public double getYearEndTax() {
        return yearEndTax;
    }

    public void setYearEndTax(double yearEndTax) {
        this.yearEndTax = yearEndTax;
    }

    public double getYearEndTaxAdjusted() {
        return yearEndTaxAdjusted;
    }

    public void setYearEndTaxAdjusted(double yearEndTaxAdjusted) {
        this.yearEndTaxAdjusted = yearEndTaxAdjusted;
    }

    public double getYearEndTaxRefund() {
        return yearEndTaxRefund;
    }

    public void setYearEndTaxRefund(double yearEndTaxRefund) {
        this.yearEndTaxRefund = yearEndTaxRefund;
    }

    @Override
    public String popupSearch(String criteria) {
        return null;
    }

}
