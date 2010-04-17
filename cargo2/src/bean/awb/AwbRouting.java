/*
 * AwbRouting.java
 * 
 * Created on Sep 30, 2007, 8:02:12 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import bean.reference.Airport;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbRouting")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, 
    columnSearch = {"route1","route2","route3"})
@Displays({
    @Display(name = "route1", type="PopSearch", linktoBean=Airport.class),
    @Display(name = "route2", type="PopSearch", linktoBean=Airport.class),
    @Display(name = "route3", type="PopSearch", linktoBean=Airport.class)
})
public class AwbRouting extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "route1", length=20)
    public String route1;
    @Column(name = "route2", length=20)
    public String route2;
    @Column(name = "route3", length=20)
    public String route3;

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public String getRoute1() {
        return route1;
    }

    public void setRoute1(String route1) {
        this.route1 = route1;
    }

    public String getRoute2() {
        return route2;
    }

    public void setRoute2(String route2) {
        this.route2 = route2;
    }

    public String getRoute3() {
        return route3;
    }

    public void setRoute3(String route3) {
        this.route3 = route3;
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

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "awbSeq");
	}
}
