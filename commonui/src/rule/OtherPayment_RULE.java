/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.Person;
import bean.reference.OtherPaymentReference;
import bean.sales.OtherPayment;

import javax.swing.JComponent;

import util.BeanUtil;

/**
 *
 * @author alex
 */
public class OtherPayment_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
    	if ("personId".equals(comp.getName())) {
    		changeCategory();
    	}
    	for (int i=1; i<=10; i++) {
        	if (comp.getName().equals(BeanUtil.concat("paymentCode",i))) {
        		JComponent p = getComponent(BeanUtil.concat("paymentCode",i));
        		OtherPaymentReference ref = (OtherPaymentReference) ((component.LookupTableFieldPallete) p).getSelectedBean();
        		setValue(BeanUtil.concat("paymentAmount",i), ref.amount);
        	}
    	}
        calculateTotal();
    }

    protected void changeCategory() {
        OtherPayment op = (OtherPayment) this.getBean();
        if (op.personId>0) {
    		JComponent p = getComponent("personId");
    		Person ref = (Person) ((component.LookupTableFieldPallete) p).getSelectedBean();
    		if ("STUDENT".equals(ref.personType)) {
                setValue("payerCategory", "STUDENT");
    		}
    		else if ("FACULTY".equals(ref.personType)) {
                setValue("payerCategory", "EMPLOYEE");
    		}
        }
    }
    
    @Override
    public void runOnClick(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
     public void calculateTotal() {
        OtherPayment op = (OtherPayment) this.getBean();
        setValue("totalAmount", op.extractTotalAmount());
    }

}
