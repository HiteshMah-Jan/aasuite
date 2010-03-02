/*
 * StorageLocation.java
 *
 * Created on Oct 2, 2007, 1:48:15 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "StorageLocation")
@UITemplate(template = TemplateSinglePage.class,
    columnSearch = {"code", "noDlvReceipt", "productCode"})
@Displays({
    @Display(name = "code"),
    @Display(name = "noDlvReceipt", width = 250),
    @Display(name = "productCode", gridFieldWidth=3, width=-1)
})
public class StorageLocation extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code", nullable = false, length = 3)
    public String code;
    @Column(name = "noDlvReceipt")
    public char noDlvReceipt;
    @Column(name = "awbLocationSeq")
    public int awbLocationSeq;
    @Column(name = "productCode")
    public String productCode;
    @Column(name = "height")
    public int height;
    @Column(name = "expImp")
    public char expImp;
    @Column(name = "nrtgTimeStamp")
    public int nrtgTimeStamp;
    @Column(name = "width")
    public int width;
    @Column(name = "stockTimeStamp")
    @Temporal(value = TemporalType.DATE)
    public Date stockTimeStamp;
    @Column(name = "volume")
    public int volume;
    @Column(name = "shcList", length = 50)
    public String shcList;
    @Column(name = "remark", length = 150)
    public String remark;
    @Column(name = "entryCode", length = 20)
    public String entryCode;
    @Column(name = "station", nullable = false, length = 3)
    public String station;
    @Column(name = "storageCode", length = 3)
    public String storageCode;
    @Column(name = "prefer")
    public boolean prefer;
    @Column(name = "posRow", length = 10)
    public String posRow;
    @Column(name = "block")
    public boolean block;
    @Column(name = "noArrReceipt")
    public char noArrReceipt;
    @Column(name = "uld")
    public boolean uld;
    @Column(name = "posLevel", length = 10)
    public String posLevel;
    @Column(name = "subType", length = 10)
    public String subType;
    @Column(name = "stlength")
    public int stlength;
    @Column(name = "customsNumber", length = 30)
    public String customsNumber;
    @Column(name = "posColumn", length = 30)
    public String posColumn;
    @Column(name = "active")
    public boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getAwbLocationSeq() {
        return awbLocationSeq;
    }

    public void setAwbLocationSeq(int awbLocationSeq) {
        this.awbLocationSeq = awbLocationSeq;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomsNumber() {
        return customsNumber;
    }

    public void setCustomsNumber(String customsNumber) {
        this.customsNumber = customsNumber;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    public char getExpImp() {
        return expImp;
    }

    public void setExpImp(char expImp) {
        this.expImp = expImp;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public char getNoArrReceipt() {
        return noArrReceipt;
    }

    public void setNoArrReceipt(char noArrReceipt) {
        this.noArrReceipt = noArrReceipt;
    }

    public char getNoDlvReceipt() {
        return noDlvReceipt;
    }

    public void setNoDlvReceipt(char noDlvReceipt) {
        this.noDlvReceipt = noDlvReceipt;
    }

    public int getNrtgTimeStamp() {
        return nrtgTimeStamp;
    }

    public void setNrtgTimeStamp(int nrtgTimeStamp) {
        this.nrtgTimeStamp = nrtgTimeStamp;
    }

    public String getPosColumn() {
        return posColumn;
    }

    public void setPosColumn(String posColumn) {
        this.posColumn = posColumn;
    }

    public String getPosLevel() {
        return posLevel;
    }

    public void setPosLevel(String posLevel) {
        this.posLevel = posLevel;
    }

    public String getPosRow() {
        return posRow;
    }

    public void setPosRow(String posRow) {
        this.posRow = posRow;
    }

    public boolean isPrefer() {
        return prefer;
    }

    public void setPrefer(boolean prefer) {
        this.prefer = prefer;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShcList() {
        return shcList;
    }

    public void setShcList(String shcList) {
        this.shcList = shcList;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public int getStlength() {
        return stlength;
    }

    public void setStlength(int stlength) {
        this.stlength = stlength;
    }

    public Date getStockTimeStamp() {
        return stockTimeStamp;
    }

    public void setStockTimeStamp(Date stockTimeStamp) {
        this.stockTimeStamp = stockTimeStamp;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public boolean isUld() {
        return uld;
    }

    public void setUld(boolean uld) {
        this.uld = uld;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"code");
    }
}
