/*
 * ReadonlyTableData.java
 * 
 * Created on Jan 25, 2008, 9:32:54 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.util.List;

/**
 *
 * @author Entokwaa
 */
public class ReadonlyTableData {
    private String title;
    private List<String> headers;
    private List<List> values;
    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getColspan() {
        return headers.size();
    }
    
    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<List> getValues() {
        return values;
    }

    public void setValues(List<List> values) {
        this.values = values;
    }
    
    
}
