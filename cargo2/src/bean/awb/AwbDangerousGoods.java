/*
 * AwbDangerousGoods.java
 *
 * Created on Oct 23, 2007, 8:35:44 PM
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

import bean.reference.SpecialHandling;
import bean.reference.TraceStatus;
import service.util.AbstractIBean;
import template.screen.TemplateTabPage;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbDangerousGoods")
@UITemplate(template = TemplateTabPage.class, gridCount = 6, 
    columnSearch = {"pieces","radioactiveActivity","quantityPerPiece"}, showChart=true)
@Displays({
    @Display(name = "pieces"),
    @Display(name = "radioactiveActivity"),
    @Display(name = "quantityPerPiece"),
    @Display(name = "weightType"),
    @Display(name = "radioactiveCategory"),
    @Display(name = "packingInstruction"),
    @Display(name = "packingType"),
    @Display(name = "createNewOverpack"),
    @Display(name = "createApio"),
    @Display(name = "overpackNumber"),
    @Display(name = "apioNumber"),
    @Display(name = "identicalOverpackCount"),
    @Display(name = "identicalApioPieces"),
    @Display(name = "drillCode"),
    @Display(name = "classOrDivision"),
    @Display(name = "subrisk1"),
    @Display(name = "subrisk2"),
    @Display(name = "shc1", type="PopSearch", linktoBean=SpecialHandling.class),
    @Display(name = "shc2", type="PopSearch", linktoBean=SpecialHandling.class),
    @Display(name = "shc3", type="PopSearch", linktoBean=SpecialHandling.class),
    @Display(name = "properShippingName"),
    @Display(name = "supplementaryInfo"),
    @Display(name = "technicalName"),
    @Display(name = "exceptionHandling"),
    @Display(name = "remarks"),
    @Display(name = "radioactiveTransportIndex"),
    @Display(name = "packingGroup"),
    @Display(name = "unNumber")
})
public class AwbDangerousGoods extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "pieces")
    public int pieces;
    @Column(name = "radioactiveActivity")
    public String radioactiveActivity;
    @Column(name = "quantityPerPiece")
    public String quantityPerPiece;
    @Column(name = "weightType")
    public String weightType;
    @Column(name = "radioactiveCategory")
    public String radioactiveCategory;
    @Column(name = "packingInstruction")
    public String packingInstruction;
    @Column(name = "packingType")
    public String packingType;
    @Column(name = "createNewOverpack")
    public String createNewOverpack;
    @Column(name = "createApio")
    public String createApio;
    @Column(name = "overpackNumber")
    public String overpackNumber;
    @Column(name = "apioNumber")
    public String apioNumber;
    @Column(name = "identicalOverpackCount")
    public String identicalOverpackCount;
    @Column(name = "identicalApioPieces")
    public String identicalApioPieces;
    @Column(name = "drillCode")
    public String drillCode;
    @Column(name = "classOrDivision")
    public String classOrDivision;
    @Column(name = "subrisk1")
    public String subrisk1;
    @Column(name = "subrisk2")
    public String subrisk2;
    @Column(name = "shc1")
    public String shc1;
    @Column(name = "shc2")
    public String shc2;
    @Column(name = "shc3")
    public String shc3;
    @Column(name = "properShippingName")
    public String properShippingName;
    @Column(name = "supplementaryInfo")
    public String supplementaryInfo;
    @Column(name = "technicalName")
    public String technicalName;
    @Column(name = "exceptionHandling")
    public String exceptionHandling;
    @Column(name = "remarks")
    public String remarks;
    @Column(name = "radioactiveTransportIndex")
    public String radioactiveTransportIndex;
    @Column(name = "packingGroup")
    public String packingGroup;
    @Column(name = "unNumber", nullable = false)
    public String unNumber;

    public String getApioNumber() {
        return apioNumber;
    }

    public void setApioNumber(String apioNumber) {
        this.apioNumber = apioNumber;
    }

    public int getAwbSeq() {
        return awbSeq;
    }

    public void setAwbSeq(int awbSeq) {
        this.awbSeq = awbSeq;
    }

    public String getClassOrDivision() {
        return classOrDivision;
    }

    public void setClassOrDivision(String classOrDivision) {
        this.classOrDivision = classOrDivision;
    }

    public String getCreateApio() {
        return createApio;
    }

    public void setCreateApio(String createApio) {
        this.createApio = createApio;
    }

    public String getCreateNewOverpack() {
        return createNewOverpack;
    }

    public void setCreateNewOverpack(String createNewOverpack) {
        this.createNewOverpack = createNewOverpack;
    }

    public String getDrillCode() {
        return drillCode;
    }

    public void setDrillCode(String drillCode) {
        this.drillCode = drillCode;
    }

    public String getExceptionHandling() {
        return exceptionHandling;
    }

    public void setExceptionHandling(String exceptionHandling) {
        this.exceptionHandling = exceptionHandling;
    }

    public String getIdenticalApioPieces() {
        return identicalApioPieces;
    }

    public void setIdenticalApioPieces(String identicalApioPieces) {
        this.identicalApioPieces = identicalApioPieces;
    }

    public String getIdenticalOverpackCount() {
        return identicalOverpackCount;
    }

    public void setIdenticalOverpackCount(String identicalOverpackCount) {
        this.identicalOverpackCount = identicalOverpackCount;
    }

    public String getOverpackNumber() {
        return overpackNumber;
    }

    public void setOverpackNumber(String overpackNumber) {
        this.overpackNumber = overpackNumber;
    }

    public String getPackingGroup() {
        return packingGroup;
    }

    public void setPackingGroup(String packingGroup) {
        this.packingGroup = packingGroup;
    }

    public String getPackingInstruction() {
        return packingInstruction;
    }

    public void setPackingInstruction(String packingInstruction) {
        this.packingInstruction = packingInstruction;
    }

    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getProperShippingName() {
        return properShippingName;
    }

    public void setProperShippingName(String properShippingName) {
        this.properShippingName = properShippingName;
    }

    public String getQuantityPerPiece() {
        return quantityPerPiece;
    }

    public void setQuantityPerPiece(String quantityPerPiece) {
        this.quantityPerPiece = quantityPerPiece;
    }

    public String getRadioactiveActivity() {
        return radioactiveActivity;
    }

    public void setRadioactiveActivity(String radioactiveActivity) {
        this.radioactiveActivity = radioactiveActivity;
    }

    public String getRadioactiveCategory() {
        return radioactiveCategory;
    }

    public void setRadioactiveCategory(String radioactiveCategory) {
        this.radioactiveCategory = radioactiveCategory;
    }

    public String getRadioactiveTransportIndex() {
        return radioactiveTransportIndex;
    }

    public void setRadioactiveTransportIndex(String radioactiveTransportIndex) {
        this.radioactiveTransportIndex = radioactiveTransportIndex;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getShc1() {
        return shc1;
    }

    public void setShc1(String shc1) {
        this.shc1 = shc1;
    }

    public String getShc2() {
        return shc2;
    }

    public void setShc2(String shc2) {
        this.shc2 = shc2;
    }

    public String getShc3() {
        return shc3;
    }

    public void setShc3(String shc3) {
        this.shc3 = shc3;
    }

    public String getSubrisk1() {
        return subrisk1;
    }

    public void setSubrisk1(String subrisk1) {
        this.subrisk1 = subrisk1;
    }

    public String getSubrisk2() {
        return subrisk2;
    }

    public void setSubrisk2(String subrisk2) {
        this.subrisk2 = subrisk2;
    }

    public String getSupplementaryInfo() {
        return supplementaryInfo;
    }

    public void setSupplementaryInfo(String supplementaryInfo) {
        this.supplementaryInfo = supplementaryInfo;
    }

    public String getTechnicalName() {
        return technicalName;
    }

    public void setTechnicalName(String technicalName) {
        this.technicalName = technicalName;
    }

    public String getUnNumber() {
        return unNumber;
    }

    public void setUnNumber(String unNumber) {
        this.unNumber = unNumber;
    }

    public String getWeightType() {
        return weightType;
    }

    public void setWeightType(String weightType) {
        this.weightType = weightType;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
