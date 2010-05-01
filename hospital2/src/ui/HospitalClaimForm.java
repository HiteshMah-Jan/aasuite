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
public class HospitalClaimForm extends TabPanelForm {

    @Override
    public String tabs() {
        return "HospitalGovernmentClaim,HospitalInsuranceClaim";
    }

}
