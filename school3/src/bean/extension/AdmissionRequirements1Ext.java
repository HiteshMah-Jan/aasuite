/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Admission;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(template=template.screen.TemplateTabPage.class,showChart=true,criteriaSearch={"lastName","firstName"},columnSearch={"lastName", "firstName", "middleInitial", "examinationDate", "remarks"}, gridCount=2)
@Displays({
        @Display(name="threeColoredIdPictures",label="Three (3) colored ID Pictures (1x1)"),
        @Display(name="birthCertificateNsoCopy",label="Birth Certificate (NSO Copy)"),
        @Display(name="baptismalCertificate"),
        @Display(name="latestReportCard",label="Latest Report Card (3rd Quarter)"),
        @Display(name="letterOfRecommendation",label="Letter of Recommendation from the principal / guidance counselor")
//        @Display(name="copyOfAcr",label="Copy of ACR"),
//        @Display(name="passportForVerification"),
//        @Display(name="completeScholasticRecords")

//        @Display(name="admissionId"),
//        @Display(name="invoiceId"),
//        @Display(name="birthDate"),
//        @Display(name="personId")
})

public class AdmissionRequirements1Ext extends Admission {
}
