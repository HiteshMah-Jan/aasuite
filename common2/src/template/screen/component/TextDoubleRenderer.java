/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import java.text.DecimalFormat;

import javax.swing.JLabel;

import component.JTextFieldPallete;

/**
 *
 * @author Entokwaa
 */
public class TextDoubleRenderer extends TextIntRenderer {
    @Override
    protected JTextFieldPallete getPallete() {
//        JTextFieldPallete pallete = new JTextFieldPallete(new DecimalFormat("###0.00"));
    	JTextFieldPallete pallete = super.getPallete();
        pallete.setDataType(JTextFieldPallete.DOUBLE);
    	pallete.setHorizontalAlignment(JLabel.RIGHT);
        return pallete;
    }
}