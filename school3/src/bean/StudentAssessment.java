package bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bean.reference.Section;

import service.util.AbstractIBean;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "StudentAssessment")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, 
		columnSearch = {"schoolYear","studentId","semeter","assessedBy","section"})
@Displays({
	@Display(name="schoolYear"),
	@Display(name="studentId", label="Student", type="PopSearch", linktoBean=Student.class),
	@Display(name="semeter", type="Combo", modelCombo={"1","2","3","4"}),
	@Display(name="assessedBy"),
	@Display(name="section", type="PopSearch", linktoBean=Section.class),
	@Display(name="schedule1", label="Schedule", labelTop=true, addInfoOnly=true),
	@Display(name="subject1", label="Subject", labelTop=true, addInfoOnly=true),
	@Display(name="unit1", label="Unit", labelTop=true, addInfoOnly=true),
	@Display(name="amount1", label="Amount", labelTop=true, addInfoOnly=true),
	@Display(name="schedule2", hideLabel=true, addInfoOnly=true),
	@Display(name="subject2", hideLabel=true, addInfoOnly=true),
	@Display(name="unit2", hideLabel=true, addInfoOnly=true),
	@Display(name="amount2", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule3", hideLabel=true, addInfoOnly=true),
	@Display(name="subject3", hideLabel=true, addInfoOnly=true),
	@Display(name="unit3", hideLabel=true, addInfoOnly=true),
	@Display(name="amount3", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule4", hideLabel=true, addInfoOnly=true),
	@Display(name="subject4", hideLabel=true, addInfoOnly=true),
	@Display(name="unit4", hideLabel=true, addInfoOnly=true),
	@Display(name="amount4", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule5", hideLabel=true, addInfoOnly=true),
	@Display(name="subject5", hideLabel=true, addInfoOnly=true),
	@Display(name="unit5", hideLabel=true, addInfoOnly=true),
	@Display(name="amount5", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule6", hideLabel=true, addInfoOnly=true),
	@Display(name="subject6", hideLabel=true, addInfoOnly=true),
	@Display(name="unit6", hideLabel=true, addInfoOnly=true),
	@Display(name="amount6", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule7", hideLabel=true, addInfoOnly=true),
	@Display(name="subject7", hideLabel=true, addInfoOnly=true),
	@Display(name="unit7", hideLabel=true, addInfoOnly=true),
	@Display(name="amount7", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule8", hideLabel=true, addInfoOnly=true),
	@Display(name="subject8", hideLabel=true, addInfoOnly=true),
	@Display(name="unit8", hideLabel=true, addInfoOnly=true),
	@Display(name="amount8", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule9", hideLabel=true, addInfoOnly=true),
	@Display(name="subject9", hideLabel=true, addInfoOnly=true),
	@Display(name="unit9", hideLabel=true, addInfoOnly=true),
	@Display(name="amount9", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule10", hideLabel=true, addInfoOnly=true),
	@Display(name="subject10", hideLabel=true, addInfoOnly=true),
	@Display(name="unit10", hideLabel=true, addInfoOnly=true),
	@Display(name="amount10", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule11", label="Schedule", labelTop=true, addInfoOnly=true),
	@Display(name="subject11", label="Subject", labelTop=true, addInfoOnly=true),
	@Display(name="unit11", label="Unit", labelTop=true, addInfoOnly=true),
	@Display(name="amount11", label="Amount", labelTop=true, addInfoOnly=true),
	@Display(name="schedule12", hideLabel=true, addInfoOnly=true),
	@Display(name="subject12", hideLabel=true, addInfoOnly=true),
	@Display(name="unit12", hideLabel=true, addInfoOnly=true),
	@Display(name="amount12", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule13", hideLabel=true, addInfoOnly=true),
	@Display(name="subject13", hideLabel=true, addInfoOnly=true),
	@Display(name="unit13", hideLabel=true, addInfoOnly=true),
	@Display(name="amount13", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule14", hideLabel=true, addInfoOnly=true),
	@Display(name="subject14", hideLabel=true, addInfoOnly=true),
	@Display(name="unit14", hideLabel=true, addInfoOnly=true),
	@Display(name="amount14", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule15", hideLabel=true, addInfoOnly=true),
	@Display(name="subject15", hideLabel=true, addInfoOnly=true),
	@Display(name="unit15", hideLabel=true, addInfoOnly=true),
	@Display(name="amount15", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule16", hideLabel=true, addInfoOnly=true),
	@Display(name="subject16", hideLabel=true, addInfoOnly=true),
	@Display(name="unit16", hideLabel=true, addInfoOnly=true),
	@Display(name="amount16", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule17", hideLabel=true, addInfoOnly=true),
	@Display(name="subject17", hideLabel=true, addInfoOnly=true),
	@Display(name="unit17", hideLabel=true, addInfoOnly=true),
	@Display(name="amount17", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule18", hideLabel=true, addInfoOnly=true),
	@Display(name="subject18", hideLabel=true, addInfoOnly=true),
	@Display(name="unit18", hideLabel=true, addInfoOnly=true),
	@Display(name="amount18", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule19", hideLabel=true, addInfoOnly=true),
	@Display(name="subject19", hideLabel=true, addInfoOnly=true),
	@Display(name="unit19", hideLabel=true, addInfoOnly=true),
	@Display(name="amount19", hideLabel=true, addInfoOnly=true),
	@Display(name="schedule20", hideLabel=true, addInfoOnly=true),
	@Display(name="subject20", hideLabel=true, addInfoOnly=true),
	@Display(name="unit20", hideLabel=true, addInfoOnly=true),
	@Display(name="amount20", hideLabel=true, addInfoOnly=true),
	@Display(name="totalUnit"),
	@Display(name="miscAmount"),
	@Display(name="totalUnitAmount"),
	@Display(name="overallAmount")
})
@ChildRecords(value = {
}, info = { 
		@ParentAddInfo(title = "Subjects 1-10", gridCount=8, 
				displayFields = {
					"schedule1","subject1","unit1","amount1",
					"schedule2","subject2","unit2","amount2",
					"schedule3","subject3","unit3","amount3",
					"schedule4","subject4","unit4","amount4",
					"schedule5","subject5","unit5","amount5",
					"schedule6","subject6","unit6","amount6",
					"schedule7","subject7","unit7","amount7",
					"schedule8","subject8","unit8","amount8",
					"schedule9","subject9","unit9","amount9",
					"schedule10","subject10","unit10","amount10"
				}),
		@ParentAddInfo(title = "Subjects 11-20", gridCount=8,
				displayFields = {
				"schedule11","subject11","unit11","amount11",
				"schedule12","subject12","unit12","amount12",
				"schedule13","subject13","unit13","amount13",
				"schedule14","subject14","unit14","amount14",
				"schedule15","subject15","unit15","amount15",
				"schedule16","subject16","unit16","amount16",
				"schedule17","subject17","unit17","amount17",
				"schedule18","subject18","unit18","amount18",
				"schedule19","subject19","unit19","amount19",
				"schedule20","subject20","unit20","amount20"
			})
})
public class StudentAssessment extends AbstractIBean {
	@Id
	public Integer seq;
	public String schoolYear;
	public int studentId;
	public int semeter;
	public String assessedBy;
	public String section;
	
	public int schedule1;
	public String subject1;
	public double unit1;
	public double amount1;
	public int schedule2;
	public String subject2;
	public double unit2;
	public double amount2;
	public int schedule3;
	public String subject3;
	public double unit3;
	public double amount3;
	public int schedule4;
	public String subject4;
	public double unit4;
	public double amount4;
	public int schedule5;
	public String subject5;
	public double unit5;
	public double amount5;
	public int schedule6;
	public String subject6;
	public double unit6;
	public double amount6;
	public int schedule7;
	public String subject7;
	public double unit7;
	public double amount7;
	public int schedule8;
	public String subject8;
	public double unit8;
	public double amount8;
	public int schedule9;
	public String subject9;
	public double unit9;
	public double amount9;
	public int schedule10;
	public String subject10;
	public double unit10;
	public double amount10;
	public int schedule11;
	public String subject11;
	public double unit11;
	public double amount11;
	public int schedule12;
	public String subject12;
	public double unit12;
	public double amount12;
	public int schedule13;
	public String subject13;
	public double unit13;
	public double amount13;
	public int schedule14;
	public String subject14;
	public double unit14;
	public double amount14;
	public int schedule15;
	public String subject15;
	public double unit15;
	public double amount15;
	public int schedule16;
	public String subject16;
	public double unit16;
	public double amount16;
	public int schedule17;
	public String subject17;
	public double unit17;
	public double amount17;
	public int schedule18;
	public String subject18;
	public double unit18;
	public double amount18;
	public int schedule19;
	public String subject19;
	public double unit19;
	public double amount19;
	public int schedule20;
	public String subject20;
	public double unit20;
	public double amount20;
	
	public double totalUnit;
	public double miscAmount;
	public double totalUnitAmount;
	public double overallAmount;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public int getSemeter() {
		return semeter;
	}
	public void setSemeter(int semeter) {
		this.semeter = semeter;
	}
	public String getAssessedBy() {
		return assessedBy;
	}
	public void setAssessedBy(String assessedBy) {
		this.assessedBy = assessedBy;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public int getSchedule1() {
		return schedule1;
	}
	public void setSchedule1(int schedule1) {
		this.schedule1 = schedule1;
	}
	public String getSubject1() {
		return subject1;
	}
	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}
	public double getUnit1() {
		return unit1;
	}
	public void setUnit1(double unit1) {
		this.unit1 = unit1;
	}
	public double getAmount1() {
		return amount1;
	}
	public void setAmount1(double amount1) {
		this.amount1 = amount1;
	}
	public int getSchedule2() {
		return schedule2;
	}
	public void setSchedule2(int schedule2) {
		this.schedule2 = schedule2;
	}
	public String getSubject2() {
		return subject2;
	}
	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}
	public double getUnit2() {
		return unit2;
	}
	public void setUnit2(double unit2) {
		this.unit2 = unit2;
	}
	public double getAmount2() {
		return amount2;
	}
	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}
	public int getSchedule3() {
		return schedule3;
	}
	public void setSchedule3(int schedule3) {
		this.schedule3 = schedule3;
	}
	public String getSubject3() {
		return subject3;
	}
	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}
	public double getUnit3() {
		return unit3;
	}
	public void setUnit3(double unit3) {
		this.unit3 = unit3;
	}
	public double getAmount3() {
		return amount3;
	}
	public void setAmount3(double amount3) {
		this.amount3 = amount3;
	}
	public int getSchedule4() {
		return schedule4;
	}
	public void setSchedule4(int schedule4) {
		this.schedule4 = schedule4;
	}
	public String getSubject4() {
		return subject4;
	}
	public void setSubject4(String subject4) {
		this.subject4 = subject4;
	}
	public double getUnit4() {
		return unit4;
	}
	public void setUnit4(double unit4) {
		this.unit4 = unit4;
	}
	public double getAmount4() {
		return amount4;
	}
	public void setAmount4(double amount4) {
		this.amount4 = amount4;
	}
	public int getSchedule5() {
		return schedule5;
	}
	public void setSchedule5(int schedule5) {
		this.schedule5 = schedule5;
	}
	public String getSubject5() {
		return subject5;
	}
	public void setSubject5(String subject5) {
		this.subject5 = subject5;
	}
	public double getUnit5() {
		return unit5;
	}
	public void setUnit5(double unit5) {
		this.unit5 = unit5;
	}
	public double getAmount5() {
		return amount5;
	}
	public void setAmount5(double amount5) {
		this.amount5 = amount5;
	}
	public int getSchedule6() {
		return schedule6;
	}
	public void setSchedule6(int schedule6) {
		this.schedule6 = schedule6;
	}
	public String getSubject6() {
		return subject6;
	}
	public void setSubject6(String subject6) {
		this.subject6 = subject6;
	}
	public double getUnit6() {
		return unit6;
	}
	public void setUnit6(double unit6) {
		this.unit6 = unit6;
	}
	public double getAmount6() {
		return amount6;
	}
	public void setAmount6(double amount6) {
		this.amount6 = amount6;
	}
	public int getSchedule7() {
		return schedule7;
	}
	public void setSchedule7(int schedule7) {
		this.schedule7 = schedule7;
	}
	public String getSubject7() {
		return subject7;
	}
	public void setSubject7(String subject7) {
		this.subject7 = subject7;
	}
	public double getUnit7() {
		return unit7;
	}
	public void setUnit7(double unit7) {
		this.unit7 = unit7;
	}
	public double getAmount7() {
		return amount7;
	}
	public void setAmount7(double amount7) {
		this.amount7 = amount7;
	}
	public int getSchedule8() {
		return schedule8;
	}
	public void setSchedule8(int schedule8) {
		this.schedule8 = schedule8;
	}
	public String getSubject8() {
		return subject8;
	}
	public void setSubject8(String subject8) {
		this.subject8 = subject8;
	}
	public double getUnit8() {
		return unit8;
	}
	public void setUnit8(double unit8) {
		this.unit8 = unit8;
	}
	public double getAmount8() {
		return amount8;
	}
	public void setAmount8(double amount8) {
		this.amount8 = amount8;
	}
	public int getSchedule9() {
		return schedule9;
	}
	public void setSchedule9(int schedule9) {
		this.schedule9 = schedule9;
	}
	public String getSubject9() {
		return subject9;
	}
	public void setSubject9(String subject9) {
		this.subject9 = subject9;
	}
	public double getUnit9() {
		return unit9;
	}
	public void setUnit9(double unit9) {
		this.unit9 = unit9;
	}
	public double getAmount9() {
		return amount9;
	}
	public void setAmount9(double amount9) {
		this.amount9 = amount9;
	}
	public int getSchedule10() {
		return schedule10;
	}
	public void setSchedule10(int schedule10) {
		this.schedule10 = schedule10;
	}
	public String getSubject10() {
		return subject10;
	}
	public void setSubject10(String subject10) {
		this.subject10 = subject10;
	}
	public double getUnit10() {
		return unit10;
	}
	public void setUnit10(double unit10) {
		this.unit10 = unit10;
	}
	public double getAmount10() {
		return amount10;
	}
	public void setAmount10(double amount10) {
		this.amount10 = amount10;
	}
	public int getSchedule11() {
		return schedule11;
	}
	public void setSchedule11(int schedule11) {
		this.schedule11 = schedule11;
	}
	public String getSubject11() {
		return subject11;
	}
	public void setSubject11(String subject11) {
		this.subject11 = subject11;
	}
	public double getUnit11() {
		return unit11;
	}
	public void setUnit11(double unit11) {
		this.unit11 = unit11;
	}
	public double getAmount11() {
		return amount11;
	}
	public void setAmount11(double amount11) {
		this.amount11 = amount11;
	}
	public int getSchedule12() {
		return schedule12;
	}
	public void setSchedule12(int schedule12) {
		this.schedule12 = schedule12;
	}
	public String getSubject12() {
		return subject12;
	}
	public void setSubject12(String subject12) {
		this.subject12 = subject12;
	}
	public double getUnit12() {
		return unit12;
	}
	public void setUnit12(double unit12) {
		this.unit12 = unit12;
	}
	public double getAmount12() {
		return amount12;
	}
	public void setAmount12(double amount12) {
		this.amount12 = amount12;
	}
	public int getSchedule13() {
		return schedule13;
	}
	public void setSchedule13(int schedule13) {
		this.schedule13 = schedule13;
	}
	public String getSubject13() {
		return subject13;
	}
	public void setSubject13(String subject13) {
		this.subject13 = subject13;
	}
	public double getUnit13() {
		return unit13;
	}
	public void setUnit13(double unit13) {
		this.unit13 = unit13;
	}
	public double getAmount13() {
		return amount13;
	}
	public void setAmount13(double amount13) {
		this.amount13 = amount13;
	}
	public int getSchedule14() {
		return schedule14;
	}
	public void setSchedule14(int schedule14) {
		this.schedule14 = schedule14;
	}
	public String getSubject14() {
		return subject14;
	}
	public void setSubject14(String subject14) {
		this.subject14 = subject14;
	}
	public double getUnit14() {
		return unit14;
	}
	public void setUnit14(double unit14) {
		this.unit14 = unit14;
	}
	public double getAmount14() {
		return amount14;
	}
	public void setAmount14(double amount14) {
		this.amount14 = amount14;
	}
	public int getSchedule15() {
		return schedule15;
	}
	public void setSchedule15(int schedule15) {
		this.schedule15 = schedule15;
	}
	public String getSubject15() {
		return subject15;
	}
	public void setSubject15(String subject15) {
		this.subject15 = subject15;
	}
	public double getUnit15() {
		return unit15;
	}
	public void setUnit15(double unit15) {
		this.unit15 = unit15;
	}
	public double getAmount15() {
		return amount15;
	}
	public void setAmount15(double amount15) {
		this.amount15 = amount15;
	}
	public int getSchedule16() {
		return schedule16;
	}
	public void setSchedule16(int schedule16) {
		this.schedule16 = schedule16;
	}
	public String getSubject16() {
		return subject16;
	}
	public void setSubject16(String subject16) {
		this.subject16 = subject16;
	}
	public double getUnit16() {
		return unit16;
	}
	public void setUnit16(double unit16) {
		this.unit16 = unit16;
	}
	public double getAmount16() {
		return amount16;
	}
	public void setAmount16(double amount16) {
		this.amount16 = amount16;
	}
	public int getSchedule17() {
		return schedule17;
	}
	public void setSchedule17(int schedule17) {
		this.schedule17 = schedule17;
	}
	public String getSubject17() {
		return subject17;
	}
	public void setSubject17(String subject17) {
		this.subject17 = subject17;
	}
	public double getUnit17() {
		return unit17;
	}
	public void setUnit17(double unit17) {
		this.unit17 = unit17;
	}
	public double getAmount17() {
		return amount17;
	}
	public void setAmount17(double amount17) {
		this.amount17 = amount17;
	}
	public int getSchedule18() {
		return schedule18;
	}
	public void setSchedule18(int schedule18) {
		this.schedule18 = schedule18;
	}
	public String getSubject18() {
		return subject18;
	}
	public void setSubject18(String subject18) {
		this.subject18 = subject18;
	}
	public double getUnit18() {
		return unit18;
	}
	public void setUnit18(double unit18) {
		this.unit18 = unit18;
	}
	public double getAmount18() {
		return amount18;
	}
	public void setAmount18(double amount18) {
		this.amount18 = amount18;
	}
	public int getSchedule19() {
		return schedule19;
	}
	public void setSchedule19(int schedule19) {
		this.schedule19 = schedule19;
	}
	public String getSubject19() {
		return subject19;
	}
	public void setSubject19(String subject19) {
		this.subject19 = subject19;
	}
	public double getUnit19() {
		return unit19;
	}
	public void setUnit19(double unit19) {
		this.unit19 = unit19;
	}
	public double getAmount19() {
		return amount19;
	}
	public void setAmount19(double amount19) {
		this.amount19 = amount19;
	}
	public int getSchedule20() {
		return schedule20;
	}
	public void setSchedule20(int schedule20) {
		this.schedule20 = schedule20;
	}
	public String getSubject20() {
		return subject20;
	}
	public void setSubject20(String subject20) {
		this.subject20 = subject20;
	}
	public double getUnit20() {
		return unit20;
	}
	public void setUnit20(double unit20) {
		this.unit20 = unit20;
	}
	public double getAmount20() {
		return amount20;
	}
	public void setAmount20(double amount20) {
		this.amount20 = amount20;
	}
	public double getTotalUnit() {
		return totalUnit;
	}
	public void setTotalUnit(double totalUnit) {
		this.totalUnit = totalUnit;
	}
	public double getMiscAmount() {
		return miscAmount;
	}
	public void setMiscAmount(double miscAmount) {
		this.miscAmount = miscAmount;
	}
	public double getTotalUnitAmount() {
		return totalUnitAmount;
	}
	public void setTotalUnitAmount(double totalUnitAmount) {
		this.totalUnitAmount = totalUnitAmount;
	}
	public double getOverallAmount() {
		return overallAmount;
	}
	public void setOverallAmount(double overallAmount) {
		this.overallAmount = overallAmount;
	}
	public static void main(String[] args) {
		view(StudentAssessment.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
