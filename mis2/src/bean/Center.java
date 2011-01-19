/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;

/**
 *
 * @author disney
 */
@Entity
@Table(name = "Center")
@UITemplate(template = TemplateDefault.class, gridCount=4, columnSearch = {"name", "branchName"})
@Displays({
        @Display(name="name"),
        @Display(name="branchName"),
        @Display(name="managedBy", type="PopSearch", linktoBean=Employee.class),
        @Display(name="memberLead", type="PopSearch", linktoBean=Member.class)
})
public class Center extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "name", nullable = false)
    public String name;
    @Column(name = "branchName", nullable = false)
    public String branchName;
    @Column(name = "managedBy")
    public int managedBy;
    @Column(name = "memberLead")
    public int memberLead;

    public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(int managedBy) {
		this.managedBy = managedBy;
	}

	public int getMemberLead() {
		return memberLead;
	}

	public void setMemberLead(int memberLead) {
		this.memberLead = memberLead;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "name", "branchName");
    }
}
