/*
 * ReportParamDialog.java
 *
 * Created on February 23, 2009, 8:20 AM
 */

package component;

import common2.Common2View;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.*;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReport;
import util.BeanUtil;
import util.PanelUtil;
import template.UITemplate;

/**
 *
 * @author  Charliemagne Mark
 */
public class ReportParamDialog extends javax.swing.JDialog {

    public static void displayParameters(JasperReport rep, Map parameters) {
        List<JRParameter> lst = new ArrayList<JRParameter>();
        JRParameter[] params = rep.getParameters();
        for (JRParameter p : params) {
            if (p.getName().startsWith("PARAM_")) {
                lst.add(p);
            }
        }
        if (lst.isEmpty()) return;
        ReportParamDialog dlg = null;
        if (Common2View.mainView==null) {
            dlg = new ReportParamDialog(null, true);
        }
        else {
            dlg = new ReportParamDialog(Common2View.mainView.getFrame(), true);
        }
        dlg.parameters = parameters;
        dlg.display(lst);
    }
    
    protected String[] getLinkColumns(String bean) {
        Class cls = PanelUtil.getBeanClass(bean);
        UITemplate temp = (UITemplate) cls.getAnnotation(UITemplate.class);
        return temp.columnSearch();
    }

    List<JComponent> lstComp = new ArrayList<JComponent>();
    Map parameters = new HashMap();
    private void display(List<JRParameter> lst) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(4, 4, 4, 4);
        cons.gridy++;
        pnl.add(new JLabel("                                    "),cons);
        pnl.add(new JLabel("                                    "), cons);
        cons.gridy++;
        pnl.add(new JLabel("                                    "), cons);
        pnl.add(new JLabel("                                    "), cons);
        cons.gridy++;
        pnl.add(new JLabel("                                    "), cons);
        pnl.add(new JLabel("                                    "), cons);
        for (JRParameter p : lst) {
            cons.gridy++;
            String name = null;
            String[] arrP = p.getName().split("_");
            JComponent pal = null;
            if (p.getName().startsWith("PARAM_C_")) {   //model combo
                name = arrP[2];
                String[] cln = new String[arrP.length-3];
                for (int k=3; k<arrP.length; k++) {
                    cln[k] = arrP[k];
                }
                JComboBox f = new JComboBox(cln);
                f.setName(name);
                pal = f;
            }
            else if (p.getName().startsWith("PARAM_P_")) {   //popup search
                name = arrP[2];
                String linkToBean = arrP[3];
                LookupTableFieldPallete f = new LookupTableFieldPallete();
                f.setBean(linkToBean);
                f.setColumns(getLinkColumns(linkToBean));
                f.setName(name);
                pal = f;
            }
            else {
                name = p.getName().substring("PARAM_".length());
                pal = new JTextFieldPallete();
                if (p.getValueClass()==Date.class) {
                    pal = new JCalendarPallete();
                }
                else {
                	((JTextFieldPallete) pal).setText(p.getDescription());
                }
                pal.setName(name);
            }
            try {
            	String val = (String) parameters.get(name);
            	if (val==null) {
                	((JTextFieldPallete) pal).setText(p.getDescription());
            	}
            	else {
                	((JTextFieldPallete) pal).setText(val);
            	}
            }
            catch (Exception e) {
            }

            lstComp.add(pal);
            cons.fill = GridBagConstraints.HORIZONTAL;
            String label = PanelUtil.getLabel(name);
            pnl.add(new JLabel(label), cons);
            pnl.add(pal, cons);
        }
        cons.gridy++;
        cons.gridwidth = 2;
        cons.fill = GridBagConstraints.NONE;
        JButton btn = new JButton("Show Report");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showReport();
            }
        });
        pnl.add(btn, cons);
        cons.gridy++;
        pnl.add(new JLabel("                                    "), cons);
        cons.gridy++;
        pnl.add(new JLabel("                                    "), cons);
        cons.gridy++;
        pnl.add(new JLabel("                                    "), cons);
        this.pack();
        this.setLocation(100, 100);
        this.setVisible(true);
    }
    
    private void showReport() {
        //put all values inside the parameters variable, then hide the dialog
        for (JComponent comp : lstComp) {
            String name = BeanUtil.concat("PARAM_",comp.getName());
            if (comp instanceof JCalendarPallete) {
                Date d = ((JCalendarPallete) comp).getDate();
                this.parameters.put(name, d);
            }
            else {
                String txt = ((IGetText) comp).getText();
                this.parameters.put(name, txt);
            }
        }
        this.setVisible(false);
    }
    /** Creates new form ReportParamDialog */
    public ReportParamDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(common2.Common2App.class).getContext().getResourceMap(ReportParamDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setAlwaysOnTop(true);
        setModal(true);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new java.awt.GridLayout());

        pnl.setName("pnl"); // NOI18N
        pnl.setLayout(new java.awt.GridBagLayout());
        getContentPane().add(pnl);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReportParamDialog dialog = new ReportParamDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnl;
    // End of variables declaration//GEN-END:variables

}
