/*
 * AwbRateDescription.java
 * 
 * Created on Oct 25, 2007, 3:07:56 PM
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

import bean.UldNumber;
import bean.reference.Country;
import bean.reference.TraceStatus;
import service.util.AbstractIBean;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbRateDescription")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, 
    columnSearch = {"serviceCode","hwbRateLineNumber","pieces","weight"}, showChart=true)
@Displays({
    @Display(name = "serviceCode"),
    @Display(name = "hwbRateLineNumber"),
    @Display(name = "pieces"),
    @Display(name = "weight"),
    @Display(name = "rateClassCode"),
    @Display(name = "commodityItemNumber"),
    @Display(name = "commodityWeight"),
    @Display(name = "rateCharge"),
    @Display(name = "chargeAmount"),
    @Display(name = "ngNatureOfGoods"),
    @Display(name = "kgLb", type="Combo", modelCombo={"KG/MC","LB/CF"}),
    @Display(name = "length"),
    @Display(name = "width"),
    @Display(name = "height"),
    @Display(name = "volumeCode"),
    @Display(name = "volume"),
    @Display(name = "uld", type="PopSearch", linktoBean=UldNumber.class),
    @Display(name = "slac"),
    @Display(name = "harmonisedCommodityCode"),
    @Display(name = "country", type="PopSearch", linktoBean=Country.class)
})
public class AwbRateDescription extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "serviceCode", nullable = false)
    public String serviceCode;
    @Column(name = "hwbRateLineNumber", nullable = false)
    public String hwbRateLineNumber;
    @Column(name = "pieces", nullable = false)
    public int pieces;
    @Column(name = "weight", nullable = false)
    public double weight;
    @Column(name = "rateClassCode", nullable = false)
    public String rateClassCode;
    @Column(name = "commodityItemNumber", nullable = false)
    public int commodityItemNumber;
    @Column(name = "commodityWeight", nullable = false)
    public double commodityWeight;
    @Column(name = "rateCharge", nullable = false)
    public double rateCharge;
    @Column(name = "chargeAmount", nullable = false)
    public double chargeAmount;
    @Column(name = "ngNatureOfGoods", nullable = false)
    public String ngNatureOfGoods;
    @Column(name = "ncNatureOfGoods", nullable = false)
    public String ncNatureOfGoods;
    @Column(name = "kgLb", nullable = false)
    public String kgLb;
    @Column(name = "dimlength", nullable = false)
    public int length;
    @Column(name = "width", nullable = false)
    public int width;
    @Column(name = "height", nullable = false)
    public int height;
    @Column(name = "volumeCode", nullable = false)
    public String volumeCode;
    @Column(name = "volume", nullable = false)
    public double volume;
    @Column(name = "uld", nullable = false)
    public String uld;
    @Column(name = "slac", nullable = false)
    public String slac;
    @Column(name = "harmonisedCommodityCode", nullable = false)
    public String harmonisedCommodityCode;
    @Column(name = "country", nullable = false)
    public String country;

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public int getCommodityItemNumber() {
        return commodityItemNumber;
    }

    public void setCommodityItemNumber(int commodityItemNumber) {
        this.commodityItemNumber = commodityItemNumber;
    }

    public double getCommodityWeight() {
        return commodityWeight;
    }

    public void setCommodityWeight(double commodityWeight) {
        this.commodityWeight = commodityWeight;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHarmonisedCommodityCode() {
        return harmonisedCommodityCode;
    }

    public void setHarmonisedCommodityCode(String harmonisedCommodityCode) {
        this.harmonisedCommodityCode = harmonisedCommodityCode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHwbRateLineNumber() {
        return hwbRateLineNumber;
    }

    public void setHwbRateLineNumber(String hwbRateLineNumber) {
        this.hwbRateLineNumber = hwbRateLineNumber;
    }

    public String getKgLb() {
        return kgLb;
    }

    public void setKgLb(String kgLb) {
        this.kgLb = kgLb;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getNcNatureOfGoods() {
        return ncNatureOfGoods;
    }

    public void setNcNatureOfGoods(String ncNatureOfGoods) {
        this.ncNatureOfGoods = ncNatureOfGoods;
    }

    public String getNgNatureOfGoods() {
        return ngNatureOfGoods;
    }

    public void setNgNatureOfGoods(String ngNatureOfGoods) {
        this.ngNatureOfGoods = ngNatureOfGoods;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public double getRateCharge() {
        return rateCharge;
    }

    public void setRateCharge(double rateCharge) {
        this.rateCharge = rateCharge;
    }

    public String getRateClassCode() {
        return rateClassCode;
    }

    public void setRateClassCode(String rateClassCode) {
        this.rateClassCode = rateClassCode;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getSlac() {
        return slac;
    }

    public void setSlac(String slac) {
        this.slac = slac;
    }

    public String getUld() {
        return uld;
    }

    public void setUld(String uld) {
        this.uld = uld;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getVolumeCode() {
        return volumeCode;
    }

    public void setVolumeCode(String volumeCode) {
        this.volumeCode = volumeCode;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }    

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
