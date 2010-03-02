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
public class CargoReferenceForm extends TabPanelForm {

    @Override
    public String tabs() {
        return "Station,Airport,AircraftType,ServiceLevel,Charges,Country,Currency,SpecialHandling,Carrier,UldType,PaymentType,State,Connection";
    }

}
