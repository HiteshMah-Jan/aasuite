package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "InventoryTransfer")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="Customer"),
	@Display(name="Name"),
	@Display(name="ContactPerson"),
	@Display(name="ShipTo"),
	@Display(name="Number"),
	@Display(name="Series"),
	@Display(name="PostingDate"),
	@Display(name="DocumentDate"),
	@Display(name="FromWarehouse"),
	@Display(name="PriceList"),
	@Display(name="SalesEmployee"),
	@Display(name="Remarks", type="TextArea", gridFieldWidth=2, width=-1),
	@Display(name="JournalRemarks", type="TextArea", gridFieldWidth=2, width=-1)
})
public class InventoryTransfer extends AbstractIBean {
@Id
	public String Customer;
	public String Name;
	public String ContactPerson;
	public String ShipTo;
	public Double Number;
	public String Series;
	public Double PostingDate;
	public Double DocumentDate;
	public String FromWarehouse;
	public String PriceList;
	public String SalesEmployee;
	public String Remarks;
	public String JournalRemarks;
	
	
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		Customer = customer;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getContactPerson() {
		return ContactPerson;
	}
	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}
	public String getShipTo() {
		return ShipTo;
	}
	public void setShipTo(String shipTo) {
		ShipTo = shipTo;
	}
	public Double getNumber() {
		return Number;
	}
	public void setNumber(Double number) {
		Number = number;
	}
	public String getSeries() {
		return Series;
	}
	public void setSeries(String series) {
		Series = series;
	}
	public Double getPostingDate() {
		return PostingDate;
	}
	public void setPostingDate(Double postingDate) {
		PostingDate = postingDate;
	}
	public Double getDocumentDate() {
		return DocumentDate;
	}
	public void setDocumentDate(Double documentDate) {
		DocumentDate = documentDate;
	}
	public String getFromWarehouse() {
		return FromWarehouse;
	}
	public void setFromWarehouse(String fromWarehouse) {
		FromWarehouse = fromWarehouse;
	}
	public String getPriceList() {
		return PriceList;
	}
	public void setPriceList(String priceList) {
		PriceList = priceList;
	}
	public String getSalesEmployee() {
		return SalesEmployee;
	}
	public void setSalesEmployee(String salesEmployee) {
		SalesEmployee = salesEmployee;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setJournal(String journal) {
		Remarks = Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getJournalRemarks() {
		return JournalRemarks;
	}
	public void setJournalRemarks(String journalRemarks) {
		JournalRemarks = journalRemarks;
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
