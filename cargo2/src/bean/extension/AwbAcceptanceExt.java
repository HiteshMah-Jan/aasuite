/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import bean.awb.AwbCheckin;
import bean.reference.Airport;
import bean.reference.SpecialHandling;
import bean.reference.TraceStatus;
import template.screen.TemplateTabPage;
@UITemplate(template = TemplateTabPage.class, gridCount = 6,
    columnSearch = {"prefix","serial","flightSeq","pieces","grossWeight","shc1","shc2"}, showChart=true)
@Displays({
    @Display(name = "prefix"),
    @Display(name = "serial"),
    @Display(name = "flightSeq"),
    @Display(name = "pieces"),
    @Display(name = "grossWeight"),
    @Display(name = "ipcs"),
    @Display(name = "container"),
    @Display(name = "containerWeight"),
    @Display(name = "shc1", type="PopSearch", linktoBean=SpecialHandling.class),
    @Display(name = "shc2", type="PopSearch", linktoBean=SpecialHandling.class),
    @Display(name = "location"),
    @Display(name = "scr"),
    @Display(name = "status", type="PopSearch", linktoBean=TraceStatus.class),
    @Display(name = "dangerousGood"),
    @Display(name = "identifier"),
    @Display(name = "offload", type="PopSearch", linktoBean=Airport.class)
})
public class AwbAcceptanceExt extends AwbCheckin {
	public static void main(String[] args) {
		view(AwbAcceptanceExt.class);
	}
}
