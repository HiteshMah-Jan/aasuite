package bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Apr 3, 2009
 * Time: 5:53:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ProjectTask")
@UITemplate(showChart=true, template= TemplateTabSinglePage.class, gridCount = 4, 
        columnSearch = {"taskType","description","employee","amount","status","priority","startDate","manDays"},
        criteriaSearch = {"taskType","description","status","priority"}, sumFooter="3,7"
)
@Displays({
//    @Display(name="projectName", type="PopSearch", linktoBean = Project.class),
    @Display(name="taskType", gridFieldWidth = 3, width = -1, type="Combo", modelCombo = {"BUG","ENHANCEMENT"}),
    @Display(name="description", gridFieldWidth = 3, width = -1, upCase=false),
    @Display(name="employeeId", gridFieldWidth = 3, width = -1, type="PopSearch", linktoBean = Employee.class),
    @Display(name="employeeId2", gridFieldWidth = 3, width = -1, type="PopSearch", linktoBean = Employee.class),
    @Display(name="status", type="Combo", modelCombo = {"OPEN","DEVELOPMENT","BUG FIX","TESTING","CLOSE"}),
    @Display(name="priority", type="Combo", modelCombo = {"MINOR","MAJOR","CRITICAL","SHOW STOPPER"}),
    @Display(name="manDays"),
    @Display(name="amount"),
    @Display(name="startDate",upCase=false,width=-1),
    @Display(name="completionDate")
})
@ActionButtons({
    @ActionButton(name="btnAddComment", label="Add Comment")
})
public class ProjectTask extends AbstractIBean {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "projectName", nullable = false)
    public String projectName;
    @Column(name = "taskType", nullable = false)
    public String taskType;
    @Column(name = "description", nullable = false)
    public String description;
    @Column(name = "employeeId", nullable = false)
    public int employeeId;
    @Column(name = "employeeId2")
    public int employeeId2;
    @Column(name = "manDays")
    public int manDays;
    @Column(name = "amount")
    public double amount;
    @Column(name = "status")
    public String status;
    @Column(name = "priority")
    public String priority;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    public Date startDate;
    @Column(name = "completionDate")
    @Temporal(TemporalType.DATE)
    public Date completionDate;
    @Column(name = "employee")
    public String employee;
    @Column(name = "employee2")
    public String employee2;

    @Override
	public void save() {
    	employee = extractPersonName(employeeId);
    	employee2 = extractPersonName(employeeId2);
		super.save();
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getEmployee2() {
		return employee2;
	}

	public void setEmployee2(String employee2) {
		this.employee2 = employee2;
	}

	public int getEmployeeId2() {
		return employeeId2;
	}

	public void setEmployeeId2(int employeeId2) {
		this.employeeId2 = employeeId2;
	}

	public int getManDays() {
		return manDays;
	}

	public void setManDays(int manDays) {
		this.manDays = manDays;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "projectName", "description");
	}
}
