/*
 * Login.java
 *
 * Created on Sep 8, 2007, 3:13:44 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import bean.Person;
import bean.admin.AclGroup;
import bean.admin.AclUser;
import constants.UserInfo;
import service.util.AbstractIBean;
import util.BeanUtil;
import util.Log;

/**
 *
 * @author Budoy Entokwa
 */
public class Login implements IService {

    @SuppressWarnings(value = "unchecked")
    public ReturnStruct callService(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        AclUser user = (AclUser) param.getData();
        String sql = BeanUtil.concat("SELECT a FROM AclUser a WHERE a.userid='",user.getUserid(),"' AND a.password='",user.getPassword(),"'");
        AclUser userNew = (AclUser) AbstractIBean.firstRecord(sql);
        if (userNew != null) {
        	Log.out("USER ",userNew.userid);
            UserInfo userInfo = new UserInfo();
            userInfo.setUser(userNew);
            userInfo.setTemporary(false);
            userInfo.setPersonType(null);
            ret.setData(userInfo);
        }
        else {
            sql = BeanUtil.concat("SELECT a FROM Person a WHERE a.userid='",user.getUserid(),"' AND a.tempPass='",user.getPassword(),"'");
            Person p = (Person) AbstractIBean.firstRecord(sql);
            if (p!=null && !p.isEmptyKey()) {
                user = new AclUser();
                user.userid = p.userid;
                user.password = p.tempPass;
                UserInfo userInfo = new UserInfo();
                userInfo.setUser(user);
                userInfo.setTemporary(true);
                userInfo.setPersonType(p.personType);
                userInfo.p = p;
                ret.setData(userInfo);
                
                //check if the group exist
                AclGroup grp = (AclGroup) AclGroup.find(AclGroup.class, p.personType.toUpperCase());
                if (grp==null || grp.isEmptyKey()) {
                    grp = new AclGroup();
                    grp.code = p.personType.toUpperCase();
                    grp.groupName = p.personType.toUpperCase();
                    grp.description = p.personType.toUpperCase();
                    grp.save();
                }
            }
        }
        return ret;
    }
}
