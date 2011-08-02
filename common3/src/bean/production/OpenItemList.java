package bean.production;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "OpenItemList")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"docNo","type","status","productNo","plannedQuantity","orderDate","dueDate"})
@Displays({
	@Display(name="openDocuments",type="Combo", modelCombo={"salesQuotation","salesOrders","deliveries","returns","aRDownpaymentsUnpaid","aRDownPaymentsNotYetFullyApplied","aRInvoice","aRCreditMemos","aRReserveInvoicesUnpaid","aRReserveInvoicesNotYetDelivered","purchaseOrders","goodRecieptPOs","goodsReurt","aPDownPaymentsUnpaid","aPDownPaymentsNotYetFullyApplied","aPInvoices","aPCreditMemos","aPReserveInvoicesUnpaid","aPDownPaymentsNotYetDelivered","productionOrders","missingItems"})
})
public class OpenItemList extends AbstractIBean{
@Id	
public String openDocuments;

	public String getOpenDocuments() {
	return openDocuments;
}

public void setOpenDocuments(String openDocuments) {
	openDocuments = openDocuments;
}

	public static void main(String[] args) {
		view(OpenItemList.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
