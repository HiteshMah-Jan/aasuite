/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import bean.accounting.AccountPayable;
import bean.accounting.Expense;
import bean.accounting.GLPostingScript;
import java.util.List;
import javax.swing.JComponent;
import service.util.AbstractIBean;
import template.report.AbstractReportTemplate;
import util.DateUtil;

/**
 *
 * @author Entokwaa
 */
public class Expense_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
      
    }

    @Override
    public void runOnClick(JComponent comp) {
        if (comp.getName().equals("btnCreateGL")) {
            createGL();
        } else if (comp.getName().equals("btnViewGL")) {
            viewGL();
        }
        else if ("btnPrintCashVoucher".equals(comp.getName())) {
            printCashVoucher();
        }
    }

    protected void createGL() {
        Expense expense = (Expense) this.getBean();
        if (expense.isEmptyKey()) {
            saveRecord();
        }
        GLPostingScript.post(expense);
    }

    protected void viewGL() {
        Expense expense = (Expense) this.getBean();
        if (expense.isEmptyKey()) {
            saveRecord();
        }
        GLPostingScript.showGL(expense);
    }

    @Override
    public void onChangeRecord() {
        Expense exp = (Expense) getBean();
        if (!exp.isEmptyKey()) {
            getComponent("amount").setEnabled(false);
            getComponent("amountPaid").setEnabled(false);
        }
        else {
            getComponent("amount").setEnabled(true);
            getComponent("amountPaid").setEnabled(true);
        }
    }

    @Override
    public void afterSave(AbstractIBean bean) {
        Expense exp = (Expense) bean;
        if (exp.amount-exp.amountPaid > 0) {
            List<AccountPayable> lst = AbstractIBean.list("SELECT a FROM AccountPayable a WHERE a.expenseId=",exp.seq);
            if (lst==null || lst.isEmpty()) {
                //create payables
                AccountPayable pay = new AccountPayable();
                pay.amount = exp.amount-exp.amountPaid;
                pay.dueDate = DateUtil.addMonth(exp.expenseDate,1);
                pay.company = exp.chargeDepartment;
                pay.expenseId = exp.seq;
                pay.remarks = "PAYABLE FOR "+exp.reason;
                pay.paid = false;
                pay.save();
            }
        }
    }

    private void printCashVoucher() {
        Expense exp = (Expense) this.getBean();
        AbstractReportTemplate.getInstance().showReportFromFileTemplate("CashVoucher", exp.seq);
    }
      
}
