/*
 * HospitalService.java
 * 
 * Created on Nov 4, 2007, 3:17:11 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import bean.Patient;
import util.DBUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class HospitalService implements IService {
	
	public Patient getPersonData(int personId) {
        Patient p = (Patient) DBUtil.getInstance().getSingleBean("SELECT a FROM Person a WHERE a.personId="+personId, null);
//		DBUtil.initializeCollection(p.getDeductionCollection());
//		DBUtil.initializeCollection(p.getDiagnosisCollection());
//		DBUtil.initializeCollection(p.getPatientAllergyCollection());
//		DBUtil.initializeCollection(p.getPatientBillingCollection());
//		DBUtil.initializeCollection(p.getPatientImmunizationCollection());
//		DBUtil.initializeCollection(p.getPatientInsuranceCollection());
//		DBUtil.initializeCollection(p.getPatientMedicalRecordCollection());
//		DBUtil.initializeCollection(p.getPatientMedicationCollection());
//		DBUtil.initializeCollection(p.getPayrollCollection());
        return p;
	}
	
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public ReturnStruct callService(ParamStruct param) {
		ReturnStruct ret = new ReturnStruct();
		ret.setStatus(constants.Constants.SUCCESS);
        int action = param.getActionCommand();
		if (action==ServiceConstants.GET_PERSON) {
			Integer personId = (Integer) param.getData();
			Patient p = getPersonData(personId);
			ret.setData(p);
		}
		return ret;
    }

}
