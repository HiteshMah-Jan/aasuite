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
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "SubTraining")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"fieldInt","fieldStr","fieldType","fieldEmployee","fieldDate"})
@Displays({
        @Display(name="trainingId", type="Label"),
        @Display(name="fieldInt"),
        @Display(name="fieldStr"),
        @Display(name="fieldType", type="Combo", modelCombo={"TYPE1","TYPE2","TYPE3"}),
        @Display(name="fieldEmployee", type="PopSearch", linktoBean=Employee.class),
        @Display(name="fieldDate")
})
@ActionButtons({
    @ActionButton(name="btnActionButton1", label="Sub Action Button 1", parentOnly=false),
    @ActionButton(name="btnActionButton2", label="Sub Action Button 2", parentOnly=false),
    @ActionButton(name="btnActionButton3", label="Sub Action Button 3", parentOnly=false)
})
public class SubTraining extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "trainingId", nullable = false)
    public int trainingId;
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

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }
    
    
}
