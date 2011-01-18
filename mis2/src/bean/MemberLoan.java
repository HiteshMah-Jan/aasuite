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
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author disney
 */
@Entity
@Table(name = "MemberLoan")
@UITemplate(template = TemplateDefault.class, gridCount=4, columnSearch = {"releaseDate", "amount", "interest", "totalAmountPaid", "interestOnLoansPaid", "sbu"})
@ChildRecords( value={
       @ChildRecord(title="Payment", entity = MemberLoanPayment.class, sql = "SELECT a FROM MemberLoanPayment a WHERE a.memberId=${personId}")
})
@Displays({
        @Display(name="releaseDate"),
        @Display(name="amount"),
        @Display(name="interest"),
        @Display(name="totalAmountPaid"),
        @Display(name="interestOnLoansPaid"),
        @Display(name="sbu")
})
@ActionButtons( {
	@ActionButton(name = "btnAddPayment", label = "Add Payment") }
)
@Reports({
    @template.Report(reportFile="LoanPayment", reportTitle="Loan Payment", reportSql="${seq}")
})
public class MemberLoan extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "memberId", nullable = false)
    public Integer memberId;
    @Column(name = "releaseDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date releaseDate;
    @Column(name = "amount")
    public Double amount;
    @Column(name = "interest")
    public Double interest;
    @Column(name = "totalAmountPaid")
    public Double totalAmountPaid;
    @Column(name = "interestOnLoansPaid")
    public Double interestOnLoansPaid;
    @Column(name = "sbu")
    public String sbu;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getInterestOnLoansPaid() {
        return interestOnLoansPaid;
    }

    public void setInterestOnLoansPaid(Double interestOnLoansPaid) {
        this.interestOnLoansPaid = interestOnLoansPaid;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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

    public Double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(Double totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    @Override
    public String popupSearch(String criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
