/*
 * HospitalServiceClient.java
 * 
 * Created on Nov 4, 2007, 6:11:21 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.adapter;

import bean.Patient;
import service.ReturnStruct;
import service.util.CallService;

/**
 *
 * @author Budoy Entokwa
 */
public class HospitalServiceClient {

	public static Patient getPersonData(int personId) {
		ReturnStruct ret = CallService.callService(personId, service.ServiceConstants.GET_PERSON, service.ServiceConstants.HOSPITAL_SERVICE);
		Patient p = (Patient) ret.getData();
		return p;
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
