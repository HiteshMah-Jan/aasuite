/*
 * Persistence.java
 *
 * Created on Sep 1, 2007, 11:25:45 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.admin.AppConfig;
import java.util.List;
import constants.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.BeanUtil;
import util.DBUtil;
import util.Log;
import service.util.AbstractIBean;
import service.util.IBean;

/**
 *
 * @author Budoy Entokwa
 */
public class Persistence implements IService {

    public ReturnStruct callService(ParamStruct param) {
        new AppConfig().runStaticConfig();
        //Check DELETE, INSERT, UPDATE, SELECT
        if (param == null || param.getActionCommand() == 0) {
            return null;
        }
        ReturnStruct ret = new ReturnStruct();
        ret.setStatus(Constants.FAIL);

        int action = param.getActionCommand();
        if (action == Constants.INSERT) {
            ret = this.insert(param);
        } else if (action == Constants.UPDATE) {
            ret = this.update(param);
        } else if (action == Constants.DELETE) {
            ret = this.delete(param);
        } else if (action == Constants.SELECT) {
            ret = this.select(param);
        } else if (action == Constants.SELECT_LIST_SERVERCACHE) {
            ret = this.selectListServerCache(param);
        } else if (action == Constants.SELECT_LIST) {
            ret = this.selectList(param);
        } else if (action == Constants.SELECT_BATCH) {
            ret = this.selectBatch(param);
        } else if (action == Constants.SELECT_BATCH_SERVERCACHE) {
            ret = this.selectBatchServerCache(param);
        } else if (action == Constants.SELECT_BATCH_NOCACHE) {
            ret = this.selectBatchNoCache(param);
        } else if (action == Constants.SELECT_AND_UPDATE_BATCH) {
            ret = this.selectBatchWithUpdateNoCache(param);
        } else if (action == Constants.SELECT_NATIVE) {
            ret = this.selectNativeList(param);
        } else if (action == Constants.SELECT_SINGLE) {
            ret = this.selectSingle(param);
        } else if (action == Constants.SELECT_SINGLE_COLUMN) {
            ret = this.selectSingleColumn(param);
        } else if (action == Constants.BATCH_BEANS) {
            ret = this.batch(param);
        } else if (action == Constants.RUN_BATCH) {
            ret = this.runBatch(param);
        } else if (action == Constants.RUN_BATCH_NATIVE) {
            ret = this.runNativeBatch(param);
        } else if (action == Constants.EXECUTE_ONLY) {
            ret = this.execute(param);
        } else if (action == Constants.EXECUTE_NATIVE_ONLY) {
            ret = this.executeNative(param);
        } else if (action == Constants.SAVE_SUBRECORDS) {
            ret = this.saveSubRecords(param);
        } else if (action == Constants.CACHE_RECORD) {
            ret = this.cacheRecord(param);
        }
        return ret;
    }

    private ReturnStruct selectBatchWithUpdateNoCache(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        Map map = new HashMap();
        if (param.getData() == null) {
//            DBUtil.getInstance().selectBean(param.getHelperSQL(), null);
        } else {
        	List tmp = (List) param.getData();
//        	setup updates
            List lstBeans = (List) tmp.get(0);
            for (Object obj : lstBeans) {
                AbstractIBean bean = (AbstractIBean) obj;
                if (bean!=null) {
                	bean.save();
                }
            }

//            setup batch query
            int start = 0;
            int recSize = 200;
            List<String> lstPage = (List) tmp.get(1);
            for (String str : lstPage) {
                if (str.startsWith("NATIVE")) {
                    String sql = str.substring("NATIVE-".length());
                    List lst = DBUtil.getInstance().selectBeanNative(sql, null);
                    map.put(str, lst);
                }
                else {
                    List lst = DBUtil.getInstance().selectBean(str, null, start, recSize);
                    map.put(str, lst);
                }
            }
        }
        ret.setData(map);
        ret.setStatus(Constants.SUCCESS);
        return ret;
	}

	private ReturnStruct selectBatchNoCache(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        Map map = new HashMap();
        if (param.getData() == null) {
//            DBUtil.getInstance().selectBean(param.getHelperSQL(), null);
        } else {
            int start = 0;
            int recSize = 200;
            List<String> lstPage = (List) param.getData();
            for (String str : lstPage) {
                if (str.startsWith("NATIVE")) {
                    String sql = str.substring("NATIVE-".length());
                    List lst = DBUtil.getInstance().selectBeanNative(sql, null);
                    map.put(str, lst);
                }
                else {
                    List lst = DBUtil.getInstance().selectBean(str, null, start, recSize);
                    map.put(str, lst);
                }
            }
        }
        ret.setData(map);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    private ReturnStruct runNativeBatch(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        if (param.getData() == null) {
//            DBUtil.getInstance().selectBean(param.getHelperSQL(), null);
        } else {
            List<String> lstPage = (List) param.getData();
            for (String str : lstPage) {
            	DBUtil.getInstance().executeSQLNative(str, null);
            }
        }
        ret.setData(param.getData());
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    private ReturnStruct runBatch(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        if (param.getData() == null) {
//            DBUtil.getInstance().selectBean(param.getHelperSQL(), null);
        } else {
            List<String> lstPage = (List) param.getData();
            for (String str : lstPage) {
            	DBUtil.getInstance().executeSQL(str, null);
            }
        }
        ret.setData(param.getData());
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    private ReturnStruct selectBatch(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        Map map = new HashMap();
        if (param.getData() == null) {
//            DBUtil.getInstance().selectBean(param.getHelperSQL(), null);
        } else {
            int start = 0;
            int recSize = 200;
            List<String> lstPage = (List) param.getData();
            for (String str : lstPage) {
                if (str.startsWith("NATIVE")) {
                    String sql = str.substring("NATIVE-".length());
                    List lst = DBUtil.getInstance().selectBeanNative(sql, null);
                    map.put(str, lst);
                }
                else {
                    List lst = DBUtil.getInstance().selectBean(str, null, start, recSize);
                    map.put(str, lst);
                }
            }
        }
        ret.setData(map);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    private ReturnStruct selectBatchServerCache(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        Map map = new HashMap();
        if (param.getData() == null) {
//            DBUtil.getInstance().selectBean(param.getHelperSQL(), null);
        } else {
            int start = 0;
            int recSize = 200;
            List<String> lstPage = (List) param.getData();
            for (String str : lstPage) {
                if (str.startsWith("NATIVE")) {
                    String sql = str.substring("NATIVE-".length());
                    List lst = DBUtil.getInstance().selectBeanNative(sql, null);
                    map.put(str, lst);
                }
                else {
                	String id = str;
                	if (start > 0) {
                		id = BeanUtil.concat(start,"-",str);
                	}
                    List lst = DBUtil.getInstance().selectBean(str, null, start, recSize);
                    map.put(str, lst);
                }
            }
        }
        ret.setData(map);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }
    
    private boolean beanExist(Object bean, List lst) {
        Object beanKey = BeanUtil.getKeyValue((IBean) bean);
        for (Object obj : lst) {
            Object key = BeanUtil.getKeyValue((IBean) obj);
            if (key.equals(beanKey)) {
                return true;
            }
        }
        return false;
    }

    protected ReturnStruct saveSubRecords(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        List lst = (List) param.getData();

        List origRecords = (List) lst.get(1);
        List savedRecords = (List) lst.get(0);

        List lstForDelete = new ArrayList();

        for (Object bean : origRecords) {
            Log.info("ORIG BEAN === ",bean);
            if (!beanExist(bean, savedRecords)) {
                Log.info("DELETE BEAN === ",bean);
                lstForDelete.add(bean);
            }
        }

        List forReturnList = new ArrayList();
        for (Object obj : lstForDelete) {
            IBean bean = (IBean) obj;
            DBUtil.getInstance().deleteBean(bean, null);
        }
        for (Object obj : savedRecords) {
            IBean bean = (IBean) obj;
            try {
                forReturnList.add(DBUtil.getInstance().insertBean(bean, null));
            } catch (Exception e) {
            }
        }
        ret.setData(forReturnList);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct execute(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        String bean = (String) param.getData();
        DBUtil.getInstance().executeSQL(bean, null);
        ret.setData("");
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct executeNative(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        String bean = (String) param.getData();
		DBUtil.getInstance().executeSQLNative(bean, null);
        ret.setData("");
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct insert(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        IBean bean = (IBean) param.getData();
        bean = DBUtil.getInstance().insertBean(bean, null);
        bean.runIncludeSave();
        ret.setData(bean);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct update(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        IBean bean = (IBean) param.getData();
        bean = DBUtil.getInstance().updateBean(bean, null);
        bean.runIncludeSave();
        ret.setData(bean);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct delete(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        IBean bean = (IBean) param.getData();
        DBUtil.getInstance().deleteBean(bean, null);
        ret.setData(bean);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct select(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        IBean bean = null;
        if (param.getData() != null && param.getData() instanceof IBean) {
            bean = (IBean) param.getData();
        } else {
            bean = (IBean) BeanUtil.toObject((byte[]) param.getData());
        }
        List lst = DBUtil.getInstance().selectBean(bean, param.getHelperSQL(), param.getActionCommand(), null);
        ret.setData(lst);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct selectListServerCache(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        List lst = null;
        if (param.getData() == null) {
        	lst = DBUtil.getInstance().selectBean(param.getHelperSQL(), null);
        } else {
            List lstPage = (List) param.getData();
            int start = (Integer) lstPage.get(0);
            int recSize = (Integer) lstPage.get(1);
            String id = param.getHelperSQL();
            if (start > 0) {
            	id = BeanUtil.concat(start,"-",param.getHelperSQL());
            }
        	lst = DBUtil.getInstance().selectBean(param.getHelperSQL(), null, start, recSize);
        }
        ret.setData(lst);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct selectList(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        List lst = null;
        if (param.getData() == null) {
            lst = DBUtil.getInstance().selectBean(param.getHelperSQL(), null);
        } else {
            List lstPage = (List) param.getData();
            int start = (Integer) lstPage.get(0);
            int recSize = (Integer) lstPage.get(1);
            lst = DBUtil.getInstance().selectBean(param.getHelperSQL(), null, start, recSize);
        }
        ret.setData(lst);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct selectNativeList(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        List lst = DBUtil.getInstance().selectBeanNative(param.getHelperSQL(), null);
        ret.setData(lst);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct selectSingle(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        Object lst = DBUtil.getInstance().getSingleBean(param.getHelperSQL(), null);
        ret.setData(lst);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct selectSingleColumn(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        Object obj = DBUtil.getInstance().selectSingleColumn(param.getHelperSQL(), null);
        ret.setData(obj);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    protected ReturnStruct batch(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        List retList = new ArrayList();
        List lstBeans = (List) param.getData();
        for (Object obj : lstBeans) {
            AbstractIBean bean = (AbstractIBean) obj;
            if (bean!=null) {
            	bean.save();
                retList.add(bean);
            }
        }
        ret.setData(retList);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }

    private ReturnStruct cacheRecord(ParamStruct param) {
        ReturnStruct ret = new ReturnStruct();
        IBean bean = (IBean) param.getData();
        bean.serverCache();
        ret.setData(bean);
        ret.setStatus(Constants.SUCCESS);
        return ret;
    }
}
