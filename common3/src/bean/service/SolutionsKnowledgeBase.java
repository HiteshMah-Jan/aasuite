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
	@Display(name="item"),
	@Display(name="updatedBy"),
	@Display(name="updatedOn"),
	@Display(name="solution"),
	@Display(name="symptom"),
	@Display(name="status"),
	@Display(name="no"),
	@Display(name="owner")
})
public class SolutionsKnowledgeBase extends AbstractIBean {
	@Id
	public String item;
	public String updatedBy;
	public String updatedOn;
	public String solution;
	public String symptom;
	public String status;
	public Double no;
	public String owner;
	
	
	
public String getItem() {
		return item;
	}
	public void setItem(String item) {
		item = item;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		updatedBy = updatedBy;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		updatedOn = updatedOn;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		solution = solution;
	}
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		symptom = symptom;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		status = status;
	}
	public Double getNo() {
		return no;
	}
	public void setNo(Double no) {
		no = no;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		owner = owner;
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
