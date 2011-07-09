/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import component.JTextFieldPallete;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLabel;

import constants.Constants;

/**
 *
 * @author Entokwaa
 */
public class TextIntRenderer extends TextRenderer {
    @Override
    protected JTextFieldPallete getPallete() {
        JTextFieldPallete pallete = super.getPallete();
        pallete.setDataType(JTextFieldPallete.INTEGER);
    	pallete.setHorizontalAlignment(JLabel.RIGHT);
        return pallete;
    }

    @Override
    public void setSize(JComponent f) {
        Dimension dim = f.getPreferredSize();        
        int width = this.field.display.width();
        if (width>0) {
            if (width==Constants.DEF_WIDTH) width = Constants.HALF_WIDTH;
            dim.width = width;
            f.setPreferredSize(dim);
            f.setSize(dim);
            f.setMinimumSize(dim);
        }
    }
    
}