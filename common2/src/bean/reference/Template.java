/*
 * Template.java
 *
 * Created on Nov 15, 2007, 1:18:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Template")
@Displays({
        @Display(name="id"),
        @Display(name="date"),
        @Display(name="percentage"),
        @Display(name="examinationType"),
        @Display(name="gradingPeriod"),
        @Display(name="score")
})
public class Template extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    public Integer id;
    @Column(name = "date", nullable = false)
    public String date;
    @Column(name = "percentage", nullable = false)
    public String percentage;
    @Column(name = "examinationType", nullable = false)
    public String examinationType;
    @Column(name = "gradingPeriod", nullable = false)
    public String gradingPeriod;
    @Column(name = "score", nullable = false)
    public String score;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(String examinationType) {
        this.examinationType = examinationType;
    }

    public String getGradingPeriod() {
        return gradingPeriod;
    }

    public void setGradingPeriod(String gradingPeriod) {
        this.gradingPeriod = gradingPeriod;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    
    @Override
    public String toString() {
        return examinationType;
    }
}
