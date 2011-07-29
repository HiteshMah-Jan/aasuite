/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import bean.admin.AppConfig;
import bean.reference.LockGrading;
import component.TabPanelForm;
import constants.UserInfo;
/**
 *
 * @author Charliemagne Mark
 */
public class FacultyGradingForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Faculty Grading Tool";
    }
    
    @Override
    public String tabs() {
    	if (AppConfig.isRawScoreGrading()) {
    		if (AppConfig.isShowCollege()) {
            	if (UserInfo.loginUser.isSuperAAA()) {
                    return "FacultyGradingTask,StudentSubject2Ext";
            	}
            	else {
                    return "FacultyGradingTaskCollegeExt";
            	}
    		}
    		else {
            	if (UserInfo.loginUser.isSuperAAA()) {
                    return "FacultyGradingTask,StudentValuesGrading,StudentSubject2Ext";
            	}
            	else {
            		LockGrading lock = LockGrading.extractGrading(AppConfig.getSchoolYear());
            		if (lock==null) {
            			lock = new LockGrading();
            		}
            		if (!lock.isQ1Locked()) {
                        return "FacultyFilterGradingTaskQ1Ext,StudentValuesFilterGradingQ1Ext";
            		}
            		else if (!lock.isQ2Locked()) {
                        return "FacultyFilterGradingTaskQ2Ext,StudentValuesFilterGradingQ2Ext";
            		}
            		else if (!lock.isQ3Locked()) {
                        return "FacultyFilterGradingTaskQ3Ext,StudentValuesFilterGradingQ3Ext";
            		}
            		else {
                        return "FacultyFilterGradingTaskQ4Ext,StudentValuesFilterGradingQ4Ext";
            		}
            	}
    		}
    	}
    	else {
    		if (AppConfig.isShowCollege()) {
            	if (UserInfo.loginUser.isSuperAAA()) {
                    return "ScheduleManualGradingExt,StudentValuesGrading,StudentSubject2Ext";
            	}
            	else {
            		LockGrading lock = LockGrading.extractGrading(AppConfig.getSchoolYear());
            		if (lock==null) {
            			lock = new LockGrading();
            		}
            		if (!lock.isQ1Locked()) {
                        return "ScheduleManualGradingQ1Ext";
            		}
            		else if (!lock.isQ2Locked()) {
                        return "ScheduleManualGradingQ2Ext";
            		}
            		else {
                        return "ScheduleManualGradingQ4Ext";
            		}
            	}
    		}
    		else {
            	if (UserInfo.loginUser.isSuperAAA()) {
                    return "ScheduleManualGradingExt,StudentValuesGrading,StudentSubject2Ext";
            	}
            	else {
            		LockGrading lock = LockGrading.extractGrading(AppConfig.getSchoolYear());
            		if (lock==null) {
            			lock = new LockGrading();
            		}
            		if (!lock.isQ1Locked()) {
                        return "ScheduleManualGradingQ1Ext,StudentValuesFilterGradingQ1Ext";
            		}
            		else if (!lock.isQ2Locked()) {
                        return "ScheduleManualGradingQ2Ext,StudentValuesFilterGradingQ2Ext";
            		}
            		else if (!lock.isQ3Locked()) {
                        return "ScheduleManualGradingQ3Ext,StudentValuesFilterGradingQ3Ext";
            		}
            		else {
                        return "ScheduleManualGradingQ4Ext,StudentValuesFilterGradingQ4Ext";
            		}
            	}
    		}
    	}
    }

}
