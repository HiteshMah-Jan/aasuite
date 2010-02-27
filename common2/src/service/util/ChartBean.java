/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.SimpleTimePeriod;
import util.DateUtil;

/**
 *
 * @author Entokwaa
 */
public class ChartBean {

    public String title;
    public Object dataset;
    boolean useNative = false;
    AbstractIBean bean;
    int top = 10;
    public Object sql;
    public int type;
    public static final int PIE = 1;
    public static final int BAR = 2;
    public static final int GANTT = 3;

    public static ChartBean getPieInstance(AbstractIBean ibean, String title, String sql) {
        ChartBean bean = new ChartBean();
        bean.bean = ibean;
        bean.sql = sql;
        bean.type = PIE;
        bean.title = title;
//        bean.setupPie(title, sql);
        return bean;
    }

    public static ChartBean getGanttInstance(AbstractIBean ibean, String title, String... sql) {
        ChartBean bean = new ChartBean();
        bean.title = title;
        bean.bean = ibean;
        bean.sql = sql;
        bean.type = GANTT;
//        bean.setupGantt(title, sql);
        return bean;
    }

    public static ChartBean getBarInstance(AbstractIBean ibean, String title, String sql) {
        ChartBean bean = new ChartBean();
        bean.title = title;
        bean.bean = ibean;
        bean.sql = sql;
        bean.type = BAR;
//        bean.setupBar(title, sql);
        return bean;
    }

    public static ChartBean getNativePieInstance(AbstractIBean ibean, String title, String sql) {
        ChartBean bean = new ChartBean();
        bean.title = title;
        bean.bean = ibean;
        bean.useNative = true;
        bean.type = PIE;
        bean.sql = sql;
//        bean.setupPie(title, sql);
        return bean;
    }

    public static ChartBean getNativeGanttInstance(AbstractIBean ibean, String title, String sql) {
        ChartBean bean = new ChartBean();
        bean.title = title;
        bean.bean = ibean;
        bean.useNative = true;
        bean.sql = sql;
        bean.type = GANTT;
//        bean.setupGantt(title, sql);
        return bean;
    }

    public static ChartBean getNativeBarInstance(AbstractIBean ibean, String title, String sql) {
        ChartBean bean = new ChartBean();
        bean.title = title;
        bean.bean = ibean;
        bean.useNative = true;
        bean.sql = sql;
        bean.type = BAR;
//        bean.setupBar(title, sql);
        return bean;
    }

    public void setup() {
        if (type==PIE) {
            setupPie(title, sql.toString());
        }
        else if (type==BAR) {
            setupBar(title, sql.toString());
        }
        else if (type==GANTT) {
            String[] arr = (String[]) sql;
            setupGantt(title, arr);
        }
    }
    
    private List getObjectArray(List lst) {
        if (lst == null) {
            return null;
        }
        List retList = new ArrayList();
        for (Object object : lst) {
            Vector vec = (Vector) object;
            retList.add(vec.toArray());
        }
        if (retList != null && retList.size() > top) {
            return retList.subList(0, top);
        }
        return retList;
    }

    private void setupPie(String title, String sql) {
        try {
            List lst = null;
            if (useNative) {
                lst = getObjectArray((List) bean.cacheMap.get(sql));
            } else {
                lst = (List) bean.cacheMap.get(sql);
                if (lst != null && lst.size() > top) {
                    lst = lst.subList(0, top);
                }
            }
            DefaultPieDataset data = new DefaultPieDataset();
            if (lst!=null) {
                for (Object obj : lst) {
                    Object[] tmp = (Object[]) obj;
                    double d = Double.parseDouble(tmp[1].toString());
                    String t = getLabel(tmp[0]);
                    if (d == 0) {
                        continue;
                    }
                    if (t == null || t.isEmpty()) {
                        continue;
                    }
                    data.setValue(t, d);
                }
            }
            this.title = title;
            this.dataset = data;
        } catch (Exception e) {
//            e.printStackTrace();
            Logger.getLogger("global").log(Level.SEVERE, "PIE CHART SQL = " + sql);
        }
    }

    private void setupGantt(String title, String... sql) {
        try {
            String[] tArr = title.split(";");
            TaskSeriesCollection data = new TaskSeriesCollection();
            this.title = tArr[0];
            this.dataset = data;

            for (int i = 0; i < sql.length; i++) {
                String isql = sql[i];
                String ttitle = tArr[i + 1];
                List lst = null;
                if (useNative) {
                    lst = getObjectArray((List) bean.cacheMap.get(isql));
                } else {
                    lst = (List) bean.cacheMap.get(isql);
                    //top 100 always for gantt chart
                    if (lst != null && lst.size() > 100) {
                        lst = lst.subList(0, 100);
                    }
                }
                TaskSeries s1 = new TaskSeries(ttitle);
                if (lst!=null) {
                    for (Object obj : lst) {
                        Object[] tmp = (Object[]) obj;
                        String t = getLabel(tmp[0]);
                        Date d1 = DateUtil.readDate(tmp[1].toString(), "HHmm");
                        Date d2 = DateUtil.readDate(tmp[2].toString(), "HHmm");
                        if (d1 == null || d2 == null) {
                            continue;
                        }
                        if (t == null || t.isEmpty()) {
                            continue;
                        }
                        s1.add(new Task(t, new SimpleTimePeriod(d1, d2)));
                    }
                }
                data.add(s1);
            }
        } catch (Exception e) {
//                e.printStackTrace();
            Logger.getLogger("global").log(Level.SEVERE, "GANT CHART SQL = " + sql);
        }
    }

    private void setupBar(String title, String sql) {
        try {
            List lst = null;
            if (useNative) {
                lst = getObjectArray((List) bean.cacheMap.get(sql));
            } else {
                lst = (List) bean.cacheMap.get(sql);
                if (lst != null && lst.size() > top) {
                    lst = lst.subList(0, top);
                }
            }
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            if (lst!=null) {
                for (Object obj : lst) {
                    Object[] tmp = (Object[]) obj;
                    double d = Double.parseDouble(tmp[2].toString());
                    String t1 = getLabel(tmp[1]);
                    String t2 = getLabel(tmp[0]);
                    if (d == 0) {
                        continue;
                    }
                    if (t1 == null || t1.isEmpty() || t2 == null || t2.isEmpty()) {
                        continue;
                    }
                    data.setValue(d, t1, t2);
                }
            }
            this.title = title;
            dataset = data;
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("global").log(Level.SEVERE, "BAR CHART SQL = " + sql);
        }
    }

    private String getLabel(Object obj) {
        if (obj instanceof Date) {
            return DateUtil.formatDate((Date) obj, "MMM dd, yyyy");
        }
        return obj == null ? "" : obj.toString();
    }
}
