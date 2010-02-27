/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.Person;
import javax.swing.JComponent;

import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class Person_RULE extends BusinessRuleWrapper {
    
    @Override
    public void runFocusLost(JComponent comp) {
        Person p = (Person) panel.getCurrentObject();
        if ("birthDate".equalsIgnoreCase(comp.getName())) {
            setAge(p);
        }
    }

    @Override
    public void runOnClick(JComponent comp) {
//        System.out.println("ON CLICK for "+comp.getName());
        if ("btnInactive".equals(comp.getName())) {
            setInactive();
        }
        else if ("btnActive".equals(comp.getName())) {
            setActive();
        }
    }

    protected void setAge(Person p) {
        int age = util.DateUtil.getSpanYears(p.birthDate);
        setValue("age", age);
    }

    private void setActive() {
        Person p = (Person) this.getBean();
        p.setIsActive(true);
        p.save();
        showMessage("Selected record is now active, please refresh.");
    }

    private void setInactive() {
        Person p = (Person) this.getBean();
        p.setIsActive(false);
        p.save();
        String sql1 = "DELETE FROM AclUser WHERE userId='"+p.userid+"'";
        String sql2 = "DELETE FROM AclUserDuty WHERE userId='"+p.userid+"'";
        String sql3 = "DELETE FROM AclUserGroup WHERE userId='"+p.userid+"'";
        String sql4 = "DELETE FROM AclUserModule WHERE userId='"+p.userid+"'";
        String sql5 = "UPDATE Person SET userId=null WHERE userId='"+p.userid+"'";
        DBClient.runBatchNative(sql1, sql2, sql3, sql4, sql5);
        showMessage("Selected record is now inactive, please refresh.");
    }
}
