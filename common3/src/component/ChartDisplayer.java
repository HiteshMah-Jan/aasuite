/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import java.awt.GridLayout;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;
import service.util.AbstractIBean;
import service.util.ChartBean;
import util.DBClient;

/**
 *
 * @author Entokwaa
 */
public class ChartDisplayer extends JPanel {

    AbstractIBean bean;
    AbstractIBean currentBean;

    public ChartDisplayer(Class currentClass) {
        try {
            currentBean = (AbstractIBean) currentClass.newInstance();
            if (constants.Constants.panelBackground!=null) {
//                this.setBackground(constants.Constants.panelBackground);
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(ChartDisplayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ChartDisplayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AbstractIBean getBean() {
        return bean;
    }

    private ChartDisplayer getMe() {
        return this;
    }
    
    private void displayChart() {
                currentBean.extractServerChildrensChartsImagesAndFiles();
                Vector<ChartBean> vec = currentBean.allChart();
                if (vec == null) {
                    updateUI();
                    return;
                }
                if (vec.size() > 0) {
                    JPanel pnl = null;
                    setLayout(new GridLayout(1, 1));

                    if (vec.size() > 4) {
                        pnl = new JPanel();
                        JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                        scroll.setViewportView(pnl);
                        pnl.setLayout(new GridLayout(1, vec.size()));
                        add(scroll);
                    } else {
                        pnl = getMe();
                    }
                    Iterator iter = vec.iterator();
                    while (iter.hasNext()) {
                        ChartBean cbean = (ChartBean) iter.next();
                        cbean.setup();
                        if (cbean.dataset instanceof DefaultPieDataset) {
                            PieChartPallete pallete = new PieChartPallete(cbean.title, (DefaultPieDataset) cbean.dataset);
                            pnl.add(pallete);
                        } else if (cbean.dataset instanceof IntervalCategoryDataset) {
                            GanttChartPallete pallete = new GanttChartPallete(cbean.title, (TaskSeriesCollection) cbean.dataset);
                            pnl.add(pallete);
                        } else if (cbean.dataset instanceof CategoryDataset) {
                            BarChartPallete pallete = new BarChartPallete(cbean.title, (CategoryDataset) cbean.dataset);
                            pnl.add(pallete);
                        }
                    }
                }
                updateUI();
    }

    public void setBean(AbstractIBean bean) {
        this.removeAll();
        this.bean = bean;
        util.BeanUtil.copyBean(bean, currentBean);
        if (bean == null) {
            this.updateUI();
            return;
        }
        displayChart();
    }
}