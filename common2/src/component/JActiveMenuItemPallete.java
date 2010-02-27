/*
 * JActiveMenuItemPallete.java
 * 
 * Created on Oct 11, 2007, 10:02:14 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class JActiveMenuItemPallete extends JMenuItem {
	AbstractPanel panel;

    public AbstractPanel getPanel() {
        return panel;
    }

    public void setPanel(AbstractPanel panel) {
        this.panel = panel;
    }
	
    /** Creates a new instance of JActiveMenuItemPallete */
    public JActiveMenuItemPallete(JPanel tpanel) {
		this.panel = (AbstractPanel) tpanel;
		setText(getTitle());
		this.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
				PanelUtil.showPanel(panel);
            }			
		});
    }

    public JActiveMenuItemPallete(JPanel tpanel, String title) {
		this.panel = (AbstractPanel) tpanel;
		setText(title);
		this.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
				PanelUtil.showPanel(panel);
            }			
		});
    }

	public String getTitle() {
		return this.panel.getTitle();
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
