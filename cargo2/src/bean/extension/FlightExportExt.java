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
import template.screen.ChildTemplateListPopupDownButton;
import bean.Carrier;
import bean.FlightManifestAwb;
import bean.FlightManifestUld;
import bean.FlightNotoc;
import bean.reference.AircraftType;
import bean.reference.Airport;
import bean.reference.TraceStatus;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = template.screen.TemplateTabSinglePage.class, gridCount = 6, 
	    columnSearch = {"carrier","flightNumber","flightDate","origin","destination","status"})
	@Displays({
	    @Display(name = "carrier", mergeFields={"flightNumber","flightDate"}, linktoBean=Carrier.class, type="PopSearch", width=60),
	    @Display(name = "flightNumber", hideLabel=true, width=30),
	    @Display(name = "flightDate", hideLabel=true),
	    @Display(name = "origin", width=50, type="PopSearch", linktoBean=Airport.class),
	    @Display(name = "destination", width=50, type="PopSearch", linktoBean=Airport.class),

	    @Display(name = "aircraftType", width=200, type="PopSearch", linktoBean=AircraftType.class, label="A/C Type"),
	    @Display(name = "status", width=50, type="PopSearch", linktoBean=TraceStatus.class),
	    @Display(name = "departureDate", label="Departure", mergeFields={"departureTime"}),
	    @Display(name = "departureTime", hideLabel=true, type="Time"),
	    @Display(name = "arrivalDate", label="Arrival", mergeFields={"arrivalTime"}),
	    @Display(name = "arrivalTime", hideLabel=true, type="Time"),

//    @Display(name = "flightScheduleSeq"),
//    @Display(name = "legNumber"),
    @Display(name = "grossAmount"),
    @Display(name = "netRevenue"),
    @Display(name = "remarks", gridFieldWidth=3, width=200),

//    @Display(name = "totalPieces"),
    @Display(name = "availWeight", label="Weight", mergeFields={"availVolume"}, width=40),
    @Display(name = "availVolume", label="Volume", width=40),
    @Display(name = "availMh", width=40, label="ULD Mh", mergeFields={"availMp","availLp","availLc"}),
    @Display(name = "availMp", width=40, label="Mp"),
    @Display(name = "availLp", width=40, label="Lp"),
    @Display(name = "availLc", width=40, label="Lc")
})
@DisplayGroups({
    @DisplayGroup(title="Accounting", gridCount=6, fields={"grossAmount","netRevenue","remarks"}),
    @DisplayGroup(title="Available Spaces", gridCount=6, fields={"availWeight","availVolume","availMh","availMp","availLp","availLc"}),
    @DisplayGroup(title="Departure/Arrival", gridCount=4, fields={"departureDate","departureTime","arrivalDate","arrivalTime"})
})
@Reports({
    @template.Report(reportFile="FlightRouteManifest", reportTitle="Route Manifest", reportSql = "${flightScheduleSeq}"),
    @template.Report(reportFile="FlightManifest", reportTitle="Manifest", reportSql = "${seq}"),
    @template.Report(reportFile="FlightManifestAwb", reportTitle="AWB", reportSql = "${seq}"),
    @template.Report(reportFile="FlightManifestUld", reportTitle="ULD", reportSql = "${seq}"),
    @template.Report(reportFile="FlightNotoc", reportTitle="Notoc", reportSql = "${seq}")
})
@ChildRecords(
    value={
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, canNew=false, canSave=false, entity=FlightManifestAwb.class, sql="SELECT a FROM FlightManifestAwb a WHERE a.flightSeq='${seq}'", title="Awb", fieldMapping={"seq","flightSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, canNew=false, canSave=false, entity=FlightManifestUld.class, sql="SELECT a FROM FlightManifestUld a WHERE a.flightSeq='${seq}'", title="ULD", fieldMapping={"seq","flightSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=FlightNotoc.class, sql="SELECT a FROM FlightNotoc a WHERE a.flightSeq='${seq}'", title="Notoc", fieldMapping={"seq","flightSeq"})
    }
)
@template.ActionButtons({
    @template.ActionButton(name="btnLoadAWB", label="Load AWB"),
    @template.ActionButton(name="btnLoadULD", label="Load ULD"),
    @template.ActionButton(name="btnManifestFlight", label="Manifest Flight"),
    @template.ActionButton(name="btnShowNotoc", label="Show Notoc"),
    @template.ActionButton(name="btnDepartFlight", label="Depart Flight"),
    @template.ActionButton(name="btnShowMessages", label="Show Messages")
})
public class FlightExportExt extends bean.Flight {
	public static void main(String[] args) {
		view(FlightExportExt.class);
	}
}
