/*
 * AwbHistory.java
 * 
 * Created on Sep 30, 2007, 8:02:06 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bean.reference.Airport;
import bean.reference.TraceStatus;
import service.util.AbstractIBean;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbHistory")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, columnSearch = {"status","origin","destination"}, showChart=true)
@Displays({
    @Display(name = "status", type="PopSearch", linktoBean=TraceStatus.class),
    @Display(name = "origin", type="PopSearch", linktoBean=Airport.class),
    @Display(name = "destination", type="PopSearch", linktoBean=Airport.class)
})
public class AwbHistory extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "awbMoveSeq", nullable = false)
    public int awbMoveSeq;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    public Date timestamp;
    @Column(name = "status", nullable = false, length=3)
    public String status;
    @Column(name = "origin", length=3)
    public String origin;
    @Column(name = "destination", length=3)
    public String destination;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;

    public int getAwbMoveSeq() {
        return awbMoveSeq;
    }

    public void setAwbMoveSeq(int awbMoveSeq) {
        this.awbMoveSeq = awbMoveSeq;
    }

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
