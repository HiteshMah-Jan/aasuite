/*
 * Bug.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.persistence.Transient;
import service.util.AbstractIBean;
import util.DBClient;

/**
 *
 * @author Budoy Entokwa
 */
public abstract class AbstractCustomerContactLink extends AbstractIBean {
    private CustomerContact cust;

    public abstract int getCustomerId();

    protected void initCustomer() {
        cust = (CustomerContact) selectFirstCache("SELECT a FROM CustomerContact a WHERE a.personId="+getCustomerId());
        if (cust==null) cust = new CustomerContact();
    }
    public String getAddress() {
        initCustomer();
        return cust.address;
    }

    public String getCompanyName() {
        initCustomer();
        return cust.companyName;
    }

    public String getContactNumber() {
        initCustomer();
        return cust.contactNumber;
    }

    public String getContactPerson() {
        initCustomer();
        return cust.contactPerson;
    }

    public String getCustomerClass() {
        initCustomer();
        return cust.customerClass;
    }

    public int getCustomerHeadcount() {
        initCustomer();
        return cust.customerHeadcount;
    }

    public String getCustomerType() {
        initCustomer();
        return cust.customerType;
    }
    @Transient
    public String email;
    @Transient
    public String location;
    @Transient
    public String address;
    @Transient
    public String companyName;
    @Transient
    public String contactNumber;
    @Transient
    public String contactPerson;
    @Transient
    public String customerClass;
    @Transient
    public int customerHeadcount;
    @Transient
    public String customerType;
}
