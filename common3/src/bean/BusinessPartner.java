/*
 * Customer.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

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
	
	public static void main(String[] args) {
		view(BusinessPartner.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
