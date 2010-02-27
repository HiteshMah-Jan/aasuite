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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
@DiscriminatorColumn(name = "types", discriminatorType = DiscriminatorType.STRING)
@UITemplate(gridCount = 4, columnSearch = {"code", "types", "value"})
@Displays({    
    @Display(name="code"),
    @Display(name="types"),
    @Display(name="value"),
    @Display(name="description")
})
public class ComboTypes extends AbstractIBean implements Serializable {
    @Id
//    @Column(name = "seq", nullable = false)
//    public Integer seq;
    @Column(name = "code", nullable = false, length = 50)
    public String code;
    @Column(name = "types", length = 30)
    public String types;
    @Column(name = "value", length = 4999)
    public String value;
    @Column(name = "description", length = 4999)
    public String description;


    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code", "types", "value", "description");
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

//    public Integer getSeq() {
//        return seq;
//    }
//
//    public void setSeq(Integer seq) {
//        this.seq = seq;
//    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return code+"-"+value;
    }
    public static String getTypeQuery(String type) {
        return "SELECT a FROM ComboTypes a WHERE a.types='" + type + "'";
    }
    public static List getComboTypeList(String type) {
        List lst = AbstractIBean.list("SELECT a FROM ComboTypes a WHERE a.types='" + type + "'");
        if (lst == null) {
            return new ArrayList();
        }
        return lst;
    }
    public static String getValue(String type, String code) {
        Object obj = AbstractIBean.singleColumn("SELECT a.value FROM ComboTypes a WHERE a.types='" + type + "' AND a.code='" + code + "'");
        if (obj != null) {
            return obj.toString();
        }
        setValue(type, code, "");
        return null;
    }
    public static void setValue(String type, String code, String value) {
        ComboTypes types = new ComboTypes();
        types.types=type;
        types.code=code;
        types.value=value;
        types.save();
    }
	
	@Override
	public void setupIndex() {
		runIndex(1, "types");
	}
}
