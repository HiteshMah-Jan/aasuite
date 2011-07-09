/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.accounting.payroll.PayrollAdjustmentHelper;
import bean.accounting.payroll.PayrollAdjustmentRef;
import javax.swing.JComponent;

/**
 *
 * @author asmiranda
 */
public class PayrollAdjustmentRef_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnCreateDefault".equals(comp.getName())) {
            createDefault();
        }
        else if ("btnTest".equals(comp.getName())) {
            test();
        }
    }

    private void createDefault() {
    	PayrollAdjustmentHelper.runSSS();
    	PayrollAdjustmentHelper.runPhilhealth();
    	PayrollAdjustmentHelper.runTaxZ();
    	PayrollAdjustmentHelper.runTaxSme();
    	PayrollAdjustmentHelper.runTaxSme1();
    	PayrollAdjustmentHelper.runTaxSme2();
    	PayrollAdjustmentHelper.runTaxSme3();
    	PayrollAdjustmentHelper.runTaxSme4();
    	PayrollAdjustmentHelper.runTaxStatus();
    	refreshRecords();
	}

	private void test() {
        PayrollAdjustmentRef r = (PayrollAdjustmentRef) this.getBean();
        r.test();
    }

}
