/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.training;

import bean.hr.Employee;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Training")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"seq","fieldInt","fieldStr","fieldType","fieldEmployee","fieldDate"})
@Displays({
//        @Display(name="seq", type="Label"),
        @Display(name="fieldInt"),
        @Display(name="fieldStr"),
        
        @Display(name="fieldType", type="Combo", modelCombo={"TYPE1","TYPE2","TYPE3"}),
        @Display(name="fieldEmployee", type="PopSearch", linktoBean=Employee.class),
        @Display(name="fieldDate"),
        
        @Display(name="fieldOther1", addInfoOnly=true),
        @Display(name="fieldOther2", addInfoOnly=true),
        @Display(name="fieldOther3", addInfoOnly=true),
        @Display(name="fieldOther4", addInfoOnly=true),
        @Display(name="fieldOther5", addInfoOnly=true)
})
@ActionButtons({
    @ActionButton(name="btnActionButton1", label="Action Button 1", parentOnly=false),
    @ActionButton(name="btnActionButton2", label="Action Button 2", parentOnly=false),
    @ActionButton(name="btnActionButton3", label="Action Button 3", parentOnly=false)
})
@ChildRecords(
    value={
       @ChildRecord(title="Sub Training", entity = SubTraining.class, sql = "SELECT a FROM SubTraining a WHERE a.trainingId = ${seq}", fieldMapping={"seq","trainingId"})
 },
    info={
        @ParentAddInfo(title="Other Information", displayFields={"fieldOther1","fieldOther2","fieldOther3","fieldOther4","fieldOther5"})
}
)
@Reports({
    @template.Report(reportFile="Training", reportTitle="Training", reportSql="${seq}")
})
@DisplayGroups({
    @DisplayGroup(title="Grouped Fields", gridCount=6, fields={"fieldType","fieldEmployee","fieldDate"})
}) 
public class Training extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "fieldInt")
    public int fieldInt;
    @Column(name = "fieldStr")
    public String fieldStr;
    @Column(name = "fieldType")	//combo
    public String fieldType;
    @Column(name = "fieldEmployee")	//pop search
    public int fieldEmployee;
    @Column(name = "fieldDate")
    @Temporal(value = TemporalType.DATE)
    public Date fieldDate = constants.Constants.useDate;

    @Column(name = "fieldOther1")
    public String fieldOther1;
    @Column(name = "fieldOther2")
    public String fieldOther2;
    @Column(name = "fieldOther3")
    public String fieldOther3;
    @Column(name = "fieldOther4")
    public String fieldOther4;
    @Column(name = "fieldOther5")
    public String fieldOther5;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Date getFieldDate() {
        return fieldDate;
    }

    public void setFieldDate(Date fieldDate) {
        this.fieldDate = fieldDate;
    }

    public int getFieldEmployee() {
        return fieldEmployee;
    }

    public void setFieldEmployee(int fieldEmployee) {
        this.fieldEmployee = fieldEmployee;
    }

    public int getFieldInt() {
        return fieldInt;
    }

    public void setFieldInt(int fieldInt) {
        this.fieldInt = fieldInt;
    }

    public String getFieldOther1() {
        return fieldOther1;
    }

    public void setFieldOther1(String fieldOther1) {
        this.fieldOther1 = fieldOther1;
    }

    public String getFieldOther2() {
        return fieldOther2;
    }

    public void setFieldOther2(String fieldOther2) {
        this.fieldOther2 = fieldOther2;
    }

    public String getFieldOther3() {
        return fieldOther3;
    }

    public void setFieldOther3(String fieldOther3) {
        this.fieldOther3 = fieldOther3;
    }

    public String getFieldOther4() {
        return fieldOther4;
    }

    public void setFieldOther4(String fieldOther4) {
        this.fieldOther4 = fieldOther4;
    }

    public String getFieldOther5() {
        return fieldOther5;
    }

    public void setFieldOther5(String fieldOther5) {
        this.fieldOther5 = fieldOther5;
    }

    public String getFieldStr() {
        return fieldStr;
    }

    public void setFieldStr(String fieldStr) {
        this.fieldStr = fieldStr;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    
    
}
