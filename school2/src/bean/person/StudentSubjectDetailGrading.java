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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import constants.UserInfo;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.PanelUtil;
import bean.EmployeeFaculty;
import bean.Student;
import bean.admin.AppConfig;
import bean.reference.LockGrading;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "StudentSubjectDetailGrading")
@UITemplate(template=TemplateTabPage.class,
		columnSearch={"studentName", "computedGrade", "gradeShare"}, 
		criteriaSearch={"studentId", "section", "component"}, 
		gridCount=4, title="Component Student Grade", editableCol="2")
@Displays({
        @Display(name="studentName", type = "Label", gridFieldWidth=3, width=-1),
        @Display(name="computedGrade", label="Computed\nGrade", type="Label"),
        @Display(name="weight", type="Label")
})
public class StudentSubjectDetailGrading extends AbstractIBean implements Serializable {
	public StudentSubjectDetailGrading() {
		schoolYear = AppConfig.getSchoolYear();
		facultyId = UserInfo.getPersonId();
	}

	@Override
	public void delete() {
		LockGrading s = LockGrading.extractGrading(schoolYear);
		if (!s.isQ1Locked()) {
			boolean b = PanelUtil.showPrompt(null, "Are you sure you want to delete the selected component detail?");
			if (b) {
				b = PanelUtil.showPrompt(null, "Selected component detail already have grade for 1st quarter, do you want to continue?");
				if (b) {
					super.delete();
				}
			}
		}
		else {
			PanelUtil.showError(null, "You can only delete component on first quarter.");
		}
	}

	public void recalculateGrade() {
		if (parentBean!=null && parentBean instanceof FacultyGradingTask) {
			FacultyGradingTask task = (FacultyGradingTask) parentBean;
			q1ItemCount1 = task.q1ItemCount1;
			q1ItemCount2 = task.q1ItemCount2;
			q1ItemCount3 = task.q1ItemCount3;
			q1ItemCount4 = task.q1ItemCount4;
			q1ItemCount5 = task.q1ItemCount5;
			q1ItemCount6 = task.q1ItemCount6;
			q1ItemCount7 = task.q1ItemCount7;
			q1ItemCount8 = task.q1ItemCount8;
			q1ItemCount9 = task.q1ItemCount9;
			q1ItemCount10 = task.q1ItemCount10;
			q1ItemCount11 = task.q1ItemCount11;
			q1ItemCount12 = task.q1ItemCount12;
			
			q2ItemCount1 = task.q2ItemCount1;
			q2ItemCount2 = task.q2ItemCount2;
			q2ItemCount3 = task.q2ItemCount3;
			q2ItemCount4 = task.q2ItemCount4;
			q2ItemCount5 = task.q2ItemCount5;
			q2ItemCount6 = task.q2ItemCount6;
			q2ItemCount7 = task.q2ItemCount7;
			q2ItemCount8 = task.q2ItemCount8;
			q2ItemCount9 = task.q2ItemCount9;
			q2ItemCount10 = task.q2ItemCount10;
			q2ItemCount11 = task.q2ItemCount11;
			q2ItemCount12 = task.q2ItemCount12;

			q3ItemCount1 = task.q3ItemCount1;
			q3ItemCount2 = task.q3ItemCount2;
			q3ItemCount3 = task.q3ItemCount3;
			q3ItemCount4 = task.q3ItemCount4;
			q3ItemCount5 = task.q3ItemCount5;
			q3ItemCount6 = task.q3ItemCount6;
			q3ItemCount7 = task.q3ItemCount7;
			q3ItemCount8 = task.q3ItemCount8;
			q3ItemCount9 = task.q3ItemCount9;
			q3ItemCount10 = task.q3ItemCount10;
			q3ItemCount11 = task.q3ItemCount11;
			q3ItemCount12 = task.q3ItemCount12;

			q4ItemCount1 = task.q4ItemCount1;
			q4ItemCount2 = task.q4ItemCount2;
			q4ItemCount3 = task.q4ItemCount3;
			q4ItemCount4 = task.q4ItemCount4;
			q4ItemCount5 = task.q4ItemCount5;
			q4ItemCount6 = task.q4ItemCount6;
			q4ItemCount7 = task.q4ItemCount7;
			q4ItemCount8 = task.q4ItemCount8;
			q4ItemCount9 = task.q4ItemCount9;
			q4ItemCount10 = task.q4ItemCount10;
			q4ItemCount11 = task.q4ItemCount11;
			q4ItemCount12 = task.q4ItemCount12;
			
			weight = task.weight;
			usePercentage = task.usePercentage;

			recalculateGrade(1);
			recalculateGrade(2);
			recalculateGrade(3);
			recalculateGrade(4);
			double total = q1Grade+q2Grade+q3Grade+q4Grade;
			if (total>0) {
				gradeShareQ1 = doubleVal(q1Grade*weight/100);
				gradeShareQ2 = doubleVal(q2Grade*weight/100);
				gradeShareQ3 = doubleVal(q3Grade*weight/100);
				gradeShareQ4 = doubleVal(q4Grade*weight/100);
				if (AppConfig.isTrimester()) {
					computedGrade = doubleVal(total/3);
					gradeShare = doubleVal((gradeShareQ1+gradeShareQ2+gradeShareQ3)/3);
				}
				else {
					computedGrade = doubleVal(total/4);
					gradeShare = doubleVal((gradeShareQ1+gradeShareQ2+gradeShareQ3+gradeShareQ4)/4);
				}
			}
		}
	}
	
	private void recalculateGrade(int quarter) {
		double newgrade = 0;
		double count = 0;
		double totalScore = extractQuarterScore(quarter);
		if (this.usePercentage) {
			count = extractPercentageItemCount(quarter);
			if (count==0) {
				newgrade = 0; 
			}
			else {
				newgrade = totalScore/count;
			}
		}
		else {
			count = extractNonPercentageItemCount(quarter);
			if (count==0) {
				newgrade = 0; 
			}
			else {
//				get highest failing
				int highestFailing = (int)((count-1)/2);
				if (totalScore>highestFailing) {
//					for passing grade
					newgrade = ((totalScore/count)*50)+50;
				}
				else {
//					for failing grade
					newgrade = (14.0*totalScore/highestFailing)+60;
				}
			}
		}
		if (quarter==1) {
//			q1Grade=DataUtil.getMoneyFormat(newgrade);
//			gradeShareQ1=DataUtil.getMoneyFormat(q1Grade*weight/100);
	        changeSupport.firePropertyChange("q1Grade", q1Grade, q1Grade=DataUtil.getMoneyFormat(newgrade));
	        changeSupport.firePropertyChange("gradeShareQ1", gradeShareQ1, gradeShareQ1=DataUtil.getMoneyFormat(q1Grade*weight/100));
		}
		else if (quarter==2) {
//			q2Grade=DataUtil.getMoneyFormat(newgrade);
//			gradeShareQ2=DataUtil.getMoneyFormat(q2Grade*weight/100);
	        changeSupport.firePropertyChange("q2Grade", q2Grade, q2Grade=DataUtil.getMoneyFormat(newgrade));
	        changeSupport.firePropertyChange("gradeShareQ2", gradeShareQ2, gradeShareQ2=DataUtil.getMoneyFormat(q2Grade*weight/100));
		}
		else if (quarter==3) {
//			q3Grade=DataUtil.getMoneyFormat(newgrade);
//			gradeShareQ3=DataUtil.getMoneyFormat(q3Grade*weight/100);
	        changeSupport.firePropertyChange("q3Grade", q3Grade, q3Grade=DataUtil.getMoneyFormat(newgrade));
	        changeSupport.firePropertyChange("gradeShareQ3", gradeShareQ3, gradeShareQ3=DataUtil.getMoneyFormat(q3Grade*weight/100));
		}
		else if (quarter==4) {
//			q4Grade=DataUtil.getMoneyFormat(newgrade);
//			gradeShareQ4=DataUtil.getMoneyFormat(q4Grade*weight/100);
	        changeSupport.firePropertyChange("q4Grade", q4Grade, q4Grade=DataUtil.getMoneyFormat(newgrade));
	        changeSupport.firePropertyChange("gradeShareQ4", gradeShareQ4, gradeShareQ4=DataUtil.getMoneyFormat(q4Grade*weight/100));
		}
	}
	
	private int extractPercentageItemCount(int quarter) {
		if (quarter==1) {
			if (q1Score1==null || q1Score1==0) return 0;
			if (q1Score2==null || q1Score2==0) return 1;
			if (q1Score3==null || q1Score3==0) return 2;
			if (q1Score4==null || q1Score4==0) return 3;
			if (q1Score5==null || q1Score5==0) return 4;
			if (q1Score6==null || q1Score6==0) return 5;
			if (q1Score7==null || q1Score7==0) return 6;
			if (q1Score8==null || q1Score8==0) return 7;
			if (q1Score9==null || q1Score9==0) return 8;
			if (q1Score10==null || q1Score10==0) return 9;
			if (q1Score11==null || q1Score11==0) return 10;
			if (q1Score12==null || q1Score12==0) return 11;
		}
		if (quarter==2) {
			if (q2Score1==null || q2Score1==0) return 0;
			if (q2Score2==null || q2Score2==0) return 1;
			if (q2Score3==null || q2Score3==0) return 2;
			if (q2Score4==null || q2Score4==0) return 3;
			if (q2Score5==null || q2Score5==0) return 4;
			if (q2Score6==null || q2Score6==0) return 5;
			if (q2Score7==null || q2Score7==0) return 6;
			if (q2Score8==null || q2Score8==0) return 7;
			if (q2Score9==null || q2Score9==0) return 8;
			if (q2Score10==null || q2Score10==0) return 9;
			if (q2Score11==null || q2Score11==0) return 10;
			if (q2Score12==null || q2Score12==0) return 11;
		}
		if (quarter==3) {
			if (q3Score1==null || q3Score1==0) return 0;
			if (q3Score2==null || q3Score2==0) return 1;
			if (q3Score3==null || q3Score3==0) return 2;
			if (q3Score4==null || q3Score4==0) return 3;
			if (q3Score5==null || q3Score5==0) return 4;
			if (q3Score6==null || q3Score6==0) return 5;
			if (q3Score7==null || q3Score7==0) return 6;
			if (q3Score8==null || q3Score8==0) return 7;
			if (q3Score9==null || q3Score9==0) return 8;
			if (q3Score10==null || q3Score10==0) return 9;
			if (q3Score11==null || q3Score11==0) return 10;
			if (q3Score12==null || q3Score12==0) return 11;
		}
		if (quarter==4) {
			if (q4Score1==null || q4Score1==0) return 0;
			if (q4Score2==null || q4Score2==0) return 1;
			if (q4Score3==null || q4Score3==0) return 2;
			if (q4Score4==null || q4Score4==0) return 3;
			if (q4Score5==null || q4Score5==0) return 4;
			if (q4Score6==null || q4Score6==0) return 5;
			if (q4Score7==null || q4Score7==0) return 6;
			if (q4Score8==null || q4Score8==0) return 7;
			if (q4Score9==null || q4Score9==0) return 8;
			if (q4Score10==null || q4Score10==0) return 9;
			if (q4Score11==null || q4Score11==0) return 10;
			if (q4Score12==null || q4Score12==0) return 11;
		}
		return 12;
	}

	private double extractNonPercentageItemCount(int quarter) {
		double ret = 0;
		if (quarter==1) {
			if (q1Score1!=null && q1Score1!=-1) ret += q1ItemCount1;
			if (q1Score2!=null && q1Score2!=-1) ret += q1ItemCount2;
			if (q1Score3!=null && q1Score3!=-1) ret += q1ItemCount3;
			if (q1Score4!=null && q1Score4!=-1) ret += q1ItemCount4;
			if (q1Score5!=null && q1Score5!=-1) ret += q1ItemCount5;
			if (q1Score6!=null && q1Score6!=-1) ret += q1ItemCount6;
			if (q1Score7!=null && q1Score7!=-1) ret += q1ItemCount7;
			if (q1Score8!=null && q1Score8!=-1) ret += q1ItemCount8;
			if (q1Score9!=null && q1Score9!=-1) ret += q1ItemCount9;
			if (q1Score10!=null && q1Score10!=-1) ret += q1ItemCount10;
			if (q1Score11!=null && q1Score11!=-1) ret += q1ItemCount11;
			if (q1Score12!=null && q1Score11!=-1) ret += q1ItemCount12;
		}
		if (quarter==2) {
			if (q2Score1!=null && q2Score1!=-1) ret += q2ItemCount1;
			if (q2Score2!=null && q2Score2!=-1) ret += q2ItemCount2;
			if (q2Score3!=null && q2Score3!=-1) ret += q2ItemCount3;
			if (q2Score4!=null && q2Score4!=-1) ret += q2ItemCount4;
			if (q2Score5!=null && q2Score5!=-1) ret += q2ItemCount5;
			if (q2Score6!=null && q2Score6!=-1) ret += q2ItemCount6;
			if (q2Score7!=null && q2Score7!=-1) ret += q2ItemCount7;
			if (q2Score8!=null && q2Score8!=-1) ret += q2ItemCount8;
			if (q2Score9!=null && q2Score9!=-1) ret += q2ItemCount9;
			if (q2Score10!=null && q2Score10!=-1) ret += q2ItemCount10;
			if (q2Score11!=null && q2Score11!=-1) ret += q2ItemCount11;
			if (q2Score12!=null && q2Score12!=-1) ret += q2ItemCount12;
		}
		if (quarter==3) {
			if (q3Score1!=null && q3Score1!=-1) ret += q3ItemCount1;
			if (q3Score2!=null && q3Score2!=-1) ret += q3ItemCount2;
			if (q3Score3!=null && q3Score3!=-1) ret += q3ItemCount3;
			if (q3Score4!=null && q3Score4!=-1) ret += q3ItemCount4;
			if (q3Score5!=null && q3Score5!=-1) ret += q3ItemCount5;
			if (q3Score6!=null && q3Score6!=-1) ret += q3ItemCount6;
			if (q3Score7!=null && q3Score7!=-1) ret += q3ItemCount7;
			if (q3Score8!=null && q3Score8!=-1) ret += q3ItemCount8;
			if (q3Score9!=null && q3Score9!=-1) ret += q3ItemCount9;
			if (q3Score10!=null && q3Score10!=-1) ret += q3ItemCount10;
			if (q3Score11!=null && q3Score11!=-1) ret += q3ItemCount11;
			if (q3Score12!=null && q3Score12!=-1) ret += q3ItemCount12;
		}
		if (quarter==4) {
			if (q4Score1!=null && q4Score1!=-1) ret += q4ItemCount1;
			if (q4Score2!=null && q4Score2!=-1) ret += q4ItemCount2;
			if (q4Score3!=null && q4Score3!=-1) ret += q4ItemCount3;
			if (q4Score4!=null && q4Score4!=-1) ret += q4ItemCount4;
			if (q4Score5!=null && q4Score5!=-1) ret += q4ItemCount5;
			if (q4Score6!=null && q4Score6!=-1) ret += q4ItemCount6;
			if (q4Score7!=null && q4Score7!=-1) ret += q4ItemCount7;
			if (q4Score8!=null && q4Score8!=-1) ret += q4ItemCount8;
			if (q4Score9!=null && q4Score9!=-1) ret += q4ItemCount9;
			if (q4Score10!=null && q4Score10!=-1) ret += q4ItemCount10;
			if (q4Score11!=null && q4Score11!=-1) ret += q4ItemCount11;
			if (q4Score12!=null && q4Score12!=-1) ret += q4ItemCount12;
		}
		return ret;
	}

	private double extractQuarterScore(int quarter) {
		double ret = 0;
		if (quarter==1) {
			if (q1Score1!=null && q1Score1>=0) ret += q1Score1;
			if (q1Score2!=null && q1Score2>=0) ret += q1Score2;
			if (q1Score3!=null && q1Score3>=0) ret += q1Score3;
			if (q1Score4!=null && q1Score4>=0) ret += q1Score4;
			if (q1Score5!=null && q1Score5>=0) ret += q1Score5;
			if (q1Score6!=null && q1Score6>=0) ret += q1Score6;
			if (q1Score7!=null && q1Score7>=0) ret += q1Score7;
			if (q1Score8!=null && q1Score8>=0) ret += q1Score8;
			if (q1Score9!=null && q1Score9>=0) ret += q1Score9;
			if (q1Score10!=null && q1Score10>=0) ret += q1Score10;
			if (q1Score11!=null && q1Score11>=0) ret += q1Score11;
			if (q1Score12!=null && q1Score11>=0) ret += q1Score12;
		}
		if (quarter==2) {
			if (q2Score1!=null && q2Score1>=0) ret += q2Score1;
			if (q2Score2!=null && q2Score2>=0) ret += q2Score2;
			if (q2Score3!=null && q2Score3>=0) ret += q2Score3;
			if (q2Score4!=null && q2Score4>=0) ret += q2Score4;
			if (q2Score5!=null && q2Score5>=0) ret += q2Score5;
			if (q2Score6!=null && q2Score6>=0) ret += q2Score6;
			if (q2Score7!=null && q2Score7>=0) ret += q2Score7;
			if (q2Score8!=null && q2Score8>=0) ret += q2Score8;
			if (q2Score9!=null && q2Score9>=0) ret += q2Score9;
			if (q2Score10!=null && q2Score10>=0) ret += q2Score10;
			if (q2Score11!=null && q2Score11>=0) ret += q2Score11;
			if (q2Score12!=null && q2Score12>=0) ret += q2Score12;
		}
		if (quarter==3) {
			if (q3Score1!=null && q3Score1>=0) ret += q3Score1;
			if (q3Score2!=null && q3Score2>=0) ret += q3Score2;
			if (q3Score3!=null && q3Score3>=0) ret += q3Score3;
			if (q3Score4!=null && q3Score4>=0) ret += q3Score4;
			if (q3Score5!=null && q3Score5>=0) ret += q3Score5;
			if (q3Score6!=null && q3Score6>=0) ret += q3Score6;
			if (q3Score7!=null && q3Score7>=0) ret += q3Score7;
			if (q3Score8!=null && q3Score8>=0) ret += q3Score8;
			if (q3Score9!=null && q3Score9>=0) ret += q3Score9;
			if (q3Score10!=null && q3Score10>=0) ret += q3Score10;
			if (q3Score11!=null && q3Score11>=0) ret += q3Score11;
			if (q3Score12!=null && q3Score12>=0) ret += q3Score12;
		}
		if (quarter==4) {
			if (q4Score1!=null && q4Score1>=0) ret += q4Score1;
			if (q4Score2!=null && q4Score2>=0) ret += q4Score2;
			if (q4Score3!=null && q4Score3>=0) ret += q4Score3;
			if (q4Score4!=null && q4Score4>=0) ret += q4Score4;
			if (q4Score5!=null && q4Score5>=0) ret += q4Score5;
			if (q4Score6!=null && q4Score6>=0) ret += q4Score6;
			if (q4Score7!=null && q4Score7>=0) ret += q4Score7;
			if (q4Score8!=null && q4Score8>=0) ret += q4Score8;
			if (q4Score9!=null && q4Score9>=0) ret += q4Score9;
			if (q4Score10!=null && q4Score10>=0) ret += q4Score10;
			if (q4Score11!=null && q4Score11>=0) ret += q4Score11;
			if (q4Score12!=null && q4Score12>=0) ret += q4Score12;
		}
		return ret;
	}
	
	private boolean isValidScore(Integer score, int itemCount) {
		if (score==null) {
			return false;
		}
		if (usePercentage) {
			if (score>100) {
				PanelUtil.showError(null, "Percentage is more than 100.");
				return false;
			}
		}
		else {
			if (score>itemCount) {
				PanelUtil.showError(null, "Score is more than item count.");
				return false;
			}
		}
		return true;
	}
	
	@Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "facultyGradingTaskId")
    public int facultyGradingTaskId;
    @Column(name = "studentSubjectId")
    public int studentSubjectId;
    @Column(name = "studentId")
    public int studentId;
    @Column(name = "scheduleId")
    public int scheduleId;
    @Column(name = "studentName")
    public String studentName;
    @Column(name = "facultyId")
    public int facultyId;
    @Column(name = "facultyName")
    public String facultyName;

    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "section")
    public String section;
    @Column(name = "subject")
    public String subject;
    @Column(name = "component")
    public String component;

    @Column(name = "description")
    public String description;
    @Column(name = "weight")
    public double weight;
    @Column(name = "usePercentage")
    public boolean usePercentage;

    @Column(name = "q1Grade")
    public double q1Grade;
    @Column(name = "q2Grade")
    public double q2Grade;
    @Column(name = "q3Grade")
    public double q3Grade;
    @Column(name = "q4Grade")
    public double q4Grade;
    @Column(name = "computedGrade")
    public double computedGrade;
    @Column(name = "gradeShare")
    public double gradeShare;

    @Column(name = "gradeShareQ1")
    public double gradeShareQ1;
    @Column(name = "gradeShareQ2")
    public double gradeShareQ2;
    @Column(name = "gradeShareQ3")
    public double gradeShareQ3;
    @Column(name = "gradeShareQ4")
    public double gradeShareQ4;

    @Column(name = "q1ItemCount1")
    public int q1ItemCount1;
    @Column(name = "q1ItemCount2")
    public int q1ItemCount2;
    @Column(name = "q1ItemCount3")
    public int q1ItemCount3;
    @Column(name = "q1ItemCount4")
    public int q1ItemCount4;
    @Column(name = "q1ItemCount5")
    public int q1ItemCount5;
    @Column(name = "q1ItemCount6")
    public int q1ItemCount6;
    @Column(name = "q1ItemCount7")
    public int q1ItemCount7;
    @Column(name = "q1ItemCount8")
    public int q1ItemCount8;
    @Column(name = "q1ItemCount9")
    public int q1ItemCount9;
    @Column(name = "q1ItemCount10")
    public int q1ItemCount10;
    @Column(name = "q1ItemCount11")
    public int q1ItemCount11;
    @Column(name = "q1ItemCount12")
    public int q1ItemCount12;

    @Column(name = "q2ItemCount1")
    public int q2ItemCount1;
    @Column(name = "q2ItemCount2")
    public int q2ItemCount2;
    @Column(name = "q2ItemCount3")
    public int q2ItemCount3;
    @Column(name = "q2ItemCount4")
    public int q2ItemCount4;
    @Column(name = "q2ItemCount5")
    public int q2ItemCount5;
    @Column(name = "q2ItemCount6")
    public int q2ItemCount6;
    @Column(name = "q2ItemCount7")
    public int q2ItemCount7;
    @Column(name = "q2ItemCount8")
    public int q2ItemCount8;
    @Column(name = "q2ItemCount9")
    public int q2ItemCount9;
    @Column(name = "q2ItemCount10")
    public int q2ItemCount10;
    @Column(name = "q2ItemCount11")
    public int q2ItemCount11;
    @Column(name = "q2ItemCount12")
    public int q2ItemCount12;

    @Column(name = "q3ItemCount1")
    public int q3ItemCount1;
    @Column(name = "q3ItemCount2")
    public int q3ItemCount2;
    @Column(name = "q3ItemCount3")
    public int q3ItemCount3;
    @Column(name = "q3ItemCount4")
    public int q3ItemCount4;
    @Column(name = "q3ItemCount5")
    public int q3ItemCount5;
    @Column(name = "q3ItemCount6")
    public int q3ItemCount6;
    @Column(name = "q3ItemCount7")
    public int q3ItemCount7;
    @Column(name = "q3ItemCount8")
    public int q3ItemCount8;
    @Column(name = "q3ItemCount9")
    public int q3ItemCount9;
    @Column(name = "q3ItemCount10")
    public int q3ItemCount10;
    @Column(name = "q3ItemCount11")
    public int q3ItemCount11;
    @Column(name = "q3ItemCount12")
    public int q3ItemCount12;

    @Column(name = "q4ItemCount1")
    public int q4ItemCount1;
    @Column(name = "q4ItemCount2")
    public int q4ItemCount2;
    @Column(name = "q4ItemCount3")
    public int q4ItemCount3;
    @Column(name = "q4ItemCount4")
    public int q4ItemCount4;
    @Column(name = "q4ItemCount5")
    public int q4ItemCount5;
    @Column(name = "q4ItemCount6")
    public int q4ItemCount6;
    @Column(name = "q4ItemCount7")
    public int q4ItemCount7;
    @Column(name = "q4ItemCount8")
    public int q4ItemCount8;
    @Column(name = "q4ItemCount9")
    public int q4ItemCount9;
    @Column(name = "q4ItemCount10")
    public int q4ItemCount10;
    @Column(name = "q4ItemCount11")
    public int q4ItemCount11;
    @Column(name = "q4ItemCount12")
    public int q4ItemCount12;

    @Column(name = "q1Score1")
    public Integer q1Score1=0;
    @Column(name = "q1Score2")
    public Integer q1Score2=0;
    @Column(name = "q1Score3")
    public Integer q1Score3=0;
    @Column(name = "q1Score4")
    public Integer q1Score4=0;
    @Column(name = "q1Score5")
    public Integer q1Score5=0;
    @Column(name = "q1Score6")
    public Integer q1Score6=0;
    @Column(name = "q1Score7")
    public Integer q1Score7=0;
    @Column(name = "q1Score8")
    public Integer q1Score8=0;
    @Column(name = "q1Score9")
    public Integer q1Score9=0;
    @Column(name = "q1Score10")
    public Integer q1Score10=0;
    @Column(name = "q1Score11")
    public Integer q1Score11=0;
    @Column(name = "q1Score12")
    public Integer q1Score12=0;

    @Column(name = "q2Score1")
    public Integer q2Score1=0;
    @Column(name = "q2Score2")
    public Integer q2Score2=0;
    @Column(name = "q2Score3")
    public Integer q2Score3=0;
    @Column(name = "q2Score4")
    public Integer q2Score4=0;
    @Column(name = "q2Score5")
    public Integer q2Score5=0;
    @Column(name = "q2Score6")
    public Integer q2Score6=0;
    @Column(name = "q2Score7")
    public Integer q2Score7=0;
    @Column(name = "q2Score8")
    public Integer q2Score8=0;
    @Column(name = "q2Score9")
    public Integer q2Score9=0;
    @Column(name = "q2Score10")
    public Integer q2Score10=0;
    @Column(name = "q2Score11")
    public Integer q2Score11=0;
    @Column(name = "q2Score12")
    public Integer q2Score12=0;

    @Column(name = "q3Score1")
    public Integer q3Score1=0;
    @Column(name = "q3Score2")
    public Integer q3Score2=0;
    @Column(name = "q3Score3")
    public Integer q3Score3=0;
    @Column(name = "q3Score4")
    public Integer q3Score4=0;
    @Column(name = "q3Score5")
    public Integer q3Score5=0;
    @Column(name = "q3Score6")
    public Integer q3Score6=0;
    @Column(name = "q3Score7")
    public Integer q3Score7=0;
    @Column(name = "q3Score8")
    public Integer q3Score8=0;
    @Column(name = "q3Score9")
    public Integer q3Score9=0;
    @Column(name = "q3Score10")
    public Integer q3Score10=0;
    @Column(name = "q3Score11")
    public Integer q3Score11=0;
    @Column(name = "q3Score12")
    public Integer q3Score12=0;

    @Column(name = "q4Score1")
    public Integer q4Score1=0;
    @Column(name = "q4Score2")
    public Integer q4Score2=0;
    @Column(name = "q4Score3")
    public Integer q4Score3=0;
    @Column(name = "q4Score4")
    public Integer q4Score4=0;
    @Column(name = "q4Score5")
    public Integer q4Score5=0;
    @Column(name = "q4Score6")
    public Integer q4Score6=0;
    @Column(name = "q4Score7")
    public Integer q4Score7=0;
    @Column(name = "q4Score8")
    public Integer q4Score8=0;
    @Column(name = "q4Score9")
    public Integer q4Score9=0;
    @Column(name = "q4Score10")
    public Integer q4Score10=0;
    @Column(name = "q4Score11")
    public Integer q4Score11=0;
    @Column(name = "q4Score12")
    public Integer q4Score12=0;

    public double getGradeShareQ1() {
		return gradeShareQ1;
	}

	public void setGradeShareQ1(double gradeShareQ1) {
		this.gradeShareQ1 = gradeShareQ1;
	}

	public double getGradeShareQ2() {
		return gradeShareQ2;
	}

	public void setGradeShareQ2(double gradeShareQ2) {
		this.gradeShareQ2 = gradeShareQ2;
	}

	public double getGradeShareQ3() {
		return gradeShareQ3;
	}

	public void setGradeShareQ3(double gradeShareQ3) {
		this.gradeShareQ3 = gradeShareQ3;
	}

	public double getGradeShareQ4() {
		return gradeShareQ4;
	}

	public void setGradeShareQ4(double gradeShareQ4) {
		this.gradeShareQ4 = gradeShareQ4;
	}

	public double getGradeShare() {
		return gradeShare;
	}

	public void setGradeShare(double gradeShare) {
		this.gradeShare = gradeShare;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public double getQ1Grade() {
		return q1Grade;
	}

	public void setQ1Grade(double grade) {
		q1Grade = grade;
	}

	public double getQ2Grade() {
		return q2Grade;
	}

	public void setQ2Grade(double grade) {
		q2Grade = grade;
	}

	public double getQ3Grade() {
		return q3Grade;
	}

	public void setQ3Grade(double grade) {
		q3Grade = grade;
	}

	public double getQ4Grade() {
		return q4Grade;
	}

	public void setQ4Grade(double grade) {
		q4Grade = grade;
	}

	public double getComputedGrade() {
		return computedGrade;
	}

	public void setComputedGrade(double computedGrade) {
		this.computedGrade = computedGrade;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getFacultyGradingTaskId() {
		return facultyGradingTaskId;
	}

	public void setFacultyGradingTaskId(int facultyGradingTaskId) {
		this.facultyGradingTaskId = facultyGradingTaskId;
	}

	public int getStudentSubjectId() {
		return studentSubjectId;
	}

	public void setStudentSubjectId(int studentSubjectId) {
		this.studentSubjectId = studentSubjectId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isUsePercentage() {
		return usePercentage;
	}

	public void setUsePercentage(boolean usePercentage) {
		this.usePercentage = usePercentage;
	}

	public int getQ1ItemCount1() {
		return q1ItemCount1;
	}

	public void setQ1ItemCount1(int itemCount1) {
		q1ItemCount1 = itemCount1;
	}

	public int getQ1ItemCount2() {
		return q1ItemCount2;
	}

	public void setQ1ItemCount2(int itemCount2) {
		q1ItemCount2 = itemCount2;
	}

	public int getQ1ItemCount3() {
		return q1ItemCount3;
	}

	public void setQ1ItemCount3(int itemCount3) {
		q1ItemCount3 = itemCount3;
	}

	public int getQ1ItemCount4() {
		return q1ItemCount4;
	}

	public void setQ1ItemCount4(int itemCount4) {
		q1ItemCount4 = itemCount4;
	}

	public int getQ1ItemCount5() {
		return q1ItemCount5;
	}

	public void setQ1ItemCount5(int itemCount5) {
		q1ItemCount5 = itemCount5;
	}

	public int getQ1ItemCount6() {
		return q1ItemCount6;
	}

	public void setQ1ItemCount6(int itemCount6) {
		q1ItemCount6 = itemCount6;
	}

	public int getQ1ItemCount7() {
		return q1ItemCount7;
	}

	public void setQ1ItemCount7(int itemCount7) {
		q1ItemCount7 = itemCount7;
	}

	public int getQ1ItemCount8() {
		return q1ItemCount8;
	}

	public void setQ1ItemCount8(int itemCount8) {
		q1ItemCount8 = itemCount8;
	}

	public int getQ1ItemCount9() {
		return q1ItemCount9;
	}

	public void setQ1ItemCount9(int itemCount9) {
		q1ItemCount9 = itemCount9;
	}

	public int getQ1ItemCount10() {
		return q1ItemCount10;
	}

	public void setQ1ItemCount10(int itemCount10) {
		q1ItemCount10 = itemCount10;
	}

	public int getQ1ItemCount11() {
		return q1ItemCount11;
	}

	public void setQ1ItemCount11(int itemCount11) {
		q1ItemCount11 = itemCount11;
	}

	public int getQ1ItemCount12() {
		return q1ItemCount12;
	}

	public void setQ1ItemCount12(int itemCount12) {
		q1ItemCount12 = itemCount12;
	}

	public int getQ2ItemCount1() {
		return q2ItemCount1;
	}

	public void setQ2ItemCount1(int itemCount1) {
		q2ItemCount1 = itemCount1;
	}

	public int getQ2ItemCount2() {
		return q2ItemCount2;
	}

	public void setQ2ItemCount2(int itemCount2) {
		q2ItemCount2 = itemCount2;
	}

	public int getQ2ItemCount3() {
		return q2ItemCount3;
	}

	public void setQ2ItemCount3(int itemCount3) {
		q2ItemCount3 = itemCount3;
	}

	public int getQ2ItemCount4() {
		return q2ItemCount4;
	}

	public void setQ2ItemCount4(int itemCount4) {
		q2ItemCount4 = itemCount4;
	}

	public int getQ2ItemCount5() {
		return q2ItemCount5;
	}

	public void setQ2ItemCount5(int itemCount5) {
		q2ItemCount5 = itemCount5;
	}

	public int getQ2ItemCount6() {
		return q2ItemCount6;
	}

	public void setQ2ItemCount6(int itemCount6) {
		q2ItemCount6 = itemCount6;
	}

	public int getQ2ItemCount7() {
		return q2ItemCount7;
	}

	public void setQ2ItemCount7(int itemCount7) {
		q2ItemCount7 = itemCount7;
	}

	public int getQ2ItemCount8() {
		return q2ItemCount8;
	}

	public void setQ2ItemCount8(int itemCount8) {
		q2ItemCount8 = itemCount8;
	}

	public int getQ2ItemCount9() {
		return q2ItemCount9;
	}

	public void setQ2ItemCount9(int itemCount9) {
		q2ItemCount9 = itemCount9;
	}

	public int getQ2ItemCount10() {
		return q2ItemCount10;
	}

	public void setQ2ItemCount10(int itemCount10) {
		q2ItemCount10 = itemCount10;
	}

	public int getQ2ItemCount11() {
		return q2ItemCount11;
	}

	public void setQ2ItemCount11(int itemCount11) {
		q2ItemCount11 = itemCount11;
	}

	public int getQ2ItemCount12() {
		return q2ItemCount12;
	}

	public void setQ2ItemCount12(int itemCount12) {
		q2ItemCount12 = itemCount12;
	}

	public int getQ3ItemCount1() {
		return q3ItemCount1;
	}

	public void setQ3ItemCount1(int itemCount1) {
		q3ItemCount1 = itemCount1;
	}

	public int getQ3ItemCount2() {
		return q3ItemCount2;
	}

	public void setQ3ItemCount2(int itemCount2) {
		q3ItemCount2 = itemCount2;
	}

	public int getQ3ItemCount3() {
		return q3ItemCount3;
	}

	public void setQ3ItemCount3(int itemCount3) {
		q3ItemCount3 = itemCount3;
	}

	public int getQ3ItemCount4() {
		return q3ItemCount4;
	}

	public void setQ3ItemCount4(int itemCount4) {
		q3ItemCount4 = itemCount4;
	}

	public int getQ3ItemCount5() {
		return q3ItemCount5;
	}

	public void setQ3ItemCount5(int itemCount5) {
		q3ItemCount5 = itemCount5;
	}

	public int getQ3ItemCount6() {
		return q3ItemCount6;
	}

	public void setQ3ItemCount6(int itemCount6) {
		q3ItemCount6 = itemCount6;
	}

	public int getQ3ItemCount7() {
		return q3ItemCount7;
	}

	public void setQ3ItemCount7(int itemCount7) {
		q3ItemCount7 = itemCount7;
	}

	public int getQ3ItemCount8() {
		return q3ItemCount8;
	}

	public void setQ3ItemCount8(int itemCount8) {
		q3ItemCount8 = itemCount8;
	}

	public int getQ3ItemCount9() {
		return q3ItemCount9;
	}

	public void setQ3ItemCount9(int itemCount9) {
		q3ItemCount9 = itemCount9;
	}

	public int getQ3ItemCount10() {
		return q3ItemCount10;
	}

	public void setQ3ItemCount10(int itemCount10) {
		q3ItemCount10 = itemCount10;
	}

	public int getQ3ItemCount11() {
		return q3ItemCount11;
	}

	public void setQ3ItemCount11(int itemCount11) {
		q3ItemCount11 = itemCount11;
	}

	public int getQ3ItemCount12() {
		return q3ItemCount12;
	}

	public void setQ3ItemCount12(int itemCount12) {
		q3ItemCount12 = itemCount12;
	}

	public int getQ4ItemCount1() {
		return q4ItemCount1;
	}

	public void setQ4ItemCount1(int itemCount1) {
		q4ItemCount1 = itemCount1;
	}

	public int getQ4ItemCount2() {
		return q4ItemCount2;
	}

	public void setQ4ItemCount2(int itemCount2) {
		q4ItemCount2 = itemCount2;
	}

	public int getQ4ItemCount3() {
		return q4ItemCount3;
	}

	public void setQ4ItemCount3(int itemCount3) {
		q4ItemCount3 = itemCount3;
	}

	public int getQ4ItemCount4() {
		return q4ItemCount4;
	}

	public void setQ4ItemCount4(int itemCount4) {
		q4ItemCount4 = itemCount4;
	}

	public int getQ4ItemCount5() {
		return q4ItemCount5;
	}

	public void setQ4ItemCount5(int itemCount5) {
		q4ItemCount5 = itemCount5;
	}

	public int getQ4ItemCount6() {
		return q4ItemCount6;
	}

	public void setQ4ItemCount6(int itemCount6) {
		q4ItemCount6 = itemCount6;
	}

	public int getQ4ItemCount7() {
		return q4ItemCount7;
	}

	public void setQ4ItemCount7(int itemCount7) {
		q4ItemCount7 = itemCount7;
	}

	public int getQ4ItemCount8() {
		return q4ItemCount8;
	}

	public void setQ4ItemCount8(int itemCount8) {
		q4ItemCount8 = itemCount8;
	}

	public int getQ4ItemCount9() {
		return q4ItemCount9;
	}

	public void setQ4ItemCount9(int itemCount9) {
		q4ItemCount9 = itemCount9;
	}

	public int getQ4ItemCount10() {
		return q4ItemCount10;
	}

	public void setQ4ItemCount10(int itemCount10) {
		q4ItemCount10 = itemCount10;
	}

	public int getQ4ItemCount11() {
		return q4ItemCount11;
	}

	public void setQ4ItemCount11(int itemCount11) {
		q4ItemCount11 = itemCount11;
	}

	public int getQ4ItemCount12() {
		return q4ItemCount12;
	}

	public void setQ4ItemCount12(int itemCount12) {
		q4ItemCount12 = itemCount12;
	}

	public Integer getQ1Score1() {
		return q1Score1;
	}

	public void setQ1Score1(Integer score1) {
		if (!isValidScore(score1, q1ItemCount1)) return;
		q1Score1 = score1;
	}

	public Integer getQ1Score2() {
		return q1Score2;
	}

	public void setQ1Score2(Integer score2) {
		if (!isValidScore(score2, q1ItemCount2)) return;
		q1Score2 = score2;
	}

	public Integer getQ1Score3() {
		return q1Score3;
	}

	public void setQ1Score3(Integer score3) {
		if (!isValidScore(score3, q1ItemCount3)) return;
		q1Score3 = score3;
	}

	public Integer getQ1Score4() {
		return q1Score4;
	}

	public void setQ1Score4(Integer score4) {
		if (!isValidScore(score4, q1ItemCount4)) return;
		q1Score4 = score4;
	}

	public Integer getQ1Score5() {
		return q1Score5;
	}

	public void setQ1Score5(Integer score5) {
		if (!isValidScore(score5, q1ItemCount5)) return;
		q1Score5 = score5;
	}

	public Integer getQ1Score6() {
		return q1Score6;
	}

	public void setQ1Score6(Integer score6) {
		if (!isValidScore(score6, q1ItemCount6)) return;
		q1Score6 = score6;
	}

	public Integer getQ1Score7() {
		return q1Score7;
	}

	public void setQ1Score7(Integer score7) {
		if (!isValidScore(score7, q1ItemCount7)) return;
		q1Score7 = score7;
	}

	public Integer getQ1Score8() {
		return q1Score8;
	}

	public void setQ1Score8(Integer score8) {
		if (!isValidScore(score8, q1ItemCount8)) return;
		q1Score8 = score8;
	}

	public Integer getQ1Score9() {
		return q1Score9;
	}

	public void setQ1Score9(Integer score9) {
		if (!isValidScore(score9, q1ItemCount9)) return;
		q1Score9 = score9;
	}

	public Integer getQ1Score10() {
		return q1Score10;
	}

	public void setQ1Score10(Integer score10) {
		if (!isValidScore(score10, q1ItemCount10)) return;
		q1Score10 = score10;
	}

	public Integer getQ1Score11() {
		return q1Score11;
	}

	public void setQ1Score11(Integer score11) {
		if (!isValidScore(score11, q1ItemCount11)) return;
		q1Score11 = score11;
	}

	public Integer getQ1Score12() {
		return q1Score12;
	}

	public void setQ1Score12(Integer score12) {
		if (!isValidScore(score12, q1ItemCount12)) return;
		q1Score12 = score12;
	}

	public Integer getQ2Score1() {
		return q2Score1;
	}

	public void setQ2Score1(Integer score1) {
		if (!isValidScore(score1, q2ItemCount1)) return;
		q2Score1 = score1;
	}

	public Integer getQ2Score2() {
		return q2Score2;
	}

	public void setQ2Score2(Integer score2) {
		if (!isValidScore(score2, q2ItemCount2)) return;
		q2Score2 = score2;
	}

	public Integer getQ2Score3() {
		return q2Score3;
	}

	public void setQ2Score3(Integer score3) {
		if (!isValidScore(score3, q2ItemCount3)) return;
		q2Score3 = score3;
	}

	public Integer getQ2Score4() {
		return q2Score4;
	}

	public void setQ2Score4(Integer score4) {
		if (!isValidScore(score4, q2ItemCount4)) return;
		q2Score4 = score4;
	}

	public Integer getQ2Score5() {
		return q2Score5;
	}

	public void setQ2Score5(Integer score5) {
		if (!isValidScore(score5, q2ItemCount5)) return;
		q2Score5 = score5;
	}

	public Integer getQ2Score6() {
		return q2Score6;
	}

	public void setQ2Score6(Integer score6) {
		if (!isValidScore(score6, q2ItemCount6)) return;
		q2Score6 = score6;
	}

	public Integer getQ2Score7() {
		return q2Score7;
	}

	public void setQ2Score7(Integer score7) {
		if (!isValidScore(score7, q2ItemCount7)) return;
		q2Score7 = score7;
	}

	public Integer getQ2Score8() {
		return q2Score8;
	}

	public void setQ2Score8(Integer score8) {
		if (!isValidScore(score8, q2ItemCount8)) return;
		q2Score8 = score8;
	}

	public Integer getQ2Score9() {
		return q2Score9;
	}

	public void setQ2Score9(Integer score9) {
		if (!isValidScore(score9, q2ItemCount9)) return;
		q2Score9 = score9;
	}

	public Integer getQ2Score10() {
		return q2Score10;
	}

	public void setQ2Score10(Integer score10) {
		if (!isValidScore(score10, q2ItemCount10)) return;
		q2Score10 = score10;
	}

	public Integer getQ2Score11() {
		return q2Score11;
	}

	public void setQ2Score11(Integer score11) {
		if (!isValidScore(score11, q2ItemCount11)) return;
		q2Score11 = score11;
	}

	public Integer getQ2Score12() {
		return q2Score12;
	}

	public void setQ2Score12(Integer score12) {
		if (!isValidScore(score12, q2ItemCount12)) return;
		q2Score12 = score12;
	}

	public Integer getQ3Score1() {
		return q3Score1;
	}

	public void setQ3Score1(Integer score1) {
		if (!isValidScore(score1, q3ItemCount1)) return;
		q3Score1 = score1;
	}

	public Integer getQ3Score2() {
		return q3Score2;
	}

	public void setQ3Score2(Integer score2) {
		if (!isValidScore(score2, q3ItemCount2)) return;
		q3Score2 = score2;
	}

	public Integer getQ3Score3() {
		return q3Score3;
	}

	public void setQ3Score3(Integer score3) {
		if (!isValidScore(score3, q3ItemCount3)) return;
		q3Score3 = score3;
	}

	public Integer getQ3Score4() {
		return q3Score4;
	}

	public void setQ3Score4(Integer score4) {
		if (!isValidScore(score4, q3ItemCount4)) return;
		q3Score4 = score4;
	}

	public Integer getQ3Score5() {
		return q3Score5;
	}

	public void setQ3Score5(Integer score5) {
		if (!isValidScore(score5, q3ItemCount5)) return;
		q3Score5 = score5;
	}

	public Integer getQ3Score6() {
		return q3Score6;
	}

	public void setQ3Score6(Integer score6) {
		if (!isValidScore(score6, q3ItemCount6)) return;
		q3Score6 = score6;
	}

	public Integer getQ3Score7() {
		return q3Score7;
	}

	public void setQ3Score7(Integer score7) {
		if (!isValidScore(score7, q3ItemCount7)) return;
		q3Score7 = score7;
	}

	public Integer getQ3Score8() {
		return q3Score8;
	}

	public void setQ3Score8(Integer score8) {
		if (!isValidScore(score8, q3ItemCount8)) return;
		q3Score8 = score8;
	}

	public Integer getQ3Score9() {
		return q3Score9;
	}

	public void setQ3Score9(Integer score9) {
		if (!isValidScore(score9, q3ItemCount9)) return;
		q3Score9 = score9;
	}

	public Integer getQ3Score10() {
		return q3Score10;
	}

	public void setQ3Score10(Integer score10) {
		if (!isValidScore(score10, q3ItemCount10)) return;
		q3Score10 = score10;
	}

	public Integer getQ3Score11() {
		return q3Score11;
	}

	public void setQ3Score11(Integer score11) {
		if (!isValidScore(score11, q3ItemCount11)) return;
		q3Score11 = score11;
	}

	public Integer getQ3Score12() {
		return q3Score12;
	}

	public void setQ3Score12(Integer score12) {
		if (!isValidScore(score12, q3ItemCount12)) return;
		q3Score12 = score12;
	}

	public Integer getQ4Score1() {
		return q4Score1;
	}

	public void setQ4Score1(Integer score1) {
		if (!isValidScore(score1, q4ItemCount1)) return;
		q4Score1 = score1;
	}

	public Integer getQ4Score2() {
		return q4Score2;
	}

	public void setQ4Score2(Integer score2) {
		if (!isValidScore(score2, q4ItemCount2)) return;
		q4Score2 = score2;
	}

	public Integer getQ4Score3() {
		return q4Score3;
	}

	public void setQ4Score3(Integer score3) {
		if (!isValidScore(score3, q4ItemCount3)) return;
		q4Score3 = score3;
	}

	public Integer getQ4Score4() {
		return q4Score4;
	}

	public void setQ4Score4(Integer score4) {
		if (!isValidScore(score4, q4ItemCount4)) return;
		q4Score4 = score4;
	}

	public Integer getQ4Score5() {
		return q4Score5;
	}

	public void setQ4Score5(Integer score5) {
		if (!isValidScore(score5, q4ItemCount5)) return;
		q4Score5 = score5;
	}

	public Integer getQ4Score6() {
		return q4Score6;
	}

	public void setQ4Score6(Integer score6) {
		if (!isValidScore(score6, q4ItemCount6)) return;
		q4Score6 = score6;
	}

	public Integer getQ4Score7() {
		return q4Score7;
	}

	public void setQ4Score7(Integer score7) {
		if (!isValidScore(score7, q4ItemCount7)) return;
		q4Score7 = score7;
	}

	public Integer getQ4Score8() {
		return q4Score8;
	}

	public void setQ4Score8(Integer score8) {
		if (!isValidScore(score8, q4ItemCount8)) return;
		q4Score8 = score8;
	}

	public Integer getQ4Score9() {
		return q4Score9;
	}

	public void setQ4Score9(Integer score9) {
		if (!isValidScore(score9, q4ItemCount9)) return;
		q4Score9 = score9;
	}

	public Integer getQ4Score10() {
		return q4Score10;
	}

	public void setQ4Score10(Integer score10) {
		if (!isValidScore(score10, q4ItemCount10)) return;
		q4Score10 = score10;
	}

	public Integer getQ4Score11() {
		return q4Score11;
	}

	public void setQ4Score11(Integer score11) {
		if (!isValidScore(score11, q4ItemCount11)) return;
		q4Score11 = score11;
	}

	public Integer getQ4Score12() {
		return q4Score12;
	}

	public void setQ4Score12(Integer score12) {
		if (!isValidScore(score12, q4ItemCount12)) return;
		q4Score12 = score12;
	}

	@Override
	public void save() {
    	if (facultyGradingTaskId>0) {
    		if (parentBean instanceof FacultyGradingTask) {
        		FacultyGradingTask fac = (FacultyGradingTask) parentBean;
            	if (fac!=null) {
            		Integer seqt = seq;
            		if (!isEmptyKey()) {
                		BeanUtil.copyBean(fac, this);
                		this.seq = seqt;
            		}
            		else {
                		BeanUtil.copyBean(fac, this);
            			this.seq = null;
            		}
            		this.scheduleId = fac.scheduleId;
            		this.facultyName = fac.faculty;
            	}
    		}
    		else {
        		FacultyGradingTask fac = (FacultyGradingTask) AbstractIBean.firstRecord("SELECT a FROM FacultyGradingTask a WHERE a.seq="+facultyGradingTaskId);
            	if (fac!=null) {
            		Integer seqt = seq;
            		if (!isEmptyKey()) {
                		BeanUtil.copyBean(fac, this);
                		this.seq = seqt;
            		}
            		else {
                		BeanUtil.copyBean(fac, this);
            			this.seq = null;
            		}
            		this.scheduleId = fac.scheduleId;
            		this.facultyName = fac.faculty;
            	}
    		}
    	}
    	if (studentId>0 && (studentName==null || studentName.trim().isEmpty())) {
        	Student stud = (Student) AbstractIBean.firstRecord("SELECT a FROM Student a WHERE a.personId="+studentId);
        	if (stud!=null) {
        		studentName = stud.toString();
        	}
    	}
    	if (facultyId>0 && (facultyName==null || facultyName.trim().isEmpty())) {
        	EmployeeFaculty fac = (EmployeeFaculty) AbstractIBean.firstRecord("SELECT a FROM EmployeeFaculty a WHERE a.personId="+facultyId);
        	if (fac!=null) {
        		facultyName = fac.toString();
        	}
    	}
		super.save();
	}

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "weight");
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "studentSubjectId");
		runIndex(2, "facultyGradingTaskId");
		runUniqueIndex(3, "schoolYear","studentId","subject","component");
		runIndex(4, "section","subject");
		runIndex(5, "studentId","subject");
	}
}
