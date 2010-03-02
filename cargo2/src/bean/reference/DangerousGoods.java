/*
 * DangerousGoods.java
 *
 * Created on Oct 23, 2007, 8:35:43 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

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
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "DangerousGoods")
@UITemplate(template = TemplateSinglePage.class, gridCount = 6,
    columnSearch = {"unNumber", "name", "description"}, showChart = true)
@Displays({
    @Display(name = "unNumber"),
    @Display(name = "name", width = 250),
    @Display(name = "description", gridFieldWidth=3, width=-1)
})
public class DangerousGoods extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "unNumber", nullable = false, length=20)
    public String unNumber;
    @Column(name = "name", nullable = false)
    public String name;
    @Column(name = "description")
    public String description;
    @Column(name = "isActive")
    public boolean isActive;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getUnNumber() {
        return unNumber;
    }

    public void setUnNumber(String unNumber) {
        this.unNumber = unNumber;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria,"unNumber","name","description");
    }
}
