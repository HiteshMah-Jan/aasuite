/*
 * ProductHistory.java
 *
 * Created on Dec 11, 2007, 7:28:01 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "ProductHistory")
@UITemplate(template = TemplateDefault.class, gridCount = 6, columnSearch = {"productName","pricePerUnit","totalStock","allocated","reOrderLevel"})
@Displays({
        @Display(name="seq"),
        @Display(name="code"),
        @Display(name="productName"),
        @Display(name="pricePerUnit"),
        @Display(name="unitOfMeasurement"),
        @Display(name="totalStock"),
        @Display(name="allocated"),
        @Display(name="reOrderLevel")

//        @Display(name="location"),
//        @Display(name="consigned"),
//        @Display(name="committed"),
//        @Display(name="attrition"),
//        @Display(name="onHand"),
//        @Display(name="onOrder"),
//        @Display(name="booked"),
//        @Display(name="transfer"),
//        @Display(name="inTransition"),
//        @Display(name="inProcess"),
//        @Display(name="average"),
//        @Display(name="remarks")
})
public class ProductHistory extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "productName", nullable = false)
    public String productName;
    @Column(name = "pricePerUnit")
    public double pricePerUnit;
    @Column(name = "unitOfMeasurement")
    public String unitOfMeasurement;
    @Column(name = "location")
    public String location;
    @Column(name = "totalStock")
    public int totalStock;
    @Column(name = "consigned")
    public int consigned;
    @Column(name = "committed")
    public int committed;
    @Column(name = "attrition")
    public int attrition;
    @Column(name = "onHand")
    public int onHand;
    @Column(name = "reOrderLevel")
    public int reOrderLevel;
    @Column(name = "onOrder")
    public int onOrder;
    @Column(name = "booked")
    public int booked;
    @Column(name = "transfer")
    public int transfer;
    @Column(name = "inTransition")
    public int inTransition;
    @Column(name = "inProcess")
    public int inProcess;
    @Column(name = "allocated")
    public int allocated;
    @Column(name = "average")
    public double average;
    @Column(name = "remarks")
    public String remarks;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public int getConsigned() {
		return consigned;
	}

	public void setConsigned(int consigned) {
		this.consigned = consigned;
	}

	public int getCommitted() {
		return committed;
	}

	public void setCommitted(int committed) {
		this.committed = committed;
	}

	public int getAttrition() {
		return attrition;
	}

	public void setAttrition(int attrition) {
		this.attrition = attrition;
	}

	public int getOnHand() {
		return onHand;
	}

	public void setOnHand(int onHand) {
		this.onHand = onHand;
	}

	public int getReOrderLevel() {
		return reOrderLevel;
	}

	public void setReOrderLevel(int reOrderLevel) {
		this.reOrderLevel = reOrderLevel;
	}

	public int getOnOrder() {
		return onOrder;
	}

	public void setOnOrder(int onOrder) {
		this.onOrder = onOrder;
	}

	public int getBooked() {
		return booked;
	}

	public void setBooked(int booked) {
		this.booked = booked;
	}

	public int getTransfer() {
		return transfer;
	}

	public void setTransfer(int transfer) {
		this.transfer = transfer;
	}

	public int getInTransition() {
		return inTransition;
	}

	public void setInTransition(int inTransition) {
		this.inTransition = inTransition;
	}

	public int getInProcess() {
		return inProcess;
	}

	public void setInProcess(int inProcess) {
		this.inProcess = inProcess;
	}

	public int getAllocated() {
		return allocated;
	}

	public void setAllocated(int allocated) {
		this.allocated = allocated;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
