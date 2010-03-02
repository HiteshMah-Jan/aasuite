/*
 * AwbCustoms.java
 * 
 * Created on Oct 25, 2007, 3:44:01 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

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
@Table(name = "AwbCustoms")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, 
    columnSearch = {"informationIdentifier","customInformationIdentifier","supplementaryCustomInformation","customReference","customOriginCode"}, showChart=true)
@Displays({
    @Display(name = "informationIdentifier"),
    @Display(name = "customInformationIdentifier"),
    @Display(name = "supplementaryCustomInformation"),
    @Display(name = "customReference"),
    @Display(name = "customOriginCode")
})
public class AwbCustoms extends AbstractIBean {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "country", nullable = false)
    public String country;
    @Column(name = "informationIdentifier", nullable = false)
    public String informationIdentifier;
    @Column(name = "customInformationIdentifier", nullable = false)
    public String customInformationIdentifier;
    @Column(name = "supplementaryCustomInformation", nullable = false)
    public String supplementaryCustomInformation;
    @Column(name = "customReference", nullable = false)
    public String customReference;
    @Column(name = "customOriginCode", nullable = false)
    public String customOriginCode;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomInformationIdentifier() {
        return customInformationIdentifier;
    }

    public void setCustomInformationIdentifier(String customInformationIdentifier) {
        this.customInformationIdentifier = customInformationIdentifier;
    }

    public String getCustomOriginCode() {
        return customOriginCode;
    }

    public void setCustomOriginCode(String customOriginCode) {
        this.customOriginCode = customOriginCode;
    }

    public String getCustomReference() {
        return customReference;
    }

    public void setCustomReference(String customReference) {
        this.customReference = customReference;
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

    public String getSupplementaryCustomInformation() {
        return supplementaryCustomInformation;
    }

    public void setSupplementaryCustomInformation(String supplementaryCustomInformation) {
        this.supplementaryCustomInformation = supplementaryCustomInformation;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
