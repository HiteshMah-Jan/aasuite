/*
 * Schedule.java
 *
 * Created on Dec 2, 2007, 6:15:07 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.Log;
import util.PanelUtil;
import bean.admin.AppConfig;
import bean.extension.StudentSubjectManualGradingExt;
import bean.extension.StudentSubjectManualGradingQ1Ext;
import bean.extension.StudentSubjectManualGradingQ2Ext;
import bean.extension.StudentSubjectManualGradingQ3Ext;
import bean.extension.StudentSubjectManualGradingQ4Ext;
import bean.reference.GradeLevel;
import bean.reference.LockGrading;
import bean.reference.Room;
import bean.reference.Section;
import bean.reference.Subject;
import constants.UserInfo;

/**
 *
 * @author Ebhoy
 */
@Entity
@Table(name = "Schedule")
@UITemplate(template=template.screen.TemplateTabSinglePage.class,showChart=false,
		criteriaSearch={"section","subject","facultyId"},
		columnSearch={"faculty","subject","section","room1","day1","schedTime1","schedTimeEnd1","room2","day2","schedTime2","schedTimeEnd2","room3","day3","schedTime3","schedTimeEnd3"},gridCount=6)
@Displays({
        @Display(name="seq", addInfoOnly=true),
        @Display(name="subject", enabled=false, gridFieldWidth=5, width=-1,linktoBean=Subject.class, type="PopSearch"),
        @Display(name="boysAndGirls", gridFieldWidth=5, width=-1, type="Combo", modelCombo={"BOTH","BOYS ONLY","GIRLS ONLY"}),
        //@Display(name="course", label="Grade Level", type="PopSearch", linktoBean=Course.class),
        @Display(name="section", enabled=false,label="Section",linktoBean=Section.class, type="PopSearch"),
        @Display(name="facultyId", enabled=false, gridFieldWidth=3, width=250, label="Faculty",linktoBean=EmployeeFaculty.class, type="PopSearch"),
        @Display(name="minimumCapacity", addInfoOnly=true),
        @Display(name="maximumCapacity", addInfoOnly=true),
        @Display(name="room1",label = "Room", linktoBean=Room.class, type="PopSearch", labelTop = true, leftLabel="1"),
        @Display(name="day1",labelTop=true, label = "Days"),
        @Display(name="schedTime1", type = "Time", labelTop=true, label="Time Start"),
        @Display(name="schedTimeEnd1", type = "Time", labelTop=true, label="Time End"),
        @Display(name="room2",label = "2", linktoBean=Room.class, type="PopSearch", searchLabel="Room 2"),
        @Display(name="day2",hideLabel=true),
        @Display(name="schedTime2", type = "Time",hideLabel=true),
        @Display(name="schedTimeEnd2", type = "Time",hideLabel=true),
        @Display(name="room3",label = "3", linktoBean=Room.class, type="PopSearch", searchLabel="Room 3"),
        @Display(name="day3",hideLabel=true),
        @Display(name="schedTime3", type = "Time",hideLabel=true),
        @Display(name="schedTimeEnd3", type = "Time",hideLabel=true)
        })
@DisplayGroups({
    @DisplayGroup(gridCount = 8, title = "Schedule", fields = {
        "room1","day1","schedTime1", "schedTimeEnd1",
        "room2","day2","schedTime2", "schedTimeEnd2",
        "room3","day3","schedTime3", "schedTimeEnd3"
        })
})
@Reports({
    @template.Report(reportFile="FacultySchedule", reportTitle="Faculty Schedule",reportSql=""),
    @template.Report(reportFile="RoomSchedule", reportTitle="Room Schedule",reportSql=""),
    @template.Report(reportFile="SectionSchedule", reportTitle="Section Schedule",reportSql="")
})
@ActionButtons( {
	@ActionButton(name = "btnViewRoom1", label = "View Schedule of Room1"), 
	@ActionButton(name = "btnViewRoom2", label = "Room2"), 
	@ActionButton(name = "btnViewRoom3", label = "Room3"),
	@ActionButton(name = "btnViewFaculty", label = "Faculty"), 
	@ActionButton(name = "btnCheckSchedules", label = "Check Schedule") 
})
@ChildRecords({
    @ChildRecord(entity = StudentSubjectManualGradingQ1Ext.class, template=ChildTemplateListOnly.class, title="Q1", sql = "SELECT a FROM StudentSubject a WHERE a.scheduleId=${seq}", fieldMapping = {"seq", "scheduleId"}),
    @ChildRecord(entity = StudentSubjectManualGradingQ2Ext.class, template=ChildTemplateListOnly.class, title="Q2", sql = "SELECT a FROM StudentSubject a WHERE a.scheduleId=${seq}", fieldMapping = {"seq", "scheduleId"}),
    @ChildRecord(entity = StudentSubjectManualGradingQ3Ext.class, template=ChildTemplateListOnly.class, title="Q3", sql = "SELECT a FROM StudentSubject a WHERE a.scheduleId=${seq}", fieldMapping = {"seq", "scheduleId"}),
    @ChildRecord(entity = StudentSubjectManualGradingQ4Ext.class, template=ChildTemplateListOnly.class, title="Q4", sql = "SELECT a FROM StudentSubject a WHERE a.scheduleId=${seq}", fieldMapping = {"seq", "scheduleId"}),
    @ChildRecord(entity = StudentSubjectManualGradingExt.class, template=ChildTemplateListOnly.class, title="All Quarters", sql = "SELECT a FROM StudentSubject a WHERE a.scheduleId=${seq}", fieldMapping = {"seq", "scheduleId"})
})
public class Schedule extends AbstractIBean implements Serializable {
	public void lockAll(boolean b) {
		lockQ1(b);
		lockQ2(b);
		lockQ3(b);
		lockQ4(b);
	}
	
	public void lockQ1(boolean b) {
		lock(b, 1);
	}
	
	public void lockQ2(boolean b) {
		lock(b, 2);
	}

	public void lockQ3(boolean b) {
		lock(b, 3);
	}
	
	public void lockQ4(boolean b) {
		lock(b, 4);
	}
	private void lock(boolean b, int quarter) {
		String qname = BeanUtil.concat("q",quarter,"LockedBy");
		if (b) {
			BeanUtil.setPropertyValue(this, qname, UserInfo.getUserName());
		}
		else {
			BeanUtil.setPropertyValue(this, qname, null);
		}
	}

	@Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "subject")
    public String subject;
    @Column(name = "section")
    public String section;
    @Column(name = "facultyId")
    public int facultyId;
    @Column(name = "faculty")
    public String faculty;
    @Column(name = "course")
    public String course;
    @Column(name = "minimumCapacity")
    public int minimumCapacity;
    @Column(name = "maximumCapacity")
    public int maximumCapacity;
    @Column(name = "unit")
    public double unit;
    
    @Column(name = "room1")
    public String room1;
    @Column(name = "day1")
    public String day1;
    @Column(name = "schedTime1")
    public String schedTime1;
    @Column(name = "schedTimeEnd1")
    public String schedTimeEnd1;
    @Column(name = "room2")
    public String room2;
    @Column(name = "day2")
    public String day2;
    @Column(name = "schedTime2")
    public String schedTime2;
    @Column(name = "schedTimeEnd2")
    public String schedTimeEnd2;
    @Column(name = "room3")
    public String room3;
    @Column(name = "day3")
    public String day3;
    @Column(name = "schedTime3")
    public String schedTime3;
    @Column(name = "schedTimeEnd3")
    public String schedTimeEnd3;

    @Column(name = "gradeLevel")
    public String gradeLevel;

    @Column(name = "q1LockedBy")
    public String q1LockedBy;
    @Column(name = "q2LockedBy")
    public String q2LockedBy;
    @Column(name = "q3LockedBy")
    public String q3LockedBy;
    @Column(name = "q4LockedBy")
    public String q4LockedBy;
    @Column(name = "college")
    public boolean college;
    @Column(name = "unitAmount")
    public double unitAmount;
    @Column(name = "boysAndGirls")
    public String boysAndGirls;
    
    public String getBoysAndGirls() {
		return boysAndGirls;
	}

	public void setBoysAndGirls(String boysAndGirls) {
		this.boysAndGirls = boysAndGirls;
	}

	public double getUnitAmount() {
		return unitAmount;
	}

	public void setUnitAmount(double unitAmount) {
		this.unitAmount = unitAmount;
	}

	public boolean isCollege() {
		return college;
	}

	public void setCollege(boolean college) {
		this.college = college;
	}

	public String getQ1LockedBy() {
		return q1LockedBy;
	}

	public void setQ1LockedBy(String lockedBy) {
		q1LockedBy = lockedBy;
	}

	public String getQ2LockedBy() {
		return q2LockedBy;
	}

	public void setQ2LockedBy(String lockedBy) {
		q2LockedBy = lockedBy;
	}

	public String getQ3LockedBy() {
		return q3LockedBy;
	}

	public void setQ3LockedBy(String lockedBy) {
		q3LockedBy = lockedBy;
	}

	public String getQ4LockedBy() {
		return q4LockedBy;
	}

	public void setQ4LockedBy(String lockedBy) {
		q4LockedBy = lockedBy;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"subject","blockOrSection");
    }

    public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    public String getRoom1() {
        return room1;
    }

    public void setRoom1(String room1) {
        this.room1 = room1;
    }

    public String getRoom2() {
        return room2;
    }

    public void setRoom2(String room2) {
        this.room2 = room2;
    }

    public String getRoom3() {
        return room3;
    }

    public void setRoom3(String room3) {
        this.room3 = room3;
    }

    public String getSchedTime1() {
        return schedTime1;
    }

    public void setSchedTime1(String schedTime1) {
        this.schedTime1 = schedTime1;
    }

    public String getSchedTime2() {
        return schedTime2;
    }

    public void setSchedTime2(String schedTime2) {
        this.schedTime2 = schedTime2;
    }

    public String getSchedTime3() {
        return schedTime3;
    }

    public void setSchedTime3(String schedTime3) {
        this.schedTime3 = schedTime3;
    }

    public String getSchedTimeEnd1() {
        return schedTimeEnd1;
    }

    public void setSchedTimeEnd1(String schedTimeEnd1) {
        this.schedTimeEnd1 = schedTimeEnd1;
    }

    public String getSchedTimeEnd2() {
        return schedTimeEnd2;
    }

    public void setSchedTimeEnd2(String schedTimeEnd2) {
        this.schedTimeEnd2 = schedTimeEnd2;
    }

    public String getSchedTimeEnd3() {
        return schedTimeEnd3;
    }

    public void setSchedTimeEnd3(String schedTimeEnd3) {
        this.schedTimeEnd3 = schedTimeEnd3;
    }
    
    
    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return BeanUtil.concat(section," - ",subject," - ",day1," - ",schedTime1);
    }   

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public int getMinimumCapacity() {
        return minimumCapacity;
    }

    public void setMinimumCapacity(int minimumCapacity) {
        this.minimumCapacity = minimumCapacity;
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

    public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getFaculty() {
		return this.faculty;
    }
    
    @Override
	public void delete() {
		if (UserInfo.canModifyReference()) {
	    	LockGrading lock = LockGrading.extractGrading(AppConfig.getSchoolYear());
	    	if (lock.isQ1Locked()) {
	    		PanelUtil.showError(null, "Q1 is already locked, you cannot delete Schedule record.");
	    		return;
	    	}
			super.delete();
		}
		else {
    		PanelUtil.showError(null, "You do not have HAS REFERENCE ACCESS duty, you cannot delete Schedule record.");
		}
	}

	@Override
	public void save() {
    	LockGrading lock = LockGrading.extractGrading(AppConfig.getSchoolYear());
    	if (lock.isQ1Locked()) {
    		PanelUtil.showError(null, "Q1 is already locked, you cannot update Schedule record.");
    		return;
    	}
    	if (facultyId>0) {
            EmployeeFaculty person = (EmployeeFaculty) AbstractIBean.firstRecord("SELECT a FROM EmployeeFaculty a WHERE a.personId=",this.facultyId);
            if (person!=null) faculty = person.toString();
    	}
		Section sec = (Section) DBClient.getFirstRecord("SELECT a FROM Section a WHERE a.code='",section,"'");
    	if (gradeLevel==null) {
    		if (sec!=null) {
    			gradeLevel = sec.gradeLevel;
    		}
    	}
    	if (course==null) {
			GradeLevel lvl = (GradeLevel) DBClient.getFirstRecord("SELECT a FROM GradeLevel a WHERE a.code='",gradeLevel,"'");
			if (lvl!=null) {
				course = lvl.course;
			}
    	}
//    	check if subject is college
    	Subject sub = (Subject) DBClient.getFirstRecord("SELECT a FROM Subject a WHERE a.code='",subject,"'");
    	if (sub!=null) {
    		unit = sub.unit;
    		college = sub.college;
    	}
    	if (sec!=null && sub!=null) {
        	if (!sub.gradeLevel.equalsIgnoreCase(sec.gradeLevel)) {
        		PanelUtil.showError(null, "Section and Subject [",sub.code,"-",sec.gradeLevel," ",sec.code,"] is not same level, please check.");
        		return;
        	}
    	}
//    	check if valid schedule
//    	faculty, room, section
    	if (hasFacultyConflict()) {
    		PanelUtil.showError(null, "There was a conflict for faculty schedule.");
    		return;
    	}
    	if (hasRoomConflict()) {
    		PanelUtil.showError(null, "There was a conflict for room schedule.");
    		return;
    	}
//    	if (hasSectionConflict()) {
//    		PanelUtil.showError(null, "There was a conflict for section schedule.");
//    		return;
//    	}
		super.save();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				if (UserInfo.canModifyReference()) {
//		    		get all faculty task
					String sql1 = BeanUtil.concat("UPDATE FacultyGradingTask SET facultyId=",facultyId,", faculty='",faculty,"', subject='",subject,"' WHERE scheduleId=",seq," AND schoolYear='",AppConfig.getSchoolYear(),"'");
		    		DBClient.runSQLNative(sql1);
//		    		get all student subject detail grades
					String sql2 = BeanUtil.concat("UPDATE StudentSubjectDetailGrading SET facultyId=",facultyId,", facultyName='",faculty,"', subject='",subject,"' WHERE scheduleId=",seq," AND schoolYear='",AppConfig.getSchoolYear(),"'");
		    		DBClient.runSQLNative(sql2);
					String sql3 = BeanUtil.concat("UPDATE StudentSubject SET facultyId=",facultyId,", facultyName='",faculty,"', subject='",subject,"' WHERE scheduleId=",seq," AND schoolYear='",AppConfig.getSchoolYear(),"'");
		    		DBClient.runSQLNative(sql3);
		    		Log.out(sql1);
		    		Log.out(sql2);
		    		Log.out(sql3);
				}
			}
		});
		t.start();
	}

	public void setUnit(double unit) {
		this.unit = unit;
	}

	public double getUnit() {
        return unit;
    }
    public static void main(String[] args) {
        view(Schedule.class);
    }
    
//    @Override
//    public java.util.Vector allChart() {
//        java.util.Vector vec = new java.util.Vector();
//        vec.add(ChartBean.getGanttInstance(this, 
//                    "Room ",room," Schedule;M;T;W;TH;F", 
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.room='",room,"' AND a.schedDay LIKE 'M%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.room='",room,"' AND a.schedDay LIKE '%T%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.room='",room,"' AND a.schedDay LIKE '%W%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.room='",room,"' AND a.schedDay LIKE '%H%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.room='",room,"' AND a.schedDay LIKE '%F%'"
//                ));
//        vec.add(ChartBean.getGanttInstance(this, 
//                    "Faculty ",getFaculty()," Schedule;M;T;W;TH;F", 
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.facultyId=",facultyId," AND a.schedDay LIKE 'M%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.facultyId=",facultyId," AND a.schedDay LIKE '%T%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.facultyId=",facultyId," AND a.schedDay LIKE '%W%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.facultyId=",facultyId," AND a.schedDay LIKE '%H%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.facultyId=",facultyId," AND a.schedDay LIKE '%F%'"
//                ));
//        vec.add(ChartBean.getGanttInstance(this, 
//                    "Section ",blockOrSection," Schedule;M;T;W;TH;F", 
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.blockOrSection='",blockOrSection,"' AND a.schedDay LIKE 'M%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.blockOrSection='",blockOrSection,"' AND a.schedDay LIKE '%T%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.blockOrSection='",blockOrSection,"' AND a.schedDay LIKE '%W%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.blockOrSection='",blockOrSection,"' AND a.schedDay LIKE '%H%'",
//                    "SELECT a.subject, a.schedTime, a.schedTimeEnd FROM Schedule a WHERE a.blockOrSection='",blockOrSection,"' AND a.schedDay LIKE '%F%'"
//                ));
//        return vec;
 //   }    

//    public static LibraryBooks createLibraryBooksObj(String isbn, String title, String author) {
//        LibraryBooks stud = new LibraryBooks();
//        stud.isbn = isbn;
//        stud.title = title;
//        stud.author = author;
//        return stud;
//    }
//    
//    @Override
//    protected void runSetup() {
//        createLibraryBooksObj("0-7534-0502-4", "CAMELS HAVE HUMPS", "ANITA GANERI").save();
//    }    
   
    @Override
      public void runSetup() {
       Room hasRoom = (Room) DBClient.getFirstRecord("SELECT a FROM Room a");
        if (hasRoom==null || hasRoom.isEmptyKey()) {
            List<GradeLevel> lst = GradeLevel.extractCacheListBeans(GradeLevel.class);
            List<Room> lstRoo = new ArrayList<Room>();
            for (GradeLevel lvl : lst) {
                for (int i = 0; i < 10; i++) {
                    lstRoo.add(Room.createRoomObj(lvl.code+i,lvl.code+i,lvl.code+i));
                }
//           }
            DBClient.persistBean((List)lstRoo);
        }
       }
        EmployeeFaculty hasFaculty = (EmployeeFaculty) DBClient.getFirstRecord("SELECT a FROM Person a");
        if (hasFaculty==null || hasFaculty.isEmptyKey()) {
            List<EmployeeFaculty> emp = new ArrayList<EmployeeFaculty>();
                emp.add(EmployeeFaculty.createEmployeeFacultyObj(1,"Estabillo", "Niq", "HIGH SCHOOL DEPARTMENT", 40000));
                emp.add(EmployeeFaculty.createEmployeeFacultyObj(2,"Miranda", "Alex", "HIGH SCHOOL DEPARTMENT", 35000));
                emp.add(EmployeeFaculty.createEmployeeFacultyObj(3,"Estabillo", "Bong", "GRADE SCHOOL DEPARTMENT", 35000));
                emp.add(EmployeeFaculty.createEmployeeFacultyObj(4,"Baluyot", "Ebhoy", "GRADE SCHOOL DEPARTMENT", 35000));
   
                DBClient.persistBean((List)emp);
            }
         Section hasSection = (Section) DBClient.getFirstRecord("SELECT a FROM Section a");
          if (hasSection==null || hasSection.isEmptyKey()) {
            List<Section> sec = new ArrayList<Section>();
            List<GradeLevel> lst = GradeLevel.extractCacheListBeans(GradeLevel.class);
            for (GradeLevel lvl : lst) {
                for (int i = 0; i < 3; i++) {    
                sec.add(Section.createSectionObj(BeanUtil.concat("S",lvl.code),BeanUtil.concat(lvl.code),1));
             
               }
            DBClient.persistBean((List)sec);
            }
        
        }
        
        
       Schedule hasSchedule = (Schedule) DBClient.getFirstRecord("SELECT a FROM Schedule a");
        if (hasSchedule==null || hasSchedule.isEmptyKey()) {
            List<Subject> lst = DBClient.getList("SELECT a FROM Subject a");
            List<Schedule> sched = new ArrayList<Schedule>();
            for (Subject subj : lst) {
//                for (int i = 0; i < 10; i++) {
                sched.add(Schedule.createScheduleObj(1,"",BeanUtil.concat(subj.code),"",""));
                sched.add(Schedule.createScheduleObj(1,"",BeanUtil.concat(subj.code),"",""));
                sched.add(Schedule.createScheduleObj(1,"",BeanUtil.concat(subj.code),"",""));
                sched.add(Schedule.createScheduleObj(1,"",BeanUtil.concat(subj.code),"",""));
   
                DBClient.persistBean((List)sched);
            }
            }
        }

     private static Schedule createScheduleObj(Integer facultyId, String section, String subject, String day, String room) {
      Schedule sched = new Schedule();
        
        sched.facultyId = facultyId;
        sched.section = section;
        sched.subject = subject;
        sched.day1 = day;
        sched.room1 = room;
        return sched;
     }

	@Override
	public boolean cacheClient() {
		return false;
	}
    
    
	@Override
	public void setupIndex() {
		runIndex(1, "section");
		runIndex(2, "facultyId");
		runUniqueIndex(3, "section","subject");
		runIndex(4, "subject");
		
		List lst = DBClient.getList("SELECT a FROM Schedule a WHERE a.faculty IS NULL", 1, 15000);
		DBClient.persistBean(lst);
	}
	
	public boolean hasSectionConflict() {
		if (section!=null && !section.isEmpty()) {
			return noConflictSection(section);
		}
		return false;
	}
	
	public boolean hasRoomConflict() {
		if (room1!=null && !room1.isEmpty()) {
			if (!noConflictRoom(room1)) {
				return true;
			}
		}
		if (room2!=null && !room2.isEmpty()) {
			if (!noConflictRoom(room3)) {
				return true;
			}
		}
		if (room3!=null && !room3.isEmpty()) {
			if (!noConflictRoom(room3)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasFacultyConflict() {
		if (facultyId>0) {
			if (!noConflictEmployee(facultyId)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean noConflict(List<Schedule> lst) {
		for (int i=0; i<lst.size(); i++) {
			Schedule scTry = lst.get(i);
			TimeConflictChecker checker = new TimeConflictChecker(scTry);
			if (checker.hasConflict(lst)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean noConflictEmployee(EmployeeFaculty emp) {
		return noConflictEmployee(emp.personId);
	}
	
	public static boolean noConflictEmployee(int emp) {
		List<Schedule> lst = DBClient.getList(BeanUtil.concat("SELECT a FROM Schedule a WHERE a.facultyId=",emp));
		for (Schedule sc:lst) {
			TimeConflictChecker conflict = new TimeConflictChecker(sc);
			if (conflict.hasConflict(lst)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean noConflictRoom(Room room) {
		return noConflictRoom(room.code);
	}

	public static boolean noConflictRoom(String room) {
		List<Schedule> lst = DBClient.getList("SELECT a FROM Schedule a WHERE a.room1='",room,"' OR a.room2='",room,"' OR a.room3='",room,"'");
		for (Schedule sc:lst) {
			TimeConflictChecker conflict = new TimeConflictChecker(sc);
			if (conflict.hasConflict(lst)) {
				return false;
			}
		}
		return true;
	}

	public static boolean noConflictSection(Section sec) {
		return noConflictSection(sec.code);
	}
	
	public static boolean noConflictSection(String sec) {
		List<Schedule> lst = DBClient.getList("SELECT a FROM Schedule a WHERE a.section='",sec,"'");
		for (Schedule sc:lst) {
			TimeConflictChecker conflict = new TimeConflictChecker(sc);
			if (conflict.hasConflict(lst)) {
				return false;
			}
		}
		return true;
	}

	public static class TimeConflictChecker {
		Schedule sched;
		List<Integer> timeStartLst = new ArrayList();
		List<Integer> timeEndLst = new ArrayList();
		List<Integer> duplicateLst = new ArrayList();

		public TimeConflictChecker(Schedule sched) {
			this.sched = sched;
			List[] l = getAllScheduleTime(sched);
			timeStartLst = l[0];
			timeEndLst = l[1];
		}
				
		public List[] getAllScheduleTime(Schedule tsched) {
			List[] lst = {new ArrayList(), new ArrayList()};
			
			List<Integer> ttimeStartLst = new ArrayList();
			List<Integer> ttimeEndLst = new ArrayList();
			lst[0] = ttimeStartLst;
			lst[1] = ttimeEndLst;
			
			for (int i=1; i<=3; i++) {
				String room = (String) BeanUtil.getPropertyValue(tsched, BeanUtil.concat("room",i));
				String day = (String) BeanUtil.getPropertyValue(tsched, BeanUtil.concat("day",i));
				String start = (String) BeanUtil.getPropertyValue(tsched, BeanUtil.concat("schedTime",i));
				String end = (String) BeanUtil.getPropertyValue(tsched, BeanUtil.concat("schedTimeEnd",i));
				if (room==null || room.isEmpty()) {
					continue;
				}
				if (day==null || day.isEmpty()) {
					continue;
				}
				if (start==null || start.isEmpty()) {
					continue;
				}
				if (end==null || end.isEmpty()) {
					continue;
				}
				if (day.contains("M")) {
					int timeStart = 10000+DataUtil.getIntValue(start);
					int timeEnd = 10000+DataUtil.getIntValue(end);
					ttimeStartLst.add(timeStart);
					ttimeEndLst.add(timeEnd);
				}
				if (day.contains("T")) {
					int timeStart = 20000+DataUtil.getIntValue(start);
					int timeEnd = 20000+DataUtil.getIntValue(end);
					ttimeStartLst.add(timeStart);
					ttimeEndLst.add(timeEnd);
				}
				if (day.contains("W")) {
					int timeStart = 30000+DataUtil.getIntValue(start);
					int timeEnd = 30000+DataUtil.getIntValue(end);
					ttimeStartLst.add(timeStart);
					ttimeEndLst.add(timeEnd);
				}
				if (day.contains("H")) {
					int timeStart = 40000+DataUtil.getIntValue(start);
					int timeEnd = 40000+DataUtil.getIntValue(end);
					ttimeStartLst.add(timeStart);
					ttimeEndLst.add(timeEnd);
				}
				if (day.contains("F")) {
					int timeStart = 50000+DataUtil.getIntValue(start);
					int timeEnd = 50000+DataUtil.getIntValue(end);
					ttimeStartLst.add(timeStart);
					ttimeEndLst.add(timeEnd);
				}
				if (day.contains("S")) {
					int timeStart = 60000+DataUtil.getIntValue(start);
					int timeEnd = 60000+DataUtil.getIntValue(end);
					ttimeStartLst.add(timeStart);
					ttimeEndLst.add(timeEnd);
				}
			}
			return lst;
		}
		
		public boolean hasConflict(List<Schedule> lst) {
//			check duplicate first
			duplicateLst.clear();
			for (Schedule sc:lst) {
				if (duplicateLst.contains(sc.seq)) {
					return true;
				}
				duplicateLst.add(sc.seq);
			}
			
			for (Schedule sc:lst) {
				if (sched.seq==sc.seq) continue; //same schedule, no need to check
				List[] l = getAllScheduleTime(sc);
				List<Integer> tmpstart = l[0];
				List<Integer> tmpend = l[1];
				
				for (int i=0; i<timeStartLst.size(); i++) {
					int start = timeStartLst.get(i);
					int end = timeEndLst.get(i);
					for (int j=0; j<tmpstart.size(); j++) {
						int istart = tmpstart.get(j);
						int iend = tmpend.get(j);
						
						if (DataUtil.isIntersecting(start, end, istart, iend)) {
							PanelUtil.showError(null, "Conflict with \n",sched.displayString()," and \n",sc.displayString());
							return true;
						}
					}
				}
			}
			return false;
		}
	}
	
	public String displayString() {
		return BeanUtil.concat(subject," ",section," ",room1," ",schedTime1,"-",schedTimeEnd1," ",faculty);
	}
}