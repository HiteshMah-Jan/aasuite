/*
 * Customer.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

@Entity
@Table(name = "BusinessPartner")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="code"),
	@Display(name="fullName"),
	@Display(name="foreignName"),
	@Display(name="firstName"),
	@Display(name="lastName"),
	@Display(name="middleName"),
	@Display(name="group"),
	@Display(name="currency"),
	@Display(name="federalTaxId"),
	@Display(name="orders"),
	@Display(name="opportunity")
})
public class BusinessPartner extends AbstractIBean {
	@Id
	public String code;
	public String fullName;
	public String foreignName;
	public String firstName;
	public String lastName;
	public String middleName;
	public String group;
	public String currency;
	public String federalTaxId;
	public double orders;
	public double opportunity;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getForeignName() {
		return foreignName;
	}
	public void setForeignName(String foreignName) {
		this.foreignName = foreignName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getFederalTaxId() {
		return federalTaxId;
	}
	public void setFederalTaxId(String federalTaxId) {
		this.federalTaxId = federalTaxId;
	}
	public double getOrders() {
		return orders;
	}
	public void setOrders(double orders) {
		this.orders = orders;
	}
	public double getOpportunity() {
		return opportunity;
	}
	public void setOpportunity(double opportunity) {
		this.opportunity = opportunity;
	}
	public static void main(String[] args) {
		view(BusinessPartner.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
