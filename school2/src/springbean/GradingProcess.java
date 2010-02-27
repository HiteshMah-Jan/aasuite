package springbean;

import java.util.ArrayList;
import java.util.List;

import service.util.AbstractIBean;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.PanelUtil;
import util.ThreadPoolUtil;
import bean.Enrollment;
import bean.Student;
import bean.admin.AppConfig;
import bean.person.StudentHonor;
import bean.person.StudentValuesGrading;
import bean.reference.Section;
import constants.UserInfo;

public class GradingProcess implements Runnable {
	String schoolYear;
	String section;
	int quarter;
	boolean runHonors;
	
	public GradingProcess(String schoolYear, String section, int quarter) {
		this.schoolYear = schoolYear;
		this.section = section;
		this.quarter = quarter;
                runHonors = true;
	}
	
	public static void rankAll(int quarter) {
		if (UserInfo.loginUser.isSuperAAA()) {
			if (PanelUtil.showPrompt(null, "Ranking for all section would take several minutes, continue?")) {
				List l = AbstractIBean.listCache("SELECT a FROM Subject a");
				boolean r = PanelUtil.showPrompt(null, "Run merits?");
				List<Section> lst = AbstractIBean.listCache("SELECT a FROM Section a");
				for (Section s:lst) {
					GradingProcess proc = new GradingProcess(AppConfig.getSchoolYear(), s.code, quarter);
					proc.runHonors = r;
					ThreadPoolUtil.execute(proc);
				}
			}
		}
	}
	
	public void run() {
		setupRank();
	}
	
	public void setupRank() {
		String gpaStr = "gpa"+quarter;
		if (quarter==5) {
			gpaStr = "gpaFinal";
		}
		String level = "";
		List sql = new ArrayList();
		int counter = 1;
		String s1 = "SELECT distinct round("+gpaStr+",2) from enrollment where schoolyear='"+schoolYear+"' and section='"+section+"' and "+gpaStr+" is not null order by "+gpaStr+" desc";
		List lst = DBClient.getListNative(s1);
		System.out.println(s1);
		for (Object obj:lst) {
			List l = (List) obj;
			double gpa = DataUtil.getDoubleValue(l.get(0).toString());
			String s = "UPDATE Enrollment set rankQ"+quarter+"="+counter+" WHERE schoolYear='"+schoolYear+"' AND section='"+section+"' AND round("+gpaStr+",2)="+gpa;
			System.out.println(s);
			sql.add(s);
			counter++;
		}
		DBClient.runBatchNative(sql);
		
		System.out.print("RANKING DONE.");
		PanelUtil.hideWaitFrame();
		
		if (runHonors) {
			setHonors();
		}
	}

	private StudentValuesGrading getValues(List<StudentValuesGrading> lst, int studentid) {
		for (StudentValuesGrading s:lst) {
			if (s.studentId==studentid) {
				return s;
			}
		}
		return null;
	}
	
	private Enrollment getEnrollment(List<Enrollment> lst, int studentid) {
		for (Enrollment s:lst) {
			if (s.studentId==studentid) {
				return s;
			}
		}
		return null;
	}

	public void setHonors() {
		PanelUtil.showWaitFrame("Check student merit.");
		System.out.println("Check student merit.");
		Section sec = (Section) DBClient.getFirstRecord("SELECT a FROM Section a WHERE a.code='"+section+"'");
		List<StudentValuesGrading> vallst = DBClient.getList("SELECT a FROM StudentValuesGrading a WHERE a.gradeLevel='"+sec.gradeLevel+"'");
		List<Enrollment> elst = DBClient.getList("SELECT a FROM Enrollment a WHERE a.gradeLevel='"+sec.gradeLevel+"'");
		
//		1. get all enrolled students
		List<Student> lst = DBClient.getList("SELECT a FROM Student a WHERE a.status='ENROLLED' AND a.section='"+section+"'");
		for (Student s:lst) {
			String sql = "UPDATE Enrollment SET meritQ"+quarter+"='' WHERE studentId="+s.personId+" AND gradeLevel='"+s.gradeLevel+"'";
	    	DBClient.runSQLNative(sql);
			StudentValuesGrading val = getValues(vallst, s.personId);
			Enrollment e = getEnrollment(elst, s.personId);

//			2. check all subjects with regards to current grade level
			if ("|K1|K2|N1|N2|P1|P2|".contains(s.gradeLevel)) {
				checkPre(s, val, quarter, e);
			}
			else if ("|G1|G2|G3|G4|".contains(s.gradeLevel)) {
				checkG1toG4(s, val, quarter, e);
			}
			else if ("|G5|G6|".contains(s.gradeLevel)) {
				checkG5toG6(s, val, quarter, e);
			}
			else if ("|H1|H2|H3|H4|".contains(s.gradeLevel)) {
				checkH1toH4(s, val, quarter, e);
			}
		}
		PanelUtil.hideWaitFrame();
	}

	protected void checkH1toH4(Student s, StudentValuesGrading val, int quarter, Enrollment e) {
		String[] grades = {"OpMath","Math","Science","English","Filipino","Reading","Writing","Computer","CCF","AP","Hekasi","TLE","CE","ME","MAPEH","ChineseA","ChineseB"};
		AveLow ave = isHonor(s, 89, 85, quarter, grades, e);
		System.out.println(ave.average);
		if (ave.isHonor) {
//			check values
			AveLow values2 = isValuesHonor(s, val, 4, 3, quarter, "pfe","cra","ca","kin","ec1","ec2");
			if (!values2.isHonor) {
				return;
			}
			AveLow values = isValuesHonor(s, val, 4, 3, quarter, "els", "wfr", "apgw", "spaa","iva", "isl", "aspvi", "hspd", "sd", "put","hlew","prs","cr","cws");
			if (values.isHonor) {
//				create or update record in StudentHonor
				StudentHonor honor = (StudentHonor) DBClient.getFirstRecord("SELECT a FROM StudentHonor a WHERE a.schoolYear='"+schoolYear+"' AND a.section='"+s.section+"'");
				if (honor==null) {
					honor = new StudentHonor();
					honor.studentId = s.personId;
					honor.schoolYear = AppConfig.getSchoolYear();
					honor.gradeLevel = s.gradeLevel;
				}
				honor.studentName = s.toString();
				honor.section = s.section;
				String honorType = "";
				if (ave.average>=93 && ave.lowest>=87 && values.average>=4 && values.lowest>=3 && values2.average>=4 && values2.lowest>=3) {
					honorType = "GREEN";
				}
				else if (ave.average>=91 && ave.lowest>=86 && values.average>=4 && values.lowest>=3 && values2.average>=4 && values2.lowest>=3) {
					honorType = "YELLOW";
				}
				else {
					honorType = "WHITE";
				}
				String strQ = "q"+quarter;
				if (quarter==5) {
					strQ = "final";
				}
				honor.changeValue(strQ+"Honor", honorType);
				honor.changeValue(strQ+"AchievementGPA", (int)ave.average);
				honor.changeValue(strQ+"AchievementLowestGrade", (int)ave.lowest);
				honor.changeValue(strQ+"Values1GPA", (int)values.average);
				honor.changeValue(strQ+"Values1LowestGrade", (int)values.lowest);
				honor.changeValue(strQ+"Values2GPA", (int)values2.average);
				honor.changeValue(strQ+"Values2LowestGrade", (int)values2.lowest);
				honor.save();
			}
		}
	}

	protected void checkG5toG6(Student s, StudentValuesGrading val, int quarter, Enrollment e) {
		String[] grades = {"OpMath","Math","Science","English","Filipino","Reading","Writing","Computer","CCF","AP","Hekasi","TLE","CE","Music","Arts","PE","ME","MAPEH","ChineseA","ChineseB"};
		AveLow ave = isHonor(s, 89, 86, quarter, grades, e);
		if (ave.isHonor) {
//			check values
			AveLow values2 = isValuesHonor(s, val, 3, 3, quarter, "pfe","cra","ca","kin","ec1","ec2");
			if (!values2.isHonor) {
				return;
			}
			AveLow values = isValuesHonor(s, val, 3, 3, quarter, "els", "wfr", "apgw", "spaa","iva", "isl", "aspvi", "hspd", "sd", "put","hlew","prs","cr","cws");
			if (values.isHonor) {
//				create or update record in StudentHonor
				StudentHonor honor = (StudentHonor) DBClient.getFirstRecord("SELECT a FROM StudentHonor a WHERE a.schoolYear='"+schoolYear+"' AND a.section='"+s.section+"'");
				if (honor==null) {
					honor = new StudentHonor();
					honor.studentId = s.personId;
					honor.schoolYear = AppConfig.getSchoolYear();
					honor.gradeLevel = s.gradeLevel;
				}
				honor.studentName = s.toString();
				honor.section = s.section;
				String honorType = "";
				if (ave.average>=93 && ave.lowest>=89 && values.average>=5 && values.lowest>=4 && values2.average>=5 && values2.lowest>=4) {
					honorType = "GREEN";
				}
				else if (ave.average>=91 && ave.lowest>=87 && values.average>=4 && values.lowest>=3 && values2.average>=4 && values2.lowest>=3) {
					honorType = "YELLOW";
				}
				else {
					honorType = "WHITE";
				}
				String strQ = "q"+quarter;
				if (quarter==5) {
					strQ = "final";
				}
				honor.changeValue(strQ+"Honor", honorType);
				honor.changeValue(strQ+"AchievementGPA", (int)ave.average);
				honor.changeValue(strQ+"AchievementLowestGrade", (int)ave.lowest);
				honor.changeValue(strQ+"Values1GPA", (int)values.average);
				honor.changeValue(strQ+"Values1LowestGrade", (int)values.lowest);
				honor.changeValue(strQ+"Values2GPA", (int)values2.average);
				honor.changeValue(strQ+"Values2LowestGrade", (int)values2.lowest);
				honor.save();
			}
		}
	}

	protected void checkG1toG4(Student s, StudentValuesGrading val, int quarter, Enrollment e) {
    	String[] grades = {"OpMath","Math","Science","English","Filipino","Reading","Writing","Computer","CCF","AP","Hekasi","TLE","CE","Music","Arts","PE","ME","MAPEH","ChineseA","ChineseB"};
		AveLow ave = isHonor(s, 89, 86, quarter, grades, e);
		if (ave.isHonor) {
//			check values
			AveLow values = isValuesHonor(s, val, 3, 3, quarter, "con", "mot", "eff", "res","ini", "per","car","tea","com","pro","focus","respect");
			if (values.isHonor) {
//				create or update record in StudentHonor
				StudentHonor honor = (StudentHonor) DBClient.getFirstRecord("SELECT a FROM StudentHonor a WHERE a.schoolYear='"+schoolYear+"' AND a.section='"+s.section+"'");
				if (honor==null) {
					honor = new StudentHonor();
					honor.studentId = s.personId;
					honor.schoolYear = AppConfig.getSchoolYear();
					honor.gradeLevel = s.gradeLevel;
				}
				honor.studentName = s.toString();
				honor.section = s.section;
				String honorType = "";
				if (ave.average>=93 && ave.lowest>=89 && values.average>=5 && values.lowest>=4) {
					honorType = "GREEN";
				}
				else if (ave.average>=91 && ave.lowest>=87 && values.average>=4 && values.lowest>=3) {
					honorType = "YELLOW";
				}
				else {
					honorType = "WHITE";
				}
				String strQ = "q"+quarter;
				if (quarter==5) {
					strQ = "final";
				}
				honor.changeValue(strQ+"Honor", honorType);
				honor.changeValue(strQ+"AchievementGPA", (int)ave.average);
				honor.changeValue(strQ+"AchievementLowestGrade", (int)ave.lowest);
				honor.changeValue(strQ+"Values1GPA", (int)values.average);
				honor.changeValue(strQ+"Values1LowestGrade", (int)values.lowest);
				honor.save();
			}
		}
	}

	protected void checkPre(Student s, StudentValuesGrading val, int quarter, Enrollment e) {
    	String[] grades = {"OpMath","Math","Science","English","Filipino","Reading","Writing","Computer","CCF","AP","Hekasi","TLE","CE","Music","Arts","PE","ME","MAPEH","ChineseA","ChineseB"};
		AveLow ave = isHonor(s, 89, 86, quarter, grades, e);
		if (ave.isHonor) {
//			check values
			AveLow values2 = isValuesHonor(s, val, 3, 3, quarter, "ap","ce","tc","cfp","df","wlr","cst","ocs","cwp","cp","pa");
			if (!values2.isHonor) {
				return;
			}
			AveLow values = isValuesHonor(s, val, 3, 3, quarter, "kp","fh","fr","aw","ft","ic","ih","wi","id","neatAndOrganize","de","wp","hs","ir","ls");
			if (values.isHonor) {
//				create or update record in StudentHonor
				StudentHonor honor = (StudentHonor) DBClient.getFirstRecord("SELECT a FROM StudentHonor a WHERE a.schoolYear='"+schoolYear+"' AND a.section='"+s.section+"'");
				if (honor==null) {
					honor = new StudentHonor();
					honor.studentId = s.personId;
					honor.schoolYear = AppConfig.getSchoolYear();
					honor.gradeLevel = s.gradeLevel;
				}
				honor.studentName = s.toString();
				honor.section = s.section;
				String honorType = "";
				if (ave.average>=93 && ave.lowest>=89 && values.average>=5 && values.lowest>=4 && values2.average>=5 && values2.lowest>=4) {
					honorType = "GREEN";
				}
				else if (ave.average>=91 && ave.lowest>=87 && values.average>=4 && values.lowest>=3 && values2.average>=4 && values2.lowest>=3) {
					honorType = "YELLOW";
				}
				else {
					honorType = "WHITE";
				}
				String strQ = "q"+quarter;
				if (quarter==5) {
					strQ = "final";
				}
				honor.changeValue(strQ+"Honor", honorType);
				honor.changeValue(strQ+"AchievementGPA", (int)ave.average);
				honor.changeValue(strQ+"AchievementLowestGrade", (int)ave.lowest);
				honor.changeValue(strQ+"Values1GPA", (int)values.average);
				honor.changeValue(strQ+"Values1LowestGrade", (int)values.lowest);
				honor.save();
			}
		}
	}

	protected AveLow isValuesHonor(Student s, StudentValuesGrading val, int average, int lowest, int quarter, String... fields) {
		AveLow avelow = new AveLow();
		String suffix = quarter+"";
		if (quarter==1) {
			suffix = "";
		}
		for (String str:fields) {
			double grade = BeanUtil.getDoubleValue(val, str+suffix);
			if (grade>0) {
				if (grade<lowest) {
					avelow.isHonor = false;
					return avelow;
				}
			}
		}
//		get average
		double total = 0;
		double totalUnit = 0;
		double lowGrade = -1;
		for (String str:fields) {
			double grade = BeanUtil.getDoubleValue(val, str+suffix);
			if (grade>0) {
				total += grade;
				totalUnit++;
				if (lowGrade==-1) {
					lowGrade = grade;
				}
				else if (lowGrade>grade){
					lowGrade = grade;
				}
			}
		}
		total = (int) ((total/totalUnit)+.5);
		if (total>=average) {
			avelow.average = total;
			avelow.lowest = lowGrade;
			avelow.isHonor = true;
			return avelow;
		}
		avelow.isHonor = false;
		return avelow;
	}
	
	protected AveLow isHonor(Student s, double average, int lowest, int quarter, String[] grades, Enrollment e) {
		AveLow avelow = new AveLow();
//		check lowest, use enrollment
		double lowGrade = -1;
		for (String sub:grades) {
			System.out.println("SUBJECT = "+sub);
			try {
				int grade = (int) (BeanUtil.getDoubleValue(e, "q"+quarter+sub)+.5);
				if (grade<=60) {
					continue;
				}
				if (grade<lowest) {
					avelow.isHonor = false;
					return avelow;
				}
				if (lowGrade==-1) {
					lowGrade = grade;
				}
				else if (lowGrade>grade){
					lowGrade = grade;
				}
			}
			catch (Exception exp) {
			}
		}
		try {
			double total = BeanUtil.getDoubleValue(e, "gpa"+quarter);
			if (total>=average) {
				avelow.average = total;
				avelow.lowest = lowGrade;
				avelow.isHonor = true;
				return avelow;
			}
		}
		catch (Exception exp) {
		}
		avelow.isHonor = false;
		return avelow;
	}
	
	private static class AveLow {
		double average;
		double lowest;
		boolean isHonor;
	}	
}
