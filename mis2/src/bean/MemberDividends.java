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
@Table(name = "MemberDividends")
@UITemplate(template = TemplateDefault.class, gridCount=4, columnSearch = {"dividendDate", "amount"})
@Displays({
        @Display(name="dividendDate"),
        @Display(name="amount")
})
public class MemberDividends extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "memberId", nullable = false)
    public Integer memberId;
    @Column(name = "dividendDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date dividendDate;
    @Column(name = "amount")
    public Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDividendDate() {
        return dividendDate;
    }

    public void setDividendDate(Date dividendDate) {
        this.dividendDate = dividendDate;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
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
    	runUniqueIndex(1, "memberId", "dividendDate");
    }
}
