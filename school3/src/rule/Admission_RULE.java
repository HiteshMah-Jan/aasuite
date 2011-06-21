/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import javax.swing.JComponent;

import service.util.AbstractIBean;
import springbean.SchoolDefaultProcess;
import template.report.AbstractReportTemplate;
import template.screen.TransactionPanel;
import util.BeanUtil;
import util.Log;
import util.PanelUtil;
import bean.Admission;
import bean.accounting.GLPostingScript;
import bean.accounting.Invoice;
import bean.accounting.PaymentAdmission;
import bean.reference.GradeLevel;
import constants.UserInfo;

/**
 *
 * @author Entokwaa
 */
public class Admission_RULE extends BusinessRuleWrapper {

    String rate = "";

    @Override
    public void onChangeRecord() {
    }

    @Override
    public void onLoad() {
        if (!UserInfo.canPrintInvoice()) {
        }
        if (!UserInfo.canViewGL()) {
        }
        if (!UserInfo.canAssessStudent()) {
        }
        if (!UserInfo.canUpdateExamDetails()) {
            //getComponent("orDate").setEnabled(true);
            //getComponent("orNumber").setEnabled(true);
        }
    }

    @Override
    public void runFocusLost(JComponent comp) {
        Admission admission = (Admission) panel.getCurrentObject();
        if ("gradeLevel".equals(comp.getName())) {
            changeItemCount();
        }
        if (comp.getName().equalsIgnoreCase("rating")) {
            if (admission.rating >= SchoolDefaultProcess.MAX_GRADE_RECALCULATE) {
                rate = "PASSED";
            } else {
                rate = "FAILED";
            }
            setValue("remarks", BeanUtil.concat(rate));
        }
        if ("birthDate".equalsIgnoreCase(comp.getName())) {
            setAge(admission);
        }
        
        if (comp.getName().contains("Count") || comp.getName().contains("Score") || comp.getName().contains("Percentage")) {
            changePercentageRemarks();
        }
    }

    private int getPercentage(int down, int top) {
        double d1 = top;
        double d2 = down;
        return (int) ((d1/d2) * 100);
    }

    private void changePercentageRemarks() {
        setValue("itemPercentage", 0);
        setValue("totalRemarks", "");
        setValue("pracRemarks", "");

        String[] arr = {"ela","math","sci","cognitive","affective","psychomotor"};
    	int countFailed = 0;
        setValue("recommendation", "ACCEPTED");
        setValue("recommendationCondition", "ACCEPTED");
        int itemCount = 0;
        int itemScore = 0;
    	for (String a:arr) {
    		Log.out(a,"Count");
    		if (getIntValue(BeanUtil.concat(a,"Count"), 0)<=0) {
    			setValue(BeanUtil.concat(a,"Score"), "0");
    			setValue(BeanUtil.concat(a,"Percentage"), "0");
    			setValue(BeanUtil.concat(a,"Remarks"), "");
    			continue;
    		}
        	changePercentageRemarks(a);        	
        	double percent = getIntValue(BeanUtil.concat(a,"Percentage"), 0);
        	if (percent <= 0.1) continue;
        	itemCount += getIntValue(BeanUtil.concat(a,"Count"), 0);
        	itemScore += getIntValue(BeanUtil.concat(a,"Score"), 0);
        	if (percent < 50) {
//        		failed
        		if (percent < 40) {
//        			this means that the failed is less than 40, this is not accepted 
            		countFailed++;
        		}
        		countFailed++;
        		if (countFailed > 1) {
                    setValue("recommendation", "NOT ACCEPTED");
                    setValue("recommendationCondition", "NOT ACCEPTED");
        		}
        		else {
                    setValue("recommendation", "ACCEPTED ON CONDITION");
                    String s = "";
                    if ("ela".equals(a)) {
                    	s = "ENGLISH";
                        setValue("recommendedRemedialEla", true);
                    }
                    else if ("math".equals(a)) {
                    	s = "MATHEMATICS";
                        setValue("recommendedRemedialMath", true);
                    }
                    else if ("sci".equals(a)) {
                    	s = "SCIENCE";
                        setValue("recommendedRemedialSci", true);
                    }
                    else if ("prac".equals(a)) {
                    	s = "PRACTICAL EXAM";
                    }
                    else if ("cognitive".equals(a)) {
                    	s = "COGNITIVE DEVELOPMENT";
                    }
                    else if ("affective".equals(a)) {
                    	s = "AFFECTIVE DEVELOPMENT";
                    }
                    else if ("psychomotor".equals(a)) {
                    	s = "PSYCHOMOTOR DEVELOPMENT";
                    }
                    Admission ad = (Admission) this.getBean();
                    if ("|K1|K2|P1|P2|N1|N2|".contains(ad.gradeLevel)) {
                        setValue("recommendationCondition", "NOT ACCEPTED");
                    }
                    else {
                        setValue("recommendationCondition", BeanUtil.concat("REMEDIAL ON ",s));
                    }
        		}
        	}
    	}
        setValue("itemTotal", itemCount);
        setValue("itemScore", itemScore);
        changePercentageRemarks("item");
        setValue("finalRemarks", getValue("recommendationCondition"));
    }

    private void changePercentageRemarks(String examName) {
        int count = 0;
    	if (examName.equals("item")) {
            count = getIntValue("itemTotal", 0);
    	}
    	else {
            count = getIntValue(BeanUtil.concat(examName,"Count"), 0);
    	}
        int score = getIntValue(BeanUtil.concat(examName,"Score"), 0);
        if (count>0 && score>0) {
        	setValue(BeanUtil.concat(examName,"Percentage"), getPercentage(count,score));
        	double percent = getDoubleValue(BeanUtil.concat(examName,"Percentage"), 0);
        	if (examName.equals("item")) {
                setValue("totalRemarks", percent>=50?"PASSED":"FAILED");
        	}
        	else {
                setValue(BeanUtil.concat(examName,"Remarks"), percent>=50?"PASSED":"FAILED");
        	}
        }
    }
    
    private void changeItemCount() {
        Admission ad = (Admission) this.getBean();
        if (ad.gradeLevel!=null && ad.schoolYear!=null) {
            GradeLevel ref = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), ad.gradeLevel);
            if (ad.elaCount<=0) {
            	setValue("elaCount", ref.elaItemCount);
            }
            if (ad.mathCount<=0) {
                setValue("mathCount", ref.mathItemCount);
            }
            if (ad.sciCount<=0) {
                setValue("sciCount", ref.sciItemCount);
            }
            if (ad.cognitiveCount<=0) {
                setValue("cognitiveCount", ref.cognitiveItemCount);
            }
            if (ad.affectiveCount<=0) {
                setValue("affectiveCount", ref.affectiveItemCount);
            }
            if (ad.psychomotorCount<=0) {
                setValue("psychomotorCount", ref.psychomotorItemCount);
            }
            if (ad.pracCount<=0) {
                setValue("pracCount", ref.pracItemCount);
            }
        }
    }

    @Override
    public void runOnClick(JComponent comp) {
        if (comp.getName().equals("btnRegisterStudent")) {
            registerStudent();
        } else if (comp.getName().equals("btnPrintInvoice")) {
            printInvoice();
        } else if (comp.getName().equals("btnViewGL")) {
            viewGL();
        }
          else if (comp.getName().equals("btnSummaryofApplicant")) {
            printAllApplicant();
        }
          else if (comp.getName().equals("btnTestApplicants")) {
            printTestApplicant();
        }
    }
    private void printAllApplicant(){
    AbstractReportTemplate.getInstance().showReportFromFileTemplate("AdmissionSummaryofApplicants", "");
    }
    private void printTestApplicant(){
    AbstractReportTemplate.getInstance().showReportFromFileTemplate("AdmissionListofApplicantsForTesting", "");
    }
    protected void registerStudent() {
        Admission ad = (Admission) this.getBean();
        if (ad.seq == null || ad.seq == 0) {
            TransactionPanel.getCurrentPanel().saveRecord();
        }
        bean.Student studTmp = (bean.Student) AbstractIBean.firstRecord("SELECT a FROM Student a WHERE a.lastName='" , ad.lastName , "' AND a.firstName='" , ad.firstName , "' AND a.middleInitial='" , ad.middleInitial , "'");
        if (studTmp==null || studTmp.isEmptyKey()) {
            ad.register();
        } else {
            if (studTmp.course==null || studTmp.course.trim().isEmpty()) {
                ad.register();
            }
            else {
                PanelUtil.showMessage(usedComp, "Student already registered for ",studTmp.course);
            }
        }
    }

    protected void printInvoice() {
        createInvoice();
        Admission ad = (Admission) this.getBean();
        AbstractReportTemplate.getInstance().showReportFromFileTemplate("AdmissionInvoice", ad.invoiceId);
    }

    protected void viewGL() {
        Admission ad = (Admission) this.getBean();
        if (ad.seq == null || ad.seq == 0) {
            saveRecord();
        }
        if (ad.invoiceId==0) {
            createInvoice();
        }
        Invoice inv = (Invoice) ad.firstRecord("SELECT a FROM Invoice a WHERE a.seq=",ad.invoiceId);
        springbean.IProcess.Process.getInstance().showGL(inv);
    }
    
    
     protected void setAge(Admission admission) {
        int age = util.DateUtil.getSpanYears(admission.birthDate);
        setValue("age", age);
    }
    
    private void createInvoice() {
        Admission ad = (Admission) this.getBean();
        if (ad.seq == null || ad.seq == 0) {
//            this will save the record if not yet save
            saveRecord();
        }
        if (ad.invoiceId == 0) {
//            this will create payment for admission
            PaymentAdmission pay = new PaymentAdmission();
            pay.accountNumber = ad.accountNumber;
            pay.amount = ad.examinationFee;
            pay.paymentFor = "ADMISSION";
            pay.form = "ADMISSION";
            pay.description = "ADMISSION";
            pay.paid = true;
            pay.paidBy = ad.getPersonId();
            pay.recordId = ad.seq;
            pay.amountPaid = ad.examinationFee;
            pay.dueDate = constants.Constants.useDate;
            pay.paymentDate = constants.Constants.useDate;
            pay.paymentTerms = "CASH";

//          this will create invoice
            bean.accounting.Invoice inv = pay.extractInvoice(pay);
            inv.billTo = ad.toString();
            inv.save();

//          this will update the invoice id of admission and payment
            pay.invoiceId = inv.seq;
            pay.save();

            ad.invoiceId = inv.seq;
            ad.save();
            
            GLPostingScript.post(inv);
        }
    }
}
