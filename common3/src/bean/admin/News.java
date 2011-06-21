/*
 * News.java
 * 
 * Created on Dec 28, 2007, 5:31:04 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "News")
@UITemplate(template = TemplateDefault.class, gridCount = 4, columnSearch = {"date", "newsDescription", "headline"})
@Displays({
        @Display(name="seq"),
        @Display(name="date"),
        @Display(name="newsDescription"),
        @Display(name="headline")
})
public class News extends AbstractIBean {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date date;
    @Column(name = "newsDescription", nullable = false)
    public String newsDescription;
    @Column(name = "headline", nullable = false)
    public boolean headline;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isHeadline() {
        return headline;
    }

    public void setHeadline(boolean headline) {
        this.headline = headline;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
