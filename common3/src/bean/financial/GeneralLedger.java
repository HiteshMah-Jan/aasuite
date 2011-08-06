package bean.financial;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

@Entity
@Table(name = "GeneralLedger")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq"),
	@Display(name="postingPeriod"),
	@Display(name="postedDate"),
	@Display(name="dueDate"),
	@Display(name="accountNumber"),
	@Display(name="accountName"),
	@Display(name="debit"),
	@Display(name="credit"),
	@Display(name="ref1"),
	@Display(name="ref2"),
	@Display(name="ref3"),
	@Display(name="ref4"),
	@Display(name="ref5"),
	@Display(name="ref6"),
	@Display(name="ref7"),
	@Display(name="ref8"),
	@Display(name="ref9"),
	@Display(name="ref10")
})
public class GeneralLedger extends AbstractIBean {
	@Id
	public Integer seq;
	public String postingPeriod;
	public Date postedDate;
	public Date dueDate;
	public String accountNumber;
	public String accountName;
	public double debit;
	public double credit;
	public String ref1;
	public String ref2;
	public String ref3;
	public String ref4;
	public String ref5;
	public String ref6;
	public String ref7;
	public String ref8;
	public String ref9;
	public String ref10;

	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getPostingPeriod() {
		return postingPeriod;
	}
	public void setPostingPeriod(String postingPeriod) {
		this.postingPeriod = postingPeriod;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public String getRef1() {
		return ref1;
	}
	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}
	public String getRef2() {
		return ref2;
	}
	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	public String getRef3() {
		return ref3;
	}
	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}
	public String getRef4() {
		return ref4;
	}
	public void setRef4(String ref4) {
		this.ref4 = ref4;
	}
	public String getRef5() {
		return ref5;
	}
	public void setRef5(String ref5) {
		this.ref5 = ref5;
	}
	public String getRef6() {
		return ref6;
	}
	public void setRef6(String ref6) {
		this.ref6 = ref6;
	}
	public String getRef7() {
		return ref7;
	}
	public void setRef7(String ref7) {
		this.ref7 = ref7;
	}
	public String getRef8() {
		return ref8;
	}
	public void setRef8(String ref8) {
		this.ref8 = ref8;
	}
	public String getRef9() {
		return ref9;
	}
	public void setRef9(String ref9) {
		this.ref9 = ref9;
	}
	public String getRef10() {
		return ref10;
	}
	public void setRef10(String ref10) {
		this.ref10 = ref10;
	}
	public static void main(String[] args) {
		view(GeneralLedger.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
