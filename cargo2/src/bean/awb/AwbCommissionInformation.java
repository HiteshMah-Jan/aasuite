/*
 * AwbCommissionInformation.java
 * 
 * Created on Oct 25, 2007, 4:15:12 PM
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
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbCommissionInformation")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, columnSearch = {"cassIndicator","cassSettlementFactor"}, showChart=true)
@Displays({
    @Display(name = "cassIndicator"),
    @Display(name = "cassSettlementFactor")
})
public class AwbCommissionInformation implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "cassIndicator", nullable = false)
    public String cassIndicator;
    @Column(name = "cassSettlementFactor", nullable = false)
    public String cassSettlementFactor;

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public String getCassIndicator() {
        return cassIndicator;
    }

    public void setCassIndicator(String cassIndicator) {
        this.cassIndicator = cassIndicator;
    }

    public String getCassSettlementFactor() {
        return cassSettlementFactor;
    }

    public void setCassSettlementFactor(String cassSettlementFactor) {
        this.cassSettlementFactor = cassSettlementFactor;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
