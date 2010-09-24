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
import template.screen.ChildTemplateListPopupDownButton;
import bean.Participant;
import bean.awb.AwbCharges;
import bean.awb.AwbCheckin;
import bean.awb.AwbCommissionInformation;
import bean.awb.AwbCustoms;
import bean.awb.AwbDangerousGoods;
import bean.awb.AwbDeliver;
import bean.awb.AwbDimension;
import bean.awb.AwbFlt;
import bean.awb.AwbHistory;
import bean.awb.AwbInspection;
import bean.awb.AwbRateDescription;
import bean.awb.AwbShc;
import bean.awb.AwbUld;
import bean.reference.Airport;
import bean.reference.Charges;
import bean.reference.Currency;
import bean.reference.ServiceLevel;
import template.screen.ChildTemplateListOnly;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = template.screen.TemplateTabPage.class, gridCount = 6, columnSearch = {"prefix","serial","origin","destination"})
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
    
    
    @Display(name = "currencyCode", addInfoOnly=true, width=50, label="Currency", type="PopSearch", linktoBean=Currency.class),
    @Display(name = "originPrepaid", addInfoOnly=true, width=30, mergeFields={"originChargeCode"}),
    @Display(name = "originChargeCode", addInfoOnly=true, width=50, label="Charge Code", type="PopSearch", linktoBean=Charges.class),
    @Display(name = "paymentType", addInfoOnly=true, width=50, type="Combo", modelCombo={"PP","CC","PC"}),
    @Display(name = "destinationPrepaid", addInfoOnly=true, width=30, mergeFields={"destinationChargeCode"}),
    @Display(name = "destinationChargeCode", addInfoOnly=true, width=50, label="Charge Code", type="PopSearch", linktoBean=Charges.class),

    @Display(name = "commodityCode", addInfoOnly=true, width=50),
    @Display(name = "declaredValueCarriage", addInfoOnly=true, width=50, label="Value Carriage", mergeFields={"declaredValueCustoms","amountInsurance"}),
    @Display(name = "declaredValueCustoms", addInfoOnly=true, width=50, label="Customs"),
    @Display(name = "amountInsurance", addInfoOnly=true, width=50, label="Insurance", gridFieldWidth=5),

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
@ChildRecords(
    value={
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=HouseAwbExt.class, sql="SELECT a FROM Awb a WHERE a.masterAwbSeq=${seq}", title="HWB", fieldMapping={"seq","masterAwbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbFlt.class, sql="SELECT a FROM AwbFlt a WHERE a.awbSeq=${seq}", title="Flights", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbDimension.class, sql="SELECT a FROM AwbDimension a WHERE a.awbSeq=${seq}", title="Dimension", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbShc.class, sql="SELECT a FROM AwbShc a WHERE a.awbSeq=${seq}", title="SHC", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbUld.class, sql="SELECT a FROM AwbUld a WHERE a.awbSeq=${seq}", title="ULD", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbCustoms.class, sql="SELECT a FROM AwbCustoms a WHERE a.awbSeq=${seq}", title="Customs", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbDangerousGoods.class, sql="SELECT a FROM AwbDangerousGoods a WHERE a.awbSeq=${seq}", title="DG", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbRateDescription.class, sql="SELECT a FROM AwbRateDescription a WHERE a.awbSeq=${seq}", title="Rate", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbCommissionInformation.class, sql="SELECT a FROM AwbCommissionInformation a WHERE a.awbSeq=${seq}", title="Commission", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbCharges.class, sql="SELECT a FROM AwbCharges a WHERE a.awbSeq=${seq}", title="Charges", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=AwbPaymentExt.class, sql="SELECT a FROM Payment a WHERE a.form='Awb' AND a.recordId=${seq}", title="Payment", fieldMapping={"seq","recordId"}),
        @ChildRecord(template=ChildTemplateListOnly.class, entity=AwbInspection.class, sql="SELECT a FROM AwbInspection a WHERE a.awbSeq='${seq}'", title="Inspection", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListOnly.class, entity=AwbCheckin.class, sql="SELECT a FROM AwbCheckin a WHERE a.awbSeq='${seq}'", title="Checkin", fieldMapping={"seq","awbSeq"}),
        @ChildRecord(template=ChildTemplateListOnly.class, entity=AwbDeliver.class, sql="SELECT a FROM AwbDeliver a WHERE a.masterAwbSeq='${seq}'", title="Arrival/Deliver", fieldMapping={"seq","masterAwbSeq"}),
        @ChildRecord(template=ChildTemplateListOnly.class, entity=AwbHistory.class, sql="SELECT a FROM AwbHistory a WHERE a.awbSeq='${seq}'", title="History", fieldMapping={"seq","awbSeq"})
    }, 
    info={
        @ParentAddInfo(gridCount=6, title="Additional Info", 
            displayFields={
                "remarks",
                "paymentType",
                "serviceCode",
                "rateClassCode",
                "uldRateClassType",
                "valueCarraige",
                "valueCustoms",
                "valueInsurance",
                "currencyCode",
                "originPrepaid",
                "destinationPrepaid",
                "commodityCode",
                "place",
                "creditDetails",
                "originChargeCode",
                "destinationChargeCode"
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
@Reports({
    @template.Report(reportFile="AwbForm", reportTitle="AWB", reportSql = "${seq}"),
    @template.Report(reportFile="AwbHouse", reportTitle="HAWB", reportSql = "${seq}"),
    @template.Report(reportFile="AwbFlight", reportTitle="Route", reportSql = "${seq}"),
    @template.Report(reportFile="AwbDimension", reportTitle="Dimension", reportSql = "${seq}"),
    @template.Report(reportFile="AwbShc", reportTitle="SHC", reportSql = "${seq}"),
    @template.Report(reportFile="AwbUld", reportTitle="ULD", reportSql = "${seq}"),
    @template.Report(reportFile="AwbCharges", reportTitle="Charges", reportSql = "${seq}"),
    @template.Report(reportFile="AwbCommissionInfo", reportTitle="Commission Info", reportSql = "${seq}"),
    @template.Report(reportFile="AwbCustoms", reportTitle="Customs", reportSql = "${seq}"),
    @template.Report(reportFile="AwbDangerousGoods", reportTitle="DG", reportSql = "${seq}"),
    @template.Report(reportFile="AwbInspection", reportTitle="Inspection", reportSql = "${seq}"),
    @template.Report(reportFile="AwbRateDescription", reportTitle="Rate", reportSql = "${seq}"),
    @template.Report(reportFile="AwbHistory", reportTitle="History", reportSql = "${seq}")
})
@template.ActionButtons({
    @template.ActionButton(name="btnPutCharges", label="Put Charges"),
    @template.ActionButton(name="btnPutCommission", label="Put Commission"),
    @template.ActionButton(name="btnCreateInvoice", label="Create Invoice"),
    @template.ActionButton(name="btnViewGL", label="View GL"),
    @template.ActionButton(name="btnShowMessages", label="Show Messages")
})
public class FullAwbExt extends bean.Awb {
	public static void main(String[] args) {
		view(FullAwbExt.class);
	}
}
