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
public class OSAForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "OSA";
    }
    @Override
    public String tabs() {
        return "StudentEncounterGuidance,StudentPersonalityExamExt,SchoolActivity";
    }

}
