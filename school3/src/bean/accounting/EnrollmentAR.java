/*
 * Payment.java
 *
 * Created on Jan 17, 2008, 9:27:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

@Entity
@Table(name = "EnrollmentAR")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"schoolYear","duedate","amount"})
@Displays({
	@Display(name="schoolYear"),
	@Display(name="duedate"),
	@Display(name="amount")
})
public class EnrollmentAR extends AbstractIBean {
	public static void main(String[] args) {
		view(EnrollmentAR.class);
	}
    @Id
    public Integer seq;
    public int studentId;
    public int assessId;
    public String schoolYear;
    public String code;
    public double amount;
    @Temporal(value = TemporalType.DATE)
    public Date duedate = constants.Constants.useDate;

	public int getAssessId() {
		return assessId;
	}

	public void setAssessId(int assessId) {
		this.assessId = assessId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
