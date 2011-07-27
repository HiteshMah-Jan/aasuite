/*
 * Template3.java
 *
 * Created on September 7, 2008, 1:52 PM
 */

package template.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import constants.UserInfo;

import service.util.AbstractIBean;
import template.report.AbstractReportTemplate;
import template.screen.component.AbstractComponentRenderer;

/**
 *
 * @author  Entokwaa
 */
public class TemplateTabPage extends AbstractTemplatePanel {
	@Override
	public void hideSearchResult() {
		pnlSearchPanel.setVisible(false);
	}

	boolean reportButtonCalled = false;
    protected int useIndex = 1;
    
    @Override
    public void newRecord() {
        super.newRecord();
        myTab.setSelectedIndex(useIndex);
    }

    @Override
    public Object getMainForm() {
        super.getMainForm();
        pnlTop.removeAll();
        pnlTab.removeAll();
        pnlCriteria3.removeAll();
        pnlResult3.removeAll();
        pnlButton.removeAll();
        
        pnlTop.add(super.pnlTop);
        pnlTab.add(super.tabChildren);
        GroupPanel pnlCrit = GroupPanel.construct("Criteria", (JPanel)super.getMainSearch(), tblResult);
        pnlCriteria3.add(pnlCrit);
        pnlResult3.add((JComponent)super.getMainSearchResult());
        pnlButton.add(super.pnlButton);
        if (!reportButtonCalled) {
            reportButtonCalled = true;
            putReports();
        }
        if (tabs!=null) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    for (AbstractChildTemplatePanel tab : tabs) {
                        Dimension dim = tab.scrollTable.getPreferredSize();
                        dim.height = 0;
                        tab.scrollTable.setPreferredSize(dim);
//                        tab.tblRecord.setPreferredSize(dim);
                        tab.scrollTable.updateUI();
                    }
                }
            });
        }
        return this;
    }

    JButton btnAll = new JButton("All Records");
    JButton btnAllDisplayed = new JButton("All Displayed");
    
    private void putReports() {
        pnlReportButtons.removeAll();
        Class cls = getBean().getClass();
        //get if there are other reports to show
        btnAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TransactionPanel pnl = (TransactionPanel) TransactionPanel.getCurrentPanel();
                if (pnl.parentPanel==null || pnl.parentPanel.list==null) {
                    AbstractReportTemplate.getInstance(currentClass).showReportToPanel(pnlReportViewer, (AbstractIBean) getCurrentObject());
                }
                else {
                    AbstractReportTemplate.getInstance(currentClass).showReportToPanel(pnl.parentPanel.list, pnlReportViewer);
                }
            }
        });
        pnlReportButtons.add(btnAll);
        btnAllDisplayed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractReportTemplate.getInstance(currentClass).showReportToPanel(pnlReportViewer, (AbstractIBean) getCurrentObject());
            }
        });
//        pnlReportButtons.add(btnAllDisplayed);
        //for single user, no need to display other reports
        
        template.Reports reports = (template.Reports) cls.getAnnotation(template.Reports.class);
        if (reports!=null && reports.value()!=null && reports.value().length>0) {
            template.Report[] reps = reports.value();
            for (final template.Report report : reps) {
                JButton btn = new JButton(report.reportTitle());
                btn.setName(report.reportFile());
                btn.setToolTipText(report.reportFile());
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton btn = (JButton) e.getSource();
                        AbstractReportTemplate.getInstance(report).showReportToPanel(pnlReportViewer, (AbstractIBean) getCurrentObject());
                    }
                });
                pnlReportButtons.add(btn);
                if (!UserInfo.hasDuty(report.duties())) {
                	btn.setVisible(false);
                }
            }
        }
        
        pnlReportButtons.updateUI();
    }
    
    @Override
    public Object getMainSearch() {
        return new JPanel();
    }

    @Override
    public Object getMainSearchResult() {
        return new JPanel();
    }

    /** Creates new form Template3 */
    public TemplateTabPage() {
        initComponents();
        if (constants.Constants.panelBackground!=null) {
            setBackground(constants.Constants.panelBackground);
            if (pnlButton!=null) pnlButton.setBackground(constants.Constants.panelBackground);
            if (pnlCriteria!=null) pnlCriteria.setBackground(constants.Constants.panelBackground);
            if (pnlCriteria3!=null) pnlCriteria3.setBackground(constants.Constants.panelBackground);
            if (pnlMainForm!=null) pnlMainForm.setBackground(constants.Constants.panelBackground);
            if (pnlMainTab!=null) pnlMainTab.setBackground(constants.Constants.panelBackground);
            if (pnlReportButtons!=null) pnlReportButtons.setBackground(constants.Constants.panelBackground);
            if (pnlReportViewer!=null) pnlReportViewer.setBackground(constants.Constants.panelBackground);
            if (pnlReportsPanel!=null) pnlReportsPanel.setBackground(constants.Constants.panelBackground);
            if (pnlResult3!=null) pnlResult3.setBackground(constants.Constants.panelBackground);
            if (pnlResults!=null) pnlResults.setBackground(constants.Constants.panelBackground);
            if (pnlSearchPanel!=null) pnlSearchPanel.setBackground(constants.Constants.panelBackground);
            if (pnlTab!=null) pnlTab.setBackground(constants.Constants.panelBackground);
            if (pnlTop!=null) pnlTop.setBackground(constants.Constants.panelBackground);
            if (pnlTopWithTab!=null) pnlTopWithTab.setBackground(constants.Constants.panelBackground);
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

        tabChildren = new javax.swing.JTabbedPane();
        myTab = new javax.swing.JTabbedPane();
        pnlSearchPanel = new javax.swing.JPanel();
        pnlCriteria3 = new javax.swing.JPanel();
        pnlResult3 = new javax.swing.JPanel();
        pnlMainScreen = new javax.swing.JPanel();
        pnlTop = new javax.swing.JPanel() {
        	public void paint(Graphics g) {
        		super.paint(g);
        		g.setColor(Color.LIGHT_GRAY);
        		g.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        		g.drawString(getWatermark(), 10, 35);
        	}
        };
        pnlMainTab = new javax.swing.JPanel();
        pnlButton = new javax.swing.JPanel();
        pnlTab = new javax.swing.JPanel();
        pnlReportsPanel = new javax.swing.JPanel();
        pnlReportButtons = new javax.swing.JPanel();
        pnlReportViewer = new javax.swing.JPanel();

        tabChildren.setName("tabChildren"); // NOI18N
        tabChildren.setPreferredSize(new java.awt.Dimension(500, 35));

        setLayout(new java.awt.BorderLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(common2.Common2App.class).getContext().getResourceMap(TemplateTabPage.class);
        myTab.setName("myTab"); // NOI18N

        pnlSearchPanel.setName("pnlSearchPanel"); // NOI18N
        pnlSearchPanel.setLayout(new java.awt.BorderLayout());

        pnlCriteria3.setName("pnlCriteria3"); // NOI18N
        pnlCriteria3.setLayout(new java.awt.GridLayout(1, 0));
        pnlSearchPanel.add(pnlCriteria3, java.awt.BorderLayout.NORTH);

        pnlResult3.setName("pnlResult3"); // NOI18N
        pnlResult3.setLayout(new java.awt.GridLayout(1, 0));
        pnlSearchPanel.add(pnlResult3, java.awt.BorderLayout.CENTER);

        myTab.addTab(resourceMap.getString("pnlSearchPanel.TabConstraints.tabTitle"), pnlSearchPanel); // NOI18N

        pnlMainScreen.setName("pnlMainScreen"); // NOI18N
        pnlMainScreen.setLayout(new java.awt.BorderLayout());

        pnlTop.setName("pnlTop"); // NOI18N
        pnlTop.setLayout(new java.awt.GridLayout(1, 0));
        pnlMainScreen.add(pnlTop, java.awt.BorderLayout.NORTH);

        pnlMainTab.setName("pnlMainTab"); // NOI18N
        pnlMainTab.setLayout(new java.awt.BorderLayout());

        pnlButton.setName("pnlButton"); // NOI18N
        pnlMainTab.add(pnlButton, java.awt.BorderLayout.SOUTH);

        pnlTab.setName("pnlTab"); // NOI18N
        pnlTab.setLayout(new java.awt.GridLayout(1, 0));
        pnlMainTab.add(pnlTab, java.awt.BorderLayout.CENTER);

        pnlMainScreen.add(pnlMainTab, java.awt.BorderLayout.CENTER);

        myTab.addTab(resourceMap.getString("pnlMainScreen.TabConstraints.tabTitle"), pnlMainScreen); // NOI18N

        pnlReportsPanel.setName("pnlReportsPanel"); // NOI18N
        pnlReportsPanel.setLayout(new java.awt.BorderLayout());

        pnlReportButtons.setName("pnlReportButtons"); // NOI18N
        pnlReportsPanel.add(pnlReportButtons, java.awt.BorderLayout.NORTH);

        pnlReportViewer.setName("pnlReportViewer"); // NOI18N
        pnlReportViewer.setLayout(new java.awt.GridLayout(1, 0));
        pnlReportsPanel.add(pnlReportViewer, java.awt.BorderLayout.CENTER);

        myTab.addTab(resourceMap.getString("pnlReportsPanel.TabConstraints.tabTitle"), pnlReportsPanel); // NOI18N

        add(myTab, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JTabbedPane myTab;
    public javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlCriteria3;
    public javax.swing.JPanel pnlMainScreen;
    protected javax.swing.JPanel pnlMainTab;
    private javax.swing.JPanel pnlReportButtons;
    private javax.swing.JPanel pnlReportViewer;
    protected javax.swing.JPanel pnlReportsPanel;
    private javax.swing.JPanel pnlResult3;
    protected javax.swing.JPanel pnlSearchPanel;
    protected javax.swing.JPanel pnlTab;
    public javax.swing.JPanel pnlTop;
    public javax.swing.JTabbedPane tabChildren;
    // End of variables declaration//GEN-END:variables

    protected JTabbedPane getSuperTabChildren() {
        return super.tabChildren;
    }
    
    public void loadSubRecords(AbstractIBean bean) {
    	if (bean==null) return; 
    	bean.showSubrecords = true;
        currentObject.extractServerChildrensChartsImagesAndFiles();
        List<AbstractChildTemplatePanel> panels = childPanels;
        for (AbstractChildTemplatePanel panel : panels) {
            panel.refreshRecords(bean);
        }
    }
    
    private String getWatermark() {
    	return ((AbstractIBean)this.getBean()).watermark();
    }
}
