package bean.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;
@Entity
@Table(name = "SolutionsKnowledgeBase")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="Item"),
	@Display(name="UpdatedBy"),
	@Display(name="UpdatedOn"),
	@Display(name="Solution"),
	@Display(name="Symptom"),
	@Display(name="Status"),
	@Display(name="No"),
	@Display(name="Owner")
})
public class SolutionsKnowledgeBase extends AbstractIBean {
	@Id
	public String Item;
	public String UpdatedBy;
	public String UpdatedOn;
	public String Solution;
	public String Symptom;
	public String Status;
	public Double No;
	public String Owner;
	
	
	
public String getItem() {
		return Item;
	}
	public void setItem(String item) {
		Item = item;
	}
	public String getUpdatedBy() {
		return UpdatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}
	public String getUpdatedOn() {
		return UpdatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		UpdatedOn = updatedOn;
	}
	public String getSolution() {
		return Solution;
	}
	public void setSolution(String solution) {
		Solution = solution;
	}
	public String getSymptom() {
		return Symptom;
	}
	public void setSymptom(String symptom) {
		Symptom = symptom;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Double getNo() {
		return No;
	}
	public void setNo(Double no) {
		No = no;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
public static void main(String[] args) {
	view(SolutionsKnowledgeBase.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
