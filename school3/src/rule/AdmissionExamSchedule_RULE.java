package rule;

import util.PanelUtil;
import util.DateUtil;
import util.DBClient;

import javax.swing.*;

import bean.admin.AppConfig;
import bean.reference.AdmissionExamSchedule;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Apr 6, 2009
 * Time: 7:51:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdmissionExamSchedule_RULE extends BusinessRuleWrapper {
    @Override
    public void runFocusLost(JComponent comp) {
        
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnGenerateForSchoolYear".equals(comp.getName())) {
            generate();
        }
    }

    List<String> days;

    private void generate() {
        String sy = (String) PanelUtil.showPromptMessage(usedComp, "Choose School Year", "Choose School Year", AppConfig.getSchoolYears(), AppConfig.getSchoolYear());
        if (days==null) {
            days = new ArrayList();
            days.add("SUNDAY");
            days.add("MONDAY");
            days.add("TUESDAY");
            days.add("WEDNESDAY");
            days.add("THURSDAY");
            days.add("FRIDAY");
            days.add("SATURDAY");
        }
        String dow = (String) PanelUtil.showPromptMessage(usedComp, "Choose Day", "Choose Day", days, "SATURDAY");
        AdmissionExamSchedule.generate(sy, dow);
    }
}
