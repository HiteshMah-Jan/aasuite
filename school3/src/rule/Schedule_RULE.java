package rule;

import java.util.List;

import javax.swing.JComponent;

import template.screen.TablePopup;
import util.BeanUtil;
import util.DBClient;
import util.PanelUtil;

import bean.Schedule;

public class Schedule_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnCheckSchedules".equals(comp.getName())) {
			checkSchedule();
		}		
		else if ("btnViewRoom1".equals(comp.getName())) {
			viewRoom1();
		}		
		else if ("btnViewRoom1".equals(comp.getName())) {
			viewRoom2();
		}		
		else if ("btnViewRoom1".equals(comp.getName())) {
			viewRoom3();
		}		
		else if ("btnViewFaculty".equals(comp.getName())) {
			viewFaculty();
		}		
	}

	private void viewFaculty() {
		Schedule sc = (Schedule) this.getBean();
		int faculty = sc.facultyId;
		if (faculty>0) {
			List lst = DBClient.getList(BeanUtil.concat("SELECT a FROM Schedule a WHERE a.facultyId=",faculty," ORDER BY a.day1, a.schedTime1"));
	        TablePopup.showRecords("Schedule Entries", lst, Schedule.class, "faculty","subject","section","room1","day1","schedTime1","schedTimeEnd1","room2","day2","schedTime2","schedTimeEnd2","room3","day3","schedTime3","schedTimeEnd3");
		}
	}

	private void viewRoom3() {
		Schedule sc = (Schedule) this.getBean();
		String room = sc.room3;
		if (room!=null && !room.isEmpty()) {
			List lst = DBClient.getList("SELECT a FROM Schedule a WHERE a.room1='",room,"' OR a.room2='",room,"' OR a.room3='",room,"' ORDER BY a.day1, a.schedTime1");
	        TablePopup.showRecords("Schedule Entries", lst, Schedule.class, "faculty","subject","section","room1","day1","schedTime1","schedTimeEnd1","room2","day2","schedTime2","schedTimeEnd2","room3","day3","schedTime3","schedTimeEnd3");
		}
	}

	private void viewRoom2() {
		Schedule sc = (Schedule) this.getBean();
		String room = sc.room2;
		if (room!=null && !room.isEmpty()) {
			List lst = DBClient.getList("SELECT a FROM Schedule a WHERE a.room1='",room,"' OR a.room2='",room,"' OR a.room3='",room,"' ORDER BY a.day1, a.schedTime1");
	        TablePopup.showRecords("Schedule Entries", lst, Schedule.class, "faculty","subject","section","room1","day1","schedTime1","schedTimeEnd1","room2","day2","schedTime2","schedTimeEnd2","room3","day3","schedTime3","schedTimeEnd3");
		}
	}

	private void viewRoom1() {
		Schedule sc = (Schedule) this.getBean();
		String room = sc.room1;
		if (room!=null && !room.isEmpty()) {
			List lst = DBClient.getList("SELECT a FROM Schedule a WHERE a.room1='",room,"' OR a.room2='",room,"' OR a.room3='",room,"' ORDER BY a.day1, a.schedTime1");
	        TablePopup.showRecords("Schedule Entries", lst, Schedule.class, "faculty","subject","section","room1","day1","schedTime1","schedTimeEnd1","room2","day2","schedTime2","schedTimeEnd2","room3","day3","schedTime3","schedTimeEnd3");
		}
	}

	protected void checkSchedule() {
		Schedule sched = (Schedule) this.getBean();
		if (sched.hasFacultyConflict()) {
//			PanelUtil.showError(null, "Conflict in schedule found, please check faculty [",sched.faculty,"].");
			return;
		}
		if (sched.hasRoomConflict()) {
//			PanelUtil.showError(null, "Conflict in schedule found, please check room [",sched.room1,"|",sched.room2,"|",sched.room3,"].");
			return;
		}
		PanelUtil.showMessage(null, "Schedule ok.");
	}

}
