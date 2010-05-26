package bean.admin;

import service.util.AbstractIBean;
import springbean.AAAConfig;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import util.DBClient;
import util.NetworkUtil;
import template.UITemplate;
import template.Displays;
import template.Display;
import template.screen.TemplateSinglePage;
import bean.reference.Division;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Apr 4, 2009
 * Time: 9:04:02 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "UserStation")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"station", "macAddress", "allowCashier", "allowAAA"})
@Displays({
    @Display(name="station", width=300),
    @Display(name="branch", type="PopSearch", linktoBean = Division.class, label = "Branch/Division"),
    @Display(name="macAddress"),
    @Display(name="allowCashier"),
    @Display(name="allowAAA")
})
public class UserStation extends AbstractIBean {
    @Id
    @Column(name = "station", nullable = false)
    public String station;
    @Column(name = "branch")
    public String branch;
    @Column(name = "macAddress")
    public String macAddress;
    @Column(name = "allowCashier")
    public boolean allowCashier;
    @Column(name = "allowAAA")
    public boolean allowAAA;
    @Column(name = "loggedUser")
    public String loggedUser;

    public UserStation() {
        this.station = NetworkUtil.getHostname();
        this.macAddress = NetworkUtil.macAddress();
    }

    public static String extractStationName() {
        String macAddress = NetworkUtil.macAddress();
        UserStation st = (UserStation) DBClient.getFirstRecord("SELECT a FROM UserStation a WHERE a.macAddress='",macAddress,"'");
        if (st==null) return "";
        return st.station;
    }

    public static boolean isCashierAllowed() throws Exception {
//        String macAddress = NetworkUtil.macAddress();
//        UserStation st = (UserStation) DBClient.getFirstRecord("SELECT a FROM UserStation a WHERE a.macAddress='"+macAddress+"'");
//        if (st==null || st.isEmptyKey()) {
//            throw new Exception("No station defined for MAC Address = "+macAddress+". Please report this to the administrator.");
//        }
//        if (!st.allowCashier) {
//            throw new Exception("Cashier not allowed for station "+st.station+". Please report this to the administrator.");
//        }
        return true;
    }

    public static boolean isAAAAllowed() throws Exception {
//        String hostAddress = NetworkUtil.getHostname();
//        if ("server".equalsIgnoreCase(hostAddress) || "alex".equalsIgnoreCase(hostAddress)) {
//        	return true;
//        }
//        String macAddress = NetworkUtil.macAddress();
//        UserStation st = (UserStation) DBClient.getFirstRecord("SELECT a FROM UserStation a WHERE a.macAddress='"+macAddress+"'");
//        if (st==null || st.isEmptyKey()) {
//            throw new Exception("No station defined for MAC Address = "+macAddress+". Please report this to the administrator.");
//        }
//        if (!st.allowAAA) {
//            throw new Exception("AAA not allowed for station "+st.station+". Please report this to the administrator.");
//        }
        return true;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public boolean isAllowCashier() {
        return allowCashier;
    }

    public void setAllowCashier(boolean allowCashier) {
        this.allowCashier = allowCashier;
    }

    public boolean isAllowAAA() {
		return allowAAA;
	}

	public void setAllowAAA(boolean allowAAA) {
		this.allowAAA = allowAAA;
	}

	public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public String popupSearch(String criteria) {
        return "";
    }
	
	@Override
	public void setupIndex() {
		runIndex(1, "macAddress");
	}
}
