package springbean;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.Vector;

import service.ParamStruct;
import service.ReturnStruct;
import util.DBClient;
import util.DateUtil;
import util.ThreadPoolUtil;
import bean.Employee;
import bean.accounting.payroll.PersonAttendance;
import bean.admin.AppConfig;
import service.ServiceWrapper;

public class AttendanceService extends ServiceWrapper {

    private static boolean running;
    private final static long fONCE_PER_DAY = 1000 * 60 * 60 * 24;
    static List<String> lst = new Vector();

    private void readFiles() {
        String str = AppConfig.getTemporaryFolder();
        File f = new File(str);
        if (!f.exists()) {
            f.mkdir();
        }
        f = new File(str + "/attendance");
        if (!f.exists()) {
            f.mkdir();
        }
        File proc = new File(str + "/attendance/processed");
        if (!proc.exists()) {
            proc.mkdir();
        }
        f = new File(str + "/attendance/in");
        if (!f.exists()) {
            f.mkdir();
        }
        // read all files from in
        File[] lst = f.listFiles();
        for (int i = 0; i < lst.length; i++) {
            File file = lst[i];
            if (this.lst.contains(file.getName())) {
//				System.out.println("\n\n####### Locked file "+file.getName()+" #######\n\n");
                continue;
            }
            this.lst.add(file.getName());
            try {
                ThreadPoolUtil.execute(new RequestRun(file, proc));
            } catch (Exception e) {
            }
        }
    }

    private static Date getTomorrow8PM() {
        Calendar tomorrow = new GregorianCalendar();
        tomorrow.add(Calendar.DATE, 1);
        Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR),
                tomorrow.get(Calendar.MONTH), tomorrow.get(Calendar.DATE),
                18, 0);
        return result.getTime();
    }

    @Override
    public ReturnStruct callService(ParamStruct param) {
        AAAConfig.getInstance();
        // process the service every 3 minutes
        if (!running) {
            Timer t = new Timer();
            t.scheduleAtFixedRate(this, getTomorrow8PM(), 1000 * 60 * 60 * 24);
            running = true;
        }
        return null;
    }

    @Override
    public void run() {
        readFiles();
    }

    private static class RequestRun implements Runnable {

        File file, proc;

        public RequestRun(File file, File proc) {
            this.file = file;
            this.proc = proc;
        }

        @Override
        public void run() {
            runFile(file);
            AttendanceService.lst.remove(file.getName());
            file.renameTo(new File(proc, file.getName()));
        }
    }

    public static void runFile(File file) {
        try {
            byte[] b = new byte[(int) file.length()];
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            raf.read(b);
            raf.close();
            String str = new String(b);
            String[] arr = str.split("\n");
            // first 1 is just label
            for (int i = 1; i < arr.length; i++) {
                try {
                    String s = arr[i];
                    if (s.trim().length() < 20) {
                        continue;
                    }
                    String cname = s.substring(0, 52).trim().toUpperCase();
                    String cid = s.substring(52, 64).trim();
                    String cdate = s.substring(64, 74).trim();
                    String cday = s.substring(76, 81).trim();
                    String ctimein = s.substring(81, 86).trim();
                    String cBreakOut1 = s.substring(92, 97).trim();
                    String cBreakIn1 = s.substring(102, 107);
                    String cLunchOut = s.substring(112, 117);
                    String cLunchIn = s.substring(122, 127);
                    String cBreakOut2 = s.substring(132, 137);
                    String cBreakIn2 = s.substring(142, 147);
                    String cTimeOut = s.substring(152, 157).trim();
                    String cTotalTime = s.substring(162, 171);

                    Date date = DateUtil.readDate(cdate.trim(), "yyyy-MM-dd");
                    String timein = DateUtil.formatTime(ctimein);
                    String timeout = DateUtil.formatTime(cTimeOut);

//					check if same attendance posted
                    Employee emp = (Employee) DBClient.getFirstRecord("SELECT a FROM Employee a WHERE a.employeeNumber='" + cid + "'");
                    if (emp == null) {
                        emp = new Employee();
                        emp.personType = "FACULTY";
                        String lastName = cname.substring(0, cname.indexOf(","));
                        String firstName = cname.substring(cname.indexOf(","), cname.length() - 2);
                        String middle = cname.substring(cname.length() - 2);
                        emp.lastName = lastName;
                        emp.firstName = firstName;
                        emp.middleInitial = middle;
                        emp.employeeNumber = cid;
                        emp.save();
                    }
                    PersonAttendance att = (PersonAttendance) DBClient.getFirstRecord("SELECT a FROM PersonAttendance a WHERE a.personId=" + emp.personId + " AND a.attendanceDate='" + DateUtil.formatDateToSql(date) + "'");
                    if (att == null || att.isEmptyKey()) {
                        att = new PersonAttendance();
                    }
                    att.attendanceDate = date;
                    att.login = timein;
                    att.logout = timeout;
                    att.personId = emp.personId;
                    att.save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
