/*
 * Admission.java
 *
 * Created on Dec 6, 2007, 11:23:05 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import bean.Employee;
import constants.UserInfo;
import template.screen.TemplateTabSinglePage;
import util.BeanUtil;
import util.Log;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "GradeLevel")
@UITemplate(template=TemplateTabSinglePage.class,columnSearch={"code", "longName", "course", "head", "sortNumber"}, gridCount=6, title="Level")
@Displays({
       // @Display(name="schoolYear"),
        @Display(name="code"),
        @Display(name="longName", upCase=false),
        @Display(name="sortNumber"),
        @Display(name="course", type="PopSearch", linktoBean=Course.class, width=-1),
        @Display(name="headId", linktoBean=Employee.class, type="PopSearch", label="Head", width=-1),
        @Display(name="college"),
        @Display(name="elaItemCount"),
        @Display(name="mathItemCount"),
        @Display(name="sciItemCount"),
        @Display(name="cognitiveItemCount"),
        @Display(name="affectiveItemCount"),
        @Display(name="psychomotorItemCount")
//        @Display(name="tuitionFee"),
//        @Display(name="otherFee"),
//        
//        @Display(name = "miscFee"),
//        @Display(name = "registrationFee", addInfoOnly=true),
//        @Display(name = "materialsFee", addInfoOnly=true),
//        @Display(name = "idFee", addInfoOnly=true),
//        @Display(name = "medicalDentalFee", addInfoOnly=true),
//        @Display(name = "libraryFee", addInfoOnly=true),
//        @Display(name = "laboratoryFee", addInfoOnly=true),
//        @Display(name = "audioVisualFee", addInfoOnly=true),
//        @Display(name = "athleticFee", addInfoOnly=true),
//        @Display(name = "insuranceFee", addInfoOnly=true),
//        @Display(name = "computerFee", addInfoOnly=true),
//        @Display(name = "handbookFee", addInfoOnly=true),
//        @Display(name = "diplomaFee", addInfoOnly=true),
//        @Display(name = "annualFee", addInfoOnly=true)
})
//@DisplayGroups({
//    @DisplayGroup(title="Misc. Breakdown", 
//        fields={"registrationFee","materialsFee","idFee","medicalDentalFee","libraryFee","laboratoryFee",
//            "audioVisualFee","athleticFee","insuranceFee","computerFee","handbookFee","diplomaFee","annualFee"})
//})
public class GradeLevel extends AbstractIBean implements Serializable {
	public static boolean checkLock(String gradeLevel) {
		GradeLevel l = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), gradeLevel);
		Log.out("RECORD 11 ",l);
		if (l!=null) {
			Log.out("GRADE ",l," - ",l.isLocked());
			return l.isLocked();
		}
		return false;
	}

	@Id
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "longName")
    public String longName;
    @Column(name = "sortNumber")
    public int sortNumber;
    @Column(name = "course")
    public String course;
    @Column(name = "tuitionFee")
    public double tuitionFee;
    @Column(name = "miscFee")
    public double miscFee;
    @Column(name = "otherFee")
    public double otherFee;
    @Column(name = "registrationFee")
    public double registrationFee;
    @Column(name = "materialsFee")
    public double materialsFee;
    @Column(name = "idFee")
    public double idFee;
    @Column(name = "medicalDentalFee")
    public double medicalDentalFee;
    @Column(name = "libraryFee")
    public double libraryFee;
    @Column(name = "laboratoryFee")
    public double laboratoryFee;
    @Column(name = "audioVisualFee")
    public double audioVisualFee;
    @Column(name = "athleticFee")
    public double athleticFee;
    @Column(name = "insuranceFee")
    public double insuranceFee;
    @Column(name = "computerFee")
    public double computerFee;
    @Column(name = "handbookFee")
    public double handbookFee;
    @Column(name = "diplomaFee")
    public double diplomaFee;
    @Column(name = "annualFee")
    public double annualFee;

    @Column(name = "elaItemCount")
    public int elaItemCount;
    @Column(name = "mathItemCount")
    public int mathItemCount;
    @Column(name = "sciItemCount")
    public int sciItemCount;
    @Column(name = "cognitiveItemCount")
    public int cognitiveItemCount;
    @Column(name = "affectiveItemCount")
    public int affectiveItemCount;
    @Column(name = "psychomotorItemCount")
    public int psychomotorItemCount;
    @Column(name = "pracItemCount")
    public int pracItemCount;
    @Column(name = "otherItemCount1")
    public int otherItemCount1;
    @Column(name = "college")
    public boolean college;
    @Column(name = "head")
    public String head;
    @Column(name = "headId")
    public int headId;
    @Column(name = "locked")
    public boolean locked;
    @Column(name="lockDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date lockDate;
    @Column(name = "lockBy")
    public String lockBy;

    @Override
	public void save() {
    	head = extractPersonName(headId);
    	if (locked) {
    		lockDate = new Date();
    		lockBy = UserInfo.getUserName();
    	}
		super.save();
	}

    public int getAffectiveItemCount() {
        return affectiveItemCount;
    }

    public void setAffectiveItemCount(int affectiveItemCount) {
        this.affectiveItemCount = affectiveItemCount;
    }

    public int getCognitiveItemCount() {
        return cognitiveItemCount;
    }

    public void setCognitiveItemCount(int cognitiveItemCount) {
        this.cognitiveItemCount = cognitiveItemCount;
    }

    public int getPsychomotorItemCount() {
        return psychomotorItemCount;
    }

    public void setPsychomotorItemCount(int psychomotorItemCount) {
        this.psychomotorItemCount = psychomotorItemCount;
    }

    
    public int getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(int sortNumber) {
        this.sortNumber = sortNumber;
    }

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Date getLockDate() {
		return lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public String getLockBy() {
		return lockBy;
	}

	public void setLockBy(String lockBy) {
		this.lockBy = lockBy;
	}

	public int getHeadId() {
		return headId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code","longName","course");
    }

    public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public boolean isCollege() {
		return college;
	}

	public void setCollege(boolean college) {
		this.college = college;
	}

	public int getElaItemCount() {
        return elaItemCount;
    }

    public void setElaItemCount(int elaItemCount) {
        this.elaItemCount = elaItemCount;
    }

    public int getMathItemCount() {
        return mathItemCount;
    }

    public void setMathItemCount(int mathItemCount) {
        this.mathItemCount = mathItemCount;
    }

    public int getPracItemCount() {
        return pracItemCount;
    }

    public void setPracItemCount(int pracItemCount) {
        this.pracItemCount = pracItemCount;
    }

    public int getOtherItemCount1() {
        return otherItemCount1;
    }

    public void setOtherItemCount1(int otherItemCount1) {
        this.otherItemCount1 = otherItemCount1;
    }

    public int getSciItemCount() {
        return sciItemCount;
    }

    public void setSciItemCount(int sciItemCount) {
        this.sciItemCount = sciItemCount;
    }

    public double getComputedMiscFee() {
        miscFee=registrationFee+materialsFee+idFee+medicalDentalFee+libraryFee+laboratoryFee+
                audioVisualFee+athleticFee+insuranceFee+computerFee+handbookFee+diplomaFee+annualFee;
        return miscFee;
    }
    public double getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
    }

    public double getAthleticFee() {
        return athleticFee;
    }

    public void setAthleticFee(double athleticFee) {
        this.athleticFee = athleticFee;
    }

    public double getAudioVisualFee() {
        return audioVisualFee;
    }

    public void setAudioVisualFee(double audioVisualFee) {
        this.audioVisualFee = audioVisualFee;
    }

    public double getComputerFee() {
        return computerFee;
    }

    public void setComputerFee(double computerFee) {
        this.computerFee = computerFee;
    }

    public double getDiplomaFee() {
        return diplomaFee;
    }

    public void setDiplomaFee(double diplomaFee) {
        this.diplomaFee = diplomaFee;
    }

    public double getHandbookFee() {
        return handbookFee;
    }

    public void setHandbookFee(double handbookFee) {
        this.handbookFee = handbookFee;
    }

    public double getIdFee() {
        return idFee;
    }

    public void setIdFee(double idFee) {
        this.idFee = idFee;
    }

    public double getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(double insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public double getLaboratoryFee() {
        return laboratoryFee;
    }

    public void setLaboratoryFee(double laboratoryFee) {
        this.laboratoryFee = laboratoryFee;
    }

    public double getLibraryFee() {
        return libraryFee;
    }

    public void setLibraryFee(double libraryFee) {
        this.libraryFee = libraryFee;
    }

    public double getMaterialsFee() {
        return materialsFee;
    }

    public void setMaterialsFee(double materialsFee) {
        this.materialsFee = materialsFee;
    }

    public double getMedicalDentalFee() {
        return medicalDentalFee;
    }

    public void setMedicalDentalFee(double medicalDentalFee) {
        this.medicalDentalFee = medicalDentalFee;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getMiscFee() {
        return miscFee;
    }

    public void setMiscFee(double miscFee) {
        this.miscFee = miscFee;
    }

    public double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(double otherFee) {
        this.otherFee = otherFee;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return BeanUtil.concat(course,"-",code);
    }

    public static GradeLevel createGradeLevelObj(String gradeLevel, String course, double tuition, double miscFee, double otherFee) {
        GradeLevel gl = new GradeLevel();
        gl.code = gradeLevel;
        gl.course = course;
        gl.tuitionFee = tuition;
        gl.registrationFee = miscFee;
        gl.miscFee = miscFee;
        gl.otherFee = otherFee;
        return gl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    @Override
    protected void runSetup() {
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
}
