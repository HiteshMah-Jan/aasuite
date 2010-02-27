/*
 * AuthorizationIntr.java
 * 
 * Created on Feb 6, 2008, 1:10:06 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import bean.admin.AclDutyModule;
import bean.admin.AclGroup;
import bean.admin.AclGroupDuty;
import bean.admin.AclGroupModule;
import bean.admin.AclModule;
import bean.admin.AclUser;
import bean.admin.AclUserDuty;
import bean.admin.AclUserGroup;
import bean.admin.AclUserModule;
import java.util.List;
import service.util.AbstractIBean;

/**
 *
 * @author Entokwaa
 */
public interface IAuthorization {
    void addDuty(String dutyCode);
    void addModule(String module);
    void addGroup(String groupCode);
    
    void removeDuty(String dutyCode);
    void removeModule(String module);
    void removeGroup(String groupCode);
    
    public static class AuthorizationImpl {
        public static IAuthorization getInstance(AbstractIBean bean) {
            if (bean==null) return null;
            if (bean instanceof AclUser) return new AuthorizationUserImpl((AclUser)bean);
            if (bean instanceof AclGroup) return new AuthorizationGroupImpl((AclGroup)bean);
            if (bean instanceof AclModule) return new AuthorizationModuleImpl((AclModule)bean);
            return null;
        }
    }

    public static class AuthorizationUserImpl implements IAuthorization{
        AclUser bean;
        
        public static IAuthorization getInstance(AclUser bean) {
            return new AuthorizationUserImpl(bean);
        }
        
        public AuthorizationUserImpl(AclUser bean) {
            this.bean = bean;
        }
        
        public void addDuty(String dutyCode) {
            AclUserDuty duty = bean.getDutyObj(dutyCode);
            if (duty!=null && duty.getDutyCode()!=null) return;
            AclUserDuty obj = new AclUserDuty();
            obj.userid = bean.userid;
            obj.dutyCode = dutyCode;
            obj.save();
        }
        public void addModule(String module) {
            AclUserModule mod = bean.getModuleObj(module);
            if (mod!=null && mod.getModuleName()!=null) return;
            AclUserModule obj = new AclUserModule();
            obj.userid = bean.userid;
            obj.moduleName = module;
            obj.save();

            AclModule modObj = (AclModule) AclModule.getObject(module);
            if (modObj!=null) {
                List<String> lst = modObj.getDutyList();
                for (String str : lst) {
                    addDuty(str);
                }
            }
        }
        public void addGroup(String groupCode) {
            AclGroup grpObj = (AclGroup) AclGroup.getObject(groupCode);
            if (grpObj==null && grpObj.getCode()==null) {
//                System.out.println("GROUP "+groupCode+" NOT FOUND.");
                return;
            }
            AclUserGroup group = new AclUserGroup();
            group.userid = bean.userid;
            group.groupCode = groupCode;
            group.save();

            List<String> lst = grpObj.getModuleList();
            for (String str : lst) {
                addModule(str);
            }

            lst = grpObj.getDutyList();
            for (String str : lst) {
                addDuty(str);
            }
        }
        public void removeDuty(String dutyCode) {
            AbstractIBean.runSQL("DELETE a FROM AclUserDuty a WHERE a.userid='"+bean.getUserid()+"' AND a.dutyCode='"+dutyCode+"'");
        }
        public void removeModule(String module) {
            AbstractIBean.runSQL("DELETE a FROM AclUserDuty a, AclDutyModule b WHERE a.dutyCode=b.dutyCode AND a.userid='"+bean.getUserid()+"' AND b.moduleName='"+module+"'");
            AbstractIBean.runSQL("DELETE a FROM AclUserModule a WHERE a.userid="+bean.getUserid()+" AND a.moduleName='"+module+"'");
        }
        public void removeGroup(String groupCode) {
            AbstractIBean.runSQL("DELETE a FROM AclUserDuty a, AclGroupDuty b WHERE a.dutyCode=b.dutyCode AND a.userid='"+bean.getUserid()+"' AND b.groupCode='"+groupCode+"'");
            AbstractIBean.runSQL("DELETE a FROM AclUserModule a, AclGroupModule b WHERE a.moduleName=b.moduleName AND a.userid="+bean.getUserid()+" AND b.groupCode='"+groupCode+"'");
            AbstractIBean.runSQL("DELETE a FROM AclUserGroup a WHERE a.userid="+bean.getUserid()+" AND a.groupCode='"+groupCode+"'");
        }
    }

    public static class AuthorizationModuleImpl implements IAuthorization{
        AclModule bean;
        
        public static IAuthorization getInstance(AclModule bean) {
            return new AuthorizationModuleImpl(bean);
        }
        
        public AuthorizationModuleImpl(AclModule bean) {
            this.bean = bean;
        }
        
        public void addDuty(String dutyCode) {
            AclDutyModule duty = bean.getDutyObj(dutyCode);
            if (duty!=null && duty.getDutyCode()!=null) return;
            AclDutyModule module = new AclDutyModule();
            module.moduleName = bean.getModuleName();
            module.dutyCode = dutyCode;
            module.save();
        }
        public void addModule(String module) {
            //no implementation
        }
        public void addGroup(String groupCode) {
            //no implementation
        }
        public void removeDuty(String dutyCode) {
            AbstractIBean.runSQL("DELETE a FROM AclDutyModule a WHERE a.moduleName='"+bean.getModuleName()+"' AND a.dutyCode='"+dutyCode+"'");
        }
        public void removeModule(String module) {
            //no implementaion
        }
        public void removeGroup(String groupCode) {
            //no implementation
        }
    }

    public static class AuthorizationGroupImpl implements IAuthorization{
        AclGroup bean;
        
        public static IAuthorization getInstance(AclGroup bean) {
            return new AuthorizationGroupImpl(bean);
        }
        
        public AuthorizationGroupImpl(AclGroup bean) {
            this.bean = bean;
        }
        
        public void addDuty(String dutyCode) {
            AclGroupDuty duty = bean.getDutyObj(dutyCode);
            if (duty!=null && duty.getDutyCode()!=null) return;
            AclGroupDuty obj = new AclGroupDuty();
            obj.dutyCode = bean.getCode();
            obj.groupCode = dutyCode;
            obj.save();
        }
        public void addModule(String module) {
            AclGroupModule mod = bean.getModuleObj(module);
            if (mod!=null && mod.getModuleName()!=null) return;
            AclGroupModule obj = new AclGroupModule();
            obj.moduleName = module;
            obj.groupCode = bean.getCode();
            obj.save();

            AclModule modObj = (AclModule) AclModule.getObject(module);
            List<String> lst = modObj.getDutyList();
            for (String str : lst) {
                addDuty(str);
            }
        }
        public void addGroup(String groupCode) {
            //no implementation
        }
        public void removeDuty(String dutyCode) {
            AbstractIBean.runSQL("DELETE a FROM AclGroupDuty a WHERE a.groupCode='"+bean.getCode()+"' AND a.dutyCode='"+dutyCode+"'");
        }
        public void removeModule(String module) {
            AbstractIBean.runSQL("DELETE a FROM AclGroupDuty a, AclDutyModule b WHERE a.dutyCode=b.dutyCode AND a.groupCode='"+bean.getCode()+"' AND b.moduleName='"+module+"'");
            AbstractIBean.runSQL("DELETE a FROM AclGroupModule a WHERE a.groupCode="+bean.getCode()+" AND a.moduleName='"+module+"'");
        }
        public void removeGroup(String groupCode) {
            //no implementation
        }
    }
}
