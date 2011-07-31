package bean.service.embedded;

import javax.persistence.Embeddable;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateEmbedded;

@Embeddable
@UITemplate(template = TemplateEmbedded.class, gridCount = 4, columnSearch = { "origin","handledBy" })
@Displays({
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="service.", mergeFields={"origin","problemType","callType","technician"}, verticalMerge=true),
	@Display(name="origin", fieldPrefix="service.", label="Origin"),
	@Display(name="problemType", fieldPrefix="service.", label="Problem Type"),
	@Display(name="callType", fieldPrefix="service.", label="Call Type"),
	@Display(name="technician", fieldPrefix="service.", label="Technician"),
	
	@Display(name="dummyField", type="MergePanel", noLabel=true, fieldPrefix="service.", mergeFields={"handledBy","queue"}, verticalMerge=true),
	@Display(name="handledBy", fieldPrefix="service.", label="handled By"),
	@Display(name="queue", fieldPrefix="service.", label="Queue"),
	
	@Display(name="dummyField", type="MergePanel", label="Response", fieldPrefix="service.", mergeFields={"responseBy","responseOn"}, verticalMerge=true),
	@Display(name="responseBy", fieldPrefix="service.", label="Response By"),
	@Display(name="responseOn", fieldPrefix="service.", label="Response On"),
	
	@Display(name="dummyField", type="MergePanel", label="Resolution", fieldPrefix="service.", mergeFields={"resolutionBy","resolutionOn"}, verticalMerge=true),
	@Display(name="resolutionBy", fieldPrefix="service.", label="Resolution By"),
	@Display(name="resolutionOn", fieldPrefix="service.", label="Resolution On"),
})
public class EmbeddedServiceServiceCallGeneral extends AbstractIBean{
	public static void main(String[] args) {
		viewEmbedded(EmbeddedServiceServiceCallGeneral.class);
	}
	public String origin;
	public String problemType;
	public String callType;
	public String technician;
	
	public String handledBy;
	public String queue;
	
	public String responseBy;
	public String responseOn;
	
	public String resolutionBy;
	public String resolutionOn;
	
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public String getHandledBy() {
		return handledBy;
	}

	public void setHandledBy(String handledBy) {
		this.handledBy = handledBy;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getResponseBy() {
		return responseBy;
	}

	public void setResponseBy(String responseBy) {
		this.responseBy = responseBy;
	}

	public String getResponseOn() {
		return responseOn;
	}

	public void setResponseOn(String responseOn) {
		this.responseOn = responseOn;
	}

	public String getResolutionBy() {
		return resolutionBy;
	}

	public void setResolutionBy(String resolutionBy) {
		this.resolutionBy = resolutionBy;
	}

	public String getResolutionOn() {
		return resolutionOn;
	}

	public void setResolutionOn(String resolutionOn) {
		this.resolutionOn = resolutionOn;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
