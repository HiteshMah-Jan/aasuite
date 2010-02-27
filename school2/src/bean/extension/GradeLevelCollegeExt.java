/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.*;
import template.screen.TemplateTabPage;
import util.DBClient;
import bean.admin.AppConfig;
import bean.reference.Course;
import bean.reference.GradeLevel;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(columnSearch={"code", "course"}, gridCount=4, title="Level", select="SELECT a FROM GradeLevel a WHERE a.college=true ")
@Displays({
       // @Display(name="schoolYear"),
        @Display(name="code"),
        @Display(name="course", type="PopSearch", linktoBean=Course.class)
//        @Display(name="tuitionFee"),
//        @Display(name="otherFee"),
//        
//        @Display(name = "miscFee"),
//        @Display(name = "registrationFee", addInfoOnly=true),
//        @Display(name = "materialsFee", addInfoOnly=true),
//        @Display(name = "idFee", addInfoOnly=true),
//        @Display(name = "medicalDentalFee", addInfoOnly=true),
//        @Display(name = "libraryFee", addInfoOnly=true),
//        @Display(name = "laboratoryFee", addInfoOnly=true),
//        @Display(name = "audioVisualFee", addInfoOnly=true),
//        @Display(name = "athleticFee", addInfoOnly=true),
//        @Display(name = "insuranceFee", addInfoOnly=true),
//        @Display(name = "computerFee", addInfoOnly=true),
//        @Display(name = "handbookFee", addInfoOnly=true),
//        @Display(name = "diplomaFee", addInfoOnly=true),
//        @Display(name = "annualFee", addInfoOnly=true)
})
//@DisplayGroups({
//    @DisplayGroup(title="Misc. Breakdown", 
//        fields={"registrationFee","materialsFee","idFee","medicalDentalFee","libraryFee","laboratoryFee",
//            "audioVisualFee","athleticFee","insuranceFee","computerFee","handbookFee","diplomaFee","annualFee"})
//})
public class GradeLevelCollegeExt extends GradeLevel {
	public GradeLevelCollegeExt() {
		college = true;
	}

	@Override
	public String addWhere() {
		return " WHERE a.college=true ";
	}
	
	@Override
	public String popupSearch(String criteria) {
		return "SELECT a FROM GradeLevel a "+addWhere();
	}

	@Override
	public void save() {
		college = true;
		super.save();
	}
	
}
