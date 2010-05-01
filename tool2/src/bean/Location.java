/*
 * Location.java
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
@Table(name = "Location")
@UITemplate(gridCount = 4, columnSearch = {"location","area"},title="Location")
@Displays({
        @Display(name="location"),
        @Display(name="area")
     
})
public class Location extends AbstractIBean implements Serializable{

    @Id
    @Column(name = "location", nullable = false, length=150)
    public String location;
    @Column(name = "area")
   public String area;
    @Column(name = "categoryCount1")
    public int categoryCount1;
    @Column(name = "categoryCount2")
    public int categoryCount2;
    @Column(name = "categoryCount3")
    public int categoryCount3;
    @Column(name = "categoryCount4")
    public int categoryCount4;
    @Column(name = "categoryCount5")
    public int categoryCount5;
    @Column(name = "categoryCount6")
    public int categoryCount6;
    @Column(name = "categoryCount7")
    public int categoryCount7;
    @Column(name = "categoryCount8")
    public int categoryCount8;
    @Column(name = "categoryCount9")
    public int categoryCount9;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getCategoryCount1() {
        return categoryCount1;
    }

    public void setCategoryCount1(int categoryCount1) {
        this.categoryCount1 = categoryCount1;
    }

    public int getCategoryCount2() {
        return categoryCount2;
    }

    public void setCategoryCount2(int categoryCount2) {
        this.categoryCount2 = categoryCount2;
    }

    public int getCategoryCount3() {
        return categoryCount3;
    }

    public void setCategoryCount3(int categoryCount3) {
        this.categoryCount3 = categoryCount3;
    }

    public int getCategoryCount4() {
        return categoryCount4;
    }

    public void setCategoryCount4(int categoryCount4) {
        this.categoryCount4 = categoryCount4;
    }

    public int getCategoryCount5() {
        return categoryCount5;
    }

    public void setCategoryCount5(int categoryCount5) {
        this.categoryCount5 = categoryCount5;
    }

    public int getCategoryCount6() {
        return categoryCount6;
    }

    public void setCategoryCount6(int categoryCount6) {
        this.categoryCount6 = categoryCount6;
    }

    public int getCategoryCount7() {
        return categoryCount7;
    }

    public void setCategoryCount7(int categoryCount7) {
        this.categoryCount7 = categoryCount7;
    }

    public int getCategoryCount8() {
        return categoryCount8;
    }

    public void setCategoryCount8(int categoryCount8) {
        this.categoryCount8 = categoryCount8;
    }

    public int getCategoryCount9() {
        return categoryCount9;
    }

    public void setCategoryCount9(int categoryCount9) {
        this.categoryCount9 = categoryCount9;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return location;
    }

     @Override
    public String getComboDisplay() {
        return location;
    }
     
     public static void main(String[] args) {
        view(Location.class);
    }
    

 	@Override
 	public String popupSearch(String criteria) {
 		return buildSearch(criteria, "location");
 	}
}
