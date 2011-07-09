/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Student;
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
        
        @Display(name="schoolBusNumber",label="School Bus Number          ",gridFieldWidth=3,width=350),
        @Display(name="busOperator",gridFieldWidth=3,width=350),
        @Display(name="busOperatorTelephoneNumber",label="Bus Operator Tel. #",gridFieldWidth=3,width=350)}
        
//        @Display(name="homeType",gridFieldWidth=3),
//        @Display(name="languagesAtHome",gridFieldWidth=3),
//        @Display(name="facilitiesForHomeStudy",gridFieldWidth=3),
//        @Display(name="tutorAtHome",gridFieldWidth=3),
//        @Display(name="obstacleToStudy",gridFieldWidth=3),
//        @Display(name="relativeAtHome",gridFieldWidth=3),
//        @Display(name="schoolWorkSup1",gridFieldWidth=3),
//        @Display(name="schoolWorkSup2",gridFieldWidth=3),
//        @Display(name="schoolWorkSup3",gridFieldWidth=3),
//        @Display(name="schoolWorkSup4",gridFieldWidth=3)
        
       
)

public class BusOperatorExt extends Student {

}
