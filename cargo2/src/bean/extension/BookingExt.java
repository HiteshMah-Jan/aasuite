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
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopupDownButton;
import bean.Participant;
import bean.awb.AwbCharges;
import bean.awb.AwbDimension;
import bean.awb.AwbFlt;
import bean.awb.AwbPackingList;
import bean.awb.AwbShc;
import bean.awb.AwbUld;
import bean.reference.Airport;
import bean.reference.Charges;
import bean.reference.Currency;
import bean.reference.ServiceLevel;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = template.screen.TemplateTabSinglePage.class, gridCount = 6, columnSearch = {"prefix","serial","origin","destination"})
@Displays({
    @Display(name = "prefix", width=30, mergeFields={"serial"}, label="Bill #               ", searchLabel="Prefix"),
    @Display(name = "serial", width=70, hideLabel=true),
    @Display(name = "origin", width=70, type="PopSearch", linktoBean=Airport.class),
    @Display(name = "destination", width=70, type="PopSearch", linktoBean=Airport.class),
    @Display(name = "departureDate"),
    @Display(name = "arrivalDate"),

    @Display(name = "priority", width=40, type="NumberCombo", startCount=1, endCount=5),
    @Display(name = "serviceLevel", width=80, type="PopSearch", linktoBean=ServiceLevel.class),
    @Display(name = "natureOfGoods", width=80),
    
    @Display(name = "agent", type="PopSearch", linktoBean=Participant.class),
    @Display(name = "shipper", type="PopSearch", linktoBean=Participant.class),
    @Display(name = "consignee", type="PopSearch", linktoBean=Participant.class),
    @Display(name = "contactPerson"),
    @Display(name = "contactPhone", label="Phone"),
    
    @Display(name = "pieces", width=50),
    @Display(name = "weight", width=50),
    @Display(name = "volume", width=50, mergeFields={"kgLb"}),
    @Display(name = "kgLb", width=50, hideLabel=true, type="Combo", modelCombo={"KG/MC","LB/CF"}),
    @Display(name = "mh", width=20, mergeFields={"mp","lp","lc"}, label="ULD Mh"),
    @Display(name = "mp", width=20),
    @Display(name = "lp", width=20),
    @Display(name = "lc", width=20),
    
    @Display(name = "commodityCode", addInfoOnly=true, width=50),
//    @Display(name = "master", addInfoOnly=true, width=30, mergeFields={"slac"}),
//    @Display(name = "slac", addInfoOnly=true, width=30),
//    @Display(name = "house", addInfoOnly=true, width=30, mergeFields={"masterAwbSeq","hwbSerial"}),
//    @Display(name = "masterAwbSeq", addInfoOnly=true, width=30, label="Master Awb"),
//    @Display(name = "hwbSerial", addInfoOnly=true, width=30),
    
    @Display(name = "specialServiceRequest", label="SSR", addInfoOnly=true, gridFieldWidth=5, width=400, type="TextArea", height=40),
    @Display(name = "otherServiceInformation", label="OSI", addInfoOnly=true, gridFieldWidth=5, width=-1, type="TextArea", height=40),
    @Display(name = "shipmentSupplementaryInformation", label="SSI", addInfoOnly=true, gridFieldWidth=5, width=-1, type="TextArea", height=40),
    @Display(name = "shipmentReferenceInformation", label="SRI", addInfoOnly=true, gridFieldWidth=5, width=-1, type="TextArea", height=40),
    @Display(name = "freeDescriptionOfGoods", label="FDG", addInfoOnly=true, gridFieldWidth=5, width=-1, type="TextArea", height=40)
    
//    @Display(name = "serviceCode", addInfoOnly=true),
//    @Display(name = "rateClassCode", addInfoOnly=true),
//    @Display(name = "uldRateClassType", addInfoOnly=true),
//    @Display(name = "place", addInfoOnly=true),
//    @Display(name = "creditDetails", addInfoOnly=true),
//    @Display(name = "remarks", addInfoOnly=true)
})
@DisplayGroups({
    @DisplayGroup(title="Service Level", gridCount=6, fields={"priority","serviceLevel","natureOfGoods"}),
    @DisplayGroup(title="Customer Contact", gridCount=6, fields={"agent","shipper","consignee","contactPerson","contactPhone"}),
    @DisplayGroup(title="Weight and Volume", gridCount=8, fields={"pieces","weight","volume","kgLb","mh","mp","lp","lc"})
})
@Reports({
    @template.Report(reportFile="AwbForm", reportTitle="All Bills", reportSql = "${seq}"),
    @template.Report(reportFile="AwbHouse", reportTitle="House", reportSql = "${seq}"),
//    @template.Report(reportFile="AwbFlight", reportTitle="Route", reportSql = "${seq}"),
    @template.Report(reportFile="AwbDimension", reportTitle="Dimension", reportSql = "${seq}"),
    @template.Report(reportFile="AwbShc", reportTitle="SHC", reportSql = "${seq}"),
    @template.Report(reportFile="AwbUld", reportTitle="ULD", reportSql = "${seq}"),
    @template.Report(reportFile="OceanBL", reportTitle="Ocean BL", reportSql = "${seq}"),
    @template.Report(reportFile="PackingList", reportTitle="Packing", reportSql = "${seq}")
})
@ChildRecords(
    value={
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=HouseAwbExt.class, sql="SELECT a FROM Awb a WHERE a.masterAwbSeq=${seq}", title="House", fieldMapping={"seq","masterAwbSeq"}),
        @ChildRecord(template=ChildTemplateListOnly.class, entity=AwbFlt.class, sql="SELECT a FROM AwbFlt a WHERE a.awbSeq=${seq}", title="Routes", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbDimension.class, sql="SELECT a FROM AwbDimension a WHERE a.awbSeq=${seq}", title="Dimension", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbShc.class, sql="SELECT a FROM AwbShc a WHERE a.awbSeq=${seq}", title="SHC", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbUld.class, sql="SELECT a FROM AwbUld a WHERE a.awbSeq=${seq}", title="ULD", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbPackingList.class, sql="SELECT a FROM AwbPackingList a WHERE a.awbSeq=${seq}", title="Packing List", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbCharges.class, sql="SELECT a FROM AwbCharges a WHERE a.awbSeq=${seq}", title="Charges", fieldMapping={"seq","awbSeq"}),
    }, 
    info={
        @ParentAddInfo(gridCount=6, title="Additional Info", 
            displayFields={
                "remarks",
                "paymentType",
                "serviceCode",
                "rateClassCode",
                "uldRateClassType",
                "commodityCode",
                "place",
                "creditDetails"
            }),
            @ParentAddInfo(gridCount=6, title="Reference Info", 
                    displayFields={
                        "specialServiceRequest",
                        "otherServiceInformation",
                        "shipmentSupplementaryInformation",
                        "shipmentReferenceInformation",
                        "freeDescriptionOfGoods"
                    })
    }
)
@template.ActionButtons({
    @template.ActionButton(name="btnChargesRule", label="Applicable Charges"),
    @template.ActionButton(name="btnCreateInvoice", label="Create Invoice"),
    @template.ActionButton(name="btnViewGL", label="View GL"),
    @template.ActionButton(name="btnShowMessages", label="Show Messages")
})
public class BookingExt extends bean.Awb {
	public static void main(String[] args) {
		view(BookingExt.class);
	}
}
