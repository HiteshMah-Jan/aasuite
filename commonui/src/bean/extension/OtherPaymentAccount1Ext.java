package bean.extension;

import bean.Person;
import constants.UserInfo;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;
import bean.reference.OtherPaymentReference;
import bean.sales.OtherPayment;

@UITemplate(template = TemplateSinglePage.class, gridCount = 4, 
	    columnSearch = {"payer","cashier","schoolYear","cashier","totalAmount"}, 
	    orderBy="a.schoolYear DESC, a.cashier")
	@Displays({
	    @Display(name = "personId", type = "PopSearch", linktoBean = Person.class, width = -1, gridFieldWidth = 3, label = "Payee"),
	    @Display(name = "payer",label="Payor Name",gridFieldWidth=3,width=-1),
        @Display(name = "payerCategory",label="Category",gridFieldWidth=3,width=-1, type="Combo",
        		modelCombo={"STUDENT","EMPLOYEE","CLIENT","LIBRARY","BOOKSTORE","PARENT","JVS","EVA","ESS MAIN","ESS SOUTH","OTHERS"}),
	    @Display(name = "totalAmount"),
	    @Display(name = "cashier", type="Label"),
//	    @Display(name = "orType", label = "Acc", type="Label"),
	    @Display(name = "orNumber", type="Label"),
	    @Display(name = "schoolYear", type="Label"),
	    @Display(name = "orDate", type="Label", gridFieldWidth=3),
	    @Display(name = "completeDesc", label="Add. Info.", gridFieldWidth=3, width=-1),

	    @Display(name = "paymentCode1",width=300, type = "PopSearch", linktoBean = OtherPaymentReferenceAccount1Ext.class, label = "Payment Code", labelTop = true),
	    @Display(name = "paymentAmount1", label = "Amount Paid", labelTop = true),
	    @Display(name = "paymentCode2",width=300, type = "PopSearch", linktoBean = OtherPaymentReferenceAccount1Ext.class, hideLabel = true),
	    @Display(name = "paymentAmount2", hideLabel = true),
	    @Display(name = "paymentCode3",width=300, type = "PopSearch", linktoBean = OtherPaymentReferenceAccount1Ext.class, hideLabel = true),
	    @Display(name = "paymentAmount3", hideLabel = true),
	    @Display(name = "paymentCode4",width=300, type = "PopSearch", linktoBean = OtherPaymentReferenceAccount1Ext.class, hideLabel = true),
	    @Display(name = "paymentAmount4", hideLabel = true),
	    @Display(name = "paymentCode5",width=300, type = "PopSearch", linktoBean = OtherPaymentReferenceAccount1Ext.class, hideLabel = true),
	    @Display(name = "paymentAmount5", hideLabel = true),
	    @Display(name = "paymentCode6",width=300, type = "PopSearch", linktoBean = OtherPaymentReferenceAccount1Ext.class, hideLabel = true),
	    @Display(name = "paymentAmount6", hideLabel = true),
	    @Display(name = "paymentCode7",width=300, type = "PopSearch", linktoBean = OtherPaymentReferenceAccount1Ext.class, hideLabel = true),
	    @Display(name = "paymentAmount7", hideLabel = true),
	    @Display(name = "paymentCode8",width=300, type = "PopSearch", linktoBean = OtherPaymentReferenceAccount1Ext.class, hideLabel = true),
	    @Display(name = "paymentAmount8", hideLabel = true),
	    @Display(name = "paymentCode9",width=300, type = "PopSearch", linktoBean = OtherPaymentReferenceAccount1Ext.class, hideLabel = true),
	    @Display(name = "paymentAmount9", hideLabel = true),
	    @Display(name = "paymentCode10",width=300, type = "PopSearch", linktoBean = OtherPaymentReferenceAccount1Ext.class, hideLabel = true),
	    @Display(name = "paymentAmount10", hideLabel = true)
	})
	@DisplayGroups({
	    @DisplayGroup(gridCount=4, title="Other Payments", fields={
	        "paymentCode1", "paymentAmount1",
	        "paymentCode2", "paymentAmount2",
	        "paymentCode3", "paymentAmount3",
	        "paymentCode4", "paymentAmount4",
	        "paymentCode5", "paymentAmount5",
	        "paymentCode6", "paymentAmount6",
	        "paymentCode7", "paymentAmount7",
	        "paymentCode8", "paymentAmount8",
	        "paymentCode9", "paymentAmount9",
	        "paymentCode10", "paymentAmount10"
	    })
	})
public class OtherPaymentAccount1Ext extends OtherPayment {
    public OtherPaymentAccount1Ext() {
    	orType = "N";
    }
}
