/*
 * Customer.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.*;
import bean.person.EmployeeLoan;
import bean.reference.PaymentMethod;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateDefault;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Person")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"supplierClass", "companyName"})
@DiscriminatorValue(value = "SUPPLIER")
@ChildRecords(
		value={
				@ChildRecord(title="Products", fieldMapping={"personId","supplierId"}, entity = SupplierProduct.class, sql = "SELECT a FROM SupplierProduct a WHERE a.supplierId=${personId}", template=ChildTemplateListPopupDownButton.class)
		}
//		info={
//				@ParentAddInfo(title="Supplier Info", displayFields={"shortName","email","contactNumber1","address1","im1","mobilePhone","country","state","cityProvince","zipCode","townDistrict","streetNumber","barangay"})}
)

@Displays({
       
       @Display(name="companyName",width=250),  
        @Display(name="supplierClass", type="Combo", modelCombo={"SMALL", "MEDIUM","LARGE"}),
       
        @Display(name="companyAddress",width=250),
        @Display(name="companyTelephoneNumber",label="Company Tel #",width=160),
        @Display(name="contactPerson",width=250),
        @Display(name="contactNumber",label="Tel #",width=160)
        
//        @Display(name="email",addInfoOnly=true),
//        @Display(name="shortName",addInfoOnly=true),
//        @Display(name="contactNumber1",addInfoOnly=true),
//        @Display(name="address1",addInfoOnly=true),
//        @Display(name="im1",addInfoOnly=true),
//        @Display(name="im2",addInfoOnly=true),
//        @Display(name="mobilePhone",addInfoOnly=true),
//        @Display(name="address",addInfoOnly=true),
//        @Display(name="individual",addInfoOnly=true),
//        @Display(name="territory",addInfoOnly=true),
//        @Display(name="priceLevel",addInfoOnly=true),
//        @Display(name="paymentTerm",addInfoOnly=true, type="PopSearch", linktoBean=PaymentMethod.class),
//        @Display(name="status",addInfoOnly=true),
//        @Display(name="remarks",addInfoOnly=true),
//        @Display(name="personId",addInfoOnly=true),
//        @Display(name="hiredDate",addInfoOnly=true),
//        @Display(name="lastName",addInfoOnly=true),
//        @Display(name="firstName",addInfoOnly=true),
//        @Display(name="middleInitial",addInfoOnly=true),
//        @Display(name="personType",addInfoOnly=true),
//        @Display(name="gender",addInfoOnly=true),
//        @Display(name="maritalStatus",addInfoOnly=true),
//        @Display(name="birthDate",addInfoOnly=true),
//        @Display(name="department",addInfoOnly=true),
//        @Display(name="age",addInfoOnly=true),
//        @Display(name="placeOfBirth",addInfoOnly=true),
//        @Display(name="citizenship",addInfoOnly=true),
//        @Display(name="religion",addInfoOnly=true),
//        @Display(name="tinNumber",addInfoOnly=true),
//        @Display(name="sssNumber",addInfoOnly=true),
//        @Display(name="pagibigNumber",addInfoOnly=true),
//        @Display(name="position",addInfoOnly=true),
//        @Display(name="occupation",addInfoOnly=true),
//        @Display(name="mother",addInfoOnly=true),
//        @Display(name="father",addInfoOnly=true),
//        @Display(name="spouse",addInfoOnly=true),
//        @Display(name="userid",addInfoOnly=true),
//        @Display(name="guardianOccupation",addInfoOnly=true),
//        @Display(name="guardianName",addInfoOnly=true),
//        @Display(name="guardianRelationship",addInfoOnly=true),
//        @Display(name="guardianAddress",addInfoOnly=true),
//        @Display(name="guardianContactNumber",addInfoOnly=true),
//        @Display(name="zipCode",addInfoOnly=true),
//        @Display(name="streetNumber",addInfoOnly=true),
//        @Display(name="barangay",addInfoOnly=true),
//        @Display(name="townDistrict",addInfoOnly=true),
//        @Display(name="cityProvince",addInfoOnly=true),
//        @Display(name="isActive",addInfoOnly=true),
//        @Display(name="place",addInfoOnly=true),
//        @Display(name="state",addInfoOnly=true),
//        @Display(name="country",addInfoOnly=true),
//        @Display(name="fax",addInfoOnly=true),
//        @Display(name="provincialAddress",addInfoOnly=true)
        }
)
          @Reports({
    @template.Report(reportFile="SupplierProduct", reportTitle="Supplier Product", reportSql=""),
    @template.Report(reportFile="ProductSupplier", reportTitle="Product Supplier", reportSql="")
   })
public class Supplier extends Person implements Serializable {
    
    @Column(name = "territory", length = 30)
    public String territory;
    @Column(name = "supplierClass", length = 10)
    public String supplierClass;
    @Column(name = "priceLevel", length = 10)
    public String priceLevel;
    @Column(name = "paymentTerm", length = 10)
    public String paymentTerm;
    @Column(name = "contactPerson", length = 50)
    public String contactPerson;
    @Column(name = "status", length = 10)
    public String status;
    @Column(name = "remarks", length = 200)
    public String remarks;

    @Override
	public void save() {
		lastName = "XXX";
		firstName = "XXX";
		personType = "SUPPLIER";
		super.save();
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearchUseEntity(criteria, "Supplier", "companyName");
    }

    public List getItemList() {
        return this.list("SELECT a FROM Supplier a WHERE a.personId=" , personId);
    }

    public void setShortName(java.lang.String shortName) {
        super.setShortName(shortName);
        if (this.getFirstName() == null) {
            setFirstName("XXX");
        }
        if (this.getLastName() == null) {
            setLastName("XXX");
        }
    }

    public java.lang.String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(java.lang.String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public java.lang.String getTerritory() {
        return territory;
    }

    public void setTerritory(java.lang.String territory) {
        this.territory = territory;
    }

    public java.lang.String getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(java.lang.String priceLevel) {
        this.priceLevel = priceLevel;
    }

    public java.lang.String getStatus() {
        return status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.lang.String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(java.lang.String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public java.lang.String getRemarks() {
        return remarks;
    }

    public void setRemarks(java.lang.String remarks) {
        this.remarks = remarks;
    }

    public java.lang.String getSupplierClass() {
        return supplierClass;
    }

    public void setSupplierClass(java.lang.String supplierClass) {
        this.supplierClass = supplierClass;
    }

	@Override
	public String toString() {
		return companyName;
	}
}


