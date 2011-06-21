/*
 * SqlSearchConstructor.java
 * 
 * Created on Oct 21, 2007, 6:23:39 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import component.IGetText;
import component.JCalendarPallete;

/**
 *
 * @author Budoy Entokwa
 */
public class SqlSearchConstructor {
	StringBuffer sql = new StringBuffer();
	Class bean;
	String key;
	boolean includeEmpty;

	public static SqlSearchConstructor getInstance(Class bean, String addCriteria, boolean includeEmpty) {
		return new SqlSearchConstructor(bean, addCriteria, includeEmpty);
	}
	
    @Override
	public String toString() {
        return sql.toString();
    }
	
    /** Creates a new instance of SqlSearchConstructor */
    public SqlSearchConstructor(Class bean, String addCriteria, boolean includeEmpty) {
		this.bean = bean;
		this.key = key;
		this.includeEmpty = includeEmpty;
		sql.append("SELECT a FROM ").append(bean.getSimpleName()).append(" a WHERE ").append(addCriteria);
    }

	public SqlSearchConstructor addNumericCriteria(String field, IGetText text) {
		if (!text.isEmpty()) sql.append(" and a.").append(field).append(" = ").append(text.getText());
		return this;
	}

	public SqlSearchConstructor addStringCriteria(String field, IGetText text) {
		if (!includeEmpty && text.isEmpty()) return this;
		sql.append(" and a.").append(field).append(" = '").append(text.getText()).append("'");
		return this;
	}

	public SqlSearchConstructor addDateCriteria(String field, JCalendarPallete text) {
		if (!text.isEmpty()) sql.append(" and a.").append(field).append(" = '").append(text.getSqlDateFormat()).append("'");
		return this;
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
