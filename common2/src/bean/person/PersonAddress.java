/*
 * PersonAddress.java
 * 
 * Created on Nov 18, 2007, 8:26:21 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.io.Serializable;
import javax.persistence.*;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PersonAddress")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"streetNo", "street", "city"})
@Displays({
        @Display(name="streetNo"),
        @Display(name="street"),
        @Display(name="district"),
        @Display(name="city"),
        @Display(name="country"),
        @Display(name="zipcode")
//        @Display(name="barangay")
//        @Display(name="personAddressId"),
//        @Display(name="personId"),
})
public class PersonAddress extends PersonAttribute implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;

    @Override
    public int getPersonId() {
        return personId;
    }

    @Override
    public Integer getSeq() {
        return seq;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Column(name = "country")
    public String country;
    @Column(name = "street_no")
    public String streetNo;
    @Column(name = "street")
    public String street;
    @Column(name = "district")
    public String district;
    @Column(name = "city")
    public String city;
    @Column(name = "zipcode")
    public String zipcode;
    @Column(name = "barangay")
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

    @Override
    public String toString() {
        return streetNo;
    }

    public java.lang.String getBarangay() {
        return barangay;
    }

    public void setBarangay(java.lang.String barangay) {
        this.barangay = barangay;
    }
}
