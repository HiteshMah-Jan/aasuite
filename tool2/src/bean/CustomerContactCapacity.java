/*
 * Bug.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CustomerContactCapacity")
@UITemplate(gridCount = 4, columnSearch = {"totalIncome","totalIRA","annualRegIncome"})
@Displays({
    @Display(name="totalIncome",mandatory=true),
    @Display(name="totalIRA"),
    @Display(name="annualRegIncome"),
    @Display(name="maxDebt"),
    @Display(name="annualAmortization"),
    @Display(name="netDebtService"),
    @Display(name="annualBorrowCapacity"),
    @Display(name="borrowCapacitySeven")
})
public class CustomerContactCapacity extends AbstractIBean {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "customerId")
    public int customerId;
    @Column(name = "totalIncome")
    public double totalIncome;
    @Column(name = "totalIRA")
    public double totalIRA;
    @Column(name = "annualRegIncome")
    public double annualRegIncome;
    @Column(name = "maxDebt")
    public double maxDebt;
    @Column(name = "annualAmortization")
    public double annualAmortization;
    @Column(name = "netDebtService")
    public double netDebtService;
    @Column(name = "annualBorrowCapacity")
    public double annualBorrowCapacity;
    @Column(name = "borrowCapacitySeven")
    public double borrowCapacitySeven;

    public double getAnnualAmortization() {
        return annualAmortization;
    }

    public void setAnnualAmortization(double annualAmortization) {
        this.annualAmortization = annualAmortization;
    }

    public double getAnnualBorrowCapacity() {
        return annualBorrowCapacity;
    }

    public void setAnnualBorrowCapacity(double annualBorrowCapacity) {
        this.annualBorrowCapacity = annualBorrowCapacity;
    }

    public double getAnnualRegIncome() {
        return annualRegIncome;
    }

    public void setAnnualRegIncome(double annualRegIncome) {
        this.annualRegIncome = annualRegIncome;
    }

    public double getBorrowCapacitySeven() {
        return borrowCapacitySeven;
    }

    public void setBorrowCapacitySeven(double borrowCapacitySeven) {
        this.borrowCapacitySeven = borrowCapacitySeven;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getMaxDebt() {
        return maxDebt;
    }

    public void setMaxDebt(double maxDebt) {
        this.maxDebt = maxDebt;
    }

    public double getNetDebtService() {
        return netDebtService;
    }

    public void setNetDebtService(double netDebtService) {
        this.netDebtService = netDebtService;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getTotalIRA() {
        return totalIRA;
    }

    public void setTotalIRA(double totalIRA) {
        this.totalIRA = totalIRA;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "contactName");
	}
	
	@Override
	public void setupIndex() {
		runIndex(1, "customerId");
	}
}
