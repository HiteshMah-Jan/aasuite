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
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "SupportTicketDetail")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"agentId","callDate","mainConcern"})
@Displays({
        @Display(name="agentId"),
        @Display(name="callDate"),
        @Display(name="mainConcern"),
        @Display(name="supportText"),
        @Display(name="knowledgeBaseCode1"),
        @Display(name="knowledgeBaseCode2"),
        @Display(name="status")
})
public class SupportTicketDetail extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "supportTicketId")
    public int supportTicketId;
    @Column(name = "agentId")
    public int agentId;
    @Column(name = "callDate")
    public String callDate;
    @Column(name = "mainConcern")
    public String mainConcern;
    @Column(name = "supportText", length=4000)
    public String supportText;
    @Column(name = "status")
    public String status;
    @Column(name = "knowledgeBaseCode1")
    public String knowledgeBaseCode1;
    @Column(name = "knowledgeBaseCode2")
    public String knowledgeBaseCode2;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getKnowledgeBaseCode1() {
        return knowledgeBaseCode1;
    }

    public void setKnowledgeBaseCode1(String knowledgeBaseCode1) {
        this.knowledgeBaseCode1 = knowledgeBaseCode1;
    }

    public String getKnowledgeBaseCode2() {
        return knowledgeBaseCode2;
    }

    public void setKnowledgeBaseCode2(String knowledgeBaseCode2) {
        this.knowledgeBaseCode2 = knowledgeBaseCode2;
    }

    public String getMainConcern() {
        return mainConcern;
    }

    public void setMainConcern(String mainConcern) {
        this.mainConcern = mainConcern;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupportText() {
        return supportText;
    }

    public void setSupportText(String supportText) {
        this.supportText = supportText;
    }

    public int getSupportTicketId() {
        return supportTicketId;
    }

    public void setSupportTicketId(int supportTicketId) {
        this.supportTicketId = supportTicketId;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "supportText");
	}
}
