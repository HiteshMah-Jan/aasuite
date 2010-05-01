/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import javax.swing.JComponent;

/**
 *
 * @author Entokwaa
 */
public class CustomerContactPresentationExt_RULE extends CustomerContact_RULE {
    @Override
    public void runOnClick(JComponent comp) {
        super.runOnClick(comp);
        if (comp.getName().equals("btnTodayCallPresentation")) {
            displayToday("PRESENTATION");
        }
        else if (comp.getName().equals("btnWeekCallPresentation")) {
            displayThisWeek("PRESENTATION");
        }
    }
}
