/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import bean.awb.AwbDeliver;
import javax.persistence.Entity;
import template.screen.TemplateTabPage;
/**
 *
 * @author Charliemagne Mark
 */
@Entity
@UITemplate(template = TemplateTabPage.class, gridCount = 6,
    columnSearch = {"prefix","serial","pieces","weight","volume","receivedBy","deliverDate"})
@Displays({
    @Display(name = "prefix"),
    @Display(name = "serial"),
    @Display(name = "pieces"),
    @Display(name = "weight"),
    @Display(name = "volume"),
    @Display(name = "receivedBy"),
    @Display(name = "deliverDate"),
    @Display(name = "totalChargesDue"),
    @Display(name = "totalPiecesReleases"),
    @Display(name = "releaseTo"),
    @Display(name = "releaseToAddress"),
    @Display(name = "releaseToIdType"),
    @Display(name = "remarks")
})
public class AwbDeliveryExt extends AwbDeliver {
	public static void main(String[] args) {
		view(AwbDeliveryExt.class);
	}
}
