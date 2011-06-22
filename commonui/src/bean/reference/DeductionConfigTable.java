/*
 * BenefitType.java
 *
 * Created on Feb 13, 2008, 8:28:16 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "DeductionConfigTable")
@UITemplate(gridCount = 4, criteriaSearch={"code"}, columnSearch = {"line","fromAmount","toAmount","percentage","fixedAmount","percentageAfterFromAmount"})
@Displays({
    @Display(name="code", type="Label"),
    @Display(name="line"),
    @Display(name="fromAmount"),
    @Display(name="toAmount"),
    @Display(name="percentage"),
    @Display(name="fixedAmount"),
    @Display(name="percentageAfterFromAmount"),
    @Display(name="description",gridFieldWidth=3,width=-1)
})
public class DeductionConfigTable extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "code", nullable = false, length = 20)
    public String code;
    @Column(name = "line")
    public int line;
    @Column(name = "fromAmount")
    public double fromAmount;
    @Column(name = "toAmount")
    public double toAmount;
    @Column(name = "percentage")
    public double percentage;
    @Column(name = "fixedAmount")
    public double fixedAmount;
    @Column(name = "percentageAfterFromAmount")
    public double percentageAfterFromAmount;
    @Column(name = "description", length = 100)
    public String description;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "description");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(double fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public double getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(double fromAmount) {
        this.fromAmount = fromAmount;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentageAfterFromAmount() {
        return percentageAfterFromAmount;
    }

    public void setPercentageAfterFromAmount(double percentageAfterFromAmount) {
        this.percentageAfterFromAmount = percentageAfterFromAmount;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getToAmount() {
        return toAmount;
    }

    public void setToAmount(double toAmount) {
        this.toAmount = toAmount;
    }
}
