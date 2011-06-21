/*
 * Product.java
 *
 * Created on Nov 25, 2007, 3:00:04 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.inventory;

import bean.Person;
import bean.hr.ProductEmployeeSupply;
import bean.reference.ProductCategory;
import bean.sales.CustomerPricing;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabSinglePage;
import util.BeanUtil;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Product")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4,criteriaSearch = {"code","productName", "productType"}, columnSearch = {"productName", "productType"})
@DiscriminatorColumn(name = "productType", discriminatorType = DiscriminatorType.STRING)
 @DisplayGroups({
    @DisplayGroup(title = "Item Count", gridCount = 8, fields = {"totalStock", "allocated", "damaged","reOrderLevel"})
    })
 

@Displays({
        @Display(name="code",width=200,gridFieldWidth=3),
        @Display(name="productType",gridFieldWidth=3,width=-1),
        @Display(name="productCategory", type = "PopSearch", linktoBean = ProductCategory.class, gridFieldWidth = 3, width = -1),
        @Display(name="productName",gridFieldWidth=3,width=-1),
        @Display(name="barcode",gridFieldWidth=3,width=-1),
        
        @Display(name="unitOfMeasure"),
        @Display(name="pricePerUnit"),
        @Display(name="totalStock",labelTop=true),
        @Display(name="allocated",labelTop=true),
        @Display(name="damaged",labelTop=true),
        @Display(name="reOrderLevel",labelTop=true)

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
//        @Display(name="location"),
//        @Display(name="nextOrderCount"),
//        @Display(name="nextOrderSupplierId"),
//        @Display(name="nextOrderDate"),
//        @Display(name="isProcessed")
})
@ChildRecords(value = {
    @ChildRecord(title="Supplies For Employee", template=ChildTemplateListOnly.class, fieldMapping = {"code", "productCode"}, entity = ProductEmployeeSupply.class, sql = "SELECT a FROM ProductEmployeeSupply a WHERE a.productCode='${code}' ORDER BY a.supplyDate DESC"),
    @ChildRecord(title="Product History", template=ChildTemplateListOnly.class, fieldMapping = {"code", "productCode"}, entity = SupplierProduct.class, sql = "SELECT a FROM SupplierProduct a WHERE a.productCode='${code}' ORDER BY a.purchaseDate DESC")
})
        @Reports( { @template.Report(reportFile = "Inventory", reportTitle = "Inventory Report", reportSql = "") })

public class Product extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "productName", nullable = false)
    public String productName;
    @Column(name = "productCategory")
    public String productCategory;
    @Column(name = "barcode")
    public String barcode;
    @Column(name = "productType")
    public String productType;
    @Column(name = "unitOfMeasure")
    public String unitOfMeasure;
    @Column(name = "pricePerUnit")
    public double pricePerUnit;
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
    @Column(name = "location")
    public String location;
    @Column(name = "nextOrderCount")
    public int nextOrderCount;
    @Column(name = "nextOrderSupplierId")
    public int nextOrderSupplierId;
    @Column(name = "nextOrderDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date nextOrderDate;
    @Column(name = "isProcessed")
    public boolean isProcessed;
    @Column(name = "damaged")
    public int damaged;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public List getItemList() {
        return list("SELECT p FROM PurchaseOrderItem p WHERE p.product='" , code , "'");
    }

    @Override
    public String toString() {
        return productName;
    }

    public java.lang.String getCode() {
        return code;
    }

    public int getDamaged() {
        return damaged;
    }

    public void setDamaged(int damaged) {
        this.damaged = damaged;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    
    

    public void setCode(java.lang.String code) {
        this.code = code;
    }

    public java.lang.String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(java.lang.String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public java.lang.String getProductName() {
        return productName;
    }

    public void setProductName(java.lang.String productName) {
        this.productName = productName;
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

    public java.lang.String getLocation() {
        return location;
    }

    public void setLocation(java.lang.String location) {
        this.location = location;
    }

   

    public List getSuppliers() {
        return list("SELECT a FROM Supplier a, SupplierProduct b, Product c WHERE a.personId=b.supplierId AND b.productCode=c.code AND c.code='" , code , "'");
    }

    public String getNextOrderSupplier() {
        if (nextOrderSupplierId == 0) {
            return "";
        }
        Person person = (Person) AbstractIBean.firstRecord("SELECT a FROM Supplier a WHERE a.personId=" , this.nextOrderSupplierId);
        return person.toString();
    }

    public int getNextOrderCount() {
        return nextOrderCount;
    }

    public void setNextOrderCount(int nextOrderCount) {
        this.nextOrderCount = nextOrderCount;
    }

    public int getNextOrderSupplierId() {
        return nextOrderSupplierId;
    }

    public void setNextOrderSupplierId(int nextOrderSupplierId) {
        this.nextOrderSupplierId = nextOrderSupplierId;
    }

    public java.util.Date getNextOrderDate() {
        return nextOrderDate;
    }

    public void setNextOrderDate(java.util.Date nextOrderDate) {
        this.nextOrderDate = nextOrderDate;
    }

    @Override
    public String getComboDisplay() {
        if (code == null) {
            return "";
        } else {
            return code;
        }
    }

    public String getMinMaxPrice(int customerId) {
        if (customerId == 0) {
            return BeanUtil.concat(this.getPricePerUnit() , "");
        } else {
            //get min max value for customer
            CustomerPricing price = (CustomerPricing) AbstractIBean.firstRecord("SELECT a FROM CustomerPricing a WHERE a.customerId=" , customerId , " AND a.productCode='" , this.code , "'");
            if (price == null || price.seq == null || price.seq == 0) {
                return BeanUtil.concat(this.getPricePerUnit() , "");
            } else {
                return BeanUtil.concat(price.getMinPricePerUnit() , " - " , price.getMaxPricePerUnit());
            }
        }
    }

    public boolean getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

    public java.lang.String getBarcode() {
        return barcode;
    }

    public void setBarcode(java.lang.String barcode) {
        this.barcode = barcode;
    }

    public java.lang.String getProductType() {
        return productType;
    }

    public void setProductType(java.lang.String productType) {
        this.productType = productType;
    }
    public static void main(String[]args){
        view(Product.class);
    }
            
}
