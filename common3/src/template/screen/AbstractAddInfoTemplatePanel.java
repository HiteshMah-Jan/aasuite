/*
 * AbstractAddInfoTemplatePanel.java
 *
 * Created on August 30, 2008, 4:21 PM
 */

package template.screen;

import component.IRule;
import component.JButtonPallete;
import component.PalleteRuleManager;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BindingGroup;
import rule.BusinessRuleWrapper;
import template.Display;
import template.DisplayGroup;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.AbstractTemplatePanel.FieldCompose;
import template.screen.component.AbstractComponentRenderer;
import util.BeanUtil;

/**
 *
 * @author  Entokwaa
 */
public class AbstractAddInfoTemplatePanel extends javax.swing.JPanel implements ITemplate {
    protected Class currentClass;
    protected Object currentObject;
    public UITemplate template;
    public ParentAddInfo parentInfo;
    public JTable tbl;
    ITemplate panel;
    public BusinessRuleWrapper ruleWrapper;
//    private List<JComponent> lstComponent = new ArrayList<JComponent>();
    private TemplateParserUtil templateParser;

	public List<AbstractChildTemplatePanel> getTabs() {
		return null;
	}

    public BusinessRuleWrapper getRuleWrapper() {
        return ruleWrapper;
    }

    private void setup(ParentAddInfo parentInfo, Object parent, JTable tbl) {
        this.parentInfo = parentInfo;
        this.currentClass = parent.getClass();
        this.currentObject = parent;
        this.tbl = tbl;

        template = (UITemplate) this.currentClass.getAnnotation(UITemplate.class);

        //bind the main form
        int gridCount = template.gridCount();
        if (parentInfo.gridCount()>0) gridCount = parentInfo.gridCount();
        
        pnlMain.removeAll();
        GridBagConstraints cons = new GridBagConstraints();
        if (gridCount>2) {
            cons.gridwidth = gridCount;
            JLabel lbl = new JLabel("");
            Dimension dim = lbl.getPreferredSize();
            dim.height = template.topMargin();
            lbl.setPreferredSize(dim);
            pnlMain.add(lbl, cons);
            cons.gridy++;
        }
        List<FieldCompose> lst = this.getFields();
        int totalGrid = -1;
        for (FieldCompose fieldCompose : lst) {
            AbstractComponentRenderer rcomp = AbstractComponentRenderer.getInstance(panel.getBinding(), fieldCompose, this.tbl, panel); 
            JComponent field = (JComponent) rcomp.getRenderedListeningField();
            JComponent label = (JComponent) rcomp.getRenderedLabel();
            panel.addFieldComponent(field);
            if (totalGrid==-1 || totalGrid >= gridCount) {
                cons.gridy++;
                totalGrid = 0;
                cons.insets = new Insets(2, 2, 0, 0);
            }
            else {
                cons.insets = new Insets(2, 20, 0, 0);
            }
            totalGrid += fieldCompose.display.gridLabelWidth() + fieldCompose.display.gridFieldWidth();
            cons.anchor = GridBagConstraints.NORTHWEST;
            cons.gridwidth = fieldCompose.display.gridLabelWidth();
            if (!fieldCompose.display.noLabel()) {
                pnlMain.add(label, cons);
            }
            cons.gridwidth = fieldCompose.display.gridFieldWidth();
            if (fieldCompose.display.width()==-1) {
                cons.fill = GridBagConstraints.HORIZONTAL;
            }
            else {
                cons.fill = GridBagConstraints.NONE;
            }
            //check if button exist in the field
            if (fieldCompose.display.button().isEmpty()) {
                pnlMain.add(field, cons);
            }
            else {
                JButton btn = new JButtonPallete(fieldCompose.display.button(), BeanUtil.concat("btn",fieldCompose.field.getName()));
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        BusinessRuleWrapper ruleWrapper = panel.getRuleWrapper();
                        PalleteRuleManager.runOnClick((IRule)e.getSource(), ruleWrapper); 
                    }
                });
                panel.addFieldComponent(btn);
                JPanel pnl = new JPanel(new GridBagLayout());
                pnl.add(field);
                pnl.add(btn);
                pnlMain.add(pnl, cons);
            }
        }
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        DisplayGroup[] groups = templateParser.getGroups();
        if (groups!=null && groups.length>0) {
            for (int i=0; i<groups.length; i++) {
                DisplayGroup displayGroup = groups[i];
                if (!displayGroup.addInfoOnly()) continue;
                if (this.parentInfo.hideGroup().contains(BeanUtil.concat(i))) continue;
                JPanel tmp = templateParser.constructGroupPanel(getBinding(), displayGroup);
                c.gridy++;
                this.add(tmp, c);
                if (constants.Constants.panelBackground!=null) {
                    tmp.setBackground(constants.Constants.panelBackground);
                }
            }
        }
    }

    public List<FieldCompose> getFields() {
        List<FieldCompose> lst = new ArrayList<FieldCompose>();
        Displays displays = (Displays) currentClass.getAnnotation(Displays.class);
        Display[] dis = displays.value();
        for (Display display : dis) {
            try {
                if (!includeField(display)) continue;
                String name = display.name();
                FieldCompose d = new FieldCompose();
                d.currentObject = currentObject;
                d.field = currentClass.getField(name);
                d.display = display;
                lst.add(d);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lst;
    }

    private boolean includeField(Display display) {
        String[] fields = parentInfo.displayFields();
        if ((fields==null || fields.length==0) && display.addInfoOnly()) return true;  //all add info will be included
        for (String string : fields) {
            if (display.name().equals(string)) return true;
        }
        return false;
    }
    
    public static AbstractAddInfoTemplatePanel getInstance(ParentAddInfo parentInfo, Object parent, JTable tbl, ITemplate panel) {
        try {
            AbstractAddInfoTemplatePanel tmp = (AbstractAddInfoTemplatePanel) parentInfo.template().newInstance();
            tmp.panel = panel;
            tmp.templateParser = TemplateParserUtil.getInstance(tmp);
            tmp.setup(parentInfo, parent, tbl);
            tmp.ruleWrapper = panel.getRuleWrapper();
            return tmp;
        } catch (Exception ex) {
            Logger.getLogger(AbstractAddInfoTemplatePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /** Creates new form AbstractAddInfoTemplatePanel */
    public AbstractAddInfoTemplatePanel() {
        initComponents();
        if (constants.Constants.panelBackground!=null) {
            setBackground(constants.Constants.panelBackground);
            if (pnlMain!=null) pnlMain.setBackground(constants.Constants.panelBackground);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlMain = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setLayout(new java.awt.GridBagLayout());

        pnlMain.setName("pnlMain"); // NOI18N
        pnlMain.setLayout(new java.awt.GridBagLayout());

        jButton1.setName("jButton1"); // NOI18N
        pnlMain.add(jButton1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(pnlMain, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables

    public void addFieldComponent(JComponent comp) {
        panel.addFieldComponent(comp);
//        lstComponent.add(comp);
    }

    public Object getCurrentObject() {
        return currentObject;
    }

    public Class getCurrentClass() {
        return this.currentClass;
    }

    public UITemplate getUITemplate() {
        return this.template;
    }

    public JTable getTable() {
        return this.tbl;
    }

    public List<JComponent> getFieldComponents() {
        return panel.getFieldComponents();
//        return lstComponent;
    }

    public boolean isParent() {
        return true;
    }

    public BindingGroup getBinding() {
        return panel.getBinding();
    }

    public List getRecordList() {
        return panel.getRecordList();
    }

	public JTabbedPane getTabPane() {
		// TODO Auto-generated method stub
		return null;
	}

}
