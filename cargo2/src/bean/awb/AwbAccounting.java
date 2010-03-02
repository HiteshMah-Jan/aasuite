/*
 * AwbAccounting.java
 * 
 * Created on Oct 25, 2007, 4:15:11 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

import bean.reference.Charges;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbAccounting")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"informationIdentifier"})
@Displays({
    @Display(name = "informationIdentifier"),
    @Display(name = "accountingInformation")
})
public class AwbAccounting extends AbstractIBean {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "informationIdentifier", nullable = false)
    public String informationIdentifier;
    @Column(name = "accountingInformation", nullable = false)
    public String accountingInformation;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;

    public String getAccountingInformation() {
        return accountingInformation;
    }

    public void setAccountingInformation(String accountingInformation) {
        this.accountingInformation = accountingInformation;
    }

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public String getInformationIdentifier() {
        return informationIdentifier;
    }

    public void setInformationIdentifier(String informationIdentifier) {
        this.informationIdentifier = informationIdentifier;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
