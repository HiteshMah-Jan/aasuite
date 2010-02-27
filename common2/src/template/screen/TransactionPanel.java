/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template.screen;

import bean.admin.BookMark;
import common2.Common2View;
import component.AbstractRobotTester;
import component.BeanPanelPallete;
import component.ITransactionPanel;
import component.listener.CrudEvent;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import org.jdesktop.beansbinding.ELProperty;
import service.util.AbstractIBean;
import service.util.IBean;
import template.ChildRecord;
import template.Report;
import template.Reports;
import template.TemplateReader;
import template.UITemplate;
import template.report.AbstractReportTemplate;
import test.TemplateViewer;
import util.BeanUtil;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class TransactionPanel extends JPanel implements ITransactionPanel {
    public static ITransactionPanel getCurrentPanel() {
    	ITransactionPanel tran = Common2View.getTransactionPanel();
    	if (tran==null) {
//    		get from bean panel
    		tran = BeanPanelPallete.currentPanel;
    	}
    	return tran;
    }
    
    public Object parentObj;
    public final static TransactionPanel dummy = new TransactionPanel() {

        @Override
        public void deleteRecord() {
            System.out.println("DUMMY");
        }

        @Override
        public void newRecord() {
            System.out.println("DUMMY");
        }

        @Override
        public void nextRecord() {
            System.out.println("DUMMY");
        }

        @Override
        public void prevRecord() {
            System.out.println("DUMMY");
        }

        @Override
        public void saveRecord() {
            System.out.println("DUMMY");
        }
    };
    public AbstractTemplatePanel parentPanel;
    public List<AbstractChildTemplatePanel> childPanels = new ArrayList<AbstractChildTemplatePanel>();

    public TransactionPanel getActivePanel() {
        return null;
    }
    
    public void resetRules() {
        String simpleName = Common2View.getTransactionPanel().getBean().getClass().getSimpleName();
        TemplateReader.reset();
        Common2View.showBeanPanel(simpleName);
    }

    public void runRobot() {
        parentPanel.ruleWrapper.testRobot(AbstractRobotTester.getInstance());
    }

    public String getTitle() {
        try {
            if (parentPanel != null && parentPanel.template != null && parentPanel.template.title() != null && !parentPanel.template.title().isEmpty()) {
                return parentPanel.template.title();
            }
            return getBean().getClass().getSimpleName();
        }
        catch (Exception e) {
            return "";
        }
    }

    @Override
    public String getName() {
        String name = getClass().getSimpleName();
        return PanelUtil.getLabel(name);
    }

    public Object getBean() {
        return parentPanel.currentObject;
    }

    public String getHelpName() {
        String help = parentPanel.template.helpName();
        if (help==null || help.isEmpty()) {
            help = parentPanel.getBean().getClass().getSimpleName();
        }
        System.out.println("HELP NAME = "+help);
        return help;
    }

    public void newRecord() {
        if (!parentPanel.template.canNew()) {
            PanelUtil.showErrorMessageToScreen("Cannot add new record.");
            return;
        }
        try {
            AbstractIBean obj = (AbstractIBean) parentPanel.currentClass.newInstance();
            parentPanel.list.clear();
            parentPanel.searchNow(0);
            parentPanel.list.add(0, obj);
            firstRecord();
            parentPanel.ruleWrapper.onNewRecord();
            parentPanel.currentObject = obj;
            scroll(parentPanel.tblResult);
        } catch (Exception ex) {
            Logger.getLogger(TransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newCopyRecord() {
        if (!parentPanel.template.canNew()) {
            PanelUtil.showErrorMessageToScreen("Cannot add new record.");
            return;
        }
        try {
            AbstractIBean obj = (AbstractIBean) parentPanel.currentClass.newInstance();
            BeanUtil.copyBean((IBean) parentPanel.currentObject, (IBean) obj);
            BeanUtil.removeBeanId((AbstractIBean) obj);
            parentPanel.list.clear();
            parentPanel.searchNow(0);
            parentPanel.list.add(0, obj);
            firstRecord();
            parentPanel.currentObject = obj;
        } catch (Exception ex) {
            Logger.getLogger(TransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveRecord() {
        if (!parentPanel.template.canSave()) {
            PanelUtil.showErrorMessageToScreen("Cannot save or update record.");
            return;
        }
        if (parentPanel.tblResult.getSelectedRow() >= 0) {
            if (!parentPanel.ruleWrapper.mandatoryOk()) return;
            if (!parentPanel.ruleWrapper.lengthOk()) return;
            Object obj = ELProperty.create("${selectedElement}").getValue(parentPanel.tblResult);
            if (obj != null && obj instanceof AbstractIBean) {
                AbstractIBean bean = (AbstractIBean) obj;
                if (parentPanel.ruleWrapper.beforeSave(bean)) {
                    if (!bean.isCanSave()) {
                        PanelUtil.showError(this, bean.getPromptOnSave());
                        bean.setCanSave(true);
                        bean.setPromptOnSave(null);
                        return;
                    }
                    if (bean.isCanSave() && bean.getPromptOnSave()!=null && !bean.getPromptOnSave().isEmpty()) {
                        boolean b = PanelUtil.showPrompt(this, bean.getPromptOnSave());
                        bean.setCanSave(true);
                        bean.setPromptOnSave(null);
                        if (!b) return;
                    }
                    parentPanel.setAllValues(bean); 
                    bean.save();
                    //saving images
                    if (parentPanel.imagePallete!=null) {
                        parentPanel.imagePallete.crudAction(new CrudEvent(bean, CrudEvent.SAVE)); 
                    }
                    if (parentPanel.filePallete!=null) {
                        parentPanel.filePallete.crudAction(new CrudEvent(bean, CrudEvent.SAVE)); 
                    }
                    
                    //cascase saving child records
                    if (childPanels != null) {
                        for (AbstractChildTemplatePanel panels : childPanels) {
                            if (panels.childAnnotation.canSave()) panels.saveAll();
                        }
                    }
                    parentPanel.ruleWrapper.afterSave(bean);
                }
            }
            parentPanel.tblResult.updateUI();
            redisplayRecord();
        }
    }

//    public boolean wasChanged() {
//        if (parentPanel.tblResult.getSelectedRow() >= 0) {
//            Object obj = ELProperty.create("${selectedElement}").getValue(parentPanel.tblResult);
//            if (obj != null && obj instanceof AbstractIBean) {
//                AbstractIBean bean = (AbstractIBean) obj;
//                if (childPanels != null) {
//                    for (AbstractChildTemplatePanel panels : childPanels) {
//                        panels.saveAll();
//                    }
//                }
//            }
//        }
//    }
    
    public void deleteRecord() {
        if (!parentPanel.template.canDelete()) {
            PanelUtil.showErrorMessageToScreen("Cannot delete record.");
            return;
        }
        JTable tblRecord = parentPanel.tblResult;
        int row = tblRecord.getSelectedRow();
        if (row >= 0) {
            if (parentPanel.template.alertOnDelete()) {
                if (!PanelUtil.showPrompt(this, "Please click ok to delete the record.")) {
                    return;
                }
            }
            Object obj = ELProperty.create("${selectedElement}").getValue(parentPanel.tblResult);
            AbstractIBean bean = (AbstractIBean) obj;
            if (!bean.isCanSave()) {
                PanelUtil.showError(this, bean.getPromptOnSave());
                return;
            }
            if (bean.isCanSave() && bean.getPromptOnSave()!=null && !bean.getPromptOnSave().isEmpty()) {
                boolean b = PanelUtil.showPrompt(this, bean.getPromptOnSave());
                if (!b) return;
            }
            if (parentPanel.ruleWrapper.beforeDelete(bean)) {
                bean.delete();
                try {
                    parentPanel.list.remove(bean);
                } catch (Exception e) {
                }
                //check all cascade delete
                if (parentPanel.childRecordsAnnotation != null) {
                    ChildRecord[] value = parentPanel.childRecordsAnnotation.value();
                    if (value != null) {
                        for (ChildRecord childRecord : value) {
                            if (childRecord.cascadeDelete()) {
                                if (childRecord.cascadeDeleteSql().isEmpty()) {
                                    Logger.getLogger("global").log(Level.WARNING, "No cascade delete sql found.");
                                } else {
                                    bean.runSQL(childRecord.cascadeDeleteSql());
                                }
                            }
                        }
                    }
                }
                if (tblRecord.getRowCount() > 0) {
                    if (tblRecord.getRowCount() > row) {
                        tblRecord.setRowSelectionInterval(row, row);
                    } else {
                        tblRecord.setRowSelectionInterval(0, 0);
                    }
                }
                parentPanel.ruleWrapper.afterDelete(bean);
            }
        }
        scroll(tblRecord);
    }

    public void prevRecord() {
        JTable tblRecord = parentPanel.tblResult;
        if (tblRecord.getRowCount() > 0) {
            int row = tblRecord.getSelectedRow();
            if (row <= 0) {
                tblRecord.setRowSelectionInterval(0, 0);
            } else {
                if (tblRecord.getRowCount() > row) {
                    tblRecord.setRowSelectionInterval(row - 1, row - 1);
                }
            }
        }
        scroll(tblRecord);
    }

    public void nextRecord() {
        JTable tblRecord = parentPanel.tblResult;
        if (tblRecord.getRowCount() > 0) {
            int row = tblRecord.getSelectedRow();
            if (row < 0) {
                tblRecord.setRowSelectionInterval(0, 0);
            } else {
                if (tblRecord.getRowCount() > row + 1) {
                    tblRecord.setRowSelectionInterval(row + 1, row + 1);
                }
            }
        }
        scroll(tblRecord);
    }

    public void firstRecord() {
        if (parentPanel!=null && parentPanel.tblResult!=null) {
            JTable tblRecord = parentPanel.tblResult;
            if (tblRecord.getSelectedRowCount()==0 && tblRecord.getRowCount()>0) {
                tblRecord.setRowSelectionInterval(0, 0);
            }
            scroll(tblRecord);
        }
    }

    public void lastRecord() {
        JTable tblRecord = parentPanel.tblResult;
        if (tblRecord.getRowCount() > 0) {
            int lastRow = tblRecord.getRowCount() - 1;
            tblRecord.setRowSelectionInterval(lastRow, lastRow);
        }
        scroll(tblRecord);
    }

    public void refreshRecords() {
    	if (parentPanel!=null) parentPanel.refreshCombo();
    	if (parentPanel!=null) parentPanel.searchNow(0);
        firstRecord();
        if (parentPanel!=null) scroll(parentPanel.tblResult);
    }

    public void searchMoreRecords() {
        int last = parentPanel.list.size()-1;
        if (parentPanel!=null) parentPanel.searchMoreRecords();
        if (parentPanel!=null) parentPanel.tblResult.setRowSelectionInterval(last, last);
        if (parentPanel!=null) scroll(parentPanel.tblResult);
    }

    public void redisplayRecord() {
        int row = parentPanel.tblResult.getSelectedRow();
        if (parentPanel.tblResult.getRowCount()<=1) {
            refreshRecords();
            return;
        }
        AbstractIBean bean = (AbstractIBean) getBean();
        if (bean!=null) {
            bean.clearCache();
        }
        if (row==0) {
            nextRecord();
            prevRecord();
        }
        else {
            prevRecord();
            nextRecord();
        }
        scroll(parentPanel.tblResult);
    }

    JPopupMenu menu;
    
    public void showReport(JComponent comp) {
        Class cls = parentPanel.currentClass;
        //get if there are other reports to show
        Reports reports = (Reports) cls.getAnnotation(Reports.class);
        if (reports==null || reports.value()==null || reports.value().length==0) {
            menu = new JPopupMenu();
            menu.add(new mnu(cls, menu, true));
            menu.add(new mnu(cls, menu, false));
            menu.show(comp, 10, 10);
        }
        else {
            menu = new JPopupMenu();
            menu.add(new mnu(cls, menu, true));
            menu.add(new mnu(cls, menu, false));
            
            Report[] reps = reports.value();
            for (Report rep:reps) {
                menu.add(new mnu(rep, menu));
            }
            menu.show(comp, 10, 10);
        }
    }
    
    static class mnu extends JMenuItem implements ActionListener {
        Report rep;
        Class bean;
        JPopupMenu menu;
        boolean allDisplay;
        
        mnu(Class bean, JPopupMenu menu, boolean allDisplay) {
            this.allDisplay = allDisplay;
            this.menu = menu;
            this.bean = bean;
            UITemplate template = (UITemplate) bean.getAnnotation(UITemplate.class);
            if (allDisplay) {
                this.setText(template.reportTitle().isEmpty()?"All Records":template.reportTitle());
            }
            else {
                this.setText(template.reportTitle().isEmpty()?"All Displayed Records Only":template.reportTitle());
            }
            this.addActionListener(this);
        }
        mnu(Report rep, JPopupMenu menu) {
            super(rep.reportTitle());
            this.menu = menu;
            this.rep = rep;
            this.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            if (rep!=null) {
                AbstractReportTemplate.getInstance(rep).showReport();
                menu.setVisible(false);
            }
            else {
                if (allDisplay) {
                    TransactionPanel pnl = (TransactionPanel) TransactionPanel.getCurrentPanel();
                    AbstractReportTemplate.getInstance(bean).showReport(pnl.parentPanel.list);
                }
                else {
                    AbstractReportTemplate.getInstance(bean).showReport();
                }
                menu.setVisible(false);
            }
        }
    }
    
    public static void view(Class cls) {
        if (cls.getName().endsWith("Form")) {
            try {
                JPanel form = (JPanel) cls.newInstance();
                JFrame frame = new JFrame();
                frame.setLayout(new GridLayout());
                frame.getContentPane().add(form);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (InstantiationException ex) {
                Logger.getLogger(TransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(TransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            TemplateViewer.view(cls);
        }
    }
    
    private void scroll(JTable tbl) {
        PanelUtil.scroll(tbl, -1);
    }
    
    public void bookmark() {
        AbstractIBean bean = (AbstractIBean) this.getBean();
        BookMark mark = new BookMark();
        mark.form = ((AbstractTemplatePanel)this).currentClass.getSimpleName();
        mark.recordId = bean.getKeyVal();
        mark.markDate = constants.Constants.useDate;
        mark.markText = bean.bookmarkText();
        mark.userId = bean.extractLoginUserId();
        mark.save();
    }
}
