package bean.payroll.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;

@Embeddable
@UITemplate(template=TemplateEmbedded.class, gridCount = 6, columnSearch = {"seq"})
@Displays({
	@Display(name="tax1"),
	@Display(name="taxPercentage1"),
	@Display(name="useTaxTable1"),
	@Display(name="tax2"),
	@Display(name="taxPercentage2"),
	@Display(name="useTaxTable2"),
	@Display(name="tax3"),
	@Display(name="taxPercentage3"),
	@Display(name="useTaxTable3"),
	@Display(name="tax4"),
	@Display(name="taxPercentage4"),
	@Display(name="useTaxTable4"),
	@Display(name="tax5"),
	@Display(name="taxPercentage5"),
	@Display(name="useTaxTable5"),
	@Display(name="tax6"),
	@Display(name="taxPercentage6"),
	@Display(name="useTaxTable6"),
	@Display(name="tax7"),
	@Display(name="taxPercentage7"),
	@Display(name="useTaxTable7"),
	@Display(name="tax8"),
	@Display(name="taxPercentage8"),
	@Display(name="useTaxTable8"),
	@Display(name="tax9"),
	@Display(name="taxPercentage9"),
	@Display(name="useTaxTable9"),
	@Display(name="tax10"),
	@Display(name="taxPercentage10"),
	@Display(name="useTaxTable10")
})
public class EmbeddedPayrollTax extends AbstractIBean {
    public String tax1;
    public double taxPercentage1;
    public boolean useTaxTable1;
    public String tax2;
    public double taxPercentage2;
    public boolean useTaxTable2;
    public String tax3;
    public double taxPercentage3;
    public boolean useTaxTable3;
    public String tax4;
    public double taxPercentage4;
    public boolean useTaxTable4;
    public String tax5;
    public double taxPercentage5;
    public boolean useTaxTable5;
    public String tax6;
    public double taxPercentage6;
    public boolean useTaxTable6;
    public String tax7;
    public double taxPercentage7;
    public boolean useTaxTable7;
    public String tax8;
    public double taxPercentage8;
    public boolean useTaxTable8;
    public String tax9;
    public double taxPercentage9;
    public boolean useTaxTable9;
    public String tax10;
    public double taxPercentage10;
    public boolean useTaxTable10;

	public String getTax1() {
		return tax1;
	}
	public void setTax1(String tax1) {
		this.tax1 = tax1;
	}
	public double getTaxPercentage1() {
		return taxPercentage1;
	}
	public void setTaxPercentage1(double taxPercentage1) {
		this.taxPercentage1 = taxPercentage1;
	}
	public boolean isUseTaxTable1() {
		return useTaxTable1;
	}
	public void setUseTaxTable1(boolean useTaxTable1) {
		this.useTaxTable1 = useTaxTable1;
	}
	public String getTax2() {
		return tax2;
	}
	public void setTax2(String tax2) {
		this.tax2 = tax2;
	}
	public double getTaxPercentage2() {
		return taxPercentage2;
	}
	public void setTaxPercentage2(double taxPercentage2) {
		this.taxPercentage2 = taxPercentage2;
	}
	public boolean isUseTaxTable2() {
		return useTaxTable2;
	}
	public void setUseTaxTable2(boolean useTaxTable2) {
		this.useTaxTable2 = useTaxTable2;
	}
	public String getTax3() {
		return tax3;
	}
	public void setTax3(String tax3) {
		this.tax3 = tax3;
	}
	public double getTaxPercentage3() {
		return taxPercentage3;
	}
	public void setTaxPercentage3(double taxPercentage3) {
		this.taxPercentage3 = taxPercentage3;
	}
	public boolean isUseTaxTable3() {
		return useTaxTable3;
	}
	public void setUseTaxTable3(boolean useTaxTable3) {
		this.useTaxTable3 = useTaxTable3;
	}
	public String getTax4() {
		return tax4;
	}
	public void setTax4(String tax4) {
		this.tax4 = tax4;
	}
	public double getTaxPercentage4() {
		return taxPercentage4;
	}
	public void setTaxPercentage4(double taxPercentage4) {
		this.taxPercentage4 = taxPercentage4;
	}
	public boolean isUseTaxTable4() {
		return useTaxTable4;
	}
	public void setUseTaxTable4(boolean useTaxTable4) {
		this.useTaxTable4 = useTaxTable4;
	}
	public String getTax5() {
		return tax5;
	}
	public void setTax5(String tax5) {
		this.tax5 = tax5;
	}
	public double getTaxPercentage5() {
		return taxPercentage5;
	}
	public void setTaxPercentage5(double taxPercentage5) {
		this.taxPercentage5 = taxPercentage5;
	}
	public boolean isUseTaxTable5() {
		return useTaxTable5;
	}
	public void setUseTaxTable5(boolean useTaxTable5) {
		this.useTaxTable5 = useTaxTable5;
	}
	public String getTax6() {
		return tax6;
	}
	public void setTax6(String tax6) {
		this.tax6 = tax6;
	}
	public double getTaxPercentage6() {
		return taxPercentage6;
	}
	public void setTaxPercentage6(double taxPercentage6) {
		this.taxPercentage6 = taxPercentage6;
	}
	public boolean isUseTaxTable6() {
		return useTaxTable6;
	}
	public void setUseTaxTable6(boolean useTaxTable6) {
		this.useTaxTable6 = useTaxTable6;
	}
	public String getTax7() {
		return tax7;
	}
	public void setTax7(String tax7) {
		this.tax7 = tax7;
	}
	public double getTaxPercentage7() {
		return taxPercentage7;
	}
	public void setTaxPercentage7(double taxPercentage7) {
		this.taxPercentage7 = taxPercentage7;
	}
	public boolean isUseTaxTable7() {
		return useTaxTable7;
	}
	public void setUseTaxTable7(boolean useTaxTable7) {
		this.useTaxTable7 = useTaxTable7;
	}
	public String getTax8() {
		return tax8;
	}
	public void setTax8(String tax8) {
		this.tax8 = tax8;
	}
	public double getTaxPercentage8() {
		return taxPercentage8;
	}
	public void setTaxPercentage8(double taxPercentage8) {
		this.taxPercentage8 = taxPercentage8;
	}
	public boolean isUseTaxTable8() {
		return useTaxTable8;
	}
	public void setUseTaxTable8(boolean useTaxTable8) {
		this.useTaxTable8 = useTaxTable8;
	}
	public String getTax9() {
		return tax9;
	}
	public void setTax9(String tax9) {
		this.tax9 = tax9;
	}
	public double getTaxPercentage9() {
		return taxPercentage9;
	}
	public void setTaxPercentage9(double taxPercentage9) {
		this.taxPercentage9 = taxPercentage9;
	}
	public boolean isUseTaxTable9() {
		return useTaxTable9;
	}
	public void setUseTaxTable9(boolean useTaxTable9) {
		this.useTaxTable9 = useTaxTable9;
	}
	public String getTax10() {
		return tax10;
	}
	public void setTax10(String tax10) {
		this.tax10 = tax10;
	}
	public double getTaxPercentage10() {
		return taxPercentage10;
	}
	public void setTaxPercentage10(double taxPercentage10) {
		this.taxPercentage10 = taxPercentage10;
	}
	public boolean isUseTaxTable10() {
		return useTaxTable10;
	}
	public void setUseTaxTable10(boolean useTaxTable10) {
		this.useTaxTable10 = useTaxTable10;
	}
	public static void main(String[] args) {
		viewEmbedded(EmbeddedPayrollTax.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
