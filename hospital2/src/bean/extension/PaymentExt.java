/*
 * Payment.java
 *
 * Created on Jan 17, 2008, 9:27:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.Patient;
import bean.accounting.Payment;
import java.util.Vector;
import service.util.ChartBean;
import template.*;
import template.screen.TemplateTabPage;
import util.DateUtil;

/**
 *
 * @author pogi
 */
@UITemplate(showChart=true, template = TemplateTabPage.class, gridCount = 6, columnSearch = {"paidBy", "amount", "paymentDate"})
@Displays({
        @Display(name="paymentDate"),
        @Display(name="amount"),
        @Display(name="invoiceId",type="Label"),
        @Display(name="paidBy", linktoBean=Patient.class, label="Patient", gridFieldWidth=5, width=-1, type="PopSearch", linktoColumns={"lastName","firstName"}),
        @Display(name="description", type="TextArea",gridFieldWidth=5,width=-1)
})
@ActionButtons({
    @ActionButton(name="btnPrintInvoice", label="Print Invoice", parentOnly=false),
    @ActionButton(name="btnViewGL", label="View GL", parentOnly=false)
})
public class PaymentExt extends Payment {
    @Override
    public Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        vec.add(ChartBean.getNativePieInstance(this,"Total Payments","SELECT "+DateUtil.getSQLMonthName("a.paymentDate")+", SUM(a.amount) FROM Payment a GROUP BY "+DateUtil.getSQLMonthName("a.paymentDate")));
        return vec;
    }
}
