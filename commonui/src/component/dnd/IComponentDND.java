/*
 * ComponentDNDIntr.java
 * 
 * Created on Jan 11, 2008, 10:59:36 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.dnd;

/**
 *
 * @author Entokwaa
 */
public interface IComponentDND {
    Object getDataTransfer() throws Exception;
    void processTransferredData(Object obj, javax.swing.JComponent source, javax.swing.JComponent target); 
    void refresh();
}
