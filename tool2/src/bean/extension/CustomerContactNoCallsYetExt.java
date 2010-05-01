/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

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
@UITemplate(showChart=false, template=TemplateTabSinglePage.class, title="New",
    gridCount = 4, criteriaSearch = {"companyName","location","customerClass","customerHeadcount","email"}, 
    columnSearch = {"companyName","email","location","customerClass","salesPerson","customerHeadcount"},
    select="SELECT a FROM CustomerContact a WHERE a.customerType is null", autoResizeTable=false)
@Displays({
    @Display(name="companyName", enabled=false),
    @Display(name="shortName", label="Primary Contact", enabled=false),

    @Display(name="customerClass", enabled=false),
    @Display(name="location", enabled=false),
    @Display(name="customerHeadcount"),

    @Display(name="willingness", type="NumberCombo", startCount=1, endCount=5),
    @Display(name="priority", type="NumberCombo", startCount=1, endCount=5),
    @Display(name="possibleRevenue"),

    @Display(name="contactPerson"),
    @Display(name="contactNumber"),
    @Display(name="email",upCase=false),

    @Display(name="address"),
    @Display(name="fax"),
    @Display(name="nextCallDate"),

    @Display(name="note", width=-1, gridFieldWidth=3)
})
@ActionButtons({
    @ActionButton(name="btnSendProposal", label="Send Proposal"),
    @ActionButton(name="btnInitialCall", label="For Initial Call")
})
@template.ChildRecords({
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopup.class, title="Contact Person", entity = CustomerContactPerson.class, sql = "SELECT a FROM CustomerContactPerson a WHERE a.customerId=${personId}"),
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopup.class, title="Capacity", entity = CustomerContactCapacity.class, sql = "SELECT a FROM CustomerContactCapacity a WHERE a.customerId=${personId}")
})
public class CustomerContactNoCallsYetExt extends CustomerContact {
    public static void main(String[] args) {
        view(CustomerContactNoCallsYetExt.class);
    }
    
    @Override
    public java.util.Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        int i = 0;
        if (personId!=null) {
            i = personId;
        }
        vec.add(ChartBean.getNativePieInstance(this, customerClass+" By Location", "SELECT a.location, COUNT(a.personId) FROM Person a WHERE a.customerType IS NULL AND a.customerClass='"+customerClass+"' GROUP BY a.location ORDER BY COUNT(a.personId) DESC"));
        vec.add(ChartBean.getPieInstance(this, location+" By Class","SELECT a.customerClass, COUNT(a.personId) FROM CustomerContact a WHERE a.customerType IS NULL AND a.location='"+location+"' GROUP BY a.customerClass"));
        return vec;
    }   
}
