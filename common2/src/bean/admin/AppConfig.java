/*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import springbean.AAAConfig;
import template.Display;
import template.Displays;
import template.UITemplate;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.DateUtil;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "AppConfig")
@UITemplate(template = template.screen.TemplateSinglePage.class, gridCount = 4, columnSearch = {"category","code","value"})
@Displays({
        @Display(name="category"),
        @Display(name="code"),
        @Display(name="value", gridFieldWidth=3, width=-1, upCase=false)
})
public class AppConfig extends AbstractIBean implements Serializable {

    public static String getCompanyLineOfBusiness() {
        return AppConfig.getAppValue("COMPANY LINE OF BUSINESS", "");
    }

    public static String getCompanyRDOCode() {
        return AppConfig.getAppValue("COMPANY RDO CODE", "");
    }

    public static String getCompanyTelNo() {
        return AppConfig.getAppValue("COMPANY TELEPHONE NUMBER", "");
    }

    public static String getCompanyTIN() {
        return AppConfig.getAppValue("COMPANY TIN NUMBER", "");
    }

    public static String getCompanyWTaxAgentCategory() {
        return AppConfig.getAppValue("COMPANY WITHHOLDING TAX AGENT CATEGORY", "");
    }

    public static String getCompanyWTaxAgent() {
        return AppConfig.getAppValue("COMPANY WITHHOLDING TAX AGENT", "");
    }

    public static String getCompanyZipCode() {
        return AppConfig.getAppValue("COMPANY ZIP CODE", "");
    }

    @Id
    @Column(name = "code")
    public String code;
    @Column(name = "category")
    public String category;
    @Column(name = "value", length=4000)
    public String value;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "category", "code", "value");
    }

    private static boolean isTrue(String code, Boolean defValue) {
        String tmp = getAppValue(code, defValue.toString());
        if (tmp==null) {
            if (AAAConfig.server) {
//                this is server side
                AppConfig c = new AppConfig();
                c.category = "APPLICATION";
                c.code = code;
                c.value = defValue.toString();
                c.save();
                return defValue;
            }
            return false;
        }
        return ("TRUE".equalsIgnoreCase(tmp));
    }
    
    private static synchronized String getValue(String category, String code, String defValue) {
        AppConfig tmp = getConfig(category, code, defValue);
        if (tmp==null) {
            if (AAAConfig.server) {
//                this is server side
                AppConfig c = new AppConfig();
                c.category = category;
                c.code = code;
                c.value = defValue.toString();
                c.save();
                return defValue;
            }
            return "";
        }
        return tmp.value;
    }

    private static String getAppValue(String code, String defValue) {
        AppConfig tmp = getConfig("APPLICATION", code, defValue);
        if (tmp==null) {
            if (AAAConfig.server) {
//                this is server side
                AppConfig c = new AppConfig();
                c.category = "APPLICATION";
                c.code = code;
                c.value = defValue.toString();
                c.save();
                return defValue;
            }
            return "";
        }
        return tmp.value;
    }

    public static synchronized void setAppValue(String code, String value) {
        AppConfig tmp = getConfig("APPLICATION", code, value);
        tmp.value = value;
        tmp.save();
    }

    static AppConfig config;
    public static List<AppConfig> lst;
    public static List<AppMenu> lstMenu;
    public static List<AclModule> lstModule;
    private static AppConfig getConfig(String category, String code, String defValue) {
        if (lst==null) {
//            Log.out("CONFIG SERVER CALL...");
            List<String> tmp = new ArrayList();
            tmp.add("SELECT a FROM AppConfig a");
            tmp.add("SELECT a FROM AppMenu a ORDER BY a.sortNum");
            tmp.add("SELECT a FROM AclModule a");
            Map map = DBClient.batchQueryServerCache(tmp);
            lst = (List) map.get("SELECT a FROM AppConfig a");
            lstMenu = (List) map.get("SELECT a FROM AppMenu a ORDER BY a.sortNum");
            lstModule = (List) map.get("SELECT a FROM AclModule a");
        }
        if (lst!=null) {
            for (AppConfig conf : lst) {
                if (conf.category.equals(category) && conf.code.equals(code)) {
                    return conf;
                }
            }
        }
        return null;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }  
    
    static boolean called1 = false;
    static boolean called2 = false;
    public void runStaticConfig() {
        if (called1 && called2) {
        	return;
        }
        else if (called1) {
        	called2 = true;
        }
        called1 = true;
        DBClient.runSQLNative("ALTER TABLE AppConfig MODIFY COLUMN seq INTEGER NOT NULL DEFAULT NULL AUTO_INCREMENT;");
        AppConfig.dynamicTabs();
        AppConfig.extractLabelColor();
        AppConfig.extractLoadingImage();
        AppConfig.extractPanelBackground();
        AppConfig.getCentWord();
        AppConfig.getCompanyAddress();
        AppConfig.getCompanyName();
        AppConfig.getCurrencyWord();
        AppConfig.getReportFolder();
        AppConfig.getSchoolYear();
        AppConfig.getSchoolYearFormat();
        AppConfig.getSchoolYears();
        AppConfig.getSurchargeValue();
        AppConfig.getUsePayroll();
        AppConfig.getWelcomeLabel();
        AppConfig.isAcceptAdmissionThruCashier();
        AppConfig.isAlwaysComputeMisc();
        AppConfig.isDetailPayrollPosting();
        AppConfig.isDiscountTuitionFeeOnly();
        AppConfig.isDynamicChangeReportFile();
        AppConfig.isDynamicCombo();
        AppConfig.isGLRepost();
        AppConfig.isGroupWithTitle();
        AppConfig.isHardcodePayrollPosting();
        AppConfig.isMiscPartOfOverallFee();
        AppConfig.isPaymentEditable();
        AppConfig.isRetainDiscountAndSurcharge();
        AppConfig.isSemestralEnrollment();
        AppConfig.isStudentNumberStart0();
        AppConfig.isUseDynamicAccess();
        AppConfig.monthInstallment();
        AppConfig.showBookmarkButton();
        AppConfig.showCalcButton();
        AppConfig.showConfigureButton();
        AppConfig.showEmailButton();
        AppConfig.showNotePadButton();
        AppConfig.getLiftCheckMessage();
        AppConfig.autoAssesOnPaymentWithoutEnrollment();
        AppConfig.discountNeedRequest();
        AppConfig.isShowHostLogin();
        AppConfig.getLicenceType();
        AppConfig.isProduction();
        AppConfig.hasExamReference();
        AppConfig.hasSchoolProfile();
        AppConfig.getTemporaryFolder();
        AppConfig.isShowCollege();
        AppConfig.getPerUnitAmount();
        AppConfig.isShowDataAnalyzer();
        AppConfig.isShowMonthlyAttendance();
        AppConfig.isSumBeforeGradeCalculation();
        AppConfig.isAlwaysCompileReport();
        AppConfig.alwaysCheckConnection();
        AppConfig.isLockPerGradeLevel();
        AppConfig.isRunFWB();
        AppConfig.hasPromotionReport();
        AppConfig.isTrimester();
        AppConfig.getCompanyLineOfBusiness();
        AppConfig.getCompanyRDOCode();
        AppConfig.getCompanyAddress();
        AppConfig.getCompanyTelNo();
        AppConfig.getCompanyTIN();
        AppConfig.getCompanyWTaxAgentCategory();
        AppConfig.getCompanyWTaxAgent();
        AppConfig.getCompanyZipCode();
        AppConfig.getCompanyNameOfBank();
        AppConfig.isAutoCreateChargeRule();
        AppConfig.isRawScoreGrading();
        AppConfig.isShowTestButton();
        String[] arrStr = {"H1","H2","H3","H4","G1","G2","G3","G4","G5","G6","K1","K2","N1","N2","P1","P2"};
        for (String s : arrStr) {
            AppConfig.isGradingGPAStraightAverage(s);
            AppConfig.isGradingGPATotalMapehThenAverage(s);
        }
    }
    
    public static boolean isTrimester() {
        return AppConfig.isTrue("IS TRIMESTER", false);
	}

	public static String getTemporaryFolder() {
        return AppConfig.getAppValue("TEMPORARY FOLDER", "C:/tmp");
	}

    public static double getPerUnitAmount() {
        return DataUtil.getDoubleValue(AppConfig.getAppValue("UNIT AMOUNT", "250"));
	}

    public static boolean alwaysCheckConnection() {
        return AppConfig.isTrue("ALWAYS CHECK CONNECTION", false);
	}

    public static boolean hasPromotionReport() {
        return AppConfig.isTrue("HAS PROMOTION REPORT", false);
	}

    public static boolean isProduction() {
        return AppConfig.isTrue("PRODUCTION STATE", false);
	}

    public static boolean discountNeedRequest() {
        return AppConfig.isTrue("DISCOUNT NEED REQUEST", false);
    }
    public static boolean isGLRepost() {
        return AppConfig.isTrue("GL REPOST", true);
    }
    public static boolean isHardcodePayrollPosting() {
        return AppConfig.isTrue("HARDCODE PAYROLL POSTING", false);
    }
    public static boolean isDetailPayrollPosting() {
        return AppConfig.isTrue("DETAIL PAYROLL POSTING", false);
    }
    public static String extractLoadingImage() {
        return AppConfig.getAppValue("LOADING IMAGE", "SETON.GIF");
    }
    public static String extractPanelBackground() {
        return AppConfig.getAppValue("PANEL BACKGROUND", "44 117 44");
    }
    public static String extractLabelColor() {
        return AppConfig.getAppValue("LABEL COLOR", "255 255 255");
    }
    public static boolean isGroupWithTitle() {
        return AppConfig.isTrue("GROUP WITH TITLE", false);
    }    
    public static boolean isUseDynamicAccess() {
        return AppConfig.isTrue("USE DYNAMIC ACCESS", false);
    }  
    static List<String> sylst;
    public static List<String> getSchoolYears() {
        if (sylst!=null) {
            return sylst;
        }
        sylst = new ArrayList<String>();
        int yyyy = Integer.parseInt(DateUtil.formatDate(constants.Constants.useDate, "yyyy"))-5;
        for (int i = 0; i < 10; i++) {
            String schoolYear = "";
            if (schoolYear==null || schoolYear.isEmpty()) {
                schoolYear = BeanUtil.concat(yyyy,"-",(yyyy+1));
            }
            yyyy++;
            sylst.add(schoolYear);
        }
        return sylst;
    }
    static List<String> lstDiscount;
    public static List<String> getDiscountTypes() {
        if (lstDiscount==null) {
            lstDiscount = new ArrayList();
            lstDiscount.add("EARLY PAYMENT");
            lstDiscount.add("SCHOLARSHIP");
//            lstDiscount.add("SURCHARGE");
            lstDiscount.add("EMPLOYEE");
        }
        return lstDiscount;
    }

    public static String getSchoolYear() {
        return AppConfig.getAppValue("SCHOOL YEAR", "2009-2010");
    }
    public static String getLiftCheckMessage() {
        return AppConfig.getAppValue("LIFT CHECK MESSAGE STATUS", "Please ask Mr./Ms. [] to lift student status.");
    }
    public static int monthInstallment() {
        try {
            return Integer.parseInt(AppConfig.getAppValue("MONTH INSTALLMENT", BeanUtil.concat(10)));
        }
        catch (Exception e) {
            return 10;
        }
    }
    public static boolean autoAssesOnPaymentWithoutEnrollment() {
        return AppConfig.isTrue("AUTO ASSESS ON PAYMENT WITHOUT ENROLLMENT", false);
    }
    public static boolean showBookmarkButton() {
        return AppConfig.isTrue("SHOW BUTTON BOOKMARK", true);
    }
    public static boolean showCalcButton() {
        return AppConfig.isTrue("SHOW CALC BOOKMARK", true);
    }
    public static boolean showConfigureButton() {
        return AppConfig.isTrue("SHOW CONF BOOKMARK", true);
    }
    public static boolean showEmailButton() {
        return AppConfig.isTrue("SHOW EMAIL BOOKMARK", true);
    }
    public static boolean showNotePadButton() {
        return AppConfig.isTrue("SHOW NOTEPAD BOOKMARK", true);
    }
    public static boolean dynamicTabs() {
        return AppConfig.isTrue("DYNAMIC CHANGE TAB", false);
    }
    public static String getReportFolder() {
        return AppConfig.getAppValue("CLIENT_REPORT_FOLDER", "SPAS");
    }
    public static String getCurrencyWord() {
        return AppConfig.getAppValue("CURRENCY WORD", "PESOS");
    }
    public static String getCentWord() {
        return AppConfig.getAppValue("CURRENCY CENT WORD", "CENTS");
    }
    public static boolean isDynamicChangeReportFile() {
        return AppConfig.isTrue("DYNAMIC CHANGE REPORT FILE", false);
    }
    public static boolean isDynamicCombo() {
        return AppConfig.isTrue("DYNAMIC COMBO", false);
    }
    public static String getWelcomeLabel() {
        return AppConfig.getAppValue("WELCOME LABEL", "To Elizabeth Seton School");
    }
    public static String getSchoolYearFormat() {
        return AppConfig.getAppValue("STUDENT NUMBER FORMAT YEAR", "yyyy");
    }
    public static boolean isStudentNumberStart0() {
        return AppConfig.isTrue("STUDENT NUMBER FORMAT START 0", true);
    }
    public static boolean isPaymentEditable() {
        return AppConfig.isTrue("ALWAYS MAKE PAYMENT EDITABLE", true);
    }
    public static String getCompanyAddress() {
        return AppConfig.getAppValue("COMPANY ADDRESS", "");
    }
    public static String getCompanyName() {
        return AppConfig.getAppValue("COMPANY NAME", "");
    }
    public static double getSurchargeValue() {
        return Double.parseDouble(AppConfig.getAppValue("SURCHARGE VALUE", ".1"));
    }
    public static String getUsePayroll() {
        return AppConfig.getValue("PAYROLL", "USE PAYROLL", "PAYROLL1");
    }
    public static boolean isRetainDiscountAndSurcharge() {
        return AppConfig.isTrue("RETAIN SURCHARGE AND DISCOUNT", false);
    }
    public static boolean isAcceptAdmissionThruCashier() {
        return AppConfig.isTrue("ACCEPT ADMISSION THRU CASHIER", true);
    }
    public static boolean isRunFWB() {
        return AppConfig.isTrue("RUN FWB", true);
    }
    public static boolean hasExamReference() {
        return AppConfig.isTrue("HAS EXAM REFERENCE", true);
    }
    public static boolean hasSchoolProfile() {
        return AppConfig.isTrue("HAS SCHOOL PROFILE", true);
    }
    public static boolean isDiscountTuitionFeeOnly() {
        return isTrue("ENROLLMENT DISCOUNT TUITION ONLY", true);
    }
    public static boolean isMiscPartOfOverallFee() {
        return isTrue("MISC PART OF OVERALL FEE", false);
    }
    public static boolean isSemestralEnrollment() {
        return isTrue("SEMESTRAL ENROLLMENT", true);
    }
    public static boolean isAlwaysComputeMisc() {
        return isTrue("ENROLLMENT ALWAYS COMPUTE MISC", true);
    }
    public static boolean isShowHostLogin() {
        return isTrue("SHOW HOST LOGIN", true);
    }
    public static String getLicenceType() {
        return AppConfig.getAppValue("LICENCE", "ALL");
    }
    public static boolean isShowCollege() {
        return isTrue("SHOW COLLEGE", false);
    }

	public static boolean isShowDataAnalyzer() {
        return isTrue("SHOW DATA ANALYZER", false);
	}
	public static boolean isShowMonthlyAttendance() {
        return isTrue("SHOW MONTHLY ATTENDANCE", false);
	}
	public static boolean isSumBeforeGradeCalculation() {
        return isTrue("SUM BEFORE GRADE CALCULATION", true);
	}
    public static boolean isAlwaysCompileReport() {
        return AppConfig.isTrue("ALWAYS COMPILE REPORT", false);
    }
    public static boolean isLockPerGradeLevel() {
        return AppConfig.isTrue("LOCK PER GRADELEVEL", false);
    }
	
	@Override
	public void setupIndex() {
		List lst = DBClient.getList("SELECT a FROM Schedule a WHERE a.faculty IS NULL", 1, 15000);
		DBClient.persistBean(lst);
	}

	public static String getCompanyNameOfBank() {
        return AppConfig.getAppValue("COMPANY NAME OF BANK REMITTANCE", "");
	}

	public static boolean isAutoCreateChargeRule() {
        return AppConfig.isTrue("AUTO CREATE CHARGE RULE", true);
	}
	
	public static boolean isRawScoreGrading() {
        return AppConfig.isTrue("RAW SCORE GRADING", true);
	}

    public static boolean isGradingGPAStraightAverage(String gradeLevel) {
        return AppConfig.isTrue(BeanUtil.concat("GRADING GPA STRAIGHT AVERAGE ", gradeLevel), true);
    }

    public static boolean isGradingGPATotalMapehThenAverage(String gradeLevel) {
        return AppConfig.isTrue(BeanUtil.concat("GRADING TOTAL MAPEH THEN AVERAGE ", gradeLevel), true);
    }

	public static boolean isShowTestButton() {
        return AppConfig.isTrue("SHOW TEST BUTTON", false);
	}
}
