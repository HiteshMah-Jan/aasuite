/*
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import java.io.Serializable;
import java.util.Date;

import template.Display;
import template.Displays;
import template.UITemplate;
import constants.UserInfo;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(
    template=template.screen.TemplateTabPage.class,
    title="Assessment", gridCount=8,
    columnSearch = {"dueDate","paymentDate","paymentFor",
    	"amount","overallAmountPaid","overallBalance",
    	"surcharge","surchargePaid","surchargeBalance",
    	"overallAmountDue"},
    sumFooter="2,3,4,5,6,7,8,9",
    orderBy="a.paymentDate",
    tableColumnWidth="55,55,55"
)
@Displays({
    @Display(name="dueDate", label="Due Date"),
    @Display(name="paymentDate", label="Last Payment Date"),
    @Display(name="paymentFor", label="Code", gridFieldWidth=3, width=-1),
    @Display(name="cashier", gridFieldWidth=3, width=-1),
    @Display(name="overallAmountDue", label="Total Due"),
    
    @Display(name="payer", type="Label", gridFieldWidth=7, width=-1),
    @Display(name="adjustedReason", gridFieldWidth=3, width=-1, label="Reason", type="Combo", 
    		modelCombo={"TUITION FEE WAIVED","SURCHARGE WAIVED","RESERVATION FEE","EMPLOYEE DISCOUNT","SCHOLARSHIP","BACK ACCOUNT","OTHERS"}),
    @Display(name="description", gridFieldWidth=3, width=-1),

    @Display(name="amount", label="Amount", labelTop=true, leftLabel="Tuition"),
    @Display(name="discount", label="Discount/Waived", labelTop=true),
    @Display(name="overallAmountPaid", label="Tuition Paid", labelTop=true, type="Label"),
    @Display(name="overallBalance", label="Tuition Balance", labelTop=true, type="Label"),
    
    @Display(name="surcharge", label="Surcharge"),
    @Display(name="discountSurcharge", label="Discount/Waived", hideLabel=true),
    @Display(name="surchargePaid", label="Surcharge Paid", hideLabel=true, type="Label"),
    @Display(name="surchargeBalance", label="Surcharge Balance", hideLabel=true, type="Label")
})
public class PaymentCashierAdjustmentExt extends bean.accounting.PaymentEnrollment implements Serializable {
    public static void main(String[] args) {
        view(PaymentCashierAdjustmentExt.class);
    }

	@Override
	public void save() {
		adjustedBy = UserInfo.getUserName();
		adjustedDate = new Date();
		super.save();
	}
}