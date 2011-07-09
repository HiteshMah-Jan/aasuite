/*
 * GenericService.java
 * 
 * Created on Oct 7, 2007, 8:09:47 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.adapter;

import bean.admin.Help;
import constants.Constants;
import service.ReturnStruct;
import service.util.CallService;
import service.util.IBean;
import service.util.WSPersistenceEntityManager;

/**
 *
 * @author Budoy Entokwa
 */
public class GenericServiceClient {
	
	public static void addHelp(Help hlp) {
		CallService.callService(hlp, Constants.ADD_HELP, Constants.Services.HELP_SERVICE);
	}
	
	public static Help getHelp(String helpName) {
		ReturnStruct ret = CallService.callService(helpName, Constants.GET_HELP, Constants.Services.HELP_SERVICE);
		if (ret==null) {
			return null;
		}
		return (Help) ret.getData();
	}

	public static IBean persistBean(IBean bean) {
        WSPersistenceEntityManager entityManager = service.util.WSPersistenceEntityManager.getInstance();
		entityManager.persist(bean);
		return bean;
	}
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
