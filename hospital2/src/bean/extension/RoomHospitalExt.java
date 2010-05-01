/*
 * Room.java
 *
 * Created on Dec 2, 2007, 12:43:17 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.ActionButton;
import template.ActionButtons;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabSinglePageLeftRight;
import bean.Patient;
import bean.person.PatientRoom;
import bean.reference.RoomHospital;
import template.Reports;
import bean.extension.RoomHospitalExt;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(
		columnSearch={"room", "bed", "type", "rate", "patient"}, 
		criteriaSearch={"room", "bed", "type", "patient"},
		gridCount=4, title="Room", template=TemplateTabSinglePageLeftRight.class, canDelete=false)
@Displays({
//        @Display(name="code"),
        @Display(name="room"),
        @Display(name="bed"),
        @Display(name="rate"),
        @Display(name="type", type="Combo", modelCombo={"PRIVATE","SEMI PRIVATE","WARD","OPERATING ROOM","ICU","DELIVERY ROOM","EMERGENCY ROOM","NURSERY","RADIOLOGY","LABORATORY"}),
        @Display(name="patientId", type="PopSearch", linktoBean=Patient.class, label="Patient",gridFieldWidth=3,width=-1),
        @Display(name="startDate"),
        @Display(name="startTime", type="Time"),
        @Display(name="endDate"),
        @Display(name="endTime", type="Time")
})
@ActionButtons({
    @ActionButton(label="Release Patient", name="btnRelease")
})
@ChildRecords(
		@template.ChildRecord(title="Room Usage and Utilization", entity = PatientRoom.class, sql = "SELECT a FROM PatientRoom a WHERE a.roomBed='${code}'", fieldMapping={"code","roomBed"},template=ChildTemplateListOnly.class)
)
 @Reports( {
		
		@template.Report(reportFile = "AdmissionDischargeSlip", reportTitle = "Discharge Slip", reportSql = "${personId}")

})
public class RoomHospitalExt extends RoomHospital {
public static void main(String[] args) {
		view(RoomHospitalExt.class);
	}}
