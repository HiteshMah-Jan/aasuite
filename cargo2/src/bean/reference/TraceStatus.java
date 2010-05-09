/*
 * State.java
 *
 * Created on Sep 30, 2007, 8:02:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;
import util.DBClient;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "TraceStatus")
@UITemplate(template = TemplateSinglePage.class, gridCount = 2, 
		columnSearch = {"code","description"})
@Displays({
    @Display(name = "code", width=60),
    @Display(name = "description", width=200)
})
public class TraceStatus extends AbstractIBean implements Serializable {

    @Override
    public boolean cacheClient() {
        return true;
    }
    
    @Id
    @Column(name = "code", nullable = false, length = 5)
    public String code;
    @Column(name = "description", nullable = false, length = 500)
    public String description;

    static List<TraceStatus> lst;
    public static String extractStatus(String status) {
    	if (lst==null) {
    		lst = DBClient.getList("SELECT a FROM TraceStatus a");
    	}
    	for (TraceStatus s:lst) {
    		if (status.equals(s.code)) {
    			return status;
    		}
    	}
//    	create the status if not found
    	TraceStatus s = new TraceStatus();
    	s.code = status;
    	s.description = status;
    	s.save();
    	lst.add(s);
    	return status;
    }
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"code","description");
    }
}
