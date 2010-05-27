package springbean;

import util.DBClient;
import bean.Enrollment;
import bean.person.StudentSubject;
import bean.person.StudentValuesGrading;

public class StudentValuesToEnrollmentGrade {
	public void setupEnrollmentGrade(StudentSubject subject) {
		StudentValuesGrading e = (StudentValuesGrading) DBClient.getFirstRecord("SELECT a FROM StudentValuesGrading a WHERE a.schoolYear='",subject.schoolYear,"' AND a.studentId=",subject.studentId);
		if (e!=null) {
			setupEnrollmentGrade(e);
		}
	}
	
	public void setupEnrollmentGrade(StudentValuesGrading subject) {
        Enrollment e = (Enrollment) DBClient.getFirstRecord("SELECT a FROM Enrollment a WHERE a.gradeLevel='",subject.gradeLevel,"' AND a.studentId=",subject.studentId);
        if (e==null) {
        	e = new Enrollment();
        	e.schoolYear = subject.schoolYear;
        	e.gradeLevel = subject.gradeLevel;
        	e.studentId = subject.studentId;
        	e.save();
        }
//        e.q1ZL
        e.q1AttendanceAbsent = subject.absent;
        e.q1AttendanceNoOfDays = subject.totalDays;
        e.q1AttendanceTardy = subject.tardy;
//        e.q1VP
//      e.q2ZL
        e.q2AttendanceAbsent = subject.absent2;
        e.q2AttendanceNoOfDays = subject.totalDays2;
        e.q2AttendanceTardy = subject.tardy2;
//        e.q2VP
//      e.q3ZL
        e.q3AttendanceAbsent = subject.absent3;
        e.q3AttendanceNoOfDays = subject.totalDays3;
        e.q3AttendanceTardy = subject.tardy3;
//        e.q3VP
//      e.q4ZL
        e.q4AttendanceAbsent = subject.absent4;
        e.q4AttendanceNoOfDays = subject.totalDays4;
        e.q4AttendanceTardy = subject.tardy4;
//        e.q4VP
        e.save();
	}
}
