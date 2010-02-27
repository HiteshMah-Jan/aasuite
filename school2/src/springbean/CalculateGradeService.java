package springbean;

import java.util.ArrayList;
import java.util.List;

import service.IService;
import service.ParamStruct;
import service.ReturnStruct;
import service.util.CallService;
import util.DBClient;
import util.ThreadPoolUtil;
import bean.Enrollment;
import bean.person.FacultyGradingTask;
import bean.person.StudentSubject;
import bean.person.StudentSubjectDetailGrading;
import bean.reference.Subject;

public class CalculateGradeService implements IService {
	public static void calculateGrade(int quarter, FacultyGradingTask task, List<StudentSubjectDetailGrading> subjects) {
		List<Object> l = new ArrayList<Object>();
		l.add(task);
		l.add(subjects);
		System.out.println("TEST");
		CallService.callService(l, quarter, CalculateGradeService.class.getName());
	}
	
	@Override
	public ReturnStruct callService(ParamStruct param) {
		int quarter = param.getActionCommand();
		List<Object> l = (List<Object>) param.getData();
		FacultyGradingTask task = (FacultyGradingTask) l.get(0);
		List<StudentSubjectDetailGrading> subjects = (List<StudentSubjectDetailGrading>) l.get(1);
		List<Subject> allsubs = DBClient.getList("SELECT a FROM Subject a");
		for (StudentSubjectDetailGrading det:subjects) {
			det.scheduleId = task.scheduleId;
			det.section = task.section;
			det.subject = task.subject;
			det.recalculateGrade();
			det.save();
			
//			update student subject
			System.out.println("SUBJECT TO USE " + det.subject);
			StudentSubject subject = (StudentSubject) DBClient.getFirstRecord("SELECT a FROM StudentSubject a WHERE a.studentId="+det.studentId+" AND a.subject='"+det.subject+"'");
			subject.changeValue("grade"+quarter, getTotalShares(quarter, det.studentId, subject.subject));
			subject.save();
			
//			update enrollment
			Enrollment e = (Enrollment) DBClient.getFirstRecord("SELECT a FROM Enrollment a WHERE a.studentId="+det.studentId+" AND a.gradeLevel='"+task.gradeLevel+"'");
			if (e != null) {
				new StudentSubjectToEnrollmentGrade(allsubs).setupEnrollmentGrade(subject, e);
				e.save();
			}
		}
		ThreadPoolUtil.execute(new GradingProcess(task.schoolYear, task.section, quarter));
		return null;
	}

	private double getTotalShares(int quarter, int studentId, String subject) {
		double val = DBClient.getSingleColumnDouble("SELECT SUM(gradeShareQ"+quarter+") FROM StudentSubjectDetailGrading WHERE studentId="+studentId+" AND subject='"+subject+"'");
		if (val < 70) {
			val = 70;
		}
		return val;
	}
}
