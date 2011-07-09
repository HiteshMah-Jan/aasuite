/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.*;
import java.io.Serializable;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template=template.screen.TemplateTabPage.class,
    criteriaSearch={"lastName","firstName"},
    columnSearch={"studentNumber","lastName", "firstName", "middleInitial"}, gridCount=4)
@ActionButtons({
    @ActionButton(label="Print Invoice", name="btnPrintInvoice")
})
@Displays({
        @Display(name="lastName", label="Last Name"),
        @Display(name="firstName", label="First Name"),
        @Display(name="middleInitial", label="Middle Initial"),
        @Display(name="examinationDate"),
        @Display(name="examinationFee", width=100, mandatory=true),
        @Display(name="accountNumber", type = "Combo", modelCombo={"102","103.1", "103.2"}, width=100, mandatory=true),
        @Display(name="rating", type="Label"),

        @Display(name="remarks", width=-1, type="Label")

//        @Display(name="admissionId"),
//        @Display(name="invoiceId"),
//        @Display(name="birthDate"),
//        @Display(name="personId")
})
@Reports({
    @template.Report(reportFile="StudentAdmissionReport", reportTitle="Admission Report", reportSql="${personId}")
})
public class CashierAdmissionExt extends Admission implements Serializable {
    public static void main(String[] args) {
        view(CashierAdmissionExt.class);
    }
}
