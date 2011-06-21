/*
 * ValueChangeEvent.java
 * 
 * Created on Aug 20, 2007, 1:17:00 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.listener;

import java.awt.AWTEvent;
import java.awt.Event;

/**
 *
 * @author Budoy Entokwa
 */
public class ValueChangeEvent extends AWTEvent {
	String newValue, oldValue;
	
	public ValueChangeEvent(Object obj, String newValue, String oldValue) {
		super(obj, Event.ACTION_EVENT);
		source = obj;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	@Override
	public Object getSource() {
		return source;
	}

    public String getNewValue() {
        return newValue;
    }

    public String getOldValue() {
        return oldValue;
    }	
}
