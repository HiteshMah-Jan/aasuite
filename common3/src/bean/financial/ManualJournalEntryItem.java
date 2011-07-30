package bean.financial;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "ManualJournalEntryItem")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, 
		columnSearch = {"accountNumber","accountName","debit","credit"}, sumFooter="2,3")
@Displays({
	@Display(name="accountNumber", type="PopSearch", linktoBean=AccountChart.class),
	@Display(name="accountName", enabled=false),
	@Display(name="debit"),
	@Display(name="credit")
})
public class ManualJournalEntryItem extends AbstractIBean {
	@Id
	public Integer seq;
	public int journalEntryId;
	public String accountNumber;
	public String accountName;
	public double debit;
	public double credit;
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getJournalEntryId() {
		return journalEntryId;
	}

	public void setJournalEntryId(int journalEntryId) {
		this.journalEntryId = journalEntryId;
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

	public static void main(String[] args) {
		view(ManualJournalEntryItem.class);
	}
	
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
