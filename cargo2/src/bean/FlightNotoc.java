/*
 * FlightManifestAwb.java
 * 
 * Created on Sep 30, 2007, 8:02:13 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import service.util.AbstractIBean;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "FlightNotoc")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, 
    columnSearch = {"inputDate","notocText"})
@Displays({
    @Display(name = "inputDate"),
    @Display(name = "notocText", width=400)
})
public class FlightNotoc extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "flightSeq", nullable = false)
    public int flightSeq;
    @Column(name = "inputDate")
    @Temporal(TemporalType.DATE)
    public Date inputDate;
    @Column(name = "notocText", nullable = false, length=500)
    public String notocText;

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "flightSeq","inputDate","notocText");
	}

	public int getFlightSeq() {
        return flightSeq;
    }

    public void setFlightSeq(int flightSeq) {
        this.flightSeq = flightSeq;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public String getNotocText() {
        return notocText;
    }

    public void setNotocText(String notocText) {
        this.notocText = notocText;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
