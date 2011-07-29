/*
 * Subject.java
 *
 * Created on Nov 15, 2007, 1:18:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabSinglePage;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.PanelUtil;
import bean.admin.AppConfig;
import bean.extension.SectionScheduleExt;
import constants.UserInfo;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Subject")
@UITemplate(template=TemplateTabSinglePage.class, sumFooter="2",
		columnSearch={"code","subject","unit","gradeLevel"}, gridCount=4, title="Subject")
@ChildRecords(value={ 
    @ChildRecord(template=ChildTemplateListOnly.class,entity=SectionScheduleExt.class, fieldMapping={"code","subject"}, sql="SELECT a FROM Schedule a WHERE a.subject='${code}'", title="Schedule")
}, info={
		@ParentAddInfo(title = "Components", gridCount=6, 
				displayFields = {
					"criteria1","percentage1","usePercentage1",
					"criteria2","percentage2","usePercentage2",
					"criteria3","percentage3","usePercentage3",
					"criteria4","percentage4","usePercentage4",
					"criteria5","percentage5","usePercentage5",
					"criteria6","percentage6","usePercentage6",
					"criteria7","percentage7","usePercentage7",
					"criteria8","percentage8","usePercentage8",
					"criteria9","percentage9","usePercentage9",
					"criteria10","percentage10","usePercentage10",
					"dummyField","totalPercentage"
				}),
})
@Displays({
    @Display(name="code"),
    @Display(name="unit",width=40),
    @Display(name="subject",gridFieldWidth=3,width=-1),
    @Display(name="course",type="PopSearch",linktoBean=Course.class,gridFieldWidth=3,width=-1),
    @Display(name="gradeLevel",gridFieldWidth=3,width=-1,type="PopSearch",linktoBean=GradeLevel.class),
    @Display(name="amount"),
    
    @Display(name="criteria1", labelTop=true, label="Criteria", type="PopSearch", linktoBean=GradingComponent.class, addInfoOnly=true),
    @Display(name="percentage1", labelTop=true, label="%", addInfoOnly=true),
    @Display(name="usePercentage1", labelTop=true, label="Use %", addInfoOnly=true),
    @Display(name="criteria2", hideLabel=true, type="PopSearch", linktoBean=GradingComponent.class, addInfoOnly=true),
    @Display(name="percentage2", hideLabel=true, addInfoOnly=true),
    @Display(name="usePercentage2", hideLabel=true, addInfoOnly=true),
    @Display(name="criteria3", hideLabel=true, type="PopSearch", linktoBean=GradingComponent.class, addInfoOnly=true),
    @Display(name="percentage3", hideLabel=true, addInfoOnly=true),
    @Display(name="usePercentage3", hideLabel=true, addInfoOnly=true),
    @Display(name="criteria4", hideLabel=true, type="PopSearch", linktoBean=GradingComponent.class, addInfoOnly=true),
    @Display(name="percentage4", hideLabel=true, addInfoOnly=true),
    @Display(name="usePercentage4", hideLabel=true, addInfoOnly=true),
    @Display(name="criteria5", hideLabel=true, type="PopSearch", linktoBean=GradingComponent.class, addInfoOnly=true),
    @Display(name="percentage5", hideLabel=true, addInfoOnly=true),
    @Display(name="usePercentage5", hideLabel=true, addInfoOnly=true),
    @Display(name="criteria6", hideLabel=true, type="PopSearch", linktoBean=GradingComponent.class, addInfoOnly=true),
    @Display(name="percentage6", hideLabel=true, addInfoOnly=true),
    @Display(name="usePercentage6", hideLabel=true, addInfoOnly=true),
    @Display(name="criteria7", hideLabel=true, type="PopSearch", linktoBean=GradingComponent.class, addInfoOnly=true),
    @Display(name="percentage7", hideLabel=true, addInfoOnly=true),
    @Display(name="usePercentage7", hideLabel=true, addInfoOnly=true),
    @Display(name="criteria8", hideLabel=true, type="PopSearch", linktoBean=GradingComponent.class, addInfoOnly=true),
    @Display(name="percentage8", hideLabel=true, addInfoOnly=true),
    @Display(name="usePercentage8", hideLabel=true, addInfoOnly=true),
    @Display(name="criteria9", hideLabel=true, type="PopSearch", linktoBean=GradingComponent.class, addInfoOnly=true),
    @Display(name="percentage9", hideLabel=true, addInfoOnly=true),
    @Display(name="usePercentage9", hideLabel=true, addInfoOnly=true),
    @Display(name="criteria10", hideLabel=true, type="PopSearch", linktoBean=GradingComponent.class, addInfoOnly=true),
    @Display(name="percentage10", hideLabel=true, addInfoOnly=true),
    @Display(name="usePercentage10", hideLabel=true, addInfoOnly=true),
    @Display(name="dummyField", type="Label", label="Total Percentage", addInfoOnly=true),
    @Display(name="totalPercentage", hideLabel=true, addInfoOnly=true),
})
@Reports({
    @template.Report(reportFile="SubjectComponent", reportTitle="Components", reportSql="")
   })
public class Subject extends AbstractIBean implements Serializable {
	public static void main(String[] args) {
		view(Subject.class);
	}
    @Id
    @Column(name = "code", nullable = false, length = 100)
    public String code;
    @Column(name = "course" )
    public String course;
    @Column(name = "gradeLevel")
    public String gradeLevel;
    @Column(name = "unit", nullable = false)
    public double unit;
    @Column(name = "subject", nullable = false, length = 150)
    public String subject;
    @Column(name = "head")
    public String head;
    @Column(name = "headId")
    public int headId;
    public double amount;
    
    public String criteria1;
    public double percentage1;
    public boolean usePercentage1;
    public String criteria2;
    public double percentage2;
    public boolean usePercentage2;
    public String criteria3;
    public double percentage3;
    public boolean usePercentage3;
    public String criteria4;
    public double percentage4;
    public boolean usePercentage4;
    public String criteria5;
    public double percentage5;
    public boolean usePercentage5;
    public String criteria6;
    public double percentage6;
    public boolean usePercentage6;
    public String criteria7;
    public double percentage7;
    public boolean usePercentage7;
    public String criteria8;
    public double percentage8;
    public boolean usePercentage8;
    public String criteria9;
    public double percentage9;
    public boolean usePercentage9;
    public String criteria10;
    public double percentage10;
    public boolean usePercentage10;
    public double totalPercentage;

	public double getTotalPercentage() {
		return totalPercentage;
	}

	public void setTotalPercentage(double totalPercentage) {
		this.totalPercentage = totalPercentage;
	}

	public String getCriteria1() {
		return criteria1;
	}

	public void setCriteria1(String criteria1) {
		this.criteria1 = criteria1;
	}

	public double getPercentage1() {
		return percentage1;
	}

	public void setPercentage1(double percentage1) {
		this.percentage1 = percentage1;
	}

	public boolean isUsePercentage1() {
		return usePercentage1;
	}

	public void setUsePercentage1(boolean usePercentage1) {
		this.usePercentage1 = usePercentage1;
	}

	public String getCriteria2() {
		return criteria2;
	}

	public void setCriteria2(String criteria2) {
		this.criteria2 = criteria2;
	}

	public double getPercentage2() {
		return percentage2;
	}

	public void setPercentage2(double percentage2) {
		this.percentage2 = percentage2;
	}

	public boolean isUsePercentage2() {
		return usePercentage2;
	}

	public void setUsePercentage2(boolean usePercentage2) {
		this.usePercentage2 = usePercentage2;
	}

	public String getCriteria3() {
		return criteria3;
	}

	public void setCriteria3(String criteria3) {
		this.criteria3 = criteria3;
	}

	public double getPercentage3() {
		return percentage3;
	}

	public void setPercentage3(double percentage3) {
		this.percentage3 = percentage3;
	}

	public boolean isUsePercentage3() {
		return usePercentage3;
	}

	public void setUsePercentage3(boolean usePercentage3) {
		this.usePercentage3 = usePercentage3;
	}

	public String getCriteria4() {
		return criteria4;
	}

	public void setCriteria4(String criteria4) {
		this.criteria4 = criteria4;
	}

	public double getPercentage4() {
		return percentage4;
	}

	public void setPercentage4(double percentage4) {
		this.percentage4 = percentage4;
	}

	public boolean isUsePercentage4() {
		return usePercentage4;
	}

	public void setUsePercentage4(boolean usePercentage4) {
		this.usePercentage4 = usePercentage4;
	}

	public String getCriteria5() {
		return criteria5;
	}

	public void setCriteria5(String criteria5) {
		this.criteria5 = criteria5;
	}

	public double getPercentage5() {
		return percentage5;
	}

	public void setPercentage5(double percentage5) {
		this.percentage5 = percentage5;
	}

	public boolean isUsePercentage5() {
		return usePercentage5;
	}

	public void setUsePercentage5(boolean usePercentage5) {
		this.usePercentage5 = usePercentage5;
	}

	public String getCriteria6() {
		return criteria6;
	}

	public void setCriteria6(String criteria6) {
		this.criteria6 = criteria6;
	}

	public double getPercentage6() {
		return percentage6;
	}

	public void setPercentage6(double percentage6) {
		this.percentage6 = percentage6;
	}

	public boolean isUsePercentage6() {
		return usePercentage6;
	}

	public void setUsePercentage6(boolean usePercentage6) {
		this.usePercentage6 = usePercentage6;
	}

	public String getCriteria7() {
		return criteria7;
	}

	public void setCriteria7(String criteria7) {
		this.criteria7 = criteria7;
	}

	public double getPercentage7() {
		return percentage7;
	}

	public void setPercentage7(double percentage7) {
		this.percentage7 = percentage7;
	}

	public boolean isUsePercentage7() {
		return usePercentage7;
	}

	public void setUsePercentage7(boolean usePercentage7) {
		this.usePercentage7 = usePercentage7;
	}

	public String getCriteria8() {
		return criteria8;
	}

	public void setCriteria8(String criteria8) {
		this.criteria8 = criteria8;
	}

	public double getPercentage8() {
		return percentage8;
	}

	public void setPercentage8(double percentage8) {
		this.percentage8 = percentage8;
	}

	public boolean isUsePercentage8() {
		return usePercentage8;
	}

	public void setUsePercentage8(boolean usePercentage8) {
		this.usePercentage8 = usePercentage8;
	}

	public String getCriteria9() {
		return criteria9;
	}

	public void setCriteria9(String criteria9) {
		this.criteria9 = criteria9;
	}

	public double getPercentage9() {
		return percentage9;
	}

	public void setPercentage9(double percentage9) {
		this.percentage9 = percentage9;
	}

	public boolean isUsePercentage9() {
		return usePercentage9;
	}

	public void setUsePercentage9(boolean usePercentage9) {
		this.usePercentage9 = usePercentage9;
	}

	public String getCriteria10() {
		return criteria10;
	}

	public void setCriteria10(String criteria10) {
		this.criteria10 = criteria10;
	}

	public double getPercentage10() {
		return percentage10;
	}

	public void setPercentage10(double percentage10) {
		this.percentage10 = percentage10;
	}

	public boolean isUsePercentage10() {
		return usePercentage10;
	}

	public void setUsePercentage10(boolean usePercentage10) {
		this.usePercentage10 = usePercentage10;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public int getHeadId() {
		return headId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}

	@Override
	public void delete() {
		if (UserInfo.canModifyReference()) {
			String oldCode = code;
			super.delete();
			boolean b = PanelUtil.showPrompt(null, "Subject is already deleted, would you like to delete records from Curriculum?");
			if (b) {
				DBClient.runSQLNative("DELETE FROM CourseSubject WHERE subject='",oldCode,"'");
				b = PanelUtil.showPrompt(null, "Curriculum Subjects is already deleted, would you like to delete Student Curriculum?");
				if (b) {
					DBClient.runSQLNative("DELETE FROM StudentSubject WHERE subject='",oldCode,"' AND grade1<=0");
				}
			}
		}
		else {
			PanelUtil.showError(null, "Only Administrator or HAS REFERENCE ACCESS duty can delete Subject records.");
		}
	}

	@Override
	public void save() {
		if (!UserInfo.canModifyReference()) {
			PanelUtil.showError(null, "Only Administrator or 'HAS REFERENCE ACCESS' duty code can update Subject records.");
			return;
		}
		DBClient.runSQLNative("UPDATE StudentSubject SET gradeLevel='",gradeLevel,"', unit=",unit," WHERE subject='",code,"'");
    	head = extractPersonName(headId);
    	if (amount==0) {
    		amount = DataUtil.getMoneyFormat(AppConfig.getAmountPerUnit()+unit);
    	}
		super.save();
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code", "subject");
    }

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
    	if (isEmptyKey()) return "";
        return BeanUtil.concat(code,"_",subject);
    }

    public double getUnit() {
        return unit;
    }

    public void setUnit(double unit) {
        this.unit = unit;
    }

    public static Subject createSubjectObj(String course, String code, String gradeLevel, int unit) {
        Subject sec = new Subject();
        sec.course = course;
        sec.code = BeanUtil.concat(gradeLevel,unit,"_",code);
        sec.subject = code;
        sec.gradeLevel = BeanUtil.concat(gradeLevel,unit);
        sec.unit = 1;
        return sec;
    }
    
    @Override
    protected void runSetup() {
    }   

    @Override
    public boolean cacheClient() {
        return true;
    }
    
	@Override
	public void setupIndex() {
		DBClient.runSQLNative("UPDATE Section SET shift=1 WHERE shift IS NULL");
		List lst = DBClient.getList("SELECT a FROM Section a WHERE a.faculty IS NULL", 1, 15000);
		DBClient.persistBean(lst);
	}
	@Transient
	public List<SubjectGradingCriteria> extractGradingCriteria() {
		List<SubjectGradingCriteria> crits = new ArrayList<SubjectGradingCriteria>();
		for(int i=1; i<=10; i++) {
			String criteria = (String) BeanUtil.getPropertyValue(this, "criteria1");
			double percent = BeanUtil.getDoubleValue(this, "percentage1");
			if (!isEmpty(criteria) && percent>0) {
				boolean usePercent = (Boolean) BeanUtil.getPropertyValue(this, "usePercentage1");
				SubjectGradingCriteria crit = new SubjectGradingCriteria();
				crit.criteria = criteria;
				crit.percentage = percent;
				crit.usePercentage = usePercent;
				crits.add(crit);
			}
		}
		return crits;
	}
}
