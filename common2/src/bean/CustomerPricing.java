/*
 * Payabletype.java
 *
 * Created on Nov 25, 2007, 3:00:04 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "CustomerPricing")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"customerPricingId", "customerId", "productCode"})
@Displays({
    @Display(name="customerId", label = "Customer", type = "PopSearch", linktoBean=Customer.class),
    @Display(name="productCode", type = "PopSearch", linktoBean=Product.class),
    @Display(name="minPricePerUnit"),
    @Display(name="maxPricePerUnit")
})
public class CustomerPricing extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "customerId", nullable = false)
    public int customerId;
    @Column(name = "productCode", nullable = false)
    public String productCode;
    @Column(name = "minPricePerUnit", nullable = false)
    public double minPricePerUnit;
    @Column(name = "maxPricePerUnit", nullable = false)
    public double maxPricePerUnit;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public java.lang.String getProductCode() {
        return productCode;
    }

    public void setProductCode(java.lang.String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        if (productCode == null || productCode.trim().length() == 0) {
            return "";
        }
        Product prod = (Product) AbstractIBean.firstRecord("SELECT a FROM Product a WHERE a.code='" + productCode + "'");
        if (prod != null) {
            return prod.getProductName();
        }
        return "";
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getMinPricePerUnit() {
        return minPricePerUnit;
    }

    public void setMinPricePerUnit(double minPricePerUnit) {
        this.minPricePerUnit = minPricePerUnit;
    }

    public double getMaxPricePerUnit() {
        return maxPricePerUnit;
    }

    public void setMaxPricePerUnit(double maxPricePerUnit) {
        this.maxPricePerUnit = maxPricePerUnit;
    }
}
