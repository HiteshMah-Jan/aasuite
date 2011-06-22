/*
 * PersonAddress.java
 *
 * Created on Nov 18, 2007, 8:26:21 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.embed;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 *
 * @author Budoy Entokwa
 */
@Embeddable
public class AbstractAddress implements Serializable {
    public String country;
    public String streetNo;
    public String street;
    public String district;
    public String city;
    public String zipcode;
    public String barangay;

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public java.lang.String getBarangay() {
        return barangay;
    }

    public void setBarangay(java.lang.String barangay) {
        this.barangay = barangay;
    }
}
