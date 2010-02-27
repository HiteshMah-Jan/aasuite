/*
 * BenefitType.java
 *
 * Created on Feb 13, 2008, 8:28:16 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.reference;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import template.ActionButton;
import template.ActionButtons;
import template.Display;
import template.Displays;
import template.UITemplate;
import util.DBClient;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "CompensationType")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "DEDUCTION")
@UITemplate(canBackup=true,template=template.screen.TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"code"})
@Displays({
    @Display(name="code"),
    @Display(name="category",type="Combo",modelCombo={"TAX","LOAN","OTHERS"}),
    @Display(name="description",gridFieldWidth=3,width=500)
})
@template.ChildRecords({
       @template.ChildRecord(title="Config Table", entity = DeductionConfigTable.class, fieldMapping={"code","code"}, sql = "SELECT a FROM DeductionConfigTable a WHERE a.code='${code}'")
})
@ActionButtons({
    @ActionButton(name = "btnGenerateTable", label = "Generate Table")
})
public class DeductionType extends CompensationType implements Serializable {
	public static DeductionType createDeduction(String code, String category, String description) {
		DeductionType t = new DeductionType();
		t.code=code;
		t.category = category;
		t.description = description;
		return t;
	}
	
	public static DeductionConfigTable createDeductionTable(DeductionType t, int line, 
			double fromAmount, double toAmount, double fixedAmount, double percentage, double percentageAfterFromAmount, String description) {
		DeductionConfigTable d = new DeductionConfigTable();
		d.code = t.code;
		d.line = line;
		d.fromAmount = fromAmount;
		d.toAmount = toAmount;
		d.fixedAmount = fixedAmount;
		d.percentage = percentage;
		d.percentageAfterFromAmount = percentageAfterFromAmount;
		d.description = description;
		return d;
	}
	
	@Override
	public void runSetup() {
            runSSS();
            runPhilhealth();
            runTaxZ();
            runTaxSme();
            runTaxSme1();
            runTaxSme2();
            runTaxSme3();
            runTaxSme4();
	}

	private void runSSS() {
		if (DBClient.exist("SELECT a FROM DeductionType a WHERE a.code='SSS'")) return;
		DeductionType ded = createDeduction("SSS","","SSS");
		ded.save();
		createDeductionTable(ded, 1, 1000, 1250-1, 33.30, 0, 0, ded.description).save();
		createDeductionTable(ded, 2, 1250, 1750-1, 50.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 3, 1750, 2250-1, 66.70, 0, 0, ded.description).save();
		createDeductionTable(ded, 4, 2250, 2750-1, 83.30, 0, 0, ded.description).save();
                createDeductionTable(ded, 5, 2750, 3250-1, 100.00, 0, 0, ded.description).save();
		createDeductionTable(ded, 6, 3250, 3750-1, 116.70, 0, 0, ded.description).save();
                createDeductionTable(ded, 7, 3750, 4250-1, 133.30, 0, 0, ded.description).save();
		createDeductionTable(ded, 8, 4250, 4750-1, 150.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 9, 4750, 5250-1, 166.70, 0, 0, ded.description).save();
		createDeductionTable(ded, 10, 5250, 5750-1, 183.30, 0, 0, ded.description).save();
                createDeductionTable(ded, 11, 5750, 6250-1, 200.00, 0, 0, ded.description).save();
		createDeductionTable(ded, 12, 6250, 6750-1, 216.70, 0, 0, ded.description).save();
                createDeductionTable(ded, 13, 6750, 7250-1, 233.30, 0, 0, ded.description).save();
		createDeductionTable(ded, 14, 7250, 7750-1, 250.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 15, 7750, 8250-1, 266.70, 0, 0, ded.description).save();
		createDeductionTable(ded, 16, 8250, 8750-1, 283.30, 0, 0, ded.description).save();
                createDeductionTable(ded, 17, 8750, 9250-1, 300.00, 0, 0, ded.description).save();
		createDeductionTable(ded, 18, 9250, 9750-1, 316.70, 0, 0, ded.description).save();
                createDeductionTable(ded, 19, 9750, 10250-1, 333.30, 0, 0, ded.description).save();
		createDeductionTable(ded, 20, 10250, 10750-1, 350.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 21, 10750, 11250-1, 366.70, 0, 0, ded.description).save();
		createDeductionTable(ded, 22, 11250, 11750-1, 383.30, 0, 0, ded.description).save();
                createDeductionTable(ded, 23, 11750, 12250-1, 400.00, 0, 0, ded.description).save();
		createDeductionTable(ded, 24, 12250, 12750-1, 416.70, 0, 0, ded.description).save();
                createDeductionTable(ded, 25, 12750, 13250-1, 433.30, 0, 0, ded.description).save();
		createDeductionTable(ded, 26, 13250, 13750-1, 450.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 27, 13750, 14250-1, 466.70, 0, 0, ded.description).save();
		createDeductionTable(ded, 28, 14250, 14750-1, 483.30, 0, 0, ded.description).save();
                createDeductionTable(ded, 29, 14750, 500000-1, 500.00, 0, 0, ded.description).save();	
	}

	private void runPhilhealth() {
            if (DBClient.exist("SELECT a FROM DeductionType a WHERE a.code='PHILHEALTH'")) return;
		DeductionType ded = createDeduction("PHILHEALTH","","PHILHEALTH");
		ded.save();
		createDeductionTable(ded, 1, 1, 5000-1, 50.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 2, 5000, 6000-1, 62.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 3, 6000, 7000-1, 75.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 4, 7000, 8000-1, 87.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 5, 8000, 9000-1, 100.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 6, 9000, 10000-1, 112.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 7, 10000, 11000-1, 125.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 8, 11000, 12000-1, 137.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 9, 12000, 13000-1, 150.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 10, 13000, 14000-1, 162.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 11, 14000, 15000-1, 175.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 12, 16000, 17000-1, 200.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 13, 17000, 18000-1, 212.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 14, 18000, 19000-1, 225.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 15, 19000, 20000-1, 237.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 16, 20000, 21000-1, 250.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 17, 21000, 22000-1, 262.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 18, 22000, 23000-1, 275.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 19, 23000, 24000-1, 287.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 20, 24000, 25000-1, 300.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 21, 25000, 26000-1, 312.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 22, 26000, 27000-1, 325.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 23, 27000, 28000-1, 337.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 24, 28000, 29000-1, 350.00, 0, 0, ded.description).save();
                createDeductionTable(ded, 25, 29000, 30000-1, 362.50, 0, 0, ded.description).save();
                createDeductionTable(ded, 26, 30000, 500000-1, 375.00, 0, 0, ded.description).save();
	}
	
	private void runTaxZ() {
            if (DBClient.exist("SELECT a FROM DeductionType a WHERE a.code='TAX-Z'")) return;
		DeductionType ded = createDeduction("TAX-Z","","TAX-Z");
		ded.save();
		createDeductionTable(ded, 1, 417, 1250-1, 20.83, 0, 10, ded.description).save();
		createDeductionTable(ded, 2, 1250, 2917-1, 104.17, 0, 15, ded.description).save();
                createDeductionTable(ded, 3, 2917, 5833-1, 354.17, 0, 20, ded.description).save();
		createDeductionTable(ded, 4, 5833, 10417-1, 937.50, 0, 25, ded.description).save();
                createDeductionTable(ded, 5, 10417, 20833-1, 2083.33, 0, 30, ded.description).save();
		createDeductionTable(ded, 6, 20833, 20833, 5208.33, 0, 32, ded.description).save();
                
	}
        
        private void runTaxSme() {
            if (DBClient.exist("SELECT a FROM DeductionType a WHERE a.code='TAX-SME'")) return;
		DeductionType ded = createDeduction("TAX-SME","","TAX-S/ME");
		ded.save();
		createDeductionTable(ded, 1, 2083, 2500-1, 0, 0, 5, ded.description).save();
		createDeductionTable(ded, 2, 2500, 3333-1, 20.83, 0, 10, ded.description).save();
                createDeductionTable(ded, 3, 3333, 5000-1, 104.17, 0, 15, ded.description).save();
		createDeductionTable(ded, 4, 5000, 7917-1, 354.17, 0, 20, ded.description).save();
                createDeductionTable(ded, 5, 7917, 12500-1, 937.50, 0, 25, ded.description).save();
		createDeductionTable(ded, 6, 12500, 22917-1, 2083.33, 0, 30, ded.description).save();
                createDeductionTable(ded, 7, 22917, 22917, 5208.33, 0, 32, ded.description).save();
                
	}
        
        private void runTaxSme1() {
            if (DBClient.exist("SELECT a FROM DeductionType a WHERE a.code='TAX-SME1'")) return;
		DeductionType ded = createDeduction("TAX-SME1","","TAX-ME1/S1");
		ded.save();
		createDeductionTable(ded, 1, 3125, 3542-1, 0, 0, 5, ded.description).save();
		createDeductionTable(ded, 2, 3542, 4375-1, 20.83, 0, 10, ded.description).save();
                createDeductionTable(ded, 3, 4375, 6042-1, 104.17, 0, 15, ded.description).save();
		createDeductionTable(ded, 4, 6042, 8958-1, 354.17, 0, 20, ded.description).save();
                createDeductionTable(ded, 5, 8958, 13542-1, 937.50, 0, 25, ded.description).save();
		createDeductionTable(ded, 6, 13542, 23958-1, 2083.33, 0, 30, ded.description).save();
                createDeductionTable(ded, 7, 23958, 23958, 5208.33, 0, 32, ded.description).save();
                
	}
        
         private void runTaxSme2() {
                if (DBClient.exist("SELECT a FROM DeductionType a WHERE a.code='TAX-SME2'")) return;
		DeductionType ded = createDeduction("TAX-SME2","","TAX-ME2/S2");
		ded.save();
		createDeductionTable(ded, 1, 4167, 4583-1, 0, 0, 5, ded.description).save();
		createDeductionTable(ded, 2, 4583, 5417-1, 20.83, 0, 10, ded.description).save();
                createDeductionTable(ded, 3, 5417, 7083-1, 104.17, 0, 15, ded.description).save();
		createDeductionTable(ded, 4, 7083, 10000-1, 354.17, 0, 20, ded.description).save();
                createDeductionTable(ded, 5, 10000, 14583-1, 937.50, 0, 25, ded.description).save();
		createDeductionTable(ded, 6, 14583, 25000-1, 2083.33, 0, 30, ded.description).save();
                createDeductionTable(ded, 7, 25000, 25000, 5208.33, 0, 32, ded.description).save();
                
	}
         
         private void runTaxSme3() {
                if (DBClient.exist("SELECT a FROM DeductionType a WHERE a.code='TAX-SME3'")) return;
		DeductionType ded = createDeduction("TAX-SME3","","TAX-ME3/S3");
		ded.save();
		createDeductionTable(ded, 1, 5208, 5625-1, 0, 0, 5, ded.description).save();
		createDeductionTable(ded, 2, 5625, 6458-1, 20.83, 0, 10, ded.description).save();
                createDeductionTable(ded, 3, 6458, 8125-1, 104.17, 0, 15, ded.description).save();
		createDeductionTable(ded, 4, 8125, 11042-1, 354.17, 0, 20, ded.description).save();
                createDeductionTable(ded, 5, 11042, 15625-1, 937.50, 0, 25, ded.description).save();
		createDeductionTable(ded, 6, 15625, 26042-1, 2083.33, 0, 30, ded.description).save();
                createDeductionTable(ded, 7, 26042, 26042, 5208.33, 0, 32, ded.description).save();
                
	}
         
          private void runTaxSme4() {
                 if (DBClient.exist("SELECT a FROM DeductionType a WHERE a.code='TAX-SME4'")) return;
		DeductionType ded = createDeduction("TAX-SME4","","TAX-ME4/S4");
		ded.save();
		createDeductionTable(ded, 1, 6250, 6667-1, 0, 0, 5, ded.description).save();
		createDeductionTable(ded, 2, 6667, 7500-1, 20.83, 0, 10, ded.description).save();
                createDeductionTable(ded, 3, 7500, 9167-1, 104.17, 0, 15, ded.description).save();
		createDeductionTable(ded, 4, 9167, 12083-1, 354.17, 0, 20, ded.description).save();
                createDeductionTable(ded, 5, 12083, 16667-1, 937.50, 0, 25, ded.description).save();
		createDeductionTable(ded, 6, 16667, 27083, 2083.33, 0, 30, ded.description).save();
                createDeductionTable(ded, 7, 27083, 27083, 5208.33, 0, 32, ded.description).save();
                
	}
	
	public static DeductionType getDeduction(String deduction, String category) {
        DeductionType dec = (DeductionType) DBClient.getFirstRecord("SELECT a FROM DeductionType a WHERE a.code='"+deduction+"'");
        if (dec==null) {
            dec = new DeductionType();
            dec.code = deduction;
            dec.description = deduction;
            dec.category = category;
            dec.save();
        }
        return dec;
    }
}
