package bean.mrp;

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
@Table(name = "Forecast")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"number","itemNo","itemDescription"})
@Displays({
	@Display(name="forecastCode"),
	@Display(name="forecastName"),
	@Display(name="startDate"),
	@Display(name="endDate"),
	
	@Display(name="View", type="Combo",modelCombo={"Daily","Weekly","Monthly"})
})
public class Forecast extends AbstractIBean {
@Id	
public String forecastCode;
public String forecastName;
@Temporal(javax.persistence.TemporalType.DATE)
public Date startDate;
@Temporal(javax.persistence.TemporalType.DATE)
public Date endDate;

public String View;


public String getForecastCode() {
	return forecastCode;
}
public void setForecastCode(String forecastCode) {
	forecastCode = forecastCode;
}
public String getForecastName() {
	return forecastName;
}
public void setForecastName(String forecastName) {
	forecastName = forecastName;
}
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	startDate = startDate;
}
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Date endDate) {
	endDate = endDate;
}
public String getView() {
	return View;
}
public void setView(String view) {
	View = view;
}
public static void main(String[] args) {
	view(Forecast.class);
}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
