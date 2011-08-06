package bean.accounting;

import javax.persistence.Entity;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

@Entity
@Table(name = "SchoolAccountMapping")
@UITemplate(template=TemplateSinglePage.class, gridCount = 2, 
		columnSearch = {"miscAccount","discountAccount","totalUnitAccount","bookSoldAccount","collectionAccount"})
@Displays({
	@Display(name="miscAccount"),
	@Display(name="discountAccount"),
	@Display(name="totalUnitAccount"),
	@Display(name="bookSoldAccount"),
	@Display(name="collectionAccount")
})
public class SchoolAccountMapping extends AbstractIBean {
	public Integer seq;
	public String miscAccount;
	public String discountAccount;
	public String totalUnitAccount;
	public String bookSoldAccount;
	public String collectionAccount;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getMiscAccount() {
		return miscAccount;
	}
	public void setMiscAccount(String miscAccount) {
		this.miscAccount = miscAccount;
	}
	public String getDiscountAccount() {
		return discountAccount;
	}
	public void setDiscountAccount(String discountAccount) {
		this.discountAccount = discountAccount;
	}
	public String getTotalUnitAccount() {
		return totalUnitAccount;
	}
	public void setTotalUnitAccount(String totalUnitAccount) {
		this.totalUnitAccount = totalUnitAccount;
	}
	public String getBookSoldAccount() {
		return bookSoldAccount;
	}
	public void setBookSoldAccount(String bookSoldAccount) {
		this.bookSoldAccount = bookSoldAccount;
	}
	public String getCollectionAccount() {
		return collectionAccount;
	}
	public void setCollectionAccount(String collectionAccount) {
		this.collectionAccount = collectionAccount;
	}
	public static void main(String[] args) {
		view(SchoolAccountMapping.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
