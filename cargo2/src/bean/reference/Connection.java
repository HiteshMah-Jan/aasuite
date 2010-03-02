/*
 * Connection.java
 *
 * Created on Oct 9, 2007, 3:04:15 PM
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
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "ConnectionI")
@UITemplate(template = template.screen.TemplateSinglePage.class, gridCount = 6, 
		columnSearch = {"origin","destination","airport1","airport2","airport3","airport4","airport5"}, 
		criteriaSearch = {"origin","destination","airport1"}, 
		showChart=true)
@Displays({
    @Display(name = "origin", width=60, type="PopSearch", linktoBean=Airport.class, mergeFields={"destination"}),
    @Display(name = "destination", width=60, type="PopSearch", linktoBean=Airport.class),
    @Display(name = "airport1", label="Airport 1", width=60, type="PopSearch", linktoBean=Airport.class, mergeFields={"airport2","airport3","airport4","airport5"}),
    @Display(name = "airport2", label="2", width=60, type="PopSearch", linktoBean=Airport.class),
    @Display(name = "airport3", label="3", width=60, type="PopSearch", linktoBean=Airport.class),
    @Display(name = "airport4", label="4", width=60, type="PopSearch", linktoBean=Airport.class),
    @Display(name = "airport5", label="5", width=60, type="PopSearch", linktoBean=Airport.class)
})
public class Connection extends AbstractIBean implements Serializable {
	public static void main(String[] args) {
		view(Connection.class);
	}
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "origin", nullable = false, length=3)
    public String origin;
    @Column(name = "destination", nullable = false, length=3)
    public String destination;
    @Column(name = "airport1", nullable = false, length=3)
    public String airport1;
    @Column(name = "airport2", nullable = false, length=3)
    public String airport2;
    @Column(name = "airport3", length=3)
    public String airport3;
    @Column(name = "airport4", length=3)
    public String airport4;
    @Column(name = "airport5", length=3)
    public String airport5;
    @Column(name = "active")
    public boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAirport1() {
        return airport1;
    }

    public void setAirport1(String airport1) {
        this.airport1 = airport1;
    }

    public String getAirport2() {
        return airport2;
    }

    public void setAirport2(String airport2) {
        this.airport2 = airport2;
    }

    public String getAirport3() {
        return airport3;
    }

    public void setAirport3(String airport3) {
        this.airport3 = airport3;
    }

    public String getAirport4() {
        return airport4;
    }

    public void setAirport4(String airport4) {
        this.airport4 = airport4;
    }

    public String getAirport5() {
        return airport5;
    }

    public void setAirport5(String airport5) {
        this.airport5 = airport5;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"origin","destination");
    }

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "origin","destination","airport1","airport2","airport3","airport4","airport5");
	}
}
