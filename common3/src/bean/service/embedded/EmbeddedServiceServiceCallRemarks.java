package bean.service.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;

@Embeddable
@UITemplate(template= TemplateEmbedded.class, gridCount = 4, columnSearch = {"remarks"})
@Displays({
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="service.", 
			mergeFields={"remarks"}, verticalMerge=true),
			@Display(name="remarks", fieldPrefix="service.", noLabel=true, type="TextArea",gridFieldWidth=10)			
})

public class EmbeddedServiceServiceCallRemarks extends AbstractIBean{
	
	public static void main(String[] args) {
		viewEmbedded(EmbeddedServiceServiceCallRemarks.class);
	}
	public String remarks;
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
