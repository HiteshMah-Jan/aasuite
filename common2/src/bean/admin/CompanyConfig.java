/*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

import template.screen.TemplateSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "CompanyConfig")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"companyName"})
@Displays({
    @Display(name="companyName", upCase=false,width=200),
    @Display(name="businessCode",width=200),
    @Display(name="startOperation",width=200),
    @Display(name="employerType",width=200),
    @Display(name="employerNumber",width=200),
   
   
    @Display(name="tinNumber",width=200),
    @Display(name="sssNumber",width=200),
    @Display(name="philhealthNumber",width=200),
    
    @Display(name="streetNumber",label="# Street",width=200),
    @Display(name="barangay",width=200),
    @Display(name="townDistrict",width=200),
    @Display(name="cityProvince",label="Province/City",width=200),
    @Display(name="zipCode",width=200),
    @Display(name="address", upCase=false,gridFieldWidth=3,width=-1),
    @Display(name="emailAddress", upCase=false,width=200),
    @Display(name="telephoneNumber",width=200),
    @Display(name="faxNumber",width=200),
    
    @Display(name="region",width=200),
    @Display(name="division",width=200),
    @Display(name="legislativeDistrict",width=200)

})
    
   
public class CompanyConfig extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "companyName", nullable = false)
    public String companyName;
    @Column(name = "employerNumber")
    public String employerNumber;
    @Column(name = "address")
    public String address;
    @Column(name = "emailAddress")
    public String emailAddress;
    @Column(name = "telephoneNumber")
    public String telephoneNumber;
    @Column(name = "employerType")
    public String employerType;
    @Column(name = "houseHold")
    public String houseHold;
    @Column(name = "philhealthNumber")
    public String philhealthNumber;
    @Column(name = "tinNumber")
    public String tinNumber;
    @Column(name = "zipCode")
    public String zipCode;
    @Column(name = "streetNumber")
    public String streetNumber;
    @Column(name = "barangay")
    public String barangay;
    @Column(name = "townDistrict")
    public String townDistrict;
    @Column(name = "cityProvince")
    public String cityProvince;
    @Column(name = "startOperation")
    public String startOperation;
    @Column(name = "natureOfBusiness")
    public String natureOfBusiness;
    @Column(name = "businessCode")
    public String businessCode;
    @Column(name = "sssNumber")
    public String sssNumber;
    
    @Column(name = "region")
    public String region;
    @Column(name = "division")
    public String division;
    @Column(name = "legislativeDistrict")
    public String legislativeDistrict;
    @Column(name = "faxNumber")
    public String faxNumber;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    @Override
    public String toString() {
        return companyName;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getLegislativeDistrict() {
        return legislativeDistrict;
    }

    public void setLegislativeDistrict(String legislativeDistrict) {
        this.legislativeDistrict = legislativeDistrict;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    

    public java.lang.String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(java.lang.String companyName) {
        this.companyName = companyName;
    }

    public java.lang.String getEmployerNumber() {
        return employerNumber;
    }

    public void setEmployerNumber(java.lang.String employerNumber) {
        this.employerNumber = employerNumber;
    }

    public java.lang.String getAddress() {
        return address;
    }

    public void setAddress(java.lang.String address) {
        this.address = address;
    }

    public java.lang.String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public java.lang.String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(java.lang.String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public java.lang.String getEmployerType() {
        return employerType;
    }

    public void setEmployerType(java.lang.String employerType) {
        this.employerType = employerType;
    }

    public java.lang.String getPhilhealthNumber() {
        return philhealthNumber;
    }

    public void setPhilhealthNumber(java.lang.String philhealthNumber) {
        this.philhealthNumber = philhealthNumber;
    }

    public java.lang.String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(java.lang.String tinNumber) {
        this.tinNumber = tinNumber;
    }

    public java.lang.String getHouseHold() {
        return houseHold;
    }

    public void setHouseHold(java.lang.String houseHold) {
        this.houseHold = houseHold;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public static CompanyConfig getCompanyConfig() {
        return (CompanyConfig) AbstractIBean.firstRecord("SELECT a FROM CompanyConfig a");
    }

    public java.lang.String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(java.lang.String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public java.lang.String getBarangay() {
        return barangay;
    }

    public void setBarangay(java.lang.String barangay) {
        this.barangay = barangay;
    }

    public java.lang.String getTownDistrict() {
        return townDistrict;
    }

    public void setTownDistrict(java.lang.String townDistrict) {
        this.townDistrict = townDistrict;
    }

    public java.lang.String getCityProvince() {
        return cityProvince;
    }

    public void setCityProvince(java.lang.String cityProvince) {
        this.cityProvince = cityProvince;
    }

    public java.lang.String getStartOperation() {
        return startOperation;
    }

    public void setStartOperation(java.lang.String startOperation) {
        this.startOperation = startOperation;
    }

    public java.lang.String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public void setNatureOfBusiness(java.lang.String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public java.lang.String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(java.lang.String businessCode) {
        this.businessCode = businessCode;
    }

    public java.lang.String getSssNumber() {
        return sssNumber;
    }

    public void setSssNumber(java.lang.String sssNumber) {
        this.sssNumber = sssNumber;
    }
//    public static void main (String[]args){
//        view(CompanyConfig.class);
//    }
}
