/*
 * Admission.java
 *
 * Created on Oct 29, 2007, 5:18:05 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Ebhoy
 */
@Entity
@Table(name = "PatientPackageHistory")
@UITemplate(template = TemplateSinglePage.class, gridCount=4, columnSearch = {"historyDate", "remarks"})
@Displays({
        @Display(name="seq"),
        @Display(name="patientPackageId"),
        @Display(name="historyDate"),
        @Display(name="remarks")
})
public class PatientPackageHistory extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "patientPackageId")
    public int patientPackageId;
    @Column(name = "historyDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date historyDate;
    @Column(name = "remarks")
    public String remarks;

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
    }

    public int getPatientPackageId() {
        return patientPackageId;
    }

    public void setPatientPackageId(int patientPackageId) {
        this.patientPackageId = patientPackageId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
