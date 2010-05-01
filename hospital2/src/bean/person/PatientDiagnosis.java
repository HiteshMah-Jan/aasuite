/*
 * Diagnosis.java
 * 
 * Created on Oct 26, 2007, 9:34:49 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Diagnosis")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"diagnosis", "patientId"})
@Displays({
        @Display(name="diagnosis"),
        @Display(name="description"),
        @Display(name="patientId", gridFieldWidth = 3, width = - 1, type = "Combo", sqlCombo = "SELET a FROM Patient a", label = "Patient")
})
public class PatientDiagnosis extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "diagnosis", nullable = false, length = 30)
    public String diagnosis;
    @Column(name = "description", length = 150)
    public String description;
    @Column(name = "patientId", nullable = false)
    public int patientId;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
