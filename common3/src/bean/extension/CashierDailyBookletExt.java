/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.accounting.CashierDailyBooklet;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSearchOnly;
import template.screen.TemplateSinglePage;

/**
 *
 * @author alex
 */
@UITemplate(template = TemplateSearchOnly.class, gridCount = 4, 
    columnSearch = {"startOR","endOR"}, 
    orderBy="a.usedDate DESC")
@Displays({
    @Display(name="startOR", label="Acct 1", width=60, labelTop = true, type="Label"),
    @Display(name="startMIS", label="Acct 2", width=60, labelTop = true, type="Label"),
    @Display(name="endOR", label="-", width=60, hideLabel = true, type="Label"),
    @Display(name="endMIS", label="-", width=60, hideLabel = true, type="Label")
})
public class CashierDailyBookletExt extends CashierDailyBooklet {

}
