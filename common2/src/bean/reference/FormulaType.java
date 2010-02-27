/*
 * FormulaType.java
 * 
 * Created on Jul 18, 2008, 6:04:18 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import service.util.AbstractIBean;
import util.ScriptRunner;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Entokwaa
 */
@Entity
@Table(name = "ComboTypes")
@UITemplate(gridCount = 4, columnSearch = {"type", "description", "formula"})
@DiscriminatorValue("FORMULA")
@Displays({
        @Display(name="code"),
        @Display(name="description"),
        @Display(name="formula")
})
public class FormulaType extends ComboTypes implements Serializable {
    @Column(name = "formula", length = 4000)
    public String formula;

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public double getCalculatedAmount(Object param) {
        Logger.getLogger("global").info(formula);
        Object obj = ScriptRunner.runGroovy(formula, "Param", param);
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            Logger.getLogger("global").log(Level.SEVERE, code, e);
        }
        return 0;
    }

    public static double getCalculatedAmount(String type, String code, Object param) {
        FormulaType form = (FormulaType) AbstractIBean.firstRecord("SELECT a FROM FormulaType a WHERE a.cod='"+code+"'");
        if (form == null) {
            form = new FormulaType();
            form.setCode(code);
            form.setDescription(code);
            form.save();
        }
        return form.getCalculatedAmount(param);
    }
}
