/*
 * Person.java
 *
 * Created on Oct 26, 2007, 9:34:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.Patient;
import bean.person.PatientAllergy;

import bean.person.PatientPregnancyHistory;
import java.io.Serializable;
import template.screen.TemplateTabPage;

import template.*;
/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"lastName", "firstName"}, criteriaSearch = {"lastName","firstName"} ,showImages=true)
@ChildRecords(
    value={
        @ChildRecord(title="Ultrasound", entity = PatientUltrasoundObExt.class, sql = "SELECT a FROM PatientUltrasoundOb a WHERE a.patient=${personId}", fieldMapping={"personId","patient"}),
        @ChildRecord(title="Allegery", entity = PatientAllergy.class, sql = "SELECT a FROM PatientAllergy a WHERE a.patientId=${personId}", fieldMapping={"personId","patientId"}),
        @ChildRecord(title="1st Pregnancy", entity = PatientPregnancyHistory.class, sql = "SELECT a FROM PatientPregnancyHistory a WHERE a.patientId=${personId} AND a.childCount=1", fieldMapping={"personId","patientId"}),
        @ChildRecord(title="2nd", entity = PatientPregnancyHistory.class, sql = "SELECT a FROM PatientPregnancyHistory a WHERE a.patientId=${personId} AND a.childCount=2", fieldMapping={"personId","patientId"}),
        @ChildRecord(title="3rd", entity = PatientPregnancyHistory.class, sql = "SELECT a FROM PatientPregnancyHistory a WHERE a.patientId=${personId} AND a.childCount=3", fieldMapping={"personId","patientId"}),
        @ChildRecord(title="4th", entity = PatientPregnancyHistory.class, sql = "SELECT a FROM PatientPregnancyHistory a WHERE a.patientId=${personId} AND a.childCount=4", fieldMapping={"personId","patientId"}),
        @ChildRecord(title="5th", entity = PatientPregnancyHistory.class, sql = "SELECT a FROM PatientPregnancyHistory a WHERE a.patientId=${personId} AND a.childCount=5", fieldMapping={"personId","patientId"}),
        @ChildRecord(title="6th", entity = PatientPregnancyHistory.class, sql = "SELECT a FROM PatientPregnancyHistory a WHERE a.patientId=${personId} AND a.childCount=6", fieldMapping={"personId","patientId"}),
        @ChildRecord(title="7th", entity = PatientPregnancyHistory.class, sql = "SELECT a FROM PatientPregnancyHistory a WHERE a.patientId=${personId} AND a.childCount=7", fieldMapping={"personId","patientId"}),
        @ChildRecord(title="8th", entity = PatientPregnancyHistory.class, sql = "SELECT a FROM PatientPregnancyHistory a WHERE a.patientId=${personId} AND a.childCount=8", fieldMapping={"personId","patientId"}),
        @ChildRecord(title="9th", entity = PatientPregnancyHistory.class, sql = "SELECT a FROM PatientPregnancyHistory a WHERE a.patientId=${personId} AND a.childCount=9", fieldMapping={"personId","patientId"}),
        @ChildRecord(title="10th", entity = PatientPregnancyHistory.class, sql = "SELECT a FROM PatientPregnancyHistory a WHERE a.patientId=${personId} AND a.childCount=10", fieldMapping={"personId","patientId"})
    },
    info={
        @ParentAddInfo(title="Additional Info", displayFields={"birthDate","age","placeOfBirth","gender","citizenship","religion","address", "contactNumber"})
}
)
@Displays({
        
        @Display(name="lastName"),
        @Display(name="firstName"),
//        @Display(name="department",width=-1,type="Combo", sqlCombo="SELECT a FROM Department a"),
//        @Display(name="physicianId",width=-1, type = "PopSearch", linktoBean=Physician.class, linktoColumns={"lastName","firstName"},label="Physician"),
        @Display(name="mobilePhone"),
        @Display(name="email"),
        @Display(name="im1"),
        @Display(name="im2"),
        @Display(name="childCount"),
        
        
        @Display(name="birthDate", addInfoOnly=true),
        @Display(name="age",width=30, addInfoOnly=true),
        @Display(name="gender", type="Combo", modelCombo={"MALE", "FEMALE"}, addInfoOnly=true,width=-1),
        @Display(name="maritalStatus", type="Combo", modelCombo={"SINGLE","MARRIED"}, addInfoOnly=true),
        
     
        @Display(name="placeOfBirth", addInfoOnly=true,width=150),
        @Display(name="citizenship", addInfoOnly=true,width=150),
        @Display(name="religion", addInfoOnly=true,width=150),
        @Display(name="contactNumber", addInfoOnly=true,gridFieldWidth=3,width=-1),
        @Display(name="address", addInfoOnly=true,gridFieldWidth=3,width=-1)
       
})
@DisplayGroups({
    @DisplayGroup(title="Contact", fields={"mobilePhone", "email", "im1", "im2"})
})
@Reports({
    @template.Report(reportFile="PatientInfo", reportTitle="Patient Info", reportSql = "${personId}"),
    @template.Report(reportFile="PatientUltrasound", reportTitle="Ultrasound", reportSql = "${personId}"),
    @template.Report(reportFile="PatientAllergy", reportTitle="Allergy", reportSql = "${personId}"),
    @template.Report(reportFile="Pregnancy1", reportTitle="1st Pregnancy", reportSql = "${personId}"),
    @template.Report(reportFile="Pregnancy2", reportTitle="2nd", reportSql = "${personId}"),
    @template.Report(reportFile="Pregnancy3", reportTitle="3rd", reportSql = "${personId}"),
    @template.Report(reportFile="Pregnancy4", reportTitle="4th", reportSql = "${personId}"),
    @template.Report(reportFile="Pregnancy5", reportTitle="5th", reportSql = "${personId}"),
    @template.Report(reportFile="Pregnancy6", reportTitle="6th", reportSql = "${personId}"),
    @template.Report(reportFile="Pregnancy7", reportTitle="7th", reportSql = "${personId}"),
    @template.Report(reportFile="Pregnancy8", reportTitle="8th", reportSql = "${personId}"),
    @template.Report(reportFile="Pregnancy9", reportTitle="9th", reportSql = "${personId}"),
    @template.Report(reportFile="Pregnancy10", reportTitle="10th", reportSql = "${personId}")
})
public class PatientOBGyneExt extends Patient implements Serializable  {
    public static void main(String[] args) {
        view(PatientOBGyneExt.class);
    }
}