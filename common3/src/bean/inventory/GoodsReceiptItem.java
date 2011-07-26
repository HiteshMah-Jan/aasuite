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
		columnSearch = {"itemNo","itemDescription","quantity","unitPrice","total"})
@Displays({
	@Display(name="seq"),
	@Display(name="itemNo"),
	@Display(name="itemDescription"),
	@Display(name="quantity"),
	@Display(name="unitPrice"),
	@Display(name="total")
})
public class GoodsReceiptItem extends AbstractIBean {
	@Id
	public Integer seq;
	public int goodsReceiptId;
	public String itemNo;
	public String itemDescription;
	public double quantity;
	public double unitPrice;
	public double total;
	
	public int getGoodsReceiptId() {
		return goodsReceiptId;
	}
	public void setGoodsReceiptId(int goodsReceiptId) {
		this.goodsReceiptId = goodsReceiptId;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public static void main(String[] args) {
		view(GoodsReceiptItem.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
