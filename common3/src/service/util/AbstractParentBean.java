/*
 * AbstractParentBean.java
 * 
 * Created on Sep 20, 2007, 10:50:38 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.util;

import java.util.List;

/**
 *
 * @author Budoy Entokwa
 */
public abstract class AbstractParentBean implements IBean {
	
	private boolean expanded;
	private boolean parent;

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public final boolean isExpanded() {
        return expanded;
    }

    public final void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
	
	public abstract List<IBean> getChildRecords(String childProp);
	
}
