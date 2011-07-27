package rule.grading;

import java.util.ArrayList;
import java.util.List;

import util.BeanUtil;
import util.DBClient;

import bean.Student;
import bean.person.StudentSubjectDetailGrading;
import bean.reference.SubjectGradingCriteria;

public abstract class AbstractGradeComputation {
	protected Student stud;
	protected String schoolYear;
	
	public AbstractGradeComputation(Student stud, String schoolYear) {
		this.stud = stud;
		this.schoolYear = schoolYear;
	}
	
	public abstract double computeQuarter1();
	public abstract double computeQuarter2();
	public abstract double computeQuarter3();
	public abstract double computeQuarter4();
	public abstract double computeFinalRating();
	
	List<StudentSubjectDetailGrading> allDetail;
	static List<SubjectGradingCriteria> allCriteria;
	
	private List<StudentSubjectDetailGrading> getDetail(int quarter, String subject, String criteria) {
		if (allDetail==null) {
			allDetail = DBClient.getList(BeanUtil.concat("SELECT a FROM StudentSubjectDetailGrading a WHERE a.schoolYear='",schoolYear,"' AND a.studentId=",stud.seq));
		}
		List<StudentSubjectDetailGrading> tmp = new ArrayList();
		for (StudentSubjectDetailGrading sub:allDetail) {
//			if (quarter!=sub.quarter) continue;
//			if (!subject.equalsIgnoreCase(sub.subject)) continue;
//			if (sub.subjectGradingCriteria.toUpperCase().startsWith(criteria)) {
//				tmp.add(sub);
//			}
		}
		return tmp;
	}

	private double getPercentage(String subject, String criteria) {
		if (allCriteria==null) {
			allCriteria = DBClient.getList("SELECT a FROM SubjectGradingCriteria a");
		}
		for (SubjectGradingCriteria s:allCriteria) {
			if (subject.equalsIgnoreCase(s.subject) && criteria.equalsIgnoreCase(s.criteria)) return s.percentage; 
		}
		return -1;
	}

	private List<String> getCriteria(String subject) {
		if (allCriteria==null) {
			allCriteria = DBClient.getList("SELECT a FROM SubjectGradingCriteria a");
		}
		List<String> lst = new ArrayList();
		for (SubjectGradingCriteria s:allCriteria) {
			if (subject.equalsIgnoreCase(s.subject)) {
				lst.add(s.criteria);
			}
		}
		return lst;
	}

	protected double getComponentShare(int quarter, String subject, String criteria) {
		List<StudentSubjectDetailGrading> lst = getDetail(quarter, subject, criteria);
		double percentage = getPercentage(subject, criteria);
		int s = lst.size();
		double total = 0;
//		for (StudentSubjectDetailGrading sub:lst) {
//			total = sub.computedGrade;
//		}
		return (total/s)*(percentage/100);
	}

	public double getGenericGrade(int quarter, String subject) {
		double share = 0;
		List<String> lst = getCriteria(subject);
		for (String s:lst) {
			share += getComponentShare(quarter, subject, s);
		}
		return share;
	}

	public double getFilipino(int quarter) {
		String subject = "FILIPINO";
		double share = getComponentShare(quarter, subject, "MAIKLI");
		share += getComponentShare(quarter, subject, "GP");
		share += getComponentShare(quarter, subject, "PART");
		share += getComponentShare(quarter, subject, "SUL");
		share += getComponentShare(quarter, subject, "T");
		share += getComponentShare(quarter, subject, "QT");
		return share;
	}
}
