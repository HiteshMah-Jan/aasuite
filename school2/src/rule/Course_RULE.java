/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.reference.Course;
import bean.reference.CourseSubject;
import bean.reference.Subject;
import java.util.List;
import javax.swing.JComponent;
import util.DBClient;
import springbean.SchoolDefaultProcess;

/**
 *
 * @author Entokwaa
 */
public class Course_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
    }

    @Override
    public void runOnClick(JComponent comp) {
//        System.out.println("ON CLICK for "+comp.getName());
        if ("btnGenerateCurriculum".equals(comp.getName())) {
            generateCurriculum();
        }
        else if ("btnGeneratePlan".equals(comp.getName())) {
            new SchoolDefaultProcess().setupPaymentPlan();
        }
    }

    private void generateCurriculum() {
        redisplayRecord();
    }
}
