/*
 * DBClient.java
 *
 * Created on Oct 1, 2007, 7:44:06 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import service.ReturnStruct;
import service.util.AbstractIBean;
import service.util.CallService;
import service.util.IBean;

/**
 *
 * @author Budoy Entokwa
 */
public class DBClient {
    public static boolean collectSQL = false;
    public static List<String> collectedSQL = new ArrayList<String>();
    
    private static void collectSql(Object obj) {
        if (collectSQL) {
            if (obj instanceof List) {
                List lst = (List) obj;
                collectedSQL.addAll(lst);
            }
            else {
                collectedSQL.add(obj.toString());
            }
        }
    }
    
    public static void runBatch(List<String> sql) {
//        System.out.println("...BATCH CALL...");
        for (String str : sql) {
//            System.out.println("\t"+str);
        }
        ReturnStruct ret = CallService.callService(sql, "BATCH", constants.Constants.RUN_BATCH, constants.Constants.PERSISTENCE_SERVICE);
    }

    public static void runBatchNative(String... sql) {
    	List<String> lst = new ArrayList();
    	for (String s:sql) {
    		lst.add(s);
    	}
    	runBatchNative(lst);
    }
    
    public static void runBatchNative(List<String> sql) {
//        System.out.println("...BATCH CALL...");
        for (String str : sql) {
//            System.out.println("\t"+str);
        }
        ReturnStruct ret = CallService.callService(sql, "BATCH", constants.Constants.RUN_BATCH_NATIVE, constants.Constants.PERSISTENCE_SERVICE);
    }
    
    public static Map batchQuery(List<String> sql) {
//        System.out.println("...BATCH CALL...");
        for (String str : sql) {
//            System.out.println("\t"+str);
        }
        ReturnStruct ret = CallService.callService(sql, "BATCH", constants.Constants.SELECT_BATCH, constants.Constants.PERSISTENCE_SERVICE);
        collectSql(sql);
        if (ret==null || ret.getData()==null) return new HashMap();
        return (Map) ret.getData();
    }

    public static Map batchQueryServerCache(List<String> sql) {
//      System.out.println("...BATCH CALL...");
      for (String str : sql) {
//          System.out.println("\t"+str);
      }
      ReturnStruct ret = CallService.callService(sql, "BATCH", constants.Constants.SELECT_BATCH_SERVERCACHE, constants.Constants.PERSISTENCE_SERVICE);
      collectSql(sql);
      if (ret==null || ret.getData()==null) return new HashMap();
      return (Map) ret.getData();
  }

    public static Map batchQueryNoCache(List<AbstractIBean> lst, List<String> sql) {
//        System.out.println("...BATCH NO CACHE CALL...");
        for (String str : sql) {
//            System.out.println("\t"+str);
        }
        List tmp = new ArrayList();
        tmp.add(lst);
        tmp.add(sql);
        ReturnStruct ret = CallService.callService(tmp, "BATCH", constants.Constants.SELECT_AND_UPDATE_BATCH, constants.Constants.PERSISTENCE_SERVICE);
        collectSql(sql);
        if (ret==null || ret.getData()==null) return new HashMap();
        return (Map) ret.getData();
    }

    public static List batchQueryNoCache(List<AbstractIBean> lst, String sql) {
        List t = new ArrayList();
        t.add(sql);
        Map map = batchQueryNoCache(lst, t);
        if (map!=null && !map.isEmpty()) {
            Iterator iter = map.values().iterator();
            if (iter.hasNext()) {
            	return (List) iter.next();
            }
        }
        return new ArrayList();
    }

    public static Map batchQueryNoCache(List<String> sql) {
//        System.out.println("...BATCH NO CACHE CALL...");
        for (String str : sql) {
//            System.out.println("\t"+str);
        }
        ReturnStruct ret = CallService.callService(sql, "BATCH", constants.Constants.SELECT_BATCH_NOCACHE, constants.Constants.PERSISTENCE_SERVICE);
        collectSql(sql);
        if (ret==null || ret.getData()==null) return new HashMap();
        return (Map) ret.getData();
    }

    public static void deleteBean(IBean bean) {
        CallService.callService(bean, constants.Constants.DELETE, constants.Constants.PERSISTENCE_SERVICE);
    }

    public static void persistBean(IBean bean) {
        ReturnStruct ret = CallService.callService(bean, constants.Constants.INSERT, constants.Constants.PERSISTENCE_SERVICE);
        BeanUtil.copyBean((IBean)ret.getData(), bean);
    }

    public static void persistBean(IBean... bean) {
    	List lst = new ArrayList();
    	for (IBean b:bean) {
        	lst.add(b);
    	}
    	persistBean(lst);
    }

    public static void runSQL(String sql) {
        CallService.callService(sql, constants.Constants.EXECUTE_ONLY, constants.Constants.PERSISTENCE_SERVICE);
        collectSql(sql);
    }

    public static void runSQL(Object... sql) {
        CallService.callService(BeanUtil.concat(sql), constants.Constants.EXECUTE_ONLY, constants.Constants.PERSISTENCE_SERVICE);
        collectSql(BeanUtil.concat(sql));
    }

    public static void runSQLNative(String sql) {
        CallService.callService(sql, constants.Constants.EXECUTE_NATIVE_ONLY, constants.Constants.PERSISTENCE_SERVICE);
        collectSql(sql);
    }

    public static void runSQLNative(Object... sql) {
        CallService.callService(BeanUtil.concat(sql), constants.Constants.EXECUTE_NATIVE_ONLY, constants.Constants.PERSISTENCE_SERVICE);
        collectSql(BeanUtil.concat(sql));
    }

    public static void persistBean(List<IBean> lst) {
		List lstTmp = new ArrayList();
		PanelUtil.setListData(lstTmp, lst);
        ReturnStruct ret = CallService.callService(lstTmp, constants.Constants.BATCH_BEANS, constants.Constants.PERSISTENCE_SERVICE);
        if (ret==null) return;
        List tmp = (List)ret.getData();
        lst.clear();
        lst.addAll(tmp);
    }

    public static void saveSubRecords(List<IBean> savedRecords, List<IBean> origRecords) {
        List lst = new ArrayList();
        if (savedRecords == null) {
            savedRecords = new ArrayList();
        }
        if (origRecords == null) {
            origRecords = new ArrayList();
        }
        lst.add(origRecords);
        lst.add(savedRecords);
        ReturnStruct ret = CallService.callService(lst, constants.Constants.SAVE_SUBRECORDS, constants.Constants.PERSISTENCE_SERVICE);
        List tmp = (List)ret.getData();
        savedRecords.clear();
        savedRecords.addAll(tmp);
    }

    public static List getList(String sql) {
        ReturnStruct ret = CallService.callService(null, sql, constants.Constants.SELECT_LIST, constants.Constants.PERSISTENCE_SERVICE);
        if (ret==null || ret.getData()==null) return new ArrayList();
        collectSql(sql);
        return (List) ret.getData();
    }

    public static List getListServerCache(String sql) {
        ReturnStruct ret = CallService.callService(null, sql, constants.Constants.SELECT_LIST_SERVERCACHE, constants.Constants.PERSISTENCE_SERVICE);
        if (ret==null || ret.getData()==null) return new ArrayList();
        collectSql(sql);
        return (List) ret.getData();
    }

    public static List getList(Object... sql) {
        collectSql(BeanUtil.concat(sql));
        return getList(BeanUtil.concat(sql));
    }

    public static List getList(String sql, int start, int recSize) {
        List lst = new ArrayList();
        if (start==1) start=0;
        lst.add(start);
        lst.add(recSize);
        ReturnStruct ret = CallService.callService(lst, sql, constants.Constants.SELECT_LIST, constants.Constants.PERSISTENCE_SERVICE);
        collectSql(sql);
        if (ret==null || ret.getData()==null) return new ArrayList();
        return (List) ret.getData();
    }

    public static List getListServerCache(String sql, int start, int recSize) {
        List lst = new ArrayList();
        if (start==1) start=0;
        lst.add(start);
        lst.add(recSize);
        ReturnStruct ret = CallService.callService(lst, sql.trim(), constants.Constants.SELECT_LIST_SERVERCACHE, constants.Constants.PERSISTENCE_SERVICE);
        collectSql(sql);
        if (ret==null || ret.getData()==null) return new ArrayList();
        return (List) ret.getData();
    }

    public static List getListNative(String sql) {
        ReturnStruct ret = CallService.callService(null, sql, constants.Constants.SELECT_NATIVE, constants.Constants.PERSISTENCE_SERVICE);
        if (ret==null || ret.getData()==null) return new ArrayList();
        collectSql(sql);
        return (List) ret.getData();
    }

    public static void updateRecordList(String sql, List lst) {
        ReturnStruct ret = CallService.callService(null, sql, constants.Constants.SELECT_LIST, constants.Constants.PERSISTENCE_SERVICE);
        List lstTmp = (List) ret.getData();
        if (lst != null && lst.size() > 0) {
            lst.clear();
        }
        lst.addAll(lstTmp);
    }

    public static Object getFirstRecord(String sql) {
        ReturnStruct ret = CallService.callService(null, sql, constants.Constants.SELECT_SINGLE, constants.Constants.PERSISTENCE_SERVICE);
        if (ret==null) return null;
        collectSql(sql);
        return ret.getData();
    }

    public static boolean exist(String sql) {
        AbstractIBean b = (AbstractIBean) getFirstRecord(sql);
        if (b==null || b.isEmptyKey()) return false;
        return true;
    }

    public static boolean exist(Class cls, Object keyVal) {
        AbstractIBean b = (AbstractIBean) getFirstRecord(cls, keyVal);
        if (b==null || b.isEmptyKey()) return false;
        return true;
    }

    public static Object getFirstRecord(String... sqlArr) {
        String sql = BeanUtil.concat(sqlArr);
        collectSql(sql);
        return getFirstRecord(sql);
    }

    public static Object getFirstRecord(Class cls, Object keyVal) {
        String sql = createSQLFromClass(cls);
        try {
            AbstractIBean bean = (AbstractIBean) cls.newInstance();
            String keyField = bean._Key();
            char initial = cls.getSimpleName().toLowerCase().charAt(0);
            StringBuffer sb = new StringBuffer();
            try {
                Integer.parseInt(keyVal.toString());
                sb.append(sql).append(" WHERE ").append(initial).append(".").append(keyField).append("=").append(keyVal.toString());
                sql = sb.toString();
            }
            catch (Exception e) {
                sb.append(sql).append(" WHERE ").append(initial).append(".").append(keyField).append("='").append(keyVal.toString()).append("'");
                sql = sb.toString();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ReturnStruct ret = CallService.callService(null, sql, constants.Constants.SELECT_SINGLE, constants.Constants.PERSISTENCE_SERVICE);
        collectSql(sql);
        if (ret==null) return null;
        return ret.getData();
    }

    public static Object getSingleColumn(String sql) {
        ReturnStruct ret = CallService.callService(null, sql, constants.Constants.SELECT_SINGLE_COLUMN, constants.Constants.PERSISTENCE_SERVICE);
        if (ret==null) return null;
        collectSql(sql);
        return ret.getData();
    }

    public static double getSingleColumnDouble(String sql) {
        ReturnStruct ret = CallService.callService(null, sql, constants.Constants.SELECT_SINGLE_COLUMN, constants.Constants.PERSISTENCE_SERVICE);
        collectSql(sql);
        try {
            Object obj = ret.getData();
            return Double.parseDouble(obj.toString());
        }
        catch (Exception e) {
        }
        return 0;
    }

    private static String createSQLFromClass(Class cls) {
        return createSQLFromClass(cls.getSimpleName());
    }

    private static String createSQLFromClass(String cls) {
        char initial = cls.toLowerCase().charAt(0);
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ").append(initial).append(" FROM ").append(cls).append(" ").append(initial);
        return sb.toString();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
