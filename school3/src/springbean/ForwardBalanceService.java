package springbean;

import service.IService;
import service.ReturnStruct;
import service.ParamStruct;
import util.BeanUtil;
import util.DBClient;
import bean.accounting.PaymentEnrollment;
import bean.Student;
import bean.admin.AppConfig;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Apr 6, 2009
 * Time: 2:55:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ForwardBalanceService implements IService {
    @Override
    public ReturnStruct callService(ParamStruct param) {
        final String schoolYear = (String) param.getData();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                runForwardBalance(schoolYear);
            }
        });
        t.start();
        return null;
    }

    private void runForwardBalance(String schoolYear) {
        List<Student> lst = DBClient.getList("SELECT a FROM Student a", 0, 10000);
        for (Student s : lst) {
            Thread.yield();
            createForwarBalance(s, schoolYear);
        }
    }

    public static void createForwarBalance(Student s, String schoolYear) {
    	boolean b = AppConfig.isRunFWB();
    	if (!b) {
//    		no need to run FWB
    		return;
    	}
//            get the sum of balance
        double d = DBClient.getSingleColumnDouble("SELECT SUM(a.balance) FROM Payment a WHERE a.shoolYear='",schoolYear,"' AND a.paidBy=",s.personId," AND a.paid=0");
//            create a forward balance for this year
        PaymentEnrollment p = new PaymentEnrollment();
        p.paidBy = s.personId;
        p.orType = "A";
        p.line = 0;
        p.schoolYear = AppConfig.getSchoolYear();
        p.accountNumber = "102";
        p.paymentFor = "FWB";
        p.description = BeanUtil.concat("FORWARD BALANCE FROM ",schoolYear);
        p.amount = p.overallAmountDue = p.balance = p.overallBalance = d;
        p.save();
//            update all the balance make it zero
        DBClient.runSQLNative("UPDATE Payment a SET a.balance=0 WHERE a.shoolYear='",schoolYear,"' AND a.paidBy=",s.personId," AND a.paid=0");
    }

    public static void main(String[] args) {

    }
}
