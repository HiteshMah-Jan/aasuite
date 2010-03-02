/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbean;

import java.util.List;

import service.util.AbstractIBean;
import template.screen.TablePopup;
import bean.Employee;
import bean.accounting.GL;
import bean.accounting.GLPostingScript;
import bean.accounting.IGL;
import bean.accounting.PaymentLoan;

/**
 *
 * @author Entokwaa
 */
public class AccountingImpl implements IAccounting {

    public List getGL(IGL gl) {
        List lst = AbstractIBean.list(util.BeanUtil.concat("SELECT a FROM GL a WHERE a.form LIKE '",gl.extractGLType().toUpperCase(),"%' AND a.recordId=",gl.extractGLRecordId()));
        return lst;
    }

    public void payLoan(PaymentLoan pay) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Employee> getAllEmployee(String contractType) {
        return AbstractIBean.list("SELECT a FROM Employee a WHERE a.contractType='"+contractType+"'");
    }

    public void createAP(IGL gl, double amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void createAR(IGL gl, double amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void showGL(IGL gl) {
        GLPostingScript.post(gl);
        List lst = getGL(gl);
        TablePopup.showRecords("GL Entries", lst, GL.class, "dateInput", "accountDescription", "reason", "debit", "credit");
    }
}
