        /*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import service.ParamStruct;
import service.ReturnStruct;
import service.util.AbstractIBean;
import service.util.CallService;
import service.util.ChartBean;
import springbean.SchoolDefaultProcess;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateLeftRight;
import util.BeanUtil;
import util.DateUtil;
import bean.accounting.Invoice;
import bean.extension.GradeLevelExamExt;
import bean.person.PersonDependent;
import bean.reference.GradeLevel;
import constants.UserInfo;
import template.ActionButton;
import template.ActionButtons;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Admission")
@UITemplate(template = TemplateLeftRight.class, showChart = false, showFiles = true,showImages=true,imageInsideForm=true, sumFooter = "5", editableCol = "5",
criteriaSearch = {"lastName", "firstName", "gradeLevel","examinationDate"},
columnSearch = {"lastName", "firstName", "middleInitial", "gradeLevel","examinationDate"},
gridCount = 2)
@ChildRecords(value = { //@ChildRecord(template=ChildTemplateListPopup.class, fieldMapping={"seq","admissionId"}, entity=AdmissionExamReference.class, sql="SELECT a FROM AdmissionExam a WHERE a.admissionId=${seq} ORDER BY a.examType", title="Exams")
},
info = {
    @ParentAddInfo(title = "Admission Requirements", gridCount = 2,
    displayFields = {"threeColoredIdPictures", "birthCertificateNsoCopy", "baptismalCertificate", "latestReportCard","originalReportCard", "letterOfRecommendation"},hideGroup="3,4,6,7,8,9,10"),
    @ParentAddInfo(title = "Applicants Other Info", gridCount = 4,
    displayFields = {"father", "fatherOccupation", "fatherCompany", "fatherCompanyAddress","fatherCompanyTelNo", 
    "mother", "motherOccupation", "motherCompany", "motherCompanyAddress","motherCompanyTelNo"},hideGroup="5,6,7,8,9,10"),
     @ParentAddInfo(title = "Examination Details", gridCount = 4, displayFields = {"orDate","orNumber"},hideGroup="3,4,5,9,10"),
     @ParentAddInfo(title = "Recommendation", gridCount = 4, hideGroup="1,2,3,4,5,6,7,8",
     displayFields={""})
    
})
@DisplayGroups({
    @DisplayGroup(title = "Applicant's Name", gridCount = 6, fields = {"lastName", "firstName", "middleInitial"}),
    @DisplayGroup(title = "Personal Details", gridCount =4, fields = {"address", "telNum", "birthDate","age", "birthPlace","nationality","ifAlienAcrNo","acrPlaceIssued","acrDateIssued","religion","otherReligion","schoolLastAttended","schoolLastAttendedAddress","reasonForTransfer"}),
    @DisplayGroup(title = "Other children studying in this School", gridCount = 4, fields = {"childName1", "childGradeSection1","childName2", "childGradeSection2", "childName3", "childGradeSection3"}),
    @DisplayGroup(title = "Recent Schools Attended", gridCount = 4, fields = {"preschoolAttended", "preschoolSchoolYear", "preschoolReasonForTransfer","elementaryAttended", "elementarySchoolYear", "elementaryReasonForTransfer","highschoolAttended", "highschoolSchoolYear", "highschoolReasonForTransfer"}, addInfoOnly = true),
    @DisplayGroup(title = "Grade point Average in Previous School", gridCount = 6, fields = { "gpaEla", "gpaMath","gpaSci","gpaFil","gpaSs","gpaCon","gpaAve","prevSchool"},addInfoOnly=true),
    @DisplayGroup(title = "For Foreigner", gridCount = 2, fields = {"foreigner","copyOfAcr", "passportForVerification", "completeScholasticRecords","studyPermit"}, addInfoOnly = true),
    @DisplayGroup(title = "Exam Schedule", gridCount = 2, fields = {"examinationDate", "examTime", "roomAssign"}, addInfoOnly = true),
    @DisplayGroup(title = "Test Score", gridCount = 8, fields = {"elaCount", "elaScore", "elaPercentage","elaRemarks","mathCount", "mathScore", "mathPercentage","mathRemarks","sciCount", "sciScore", "sciPercentage","sciRemarks","cognitiveCount", "cognitiveScore", "cognitivePercentage","cognitiveRemarks","psychomotorCount", "psychomotorScore", "psychomotorPercentage","psychomotorRemarks","affectiveCount", "affectiveScore", "affectivePercentage","affectiveRemarks","finalRemarks","forImprovementIn"}, addInfoOnly = true),
    @DisplayGroup(title = "Evaluation", gridCount = 2, fields = {"evaluationDate", "evaluation", "enrolledDate"}, addInfoOnly = true),
    @DisplayGroup(title = "Recommended Remedial Class", gridCount = 6, fields = {"recommendedRemedialEla","recommendedRemedialReading","recommendedRemedialWriting", "recommendedRemedialMath","recommendedRemedialSci","recommendedRemedialFil","recommendedRemedialSS","recommendedRemedialCon","betterQualifiedLevel"}, addInfoOnly = true),
    @DisplayGroup(title = "Recommendation", gridCount = 4, fields = {"recommendation", "recommendationCondition"}, addInfoOnly = true)
    
})
@Displays({
     @Display(name = "branchCode",duties={"CAN EDIT ADMISSION DETAIL"}),
    @Display(name = "schoolYear",duties={"CAN EDIT ADMISSION DETAIL"}),
   
   // @Display(name = "applicationStatus", type = "Combo", modelCombo = {"OLD", "NEW"},width=150),
    //@Display(name="branchCode", width = 150),
    
    @Display(name = "gender",duties={"CAN EDIT ADMISSION DETAIL"},type="Combo",modelCombo={"MALE","FEMALE"}),
    @Display(name = "previousGradeLevel",duties={"CAN EDIT ADMISSION DETAIL"}, type = "PopSearch", linktoBean = GradeLevelExamExt.class,label="Previous Level"),
   //@Display(name = "course", type = "PopSearch", linktoBean = Course.class,width=150),
    @Display(name = "gradeLevel",duties={"CAN EDIT ADMISSION DETAIL"}, type = "PopSearch", linktoBean = GradeLevelExamExt.class,label="Level Applied For"),
    // @Display(name="section", type="PopSearch", linktoBean=Section.class, width = 117),
    
    @Display(name = "lastName",labelTop=true),
    @Display(name = "firstName",labelTop=true),
    @Display(name = "middleInitial", label = "Middle Name",labelTop=true),
  
    
    @Display(name = "birthDate",duties={"CAN EDIT ADMISSION DETAIL"}),
    @Display(name = "age",duties={"CAN EDIT ADMISSION DETAIL"},width=20),
    @Display(name = "birthPlace",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false),
    @Display(name = "telNum",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false),
    @Display(name = "address",duties={"CAN EDIT ADMISSION DETAIL"},gridFieldWidth=3,width=-1,upCase=false),
    @Display(name = "nationality",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false),
    @Display(name = "foreigner",duties={"CAN EDIT ADMISSION DETAIL"}),
    @Display(name = "ifAlienAcrNo",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false),
    @Display(name = "acrPlaceIssued",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false),
    @Display(name = "acrDateIssued",duties={"CAN EDIT ADMISSION DETAIL"}),
    @Display(name = "religion",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false),
    @Display(name = "otherReligion",duties={"CAN EDIT ADMISSION DETAIL"},label="Non Catholic(Specify)",upCase=false),
    @Display(name = "schoolLastAttended",duties={"CAN EDIT ADMISSION DETAIL"},gridFieldWidth=3,width=-1,upCase=false),
    @Display(name = "schoolLastAttendedAddress",duties={"CAN EDIT ADMISSION DETAIL"},gridFieldWidth=3,width=-1,label="Address of School",upCase=false),
    @Display(name = "reasonForTransfer",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,gridFieldWidth=3,width=-1),
    
    
    @Display(name = "father",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,addInfoOnly=true,label="Father's Name",gridFieldWidth=3,width=-1),
    @Display(name = "fatherOccupation",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,addInfoOnly=true,label="Occupation"),
    @Display(name = "fatherCompanyTelNo",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,addInfoOnly=true,label="Tel No./s"),
    @Display(name = "fatherCompany",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,addInfoOnly=true,label="Company",gridFieldWidth=3,width=-1),
    @Display(name = "fatherCompanyAddress",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,addInfoOnly=true,gridFieldWidth=3,width=-1,label="Company Address"),
    @Display(name = "mother",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,addInfoOnly=true,label="Mother's Name",gridFieldWidth=3,width=-1),
    @Display(name = "motherOccupation",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,addInfoOnly=true,label="Occupation"),
    @Display(name = "motherCompanyTelNo",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,addInfoOnly=true,label="Tel No./s"),
    @Display(name = "motherCompany",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,addInfoOnly=true,label="Company",gridFieldWidth=3,width=-1),
    
    @Display(name = "motherCompanyAddress",duties={"CAN EDIT ADMISSION DETAIL"},addInfoOnly=true,gridFieldWidth=3,width=-1,upCase=false,label="Company Address"),
   
    
    
    @Display(name = "childName1",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,labelTop=true,label="Name",width=250),
    @Display(name = "childGradeSection1",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,labelTop=true,label="Grade / Section"),
    @Display(name = "childName2",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,hideLabel=true,width=250),
    @Display(name = "childGradeSection2",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,hideLabel=true),
    @Display(name = "childName3",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,hideLabel=true,width=250),
    @Display(name = "childGradeSection3",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,hideLabel=true),
    
    
    @Display(name = "preschoolAttended",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,label="Schools Attended",addInfoOnly=true,width=250,labelTop=true,leftLabel="Pre School"),
    @Display(name = "preschoolSchoolYear",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,label="School Year",addInfoOnly=true,width=60,labelTop=true),
    @Display(name = "preschoolReasonForTransfer",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,gridFieldWidth=3,width=-1,label="Reason for Transfer"),
    @Display(name = "elementaryAttended",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,label="Elementary",addInfoOnly=true,width=250),
    @Display(name = "elementarySchoolYear",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,label="School Year",addInfoOnly=true,width=60,hideLabel=true),
    @Display(name = "elementaryReasonForTransfer",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,gridFieldWidth=3,width=-1,label="Reason for Transfer"),
    @Display(name = "highschoolAttended",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,label="High School",addInfoOnly=true,width=250),
    @Display(name = "highschoolSchoolYear",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,label="School Year",addInfoOnly=true,width=60,hideLabel=true),
    @Display(name = "highschoolReasonForTransfer",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,gridFieldWidth=3,width=-1,addInfoOnly=true,label="Reason for Transfer"),
    
    
        
    @Display(name = "gpaEla",duties={"CAN EDIT ADMISSION DETAIL"},addInfoOnly=true,label="English",width=50),
    @Display(name = "gpaMath",duties={"CAN EDIT ADMISSION DETAIL"},addInfoOnly=true,label="Math",width=50),
    @Display(name = "gpaSci",duties={"CAN EDIT ADMISSION DETAIL"},addInfoOnly=true,label="Science",width=50),
    @Display(name = "gpaFil",duties={"CAN EDIT ADMISSION DETAIL"},addInfoOnly=true,label="Filipino",width=50),
    @Display(name = "gpaSs",duties={"CAN EDIT ADMISSION DETAIL"},addInfoOnly=true,label="SS",width=50),
    @Display(name = "gpaAve",duties={"CAN EDIT ADMISSION DETAIL"},addInfoOnly=true,label="Average",width=50),
    @Display(name = "gpaCon",duties={"CAN EDIT ADMISSION DETAIL"},addInfoOnly=true,label="Conduct",gridFieldWidth=5,width=60,type="Combo",modelCombo={"VERY GOOD","GOOD"}),
   
    @Display(name = "prevSchool",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,addInfoOnly=true,label="Prev. School",gridFieldWidth=5,width=-1),
    
    
    
    @Display(name =  "orDate",duties={"CAN EDIT ADMISSION DETAIL"},addInfoOnly=true),
    @Display(name =  "orNumber",duties={"CAN EDIT ADMISSION DETAIL"},width=120,addInfoOnly=true),
    
    @Display(name = "examinationDate",duties={"CAN EDIT ADMISSION DETAIL"},label="Scheduled Date",addInfoOnly=true),
    @Display(name = "examTime",duties={"CAN EDIT ADMISSION DETAIL"}, type = "Time",addInfoOnly=true,width=-1),
    @Display(name = "roomAssign",duties={"CAN EDIT ADMISSION DETAIL"},upCase=false,width=-1,addInfoOnly=true),
    
    @Display(name = "evaluationDate",duties={"CAN EDIT ADMISSION DETAIL"},addInfoOnly=true),
    @Display(name = "evaluation",duties={"CAN EDIT ADMISSION DETAIL"},type="Combo", modelCombo = {"PASSED", "FAILED", "RECONSIDERED","RESCHEDULED"},addInfoOnly=true,width=-1,label="Evaluation"),
    @Display(name = "enrolledDate",duties={"CAN EDIT ADMISSION DETAIL"},addInfoOnly=true),
     
     
     @Display(name = "cognitiveCount",addInfoOnly=true,width=60, leftLabel= "Cognitive",labelTop=true,label="Item Count"), 
     @Display(name = "cognitiveScore",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,labelTop=true,label="Score"), 
     @Display(name = "cognitivePercentage",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,labelTop=true,label="Percentage"), 
     @Display(name = "cognitiveRemarks",addInfoOnly=true,width=80,labelTop=true,label="Remarks"),
     @Display(name = "psychomotorCount",addInfoOnly=true,width=60, label = "Psychomotor"), 
     @Display(name = "psychomotorScore",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,hideLabel=true), 
     @Display(name = "psychomotorPercentage",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,hideLabel=true), 
     @Display(name = "psychomotorRemarks",addInfoOnly=true,width=80,hideLabel=true),
     @Display(name = "affectiveCount",addInfoOnly=true,width=60, label = "Affective"), 
     @Display(name = "affectiveScore",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,hideLabel=true), 
     @Display(name = "affectivePercentage",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,hideLabel=true), 
     @Display(name = "affectiveRemarks",addInfoOnly=true,width=80,hideLabel=true),
     
     
     @Display(name = "elaCount",addInfoOnly=true,width=60,label="English"),
     @Display(name = "elaScore",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,label="Score",hideLabel=true), 
     @Display(name = "elaPercentage",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,label="Percentage",hideLabel=true), 
     @Display(name = "elaRemarks",addInfoOnly=true,width=80,label="Remarks",hideLabel=true), 
     @Display(name = "mathCount",addInfoOnly=true,width=60,label="Math"), 
     @Display(name = "mathScore",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,hideLabel=true), 
     @Display(name = "mathPercentage",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,hideLabel=true), 
     @Display(name = "mathRemarks",addInfoOnly=true,width=80,hideLabel=true),
     @Display(name = "sciCount",addInfoOnly=true,width=60,label="Science"), 
     @Display(name = "sciScore",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,hideLabel=true), 
     @Display(name = "sciPercentage",duties={"CAN EDIT ADMISSION SCORE"},addInfoOnly=true,width=60,hideLabel=true), 
     @Display(name = "sciRemarks",addInfoOnly=true,width=80,hideLabel=true),
     
//     @Display(name = "pracCount",addInfoOnly=true,width=60,label="Prac"), 
//     @Display(name = "pracScore",addInfoOnly=true,width=60,hideLabel=true), 
//     @Display(name = "pracPercentage",addInfoOnly=true,width=60,hideLabel=true), 
//     @Display(name = "pracRemarks",addInfoOnly=true,width=80,hideLabel=true),
//     @Display(name = "itemTotal",addInfoOnly=true,width=60,label="Total Items"), 
//     @Display(name = "itemScore",addInfoOnly=true,width=60,hideLabel=true), 
//     @Display(name = "itemPercentage",addInfoOnly=true,width=60,hideLabel=true), 
//     @Display(name = "totalRemarks",addInfoOnly=true,width=80,hideLabel=true),
     @Display(name = "finalRemarks",addInfoOnly=true,gridFieldWidth= 7 ,width = -1),
     
    @Display(name = "forImprovementIn", addInfoOnly = true,gridFieldWidth=7,upCase=false,width=-1),
    @Display(name = "recommendedRemedialEla", addInfoOnly = true, label = "English"),
    @Display(name = "recommendedRemedialReading", addInfoOnly = true, label = "Reading"),
    @Display(name = "recommendedRemedialWriting", addInfoOnly = true, label = "Writing"),
    @Display(name = "recommendedRemedialMath", addInfoOnly = true, label = "Math"),
    @Display(name = "recommendedRemedialSci", addInfoOnly = true, label = "Science"),
    @Display(name = "recommendedRemedialFil", addInfoOnly = true, label = "Filipino"),
    @Display(name = "recommendedRemedialSS", addInfoOnly = true, label = "Social Studies"),
    @Display(name = "recommendedRemedialCon", addInfoOnly = true, label = "Conduct", gridFieldWidth=3),
    @Display(name = "betterQualifiedLevel", addInfoOnly = true, type = "PopSearch", linktoBean = GradeLevel.class ,label = "Better for", gridFieldWidth=5, width = -1),
     
    @Display(name = "recommendation", addInfoOnly = true,type="Combo",modelCombo={"ACCEPTED","ACCEPTED ON CONDITION","NOT ACCEPTED"},labelTop=true),
    @Display(name = "recommendationCondition", addInfoOnly = true,label="Condition",labelTop=true,width=250, upCase=false),
    
        
    @Display(name = "threeColoredIdPictures",duties={"CAN EDIT ADMISSION DETAIL"}, label = "Three (3) colored ID Pictures (1x1)", addInfoOnly = true, gridFieldWidth = 3),
    @Display(name = "birthCertificateNsoCopy",duties={"CAN EDIT ADMISSION DETAIL"}, label = "Birth Certificate (NSO Copy)", addInfoOnly = true, gridFieldWidth = 3),
    @Display(name = "baptismalCertificate",duties={"CAN EDIT ADMISSION DETAIL"}, addInfoOnly = true, gridFieldWidth = 3),
    @Display(name = "latestReportCard",duties={"CAN EDIT ADMISSION DETAIL"}, label = "Latest Report Card", addInfoOnly = true, gridFieldWidth = 3),
    @Display(name = "originalReportCard",duties={"CAN EDIT ADMISSION DETAIL"}, label = "Certified true Copy of Form 137", addInfoOnly = true, gridFieldWidth = 3),
    @Display(name = "goodMoral",duties={"CAN EDIT ADMISSION DETAIL"}, label = "Good Moral/Certificate of Eligibility to Transfer", addInfoOnly = true, gridFieldWidth = 3),
    @Display(name = "letterOfRecommendation",duties={"CAN EDIT ADMISSION DETAIL"}, label = "Letter of Recommendation from the principal / guidance counselor", addInfoOnly = true, gridFieldWidth = 3),
    @Display(name = "copyOfAcr",duties={"CAN EDIT ADMISSION DETAIL"}, label = "Copy of ACR", addInfoOnly = true, gridFieldWidth = 3),
    @Display(name = "passportForVerification",duties={"CAN EDIT ADMISSION DETAIL"}, addInfoOnly = true, gridFieldWidth = 3),
    @Display(name = "completeScholasticRecords",duties={"CAN EDIT ADMISSION DETAIL"}, addInfoOnly = true, label = "Complete Scholastic Records", gridFieldWidth = 3),
    @Display(name = "studyPermit",duties={"CAN EDIT ADMISSION DETAIL"}, addInfoOnly = true, gridFieldWidth = 3)

//        @Display(name="admissionId"),
//        @Display(name="invoiceId"),
//        @Display(name="birthDate"),
//        @Display(name="personId")
})
@ActionButtons( {
		@ActionButton(name = "btnSummaryofApplicant", label = "All Applicants"),
		@ActionButton(name = "btnTestApplicants", label = "Test Applicants")
})
@Reports({
   // @template.Report(reportFile="StudentAdmissionReport", reportTitle="Admission Report", reportSql="${personId}"),
   // @template.Report(reportFile="AdmissionSummaryofApplicants", reportTitle="All Applicants", reportSql=""),
    @template.Report(reportFile="AdmissionLevelWaiver", reportTitle="Lowered In Level", reportSql="${seq}"),
    @template.Report(reportFile="AdmissionReport", reportTitle="Student Admission", reportSql="${seq}"),
    @template.Report(reportFile="AcademicWaiver", reportTitle="Waiver", reportSql="${seq}"),
    @template.Report(reportFile="ExaminationPermit", reportTitle="Permit", reportSql="${seq}"),
    @template.Report(reportFile="AdmissionSlip", reportTitle="Admission Slip", reportSql="${seq}"),
    @template.Report(reportFile="STAR", reportTitle="STAR", reportSql=""),
    @template.Report(reportFile="IncompleteRequirement", reportTitle="Inc. Req.", reportSql=""),
    @template.Report(reportFile="ForeignIncompleteRequirement", reportTitle="Inc. Req Foreigner", reportSql="")
   
 
})

public class Admission extends AbstractIBean implements Serializable {
    public Admission() {
        accountNumber = "102";
        cashier = UserInfo.getUserName();
        orDate = constants.Constants.useDate;
//        orNumber = new CashierDailyBooklet().extractNextOR()+"";
        schoolYear = springbean.SchoolConfig.getSchoolYear();
    }

    @Override
    public void save() {
        super.save();
    }
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "invoiceId")
    public int invoiceId;
    @Column(name = "lastName", nullable = false)
    public String lastName;
    @Column(name = "middleInitial")
    public String middleInitial;
    @Column(name = "firstName", nullable = false)
    public String firstName;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "branchCode")
    public String branchCode;
    @Column(name = "previousGradeLevel")
    public String  previousGradeLevel;
    @Column(name = "course")
    public String course;
    @Column(name = "gradeLevel")
    public String gradeLevel;
    @Column(name = "address")
    public String address;
    @Column(name = "telNum")
    public String telNum;
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "birthDate")
    public Date birthDate;
    @Column(name = "age")
    public int age;
    @Column(name = "birthPlace")
    public String birthPlace;
    @Column(name = "gender")
    public String gender;
    @Column(name = "nationality")
    public String nationality;
    @Column(name = "ifAlienAcrNo")
    public String ifAlienAcrNo;
    @Column(name = "foreigner")
    public boolean foreigner;
    @Column(name = "acrPlaceIssued")
    public String acrPlaceIssued;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "acrDateIssued")
    public Date acrDateIssued;
    @Column(name = "religion")
    public String religion;
    @Column(name = "otherReligion")
    public String otherReligion;
    @Column(name = "schoolLastAttended")
    public String schoolLastAttended;
    @Column(name = "schoolLastAttendedAddress")
    public String schoolLastAttendedAddress;
    @Column(name = "reasonForTransfer")
    public String reasonForTransfer;
    
    
    @Column(name = "father")
    public String father;
    @Column(name = "fatherOccupation")
    public String fatherOccupation;
    @Column(name = "fatherCompany")
    public String fatherCompany;
    @Column(name = "fatherCompanyAddress")
    public String fatherCompanyAddress;
    @Column(name = "fatherCompanyTelNo")
    public String fatherCompanyTelNo;
    @Column(name = "mother")
    public String mother;
    @Column(name = "motherOccupation")
    public String motherOccupation;
    @Column(name = "motherCompany")
    public String motherCompany;
    @Column(name = "motherCompanyAddress")
    public String motherCompanyAddress;
    @Column(name = "motherCompanyTelNo")
    public String motherCompanyTelNo;
    @Column(name = "childName1")
    public String childName1;
    @Column(name = "childName2")
    public String childName2;
    @Column(name = "childName3")
    public String childName3;
    @Column(name = "childGradeSection1")
    public String childGradeSection1;
    @Column(name = "childGradeSection2")
    public String childGradeSection2;
    @Column(name = "childGradeSection3")
    public String childGradeSection3;
    @Column(name = "preschoolAttended")
    public String preschoolAttended;
    @Column(name = "preschoolSchoolYear")
    public String preschoolSchoolYear;
    @Column(name = "preschoolReasonForTransfer")
    public String preschoolReasonForTransfer;
    @Column(name = "elementaryAttended")
    public String elementaryAttended;
    @Column(name = "elementarySchoolYear")
    public String elementarySchoolYear;
    @Column(name = "elementaryReasonForTransfer")
    public String elementaryReasonForTransfer;
    @Column(name = "highschoolAttended")
    public String highschoolAttended;
    @Column(name = "highschoolSchoolYear")
    public String highschoolSchoolYear;
    @Column(name = "highschoolReasonForTransfer")
    public String highschoolReasonForTransfer;
    @Column(name = "birthCertificateNsoCopy")
    public boolean birthCertificateNsoCopy;
    @Column(name = "baptismalCertificate")
    public boolean baptismalCertificate;
    @Column(name = "latestReportCard")
    public boolean latestReportCard;
    @Column(name = "threeColoredIdPictures")
    public boolean threeColoredIdPictures;
    @Column(name = "originalReportCard")
    public boolean originalReportCard;
    @Column(name = "letterOfRecommendation")
    public boolean letterOfRecommendation;
    @Column(name = "copyOfAcr")
    public boolean copyOfAcr;
    @Column(name = "passportForVerification")
    public boolean passportForVerification;
    @Column(name = "completeScholasticRecords")
    public boolean completeScholasticRecords;
    @Column(name = "goodMoral")
    public boolean goodMoral;
    @Column(name = "studyPermit")
    public boolean studyPermit;
    @Column(name = "prevSchool")
    public String prevSchool;
    @Column(name = "forImprovementIn")
    public String forImprovementIn;
    @Column(name = "gpaEla")
    public String gpaEla;
    @Column(name = "gpaMath")
    public String gpaMath;
    @Column(name = "gpaSci")
    public String gpaSci;
    @Column(name = "gpaFil")
    public String gpaFil;
    @Column(name = "gpaSs")
    public String gpaSs;
    @Column(name = "gpaAve")
    public String gpaAve;
    @Column(name = "gpaCon")
    public String gpaCon;
    @Column(name = "examinationFee")
    public double examinationFee;
    @Column(name = "applicationDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date applicationDate;
    @Column(name = "orNumber")
    public String orNumber;
    @Column(name = "orType")
    public String orType;
    @Column(name = "examinationDate")
    @Temporal(value = TemporalType.DATE)
    public Date examinationDate;
    @Column(name = "examTime")
    public String examTime;
    @Column(name = "roomAssign")
    public String roomAssign;
    @Column(name = "elaCount")
    public int elaCount;
    @Column(name = "elaScore")
    public int elaScore;
    @Column(name = "elaPercentage")
    public double elaPercentage;
    @Column(name = "elaRemarks")
    public String elaRemarks;
    
    @Column(name = "mathCount")
    public int mathCount;
    @Column(name = "mathScore")
    public int mathScore;
    @Column(name = "mathPercentage")
    public double mathPercentage;
    @Column(name = "mathRemarks")
    public String mathRemarks;

    @Column(name = "pracCount")
    public int pracCount;
    @Column(name = "pracScore")
    public int pracScore;
    @Column(name = "pracPercentage")
    public double pracPercentage;
    @Column(name = "pracRemarks")
    public String pracRemarks;

    @Column(name = "cognitiveCount")
    public int cognitiveCount;
    @Column(name = "cognitiveScore")
    public int cognitiveScore;
    @Column(name = "cognitivePercentage")
    public double cognitivePercentage;
    @Column(name = "cognitiveRemarks")
    public String cognitiveRemarks;

    @Column(name = "psychomotorCount")
    public int psychomotorCount;
    @Column(name = "psychomotorScore")
    public int psychomotorScore;
    @Column(name = "psychomotorPercentage")
    public double psychomotorPercentage;
    @Column(name = "psychomotorRemarks")
    public String psychomotorRemarks;

    @Column(name = "affectiveCount")
    public int affectiveCount;
    @Column(name = "affectiveScore")
    public int affectiveScore;
    @Column(name = "affectivePercentage")
    public double affectivePercentage;
    @Column(name = "affectiveRemarks")
    public String affectiveRemarks;
    @Column(name = "finalRemarks")
    public String finalRemarks;

    @Column(name = "sciCount")
    public int sciCount;
    @Column(name = "sciScore")
    public int sciScore;
    @Column(name = "sciPercentage")
    public double sciPercentage;
    @Column(name = "sciRemarks")
    public String sciRemarks;

    @Column(name = "itemTotal")
    public int itemTotal;
    @Column(name = "itemPercentage")
    public double itemPercentage;
    @Column(name = "itemScore")
    public int itemScore;
    @Column(name = "totalRemarks")
    public String totalRemarks;
    
    @Column(name = "recommendation")
    public String recommendation;
    @Column(name = "recommendationCondition")
    public String recommendationCondition;
            
            
    @Column(name = "evaluationDate")
    @Temporal(value = TemporalType.DATE)
    public Date evaluationDate;
    @Column(name = "evaluation")
    public String evaluation;
    @Column(name = "status")
    public String status;
    @Column(name = "enrolledDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date enrolledDate;
    
    @Column(name = "recommendedRemedialReading")
    public boolean recommendedRemedialReading;
    @Column(name = "recommendedRemedialWriting")
    public boolean recommendedRemedialWriting;
    @Column(name = "recommendedRemedialEla")
    public boolean recommendedRemedialEla;
    @Column(name = "recommendedRemedialMath")
    public boolean recommendedRemedialMath;
    @Column(name = "recommendedRemedialSci")
    public boolean recommendedRemedialSci;
    @Column(name = "recommendedRemedialFil")
    public boolean recommendedRemedialFil;
    @Column(name = "recommendedRemedialSS")
    public boolean recommendedRemedialSS;
    @Column(name = "recommendedRemedialCon")
    public boolean recommendedRemedialCon;
    @Column(name = "betterQualifiedLevel")
    public String betterQualifiedLevel;
    //OLD FIELDS
    //    @Column(name = "prevSchoolAdd")
//    public String prevSchoolAdd;
    @Column(name = "rating")
    public int rating;
    @Column(name = "remarks")
    public String remarks;
    @Column(name = "section")
    public String section;
    @Column(name = "personId")
    public int personId;
    @Column(name = "accountNumber")
    public String accountNumber;
    @Column(name = "processedBy")
    public int processedBy;
    @Column(name = "gpaTes")
    public String gpaTes;
    @Column(name = "gpaPra")
    public String gpaPra;
    @Column(name = "applicationStatus")
    public String applicationStatus;
    @Column(name = "cashier")
    public String cashier;
    @Column(name = "studentNumber")
    public String studentNumber;
    @Column(name = "examinerName")
    public String examinerName;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "orDate")
    public Date orDate;
    @Column(name = "barangay")
    public String barangay;
    @Column(name = "municipal")
    public String municipal;
    @Column(name = "province")
    public String province;
    @Column(name = "zipcode")
    public String zipcode;
    @Column(name = "mobileNum")
    public String mobileNum;
    @Column(name = "hobby")
    public String hobby;
    @Column(name = "talents")
    public String talents;

    public boolean isForeigner() {
        return foreigner;
    }

    public void setForeigner(boolean foreigner) {
        this.foreigner = foreigner;
    }

    public String getForImprovementIn() {
        return forImprovementIn;
    }

    public void setForImprovementIn(String forImprovementIn) {
        this.forImprovementIn = forImprovementIn;
    }

    
    public boolean isRecommendedRemedialReading() {
        return recommendedRemedialReading;
    }

    public void setRecommendedRemedialReading(boolean recommendedRemedialReading) {
        this.recommendedRemedialReading = recommendedRemedialReading;
    }

    public boolean isRecommendedRemedialWriting() {
        return recommendedRemedialWriting;
    }

    public void setRecommendedRemedialWriting(boolean recommendedRemedialWriting) {
        this.recommendedRemedialWriting = recommendedRemedialWriting;
    }

    
    public String getBetterQualifiedLevel() {
        return betterQualifiedLevel;
    }

    public void setBetterQualifiedLevel(String betterQualifiedLevel) {
        this.betterQualifiedLevel = betterQualifiedLevel;
    }

    
    
    public int getAffectiveCount() {
        return affectiveCount;
    }

    public void setAffectiveCount(int affectiveCount) {
        this.affectiveCount = affectiveCount;
    }

    public double getAffectivePercentage() {
        return affectivePercentage;
    }

    public void setAffectivePercentage(double affectivePercentage) {
        this.affectivePercentage = affectivePercentage;
    }

    public String getAffectiveRemarks() {
        return affectiveRemarks;
    }

    public void setAffectiveRemarks(String affectiveRemarks) {
        this.affectiveRemarks = affectiveRemarks;
    }

    public int getAffectiveScore() {
        return affectiveScore;
    }

    public void setAffectiveScore(int affectiveScore) {
        this.affectiveScore = affectiveScore;
    }

    public int getCognitiveCount() {
        return cognitiveCount;
    }

    public void setCognitiveCount(int cognitiveCount) {
        this.cognitiveCount = cognitiveCount;
    }

    public double getCognitivePercentage() {
        return cognitivePercentage;
    }

    public void setCognitivePercentage(double cognitivePercentage) {
        this.cognitivePercentage = cognitivePercentage;
    }

    public String getCognitiveRemarks() {
        return cognitiveRemarks;
    }

    public void setCognitiveRemarks(String cognitiveRemarks) {
        this.cognitiveRemarks = cognitiveRemarks;
    }

    public int getCognitiveScore() {
        return cognitiveScore;
    }

    public void setCognitiveScore(int cognitiveScore) {
        this.cognitiveScore = cognitiveScore;
    }

    public String getFinalRemarks() {
        return finalRemarks;
    }

    public void setFinalRemarks(String finalRemarks) {
        this.finalRemarks = finalRemarks;
    }

    public boolean isGoodMoral() {
        return goodMoral;
    }

    public void setGoodMoral(boolean goodMoral) {
        this.goodMoral = goodMoral;
    }

    public int getPsychomotorCount() {
        return psychomotorCount;
    }

    public void setPsychomotorCount(int psychomotorCount) {
        this.psychomotorCount = psychomotorCount;
    }

    public double getPsychomotorPercentage() {
        return psychomotorPercentage;
    }

    public void setPsychomotorPercentage(double psychomotorPercentage) {
        this.psychomotorPercentage = psychomotorPercentage;
    }

    public String getPsychomotorRemarks() {
        return psychomotorRemarks;
    }

    public void setPsychomotorRemarks(String psychomotorRemarks) {
        this.psychomotorRemarks = psychomotorRemarks;
    }

    public int getPsychomotorScore() {
        return psychomotorScore;
    }

    public void setPsychomotorScore(int psychomotorScore) {
        this.psychomotorScore = psychomotorScore;
    }

   
    
    public int getElaCount() {
        return elaCount;
    }

    public void setElaCount(int elaCount) {
        this.elaCount = elaCount;
    }

    public int getElaScore() {
        return elaScore;
    }

    public void setElaScore(int elaScore) {
        this.elaScore = elaScore;
    }

    public int getItemScore() {
        return itemScore;
    }

    public void setItemScore(int itemScore) {
        this.itemScore = itemScore;
    }

    public int getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(int itemTotal) {
        this.itemTotal = itemTotal;
    }

    public int getMathCount() {
        return mathCount;
    }

    public void setMathCount(int mathCount) {
        this.mathCount = mathCount;
    }

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

    public int getSciCount() {
        return sciCount;
    }

    public void setSciCount(int sciCount) {
        this.sciCount = sciCount;
    }

    public int getSciScore() {
        return sciScore;
    }

    public void setSciScore(int sciScore) {
        this.sciScore = sciScore;
    }
    
    
    
    
    
    
    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "lastName", "firstName","schoolYear","gradeLevel");
    }

    public String getOrType() {
        return orType;
    }

    public void setOrType(String orType) {
        this.orType = orType;
    }

    public Date getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(Date enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    public String getElaRemarks() {
        return elaRemarks;
    }

    public void setElaRemarks(String elaRemarks) {
        this.elaRemarks = elaRemarks;
    }

    public String getMathRemarks() {
        return mathRemarks;
    }

    public void setMathRemarks(String mathRemarks) {
        this.mathRemarks = mathRemarks;
    }

    public String getPreviousGradeLevel() {
        return previousGradeLevel;
    }

    public void setPreviousGradeLevel(String previousGradeLevel) {
        this.previousGradeLevel = previousGradeLevel;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getRecommendationCondition() {
        return recommendationCondition;
    }

    public void setRecommendationCondition(String recommendationCondition) {
        this.recommendationCondition = recommendationCondition;
    }

    public String getSciRemarks() {
        return sciRemarks;
    }

    public void setSciRemarks(String sciRemarks) {
        this.sciRemarks = sciRemarks;
    }

    public boolean isStudyPermit() {
        return studyPermit;
    }

    public void setStudyPermit(boolean studyPermit) {
        this.studyPermit = studyPermit;
    }

    public String getTotalRemarks() {
        return totalRemarks;
    }

    public void setTotalRemarks(String totalRemarks) {
        this.totalRemarks = totalRemarks;
    }
    
    

    
    
    public boolean isRecommendedRemedialCon() {
        return recommendedRemedialCon;
    }

    public void setRecommendedRemedialCon(boolean recommendedRemedialCon) {
        this.recommendedRemedialCon = recommendedRemedialCon;
    }

    public boolean isRecommendedRemedialEla() {
        return recommendedRemedialEla;
    }

    public void setRecommendedRemedialEla(boolean recommendedRemedialEla) {
        this.recommendedRemedialEla = recommendedRemedialEla;
    }

    public boolean isRecommendedRemedialFil() {
        return recommendedRemedialFil;
    }

    public void setRecommendedRemedialFil(boolean recommendedRemedialFil) {
        this.recommendedRemedialFil = recommendedRemedialFil;
    }

    public boolean isRecommendedRemedialMath() {
        return recommendedRemedialMath;
    }

    public void setRecommendedRemedialMath(boolean recommendedRemedialMath) {
        this.recommendedRemedialMath = recommendedRemedialMath;
    }

    public boolean isRecommendedRemedialSS() {
        return recommendedRemedialSS;
    }

    public void setRecommendedRemedialSS(boolean recommendedRemedialSS) {
        this.recommendedRemedialSS = recommendedRemedialSS;
    }

    public boolean isRecommendedRemedialSci() {
        return recommendedRemedialSci;
    }

    public void setRecommendedRemedialSci(boolean recommendedRemedialSci) {
        this.recommendedRemedialSci = recommendedRemedialSci;
    }
    
    
    
    

    public Date getAcrDateIssued() {
        return acrDateIssued;
    }

    public void setAcrDateIssued(Date AcrDateIssued) {
        this.acrDateIssued = AcrDateIssued;
    }

    public String getAcrPlaceIssued() {
        return acrPlaceIssued;
    }

    public void setAcrPlaceIssued(String AcrPlaceIssued) {
        this.acrPlaceIssued = AcrPlaceIssued;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getChildGradeSection1() {
        return childGradeSection1;
    }

    public void setChildGradeSection1(String childGradeSection1) {
        this.childGradeSection1 = childGradeSection1;
    }

    public String getChildGradeSection2() {
        return childGradeSection2;
    }

    public void setChildGradeSection2(String childGradeSection2) {
        this.childGradeSection2 = childGradeSection2;
    }

    public String getChildGradeSection3() {
        return childGradeSection3;
    }

    public void setChildGradeSection3(String childGradeSection3) {
        this.childGradeSection3 = childGradeSection3;
    }

    public String getChildName1() {
        return childName1;
    }

    public void setChildName1(String childName1) {
        this.childName1 = childName1;
    }

    public String getChildName2() {
        return childName2;
    }

    public void setChildName2(String childName2) {
        this.childName2 = childName2;
    }

    public String getChildName3() {
        return childName3;
    }

    public void setChildName3(String childName3) {
        this.childName3 = childName3;
    }

    

   

    public double getElaPercentage() {
        return elaPercentage;
    }

    public void setElaPercentage(double elaPercentage) {
        this.elaPercentage = elaPercentage;
    }

    public String getGpaAve() {
        return gpaAve;
    }

    public void setGpaAve(String gpaAve) {
        this.gpaAve = gpaAve;
    }

    public String getGpaEla() {
        return gpaEla;
    }

    public void setGpaEla(String gpaEla) {
        this.gpaEla = gpaEla;
    }

    public String getGpaFil() {
        return gpaFil;
    }

    public void setGpaFil(String gpaFil) {
        this.gpaFil = gpaFil;
    }

    public String getGpaMath() {
        return gpaMath;
    }

    public void setGpaMath(String gpaMath) {
        this.gpaMath = gpaMath;
    }

    public String getGpaSci() {
        return gpaSci;
    }

    public void setGpaSci(String gpaSci) {
        this.gpaSci = gpaSci;
    }

    public String getGpaSs() {
        return gpaSs;
    }

    public void setGpaSs(String gpaSs) {
        this.gpaSs = gpaSs;
    }


    public String getElementaryAttended() {
        return elementaryAttended;
    }

    public void setElementaryAttended(String elementaryAttended) {
        this.elementaryAttended = elementaryAttended;
    }

    public String getElementaryReasonForTransfer() {
        return elementaryReasonForTransfer;
    }

    public void setElementaryReasonForTransfer(String elementaryReasonForTransfer) {
        this.elementaryReasonForTransfer = elementaryReasonForTransfer;
    }

    public String getElementarySchoolYear() {
        return elementarySchoolYear;
    }

    public void setElementarySchoolYear(String elementarySchoolYear) {
        this.elementarySchoolYear = elementarySchoolYear;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getFatherCompany() {
        return fatherCompany;
    }

    public void setFatherCompany(String fatherCompany) {
        this.fatherCompany = fatherCompany;
    }

    public String getFatherCompanyAddress() {
        return fatherCompanyAddress;
    }

    public void setFatherCompanyAddress(String fatherCompanyAddress) {
        this.fatherCompanyAddress = fatherCompanyAddress;
    }

    public String getFatherCompanyTelNo() {
        return fatherCompanyTelNo;
    }

    public void setFatherCompanyTelNo(String fatherCompanyTelNo) {
        this.fatherCompanyTelNo = fatherCompanyTelNo;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   
    public String getHighschoolAttended() {
        return highschoolAttended;
    }

    public void setHighschoolAttended(String highschoolAttended) {
        this.highschoolAttended = highschoolAttended;
    }

    public String getHighschoolReasonForTransfer() {
        return highschoolReasonForTransfer;
    }

    public void setHighschoolReasonForTransfer(String highschoolReasonForTransfer) {
        this.highschoolReasonForTransfer = highschoolReasonForTransfer;
    }

    public String getHighschoolSchoolYear() {
        return highschoolSchoolYear;
    }

    public void setHighschoolSchoolYear(String highschoolSchoolYear) {
        this.highschoolSchoolYear = highschoolSchoolYear;
    }

    public String getIfAlienAcrNo() {
        return ifAlienAcrNo;
    }

    public void setIfAlienAcrNo(String ifAlienAcrNo) {
        this.ifAlienAcrNo = ifAlienAcrNo;
    }

    public double getItemPercentage() {
        return itemPercentage;
    }

    public void setItemPercentage(double itemPercentage) {
        this.itemPercentage = itemPercentage;
    }

   

    public double getMathPercentage() {
        return mathPercentage;
    }

    public void setMathPercentage(double mathPercentage) {
        this.mathPercentage = mathPercentage;
    }

   

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getMotherCompany() {
        return motherCompany;
    }

    public void setMotherCompany(String motherCompany) {
        this.motherCompany = motherCompany;
    }

    public String getMotherCompanyAddress() {
        return motherCompanyAddress;
    }

    public void setMotherCompanyAddress(String motherCompanyAddress) {
        this.motherCompanyAddress = motherCompanyAddress;
    }

    public String getMotherCompanyTelNo() {
        return motherCompanyTelNo;
    }

    public void setMotherCompanyTelNo(String motherCompanyTelNo) {
        this.motherCompanyTelNo = motherCompanyTelNo;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isOriginalReportCard() {
        return originalReportCard;
    }

    public void setOriginalReportCard(boolean originalReportCard) {
        this.originalReportCard = originalReportCard;
    }

    public String getOtherReligion() {
        return otherReligion;
    }

    public void setOtherReligion(String otherReligion) {
        this.otherReligion = otherReligion;
    }

    public String getPreschoolAttended() {
        return preschoolAttended;
    }

    public void setPreschoolAttended(String preschoolAttended) {
        this.preschoolAttended = preschoolAttended;
    }

    public String getPreschoolReasonForTransfer() {
        return preschoolReasonForTransfer;
    }

    public void setPreschoolReasonForTransfer(String preschoolReasonForTransfer) {
        this.preschoolReasonForTransfer = preschoolReasonForTransfer;
    }

    public String getPreschoolSchoolYear() {
        return preschoolSchoolYear;
    }

    public void setPreschoolSchoolYear(String preschoolSchoolYear) {
        this.preschoolSchoolYear = preschoolSchoolYear;
    }

    public String getReasonForTransfer() {
        return reasonForTransfer;
    }

    public void setReasonForTransfer(String reasonForTransfer) {
        this.reasonForTransfer = reasonForTransfer;
    }

    public String getSchoolLastAttended() {
        return schoolLastAttended;
    }

    public void setSchoolLastAttended(String schoolLastAttended) {
        this.schoolLastAttended = schoolLastAttended;
    }

    public String getSchoolLastAttendedAddress() {
        return schoolLastAttendedAddress;
    }

    public void setSchoolLastAttendedAddress(String schoolLastAttendedAddress) {
        this.schoolLastAttendedAddress = schoolLastAttendedAddress;
    }

    

    public double getSciPercentage() {
        return sciPercentage;
    }

    public void setSciPercentage(double sciPercentage) {
        this.sciPercentage = sciPercentage;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getRoomAssign() {
        return roomAssign;
    }

    public void setRoomAssign(String roomAssign) {
        this.roomAssign = roomAssign;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getExaminerName() {
        return examinerName;
    }

    public void setExaminerName(String examinerName) {
        this.examinerName = examinerName;
    }

    public String getGpaPra() {
        return gpaPra;
    }

    public void setGpaPra(String gpaPra) {
        this.gpaPra = gpaPra;
    }


    public String getGpaCon() {
        return gpaCon;
    }

    public void setGpaCon(String gpaCon) {
        this.gpaCon = gpaCon;
    }

    public String getGpaTes() {
        return gpaTes;
    }

    public void setGpaTes(String gpaTes) {
        this.gpaTes = gpaTes;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getMunicipal() {
        return municipal;
    }

    public void setMunicipal(String municipal) {
        this.municipal = municipal;
    }

    public Date getOrDate() {
        return orDate;
    }

    public void setOrDate(Date orDate) {
        this.orDate = orDate;
    }

    public String getOrNumber() {
        return orNumber;
    }

    public void setOrNumber(String orNumber) {
        this.orNumber = orNumber;
    }

    public String getPrevSchool() {
        return prevSchool;
    }

    public void setPrevSchool(String prevSchool) {
        this.prevSchool = prevSchool;
    }


    public int getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(int processedBy) {
        this.processedBy = processedBy;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getTalents() {
        return talents;
    }

    public void setTalents(String talents) {
        this.talents = talents;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public boolean isBaptismalCertificate() {
        return baptismalCertificate;
    }

    public void setBaptismalCertificate(boolean baptismalCertificate) {
        this.baptismalCertificate = baptismalCertificate;
    }

    public boolean isBirthCertificateNsoCopy() {
        return birthCertificateNsoCopy;
    }

    public void setBirthCertificateNsoCopy(boolean birthCertificateNsoCopy) {
        this.birthCertificateNsoCopy = birthCertificateNsoCopy;
    }

    public boolean isCompleteScholasticRecords() {
        return completeScholasticRecords;
    }

    public void setCompleteScholasticRecords(boolean completeScholasticRecords) {
        this.completeScholasticRecords = completeScholasticRecords;
    }

    public boolean isCopyOfAcr() {
        return copyOfAcr;
    }

    public void setCopyOfAcr(boolean copyOfAcr) {
        this.copyOfAcr = copyOfAcr;
    }

    public boolean isLatestReportCard() {
        return latestReportCard;
    }

    public void setLatestReportCard(boolean latestReportCard) {
        this.latestReportCard = latestReportCard;
    }

    public boolean isLetterOfRecommendation() {
        return letterOfRecommendation;
    }

    public void setLetterOfRecommendation(boolean letterOfRecommendation) {
        this.letterOfRecommendation = letterOfRecommendation;
    }

    public boolean isPassportForVerification() {
        return passportForVerification;
    }

    public void setPassportForVerification(boolean passportForVerification) {
        this.passportForVerification = passportForVerification;
    }

    public boolean isThreeColoredIdPictures() {
        return threeColoredIdPictures;
    }

    public void setThreeColoredIdPictures(boolean threeColoredIdPictures) {
        this.threeColoredIdPictures = threeColoredIdPictures;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    public double getExaminationFee() {
        return examinationFee;
    }

    public void setExaminationFee(double examinationFee) {
        this.examinationFee = examinationFee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getPracCount() {
		return pracCount;
	}

	public void setPracCount(int pracCount) {
		this.pracCount = pracCount;
	}

	public int getPracScore() {
		return pracScore;
	}

	public void setPracScore(int pracScore) {
		this.pracScore = pracScore;
	}

	public double getPracPercentage() {
		return pracPercentage;
	}

	public void setPracPercentage(double pracPercentage) {
		this.pracPercentage = pracPercentage;
	}

	public String getPracRemarks() {
		return pracRemarks;
	}

	public void setPracRemarks(String pracRemarks) {
		this.pracRemarks = pracRemarks;
	}

	public boolean isAlreadyRegistered() {
        if (seq != null && seq > 0) {
            Student student = (Student) firstRecord("SELECT a FROM Student a WHERE a.lastName='"+this.lastName+"' AND a.firstName='"+this.firstName+"'");
            return student != null;
        }
        return true;
    }
    public Invoice extractInvoice() {
        if (this.invoiceId == 0) {
            return null;
        }
        Invoice inv = (Invoice) firstRecord("SELECT a FROM Invoice a WHERE a.seq="+this.invoiceId);
        return inv;
    }
    public Person extractCustomer() {
        Person p = new Person();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setMiddleInitial(middleInitial);
        return p;
    }

    @Override
    public String orderBy() {
        return "a.lastName";
    }
       @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return BeanUtil.concat(this.lastName,", ",this.firstName);
    }

    @Override
    public Vector allChart() {
        java.util.Vector vec = new java.util.Vector();
        vec.add(ChartBean.getNativeBarInstance(this, "Total Passed/Failed Per Year","SELECT "+DateUtil.getSQLYear("a.examinationDate")+",a.remarks, COUNT(a.seq) FROM Admission a GROUP BY "+DateUtil.getSQLYear("a.examinationDate")+",a.remarks"));
        vec.add(ChartBean.getNativePieInstance(this, "Ave. Rating Per Year","SELECT "+DateUtil.getSQLYear("a.examinationDate")+", AVG(a.rating) FROM Admission a GROUP BY "+DateUtil.getSQLYear("a.examinationDate")));
        vec.add(ChartBean.getNativePieInstance(this, "Exam. Fee Per Year", "SELECT "+DateUtil.getSQLYear("a.examinationDate")+", SUM(a.examinationFee) FROM Admission a GROUP BY "+DateUtil.getSQLYear("a.examinationDate")));
        return vec;
    }
    
    public static void main(String[] args) {
        view(Admission.class);
    }

    public void register() {
    	CallService.callService(this, 1, Admission.class.getName());
    }
    
    private void serverRegister() {
    	Admission admission = this;
        bean.Student studTmp = (bean.Student) AbstractIBean.firstRecord("SELECT a FROM Student a WHERE a.lastName='" + admission.getLastName() + "' AND a.firstName='" + admission.getFirstName() + "' AND a.middleInitial='" + admission.getMiddleInitial() + "'");
        if (studTmp == null || studTmp.getPersonId() == null || studTmp.getPersonId() == 0) {
            Student student = new Student();
            student.copyFrom(admission);
            student.setDefaultStudentNumber();
            student.personId = null;
            student.save();
            
            PersonDependent father = student.extractOrCreateDependent("FATHER");
            father.lastName = admission.father;
            father.companyName = admission.fatherCompany;
            father.companyAddress = admission.fatherCompanyAddress;
            father.telephoneNumber = admission.fatherCompanyTelNo;
            father.occupation = admission.fatherOccupation;
            father.save();
            
            PersonDependent mother = student.extractOrCreateDependent("MOTHER");
            mother.lastName = admission.mother;
            mother.companyName = admission.motherCompany;
            mother.companyAddress = admission.motherCompanyAddress;
            mother.telephoneNumber = admission.motherCompanyTelNo;
            mother.occupation = admission.motherOccupation;
            mother.save();

            student.extractOrCreateDependent("GUARDIAN");

            admission.personId = student.personId;
            admission.studentNumber = student.studentNumber;
            admission.save();
            
            new SchoolDefaultProcess().createAllSubjects(student);
        } else {
            studTmp.course = admission.course;
            studTmp.setDefaultStudentNumber();
            studTmp.save();

            admission.personId = studTmp.personId;
            admission.studentNumber = studTmp.studentNumber;
            admission.save();
        }
    }
    
	@Override
	public ReturnStruct callService(ParamStruct param) {
		// TODO Auto-generated method stub
		super.callService(param);
		Admission ad = (Admission) param.getData();
		ad.serverRegister();
		return null;
	} 

	@Override
	public void setupIndex() {
		runIndex(1, "lastName", "firstName");
		runUniqueIndex(2, "lastName","firstName","middleInitial");
	}
}
