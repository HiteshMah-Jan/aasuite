/*
 * AbstractTabPanel.java
 * 
 * Created on Mar 5, 2008, 5:19:03 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import common2.Common2View;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import template.TemplateReader;
import template.screen.AbstractTemplatePanel;
import template.screen.TransactionPanel;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class AbstractTabPanel extends JTabbedPane {
    public AbstractTabPanel() {
        if (constants.Constants.font!=null) setFont(constants.Constants.font);
    }

    private String[] tabs;
    List<ITransactionPanel> panels = new ArrayList<ITransactionPanel>();
    TransactionPanel transactionPanel;

    public void setTransactionPanel(TransactionPanel transactionPanel) {
        this.transactionPanel = transactionPanel;
    }
    
    public String[] getTabs() {
        return tabs;
    }

    public void setTabs(String[] tabs) {
        this.tabs = tabs;
    }
    
    public void initialize(TransactionPanel transactionPanel) {
        setTransactionPanel(transactionPanel);
        for (String string : tabs) {
            try {
                if (string.endsWith("Form")) {
                    TransactionPanel transPanel = (TransactionPanel) PanelUtil.getPanel(string, string);
                    panels.add(transPanel);
                    add(transPanel.getTitle(), transPanel);
                }
                else {
//                	System.out.println("BEAN="+string);
                    Class bean = PanelUtil.getBeanClass(string);
                    AbstractTemplatePanel pnl = TemplateReader.getTemplateForTab(bean);
                    TransactionPanel tmp = (TransactionPanel) pnl.getMainForm();
                    panels.add(tmp);
                    add(pnl.getSimpleTitle(), tmp);
                    pnl.getMainSearch();
                    pnl.getMainSearchResult();

                    if (pnl.list.size()==0) {
                        pnl.newRecord();
                    }
                    pnl.firstRecord();
                    pnl.ruleWrapper.onLoad();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                changeTab();
            }
        });
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.currentThread().sleep(500);
                    changeTab();
                } catch (InterruptedException ex) {
                    Logger.getLogger(AbstractTabPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t.start();
    }

    private void changeTab() {
        try {
            int index = this.getSelectedIndex();
            ITransactionPanel template = panels.get(index);
            if (template instanceof AbstractTemplatePanel) {
                AbstractTemplatePanel tmp = (AbstractTemplatePanel) template;
                Common2View.showSearch(tmp);
            }
        }
        catch (Exception e) {
        }
    }
    
    public Object getBean() {
        ITransactionPanel p = getCurrentPanel();
        if (p!=null) return p.getBean();
        return null;
    }
    
    public TransactionPanel getCurrentPanel() {
        JComponent comp = (JComponent) this.getSelectedComponent();
        if (comp instanceof TransactionPanel) {
            return (TransactionPanel) comp;
        }
        return null;
    }
    
    public void deleteRecord() {
        ITransactionPanel p = getCurrentPanel();
        if (p!=null) p.deleteRecord();
    }

    public void first() {
        ITransactionPanel p = getCurrentPanel();
        if (p!=null) p.firstRecord();
    }

    public void last() {
        ITransactionPanel p = getCurrentPanel();
        if (p!=null) p.lastRecord();
    }

    public void newRecord() {
        ITransactionPanel p = getCurrentPanel();
        if (p!=null) p.newRecord();
    }

    public void next() {
        ITransactionPanel p = getCurrentPanel();
        if (p!=null) p.nextRecord();
    }

    public void prev() {
        ITransactionPanel p = getCurrentPanel();
        if (p!=null) p.prevRecord();
    }

    public void showReport(JComponent comp) {
        ITransactionPanel p = getCurrentPanel();
        if (p!=null) p.showReport(comp);
    }

    public void refreshRecord() {
        ITransactionPanel p = getCurrentPanel();
        if (p!=null) p.refreshRecords();
    }

    public void saveRecord() {
        ITransactionPanel p = getCurrentPanel();
        if (p!=null) p.saveRecord();
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public Class getBeanClass() {
        ITransactionPanel p = getCurrentPanel();
        if (p!=null) return p.getBean().getClass();
        return null;
    }
}
