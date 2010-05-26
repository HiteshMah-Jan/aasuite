/*
 * JLinkLabelPallete.java
 *
 * Created on Aug 19, 2007, 9:41:04 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import common2.Common2View;
import java.awt.event.*;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.BeanUtil;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class JLinkLabelPallete extends JLabel {
    Class bean;
    private IGetText linkFor;
    private String text;

    public void superText(String text) {
        super.setText(text);
    }
    
    @Override
    public void setText(String text) {
        String fontSize = "";
        if (getFont() != null) {
            fontSize = BeanUtil.concat(getFont().getSize(),"");
        }
        this.text = text;
        if (constants.Constants.labelColor!=null) {
            text = BeanUtil.concat("<html><u><font ",fontSize,">",text,"</font></u></html>");
        }
        else {
            text = BeanUtil.concat("<html><u><font color=blue",fontSize,">",text,"</font></u></html>");
        }
        super.setText(text);
    }

    /** Creates a new instance of CodeLabelPallete */
    public JLinkLabelPallete() {
        super.setText("");
        text = "";
    }

    public JLinkLabelPallete(String text, IGetText linkFor) {
        super.setText(text);
        this.text = text;
        this.linkFor = linkFor;
        if (constants.Constants.labelColor!=null) {
            setForeground(constants.Constants.labelColor);
        }
    }

    public void addLinkBean(Class lbean) {
        this.bean = lbean;
        setText(text);
        setToolTipText(BeanUtil.concat("<html>Link to ",PanelUtil.getTitle(bean),"<br>",linkFor.getFieldCompose().display.tooltip(),"</html>"));
        try {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent v) {
                    JLabel lbl = (JLabel) v.getSource();
                    lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                // Invoked when the mouse exits a component.
                @Override
                public void mouseExited(MouseEvent v) {
                    JLabel lbl = (JLabel) v.getSource();
                    lbl.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    Common2View.showBeanPanel(bean.getSimpleName(), linkFor);
                }
            });
        } catch (Exception e) {
            Logger.getLogger("global").log(Level.SEVERE, null, e);
        }
    }
}
