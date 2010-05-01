/*
 * PatientMedication.java
 * 
 * Created on Oct 26, 2007, 9:34:49 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.Physician;
import bean.person.*;
import bean.reference.Drug;
import java.io.Serializable;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"drugName", "dose", "startDate","endDate"})
@Displays({
        @Display(name="drugName", linktoBean=Drug.class, label="Medicine", gridFieldWidth=3, width=-1, type="PopSearch", linktoColumns={"genericName","productName"}),
        //@Display(name="patientId", type = "Combo", sqlCombo = "SELECT a FROM Patient a"),
        @Display(name="dose",gridFieldWidth=3,width=-1),
        @Display(name="startDate"),
        @Display(name="endDate"),
        @Display(name="physicianId", type = "PopSearch", linktoBean=Physician.class, linktoColumns={"lastName","firstName"},label="Attending Physician",gridFieldWidth=3,width=-1),
        @Display(name="instruction",type="TextArea",gridFieldWidth=3,width=-1)
        //@Display(name="remarks")
})
public class PatientMedicationClinicExt extends PatientMedication implements Serializable  {
    public static void main(String[] args) {
        view(PatientMedicationClinicExt.class);
    }
}