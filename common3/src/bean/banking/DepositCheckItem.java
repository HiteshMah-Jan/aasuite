package bean.banking;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "DepositCheckItem")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="seq,hoy"),
	@Display(name="depositId"),
	@Display(name="checkDate"),
	@Display(name="checkNumber"),
	@Display(name="bank"),
	@Display(name="branch"),
	@Display(name="accountNo"),
	@Display(name="customer"),
	@Display(name="checkAmount"),
	@Display(name="parentBean"),
	@Display(name="changeSupport"),
	@Display(name="dummyField"),
	@Display(name="myNode"),
	@Display(name="newCache"),
	@Display(name="cacheMap"),
	@Display(name="includeSearch"),
	@Display(name="showImages"),
	@Display(name="showFile"),
	@Display(name="showSubrecords"),
	@Display(name="showCharts")
})

public class DepositCheckItem extends AbstractIBean {
	public static void main(String[] args) {
		view(DepositCheckItem.class);
	}

	@Id
	public Integer seq;
	public int depositId;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date checkDate;
	public String checkNumber;
	public String bank;
	public String branch;
	public String accountNo;
	public String customer;
	public double checkAmount;
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getDepositId() {
		return depositId;
	}

	public void setDepositId(int depositId) {
		this.depositId = depositId;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public double getCheckAmount() {
		return checkAmount;
	}

	public void setCheckAmount(double checkAmount) {
		this.checkAmount = checkAmount;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
