/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Student;
import bean.reference.Course;
import bean.reference.Section;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(showChart=true,select="SELECT a FROM Student a WHERE a.parentId=0 ", template=TemplateTabPage.class, gridCount = 4, columnSearch = {"studentNumber","lastName","firstName","section","course"}, criteriaSearch = {"studentNumber","lastName","firstName","section"}, showImages=true, showFiles=true, orderBy="a.lastName", imageInsideForm=true, imageEditable=false)
@Displays({
        @Display(name="schoolYear",gridFieldWidth=4,width=-1),
        @Display(name="course", label="Grade", type="PopSearch", linktoBean=Course.class, mandatory=true,gridFieldWidth=3,width=-1),
        @Display(name="section", type="PopSearch", linktoBean=Section.class,gridFieldWidth=3,width=-1),
        @Display(name="toSection", type="PopSearch", linktoBean=Section.class,gridFieldWidth=3,width=-1),

        @Display(name="im1", label="Class Number",width=70),
        @Display(name="status", type="Combo", modelCombo={"NEW"},width=70),
        @Display(name="studentNumber", button="Change",gridFieldWidth=3,width=150),
        @Display(name="gender", label="Sex",type="Combo", modelCombo={"MALE","FEMALE"},width=70),
        @Display(name="department", label="Branch",width=70),
        
        @Display(name="lastName", labelTop=true,width=150),
        @Display(name="firstName", labelTop=true,width=150),
        @Display(name="middleInitial",label="Middle Name", labelTop=true,width=150),
        
        @Display(name="address",gridFieldWidth=3,width=-1),
        @Display(name="barangay",gridFieldWidth=3,width=-1),
        @Display(name="cityProvince", label="City/Municipality",width=120),
        @Display(name="zipCode",width=-1),
        @Display(name="provincialAddress", label="Province",gridFieldWidth=3,width=-1),
        @Display(name="contactNumber1",width=120),
        @Display(name="mobilePhone",width=120),
        @Display(name="email",gridFieldWidth=3,width=-1),
        
        @Display(name="guardianName", label="Person to Notify",width=150),
        @Display(name="guardianName2", hideLabel=true,width=150),
        @Display(name="guardianContactNumber", label="Contact No.",width=150),
        @Display(name="guardianContactNumber2", hideLabel=true,width=150),
        @Display(name="guardianRelationship", label="Relationship",width=150),
        @Display(name="guardianRelationship2", hideLabel=true,width=150)
})
@DisplayGroups({
    @DisplayGroup(gridCount=6, title="Name", fields={"lastName","firstName","middleInitial"}),
    @DisplayGroup(gridCount=4, title="Address", fields={"address","barangay","cityProvince","zipCode","provincialAddress","contactNumber1","mobilePhone","email"}),
    @DisplayGroup(gridCount=4, title="In Case of Emergency", fields={"guardianName","guardianName2","guardianContactNumber","guardianContactNumber2","guardianRelationship","guardianRelationship2"})
})
public class StudentPersonalInfoExt extends Student {
    public static void main(String[] args) {
        view(StudentPersonalInfoExt.class);
    }
}
