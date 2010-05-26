/*
 * Person.java
 * 
 * Created on Oct 26, 2007, 9:34:48 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.hr.HRAssessment;
import bean.person.*;
import bean.accounting.payroll.Payroll;
import bean.accounting.payroll.PersonAttendance;
import bean.reference.BenefitType;
import bean.reference.DeductionType;
import bean.reference.Department;
import bean.reference.TaxTable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import constants.UserInfo;
import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateTabPage; 
import util.BeanUtil;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Person")
@UITemplate(canBackup=false, template = TemplateTabPage.class, gridCount = 6, 
		columnSearch = {"lastName", "firstName"},
		showImages=true)
@ChildRecords(
    value={
       @ChildRecord(title="Experience", entity = PersonExperience.class, sql = "SELECT a FROM PersonExperience a WHERE a.personId=${personId}"),
       @ChildRecord(title="Education", entity = PersonEducation.class, sql = "SELECT a FROM PersonEducation a WHERE a.personId=${personId}"),
       @ChildRecord(title="Dependent", entity = PersonDependent.class, sql = "SELECT a FROM PersonDependent a WHERE a.personId=${personId}"),
       @ChildRecord(title="Position History", entity = PersonPositionHistory.class, sql = "SELECT a FROM PersonPositionHistory a WHERE a.personId=${personId}"),
       @ChildRecord(title="Assessment", entity = HRAssessment.class, sql = "SELECT a FROM HRAssessment a WHERE a.personId=${personId}"),
       @ChildRecord(title="Benefit", entity = EmployeeBenefit.class, fieldMapping={"personId","personId"}, sql = "SELECT a FROM EmployeeBenefit a WHERE a.personId=${personId}"),
       @ChildRecord(title="Deduction", entity = EmployeeDeduction.class, fieldMapping={"personId","personId"}, sql = "SELECT a FROM EmployeeDeduction a WHERE a.personId=${personId}"),
       @ChildRecord(title="Leave", entity = EmployeeLeaveApplication.class, fieldMapping={"personId","personId"}, sql = "SELECT a FROM EmployeeLeaveApplication a WHERE a.personId=${personId}"),
       @ChildRecord(title="Notes", entity = PersonNotes.class, sql = "SELECT a FROM PersonNotes a WHERE a.personId=${personId}"),
       @ChildRecord(title="Attendance", entity = PersonAttendance.class, sql = "SELECT a FROM PersonAttendance a WHERE a.personId=${personId}"),
       @ChildRecord(title="Loan", entity = EmployeeLoan.class, sql = "SELECT a FROM EmployeeLoan a WHERE a.personId=${personId}"),
       @ChildRecord(title="Paid Loan", entity = EmployeeLoan.class, sql = "SELECT a FROM PaymentLoan a WHERE a.employeeId=${personId}")
 },
    info={
        @ParentAddInfo(title="Personal Information", displayFields={"hiredDate", "email","birthDate","gender","age","citizenship","sssNumber","maritalStatus","religion","address", "contactNumber","placeOfBirth","tinNumber","pagibigNumber","philhealthNumber","address1","contactNumber1"})
}
)
@DiscriminatorValue("EMPLOYEE")
@Displays({
        @Display(name="employeeNumber",addInfoOnly=true),
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="middleInitial",width=20,label="M.I"),
        @Display(name="department",type="PopSearch",linktoBean=Department.class),
        @Display(name="position",type="Combo",modelCombo={"DIRECTOR","OIC","HEAD PRESCHOOL","ASSISTANT OIC","FINANCE OFFICER","HR OFFICER","RELIGION COORDINATOR","STUDENT AFFAIRS(DISCIPLINE)","STUDENT AFFAIRS(ACTIVITIES)","REGISTRAR","ACADEMIC COORDINATOR","GUIDANCE COUNCELOR","LIBRARIAN","INSTRUCTOR","GUARD","MAINTENANCE"},gridFieldWidth=3),
        @Display(name="status",type="Combo",modelCombo={"Z","SME","SME1","SME2","SME3","SME4"}),
        @Display(name="basicPay",width=80),
        @Display(name="monthlyAllowance",addInfoOnly=true),
        @Display(name="monthlyLoan",addInfoOnly=true),
        @Display(name = "hiredDate", addInfoOnly = true,gridFieldWidth=5),
        @Display(name = "birthDate", addInfoOnly = true),
        @Display(name = "age", addInfoOnly = true,gridFieldWidth=3,width=30),
        @Display(name="citizenship",addInfoOnly=true,width=150),
        @Display(name="religion",addInfoOnly=true,gridFieldWidth=3,width=150),
        @Display(name="gender",addInfoOnly=true,type="Combo",modelCombo={"MALE", "FEMALE"},width=150),
        @Display(name="maritalStatus",addInfoOnly=true,type="Combo",modelCombo={"SINGLE", "MARRIED"},gridFieldWidth=3,width=150),
        @Display(name = "placeOfBirth", addInfoOnly = true,width=150),
        @Display(name="email",addInfoOnly=true,gridFieldWidth=3,width=150),
        @Display(name="contactNumber",addInfoOnly=true,width=150),
        @Display(name="contactNumber1",addInfoOnly=true,gridFieldWidth=3,width=150),
        @Display(name="address",addInfoOnly=true,gridFieldWidth=5,width=-1),
        @Display(name="address1",addInfoOnly=true,gridFieldWidth=5,width=-1),
        @Display(name="tinNumber",addInfoOnly=true,width=150),
        @Display(name="sssNumber",addInfoOnly=true,gridFieldWidth=3,width=150),
        @Display(name="pagibigNumber",addInfoOnly=true,width=150),
        @Display(name="philhealthNumber",addInfoOnly=true,width=150),
        
        
        @Display(name="perHourPay",addInfoOnly=true),
        @Display(name="sickLeaveBenefit",addInfoOnly=true),
        @Display(name="sickLeaveUsed",addInfoOnly=true),
        @Display(name="vacLeaveBenefit",addInfoOnly=true),
        @Display(name="vacLeaveUsed",addInfoOnly=true),
        @Display(name="restDay1",addInfoOnly=true),
        @Display(name="restDay2",addInfoOnly=true),
        @Display(name="taxTable",addInfoOnly=true),
        @Display(name="contractType",addInfoOnly=true,type="Combo",modelCombo={"PER HOUR","DAILY","WEEKLY","BIMONTHLY","MONTHLY"}),
        @Display(name="scheduleType",addInfoOnly=true),
        @Display(name="pagibigNumber",addInfoOnly=true),
        
        @Display(name="bankName",addInfoOnly=true),
        @Display(name="accountNumber",addInfoOnly=true),
        @Display(name="perDay",addInfoOnly=true),
        @Display(name="rdoCode",addInfoOnly=true),
        @Display(name="personId",addInfoOnly=true),
       
        @Display(name="shortName",addInfoOnly=true),
        @Display(name="personType",addInfoOnly=true),
        
        
     
        
      
       
       
       
        @Display(name="occupation",addInfoOnly=true),
        @Display(name="mother",addInfoOnly=true),
        @Display(name="father",addInfoOnly=true),
        @Display(name="spouse",addInfoOnly=true),
        @Display(name="im1",addInfoOnly=true),
        @Display(name="im2",addInfoOnly=true),
        @Display(name="mobilePhone",addInfoOnly=true),
        @Display(name="userid",addInfoOnly=true),
        @Display(name="guardianOccupation",addInfoOnly=true),
        @Display(name="guardianName",addInfoOnly=true),
        @Display(name="guardianRelationship",addInfoOnly=true),
        @Display(name="guardianAddress",addInfoOnly=true),
        @Display(name="guardianContactNumber",addInfoOnly=true),
        @Display(name="zipCode",addInfoOnly=true),
        @Display(name="streetNumber",addInfoOnly=true),
        @Display(name="barangay",addInfoOnly=true),
        @Display(name="townDistrict",addInfoOnly=true),
        @Display(name="cityProvince",addInfoOnly=true),
        @Display(name="isActive",addInfoOnly=true),
        @Display(name="place",addInfoOnly=true),
        @Display(name="state",addInfoOnly=true),
        @Display(name="country",addInfoOnly=true),
        @Display(name="fax",addInfoOnly=true),
        @Display(name="companyName",addInfoOnly=true),
        @Display(name="provincialAddress",addInfoOnly=true),
        @Display(name="companyTelephoneNumber",addInfoOnly=true),
        @Display(name="companyAddress",addInfoOnly=true)
})
@Reports({ 
    @template.Report(reportFile="EmployeeList", reportTitle="Employee Report", reportSql="")
})
public class Employee extends Person implements Serializable {  

	@Override
	public void delete() {
		if (UserInfo.canDeleteEmployee()) {
			super.delete();
		}
		else {
			PanelUtil.showError(null, "Only Administrator or 'DELETE EMPLOYEE' duty code can delete Student records.");
		}
	}

    @Column(name = "employeeNumber", length = 30)
    public String employeeNumber;
    @Column(name = "sickLeaveUsed")
    public int sickLeaveUsed;
    @Column(name = "vacLeaveUsed")
    public int vacLeaveUsed;
    @Column(name = "bankName")
    public String bankName;
    @Column(name = "rdoCode")
    public String rdoCode;
    @Column(name = "monthlyLoan")
    public double monthlyLoan;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public int getSickLeaveUsed() {
        return sickLeaveUsed;
    }

    public void setSickLeaveUsed(int sickLeaveUsed) {
        this.sickLeaveUsed = sickLeaveUsed;
    }

    public int getVacLeaveUsed() {
        return vacLeaveUsed;
    }

    public void setVacLeaveUsed(int vacLeaveUsed) {
        this.vacLeaveUsed = vacLeaveUsed;
    }

    public double getMonthlyLoan() {
        return monthlyLoan;
    }

    public void setMonthlyLoan(double monthlyLoan) {
        this.monthlyLoan = monthlyLoan;
    }

    public Employee() {
        restDay1 = 6;
        restDay2 = 7;
    }

    public String getRdoCode() {
        return rdoCode;
    }

    public void setRdoCode(String rdoCode) {
        this.rdoCode = rdoCode;
    }

    public double getPerHourPay() {
        if (perHourPay == 0) {
            perHourPay = extractCalculatedPerHour(basicPay);
        }
        return perHourPay;
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeDeduction> extractDeductions() { 
        return list("SELECT a FROM EmployeeDeduction a WHERE a.personId=" , getPersonId());
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeLoan> extractLoans() {
        List<EmployeeLoan> retLoans = new ArrayList<EmployeeLoan>();
        List<EmployeeLoan> loans = list("SELECT a FROM EmployeeLoan a WHERE a.personId=" , getPersonId());
        for (EmployeeLoan loan : loans) {
            if (loan.isFinishedLoan()) {
                continue;
            }
            retLoans.add(loan);
        }
        return retLoans;
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeBenefit> extractBenefits() {
        return list("SELECT a FROM EmployeeBenefit a WHERE a.personId=" , getPersonId());
    }

//    @PostPersist
//    public void postPersist() {
//        @SuppressWarnings("unchecked")
//        List<BenefitType> lstBenefit = DBClient.getList("SELECT a FROM BenefitType a WHERE a.isAlways='true'");
//        for (BenefitType ben : lstBenefit) {
//            EmployeeBenefit.addEmployeeBenefit(this, ben);
//        }
//        @SuppressWarnings("unchecked")
//        List<DeductionType> lstDeduction = DBClient.getList("SELECT a FROM DeductionType a WHERE a.isAlways='true'");
//        for (DeductionType dec : lstDeduction) {
//            EmployeeDeduction.addEmployeeBenefit(this, dec);
//        }
//    }

    public static double extractCalculatedPerHour(double monthBasic) {
        //default to 21 days calculations only - in consideration of holiday and saturday sunday
        if (monthBasic <= 0) {
            return 0;
        }
        return (int) monthBasic / (20 * 8);
    }

    private EmployeeBenefit extractBenefit(String benefit) {
        if (personId == null || personId == 0) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<EmployeeBenefit> benList = new ArrayList<EmployeeBenefit>();
        if (benefit == null || benefit.isEmpty()) {
            benList = list("SELECT a FROM EmployeeBenefit a WHERE a.personId=" , personId);
        }
        for (EmployeeBenefit ben : benList) {
            if (ben.getBenefitCode().indexOf(benefit) != -1) {
                return ben;
            }
        }
        return null;
    }

    public double extractDeductionValue(String deduction, String category) {
        EmployeeDeduction dec = extractDeduction(deduction);
        if (dec!=null) {
            return dec.getDeductionTypeObj().getCalculatedValue(this);
        }
        else {
            return DeductionType.getDeduction(deduction, category).getCalculatedValue(this);
        }
    }
    
    public double extractBenefitValue(String benefit, String category) {
        EmployeeBenefit dec = extractBenefit(benefit);
        if (dec!=null) {
            return dec.getBenefitTypeObj().getCalculatedValue(this);
        }
        else {
            return BenefitType.getBenefit(benefit, category).getCalculatedValue(this);
        }
    }

    private EmployeeDeduction extractDeduction(String deduction) {
        if (personId == null || personId == 0) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<EmployeeDeduction> decList = new ArrayList<EmployeeDeduction>();
        if (deduction == null || deduction.isEmpty()) {
            decList = list("SELECT a FROM EmployeeDeduction a WHERE a.employeeId=" , personId);
        }
        for (EmployeeDeduction ben : decList) {
            if (ben.getDeductionCode().indexOf(deduction) != -1) {
                return ben;
            }
        }
        //need to get the global value
        return null;
    }

    public double getPerDay() {
        return perDay;
    }

    public void setPerDay(double perDay) {
        this.perDay = perDay;
    }

    @Override
    public String getComboDisplay() {
        if (personId == null || personId == 0) {
            return "";
        }
        if (getLastName().equals("XXX")) {
            return BeanUtil.concat(getPersonId() , " - " , getShortName());
        } else {
            return BeanUtil.concat(getLastName() , ", " , getFirstName() , "   " , getMiddleInitial());
        }
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeLoan> extractAllLoan() {
        if (personId == null || personId == 0) {
            return null;
        }
        return list("SELECT a FROM EmployeeLoan a WHERE a.employeeId=" , personId);
    }

    public double extractTaxExcemption() {
        if (personId == null || personId == 0) {
            return 0;
        }
        if (taxTable == null || taxTable.isEmpty()) {
            return 0;
        }
        TaxTable tbl = (TaxTable) TaxTable.extractObject(TaxTable.class.getSimpleName(),taxTable);
        return tbl.getExcemption();
    }

    public static Employee getObject(Object personId) {
        return (Employee) AbstractIBean.firstRecord("SELECT a FROM Employee a WHERE a.personId=",personId);
    }

    @Transient
    List<PersonDependent> lstDependents;

    public PersonDependent extractDependent(int index) {
        if (lstDependents == null) {
            lstDependents = list("SELECT a FROM PersonDependent a WHERE a.personId=" , personId);
        }
        if (lstDependents.size() < index + 1) {
            lstDependents = new ArrayList();
            return new PersonDependent();
        }
        return lstDependents.get(index);
    }
    public int extractDependentCount() {
        if (lstDependents == null) {
            lstDependents = list("SELECT a FROM PersonDependent a WHERE a.personId=" , personId);
        }
        return lstDependents.size();
    }
    @Transient
    List<EmployeeAnnualSummary> annualSummary;

    public EmployeeAnnualSummary extractAnnualSummary(Integer year) {
        //need to change the code
        if (personId == null || personId == 0) {
        } else {
            if (annualSummary == null) {
                annualSummary = new ArrayList<EmployeeAnnualSummary>();
                annualSummary.addAll(list("SELECT a FROM EmployeeAnnualSummary a WHERE a.personId=" , personId));
            }
            //get the summary with respect to the year
            for (EmployeeAnnualSummary annual : annualSummary) {
                if (annual.getYear() == year) {
                    return annual;
                }
            }
        }
        return new EmployeeAnnualSummary();
    }
    
    @Transient
    public double tCalc1;
    @Transient
    public double tCalc2;

    public double getTCalc1() {
        return tCalc1;
    }

    public void setTCalc1(double tCalc1) {
        this.tCalc1 = tCalc1;
    }

    public double getTCalc2() {
        return tCalc2;
    }

    public void setTCalc2(double tCalc2) {
        this.tCalc2 = tCalc2;
    }
}
