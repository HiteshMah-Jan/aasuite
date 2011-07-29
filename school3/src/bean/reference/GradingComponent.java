/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateTabSinglePage;
import util.BeanUtil;

@Entity
@Table(name = "GradingComponent")
@UITemplate(template=TemplateTabSinglePage.class, gridCount = 4, columnSearch = {"component"})
@Displays({
	@Display(name="component"),
	@Display(name="description")
})
public class GradingComponent extends AbstractIBean implements Serializable {
	@Id
	public String component;
	public String description;

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static void main(String[] args) {
        view(GradingComponent.class);
    }
    
    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        return BeanUtil.concat(component," - ",description);
    }
	
	@Override
	public boolean cacheClient() {
		return true;
	}

	@Override
	public String popupSearch(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}



