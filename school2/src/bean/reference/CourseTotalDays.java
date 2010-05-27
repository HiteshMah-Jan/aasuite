/*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;
import util.DBClient;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "CourseTotalDays")
@UITemplate(template=TemplateSinglePage.class, columnSearch={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dece","q1","q2","q3","q4"}, 
		gridCount=4)
@Displays({
    @Display(name="jan"),
    @Display(name="feb"),
    @Display(name="mar"),
    @Display(name="apr"),
    @Display(name="may"),
    @Display(name="jun"),
    @Display(name="jul"),
    @Display(name="aug"),
    @Display(name="sep"),
    @Display(name="oct"),
    @Display(name="nov"),
    @Display(name="dece"),
    @Display(name="q1"),
    @Display(name="q2"),
    @Display(name="q3"),
    @Display(name="q4")
})
public class CourseTotalDays extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "course")
    public String course;
    @Column(name = "jan")
    public int jan;
    @Column(name = "feb")
    public int feb;
    @Column(name = "mar")
    public int mar;
    @Column(name = "apr")
    public int apr;
    @Column(name = "may")
    public int may;
    @Column(name = "jun")
    public int jun;
    @Column(name = "jul")
    public int jul;
    @Column(name = "aug")
    public int aug;
    @Column(name = "sep")
    public int sep;
    @Column(name = "oct")
    public int oct;
    @Column(name = "nov")
    public int nov;
    @Column(name = "dece")
    public int dece;

    @Column(name = "q1")
    public int q1;
    @Column(name = "q2")
    public int q2;
    @Column(name = "q3")
    public int q3;
    @Column(name = "q4")
    public int q4;
	
	@Override
	public boolean cacheClient() {
		return true;
	}
    
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getJan() {
		return jan;
	}

	public void setJan(int jan) {
		this.jan = jan;
	}

	public int getFeb() {
		return feb;
	}

	public void setFeb(int feb) {
		this.feb = feb;
	}

	public int getMar() {
		return mar;
	}

	public void setMar(int mar) {
		this.mar = mar;
	}

	public int getApr() {
		return apr;
	}

	public void setApr(int apr) {
		this.apr = apr;
	}

	public int getMay() {
		return may;
	}

	public void setMay(int may) {
		this.may = may;
	}

	public int getJun() {
		return jun;
	}

	public void setJun(int jun) {
		this.jun = jun;
	}

	public int getJul() {
		return jul;
	}

	public void setJul(int jul) {
		this.jul = jul;
	}

	public int getAug() {
		return aug;
	}

	public void setAug(int aug) {
		this.aug = aug;
	}

	public int getSep() {
		return sep;
	}

	public void setSep(int sep) {
		this.sep = sep;
	}

	public int getOct() {
		return oct;
	}

	public void setOct(int oct) {
		this.oct = oct;
	}

	public int getNov() {
		return nov;
	}

	public void setNov(int nov) {
		this.nov = nov;
	}

	public int getDece() {
		return dece;
	}

	public void setDece(int dece) {
		this.dece = dece;
	}

	public int getQ1() {
		return q1;
	}

	public void setQ1(int q1) {
		this.q1 = q1;
	}

	public int getQ2() {
		return q2;
	}

	public void setQ2(int q2) {
		this.q2 = q2;
	}

	public int getQ3() {
		return q3;
	}

	public void setQ3(int q3) {
		this.q3 = q3;
	}

	public int getQ4() {
		return q4;
	}

	public void setQ4(int q4) {
		this.q4 = q4;
	}

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "course");
	}
	
	static Map<String, CourseTotalDays> map = new HashMap();
	public static CourseTotalDays extractTotalCourseDays(String course) {
		CourseTotalDays d = map.get(course);
		if (d==null) {
			d = (CourseTotalDays) DBClient.getFirstRecord("SELECT a FROM CourseTotalDays a WHERE a.course='",course,"'");
			if (d==null || d.isEmptyKey()) {
				d = new CourseTotalDays();
				d.course = course;
//				d.aug = 10;
//				d.q1 = 45;
				d.save();
			}
			map.put(course, d);
		}
		return d;
	}

	public static CourseTotalDays extractTotalGradeLevelDays(String gradeLevel) {
		CourseTotalDays d = map.get(gradeLevel);
//		map.clear();
		if (d==null) {
			d = (CourseTotalDays) DBClient.getFirstRecord("SELECT a FROM CourseTotalDays a, GradeLevel b WHERE a.course=b.course AND b.code='",gradeLevel,"'");
			if (d==null || d.isEmptyKey()) {
				Course c = (Course) DBClient.getFirstRecord("SELECT a FROM Course a, GradeLevel b WHERE a.code=b.course AND b.code='",gradeLevel,"'");
				d = new CourseTotalDays();
				d.course = c.code;
//				d.aug = 10;
//				d.q1 = 45;
				d.save();
			}
			map.put(gradeLevel, d);
		}
		return d;
	}
}
