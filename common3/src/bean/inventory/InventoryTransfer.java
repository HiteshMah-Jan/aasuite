package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

import com.sun.star.bridge.oleautomation.Date;

@Entity
@Table(name = "InventoryTransfer")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"number","itemNo","toWarehouse","quantity","inStock","committed","ordered","itemCost","unitOfMeasure","distrRule"})
@Displays({
	@Display(name="customer"),
	@Display(name="name"),
	@Display(name="contactPerson"),
	@Display(name="shipTo"),
	@Display(name="number"),
	@Display(name="series", type="Combo", modelCombo={"Primary"}),
	@Display(name="postingDate"),
	@Display(name="documentDate"),
	@Display(name="frmWarehouse"),
	@Display(name="priceList", type="Combo", modelCombo={}),
	@Display(name="salesEmployee"),
	@Display(name="remarks", type="TextArea", gridFieldWidth=2, width=-1),
	@Display(name="journalRemarks", type="TextArea", gridFieldWidth=2, width=-1)
})
public class InventoryTransfer extends AbstractIBean {
@Id
	public String customer;
	public String name;
	public String contactPerson;
	public String shipTo;

	public Double number;
	public String series;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date postingDate;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date documentDate;

	public String frmWarehouse;
	public String priceList;
	public String salesEmployee;
	public String remarks;
	public String journalRemarks;
		
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		customer = customer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = name;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		contactPerson = contactPerson;
	}
	public String getShipTo() {
		return shipTo;
	}
	public void setShipTo(String shipTo) {
		shipTo = shipTo;
	}
	public Double getNumber() {
		return number;
	}
	public void setNumber(Double number) {
		number = number;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		series = series;
	}
	public Date getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Date postingDate) {
		postingDate = postingDate;
	}
	public Date getDocumentDate() {
		return documentDate;
	}
	public void setDocumentDate(Date documentDate) {
		documentDate = documentDate;
	}
	public String getFrmWarehouse() {
		return frmWarehouse;
	}
	public void setFrmWarehouse(String frmWarehouse) {
		frmWarehouse = frmWarehouse;
	}
	public String getPriceList() {
		return priceList;
	}
	public void setPriceList(String priceList) {
		priceList = priceList;
	}
	public String getSalesEmployee() {
		return salesEmployee;
	}
	public void setSalesEmployee(String salesEmployee) {
		salesEmployee = salesEmployee;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		remarks = remarks;
	}
	public String getJournalRemarks() {
		return journalRemarks;
	}
	public void setJournalRemarks(String journalRemarks) {
		journalRemarks = journalRemarks;
	}
	public static void main(String[] args) {
	view(InventoryTransfer.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
