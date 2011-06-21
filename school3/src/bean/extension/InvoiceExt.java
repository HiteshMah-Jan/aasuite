package bean.extension;

import java.io.Serializable;

import javax.persistence.Transient;

import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateSinglePage;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;
import bean.Student;
import bean.accounting.BookSold;
import bean.businesspartners.Customer;
import bean.reference.AccountType;
import bean.reference.PaymentMethod;
import bean.reference.Section;
import bean.sales.OtherPayment;
import bean.sales.Payment;

@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, 
		columnSearch = {"schoolYear", "invoiceDate", "orNumber", "cashier", "payer", "totalAmount"}, autoResizeTable=false)
@Displays({    
        @Display(name="schoolYear", type="Label"),
        @Display(name="invoiceDate", label="Date"),

        @Display(name="actNum", type="Label", label="Account #"),
        @Display(name="orNumber"),
        
        @Display(name="cashier"),        
        @Display(name="payer"),
        
        @Display(name="totalAmount", label="Amount"),
        @Display(name="discount"),
        
        @Display(name="surchargePaid", label="Surcharge"),
        @Display(name="surchargeDiscount", label="Sur. Discount"),
        
//        @Display(name="checkNumber", label="Chk"),
        @Display(name="checkAmount", label="Chk. Amt.", gridFieldWidth=3),
        
        @Display(name="section", type="PopSearch", linktoBean=Section.class, width=-1, gridFieldWidth=3),
        @Display(name="description", gridFieldWidth=5, width=-1),

        @Display(name="accountNumber1", label = "Acc.#", labelTop=true),
        @Display(name="amount1", label = "Amount", labelTop=true),
        @Display(name="bounceCheck1", label="Bounce", type="CheckBox", labelTop=true),
        @Display(name="accountNumber2", labelTop=true, label="Acc.#"),
        @Display(name="amount2", labelTop=true, label="Amount"),
        @Display(name="bounceCheck2", label="Bounce", labelTop=true, type="CheckBox"),
        @Display(name="accountNumber3", hideLabel=true),
        @Display(name="amount3", hideLabel=true),
        @Display(name="bounceCheck3", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber4", hideLabel=true),
        @Display(name="amount4", hideLabel=true),
        @Display(name="bounceCheck4", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber5", hideLabel=true),
        @Display(name="amount5", hideLabel=true),
        @Display(name="bounceCheck5", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber6", hideLabel=true),
        @Display(name="amount6", hideLabel=true),
        @Display(name="bounceCheck6", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber7", hideLabel=true),
        @Display(name="amount7", hideLabel=true),
        @Display(name="bounceCheck7", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber8", hideLabel=true),
        @Display(name="amount8", hideLabel=true),
        @Display(name="bounceCheck8", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber9", hideLabel=true),
        @Display(name="amount9", hideLabel=true),
        @Display(name="bounceCheck9", hideLabel=true, type="CheckBox"),
        @Display(name="accountNumber10", hideLabel=true),
        @Display(name="amount10", hideLabel=true),
        @Display(name="bounceCheck10", hideLabel=true, type="CheckBox")
})
@DisplayGroups({
    @DisplayGroup(gridCount=12, addInfoOnly=true, title="Check", 
    		fields={"bank1","accountNumber1","amount1","bounceCheck1",
    "bank2","accountNumber2","amount2","bounceCheck2",
    "bank3","accountNumber3","amount3","bounceCheck3",
    "bank4","accountNumber4","amount4","bounceCheck4",
    "bank5","accountNumber5","amount5","bounceCheck5",
    "bank6","accountNumber6","amount6","bounceCheck6",
    "bank7","accountNumber7","amount7","bounceCheck7",
    "bank8","accountNumber8","amount8","bounceCheck8",
    "bank9","accountNumber9","amount9","bounceCheck9",
    "bank10","accountNumber10","amount10","bounceCheck10"
    })
})
@template.ChildRecords(
		info = {@ParentAddInfo(title = "Check", gridCount = 4,displayFields = {})},
		value = {
			    @template.ChildRecord(title="Tuition Details", entity = PaymentCashierPaymentInvoiceExt.class, sql = "SELECT a FROM Payment a WHERE a.invoiceId=${seq}", fieldMapping={"seq","invoiceId","orType","orType","orNumber","orNumber"}),
			    @template.ChildRecord(title="Other Payment", entity = OtherPayment.class, sql = "SELECT a FROM OtherPayment a WHERE a.invoiceId=${seq}", fieldMapping={"seq","invoiceId","orType","orType","orNumber","orNumber"}),
			    @template.ChildRecord(title="Books", entity = BookSold.class, sql = "SELECT a FROM BookSold a WHERE a.invoiceId=${seq}", fieldMapping={"seq","invoiceId","orType","orType","orNumber","orNumber"})
			}
)

public class InvoiceExt extends bean.accounting.Invoice implements Serializable {
	public String actNum;
	@Override
	public String getOrType() {
		actNum = (orType!=null && orType.equals("A"))?"2":"1";
		return super.getOrType();
	}

	public String getActNum() {
		return actNum;
	}
	
	public void setActNum(String actNum) {
		this.actNum = actNum;
	}

	public static void main(String[] args) {
        view(InvoiceExt.class);
    }
}