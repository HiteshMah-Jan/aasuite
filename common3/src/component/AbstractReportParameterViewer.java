/*
 * AbstractReportParameterViewer.java
 * 
 * Created on Dec 2, 2007, 7:51:31 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Budoy Entokwa
 */
public abstract class AbstractReportParameterViewer extends JPanel {

	public abstract String getTitle();
	public void setParentDialog(JDialog dialog) {		
	}
	
    /** Creates a new instance of AbstractReportParameterViewer */
    public AbstractReportParameterViewer() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
