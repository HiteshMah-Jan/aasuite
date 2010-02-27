/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author Entokwaa
 */
public class NumberComboRenderer extends ComboRenderer {
    protected List getList() {
        List lst = new ArrayList();
        for (int i = this.field.display.startCount(); i <= this.field.display.endCount(); i++) {
            lst.add(new DummyBean(i+""));
        }
        return lst;
    }
    
    @Override
    public void setSize(JComponent f) {
        Dimension dim = f.getPreferredSize();        
        int width = this.field.display.width();
        if (width>0) {
            if (width==constants.Constants.DEF_WIDTH) width = constants.Constants.HALF_WIDTH;
            dim.width = width;
            f.setPreferredSize(dim);
            f.setSize(dim);
            f.setMinimumSize(dim);
        }
    }
}