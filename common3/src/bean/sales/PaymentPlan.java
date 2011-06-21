/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.sales;

import bean.admin.AppConfig;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.*;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;
import util.BeanUtil;
import util.DBClient;
import util.DateUtil;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "PaymentPlan")
@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4, 
//    columnSearch = {"useYear", "code", "gradeLevel", "miscFee", "totalAmount","date1", "amount1", "date2", "amount2","date3", "amount3", "date4", "amount4"},
    columnSearch = {"useYear", "code", "gradeLevel", "miscFee", "totalAmount","date1", "amount1"},
    criteriaSearch = {"useYear", "code", "gradeLevel"},
    orderBy="a.schoolYear DESC, a.code, a.gradeLevel", autoResizeTable=false)
@Displays({
    @Display(name = "useYear"),
    @Display(name = "code"),
    @Display(name = "gradeLevel"),
    @Display(name = "totalAmount"),
@Display(name = "date1"),
@Display(name = "amount1", width = 100),
@Display(name = "date2"),
@Display(name = "amount2", width = 100),
@Display(name = "date3"),
@Display(name = "amount3", width = 100),
@Display(name = "date4"),
@Display(name = "amount4", width = 100),

@Display(name = "date5"),
@Display(name = "amount5", width = 100),
@Display(name = "date6"),
@Display(name = "amount6", width = 100),
@Display(name = "date7"),
@Display(name = "amount7", width = 100),
@Display(name = "date8"),
@Display(name = "amount8", width = 100),
@Display(name = "date9"),
@Display(name = "amount9", width = 100),
@Display(name = "date10"),
@Display(name = "amount10", width = 100),

@Display(name = "miscFee"),
@Display(name = "registrationFee", addInfoOnly=true),
@Display(name = "materialsFee", addInfoOnly=true),
@Display(name = "idFee", addInfoOnly=true),
@Display(name = "medicalDentalFee", addInfoOnly=true),
@Display(name = "libraryFee", addInfoOnly=true),
@Display(name = "laboratoryFee", addInfoOnly=true),
@Display(name = "audioVisualFee", addInfoOnly=true),
@Display(name = "athleticFee", addInfoOnly=true),
@Display(name = "insuranceFee", addInfoOnly=true),
@Display(name = "computerFee", addInfoOnly=true),
@Display(name = "handbookFee", addInfoOnly=true),
@Display(name = "diplomaFee", addInfoOnly=true),
@Display(name = "annualFee", addInfoOnly=true),

@Display(name = "newsletterFee", addInfoOnly=true),
@Display(name = "classPictureFee", addInfoOnly=true),
@Display(name = "mimeographingFee", addInfoOnly=true),
@Display(name = "ticketsFee", addInfoOnly=true),
@Display(name = "airconFee", addInfoOnly=true),
@Display(name = "councilFee", addInfoOnly=true),
@Display(name = "diagnosticFee", addInfoOnly=true),
@Display(name = "cabinetRentalFee", addInfoOnly=true),
@Display(name = "artProjectFee", addInfoOnly=true),
@Display(name = "recollectionFee", addInfoOnly=true),
@Display(name = "retreatFee", addInfoOnly=true),
@Display(name = "firstCommunionFee", addInfoOnly=true),
@Display(name = "graduationFee", addInfoOnly=true),
@Display(name = "fieldTripFee", addInfoOnly=true)
})
@DisplayGroups({
    @DisplayGroup(gridCount = 4, title = "Payment Schedule", fields = {"date1",
    "amount1","date2", "amount2", "date3", "amount3",
        "date4","amount4"
    }),
    @DisplayGroup(gridCount = 4,addInfoOnly=true, title = "Optional Payment Schedule", fields = {"date5",
    "amount5","date6", "amount6", "date7", "amount7",
        "date8","amount8", "date9", "amount9",
        "date10","amount10"
    })            
})
@ActionButtons({
    @ActionButton(name="btnForwardToYear", label="Forward this Plan", parentOnly=false)
})
@ChildRecords(value = {
},
info = {
	    @ParentAddInfo(title = "Additional Months",
	    	    displayFields = {""}),
	    @ParentAddInfo(title = "Miscellaneous Info",
	    	    	    displayFields = {"registrationFee", "materialsFee", "idFee", "medicalDentalFee", "libraryFee", "laboratoryFee",
	    	    	        "audioVisualFee", "athleticFee", "insuranceFee", "computerFee", "handbookFee", "diplomaFee", "annualFee",
	    	    	        "newsletterFee","classPictureFee","mimeographingFee","ticketsFee","airconFee",
	    	    	        "councilFee","diagnosticFee","cabinetRentalFee","artProjectFee","recollectionFee",
	    	    	        "retreatFee","firstCommunionFee","graduationFee","fieldTripFee"
	    	    	    }, hideGroup="1")
})
public class PaymentPlan extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "code", nullable = false)
    public String code;
    @Column(name = "schoolYear")
    public String schoolYear;
    @Column(name = "gradeLevel")
    public String gradeLevel;
    @Column(name = "totalAmount")
    public double totalAmount;
    @Column(name = "date1", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date date1 = constants.Constants.useDate;
    @Column(name = "date2")
    @Temporal(value = TemporalType.DATE)
    public Date date2;
    @Column(name = "date3")
    @Temporal(value = TemporalType.DATE)
    public Date date3;
    @Column(name = "date4")
    @Temporal(value = TemporalType.DATE)
    public Date date4;
    @Column(name = "date5")
    @Temporal(value = TemporalType.DATE)
    public Date date5;
    @Column(name = "date6")
    @Temporal(value = TemporalType.DATE)
    public Date date6;
    @Column(name = "date7")
    @Temporal(value = TemporalType.DATE)
    public Date date7;
    @Column(name = "date8")
    @Temporal(value = TemporalType.DATE)
    public Date date8;
    @Column(name = "date9")
    @Temporal(value = TemporalType.DATE)
    public Date date9;
    @Column(name = "date10")
    @Temporal(value = TemporalType.DATE)
    public Date date10;
    @Column(name = "amount1")
    public double amount1;
    @Column(name = "amount2")
    public double amount2;
    @Column(name = "amount3")
    public double amount3;
    @Column(name = "amount4")
    public double amount4;
    @Column(name = "amount5")
    public double amount5;
    @Column(name = "amount6")
    public double amount6;
    @Column(name = "amount7")
    public double amount7;
    @Column(name = "amount8")
    public double amount8;
    @Column(name = "amount9")
    public double amount9;
    @Column(name = "amount10")
    public double amount10;
    @Column(name = "earlyPaymentDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date earlyPaymentDate = constants.Constants.useDate;
    @Column(name = "earlyPaymentDiscount")
    public double earlyPaymentDiscount;
    
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
   
    @Column(name = "newsletterFee")
    public double newsletterFee;
    @Column(name = "classPictureFee")
    public double classPictureFee;
    @Column(name = "mimeographingFee")
    public double mimeographingFee;
    @Column(name = "ticketsFee")
    public double ticketsFee;
    @Column(name = "airconFee")
    public double airconFee;
    @Column(name = "councilFee")
    public double councilFee;
    @Column(name = "diagnosticFee")
    public double diagnosticFee;
    @Column(name = "cabinetRentalFee")
    public double cabinetRentalFee;
    @Column(name = "artProjectFee")
    public double artProjectFee;
    @Column(name = "recollectionFee")
    public double recollectionFee;
    @Column(name = "retreatFee")
    public double retreatFee;
    @Column(name = "firstCommunionFee")
    public double firstCommunionFee;
    @Column(name = "graduationFee")
    public double graduationFee;
    @Column(name = "fieldTripFee")
    public double fieldTripFee;

    @Column(name = "description")
    public String description;
    @Column(name = "paymentCount")
    public int paymentCount;
    @Column(name = "useYear")
    public String useYear;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "schoolYear", "code", "gradeLevel");
    }

    @Override
	public void save() {
    	totalAmount = extractTotalAmount();
		super.save();
	}

	public double extractTotalAmount() {
    	return miscFee+amount1+amount2+amount3+amount4+amount5+amount6+amount7+amount8+amount9+amount10;
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

	public double getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(double registrationFee) {
		this.registrationFee = registrationFee;
	}

	public double getMaterialsFee() {
		return materialsFee;
	}

	public void setMaterialsFee(double materialsFee) {
		this.materialsFee = materialsFee;
	}

	public double getIdFee() {
		return idFee;
	}

	public void setIdFee(double idFee) {
		this.idFee = idFee;
	}

	public double getMedicalDentalFee() {
		return medicalDentalFee;
	}

	public void setMedicalDentalFee(double medicalDentalFee) {
		this.medicalDentalFee = medicalDentalFee;
	}

	public double getLibraryFee() {
		return libraryFee;
	}

	public void setLibraryFee(double libraryFee) {
		this.libraryFee = libraryFee;
	}

	public double getLaboratoryFee() {
		return laboratoryFee;
	}

	public void setLaboratoryFee(double laboratoryFee) {
		this.laboratoryFee = laboratoryFee;
	}

	public double getAudioVisualFee() {
		return audioVisualFee;
	}

	public void setAudioVisualFee(double audioVisualFee) {
		this.audioVisualFee = audioVisualFee;
	}

	public double getAthleticFee() {
		return athleticFee;
	}

	public void setAthleticFee(double athleticFee) {
		this.athleticFee = athleticFee;
	}

	public double getInsuranceFee() {
		return insuranceFee;
	}

	public void setInsuranceFee(double insuranceFee) {
		this.insuranceFee = insuranceFee;
	}

	public double getComputerFee() {
		return computerFee;
	}

	public void setComputerFee(double computerFee) {
		this.computerFee = computerFee;
	}

	public double getHandbookFee() {
		return handbookFee;
	}

	public void setHandbookFee(double handbookFee) {
		this.handbookFee = handbookFee;
	}

	public double getDiplomaFee() {
		return diplomaFee;
	}

	public void setDiplomaFee(double diplomaFee) {
		this.diplomaFee = diplomaFee;
	}

	public double getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(double annualFee) {
		this.annualFee = annualFee;
	}

	public double getAmount1() {
        return amount1;
    }

    public void setAmount1(double amount1) {
        this.amount1 = amount1;
    }

    public double getAmount10() {
        return amount10;
    }

    public void setAmount10(double amount10) {
        this.amount10 = amount10;
    }

    public double getAmount2() {
        return amount2;
    }

    public void setAmount2(double amount2) {
        this.amount2 = amount2;
    }

    public double getAmount3() {
        return amount3;
    }

    public void setAmount3(double amount3) {
        this.amount3 = amount3;
    }

    public double getAmount4() {
        return amount4;
    }

    public void setAmount4(double amount4) {
        this.amount4 = amount4;
    }

    public double getAmount5() {
        return amount5;
    }

    public void setAmount5(double amount5) {
        this.amount5 = amount5;
    }

    public double getAmount6() {
        return amount6;
    }

    public void setAmount6(double amount6) {
        this.amount6 = amount6;
    }

    public double getAmount7() {
        return amount7;
    }

    public void setAmount7(double amount7) {
        this.amount7 = amount7;
    }

    public double getAmount8() {
        return amount8;
    }

    public void setAmount8(double amount8) {
        this.amount8 = amount8;
    }

    public double getAmount9() {
        return amount9;
    }

    public void setAmount9(double amount9) {
        this.amount9 = amount9;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate10() {
        return date10;
    }

    public void setDate10(Date date10) {
        this.date10 = date10;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public Date getDate4() {
        return date4;
    }

    public void setDate4(Date date4) {
        this.date4 = date4;
    }

    public Date getDate5() {
        return date5;
    }

    public void setDate5(Date date5) {
        this.date5 = date5;
    }

    public Date getDate6() {
        return date6;
    }

    public void setDate6(Date date6) {
        this.date6 = date6;
    }

    public Date getDate7() {
        return date7;
    }

    public void setDate7(Date date7) {
        this.date7 = date7;
    }

    public Date getDate8() {
        return date8;
    }

    public void setDate8(Date date8) {
        this.date8 = date8;
    }

    public Date getDate9() {
        return date9;
    }

    public void setDate9(Date date9) {
        this.date9 = date9;
    }

    public double getNewsletterFee() {
		return newsletterFee;
	}

	public void setNewsletterFee(double newsletterFee) {
		this.newsletterFee = newsletterFee;
	}

	public double getClassPictureFee() {
		return classPictureFee;
	}

	public void setClassPictureFee(double classPictureFee) {
		this.classPictureFee = classPictureFee;
	}

	public double getMimeographingFee() {
		return mimeographingFee;
	}

	public void setMimeographingFee(double mimeographingFee) {
		this.mimeographingFee = mimeographingFee;
	}

	public double getTicketsFee() {
		return ticketsFee;
	}

	public void setTicketsFee(double ticketsFee) {
		this.ticketsFee = ticketsFee;
	}

	public double getAirconFee() {
		return airconFee;
	}

	public void setAirconFee(double airconFee) {
		this.airconFee = airconFee;
	}

	public double getCouncilFee() {
		return councilFee;
	}

	public void setCouncilFee(double councilFee) {
		this.councilFee = councilFee;
	}

	public double getDiagnosticFee() {
		return diagnosticFee;
	}

	public void setDiagnosticFee(double diagnosticFee) {
		this.diagnosticFee = diagnosticFee;
	}

	public double getCabinetRentalFee() {
		return cabinetRentalFee;
	}

	public void setCabinetRentalFee(double cabinetRentalFee) {
		this.cabinetRentalFee = cabinetRentalFee;
	}

	public double getArtProjectFee() {
		return artProjectFee;
	}

	public void setArtProjectFee(double artProjectFee) {
		this.artProjectFee = artProjectFee;
	}

	public double getRecollectionFee() {
		return recollectionFee;
	}

	public void setRecollectionFee(double recollectionFee) {
		this.recollectionFee = recollectionFee;
	}

	public double getRetreatFee() {
		return retreatFee;
	}

	public void setRetreatFee(double retreatFee) {
		this.retreatFee = retreatFee;
	}

	public double getFirstCommunionFee() {
		return firstCommunionFee;
	}

	public void setFirstCommunionFee(double firstCommunionFee) {
		this.firstCommunionFee = firstCommunionFee;
	}

	public double getGraduationFee() {
		return graduationFee;
	}

	public void setGraduationFee(double graduationFee) {
		this.graduationFee = graduationFee;
	}

	public double getFieldTripFee() {
		return fieldTripFee;
	}

	public void setFieldTripFee(double fieldTripFee) {
		this.fieldTripFee = fieldTripFee;
	}

	public Date getEarlyPaymentDate() {
        return earlyPaymentDate;
    }

    public void setEarlyPaymentDate(Date earlyPaymentDate) {
        this.earlyPaymentDate = earlyPaymentDate;
    }

    public double getEarlyPaymentDiscount() {
        return earlyPaymentDiscount;
    }

    public void setEarlyPaymentDiscount(double earlyPaymentDiscount) {
        this.earlyPaymentDiscount = earlyPaymentDiscount;
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

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUseYear() {
        return useYear;
    }

    public void setUseYear(String useYear) {
        this.useYear = useYear;
    }

    public int getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(int paymentCount) {
        this.paymentCount = paymentCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static PaymentPlan createPlan(String code, String description, int count, String useYear, String gradeLevel, double amount, double earlyPayment, Date earlyDate, double misc) {
        PaymentPlan p = new PaymentPlan();
        p.code = code;
        p.description = description;
        p.paymentCount = count;
        if (useYear==null || useYear.isEmpty()) {
            p.useYear = AppConfig.getSchoolYear();
        }
        else {
            p.useYear = useYear;
        }
        p.miscFee = misc;
        p.totalAmount = amount;
        p.gradeLevel = gradeLevel;
        p.earlyPaymentDate = earlyDate;
        p.earlyPaymentDiscount = earlyPayment;
        if (amount<count) return p;//no need to distribute amount
        double amountDis = amount/count;
        int value = AppConfig.monthInstallment();//default to 10 months only
        int days = value*30/count;
        Date d = constants.Constants.useDate;
        p.date1 = d;
        p.amount1 = amountDis;
        for (int i = 1; i < count; i++) {
            d = DateUtil.addDay(d, days);
            p.setupAmount(i+1, amountDis, d);
        }
        return p;
    }
    public static PaymentPlan createPlan(String code, String description, int count, String useYear, String gradeLevel, double amount, double earlyPayment, double misc) {
        Date d = DateUtil.addDay(constants.Constants.useDate, 15);
        return createPlan(code, description, count, useYear, gradeLevel, amount, earlyPayment, d, misc);
    }
    public static PaymentPlan createPlan(String code, String description, int count, String useYear, String gradeLevel, double amount) {
        return createPlan(code, description, count, useYear, gradeLevel, amount, 0, 0);
    }

    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return BeanUtil.concat(code," - ",description);
    }
    
    @Override
    public void runSetup() {
        PaymentPlan a = (PaymentPlan) DBClient.getFirstRecord("SELECT a FROM PaymentPlan a");
        if (a!=null && !a.isEmptyKey()) return;
        createPlan("A", "ONE TIME PAYMENT", 1, "", "", 0).save();
        createPlan("S", "SEMI ANNUAL", 2, "", "", 0).save();
        createPlan("Q", "QUARTER", 4, "", "", 0).save();
        createPlan("M", "MONTHLY", 10, "", "", 0).save();
    }   
    
    public void setupAmount(int index, double amount, Date d) {
        switch (index) {
            case 1: 
                amount1 = amount;
                date1 = d;
                return;
            case 2: 
                amount2 = amount;
                date2 = d;
                return;
            case 3: 
                amount3 = amount;
                date3 = d;
                return;
            case 4: 
                amount4 = amount;
                date4 = d;
                return;
            case 5: 
                amount5 = amount;
                date5 = d;
                return;
            case 6: 
                amount6 = amount;
                date6 = d;
                return;
            case 7: 
                amount7 = amount;
                date7 = d;
                return;
            case 8: 
                amount8 = amount;
                date8 = d;
                return;
            case 9: 
                amount9 = amount;
                date9 = d;
                return;
            case 10: 
                amount10 = amount;
                date10 = d;
                return;
        }
    }

    public double extractAmount(int index) {
        switch (index) {
            case 1: return amount1;
            case 2: return amount2;
            case 3: return amount3;
            case 4: return amount4;
            case 5: return amount5;
            case 6: return amount6;
            case 7: return amount7;
            case 8: return amount8;
            case 9: return amount9;
            default: return amount10;
        }
    }

    public Date extractDate(int index) {
        switch (index) {
            case 1: return date1;
            case 2: return date2;
            case 3: return date3;
            case 4: return date4;
            case 5: return date5;
            case 6: return date6;
            case 7: return date7;
            case 8: return date8;
            case 9: return date9;
            default: return date10;
        }
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
    
	@Override
	public void setupIndex() {
		runIndex(1, "code","gradeLevel");
		runUniqueIndex(2, "code","gradeLeve");
	}
}
