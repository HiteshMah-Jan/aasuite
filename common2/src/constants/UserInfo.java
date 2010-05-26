/*
 * UserInfo.java
 *
 * Created on Oct 30, 2007, 4:09:15 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import service.IService;
import service.ParamStruct;
import service.ReturnStruct;
import service.util.AbstractIBean;
import service.util.CallService;
import util.BeanUtil;
import util.DBClient;
import util.PanelUtil;
import bean.Person;
import bean.admin.AclDuty;
import bean.admin.AclUser;
import bean.admin.AclUserDuty;
import bean.admin.AclUserGroup;
import bean.admin.AclUserModule;
import bean.admin.AppConfig;
import bean.admin.UserStation;

import component.JActiveMenuPallete;

/**
 *
 * @author Budoy Entokwa
 */
public class UserInfo implements Serializable, IService {

    public static final String SUPER_AAA_USER = "AAA";
    public static final String SUPER_AAA_PASS = "111222111";

    public static UserInfo loginUser;
    public Person p;

    public static int getPersonId() {
    	if (loginUser==null || loginUser.isSuperAAA()) {
    		return -1;
    	}
        if (loginUser.p==null) {
        	loginUser.p = (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.userid='",loginUser.user.getUserid(),"'");
        }
        if (loginUser.p==null || loginUser.p.getPersonId()==null) return 0;
        return loginUser.p.getPersonId();
    }
    
    public String getPersonName() {
        if (p==null) p = (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.userid='",user.getUserid(),"'");
        if (p==null || p.getPersonId()==null) return "";
        return p.toString();
    }

    public boolean isSuperAAA() {
        return user.getUserid().equals(SUPER_AAA_USER);
    }

    public static UserInfo login(String user, String password) {
        AclUser acluser = new AclUser();
        acluser.setUserid(user);
        acluser.setPassword(password);
        acluser.setFirstname(null);
        acluser.setLastname(null);
        if (user.equals(SUPER_AAA_USER) && password.equals(SUPER_AAA_PASS)) {
//        	check for user station
        	try {
				UserStation.isAAAAllowed();
			} catch (Exception e) {
				PanelUtil.showError(null, e.getMessage());
				return null;
			}
            loginUser = new UserInfo();
            loginUser.setUser(acluser);
        } else {
//    		System.out.println("CALLED LOGIN");
            ReturnStruct ret = CallService.callService(acluser, Constants.ADD_HELP, Constants.LOGIN_SERVICE);
            if (ret!=null) {
//        		System.out.println("CALLED LOGIN1");
            	loginUser = (UserInfo) ret.getData();
//        		System.out.println("CALLED LOGIN3");
            }
        }
        JActiveMenuPallete.removeAllFromMemory();
        return loginUser;
    }

    public static UserInfo weblogin(String user, String password) {
        AclUser acluser = new AclUser();
        acluser.setUserid(user);
        acluser.setPassword(password);
        acluser.setFirstname(null);
        acluser.setLastname(null);
        if (user.equals(SUPER_AAA_USER) && password.equals(SUPER_AAA_PASS)) {
            loginUser = new UserInfo();
            loginUser.setUser(acluser);
        } else {
//            System.out.println("LOGIN SERVER CALL....");
            ReturnStruct ret = CallService.callService(acluser, Constants.ADD_HELP, Constants.LOGIN_SERVICE);
            loginUser = (UserInfo) ret.getData();
        }
        return loginUser;
    }
    
    boolean temporary;
    String personType;
    AclUser user;
    List<AclUserDuty> duties = new ArrayList<AclUserDuty>();
    List<AclUserGroup> groups = new ArrayList<AclUserGroup>();
    List<AclUserModule> modules = new ArrayList<AclUserModule>();

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    
    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    public AclUser getUser() {
        return user;
    }

    public void setUser(AclUser user) {
        this.user = user;
        List<String> lst = new ArrayList();
        lst.add(BeanUtil.concat("SELECT a FROM AclUserDuty a WHERE a.userid='",user.userid,"'"));
        lst.add(BeanUtil.concat("SELECT a FROM AclUserModule a WHERE a.userid='",user.userid,"'"));
        lst.add(BeanUtil.concat("SELECT a FROM AclUserGroup a WHERE a.userid='",user.userid,"'"));
        Map map = DBClient.batchQueryServerCache(lst);
        duties = (List<AclUserDuty>) map.get(BeanUtil.concat("SELECT a FROM AclUserDuty a WHERE a.userid='",user.userid,"'"));
        groups = (List<AclUserGroup>) map.get(BeanUtil.concat("SELECT a FROM AclUserModule a WHERE a.userid='",user.userid,"'"));
        modules = (List<AclUserModule>) map.get(BeanUtil.concat("SELECT a FROM AclUserModule a WHERE a.userid='",user.userid,"'"));
    }

    public List<AclUserDuty> getDuties() {
        return duties;
    }

    public void setDuties(List<AclUserDuty> duties) {
        this.duties = duties;
    }

    public List<AclUserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<AclUserGroup> groups) {
        this.groups = groups;
    }

    public List<AclUserModule> getModules() {
        return modules;
    }

    public void setModules(List<AclUserModule> modules) {
        this.modules = modules;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public static String getUserName() {
        if (loginUser==null) return "";
        if (loginUser.user != null) {
            return loginUser.user.getUserid();
        }
        return "";
    }
    
    static List<AclUserDuty> dutyLst;
    public static boolean hasDuty(String dutycode) {
        if (loginUser==null) return false;
        if (dutyLst==null) {
//            need to call the server first
            CallService.callService("", 1, UserInfo.class.getName());
            dutyLst = DBClient.getListServerCache(BeanUtil.concat("SELECT a FROM AclUserDuty a WHERE a.userid='",loginUser.user.userid,"'"));
        }
        if (loginUser.isSuperAAA()) {
            return true;
        }
        for (AclUserDuty duty : dutyLst) {
            if (duty.dutyCode.equals(dutycode)) return true;
        }
        return false;
    }

    public static void clear() {
    	dutyLst = null;
    }
    
    public static boolean canChangeStudentNumber() {
        return hasDuty("CAN CHANGE STUDENT NUMBER");
    }

    public static boolean canAssessStudent() {
        return hasDuty("HAS CREATE ENROLLMENT PAYMENT");
    }

    public static boolean canLockInvoice() {
        return hasDuty("HAS LOCK OR");
    }

    public static boolean canPrintInvoice() {
        return hasDuty("HAS PRINT INVOICE");
    }

    public static boolean canViewGL() {
        return hasDuty("HAS VIEW GL");
    }

    public static boolean canMarkBounceCheck() {
        return hasDuty("ALLOWED TO MARK BOUNCE");
    }
    public static boolean canLiftSatusForBounceCheck() {
        return hasDuty("ALLOWED TO LIFT CHECK STATUS");
    }

    public static boolean canUpdateExamDetails() {
        return hasDuty("HAS UPDATE EXAMINATION DETAILS");
    }

    public static boolean canRequestDiscount() {
        return hasDuty("HAS REQUEST DISCOUNT");
    }
    public static boolean canApproveTuitionDiscount() {
        return hasDuty("HAS APPROVE TUITION REQUEST DISCOUNT");
    }
    public static boolean canApproveSurchargeDiscount() {
        return hasDuty("HAS APPROVE SURCHARGE REQUEST DISCOUNT");
    }
    public static boolean canRequestPO() {
        return hasDuty("HAS REQUEST PO");
    }
    public static boolean canApprovePO() {
        return hasDuty("HAS APPROVE PO");
    }
    public static boolean canApprovePO2() {
        return hasDuty("HAS APPROVE PO2");
    }
    public static boolean canOverridePO() {
        return hasDuty("HAS OVERRIDE PO");
    }
    public static boolean canPrintVoucher() {
        return hasDuty("HAS PRINT VOUCHER");
    }
    public static boolean canReceivePO() {
        return hasDuty("HAS RECEIVE PO");
    }
    @Override
    public ReturnStruct callService(ParamStruct param) {
//        populate all the duty codes here
//    	System.out.println("CALLED USERINFO.");
        AclDuty.createDuty("CAN EDIT SALARY", "");
        AclDuty.createDuty("CAN CHANGE STUDENT NUMBER", "");
        AclDuty.createDuty("HAS CREATE ENROLLMENT PAYMENT", "");
        AclDuty.createDuty("HAS PRINT INVOICE", "");
        AclDuty.createDuty("HAS VIEW GL", "");
        AclDuty.createDuty("ALLOWED TO MARK BOUNCE", "");
        AclDuty.createDuty("ALLOWED TO LIFT CHECK STATUS", "");
        AclDuty.createDuty("HAS UPDATE EXAMINATION DETAILS", "");
        AclDuty.createDuty("HAS REQUEST DISCOUNT", "");
        AclDuty.createDuty("HAS APPROVE TUITION REQUEST DISCOUNT", "");
        AclDuty.createDuty("HAS APPROVE SURCHARGE REQUEST DISCOUNT", "");
        AclDuty.createDuty("HAS LOCK OR", "");
        AclDuty.createDuty("HAS REFERENCE ACCESS", "");
        AclDuty.createDuty("HAS REQUEST PO", "");
        AclDuty.createDuty("HAS APPROVE PO", "");
        AclDuty.createDuty("HAS APPROVE PO2", "");
        AclDuty.createDuty("HAS OVERRIDE PO", "");
        AclDuty.createDuty("HAS PRINT VOUCHER", "");
        AclDuty.createDuty("HAS RECEIVE PO", "");
        AclDuty.createDuty("HAS DELETE GRADE", "");
        AclDuty.createDuty("DELETE STUDENT", "");
        AclDuty.createDuty("DELETE EMPLOYEE", "");
        AclDuty.createDuty("CAN EDIT ADMISSION SCORE", "");
        AclDuty.createDuty("CAN EDIT ADMISSION DETAIL", "");
        AclDuty.createDuty("CAN VIEW REPORT STUDENT PROFILE", "");
//        AclDuty.createDuty("", "");
//        AclDuty.createDuty("", "");
//        AclDuty.createDuty("", "");
//        AclDuty.createDuty("", "");
        
        return null;
    }

	public static boolean canEditAdmissionScore() {
        return hasDuty("CAN EDIT ADMISSION SCORE");
	}
	public static boolean canEditAdmissionDetail() {
        return hasDuty("CAN EDIT ADMISSION DETAIL");
	}

	public static boolean canModifyReference() {
        return hasDuty("HAS REFERENCE ACCESS");
	}

	public static boolean canDeleteGrade() {
        return hasDuty("HAS DELETE GRADE");
	}
	
	public static String useYear;
	public static String getUseYear() {
		if (useYear==null) {
			return AppConfig.getSchoolYear();
		}
		return useYear;
	}

	public static boolean canDeleteStudent() {
        return hasDuty("DELETE STUDENT");
	}

	public static boolean canDeleteEmployee() {
        return hasDuty("DELETE EMPLOYEE");
	}

	public static boolean hasDuty(String[] duties) {
		if (duties == null || duties.length == 0) {
			return true;
		}
		for (String s:duties) {
//			should not have empty, empty is for all user
			if (s.isEmpty() || hasDuty(s)) {
				return true;
			}
		}
		return false;
	}
}
