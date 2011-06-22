/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import rule.BusinessRuleWrapper;
import template.screen.AbstractTemplatePanel;

/**
 *
 * @author Entokwaa
 */
public class TemplateReader {
    static Map<Class, AbstractTemplatePanel> templates = new HashMap<Class, AbstractTemplatePanel>();
    static Map<Class, AbstractTemplatePanel> tabtemplates = new HashMap<Class, AbstractTemplatePanel>();
    public static void reset() {
        templates.clear();
    }
    public static void resetTab() {
        tabtemplates.clear();
    }
    
    public static AbstractTemplatePanel getTemplate(Class bean) {
        if (templates.get(bean)!=null) {
            return templates.get(bean);
        }
        AbstractTemplatePanel newTemplate = getNewTemplate(bean);
        if (newTemplate!=null) templates.put(bean, newTemplate);
        return newTemplate;
    }

    public static AbstractTemplatePanel getTemplateForTab(Class bean) {
//        if (tabtemplates.get(bean)!=null) {
//            return tabtemplates.get(bean);
//        }
        AbstractTemplatePanel newTemplate = getNewTemplate(bean);
//        if (newTemplate!=null) tabtemplates.put(bean, newTemplate);
        return newTemplate;
    }
    
    private static AbstractTemplatePanel getNewTemplate(Class bean) {
        try {
            UITemplate template = (UITemplate) bean.getAnnotation(UITemplate.class); 
            if (template==null) throw new Exception("NULL TEMPLATE");
            Class templateCls = template.template();
            AbstractTemplatePanel tmp = AbstractTemplatePanel.getInstance(templateCls, template);
            if (tmp==null) throw new Exception("NULL TEMPLATE");
            tmp.setBean(bean);
            tmp.ruleWrapper = BusinessRuleWrapper.getInstance(tmp);
            return tmp;
        } catch (Exception ex) {
        	util.Log.warning(bean.getName(),"\n",ex.getMessage());
        }
        return null;
    }
}
