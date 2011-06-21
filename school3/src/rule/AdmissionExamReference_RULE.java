package rule;

import util.PanelUtil;

import javax.swing.*;

import bean.admin.AppConfig;
import bean.reference.AdmissionExamSchedule;
import bean.reference.AdmissionExamReference;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Apr 6, 2009
 * Time: 7:50:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdmissionExamReference_RULE extends BusinessRuleWrapper {
    @Override
    public void runFocusLost(JComponent comp) {
        
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnGenerateForSchoolYear".equals(comp.getName())) {
            String sy = (String) PanelUtil.showPromptMessage(usedComp, "Choose School Year", "Choose School Year", AppConfig.getSchoolYears(), AppConfig.getSchoolYear());
            AdmissionExamReference.generate(sy);
        }
        
        }
    }

