/*
 * Product.java
 *
 * Created on Nov 25, 2007, 3:00:04 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.KnowledgeBase;
import bean.Product;
import template.*;
import template.screen.TemplateTabPage;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"code","productName","barcode","productType"}, showChart=true, showFiles=true)
@Displays({
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
        @ChildRecord(title="KnowledgeBase", entity = KnowledgeBase.class, sql = "SELECT a FROM KnowledgeBase a WHERE a.productCode='${code}'", fieldMapping={"code","productCode"})
    },
    info={
        @ParentAddInfo(title="Inventory Info", 
            displayFields={"unitOfMeasure","pricePerUnit","totalStock","consigned","committed","attrition",
                "onHand","reOrderLevel","onOrder","booked","transfer","inTransition","inProcess","allocated",
                "average","location","nextOrderCount","nextOrderSupplierId","nextOrderDate","isProcessed"})
}
)
public class ProductExt extends Product {
}
