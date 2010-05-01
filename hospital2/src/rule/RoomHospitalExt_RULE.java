package rule;

import java.util.Date;

import javax.swing.JComponent;

import util.DateUtil;

import bean.person.PatientRoom;
import bean.reference.RoomHospital;

public class RoomHospitalExt_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnRelease".equals(comp.getName())) {
			releasePatient();
		}
	}

	protected void releasePatient() {
		RoomHospital room = (RoomHospital) this.getBean();
		int patientId = room.patientId;
		room.patientId = 0;
		room.endDate = new Date();
		room.endTime = DateUtil.getTime();
		room.save();

//		create record entry in PatientRoom
		PatientRoom pr = new PatientRoom();
		pr.room = room.room;
		pr.bed = room.bed;
		pr.patientId = patientId;
		pr.rate = room.rate;
		pr.startDate = room.startDate;
		pr.startTime = room.startTime;
		pr.endDate = room.endDate;
		pr.endTime = room.endTime;
		pr.save();
		
		redisplayRecord();
	}

}
