/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import service.util.AbstractIBean;
import template.ChildRecord;

/**
 *
 * @author Charliemagne Mark
 */
public class ChildTemplateListPopupDownButton extends ChildTemplateListPopup {
    @Override
    protected void setup(ChildRecord childAnnotation, AbstractIBean parent) {
        super.setup(childAnnotation, parent);
        this.removeAll();
        this.setLayout(new BorderLayout());
        pnlTempRight.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        this.add(newpnl, BorderLayout.CENTER);
        this.add(pnlTempRight, BorderLayout.SOUTH);
        this.updateUI();
    }
}
