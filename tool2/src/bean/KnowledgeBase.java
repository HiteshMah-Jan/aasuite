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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import template.*;
import template.screen.*;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "KnowledgeBase")
@UITemplate(template = TemplateTabPage.class, gridCount = 4, columnSearch = {"code","productCode","title"}, showFiles=true)
@Displays({
        @Display(name="code"),
        @Display(name="productCode"),
        @Display(name="title", gridFieldWidth=3, width=-1),
        @Display(name="description", gridFieldWidth=3, width=-1)
})
@ChildRecords(
    value={
        @ChildRecord(title="Details", entity = KnowledgeBaseDetail.class, sql = "SELECT a FROM KnowledgeBaseDetail a WHERE a.knowledgeBaseCode='${code}'", fieldMapping={"code","knowledgeBaseCode"}),
        @ChildRecord(title="Links", entity = KnowledgeBaseLink.class, sql = "SELECT a FROM KnowledgeBaseLink a WHERE a.knowledgeBaseCode='${code}'", fieldMapping={"code","knowledgeBaseCode"})
    }
)
public class KnowledgeBase extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "code")
    public String code;
    @Column(name = "productCode")
    public String productCode;
    @Column(name = "title")
    public String title;
    @Column(name = "description", length=4000)
    public String description;

    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return code+"-"+title;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "code", "description");
	}
}
