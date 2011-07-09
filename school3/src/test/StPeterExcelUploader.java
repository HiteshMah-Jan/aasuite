/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import bean.Student;
import bean.person.PersonDependent;
import bean.person.PersonEducation;
import bean.reference.Course;
import bean.reference.GradeLevel;
import bean.reference.Section;
import java.io.File;
import java.util.List;
import service.util.AbstractIBean;
import springbean.AAAConfig;
import util.DateUtil;
import util.Log;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class StPeterExcelUploader {
    private static void uploadPreSchool() {
        boolean b = PanelUtil.showPrompt(null, "Do you want to delete all Student?");
        if (b) {
            AbstractIBean.runSQL("DELETE FROM Student");
            AbstractIBean.runSQL("DELETE FROM StudentSubject");
            AbstractIBean.runSQL("DELETE FROM PersonDependent");
//            DBClient.runSQL("DELETE FROM Course");
//            DBClient.runSQL("DELETE FROM CourseSubject");
//            DBClient.runSQL("DELETE FROM Section");
        }
        File f = PanelUtil.showOpenFile("Upload File", ".xls", null);
        String filename = f.getPath();
        String[] beanMapping = {
            "Student,registrationDate,gradeLevel,section,schoolYear,lastName,firstName,middleInitial,chinese,gender,birthDate,placeOfBirth,citizenship,religion,address,contactNumber,mobilePhone",
            "PersonEducation,school,address,grade,completeEnglish,completeChinese,year",
            "PersonDependent,lastName,chineseName,citizenship,religion,occupation,companyName,companyAddress,telephoneNumber,mobileNumber,email",
            "PersonDependent,lastName,chineseName,citizenship,religion,occupation,companyName,companyAddress,telephoneNumber,mobileNumber,email"
        };
        int studentNumber = Integer.parseInt(AbstractIBean.singleColumn("SELECT COUNT(*) FROM Person WHERE personType='STUDENT'").toString());
        
        Class[] clsArr = Collection2BeanMapper.getClassFromMapping(beanMapping);
        List<List> lst = ExcelReader.getInstance(filename, clsArr).getMapping(beanMapping);
        for (int i=0; i<lst.size(); i++) {
            List list = lst.get(i);
            Student stud = (Student) list.get(0);
            
            PersonEducation ed = (PersonEducation) list.get(1);
            PersonDependent father = (PersonDependent) list.get(2);
            PersonDependent mother = (PersonDependent) list.get(3);
            
            Log.out("#",(i+1)," Loading ",stud.firstName," ",stud.lastName);
            
            stud.father = father.lastName;
            stud.mother = mother.lastName;
            stud.age = 0;
            if (stud.birthDate!=null) {
                int span = DateUtil.getSpanYears(stud.birthDate);
                int month = DateUtil.getSpanMonth(stud.birthDate);
                if (month>=0) {
                    stud.age = span;
                }
                else {
                    stud.age = span-1;
                }
            }
            String courseCode = "";
            if (stud!=null && stud.gradeLevel.startsWith("N")) {
                courseCode = "NURSERY";
            }
            else if (stud!=null && stud.gradeLevel.startsWith("K")) {
                courseCode = "KINDER";
            }
            stud.setStudentNumber(Student.getDefaultStudentNumber(studentNumber++));
            stud.course = courseCode;
            stud.save();
            
            Integer personId = stud.getPersonId();
            if (ed!=null && personId!=null) {
                ed.setPersonId(personId);
                ed.save();
            }
            if (father!=null && personId!=null) {
                father.setPersonId(personId);
                father.setRelation("FATHER");
                father.save();
            }
            if (mother!=null && personId!=null) {
                mother.setPersonId(personId);
                mother.setRelation("MOTHER");
                mother.save();
            }

            Course course = new Course();
            course.code = courseCode;
            course.courseName = courseCode;
//            course.save();
            
            Section section = new Section();
            section.code = stud.section;
            section.gradeLevel = stud.gradeLevel;
            section.sectionDescription = stud.section;
//            section.save();
            
            GradeLevel level = new GradeLevel();
            level.code = stud.gradeLevel;
            level.course = courseCode;
//            level.save();
        }
        Log.out("FIN PRESCHOOL.");
    }
    
    private static void uploadHSElem() {
        File f = PanelUtil.showOpenFile("Upload File", ".xls", null);
        String filename = f.getPath();
        String[] beanMapping = {
            "Student,any,lastName,chineseName,father,address,contactNumber"
        };
        int studentNumber = Integer.parseInt(AbstractIBean.singleColumn("SELECT COUNT(*) FROM Person WHERE personType='STUDENT'").toString());
        
        Class[] clsArr = Collection2BeanMapper.getClassFromMapping(beanMapping);
        List<List> lst = ExcelReader.getInstance(filename, clsArr).getMapping(beanMapping);
        Log.out("LIST SIZE == ",lst);
        for (int i=0; i<lst.size(); i++) {
            List list = lst.get(i);
            Student stud = (Student) list.get(0);
            String str = stud.lastName;
            stud.lastName = getName(str, 0);
            stud.firstName = getName(str, 1);
            stud.middleInitial = getName(str, 2);
            if (stud.lastName==null || stud.lastName.isEmpty()) continue;
            if (stud.firstName==null || stud.firstName.isEmpty()) continue;
            
            Log.out("#",(i+1)," Loading ",stud.firstName," ",stud.lastName);
            stud.setStudentNumber(Student.getDefaultStudentNumber(studentNumber++));
            stud.save();
        }
        Log.out("FIN.");
    }

    private static String getName(String name, int position) {
        if (name==null) return null;
        if (name.indexOf(",")==-1) return null;
        String val = null;
        if (position==0) {
            val = name.substring(0, name.indexOf(","));
        }
        else if (position==1) {
            val = name.substring(name.indexOf(",")+1, name.length()-2);
        }
        else if (position==2) {
            val = name.substring(name.length()-2, name.length()-1);
        }
        if (val!=null) val = val.trim();
        return val;
    }
    
    public static void main(String[] args) {
        AAAConfig.getInstance();
        uploadPreSchool();
        uploadHSElem();
//        String str = "Aguilar, Darren Vedder M.";
//        Log.out(getName(str, 0));
//        Log.out(getName(str, 1));
//        Log.out(getName(str, 2));
    }
}
