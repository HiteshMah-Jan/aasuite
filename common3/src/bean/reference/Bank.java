/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Bank")
@UITemplate(showChart=true, template=TemplateTabSinglePage.class, title="Bank Table", gridCount = 4, columnSearch = {"type", "value"})
@Displays({
    @Display(name="type", label="Name"),
    @Display(name="value", label="Address")
})
@ChildRecords({
    @ChildRecord(template=ChildTemplateListOnly.class, entity=BankAccount.class, sql="SELECT a FROM BankAccount a WHERE a.bankName='${type}'", title="Bank Accounts")
})
public class Bank extends ReferenceType implements Serializable {
    @Id
    @Column(name = "type", nullable = false)
    public String type;
    @Column(name = "value")
    public String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
