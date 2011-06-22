/*
 * Assessment.java
 * 
 * Created on Feb 13, 2008, 1:57:29 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.hr;

import bean.person.PersonAttribute;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "HRAssessment")
@UITemplate(template = TemplateDefault.class, gridCount = 6, columnSearch = {"assessmentDate","totalScore", "average"})
@Displays({
   // @Display(name="employeeId", label = "Employee", type = "PopSearch", linktoBean=Employee.class),
    @Display(name="assessmentDate",gridFieldWidth=5),
    @Display(name="jobKnowledge", width=70,type = "Combo", modelCombo = {"1","2","3","4","5","6","7","8","9"}),
    @Display(name="availability", width=70,type = "Combo", modelCombo = {"1","2","3","4","5","6","7","8","9"}),
    @Display(name="dependability", width=70,type = "Combo", modelCombo = {"1","2","3","4","5","6","7","8","9"}),
    @Display(name="initiative", width=70,type = "Combo", modelCombo = {"1","2","3","4","5","6","7","8","9"}),
    @Display(name="judgement", width=70,type = "Combo", modelCombo = {"1","2","3","4","5","6","7","8","9"}),
    @Display(name="productiveness", width=70,type = "Combo", modelCombo = {"1","2","3","4","5","6","7","8","9"}),
    @Display(name="quality", width=70,type = "Combo", modelCombo = {"1","2","3","4","5","6","7","8","9"}),
    @Display(name="reliability", width=70,type = "Combo", modelCombo = {"1","2","3","4","5","6","7","8","9"}),
    @Display(name="workingRelationships", width=70,type = "Combo", modelCombo = {"1","2","3","4","5","6","7","8","9"}),
    @Display(name="totalScore", width=70),
    @Display(name="average", width=70,gridFieldWidth=3),
    @Display(name="comment", type="TextArea",gridFieldWidth=5,width=-1)
})
public class HRAssessment extends PersonAttribute implements Serializable {

    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "personId")
    public int personId;
    @Column(name = "assessmentDate")
    @Temporal(TemporalType.DATE)
    public Date assessmentDate;
    @Column(name = "jobKnowledge", nullable = false)
    public int jobKnowledge;
    @Column(name = "availability", nullable = false)
    public int availability;
    @Column(name = "dependability", nullable = false)
    public int dependability;
    @Column(name = "initiative", nullable = false)
    public int initiative;
    @Column(name = "judgement", nullable = false)
    public int judgement;
    @Column(name = "productiveness", nullable = false)
    public int productiveness;
    @Column(name = "quality", nullable = false)
    public int quality;
    @Column(name = "reliability", nullable = false)
    public int reliability;
    @Column(name = "workingRelationships", nullable = false)
    public int workingRelationships;
    @Column(name = "rating")
    public double rating;
    @Column(name = "totalScore", nullable = false)
    public double totalScore;
    @Column(name = "average", nullable = false)
    public double average;
    @Column(name = "comment")
    public String comment;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public int getPersonId() {
        return personId;
    }

    
    public Integer getSeq() {
        return seq;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(Date assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getDependability() {
        return dependability;
    }

    public void setDependability(int dependability) {
        this.dependability = dependability;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getJobKnowledge() {
        return jobKnowledge;
    }

    public void setJobKnowledge(int jobKnowledge) {
        this.jobKnowledge = jobKnowledge;
    }

    public int getJudgement() {
        return judgement;
    }

    public void setJudgement(int judgement) {
        this.judgement = judgement;
    }

    public int getProductiveness() {
        return productiveness;
    }

    public void setProductiveness(int productiveness) {
        this.productiveness = productiveness;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public int getWorkingRelationships() {
        return workingRelationships;
    }

    public void setWorkingRelationships(int workingRelationships) {
        this.workingRelationships = workingRelationships;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
