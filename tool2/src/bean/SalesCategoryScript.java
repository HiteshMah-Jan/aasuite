/*
 * SalesScriptQuestion.java
 *
 * Created on Apr 26, 2008, 12:20:42 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;

import service.util.AbstractIBean;

import javax.persistence.*;

import util.DBClient;

/**
 *
 * @author ebhoy
 */
@Entity
@Table(name = "SalesCategoryScript")
public class SalesCategoryScript extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "salesCategory")
    public String salesCategory;
    @Column(name = "salesScriptSeq")
    public int salesScriptSeq;
    @Column(name = "sortNumber")
    public int sortNumber;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    @Column(name = "enterDate")
    public Date enterDate;


    public java.lang.Integer getSeq() {
        return seq;
    }

    public void setSeq(java.lang.Integer seq) {
        this.seq = seq;
    }

    public java.lang.String getSalesCategory() {
        return salesCategory;
    }

    public void setSalesCategory(java.lang.String salesCategory) {
        this.salesCategory = salesCategory;
    }

    public int getSalesScriptSeq() {
        return salesScriptSeq;
    }

    public void setSalesScriptSeq(int salesScriptSeq) {
        this.salesScriptSeq = salesScriptSeq;
    }

    public int getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(int sortNumber) {
        this.sortNumber = sortNumber;
    }

    public java.util.Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(java.util.Date enterDate) {
        this.enterDate = enterDate;
    }

    public String getScript() {
        if (salesScriptSeq==0) return "";
        SalesScript sales = (SalesScript) selectFirstCache("SELECT a FROM SalesScript a WHERE a.seq="+salesScriptSeq);
        return sales.getQuestion();
    }

    public String getFullScript() {
        if (salesScriptSeq==0) return "";
        SalesScript sales = (SalesScript) selectFirstCache("SELECT a FROM SalesScript a WHERE a.seq="+salesScriptSeq);
        return sales.getQuestion();
    }
    
    @Override
    public String getComboDisplay() {
        return getScript();
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "salesCategory");
	}
}
