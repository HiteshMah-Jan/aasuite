package bean.extension;

import bean.Employee;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import bean.accounting.payroll.EmployeePayroll;
import bean.accounting.payroll.EmployeePayrollAdjustment;
import template.screen.TemplateTabSinglePage;

@UITemplate(template = TemplateTabSinglePage.class, gridCount = 4,
columnSearch = {"employeeName"})
@Displays({
    @Display(name = "employeeId", gridFieldWidth = 3, label = "Employee",type="PopSearch", linktoBean=Employee.class)
})        
@ChildRecords(value={
    @ChildRecord(entity=EmployeePayrollAdjustment.class, template=ChildTemplateListPopupDownButton.class, fieldMapping={"seq","employeePayrollId"}, sql="SELECT a FROM EmployeePayrollAdjustment a WHERE a.employeePayrollId = ${seq}", sortable=false)
})
public class EmployeePayrollExt extends EmployeePayroll {
      public static void main(String[] args) {
        view(EmployeePayrollExt.class);
    }
  
}
