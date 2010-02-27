/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import java.util.List;

import javax.swing.JComponent;

import springbean.SuccessfulLogin;
import template.report.AbstractReportTemplate;
import ui.EmployeeAttendanceForm;
import util.DBClient;
import util.PanelUtil;
import bean.Employee;
import bean.Person;
import bean.accounting.PaymentPlan;
import bean.admin.AclGroup;
import bean.admin.AclModule;
import bean.admin.AclUser;
import bean.admin.LoginMonitor;

import common2.Common2View;
import component.IAuthorization;
import component.SpringCall;
import constants.UserInfo;

/**
 *
 * @author Entokwaa
 */
public class Employee_RULE extends Person_RULE {
    @Override
    public void runOnClick(JComponent comp) {
    	super.runOnClick(comp);
    	if ("btnResetPassword".equals(comp.getName())) {
            resetPassword();
        }
    	else if ("btnEmployeeAttendance".equals(comp.getName())) {
            displayAttendance();
        }
    	else if ("btnAutoLogin".equals(comp.getName())) {
            autoLogin();
        }
    	else if ("btnMonitorLogin".equals(comp.getName())) {
            monitorLogin();
        }
    	else if ("btnAddGroupAccount".equals(comp.getName())) {
            addGroupAccount();
        }
    	else if ("btnAddModuleAccount".equals(comp.getName())) {
            addModuleAccount();
        }
    }

	protected void addModuleAccount() {
		List lst = DBClient.getList("SELECT a FROM AclModule a ORDER BY a.moduleName");
		AclModule s = (AclModule) PanelUtil.showPromptMessage(null, "Choose Module", "", lst, "");
		if (s!=null) {
			addModule(s.moduleName);
		}
	}

    protected void addGroupAccount() {
		List lst = DBClient.getList("SELECT a FROM AclGroup a ORDER BY a.code");
		AclGroup s = (AclGroup) PanelUtil.showPromptMessage(null, "Choose Group", "", lst, "");
		if (s!=null) {
			addToGroup(s.code);
		}
	}

    protected void monitorLogin() {
    	PanelUtil.popupBeanTemplate(LoginMonitor.class, "Login Monitor", false);
	}

	protected void autoLogin() {
		Employee emp = (Employee) this.getBean();
		AclUser u = (AclUser) DBClient.getFirstRecord("SELECT a FROM AclUser a WHERE a.userid='"+emp.getUserid()+"'");
		String user = u.userid;
		String pass = u.password;
		UserInfo.clear();
		UserInfo userInfo = UserInfo.login(user, pass.toUpperCase());
		if (userInfo == null) {
			PanelUtil.showError(null, "Invalid username or password.");
			return;
		}
		Common2View.lstPanel.clear();
		Common2View.mapPanels.clear();

		util.AccessControlUtil.setAccessControl(Common2View.mainView.getMenuBar());
		SpringCall.getSpringCallBean(SuccessfulLogin.class).call(userInfo);
		LoginMonitor.addLogin();
	}

	protected void displayAttendance() {
	    Employee p = (Employee) this.getBean();
	    EmployeeAttendanceForm.display(p);
	}

	protected void addModule(String module) {
	    Person p = (Person) this.getBean();
    	AclUser user = (AclUser) DBClient.getFirstRecord("SELECT a FROM AclUser a WHERE a.userid='"+p.userid+"'");
    	if (user==null || user.isEmptyKey()) {
    		PanelUtil.showMessage(null, "User does not have account yet.");
    		return;
    	}
	    IAuthorization intr = IAuthorization.AuthorizationImpl.getInstance(user);
	    intr.addModule(module);
	    if (UserInfo.loginUser.isSuperAAA()) {
	    	boolean b = PanelUtil.showPrompt(null, "Would you like to view all the users?");
	    	if (b) {
	    		AbstractReportTemplate.getInstance().showReportFromFileTemplate("UserAccounts", "");
	    	}
	    }
	}
	
	protected void addToGroup(String group) {
	    Person p = (Person) this.getBean();
	    if (p.userid!=null && p.userid.startsWith("T")) {
//	    	check if userid exist
	    	AclUser b = (AclUser) DBClient.getFirstRecord("SELECT a FROM AclUser a WHERE a.userid='"+p.userid+"'");
	    	if (b!=null && !b.isEmptyKey()) {
		    	PanelUtil.showMessage(usedComp, p.toString()+" already has userid "+p.userid+" - "+b.password);
			    if (UserInfo.loginUser.isSuperAAA()) {
			    	boolean b1 = PanelUtil.showPrompt(null, "Would you like to view all the users?");
			    	if (b1) {
			    		AbstractReportTemplate.getInstance().showReportFromFileTemplate("UserAccounts", "");
			    	}
			    }
		    	return;
	    	}
	    }
	    p.setUserid("T"+p.personId);
	    p.save();
	    
        String password = "PASSWORD"+((int)(Math.random()*1000));

        AclUser user = new AclUser();
	    user.setFirstname(p.getFirstName());
	    user.setLastname(p.getLastName());
	    user.setPassword(password);
	    user.setUserid(p.userid);
	    user.save();
	    
	    IAuthorization intr = IAuthorization.AuthorizationImpl.getInstance(user);
	    intr.addGroup(group);
	    PanelUtil.showMessage(usedComp, "Username is ["+p.userid+"] and password is ["+password+"] for "+p.toString());
	    if (UserInfo.loginUser.isSuperAAA()) {
	    	boolean b = PanelUtil.showPrompt(null, "Would you like to view all the users?");
	    	if (b) {
	    		AbstractReportTemplate.getInstance().showReportFromFileTemplate("UserAccounts", "");
	    	}
	    }
	}

	protected void resetPassword() {
	    Person p = (Person) this.getBean();
		AclUser usr = (AclUser) DBClient.getFirstRecord("SELECT a FROM AclUser a WHERE a.userid='"+p.userid+"'");
        if (usr==null || usr.getUserid()==null) {
        	PanelUtil.showError(null, "Employee does not seem to have userid and password.");
        	return;
        }
        String userId = usr.getUserid();

        String password = "PASSWORD"+((int)(Math.random()*1000));
        PanelUtil.showMessage(usedComp, "Password reset to ["+password+"]");
        usr.setUserid(userId);
        usr.setPassword(password);
        usr.save();
	}
}
