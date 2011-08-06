package bean.payroll.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;

@Embeddable
@UITemplate(template=TemplateEmbedded.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="adjustment1"),
	@Display(name="adjustmentAmount1"),
	@Display(name="adjustment2"),
	@Display(name="adjustmentAmount2"),
	@Display(name="adjustment3"),
	@Display(name="adjustmentAmount3"),
	@Display(name="adjustment4"),
	@Display(name="adjustmentAmount4"),
	@Display(name="adjustment5"),
	@Display(name="adjustmentAmount5"),
	@Display(name="adjustment6"),
	@Display(name="adjustmentAmount6"),
	@Display(name="adjustment7"),
	@Display(name="adjustmentAmount7"),
	@Display(name="adjustment8"),
	@Display(name="adjustmentAmount8"),
	@Display(name="adjustment9"),
	@Display(name="adjustmentAmount9"),
	@Display(name="adjustment10"),
	@Display(name="adjustmentAmount10")
})
public class EmbeddedPayrollAdjustment extends AbstractIBean {
    public String adjustment1;
    public double adjustmentAmount1;
    public String adjustment2;
    public double adjustmentAmount2;
    public String adjustment3;
    public double adjustmentAmount3;
    public String adjustment4;
    public double adjustmentAmount4;
    public String adjustment5;
    public double adjustmentAmount5;
    public String adjustment6;
    public double adjustmentAmount6;
    public String adjustment7;
    public double adjustmentAmount7;
    public String adjustment8;
    public double adjustmentAmount8;
    public String adjustment9;
    public double adjustmentAmount9;
    public String adjustment10;
    public double adjustmentAmount10;

	public String getAdjustment1() {
		return adjustment1;
	}
	public void setAdjustment1(String adjustment1) {
		this.adjustment1 = adjustment1;
	}
	public double getAdjustmentAmount1() {
		return adjustmentAmount1;
	}
	public void setAdjustmentAmount1(double adjustmentAmount1) {
		this.adjustmentAmount1 = adjustmentAmount1;
	}
	public String getAdjustment2() {
		return adjustment2;
	}
	public void setAdjustment2(String adjustment2) {
		this.adjustment2 = adjustment2;
	}
	public double getAdjustmentAmount2() {
		return adjustmentAmount2;
	}
	public void setAdjustmentAmount2(double adjustmentAmount2) {
		this.adjustmentAmount2 = adjustmentAmount2;
	}
	public String getAdjustment3() {
		return adjustment3;
	}
	public void setAdjustment3(String adjustment3) {
		this.adjustment3 = adjustment3;
	}
	public double getAdjustmentAmount3() {
		return adjustmentAmount3;
	}
	public void setAdjustmentAmount3(double adjustmentAmount3) {
		this.adjustmentAmount3 = adjustmentAmount3;
	}
	public String getAdjustment4() {
		return adjustment4;
	}
	public void setAdjustment4(String adjustment4) {
		this.adjustment4 = adjustment4;
	}
	public double getAdjustmentAmount4() {
		return adjustmentAmount4;
	}
	public void setAdjustmentAmount4(double adjustmentAmount4) {
		this.adjustmentAmount4 = adjustmentAmount4;
	}
	public String getAdjustment5() {
		return adjustment5;
	}
	public void setAdjustment5(String adjustment5) {
		this.adjustment5 = adjustment5;
	}
	public double getAdjustmentAmount5() {
		return adjustmentAmount5;
	}
	public void setAdjustmentAmount5(double adjustmentAmount5) {
		this.adjustmentAmount5 = adjustmentAmount5;
	}
	public String getAdjustment6() {
		return adjustment6;
	}
	public void setAdjustment6(String adjustment6) {
		this.adjustment6 = adjustment6;
	}
	public double getAdjustmentAmount6() {
		return adjustmentAmount6;
	}
	public void setAdjustmentAmount6(double adjustmentAmount6) {
		this.adjustmentAmount6 = adjustmentAmount6;
	}
	public String getAdjustment7() {
		return adjustment7;
	}
	public void setAdjustment7(String adjustment7) {
		this.adjustment7 = adjustment7;
	}
	public double getAdjustmentAmount7() {
		return adjustmentAmount7;
	}
	public void setAdjustmentAmount7(double adjustmentAmount7) {
		this.adjustmentAmount7 = adjustmentAmount7;
	}
	public String getAdjustment8() {
		return adjustment8;
	}
	public void setAdjustment8(String adjustment8) {
		this.adjustment8 = adjustment8;
	}
	public double getAdjustmentAmount8() {
		return adjustmentAmount8;
	}
	public void setAdjustmentAmount8(double adjustmentAmount8) {
		this.adjustmentAmount8 = adjustmentAmount8;
	}
	public String getAdjustment9() {
		return adjustment9;
	}
	public void setAdjustment9(String adjustment9) {
		this.adjustment9 = adjustment9;
	}
	public double getAdjustmentAmount9() {
		return adjustmentAmount9;
	}
	public void setAdjustmentAmount9(double adjustmentAmount9) {
		this.adjustmentAmount9 = adjustmentAmount9;
	}
	public String getAdjustment10() {
		return adjustment10;
	}
	public void setAdjustment10(String adjustment10) {
		this.adjustment10 = adjustment10;
	}
	public double getAdjustmentAmount10() {
		return adjustmentAmount10;
	}
	public void setAdjustmentAmount10(double adjustmentAmount10) {
		this.adjustmentAmount10 = adjustmentAmount10;
	}
	public static void main(String[] args) {
		viewEmbedded(EmbeddedPayrollAdjustment.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
