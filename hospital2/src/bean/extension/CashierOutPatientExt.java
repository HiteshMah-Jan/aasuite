package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import bean.OutPatient;

@UITemplate(template = TemplateTabPage.class, gridCount = 6, 
		columnSearch = {"consultDate", "consultTime", "physicianName", "totalAmount", "overallAmountPaid", "totalBalance"}, sumFooter="3,4,5")
	@Displays({
	        @Display(name="consultDate"),
	        @Display(name="consultTime", type="Time"),
	        @Display(name="overallAmountPaid", label="Total Amount Paid", type="Label"),
	        @Display(name="totalBalance", type="Label")
	})
public class CashierOutPatientExt extends OutPatient {

}
