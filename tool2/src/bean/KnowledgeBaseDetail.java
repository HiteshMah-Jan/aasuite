/*
 * Bug.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.*;
import template.screen.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "KnowledgeBaseDetail")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"title"})
@Displays({
        @Display(name="title", gridFieldWidth=3, width=400),
        @Display(name="description", gridFieldWidth=3, width=-1)
})
public class KnowledgeBaseDetail extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "knowledgeBaseCode")
    public String knowledgeBaseCode;
    @Column(name = "title")
    public String title;
    @Column(name = "description", length=4000)
    public String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKnowledgeBaseCode() {
        return knowledgeBaseCode;
    }

    public void setKnowledgeBaseCode(String knowledgeBaseCode) {
        this.knowledgeBaseCode = knowledgeBaseCode;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "title");
	}
}
