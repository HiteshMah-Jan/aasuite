package bean.extension;

import template.Display;
import template.Displays;
import template.UITemplate;
import util.BeanUtil;
import bean.reference.OtherPaymentReference;

@UITemplate(gridCount = 6, columnSearch = {"code","description","amount","orType"}, 
		select="SELECT a FROM OtherPaymentReference a WHERE a.orType='A' ")
@Displays({
       // @Display(name="id"),
        @Display(name="code"),
        @Display(name="orType", type="Label", label="Acct Type"),
        @Display(name="amount"),
        @Display(name="description",gridFieldWidth=5,width=-1)
})
public class OtherPaymentReferenceAccount2Ext extends OtherPaymentReference {
	public OtherPaymentReferenceAccount2Ext() {
		orType = "A";
	}

	@Override
	public String addWhere() {
		return " WHERE a.orType='A' ";
	}

	@Override
	public String popupSearch(String criteria) {
		return BeanUtil.concat("SELECT a FROM OtherPaymentReference a ",addWhere());
	}
	
}
