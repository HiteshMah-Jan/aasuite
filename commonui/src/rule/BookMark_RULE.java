/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.admin.BookMark;
import common2.Common2View;
import javax.swing.JComponent;

/**
 *
 * @author Charliemagne Mark
 */
public class BookMark_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnShowRecord".equals(comp.getName())) {
            showRecord();
        }
    }

    private void showRecord() {
        BookMark mark = (BookMark) this.getBean();
        Common2View.showBeanPanel(mark.form, mark.recordId);
    }

}
