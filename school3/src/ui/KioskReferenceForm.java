/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import component.TabPanelForm;
/**
 *
 * @author Charliemagne Mark
 */
public class KioskReferenceForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Kiosk";
    }
    @Override
    public String tabs() {
        return "LibraryBooksSearchExt,SchoolActivityExt";
    }

}
