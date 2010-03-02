/*
 * AwbCharges.java
 *
 * Created on Sep 30, 2007, 8:02:10 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

import bean.reference.Charges;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbCharges")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, columnSearch = {"chargeCode","amount","reason"}, showChart=true)
@Displays({
    @Display(name = "chargeCode", width=60, type="PopSearch", linktoBean=Charges.class),
    @Display(name = "amount"),
    @Display(name = "reason")
})
public class AwbCharges extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "amount", nullable = false)
    public double amount;
    @Column(name = "chargeCode", nullable = false, length = 5)
    public String chargeCode;
    @Column(name = "reason", length = 100)
    public String reason;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;

    //additional
    @Column(name = "chargeType", length = 50)
    public String chargeType;
    @Column(name = "cassIndicator", length = 5)
    public String cassIndicator;
    @Column(name = "rateExchange")
    public double rateExchange;
    @Column(name = "paid")
    public boolean paid;

    public AwbCharges() {
        reason = "--";
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public String getCassIndicator() {
        return cassIndicator;
    }

    public void setCassIndicator(String cassIndicator) {
        this.cassIndicator = cassIndicator;
    }

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public double getRateExchange() {
        return rateExchange;
    }

    public void setRateExchange(double rateExchange) {
        this.rateExchange = rateExchange;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
