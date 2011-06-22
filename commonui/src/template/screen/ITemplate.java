/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BindingGroup;
import rule.BusinessRuleWrapper;
import template.UITemplate;

/**
 *
 * @author Entokwaa
 */
public interface ITemplate {
    public List<template.screen.component.AbstractComponentRenderer> lstAbsComponent = new ArrayList<template.screen.component.AbstractComponentRenderer>();
    
    void addFieldComponent(JComponent comp);
    Object getCurrentObject();
    Class getCurrentClass();
    UITemplate getUITemplate();
    JTable getTable();
    BusinessRuleWrapper getRuleWrapper();
    List<JComponent> getFieldComponents();
    boolean isParent();
    BindingGroup getBinding();
    List getRecordList();
    
    List<AbstractChildTemplatePanel> getTabs();
    javax.swing.JTabbedPane getTabPane();
}
