/*
 * CrudEvent.java
 * 
 * Created on Dec 22, 2007, 8:21:54 PM
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
public class CrudEvent extends AWTEvent {
	public static final int NEW = 0;
	public static final int SAVE = 1;
	public static final int DELETE = 2;
	public static final int REFRESH = 3;
	
	Object bean;
	int event;
	
	public CrudEvent(Object bean, int event) {
		super(bean, Event.ACTION_EVENT);
		this.bean = bean;
		this.event = event;
    }

    public Object getBean() {
        return bean;
    }

    public int getEvent() {
        return event;
    }

}
