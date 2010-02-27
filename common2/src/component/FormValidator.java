/*
 * FormValidator.java
 * 
 * Created on Oct 21, 2007, 8:20:04 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import javax.swing.SwingUtilities;

/**
 *
 * @author Budoy Entokwa
 */
public class FormValidator {

	private AbstractPanel pnl;

	protected void initializePanel() {
		
	}
	
    public AbstractPanel getPnl() {
        return pnl;
    }

    public void setPnl(AbstractPanel pnl) {
        this.pnl = pnl;
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initializePanel();
            }			
		});
    }

	public boolean validateOnClickNew() {
		return true;
	}
	
	public boolean validateOnClickDelete() {
		return true;
	}

	public boolean validateOnClickRefresh() {
		return true;
	}

	public boolean validateOnClickSave(boolean isNew) {
		return true;
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
