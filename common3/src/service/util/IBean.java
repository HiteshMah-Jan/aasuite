package service.util;

import java.io.Serializable;

/*
 * IBean.java
 * 
 * Created on Sep 8, 2007, 5:49:53 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Budoy Entokwa
 */
public interface IBean extends Serializable {
        void changeCache(IBean bean);
        boolean isNewCache();
	String _Key();
	String _Table();
	int _Status();
	
	IBean updateMe(javax.persistence.EntityManager entityManager);
	IBean getMe(javax.persistence.EntityManager entityManager);
        boolean canSet(String field);
        void serverCache();
//        boolean canCache();
        void runIncludeSave();
}
