/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import service.util.ChartBean;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
import bean.CustomerCallHistory;
import bean.CustomerContact;
import bean.CustomerContactCapacity;
import bean.CustomerContactPerson;
import bean.CustomerContract;
import bean.CustomerPayment;

/**
 *
 * @author Entokwaa
 */
@UITemplate(showChart=false, template=TemplateTabSinglePage.class, title="Accounts", showFiles=true,
    gridCount = 4, criteriaSearch = {"companyName","location","customerClass","priority","willingness","customerHeadcount","email"},
    columnSearch = {"nextCallDate","companyName","email","location","customerClass","priority","willingness","customerHeadcount"},
    select="SELECT a FROM CustomerContact a WHERE a.customerType='SALES ACCOUNT'", autoResizeTable=false)
@Displays({
    @Display(name="companyName", enabled=false),
    @Display(name="shortName", label="Primary Contact"),

    @Display(name="customerClass", enabled=false),
    @Display(name="location", enabled=false),
    @Display(name="customerHeadcount"),

    @Display(name="willingness", type="NumberCombo", startCount=1, endCount=5, width=50),
    @Display(name="priority", type="NumberCombo", startCount=1, endCount=5, width=50),
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
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopupDownButton.class, title="Contact Person", entity = CustomerContactPerson.class, sql = "SELECT a FROM CustomerContactPerson a WHERE a.customerId=${personId}"),
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopupDownButton.class, title="Capacity", entity = CustomerContactCapacity.class, sql = "SELECT a FROM CustomerContactCapacity a WHERE a.customerId=${personId}"),
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListOnly.class, title="Call History", entity = CustomerCallHistory.class, sql = "SELECT a FROM CustomerCallHistory a WHERE a.customerId=${personId} ORDER BY a.seq DESC"),
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopupDownButton.class, title="Contract", entity = CustomerContract.class, sql = "SELECT a FROM CustomerContract a WHERE a.customerId=${personId}"),
    @template.ChildRecord(fieldMapping={"personId","customerId"}, template=template.screen.ChildTemplateListPopupDownButton.class, title="Payment", entity = CustomerPayment.class, sql = "SELECT a FROM CustomerPayment a WHERE a.customerId=${personId}")
})
@ActionButtons({
    @ActionButton(name="btnSendProposal", label="Send Proposal"),
    @ActionButton(name="btnTodayCallSales", label="Display Today's Sales"),
    @ActionButton(name="btnWeekCallSales", label="Display This Week Sales"),
    @ActionButton(name="btnNewOpportunity", label="New Opportunity")
})
public class CustomerContactSalesExt extends CustomerContact {
    public static void main(String[] args) {
        view(CustomerContactSalesExt.class);
    }
    
    @Override
    public java.util.Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        int i = 0;
        if (personId!=null) {
            i = personId;
        }
        vec.add(ChartBean.getPieInstance(this, "Customer Calls By Type", "SELECT a.callType, COUNT(a.callType) FROM CustomerCallHistory a WHERE a.customerId="+i+" GROUP BY a.callType"));
        vec.add(ChartBean.getPieInstance(this, location+" By Class","SELECT a.customerClass, COUNT(a.personId) FROM CustomerContact a WHERE a.customerType='SALES ACCOUNT' AND a.location='"+location+"' GROUP BY a.customerClass"));
        vec.add(ChartBean.getPieInstance(this, "Sales By Call Date","SELECT a.nextCallDate, COUNT(a.personId) FROM CustomerContact a WHERE a.customerType='SALES ACCOUNT' GROUP BY a.nextCallDate"));
        return vec;
    }   
}
