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
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(template=TemplateTabSinglePage.class,gridCount = 6, columnSearch = {"supplierClass", "companyName","contactPerson","companyTelephoneNumber"},criteriaSearch={"supplierClass","companyName"})
@Displays({
    @Display(name="supplierClass", type="Label"),
        @Display(name="companyName", type = "Label"),
        @Display(name="companyAddress",type = "Label"),
        @Display(name="companyTelephoneNumber",label = "Company tel #",type = "Label"),
        @Display(name="contactPerson",type = "Label"),
        @Display(name="shortName",type = "Label"),
        @Display(name="email",type = "Label"),
        @Display(name="contactNumber",type = "Label"),
        @Display(name="contactNumber1",type = "Label"),
        @Display(name="address1",type = "Label"),
        @Display(name="im1",type = "Label")
})
@ChildRecords(value = {
    @ChildRecord(template=ChildTemplateListOnly.class,fieldMapping = {"personId", "supplierId"}, entity = SupplierProduct.class, sql = "SELECT a FROM SupplierProduct a WHERE a.supplierId=${personId}"),
    @ChildRecord(template=ChildTemplateListOnly.class,fieldMapping = {"personId", "purchaseFrom"}, entity = PurchaseOrder.class, sql = "SELECT a FROM PurchaseOrder a WHERE a.purchseFrom=${personId}")
    
})

public class SupplierExt extends Supplier {
    public static void main(String[] args) {
        view(SupplierExt.class);
    }
}
