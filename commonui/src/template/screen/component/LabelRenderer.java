/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import java.awt.Color;
import java.util.Date;
import javax.swing.JLabel;

import component.LabelPallete;

/**
 *
 * @author Entokwaa
 */
public class LabelRenderer extends AbstractComponentRenderer {

    @Override
    public Object getRenderedField() {
        String name = this.field.field.getName();
        LabelPallete f = new LabelPallete();
        if (constants.Constants.labelColor!=null) {
            f.setForeground(constants.Constants.labelColor);
        }
        else {
            f.setForeground(Color.BLUE);
        }
        f.setFieldCompose(this.field);
        f.setName(name);
        f.setToolTipText(this.field.display.name());
        
        if (this.field.field.getType()==Date.class) {
            bindMe("date", false, f);
        }
        else {
            bindMe("txt", false, f);
        }
        if (this.field.field.getType()==int.class || this.field.field.getType()==double.class) {
            f.setHorizontalAlignment(JLabel.RIGHT);
        }
        return f;
    }
}