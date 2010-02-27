/*
 * BenefitType.java
 *
 * Created on Feb 13, 2008, 8:28:16 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import template.Display;
import template.Displays;
import template.UITemplate;
import util.DBClient;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "CompensationType")
@DiscriminatorValue("BENEFIT")
@UITemplate(canBackup=true,template=template.screen.TemplateTabPage.class, gridCount = 4, columnSearch = {"code"})
@Displays({
    @Display(name="code"),
    @Display(name="category",type="Combo",modelCombo={"ALLOWANCE","HMO","OTHERS","TAX EXEMPTION"}),
    @Display(name="description",gridFieldWidth=3,width=500)
})
@template.ChildRecords({
       @template.ChildRecord(title="Config Table", entity = DeductionConfigTable.class, fieldMapping={"code","code"}, sql = "SELECT a FROM DeductionConfigTable a WHERE a.code='${code}'")
})
public class BenefitType extends CompensationType implements Serializable {
    public static BenefitType getBenefit(String benefit, String category) {
        BenefitType ben = (BenefitType) DBClient.getFirstRecord("SELECT a FROM BenefitType a WHERE a.code='"+benefit+"'");
        if (ben==null) {
            ben = new BenefitType();
            ben.code = benefit;
            ben.description = benefit;
            ben.category = category;
            ben.save();
        }
        return ben;
    }
}
