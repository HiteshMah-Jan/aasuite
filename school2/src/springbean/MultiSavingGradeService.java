package springbean;

import java.util.ArrayList;
import java.util.List;

import service.IService;
import service.ParamStruct;
import service.ReturnStruct;
import service.util.CallService;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.Log;
import bean.Enrollment;
import bean.person.FacultyGradingTask;
import bean.person.StudentSubject;
import bean.person.StudentSubjectDetailGrading;
import bean.reference.Subject;

public class MultiSavingGradeService implements IService {
	static List<Subject> allsubs = DBClient.getList("SELECT a FROM Subject a");

	public static void calculateGrade(int quarter, FacultyGradingTask task, List<StudentSubjectDetailGrading> subjects) {
		List<Object> l = new ArrayList<Object>();
		l.add(task);
		l.add(subjects);
		Log.out("TEST");
		CallService.callService(l, quarter, MultiSavingGradeService.class.getName());
	}
	
	@Override
	public ReturnStruct callService(ParamStruct param) {
		int quarter = param.getActionCommand();
		List<Object> l = (List<Object>) param.getData();
		FacultyGradingTask task = (FacultyGradingTask) l.get(0);
		List<StudentSubjectDetailGrading> subjects = (List<StudentSubjectDetailGrading>) l.get(1);
		for (StudentSubjectDetailGrading det:subjects) {
//			update student subject
			Log.out("SUBJECT TO USE " + det.subject);
			StudentSubject subject = (StudentSubject) DBClient.getFirstRecord("SELECT a FROM StudentSubject a WHERE a.studentId=",det.studentId," AND a.subject='",det.subject,"'");
			subject.changeValue(BeanUtil.concat("grade",quarter), getTotalShares(quarter, det.studentId, subject.subject));
			subject.save();
			
//			update enrollment
			Enrollment e = (Enrollment) DBClient.getFirstRecord("SELECT a FROM Enrollment a WHERE a.studentId=",det.studentId," AND a.gradeLevel='",task.gradeLevel,"'");
			if (e != null) {
				e = new StudentSubjectToEnrollmentGrade(allsubs).setupEnrollmentGrade(subject, e, quarter);
				e.save();
			}
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
