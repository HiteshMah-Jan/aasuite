/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.CustomerCallHistory;
import bean.CustomerContact;
import bean.CustomerContactCapacity;
import bean.CustomerContactPerson;
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
@UITemplate(showChart=false, template=TemplateTabSinglePage.class, title="All Customers",
    gridCount = 4, criteriaSearch = {"companyName","location","customerClass","priority","willingness","customerHeadcount","email"}, 
    columnSearch = {"companyName","email","location","customerClass","customerType","nextCallDate","priority","willingness","customerHeadcount"}, autoResizeTable=false)
@Displays({
    @Display(name="companyName",mandatory=true,gridFieldWidth=3,width=-1),
    @Display(name="customerClass",width=250),
    @Display(name="location", type="PopSearch", linktoBean=bean.Location.class, linktoColumns={"location","area"}, width=250),
    @Display(name="contactPerson",width=-1),
    @Display(name="contactNumber",width=-1),
    @Display(name="email",width=-1,upCase=false),
    @Display(name="customerHeadcount"),
    @Display(name="willingness", type="NumberCombo", startCount=1, endCount=5, width=50),
    @Display(name="priority", type="NumberCombo", startCount=1, endCount=5, width=50),
    @Display(name="address",gridFieldWidth=3,width=-1)
})
@ActionButtons({
    @ActionButton(name="btnSendProposal", label="Send Proposal")
})
@template.ChildRecords({
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopup.class, title="Contact Person", entity = CustomerContactPerson.class, sql = "SELECT a FROM CustomerContactPerson a WHERE a.customerId=${personId}"),
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopup.class, title="Capacity", entity = CustomerContactCapacity.class, sql = "SELECT a FROM CustomerContactCapacity a WHERE a.customerId=${personId}"),
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListOnly.class, title="Call History", entity = CustomerCallHistory.class, sql = "SELECT a FROM CustomerCallHistory a WHERE a.customerId=${personId} ORDER BY a.seq DESC")
})
public class CustomerContactAllExt extends CustomerContact {
    public static void main(String[] args) {
        view(CustomerContactAllExt.class);
    }
    
    @Override
    public java.util.Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        vec.add(ChartBean.getNativePieInstance(this, customerClass+" By Location", "SELECT a.location, COUNT(a.personId) FROM Person a WHERE a.customerType IS NULL AND a.customerClass='"+customerClass+"' GROUP BY a.location ORDER BY COUNT(a.personId) DESC"));
        vec.add(ChartBean.getPieInstance(this, location+" By Class","SELECT a.customerClass, COUNT(a.personId) FROM CustomerContact a WHERE a.customerType IS NULL AND a.location='"+location+"' GROUP BY a.customerClass"));
        return vec;
    }   
}
