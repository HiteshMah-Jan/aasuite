/*
 * Department.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.Product;
import bean.PurchaseOrder;
import bean.Supplier;
import bean.SupplierProduct;
import bean.reference.Department;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"supplierId", "productCode"})
@Displays({
    @Display(name = "supplierProductId"),
    @Display(name = "supplierId", type = "PopSearch", linktoBean = Supplier.class),
    @Display(name = "productCode", type = "PopSearch", linktoBean = Product.class),
    @Display(name = "minPricePerUnit"),
    @Display(name = "maxPricePerUnit")
})
@ChildRecords({
    @ChildRecord(fieldMapping = {"code", "productCode"}, entity = Product.class, sql = "SELECT a FROM Product a WHERE a.code=${productCode}")
})
public class SupplierProductExt extends SupplierProduct {

    public static void main(String[] args) {
        view(SupplierProductExt.class);
    }
}
