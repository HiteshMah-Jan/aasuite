/*
 * PanelNavigatorIntr.java
 *
 * Created on Feb 5, 2008, 4:11:42 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import service.util.AbstractIBean;
import util.BeanUtil;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public interface IPanelNavigator extends ITransactionPanel {

    public static class RecordNavigator {
        IPanelNavigator pnl;

        public RecordNavigator(JComponent comp) {
            if (PanelUtil.inMainPanel(comp)) {
                pnl = PanelUtil.getActivePanel(comp);
            } else {
                pnl = (IPanelNavigator) PanelUtil.getMySubPanel(comp);
            }
        }

        public static void navigate(JComponent pnl, KeyEvent v) {
            RecordNavigator rec = new RecordNavigator(pnl);
            rec.passTheKey(v);
        }

        public boolean passTheKey(KeyEvent v) {
            int c = v.getKeyCode();
            int mod = v.getModifiers();
            
            if (c == KeyEvent.VK_BACK_SPACE) {
            } else if (c == KeyEvent.VK_DELETE) {
            } else if (c == KeyEvent.VK_F1) {
                PanelUtil.showHelp();
            } else if (c == KeyEvent.VK_ESCAPE) {
            } else if (c == KeyEvent.VK_PAGE_UP) {
                if (pnl!=null) pnl.prevRecord();
                return true;
            } else if (c == KeyEvent.VK_PAGE_DOWN) {
                if (pnl!=null) pnl.nextRecord();
                return true;
            }
            else if (c == KeyEvent.VK_INSERT) {
                if (mod == KeyEvent.CTRL_MASK) {
                    if (pnl instanceof AbstractPanel) {
                        AbstractPanel abs = (AbstractPanel) pnl;
                        AbstractIBean selectedBean = (AbstractIBean) abs.getBean(); 
                        if (pnl!=null) pnl.newRecord();
                        AbstractIBean newBean = (AbstractIBean) abs.getBean(); 
                        BeanUtil.copyBean(selectedBean, newBean);
                        BeanUtil.removeBeanId(newBean);
                        pnl.prevRecord();
                        pnl.nextRecord();
                        newBean.setNewRecord(true);
                    }
                }
                else {
                    if (pnl!=null) pnl.newRecord();
                }
                return true;
            }
            else if (c == KeyEvent.VK_S && mod == KeyEvent.CTRL_MASK) {
                if (pnl!=null) pnl.saveRecord();
                return true;
            }
//            else if (c == KeyEvent.VK_DELETE && mod == KeyEvent.CTRL_MASK) {
//                if (pnl!=null) pnl.deleteRecord();
//            }
            else if (c == KeyEvent.VK_F5) {
                if (pnl!=null) pnl.refreshRecords();
                return true;
            }
            return false;
        }
    }
}
