/*
 * Department.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.reference.Department;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(gridCount = 4, columnSearch = {"type", "value"})
@Displays({
    @Display(name="type"),
    @Display(name="value")
})
public class DepartmentExt extends Department {
    public static void main(String[] args) {
        view(DepartmentExt.class);
    }
}
