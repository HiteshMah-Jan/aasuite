/*
 * StudentExt.java
 * 
 * Created on Dec 3, 2007, 4:37:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.extension;

import bean.LibraryBorrowedBooks;
import bean.hr.PersonAttendance;
import bean.person.StudentEncounter;
import bean.person.StudentEncounterClinic;
import bean.person.StudentPersonalityExam;
import java.io.Serializable;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListOnly;
import template.screen.TemplateTabPage;

/**
 *
 * @author Ebhoy
 */
@UITemplate(template = TemplateTabPage.class, canSave=false, canNew=false, canDelete=false, criteriaSearch = {"lastName", "firstName", "section"}, columnSearch = {"studentNumber", "lastName", "firstName", "course", "section"}, gridCount = 6, title = "Student")
@Displays({
    @Display(name = "studentNumber", type = "Label"),
    @Display(name = "lastName", type = "Label"),
    @Display(name = "firstName", type = "Label"),
    @Display(name = "schoolYear", type = "Label"),
    @Display(name = "section", type = "Label"),
    @Display(name = "course", label = "Grade", type = "Label"),
    @Display(name = "gender", type = "Label"),
    @Display(name = "citizenship", type = "Label"),
    @Display(name = "religion", type = "Label"),
    @Display(name = "contactNumber", type = "Label")
})
@ChildRecords(value = {
    @ChildRecord(canSave = false, template = ChildTemplateListOnly.class, fieldMapping = {"personId", "studentId"}, title = "OSA", entity = StudentEncounter.class, sql = "SELECT a FROM StudentEncounter a WHERE a.studentId = ${personId}"),
    @ChildRecord(canSave = false, template = ChildTemplateListOnly.class, fieldMapping = {"personId", "personId"}, title = "Book Borrowed", entity = LibraryBorrowedBooks.class, sql = "SELECT a FROM LibraryBorrowedBooks a WHERE a.personId=${personId}"),
    @ChildRecord(canSave = false, template = ChildTemplateListOnly.class, fieldMapping = {"personId", "studentId"}, title = "Clinic", entity = StudentEncounterClinic.class, sql = "SELECT a FROM StudentEncounterClinic a WHERE a.studentId=${personId}"),
    @ChildRecord(canSave = false, template = ChildTemplateListOnly.class, fieldMapping = {"personId", "personId"}, title = "Attendance", entity = PersonAttendance.class, sql = "SELECT a FROM PersonAttendance a WHERE a.personId=${personId}"),
    @ChildRecord(canSave = false, template = ChildTemplateListOnly.class, fieldMapping = {"personId", "studentId"}, title = "Personal Exam", entity = StudentPersonalityExam.class, sql = "SELECT a FROM StudentPersonalityExam a WHERE a.studentId=${personId}"),
    @ChildRecord(canSave = false, template = ChildTemplateListOnly.class, fieldMapping = {"personId", "studentId"}, title = "Student Class Performance", entity = EnrollmentSubjectFacultyExtOsa.class, sql = "SELECT a FROM StudentSubject a WHERE a.studentId=${personId}")
})
public class StudentExt extends bean.Student implements Serializable {

    public static void main(String[] args) {
        view(StudentExt.class);
    }
}
