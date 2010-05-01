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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import service.util.AbstractIBean;
import javax.persistence.Table;

/**
 *
 * @author ebhoy
 */
@Entity
@Table(name = "SalesFeatures")
public class SalesFeatures extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "productCode")
    public String productCode;
    @Column(name = "features", length=4000)
    public String features;

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

	@Override
	public String popupSearch(String criteria) {
		return buildSearch(criteria, "productCode", "features");
	}
}
