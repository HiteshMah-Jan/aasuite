/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import component.LineNumber;
import javax.swing.JScrollPane;

/**
 *
 * @author Entokwaa
 */
public class TextArea2Renderer extends TextAreaRenderer {
    @Override
    public Object getRenderedField() {
        JScrollPane pane = (JScrollPane) super.getRenderedField();
        LineNumber lineNumber = new LineNumber(f);
        lineNumber.setPreferredSize(99999);
        pane.setRowHeaderView(lineNumber);
        return pane;
    }
}