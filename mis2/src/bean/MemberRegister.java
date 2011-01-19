/*
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
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author disney
 */
@Entity
@Table(name = "MemberRegister")
@UITemplate(template = TemplateDefault.class, gridCount=4, columnSearch = {"registerDate", "loan", "ps", "sbu"})
@Displays({
        @Display(name="registerDate"),
        @Display(name="loan"),
        @Display(name="ps"),
        @Display(name="sbu")
})
public class MemberRegister extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "memberId", nullable = false)
    public Integer memberId;
    @Column(name = "registerDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date registerDate;
    @Column(name = "loan")
    public Double loan;
    @Column(name = "ps")
    public Double ps;
    @Column(name = "sbu")
    public String sbu;

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Double getPs() {
        return ps;
    }

    public void setPs(Double ps) {
        this.ps = ps;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getSbu() {
        return sbu;
    }

    public void setSbu(String sbu) {
        this.sbu = sbu;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String popupSearch(String criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setupIndex() {
    	runUniqueIndex(1, "memberId", "registerDate");
    }
}
