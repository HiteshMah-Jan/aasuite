/*
 * Carrier.java
 *
 * Created on Sep 30, 2007, 8:02:07 PM
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
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "ChargesRule")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, 
    columnSearch = {"chargeCode","startDate","endDate","active","origin","destination","shc","serviceLevel","amount","amountPerKg"}, showChart=true)
@Displays({
    @Display(name = "chargeCode", width=40),
    @Display(name = "active", width=40),
    @Display(name = "startDate"),
    @Display(name = "endDate"),
    @Display(name = "origin"),
    @Display(name = "destination"),
    @Display(name = "shc"),
    @Display(name = "serviceLevel"),
    @Display(name = "amount"),
    @Display(name = "amountPerKg")
})
public class ChargesRule extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "chargeCode", nullable = false, length = 5)
    public String chargeCode;
    @Column(name = "active")
    public boolean active;
    @Column(name = "startDate", nullable=false)
    @Temporal(value = TemporalType.DATE)
    public Date startDate;
    @Column(name = "endDate", nullable=false)
    @Temporal(value = TemporalType.DATE)
    public Date endDate;
    @Column(name = "origin", nullable=false)
    public String origin;
    @Column(name = "destination", nullable=false)
    public String destination;
    @Column(name = "shc")
    public String shc;
    @Column(name = "serviceLevel")
    public String serviceLevel;
    @Column(name = "amount")
    public double amount;
    @Column(name = "amountPerKg")
    public double amountPerKg;


    public double getAmountPerKg() {
		return amountPerKg;
	}


	public void setAmountPerKg(double amountPerKg) {
		this.amountPerKg = amountPerKg;
	}


	public Integer getSeq() {
		return seq;
	}


	public void setSeq(Integer seq) {
		this.seq = seq;
	}


	public String getChargeCode() {
		return chargeCode;
	}


	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public String getShc() {
		return shc;
	}


	public void setShc(String shc) {
		this.shc = shc;
	}


	public String getServiceLevel() {
		return serviceLevel;
	}


	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "chargeCode","active","origin","destination","shc","priority");
    }
}
