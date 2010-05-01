/*
 * Area.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

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
@Table(name = "Area")
@UITemplate(gridCount = 4, columnSearch = {"area","salesPriority"},title="Area")
@Displays({
        @Display(name="area"),
        @Display(name="salesPriority")
     
})
public class Area extends AbstractIBean implements Serializable{

    @Id
    @Column(name = "area", nullable = false, length=150)
    public String area;
    @Column(name = "description")
    public String description;
    @Column(name = "salesPriority")
    public int salesPriority;
    @Column(name = "notes")
    public String notes;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getSalesPriority() {
        return salesPriority;
    }

    public void setSalesPriority(int salesPriority) {
        this.salesPriority = salesPriority;
    }

    @Override
    public String toString() {
        return area;
    }

     public static void main(String[] args) {
        view(Area.class);
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "area");
	}
}
