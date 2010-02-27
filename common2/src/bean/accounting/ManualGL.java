/*
 * Expense.java
 * 
 * Created on Feb 12, 2008, 11:03:04 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.accounting;

import bean.reference.Department;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author pogi
 */
@Entity
@Table(name = "ManualGL")
@UITemplate(title="Manual GL Posting", template = TemplateTabSinglePage.class, gridCount = 4, criteriaSearch = {"chargeDepartment"}, columnSearch = {"creationDate", "chargeDepartment", "reason", "posted"})
@Displays({
        @Display(name="creationDate"),
        @Display(name="chargeDepartment", type = "PopSearch", linktoBean = Department.class),
        @Display(name="reason",gridFieldWidth=3,width=-1)
})
@template.ChildRecords({
    @template.ChildRecord(title="GL Entries", entity = ManualGLDetail.class, sql = "SELECT a FROM ManualGLDetail a WHERE a.manualGLId=${seq}", fieldMapping={"seq","manualGLId"})
})
@template.ActionButtons({
    @template.ActionButton(name="btnCreateGL", label="Post GL", parentOnly=true),
    @template.ActionButton(name="btnViewGL", label="View GL", parentOnly=true)
})
public class ManualGL extends AbstractIBean implements Serializable, IGL {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "creationDate", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date creationDate;
    @Column(name = "chargeDepartment", nullable = false)
    public String chargeDepartment;
    @Column(name = "amount", nullable = false)
    public double amount;
    @Column(name = "reason")
    public String reason;
    @Column(name = "posted")
    public boolean posted;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public static void main(String[] args) {
        view(ManualGL.class);
    }
    
    public String getForm() {
        return "ManualGL";
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getChargeDepartment() {
        return chargeDepartment;
    }

    public void setChargeDepartment(String chargeDepartment) {
        this.chargeDepartment = chargeDepartment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String extractGLSubType() {
        return "";
    }
    
    public boolean hardcodePosting() {
        if (this.posted) {
            Logger.getLogger("global").log(Level.INFO, "GL is already posted for "+this.extractGLType().toUpperCase()+"-"+seq+".");
            return true;
        }
        List<ManualGLDetail> details = list("SELECT a FROM ManualGLDetail a WHERE a.manualGLId="+seq);
        if (details!=null && details.size()>1 && isBalanced(details)) {
            for (ManualGLDetail mgl : details) {
                GL gl = new GL();
                gl.dateInput = this.creationDate;
                gl.accountNumber = mgl.accountNumber;
                gl.debit = mgl.debit;
                gl.credit = mgl.credit;
                gl.reason = mgl.reason;
                gl.accountName = gl.getAccountDescription();
                gl.form = getClass().getSimpleName().toUpperCase();
                gl.recordId = seq;
                gl.subLedger = "ManualGL";
                gl.save();
            }
            this.posted = true;
            save();
        }
        return true;
    }  
    
    private boolean isBalanced(List<ManualGLDetail> details) {
        double debit = 0;
        double credit = 0;
        for (ManualGLDetail gl : details) {
            debit += gl.debit;
            credit += gl.credit;
        }
        return debit>0 && debit==credit;
    }

    public boolean isBalanced() {
        List<ManualGLDetail> details = list("SELECT a FROM ManualGLDetail a WHERE a.manualGLId="+seq);
        return isBalanced(details);
    }

    public String extractDefaultFormula() {
        return "";
    }

    public String extractChargeDepartment() {
        return chargeDepartment;
    }
}
