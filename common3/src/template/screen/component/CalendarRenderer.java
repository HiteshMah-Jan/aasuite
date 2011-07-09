/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import component.JCalendarPallete;
import java.util.Date;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.ELProperty;

import util.BeanUtil;

/**
 *
 * @author Entokwaa
 */
public class CalendarRenderer extends AbstractComponentRenderer {
    JCalendarPallete f;

    @Override
    public Object getRenderedField() {
        String name = this.field.display.fieldPrefix()+this.field.field.getName();
        f = new JCalendarPallete();
        f.setName(name);
        f.setMandatory(this.field.display.mandatory());
        f.setDate(null);
        f.setToolTipText(this.field.display.name());
        bindMe("date", true, f);
        return f;
    }

    @Override
    public void lostFocus() {
        Date value = f.getDate();
        String name = this.field.field.getName();
        if (tbl instanceof JTable) {
            ELProperty.create(BeanUtil.concat("${selectedElement.",name,"}")).setValue(tbl, value);
        }
        else {
            ELProperty.create(BeanUtil.concat("${",name,"}")).setValue(tbl, value);
        }
    }
}
