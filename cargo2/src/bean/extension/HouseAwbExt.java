/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import bean.reference.Airport;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = template.screen.TemplateTabSinglePage.class, gridCount = 8, columnSearch = {"prefix","serial","origin","destination"}, showChart=true)
@Displays({
    @Display(name = "prefix", width=30, mergeFields={"serial"}, label="Awb"),
    @Display(name = "serial", width=70, hideLabel=true),
    @Display(name = "origin", width=50, type="PopSearch", linktoBean=Airport.class, mergeFields={"destination"}),
    @Display(name = "destination", width=50, type="PopSearch", linktoBean=Airport.class),

    @Display(name = "pieces", width=50, mergeFields={"weight","volume","kgLb"}),
    @Display(name = "weight", width=50),
    @Display(name = "volume", width=50),
    @Display(name = "kgLb", width=50, hideLabel=true, type="Combo", modelCombo={"KG/MC","LB/CF"}),

    @Display(name = "mh", width=20, mergeFields={"mp","lp","lc"}, label="ULD Mh"),
    @Display(name = "mp", width=20),
    @Display(name = "lp", width=20),
    @Display(name = "lc", width=20)
})
public class HouseAwbExt extends bean.Awb {

}
