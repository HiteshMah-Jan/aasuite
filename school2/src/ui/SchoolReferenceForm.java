/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import bean.admin.AppConfig;
import component.TabPanelForm;
/**
 *
 * @author Charliemagne Mark
 */
public class SchoolReferenceForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "School Reference";
    }
    @Override
    public String tabs() {
    	String lic = AppConfig.getLicenceType();
    	if ("ALL".equals(lic)) {
//            String str = "Course,GradeLevel,Subject,ValuesSubjectGradingCriteria,Section,Department,Building,Room,ScholarshipTable,EventHoliday,Offense,ActionTaken";
            String str = "Course,GradeLevel,Subject,Section,Schedule,Room";
    		if (AppConfig.hasExamReference()) {
    			str += ",AdmissionExamReference";
    		}
    		if (AppConfig.hasSchoolProfile()) {
//    			str += ",SchoolProfileSummary";
    		}
            return str;
    	}
    	else {
    		if (lic!=null && lic.contains("GUIDANCE")) {
                return "Course,GradeLevel,Subject,Section,Room";
    		}
    		else {
                return "Course,GradeLevel,Subject,Section,Room";
    		}
    	}
    }

}
