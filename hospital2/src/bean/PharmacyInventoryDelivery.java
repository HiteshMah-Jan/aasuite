/*
 * PharmacyInventoryDelivery.java
 * 
 * Created on Oct 26, 2007, 9:34:49 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PharmacyInventoryDelivery")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"deliveryDate", "remarks"})
@Displays({
        @Display(name="seq"),
        @Display(name="deliveryDate"),
        @Display(name="drugCode"),
        @Display(name="genericName"),
        @Display(name="stockInHand"),
        @Display(name="brandName"),
        @Display(name="unitOfMeasurement"),
        @Display(name="quantity"),
        @Display(name="drugPrice"),
        @Display(name="remarks"),
        @Display(name="drugDescription")
})
public class PharmacyInventoryDelivery extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "deliveryDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date deliveryDate;
    @Column(name = "drugCode", nullable = false)
    public String drugCode;
    @Column(name = "genericName", nullable = false, length = 50)
    public String genericName;
    @Column(name = "stockInHand")
    public int stockInHand;
    @Column(name = "brandName", length = 50)
    public String brandName;
    @Column(name = "unitOfMeasurement", length = 20)
    public String unitOfMeasurement;
    @Column(name = "quantity")
    public int quantity;
    @Column(name = "drugPrice")
    public double drugPrice;
    @Column(name = "remarks", length = 150)
    public String remarks;
    @Column(name = "drugDescription", length = 150)
    public String drugDescription;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getDrugDescription() {
        return drugDescription;
    }

    public void setDrugDescription(String drugDescription) {
        this.drugDescription = drugDescription;
    }

    public double getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(double drugPrice) {
        this.drugPrice = drugPrice;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getStockInHand() {
        return stockInHand;
    }

    public void setStockInHand(int stockInHand) {
        this.stockInHand = stockInHand;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
