/*
 * PersonAttendance.java
 *
 * Created on Nov 18, 2007, 8:26:21 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import util.DBClient;
import util.DateUtil;
import util.Log;
import util.PanelUtil;
import util.ThreadPoolUtil;
import bean.Employee;
import bean.reference.EventHoliday;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PersonAttendance")
@UITemplate(template = TemplateDefault.class, gridCount = 2, columnSearch = {"attendanceDate","login","logout","totalHours","overtime","late","attendanceType"})
@Displays({
        @Display(name="personId"),
        @Display(name="attendanceDate", label="Date"),
        @Display(name="login", label="In"),
        @Display(name="logout", label="Out"),
        @Display(name="totalHours"),
        @Display(name="late"),
        @Display(name="overtime"),
        @Display(name="nightDiff"),
        @Display(name="payrollid"),
        @Display(name="attendanceType", label="Type"),
        @Display(name="approvedOvertime", label="OT"),
        @Display(name="approvedNightDiff"),
        @Display(name="overtimeAmount"),
        @Display(name="nightDifAmount"),
        @Display(name="lateAmountDeduction"),
        @Display(name="totalAmount")
})
public class PersonAttendance extends PersonAttribute implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;
    @Column(name = "attendanceDate")
    @Temporal(value = TemporalType.DATE)
    public Date attendanceDate;
    @Column(name = "login", length = 5)
    public String login;
    @Column(name = "logout", length = 5)
    public String logout;
    @Column(name = "totalHours")
    public double totalHours;
    @Column(name = "late")
    public double late;
    @Column(name = "overtime")
    public double overtime;
    @Column(name = "nightDiff")
    public double nightDiff;
    @Column(name = "payrollid")
    public int payrollid;
    @Column(name = "attendanceType")
    public String attendanceType;
    @Column(name = "approvedOvertime")
    public double approvedOvertime;
    @Column(name = "approvedNightDiff")
    public double approvedNightDiff;
    @Column(name = "overtimeAmount")
    public double overtimeAmount;
    @Column(name = "nightDifAmount")
    public double nightDifAmount;
    @Column(name = "lateAmountDeduction")
    public double lateAmountDeduction;
    @Column(name = "totalAmount")
    public double totalAmount;
    @Column(name = "holidayPercentPay")
    public double holidayPercentPay;
    @Column(name = "holidayExtraPay")
    public double holidayExtraPay;
    @Column(name = "personName")
    public String personName;

    public double getHolidayPercentPay() {
		return holidayPercentPay;
	}

	public void setHolidayPercentPay(double holidayPercentPay) {
		this.holidayPercentPay = holidayPercentPay;
	}

	public double getHolidayExtraPay() {
		return holidayExtraPay;
	}

	public void setHolidayExtraPay(double holidayExtraPay) {
		this.holidayExtraPay = holidayExtraPay;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getApprovedNightDiff() {
        return approvedNightDiff;
    }

    public void setApprovedNightDiff(double approvedNightDiff) {
        this.approvedNightDiff = approvedNightDiff;
    }

    public double getApprovedOvertime() {
        return approvedOvertime;
    }

    public void setApprovedOvertime(double approvedOvertime) {
        this.approvedOvertime = approvedOvertime;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public double getLate() {
        return late;
    }

    public void setLate(double late) {
        this.late = late;
    }

    public double getLateAmountDeduction() {
        return lateAmountDeduction;
    }

    public void setLateAmountDeduction(double lateAmountDeduction) {
        this.lateAmountDeduction = lateAmountDeduction;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public double getNightDifAmount() {
        return nightDifAmount;
    }

    public void setNightDifAmount(double nightDifAmount) {
        this.nightDifAmount = nightDifAmount;
    }

    public double getNightDiff() {
        return nightDiff;
    }

    public void setNightDiff(double nightDiff) {
        this.nightDiff = nightDiff;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public double getOvertimeAmount() {
        return overtimeAmount;
    }

    public void setOvertimeAmount(double overtimeAmount) {
        this.overtimeAmount = overtimeAmount;
    }

    public int getPayrollid() {
        return payrollid;
    }

    public void setPayrollid(int payrollid) {
        this.payrollid = payrollid;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    @Override
    public int getPersonId() {
        return personId;
    }

    @Override
    public Integer getSeq() {
        return seq;
    }
    
    public void calculate() {
        //this will calculate the time
    	if (login==null || login.isEmpty() || logout==null || logout.isEmpty()) return;
    	login = DateUtil.formatTime(login);
    	logout = DateUtil.formatTime(logout);
    	double tot = DateUtil.getTotalHours(login, logout);
    	if (tot>=0) {
        	if (tot-1>0) changeValue("totalHours", tot-1);
        	if (tot-1>0) changeValue("totalHours", tot-1);
    	}
    	if (tot>9) {
    		if (tot-9>0) changeValue("overtime", tot-9);
    		if (tot-9>0) changeValue("overtime", tot-9);
    	}
    	else {
        	changeValue("overtime", 0);
        	changeValue("overtime", 0);
    	}
    	if (tot<9) {
    		if (attendanceType==null || attendanceType.equals("REGULAR")) {
            	changeValue("late", 9-tot);
            	changeValue("late", 9-tot);
    		}
    	}
    	else {
        	changeValue("late", 0);
        	changeValue("late", 0);
    	}
    }

	@Override
	public void save() {
		personName = extractPersonName(personId);
		super.save();
	}

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "personId", "attendanceDate");
	}
	
	public static void populateAttendance() {
//		this must start yesterday going back to 16 days
		boolean b = PanelUtil.showPrompt(null, "Attendance Check will take several minutes, would you like to continue.");
		if (b) {
			List<Employee> lst = DBClient.getList("SELECT a FROM Employee a");
			b = PanelUtil.showPrompt(null, "Total Employee Count [",lst.size(),"].\nWould you like to continue?");
			if (b) {
				for (Employee emp:lst) {
					if (emp.isActive) {
						ThreadPoolUtil.execute(new Runner(emp));
					}
				}
			}
		}
	}
	
	private static class Runner implements Runnable {
		Date d = new Date();
		Employee e;
		private Runner(Employee e) {
			this.e = e;
		}
		
		public void run() {
    		PanelUtil.showWaitFrame();
			List l = new ArrayList();
			for (int i=1; i<16; i++) {
				Date newd = DateUtil.addDay(d, i*-1);
				PersonAttendance att = new PersonAttendance();
				att.personId = e.personId;
				att.attendanceDate = newd;
				EventHoliday hol = EventHoliday.extractHoliday(newd);
				if (hol!=null) {
					att.attendanceType = hol.description;
				}
				else {
					boolean sat = DateUtil.Days.isSaturday(newd);
					boolean sun = DateUtil.Days.isSunday(newd);
					if (sat) {
						att.attendanceType = "SATURDAY";
					}
					else if (sun) {
						att.attendanceType = "SUNDAY";
					}
					else {
						att.attendanceType = "REGULAR";
					}
				}
				l.add(att);
			}
			insert(l);
			l.clear();
			Log.out("RUN ATTENDANCE FOR ",e.toString());
    		PanelUtil.hideWaitFrame();
		}
		
		private synchronized void insert(List l) {
			DBClient.persistBean(l);
		}
	}
}
