/*
 * Person.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import bean.reference.Department;
import bean.reference.DoctorSpecialization;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;


/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Person")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"lastName", "firstName"},showImages=true)
@ChildRecords(
    value={
       @ChildRecord(entity=Appointment.class, fieldMapping = {"personId", "physicianId"}, sql = "SELECT a FROM Appointment a WHERE a.physicianId=${personId}")
       },
    info={
        @ParentAddInfo(title="Personal Information", displayFields={"birthDate","age","placeOfBirth","gender","citizenship","religion","address", "contactNumber"})
}
)
 
@DiscriminatorValue(value = "PHYSICIAN")
@Displays({
        @Display(name="employeeNumber",addInfoOnly=true),
        @Display(name="lastName"),
        @Display(name="firstName"),
        
        @Display(name="department",type="PopSearch",linktoBean=Department.class),
        @Display(name="specialization", type = "PopSearch", linktoBean=DoctorSpecialization.class),
        
        @Display(name="opdFee"),
        @Display(name="erFee"),
        @Display(name="operationFee"),
        @Display(name="semiPrivateAdjustment"),
        @Display(name="privateAdjustment"),
        
        @Display(name="birthDate", addInfoOnly=true),
        @Display(name="age",width=30, addInfoOnly=true),
        @Display(name="gender", type="Combo", modelCombo={"MALE", "FEMALE"}, addInfoOnly=true,width=-1),
        @Display(name="maritalStatus", type="Combo", modelCombo={"SINGLE","MARRIED"}, addInfoOnly=true),
        
     
        @Display(name="placeOfBirth", addInfoOnly=true,width=150),
        @Display(name="citizenship", addInfoOnly=true,width=150),
        @Display(name="religion", addInfoOnly=true,width=150),
        @Display(name="contactNumber", addInfoOnly=true,gridFieldWidth=3,width=-1),
        @Display(name="address", addInfoOnly=true,gridFieldWidth=3,width=-1),
    
    
    
    
        @Display(name="bloodType", type = "Combo", sqlCombo = "SELECT a FROM Blood a",addInfoOnly=true),
        @Display(name="perHourPay",addInfoOnly=true),
        @Display(name="sickLeaveBenefit",addInfoOnly=true),
        @Display(name="sickLeaveUsed",addInfoOnly=true),
        @Display(name="vacLeaveBenefit",addInfoOnly=true),
        @Display(name="vacLeaveUsed",addInfoOnly=true),
        @Display(name="restDay1",addInfoOnly=true),
        @Display(name="restDay2",addInfoOnly=true),
        @Display(name="taxTable",addInfoOnly=true),
        @Display(name="contractType",addInfoOnly=true),
        @Display(name="scheduleType",addInfoOnly=true),
        @Display(name="pagibigNumber",addInfoOnly=true),
        @Display(name="philhealthNumber",addInfoOnly=true),
        @Display(name="bankName",addInfoOnly=true),
        @Display(name="accountNumber",addInfoOnly=true),
        @Display(name="perDay",addInfoOnly=true),
        @Display(name="rdoCode",addInfoOnly=true),
        @Display(name="personId",addInfoOnly=true),
        @Display(name="hiredDate",addInfoOnly=true),
        @Display(name="shortName",addInfoOnly=true),    
        @Display(name="middleInitial",addInfoOnly=true),
        @Display(name="personType",addInfoOnly=true),  
        @Display(name="contactNumber1",addInfoOnly=true),
        @Display(name="address1",addInfoOnly=true),
        @Display(name="tinNumber",addInfoOnly=true),
        @Display(name="sssNumber",addInfoOnly=true),
        @Display(name="status",addInfoOnly=true),
        @Display(name="position",addInfoOnly=true),
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
@Reports( {
	@template.Report(reportFile = "PhysicianList", reportTitle = "Physician List", reportSql = ""),
	@template.Report(reportFile = "AppointmentList", reportTitle = "Appointment List", reportSql = "")
})
public class Physician extends EmployeeHospital implements Serializable {

    @Column(name = "opdFee")
    public double opdFee;
    @Column(name = "erFee")
    public double erFee;
    @Column(name = "operationFee")
    public double operationFee;
    @Column(name = "semiPrivateAdjustment")
    public double semiPrivateAdjustment;
    @Column(name = "privateAdjustment")
    public double privateAdjustment;
    
    @Column(name = "bloodType")
    public String bloodType;
    @Column(name = "specialization")
    public String specialization;

    public Physician() {
    }

    public Physician(String firstName, String lastName, String middleInitial) {
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setMiddleInitial(middleInitial);
    }

    public double getOpdFee() {
		return opdFee;
	}

	public void setOpdFee(double opdFee) {
		this.opdFee = opdFee;
	}

	public double getErFee() {
		return erFee;
	}

	public void setErFee(double erFee) {
		this.erFee = erFee;
	}

	public double getOperationFee() {
		return operationFee;
	}

	public void setOperationFee(double operationFee) {
		this.operationFee = operationFee;
	}

	public double getSemiPrivateAdjustment() {
		return semiPrivateAdjustment;
	}

	public void setSemiPrivateAdjustment(double semiPrivateAdjustment) {
		this.semiPrivateAdjustment = semiPrivateAdjustment;
	}

	public double getPrivateAdjustment() {
		return privateAdjustment;
	}

	public void setPrivateAdjustment(double privateAdjustment) {
		this.privateAdjustment = privateAdjustment;
	}

	public java.lang.String getBloodType() {
        return bloodType;
    }

    public void setBloodType(java.lang.String bloodType) {
        this.bloodType = bloodType;
    }

    public java.lang.String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(java.lang.String specialization) {
        this.specialization = specialization;
    }
}
