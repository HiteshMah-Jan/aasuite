/*
 * Carrier.java
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
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbPackingList")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, 
    columnSearch = {"boxNo","value","description"})
@Displays({
    @Display(name = "boxNo"),
    @Display(name = "value"),
    @Display(name = "description", gridFieldWidth=3, width=-1)
})
public class AwbPackingList extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "boxNo")
    public String boxNo;
    @Column(name = "description")
    public String description;
    @Column(name = "value")
    public double value;

    public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getAwbSeq() {
		return awbSeq;
	}

	public void setAwbSeq(int awbSeq) {
		this.awbSeq = awbSeq;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "boxNo","awbSeq");
	}
}
