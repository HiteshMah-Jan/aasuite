/*
 * Deduction.java
 *
 * Created on Oct 26, 2007, 9:34:49 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "ScholarshipTable")
@UITemplate(columnSearch={"code","discountPercentage", "discountAmount"}, gridCount=4, title="Scholarship")
@Displays({
        @Display(name="code"),
        @Display(name="description"),
        @Display(name="discountPercentage"),
        @Display(name="discountAmount")
})
public class ScholarshipTable extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "description")
    public String description;
    @Column(name = "discountPercentage")
    public double discountPercentage;
    @Column(name = "discountAmount")
    public double discountAmount;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    public double getScholarshipDiscount(double enrollmentAmount) {
        if (discountPercentage > 0) {
            //use percentage amount
            return (enrollmentAmount * discountPercentage)/100;
        }
        else {
            return enrollmentAmount - discountAmount;
        }
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
