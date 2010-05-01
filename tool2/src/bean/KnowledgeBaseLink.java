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
@Table(name = "KnowledgeBase")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"linkCode","title"})
@Displays({
        @Display(name="linkCode"),
        @Display(name="title")
})
public class KnowledgeBaseLink extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "knowledgeBaseCode")
    public String knowledgeBaseCode;
    @Column(name = "linkCode")
    public String linkCode;
    @Column(name = "title")
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKnowledgeBaseCode() {
        return knowledgeBaseCode;
    }

    public void setKnowledgeBaseCode(String knowledgeBaseCode) {
        this.knowledgeBaseCode = knowledgeBaseCode;
    }

    public String getLinkCode() {
        return linkCode;
    }

    public void setLinkCode(String linkCode) {
        this.linkCode = linkCode;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "linkCode");
	}
}
