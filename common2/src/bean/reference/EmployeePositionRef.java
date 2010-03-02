/*
 * Product.java
 *
 * Created on Nov 25, 2007, 3:00:04 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "EmployeePositionRef")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 2,
    columnSearch = {"code","position"})
@Displays({
        @Display(name="code",width=200),
        @Display(name="position",width=200)
})
public class EmployeePositionRef extends AbstractIBean {
    @Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "position", nullable = false)
    public String position;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code", "position");
    }

    public static final String ANY = "ANY";

    @Override
    public void setupIndex() {
        //create a record ANY.
    }


}
