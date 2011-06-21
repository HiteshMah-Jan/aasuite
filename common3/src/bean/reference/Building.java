/*
 * Building.java
 *
 * Created on Dec 2, 2007, 12:37:12 PM
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
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Building")
@UITemplate(columnSearch={"code", "building"}, gridCount=2, title="Building")
@Displays({
        @Display(name="code"),
        @Display(name="building"),
        @Display(name="buildingDescription")
})

public class Building extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "building", nullable = false)
    public String building;
    @Column(name = "buildingDescription")
    public String buildingDescription;


    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBuildingDescription() {
        return buildingDescription;
    }

    public void setBuildingDescription(String buildingDescription) {
        this.buildingDescription = buildingDescription;
    }

    @Override
    public String toString() {
        return building;
    }

    @Override
    public String getComboDisplay() {
        return building;
    }

    public static Building createBuildingObj(String code, String name) {
        Building course = new Building();
        course.code = code;
        course.building = name;
        return course;
    }
    
    @Override
    protected void runSetup() {
        createBuildingObj("BLDG1", "BUILDING 1").save();
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
