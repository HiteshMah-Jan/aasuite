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
@Table(name = "SerialNumberDetails")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	
	@Display(name="itemNumber"),
	@Display(name="itemDescription"),
	@Display(name="warehouse"),
	@Display(name="status"),
	
	@Display(name="mfrSerialNo"),
	@Display(name="serialNumber"),
	@Display(name="lotNumber"),
	@Display(name="systemNo"),
	@Display(name="admissionDate"),
	@Display(name="manufacturingDate"),
	@Display(name="expirationDate"),
	@Display(name="mfrWarrantyStart"),
	@Display(name="mfrWarrantyEnd"),
	@Display(name="location")
})
public class SerialNumberDetails extends AbstractIBean{
@Id
	public double itemNumber;
	public String itemDescription;
	public double warehouse;
	public String status;
	
	public double mfrSerialNo;
	public double serialNumber;
	public double lotNumber;
	public double systemNo;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date admissionDate;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date manufacturingDate;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date expirationDate;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date mfrWarrantyStart;
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date mfrWarrantyEnd;
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

	public double getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(double warehouse) {
		this.warehouse = warehouse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getMfrSerialNo() {
		return mfrSerialNo;
	}

	public void setMfrSerialNo(double mfrSerialNo) {
		this.mfrSerialNo = mfrSerialNo;
	}

	public double getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(double serialNumber) {
		this.serialNumber = serialNumber;
	}

	public double getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(double lotNumber) {
		this.lotNumber = lotNumber;
	}

	public double getSystemNo() {
		return systemNo;
	}

	public void setSystemNo(double systemNo) {
		this.systemNo = systemNo;
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

	public Date getMfrWarrantyStart() {
		return mfrWarrantyStart;
	}

	public void setMfrWarrantyStart(Date mfrWarrantyStart) {
		this.mfrWarrantyStart = mfrWarrantyStart;
	}

	public Date getMfrWarrantyEnd() {
		return mfrWarrantyEnd;
	}

	public void setMfrWarrantyEnd(Date mfrWarrantyEnd) {
		this.mfrWarrantyEnd = mfrWarrantyEnd;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String location;
	
	
	
	public static void main(String[] args) {
		view(SerialNumberDetails.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
