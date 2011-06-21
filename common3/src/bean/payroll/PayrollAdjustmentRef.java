package bean.payroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabSinglePageLeftRight;
import util.BeanUtil;
import util.DBClient;
import util.DataUtil;
import util.Log;
import util.PanelUtil;
import bean.reference.EmployeePositionRef;
import bean.reference.EmployeeTaxStatus;
import template.Reports;

@Entity
@Table(name = "PayrollAdjustmentRef")
@UITemplate(template = TemplateTabSinglePageLeftRight.class, gridCount = 4,
    columnSearch = {"sortNum", "adjustmentName", "position", "employeeTaxStatus"})
@Displays({
    @Display(name = "sortNum"),
    @Display(name = "adjustmentName"),
    @Display(name = "position", type = "PopSearch", linktoBean=EmployeePositionRef.class),
    @Display(name = "employeeTaxStatus", type = "PopSearch", linktoBean=EmployeeTaxStatus.class),
    @Display(name = "taxable", type="CheckBox"),
    @Display(name = "deduct", type="CheckBox")
})
@ChildRecords(value={
    @ChildRecord(entity=PayrollAdjustmentRefConfigTable.class, template=ChildTemplateListPopupDownButton.class, fieldMapping={"seq","payrollAdjustmentId"}, sql="SELECT a FROM PayrollAdjustmentRefConfigTable a WHERE a.payrollAdjustmentId = ${seq}", sortable=false)
})
@template.ActionButtons({
    @template.ActionButton(name="btnCreateDefault", label="Setup Tax Table"),
    @template.ActionButton(name="btnTestConfig", label="Test Configuration")
})
 @Reports( {
		@template.Report(reportFile = "TaxTable", reportTitle = "Adjustment List", reportSql = "")
	
})      
public class PayrollAdjustmentRef extends AbstractIBean {

    @Override
	public String toString() {
    	if (isEmptyKey()) {
    		return "-------";
    	}
		return BeanUtil.concat(adjustmentName,"[",position,"-",employeeTaxStatus,"]");
	}

	public static void main(String[] args) {
        view(PayrollAdjustmentRef.class);
    }
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "sortNum")
    public int sortNum;
    @Column(name = "adjustmentName", nullable = false)
    public String adjustmentName;
    @Column(name = "position")
    public String position;
    @Column(name = "employeeTaxStatus")
    public String employeeTaxStatus;
    @Column
    public boolean taxable;
    @Column
    public boolean deduct;

    @Override
    public String popupSearch(String criteria) {
        return null;
    }

    public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getEmployeeTaxStatus() {
        return employeeTaxStatus;
    }

    public void setEmployeeTaxStatus(String employeeTaxStatus) {
        this.employeeTaxStatus = employeeTaxStatus;
    }

    public boolean isDeduct() {
        return deduct;
    }

    public void setDeduct(boolean deduct) {
        this.deduct = deduct;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public String getAdjustmentName() {
        return adjustmentName;
    }

    public void setAdjustmentName(String adjustmentName) {
        this.adjustmentName = adjustmentName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public void runSetup() {
    	runUniqueIndex(1, "adjustmentName", "position", "employeeTaxStatus");
    }

    private static Map<String, PayrollAdjustmentRef> map;
    private static List<PayrollAdjustmentRef> lst;

    private static void cacheRef() {
        if (map == null) {
        	map = new HashMap<String, PayrollAdjustmentRef>();
            lst = DBClient.getList("SELECT a FROM PayrollAdjustmentRef a");
            for (PayrollAdjustmentRef p : lst) {
                map.put(BeanUtil.concat(p.adjustmentName,p.position,p.employeeTaxStatus), p);
            }
        }
    }

    public static double extractCalculateValue(String ref, String position, String taxStatus, double amount) {
        cacheRef();
        PayrollAdjustmentRef p = map.get(BeanUtil.concat(ref,position,taxStatus));
        if (p == null) {
            p = map.get(BeanUtil.concat(ref,EmployeePositionRef.ANY,taxStatus));
            if (p == null) {
                p = map.get(BeanUtil.concat(ref,position,EmployeePositionRef.ANY)); 
                if (p == null) {
                    p = map.get(BeanUtil.concat(ref,EmployeePositionRef.ANY,EmployeePositionRef.ANY));
                }
            }
        }
        return extractCalculateValue(p, amount);
    }

    public static double extractCalculateValue(PayrollAdjustmentRef ref, double amount) {
        double d = 0;
        List<PayrollAdjustmentRefConfigTable> lst = DBClient.getList(BeanUtil.concat("SELECT a FROM PayrollAdjustmentRefConfigTable a WHERE a.payrollAdjustmentId=",ref.seq," ORDER BY a.line"));
        for (PayrollAdjustmentRefConfigTable a:lst) {
            if (DataUtil.isBetween(a.fromAmount, a.toAmount, amount)) {
                if (a.percentageAfterFromAmount > 0) {
                    double fromToAmount = amount - a.fromAmount;
                    d = a.fixedAmount + (fromToAmount * a.percentageAfterFromAmount/100);
                }
                else {
                    d = a.fixedAmount;
                }
            }
        }
        return d;
    }

    public static List<PayrollAdjustmentRef> extractAll(String position, String taxStatus) {
        cacheRef();
        String bothAny = BeanUtil.concat(EmployeePositionRef.ANY,EmployeePositionRef.ANY);
        String staAny = BeanUtil.concat(EmployeePositionRef.ANY,taxStatus);
        String posAny = BeanUtil.concat(position,taxStatus);
        List<PayrollAdjustmentRef> reflst = new ArrayList<PayrollAdjustmentRef>();
        for (PayrollAdjustmentRef p : lst) {
        	Log.out("ADJUSTMENT == ",p);
            String combine = BeanUtil.concat(p.position,p.employeeTaxStatus);
            if (combine.equals(bothAny) || combine.equals(staAny) || combine.equals(posAny)) {
                reflst.add(p);
            }
        }
        return reflst;
    }

    public void test() {
        double s = PanelUtil.showPromptMessage(null, "Please provide an amount to test configuration", 100);
        if (s <= 0) {
            PanelUtil.showError(null, "Please input a numeric value.");
            return;
        }
        s = extractCalculateValue(this, s);
        PanelUtil.showMessage(null, BeanUtil.concat("Value is ",s));
    }
}
