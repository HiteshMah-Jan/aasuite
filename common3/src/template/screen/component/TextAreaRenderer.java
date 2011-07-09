/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template.screen.component;

import component.JTextAreaPallete;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import template.screen.AbstractTemplatePanel.FieldCompose;
import ui.DynamicAccessForm;

/**
 *
 * @author Entokwaa
 */
public class TextAreaRenderer extends AbstractComponentRenderer {

    protected JTextAreaPallete f;

    @Override
    public Object getRenderedField() {
        String name = this.field.display.fieldPrefix()+this.field.field.getName();
        f = new JTextAreaPallete();
        f.setFieldCompose(field);
        f.setName(name);
        f.setUpCase(this.field.display.upCase());
        f.setToolTipText(this.field.display.name());
        DynamicAccessForm.initializeComponent(f);
        bindMe("text", true, f);

        f.setAutoscrolls(true);
        ScrollPane pane = new ScrollPane();
        pane.setTextArea(f);
        pane.setViewportView(f);
        pane.setAutoscrolls(true);

        int width = this.field.display.width();
        int height = this.field.display.height();
        pane.setPreferredSize(new Dimension(width, height));
        return pane;
    }

    public static class ScrollPane extends JScrollPane implements component.IGetText {
        JTextAreaPallete area;
        private void setTextArea(JTextAreaPallete area) {
            this.area = area;
        }
        
        public String getText() {
            return area.getText();
        }

        public void setText(String text) {
            area.setText(text);
            area.updateUI();
            updateUI();
        }

        public boolean isEmpty() {
            return area.isEmpty();
        }

        public FieldCompose getFieldCompose() {
            return area.getFieldCompose();
        }

        public void setFieldCompose(FieldCompose field) {
            area.setFieldCompose(field);
        }

        public Object getValue() {
            return area.getValue();
        }
    }
}