/*
 * Room.java
 *
 * Created on Dec 2, 2007, 12:43:17 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
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
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Room")
@DiscriminatorColumn(name = "roomType", discriminatorType = DiscriminatorType.STRING)
@UITemplate(columnSearch={"code", "room"}, gridCount=4, title="Room")
@Displays({
        @Display(name="code"),
        @Display(name="room"),
        @Display(name="roomDescription"),
        @Display(name="building", type="PopSearch", linktoBean=Building.class),
        @Display(name="minimumCapacity"), 
        @Display(name="maximumCapacity")
})
public class Room extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "room", nullable = false)
    public String room;
    @Column(name = "roomType")
    public String roomType;
    @Column(name = "type", length = 30)
    public String type;
    @Column(name = "rate")
    public double rate;
    @Column(name = "roomDescription")
    public String roomDescription="";
    @Column(name = "building")
    public String building;
    @Column(name = "minimumCapacity")
    public int minimumCapacity;
    @Column(name = "maximumCapacity")
    public int maximumCapacity;


    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public int getMinimumCapacity() {
        return minimumCapacity;
    }

    public void setMinimumCapacity(int minimumCapacity) {
        this.minimumCapacity = minimumCapacity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public String getComboDisplay() {
        return room;
    }

    public static Room createRoomObj(String code, String room, String building) {
        Room course = new Room(); 
        course.code = code;
        course.room = room;
        course.roomDescription = room;
        course.building = building;
        return course;
    }
    
    @Override
    protected void runSetup() {
        createRoomObj("RM_101", "RM_101", "BLDG1").save();
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
