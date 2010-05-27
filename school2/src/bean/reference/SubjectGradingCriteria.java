/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;
import util.BeanUtil;
import util.PanelUtil;
import bean.admin.AppConfig;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "SubjectGradingCriteria")
@UITemplate(template=TemplateSinglePage.class,
		columnSearch={"subject","criteria","percentage","description","usePercentage"}, 
		gridCount=8, title="Subject Grading Criteria", sumFooter="2")
@Displays({
//        @Display(name="subject", linktoBean=Subject.class, label="Subject", gridFieldWidth=3, width=-1, type="PopSearch"),
        @Display(name="sortNumber", label="Sort Number", type="Combo", modelCombo={"1","2","3","4","5","6","7","8","9","10"}),
        @Display(name="criteria",type="Combo",
        		modelCombo={"ASSIGNMENT","ATTENDANCE","ATTITUDE","COMPOSITION/CREATIVE OUTPUT","FORMAL THEME/JOURNAL","GAP","HANDS ON","LABORATORY","LONG TEST","MASTERY 1","MASTERY 2","OPERATIONAL READING","OPERATIONAL MATH","PARTICIPATION","PERFORMANCE","PRACTICAL TEST","PRO MATH","PROJECT","QUARTERLY TEST","QUIZ","QUIZ/SW/READING TEST","RECITATION","SEATWORK","SW/HW/QUIZ","UNIFORM","WORK ETHICS"}),
        @Display(name="percentage", label="Weight"),
        @Display(name="usePercentage"),
        @Display(name="description", gridFieldWidth=7, width=-1)
})
public class SubjectGradingCriteria extends AbstractIBean implements Serializable {
    
    @Override
	public void delete() {
    	LockGrading lock = LockGrading.extractGrading(AppConfig.getSchoolYear());
    	if (lock.isQ1Locked()) {
    		PanelUtil.showError(null, "Q1 is already locked, you cannot delete Subject Component record.");
    		return;
    	}
		super.delete();
	}

	@Override
	public void save() {
    	LockGrading lock = LockGrading.extractGrading(AppConfig.getSchoolYear());
    	if (lock.isQ1Locked()) {
    		PanelUtil.showError(null, "Q1 is already locked, you cannot update Subject Component record.");
    		return;
    	}
		super.save();
	}

	public static void main(String[] args) {
        view(SubjectGradingCriteria.class);
    }
    
    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return BeanUtil.concat(subject," - ",criteria," ",percentage);
    }
	
	@Override
	public boolean cacheClient() {
		return true;
	}

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "subject")
    public String subject;
    @Column(name = "sortNumber")
    public int sortNumber;
    @Column(name = "criteria")
    public String criteria;
    @Column(name = "description")
    public String description;
    @Column(name = "percentage")
    public double percentage;
    @Column(name = "usePercentage")
    public boolean usePercentage;

    public boolean isUsePercentage() {
		return usePercentage;
	}

	public void setUsePercentage(boolean usePercentage) {
		this.usePercentage = usePercentage;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "subjectCode","name", "percentage");
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "subject");
	}
}



