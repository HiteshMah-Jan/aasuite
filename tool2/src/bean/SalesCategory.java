/*
 * SalesScriptQuestion.java
 *
 * Created on Apr 26, 2008, 12:20:42 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import service.util.AbstractIBean;
import javax.persistence.Table;
import util.DBClient;

/**
 *
 * @author ebhoy
 */
@Entity
@Table(name = "SalesCategory")
public class SalesCategory extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "category")
    private String category;
    @Column(name = "product")
    private String product;
    @Column(name = "fullScript", length = 4000)
    private String fullScript;
    @Column(name = "description")
    private String description;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullScript() {
        return fullScript;
    }

    public void setFullScript(String fullScript) {
        this.fullScript = fullScript;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void createFullScript() {
        StringBuffer sb = new StringBuffer();
        List lst = selectListCache("SELECT a FROM SalesCategoryScript a WHERE a.salesCategory='"+category+"'");
        String myScript = null;
        for (Object object : lst) {
            SalesCategoryScript sales = (SalesCategoryScript) object;
            myScript = sales.getFullScript();
            sb.append(sales.getSortNumber()).append(". ").append(myScript).append("\n-------------------------------------------------\n\n");
        }
        setFullScript(sb.toString());
        DBClient.persistBean(this);
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "category");
	}
}
