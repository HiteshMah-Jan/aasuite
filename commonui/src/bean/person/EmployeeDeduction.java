/*
 * Deduction.java
 *
 * Created on Oct 26, 2007, 9:34:49 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.hr.Employee;
import bean.reference.DeductionType;
import java.io.Serializable;
import javax.persistence.*;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "EmployeeDeduction")
@UITemplate(template = TemplateDefault.class, gridCount = 2, columnSearch = {"seq", "deductionCode"})
@Displays({
       // @Display(name="seq"),
        @Display(name="deductionCode", type = "PopSearch", linktoBean = DeductionType.class, width = 250)
})
public class EmployeeDeduction extends PersonAttribute implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;

    @Override
    public int getPersonId() {
        return personId;
    }

    @Override
    public Integer getSeq() {
        return seq;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Column(name = "deductionCode", nullable = false)
    public String deductionCode;

    @Override
    public String toString() {
        return deductionCode;
    }

    public java.lang.String getDeductionCode() {
        return deductionCode;
    }

    public void setDeductionCode(java.lang.String deductionCode) {
        this.deductionCode = deductionCode;
    }

    public static void addEmployeeBenefit(Employee emp, DeductionType dec) {
        if (emp == null || dec == null) {
            return;
        }
        EmployeeDeduction deduction = new EmployeeDeduction();
        deduction.setDeductionCode(dec.getCode());
        deduction.setPersonId(emp.getPersonId());
        deduction.save();
    }

    public DeductionType getDeductionTypeObj() {
        if (deductionCode == null || deductionCode.isEmpty()) {
            return null;
        }
        return (DeductionType) DeductionType.extractObject(DeductionType.class.getSimpleName(),deductionCode);
    }
}
