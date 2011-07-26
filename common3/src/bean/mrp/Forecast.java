package bean.mrp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePageLeftRight;

@Entity
@Table(name = "Forecast")
@UITemplate(template=TemplateTabSinglePageLeftRight.class, gridCount = 4, columnSearch = {"seq"})
@Displays({
	@Display(name="ForecastCode"),
	@Display(name="ForecastName"),
	@Display(name="StartDate"),
	@Display(name="EndDate"),
	@Display(name="View")
})
public class Forecast extends AbstractIBean {
@Id	
public String ForecastCode;
public String ForecastName;
public Double StartDate;
public Double EndDate;
public String View;


public String getForecastCode() {
	return ForecastCode;
}
public void setForecastCode(String forecastCode) {
	ForecastCode = forecastCode;
}
public String getForecastName() {
	return ForecastName;
}
public void setForecastName(String forecastName) {
	ForecastName = forecastName;
}
public Double getStartDate() {
	return StartDate;
}
public void setStartDate(Double startDate) {
	StartDate = startDate;
}
public Double getEndDate() {
	return EndDate;
}
public void setEndDate(Double endDate) {
	EndDate = endDate;
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
