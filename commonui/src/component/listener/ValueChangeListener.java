/*
 * ValueChangeListener.java
 * 
 * Created on Aug 20, 2007, 1:16:18 PM
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
public interface ValueChangeListener extends EventListener {
	public void valueChanged(ValueChangeEvent evt);
}

