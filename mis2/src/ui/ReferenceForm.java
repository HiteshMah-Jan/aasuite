/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import component.TabPanelForm;

/**
 *
 * @author disney
 */
public class ReferenceForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Reference Form";
    }

    @Override
    public String tabs() {
        return "Branch,Center";
    }

}