/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import bean.awb.AwbFlt;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = template.screen.TemplateTabSinglePageLeftRightNoSearchResult.class, 
		columnSearch = {"serial"})
@Displays({
    @Display(name = "prefix", width=30, mergeFields={"serial"}),
    @Display(name = "serial", width=70, hideLabel=true)
})
@Reports({
    @template.Report(reportFile="AwbForm", reportTitle="All Bills", reportSql = "${seq}"),
    @template.Report(reportFile="AwbHouse", reportTitle="House", reportSql = "${seq}"),
    @template.Report(reportFile="AwbFlight", reportTitle="Route", reportSql = "${seq}"),
    @template.Report(reportFile="AwbDimension", reportTitle="Dimension", reportSql = "${seq}"),
    @template.Report(reportFile="AwbShc", reportTitle="SHC", reportSql = "${seq}"),
    @template.Report(reportFile="AwbUld", reportTitle="ULD", reportSql = "${seq}"),
    @template.Report(reportFile="OceanBL", reportTitle="Ocean BL", reportSql = "${seq}"),
    @template.Report(reportFile="PackingList", reportTitle="Packing", reportSql = "${seq}")
})
@ChildRecords(
    {
        @ChildRecord(template=ChildTemplateListOnly.class, entity=AwbFlt.class, sql="SELECT a FROM AwbFlt a WHERE a.awbSeq=${seq} ORDER BY a.lastUpdated DESC, a.flightDate DESC", title="Routes Status", fieldMapping={"seq","awbSeq"})
    }
)
public class TrackAndTraceExt extends bean.Awb {
	public static void main(String[] args) {
		view(TrackAndTraceExt.class);
	}
}
