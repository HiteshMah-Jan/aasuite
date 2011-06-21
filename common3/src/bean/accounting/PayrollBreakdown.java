/*
 * AclDuty.java
 *
 * Created on Sep 30, 2007, 8:02:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import bean.reference.BenefitType;
import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PayrollBreakdown")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"payrollId", "benefitDeductionCode", "computedAmount"})
@Displays({
        @Display(name="seq"),
        @Display(name="payrollId"),
        @Display(name="benefitDeductionCode", type = "PopSearch", linktoBean = BenefitType.class, gridFieldWidth = 3, width = -1),
        @Display(name="isBenefit"),
        @Display(name="isLoan"),
        @Display(name="computedAmount")
})
public class PayrollBreakdown extends AbstractIBean implements Serializable {

    @Id
   @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "payrollId", nullable = false)
    public int payrollId;
    @Column(name = "benefitDeductionCode", nullable = false, length = 100)
    public String benefitDeductionCode;
    @Column(name = "isBenefit", nullable = false)
    public boolean isBenefit;
    @Column(name = "isLoan")
    public boolean isLoan = false;
    @Column(name = "computedAmount", nullable = false)
    public double computedAmount;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public String getBenefitDeductionCode() {
        return benefitDeductionCode;
    }

    public void setBenefitDeductionCode(String benefitDeductionCode) {
        this.benefitDeductionCode = benefitDeductionCode;
    }

    public double getComputedAmount() {
        return computedAmount;
    }

    public void setComputedAmount(double computedAmount) {
        this.computedAmount = computedAmount;
    }

    public boolean isIsBenefit() {
        return isBenefit;
    }

    public void setIsBenefit(boolean isBenefit) {
        this.isBenefit = isBenefit;
    }

    public boolean isIsLoan() {
        return isLoan;
    }

    public void setIsLoan(boolean isLoan) {
        this.isLoan = isLoan;
    }

    public int getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
