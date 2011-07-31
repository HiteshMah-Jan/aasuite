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
@Table(name = "ServiceServiceCallSolutionItem")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"number","id","solution","createdOn","owner","status","handledBy"})
@Displays({
//	@Display(name="solNumber", label="#"),
//	@Display(name="solId", label="ID"),
//	@Display(name="solSolution", label="Solution"),
//	@Display(name="createdOn", label="Created On"),
//	@Display(name="owner", label="Owner"),
//	@Display(name="status", label="Status"),
//	@Display(name="handledBy", label="Handled By")
})
public class ServiceServiceCallSolutionItem extends AbstractIBean{
@Id
public double number;
public String id;
public String solution;
public String createdOn;
public String owner;
public String status;
public String handledBy;

	public double getnumber() {
	return number;
}

public void setnumber(double number) {
	this.number = number;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getsolution() {
	return solution;
}

public void setSolution(String solution) {
	this.solution = solution;
}

public String getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(String createdOn) {
	this.createdOn = createdOn;
}

public String getOwner() {
	return owner;
}

public void setOwner(String owner) {
	this.owner = owner;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getHandledBy() {
	return handledBy;
}

public void setHandledBy(String handledBy) {
	this.handledBy = handledBy;
}

	public static void main(String[] args) {
		view(ServiceServiceCallSolutionItem.class);
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
