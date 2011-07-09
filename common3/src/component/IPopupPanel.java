/*
 * PopupPanelIntr.java
 *
 * Created on Dec 6, 2007, 7:23:48 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

/**
 *
 * @author Budoy Entokwa
 */
public interface IPopupPanel {

    void callPopup(AbstractPanel parentPanel, IGetText linkFor, String title, Object source);

    String getDialogTitle(String usedTitle, IGetText linkFor);
    
}
