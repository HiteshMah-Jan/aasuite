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
@UITemplate(showChart=false, template=TemplateTabSinglePage.class, title="Negative",
    gridCount = 4, criteriaSearch = {"companyName","location","customerClass","customerHeadcount","email"}, 
    columnSearch = {"companyName","email","location","customerClass","salesPerson","customerHeadcount"},
    select="SELECT a FROM CustomerContact a WHERE a.customerType='NEGATIVE'", autoResizeTable=false)
@Displays({
    @Display(name="companyName",type="Label",gridFieldWidth=3),
    @Display(name="customerClass", type="Label"),
    @Display(name="location", type="Label"),
    @Display(name="contactPerson",type="Label"),
    @Display(name="contactNumber",type="Label"),
    @Display(name="email",width=-1,upCase=false),
    @Display(name="customerHeadcount",type="Label"),
    @Display(name="address",gridFieldWidth=3,type="Label")
})
@ActionButtons({
    @ActionButton(name="btnFollowUp", label="For Follow Up")
})
@template.ChildRecords({
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopup.class, title="Contact Person", entity = CustomerContactPerson.class, sql = "SELECT a FROM CustomerContactPerson a WHERE a.customerId=${personId}"),
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopup.class, title="Capacity", entity = CustomerContactCapacity.class, sql = "SELECT a FROM CustomerContactCapacity a WHERE a.customerId=${personId}"),
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListOnly.class, title="Call History", entity = CustomerCallHistory.class, sql = "SELECT a FROM CustomerCallHistory a WHERE a.customerId=${personId} ORDER BY a.seq DESC")
})
public class CustomerContactNegativeExt extends CustomerContact {
    public static void main(String[] args) {
        view(CustomerContactNegativeExt.class);
    }
    
    @Override
    public java.util.Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        int i = 0;
        if (personId!=null) {
            i = personId;
        }
        vec.add(ChartBean.getPieInstance(this, "Customer Calls By Type", "SELECT a.callType, COUNT(a.callType) FROM CustomerCallHistory a WHERE a.customerId="+i+" GROUP BY a.callType"));
        vec.add(ChartBean.getPieInstance(this, location+" By Class","SELECT a.customerClass, COUNT(a.personId) FROM CustomerContact a WHERE a.customerType='NEGATIVE' AND a.location='"+location+"' GROUP BY a.customerClass"));
        vec.add(ChartBean.getPieInstance(this, "Negative By Call Date","SELECT a.nextCallDate, COUNT(a.personId) FROM CustomerContact a WHERE a.customerType='NEGATIVE' GROUP BY a.nextCallDate"));
        return vec;
    }   
}
