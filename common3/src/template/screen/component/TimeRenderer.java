/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import component.TimeRendererPallete;
import javax.swing.JTable;
import org.jdesktop.beansbinding.ELProperty;

import util.BeanUtil;

/**
 *
 * @author Entokwaa
 */
public class TimeRenderer extends AbstractComponentRenderer {
    TimeRendererPallete f;
    
    @Override
    public Object getRenderedField() {
        String name = this.field.field.getName();
        f = new TimeRendererPallete();
        f.setName(name);
        f.setToolTipText(this.field.display.name());
        bindMe("text", true, f);
        return f;
    }

    @Override
    public void lostFocus() {
        String value = f.getTime();
        String name = this.field.field.getName();
        if (tbl instanceof JTable) {
            ELProperty.create(BeanUtil.concat("${selectedElement.",name,"}")).setValue(tbl, value);
        }
        else {
            ELProperty.create(BeanUtil.concat("${",name,"}")).setValue(tbl, value);
        }
    }
}