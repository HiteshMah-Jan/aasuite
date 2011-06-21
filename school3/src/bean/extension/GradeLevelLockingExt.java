/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import bean.hr.Employee;
import bean.reference.Course;
import bean.reference.GradeLevel;

/**
 *
 * @author Budoy Entokwa
 */
@UITemplate(columnSearch={"code", "longName", "course", "head", "locked"}, gridCount=4, title="Level")
@Displays({
       // @Display(name="schoolYear"),
        @Display(name="code"),
        @Display(name="longName", upCase=false),
        @Display(name="course", type="PopSearch", linktoBean=Course.class),
        @Display(name="headId", linktoBean=Employee.class, type="PopSearch", label="Head"),
        @Display(name="lock")
})
@ActionButtons( {
		@ActionButton(name = "btnLock", label = "Lock Level"),
		@ActionButton(name = "btnRelease", label = "Release Lock"),
		@ActionButton(name = "btnReleaseAll", label = "Release All Lock")
})
public class GradeLevelLockingExt extends GradeLevel {

}
