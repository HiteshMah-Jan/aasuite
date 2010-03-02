/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbean;

import bean.Employee;
import bean.accounting.payroll.AlphaList;
import bean.accounting.payroll.AlphaListEmployee;
import bean.accounting.payroll.AlphaListRemittanceSummary;
import bean.accounting.payroll.EmployeePayroll;
import bean.accounting.payroll.EmployeePayrollAdjustment;
import bean.accounting.payroll.Payroll;
import bean.accounting.payroll.PayrollAdjustmentRef;
import bean.accounting.payroll.PersonAttendance;
import bean.reference.EventHoliday;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.ParamStruct;
import service.ReturnStruct;
import util.DBClient;
import util.DataUtil;
import util.DateUtil;

/**
 *
 * @author asmiranda
 */
public class PayrollService extends AttendanceService {
    public final static int STEP1 = 101;
    public final static int STEP2 = 102;
    public final static int STEP3 = 103;
    public final static int STEP3_1 = 1031;
    public final static int STEP4 = 104;

//    step 1. Create attendance for a month. Must call from Server
    public void createAttendace() {
        callMe(STEP1, "");
    }

    private void createAttendance(int employeeId) {
//        create 2 months of attendance
        List<PersonAttendance> lst = DBClient.getList("SELECT a FROM PersonAttendance a ORDER BY a.attendanceDate DESC", 0, 35);
        Date first = DateUtil.getFirstDayOfMonth();
        for (int i=-15; i<45; i++) {
            Date d = DateUtil.addDay(first, i);
            if (!attendanceExist(lst, d)) {
//                create attendance
                PersonAttendance newatt = new PersonAttendance();
                newatt.attendanceDate = d;
                newatt.personId = employeeId;
                newatt.attendanceType = "OTHERS";
                newatt.save();
            }
        }
    }

    private final boolean attendanceExist(List<PersonAttendance> lst, Date d) {
        String sd = DateUtil.formatDate(d);
        for (PersonAttendance att : lst) {
            if (sd.equals(DateUtil.formatDate(att.attendanceDate))) {
                return true;
            }
        }
        return false;
    }

//    step 2. Calculate each attendance.
    public static void calculateAttendance(List<PersonAttendance> att) {
        for (PersonAttendance p:att) {
            calculateAttendance(p);
        }
    }

    public static void calculateAttendance(PersonAttendance att) {
        double total = DateUtil.getTotalHours(att.login, att.logout);	//there will always be breaktime if time is greater than 5 hours
        if (total > 5) {
            total--;
        }
        double ot = total - 8 > 0 ? total - 8 : 0;
        double ut = total - 8 < 0 ? (total - 8) * -1 : 0;
        double nt = DateUtil.getNightHours(att.logout) > 0 ? DateUtil.getNightHours(att.logout) : 0;

        att.totalHours = total;
        att.otHours = ot;
        att.underTime = ut;
        att.nightHours = nt;
    }

    public static void createPayroll() {
        List lst = new ArrayList();
        int year = DateUtil.getYear();
        System.out.println("YEAR = "+year);
        for (int i=1; i<=12; i++) {
            lst.add(newPayroll(year, i, Payroll.FIRST_HALF));
            lst.add(newPayroll(year, i, Payroll.SECOND_HALF));
        }
        DBClient.persistBean(lst);
    }

    private static Payroll newPayroll(int year, int month, int half) {
        Payroll p = new Payroll();
        if (half==Payroll.FIRST_HALF) {
            p.startDate = DateUtil.readDate(year+"-"+month+"-01", "yyyy-MM-dd");
            p.endDate = DateUtil.readDate(year+"-"+month+"-15", "yyyy-MM-dd");
            p.payrollName = DateUtil.formatDate(p.endDate, "MM-yyyy ") + "01-15 " + DateUtil.formatDate(p.endDate, "(MMM)");
        }
        else {
            p.startDate = DateUtil.readDate(year+"-"+month+"-16", "yyyy-MM-dd");
            p.endDate = DateUtil.getEndOfMonth(p.startDate);
            p.payrollName = DateUtil.formatDate(p.endDate, "MM-yyyy ") + "16-" + DateUtil.formatDate(p.endDate, "d") + DateUtil.formatDate(p.endDate, " (MMM)");
        }
        return p;
    }

//    step 3. Create payroll for all employee. Must call from server.
    public void createEmployeePayroll(Payroll pay) {
        callMe(STEP3, pay);
    }

    private void createEmployeePayroll(Payroll pay, int employeeId) {
        Employee e = (Employee) pay.extractPerson(employeeId);
        if (e.basicPay <= 0 && e.perHourPay <= 0) {
        	System.out.println("EMPLOYEE HAS NO BASIC PAY OR PER HOUR PAY ["+e.lastName+", "+e.firstName+"]");
        	return;
        }

        EmployeePayroll p = new EmployeePayroll();
        p.payrollId = pay.seq;
        p.employeeId = employeeId;
        p.startDate = pay.startDate;
        p.endDate = pay.endDate;
        p.employeeName = e.lastName+", "+e.firstName+" "+e.middleInitial;

//        calculate total hours
        List<PersonAttendance> lst = DBClient.getList("SELECT a FROM PersonAttendance a WHERE a.personId="+employeeId+" AND a.attendanceDate BETWEEN '"+DateUtil.formatDateToSql(pay.startDate)+"' AND '"+DateUtil.formatDateToSql(pay.endDate)+"'");
        for (PersonAttendance pa : lst) {
            p.totalHours += pa.totalHours;
            p.totalNDHours += pa.nightHours;
            p.totalOTHours += pa.approvedOTHours;
            p.totalUTHours += pa.underTime;

            if (pa.totalHours==0 && p.totalUTHours==0 && "OTHERS".equals(pa.attendanceType)) {
                p.totalAbsentHours += 8;
            }
        }
        double overallBasicHours = p.totalHours + p.totalAbsentHours + p.totalUTHours;
        if (overallBasicHours <= 0) {
        	System.out.println("EMPLOYEE HAS NO BILLABLE HOURS ["+e.lastName+", "+e.firstName+"]");
        	return;
        }
        if (e.basicPay > 0) {
            p.basicPay = DataUtil.getMoneyFormat(e.basicPay/2);
            p.perHourSalary = p.basicPay / overallBasicHours;
        	System.out.println("1 ["+e.lastName+", "+e.firstName+"] "+p.basicPay+"-"+p.perHourSalary);
        }
        else {
            p.basicPay = DataUtil.getMoneyFormat(e.perHourPay * overallBasicHours);
            p.perHourSalary = e.perHourPay;
        	System.out.println("2 ["+e.lastName+", "+e.firstName+"] "+p.basicPay+"-"+p.perHourSalary);
        }
        p.save();

//        create all adjustment config
        List<PayrollAdjustmentRef> adjustments = PayrollAdjustmentRef.extractAll(e.position, e.taxTable);
        for (PayrollAdjustmentRef a:adjustments) {
            EmployeePayrollAdjustment ad = new EmployeePayrollAdjustment();
            ad.sortNum = a.sortNum;
            ad.payrollAdjustmentRefId = a.seq;
            ad.amount = PayrollAdjustmentRef.extractCalculateValue(a, p.basicPay);
            ad.adjustmentName = a.adjustmentName;
            ad.employeeId = p.employeeId;
            ad.employeePayrollId = p.seq;
            ad.deduct = a.deduct;
            ad.taxable = a.taxable;
            ad.save();
        }
        calculatePayroll(p);
        p.save();
    }

//    step 3_1. Recalculate payroll. Must call from server.
    public void recalculatePayroll(EmployeePayroll p) {
        callMe(STEP3_1, p);
    }

    private void calculatePayroll(EmployeePayroll p) {
        p.totalGrossPay = p.totalNetAmount = p.basicPay;

        List<PersonAttendance> lst = DBClient.getList("SELECT a FROM PersonAttendance a WHERE a.personId="+p.employeeId+" AND a.attendanceDate BETWEEN '"+DateUtil.formatDateToSql(p.startDate)+"' AND '"+DateUtil.formatDateToSql(p.endDate)+"'");
        double percentage = 100;
        for (PersonAttendance pa : lst) {
            EventHoliday e = EventHoliday.extractHoliday(pa.attendanceDate);
            if (e!=null) {
            	percentage = e.percentage;
            }
            p.totalOverTimePay += pa.approvedOTHours * p.perHourSalary * percentage;
            p.totalNightTimePay += p.totalNDHours * p.perHourSalary * percentage * 1.1;
        }
        p.totalGrossPay += p.totalOverTimePay + p.totalNightTimePay;
        p.totalNetAmount = p.totalNetAmount = p.basicPay;

        p.totalAdjustmentAmount = p.totalAllowanceAmount = p.totalDeductionAmount = p.totalLoanAmount = p.totalTaxAmount = 0;
        List<EmployeePayrollAdjustment> adjustments = DBClient.getList("SELECT a FROM EmployeePayrollAdjustment a WHERE a.employeePayrollId="+p.seq);
        for (EmployeePayrollAdjustment ad:adjustments) {
            double amount = ad.amount;
            if (ad.deduct) {
                amount = amount * -1;
            }
            else {
                p.totalGrossPay += amount;
            }
            p.totalNetAmount += amount;
            if (ad.adjustmentName.contains("TAX")) {
                p.totalTaxAmount += amount;
            }
            else if (ad.adjustmentName.contains("ALLOWANCE")) {
                p.totalAllowanceAmount += amount;
            }
            else if (ad.adjustmentName.contains("DEDUCTION")) {
                p.totalDeductionAmount += amount;
            }
            else if (ad.adjustmentName.contains("LOAN")) {
                p.totalLoanAmount += amount;
            }
            else {
                p.totalAdjustmentAmount += amount;
            }
        }
        double totalUT = p.totalUTHours * p.perHourSalary * -1;
        double totalAbsent = p.totalAbsentHours * p.perHourSalary * -1;
        p.totalNetAmount += totalUT + totalAbsent;
    }

    private final boolean payrollExist(List<EmployeePayroll> lst, int employeeId) {
        if (lst == null) {
            return false;
        }
        for (EmployeePayroll e : lst) {
            if (e.employeeId == employeeId) {
                return true;
            }
        }
        return false;
    }

//    step 4. Create AlphaList. Must call from server.
    public void createAlphaList(AlphaList a) {
        callMe(STEP4, a);
    }

    private void createAlphaListEmployee(int personId, AlphaList a) {
        AlphaListEmployee e = new AlphaListEmployee();
        Employee emp = (Employee) e.extractPerson(personId);
        e.address = emp.address + " " + emp.address1;
        e.alphalistId = a.seq;
        e.employeeId = personId;
        e.exemptionAmount = emp.extractTaxExcemption();
        e.firstName = emp.firstName;
        e.lastName = emp.lastName;
        e.middleName = emp.middleInitial;
        e.tin = emp.tinNumber;

//        manual input
        e.taxRate = 0;
        e.taxDueJanToNov = 0;
        e.taxDueJantoDec = 0;

//        manual input
        e.prevGsis = 0;
        e.prevNonTaxBenefitAnd13Month = 0;
        e.prevNonTaxCompensation = 0;
        e.prevPagibig = 0;
        e.prevPhic = 0;
        e.prevSss = 0;
        e.prevTaxBenefitAnd13Month = 0;
        e.prevTaxCompensation = 0;
        e.prevTaxSalaries = 0;
        e.prevUnionAmount = 0;

        String sql = "SELECT a FROM EmployeePayrollAdjustment a, EmployeePayroll b " +
                "WHERE a.employeeId="+e.employeeId+" AND a.employeePayrollId=b.seq AND " +
                "b.startDate BETWEEN '"+a.year+"-01-01' AND '"+a.year+"-12-31'";
        List<EmployeePayrollAdjustment> adjustments = DBClient.getList(sql);

//        extract from employee payroll adjustments
        String dis = null;
        double d = 0;
        for (EmployeePayrollAdjustment pay : adjustments) {
            dis = pay.adjustmentName.toUpperCase();
            d = pay.amount;
            if (pay.deduct) {
                d = d * -1;
            }
            if (dis.contains("UNION")) {
                e.unionAmount += d;
            }
            else if (dis.contains("HEALTH") && dis.contains("INS")) {
                e.premPaidOnHealthIns += d;
            }
            else if (dis.contains("GSIS")) {
                e.gsis += d;
            }
            else if (dis.contains("PAGIBIG") || dis.contains("PAG-IBIG")) {
                e.pagibig += d;
            }
            else if (dis.contains("PHILHEALTH") || dis.contains("PHIC")) {
                e.phic += d;
            }
            else if (dis.contains("SSS")) {
                e.sss += d;
            }
            else if (pay.adjustmentName.contains("ALLOWANCE")) {
                e.fringeBenefitAmount += d;
            }
            else if (dis.contains("13")) {
                e.nonTaxBenefitAnd13Month += d;
            }
            else if (dis.contains("COMPENSATION")) {
                e.nonTaxCompensation += d;
            }
            else if (dis.contains("SALARY")) {
                e.nonTaxSalaries += d;
            }
        }

        sql = "SELECT a FROM EmployeePayroll a " +
                "WHERE a.employeeId="+e.employeeId+" AND " +
                "a.startDate BETWEEN '"+a.year+"-01-01' AND '"+a.year+"-12-31'";
        List<EmployeePayroll> payrolls = DBClient.getList(sql);
        for (EmployeePayroll pay : payrolls) {
//        extract from emplyoee payroll
            e.incomeAmount += pay.totalNetAmount;
            e.grossedUpMonetaryValue += pay.totalGrossPay;
            e.taxBenefitAnd13Month = 0;
            e.taxCompensation = 0;
            e.taxSalaries = 0;
            e.taxWithheld += pay.totalTaxAmount;

//        extract from emplyoee payroll
            e.yearEndTax += pay.totalTaxAmount;
            e.yearEndTaxAdjusted = 0;
            e.yearEndTaxRefund = 0;
        }

        e.isSubjectToFinalWTax = true;
        if (e.fringeBenefitAmount > 0) {
            e.isIncludeInFringeBenefit = true;
        }
        e.seq = null;
        e.save();
    }

    private void createAlphaListSummary(AlphaList a) {
        new AlphaListRemittanceSummary(a.seq, "1601-C", "JAN", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-C", "FEB", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-C", "MAR", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-C", "APR", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-C", "MAY", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-C", "JUN", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-C", "JUL", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-C", "AUG", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-C", "SEP", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-C", "OCT", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-C", "NOV", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-C", "DEC", 0).save();
        
        new AlphaListRemittanceSummary(a.seq, "1601-F", "JAN", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-F", "FEB", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-F", "MAR", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-F", "APR", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-F", "MAY", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-F", "JUN", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-F", "JUL", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-F", "AUG", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-F", "SEP", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-F", "OCT", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-F", "NOV", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1601-F", "DEC", 0).save();

        new AlphaListRemittanceSummary(a.seq, "1602", "1ST QTR", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1602", "2ND QTR", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1602", "3RD QTR", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1602", "4TH QTR", 0).save();

        new AlphaListRemittanceSummary(a.seq, "1603", "1ST QTR", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1603", "2ND QTR", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1603", "3RD QTR", 0).save();
        new AlphaListRemittanceSummary(a.seq, "1603", "4TH QTR", 0).save();
    }

    @Override
    public ReturnStruct callService(ParamStruct param) {
        super.callService(param);
        if (param.getActionCommand() == STEP1) {
            //must be called every payday.
            List<Employee> lst = DBClient.getList("SELECT a FROM Employee a WHERE a.isActive=true");
            for (Employee e:lst) {
                createAttendance(e.personId);
            }
        }
        else if (param.getActionCommand() == STEP3) {
            Payroll pay = (Payroll) param.getData();
            List<EmployeePayroll> lste = DBClient.getList("SELECT a FROM EmployeePayroll a WHERE a.payrollId="+pay.seq);

            List<Employee> lst = DBClient.getList("SELECT a FROM Employee a WHERE a.isActive=true");
            for (Employee e:lst) {
                if (!payrollExist(lste, e.personId)) {
                    createEmployeePayroll(pay, e.personId);
                }
            }
        }
        else if (param.getActionCommand() == STEP3_1) {
            EmployeePayroll a = (EmployeePayroll) param.getData();
            calculatePayroll(a);
            a.save();
        }
        else if (param.getActionCommand() == STEP4) {
            AlphaList a = (AlphaList) param.getData();
            a.save();
            List<Employee> lst = DBClient.getList("SELECT a FROM Employee a WHERE a.isActive=true");
            for (Employee e:lst) {
                createAlphaListEmployee(e.personId, a);
            }
            createAlphaListSummary(a);
        }
        return null;
    }

}
