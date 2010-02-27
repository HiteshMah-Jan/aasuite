/*
 * ColumnHeader.java
 *
 * Created on Oct 19, 2007, 7:24:38 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.jtreetable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Budoy Entokwa
 */
public class ColumnHeader {
	List<ColumnHeader> headers = new ArrayList<ColumnHeader>();

    String parentField;
    String childField;
    String header;

    private ColumnHeader() {
    }

	public static ColumnHeader getInstance() {
		return new ColumnHeader();
	}
	
	public ColumnHeader appendHeader(String parentField, String childField, String header) {
		headers.add(new ColumnHeader(parentField, childField, header));
		return this;
	}

	public ColumnHeader appendHeader(ColumnHeader header) {
		headers.add(header);
		return this;
	}
	
    public ColumnHeader(String parentField, String childField, String header) {
        this.parentField = parentField;
        this.childField = childField;
        this.header = header;
    }

    public List<ColumnHeader> getHeaders() {
        return headers;
    }

	public String getChildField() {
        return childField;
    }

    public String getHeader() {
        return header;
    }

    public String getParentField() {
        return parentField;
    }
}