/*
 * Paymentmethod.java
 *
 * Created on Nov 23, 2007, 8:40:57 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.sales;

import java.io.Serializable;
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
@Table(name = "PaymentLineItem")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"code", "description", "charges", "netSurcharge", "discount", "totalPaidAmount"})
@Displays({
        @Display(name="seq"),
        @Display(name="paymentId"),
        @Display(name="code"),
        @Display(name="description"),
        @Display(name="charges"),
        @Display(name="netSurcharge"),
        @Display(name="discount"),
        @Display(name="totalPaidAmount")
        })
public class PaymentLineItem extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "paymentId", nullable = false)
    public int paymentId;
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "description", nullable = false)
    public String description;
    @Column(name = "charges")
    public double charges;
    @Column(name = "netSurcharge")
    public double netSurcharge;
    @Column(name = "discount")
    public double discount;
    @Column(name = "totalPaidAmount")
    public double totalPaidAmount;

    @Column(name = "info1")
    public String info1;
    @Column(name = "info2")
    public String info2;
    @Column(name = "info3")
    public String info3;
    @Column(name = "info4")
    public String info4;
    @Column(name = "info5")
    public String info5;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public String getInfo4() {
        return info4;
    }

    public void setInfo4(String info4) {
        this.info4 = info4;
    }

    public String getInfo5() {
        return info5;
    }

    public void setInfo5(String info5) {
        this.info5 = info5;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getNetSurcharge() {
        return netSurcharge;
    }

    public void setNetSurcharge(double netSurcharge) {
        this.netSurcharge = netSurcharge;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }
    
}
