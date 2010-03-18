/*
 * AwbFlt.java
 *
 * Created on Sep 30, 2007, 8:02:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import bean.awb.AwbFlt;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template = TemplateTabPage.class, gridCount = 10, sumFooter="3,4,5", 
	columnSearch = {"awb", "flightDesc", "flightDate", "pieces", "weight", "volume", "status", "importStatus", "shc1", "shc2"},
	criteriaSearch = {"carrier", "flightNumber", "flightDate", "status", "importStatus", "shc1", "shc2"}
)
@Displays({
    @Display(name = "awb", label="Awb", type="Label"),
    @Display(name = "pieces", type="Label"),
    @Display(name = "weight", type="Label"),
    @Display(name = "volume", type="Label"),
    @Display(name = "flightDesc", type="Label"),
    @Display(name = "importStatus", type="Label"),
    @Display(name = "shc1", type="Label"),
    @Display(name = "shc2", type="Label"),
    @Display(name = "mh", type="Label"),
    @Display(name = "mp", type="Label"),
    @Display(name = "lp", type="Label"),
    @Display(name = "lc", type="Label")
})
public class AwbFltBuildUpListExt extends AwbFlt {
}
