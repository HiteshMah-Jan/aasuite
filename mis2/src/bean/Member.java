/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import bean.admin.AppMenu;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateDefault;
import util.DBClient;

/**
 *
 * @author Entokwaa
 */
@Entity
@Table(name = "Person")
@UITemplate(template = TemplateDefault.class, gridCount=4, columnSearch = {"lastName", "firstName", "center"})
@Displays({
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="center", type="PopSearch", linktoBean=Center.class),
        @Display(name="shareDate"),
        @Display(name="totalLoanAmount"),
        @Display(name="totalLoanInterest"),
        @Display(name="totalLoanPayment"),
        @Display(name="totalDividends")
})
@ChildRecords(
    value={
       @ChildRecord(title="Loan", entity = MemberLoan.class, sql = "SELECT a FROM MemberLoan a WHERE a.memberId=${personId}"),
       @ChildRecord(title="Register", entity = MemberRegister.class, sql = "SELECT a FROM MemberRegister a WHERE a.memberId=${personId}"),
       @ChildRecord(title="Passbook", entity = MemberPassbook.class, sql = "SELECT a FROM MemberPassbook a WHERE a.memberId=${personId}"),
       @ChildRecord(title="Dividends", entity = MemberDividends.class, sql = "SELECT a FROM MemberDividends a WHERE a.memberId=${personId}")
 }
)
@ActionButtons( {
		@ActionButton(name = "btnAddLoan", label = "Add Loan"),
		@ActionButton(name = "btnAddPayment", label = "Add Payment"),
		@ActionButton(name = "btnComputeDividends", label = "Compute Dividends") }
)
@Reports({ 
    @template.Report(reportFile="CenterMemberList", reportTitle="Center Member List", reportSql="${center}"),
    @template.Report(reportFile="BranchMemberList", reportTitle="Branch Member List", reportSql="${branch}"),
    @template.Report(reportFile="AllMemberList", reportTitle="All Member List", reportSql="")
})
public class Member extends Person implements Serializable {
    @Column(name = "branch")
    public String branch;
    @Column(name = "center")
    public String center;
    @Column(name = "shareDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date shareDate;
    @Column(name = "totalLoanAmount")
    public Double totalLoanAmount;
    @Column(name = "totalLoanInterest")
    public Double totalLoanInterest;
    @Column(name = "totalLoanPayment")
    public Double totalLoanPayment;
    @Column(name = "totalDividends")
    public Double totalDividends;

    public Double getTotalDividends() {
        return totalDividends;
    }

    public void setTotalDividends(Double totalDividends) {
        this.totalDividends = totalDividends;
    }

    public Double getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void setTotalLoanAmount(Double totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public Double getTotalLoanInterest() {
        return totalLoanInterest;
    }

    public void setTotalLoanInterest(Double totalLoanInterest) {
        this.totalLoanInterest = totalLoanInterest;
    }

    public Double getTotalLoanPayment() {
        return totalLoanPayment;
    }

    public void setTotalLoanPayment(Double totalLoanPayment) {
        this.totalLoanPayment = totalLoanPayment;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }
    
    @Override
    public void runSetup() {
//        select concat('createAppMenuObj("',moduleName,'", "',moduleLabel,'", "',menuLabel,'", ',sortNum,').save();') from appmenu order by sortNum
        List lst = DBClient.getList("SELECT a FROM AppMenu a");
        if (lst!=null && !lst.isEmpty()) {
            return;
        }
        AppMenu.createAppMenuObj("MyBranchMemberExt", "Main", "Branch Member", 110).save();
        AppMenu.createAppMenuObj("MyCenterMemberExt", "Main", "Center Member", 120).save();
        AppMenu.createAppMenuObj("SearchAllMembersOnlyExt", "Main", "Search Member", 130).save();
        AppMenu.createAppMenuObj("ReferenceForm", "Reference", "Reference", 200).save();
    }
}
