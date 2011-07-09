/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import component.JCheckBoxPallete;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class CheckBoxRenderer extends AbstractComponentRenderer {

//    @Override
//    public Object getRenderedLabel() {
//        return new JLabel("");
//    }
//
    @Override
    public Object getRenderedField() {
        String name = this.field.display.fieldPrefix()+this.field.field.getName();
        JCheckBoxPallete f = new JCheckBoxPallete();
        f.setName(name);
        f.setToolTipText(this.field.display.name());
        if (constants.Constants.labelColor!=null) {
            f.setForeground(constants.Constants.labelColor);
        }
        if (constants.Constants.panelBackground!=null) {
            f.setBackground(constants.Constants.panelBackground);
        }
        f.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
		                lostFocus();
					}
            	});
            }
        });                
        bindMe("selected", false, f);
        return f;
    }
    
    @Override
    protected void lostFocus() {
//        if (tbl instanceof JTable) {
//            Object objTmp = getRenderedField();
//            if (objTmp instanceof IGetText) {
//                String name = this.field.field.getName();
//                AbstractIBean bean = (AbstractIBean) ELProperty.create("${selectedElement}").getValue(tbl);
//                
//                if (objTmp instanceof IParentComp) {
//                    objTmp = ((IParentComp) objTmp).getTrueTxt();
//                }
//                if (bean!=null && objTmp!=null) {
//                    bean.changeValue(name, ((IGetText)objTmp).getValue());
//                    bean.changeValue(name, ((IGetText)objTmp).getValue());
//                }
//            }
//        }
    }
}