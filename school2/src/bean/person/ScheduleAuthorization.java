package bean.person;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import util.DBClient;
import bean.Schedule;
import bean.admin.AppConfig;
import constants.UserInfo;


@Entity
@Table(name = "ScheduleAuthorization")
@UITemplate(template=TemplateTabPage.class,
    columnSearch={"schoolYear", "userId", "section", "subject", "faculty"},
    criteriaSearch={"schoolYear", "userId", "scheduleId"},
    gridCount=4, title="Schedule Authorization")
@Displays({
        @Display(name="schoolYear", type="Label"),
        @Display(name="userId", type="Label"),
        @Display(name="scheduleId", type="PopSearch", linktoBean=Schedule.class),
        @Display(name="section", type="Label"),
        @Display(name="subject", type="Label"),
        @Display(name="faculty", type="Label")
})
public class ScheduleAuthorization extends AbstractIBean implements Serializable {
	public ScheduleAuthorization() {
		schoolYear = AppConfig.getSchoolYear();
		userId = UserInfo.getUserName();
	}
	
    @Override
	public void save() {
    	if (scheduleId>0) {
    		Schedule s = (Schedule) DBClient.getFirstRecord("SELECT a FROM Schedule a WHERE a.seq=",scheduleId);
    		if (s!=null) {
    			section = s.section;
    			subject = s.subject;
    			faculty = s.faculty;
    		}
    	}
		super.save();
	}

	@Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "userId")
    public String userId;
    @Column(name = "scheduleId")
    public int scheduleId;
    @Column(name = "section")
    public String section;
    @Column(name = "subject")
    public String subject;
    @Column(name = "faculty")
    public String faculty;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	@Override
	public void setupIndex() {
		runIndex(1, "schoolYear", "userId");
	}

	@Override
	public String popupSearch(String criteria) {
        return buildSearch(criteria, "schoolYear","userId");
	}
}
