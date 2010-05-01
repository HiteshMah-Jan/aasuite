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
@Table(name = "SupportTicket")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"customerId","agentId","callDate","productCode","mainConcern"}, showChart=true, showFiles=true)
@Displays({
        @Display(name="customerId"),
        @Display(name="agentId"),
        @Display(name="callDate"),
        @Display(name="productCode"),
        @Display(name="mainConcern"),
        @Display(name="supportText"),
        @Display(name="status")
})
@ChildRecords(
    value={
        @ChildRecord(title="Call Detail", entity = SupportTicketDetail.class, sql = "SELECT a FROM SupportTicketDetail a WHERE a.supportTicketId=${seq}", fieldMapping={"seq","supportTicketId"})
    }
)
public class SupportTicket extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq")
    public Integer seq;
    @Column(name = "customerId")
    public int customerId;
    @Column(name = "agentId")
    public int agentId;
    @Column(name = "callDate")
    public String callDate;
    @Column(name = "productCode")
    public String productCode;
    @Column(name = "mainConcern")
    public String mainConcern;
    @Column(name = "supportText", length=4000)
    public String supportText;
    @Column(name = "status")
    public String status;

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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getMainConcern() {
        return mainConcern;
    }

    public void setMainConcern(String mainConcern) {
        this.mainConcern = mainConcern;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "productCode");
	}
}
