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
	@Display(name="benefit1"),
	@Display(name="benefitAmount1"),
	@Display(name="benefitTaxable1"),
	@Display(name="benefit2"),
	@Display(name="benefitAmount2"),
	@Display(name="benefitTaxable2"),
	@Display(name="benefit3"),
	@Display(name="benefitAmount3"),
	@Display(name="benefitTaxable3"),
	@Display(name="benefit4"),
	@Display(name="benefitAmount4"),
	@Display(name="benefitTaxable4"),
	@Display(name="benefit5"),
	@Display(name="benefitAmount5"),
	@Display(name="benefitTaxable5"),
	@Display(name="benefit6"),
	@Display(name="benefitAmount6"),
	@Display(name="benefitTaxable6"),
	@Display(name="benefit7"),
	@Display(name="benefitAmount7"),
	@Display(name="benefitTaxable7"),
	@Display(name="benefit8"),
	@Display(name="benefitAmount8"),
	@Display(name="benefitTaxable8"),
	@Display(name="benefit9"),
	@Display(name="benefitAmount9"),
	@Display(name="benefitTaxable9"),
	@Display(name="benefit10"),
	@Display(name="benefitAmount10"),
	@Display(name="benefitTaxable10")
})
public class EmbeddedPayrollBenefit extends AbstractIBean {
    public String benefit1;
    public double benefitAmount1;
    public boolean benefitTaxable1;
    public String benefit2;
    public double benefitAmount2;
    public boolean benefitTaxable2;
    public String benefit3;
    public double benefitAmount3;
    public boolean benefitTaxable3;
    public String benefit4;
    public double benefitAmount4;
    public boolean benefitTaxable4;
    public String benefit5;
    public double benefitAmount5;
    public boolean benefitTaxable5;
    public String benefit6;
    public double benefitAmount6;
    public boolean benefitTaxable6;
    public String benefit7;
    public double benefitAmount7;
    public boolean benefitTaxable7;
    public String benefit8;
    public double benefitAmount8;
    public boolean benefitTaxable8;
    public String benefit9;
    public double benefitAmount9;
    public boolean benefitTaxable9;
    public String benefit10;
    public double benefitAmount10;
    public boolean benefitTaxable10;
	
	public String getBenefit1() {
		return benefit1;
	}
	public void setBenefit1(String benefit1) {
		this.benefit1 = benefit1;
	}
	public double getBenefitAmount1() {
		return benefitAmount1;
	}
	public void setBenefitAmount1(double benefitAmount1) {
		this.benefitAmount1 = benefitAmount1;
	}
	public boolean isBenefitTaxable1() {
		return benefitTaxable1;
	}
	public void setBenefitTaxable1(boolean benefitTaxable1) {
		this.benefitTaxable1 = benefitTaxable1;
	}
	public String getBenefit2() {
		return benefit2;
	}
	public void setBenefit2(String benefit2) {
		this.benefit2 = benefit2;
	}
	public double getBenefitAmount2() {
		return benefitAmount2;
	}
	public void setBenefitAmount2(double benefitAmount2) {
		this.benefitAmount2 = benefitAmount2;
	}
	public boolean isBenefitTaxable2() {
		return benefitTaxable2;
	}
	public void setBenefitTaxable2(boolean benefitTaxable2) {
		this.benefitTaxable2 = benefitTaxable2;
	}
	public String getBenefit3() {
		return benefit3;
	}
	public void setBenefit3(String benefit3) {
		this.benefit3 = benefit3;
	}
	public double getBenefitAmount3() {
		return benefitAmount3;
	}
	public void setBenefitAmount3(double benefitAmount3) {
		this.benefitAmount3 = benefitAmount3;
	}
	public boolean isBenefitTaxable3() {
		return benefitTaxable3;
	}
	public void setBenefitTaxable3(boolean benefitTaxable3) {
		this.benefitTaxable3 = benefitTaxable3;
	}
	public String getBenefit4() {
		return benefit4;
	}
	public void setBenefit4(String benefit4) {
		this.benefit4 = benefit4;
	}
	public double getBenefitAmount4() {
		return benefitAmount4;
	}
	public void setBenefitAmount4(double benefitAmount4) {
		this.benefitAmount4 = benefitAmount4;
	}
	public boolean isBenefitTaxable4() {
		return benefitTaxable4;
	}
	public void setBenefitTaxable4(boolean benefitTaxable4) {
		this.benefitTaxable4 = benefitTaxable4;
	}
	public String getBenefit5() {
		return benefit5;
	}
	public void setBenefit5(String benefit5) {
		this.benefit5 = benefit5;
	}
	public double getBenefitAmount5() {
		return benefitAmount5;
	}
	public void setBenefitAmount5(double benefitAmount5) {
		this.benefitAmount5 = benefitAmount5;
	}
	public boolean isBenefitTaxable5() {
		return benefitTaxable5;
	}
	public void setBenefitTaxable5(boolean benefitTaxable5) {
		this.benefitTaxable5 = benefitTaxable5;
	}
	public String getBenefit6() {
		return benefit6;
	}
	public void setBenefit6(String benefit6) {
		this.benefit6 = benefit6;
	}
	public double getBenefitAmount6() {
		return benefitAmount6;
	}
	public void setBenefitAmount6(double benefitAmount6) {
		this.benefitAmount6 = benefitAmount6;
	}
	public boolean isBenefitTaxable6() {
		return benefitTaxable6;
	}
	public void setBenefitTaxable6(boolean benefitTaxable6) {
		this.benefitTaxable6 = benefitTaxable6;
	}
	public String getBenefit7() {
		return benefit7;
	}
	public void setBenefit7(String benefit7) {
		this.benefit7 = benefit7;
	}
	public double getBenefitAmount7() {
		return benefitAmount7;
	}
	public void setBenefitAmount7(double benefitAmount7) {
		this.benefitAmount7 = benefitAmount7;
	}
	public boolean isBenefitTaxable7() {
		return benefitTaxable7;
	}
	public void setBenefitTaxable7(boolean benefitTaxable7) {
		this.benefitTaxable7 = benefitTaxable7;
	}
	public String getBenefit8() {
		return benefit8;
	}
	public void setBenefit8(String benefit8) {
		this.benefit8 = benefit8;
	}
	public double getBenefitAmount8() {
		return benefitAmount8;
	}
	public void setBenefitAmount8(double benefitAmount8) {
		this.benefitAmount8 = benefitAmount8;
	}
	public boolean isBenefitTaxable8() {
		return benefitTaxable8;
	}
	public void setBenefitTaxable8(boolean benefitTaxable8) {
		this.benefitTaxable8 = benefitTaxable8;
	}
	public String getBenefit9() {
		return benefit9;
	}
	public void setBenefit9(String benefit9) {
		this.benefit9 = benefit9;
	}
	public double getBenefitAmount9() {
		return benefitAmount9;
	}
	public void setBenefitAmount9(double benefitAmount9) {
		this.benefitAmount9 = benefitAmount9;
	}
	public boolean isBenefitTaxable9() {
		return benefitTaxable9;
	}
	public void setBenefitTaxable9(boolean benefitTaxable9) {
		this.benefitTaxable9 = benefitTaxable9;
	}
	public String getBenefit10() {
		return benefit10;
	}
	public void setBenefit10(String benefit10) {
		this.benefit10 = benefit10;
	}
	public double getBenefitAmount10() {
		return benefitAmount10;
	}
	public void setBenefitAmount10(double benefitAmount10) {
		this.benefitAmount10 = benefitAmount10;
	}
	public boolean isBenefitTaxable10() {
		return benefitTaxable10;
	}
	public void setBenefitTaxable10(boolean benefitTaxable10) {
		this.benefitTaxable10 = benefitTaxable10;
	}
	public static void main(String[] args) {
		viewEmbedded(EmbeddedPayrollBenefit.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
