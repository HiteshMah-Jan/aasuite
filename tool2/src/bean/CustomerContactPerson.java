/*
 * Bug.java
 *
 * Created on Oct 31, 2007, 3:52:31 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.persistence.*;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CustomerContactPerson")
@UITemplate(gridCount = 4, columnSearch = {"contactName","position","telephone"})
@Displays({
    @Display(name="contactName",mandatory=true,gridFieldWidth=3,width=-1),
    @Display(name="position",gridFieldWidth=3,width=-1),
    @Display(name="telephone"),
    @Display(name="fax")
})
public class CustomerContactPerson extends AbstractIBean {
    @Id
    public Integer seq;
    @Column(name = "customerId")
    public int customerId;
    @Column(name = "contactName")
    public String contactName;
    @Column(name = "position", length=50)
    public String position;
    @Column(name = "telephone", length=20)
    public String telephone;
    @Column(name = "fax", length=20)
    public String fax;
    @Column(name = "bestContact")
    public boolean bestContact;

    public boolean isBestContact() {
        return bestContact;
    }

    public void setBestContact(boolean bestContact) {
        this.bestContact = bestContact;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "contactName");
	}
	
	@Override
	public void setupIndex() {
		runIndex(1, "customerId");
	}
}
