/*
 * Airport.java
 *
 * Created on Sep 30, 2007, 8:02:06 PM
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
import template.*;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "CargoMessageTransactionDetail")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, 
    columnSearch = {"entity","entityId","remarks"})
@Displays({
    @Display(name = "entity", type="Combo", modelCombo={"Awb","UldNumber","Flight","FlightSchedule"}),
    @Display(name = "entityId", label="Entity ID"),
    @Display(name = "remarks", width=-1, gridFieldWidth=3)
})
@template.ActionButtons({
    @template.ActionButton(name="btnShowRecord", label="Show Record")
})
public class CargoMessageTransactionDetail extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "cargoMessageInboxSeq")
    public int cargoMessageInboxSeq;
    @Column(name = "entity")
    public String entity;
    @Column(name = "entityId")
    public String entityId;
    @Column(name = "remarks")
    public String remarks;

    public int getCargoMessageInboxSeq() {
        return cargoMessageInboxSeq;
    }

    public void setCargoMessageInboxSeq(int cargoMessageInboxSeq) {
        this.cargoMessageInboxSeq = cargoMessageInboxSeq;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }    

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "entity","entityId","remarks");
	}
}
