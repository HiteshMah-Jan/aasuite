/*
 * Payabletype.java
 *
 * Created on Nov 25, 2007, 3:00:04 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.inventory;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "SupplierProduct")
@UITemplate(template = TemplateDefault.class, gridCount = 6, 
		columnSearch = {"productCode","purchaseDate","minPricePerUnit","maxPricePerUnit","remarks"})
@Displays({
        @Display(name="purchaseDate"),
//        @Display(name="supplierId", label = "Supplier",type="PopSearch", linktoBean=Supplier.class),
        @Display(name="productCode", label="Product",type = "PopSearch", linktoBean = Product.class),
        @Display(name="minPricePerUnit"),
        @Display(name="maxPricePerUnit"),
        @Display(name="remarks")
        
})
@ChildRecords(value = {
    @ChildRecord(fieldMapping = {"code", "productCode"}, entity = Product.class, sql = "SELECT a FROM Product a WHERE a.code='${productCode}'")
})
public class SupplierProduct extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "supplierId", nullable = false)
    public int supplierId;
    @Column(name = "purchaseDate")
    @Temporal(value = TemporalType.DATE)
    public Date purchaseDate;
    @Column(name = "productCode")
    public String productCode;
    @Column(name = "minPricePerUnit")
    public double minPricePerUnit;
    @Column(name = "maxPricePerUnit")
    public double maxPricePerUnit;
    @Column(name = "remarks")
    public String remarks;
    
    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public double getMaxPricePerUnit() {
        return maxPricePerUnit;
    }

    public void setMaxPricePerUnit(double maxPricePerUnit) {
        this.maxPricePerUnit = maxPricePerUnit;
    }

    public double getMinPricePerUnit() {
        return minPricePerUnit;
    }

    public void setMinPricePerUnit(double minPricePerUnit) {
        this.minPricePerUnit = minPricePerUnit;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
