/*
 * Person.java
 * 
 * Created on Oct 26, 2007, 9:34:48 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "PersonInformation")
@UITemplate(gridCount = 6, columnSearch = {"applicationDate"})
@Displays({
    @Display(name = "applicationDate"),
    @Display(name = "dateAvailable", gridFieldWidth = 3),
    @Display(name = "languageSpoken"),
    @Display(name = "languageRead"),
    @Display(name = "languageWritten"),
    @Display(name = "daysContactNumber"),
    @Display(name = "eveningsContactNumber"),
    @Display(name = "bestTimeToCall"),
    
    @Display(name = "howLongLivedToAddress", label = "How long have you lived at the above address?"),
    @Display(name = "permanentAddress", label = "Address if diff. than present"),
    @Display(name = "permanentPhoneNumber", label = "Phone # if diff. than present"),
    
    @Display(name = "personExperienceAlreadySignedAContractForNextYear", gridFieldWidth=3,label = "Already signed a contract for next year?"),
    
    @Display(name = "personToNotifyName" , label = "Name"),
    @Display(name = "personToNotifyRelation", label = "Relation"),
    @Display(name = "personToNotifyAddress", label = "Address"),
    @Display(name = "personToNotifyContactNumber",label="Contact #"),
    
    @Display(name = "preschool", type = "Combo", modelCombo={"1", "2", "3"}),
    @Display(name = "preschoolGradeSubject", gridFieldWidth=3, width=-1),
    @Display(name = "elementary", type = "Combo", modelCombo={"1", "2", "3"}),
    @Display(name = "elementaryGradeSubject", gridFieldWidth=3, width=-1),
    @Display(name = "highSchool", type = "Combo", modelCombo={"1", "2", "3"}),
    @Display(name = "highSchoolGradeSubject", gridFieldWidth=3, width=-1),
 
    @Display(name = "fullTime", type = "CheckBox"),
    @Display(name = "partTime", type = "CheckBox"),
    @Display(name = "substitute", type = "CheckBox"),
    
    @Display(name = "howDidYouLearnedAboutThePosition", type = "TextArea", gridFieldWidth=5, width = -1),
    @Display(name = "specialAbilities", type = "TextArea", gridFieldWidth=5, width = -1),
    @Display(name = "futurePlansFiveYearsFromNow", type = "TextArea", gridFieldWidth=5, width = -1),
    
    @Display(name = "trainingMajor", label = "Major Training", gridFieldWidth=5, width=-1),
    @Display(name = "trainingMinor", label = "Minor Training", gridFieldWidth=5, width=-1),
    @Display(name = "trainingCumulativeGradeAverage"),
    @Display(name = "trainingTotalUnitsAfterBachelorsDegree"),
    
    @Display(name = "teachingEducationalAdvantages", label = "Any other educational advantages that you have had, including opportunities for travel",width=400),
    @Display(name = "teachingBookThatHelpedToGrowProfessionally", label = "Any books or articles that you have read recently that have helped you to grow professionally",width= 400),
    
    @Display(name = "fullySupport", label = "I fully support the Statement as written without mental reservation", type = "CheckBox"),
    @Display(name = "supportTheStatementExcept", type= "CheckBox"),
    @Display(name = "placeOfWorshipAttended", label ="Where do you regularly attend worship?"),
    @Display(name = "howManyYearsAttended"),
    @Display(name = "degreeOfRegularity", label = "In what congregational are you involved?"),
    @Display(name = "capableOfTeachingBibleClass", type = "CheckBox"),
    @Display(name = "whatSubjectPreferences", label = "What would be your subject preferences?"),
    @Display(name = "routineOfPersonalBibleStudy", label = "Described your routine of personal Bible study and prayer"),
    @Display(name = "booksHaveReadRecently", label = "Books have read recently that have helped you spiritually?"),
    
    @Display(name = "credentialPassedLicensureExam", label = "Passed Licensure exam?",type = "CheckBox"),
    @Display(name = "credentialPassedLicensureLevel",  label = "If yes, what level?",width=400),
    @Display(name = "credentialValidUntil", label = "Remains valid for",type = "Calendar"),
    @Display(name = "credentialOtherRequirement", label = "What requirements do you lack?", width=400),
    
    @Display(name = "convictedOfCrime", label = "Are you convicted of a crime?",type = "CheckBox", gridFieldWidth=5, width=-1),
    @Display(name = "convictedChargeCourtDateAndDisposition",label ="Explain the charge and court date and disposition", type = "TextArea",gridFieldWidth=5,width= 400, height=200),
    @Display(name = "convictedCrimeFelonyOrMisdemeanor", label ="If yes, was the crime a felony or misdemeanor?",type = "CheckBox", gridFieldWidth=5),
    @Display(name = "convictedServeInJailOrPrison", type = "CheckBox", label ="If yes, did you served any time in jail or prison?", gridFieldWidth=5),
    @Display(name = "convictedWhenWhereAndHowLong", type = "TextArea",label = "If yes, please state when, where and how long?", gridFieldWidth=5 ,width= 400, height=200),
    @Display(name = "convictedRecognizancePendingTrial", type = "CheckBox", label ="Are you currently out on bail or on personal recognizance pending trial?", gridFieldWidth=5),
    @Display(name = "convictedRecognizancePendingTrailDateAndExplain", type = "TextArea", label ="If yes, please explain and give dates", gridFieldWidth=5, width= 400, height=200),
   
    @Display(name = "hadAnyCourseChristianPhilosophyOfEducation",label = "Have you had any course in the Christian Philosophy of Education?", type = "CheckBox", gridFieldWidth=5, width=-1),
    @Display(name = "hadAnyCourseChristianPhilosophyOfEducationWhenAndWhere",label = "If so, when and where?", type = "TextArea", gridFieldWidth=5, width=-1)
   })
   @DisplayGroups({
    @DisplayGroup(title="Applicant Availability", gridCount=4, fields={"applicationDate","dateAvailable","daysContactNumber","eveningsContactNumber","bestTimeToCall","languageSpoken","languageRead","languageWritten"}),
    @DisplayGroup(title="Other Address", gridCount=4, fields={"howLongLivedToAddress","permanentAddress","permanentPhoneNumber"}),
    @DisplayGroup(title="Person to notify in case of emergency", gridCount=4, fields={"personToNotifyName" ,"personToNotifyRelation","personToNotifyAddress","personToNotifyContactNumber"}),
    @DisplayGroup(title="Position Desired: Please indicate 1, 2, 3 choice in the selection.", gridCount=4, fields={"preschool","preschoolGradeSubject","elementary","elementaryGradeSubject","highSchool","highSchoolGradeSubject", "fullTime", "partTime", "substitute","howDidYouLearnedAboutThePosition","specialAbilities","futurePlansFiveYearsFromNow"}),
    @DisplayGroup(title="Training", gridCount=4, fields={"trainingMajor","trainingMinor","trainingCumulativeGradeAverage","trainingTotalUnitsAfterBachelorsDegree"}),
    @DisplayGroup(title="Teaching Advantages", gridCount=2, fields={"teachingEducationalAdvantages","teachingBookThatHelpedToGrowProfessionally"}),
    @DisplayGroup(title="Credentials", gridCount=4, fields={"credentialPassedLicensureExam","credentialPassedLicensureLevel","credentialValidUntil","credentialOtherRequirement"}),
    @DisplayGroup(title="Christian School Preparation", gridCount=4, fields={"hadAnyCourseChristianPhilosophyOfEducation","hadAnyCourseChristianPhilosophyOfEducationWhenAndWhere"}),
    @DisplayGroup(title="statementOfFaith", gridCount=2, fields={"fullySupport","supportTheStatementExcept","placeOfWorshipAttended","howManyYearsAttended","degreeOfRegularity","capableOfTeachingBibleClass","whatSubjectPreferences","routineOfPersonalBibleStudy","booksHaveReadRecently"}),
    @DisplayGroup(title="Crime", gridCount=4, fields={"convictedOfCrime","convictedChargeCourtDateAndDisposition","convictedCrimeFelonyOrMisdemeanor","convictedServeInJailOrPrison","convictedWhenWhereAndHowLong","convictedRecognizancePendingTrial","convictedRecognizancePendingTrailDateAndExplain"})
})
public class PersonInformation extends PersonAttribute{

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "personId", nullable = false)
    public int personId;
    @Column(name = "applicationDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date applicationDate;
    @Column(name = "dateAvailable")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date dateAvailable;
    @Column(name = "daysContactNumber")
    public String daysContactNumber;
    @Column(name = "eveningsContactNumber")
    public String eveningsContactNumber;
    @Column(name = "bestTimeToCall")
    public String bestTimeToCall;
    @Column(name = "howLongLivedToAddress")
    public String howLongLivedToAddress;
    @Column(name = "permanentAddress")
    public String permanentAddress;
    @Column(name = "permanentPhoneNumber")
    public String permanentPhoneNumber;
    @Column(name = "personToNotifyName")
    public String personToNotifyName;
    @Column(name = "personToNotifyAddress")
    public String personToNotifyAddress;
    @Column(name = "personToNotifyRelation")
    public String personToNotifyRelation;
    @Column(name = "personToNotifyContactNumber")
    public String personToNotifyContactNumber;
    @Column(name = "trainingMinor")
    public String trainingMinor;
    @Column(name = "trainingMajor")
    public String trainingMajor;
    @Column(name = "trainingCumulativeGradeAverage")
    public int trainingCumulativeGradeAverage;
    @Column(name = "trainingTotalUnitsAfterBachelorsDegree")
    public int trainingTotalUnitsAfterBachelorsDegree;
    @Column(name = "teachingEducationalAdvantages")
    public String teachingEducationalAdvantages;
    @Column(name = "teachingBookThatHelpedToGrowProfessionally")
    public String teachingBookThatHelpedToGrowProfessionally;
    @Column(name = "credentialPassedLicensureExam")
    public String credentialPassedLicensureExam;
    @Column(name = "credentialPassedLicensureLevel")
    public String credentialPassedLicensureLevel;
    @Column(name = "credentialValidUntil")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date credentialValidUntil;
    @Column(name = "credentialOtherRequirement")
    public String credentialOtherRequirement;
    @Column(name = "personExperienceAlreadySignedAContractForNextYear")
    public boolean personExperienceAlreadySignedAContractForNextYear;
    @Column(name = "convictedOfCrime")
    public boolean convictedOfCrime;
    @Column(name = "convictedChargeCourtDateAndDisposition")
    public String convictedChargeCourtDateAndDisposition;
    @Column(name = "convictedCrimeFelonyOrMisdemeanor")
    public boolean convictedCrimeFelonyOrMisdemeanor;
    @Column(name = "convictedServeInJailOrPrison")
    public boolean convictedServeInJailOrPrison;
    @Column(name = "convictedWhenWhereAndHowLong")
    public String convictedWhenWhereAndHowLong;
    @Column(name = "convictedRecognizancePendingTrial")
    public boolean convictedRecognizancePendingTrial;
    @Column(name = "convictedRecognizancePendingTrailDateAndExplain")
    public String convictedRecognizancePendingTrailDateAndExplain;
    @Column(name = "hadAnyCourseChristianPhilosophyOfEducation")
    public boolean hadAnyCourseChristianPhilosophyOfEducation;
    @Column(name = "hadAnyCourseChristianPhilosophyOfEducationWhenAndWhere")
    public String hadAnyCourseChristianPhilosophyOfEducationWhenAndWhere;
    @Column(name = "languageSpoken")
    public String languageSpoken;
    @Column(name = "languageRead")
    public String languageRead;
    @Column(name = "languageWritten")
    public String languageWritten;
    @Column(name = "preschool")
    public int preschool;
    @Column(name = "elementary")
    public int elementary;
    @Column(name = "highSchool")
    public int highSchool;
    @Column(name = "preschoolGradeSubject")
    public String preschoolGradeSubject;
    @Column(name = "elementaryGradeSubject")
    public String elementaryGradeSubject;
    @Column(name = "highSchoolGradeSubject")
    public String highSchoolGradeSubject;
    @Column(name = "howDidYouLearnedAboutThePosition")
    public String howDidYouLearnedAboutThePosition;
    @Column(name = "fullTime")
    public boolean fullTime;
    @Column(name = "partTime")
    public boolean partTime;
    @Column(name = "substitute")
    public String substitute;
    @Column(name = "specialAbilities")
    public String specialAbilities;
    @Column(name = "futurePlansFiveYearsFromNow")
    public String futurePlansFiveYearsFromNow;
    @Column(name = "fullySupport")
    public boolean fullySupport;
    @Column(name = "placeOfWorshipAttended")
    public String placeOfWorshipAttended;
    @Column(name = "howManyYearsAttended")
    public String howManyYearsAttended;
    @Column(name = "degreeOfRegularity")
    public String degreeOfRegularity;
    @Column(name = "capableOfTeachingBibleClass")
    public boolean capableOfTeachingBibleClass;
    @Column(name = "whatSubjectPreferences")
    public String whatSubjectPreferences;
    @Column(name = "routineOfPersonalBibleStudy")
    public String routineOfPersonalBibleStudy;
    @Column(name = "booksHaveReadRecently")
    public String booksHaveReadRecently;
    @Column(name = "supportTheStatementExcept")
    public Boolean supportTheStatementExcept;

    public String getBooksHaveReadRecently() {
        return booksHaveReadRecently;
    }

    public void setBooksHaveReadRecently(String booksHaveReadRecently) {
        this.booksHaveReadRecently = booksHaveReadRecently;
    }

    public boolean isCapableOfTeachingBibleClass() {
        return capableOfTeachingBibleClass;
    }

    public void setCapableOfTeachingBibleClass(boolean capableOfTeachingBibleClass) {
        this.capableOfTeachingBibleClass = capableOfTeachingBibleClass;
    }

    public String getDegreeOfRegularity() {
        return degreeOfRegularity;
    }

    public void setDegreeOfRegularity(String degreeOfRegularity) {
        this.degreeOfRegularity = degreeOfRegularity;
    }

    public boolean isFullySupport() {
        return fullySupport;
    }

    public void setFullySupport(boolean fullySupport) {
        this.fullySupport = fullySupport;
    }

    public String getHowManyYearsAttended() {
        return howManyYearsAttended;
    }

    public void setHowManyYearsAttended(String howManyYearsAttended) {
        this.howManyYearsAttended = howManyYearsAttended;
    }

    public String getPlaceOfWorshipAttended() {
        return placeOfWorshipAttended;
    }

    public void setPlaceOfWorshipAttended(String placeOfWorshipAttended) {
        this.placeOfWorshipAttended = placeOfWorshipAttended;
    }

    public String getRoutineOfPersonalBibleStudy() {
        return routineOfPersonalBibleStudy;
    }

    public void setRoutineOfPersonalBibleStudy(String routineOfPersonalBibleStudy) {
        this.routineOfPersonalBibleStudy = routineOfPersonalBibleStudy;
    }

    public Boolean getSupportTheStatementExcept() {
        return supportTheStatementExcept;
    }

    public void setSupportTheStatementExcept(Boolean supportTheStatementExcept) {
        this.supportTheStatementExcept = supportTheStatementExcept;
    }

    public String getWhatSubjectPreferences() {
        return whatSubjectPreferences;
    }

    public void setWhatSubjectPreferences(String whatSubjectPreferences) {
        this.whatSubjectPreferences = whatSubjectPreferences;
    }
   
    public int getElementary() {
        return elementary;
    }

    public void setElementary(int elementary) {
        this.elementary = elementary;
    }

    public String getElementaryGradeSubject() {
        return elementaryGradeSubject;
    }

    public void setElementaryGradeSubject(String elementaryGradeSubject) {
        this.elementaryGradeSubject = elementaryGradeSubject;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public String getFuturePlansFiveYearsFromNow() {
        return futurePlansFiveYearsFromNow;
    }

    public void setFuturePlansFiveYearsFromNow(String futurePlansFiveYearsFromNow) {
        this.futurePlansFiveYearsFromNow = futurePlansFiveYearsFromNow;
    }

    public int getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(int highSchool) {
        this.highSchool = highSchool;
    }

    public String getHighSchoolGradeSubject() {
        return highSchoolGradeSubject;
    }

    public void setHighSchoolGradeSubject(String highSchoolGradeSubject) {
        this.highSchoolGradeSubject = highSchoolGradeSubject;
    }

    public String getHowDidYouLearnedAboutThePosition() {
        return howDidYouLearnedAboutThePosition;
    }

    public void setHowDidYouLearnedAboutThePosition(String howDidYouLearnedAboutThePosition) {
        this.howDidYouLearnedAboutThePosition = howDidYouLearnedAboutThePosition;
    }

    public boolean isPartTime() {
        return partTime;
    }

    public void setPartTime(boolean partTime) {
        this.partTime = partTime;
    }

    public int getPreschool() {
        return preschool;
    }

    public void setPreschool(int preschool) {
        this.preschool = preschool;
    }

    public String getPreschoolGradeSubject() {
        return preschoolGradeSubject;
    }

    public void setPreschoolGradeSubject(String preschoolGradeSubject) {
        this.preschoolGradeSubject = preschoolGradeSubject;
    }

    public String getSpecialAbilities() {
        return specialAbilities;
    }

    public void setSpecialAbilities(String specialAbilities) {
        this.specialAbilities = specialAbilities;
    }

    public String getSubstitute() {
        return substitute;
    }

    public void setSubstitute(String substitute) {
        this.substitute = substitute;
    }

    public String getLanguageRead() {
        return languageRead;
    }

    public void setLanguageRead(String languageRead) {
        this.languageRead = languageRead;
    }

    public String getLanguageSpoken() {
        return languageSpoken;
    }

    public void setLanguageSpoken(String languageSpoken) {
        this.languageSpoken = languageSpoken;
    }

    public String getLanguageWritten() {
        return languageWritten;
    }

    public void setLanguageWritten(String languageWritten) {
        this.languageWritten = languageWritten;
    }

    public boolean isPersonExperienceAlreadySignedAContractForNextYear() {
        return personExperienceAlreadySignedAContractForNextYear;
    }

    public void setPersonExperienceAlreadySignedAContractForNextYear(boolean personExperienceAlreadySignedAContractForNextYear) {
        this.personExperienceAlreadySignedAContractForNextYear = personExperienceAlreadySignedAContractForNextYear;
    }

    public boolean isHadAnyCourseChristianPhilosophyOfEducation() {
        return hadAnyCourseChristianPhilosophyOfEducation;
    }

    public void setHadAnyCourseChristianPhilosophyOfEducation(boolean hadAnyCourseChristianPhilosophyOfEducation) {
        this.hadAnyCourseChristianPhilosophyOfEducation = hadAnyCourseChristianPhilosophyOfEducation;
    }

    public String getHadAnyCourseChristianPhilosophyOfEducationWhenAndWhere() {
        return hadAnyCourseChristianPhilosophyOfEducationWhenAndWhere;
    }

    public void setHadAnyCourseChristianPhilosophyOfEducationWhenAndWhere(String hadAnyCourseChristianPhilosophyOfEducationWhenAndWhere) {
        this.hadAnyCourseChristianPhilosophyOfEducationWhenAndWhere = hadAnyCourseChristianPhilosophyOfEducationWhenAndWhere;
    }

    public String getConvictedChargeCourtDateAndDisposition() {
        return convictedChargeCourtDateAndDisposition;
    }

    public void setConvictedChargeCourtDateAndDisposition(String convictedChargeCourtDateAndDisposition) {
        this.convictedChargeCourtDateAndDisposition = convictedChargeCourtDateAndDisposition;
    }

    public boolean isConvictedCrimeFelonyOrMisdemeanor() {
        return convictedCrimeFelonyOrMisdemeanor;
    }

    public void setConvictedCrimeFelonyOrMisdemeanor(boolean convictedCrimeFelonyOrMisdemeanor) {
        this.convictedCrimeFelonyOrMisdemeanor = convictedCrimeFelonyOrMisdemeanor;
    }

    public boolean isConvictedOfCrime() {
        return convictedOfCrime;
    }

    public void setConvictedOfCrime(boolean convictedOfCrime) {
        this.convictedOfCrime = convictedOfCrime;
    }

    public String getConvictedRecognizancePendingTrailDateAndExplain() {
        return convictedRecognizancePendingTrailDateAndExplain;
    }

    public void setConvictedRecognizancePendingTrailDateAndExplain(String convictedRecognizancePendingTrailDateAndExplain) {
        this.convictedRecognizancePendingTrailDateAndExplain = convictedRecognizancePendingTrailDateAndExplain;
    }

    public boolean isConvictedRecognizancePendingTrial() {
        return convictedRecognizancePendingTrial;
    }

    public void setConvictedRecognizancePendingTrial(boolean convictedRecognizancePendingTrial) {
        this.convictedRecognizancePendingTrial = convictedRecognizancePendingTrial;
    }

    public boolean isConvictedServeInJailOrPrison() {
        return convictedServeInJailOrPrison;
    }

    public void setConvictedServeInJailOrPrison(boolean convictedServeInJailOrPrison) {
        this.convictedServeInJailOrPrison = convictedServeInJailOrPrison;
    }

    public String getConvictedWhenWhereAndHowLong() {
        return convictedWhenWhereAndHowLong;
    }

    public void setConvictedWhenWhereAndHowLong(String convictedWhenWhereAndHowLong) {
        this.convictedWhenWhereAndHowLong = convictedWhenWhereAndHowLong;
    }

    public String getCredentialOtherRequirement() {
        return credentialOtherRequirement;
    }

    public void setCredentialOtherRequirement(String credentialOtherRequirement) {
        this.credentialOtherRequirement = credentialOtherRequirement;
    }

    public String getCredentialPassedLicensureExam() {
        return credentialPassedLicensureExam;
    }

    public void setCredentialPassedLicensureExam(String credentialPassedLicensureExam) {
        this.credentialPassedLicensureExam = credentialPassedLicensureExam;
    }

    public String getCredentialPassedLicensureLevel() {
        return credentialPassedLicensureLevel;
    }

    public void setCredentialPassedLicensureLevel(String credentialPassedLicensureLevel) {
        this.credentialPassedLicensureLevel = credentialPassedLicensureLevel;
    }

    public Date getCredentialValidUntil() {
        return credentialValidUntil;
    }

    public void setCredentialValidUntil(Date credentialValidUntil) {
        this.credentialValidUntil = credentialValidUntil;
    }

    public Date getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(Date dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public String getTeachingBookThatHelpedToGrowProfessionally() {
        return teachingBookThatHelpedToGrowProfessionally;
    }

    public void setTeachingBookThatHelpedToGrowProfessionally(String teachingBookThatHelpedToGrowProfessionally) {
        this.teachingBookThatHelpedToGrowProfessionally = teachingBookThatHelpedToGrowProfessionally;
    }

    public String getTeachingEducationalAdvantages() {
        return teachingEducationalAdvantages;
    }

    public void setTeachingEducationalAdvantages(String teachingEducationalAdvantages) {
        this.teachingEducationalAdvantages = teachingEducationalAdvantages;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getBestTimeToCall() {
        return bestTimeToCall;
    }

    public void setBestTimeToCall(String bestTimeToCall) {
        this.bestTimeToCall = bestTimeToCall;
    }

    public String getDaysContactNumber() {
        return daysContactNumber;
    }

    public void setDaysContactNumber(String daysContactNumber) {
        this.daysContactNumber = daysContactNumber;
    }

    public String getEveningsContactNumber() {
        return eveningsContactNumber;
    }

    public void setEveningsContactNumber(String eveningsContactNumber) {
        this.eveningsContactNumber = eveningsContactNumber;
    }

    public String getHowLongLivedToAddress() {
        return howLongLivedToAddress;
    }

    public void setHowLongLivedToAddress(String howLongLivedToAddress) {
        this.howLongLivedToAddress = howLongLivedToAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentPhoneNumber() {
        return permanentPhoneNumber;
    }

    public void setPermanentPhoneNumber(String permanentPhoneNumber) {
        this.permanentPhoneNumber = permanentPhoneNumber;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonToNotifyAddress() {
        return personToNotifyAddress;
    }

    public void setPersonToNotifyAddress(String personToNotifyAddress) {
        this.personToNotifyAddress = personToNotifyAddress;
    }

    public String getPersonToNotifyContactNumber() {
        return personToNotifyContactNumber;
    }

    public void setPersonToNotifyContactNumber(String personToNotifyContactNumber) {
        this.personToNotifyContactNumber = personToNotifyContactNumber;
    }

    public String getPersonToNotifyName() {
        return personToNotifyName;
    }

    public void setPersonToNotifyName(String personToNotifyName) {
        this.personToNotifyName = personToNotifyName;
    }

    public String getPersonToNotifyRelation() {
        return personToNotifyRelation;
    }

    public void setPersonToNotifyRelation(String personToNotifyRelation) {
        this.personToNotifyRelation = personToNotifyRelation;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getTrainingCumulativeGradeAverage() {
        return trainingCumulativeGradeAverage;
    }

    public void setTrainingCumulativeGradeAverage(int trainingCumulativeGradeAverage) {
        this.trainingCumulativeGradeAverage = trainingCumulativeGradeAverage;
    }

    public String getTrainingMajor() {
        return trainingMajor;
    }

    public void setTrainingMajor(String trainingMajor) {
        this.trainingMajor = trainingMajor;
    }

    public String getTrainingMinor() {
        return trainingMinor;
    }

    public void setTrainingMinor(String trainingMinor) {
        this.trainingMinor = trainingMinor;
    }

    public int getTrainingTotalUnitsAfterBachelorsDegree() {
        return trainingTotalUnitsAfterBachelorsDegree;
    }

    public void setTrainingTotalUnitsAfterBachelorsDegree(int trainingTotalUnitsAfterBachelorsDegree) {
        this.trainingTotalUnitsAfterBachelorsDegree = trainingTotalUnitsAfterBachelorsDegree;
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "personId");
	}
}

   