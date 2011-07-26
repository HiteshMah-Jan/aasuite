/*
 * Department.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import java.io.Serializable;

import template.Display;
import template.Displays;
import template.UITemplate;
import bean.reference.Subject;

/**
 *
 * @author Charliemagne Mark
 */
@UITemplate( gridCount=10, title="Subjects", 
    columnSearch={"schoolYear","subject","seq","unit","grade1","grade2","grade3","grade4","finalRating","passed"},
    criteriaSearch={"subject","grade1","grade2","grade3","grade4","finalRating"}
//	, sumFooter="4,5,6,7,8,9,10"
)
@Displays({
    @Display(name="schoolYear", width=100),
//    @Display(name="preferredSemester", width=100, label="Semester", type="Combo", modelCombo={"1","2","3"}),
    @Display(name="subject", type="PopSearch", linktoBean=Subject.class, gridFieldWidth=3, width=-1),
    @Display(name="seq", type="Label"),
    @Display(name="section", type="Label"),
    @Display(name="grade1",label="1st"),
    @Display(name="grade2",label="2nd"),
    @Display(name="grade3",label="3rd"),
    @Display(name="grade4",label="4th"),
    @Display(name="finalRating",label="Final Rating")
})

//    @Display(name="preSubject1", label="1", type = "PopSearch", linktoBean = Subject.class, width=300),
//    @Display(name="preSubject2",label="2", type = "PopSearch", linktoBean = Subject.class, width=300),
//    @Display(name="preSubject3",label="3", type = "PopSearch", linktoBean = Subject.class, width=300),
//    @Display(name="preSubject4",label="4", type = "PopSearch", linktoBean = Subject.class, width=300),
//    @Display(name="preSubject5",label="5", type = "PopSearch", linktoBean = Subject.class, width=300),
//    @Display(name="coSubject1", label="1", type = "PopSearch", linktoBean = Subject.class, width=300),
//    @Display(name="coSubject2",label="2", type = "PopSearch", linktoBean = Subject.class, width=300),
//    @Display(name="coSubject3",label="3", type = "PopSearch", linktoBean = Subject.class, width=300),
//    @Display(name="coSubject4",label="4", type = "PopSearch", linktoBean = Subject.class, width=300),
//    @Display(name="coSubject5",label="5", type = "PopSearch", linktoBean = Subject.class, width=300)

//@ActionButtons({
//    @ActionButton(name = "btnCalculateQ1", label = "Calculate Q1"),
//    @ActionButton(name = "btnCalculateQ1", label = "Q2"),
//    @ActionButton(name = "btnCalculateQ1", label = "Q3"),
//    @ActionButton(name = "btnCalculateQ1", label = "Q4"),
//    @ActionButton(name = "btnCalculateFinal", label = "Final Rating"),
//})
 //@DisplayGroups({
//    @DisplayGroup(gridCount = 2, title = "Prerequisite Subject",  fields = {
//        "preSubject1","preSubject2","preSubject3","preSubject4","preSubject5"
//    }, addInfoOnly=true),
//     @DisplayGroup(gridCount = 2, title = "Corequisite Subject",  fields = {
//        "coSubject1","coSubject2","coSubject3","coSubject4","coSubject5"
//    }, addInfoOnly=true)}
//)
public class StudentSubjectExt extends bean.person.StudentSubject implements Serializable {
    public static void main(String[] args) {
//        view(StudentSubjectExt.class);
    }
}
