package springbean;

import java.util.List;

import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.Log;
import bean.Enrollment;
import bean.admin.AppConfig;
import bean.person.StudentSubject;
import bean.reference.Subject;

public class StudentSubjectToEnrollmentGrade {
	private List<Subject> allsubs;

	private Subject getSubject(String code) {
		for (Subject sub:allsubs) {
			if (code.equals(sub.code)) {
				return sub;
			}
		}
		return null;
	}

	public StudentSubjectToEnrollmentGrade(List<Subject> allsubs) {
		this.allsubs = allsubs;
	}

	private double getMapehUnit(List<StudentSubject> l) {
		double totalMAPEHUnits1 = 0;
		for (StudentSubject s:l) {
	        String mysub = s.subject.toUpperCase();
	        mysub = mysub.replaceAll("MAPEH", "");
	        mysub = mysub.replaceAll("MK", "");
			if (mysub.contains("MUSIC") || mysub.contains("ART") || mysub.contains("PE") || mysub.contains("HEALTH") || mysub.contains("GK")) {
				Subject subject = getSubject(s.subject);
				if (subject != null && subject.unit>0) {
					if (s.grade1>60) {
						totalMAPEHUnits1 += subject.unit;
					}
				}
			}
		}
		return totalMAPEHUnits1;
	}

	protected void putAllMapeh(Enrollment e, List<StudentSubject> l) {
		double totalMAPEHUnits1 = 0;
		double totalMAPEHUnits2 = 0;
		double totalMAPEHUnits3 = 0;
		double totalMAPEHUnits4 = 0;
		double totalMAPEH1 = 0;
		double totalMAPEH2 = 0;
		double totalMAPEH3 = 0;
		double totalMAPEH4 = 0;
		for (StudentSubject s:l) {
	        String mysub = s.subject.toUpperCase();
	        mysub = mysub.replaceAll("MAPEH", "");
	        mysub = mysub.replaceAll("MK", "");
			if (mysub.contains("MUSIC") || mysub.contains("ART") || mysub.contains("PE") || mysub.contains("HEALTH") || mysub.contains("GK")) {
				Subject subject = getSubject(s.subject);
				if (subject != null && subject.unit>0) {
					if (s.grade1>60) {
						totalMAPEHUnits1 += subject.unit;
						totalMAPEH1 += ((int) (s.grade1+.5)) * subject.unit;
					}
					if (s.grade2>60) {
						totalMAPEHUnits2 += subject.unit;
						totalMAPEH2 += ((int) (s.grade2+.5)) * subject.unit;
					}
					if (s.grade3>60) {
						totalMAPEHUnits3 += subject.unit;
						totalMAPEH3 += ((int) (s.grade3+.5)) * subject.unit;
					}
					if (s.grade4>60) {
						totalMAPEHUnits4 += subject.unit;
						totalMAPEH4 += ((int) (s.grade4+.5)) * subject.unit;
					}
				}
			}
		}
		if (totalMAPEH1>0 && totalMAPEHUnits1>0) e.q1MAPEH = totalMAPEH1/totalMAPEHUnits1;
		if (totalMAPEH2>0 && totalMAPEHUnits2>0) e.q2MAPEH = totalMAPEH2/totalMAPEHUnits2;
		if (totalMAPEH3>0 && totalMAPEHUnits3>0) e.q3MAPEH = totalMAPEH3/totalMAPEHUnits3;
		if (totalMAPEH4>0 && totalMAPEHUnits4>0) e.q4MAPEH = totalMAPEH4/totalMAPEHUnits4;
	}

    protected void putAllTEPP(Enrollment e, List<StudentSubject> l) {
		double totalMAPEHUnits1 = 0;
		double totalMAPEHUnits2 = 0;
		double totalMAPEHUnits3 = 0;
		double totalMAPEHUnits4 = 0;
		double totalMAPEH1 = 0;
		double totalMAPEH2 = 0;
		double totalMAPEH3 = 0;
		double totalMAPEH4 = 0;
		for (StudentSubject s:l) {
	        String mysub = s.subject.toUpperCase();
	        mysub = mysub.replaceAll("TEPP", "");
	       	mysub = mysub.replaceAll("MK", "");
		if (mysub.contains("TLE") || mysub.contains("COMPUTER")) {
				Subject subject = getSubject(s.subject);
				if (subject != null && subject.unit>0) {
					if (s.grade1>60) {
						totalMAPEHUnits1 += subject.unit;
						totalMAPEH1 += ((int) (s.grade1+.5)) * subject.unit;
					}
					if (s.grade2>60) {
						totalMAPEHUnits2 += subject.unit;
						totalMAPEH2 += ((int) (s.grade2+.5)) * subject.unit;
					}
					if (s.grade3>60) {
						totalMAPEHUnits3 += subject.unit;
						totalMAPEH3 += ((int) (s.grade3+.5)) * subject.unit;
					}
					if (s.grade4>60) {
						totalMAPEHUnits4 += subject.unit;
						totalMAPEH4 += ((int) (s.grade4+.5)) * subject.unit;
					}
				}
			}
		}
		if (totalMAPEH1>0 && totalMAPEHUnits1>0) e.q1TEPP = totalMAPEH1/totalMAPEHUnits1;
		if (totalMAPEH2>0 && totalMAPEHUnits2>0) e.q2TEPP = totalMAPEH2/totalMAPEHUnits2;
		if (totalMAPEH3>0 && totalMAPEHUnits3>0) e.q3TEPP = totalMAPEH3/totalMAPEHUnits3;
		if (totalMAPEH4>0 && totalMAPEHUnits4>0) e.q4TEPP = totalMAPEH4/totalMAPEHUnits4;
	}

	protected void putAllMakabayan(Enrollment e, List<StudentSubject> l) {
		double totalMAPEHUnits1 = 0;
		double totalMAPEHUnits2 = 0;
		double totalMAPEHUnits3 = 0;
		double totalMAPEHUnits4 = 0;
		double totalMAPEH1 = 0;
		double totalMAPEH2 = 0;
		double totalMAPEH3 = 0;
		double totalMAPEH4 = 0;
		for (StudentSubject s:l) {
	        String mysub = s.subject.toUpperCase();
	        mysub = mysub.replaceAll("MAPEH", "");
	        mysub = mysub.replaceAll("MK", "");
			if (mysub.contains("MUSIC") ||
					mysub.contains("ART") ||
					mysub.contains("PE") ||
					mysub.contains("HEALTH") ||
					mysub.contains("AP") ||
					mysub.contains("HEKASI") ||
					mysub.contains("SIBIKA") ||
					mysub.contains("TLE") ||
					mysub.contains("COMPUTER") ||
					mysub.contains("TEPP") ||
					mysub.contains("EP") ||
					mysub.contains("SOCIAL") ||
					mysub.contains("HELE") ||
					mysub.contains("HELE") ||
					mysub.contains("GK")) {
				Subject subject = getSubject(s.subject);
				if (subject != null && subject.unit>0) {
					if (s.grade1>60) {
						totalMAPEHUnits1 += subject.unit;
						totalMAPEH1 += ((int) (s.grade1+.5)) * subject.unit;
					}
					if (s.grade2>60) {
						totalMAPEHUnits2 += subject.unit;
						totalMAPEH2 += ((int) (s.grade2+.5)) * subject.unit;
					}
					if (s.grade3>60) {
						totalMAPEHUnits3 += subject.unit;
						totalMAPEH3 += ((int) (s.grade3+.5)) * subject.unit;
					}
					if (s.grade4>60) {
						totalMAPEHUnits4 += subject.unit;
						totalMAPEH4 += ((int) (s.grade4+.5)) * subject.unit;
					}
				}
			}
		}
		if (totalMAPEH1>0 && totalMAPEHUnits1>0) e.q1Makabayan = totalMAPEH1/totalMAPEHUnits1;
		if (totalMAPEH2>0 && totalMAPEHUnits2>0) e.q2Makabayan = totalMAPEH2/totalMAPEHUnits2;
		if (totalMAPEH3>0 && totalMAPEHUnits3>0) e.q3Makabayan = totalMAPEH3/totalMAPEHUnits3;
		if (totalMAPEH4>0 && totalMAPEHUnits4>0) e.q4Makabayan = totalMAPEH4/totalMAPEHUnits4;
	}

	protected void putAllSubjects(Enrollment e, List<StudentSubject> l) {
		double totalUnits1 = 0;
		double totalUnits2 = 0;
		double totalUnits3 = 0;
		double totalUnits4 = 0;
		double totalGPA1 = 0;
		double totalGPA2 = 0;
		double totalGPA3 = 0;
		double totalGPA4 = 0;
		for (StudentSubject s:l) {
			Subject subject = getSubject(s.subject);
			if (subject == null) {
				Log.out("NULL SUBJECT == ",s.subject," -> ",s.studentName);
			}
			if (subject != null && subject.unit>0) {
				if (s.grade1>60) {
					totalUnits1 += subject.unit;
					Log.out(e.student + " - " + e.gpa1,":",e.gpa2,":",e.gpa3,":",e.gpa4);
					totalGPA1 += ((int) (s.grade1+.5)) * subject.unit;
				}
				if (s.grade2>60) {
					totalUnits2 += subject.unit;
					totalGPA2 += ((int) (s.grade2+.5)) * subject.unit;
				}
				if (s.grade3>60) {
					totalUnits3 += subject.unit;
					totalGPA3 += ((int) (s.grade3+.5)) * subject.unit;
				}
				if (s.grade4>60) {
					totalUnits4 += subject.unit;
					totalGPA4 += ((int) (s.grade4+.5)) * subject.unit;
				}
			}
		}
		if (totalGPA1>0 && totalUnits1>0) e.gpa1 = DataUtil.getMoneyFormat(totalGPA1/totalUnits1);
		if (totalGPA2>0 && totalUnits2>0) e.gpa2 = DataUtil.getMoneyFormat(totalGPA2/totalUnits2);
		if (totalGPA3>0 && totalUnits3>0) e.gpa3 = DataUtil.getMoneyFormat(totalGPA3/totalUnits3);
		if (totalGPA4>0 && totalUnits4>0) e.gpa4 = DataUtil.getMoneyFormat(totalGPA4/totalUnits4);
	}

	private void calculateGPA(Enrollment e, List<StudentSubject> l) {
		e.gpaFinal = GPACalculator.calculateGPA(e);
		if (e.gpaFinal == -1) {
			if (AppConfig.isGradingGPAStraightAverage(e.gradeLevel)) {	//for ESS = "|P1|P2|K1|K2|N1|N2|"
				e.gpaFinal = DataUtil.getMoneyFormat( (e.gpa1+e.gpa2+e.gpa3+e.gpa4)/4 );
			}
			else {
				double totalUnits = 0;
				double totalGPAFinal = 0;
				for (StudentSubject s:l) {
					Subject subject = getSubject(s.subject);
					if (subject == null) {
						Log.out("NULL SUBJECT == ",s.subject," -> ",s.studentName);
					}
					if (AppConfig.isGradingGPATotalMapehThenAverage(e.gradeLevel)) {	//for ESS = "|H1|H2|H3|H4|"
				        String mysub = s.subject.toUpperCase();
				        mysub = mysub.replaceAll("MAPEH", "");
				        mysub = mysub.replaceAll("MK", "");
						if (mysub.contains("MUSIC") || mysub.contains("ART") || mysub.contains("PE") || mysub.contains("HEALTH") || mysub.contains("GK")) {
//							this is mapeh, which is causing the discrepancy
							Log.out("MAPEH SUBJECT " + s.subject);
							continue;
						}
					}
					if (subject != null && subject.unit>0) {
						totalUnits += subject.unit;
						totalGPAFinal += s.finalRating * subject.unit;
						Log.out(subject.code,"\t",s.finalRating,"\t",subject.unit);
					}
				}
				if (AppConfig.isGradingGPATotalMapehThenAverage(e.gradeLevel)) {	//for ESS = "|H1|H2|H3|H4|"
					double totalMapehUnit = getMapehUnit(l);
					totalGPAFinal += e.qallMAPEH * totalMapehUnit;
					totalUnits += totalMapehUnit;
					Log.out("MAPEH\t",e.qallMAPEH,"\t",totalMapehUnit);
				}
				Log.out(e.student,"\t",totalGPAFinal,"\t/",totalUnits,"\t=",(totalGPAFinal / totalUnits));
				e.gpaFinal = DataUtil.getMoneyFormat(totalGPAFinal/totalUnits);
			}
		}
	}

	private void setGrades(Enrollment e, StudentSubject s, String subjectName, int quarter) {
		if (quarter==1) {
			e.changeValue("q1" + subjectName, s.grade1);
		}
		if (quarter==2) {
			e.changeValue("q2" + subjectName, s.grade2);
		}
		if (quarter==3) {
			e.changeValue("q3" + subjectName, s.grade3);
		}
		if (quarter==4) {
			e.changeValue("q4" + subjectName, s.grade4);
		}
		e.changeValue("qall" + subjectName, s.finalRating);
	}

	public Enrollment setupEnrollmentGrade(StudentSubject subject, Enrollment e, int quarter) {
        if (e == null || e.studentId!=subject.studentId) {
        	Log.out("ERROR SUBJECT ENROLLMENT MATCHING FOR STUDENT ",subject.studentName,".");
        	return e;
        }
		List<StudentSubject> allStudSubjects = DBClient.getList(BeanUtil.concat("SELECT a FROM StudentSubject a WHERE a.gradeLevel='",e.gradeLevel,"' AND a.studentId=",e.studentId));
		putAllMapeh(e, allStudSubjects);
		putAllTEPP(e, allStudSubjects);
		putAllMakabayan(e, allStudSubjects);
		putAllSubjects(e, allStudSubjects);
		calculateGPA(e, allStudSubjects);

        String mysub = subject.subject.toUpperCase();
        mysub = mysub.replaceAll("MAPEH", "");
        mysub = mysub.replaceAll("MK", "");
    	Log.out(mysub);
        if (mysub.contains("ENGLISH3") || mysub.contains("ENGLISHC")) {
        	setGrades(e, subject, "English3", quarter);
        }
        else if (mysub.contains("ENGLISH2") || mysub.contains("ENGLISHB")) {
        	setGrades(e, subject, "English2", quarter);
        }
        else if (mysub.contains("ENGLISH") || mysub.contains("ENGLISH1") || mysub.contains("ENGLISHA")) {
        	setGrades(e, subject, "English", quarter);
        }
        else if (mysub.contains("FILIPINO")) {
        	setGrades(e, subject, "Filipino", quarter);
        }
        else if (mysub.contains("OP") && mysub.contains("MATH")) {
        	setGrades(e, subject, "OpMath", quarter);
        }
        else if (mysub.contains("READ")) {
        	setGrades(e, subject, "Reading", quarter);
        }
        else if (mysub.contains("LANG")) {
        	setGrades(e, subject, "Language", quarter);
        }
        else if (mysub.contains("MATH3") || mysub.contains("MATHEMATICS3") || mysub.contains("MATHEMATICSC")) {
        	setGrades(e, subject, "Math3", quarter);
        }
        else if (mysub.contains("MATH2") || mysub.contains("MATHEMATICS2") || mysub.contains("MATHEMATICSB")) {
        	setGrades(e, subject, "Math2", quarter);
        }
        else if (mysub.contains("MATH") || mysub.contains("MATH1") || mysub.contains("MATHEMATICS") || mysub.contains("MATHEMATICS1") || mysub.contains("MATHEMATICSA")) {
        	setGrades(e, subject, "Math", quarter);
        }
        else if (mysub.contains("RESEARCH")) {
        	setGrades(e, subject, "Research", quarter);
        }
        else if (mysub.contains("SCIENCE3") || mysub.contains("SCIENCEC")) {
        	setGrades(e, subject, "Science3", quarter);
        }
        else if (mysub.contains("SCIENCE2") || mysub.contains("SCIENCEB")) {
        	setGrades(e, subject, "Science2", quarter);
        }
        else if (mysub.contains("SCIENCE") || mysub.contains("SCIENCE1") || mysub.contains("SCIENCEA")) {
        	setGrades(e, subject, "Science", quarter);
        }
        else if (mysub.contains("COMPUTER")) {
        	setGrades(e, subject, "Computer", quarter);
        }
        else if (mysub.contains("ART")) {
        	setGrades(e, subject, "Arts", quarter);
        }
        else if (mysub.contains("HEKASI")) {
        	setGrades(e, subject, "Hekasi", quarter);
        }
        else if (mysub.contains("MUSIC")) {
        	setGrades(e, subject, "Music", quarter);
        }
        else if (mysub.contains("PE")) {
        	setGrades(e, subject, "PE", quarter);
        }
        else if (mysub.contains("RELIGION")) {
        	setGrades(e, subject, "CCF", quarter);
        }
        else if (mysub.contains("EP")) {
        	setGrades(e, subject, "CCF", quarter);
        }
        else if (mysub.contains("AP") || mysub.contains("ARALI")) {
        	setGrades(e, subject, "AP", quarter);
        }
        else if (mysub.contains("TLE")) {
        	setGrades(e, subject, "TLE", quarter);
        }
        else if (mysub.contains("HEALTH")) {
        	setGrades(e, subject, "Health", quarter);
        }
        else if (mysub.contains("GK") || mysub.contains("CAT")) {
        	setGrades(e, subject, "CE", quarter);
        }
        else if (mysub.contains("CHINESE") && mysub.contains("B")) {
        	setGrades(e, subject, "ChineseB", quarter);
        }
        else if (mysub.contains("CHINESE")) {
        	setGrades(e, subject, "ChineseA", quarter);
        }
        return e;
	}
}
