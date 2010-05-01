/*
 * Blood.java
 *
 * Created on Oct 26, 2007, 9:34:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PatientLaboratory")
@DiscriminatorValue(value = "RENAL SCAN")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, columnSearch = {"radiologist", "patient"})
@Displays({
        @Display(name="radiologist"),
        @Display(name="hasPreviousCtscan"),
        @Display(name="hasPreviousXray"),
        @Display(name="hasPreviousUltrasound"),
        @Display(name="radiologyType"),
        @Display(name="dicom"),
        @Display(name="seq"),
        @Display(name="patient"),
        @Display(name="labDate"),
        @Display(name="laboratoryTest"),
        @Display(name="requestingPhysician"),
        @Display(name="commendation"),
        @Display(name="labresult"),
        @Display(name="amount"),
        @Display(name="findings"),
        @Display(name="impression"),
        @Display(name="exam"),
        @Display(name="addendum"),
        @Display(name="sonographer"),
        @Display(name="labinput"),
        @Display(name="laboutput"),
        @Display(name="er_a"),
        @Display(name="er_wic"),
        @Display(name="bilrubin"),
        @Display(name="alkPhos"),
        @Display(name="sgot"),
        @Display(name="sgpt"),
        @Display(name="amylase"),
        @Display(name="bun"),
        @Display(name="creatinine"),
        @Display(name="requestDate"),
        @Display(name="requestTime"),
        @Display(name="finishDate"),
        @Display(name="opdId")
})
public class PatientRenalScan extends PatientRadiology implements Serializable {
}
