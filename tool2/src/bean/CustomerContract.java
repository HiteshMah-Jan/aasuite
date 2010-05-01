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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CustomerContract")
@UITemplate(showChart=false, template=TemplateTabSinglePage.class, title="Customer Contract",
    gridCount = 6, columnSearch = {"contract","signDate","totalAmount"},sumFooter="2")
@Displays({
//    @Display(name="customerId", type="PopSearch", linktoBean=CustomerContact.class),
    @Display(name="contract", type="PopSearch", linktoBean=Contract.class),
    @Display(name="totalAmount"),
    @Display(name="signDate"),
    @Display(name="description", type="TextArea", gridFieldWidth=5, width=-1)
})
public class CustomerContract extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq")
    public String seq;
    @Column(name = "contract")
    public String contract;
    @Column(name = "customerId")
    public int customerId;
    @Column(name = "totalAmount")
    public double totalAmount;
    @Column(name = "signDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date signDate;
    @Column(name = "description", length=4000)
    public String description;

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "contract");
	}
	
    public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
