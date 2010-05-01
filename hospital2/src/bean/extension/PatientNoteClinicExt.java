/*
 * PatientNurseNote.java
 * 
 * Created on Nov 5, 2007, 9:18:22 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.Physician;
import bean.person.*;
import java.io.Serializable;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template = TemplateTabPage.class, gridCount=4 ,columnSearch = {"Date","Time"})
@Displays({
        
       // @Display(name="patientId", type="Combo", label="Patient", sqlCombo="SELECT p FROM Patient p"),
        @Display(name="roundDate",label="Date"),
        @Display(name="roundTime",label="Time",type="Time"),
        @Display(name="nurseId", label="Attending Physician", type = "PopSearch", linktoBean=Physician.class, linktoColumns={"lastName","firstName"},gridFieldWidth=3,width=-1),
        @Display(name="weight",width=120),
        @Display(name="height",width=120),
       // @Display(name="severity"),
        @Display(name="bloodPressure",width=120),
        @Display(name="temperature",width=120),
        @Display(name="pulseRate",width=120),
        @Display(name="respiratoryRate",width=120),
        @Display(name="medicineTaken",gridFieldWidth=3,width=-1),
         @Display(name="note",gridFieldWidth=3,width=-1,type="TextArea")
        
       // @Display(name="ie"),
       
})
public class PatientNoteClinicExt extends PatientNurseNote implements Serializable  {
    public static void main(String[] args) {
        view(PatientNoteClinicExt.class);
    }
}