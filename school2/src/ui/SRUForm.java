package ui;

import bean.admin.AppConfig;

import component.TabPanelForm;

public class SRUForm extends TabPanelForm {

	@Override
	public String tabs() {
//        return "SectioningForm,StudentSubjectSRUExt,EnrollmentGradeExt,LockGrading,StudentHonor";
		if (AppConfig.isLockPerGradeLevel()) {
	        return "SectioningForm,EnrollmentGradeExt,LockGrading,GradeLevelLockingExt,StudentHonor";
		}
		else {
	        return "SectioningForm,EnrollmentGradeExt,LockGrading,StudentHonor";
		}
	}

}
