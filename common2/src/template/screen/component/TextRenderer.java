/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import component.JTextFieldPallete;

/**
 *
 * @author Entokwaa
 */
public class TextRenderer extends AbstractComponentRenderer {
    JTextFieldPallete f;
    
    protected JTextFieldPallete getPallete() {
        return new JTextFieldPallete();
    }
    
    @Override
    public Object getRenderedField() {
        if (f!=null) return f;
        String name = this.field.field.getName();
        if (f==null) f = getPallete();
        f.setUpCase(this.field.display.upCase());
        f.setName(name); 
        f.setToolTipText(this.field.display.name());
        if (tbl!=null) f.setMandatory(this.field.display.mandatory());
        setSize(f);
        
        bindMe("text", true, f);
        return f;
    }
}