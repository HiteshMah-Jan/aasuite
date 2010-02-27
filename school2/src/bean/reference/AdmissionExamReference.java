/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import java.util.Vector;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import service.util.AbstractIBean;
import service.util.CallService;
import service.ReturnStruct;
import service.ParamStruct;
import template.*;
import util.DateUtil;
import util.DBClient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "AdmissionExamReference")
@UITemplate(template=template.screen.TemplateSinglePage.class,
    criteriaSearch={"schoolYear", "gradeLevel"},
    columnSearch={"schoolYear", "gradeLevel", "elaItemCount", "mathItemCount","sciItemCount","pracItemCount"}, gridCount=8)
@Displays({
        @Display(name="schoolYear",gridFieldWidth = 3),
        @Display(name="gradeLevel",gridFieldWidth = 3),
        @Display(name="elaItemCount"),
        @Display(name="mathItemCount"),
        @Display(name="sciItemCount"),
        @Display(name="pracItemCount")
})
@ActionButtons({
    @ActionButton(name = "btnGenerateForSchoolYear", label = "Generate Reference")
})
public class AdmissionExamReference extends AbstractIBean implements Serializable  {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "admissionId")
    public int admissionId;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "gradeLevel")
    public String gradeLevel;
    @Column(name = "elaItemCount")
    public double elaItemCount;
    @Column(name = "mathItemCount")
    public double mathItemCount;
    @Column(name = "sciItemCount")
    public double sciItemCount;
    @Column(name = "pracItemCount")
    public double pracItemCount;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "schoolYear", "gradeLevel");
    }
    
    

    public double getPracItemCount() {
        return pracItemCount;
    }

    public void setPracItemCount(double pracItemCount) {
        this.pracItemCount = pracItemCount;
    }

    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public double getElaItemCount() {
        return elaItemCount;
    }

    public void setElaItemCount(double elaItemCount) {
        this.elaItemCount = elaItemCount;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public double getMathItemCount() {
        return mathItemCount;
    }

    public void setMathItemCount(double mathItemCount) {
        this.mathItemCount = mathItemCount;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public double getSciItemCount() {
        return sciItemCount;
    }

    public void setSciItemCount(double sciItemCount) {
        this.sciItemCount = sciItemCount;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public static void generate(String sy) {
        CallService.callService(sy, 1, AdmissionExamReference.class.getName());
    }

    @Override
    public ReturnStruct callService(ParamStruct param) {
        String sy = (String) param.getData();
        create(sy, "N1");
        create(sy, "N2");
        create(sy, "K1");
        create(sy, "K2");
        create(sy, "P1");
        create(sy, "G1");
        create(sy, "G2");
        create(sy, "G3");
        create(sy, "G4");
        create(sy, "G5");
        create(sy, "G6");
        create(sy, "G7");
        create(sy, "H1");
        create(sy, "H2");
        create(sy, "H3");
        create(sy, "H4");
        return null;
    }

    public static void create(String sy, String grd) {
        boolean b = DBClient.exist("SELECT a FROM AdmissionExamReference a WHERE a.schoolYear='"+sy+"' AND a.gradeLevel='"+grd+"'");
        if (b) return;
        
        AdmissionExamReference ref = new AdmissionExamReference();
        ref.schoolYear = sy;
        ref.gradeLevel = grd;
        ref.save();
    }
}
