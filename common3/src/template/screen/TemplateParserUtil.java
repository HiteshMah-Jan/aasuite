/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template.screen;

import bean.admin.AppConfig;
import component.IRule;
import component.JButtonPallete;
import component.JLinkLabelPallete;
import component.PalleteRuleManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Converter;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import constants.UserInfo;
import rule.BusinessRuleWrapper;
import template.*;
import template.screen.AbstractTemplatePanel.FieldCompose;
import template.screen.component.AbstractComponentRenderer;
import template.screen.component.JTableReadOnly;
import util.BeanUtil;
import util.Log;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class TemplateParserUtil {

    public ITemplate templatePanel;

    public JPanel constructGroupPanel(BindingGroup bindingGroup, DisplayGroup group) {
        List<FieldCompose> lstCompose;
        if (group == null) {
            lstCompose = getFields();
        } else {
            lstCompose = getFields(group);
        }
        GridBagConstraints cons = new GridBagConstraints();
        int totalGrid = -1;
        JPanel pnlGroup = new JPanel(new GridBagLayout());
        pnlGroup.setToolTipText(templatePanel.getCurrentClass().getSimpleName());
        int gridCount = templatePanel.getUITemplate().gridCount();
        if (group != null && group.gridCount() > 0) {
            gridCount = group.gridCount();
        }
        for (FieldCompose fieldCompose : lstCompose) {
            if (inMerge(fieldCompose, lstCompose)) {
                continue;
            }
            AbstractComponentRenderer rcomp = AbstractComponentRenderer.getInstance(bindingGroup, fieldCompose, templatePanel.getTable(), templatePanel);
            JComponent field = (JComponent) rcomp.getRenderedListeningField();
            if (!fieldCompose.display.enabled()) {
            	field.setEnabled(false);
            }
            JComponent label = (JComponent) rcomp.getRenderedLabel();
            templatePanel.addFieldComponent(field);
            templatePanel.addFieldComponent(label);
            templatePanel.lstAbsComponent.add(rcomp);
            if (totalGrid == -1 || totalGrid >= gridCount) {
                cons.gridy++;
                totalGrid = 0;
                cons.insets = new Insets(1, 2, 0, 0);
            } else {
            	if (fieldCompose.display.labelTop() || fieldCompose.display.hideLabel()) {
                    cons.insets = new Insets(1, 1, 0, 0);
            	}
            	else {
                    cons.insets = new Insets(1, 20, 0, 0);
            	}
            }
            totalGrid += fieldCompose.display.gridLabelWidth() + fieldCompose.display.gridFieldWidth();
            cons.anchor = GridBagConstraints.NORTHWEST;
            cons.gridwidth = fieldCompose.display.gridLabelWidth();
            if (hasMerge(fieldCompose)) {
                GridBagConstraints mergeCons = new GridBagConstraints();
                mergeCons.anchor = GridBagConstraints.NORTHWEST;
                mergeCons.insets = new Insets(1, 1, 0, 0);
                JPanel pnlMerge = new JPanel(new GridBagLayout());
                pnlMerge.setToolTipText(templatePanel.getCurrentClass().getSimpleName());
                pnlMerge.add(label, mergeCons);
                cons.gridwidth = fieldCompose.display.gridFieldWidth() + 1;
                if (fieldCompose.display.width() == -1) {
                    cons.fill = GridBagConstraints.HORIZONTAL;
                } else {
                    cons.fill = GridBagConstraints.NONE;
                }

                //check if button exist in the field
                if (fieldCompose.display.button().isEmpty()) {
                    pnlMerge.add(field, mergeCons);
                } else {
                    JButtonPallete btn = new JButtonPallete(fieldCompose.display.button(), BeanUtil.concat("btn",fieldCompose.field.getName()));
                    btn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            BusinessRuleWrapper ruleWrapper = templatePanel.getRuleWrapper();
                            PalleteRuleManager.runOnClick((IRule)e.getSource(), ruleWrapper); 
                        }
                    });
                    templatePanel.addFieldComponent(field);
                    JPanel pnl = new JPanel(new GridBagLayout());
                    pnl.setToolTipText(templatePanel.getCurrentClass().getSimpleName());
                    pnl.add(field);
                    pnl.add(btn);
                    pnlMerge.add(pnl, mergeCons);
                }
                addMergeFields(bindingGroup, pnlMerge, mergeCons, fieldCompose, lstCompose);
                pnlGroup.add(pnlMerge, cons);
            } else {
                if (!fieldCompose.display.labelTop()) {
                	if (!fieldCompose.display.noLabel()) {
                        pnlGroup.add(label, cons);
                	}
                }
                else {
                    int oldCons = cons.anchor;
                    cons.anchor = GridBagConstraints.NORTHWEST;
                    JPanel tpnl = new JPanel(new GridLayout(2,0, 2, 1));
                    tpnl.add(new JLabel(""));
                    String ltmp = fieldCompose.display.leftLabel();
                    if (ltmp!=null && !ltmp.trim().isEmpty()) {
                        ltmp += ":";
                    }
                    JLabel lbl = new JLinkLabelPallete(ltmp, null);
                    tpnl.add(lbl);
                    
                    pnlGroup.add(tpnl, cons);
                    cons.anchor = oldCons;
                }
                cons.gridwidth = fieldCompose.display.gridFieldWidth();
                if (fieldCompose.display.width() == -1) {
                    cons.fill = GridBagConstraints.HORIZONTAL;
                } else {
                    cons.fill = GridBagConstraints.NONE;
                }

                //check if button exist in the field
                if (fieldCompose.display.button().isEmpty()) {
                    if (fieldCompose.display.labelTop()) {
                        GridBagConstraints c = new GridBagConstraints();
                        c.gridx = 0;
                        c.anchor = GridBagConstraints.CENTER;
                        JPanel tmp = new JPanel(new GridBagLayout());
                        c.gridy = 1;
                        JLabel lbl = (JLabel) label;
                        if (lbl.getText().endsWith(":")) {
                            lbl.setText(lbl.getText().replace(":",""));
                        }
                        tmp.add(label, c);
                        c.gridy = 2;
                        c.anchor = GridBagConstraints.WEST;
                        tmp.add(field, c);
                        pnlGroup.add(tmp, cons);
                    }
                    else {
                        pnlGroup.add(field, cons);
                    }
                } else {
                    JButtonPallete btn = new JButtonPallete(fieldCompose.display.button(), BeanUtil.concat("btn",fieldCompose.field.getName()));
                    templatePanel.addFieldComponent(field);
                    JPanel pnl = new JPanel(new GridBagLayout());
                    pnl.setToolTipText(templatePanel.getCurrentClass().getSimpleName());
                    btn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            BusinessRuleWrapper ruleWrapper = templatePanel.getRuleWrapper();
                            PalleteRuleManager.runOnClick((IRule)e.getSource(), ruleWrapper); 
                        }
                    });
                    pnl.add(field);
                    pnl.add(btn);
                    if (fieldCompose.display.labelTop()) {
//                        pnl.setBorder(javax.swing.BorderFactory.createTitledBorder(((JLabel)label).getText()));
                        pnl.removeAll();
                        pnl.setLayout(new GridBagLayout());
                        GridBagConstraints con = new GridBagConstraints();
//                        con.gridwidth = 2;
                        con.gridy = 1;
                        JLabel lbl = (JLabel) label;
                        if (lbl.getText().endsWith(":")) {
                            lbl.setText(lbl.getText().replace(":",""));
                        }
                        pnl.add(label, con);
//                        con.gridwidth = 1;
                        con.gridy = 2;
                        pnl.add(field, con);
                        pnl.add(btn, con);
                        pnlGroup.add(pnl, cons);
                    }
                    else {
                        pnlGroup.add(pnl, cons);
                    }
                }
            }
        }
        if (group != null) {
        	if (!UserInfo.hasDuty(group.duties())) {
        		pnlGroup.setVisible(false);
        	}
        	else {
                pnlGroup = GroupPanel.construct(group.title(), pnlGroup, templatePanel.getTable());
                pnlGroup.setToolTipText(templatePanel.getCurrentClass().getSimpleName());
                if (constants.Constants.panelBackground!=null) {
//                    pnlGroup.setBackground(constants.Constants.panelBackground);
                }
        	}
        }
        return pnlGroup;
    }

    private void addMergeFields(BindingGroup bindingGroup, JPanel pnl, GridBagConstraints mergeCons, FieldCompose fieldCompose, List<FieldCompose> lstCompose) {
        String[] mergeFields = fieldCompose.display.mergeFields();
        if (fieldCompose.display.verticalMerge()) {
        	JPanel mergePnl = new JPanel(new GridLayout(0, 2, 2, 2));
        	if (!fieldCompose.display.noLabel()) {
            	mergePnl.setBorder(new TitledBorder(fieldCompose.display.label()));
        	}
            for (String string : mergeFields) {
                //get the field
                FieldCompose fTmp = null;
                for (FieldCompose f : lstCompose) {
                    if (string.equals(f.display.name())) {
                        fTmp = f;
                        break;
                    }
                }
                //merge the field in panel            
                AbstractComponentRenderer rcomp = AbstractComponentRenderer.getInstance(bindingGroup, fTmp, templatePanel.getTable(), templatePanel);
                JComponent field = (JComponent) rcomp.getRenderedListeningField();
                JComponent label = (JComponent) rcomp.getRenderedLabel();
                templatePanel.addFieldComponent(field);
                templatePanel.lstAbsComponent.add(rcomp);
                mergePnl.add(label);
                mergePnl.add(field);
            }
            pnl.add(mergePnl);
        }
        else {
            for (String string : mergeFields) {
                //get the field
                FieldCompose fTmp = null;
                for (FieldCompose f : lstCompose) {
                    if (string.equals(f.display.name())) {
                        fTmp = f;
                        break;
                    }
                }
                //merge the field in panel            
                AbstractComponentRenderer rcomp = AbstractComponentRenderer.getInstance(bindingGroup, fTmp, templatePanel.getTable(), templatePanel);
                JComponent field = (JComponent) rcomp.getRenderedListeningField();
                JComponent label = (JComponent) rcomp.getRenderedLabel();
                templatePanel.addFieldComponent(field);
                templatePanel.lstAbsComponent.add(rcomp);
                pnl.add(label, mergeCons);
                if (fieldCompose.display.width() == -1) {
                    mergeCons.fill = GridBagConstraints.HORIZONTAL;
                } else {
                    mergeCons.fill = GridBagConstraints.NONE;
                }
                pnl.add(field, mergeCons);
            }
        }
    }

    private boolean hasMerge(FieldCompose fieldCompose) {
        String[] merge = fieldCompose.display.mergeFields();
        return (merge != null && merge[0] != null && !merge[0].isEmpty());
    }

    public boolean inMerge(FieldCompose fieldCompose, List<FieldCompose> lstCompose) {
        List<String> lst = getMergeFields(lstCompose);
        return lst.contains(fieldCompose.display.name());
    }

    public String getWebType(FieldCompose field) {
        String type = field.display.type();
        Class cls = field.field.getType();
        if (type.equals("Any")) {
            if (cls.getSimpleName().equalsIgnoreCase("Date")) {
                type = "Calendar";
            } else if (cls.getSimpleName().equalsIgnoreCase("Boolean")) {
                type = "CheckBox";
            } else {
                if (cls.getSimpleName().toUpperCase().startsWith("INT")) {
                    type = "TextInt";
                } else if (cls.getSimpleName().equalsIgnoreCase("Double")) {
                    type = "TextDouble";
                } else {
                    type = "Text";
                }
            }
        }
        return type;
    }
    
    private List<String> getMergeFields(List<FieldCompose> lstCompose) {
        List<String> lst = new ArrayList<String>();
        for (FieldCompose fieldCompose : lstCompose) {
            Display display = fieldCompose.display;
            String[] mergeFields = display.mergeFields();
            for (String string : mergeFields) {
                lst.add(string);
            }
        }
        return lst;
    }

    public DisplayGroup[] getGroups() {
        DisplayGroups groups = (DisplayGroups) templatePanel.getCurrentClass().getAnnotation(DisplayGroups.class);
        if (groups != null) {
            return groups.value();
        }
        return null;
    }

    public List<FieldCompose> getAllFields() {
        return getAllFields(templatePanel.getCurrentClass(), templatePanel);
    }

    public static List<FieldCompose> getAllFields(Class cls, ITemplate template) {
        List<FieldCompose> lst = new ArrayList<FieldCompose>();
        Displays displays = (Displays) cls.getAnnotation(Displays.class);
        Display[] dis = displays.value();
        for (Display display : dis) {
            String name = display.name();
            try {
                FieldCompose d = new FieldCompose();
                if (template != null) {
                    d.currentObject = template.getCurrentObject();
                }
                d.field = cls.getField(name);
                d.display = display;
                lst.add(d);
            } catch (Exception e) {
            	util.Log.warning(e.getMessage(),": ",cls.getSimpleName(),":",name);
//                e.printStackTrace();
            }
        }
        return lst;
    }

    public List<FieldCompose> getFields() {
        DisplayGroups groups = (DisplayGroups) templatePanel.getCurrentClass().getAnnotation(DisplayGroups.class);
        List<FieldCompose> lst = new ArrayList<FieldCompose>();
        Displays displays = (Displays) templatePanel.getCurrentClass().getAnnotation(Displays.class);
        Display[] dis = displays.value();
        for (Display display : dis) {
            if (groups != null && inGroup(display, groups)) {
                continue;
            }
            if (templatePanel.isParent() && display.addInfoOnly()) {
                continue;
            }
            try {
                String name = display.name();
                FieldCompose d = new FieldCompose();
                d.currentObject = templatePanel.getCurrentObject();
                d.field = templatePanel.getCurrentClass().getField(name);
                d.display = display;
                lst.add(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lst;
    }

    public List<FieldCompose> getFields(DisplayGroup group) {
        List<FieldCompose> lst = new ArrayList<FieldCompose>();
        Displays displays = (Displays) templatePanel.getCurrentClass().getAnnotation(Displays.class);
        Display[] dis = displays.value();
        for (Display display : dis) {
            if (!inGroup(display, group)) {
                continue;
            }
            try {
                String name = display.name();
                FieldCompose d = new FieldCompose();
                d.currentObject = templatePanel.getCurrentObject();
                d.field = templatePanel.getCurrentClass().getField(name);
                d.display = display;
                lst.add(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lst;
    }

    private boolean inGroup(Display display, DisplayGroups group) {
        DisplayGroup[] grps = group.value();
        for (DisplayGroup displayGroup : grps) {
            if (inGroup(display, displayGroup)) {
                return true;
            }
        }
        return false;
    }

    private boolean inGroup(Display display, DisplayGroup group) {
        String[] strArr = group.fields();
        for (String string : strArr) {
            if (display.name().equals(string)) {
                return true;
            }
        }
        return false;
    }

    public TemplateParserUtil(ITemplate templatePanel) {
        this.templatePanel = templatePanel;
    }

    public static TemplateParserUtil getInstance(ITemplate templatePanel) {
        return new TemplateParserUtil(templatePanel);
    }

    public void bindTable(BindingGroup bindingGroup, List list, JTable tblResult, Class currentClass) {
    	if (tblResult instanceof JTableReadOnly) {
    		((JTableReadOnly) tblResult).beanClass = currentClass;
    		((JTableReadOnly) tblResult).lst = list;
    	}
        UITemplate temp = (UITemplate) currentClass.getAnnotation(UITemplate.class);
        String[] columnSearch = temp.columnSearch();
        bindTable(bindingGroup, list, tblResult, util.BeanUtil.getEntityClass(currentClass), columnSearch);
    }

    private String getColumnLabel(String field) {
        List<FieldCompose> lstFields = getAllFields();
        for (FieldCompose f : lstFields) {
            if (field.equals(f.display.name())) {
                String label = PanelUtil.getLabel(f.field, f.display);
                return getHTML(label);
            }
        }
        return getHTML(PanelUtil.getLabel(field));
    }

    private String getHTML(String label) {
        if (label.contains("\n")) {
            label = label.replace("\n", "<br>");
            return BeanUtil.concat("<html><center>",label,"</center></html>");
        }
        return label;
    }
    
    private static String getColumnLabel(String field, Class cls) {
        List<FieldCompose> lstFields = getAllFields(cls, null);
        for (FieldCompose f : lstFields) {
            if (field.equals(f.display.name())) {
                return PanelUtil.getLabel(f.field, f.display);
            }
        }
        return PanelUtil.getLabel(field);
    }

    public void bindTable(BindingGroup bindingGroup, List list, JTable tblResult, Class currentClass, String... columnSearch) {
        JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, list, tblResult);

        for (String column : columnSearch) {
            JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(ELProperty.create(BeanUtil.concat("${",column,"}")));

            String lbl = getColumnLabel(column);
            if (lbl.contains("<br>")) {
                Dimension dim = new Dimension(100,35);
                tblResult.getTableHeader().setPreferredSize(dim);
            }
            columnBinding.setColumnName(lbl);
            try {
                String simpleName = currentClass.getField(column).getType().getSimpleName();
                if ("Date|String".contains(simpleName)) {
                    columnBinding.setColumnClass(currentClass.getField(column).getType());
                } else {
                    if (simpleName.equals("int")) {
                        simpleName = "Integer";
                    }
                    simpleName = BeanUtil.concat(Character.toUpperCase(simpleName.charAt(0)),simpleName.substring(1));
                    columnBinding.setColumnClass(Class.forName(BeanUtil.concat("java.lang.",simpleName)));
                }
            } catch (Exception e) {
                columnBinding.setColumnClass(String.class);
                Log.out("EXCEPTION ",e.getMessage());
            }
        }
        bindingGroup.addBinding(jTableBinding);
    }

    public static void bindPopTable(BindingGroup bindingGroup, List list, JTable tblResult, Class currentClass, String... columnSearch) {
        JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, list, tblResult);

        for (String column : columnSearch) {
            JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(ELProperty.create(BeanUtil.concat("${",column,"}")));

            String lbl = getColumnLabel(column, currentClass);
            if (lbl.contains("<br>")) {
                Dimension dim = new Dimension(100,35);
                tblResult.getTableHeader().setPreferredSize(dim);
            }
            columnBinding.setColumnName(lbl);
            try {
                String simpleName = currentClass.getField(column).getType().getSimpleName();
                if ("Date|String".contains(simpleName)) {
                    columnBinding.setColumnClass(currentClass.getField(column).getType());
                } else {
                    if (simpleName.equals("int")) {
                        simpleName = "Integer";
                    }
                    simpleName = BeanUtil.concat(Character.toUpperCase(simpleName.charAt(0)),simpleName.substring(1));
                    columnBinding.setColumnClass(Class.forName(BeanUtil.concat("java.lang.",simpleName)));
                }
            } catch (Exception e) {
                columnBinding.setColumnClass(String.class);
                Log.out("EXCEPTION ",e.getMessage());
            }
        }
        bindingGroup.addBinding(jTableBinding);
    }
}
