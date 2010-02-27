/*
 * Asset.java
 *
 * Created on Nov 25, 2007, 7:54:25 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "Asset")
@UITemplate(template = TemplateDefault.class, gridCount=6, columnSearch = {"assetName", "paymentMethod", "category", "location", "serialNumber"})
@Displays({
    @Display(name="seq"),
    @Display(name="assetName"),
    @Display(name="paymentMethod", width=100, type="PopSearch", linktoBean=PaymentMethod.class),
    @Display(name="purchaseDate"),
    @Display(name="purchaseAt"),
    @Display(name="remarks"),
    @Display(name="replacementCost"),
    @Display(name="category", width=100, type="PopSearch", linktoBean=AssetCategory.class),
    @Display(name="resaleValue"),
    @Display(name="insurancePolicy"),
    @Display(name="location", width=100, type="Combo", sqlCombo="SELECT a FROM AssetLocation a"),
    @Display(name="size"),
    @Display(name="barCode"),
    @Display(name="serialNumber"),
    @Display(name="purchasePrice"),
    @Display(name="notes"),
    @Display(name="estimatedLifeYears"),
    @Display(name="scrapValue")
})
public class Asset extends AbstractIBean implements Serializable {

    @Id
   @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "assetName", nullable = false)
    public String assetName;
    @Column(name = "paymentMethod")
    public String paymentMethod;
    @Column(name = "purchaseDate")
    @Temporal(value = TemporalType.DATE)
    public Date purchaseDate;
    @Column(name = "purchaseAt")
    public String purchaseAt;
    @Column(name = "remarks")
    public String remarks;
    @Column(name = "replacementCost")
    public double replacementCost;
    @Column(name = "category")
    public String category;
    @Column(name = "resaleValue")
    public double resaleValue;
    @Column(name = "insurancePolicy")
    public String insurancePolicy;
    @Column(name = "location")
    public String location;
    @Column(name = "size")
    public int size;
    @Column(name = "barCode")
    public String barCode;
    @Column(name = "serialNumber")
    public String serialNumber;
    @Column(name = "purchasePrice")
    public double purchasePrice;
    @Column(name = "notes")
    public String notes;
    @Column(name = "estimatedLifeYears")
    public int estimatedLifeYears;
    @Column(name = "scrapValue")
    public double scrapValue;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "assetName");
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getEstimatedLifeYears() {
        return estimatedLifeYears;
    }

    public void setEstimatedLifeYears(int estimatedLifeYears) {
        this.estimatedLifeYears = estimatedLifeYears;
    }

    public String getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(String insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPurchaseAt() {
        return purchaseAt;
    }

    public void setPurchaseAt(String purchaseAt) {
        this.purchaseAt = purchaseAt;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public double getResaleValue() {
        return resaleValue;
    }

    public void setResaleValue(double resaleValue) {
        this.resaleValue = resaleValue;
    }

    public double getScrapValue() {
        return scrapValue;
    }

    public void setScrapValue(double scrapValue) {
        this.scrapValue = scrapValue;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
