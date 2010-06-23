package rule;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import springbean.CalculateGradeService;
import template.screen.AbstractChildTemplatePanel;
import util.PanelUtil;
import bean.extension.ScheduleManualGradingExt;
import bean.person.StudentSubject;

public class ScheduleManualGradingExt_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnSaveGrade1".equals(comp.getName())) {
			saveAllGrades(1);
		}
		else if ("btnSaveGrade2".equals(comp.getName())) {
			saveAllGrades(2);
		}
		else if ("btnSaveGrade3".equals(comp.getName())) {
			saveAllGrades(3);
		}
		else if ("btnSaveGrade4".equals(comp.getName())) {
			saveAllGrades(4);
		}
	}

	private void saveAllGrades(int quarter) {
		ScheduleManualGradingExt bean = (ScheduleManualGradingExt) this.getBean();
		List<AbstractChildTemplatePanel> tabs = this.panel.getTabs();
		AbstractChildTemplatePanel tab = tabs.get(0);
		
		List<StudentSubject> subjects = tab.list;
		List<StudentSubject> tlist = new ArrayList<StudentSubject>();
		for (StudentSubject s : subjects) {
			tlist.add(s);
		}
		
		CalculateGradeService.calculateGrade(quarter, bean, tlist);
		redisplayRecord();
		PanelUtil.hideWaitFrame();
	}

}
