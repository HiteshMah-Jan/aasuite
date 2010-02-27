/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbean;

import bean.Person;
import bean.PurchaseOrderItem;
import bean.SalesOrderItem;
import bean.person.EmployeeLeaveApplication;
import bean.person.PersonAttendance;
import bean.reference.EventHoliday;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import util.DBClient;

/**
 *
 * @author Entokwaa
 */
public class ProcessImpl extends AccountingImpl implements IProcess {

    public void doLogin(Person person) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void doLogout(Person person) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void applyLeave(EmployeeLeaveApplication app) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PersonAttendance> getAttendance(Person person) {
        getAbsent(person);
        List<PersonAttendance> lst = DBClient.getList("SELECT a FROM PersonAttendance a WHERE a.personId="+person.personId+" ORDER BY a.attendanceDate DESC", 0, 32);
        Iterator iter = lst.iterator();
        while (iter.hasNext()) {
            PersonAttendance att = (PersonAttendance) iter.next();
            if ("ABSENT".equals(att.attendanceType)) {
                iter.remove();
            }
        }
        return lst;
    }

    public List<Date> getAbsent(Person person) {
        List<Date> lstDate = new ArrayList<Date>();
        List<PersonAttendance> lst = DBClient.getList("SELECT a FROM PersonAttendance a WHERE a.personId="+person.personId+" ORDER BY a.attendanceDate DESC", 0, 32);
        if (lst!=null) {
            //check for 32 days
            Date fromDate = util.DateUtil.addDay(constants.Constants.useDate, -32);
            Date toDate = util.DateUtil.addDay(constants.Constants.useDate, -1);
            while (fromDate.getTime()<=toDate.getTime()) {
                fromDate = util.DateUtil.addDay(fromDate, 1);
                if (util.DateUtil.isHoliday(fromDate)) {
                    PersonAttendance att = new PersonAttendance();
                    att.attendanceDate = fromDate;
                    att.attendanceType = "HOLIDAY";
                    att.personId = person.personId;
                    att.save();
                    continue;
                }
                if (isLoggedIn(person, lst, fromDate)) continue;
                if (isOnLeave(person, fromDate)) {
                    PersonAttendance att = new PersonAttendance();
                    att.attendanceDate = fromDate;
                    att.attendanceType = getLeave(person, fromDate);
                    att.personId = person.personId;
                    att.save();
                }

                //create absent
                PersonAttendance att = new PersonAttendance();
                att.attendanceDate = fromDate;
                att.attendanceType = "ABSENT";
                att.personId = person.personId;
                att.save();
                
                lstDate.add(fromDate);
            }
        }
        return lstDate;
    }

    private boolean isLoggedIn(Person p, List<PersonAttendance> lst, Date d) {
        for (PersonAttendance att : lst) {
            if (d.getTime()==att.attendanceDate.getTime()) {
                //check attendance type
                if ("ABSENT".equals(att.attendanceType)) return false;
                return true;
            }
        }
        return false;
    }
    
    List<EmployeeLeaveApplication> lstLeave;
    private boolean isOnLeave(Person p, Date d) {
        if (lstLeave==null) {
            lstLeave = DBClient.getList("SELECT a FROM EmployeeLeaveApplication a WHERE a.personId="+p.personId);
        }
        for (EmployeeLeaveApplication leave : lstLeave) {
            if (util.DateUtil.isBetween(d, leave.startDate, leave.endDate)) {
                return true;
            }
        }
        return false;
    }
    
    private String getLeave(Person p, Date d) {
        if (lstLeave==null) {
            lstLeave = DBClient.getList("SELECT a FROM EmployeeLeaveApplication a WHERE a.personId="+p.personId);
        }
        for (EmployeeLeaveApplication leave : lstLeave) {
            if (util.DateUtil.isBetween(d, leave.startDate, leave.endDate)) {
                return leave.leaveType;
            }
        }
        return null;
    }

    public void updateStock(PurchaseOrderItem item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateStock(SalesOrderItem item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object doOtherProcess(int processId, Object... obj) {
        return obj;
    }

}
