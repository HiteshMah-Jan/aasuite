/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen;

import java.util.List;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BindingGroup;
import rule.BusinessRuleWrapper;
import template.UITemplate;

/**
 *
 * @author Charliemagne Mark
 */
public class WebTemplate implements ITemplate {
    Class bean;
    Object obj;
    
    @Override
	public List<AbstractChildTemplatePanel> getTabs() {
		return null;
	}

    public static WebTemplate getInstance(String bean) {
        WebTemplate web = new WebTemplate();
        web.bean = util.PanelUtil.getBeanClass(bean);
        try {
            web.obj = web.bean.newInstance();
        }
        catch (Exception e) {
            util.Log.severe("Web Template cannot find "+bean, e);
        }
        return web;
    }
    
    public void addFieldComponent(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getCurrentObject() {
        return obj;
    }

    public Class getCurrentClass() {
        return bean;
    }

    public UITemplate getUITemplate() {
        return (UITemplate) bean.getAnnotation(UITemplate.class);
    }

    public JTable getTable() {
        return null;
    }

    public BusinessRuleWrapper getRuleWrapper() {
        return BusinessRuleWrapper.getInstance(getUITemplate());
    }

    public List<JComponent> getFieldComponents() {
        return null;
    }

    public boolean isParent() {
        return true;
    }

    public BindingGroup getBinding() {
        return null;
    }

    public List getRecordList() {
        return null;
    }

	@Override
	public JTabbedPane getTabPane() {
		return null;
	}
}
