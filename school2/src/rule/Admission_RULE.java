/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import bean.Admission;
import bean.reference.GradeLevel;
import bean.accounting.GLPostingScript;
import bean.accounting.Invoice;
import bean.accounting.PaymentAdmission;
import constants.UserInfo;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import service.util.AbstractIBean;
import springbean.SchoolDefaultProcess;
import template.report.AbstractReportTemplate;
import template.screen.TransactionPanel;
import util.PanelUtil;
import util.DBClient;

/**
 *
 * @author Entokwaa
 */
public class Admission_RULE extends BusinessRuleWrapper {

    String rate = "";

    @Override
    public void onChangeRecord() {
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
		        Admission ad = (Admission) getBean();
		        if (ad!=null && getComponent("recommendationCondition")!=null) {
		            if(ad.recommendation!=null)
		                    getComponent("recommendationCondition").setEnabled(ad.recommendation.equals("ACCEPTED ON CONDITION"));
		        }
			}
    	});
    }

    @Override
    public void onLoad() {
        if (!UserInfo.canPrintInvoice()) {
//            getComponent("btnPrintInvoice").setEnabled(false);
        }
        if (!UserInfo.canViewGL()) {
//            getComponent("btnViewGL").setEnabled(false);
        }
        if (!UserInfo.canAssessStudent()) {
            //getComponent("examinationFee").setEnabled(false);
            //getComponent("accountNumber").setEnabled(false);
            //getComponent("orNumber").setEnabled(false);
            //getComponent("btnorNumber").setEnabled(false);
        }
        if (!UserInfo.canUpdateExamDetails()) {
            getComponent("orDate").setEnabled(false);
            getComponent("orNumber").setEnabled(false);
//            getComponent("elaCount").setEnabled(false);
//            getComponent("elaScore").setEnabled(false);
//            getComponent("elaPercentage").setEnabled(false);
//            getComponent("mathCount").setEnabled(false);
//            getComponent("mathScore").setEnabled(false);
//            getComponent("mathPercentage").setEnabled(false);
//            getComponent("sciCount").setEnabled(false);
//            getComponent("sciScore").setEnabled(false);
//            getComponent("sciPercentage").setEnabled(false);
//            getComponent("itemTotal").setEnabled(false);
//            getComponent("itemScore").setEnabled(false);
//            getComponent("itemPercentage").setEnabled(false);
        }
    }

    @Override
    public void runFocusLost(JComponent comp) {
        Admission admission = (Admission) panel.getCurrentObject();
        if (comp.getName().equalsIgnoreCase("rating")) {
            if (admission.rating >= SchoolDefaultProcess.MAX_GRADE_RECALCULATE) {
                rate = "PASSED";
            } else {
                rate = "FAILED";
            }
            setValue("remarks", rate + "");
        }
        if ("birthDate".equalsIgnoreCase(comp.getName())) {
            setAge(admission);
        }
        if ("gradeLevel".equals(comp.getName())) {
            changeItemCount();
        }
        if ("elaCount|mathCount|sciCount|elaScore|mathScore|sciScore".contains(comp.getName())) {
            changePercentageRemarks();
        }
    }

    private int getPercentage(int down, int top) {
        double d1 = top;
        double d2 = down;
        return (int) ((d1/d2) * 100);
    }

    private void changePercentageRemarks() {
        Admission ad = (Admission) this.getBean();
        setValue("itemTotal", ad.elaCount+ad.mathCount+ad.sciCount);
        setValue("itemScore", ad.elaScore+ad.mathScore+ad.sciScore);

        if (ad.elaCount>0 && ad.elaScore>0) setValue("elaPercentage", getPercentage(ad.elaCount,ad.elaScore));
        if (ad.mathCount>0 && ad.mathScore>0) setValue("mathPercentage", getPercentage(ad.mathCount,ad.mathScore));
        if (ad.sciCount>0 && ad.sciScore>0) setValue("sciPercentage", getPercentage(ad.sciCount,ad.sciScore));
        setValue("itemPercentage", getPercentage(ad.itemTotal, ad.itemScore));

        setValue("elaRemarks", ad.elaPercentage>50?"PASSED":"FAILED");
        setValue("mathRemarks", ad.mathPercentage>50?"PASSED":"FAILED");
        setValue("sciRemarks", ad.sciPercentage>50?"PASSED":"FAILED");
        setValue("totalRemarks", ad.itemPercentage>50?"PASSED":"FAILED");

        getComponent("recommendationCondition").setEnabled(false);
        setValue("recommendationCondition", "");
//        category A
        if (ad.elaPercentage>=50 && ad.mathPercentage>=50 && ad.sciPercentage>=50) {
//            just need to have 50 percent of items
            setValue("recommendation", "ACCEPTED");
        }
        else {
            if (ad.elaPercentage<40 || ad.mathPercentage<40 || ad.sciPercentage<40) {
                setValue("recommendation", "NOT ACCEPTED");
            }
//        category B
            else {
                if (ad.elaPercentage>=50 && ad.mathPercentage>=50) {
                    setValue("recommendation", "ACCEPTED ON CONDITION");
                    getComponent("recommendationCondition").setEnabled(true);
                    setValue("recommendationCondition", "REMEDIAL ON SCIENCE");
                }
                else if (ad.mathPercentage>=50 && ad.sciPercentage>=50) {
                    setValue("recommendation", "ACCEPTED ON CONDITION");
                    getComponent("recommendationCondition").setEnabled(true);
                    setValue("recommendationCondition", "REMEDIAL ON ENGLISH");
                }
                else if (ad.elaPercentage>=50 && ad.sciPercentage>=50) {
                    setValue("recommendation", "ACCEPTED ON CONDITION");
                    getComponent("recommendationCondition").setEnabled(true);
                    setValue("recommendationCondition", "REMEDIAL ON MATHEMATICS");
                }
                else {
                    setValue("recommendation", "NOT ACCEPTED");
                }
            }
        }
    }

    private void changeItemCount() {
        Admission ad = (Admission) this.getBean();
        if (ad.gradeLevel!=null && ad.schoolYear!=null) {
            GradeLevel ref = (GradeLevel) GradeLevel.extractObject(GradeLevel.class.getSimpleName(), ad.gradeLevel);
            setValue("elaCount", ref.elaItemCount);
            setValue("mathCount", ref.mathItemCount);
            setValue("sciCount", ref.sciItemCount);
            setValue("pracCount", ref.pracItemCount);
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
    }

    protected void registerStudent() {
        Admission ad = (Admission) this.getBean();
        if (ad.seq == null || ad.seq == 0) {
            TransactionPanel.getCurrentPanel().saveRecord();
        }
        bean.Student studTmp = (bean.Student) AbstractIBean.firstRecord("SELECT a FROM Student a WHERE a.lastName='" + ad.lastName + "' AND a.firstName='" + ad.firstName + "' AND a.middleInitial='" + ad.middleInitial + "'");
        if (studTmp==null || studTmp.isEmptyKey()) {
            ad.register();
        } else {
            if (studTmp.course==null || studTmp.course.trim().isEmpty()) {
                ad.register();
            }
            else {
                PanelUtil.showMessage(usedComp, "Student already registered for "+studTmp.course);
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
        Invoice inv = (Invoice) ad.firstRecord("SELECT a FROM Invoice a WHERE a.seq="+ad.invoiceId);
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
