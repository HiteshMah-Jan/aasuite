/*
 * PharmacyInventoryExpired.java
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
@Table(name = "PharmacyInventoryExpired")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"expiryDate", "brandName", "quantity"})
@Displays({
        @Display(name="seq"),
        @Display(name="drugCode"),
        @Display(name="genericName"),
        @Display(name="brandName"),
        @Display(name="quantity"),
        @Display(name="drugPrice"),
        @Display(name="expiryDate"),
        @Display(name="remarks")
})
public class PharmacyInventoryExpired extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "drugCode", nullable = false)
    public String drugCode;
    @Column(name = "genericName", length = 50)
    public String genericName;
    @Column(name = "brandName", length = 50)
    public String brandName;
    @Column(name = "quantity")
    public int quantity;
    @Column(name = "drugPrice")
    public double drugPrice;
    @Column(name = "expiryDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date expiryDate;
    @Column(name = "remarks", length = 50)
    public String remarks;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public double getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(double drugPrice) {
        this.drugPrice = drugPrice;
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

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
