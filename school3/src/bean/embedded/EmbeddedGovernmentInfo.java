package bean.embedded;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Embeddable
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 2, columnSearch = {"seq"})
@Displays({
	@Display(name="citizenship"),
	@Display(name="tinNumber"),
	@Display(name="sssNumber"),
	@Display(name="pagibigNumber"),
	@Display(name="philhealthNumber"),
	@Display(name="ifAlienAcrNo"),
	@Display(name="acrPlaceIssued", label="ACR Place Issued"),
	@Display(name="acrDateIssued", label="ACR Date Issued"),
	@Display(name="aCROrICRNumber", label="ACR/ICR Number")
})
public class EmbeddedGovernmentInfo extends AbstractIBean {
    public String citizenship;
    public String tinNumber;
    public String sssNumber;
    public String pagibigNumber;
    public String philhealthNumber;
	public String ifAlienAcrNo;
	public String acrPlaceIssued;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date acrDateIssued;
    public String aCROrICRNumber;
    
	public String getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	public String getTinNumber() {
		return tinNumber;
	}
	public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}
	public String getSssNumber() {
		return sssNumber;
	}
	public void setSssNumber(String sssNumber) {
		this.sssNumber = sssNumber;
	}
	public String getPagibigNumber() {
		return pagibigNumber;
	}
	public void setPagibigNumber(String pagibigNumber) {
		this.pagibigNumber = pagibigNumber;
	}
	public String getPhilhealthNumber() {
		return philhealthNumber;
	}
	public void setPhilhealthNumber(String philhealthNumber) {
		this.philhealthNumber = philhealthNumber;
	}
	public String getIfAlienAcrNo() {
		return ifAlienAcrNo;
	}
	public void setIfAlienAcrNo(String ifAlienAcrNo) {
		this.ifAlienAcrNo = ifAlienAcrNo;
	}
	public String getAcrPlaceIssued() {
		return acrPlaceIssued;
	}
	public void setAcrPlaceIssued(String acrPlaceIssued) {
		this.acrPlaceIssued = acrPlaceIssued;
	}
	public Date getAcrDateIssued() {
		return acrDateIssued;
	}
	public void setAcrDateIssued(Date acrDateIssued) {
		this.acrDateIssued = acrDateIssued;
	}
	public String getaCROrICRNumber() {
		return aCROrICRNumber;
	}
	public void setaCROrICRNumber(String aCROrICRNumber) {
		this.aCROrICRNumber = aCROrICRNumber;
	}
	public static void main(String[] args) {
		view(EmbeddedGovernmentInfo.class);
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
