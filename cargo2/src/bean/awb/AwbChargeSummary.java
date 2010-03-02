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
@Table(name = "AwbChargeSummary")
@UITemplate(template = TemplateTabPage.class, columnSearch = {"summaryType"})
@Displays({
    @Display(name = "summaryType", type="Combo", modelCombo={"PREPAID","COLLECT"}),
    @Display(name = "totalWeightAmount"),
    @Display(name = "valuationChargeAmount"),
    @Display(name = "taxesAmount"),
    @Display(name = "totalOtherChargesDueAgent"),
    @Display(name = "totalOtherChargesDueCarrier"),
    @Display(name = "chargeSummaryTotal")
})
public class AwbChargeSummary extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "summaryType", nullable = false)
    public String summaryType;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "totalWeightAmount")
    public double totalWeightAmount;
    @Column(name = "valuationChargeAmount")
    public double valuationChargeAmount;
    @Column(name = "taxesAmount")
    public double taxesAmount;
    @Column(name = "totalOtherChargesDueAgent")
    public double totalOtherChargesDueAgent;
    @Column(name = "totalOtherChargesDueCarrier")
    public double totalOtherChargesDueCarrier;
    @Column(name = "chargeSummaryTotal")
    public double chargeSummaryTotal;

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public double getChargeSummaryTotal() {
        return chargeSummaryTotal;
    }

    public void setChargeSummaryTotal(double chargeSummaryTotal) {
        this.chargeSummaryTotal = chargeSummaryTotal;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getSummaryType() {
        return summaryType;
    }

    public void setSummaryType(String summaryType) {
        this.summaryType = summaryType;
    }

    public double getTaxesAmount() {
        return taxesAmount;
    }

    public void setTaxesAmount(double taxesAmount) {
        this.taxesAmount = taxesAmount;
    }

    public double getTotalOtherChargesDueAgent() {
        return totalOtherChargesDueAgent;
    }

    public void setTotalOtherChargesDueAgent(double totalOtherChargesDueAgent) {
        this.totalOtherChargesDueAgent = totalOtherChargesDueAgent;
    }

    public double getTotalOtherChargesDueCarrier() {
        return totalOtherChargesDueCarrier;
    }

    public void setTotalOtherChargesDueCarrier(double totalOtherChargesDueCarrier) {
        this.totalOtherChargesDueCarrier = totalOtherChargesDueCarrier;
    }

    public double getTotalWeightAmount() {
        return totalWeightAmount;
    }

    public void setTotalWeightAmount(double totalWeightAmount) {
        this.totalWeightAmount = totalWeightAmount;
    }

    public double getValuationChargeAmount() {
        return valuationChargeAmount;
    }

    public void setValuationChargeAmount(double valuationChargeAmount) {
        this.valuationChargeAmount = valuationChargeAmount;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
