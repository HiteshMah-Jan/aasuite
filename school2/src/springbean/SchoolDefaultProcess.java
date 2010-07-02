/*
 * SchoolDefaultProcess.java
 *
 * Created on Jan 19, 2008, 3:12:34 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package springbean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import service.IService;
import service.ParamStruct;
import service.ReturnStruct;
import service.util.AbstractIBean;
import service.util.CallService;
import util.BeanUtil;
import util.DBClient;
import util.DateUtil;
import util.PanelUtil;
import util.ThreadPoolUtil;
import bean.EmployeeFaculty;
import bean.Enrollment;
import bean.Schedule;
import bean.Student;
import bean.accounting.GLPostingScript;
import bean.accounting.Invoice;
import bean.accounting.Payment;
import bean.accounting.PaymentEnrollment;
import bean.accounting.PaymentLineItem;
import bean.accounting.PaymentPlan;
import bean.admin.AclUser;
import bean.admin.AppConfig;
import bean.person.StudentSchoolAttended;
import bean.person.StudentSubject;
import bean.person.StudentSummerSchoolAttended;
import bean.person.StudentValuesGrading;
import bean.reference.CourseSubject;
import bean.reference.GradeLevel;
import bean.reference.ScholarshipTable;
import bean.reference.Section;

import component.IAuthorization;

/**
 *
 * @author Entokwaa
 */
public class SchoolDefaultProcess extends ProcessImpl implements IService {
    static AppConfig appCache = new AppConfig();
    public static final int MAX_GRADE_RECALCULATE = 10000; //so that there will be less change in the code

    public void doProcess(String method, Object... param) {
        Vector vec = new Vector();
        vec.add(method);
        vec.add(param);
        CallService.callService(vec, 1, "springbean.SchoolDefaultProcess");
    }    

    public ReturnStruct callService(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        Vector vec = (Vector) param.getData();
        String method = (String) vec.get(0);
        Object[] obj = (Object[]) vec.get(1);
        Method[] met = this.getClass().getMethods();
        for (Method m : met) {
            if (method.equals(m.getName())) {
                try {
                    Object o = m.invoke(this, obj);
                    ret.setData(o);
                    ret.setStatus(constants.Constants.SUCCESS);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(SchoolDefaultProcess.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(SchoolDefaultProcess.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(SchoolDefaultProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ret;
    }
    
    public static int countMonths = 10;

    public void createAllSubjects(Student student) {
    	ThreadPoolUtil.execute(new CreateCurriculum(student));
    }

    public void updateSchedules(Enrollment enroll) {
        Integer enrollmentId = enroll.seq;
        //check if there are
        if (enrollmentId == null || enrollmentId == 0) {
            return;
        }
        List<StudentSubject> lst = AbstractIBean.list("SELECT a FROM StudentSubject a WHERE a.gradeLevel='",enroll.gradeLevel,"%' AND a.studentId=",enroll.studentId);
        if (lst!=null) {
            List<Schedule> scheds = appCache.selectListCache("SELECT a FROM Schedule a WHERE a.section='",enroll.getSection(),"'");
            for (StudentSubject sub : lst) {
            	sub.enrollmentId = enroll.seq;
            	sub.section = enroll.section;
            	sub.schoolYear = enroll.schoolYear;

            	Schedule sched = extractScheduleId(scheds, sub);
            	if (sched==null) continue;
            	sub.scheduleId = sched.seq;
            	sub.facultyId = sched.facultyId;
            	sub.faculty = sched.faculty;
            }
            DBClient.persistBean((List) lst);
        }
    }

    public Schedule extractScheduleId(List<Schedule> schs, StudentSubject sub) {
    	for (Schedule s:schs) {
    		if (s.subject.equals(sub.subject)) {
    			return s;
    		}
    	}
    	return null;
    }
    
    public void updateFWB(Payment pay) {
    	List<PaymentEnrollment> allPayments = DBClient.getList(BeanUtil.concat("SELECT a FROM PaymentEnrollment a WHERE a.paidBy=",pay.paidBy," AND a.schoolYear='",pay.schoolYear,"'"));
        double totalBack = 0;
        String useYear = pay.schoolYear;
        for (PaymentEnrollment p:allPayments) {
            totalBack += p.balance;
        }
        PaymentEnrollment p = (PaymentEnrollment) DBClient.getFirstRecord("SELECT a FROM PaymentEnrollment a WHERE a.paidBy=",pay.paidBy," AND a.description LIKE '%",useYear,"%'");
		updateFWB(p, totalBack);
    }

    public void updateFWB(PaymentEnrollment payment, double amount) {
//        payment.amount = payment.overallAmountDue = payment.balance = payment.overallBalance = amount;
//        payment.save();
    }

    public void createOrUpdateFWB(PaymentEnrollment payment, Enrollment enroll, double amount, String useYear, String fwb) {
    	payment.line = 100000;
        payment.paidTo = enroll.studentId;
        payment.form = "ENROLLMENT";
        payment.paymentTerms = enroll.paymentMode;
        payment.amountPaid = 0;
        payment.accountNumber = enroll.accountNumber;
        payment.amount = payment.overallAmountDue = payment.balance = payment.overallBalance = amount;

        payment.paid = false;
        payment.recordId=enroll.seq;
        payment.paidBy=enroll.studentId;
        payment.description=BeanUtil.concat("SY: ",useYear," FORWARDED BALANCE");
        payment.orType = "N";
        payment.paymentFor=fwb;
        payment.save();
    }
    
    public void createPayment(Enrollment enroll) {
        if (enroll == null) {
            return;
        }
        Student stud = enroll.extractStudentObj();
        PaymentPlan cal = extractPaymentPlan(enroll);
        enroll.miscellaneousFee = cal.miscFee;
        List lstBack = stud.extractBackAccount(enroll.schoolYear);
        double backAccount = PanelUtil.getDoubleValue(lstBack.get(0).toString());
        if (backAccount > 0) {
            String useYear = lstBack.get(2).toString();
            PaymentEnrollment payment = new PaymentEnrollment();
            payment.schoolYear = enroll.schoolYear;
            payment.dueDate = payment.paymentDate = cal.date1;
            createOrUpdateFWB(payment, enroll, backAccount, useYear, "FWB");
            addPaymentLineItem(payment, "FWB", BeanUtil.concat("SY: ",useYear," FORWARDED BALANCE"), backAccount);
        }
        if (enroll.miscellaneousFee > 0) {
            PaymentEnrollment payment = new PaymentEnrollment();
            payment.schoolYear = enroll.schoolYear;
            payment.line = 200000;
            payment.paidBy = enroll.studentId;
            payment.form = "ENROLLMENT";
            payment.paymentTerms = enroll.paymentMode;
            payment.accountNumber = enroll.accountNumber;
            payment.overallBalance = payment.amount = payment.overallAmountDue = payment.balance = enroll.miscellaneousFee;

            payment.paid=false;
            payment.dueDate=cal.date1;
            payment.paymentDate=cal.date1;
            payment.recordId=enroll.seq;
            payment.paidBy=enroll.studentId;
            payment.paymentFor=BeanUtil.concat(enroll.gradeLevel,"-MISC");
            payment.description="MISC";
            payment.orType = "A";
            payment.plan = enroll.paymentPlanType;
            payment.save();
            addMisc(enroll, payment);
        }
        for (int i = 0; i < cal.paymentCount; i++) {
            double amount = cal.extractAmount(i+1);
            Date datePay = cal.extractDate(i+1);
            PaymentEnrollment payment = new PaymentEnrollment();
            payment.schoolYear = enroll.schoolYear;
            payment.line = 300000+i;
            payment.form = "ENROLLMENT";
            payment.paymentTerms = enroll.paymentMode;
            payment.overallAmountDue = payment.overallBalance = payment.balance = payment.amount = amount;
            payment.accountNumber = enroll.accountNumber;
            payment.orType = "N";
            payment.paid=false;
            payment.dueDate=datePay;
            payment.paymentDate=datePay;
            payment.recordId=enroll.seq;
            payment.paidBy=enroll.studentId;
            payment.description="TUITION";
            payment.plan = enroll.paymentPlanType;
            if ("A".equals(cal.code)) {
                payment.paymentFor=BeanUtil.concat(enroll.gradeLevel,"-TFEE");
            }
            else {
                payment.setPaymentFor(BeanUtil.concat(enroll.gradeLevel,"-",cal.code + (i+1)));
            }
            payment.save();
            addPaymentLineItem(payment, "TFEE", "TUITION FEE", payment.balance);
        }
    }

    public void createPayment(Enrollment enroll, double miscFee, double totalFee) {
        if (enroll == null) {
            return;
        }
        Student stud = enroll.extractStudentObj();
        PaymentPlan cal = extractPaymentPlan(enroll);
        List lstBack = stud.extractBackAccount(enroll.schoolYear);
        double backAccount = PanelUtil.getDoubleValue(lstBack.get(0).toString());
        if (backAccount > 0) {
            String useYear = lstBack.get(2).toString();
            PaymentEnrollment payment = new PaymentEnrollment();
            payment.schoolYear = enroll.schoolYear;
            payment.dueDate = payment.paymentDate = cal.date1;
            createOrUpdateFWB(payment, enroll, backAccount, useYear, "FWB");
            addPaymentLineItem(payment, "FWB", BeanUtil.concat("SY: ",useYear," FORWARDED BALANCE"), backAccount);
        }
        if (miscFee > 0) {
            PaymentEnrollment payment = new PaymentEnrollment();
            payment.schoolYear = enroll.schoolYear;
            payment.line = 200000;
            payment.paidBy = enroll.studentId;
            payment.form = "ENROLLMENT";
            payment.paymentTerms = enroll.paymentMode;
            payment.accountNumber = enroll.accountNumber;
            payment.overallBalance = payment.amount = payment.overallAmountDue = payment.balance = miscFee;

            payment.paid=false;
            payment.dueDate=cal.date1;
            payment.paymentDate=cal.date1;
            payment.recordId=enroll.seq;
            payment.paidBy=enroll.studentId;
            payment.paymentFor=BeanUtil.concat(enroll.gradeLevel,"-MISC");
            payment.description="MISC";
            payment.orType = "A";
            payment.plan = enroll.paymentPlanType;
            payment.save();
            addMisc(enroll, payment);
        }
        for (int i = 0; i < cal.paymentCount; i++) {
            double amount = cal.extractAmount(i+1);
            if (amount>totalFee) {
            	amount = totalFee;
            }
            if (totalFee==0) continue;
            
            totalFee -= amount;
            Date datePay = cal.extractDate(i+1);
            PaymentEnrollment payment = new PaymentEnrollment();
            payment.schoolYear = enroll.schoolYear;
            payment.line = 300000+i;
            payment.form = "ENROLLMENT";
            payment.paymentTerms = enroll.paymentMode;
            payment.overallAmountDue = payment.overallBalance = payment.balance = payment.amount = amount;
            payment.accountNumber = enroll.accountNumber;
            payment.orType = "N";
            payment.paid=false;
            payment.dueDate=datePay;
            payment.paymentDate=datePay;
            payment.recordId=enroll.seq;
            payment.paidBy=enroll.studentId;
            payment.description="TUITION";
            payment.plan = enroll.paymentPlanType;
            if ("A".equals(cal.code)) {
                payment.paymentFor=BeanUtil.concat(enroll.gradeLevel,"-TFEE");
            }
            else {
                payment.setPaymentFor(BeanUtil.concat(enroll.gradeLevel,"-",cal.code + (i+1)));
            }
            payment.save();
            addPaymentLineItem(payment, "TFEE", "TUITION FEE", payment.balance);
        }
    }
    
    public void addMisc(Enrollment e, Payment p) {
        addPaymentLineItem(p, "ANN", "ANNUAL FEE", e.annualFee);
        addPaymentLineItem(p, "ATH", "ATHLETIC FEE", e.athleticFee);
        addPaymentLineItem(p, "AUD", "AUDIO VISUAL FEE", e.audioVisualFee);
        addPaymentLineItem(p, "COM", "COMPUTER FEE", e.computerFee);
        addPaymentLineItem(p, "DIP", "DIPLOMA FEE", e.diplomaFee);
        addPaymentLineItem(p, "HNB", "HANDBOOK FEE", e.handbookFee);
        addPaymentLineItem(p, "IDF", "ID FEE", e.idFee);
        addPaymentLineItem(p, "INS", "INSURANCe FEE", e.insuranceFee);
        addPaymentLineItem(p, "LAB", "LABORATORY FEE", e.laboratoryFee);
        addPaymentLineItem(p, "LIB", "LIBRARY FEE", e.libraryFee);
        addPaymentLineItem(p, "MAT", "MATERIALS FEE", e.materialsFee);
        addPaymentLineItem(p, "MED", "MEDICAL DENTAL FEE", e.medicalDentalFee);
        addPaymentLineItem(p, "REG", "REGISTRATION FEE", e.registrationFee);
    }

    public void addPaymentLineItem(Payment p, String code, String desc, double amount) {
        if (amount <= 0) {
            return;
        }
        PaymentLineItem item = new PaymentLineItem();
        item.code = code;
        item.description = desc;
        item.charges = amount;
        item.paymentId = p.seq;
        item.save();
    }

    public void addModules(Student student) {
        AclUser user = new AclUser();
        user.userid = student.getStudentNumber();
        user.password = student.getStudentNumber();
        user.firstname = student.getFirstName();
        user.lastname = student.getLastName();
        user.save();
        IAuthorization.AuthorizationImpl.getInstance(user).addGroup("STU");
    }

    public void addModules(EmployeeFaculty faculty) {
        if (faculty.getUserid() != null && !faculty.getUserid().isEmpty()) {
            AclUser user = new AclUser();
            user.userid = faculty.getLastName();
            user.password = "PASSWORD";
            user.firstname = faculty.getFirstName();
            user.lastname = faculty.getLastName();
            user.save();
            IAuthorization.AuthorizationImpl.getInstance(user).addGroup("FAC");
        }
    }

    public void createSchoolAttended(Student student) {
        List lst = DBClient.getList("SELECT a FROM StudentSchoolAttended a WHERE a.studentId=" + student.personId);
        if (lst == null || lst.size() == 0) {
            //create 12 records
            for (int i = 1; i <= 12; i++) {
                createSchoolAttended(student, i);
            }
        }
    }

    public void createSchoolAttended(Student student, int level) {
        StudentSchoolAttended att = new StudentSchoolAttended();
        att.address = AppConfig.getCompanyAddress();
        att.curriculumYear = student.schoolYear;
        att.school = AppConfig.getCompanyName();
        att.schoolYear = student.schoolYear;
        att.studentId = student.personId;
        att.yearCount = 1 + "";
        att.yearLevel = level + "";
        att.save();
    }

    public void createSummerSchoolAttended(Student student) {
        List lst = DBClient.getList("SELECT a FROM StudentSummerSchoolAttended a WHERE a.studentId=" + student.personId);
        if (lst == null || lst.size() == 0) {
            //create 12 records
            for (int i = 1; i <= 12; i++) {
                createSummerSchoolAttended(student, i);
            }
        }
    }

    private void createSummerSchoolAttended(Student student, int i) {
        StudentSummerSchoolAttended att = new StudentSummerSchoolAttended();
        att.address = AppConfig.getCompanyAddress();
        att.curriculumYear = student.schoolYear;
        att.school = AppConfig.getCompanyName();
        att.schoolYear = student.schoolYear;
        att.studentId = student.personId;
        att.yearCount = 1 + "";
        att.yearLevel = i + "";
        att.month1Days = 0;
        att.month1Present = 0;
        att.month2Days = 0;
        att.month2Present = 0;
        att.month3Days = 0;
        att.month3Present = 0;
        att.save();
    }

    public void changeGradeLevel(Student stud) {
        if (stud.toSection != null && !stud.toSection.trim().isEmpty()) {
            Section sec = (Section) AbstractIBean.extractObject(Section.class.getSimpleName(), stud.toSection);
            if (sec != null) {
                stud.gradeLevel = sec.gradeLevel;
            }
        } else if (stud.section != null && !stud.section.trim().isEmpty()) {
            Section sec = (Section) AbstractIBean.extractObject(Section.class.getSimpleName(), stud.section);
            if (sec != null) {
                stud.gradeLevel = sec.gradeLevel;
            }
        }
    }

    public boolean isEnrolled(Student stud, String schoolYear) {
        String sql = "SELECT a FROM Enrollment a WHERE a.studentId=" + stud.personId + " AND a.schoolYear='" + schoolYear + "'";
        boolean b = DBClient.getFirstRecord(sql) != null;
        return b;
    }

    public GradeLevel getGradeLevel(Student stud) {
        GradeLevel lvl = null;
        if (stud.gradeLevel!=null) {
            lvl = (GradeLevel) AbstractIBean.extractObject(GradeLevel.class.getSimpleName(), stud.gradeLevel);
        }
        else {
            lvl = (GradeLevel) DBClient.getFirstRecord("SELECT a FROM GradeLevel a, Enrollment b WHERE a.code=b.gradeLevel AND b.studentId=" + stud.personId + " ORDER BY b.schoolYear DESC");
        }
        return lvl;
    }

    public Enrollment getStudentEnrollment(Student stud) {
        return (Enrollment) DBClient.getFirstRecord("SELECT a FROM Enrollment a WHERE a.studentId=" + stud.personId + " AND a.schoolYear='" + AppConfig.getSchoolYear() + "'");
    }
     
    public boolean hasPayment(Enrollment enroll) {
        if (enroll != null) {
            PaymentEnrollment p = (PaymentEnrollment) DBClient.getFirstRecord("SELECT a FROM PaymentEnrollment a WHERE a.schoolYear=" + enroll.schoolYear," AND a.paidBy=",enroll.studentId," ORDER BY a.paidBy");
            return !(p==null || p.isEmptyKey());
        }
        return false;
    }
    
    public void setupMisc(Enrollment e, PaymentPlan cal) {
    	e.tuitionFee = cal.totalAmount;
        e.annualFee = cal.annualFee;
        e.athleticFee = cal.athleticFee;
        e.audioVisualFee = cal.audioVisualFee;
        e.computerFee = cal.computerFee;
        e.diplomaFee = cal.diplomaFee;
        e.handbookFee = cal.handbookFee;
        e.idFee = cal.idFee;
        e.insuranceFee = cal.insuranceFee;
        e.laboratoryFee = cal.laboratoryFee;
        e.libraryFee = cal.libraryFee;
        e.materialsFee = cal.materialsFee;
        e.medicalDentalFee = cal.medicalDentalFee;
        e.registrationFee = cal.registrationFee;
        e.miscellaneousFee = cal.miscFee;
    }
    
    public double extractBackAccount(Student stud) {
        double totalBalance = 0;
        Enrollment e = (Enrollment) AbstractIBean.firstRecord("SELECT a FROM Enrollment a WHERE a.studentId=" + stud.personId + " ORDER BY a.seq DESC");
        if (e != null) {
            List<PaymentEnrollment> lst = AbstractIBean.list("SELECT a FROM PaymentEnrollment a WHERE a.recordId=" + e.seq);
            for (PaymentEnrollment pay : lst) {
                totalBalance += pay.overallBalance;
            }
        }
        if (totalBalance < 0) {
            return 0;
        }
        return totalBalance;
    }

    public GradeLevel extractGradeLevelObj(Enrollment enroll) {
        GradeLevel level = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), enroll.gradeLevel);
        if (level != null) {
            return level;
        }
        Student stud = enroll.extractStudentObj();
        if (stud == null) {
            return null;
        }
        level = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), stud.gradeLevel);
        if (level != null) {
            enroll.gradeLevel = level.code;
            enroll.save();
        }
        return level;
    }

    public GradeLevel extractGradeLevelSummerObj(Enrollment enroll) {
        GradeLevel level = extractGradeLevelObj(enroll);
        GradeLevel lvl = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), level.code + "_SUMMER'");
        if (lvl == null) {
            lvl = level;
            lvl.code = level.code + "_SUMMER";
            lvl.tuitionFee = lvl.tuitionFee * .20;
            lvl.save();
        }
        return lvl;
    }

    public Enrollment extractOldEnrollment(Enrollment enroll) {
        if (enroll.isEmptyKey()) {
            String sql = util.BeanUtil.concat("SELECT a FROM Enrollment a WHERE a.studentId=", enroll.studentId, " AND a.schoolYear='", enroll.schoolYear, "'");
            Enrollment e = (Enrollment) AbstractIBean.firstRecord(sql);
            return e;
        }
        return null;
    }

    public void setupPaymentPlan() {
        PaymentPlan a = (PaymentPlan) DBClient.getFirstRecord("SELECT a FROM PaymentPlan a");
        if (a != null && !a.isEmptyKey()) {
            return;
        }
        List<GradeLevel> lvls = GradeLevel.extractCacheListBeans(GradeLevel.class);
        for (GradeLevel gl : lvls) {
            PaymentPlan.createPlan("A", "ONE TIME PAYMENT", 1, SchoolConfig.getSchoolYear(), gl.code, gl.tuitionFee+gl.otherFee, 1500, gl.miscFee).save();
            PaymentPlan.createPlan("S", "SEMI ANNUAL", 2, SchoolConfig.getSchoolYear(), gl.code, gl.tuitionFee+gl.otherFee+1000, 1500, gl.miscFee).save();
            PaymentPlan.createPlan("Q", "QUARTER", 4, SchoolConfig.getSchoolYear(), gl.code, gl.tuitionFee+gl.otherFee+2000, 1500, gl.miscFee).save();
            PaymentPlan.createPlan("M", "MONTHLY", 10, SchoolConfig.getSchoolYear(), gl.code, gl.tuitionFee+gl.otherFee+3000, 1500, gl.miscFee).save();
        }
    }

    public PaymentPlan extractPaymentPlan(Enrollment enroll) {
        return (PaymentPlan) enroll.selectFirstCache("SELECT a FROM PaymentPlan a WHERE a.code='" + enroll.paymentPlanType + "' AND a.gradeLevel='",enroll.gradeLevel,"'");
    }

    public double getDuePayments(Enrollment enroll) {
        Date de = constants.Constants.useDate;
        List<Payment> lst = AbstractIBean.list("SELECT a FROM PaymentEnrollment a WHERE a.recordId=" + enroll.seq + " AND a.dueDate<='" + DateUtil.formatDateToSql(de) + "'");
        if (lst == null) {
            return 0;
        }
        double d = 0;
        for (Payment p : lst) {
                d += p.overallBalance;
        }
        return d;
    }

    static List lst;
    public static class CreateCurriculum implements Runnable {
    	Student student;
    	List<Enrollment> enrolls;
    	
    	public CreateCurriculum(Student stud) {
    		this.student = stud;
    	}

    	@SuppressWarnings("unchecked")
		protected void createEnrollment() {
//    		create for preschool, gradeschool, high school
    		List l = new ArrayList();
    		l.add(newEnrollment("P1"));
    		l.add(newEnrollment("P2"));
    		l.add(newEnrollment("K1"));
    		l.add(newEnrollment("K2"));
    		l.add(newEnrollment("N1"));
    		l.add(newEnrollment("N2"));
    		l.add(newEnrollment("G1"));
    		l.add(newEnrollment("G2"));
    		l.add(newEnrollment("G3"));
    		l.add(newEnrollment("G4"));
    		l.add(newEnrollment("G5"));
    		l.add(newEnrollment("G6"));
    		l.add(newEnrollment("H1"));
    		l.add(newEnrollment("H2"));
    		l.add(newEnrollment("H3"));
    		l.add(newEnrollment("H4"));

    		l.add(newValues("P1"));
    		l.add(newValues("P2"));
    		l.add(newValues("K1"));
    		l.add(newValues("K2"));
    		l.add(newValues("N1"));
    		l.add(newValues("N2"));
    		l.add(newValues("G1"));
    		l.add(newValues("G2"));
    		l.add(newValues("G3"));
    		l.add(newValues("G4"));
    		l.add(newValues("G5"));
    		l.add(newValues("G6"));
    		l.add(newValues("H1"));
    		l.add(newValues("H2"));
    		l.add(newValues("H3"));
    		l.add(newValues("H4"));
    		DBClient.persistBean(l);
    	}

    	protected Enrollment newEnrollment(String level) {
    		if (enrolls==null) {
    			enrolls = DBClient.getList(BeanUtil.concat("SELECT a FROM Enrollment a WHERE a.studentId=",student.personId));
    		}
    		if (enrolls != null) {
    			for (Enrollment e : enrolls) {
    				if (e.gradeLevel!=null && e.gradeLevel.equals(level)) {
    					return e;
    				}
    			}
    		}
    		Enrollment e = new Enrollment();
    		e.gradeLevel = level;
    		e.studentId = student.personId;
    		return e;
    	}
    	
    	protected StudentValuesGrading newValues(String level) {
    		StudentValuesGrading val = new StudentValuesGrading();
    		val.gradeLevel = level;
    		val.studentId = student.personId;
    		val.schoolYear = AppConfig.getSchoolYear();
    		return val;
    	}
    	
    	protected void createSubjects() {
	        lst = appCache.selectListCache("SELECT a FROM CourseSubject a WHERE a.course='" + student.course + "'");
	        List lstSubjects = new ArrayList();
	        if (lst==null || lst.isEmpty()) {
//	        	create curriculum using subjects created
	        	String sql1 = "update subject set course=(select distinct course from gradelevel where code=subject.gradelevel) where course is null or course=''";
	        	String sql2 = "insert into coursesubject(subject, course, weight) (select distinct code, course, unit from subject where course is not null or course!='')";
	        	DBClient.runBatchNative(sql1, sql2);
		        lst = appCache.selectListCache("SELECT a FROM CourseSubject a WHERE a.course='" + student.course + "'");
	        }
	        for (Object obj : lst) {
	            CourseSubject csubject = (CourseSubject) obj;
	            
	            if (!PanelUtil.isEmpty(student.getSchoolYear()) && !PanelUtil.isEmpty(csubject.getExceptCurriculumYears()) && csubject.getExceptCurriculumYears().indexOf(student.getSchoolYear()) != -1) {
	                //do not include the subject to this curriculum
	                continue;
	            }
//	            check if student already has the subject
	            StudentSubject subject = new StudentSubject();
	            subject.course=(student.course);
	            subject.preSubject1=(csubject.preSubject1);
	            subject.preSubject2=(csubject.preSubject2);
	            subject.preSubject3=(csubject.preSubject3);
	            subject.preSubject4=(csubject.preSubject4);
	            subject.preSubject5=(csubject.preSubject5);
	            subject.preferredSemester=(csubject.preferredSemester);
	            subject.gradeLevel = csubject.preferredYear;
	            subject.studentId=(student.personId);
	            subject.subject=(csubject.subject);
	            subject.unit = csubject.weight;
	            subject.studentName = student.toString();
	            lstSubjects.add(subject);
	        }
	        student.officiallyRegistered = true;
	        lstSubjects.add(student);
	        DBClient.persistBean(lstSubjects);
    	}
    	
    	@Override
		public void run() {
	        String course = student.course;
	        int personId = student.personId;
	        if (PanelUtil.isEmpty(course)) {
	        	PanelUtil.showError(null, "Student does not have a course.");
	            return;
	        }
	        createSubjects();
	        createEnrollment();
		}
    }
}
