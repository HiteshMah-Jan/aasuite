/*
 * AwbDimension.java
 * 
 * Created on Sep 30, 2007, 8:02:07 PM
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
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbDimension")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, 
    columnSearch = {"marksNumber","pieces","width","length","height","weight","volume","kgLb"}, showChart=true)
@Displays({
    @Display(name = "marksNumber", width=30),
    @Display(name = "pieces", width=30, mergeFields={"width","length","height"}),
    @Display(name = "width", width=30),
    @Display(name = "length", width=30),
    @Display(name = "height", width=30),
    @Display(name = "weight", width=30, mergeFields={"volume","kgLb"}),
    @Display(name = "volume", width=30),
    @Display(name = "kgLb", type="Combo", modelCombo={"KG/MC","LB/CF"}, hideLabel=true, label="UOM", width=50),
    @Display(name = "description")
})
public class AwbDimension extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "marksNumber")
    public String marksNumber;
    @Column(name = "description")
    public String description;
    @Column(name = "width")
    public int width;
    @Column(name = "pieces", nullable = false)
    public int pieces;
    @Column(name = "dimlength")
    public int length;
    @Column(name = "height")
    public int height;
    @Column(name = "volume", nullable = false)
    public double volume;
    @Column(name = "weight", nullable = false)
    public double weight;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "kgLb", nullable = false, length=10)
    public String kgLb;

    public String getMarksNumber() {
		return marksNumber;
	}

	public void setMarksNumber(String marksNumber) {
		this.marksNumber = marksNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getKgLb() {
        return kgLb;
    }

    public void setKgLb(String kgLb) {
        this.kgLb = kgLb;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
