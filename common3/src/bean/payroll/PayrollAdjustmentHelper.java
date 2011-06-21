package bean.payroll;

import java.util.ArrayList;
import java.util.List;

import bean.reference.EmployeePositionRef;
import bean.reference.EmployeeTaxStatus;

import util.BeanUtil;
import util.DBClient;

public class PayrollAdjustmentHelper {
	public static PayrollAdjustmentRef createAdjustment(String code, String position, String status, int sortNum) {
		PayrollAdjustmentRef t = new PayrollAdjustmentRef();
		t.adjustmentName = code;
		t.position = position;
		t.employeeTaxStatus = status;
		t.deduct = true;
		t.sortNum = sortNum;
		return t;
	}

	public static PayrollAdjustmentRefConfigTable createAdjustmentTable(PayrollAdjustmentRef t, int line, double fromAmount, double toAmount, double fixedAmount, double percentageAfterFromAmount) {
		PayrollAdjustmentRefConfigTable d = new PayrollAdjustmentRefConfigTable();
		d.payrollAdjustmentId = t.seq;
		d.line = line;
		d.fromAmount = fromAmount;
		d.toAmount = toAmount;
		d.fixedAmount = fixedAmount;
		d.percentageAfterFromAmount = percentageAfterFromAmount;
		d.description = BeanUtil.concat(t.adjustmentName," ",t.position," ",t.employeeTaxStatus);
		return d;
	}

	public static void runSSS() {
		if (DBClient.exist("SELECT a FROM PayrollAdjustmentRef a WHERE a.adjustmentName='SSS'")) {
			return;
		}
		PayrollAdjustmentRef ded = createAdjustment("SSS", EmployeeTaxStatus.ANY, EmployeeTaxStatus.ANY, 100);
		ded.save();
		List lst = new ArrayList();
		lst.add(createAdjustmentTable(ded, 1, 1000, 1250 - 1, 33.30, 0));
		lst.add(createAdjustmentTable(ded, 2, 1250, 1750 - 1, 50.00, 0));
		lst.add(createAdjustmentTable(ded, 3, 1750, 2250 - 1, 66.70, 0));
		lst.add(createAdjustmentTable(ded, 4, 2250, 2750 - 1, 83.30, 0));
		lst.add(createAdjustmentTable(ded, 5, 2750, 3250 - 1, 100.00, 0));
		lst.add(createAdjustmentTable(ded, 6, 3250, 3750 - 1, 116.70, 0));
		lst.add(createAdjustmentTable(ded, 7, 3750, 4250 - 1, 133.30, 0));
		lst.add(createAdjustmentTable(ded, 8, 4250, 4750 - 1, 150.00, 0));
		lst.add(createAdjustmentTable(ded, 9, 4750, 5250 - 1, 166.70, 0));
		lst.add(createAdjustmentTable(ded, 10, 5250, 5750 - 1, 183.30, 0));
		lst.add(createAdjustmentTable(ded, 11, 5750, 6250 - 1, 200.00, 0));
		lst.add(createAdjustmentTable(ded, 12, 6250, 6750 - 1, 216.70, 0));
		lst.add(createAdjustmentTable(ded, 13, 6750, 7250 - 1, 233.30, 0));
		lst.add(createAdjustmentTable(ded, 14, 7250, 7750 - 1, 250.00, 0));
		lst.add(createAdjustmentTable(ded, 15, 7750, 8250 - 1, 266.70, 0));
		lst.add(createAdjustmentTable(ded, 16, 8250, 8750 - 1, 283.30, 0));
		lst.add(createAdjustmentTable(ded, 17, 8750, 9250 - 1, 300.00, 0));
		lst.add(createAdjustmentTable(ded, 18, 9250, 9750 - 1, 316.70, 0));
		lst.add(createAdjustmentTable(ded, 19, 9750, 10250 - 1, 333.30, 0));
		lst.add(createAdjustmentTable(ded, 20, 10250, 10750 - 1, 350.00, 0));
		lst.add(createAdjustmentTable(ded, 21, 10750, 11250 - 1, 366.70, 0));
		lst.add(createAdjustmentTable(ded, 22, 11250, 11750 - 1, 383.30, 0));
		lst.add(createAdjustmentTable(ded, 23, 11750, 12250 - 1, 400.00, 0));
		lst.add(createAdjustmentTable(ded, 24, 12250, 12750 - 1, 416.70, 0));
		lst.add(createAdjustmentTable(ded, 25, 12750, 13250 - 1, 433.30, 0));
		lst.add(createAdjustmentTable(ded, 26, 13250, 13750 - 1, 450.00, 0));
		lst.add(createAdjustmentTable(ded, 27, 13750, 14250 - 1, 466.70, 0));
		lst.add(createAdjustmentTable(ded, 28, 14250, 14750 - 1, 483.30, 0));
		lst.add(createAdjustmentTable(ded, 29, 14750, 500000 - 1, 500.00, 0));
		DBClient.persistBean(lst);
	}

	public static void runPhilhealth() {
		if (DBClient.exist("SELECT a FROM PayrollAdjustmentRef a WHERE a.adjustmentName='PHILHEALTH'")) {
			return;
		}
		PayrollAdjustmentRef ded = createAdjustment("PHILHEALTH", EmployeeTaxStatus.ANY, EmployeeTaxStatus.ANY, 110);
		ded.save();
		List lst = new ArrayList();
		lst.add(createAdjustmentTable(ded, 1, 1, 5000 - 1, 50.00, 0));
		lst.add(createAdjustmentTable(ded, 2, 5000, 6000 - 1, 62.50, 0));
		lst.add(createAdjustmentTable(ded, 3, 6000, 7000 - 1, 75.00, 0));
		lst.add(createAdjustmentTable(ded, 4, 7000, 8000 - 1, 87.50, 0));
		lst.add(createAdjustmentTable(ded, 5, 8000, 9000 - 1, 100.00, 0));
		lst.add(createAdjustmentTable(ded, 6, 9000, 10000 - 1, 112.50, 0));
		lst.add(createAdjustmentTable(ded, 7, 10000, 11000 - 1, 125.00, 0));
		lst.add(createAdjustmentTable(ded, 8, 11000, 12000 - 1, 137.50, 0));
		lst.add(createAdjustmentTable(ded, 9, 12000, 13000 - 1, 150.00, 0));
		lst.add(createAdjustmentTable(ded, 10, 13000, 14000 - 1, 162.50, 0));
		lst.add(createAdjustmentTable(ded, 11, 14000, 15000 - 1, 175.00, 0));
		lst.add(createAdjustmentTable(ded, 12, 16000, 17000 - 1, 200.00, 0));
		lst.add(createAdjustmentTable(ded, 13, 17000, 18000 - 1, 212.50, 0));
		lst.add(createAdjustmentTable(ded, 14, 18000, 19000 - 1, 225.00, 0));
		lst.add(createAdjustmentTable(ded, 15, 19000, 20000 - 1, 237.50, 0));
		lst.add(createAdjustmentTable(ded, 16, 20000, 21000 - 1, 250.00, 0));
		lst.add(createAdjustmentTable(ded, 17, 21000, 22000 - 1, 262.50, 0));
		lst.add(createAdjustmentTable(ded, 18, 22000, 23000 - 1, 275.00, 0));
		lst.add(createAdjustmentTable(ded, 19, 23000, 24000 - 1, 287.50, 0));
		lst.add(createAdjustmentTable(ded, 20, 24000, 25000 - 1, 300.00, 0));
		lst.add(createAdjustmentTable(ded, 21, 25000, 26000 - 1, 312.50, 0));
		lst.add(createAdjustmentTable(ded, 22, 26000, 27000 - 1, 325.00, 0));
		lst.add(createAdjustmentTable(ded, 23, 27000, 28000 - 1, 337.50, 0));
		lst.add(createAdjustmentTable(ded, 24, 28000, 29000 - 1, 350.00, 0));
		lst.add(createAdjustmentTable(ded, 25, 29000, 30000 - 1, 362.50, 0));
		lst.add(createAdjustmentTable(ded, 26, 30000, 500000 - 1, 375.00, 0));
		DBClient.persistBean(lst);
	}

	public static void runTaxZ() {
		if (DBClient.exist("SELECT a FROM PayrollAdjustmentRef a WHERE a.adjustmentName='WITHHOLDING TAX' AND a.employeeTaxStatus='TAX-Z'")) {
			return;
		}
		PayrollAdjustmentRef ded = createAdjustment("WITHHOLDING TAX", EmployeeTaxStatus.ANY, "TAX-Z", 200);
		ded.save();
		List lst = new ArrayList();
		lst.add(createAdjustmentTable(ded, 1, 417, 1250 - 1, 20.83, 10));
		lst.add(createAdjustmentTable(ded, 2, 1250, 2917 - 1, 104.17, 15));
		lst.add(createAdjustmentTable(ded, 3, 2917, 5833 - 1, 354.17, 20));
		lst.add(createAdjustmentTable(ded, 4, 5833, 10417 - 1, 937.50, 25));
		lst.add(createAdjustmentTable(ded, 5, 10417, 20833 - 1, 2083.33, 30));
		lst.add(createAdjustmentTable(ded, 6, 20833, 20833, 5208.33, 32));
		DBClient.persistBean(lst);
	}

	public static void runTaxSme() {
		if (DBClient.exist("SELECT a FROM PayrollAdjustmentRef a WHERE a.adjustmentName='WITHHOLDING TAX' AND a.employeeTaxStatus='TAX-SME'")) {
			return;
		}
		PayrollAdjustmentRef ded = createAdjustment("WITHHOLDING TAX", EmployeeTaxStatus.ANY, "TAX-SME", 200);
		ded.save();
		List lst = new ArrayList();
		lst.add(createAdjustmentTable(ded, 1, 2083, 2500 - 1, 0, 5));
		lst.add(createAdjustmentTable(ded, 2, 2500, 3333 - 1, 20.83, 10));
		lst.add(createAdjustmentTable(ded, 3, 3333, 5000 - 1, 104.17, 15));
		lst.add(createAdjustmentTable(ded, 4, 5000, 7917 - 1, 354.17, 20));
		lst.add(createAdjustmentTable(ded, 5, 7917, 12500 - 1, 937.50, 25));
		lst.add(createAdjustmentTable(ded, 6, 12500, 22917 - 1, 2083.33, 30));
		lst.add(createAdjustmentTable(ded, 7, 22917, 22917, 5208.33, 32));
		DBClient.persistBean(lst);
	}

	public static void runTaxSme1() {
		if (DBClient.exist("SELECT a FROM PayrollAdjustmentRef a WHERE a.adjustmentName='WITHHOLDING TAX' AND a.employeeTaxStatus='TAX-SME1'")) {
			return;
		}
		PayrollAdjustmentRef ded = createAdjustment("WITHHOLDING TAX", EmployeeTaxStatus.ANY, "TAX-SME1", 200);
		ded.save();
		List lst = new ArrayList();
		lst.add(createAdjustmentTable(ded, 1, 3125, 3542 - 1, 0, 5));
		lst.add(createAdjustmentTable(ded, 2, 3542, 4375 - 1, 20.83, 10));
		lst.add(createAdjustmentTable(ded, 3, 4375, 6042 - 1, 104.17, 15));
		lst.add(createAdjustmentTable(ded, 4, 6042, 8958 - 1, 354.17, 20));
		lst.add(createAdjustmentTable(ded, 5, 8958, 13542 - 1, 937.50, 25));
		lst.add(createAdjustmentTable(ded, 6, 13542, 23958 - 1, 2083.33, 30));
		lst.add(createAdjustmentTable(ded, 7, 23958, 23958, 5208.33, 32));
		DBClient.persistBean(lst);
	}

	public static void runTaxSme2() {
		if (DBClient.exist("SELECT a FROM PayrollAdjustmentRef a WHERE a.adjustmentName='WITHHOLDING TAX' AND a.employeeTaxStatus='TAX-SME2'")) {
			return;
		}
		PayrollAdjustmentRef ded = createAdjustment("WITHHOLDING TAX", EmployeeTaxStatus.ANY, "TAX-SME2", 200);
		ded.save();
		List lst = new ArrayList();
		lst.add(createAdjustmentTable(ded, 1, 4167, 4583 - 1, 0, 5));
		lst.add(createAdjustmentTable(ded, 2, 4583, 5417 - 1, 20.83, 10));
		lst.add(createAdjustmentTable(ded, 3, 5417, 7083 - 1, 104.17, 15));
		lst.add(createAdjustmentTable(ded, 4, 7083, 10000 - 1, 354.17, 20));
		lst.add(createAdjustmentTable(ded, 5, 10000, 14583 - 1, 937.50, 25));
		lst.add(createAdjustmentTable(ded, 6, 14583, 25000 - 1, 2083.33, 30));
		lst.add(createAdjustmentTable(ded, 7, 25000, 25000, 5208.33, 32));
		DBClient.persistBean(lst);
	}

	public static void runTaxSme3() {
		if (DBClient.exist("SELECT a FROM PayrollAdjustmentRef a WHERE a.adjustmentNam='WITHHOLDING TAX' AND a.employeeTaxStatus='TAX-SME3'")) {
			return;
		}
		PayrollAdjustmentRef ded = createAdjustment("WITHHOLDING TAX", EmployeeTaxStatus.ANY, "TAX-SME3", 200);
		ded.save();
		List lst = new ArrayList();
		lst.add(createAdjustmentTable(ded, 1, 5208, 5625 - 1, 0, 5));
		lst.add(createAdjustmentTable(ded, 2, 5625, 6458 - 1, 20.83, 10));
		lst.add(createAdjustmentTable(ded, 3, 6458, 8125 - 1, 104.17, 15));
		lst.add(createAdjustmentTable(ded, 4, 8125, 11042 - 1, 354.17, 20));
		lst.add(createAdjustmentTable(ded, 5, 11042, 15625 - 1, 937.50, 25));
		lst.add(createAdjustmentTable(ded, 6, 15625, 26042 - 1, 2083.33, 30));
		lst.add(createAdjustmentTable(ded, 7, 26042, 26042, 5208.33, 32));
		DBClient.persistBean(lst);
	}

	public static void runTaxSme4() {
		if (DBClient.exist("SELECT a FROM PayrollAdjustmentRef a WHERE a.adjustmentName='WITHHOLDING TAX' AND a.employeeTaxStatus='TAX-SME4'")) {
			return;
		}
		PayrollAdjustmentRef ded = createAdjustment("WITHHOLDING TAX", EmployeeTaxStatus.ANY, "TAX-SME4", 200);
		ded.save();
		List lst = new ArrayList();
		lst.add(createAdjustmentTable(ded, 1, 6250, 6667 - 1, 0, 5));
		lst.add(createAdjustmentTable(ded, 2, 6667, 7500 - 1, 20.83, 10));
		lst.add(createAdjustmentTable(ded, 3, 7500, 9167 - 1, 104.17, 15));
		lst.add(createAdjustmentTable(ded, 4, 9167, 12083 - 1, 354.17, 20));
		lst.add(createAdjustmentTable(ded, 5, 12083, 16667 - 1, 937.50, 25));
		lst.add(createAdjustmentTable(ded, 6, 16667, 27083, 2083.33, 30));
		lst.add(createAdjustmentTable(ded, 7, 27083, 27083, 5208.33, 32));
		DBClient.persistBean(lst);
	}

	public static void runTaxStatus() {
		List lst = new ArrayList();
		lst.add(newStatus(EmployeeTaxStatus.ANY, EmployeeTaxStatus.ANY));
		lst.add(newStatus("TAX-Z", "SINGLE"));
		lst.add(newStatus("TAX-SME", "SME"));
		lst.add(newStatus("TAX-SME1", "SME 1"));
		lst.add(newStatus("TAX-SME2", "SME 2"));
		lst.add(newStatus("TAX-SME3", "SME 3"));
		lst.add(newStatus("TAX-SME4", "SME 4"));

		lst.add(newPosition(EmployeeTaxStatus.ANY, EmployeeTaxStatus.ANY));
		DBClient.persistBean(lst);
	}
	
	private static EmployeeTaxStatus newStatus(String code, String description) {
		EmployeeTaxStatus s = new EmployeeTaxStatus();
		s.code = code;
		s.description = description;
		return s;
	}

	private static EmployeePositionRef newPosition(String code, String description) {
		EmployeePositionRef s = new EmployeePositionRef();
		s.code = code;
		s.position = description;
		return s;
	}
}
