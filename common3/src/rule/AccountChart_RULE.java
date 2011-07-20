/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.financial.AccountChart;
import bean.financial.AccountGroup;
import component.JComboBoxPallete;
import component.LookupTableFieldPallete;
import javax.swing.JComponent;
import javax.swing.JLabel;
import template.screen.component.PopSearchRenderer;

/**
 *
 * @author Entokwaa
 */
public class AccountChart_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
        if ("groupName".equals(comp.getName())) {
            changeCategory();            
        }
    }

    @Override
    public void runOnClick(JComponent comp) {
    }

    private void changeCategory() {
//        JLabel lbl = (JLabel) getComponent("category");
        LookupTableFieldPallete cbo = (LookupTableFieldPallete) getComponent("groupName");
        AccountGroup grp = (AccountGroup) cbo.getSelectedBean();
        if (grp!=null) {
//            lbl.setText(grp.category);
            AccountChart chart = (AccountChart) getBean();
            chart.category = grp.category;
        }
    }
}
