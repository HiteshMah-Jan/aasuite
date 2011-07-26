package bean.embedded;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;
import template.screen.TemplateTabSinglePageLeftRight;

@Embeddable
@UITemplate(template=TemplateEmbedded.class, gridCount = 8, columnSearch = {"seq"})
@Displays({
	@Display(name="education1", label="Education", labelTop=true),
	@Display(name="school1", label="School", labelTop=true),
	@Display(name="startDate1", label="Start", labelTop=true),
	@Display(name="endDate1", label="End", labelTop=true),
	@Display(name="education2", hideLabel=true),
	@Display(name="school2", hideLabel=true),
	@Display(name="startDate2", hideLabel=true),
	@Display(name="endDate2", hideLabel=true),
	@Display(name="education3", hideLabel=true),
	@Display(name="school3", hideLabel=true),
	@Display(name="startDate3", hideLabel=true),
	@Display(name="endDate3", hideLabel=true)
})
public class EmbeddedEducation extends AbstractIBean {
	public String education1;
	public String school1;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date startDate1;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date endDate1;
	public String education2;
	public String school2;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date startDate2;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date endDate2;
	public String education3;
	public String school3;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date startDate3;
    @Temporal(value = javax.persistence.TemporalType.DATE)
	public Date endDate3;
	
	public String getEducation1() {
		return education1;
	}
	public void setEducation1(String education1) {
		this.education1 = education1;
	}
	public String getSchool1() {
		return school1;
	}
	public void setSchool1(String school1) {
		this.school1 = school1;
	}
	public Date getStartDate1() {
		return startDate1;
	}
	public void setStartDate1(Date startDate1) {
		this.startDate1 = startDate1;
	}
	public Date getEndDate1() {
		return endDate1;
	}
	public void setEndDate1(Date endDate1) {
		this.endDate1 = endDate1;
	}
	public String getEducation2() {
		return education2;
	}
	public void setEducation2(String education2) {
		this.education2 = education2;
	}
	public String getSchool2() {
		return school2;
	}
	public void setSchool2(String school2) {
		this.school2 = school2;
	}
	public Date getStartDate2() {
		return startDate2;
	}
	public void setStartDate2(Date startDate2) {
		this.startDate2 = startDate2;
	}
	public Date getEndDate2() {
		return endDate2;
	}
	public void setEndDate2(Date endDate2) {
		this.endDate2 = endDate2;
	}
	public String getEducation3() {
		return education3;
	}
	public void setEducation3(String education3) {
		this.education3 = education3;
	}
	public String getSchool3() {
		return school3;
	}
	public void setSchool3(String school3) {
		this.school3 = school3;
	}
	public Date getStartDate3() {
		return startDate3;
	}
	public void setStartDate3(Date startDate3) {
		this.startDate3 = startDate3;
	}
	public Date getEndDate3() {
		return endDate3;
	}
	public void setEndDate3(Date endDate3) {
		this.endDate3 = endDate3;
	}
	public static void main(String[] args) {
		view(EmbeddedEducation.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
