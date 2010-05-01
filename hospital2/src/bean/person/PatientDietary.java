/*
 * Dietary.java
 * 
 * Created on Oct 26, 2007, 9:34:50 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.person;

import bean.Patient;
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
@Table(name = "PatientDietary")
@UITemplate(template = TemplateSinglePage.class, columnSearch = {"seq", "patientId", "diet"}, gridCount = 4)
@Displays({
        @Display(name="seq"),
        @Display(name="patientId", label = "Patient", type = "PopSearch", linktoBean=Patient.class, linktoColumns={"lastName","firstName"}),
        @Display(name="mealTime"),
        @Display(name="diet"),
        @Display(name="clinicalInstruction"),
        @Display(name="dietricianInstruction"),
        @Display(name="roomNumber", type = "Combo", sqlCombo = "SELECT a FROM Room a", label = "Room")
})
public class PatientDietary extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "patientId", nullable = false)
    public int patientId;
    @Column(name = "mealTime", length = 4)
    public String mealTime;
    @Column(name = "diet", length = 150)
    public String diet;
    @Column(name = "clinicalInstruction", length = 150)
    public String clinicalInstruction;
    @Column(name = "dietricianInstruction", length = 150)
    public String dietricianInstruction;
    @Column(name = "roomNumber", length = 30)
    public String roomNumber;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getClinicalInstruction() {
        return clinicalInstruction;
    }

    public void setClinicalInstruction(String clinicalInstruction) {
        this.clinicalInstruction = clinicalInstruction;
    }

    public String getDietricianInstruction() {
        return dietricianInstruction;
    }

    public void setDietricianInstruction(String dietricianInstruction) {
        this.dietricianInstruction = dietricianInstruction;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seq != null ? seq.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Dietary[" + seq + "]";
    }

    public String _Key() {
        return "seq";
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, this._Key());
	}
}
