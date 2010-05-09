/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "BookSaleRef")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, 
    columnSearch = {"code","title","schoolYear","course","gradeLevel","amount"}, orderBy="a.schoolYear DESC, a.course, a.gradeLevel, a.code")
@Displays({
        @Display(name="code"),
        @Display(name="title"),
        @Display(name="author"),
        @Display(name="schoolYear"),
        @Display(name="course", type="PopSearch", linktoBean=Course.class),
        @Display(name="gradeLevel", type="Combo", modelCombo={"1","2","3","4","5","6","7"}),
        @Display(name="description"),
        @Display(name="amount")
})
public class BookSaleRef extends AbstractIBean {
    @Id
    @Column(name="seq", nullable=false)
    public int seq;
    @Column(name="code", nullable=false)
    public String code;
    @Column(name="title")
    public String title;
    @Column(name="author")
    public String author;
    @Column(name="schoolYear")
    public String schoolYear;
    @Column(name="course")
    public String course;
    @Column(name="gradeLevel")
    public String gradeLevel;
    @Column(name="description")
    public String description;
    @Column(name="amount")
    public double amount;

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "code","title","gradeLevel");
    }

    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return code.trim()+"-"+title.trim();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean cacheClient() {
        return true;
    }
	
	@Override
	public void setupIndex() {
		runIndex(1, "gradeLevel");
	}
}
