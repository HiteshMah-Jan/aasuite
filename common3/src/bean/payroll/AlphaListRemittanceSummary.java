/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.payroll;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import bean.admin.AppConfig;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;


/**
 * @author asmiranda
 */
@Entity
@Table(name = "AlphaListRemittanceSummary")
@UITemplate(template=TemplateTabPage.class,
		columnSearch={"remittanceNum","monthQuarter","remittanceDate","nameOfBank"},
                criteriaSearch={"remittanceNum","monthQuarter","remittanceDate"}, 
		gridCount=6, title="Alpha List Remittance Summary")	
@Displays({
      //  @Display(name="alphalistId"),
        @Display(name="remittanceNum"),
        @Display(name="monthQuarter"),
        @Display(name="remittanceDate"),
        @Display(name="nameOfBank",gridFieldWidth=5,width=-1),
        @Display(name="taxesWithheld",width=-1),
         @Display(name="adjustment",width=-1),
        @Display(name="penalties",width=-1),
        @Display(name="totalAmountRemitted",width=-1)
})
public class AlphaListRemittanceSummary extends AbstractIBean {
    @Id
    @Column(name="seq", nullable = false)
    public Integer seq;
    @Column(name="alphalistId")
    public int alphalistId;
    
    @Column(name="remittanceNum")
    public String remittanceNum;    //1601-C,1601-F,1602,1603
    @Column(name="monthQuarter")
    public String monthQuarter;     //Jan, Feb, 1st Quarter, 2nd Quarter, etc.
    @Column(name="remittanceDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date remittanceDate;
    @Column(name="nameOfBank")
    public String nameOfBank;
    @Column(name="taxesWithheld")
    public double taxesWithheld;
    @Column(name="adjustment")
    public double adjustment;
    @Column(name="penalties")
    public double penalties;
    @Column(name="totalAmountRemitted")
    public double totalAmountRemitted;

    public AlphaListRemittanceSummary() {
    }

    public AlphaListRemittanceSummary(int alphalistId, String remittanceNum, String monthQuarter, double taxesWithheld) {
        this.alphalistId = alphalistId;
        this.remittanceNum = remittanceNum;
        this.monthQuarter = monthQuarter;
        this.taxesWithheld = taxesWithheld;
        this.nameOfBank = AppConfig.getCompanyNameOfBank();
    }

    public int getAlphalistId() {
        return alphalistId;
    }

    public void setAlphalistId(int alphalistId) {
        this.alphalistId = alphalistId;
    }

    public String getMonthQuarter() {
        return monthQuarter;
    }

    public void setMonthQuarter(String monthQuarter) {
        this.monthQuarter = monthQuarter;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }

    public double getPenalties() {
        return penalties;
    }

    public void setPenalties(double penalties) {
        this.penalties = penalties;
    }

    public Date getRemittanceDate() {
        return remittanceDate;
    }

    public void setRemittanceDate(Date remittanceDate) {
        this.remittanceDate = remittanceDate;
    }

    public String getRemittanceNum() {
        return remittanceNum;
    }

    public void setRemittanceNum(String remittanceNum) {
        this.remittanceNum = remittanceNum;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getTaxesWithheld() {
        return taxesWithheld;
    }

    public void setTaxesWithheld(double taxesWithheld) {
        this.taxesWithheld = taxesWithheld;
    }

    public double getTotalAmountRemitted() {
        return totalAmountRemitted;
    }

    public void setTotalAmountRemitted(double totalAmountRemitted) {
        this.totalAmountRemitted = totalAmountRemitted;
       
    }

    public double getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(double adjustment) {
        this.adjustment = adjustment;
    }
    
    

    @Override
    public String popupSearch(String criteria) {
        return null;
    }


}
