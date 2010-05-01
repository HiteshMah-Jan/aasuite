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
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Person")
@UITemplate(template = TemplateTabPage.class, columnSearch = {"bloodType", "specialization"})
@DiscriminatorValue(value = "NURSE")
@Displays({
        @Display(name="bloodType"),
        @Display(name="specialization"),
        @Display(name="employeeNumber"),
        @Display(name="basicPay"),
        @Display(name="perHourPay"),
        @Display(name="sickLeaveBenefit"),
        @Display(name="sickLeaveUsed"),
        @Display(name="vacLeaveBenefit"),
        @Display(name="vacLeaveUsed"),
        @Display(name="restDay1"),
        @Display(name="restDay2"),
        @Display(name="taxTable"),
        @Display(name="contractType"),
        @Display(name="scheduleType"),
        @Display(name="pagibigNumber"),
        @Display(name="philhealthNumber"),
        @Display(name="bankName"),
        @Display(name="accountNumber"),
        @Display(name="perDay"),
        @Display(name="rdoCode"),
        @Display(name="personId"),
        @Display(name="hiredDate"),
        @Display(name="shortName"),
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="middleInitial"),
        @Display(name="personType"),
        @Display(name="gender"),
        @Display(name="maritalStatus"),
        @Display(name="birthDate"),
        @Display(name="department"),
        @Display(name="age"),
        @Display(name="placeOfBirth"),
        @Display(name="citizenship"),
        @Display(name="religion"),
        @Display(name="email"),
        @Display(name="contactNumber"),
        @Display(name="contactNumber1"),
        @Display(name="address"),
        @Display(name="address1"),
        @Display(name="tinNumber"),
        @Display(name="sssNumber"),
        @Display(name="pagibig"),
        @Display(name="status"),
        @Display(name="position"),
        @Display(name="occupation"),
        @Display(name="mother"),
        @Display(name="father"),
        @Display(name="spouse"),
        @Display(name="im1"),
        @Display(name="im2"),
        @Display(name="mobilePhone"),
        @Display(name="userid"),
        @Display(name="guardianOccupation"),
        @Display(name="guardianName"),
        @Display(name="guardianRelationship"),
        @Display(name="guardianAddress"),
        @Display(name="guardianContactNumber"),
        @Display(name="zipCode"),
        @Display(name="streetNumber"),
        @Display(name="barangay"),
        @Display(name="townDistrict"),
        @Display(name="cityProvince"),
        @Display(name="isActive"),
        @Display(name="place"),
        @Display(name="state"),
        @Display(name="country"),
        @Display(name="fax"),
        @Display(name="companyName"),
        @Display(name="provincialAddress"),
        @Display(name="companyTelephoneNumber"),
        @Display(name="companyAddress")
})
public class Nurse extends EmployeeHospital implements Serializable {

    @Column(name = "bloodType", length = 3)
    public String bloodType;
    @Column(name = "specialization", length = 50)
    public String specialization;

    public Nurse() {
    }

    public Nurse(String lastName, String firstName, String middleInitial) {

        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setMiddleInitial(middleInitial);
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

    @Override
    public String getComboDisplay() {
        if (personId == null || personId == 0) {
            return "";
        }
        return getLastName() + ",  " + getFirstName() + "   " + getMiddleInitial();
    }
}
