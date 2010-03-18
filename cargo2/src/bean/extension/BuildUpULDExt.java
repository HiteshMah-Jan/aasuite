/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopupDownButton;
import bean.Flight;
import bean.awb.AwbFlt;
import bean.awb.AwbUld;
import bean.reference.Airport;
import bean.reference.TraceStatus;
/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = template.screen.TemplateTabSinglePage.class, gridCount = 8, 
    columnSearch = {"uld","currentAirport","destination","finalDestination","flightSeq","status"})
@Displays({
    @Display(name = "uld", width=90, enabled=false),
    @Display(name = "currentAirport", width=60, type="PopSearch", linktoBean=Airport.class, enabled=false),
    @Display(name = "destination", label="Dest", width=60, type="PopSearch", linktoBean=Airport.class, enabled=false),
    @Display(name = "finalDestination", label="Final Dest", width=60, type="PopSearch", linktoBean=Airport.class, enabled=false),

    @Display(name = "availWeight", label="Weight", width=60, labelTop=true, leftLabel="Available", enabled=false),
    @Display(name = "availVolume", width=60, label="Volume", labelTop=true, enabled=false),
    @Display(name = "maxWeight", width=60, label="Maximum", enabled=false),
    @Display(name = "maxVolume", width=60, hideLabel=true, enabled=false),
    @Display(name = "scaleWeight", width=60, gridFieldWidth=3, label="Scale", enabled=false),
    @Display(name = "tareWeight", width=60, gridFieldWidth=3, label="Tare", enabled=false),

    @Display(name = "flightSeq", label="Flight", width=300, type="PopSearch", linktoBean=Flight.class),
    @Display(name = "deck", width=60),
    @Display(name = "status", width=60, type="PopSearch", linktoBean=TraceStatus.class)

})
@DisplayGroups({
    @DisplayGroup(title="Dimension", gridCount=4, 
    		fields={"availWeight","availVolume","maxWeight","maxVolume","scaleWeight","tareWeight"}),
    @DisplayGroup(title="Flight", gridCount=6,
    		fields={"flightSeq","deck","status"})
})
@Reports({
    @template.Report(reportFile="Uld", reportTitle="ULD Report", reportSql = "${seq}"),
    @template.Report(reportFile="UldAwb", reportTitle="AWB in ULD", reportSql = "${seq}")
})
@ChildRecords(
    value={
        @ChildRecord(template=ChildTemplateListOnly.class, entity=AwbFltBuildUpListExt.class, sql="SELECT a FROM AwbFlt a WHERE a.uldNumber='${uld}'", title="Awb")
    }
)
@template.ActionButtons({
    @template.ActionButton(name="btnBuildUpULD", label="Build Up ULD")
})
public class BuildUpULDExt extends bean.UldNumber {
	public static void main(String[] args) {
		view(BuildUpULDExt.class);
	}
}
