/*
 * Allergy.java
 *
 * Created on Oct 26, 2007, 9:34:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "BusinessRuleService")
@UITemplate(template = TemplateSinglePage.class, gridCount = 10, 
    criteriaSearch = {"service"},
    columnSearch = {"service","oneTimeOnly","runMonth","runWeek","runDay","runTime","lastRunDate"})
@Displays({
    @Display(name="service", gridFieldWidth=9, width=-1),
    @Display(name="runMonth", width=50,type="Combo", modelCombo={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"}),
    @Display(name="runWeek", width=40, type="Combo", modelCombo={"1","2","3","4"}),
    @Display(name="runDay", width=50, type="Combo", modelCombo={"MON","TUE","WED","THU","FRI","SAT","SUN"}),
    @Display(name="runTime", type="Time"),
    @Display(name="oneTimeOnly"),
    @Display(name="param1", width=70, gridFieldWidth=9, label="Param      ", mergeFields={"param2","param3","param4","param5","param6","param7","param8","param9","param10"}),
    @Display(name="param2", width=70, hideLabel=true),
    @Display(name="param3", width=70, hideLabel=true),
    @Display(name="param4", width=70, hideLabel=true),
    @Display(name="param5", width=70, hideLabel=true),
    @Display(name="param6", width=70, hideLabel=true),
    @Display(name="param7", width=70, hideLabel=true),
    @Display(name="param8", width=70, hideLabel=true),
    @Display(name="param9", width=70, hideLabel=true),
    @Display(name="param10", width=70, hideLabel=true),
    @Display(name="description", gridFieldWidth=9, width=-1),
    @Display(name="rule", type="TextArea2", width=-1, height=150, gridFieldWidth=9)
})
@ActionButtons({
    @ActionButton(label="Test Code", name="btnTest"),
    @ActionButton(label="Append for", name="btnFor"),
    @ActionButton(label="fore", name="btnFore"),
    @ActionButton(label="if", name="btnIf"),
    @ActionButton(label="ifelse", name="btnIfElse"),
    @ActionButton(label="try", name="btnTry"),
    @ActionButton(label="print", name="btnPrint"),
    @ActionButton(label="select", name="btnSelect"),
    @ActionButton(label="selectlist", name="btnSelectList")
})
public class BusinessRuleService extends AbstractIBean {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "service", nullable = false)
    public String service;

    @Column(name = "oneTimeOnly")
    public boolean oneTimeOnly;

    @Column(name = "runMonth")
    public String runMonth;
    @Column(name = "runWeek")
    public String runWeek;
    @Column(name = "runDay")
    public String runDay;
    @Column(name = "runTime")
    public String runTime;

    @Column(name = "param1")
    public String param1;
    @Column(name = "param2")
    public String param2;
    @Column(name = "param3")
    public String param3;
    @Column(name = "param4")
    public String param4;
    @Column(name = "param5")
    public String param5;
    @Column(name = "param6")
    public String param6;
    @Column(name = "param7")
    public String param7;
    @Column(name = "param8")
    public String param8;
    @Column(name = "param9")
    public String param9;
    @Column(name = "param10")
    public String param10;
    @Column(name = "description")
    public String description;
    @Column(name = "rule", length=4000)
    public String rule;

    @Column(name = "lastRunDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date lastRunDate;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public static void main(String[] args) {
        view(BusinessRuleService.class);
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastRunDate() {
        return lastRunDate;
    }

    public void setLastRunDate(Date lastRunDate) {
        this.lastRunDate = lastRunDate;
    }

    public boolean isOneTimeOnly() {
        return oneTimeOnly;
    }

    public void setOneTimeOnly(boolean oneTimeOnly) {
        this.oneTimeOnly = oneTimeOnly;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam10() {
        return param10;
    }

    public void setParam10(String param10) {
        this.param10 = param10;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public String getParam5() {
        return param5;
    }

    public void setParam5(String param5) {
        this.param5 = param5;
    }

    public String getParam6() {
        return param6;
    }

    public void setParam6(String param6) {
        this.param6 = param6;
    }

    public String getParam7() {
        return param7;
    }

    public void setParam7(String param7) {
        this.param7 = param7;
    }

    public String getParam8() {
        return param8;
    }

    public void setParam8(String param8) {
        this.param8 = param8;
    }

    public String getParam9() {
        return param9;
    }

    public void setParam9(String param9) {
        this.param9 = param9;
    }

    public String getRunDay() {
        return runDay;
    }

    public void setRunDay(String runDay) {
        this.runDay = runDay;
    }

    public String getRunMonth() {
        return runMonth;
    }

    public void setRunMonth(String runMonth) {
        this.runMonth = runMonth;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getRunWeek() {
        return runWeek;
    }

    public void setRunWeek(String runWeek) {
        this.runWeek = runWeek;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return service;
    }

    public java.lang.Integer getSeq() {
        return seq;
    }

    public void setSeq(java.lang.Integer seq) {
        this.seq = seq;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public List<String> getAllParam() {
        List<String> lst = new ArrayList<String>();
        if (param1!=null) lst.add(param1);
        if (param2!=null) lst.add(param2);
        if (param3!=null) lst.add(param3);
        if (param4!=null) lst.add(param4);
        if (param5!=null) lst.add(param5);
        if (param6!=null) lst.add(param6);
        if (param7!=null) lst.add(param7);
        if (param8!=null) lst.add(param8);
        if (param9!=null) lst.add(param9);
        if (param10!=null) lst.add(param10);
        return lst;    
    }
}
