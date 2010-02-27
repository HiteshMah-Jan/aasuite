/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.accounting;

import bean.admin.AclUser;
import constants.UserInfo;
import java.util.Date;
import java.util.Vector;
import javax.persistence.*;

import service.ParamStruct;
import service.ReturnStruct;
import service.util.AbstractIBean;
import service.util.CallService;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;
import util.DBClient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "CashierDailyBooklet")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, 
    columnSearch = {"cashier","usedDate","startOR","endOR","station"}, 
    orderBy="a.usedDate DESC, a.cashier")
@Displays({
    @Display(name="cashier", type="PopSearch", linktoBean=AclUser.class),
    @Display(name="usedDate"),
    @Display(name="startOR", mergeFields={"endOR"}),
    @Display(name="endOR"),
    @Display(name="startMIS", mergeFields={"endMIS"}),
    @Display(name="endMIS"),
    @Display(name="station")
})
public class CashierDailyBooklet extends AbstractIBean {
    @Id
    @Column(name="seq", nullable=false)
    public Integer seq;
    @Column(name="cashier")
    public String cashier;
    @Column(name="usedDate", nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date usedDate;
    @Column(name="startOR", nullable=false)
    public int startOR;
    @Column(name="endOR", nullable=false)
    public int endOR;
    @Column(name="lastUsedOR")
    public int lastUsedOR;
    @Column(name="station")
    public String station;
    @Column(name="status")
    public String status;
    @Column(name="startMIS")
    public int startMIS;
    @Column(name="endMIS")
    public int endMIS;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "cashier");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStartMIS() {
        return startMIS;
    }

    public void setStartMIS(int startMIS) {
        this.startMIS = startMIS;
    }

    public int getEndMIS() {
        return endMIS;
    }

    public void setEndMIS(int endMIS) {
        this.endMIS = endMIS;
    }

    public int getLastUsedOR() {
        return lastUsedOR;
    }

    public void setLastUsedOR(int lastUsedOR) {
        this.lastUsedOR = lastUsedOR;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public int getEndOR() {
        return endOR;
    }

    public void setEndOR(int endOR) {
        this.endOR = endOR;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getStartOR() {
        return startOR;
    }

    public void setStartOR(int startOR) {
        this.startOR = startOR;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Date getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(Date usedDate) {
        this.usedDate = usedDate;
    }

    @Override
    public ReturnStruct callService(ParamStruct param) {
//        for now only next OR
        ReturnStruct ret = new ReturnStruct();
        if (param.getActionCommand()==1) {
            Vector vec = (Vector) param.getData();
            String user = (String) vec.get(0);
            String orType = (String) vec.get(1);
            ret.setStatus(constants.Constants.SUCCESS);
            ret.setData(extractNextOR(user, orType));
        }
        return ret;
    }
    
    private int extractNextOR(String user, String orType) {
        CashierDailyBooklet booklet = (CashierDailyBooklet) DBClient.getFirstRecord("SELECT a FROM CashierDailyBooklet a WHERE a.cashier='"+user+"' ORDER BY a.seq DESC");
        if (booklet==null) {
            logBooklet(user, 0, 0, 0, 0, "", "EMPTY RANGE");
            return 0;
        }
        if (orType==null || orType.trim().isEmpty()) {
            orType = "N";
        }
        int start = booklet.startOR;
        int end = booklet.endOR;
        if (orType.equals("A")) {
            start = booklet.startMIS;
            end = booklet.endMIS;
        }
        int orInvoice = (int) DBClient.getSingleColumnDouble("SELECT MAX(orNumber)+1 FROM Invoice WHERE cashier='"+user+"' AND orType='"+orType+"' AND orNumber BETWEEN "+start+" AND "+end);
        if (orInvoice<=1) {
        	orInvoice = start;
        }
        return orInvoice;
    }
    
    public int extractNextOR(String orType) {
        String user = UserInfo.getUserName();
        Vector vec = new Vector();
        vec.add(user);
        vec.add(orType);
        ReturnStruct ret = CallService.callService(vec, 1, this.getClass().getName());
        try {
            return Integer.parseInt(ret.getData().toString());
        }
        catch (Exception e) {
        }
        return -1;
    }
    
    public static CashierDailyBooklet logBooklet(String user, int start, int end, int startM, int endM, String station, String message) {
        CashierDailyBooklet booklet = new CashierDailyBooklet();
        booklet.cashier = user;
        booklet.lastUsedOR = start;
        booklet.startOR = start;
        booklet.endOR = end;
        booklet.startMIS = startM;
        booklet.endMIS = endM;
        booklet.usedDate = constants.Constants.useDate;
        booklet.station = station;
        booklet.save();
        return booklet;
    }

    public static CashierDailyBooklet logBooklet(String user, int start, int end, int startM, int endM) {
        return logBooklet(user, start, end, startM, endM, null, null);
    }

    public static CashierDailyBooklet logBooklet(int start, int end, int startM, int endM) {
        String user = UserInfo.getUserName();
        return logBooklet(user, start, end, startM, endM, null, null);
    }
    
    public static CashierDailyBooklet getMyBooklet() {
    	String user = UserInfo.getUserName();
        return (CashierDailyBooklet) DBClient.getFirstRecord("SELECT a FROM CashierDailyBooklet a WHERE a.cashier='"+user+"' ORDER BY a.seq DESC");
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "cashier");
	}
}
