/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.reference.GradeLevel;
import javax.swing.JComponent;
import springbean.SchoolConfig;

/**
 *
 * @author Charliemagne Mark
 */
public class GradeLevel_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
        GradeLevel level = (GradeLevel) this.getBean();
        if (SchoolConfig.isAlwaysComputeMisc()) {
//            System.out.println("RECOMPUTE MISC FEE.");
            setValue("miscFee", level.getComputedMiscFee());
        }
    }

    @Override
    public void runOnClick(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

}
