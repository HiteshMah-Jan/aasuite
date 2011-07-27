package test;

import java.util.List;

import bean.Customer;
import bean.Person;
import bean.Student;

import springbean.AAAConfig;
import util.BeanUtil;
import util.DBClient;

public class MigrateStudentFromPerson {
	public static void main(String[] args) {
		AAAConfig.getServerInstance();
		List<Customer> allStudents = DBClient.getList("SELECT a FROM Customer a WHERE a.personType='STUDENT'", 0, 10000);
		for (Customer p: allStudents) {
			Student s = new Student();
			s.seq = p.personId;
			s.firstName = p.firstName;
			s.lastName = p.lastName;
			s.middleInitial = p.middleInitial;
			s.birthDate = p.birthDate;
			s.gradeLevel = p.gradeLevel;
			s.gender = p.gender;
			s.studentNumber = p.studentNumber;
			s.officiallyRegistered = true;
			s.placeOfBirth = p.placeOfBirth;
			s.schoolYear = p.schoolYear;
			s.section = p.section;
			s.save();
		}
	}
}
