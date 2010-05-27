package rule;

import java.util.List;

import javax.swing.JComponent;

import util.DBClient;
import bean.Schedule;
import bean.reference.Section;

public class Section_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnCheckSchedules".equals(comp.getName())) {
			checkSchedule();
		}		
	}

	private void checkSchedule() {
		Section sec = (Section) this.getBean();
		if (Schedule.noConflictSection(sec)) {
			List<Schedule> sc = DBClient.getList("SELECT a FROM Schedule a WHERE a.section='",sec.code,"'");
			for (Schedule sched:sc) {
				if (sched.hasFacultyConflict()) {
//					PanelUtil.showError(null, "Conflict in schedule found, please check faculty [",sched.faculty,"].");
					return;
				}
				if (sched.hasRoomConflict()) {
//					PanelUtil.showError(null, "Conflict in schedule found, please check room [",sched.room1,"|",sched.room2,"|",sched.room3,"].");
					return;
				}
			}
		}
		else {
//			PanelUtil.showError(null, "Conflict in schedule found, please check.");
		}
	}

}
