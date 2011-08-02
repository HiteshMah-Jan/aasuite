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
@Table(name = "InventoryRevaluation")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"number","itemNo","itemDescription","whse","currentCost","newCost","unitOfMeasure","inStock","gLDecreaseAcct","valMethod","distRule"})
@Displays({
	@Display(name="number"),
	@Display(name="revaluationType", type="Combo", modelCombo={"Price Change","Inventory Debit/Credit"}),
	@Display(name="series"),
	@Display(name="postingDate"),
	@Display(name="documentDate"),
	@Display(name="reference")
})
public class InventoryRevaluation extends AbstractIBean{
@Id
public Integer number;
public String revaluationType;
public String series;
@Temporal(javax.persistence.TemporalType.DATE)
public Date postingDate;
@Temporal(javax.persistence.TemporalType.DATE)
public Date documentDate;
public Integer getNumber() {
	return number;
}

public void setNumber(Integer number) {
	this.number = number;
}

public String getRevaluationType() {
	return revaluationType;
}

public void setRevaluationType(String revaluationType) {
	this.revaluationType = revaluationType;
}

public String getSeries() {
	return series;
}

public void setSeries(String series) {
	this.series = series;
}

public Date getPostingDate() {
	return postingDate;
}

public void setPostingDate(Date postingDate) {
	this.postingDate = postingDate;
}

public Date getDocumentDate() {
	return documentDate;
}

public void setDocumentDate(Date documentDate) {
	this.documentDate = documentDate;
}

public String getReference() {
	return reference;
}

public void setReference(String reference) {
	this.reference = reference;
}

public String reference;


	public static void main(String[] args) {
		view(InventoryRevaluation.class);
	}

@Override
public String popupSearch(String criteria) {
	// TODO Auto-generated method stub
	return null;
}

}
