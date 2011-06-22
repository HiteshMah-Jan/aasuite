/*
 * Customer.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.businesspartners;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import bean.Person;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Person")
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


