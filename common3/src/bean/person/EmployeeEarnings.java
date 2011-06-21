/*
 * Earnings.java
 * 
 * Created on Oct 26, 2007, 9:34:50 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.Employee;
import java.io.Serializable;
import javax.persistence.*;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import util.BeanUtil;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "EmployeeEarnings")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"earningsId", "basicSalary", "employeeId"})
@Displays({
        @Display(name="earningsId"),
        @Display(name="employeeId", gridFieldWidth = 3, label = "Employee", width = -1, type = "PopSearch", linktoBean=Employee.class),
        @Display(name="basicSalary"),
        @Display(name="allowance"),
        @Display(name="overtimePay"),
        @Display(name="regularHoliday"),
        @Display(name="specialHoliday"),
        @Display(name="grossPay")
})
public class EmployeeEarnings extends PersonAttribute implements Serializable {
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

    @Column(name = "basicSalary", nullable = false)
    public double basicSalary;
    @Column(name = "allowance")
    public Double allowance;
    @Column(name = "overtimePay")
    public Double overtimePay;
    @Column(name = "regularHoliday")
    public Double regularHoliday;
    @Column(name = "specialHoliday")
    public Double specialHoliday;
    @Column(name = "grossPay")
    public Double grossPay;

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public Double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(Double overtimePay) {
        this.overtimePay = overtimePay;
    }

    public Double getRegularHoliday() {
        return regularHoliday;
    }

    public void setRegularHoliday(Double regularHoliday) {
        this.regularHoliday = regularHoliday;
    }

    public Double getSpecialHoliday() {
        return specialHoliday;
    }

    public void setSpecialHoliday(Double specialHoliday) {
        this.specialHoliday = specialHoliday;
    }

    public Double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(Double grossPay) {
        this.grossPay = grossPay;
    }

    @Override
    public String toString() {
        return BeanUtil.concat(basicSalary);
    }

    public double getSumEarning() {
        double grossPay = basicSalary + allowance + overtimePay + regularHoliday + specialHoliday;
        return grossPay;
    }
}

