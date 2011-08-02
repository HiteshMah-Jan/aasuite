package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "GoodsReceiptItem")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, 
		columnSearch = {"number","itemNo","itemDescription","quantity","unitPrice","total"})
@Displays({
	
})
public class GoodsReceiptItem extends AbstractIBean {
	@Id
	
	public static void main(String[] args) {
		view(GoodsReceiptItem.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
