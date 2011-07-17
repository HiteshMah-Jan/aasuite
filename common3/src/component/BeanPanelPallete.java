/*
 * BeanPanelPallete.java
 *
 * Created on March 12, 2009, 8:23 AM
 */

package component;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JPanel;

import org.jdesktop.beansbinding.ELProperty;

import service.util.AbstractIBean;
import springbean.AAAConfig;
import template.screen.BeanFormDisplayerPage;
import template.screen.TransactionPanel;
import util.PanelUtil;

/**
 *
 * @author  Charliemagne Mark
 */
public class BeanPanelPallete extends javax.swing.JPanel {
    private boolean showForm = true;
    private boolean showCriteria;
    private boolean showResult;
    private boolean showImage = true;
    private boolean showSubRecords;
    private String beanName;
    private AbstractIBean bean;
    public BeanFormDisplayerPage view;
    public static TransactionPanel currentPanel;

    public static JPanel getPanel(String beanName, boolean showButton) {
    	AAAConfig.getInstance();
    	BeanPanelPallete pallete = new BeanPanelPallete();
    	pallete.setBeanName(beanName);
    	pallete.setShowSubRecords(true);
    	JPanel pnl = pallete.view.pnlButton;
    	JPanel newPanel = new JPanel(new BorderLayout());
    	newPanel.add(pallete, BorderLayout.CENTER);
    	newPanel.add(pnl, BorderLayout.SOUTH);
    	
    	currentPanel = pallete.view;
    	return newPanel;
    }
    
    public JPanel getPanel(boolean showButton) {
    	this.setBeanName(beanName);
    	this.setShowSubRecords(true);
    	JPanel pnl = this.view.pnlButton;
    	JPanel newPanel = new JPanel(new BorderLayout());
    	newPanel.add(this, BorderLayout.CENTER);
    	newPanel.add(pnl, BorderLayout.SOUTH);
    	
    	currentPanel = this.view;
    	return newPanel;
    }

    public void setupBean(AbstractIBean b) {
        if (b!=null) {
            b.showCharts = false;
            b.showFile = false;
            b.showImages = showImage;
            b.showSubrecords = showSubRecords;
            view.ruleWrapper.onChangeRecord();
        }
    }

    public boolean isShowImage() {
        return showImage;
    }

    public void setShowImage(boolean showImage) {
        this.showImage = showImage;
        init();
    }

    public void setList(List<AbstractIBean> lst) {
        if (lst!=null) {
            for (AbstractIBean b:lst) {
                setupBean(b);
            }
        }
        if (view!=null && view.list!=null) {
            view.list.clear();
            if (lst!=null) {
                view.list.addAll(lst);
                view.tblResult.updateUI();
                if (lst!=null && !lst.isEmpty()) {
                    view.tblResult.setRowSelectionInterval(0, 0);
                }
            }
        }
    }
    
    public boolean isShowCriteria() {
        return showCriteria;
    }

    public void setShowCriteria(boolean showCriteria) {
        this.showCriteria = showCriteria;
        init();
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
        init();
    }

    public boolean isShowResult() {
        return showResult;
    }

    public void setShowResult(boolean showResult) {
        this.showResult = showResult;
        init();
    }

    public boolean isShowSubRecords() {
        return showSubRecords;
    }

    public void setShowSubRecords(boolean showSubRecords) {
        this.showSubRecords = showSubRecords;
        try {
            if (view!=null) {
            	view.showSubRecords();
            }
        }
        catch(Exception e) {
        }
    }

    @Override
    public void setLayout(LayoutManager mgr) {
//        super.setLayout(mgr);
    }

    public void init() {
        try {
            if (view==null) {
                Class cls = PanelUtil.getBeanClass(beanName);
                AbstractIBean tmp = (AbstractIBean) cls.newInstance();
                view = (BeanFormDisplayerPage) BeanFormDisplayerPage.setupTemplate(tmp.getClass());
                view.mybean = tmp;
            }
            this.removeAll();
            GridLayout layout = new GridLayout(3,1);
            super.setLayout(layout);
            JPanel pnl = (JPanel)view.getMainForm();
            if (showCriteria) {
                this.add(view.pnlCriteria);
            }
            else {
                layout.setRows(2);
            }
            if (showResult) {
                this.add(view.pnlResults);
            }
            else {
                layout.setRows(layout.getRows()-1);
            }
            if (showForm) {
                if (showImage) {
                    this.add(pnl);
                }
                else {
                    this.add(pnl);
                    if (view.pnlImages!=null) {
                        view.pnlImages.setVisible(false);
                    }
                }
            }
            else {
                layout.setRows(layout.getRows()-1);
            }
            this.updateUI();
        } catch (Exception ex) {
        	ex.printStackTrace();
            util.Log.severe("BEAN PANEL EXCEPTION:[",beanName,"]");
        }
    }
    
    public AbstractIBean getBean() {
        if (view!=null) {
            return (AbstractIBean) ELProperty.create("${selectedElement}").getValue(view.tblResult);
        }
        return bean;
    }

    public void setBean(AbstractIBean bean) {
        this.bean = bean;
        setupBean(bean);
        List l = view.list;
        if (l!=null && l.size()>0) {
            l.clear();
        }
        if (bean!=null) {
            l.add(bean);
            view.tblResult.clearSelection();
            view.tblResult.setRowSelectionInterval(0, 0);
            if (showSubRecords) {
            	view.loadSubRecords(bean);
            }
            if (showImage) {
            	if (view.imagePallete!=null) {
                	view.imagePallete.setBean(bean);
            	}
            }
        }
        else {
            view.tblResult.clearSelection();
        }
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
        init();
    }
    
    public boolean isEmpty() {
        if (view==null || view.list==null || view.list.isEmpty()) return true;
        return false;
    }
    /** Creates new form BeanPanelPallete */
    public BeanPanelPallete() {
        try {
            AAAConfig.getInstance();
        }
        catch (Exception e) {
        }
        initComponents();
        super.setLayout(new GridLayout());
    }

    public BeanPanelPallete(boolean showImage) {
        try {
            AAAConfig.getInstance();
        }
        catch (Exception e) {
        }
        this.showImage = showImage;
        initComponents();
        super.setLayout(new GridLayout());
    }

    public BeanPanelPallete(String bean, Object host) {
        try {
            String[] arr = {host.toString()};
            AAAConfig.getInstance(arr).setBootStrap("bean.Student");
        }
        catch (Exception e) {
        }
        initComponents();
        super.setLayout(new GridLayout());
        setBeanName(bean);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setName("Form"); // NOI18N
        setLayout(new java.awt.GridLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
