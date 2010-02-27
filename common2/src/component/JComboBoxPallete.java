/*
 * JComboBoxPallete.java
 *
 * Created on Aug 17, 2007, 4:56:48 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import service.util.AbstractIBean;
import template.screen.AbstractTemplatePanel.FieldCompose;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class JComboBoxPallete extends JComboBox implements IGetText, IHelp, IRule {
    private boolean mandatory;
    private String code;
    private List list;
    private Query query;
    private String field;
    private boolean textComplete;
    private boolean numericData;

    public boolean isNumericData() {
        return numericData;
    }

    public void setNumericData(boolean numericData) {
        this.numericData = numericData;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void refresh() {
        if (query==null || list==null) {
            Logger.getLogger("global").log(Level.INFO, "query and list fields not set, please set this at design time");
            return;
        }
        List lst = query.getResultList();
        PanelUtil.addEmpty(lst);
        PanelUtil.setListData(list, lst);
    }
    
    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List getList() {
        return list;
    }

    boolean added;
    
    @SuppressWarnings(value = "unchecked")
    public void setList(List list) {
        this.list = list;            
    }

    private String errorText;

    public boolean withError() {
        if (mandatory && PanelUtil.isEmpty(this)) {
            errorText = "Mandatory Field";
        }
        //check other things here
        return !PanelUtil.isEmpty(errorText);
    }

    public String getErrorText() {
        return errorText;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isEmpty() {
        return PanelUtil.isEmpty(this);
    }

    private JComboBox getMe() {
        return this;
    }

    public JComboBoxPallete() {
//        this.setEditable(true);
        this.setTextComplete(true);
        editor.getEditorComponent().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField cbo = (JTextField) e.getSource();
                cbo.selectAll();
            }
        });
    }

    public int getIntCode() {
        return (int) this.getDoubleCode();
    }

    public double getDoubleCode() {
        try {
            return Double.parseDouble(this.getCode());
        } catch (Exception e) {
            return 0;
        }
    }

    public String getCode() {
        try {
            if (list==null || list.isEmpty()) {
                code = PanelUtil.getGenericCode(getSelectedItem().toString());
            }
            else {
                AbstractIBean bean = this.getSelectedBean();
                if (bean!=null && bean.keyVal()!=null) {
                    code = bean.keyVal().toString();
                }
                else {
                    code = PanelUtil.getGenericCode(getSelectedItem().toString());
                }
            }
        }
        catch (Exception e) {
            if (getSelectedItem()!=null) {
                code = PanelUtil.getGenericCode(getSelectedItem().toString());
            }
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        if (code==null || code.trim().isEmpty()) {
            if (this.isEditable()) {
                JTextField f = (JTextField) this.getEditor().getEditorComponent();
                f.setText("");
            }
            if (this.getItemCount()>0) this.setSelectedIndex(0);
            return;
        }
        int itemCount = this.getItemCount();
        for (int i=0; i<itemCount; i++) {
            Object obj = this.getItemAt(i);
            String txt = null;
            if (list!=null && !list.isEmpty()) {
                Object objTmp = list.get(i);
                if (objTmp instanceof AbstractIBean) {
                    AbstractIBean bean = (AbstractIBean) objTmp;
                    if (bean!=null && bean.keyVal()!=null) txt = bean.keyVal().toString();
                    else txt = "";
                }
                else {
                    txt = PanelUtil.getGenericCode(obj.toString());
                }
            }
            else {
                txt = PanelUtil.getGenericCode(obj.toString());
            }
            if (code.equalsIgnoreCase(txt)) {
                this.setSelectedIndex(i);
                break;
            }
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runRuleManager();
            }
        });
    }

    private void runRuleManager() {
        PalleteRuleManager.useRule(this);
    }
    
    @Override
    public String getText() {
        return getCode();
    }

    @Override
    public void setText(String text) {
        setCode(text);
    }

    String helpName;

    public String getHelpName() {
        return helpName;
    }

    public void setHelpName(String helpName) {
        this.helpName = helpName;
    }

    public AbstractIBean getSelectedBean() {
        if (list == null) {
            System.out.println("LIST not defined at design time");
            return null;
        }
        if (this.getSelectedIndex()<0) return null;
        return (AbstractIBean) list.get(this.getSelectedIndex());
    }

    @Override
    public ComboBoxEditor getEditor() {
        if (isEditable && super.getEditor()!=null) {
            JTextField f = (JTextField) super.getEditor().getEditorComponent();
            if (f!=null) f.setColumns(3);
        }
        return super.getEditor();
    }
    
    public int getIntText() {
        return (int) this.getDoubleText();
    }

    public double getDoubleText() {
        try {
            return Double.parseDouble(this.getCode());
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isTextComplete() {
        return textComplete;
    }

    public void setTextComplete(boolean textComplete) {
        this.textComplete = textComplete;
//        if (textComplete) {
//            AutoCompleteDecorator.decorate(this);
//        }
    }
    
    private String allowedUserId;
    private String allowedGroup;
    private String allowedDuty;

    private String onClickRule;
    private String onValueChangeRule;
    private String onLostFocusRule;

    public String getAllowedDuty() {
        return allowedDuty;
    }

    public void setAllowedDuty(String allowedDuty) {
        this.allowedDuty = allowedDuty;
    }

    public String getAllowedGroup() {
        return allowedGroup;
    }

    public void setAllowedGroup(String allowedGroup) {
        this.allowedGroup = allowedGroup;
    }

    public String getAllowedUserId() {
        return allowedUserId;
    }

    public void setAllowedUserId(String allowedUserId) {
        this.allowedUserId = allowedUserId;
    }

    public String getRuleForAllowed() {
        return PalleteRuleManager.getRuleForAllowed(this);
    }
    
    public String getOnClickRule() {
        return onClickRule;
    }

    public void setOnClickRule(String onClickRule) {
        this.onClickRule = onClickRule;
    }

    public String getOnLostFocusRule() {
        return onLostFocusRule;
    }

    public void setOnLostFocusRule(String onLostFocusRule) {
        this.onLostFocusRule = onLostFocusRule;
    }

    public String getOnValueChangeRule() {
        return onValueChangeRule;
    }

    public void setOnValueChangeRule(String onValueChangeRule) {
        this.onValueChangeRule = onValueChangeRule;
    }

    FieldCompose fieldCompose;
    public FieldCompose getFieldCompose() {
        return fieldCompose;
    }

    public void setFieldCompose(FieldCompose field) {
        this.fieldCompose = field;
    }

    public Object getValue() {
        return getText();
    }
}
