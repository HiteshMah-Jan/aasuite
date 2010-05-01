/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.CustomerContact;
import service.util.ChartBean;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Entokwaa
 */
@UITemplate(showChart=false, template=TemplateTabSinglePage.class, title="Send Proposal",
    gridCount = 4, criteriaSearch = {"companyName","location","customerClass","customerHeadcount","email"}, 
    columnSearch = {"companyName","email","location","customerClass","salesPerson","customerHeadcount"},
    select="SELECT a FROM CustomerContact a WHERE a.sendProposal=true", autoResizeTable=false)
@Displays({
    @Display(name="companyName",type="Label",gridFieldWidth=3),
    @Display(name="customerClass",width=250),
    @Display(name="email",upCase=false),
    @Display(name="fax"),
    @Display(name="location", type="Label"),
    @Display(name="contactPerson",type="Label"),
    @Display(name="contactNumber",type="Label"),
    @Display(name="customerHeadcount",type="Label"),
    @Display(name="address",gridFieldWidth=3,type="Label")
})
@ActionButtons({
    @ActionButton(name="btnRemoveFromList", label="Remove From List"),
    @ActionButton(name="btnFollowUp", label="For Follow Up"),
    @ActionButton(name="btnSendToAll", label="Send To All"),
    @ActionButton(name="btnSendToSelected", label="Send To Selected"),
    @ActionButton(name="btnSendToFax", label="Send To Printer/Fax"),
    @ActionButton(name="btnPresentation", label="Presentation")
})
public class CustomerContactSendProposalExt extends CustomerContact {
    public static void main(String[] args) {
        view(CustomerContactSendProposalExt.class);
    }
    
    @Override
    public java.util.Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        int i = 0;
        if (personId!=null) {
            i = personId;
        }
        vec.add(ChartBean.getPieInstance(this, "Customer Calls By Type", "SELECT a.callType, COUNT(a.callType) FROM CustomerCallHistory a WHERE a.customerId="+i+" GROUP BY a.callType"));
        vec.add(ChartBean.getPieInstance(this, location+" By Class","SELECT a.customerClass, COUNT(a.personId) FROM CustomerContact a WHERE a.sendProposal=true AND a.location='"+location+"' GROUP BY a.customerClass"));
        vec.add(ChartBean.getPieInstance(this, "Sending By Call Date","SELECT a.nextCallDate, COUNT(a.personId) FROM CustomerContact a WHERE a.sendProposal=true GROUP BY a.nextCallDate"));
        return vec;
    }   
}
