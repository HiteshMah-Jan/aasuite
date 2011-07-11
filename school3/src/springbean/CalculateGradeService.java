package springbean;

import java.util.ArrayList;
import java.util.List;

import service.IService;
import service.ParamStruct;
import service.ReturnStruct;
import service.util.AbstractIBean;
import service.util.CallService;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.Log;
import util.ThreadPoolUtil;
import bean.Enrollment;
import bean.Schedule;
import bean.admin.AppConfig;
import bean.person.FacultyGradingTask;
import bean.person.StudentSubject;
import bean.person.StudentSubjectDetailGrading;
import bean.reference.Subject;

public class CalculateGradeService implements IService {
	public static void calculateGrade(int quarter, FacultyGradingTask task, List<StudentSubjectDetailGrading> subjects) {
		List<Object> l = new ArrayList<Object>();
		l.add(task);
		l.add(subjects);
		Log.out("TEST");
		CallService.callService(l, quarter, CalculateGradeService.class.getName());
	}
	
	public static void calculateGrade(int quarter, Schedule sched, List<StudentSubject> subjects) {
		List<Object> l = new ArrayList<Object>();
		l.add(sched);
		l.add(subjects);
		Log.out("TEST");
		CallService.callService(l, quarter, CalculateGradeService.class.getName());
	}

	public ReturnStruct callService(ParamStruct param) {
		List<Subject> allsubs = AbstractIBean.listCache("SELECT a FROM Subject a");
		int quarter = param.getActionCommand();
		List<Object> l = (List<Object>) param.getData();
		Object obj = l.get(0);
		if (obj instanceof FacultyGradingTask) {
			FacultyGradingTask task = (FacultyGradingTask) l.get(0);
			List<StudentSubjectDetailGrading> subjects = (List<StudentSubjectDetailGrading>) l.get(1);
			for (StudentSubjectDetailGrading det:subjects) {
				det.scheduleId = task.scheduleId;
				det.section = task.section;
				det.subject = task.subject;
				det.recalculateGrade();
				det.save();
				
//				update student subject
				Log.out("SUBJECT TO USE " + det.subject);
				StudentSubject subject = (StudentSubject) DBClient.getFirstRecord("SELECT a FROM StudentSubject a WHERE a.studentId=",det.studentId," AND a.subject='",det.subject,"'");
				subject.changeValue(BeanUtil.concat("grade",quarter), getTotalShares(quarter, det.studentId, subject.subject));
				subject.save();
				
//				update enrollment
				Enrollment e = (Enrollment) DBClient.getFirstRecord("SELECT a FROM Enrollment a WHERE a.studentId=",det.studentId," AND a.gradeLevel='",task.gradeLevel,"'");
				if (e != null) {
					e = new StudentSubjectToEnrollmentGrade(allsubs).setupEnrollmentGrade(subject, e, quarter);
					e.section = task.section;
					e.save();
				}
			}
			ThreadPoolUtil.execute(new GradingProcess(task.schoolYear, task.section, quarter));
		}
		else {
			Schedule schedule = (Schedule) l.get(0);
			List<StudentSubject> subjects = (List<StudentSubject>) l.get(1);
			for (StudentSubject subject:subjects) {
//				update student subject
				subject.save();
				
//				update enrollment
				Enrollment e = (Enrollment) DBClient.getFirstRecord("SELECT a FROM Enrollment a WHERE a.studentId=",subject.studentId," AND a.gradeLevel='",schedule.gradeLevel,"'");
				if (e != null) {
					e = new StudentSubjectToEnrollmentGrade(allsubs).setupEnrollmentGrade(subject, e, quarter);
					e.section = schedule.section;
					e.save();
				}
			}
			ThreadPoolUtil.execute(new GradingProcess(AppConfig.getSchoolYear(), schedule.section, quarter));
		}
		return null;
	}

	private double getTotalShares(int quarter, int studentId, String subject) {
		double val = DBClient.getSingleColumnDouble("SELECT SUM(gradeShareQ",quarter,") FROM StudentSubjectDetailGrading WHERE studentId=",studentId," AND subject='",subject,"'");
		if (val < 70 && val > 60) {
			val = 70;
		}
		return DataUtil.round(val);
	}
}
