/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package springbean;

import bean.Person;
import bean.PurchaseOrderItem;
import bean.SalesOrderItem;
import bean.accounting.payroll.PersonAttendance;
import bean.person.EmployeeLeaveApplication;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Entokwaa
 */
public interface IProcess extends IAccounting {
    void doLogin(Person person);
    void doLogout(Person person);

    void applyLeave(EmployeeLeaveApplication app);
    List<PersonAttendance> getAttendance(Person person);
    List<Date> getAbsent(Person person);
    
    void updateStock(PurchaseOrderItem item);
    void updateStock(SalesOrderItem item);
    
    Object doOtherProcess(int processId, Object... obj);
    
    public static class Process {
        public static IProcess getInstance() {
            try {
                java.lang.String process = constants.Constants.defaultProcess;
                IProcess proc = (IProcess) java.lang.Class.forName(process).newInstance();
                return proc;
            } catch (Exception ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }
}
