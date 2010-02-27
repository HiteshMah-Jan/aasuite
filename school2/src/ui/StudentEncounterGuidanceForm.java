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
public class StudentEncounterGuidanceForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Guidance";
    }
    @Override
    public String tabs() {
        return "OSAExt,StudentPersonalityExam";
    }

}
