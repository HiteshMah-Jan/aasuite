package bean.inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

import com.sun.star.bridge.oleautomation.Date;
@Entity
@Table(name = "BatchDetails")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	
	@Display(name="itemNumber"),
	@Display(name="itemDescription"),
	@Display(name="status"),
	
	@Display(name="batch"),
	@Display(name="batchAttribute1"),
	@Display(name="batchAttribute2"),
	@Display(name="admissionDate"),
	@Display(name="manufacturingDate"),
	@Display(name="expirationDate"),
	@Display(name="details")
})
public class BatchDetail extends AbstractIBean{
@Id
	public double itemNumber;
	public String itemDescription;
	public String status;
	
	public String batch;
	public String batchAttribute1;
	public String batchAttribute2;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date admissionDate;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date manufacturingDate;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date expirationDate;
	public String details;
	
	

	public double getItemNumber() {
		return itemNumber;
	}



	public void setItemNumber(double itemNumber) {
		this.itemNumber = itemNumber;
	}



	public String getItemDescription() {
		return itemDescription;
	}



	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getBatch() {
		return batch;
	}



	public void setBatch(String batch) {
		this.batch = batch;
	}



	public String getBatchAttribute1() {
		return batchAttribute1;
	}



	public void setBatchAttribute1(String batchAttribute1) {
		this.batchAttribute1 = batchAttribute1;
	}



	public String getBatchAttribute2() {
		return batchAttribute2;
	}



	public void setBatchAttribute2(String batchAttribute2) {
		this.batchAttribute2 = batchAttribute2;
	}



	public Date getAdmissionDate() {
		return admissionDate;
	}



	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}



	public Date getManufacturingDate() {
		return manufacturingDate;
	}



	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}



	public Date getExpirationDate() {
		return expirationDate;
	}



	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}



	public String getDetails() {
		return details;
	}



	public void setDetails(String details) {
		this.details = details;
	}



	public static void main(String[] args) {
		view(BatchDetail.class);
	}



	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
