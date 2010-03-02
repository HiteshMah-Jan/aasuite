/*
 * AwbShc.java
 *
 * Created on Sep 30, 2007, 8:02:08 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

import bean.reference.SpecialHandling;
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
@Table(name = "AwbShc")
@UITemplate(template = TemplateTabPage.class, gridCount = 8, 
    columnSearch = {"shcCode"})
@Displays({
    @Display(name = "shcCode", width=60, type="PopSearch", linktoBean=SpecialHandling.class)
})
public class AwbShc extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "awbMoveSeq", nullable = false)
    public int awbMoveSeq;
    @Column(name = "shcCode", length=3)
    public String shcCode;
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

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getShcCode() {
        return shcCode;
    }

    public void setShcCode(String shcCode) {
        this.shcCode = shcCode;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "shcCode","awbSeq");
	}
	
}
