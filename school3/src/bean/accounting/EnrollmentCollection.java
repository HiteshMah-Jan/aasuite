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

import bean.StudentAssessment;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
import util.DBClient;

@Entity
@Table(name = "EnrollmentCollection")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"collected","paymentDate","bankAccount","bankReference"})
@Displays({
	@Display(name="collected", mandatory=true),
	@Display(name="paymentDate", mandatory=true),
	@Display(name="bankAccount", type="PopSearch", mandatory=true, linktoBean=BankAccount.class),
	@Display(name="bankReference", mandatory=true)
})
public class EnrollmentCollection extends AbstractIBean {
	public void save() {
		if (seq==null || seq==0) {
//			update the value of collection
			StudentAssessment assess = (StudentAssessment) DBClient.getFirstRecord("SELECT a FROM StudentAssessment a WHERE a.seq="+assessId);
			assess.addCollection(collected);
			assess.save();
			this.studentId = assess.studentId;
		}
		super.save();
	}
	public static void main(String[] args) {
		view(EnrollmentCollection.class);
	}
    @Id
    public Integer seq;
    public int studentId;
    public int assessId;
    public String schoolYear;
    public double collected;
    @Temporal(value = TemporalType.DATE)
    public Date paymentDate = constants.Constants.useDate;
    public String bank;
    public String bankAccount;
    public String bankReference;
    
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getAssessId() {
		return assessId;
	}

	public void setAssessId(int assessId) {
		this.assessId = assessId;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public double getCollected() {
		return collected;
	}

	public void setCollected(double collected) {
		this.collected = collected;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankReference() {
		return bankReference;
	}

	public void setBankReference(String bankReference) {
		this.bankReference = bankReference;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
