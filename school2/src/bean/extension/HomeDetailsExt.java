/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;


import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import bean.Student;


/**
 *
 * @author Charliemagne Mark
 */
@UITemplate(showChart=false,select="SELECT a FROM Student a WHERE a.parentId=0 ", template=TemplateTabPage.class, gridCount = 4, columnSearch = {"studentNumber","lastName","firstName","section","course"}, criteriaSearch = {"studentNumber","lastName","firstName","section"}, showImages=false, showFiles=true, orderBy="a.lastName")
@Displays({
//        @Display(name="schoolLastAttended",label="Previous School",gridFieldWidth=3,width=-1),
//        @Display(name="yearStartedInThisSchool",label="Yr.Started In This School",width=100),
//        @Display(name="yearsInThisSchool",label="Yrs. Studying Here",width=80),
//        @Display(name="yearsLeftInThisSchool",label="Yrs. Remaining",gridFieldWidth=3,width=100),
//
//        @Display(name="citizenship",label="Nationality",gridFieldWidth=3,width=-1),
//        @Display(name="birthDate",width=100),
//        @Display(name="age",width=80),
//        @Display(name="placeOfBirth",label="Birthplace",gridFieldWidth=3,width=-1),
//        @Display(name="religion",gridFieldWidth=3,width=-1),
//        @Display(name="livesWith",gridFieldWidth=3,width=-1),
//        
//        @Display(name="schoolBusNumber",gridFieldWidth=3,width=300),
//        @Display(name="busOperator",gridFieldWidth=3,width=-1),
//        @Display(name="busOperatorTelephoneNumber",gridFieldWidth=3,width=-1),
        
        @Display(name="homeType",gridFieldWidth=3,width=350),
        @Display(name="languagesAtHome",gridFieldWidth=3,width=-1),
        @Display(name="facilitiesForHomeStudy",label="Facilities For Home Study   ",gridFieldWidth=3,width=-1),
        @Display(name="tutorAtHome",gridFieldWidth=3,width=-1),
        @Display(name="obstacleToStudy",gridFieldWidth=3,width=-1),
        @Display(name="relativeAtHome",gridFieldWidth=3,width=-1),
        @Display(name="schoolWorkSup1",label="School Work Sup-1",gridFieldWidth=3,width=-1),
        @Display(name="schoolWorkSup2",label="School Work Sup-2",gridFieldWidth=3,width=-1),
        @Display(name="schoolWorkSup3",label="School Work Sup-3",gridFieldWidth=3,width=-1),
        @Display(name="schoolWorkSup4",label="School Work Sup-4",gridFieldWidth=3,width=-1)
        
       
})

public class HomeDetailsExt extends Student {

}
