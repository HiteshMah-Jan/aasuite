/*
 * Deduction.java
 *
 * Created on Oct 26, 2007, 9:34:49 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.Employee;
import bean.reference.BenefitType;
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
@Table(name = "EmployeeBenefit")
@UITemplate(template = TemplateDefault.class, gridCount = 2, columnSearch = {"seq", "benefitCode"})
@Displays({
       // @Display(name="seq"),
        @Display(name="benefitCode", type = "PopSearch", linktoBean = BenefitType.class, width = 250)
})
public class EmployeeBenefit extends PersonAttribute implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;
    @Column(name = "benefitCode", nullable = false)
    public String benefitCode;

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

    @Override
    public String toString() {
        return benefitCode;
    }

    public java.lang.String getBenefitCode() {
        return benefitCode;
    }

    public void setBenefitCode(java.lang.String benefitCode) {
        this.benefitCode = benefitCode;
    }

    public static void addEmployeeBenefit(Employee emp, BenefitType ben) {
        if (emp == null || ben == null) {
            return;
        }
        EmployeeBenefit benefit = new EmployeeBenefit();
        benefit.setBenefitCode(ben.getCode());
        benefit.setPersonId(emp.getPersonId());
        benefit.save();
    }

    public BenefitType getBenefitTypeObj() {
        return (BenefitType) BenefitType.extractObject(BenefitType.class.getSimpleName(), benefitCode);
    }
}
