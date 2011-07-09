/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen;

import java.awt.GridLayout;

/**
 *
 * @author Charliemagne Mark
 */
public class TemplateScrollableTabPage extends TemplateScrollableSinglePage {
    @Override
    public Object getMainForm() {
        super.getMainForm();
        pnlTab.removeAll();
        pnlTab.add(pnlThis);
        this.removeAll();
        this.setLayout(new GridLayout());
        this.add(myTab);
        return this;
    }
}
