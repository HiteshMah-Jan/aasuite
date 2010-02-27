/*
 * Customer.java
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
import javax.persistence.Transient;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Person")
@UITemplate(canBackup=false, template = TemplateDefault.class, gridCount = 4, columnSearch = {"salesPersonId1", "customerClass", "contactPerson"})
@DiscriminatorValue(value = "CUSTOMER")
@Displays({
    @Display(name="lastName"),
    @Display(name="firstName"),
    @Display(name="individual"),
    @Display(name="paymentTerm"),
    @Display(name="salesPersonId1", type = "PopSearch", linktoBean=Employee.class),
    @Display(name="salesPersonId2", type = "PopSearch", linktoBean=Employee.class),
    @Display(name="salesPersonId3", type = "PopSearch", linktoBean=Employee.class),
    @Display(name="salesPersonId4", type = "PopSearch", linktoBean=Employee.class),
    @Display(name="salesPersonId5", type = "PopSearch", linktoBean=Employee.class),
    @Display(name="territory"),
    @Display(name="customerClass"),
    @Display(name="priceLevel"),
    @Display(name="contactPerson"),
    @Display(name="employeeHeadcount"),
    @Display(name="customerHeadcount")
})
public class Customer extends Person implements Serializable {

    @Column(name = "paymentTerm", length = 10)
    public String paymentTerm;
    @Column(name = "salesPersonId1")
    public int salesPersonId1;
    @Column(name = "salesPersonId2")
    public int salesPersonId2;
    @Column(name = "salesPersonId3")
    public int salesPersonId3;
    @Column(name = "salesPersonId4")
    public int salesPersonId4;
    @Column(name = "salesPersonId5")
    public int salesPersonId5;
    @Column(name = "territory", length = 30)
    public String territory;
    @Column(name = "customerClass", length = 20)
    public String customerClass;
    @Column(name = "priceLevel", length = 10)
    public String priceLevel;
    @Column(name = "contactPerson", length = 255)
    public String contactPerson;
    @Column(name = "employeeHeadcount")
    public int employeeHeadcount;
    @Column(name = "customerHeadcount")
    public int customerHeadcount;
    @Column(name = "template")
    public String template;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public java.lang.String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(java.lang.String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public int getSalesPersonId1() {
        return salesPersonId1;
    }

    public void setSalesPersonId1(int salesPersonId1) {
        this.salesPersonId1 = salesPersonId1;
    }

    public int getSalesPersonId2() {
        return salesPersonId2;
    }

    public void setSalesPersonId2(int salesPersonId2) {
        this.salesPersonId2 = salesPersonId2;
    }

    public int getSalesPersonId3() {
        return salesPersonId3;
    }

    public void setSalesPersonId3(int salesPersonId3) {
        this.salesPersonId3 = salesPersonId3;
    }

    public int getSalesPersonId4() {
        return salesPersonId4;
    }

    public void setSalesPersonId4(int salesPersonId4) {
        this.salesPersonId4 = salesPersonId4;
    }

    public int getSalesPersonId5() {
        return salesPersonId5;
    }

    public void setSalesPersonId5(int salesPersonId5) {
        this.salesPersonId5 = salesPersonId5;
    }

    public java.lang.String getTerritory() {
        return territory;
    }

    public void setTerritory(java.lang.String territory) {
        this.territory = territory;
    }

    public java.lang.String getCustomerClass() {
        return customerClass;
    }

    public void setCustomerClass(java.lang.String customerClass) {
        this.customerClass = customerClass;
    }

    public java.lang.String getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(java.lang.String priceLevel) {
        this.priceLevel = priceLevel;
    }

    public java.lang.String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(java.lang.String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Transient
    Person salesPerson;

    public String extractSalesPerson() {
        if (this.salesPersonId1 <= 0) {
            return "";
        }
        if (salesPerson == null) {
            salesPerson = (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.personId="+salesPersonId1);
        }
        if (salesPerson == null) {
            return "";
        }
        return salesPerson.getUserid();
    }

    public int getEmployeeHeadcount() {
        return employeeHeadcount;
    }

    public void setEmployeeHeadcount(int employeeHeadcount) {
        this.employeeHeadcount = employeeHeadcount;
    }

    public int getCustomerHeadcount() {
        return customerHeadcount;
    }

    public void setCustomerHeadcount(int customerHeadcount) {
        this.customerHeadcount = customerHeadcount;
    }
}
