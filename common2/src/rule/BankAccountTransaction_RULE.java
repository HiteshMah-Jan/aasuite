/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.accounting.BankAccountTransaction;
import bean.accounting.GLPostingScript;
import javax.swing.JComponent;
import template.report.AbstractReportTemplate;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class BankAccountTransaction_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
        if ("amount".equals(comp.getName())) {
            BankAccountTransaction tran = (BankAccountTransaction) getBean();
            tran.amountInWord = getAmountInWord(tran.amount);
            tran.changeValue("amountInWord", tran.amountInWord);
        }
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnPrintCheck".equals(comp.getName())) {
            printCheck();
        }
        else if ("btnCreateGL".equals(comp.getName())) {
            createGL();
        }
        else if ("btnViewGL".equals(comp.getName())) {
            viewGL();
        }
        else if ("btnPrintCheckVoucher".equals(comp.getName())) {
            printCheckVoucher();
        }
    }

    private void printCheck() {
        BankAccountTransaction tran = (BankAccountTransaction) getBean();
        if ("WITHDRAWAL".equals(tran.transactionType)) {
            //check mandatory
            try {
                if (!mandatoryOk()) {
                    return;
                }
                alertEmpty("checkNumber", "Check Number is mandatory for printing.");
                alertEmpty("checkPayee", "Check Payee is mandatory for printing.");
                alertEmpty("checkMemo", "Check Memo is mandatory for printing.");
                alertEmpty("checkDate", "Check Date is mandatory for printing.");
            }
            catch (Exception e) {
                return;
            }
            //check if already printed
            if (tran.checkPrinted) {
                boolean b = PanelUtil.showPrompt(null, "Check is already printed. Would you like to print again?");
                if (!b) {
                    return;
                }
            }
            PanelUtil.showMessage(null, "Please put the check to the printer ["+tran.accountNumber+" - "+tran.checkNumber+"].");
            //print the check
            tran.checkPrinted = true;
            tran.save();
            AbstractReportTemplate.getInstance().showReportFromFileTemplate(getCheckFile(), tran.seq);
        }
        else {
            PanelUtil.showErrorMessageToScreen("Check printing is for withdrawal only");
        }
    }

    protected String getCheckFile() {
        return "Cheque";
    }
    
    private void createGL() {
        BankAccountTransaction tran = (BankAccountTransaction) this.getBean();
        if (tran.seq==null || tran.seq==0) {
            saveRecord();
        }
        GLPostingScript.post(tran);
    }

    private void printCheckVoucher() {
        BankAccountTransaction tran = (BankAccountTransaction) this.getBean();
        AbstractReportTemplate.getInstance().showReportFromFileTemplate("CheckVoucher", tran.seq);
    }

    private void viewGL() {
        BankAccountTransaction tran = (BankAccountTransaction) this.getBean();
        if (tran.seq==null || tran.seq==0) {
            saveRecord();
        }
        GLPostingScript.post(tran);
        springbean.IProcess.Process.getInstance().showGL(tran);
    }
    
    private String getAmountInWord(double amount) {
        return "";
    }
}
