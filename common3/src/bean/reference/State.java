/*
 * Department.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "State")
@UITemplate(gridCount = 4, columnSearch = {"code", "name"})
@Displays({
    @Display(name="code"),
    @Display(name="name")
})
public class State extends ReferenceType implements Serializable {
    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "name")
    public String name;

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public boolean cacheClient() {
        return true;
    }
}
