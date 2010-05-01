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
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

import service.util.AbstractIBean;
import template.screen.*;
import template.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "Licence")
@UITemplate(showChart=true, template=TemplateSinglePage.class, title="Licence Generator",
    gridCount = 4, columnSearch = {"product","customerName","email","sentBy","packageType"})
@Displays({
    @Display(name = "product", type="Combo", modelCombo={"SCHOOL","HOSPITAL"}),
    @Display(name = "customerName"),
    @Display(name = "email"),
    @Display(name = "sentBy"),
    @Display(name = "packageType", type="Combo", modelCombo={"COMPLETE","A","B","C","D"}, gridFieldWidth=3, width=-1)
})
@ActionButtons({
    @ActionButton(name="btnCreateLicence", label="Create Licence"),
    @ActionButton(name="btnSendLicence", label="Send Licence")
})
public class Licence extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "product", nullable = false, length=50)
    public String product;
    @Column(name = "customerName", nullable = false, length=150)
    public String customerName;
    @Column(name = "email", nullable = false, length=50)
    public String email;
    @Column(name = "companyName", length=100)
    public String companyName;
    @Column(name = "userCount")
    public int userCount;
    @Column(name = "sentBy", length=100)
    public String sentBy;
    @Column(name = "packageType")
    public String packageType;
    @Column(name = "sentDate")
    @Temporal(value = TemporalType.DATE)
    public Date sentDate;

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }


	public Map getLicence() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq", seq);
		map.put("product", product);
		map.put("customerName", customerName);
		map.put("email", email);
		map.put("companyName", companyName);
		map.put("userCount", userCount);
		map.put("packageType", packageType);
		map.put("sentBy", sentBy);
		map.put("sentDate", sentDate);
		return map;
	}	

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "customerName");
	}
}
