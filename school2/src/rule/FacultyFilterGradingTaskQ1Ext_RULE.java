package rule;

import java.util.List;

import javax.swing.JComponent;

import template.screen.AbstractChildTemplatePanel;
import util.BeanUtil;
import util.DataUtil;
import util.PanelUtil;
import bean.admin.AppConfig;
import bean.person.FacultyGradingTask;
import bean.person.StudentSubjectDetailGrading;
import bean.reference.LockGrading;

public class FacultyFilterGradingTaskQ1Ext_RULE extends FacultyGradingTask_RULE {
	@Override
	public void onChangeRecord() {
		super.onChangeRecord();
		FacultyGradingTask task = (FacultyGradingTask) this.getBean();
		if (task.component==null) {
			task.component = "";
		}
		if (task.usePercentage) {
			hide("grpItem Counts");
//			setDisplay(0);
			setColDisplay(12);
		}
		else {
			show("grpItem Counts");
			if (task.component.contains("QUARTER")) {
				setDisplay(1);
				setColDisplay(1);
			}
			else if (task.component.contains("LONG")) {
				setDisplay(8);
				setColDisplay(8);
			}
			else {
				setDisplay(12);
				setColDisplay(12);
			}
		}
	}

	private void setDisplay(int k) {
		JComponent comp1 = getComponent("q1ItemCount1");
		JComponent comp2 = getComponent("q2ItemCount1");
		JComponent comp3 = getComponent("q3ItemCount1");
		if (comp1!=null) {
			for (int i=1; i<=12; i++) {
				if (i<=k) show("q1ItemCount"+i);
				else hide("q1ItemCount"+i);
			}
		}
		if (comp2!=null) {
			for (int i=1; i<=12; i++) {
				if (i<=k) show("q2ItemCount"+i);
				else hide("q2ItemCount"+i);
			}
		}
		if (comp3!=null) {
			for (int i=1; i<=12; i++) {
				if (i<=k) show("q3ItemCount"+i);
				else hide("q3ItemCount"+i);
			}
		}
		for (int i=1; i<=12; i++) {
			if (i<=k) show("q4ItemCount"+i);
			else hide("q4ItemCount"+i);
		}
	}

	private void setColDisplay(int k) {
		showAllColumns(0);
		for (int i=1; i<=12; i++) {
			if (i>k) {
				hideChildTableCol(0, i);
			}
		}
	}

	@Override
	public void runFocusLost(JComponent comp) {
		if (comp.getName().contains("ItemCount")) {
			validateScores(comp.getName());
		}
	}

	private void validateScores(String name) {
		String[] arr = name.split("ItemCount");
		String qtr = arr[0].replace("q", "");
		int quarter = DataUtil.getIntValue(qtr);
		int itemNumber = DataUtil.getIntValue(arr[1]);
		int itemCount = getIntValue(name, 0);
		String scoreName = "q"+quarter+"Score"+itemNumber;
			
		FacultyGradingTask task = (FacultyGradingTask) this.getBean();
		List<AbstractChildTemplatePanel> tabs = this.panel.getTabs();
		AbstractChildTemplatePanel tab = tabs.get(0);
		List lst = tab.list;
		boolean changed = false;
		for (Object obj:lst) {
			if (obj instanceof StudentSubjectDetailGrading) {
				StudentSubjectDetailGrading s = (StudentSubjectDetailGrading)obj;
				int score = (int) BeanUtil.getDoubleValue(s, scoreName);
				if (score>itemCount) {
					changed = true;
					PanelUtil.showMessage(null, "Score "+score+" is more than item count "+itemCount+". System will replace value to 0.");
					s.changeValue(scoreName, 0);
					s.changeValue(scoreName, 0);
				}
			}
		}
		if (changed && PanelUtil.showPrompt(null, "Would you like to recalculate the changes?")) {
    		LockGrading lock = LockGrading.extractGrading(AppConfig.getSchoolYear());
    		if (lock==null) {
    			lock = new LockGrading();
    		}
    		if (!lock.isQ1Locked()) {
    			saveAllScore(1);
    		}
    		else if (!lock.isQ2Locked()) {
    			saveAllScore(2);
    		} 
    		else if (!lock.isQ3Locked()) {
    			saveAllScore(3);
    		} 
    		else {
    			saveAllScore(4);
    		} 
		}
	}
}
