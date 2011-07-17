/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import template.screen.AbstractTemplatePanel;
import template.screen.AbstractTemplatePanel.FieldCompose;
import test.TemplateViewer;
import util.PanelUtil;

import component.IGetText;

/**
 *
 * @author Entokwaa
 */
public class EmbeddedRenderer extends AbstractComponentRenderer {
	EmbeddedPanel f;
    
    protected EmbeddedPanel getPallete() {
        return new EmbeddedPanel();
    }
    
    @Override
	public Object getRenderedField() {
        if (f!=null) return f;
        String name = this.field.field.getName();
        if (f==null) f = getPallete();
        f.setName(this.field.display.fieldPrefix()+name); 
        f.setToolTipText(this.field.display.name());
//        Dimension preferredSize = new Dimension(this.field.display.width(), this.field.display.height());
//		f.setPreferredSize(preferredSize);
//        setSize(f);
        if(!this.field.display.noLabel()) {
            f.setBorder(new TitledBorder(" "+PanelUtil.getLabel(field)+": "));
        }
        f.setLayout(new GridLayout());
        Class cls = this.field.field.getType();
        AbstractTemplatePanel pnl = TemplateViewer.getPanel(cls);
        pnl.tblResult = (JTable) this.tbl;
        f.add((Component) pnl.getMainForm());
        return f;
	}
    
    class EmbeddedPanel extends JPanel implements IGetText {

		public String getText() {
			// TODO Auto-generated method stub
			return null;
		}

		public void setText(String text) {
			// TODO Auto-generated method stub
			
		}

		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		public Object getValue() {
			// TODO Auto-generated method stub
			return null;
		}

		public FieldCompose getFieldCompose() {
			// TODO Auto-generated method stub
			return null;
		}

		public void setFieldCompose(FieldCompose field) {
			// TODO Auto-generated method stub
			
		}
    	
    }
}