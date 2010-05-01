/*
 * SentEmail.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import service.util.ChartBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;
import bean.admin.SentEmail;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(showChart=false, template=TemplateSinglePage.class, gridCount = 4, orderBy="a.sentDate DESC",
	criteriaSearch = {"customerId","recipient","sendDate"},
	columnSearch = {"customer","recipient","subject","sendDate"})
@Displays({
    @Display(name="content", type="TextArea", width=600, gridFieldWidth=3, height=400)
})
public class SentEmailExt extends SentEmail {
    @Override
    public java.util.Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        int i = customerId;
        vec.add(ChartBean.getNativePieInstance(this, "Sent By Location","SELECT a.location, COUNT(a.personId) FROM person a, SentEmail b WHERE a.personId=b.customerId GROUP BY a.location ORDER BY COUNT(a.personId) DESC"));
        vec.add(ChartBean.getPieInstance(this, "Sent By Date","SELECT a.sentDate, COUNT(a.seq) FROM SentEmail a GROUP BY a.sentDate ORDER BY a.sentDate DESC"));
        return vec;
    }   
}
