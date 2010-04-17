/*
 * ComboTypes.java
 * 
 * Created on Oct 28, 2007, 10:40:57 AM
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

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "ComboTypes")
@UITemplate(gridCount = 4, columnSearch = {"code", "category", "value"})
@Displays({    
    @Display(name="code"),
    @Display(name="category"),
    @Display(name="value"),
    @Display(name="description")
})
public class ComboTypes extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "code", nullable = false, length = 50)
    public String code;
    @Column(name = "category", length = 30)
    public String category;
    @Column(name = "value", length = 4999)
    public String value;
    @Column(name = "description", length = 4999)
    public String description;


    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code", "category", "value", "description");
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
    
    
}
