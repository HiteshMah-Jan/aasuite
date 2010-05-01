/*
 * PharmacyInventoryStocks.java
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
@Table(name = "PharmacyInventoryStocks")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"genericName", "brandName", "stockQuantity"})
@Displays({
        @Display(name="seq"),
        @Display(name="drugCode"),
        @Display(name="genericName"),
        @Display(name="brandName"),
        @Display(name="remarks"),
        @Display(name="stockQuantity"),
        @Display(name="stockPrice"),
        @Display(name="deliveryDate"),
        @Display(name="expiryDate")
})
public class PharmacyInventoryStocks extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "drugCode", nullable = false)
    public String drugCode;
    @Column(name = "genericName", nullable = false, length = 50)
    public String genericName;
    @Column(name = "brandName", nullable = false, length = 50)
    public String brandName;
    @Column(name = "remarks", nullable = false, length = 150)
    public String remarks;
    @Column(name = "stockQuantity", nullable = false)
    public int stockQuantity;
    @Column(name = "stockPrice", nullable = false)
    public double stockPrice;
    @Column(name = "deliveryDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date deliveryDate;
    @Column(name = "expiryDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date expiryDate;

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

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
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

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
