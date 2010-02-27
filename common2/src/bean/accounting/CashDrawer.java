/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

import bean.accounting.Invoice;
import bean.accounting.Payment;
import constants.UserInfo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import util.DBClient;
import util.DateUtil;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CashDrawer")
@UITemplate(template=template.screen.TemplateTabPage.class,columnSearch={"branch", "station"}, gridCount=2)

@Displays({
        @Display(name="cash", width = -1, type="Label"),
        @Display(name="checkAmount", width = -1, type="Label", label="Check"),
        @Display(name="credit", width = -1, type="Label"),
        @Display(name="total", width = 80, type="Label")        
})


public class CashDrawer extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "cash")
    public double cash;
    @Column(name = "checkAmount")
    public double checkAmount;
    @Column(name = "credit")
    public double credit;
    @Column(name = "cancelAmount")
    public double cancelAmount;
    @Column(name = "total")
    public double total;
    @Column(name = "branch")
    public String branch;
    @Column(name = "userStation")
    public String userStation;
    @Column(name = "businessDate")
    @Temporal(value = TemporalType.DATE)
    public Date businessDate;
    @Column(name = "lastUsedTime")
    public String lastUsedTime;
    @Column(name = "userId")
    public int userId;
    @Column(name = "cashier")
    public String cashier;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "branch","businessDate", "cashier");
    }
    
    public double getCancelAmount() {
		return cancelAmount;
	}

	public void setCancelAmount(double cancelAmount) {
		this.cancelAmount = cancelAmount;
	}

	public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(double checkAmount) {
        this.checkAmount = checkAmount;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getLastUsedTime() {
        return lastUsedTime;
    }

    public void setLastUsedTime(String lastUsedTime) {
        this.lastUsedTime = lastUsedTime;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserStation() {
        return userStation;
    }

    public void setUserStation(String userStation) {
        this.userStation = userStation;
    }

    public static List<Invoice> extractDailyTransaction(Date d) {
        String cashier = UserInfo.getUserName();
        String sql = "SELECT a FROM Invoice a WHERE a.cashier='"+cashier+"' AND a.orDate='"+DateUtil.formatDateToSql(d)+"'";
        return DBClient.getList(sql);
    }
    
    public static List<Invoice> extractDailyTransaction() {
        return extractDailyTransaction(constants.Constants.useDate);
    }

    public static CashDrawer updateCashDrawer() {
        double cash = 0;
        double check = 0;
        double credit = 0;
        double cancelled = 0;
        List<Invoice> lst = extractDailyTransaction();
        for (Invoice payment : lst) {
        	if (payment.isCancelled()) {
        		cancelled += payment.allAmount;
        		continue;
        	}
            cash += (payment.totalAmount-payment.creditAmount-payment.checkAmount);
            credit += payment.creditAmount;
            check += payment.checkAmount;
        }
        CashDrawer drawer = (CashDrawer) DBClient.getFirstRecord("SELECT a FROM CashDrawer a WHERE a.businessDate='"+DateUtil.formatDateToSql(constants.Constants.useDate)+"' AND a.cashier='"+UserInfo.getUserName()+"'");
        if (drawer==null) {
            drawer = new CashDrawer();
            drawer.businessDate = constants.Constants.useDate;
            drawer.cashier = UserInfo.getUserName();
            try {
                drawer.userId = UserInfo.loginUser.getPersonId();
            }
            catch (Exception e) {
            }
        }
        drawer.cash = cash;
        drawer.credit = credit;
        drawer.checkAmount = check;
        drawer.cancelAmount = cancelled;
        drawer.total = cash+credit+check;
        drawer.lastUsedTime = DateUtil.getTime();
        drawer.threadSave();
        return drawer;
    }

    @Override
    public void save() {
        cash = formatMoney(cash);
        this.checkAmount = formatMoney(checkAmount);
        this.credit = formatMoney(credit);
        this.total = formatMoney(total);
        super.save();
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "businessDate", "cashier");
	}
}
