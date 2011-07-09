/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import javax.swing.JComponent;

/**
 *
 * @author Entokwaa
 */
public interface ITransactionPanel {

    public Object getBean();

    public String getHelpName();

    public String getTitle();

    public void searchMoreRecords();
    public void nextRecord();
    public void prevRecord();
    public void firstRecord();
    public void lastRecord();

    public void newRecord();
    public void deleteRecord();

    public void resetRules();

    public void runRobot();
    public void saveRecord();
    public void refreshRecords();

    public void showReport(JComponent btnReport);
}
