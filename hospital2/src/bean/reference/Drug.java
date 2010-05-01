/*
 * Drug.java
 *
 * Created on Oct 26, 2007, 9:34:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import bean.*;
import bean.extension.PatientUltrasoundObExt;
import bean.person.PatientAllergy;
import bean.person.PatientPregnancyHistory;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.ChildRecord;
import template.ChildRecords;
import util.DBClient;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Product")
@DiscriminatorValue(value = "DRUG")
@UITemplate(template=TemplateTabPage.class, gridCount = 4, columnSearch = {"genericName","productName"})
@Displays({
        @Display(name="genericName"),
        @Display(name="remarks"),
        @Display(name="code"),
        @Display(name="productName"),
        @Display(name="barcode"),
        @Display(name="productType"),
        @Display(name="unitOfMeasure", addInfoOnly=true),
        @Display(name="pricePerUnit", addInfoOnly=true),
        @Display(name="totalStock", addInfoOnly=true),
        @Display(name="consigned", addInfoOnly=true),
        @Display(name="committed", addInfoOnly=true),
        @Display(name="attrition", addInfoOnly=true),
        @Display(name="onHand", addInfoOnly=true),
        @Display(name="reOrderLevel", addInfoOnly=true),
        @Display(name="onOrder", addInfoOnly=true),
        @Display(name="booked", addInfoOnly=true),
        @Display(name="transfer", addInfoOnly=true),
        @Display(name="inTransition", addInfoOnly=true),
        @Display(name="inProcess", addInfoOnly=true),
        @Display(name="allocated", addInfoOnly=true),
        @Display(name="average", addInfoOnly=true),
        @Display(name="location", addInfoOnly=true),
        @Display(name="nextOrderCount", addInfoOnly=true),
        @Display(name="nextOrderSupplierId", addInfoOnly=true),
        @Display(name="nextOrderDate", addInfoOnly=true),
        @Display(name="isProcessed", addInfoOnly=true)
})
@ChildRecords(
    value={
        @ChildRecord(title="Delivery", entity = PharmacyInventoryDelivery.class, sql = "SELECT a FROM PharmacyInventoryDelivery a WHERE a.drugCode=${code}", fieldMapping={"code","drugCode"}),
        @ChildRecord(title="Expiry", entity = PharmacyInventoryExpired.class, sql = "SELECT a FROM PharmacyInventoryExpired a WHERE a.drugCode=${code}", fieldMapping={"code","drugCode"}),
        @ChildRecord(title="Stocks", entity = PharmacyInventoryStocks.class, sql = "SELECT a FROM PharmacyInventoryStocks a WHERE a.drugCode=${code}", fieldMapping={"code","drugCode"})
    },
    info={
        @ParentAddInfo(title="Inventory Info", 
            displayFields={"unitOfMeasure","pricePerUnit","totalStock","consigned","committed","attrition",
                "onHand","reOrderLevel","onOrder","booked","transfer","inTransition","inProcess","allocated",
                "average","location","nextOrderCount","nextOrderSupplierId","nextOrderDate","isProcessed"})
}
)
public class Drug extends Product implements Serializable {
    @Column(name = "genericName", length = 200)
    public String genericName;
    @Column(name = "remarks", length = 200)
    public String remarks;

    public String getDrugName() {
        return getCode();
    }

    public void setDrugName(String drugName) {
        setCode(drugName);
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public static double getDrugPrice(String drugName, int count) {
        Drug drug = (Drug) DBClient.getFirstRecord("SELECT a FROM Drug a WHERE a.code='" + drugName + "'");
        if (drug == null) {
            return 0;
        }
        return drug.getPricePerUnit() + count;
    }

    public static double getDrugPrice(String drugName) {
        Drug drug = (Drug) DBClient.getFirstRecord("SELECT a FROM Drug a WHERE a.code='" + drugName + "'");
        if (drug == null) {
            return 0;
        }
        return drug.getPricePerUnit();
    }
}
