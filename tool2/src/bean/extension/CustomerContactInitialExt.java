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
@UITemplate(showChart=false, template=TemplateTabSinglePage.class, title="Initial",
    gridCount = 4, criteriaSearch = {"companyName","location","customerClass","priority","willingness","customerHeadcount","email"},
    columnSearch = {"nextCallDate","companyName","email","location","customerClass","priority","willingness","customerHeadcount"},
    select="SELECT a FROM CustomerContact a WHERE a.customerType='INITIAL'", autoResizeTable=false)
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
@template.ChildRecords({
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopup.class, title="Contact Person", entity = CustomerContactPerson.class, sql = "SELECT a FROM CustomerContactPerson a WHERE a.customerId=${personId}"),
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopup.class, title="Capacity", entity = CustomerContactCapacity.class, sql = "SELECT a FROM CustomerContactCapacity a WHERE a.customerId=${personId}"),
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListOnly.class, title="Call History", entity = CustomerCallHistory.class, sql = "SELECT a FROM CustomerCallHistory a WHERE a.customerId=${personId} ORDER BY a.seq DESC")
})
@ActionButtons({
    @ActionButton(name="btnSendProposal", label="Send Proposal"),
    @ActionButton(name="btnTodayCallInitial", label="Display Today's Call"),
    @ActionButton(name="btnWeekCallInitial", label="Display This Week Call"),
    @ActionButton(name="btnFollowUp", label="For Follow Up"),
    @ActionButton(name="btnSales", label="For Sales"),
    @ActionButton(name="btnNegative", label="Negative")
})
public class CustomerContactInitialExt extends CustomerContact {
    public static void main(String[] args) {
        view(CustomerContactInitialExt.class);
    }
    
    @Override
    public java.util.Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        int i = 0;
        if (personId!=null) {
            i = personId;
        }
        vec.add(ChartBean.getPieInstance(this, "Customer Calls By Type", "SELECT a.callType, COUNT(a.callType) FROM CustomerCallHistory a WHERE a.customerId="+i+" GROUP BY a.callType"));
        vec.add(ChartBean.getPieInstance(this, location+" By Class","SELECT a.customerClass, COUNT(a.personId) FROM CustomerContact a WHERE a.customerType='INITIAL' AND a.location='"+location+"' GROUP BY a.customerClass"));
        vec.add(ChartBean.getPieInstance(this, "Initial By Call Date","SELECT a.nextCallDate, COUNT(a.personId) FROM CustomerContact a WHERE a.customerType='INITIAL' GROUP BY a.nextCallDate"));
        return vec;
    }   
}
