/*
 * AwbFlt.java
 *
 * Created on Sep 30, 2007, 8:02:09 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.awb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabPage;
import util.DBClient;
import bean.Participant;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "AwbParticipant")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"identifier","name"})
@Displays({
    @Display(name = "identifier", type="Combo", sqlCombo="SELECT a.code FROM ComboTypes a WHERE a.category='PARTICIPANT TYPE'"),
    @Display(name = "code", type="PopSearch", linktoBean=Participant.class)
})
public class AwbParticipant extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "awbSeq", nullable = false)
    public int awbSeq;
    @Column(name = "identifier")
    public String identifier;
    @Column(name = "code")
    public String code;
    @Column(name = "name")
    public String name;

    @Override
	public void save() {
    	if (code != null) {
    		Participant part = (Participant) DBClient.getFirstRecord("SELECT a FROM Participant a WHERE a.personId='",code,"'");
    		name = part.name;
    	}
		super.save();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public int getAwbSeq() {
		return awbSeq;
	}

	public void setAwbSeq(int awbSeq) {
		this.awbSeq = awbSeq;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }
}
