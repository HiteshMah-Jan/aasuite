/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.Member;
import bean.MemberDividends;
import bean.MemberLoan;
import bean.MemberPassbook;
import bean.MemberRegister;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author disney
 */
@UITemplate(template = TemplateDefault.class, gridCount=4, columnSearch = {"lastName", "firstName", "center"})
@Displays({
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="center"),
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
		@ActionButton(name = "btnComputeDividends", label = "Compute Dividends"),
		@ActionButton(name = "btnAddBranchMember", label = "Add Member") }
)
@Reports({
    @template.Report(reportFile="CenterMemberList", reportTitle="Center Member List", reportSql="${center}"),
    @template.Report(reportFile="BranchMemberList", reportTitle="Branch Member List", reportSql="${branch}")
})
public class MyBranchMemberExt extends Member {
      public static void main(String[] args) {
        view(MyBranchMemberExt.class);
    }
}
