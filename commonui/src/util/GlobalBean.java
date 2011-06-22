/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import constants.UserInfo;

/**
 *
 * @author Entokwaa
 */
public class GlobalBean {
    private int personId;
    private String userid;
    private String useYear;

    public static GlobalBean getInstance() {
        GlobalBean bean = new GlobalBean();
        if (UserInfo.loginUser==null || UserInfo.loginUser.isSuperAAA()) {
            bean.personId = 0;
            bean.userid = UserInfo.SUPER_AAA_USER;
        }
        else {
            bean.personId = UserInfo.loginUser.getPersonId();
            bean.userid = UserInfo.getUserName();
        }
        return bean;
    }
    
    public String getUseYear() {
		return UserInfo.getUseYear();
	}

	public void setUseYear(String useYear) {
		this.useYear = useYear;
	}

	public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    } 
}
