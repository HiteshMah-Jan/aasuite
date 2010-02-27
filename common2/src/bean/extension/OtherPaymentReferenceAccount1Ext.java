package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import bean.reference.OtherPaymentReference;

@UITemplate(gridCount = 6, columnSearch = {"code","description","amount","orType"}, select="SELECT a FROM OtherPaymentReference a WHERE a.orType='N' ")
@Displays({
       // @Display(name="id"),
        @Display(name="code"),
        @Display(name="orType", type="Label", label="Acct Type"),
        @Display(name="amount"),
        @Display(name="description",gridFieldWidth=5,width=-1)
})
public class OtherPaymentReferenceAccount1Ext extends OtherPaymentReference {
	public OtherPaymentReferenceAccount1Ext() {
		orType = "N";
	}

	@Override
	public String addWhere() {
		return " WHERE a.orType='N' ";
	}
	
	@Override
	public String popupSearch(String criteria) {
		return "SELECT a FROM OtherPaymentReference a "+addWhere();
	}
	
}
