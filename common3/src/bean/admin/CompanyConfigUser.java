/*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateSinglePage;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "CompanyConfigUser")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"userName"})
@Displays({
    @Display(name="businessCode", type="Label", width=200),
    @Display(name="userName", linktoBean=AclUser.class, type="PopSearch")
})
    
   
public class CompanyConfigUser extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "businessCode")
    public String businessCode;
    @Column(name = "userName")
    public String userName;
	@Override
	
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}	
	
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
