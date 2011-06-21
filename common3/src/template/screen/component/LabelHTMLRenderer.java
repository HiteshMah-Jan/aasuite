/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import java.awt.Color;
import javax.swing.JLabel;
import template.screen.AbstractTemplatePanel.FieldCompose;

/**
 *
 * @author Entokwaa
 */
public class LabelHTMLRenderer extends AbstractComponentRenderer {

    @Override
    public Object getRenderedField() {
        String name = this.field.field.getName();
        Label f = new Label();
        f.setForeground(Color.BLUE);
        f.setFieldCompose(this.field);
        f.setName(name);
        
        bindMe("txt", false, f);
        return f;
    }
    
    public static class Label extends JLabel implements component.IGetText {
        FieldCompose field;
        int maxChar;
        String txt;

        public String getTxt() {
            if (txt==null || txt.isEmpty()) return "";
            StringBuffer sb = new StringBuffer();
            sb.append("<html><pre>").append(txt).append("</pre></html>");
            return sb.toString();
        }

        public void setTxt(String arg0) {
        	this.txt = arg0;
			if (arg0!=null && maxChar>0 && maxChar<arg0.length()) {
				arg0 = arg0.substring(0, maxChar);
			}
			super.setText(arg0);
        }
        
        public boolean isEmpty() {
            if (txt==null) return true;
            return txt.isEmpty();
        }

        public FieldCompose getFieldCompose() {
            return field;
        }

        public void setFieldCompose(FieldCompose field) {
            this.field = field;
            try {
                this.maxChar = this.field.display.maxChar();
            }
            catch (Exception e) {
            }
        }
        
        public Object getValue() {
            return txt;
        }
    }
}