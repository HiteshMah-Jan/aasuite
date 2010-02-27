/*
 * GeneralLedger.java
 *
 * Created on Nov 24, 2007, 8:44:41 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import bean.admin.AppConfig;
import constants.Constants;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

import service.ParamStruct;
import service.ReturnStruct;
import service.util.AbstractIBean;
import service.util.CallService;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "GLPostingScript")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, criteriaSearch = {"glType","glSubType"}, columnSearch = {"glType","glSubType","dateInput"})
@Displays({
        @Display(name="glType", width=200, type="Combo", modelCombo={"Invoice","AccountPayable","AccountReceivable","Expense","Payroll","PayrollPeriod","ManualGL"}),
        @Display(name="glSubType", width=200),
        @Display(name="script", type="TextArea2", gridFieldWidth=3, height=500, width=-1, upCase=false)
})
//@Reports({
//    @template.Report(reportFile="GLByDate", reportTitle="GL Posting", reportSql="${seq}"),
//    @template.Report(reportFile="TrialBalance", reportTitle="Trial Balance", reportSql="${seq}"),
//    @template.Report(reportFile="Receivable", reportTitle="Account Receivables", reportSql="${seq}")
//})
public class GLPostingScript extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "dateInput", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date dateInput;
    @Column(name = "glType")
    public String glType;
    @Column(name = "glSubType")
    public String glSubType;
    @Column(name = "script", length=4000)
    public String script;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Date getDateInput() {
        return dateInput;
    }

    public void setDateInput(Date dateInput) {
        this.dateInput = dateInput;
    }

    public String getGlSubType() {
        return glSubType;
    }

    public void setGlSubType(String glSubType) {
        this.glSubType = glSubType;
    }

    public String getGlType() {
        return glType;
    }

    public void setGlType(String glType) {
        this.glType = glType;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public static void main(String[] args) {
        view(GLPostingScript.class);
    }
    
    public static boolean isPosted(IGL gl) {
        if (gl.isPosted()) {
            Logger.getLogger("global").log(Level.INFO, "GL is already posted for "+gl.extractGLType().toUpperCase()+"-"+gl.extractGLRecordId()+".");
            return true;
        }
        return false;
    }
    
    public void postServer(IGL gl) {
        String type = gl.extractGLType().toUpperCase();
        String subType = gl.extractGLSubType();
        if (gl.hardcodePosting()) {
            Logger.getLogger("global").log(Level.INFO, "Hard code posting for GL["+type+"].");
            return;
        }
//        remove and repost
        if (AppConfig.isGLRepost()) {
            runSQL("DELETE FROM GL a WHERE a.form LIKE '",gl.extractGLSubType().toUpperCase(),"' AND a.recordId=",gl.extractGLRecordId());
        }
        if (gl.isPosted()) {
            Logger.getLogger("global").log(Level.INFO, "GL is already posted for "+type+"-"+gl.extractGLRecordId()+".");
            return;
        }
        GLPostingScript scr = null;
        if (subType==null || subType.isEmpty()) {
            scr = (GLPostingScript) firstRecord("SELECT a FROM GLPostingScript a WHERE a.glType='",type,"' AND a.glSubType IS NULL");
        }
        else {
            scr = (GLPostingScript) firstRecord("SELECT a FROM GLPostingScript a WHERE a.glType='",type,"' AND a.glSubType='",subType,"'");
        }
        if (scr==null) {
            scr = new GLPostingScript();
            scr.dateInput = constants.Constants.useDate;
            scr.glType = gl.extractGLType();
            scr.glSubType = subType;
            scr.script = gl.extractDefaultFormula();
            scr.save();
        }
        if (scr.script==null || scr.script.trim().length()<10) {
            Logger.getLogger("global").log(Level.INFO, "GL script not defined for "+type+"-"+gl.extractGLRecordId()+".");
            return;
        }
        util.ScriptRunner.runGroovy(scr.script, type, gl);
        List lst = list("SELECT a FROM GL a WHERE a.form='",type,"' AND a.recordId=",gl.extractGLRecordId());
        double debit = 0;
        double credit = 0;
        for (Object object : lst) {
            GL l = (GL) object;
            debit += l.debit;
            credit += l.credit;
        }
        if (debit!=credit || debit==0) {
            Logger.getLogger("global").log(Level.SEVERE, "GL is not balance for "+type+"-"+gl.extractGLRecordId()+". Removing entries.");
            runSQL("DELETE FROM GL a WHERE a.form='",type,"' AND a.recordId=",gl.extractGLRecordId());
        }
        else {
            gl.setPosted(true);
            gl.save();
        }
    }
    public void counterPostServer(IGL gl) {
        String type = gl.extractGLType().toUpperCase();
        if ("DELETE".equals(gl.extractCounterPostAccountNumber())) {
            //delete posting
            runSQL("DELETE FROM GL a WHERE a.form='",type,"' AND a.recordId=",gl.extractGLRecordId());
        }
        else {
            //should we reverse posting
        }
    }
    public static void post(IGL gl) {
        CallService.callService(gl, 1, GLPostingScript.class.getName());
    }
    public static void counterPost(IGL gl) {
        CallService.callService(gl, 2, GLPostingScript.class.getName());
    }
    public static void showGL(IGL gl) {
        springbean.IProcess.Process.getInstance().showGL(gl);
    }

    @Override
    public ReturnStruct callService(ParamStruct param) {
        if (param == null || param.getActionCommand() == 0) {
            return null;
        }
        IGL gl = (IGL) param.getData();
        ReturnStruct ret = new ReturnStruct();
        ret.setStatus(Constants.SUCCESS);
        ret.setData(gl);
        if (param.getActionCommand()==1) {
            postServer(gl);
        }
        else { 
            counterPostServer(gl);
        }
        return ret;
    }    
}
