/*
 * AwbInspection.java
 * 
 * Created on Sep 30, 2007, 8:02:11 PM
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
import service.util.AbstractIBean;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbInspection")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, 
    columnSearch = {"inspectedBy","inspectedBy","exemptPieces","eligiblePieces"}, showChart=true)
@Displays({
    @Display(name = "inspectedBy"),
    @Display(name = "inspectedBy"),
    @Display(name = "exemptPieces"),
    @Display(name = "eligiblePieces"),
    @Display(name = "reason")
})
public class AwbInspection extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "inspectedBy", nullable = false, length=50)
    public String inspectedBy;
    @Column(name = "inspectedDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date inspectedDate;
    @Column(name = "exemptPieces", nullable = false)
    public int exemptPieces;
    @Column(name = "reason", nullable = false, length=150)
    public String reason;
    @Column(name = "eligiblePieces", nullable = false)
    public int eligiblePieces;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public int getEligiblePieces() {
        return eligiblePieces;
    }

    public void setEligiblePieces(int eligiblePieces) {
        this.eligiblePieces = eligiblePieces;
    }

    public int getExemptPieces() {
        return exemptPieces;
    }

    public void setExemptPieces(int exemptPieces) {
        this.exemptPieces = exemptPieces;
    }

    public String getInspectedBy() {
        return inspectedBy;
    }

    public void setInspectedBy(String inspectedBy) {
        this.inspectedBy = inspectedBy;
    }

    public Date getInspectedDate() {
        return inspectedDate;
    }

    public void setInspectedDate(Date inspectedDate) {
        this.inspectedDate = inspectedDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
