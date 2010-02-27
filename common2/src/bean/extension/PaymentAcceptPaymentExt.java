package bean.extension;

import bean.accounting.PaymentLineItem;
import java.io.Serializable;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"accountNumber1","amount1"})
@Displays({
    	@Display(name="amount", type="Label"),
    	@Display(name="discount", type="Label"),

    	@Display(name="accountNumber1", label = "Acc.#", labelTop=true),
        @Display(name="amount1", label = "Amount", labelTop=true),
//        @Display(name="bounceCheck1", label="Bounce?", labelTop=true),
        //        @Display(name="bank2", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber2", hideLabel=true, label="Acc.#"),
        @Display(name="amount2", hideLabel=true, label="Amount"),
//        @Display(name="bounceCheck2", label="bounceCheck", hideLabel=true),
       
        //        @Display(name="bank3", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber3", hideLabel=true),
        @Display(name="amount3", hideLabel=true),
//        @Display(name="bounceCheck3", hideLabel=true),
        
        @Display(name="accountNumber4", hideLabel=true),
        @Display(name="amount4", hideLabel=true),
//        @Display(name="bounceCheck4", hideLabel=true),
       //        @Display(name="bank5", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber5", hideLabel=true),
        @Display(name="amount5", hideLabel=true),
//        @Display(name="bounceCheck5", hideLabel=true),
       //        @Display(name="bank6", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber6", hideLabel=true),
        @Display(name="amount6", hideLabel=true),
//        @Display(name="bounceCheck6", hideLabel=true),
        //        @Display(name="bank7", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber7", hideLabel=true),
        @Display(name="amount7", hideLabel=true),
//        @Display(name="bounceCheck7", hideLabel=true),
       //        @Display(name="bank8", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber8", hideLabel=true),
        @Display(name="amount8", hideLabel=true),
//        @Display(name="bounceCheck8", hideLabel=true),
       //        @Display(name="bank9", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber9", hideLabel=true),
        @Display(name="amount9", hideLabel=true),
//        @Display(name="bounceCheck9", hideLabel=true),
       //        @Display(name="bank10", hideLabel=true, type="PopSearch", linktoBean=Bank.class),
        @Display(name="accountNumber10", hideLabel=true),
        @Display(name="amount10", hideLabel=true)
//        @Display(name="bounceCheck10", hideLabel=true)
})
public class PaymentAcceptPaymentExt extends bean.accounting.Payment {
    public static void main(String[] args) {
        view(PaymentAcceptPaymentExt.class);
    }
}