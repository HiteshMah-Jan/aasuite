/*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.person;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.PanelUtil;
import bean.Person;
import bean.Student;
import bean.admin.AppConfig;
import bean.reference.Subject;
import constants.UserInfo;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentSubject")
@UITemplate(
    columnSearch={"studentId","schoolYear","subject","grade1","grade2","grade3","grade4","finalRating"}, 
    gridCount=6, title="Subjects")
@Displays({
    @Display(name="gradeLevel", width=100, label="Grade", type="Combo", modelCombo={"1","2","3","4","5","6","7","8","9","10"}),
    @Display(name="schoolYear", width=100, label="Year"),
//    @Display(name="preferredSemester", width=100, label="Semester", type="Combo", modelCombo={"1","2","3"}),
    @Display(name="subject", type="PopSearch", linktoBean=Subject.class, gridFieldWidth=3, width=-1),
    @Display(name="passed"),
    @Display(name="grade1",label="1st"),
    @Display(name="grade2",label="2nd"),
    @Display(name="grade3",label="3rd"),
    @Display(name="grade4",label="4th"),
    @Display(name="finalRating",label="Final Rating"),
    @Display(name="actionTaken",label="Action Taken", type="Combo", modelCombo={"PASSED","FAILED","DO","DU"}),

    @Display(name="preSubject1", label="1", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="preSubject2",label="2", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="preSubject3",label="3", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="preSubject4",label="4", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="preSubject5",label="5", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="coSubject1", label="1", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="coSubject2",label="2", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="coSubject3",label="3", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="coSubject4",label="4", type = "PopSearch", linktoBean = Subject.class, width=300),
    @Display(name="coSubject5",label="5", type = "PopSearch", linktoBean = Subject.class, width=300)
})
//@ActionButtons({
//    @ActionButton(name = "btnCalculateQ1", label = "Calculate Q1"),
//    @ActionButton(name = "btnCalculateQ1", label = "Q2"),
//    @ActionButton(name = "btnCalculateQ1", label = "Q3"),
//    @ActionButton(name = "btnCalculateQ1", label = "Q4"),
//    @ActionButton(name = "btnCalculateFinal", label = "Final Rating"),
//})
 @DisplayGroups({
    @DisplayGroup(gridCount = 2, title = "Prerequisite Subject",  fields = {
        "preSubject1","preSubject2","preSubject3","preSubject4","preSubject5"
    }, addInfoOnly=true),
     @DisplayGroup(gridCount = 2, title = "Corequisite Subject",  fields = {
        "coSubject1","coSubject2","coSubject3","coSubject4","coSubject5"
    }, addInfoOnly=true)}
)
public class StudentSubject extends AbstractIBean implements Serializable {

	@Override
	public int getRecordListCount() {
		return 100;
	}

	@Override
	public void delete() {
		if (UserInfo.canDeleteGrade()) {
			super.delete();
		}
		else {
			PanelUtil.showError(null, "You do not have duty code [HAS DELETE GRADE] to delete component.");
		}
	}
	
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "studentId", nullable = false)
    public int studentId;
    @Column(name = "section")
    public String section;
    @Column(name = "subject", length = 100)
    public String subject;
    @Column(name = "schoolYear", length = 20)
    public String schoolYear;
    @Column(name = "preferredSemester")
    public int preferredSemester;
    @Column(name = "passed")
    public boolean passed;
    @Column(name = "enrollmentId")
    public int enrollmentId;
    @Column(name = "scheduleId")
    public int scheduleId;
    @Column(name = "facultyId")
    public int facultyId;
    @Column(name = "faculty")
    public String faculty;
    @Column(name = "course")
    public String course;
    @Column(name = "actionTaken")
    public String actionTaken;
    @Column(name = "grade1")
    public Double grade1=0.0;
    @Column(name = "grade2")
    public Double grade2=0.0;
    @Column(name = "grade3")
    public Double grade3=0.0;
    @Column(name = "grade4")
    public Double grade4=0.0;
    @Column(name = "finalRating")
    public double finalRating;
    @Column(name = "tempGrade1")
    public double tempGrade1;
    @Column(name = "tempGrade2")
    public double tempGrade2;
    @Column(name = "tempGrade3")
    public double tempGrade3;
    @Column(name = "tempGrade4")
    public double tempGrade4;
    @Column(name = "finalTempRating")
    public double finalTempRating;

    @Column(name = "rankQ1")
    public int rankQ1;
    @Column(name = "rankQ2")
    public int rankQ2;
    @Column(name = "rankQ3")
    public int rankQ3;
    @Column(name = "rankQ4")
    public int rankQ4;
    @Column(name = "rankFinal")
    public int rankFinal;

    @Column(name = "gradeLevel")
    public String gradeLevel;
    @Column(name = "studentName")
    public String studentName;

    @Column(name = "lockQ1")
    public boolean lockQ1;
    @Column(name = "lockQ2")
    public boolean lockQ2;
    @Column(name = "lockQ3")
    public boolean lockQ3;
    @Column(name = "lockQ4")
    public boolean lockQ4;

    @Column(name = "unit")
    public double unit;
    @Column(name = "unitShareQ1")
    public double unitShareQ1;
    @Column(name = "unitShareQ2")
    public double unitShareQ2;
    @Column(name = "unitShareQ3")
    public double unitShareQ3;
    @Column(name = "unitShareQ4")
    public double unitShareQ4;
    @Column(name = "unitShareFinal")
    public double unitShareFinal;

    @Column(name = "preSubject1", length = 100)
    public String preSubject1;
    @Column(name = "preSubject2", length = 100)
    public String preSubject2;
    @Column(name = "preSubject3", length = 100)
    public String preSubject3;
    @Column(name = "preSubject4", length = 100)
    public String preSubject4;
    @Column(name = "preSubject5", length = 100)
    public String preSubject5;
    @Column(name = "coSubject1", length = 100)
    public String coSubject1;
    @Column(name = "coSubject2", length = 100)
    public String coSubject2;
    @Column(name = "coSubject3", length = 100)
    public String coSubject3;
    @Column(name = "coSubject4", length = 100)
    public String coSubject4;
    @Column(name = "coSubject5", length = 100)
    public String coSubject5;

    public double getUnitShareFinal() {
		return unitShareFinal;
	}

	public void setUnitShareFinal(double unitShareFinal) {
		this.unitShareFinal = unitShareFinal;
	}

	public double getUnitShareQ1() {
		return unitShareQ1;
	}

	public void setUnitShareQ1(double unitShareQ1) {
		this.unitShareQ1 = unitShareQ1;
	}

	public double getUnitShareQ2() {
		return unitShareQ2;
	}

	public void setUnitShareQ2(double unitShareQ2) {
		this.unitShareQ2 = unitShareQ2;
	}

	public double getUnitShareQ3() {
		return unitShareQ3;
	}

	public void setUnitShareQ3(double unitShareQ3) {
		this.unitShareQ3 = unitShareQ3;
	}

	public double getUnitShareQ4() {
		return unitShareQ4;
	}

	public void setUnitShareQ4(double unitShareQ4) {
		this.unitShareQ4 = unitShareQ4;
	}

	public void setUnit(double unit) {
		this.unit = unit;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public boolean isLockQ1() {
		return lockQ1;
	}

	public void setLockQ1(boolean lockQ1) {
		this.lockQ1 = lockQ1;
	}

	public boolean isLockQ2() {
		return lockQ2;
	}

	public void setLockQ2(boolean lockQ2) {
		this.lockQ2 = lockQ2;
	}

	public boolean isLockQ3() {
		return lockQ3;
	}

	public void setLockQ3(boolean lockQ3) {
		this.lockQ3 = lockQ3;
	}

	public boolean isLockQ4() {
		return lockQ4;
	}

	public void setLockQ4(boolean lockQ4) {
		this.lockQ4 = lockQ4;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public double getFinalTempRating() {
		return finalTempRating;
	}

	public void setFinalTempRating(double finalTempRating) {
		this.finalTempRating = finalTempRating;
	}

	public int getRankQ1() {
		return rankQ1;
	}

	public void setRankQ1(int rankQ1) {
		this.rankQ1 = rankQ1;
	}

	public int getRankQ2() {
		return rankQ2;
	}

	public void setRankQ2(int rankQ2) {
		this.rankQ2 = rankQ2;
	}

	public int getRankQ3() {
		return rankQ3;
	}

	public void setRankQ3(int rankQ3) {
		this.rankQ3 = rankQ3;
	}

	public int getRankQ4() {
		return rankQ4;
	}

	public void setRankQ4(int rankQ4) {
		this.rankQ4 = rankQ4;
	}

	public int getRankFinal() {
		return rankFinal;
	}

	public void setRankFinal(int rankFinal) {
		this.rankFinal = rankFinal;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "subject","preSubject", "cashier");
    }

    public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public double getTempGrade1() {
		return tempGrade1;
	}

	public void setTempGrade1(double tempGrade1) {
		this.tempGrade1 = tempGrade1;
	}

	public double getTempGrade2() {
		return tempGrade2;
	}

	public void setTempGrade2(double tempGrade2) {
		this.tempGrade2 = tempGrade2;
	}

	public double getTempGrade3() {
		return tempGrade3;
	}

	public void setTempGrade3(double tempGrade3) {
		this.tempGrade3 = tempGrade3;
	}

	public double getTempGrade4() {
		return tempGrade4;
	}

	public void setTempGrade4(double tempGrade4) {
		this.tempGrade4 = tempGrade4;
	}

	public double getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(double finalRating) {
        this.finalRating = finalRating;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getPreSubject1() {
		return preSubject1;
	}

	public void setPreSubject1(String preSubject1) {
		this.preSubject1 = preSubject1;
	}

	public String getPreSubject2() {
		return preSubject2;
	}

	public void setPreSubject2(String preSubject2) {
		this.preSubject2 = preSubject2;
	}

	public String getPreSubject3() {
		return preSubject3;
	}

	public void setPreSubject3(String preSubject3) {
		this.preSubject3 = preSubject3;
	}

	public String getPreSubject4() {
		return preSubject4;
	}

	public void setPreSubject4(String preSubject4) {
		this.preSubject4 = preSubject4;
	}

	public String getPreSubject5() {
		return preSubject5;
	}

	public void setPreSubject5(String preSubject5) {
		this.preSubject5 = preSubject5;
	}

	public String getCoSubject1() {
		return coSubject1;
	}

	public void setCoSubject1(String coSubject1) {
		this.coSubject1 = coSubject1;
	}

	public String getCoSubject2() {
		return coSubject2;
	}

	public void setCoSubject2(String coSubject2) {
		this.coSubject2 = coSubject2;
	}

	public String getCoSubject3() {
		return coSubject3;
	}

	public void setCoSubject3(String coSubject3) {
		this.coSubject3 = coSubject3;
	}

	public String getCoSubject4() {
		return coSubject4;
	}

	public void setCoSubject4(String coSubject4) {
		this.coSubject4 = coSubject4;
	}

	public String getCoSubject5() {
		return coSubject5;
	}

	public void setCoSubject5(String coSubject5) {
		this.coSubject5 = coSubject5;
	}

	@Override
    public String toString() {
        return subject;
    }

    public java.lang.String getSubject() {
        return subject;
    }

    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public int getPreferredSemester() {
        return preferredSemester;
    }

    public void setPreferredSemester(int preferredSemester) {
        this.preferredSemester = preferredSemester;
    }

    public boolean getPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Double getGrade1() {
		return grade1;
	}

	private boolean isValidScore(Double grade) {
		if (grade==null) {
			return false;
		}
		if (grade>100 && !AppConfig.isShowTestButton()) {
			PanelUtil.showError(null, "Percentage is more than 100.");
			return false;
		}
		return true;
	}

	public void setGrade1(Double grade1) {
		if (!isValidScore(grade1)) return;
		this.grade1 = grade1;
	}

	public Double getGrade2() {
		return grade2;
	}

	public void setGrade2(Double grade2) {
		if (!isValidScore(grade2)) return;
		this.grade2 = grade2;
	}

	public Double getGrade3() {
		return grade3;
	}

	public void setGrade3(Double grade3) {
		if (!isValidScore(grade3)) return;
		this.grade3 = grade3;
	}

	public Double getGrade4() {
		return grade4;
	}

	public void setGrade4(Double grade4) {
		if (!isValidScore(grade4)) return;
		this.grade4 = grade4;
	}

	@Override
	public void save() {
		if (studentName==null) {
	        Student stud = (Student) AbstractIBean.firstRecord("SELECT a FROM Student a WHERE a.personId=",studentId);
	        if (stud!=null) studentName = stud.toString();
		}
		if (faculty==null && facultyId>0) {
	        Person f = (Person) AbstractIBean.firstRecord("SELECT a FROM Person a WHERE a.personId=",facultyId);
	        if (f!=null) faculty = f.toString();
		}
		double totalCount = 0;
		double totalGrade = 0;
		if (grade1!=null && grade1>60) {
			totalGrade += (int) (grade1+.5);
			totalCount++;
		}
		if (grade2!=null && grade2>60) {
			totalGrade += (int) (grade2+.5);
			totalCount++;
		}
		if (grade3!=null && grade3>60) {
			totalGrade += (int) (grade3+.5);
			totalCount++;
		}
		if (grade4!=null && grade4>60) {
			totalGrade += (int) (grade4+.5);
			totalCount++;
		}
		if (totalGrade > 0 && totalCount > 0) {
			finalRating = finalTempRating = DataUtil.getMoneyFormat(totalGrade/totalCount);
			if (finalRating>74) {
				actionTaken = "PASSED";
			}
			else {
				actionTaken = "FAILED";
			}
		}
        Subject sub = (Subject) AbstractIBean.objCache("SELECT a FROM Subject a WHERE a.code='",subject,"'");
        if (sub!=null) {
        	gradeLevel = sub.gradeLevel;
        	unit = sub.getUnit();
        }
		super.save();
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentName() {
		return this.studentName;
    }

    public double getUnit() {
    	return unit;
    }

    public String getSubjectName() {
        Subject sub = (Subject) AbstractIBean.firstRecord("SELECT a FROM Subject a WHERE a.code='",subject,"'");
        if (sub==null) return "";
        return sub.getSubject();
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "enrollmentId");
		runIndex(2, "studentId");
		runUniqueIndex(3, "studentId","subject");
		runIndex(4, "facultyId");
		runIndex(5, "scheduleId","section","subject");
		runIndex(6, "schoolYear","section");
		runIndex(7, "schoolYear","gradeLevel","section");
		runIndex(8, "schoolYear","gradeLevel","studentId");
		
		List lst = DBClient.getList("SELECT a FROM StudentSubject a WHERE a.studentName IS NULL", 1, 15000);
		DBClient.persistBean(lst);
	}
}
