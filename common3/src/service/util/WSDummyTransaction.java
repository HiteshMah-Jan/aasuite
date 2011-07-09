/*
 * WSDummyTransaction.java
 * 
 * Created on Sep 16, 2007, 12:16:20 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.util;

import javax.persistence.EntityTransaction;

/**
 *
 * @author Budoy Entokwa
 */
public class WSDummyTransaction implements EntityTransaction {

    public WSDummyTransaction() {
    }

    public void begin() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void commit() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rollback() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setRollbackOnly() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean getRollbackOnly() {
		return false;
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isActive() {
		return true;
    }

}
