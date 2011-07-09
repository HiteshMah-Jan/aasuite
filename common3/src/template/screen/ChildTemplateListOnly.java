/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen;

import java.awt.GridLayout;

import service.util.AbstractIBean;
import template.ChildRecord;

/**
 *
 * @author Charliemagne Mark
 */
public class ChildTemplateListOnly extends ChildTemplateListPopup {
    @Override
    protected void setup(ChildRecord childAnnotation, AbstractIBean parent) {
        super.setup(childAnnotation, parent);
        this.removeAll();
        this.setLayout(new GridLayout());
        this.add(pnlTempLeft);
        this.pnlSave.setVisible(false);
        this.updateUI();
    }
}
