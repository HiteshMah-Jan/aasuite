/*
 * CrudListener.java
 * 
 * Created on Dec 22, 2007, 8:25:39 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.listener;

import java.util.EventListener;

/**
 *
 * @author Budoy Entokwa
 */
public interface CrudListener extends EventListener {
	public void crudAction(CrudEvent evt);
}
