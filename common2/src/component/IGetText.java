/*
 * IGetText.java
 * 
 * Created on Aug 19, 2007, 9:45:10 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import template.screen.AbstractTemplatePanel.FieldCompose;

/**
 * 
 * @author Budoy Entokwa
 */
public interface IGetText {
	String getText();

	void setText(String text);

	boolean isEmpty();

	Object getValue();

	FieldCompose getFieldCompose();

	void setFieldCompose(FieldCompose field);
}
