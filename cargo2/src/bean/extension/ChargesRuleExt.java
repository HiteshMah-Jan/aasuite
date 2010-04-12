/*
 * Carrier.java
 *
 * Created on Sep 30, 2007, 8:02:07 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSearchOnlyNoCriteria;
import bean.ChargesRule;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template = TemplateSearchOnlyNoCriteria.class, gridCount = 4, 
    columnSearch = {"chargeCode","origin","destination","shc","serviceLevel","amount","amountPerKg"},
    sumFooter="5", editableCol="5,6")
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
public class ChargesRuleExt extends ChargesRule {

	@Override
	public void save() {
//		do not call save
	}
	
}
