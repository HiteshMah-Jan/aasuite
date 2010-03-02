/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.accounting.*;
import template.*;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = template.screen.TemplateTabSinglePage.class, gridCount = 8, columnSearch = {"paidBy", "amount", "paymentDate", "invoiceId"})
@ChildRecords({
    @ChildRecord(entity=PaymentDetail.class, sql="SELECT a FROM PaymentDetail a WHERE a.paymentId=${paymentId}", title="Payment Detail"),
    @ChildRecord(entity=PaymentPartialDetail.class, sql="SELECT a FROM PartialPaymentDetail a WHERE a.paymentId=${paymentId}", title="Partial Payment Detail")
})
@Displays({
        @Display(name="paidBy"),
        @Display(name="amount", mergeFields={"paid","invoiceId"}),
        @Display(name="paid"),
        @Display(name="invoiceId", type="Label"),
        @Display(name="paymentDate"),
        @Display(name="dueDate"),
        @Display(name="description", gridFieldWidth=7)
})
public class AwbPaymentExt extends Payment {

}
