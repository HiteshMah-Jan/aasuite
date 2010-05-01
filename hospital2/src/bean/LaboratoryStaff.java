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
@UITemplate(template = TemplateTabPage.class, columnSearch = {"isSonographer", "isRadiologist"})
@DiscriminatorValue(value = "LABORATORY STAFF")
@Displays({
        @Display(name="isSonographer"),
        @Display(name="isRadiologist"),
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
public class LaboratoryStaff extends Employee implements Serializable {

    @Column(name = "isSonographer")
    public boolean isSonographer;
    @Column(name = "isRadiologist")
    public boolean isRadiologist;

    public boolean isIsRadiologist() {
        return isRadiologist;
    }

    public void setIsRadiologist(boolean isRadiologist) {
        this.isRadiologist = isRadiologist;
    }

    public boolean isIsSonographer() {
        return isSonographer;
    }

    public void setIsSonographer(boolean isSonographer) {
        this.isSonographer = isSonographer;
    }
}
