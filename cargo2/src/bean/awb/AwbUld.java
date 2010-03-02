/*
 * AwbUld.java
 * 
 * Created on Oct 25, 2007, 3:07:57 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

import bean.UldNumber;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbUld")
@UITemplate(template = TemplateTabPage.class, gridCount = 8, 
    columnSearch = {"uld","toUld","uldIndicator","weight","kgLb"})
@Displays({
    @Display(name = "uld", width=80, type="PopSearch", linktoBean=UldNumber.class, mergeFields={"toUld"}),
    @Display(name = "toUld", width=80, type="PopSearch", linktoBean=UldNumber.class),
    @Display(name = "uldIndicator", width=30),
    @Display(name = "weight", width=30, mergeFields={"kgLb"}),
    @Display(name = "kgLb", width=50, type="Combo", label="UOM", hideLabel=true, modelCombo={"KG/MC","LB/CF"})
})
public class AwbUld extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "uld", nullable = false)
    public String uld;
    @Column(name = "uldIndicator")
    public String uldIndicator;
    @Column(name = "uldCount")
    public int uldCount;
    @Column(name = "kgLb")
    public String kgLb;
    @Column(name = "weight")
    public double weight;
    @Column(name = "remarks")
    public String remarks;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "toUld")
    public String toUld;

    public String getToUld() {
        return toUld;
    }

    public void setToUld(String toUld) {
        this.toUld = toUld;
    }

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public String getKgLb() {
        return kgLb;
    }

    public void setKgLb(String kgLb) {
        this.kgLb = kgLb;
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

    public String getUld() {
        return uld;
    }

    public void setUld(String uld) {
        this.uld = uld;
    }

    public int getUldCount() {
        return uldCount;
    }

    public void setUldCount(int uldCount) {
        this.uldCount = uldCount;
    }

    public String getUldIndicator() {
        return uldIndicator;
    }

    public void setUldIndicator(String uldIndicator) {
        this.uldIndicator = uldIndicator;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "uld","awbSeq");
	}
}
