/*
 * CarrierEdiAddress.java
 * 
 * Created on Sep 30, 2007, 8:02:12 PM
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
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CarrierEdiAddress")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, 
    columnSearch = {"station","message","address"})
@Displays({
    @Display(name = "station", type="PopSearch", linktoBean=Station.class),
    @Display(name = "message"),
    @Display(name = "address", gridFieldWidth=3, width=-1)
})
public class CarrierEdiAddress extends AbstractIBean implements Serializable {

    @Override
    public boolean cacheClient() {
        return true;
    }
    
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "station", nullable = false, length=3)
    public String station;
    @Column(name = "flags", length=5)
    public String flags;
    @Column(name = "address", length=50)
    public String address;
    @Column(name = "message", nullable = false, length=50)
    public String message;
    @Column(name = "type", length=20)
    public String type;
    @Column(name = "remarks", length=150)
    public String remarks;
    @Column(name = "office", nullable = false, length=50)
    public String office;
    @Column(name = "dest", nullable = false, length=3)
    public String dest;
    @Column(name = "carrier", nullable = false, length=3)
    public String carrier;


	@Override
	public void setupIndex() {
		runUniqueIndex(1, "carrier","station","message");
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
