/*
 * AbstractIBean.java
 *
 * Created on Sep 22, 2007, 10:08:58 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.util;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
//import java.beans.XMLDecoder;
//import java.beans.XMLEncoder;
//import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

//import net.sf.json.JSONObject;
//import net.sf.json.JsonConfig;
//import net.sf.json.util.CycleDetectionStrategy;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.ELProperty;

import service.IService;
import service.ParamStruct;
import service.ReturnStruct;
import springbean.AAAConfig;
import template.ChildRecord;
import template.ChildRecords;
import test.TemplateViewer;
import util.BeanUtil;
import util.DBClient;
import util.DBUtil;
import util.DataUtil;
import util.PanelUtil;
import bean.Person;
import bean.admin.DocumentTable;
import bean.admin.ImageTable;
import constants.Constants;
import constants.UserInfo;

/**
 *
 * @author Budoy Entokwa
 */
public abstract class AbstractIBean extends CheckerBean implements IBean, IService {
	@Transient
	public AbstractIBean parentBean;
	
    @Transient
	public PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    IBean myClone;
    @Transient
    public String comboDisplay;
    @Transient
    private boolean newRecord;
    @Transient
    public DefaultMutableTreeNode myNode;
//    @Transient
//    public boolean useGlobalCache;

    public abstract String popupSearch(String criteria);

    protected String buildSearch(String criteria, String... columns) {
        String beanName = BeanUtil.getEntityClass(getClass()).getSimpleName();
        if (beanName.endsWith("Ext")) {
            beanName = beanName.replace("Ext", "");
        }
        StringBuffer sb = new StringBuffer();
        if (columns==null || columns.length==0 || criteria==null || criteria.isEmpty()) {
            sb.append("SELECT a FROM ").append(beanName).append(" a");
        }
        else {
            sb.append("SELECT a FROM ").append(beanName).append(" a WHERE ");
            for (int i=0; i< columns.length; i++) {
                String col = columns[i];
                sb.append(" a.").append(col).append(" LIKE '").append(criteria).append("%' ");
                if (i+1<columns.length) {
                    sb.append(" OR ");
                }
            }
        }
        return sb.toString();
    }

    protected String buildSearchUseEntity(String criteria, String entity, String... columns) {
        StringBuffer sb = new StringBuffer();
        if (columns==null || columns.length==0 || criteria==null || criteria.isEmpty()) {
            sb.append("SELECT a FROM ").append(entity).append(" a");
        }
        else {
            sb.append("SELECT a FROM ").append(entity).append(" a WHERE ");
            for (int i=0; i< columns.length; i++) {
                String col = columns[i];
                sb.append(" a.").append(col).append(" LIKE '").append(criteria).append("%' ");
                if (i+1<columns.length) {
                    sb.append(" OR ");
                }
            }
        }
        return sb.toString();
    }

    protected String buildSearchIncludeWhere(String criteria, String where, String... columns) {
        StringBuffer sb = new StringBuffer();
        if (columns==null || columns.length==0 || criteria==null || criteria.isEmpty()) {
            sb.append("SELECT a FROM ").append(_Table()).append(" a WHERE ");
        }
        else {
            sb.append("SELECT a FROM ").append(_Table()).append(" a WHERE ");
            for (int i=0; i< columns.length; i++) {
                String col = columns[i];
                sb.append(" a.").append(col).append(" LIKE '").append(criteria).append("%' ");
                if (i+1<columns.length) {
                    sb.append(" OR ");
                }
            }
        }
        sb.append(where);
        return sb.toString();
    }

    public boolean isNewRecord() {
        return newRecord;
    }

    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }

    public AbstractIBean() {
        cacheMap.put("BATCH CALLED", false);
        setEnteredDate(constants.Constants.useDate);
        if (UserInfo.loginUser == null) {
            setEnteredBy("AAA");
        } else {
            try {
                String userId = UserInfo.loginUser.getUser().getUserid();
                setEnteredBy(userId);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public String _Key() {
        Field[] fs = this.getClass().getFields();
        for (Field field : fs) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                return field.getName();
            }
        }
        return "seq";
    }

    @Override
    public String _Table() {
        //get root table
        if (this.getClass().getName().endsWith("Ext")) {
            try {
                Class cls = PanelUtil.getBeanClass(this.getClass().getSuperclass().getSimpleName());
                Table tbl = (Table) cls.getAnnotation(Table.class);
                return tbl.name();
            } catch (Exception ex) {
                severe(null, ex);
            }
        }
        Table tbl = this.getClass().getAnnotation(Table.class);
        return tbl.name();
    }

    @Override
    public int _Status() {
        return Constants.SUCCESS;
    }

    @Override
    public IBean getMe(EntityManager entityManager) {
        Object obj = null;
        try {
            if (!isEmptyKey()) {
                obj = entityManager.find(this.getClass(), BeanUtil.getKeyValue(this));
            }
        } catch (Exception e) {
            severe(null, e);
        }
        return (IBean) obj;
    }

    @Override
    public IBean updateMe(EntityManager entityManager) {
        try {
        	try {
                String key = _Key();
                entityManager.getTransaction().begin();
            	if (isEmptyKey() && ("seq".equals(key) || "personId".equals(key))) {
                    this.putNextKey();
                    entityManager.persist(this);
            	}
            	else {
                    this.putNextKey();
                    entityManager.merge(this);
            	}
                entityManager.getTransaction().commit();
                entityManager.close();
        	}
        	catch (Exception e) {
        		DBUtil.cleanFailedConnection(entityManager, e);
        	}
        } catch (Exception e) {
            severe(null, e);
        }
        return this;
    }

    final static Map<String, Integer> keyMap = new Hashtable<String, Integer>();
    private synchronized void putNextKey() {
    	if (!isEmptyKey()) return;
        String key = _Key();
        if ("seq".equals(key) || "personId".equals(key)) {
        	Integer i = keyMap.get(_Table());
        	if (!keyMap.containsKey(_Table())) {
                int id = (int) DBClient.getSingleColumnDouble("SELECT MAX("+_Key()+") FROM "+_Table());
                BeanUtil.setPropertyValue(this, _Key(), id+1);
                keyMap.put(_Table(), id+1);
        	}
        	else {
                BeanUtil.setPropertyValue(this, _Key(), i+1);
                keyMap.put(_Table(), i+1);
        	}
        }
    }
    
    public IBean findMe(EntityManager entityManager) {
        Object obj = null;
        try {
            obj = entityManager.find(this.getClass(), BeanUtil.getKeyValue(this));
        } catch (Exception e) {
            severe(null, e);
        }
        if (obj != null) {
            BeanUtil.copyBean(this, (IBean) obj);
            AbstractIBean bean = (AbstractIBean) obj;
            return bean;
        }
        return this;
    }

    @Override
    public IBean clone() {
        try {
            IBean obj = this.getClass().newInstance();
            util.BeanUtil.copyBean(this, obj);
            return obj;
        } catch (Exception ex) {
            severe(null, ex);
        }
        return null;
    }

    public Object keyVal() {
        return BeanUtil.getKeyValue(this);
    }

    public int intKeyVal() {
        try {
            return Integer.parseInt(BeanUtil.getKeyValue(this).toString());
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean isEmptyKey() {
        Object obj = keyVal();
        if (obj == null) {
            return true;
        }
        if (obj.toString().trim().isEmpty()) {
            return true;
        }
        if (obj instanceof Integer || obj instanceof Double) {
            int i = (Integer) obj;
            if (i == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canCache() {
        return false;
    }

    public Collection _Children(Class cls, String additionalCriteria) {
        return null;
    }

    public String _DataDisplay() {
        return this._Table() + "-" + keyVal();
    }

    public String getComboDisplay() {
        Object obj = keyVal();
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public String extractFormattedTitle() {
        return getComboDisplay();
    }

    public TreeModel extractTreeModel(String modelType) {
        return null;
    }

    public List extractImageList() {
        List lst = ImageTable.getImageList(this);
        return lst;
    }

    public String extractImageHTML(int index, int width, int height) {
        List lst = extractImageList();
        if (lst != null) {
            try {
                java.lang.StringBuffer name = new java.lang.StringBuffer();
                name.append(this.getClass().getSimpleName()).append(this.keyVal()).append("_").append(index).append(".jpg");
                java.lang.String file = name.toString();

                name.setLength(0);
                if (width > 0 && height > 0) {
                    name.append("<img width='").append(width).append("' height='").append(height).append("' src=\'").append(constants.Constants.ROOT_FOLDER).append("images/");
                } else {
                    name.append("<img src=\'").append(Constants.ROOT_FOLDER).append("images/");
                }
                name.append(file);
                name.append("\'/>");
                byte[] b = null;
                if (lst.size() > index) {
                    b = (byte[]) lst.get(lst.size() - 1);
                } else {
                    b = (byte[]) lst.get(index);
                }

                //create the file here
                java.io.File fDir = new java.io.File(Constants.ROOT_FOLDER + "images");
                if (!fDir.exists() || !fDir.isDirectory()) {
                    fDir.mkdir();
                }

                java.io.File f = new java.io.File(fDir, file);
                f.delete();
                ByteArrayInputStream is = new ByteArrayInputStream(b);
                BufferedImage img = ImageIO.read(is);
                ImageIO.write(img, "JPG", f);

                return name.toString();
            } catch (Exception ex) {
                severe(null, ex);
            }

        }
        return "<img src='noimage.jpg'/>";
    }

    public String extractPictureHTML() {
        String str = extractImageHTML(0, 0, 0);
        info(str);
        return str;
    }

    public String extractHTMLDocument() {
        return "<html><body><center><h1>HTML document not set for this bean.</h1></center></body></html>";
    }

    public AbstractIBean extractMe() {
        return this;
    }

    public void clearId() {
        try {
            BeanUtil.setPropertyValue(this, _Key(), 0);
            return;
        } catch (Exception e) {
        }
        try {
            BeanUtil.setPropertyValue(this, _Key(), null);
        } catch (Exception e) {
        }
    }

    public AAAConfig extractConfig() {
        return AAAConfig.getInstance();
    }

    public void setIsActive(boolean isActive) {
    }

    public AbstractIBean extractObject(String code) {
    	return extractObject(this.getClass().getSimpleName(), code);
    }

    public static AbstractIBean extractObject(String entity, String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }
        try {
            java.lang.Class cls = PanelUtil.getBeanClass(entity);
            cls = extractBeanClass(cls);
            AbstractIBean tmp = (AbstractIBean) cls.newInstance();
            if (tmp.cacheClient()) {
                return AbstractIBean.extractCacheBeans(cls, code);
            }
            else {
                return (AbstractIBean) DBClient.getFirstRecord(cls, code);
            }
        } catch (Exception ex) {
            severe(null, ex);
        }
        return null;
    }

    public void setEnteredBy(String enteredBy) {
    }

    public void setEnteredDate(Date enteredDate) {
    }
    @Transient
    private Object formulaValue;

    public Object getFormulaValue() {
        return formulaValue;
    }

    public void setFormulaValue(Object formulaValue) {
        this.formulaValue = formulaValue;
    }

    public String getKeyVal() {
        return this.keyVal() + "";
    }

    public void setKeyVal(String key) {
        try {
            BeanUtil.setPropertyValue(this, _Key(), key);
        } catch (Exception e) {
        }
    }

    @Override
    public String toString() {
        Object obj = this.keyVal();
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!obj.getClass().getName().equals(this.getClass().getName())) {
            return false;
        }
        AbstractIBean bean = (AbstractIBean) obj;
        return bean.getKeyVal().equals(this.getKeyVal());
    }

    protected static void view(Class cls) {
        TemplateViewer.view(cls);
    }

    public int getRecordListCount() {
        return 25;
    }

    public void save() {
        DBClient.persistBean(this);
        clearCache();
    }

    public void threadSave() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                save();
            }
        });
        t.start();
    }
    
    public void delete() {
        DBClient.deleteBean(this);
    }

    public AbstractIBean newBean() {
        try {
            AbstractIBean bean = this.getClass().newInstance();
            return bean;
        } catch (Exception ex) {
            severe(null, ex);
        }
        return null;
    }

    public AbstractIBean newCopy() {
        try {
            AbstractIBean bean = this.getClass().newInstance();
            BeanUtil.copyBean(this, bean);
            BeanUtil.removeBeanId(bean);
            return bean;
        } catch (Exception ex) {
            severe(null, ex);
        }
        return null;
    }

    public void reRead() {
        AbstractIBean obj = (AbstractIBean) DBClient.getFirstRecord(this.getClass(), keyVal());
        BeanUtil.copyBean(obj, this);
    }

    public void showVariables() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName());
        Field[] fields = this.getClass().getFields();
        String fieldName = "";
        for (Field elem : fields) {
            try {
                fieldName = elem.getName();
                Object obj = BeanProperty.create(fieldName).getValue(this);
                sb.append("\n\t").append(fieldName).append("=").append(obj.toString());
            } catch (Exception e) {
            }
        }
//        System.out.println(sb.toString());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public void changeValue(String field, Object newValue) {
        if (myClone == null) {
            myClone = this.clone();
        }
        try {
            Object oldValue = BeanProperty.create(field).getValue(myClone);
            changeSupport.firePropertyChange(field, oldValue, newValue);
            BeanUtil.setPropertyValue(this, field, newValue);
        } catch (Exception e) {
        	e.printStackTrace();
            warning("Change value of " + field+" - "+this.getClass().getName());
        }
    }

    public void changeValuePop(String field, Object newValue) {
        if (myClone == null) {
            myClone = this.clone();
        }
        Object oldValue = BeanProperty.create(field).getValue(myClone);
        changeSupport.firePropertyChange(field, oldValue, newValue);
        BeanUtil.setPropertyValue(this, field, newValue);
    }

    public boolean wasChanged() {
        if (myClone == null) {
            return true;
        }
        Field[] fields = this.getClass().getFields();
        for (Field field : fields) {
            Object valClone = BeanProperty.create(field.getName()).getValue(myClone);
            Object valThis = BeanProperty.create(field.getName()).getValue(this);

            if (!valClone.equals(valThis)) {
                return false;
            }
        }
        return true;
    }

    public String orderBy() {
        return null;
    }

    public boolean canSet(String field) {
        if ("comboDisplay".equals(field)) {
            return false;
        }
        return true;
    }

    public final String extractGLType() {
        return getClass().getSimpleName();
    }

    public int extractGLRecordId() {
        return intKeyVal();
    }

    public java.util.Vector allChart() {
        return null;
    }

    public String extractEmailSubject() {
        return toString();
    }

    public String extractEmailContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("Dear , \n");
        sb.append("\n\n");
        sb.append("Sincerely Yours,\n");
        return sb.toString();
    }

    public int extractEmailCustomerId() {
        return 0;
    }

    public String extractEmailRecipient() {
        return "";
    }

    public String extractEmailAttachment() {
        return "";
    }

    public final String extractLoginName() {
        UserInfo info = UserInfo.loginUser;
        if (info == null) {
            return "";
        }
        if (info.isSuperAAA()) {
            return "System";
        }
        return info.getPersonName();
    }

    public final String extractLoginUserId() {
        UserInfo info = UserInfo.loginUser;
        if (info == null || info.isSuperAAA()) {
            return "AAA";
        }
        return info.getUserName();
    }

    public final int extractLoginPersonId() {
        UserInfo info = UserInfo.loginUser;
        if (info == null) {
            return -1;
        }
        if (info.isSuperAAA()) {
            return -1;
        }
        return info.getPersonId();
    }
    public boolean newCache = true;
    public Map<String, Object> cacheMap = new HashMap<String, Object>();

    @Override
    public boolean isNewCache() {
        return newCache;
    }

    @Override
    public void changeCache(IBean bean) {
        cacheMap = ((AbstractIBean) bean).cacheMap;
        newCache = false;
        ((AbstractIBean) bean).newCache = false;
    }

    @Override
    public final void serverCache() {
        manualCache();
    }

    protected void manualCache() {
    }

    public void clearCache() {
        newCache = true;
        cacheMap.clear();
    }

    public void clearCache(String str) {
        cacheMap.remove(str);
    }

    public synchronized void extractServerChildrensChartsImagesAndFiles() {
        if (isEmptyKey() || AAAConfig.server) {
            return;
        }
        try {
            boolean called = (Boolean) cacheMap.get("BATCH CALLED");
            if (called) return;
        }
        catch (Exception e) {
        }
        cacheMap.put("BATCH CALLED", true);
        List<String> lstSql = new ArrayList<String>();
//        for child records
        if (showSubrecords) {
            ChildRecords childRecordsAnnotation = getClass().getAnnotation(ChildRecords.class);
            if (childRecordsAnnotation!=null) {
                ChildRecord[] records = childRecordsAnnotation.value();
                if (records!=null && records.length>0) {
                    for (ChildRecord childRecord : records) {
                        lstSql.add(ELProperty.create(childRecord.sql()).getValue(this).toString());
                    }
                }
            }
        }
//        for charts
        if (showCharts) {
            Vector<ChartBean> vec = allChart();
            if (vec!=null) {
                for (ChartBean b : vec) {
                    if (b.useNative) {
                        if (b.sql instanceof String) {
                            lstSql.add("NATIVE-"+b.sql.toString());
                        }
                        else if (b.sql instanceof String[]) {
                            String[] tmp = (String[]) b.sql;
                            if (tmp!=null) {
                                for (String t : tmp) {
                                    lstSql.add("NATIVE-"+t);
                                }
                            }
                        }
                    }
                    else {
                        if (b.sql instanceof String) {
                            lstSql.add(b.sql.toString());
                        }
                        else if (b.sql instanceof String[]) {
                            String[] tmp = (String[]) b.sql;
                            if (tmp!=null) {
                                for (String t : tmp) {
                                    lstSql.add(t);
                                }
                            }
                        }
                    }
                }
            }
        }
//        for images
        if (showImages) {
            lstSql.add("SELECT a FROM ImageTable a WHERE a.recTable=\'" + this._Table() + "\' AND a.recordId=\'" + this.keyVal() + "\'");
        }
//        for files
        if (showFile) {
            lstSql.add("SELECT a FROM DocumentTable a WHERE a.recTable=\'" + this._Table() + "\' AND a.recordId=\'" + this.keyVal() + "\'");
        }
        if (lstSql!=null && !lstSql.isEmpty()) {
            Map<String, Object> map = DBClient.batchQuery(lstSql);
            cacheMap.putAll(map);
        }
    }

    public List<ImageTable> extractImages() {
    	if (AAAConfig.server) {
            return DBClient.getList("SELECT a FROM ImageTable a WHERE a.recTable='" + this._Table() + "' AND a.recordId='" + this.keyVal() + "'");
    	}
    	else {
            return (List) cacheMap.get("SELECT a FROM ImageTable a WHERE a.recTable='" + this._Table() + "' AND a.recordId='" + this.keyVal() + "'");
    	}
    }

    public List<DocumentTable> extractFiles() {
    	if (AAAConfig.server) {
            return DBClient.getList("SELECT a FROM DocumentTable a WHERE a.recTable='" + this._Table() + "' AND a.recordId='" + this.keyVal() + "'");
    	}
    	else {
            return (List) cacheMap.get("SELECT a FROM DocumentTable a WHERE a.recTable='" + this._Table() + "' AND a.recordId='" + this.keyVal() + "'");
    	}
    }

    public void extractServerChildrenRecords() {
        if (isEmptyKey()) return;
        ChildRecords childRecordsAnnotation = getClass().getAnnotation(ChildRecords.class);
        if (childRecordsAnnotation==null) return;
        ChildRecord[] records = childRecordsAnnotation.value();
        if (records!=null && records.length>0) {
            List<String> lstSql = new ArrayList<String>();
            for (ChildRecord childRecord : records) {
                lstSql.add(ELProperty.create(childRecord.sql()).getValue(this).toString());
            }
            Map<String, Object> map = DBClient.batchQuery(lstSql);
            cacheMap.putAll(map);
        }
    }

    public List selectListCache(Object... obj) {
        String sql = BeanUtil.concat(obj);
        if (!cacheMap.containsKey(sql)) {
        	cacheMap.put(sql, DBClient.getList(sql));
        }
        return (List) cacheMap.get(sql);
    }

    public static boolean recordExist(Object... obj) {
        String sql = BeanUtil.concat(obj);
        AbstractIBean b = (AbstractIBean) DBClient.getFirstRecord(sql);
        return (b != null && !b.isEmptyKey());
    }

    public boolean recordCacheExist(Object... obj) {
        String sql = BeanUtil.concat(obj);
        AbstractIBean b = null;
        if (!cacheMap.containsKey(sql)) {
            b = (AbstractIBean) DBClient.getFirstRecord(sql);
            cacheMap.put(sql, b);
        }
        return (b != null && !b.isEmptyKey());
    }

    public Object selectFirstCache(Object... obj) {
        String sql = BeanUtil.concat(obj);
        if (!cacheMap.containsKey(sql)) {
        	cacheMap.put(sql, DBClient.getFirstRecord(sql));
        }
        return cacheMap.get(sql);
    }

    public Object selectFirstColCache(Object... obj) {
        String sql = BeanUtil.concat(obj);
        if (!cacheMap.containsKey(sql)) {
        	cacheMap.put(sql, DBClient.getSingleColumn(sql));
        }
        return cacheMap.get(sql);
    }

    public List selectNativeListCache(String sql) {
        if (!cacheMap.containsKey(sql)) {
        	cacheMap.put(sql, DBClient.getListNative(sql));
        }
        return (List) cacheMap.get(sql);
    }

    public Object singleColumnCache(Object... obj) {
        String sql = BeanUtil.concat(obj);
        if (!cacheMap.containsKey(sql)) {
        	cacheMap.put(sql, DBClient.getSingleColumn(sql));
        }
        return cacheMap.get(sql);
    }

    public static void save(AbstractIBean bean) {
        bean.save();
    }

    public static void delete(AbstractIBean bean) {
        bean.delete();
    }

    public static void runSQL(String str) {
        DBClient.runSQL(str);
    }

    public static void runSQL(Object... obj) {
        DBClient.runSQL(obj);
    }

    public static void save(List lst) {
        DBClient.persistBean(lst);
    }

    public static void saveSubRecords(List saveRecords, List origRecords) {
        DBClient.saveSubRecords(saveRecords, origRecords);
    }

    public static AbstractIBean firstRecord(String... obj) {
        return (AbstractIBean) DBClient.getFirstRecord(obj);
    }

    public static List list(Object... obj) {
        return DBClient.getList(obj);
    }

    public static List nativeList(String obj) {
        return DBClient.getListNative(obj);
    }

    public static Object singleColumn(String string) {
        return DBClient.getSingleColumn(string);
    }

    public AbstractIBean find(Object obj) {
        return (AbstractIBean) DBClient.getFirstRecord(extractBeanClass(), obj);
    }

    public static AbstractIBean find(Class cls, Object obj) {
        return (AbstractIBean) DBClient.getFirstRecord(extractBeanClass(cls), obj);
    }

    private Class extractBeanClass() {
        return extractBeanClass(this.getClass());
    }

    public static Class extractBeanClass(Class cls) {
        if (cls.getSimpleName().endsWith("Ext")) {
            //not the bean we need
            return cls.getSuperclass();
        }
        return cls;
    }

    @Override
    public ReturnStruct callService(ParamStruct param) {
//        throw new UnsupportedOperationException("Not supported yet.");
        //all beans can be service
        return null;
    }

    public String extractCounterPostAccountNumber() {
        return "DELETE";
    }

    public String bookmarkText() {
        return toString();
    }

    public final void setup() {
        Class cls = extractBeanClass();
        AbstractIBean bean = (AbstractIBean) DBClient.getFirstRecord("SELECT a FROM " + cls.getSimpleName() + " a");
        if (bean == null) {
            runSetup();
        }
    }

    protected void runSetup() {
    }

//    public String extractXML() {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        XMLEncoder x = new XMLEncoder(new BufferedOutputStream(baos));
//        x.writeObject(this);
//        x.close();
//        String str = new String(baos.toByteArray());
//        return str.trim();
//    }
//
//    public void loadXML(String xml) {
//        ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
//        XMLDecoder x = new XMLDecoder(bais);
//        Object obj = x.readObject();
//        x.close();
//        BeanUtil.copyBean((IBean) obj, this);
//    }
//
    List includeSave = new ArrayList();
    public void includeSave(List obj) {
        includeSave.add(obj);
    }
    
    public void includeSave(AbstractIBean obj) {
        includeSave.add(obj);
    }

    public void runIncludeSave() {
        if (includeSave.isEmpty()) return;
        for (Object object : includeSave) {
            if (object instanceof List) {
                DBClient.persistBean((List)object);
            }
            else if (object instanceof AbstractIBean) {
                DBClient.persistBean((AbstractIBean)object);
            }
        }
    }
    
    public int pageSize() {
        return 200;
    }
    
    @Transient
    public boolean includeSearch = true;

    static Map<String, AbstractIBean> mapBeans = new HashMap();
    private static AbstractIBean extractCacheBeans(Class b, String code) {
        AbstractIBean bean = mapBeans.get(b.getSimpleName()+code);
        if (bean==null) {
            List<AbstractIBean> lst = extractCacheListBeans(b);
            for (AbstractIBean ib : lst) {
                String c = ib.keyVal().toString();
                mapBeans.put(b.getSimpleName()+c, ib);
            }
            bean = mapBeans.get(b.getSimpleName()+code);
        }
        return bean;
    }

    static Map<String, List> mapListBeans = new HashMap();
    public static List extractCacheListBeans(Class b) {
        List bean = mapListBeans.get(b.getSimpleName());
        if (bean==null || bean.isEmpty()) {
            try {
                List lst = DBClient.getList("SELECT a FROM "+b.getSimpleName()+" a",0,1000);
//                System.out.println("CACHE BEANS: "+b.getSimpleName()+" SIZE: "+lst.size());
                mapListBeans.put(b.getSimpleName(), lst);
                bean = mapListBeans.get(b.getSimpleName());
            }
            catch (Exception e) {
            }
        }
        return bean;
    }

    public boolean cacheClient() {
        return false;
    }

    @Transient
    public boolean showImages = true;
    @Transient
    public boolean showFile = true;
    @Transient
    public boolean showSubrecords = true;
    @Transient
    public boolean showCharts = true;

    public static double formatMoney(double d) {
        return DataUtil.getMoneyFormat(d);
    }
    
    public void copyFrom(AbstractIBean bean) {
    	String keyVal = this.getKeyVal();
    	BeanUtil.copyBean(bean, this);
    	this.setKeyVal(keyVal);
    }
    
    public String addWhere() {
    	return "";
    }
    
    public void setupIndex() {
//    	this must call the runindex
    }
    
    protected void runIndex(int index, String... fields) {
    	StringBuffer sb = new StringBuffer();
    	sb.append("ALTER TABLE ").
    		append(_Table()).
    		append(" ADD INDEX ").
    		append("IDX").append(index).append("_").append(_Table());
		sb.append(" (");
    	for (int i=0; i<fields.length; i++) {
    		sb.append(fields[i]);
    		if (i+1<fields.length) {
    			sb.append(",");
    		}
    	}
		sb.append(");");
		DBClient.runSQLNative(sb.toString());
    }
    
    protected void runUniqueIndex(int index, String... fields) {
    	StringBuffer sb = new StringBuffer();
    	sb.append("ALTER TABLE ").
    		append(_Table()).
    		append(" ADD UNIQUE INDEX ").
    		append("IDX").append(index).append("_").append(_Table());
		sb.append(" (");
    	for (int i=0; i<fields.length; i++) {
    		sb.append(fields[i]);
    		if (i+1<fields.length) {
    			sb.append(",");
    		}
    	}
		sb.append(");");
		DBClient.runSQLNative(sb.toString());
    }

    public static double doubleVal(double d) {
    	return DataUtil.getMoneyFormat(d);
    }
    
    public static int roundVal(double d) {
    	return (int) Math.round(d);
    }

    public static boolean isEmpty(String s) {
    	return (s==null || s.trim().isEmpty());
    }

    public static boolean notEmpty(String s) {
    	return !(s==null || s.trim().isEmpty());
    }
    
    public String extractPersonName(int personId) {
    	if (personId>0) {
        	Person p = (Person) DBClient.getFirstRecord("SELECT a FROM Person a WHERE a.personId="+personId);
        	if (p!=null) return p.toString();
    	}
    	return null;
    }

    public Person extractPerson(int personId) {
    	if (personId>0) {
        	return (Person) DBClient.getFirstRecord("SELECT a FROM Person a WHERE a.personId="+personId);
    	}
    	return null;
    }
    
    public String concat(Object... arr) {
    	return BeanUtil.concat(arr);
    }
    
    public boolean isSuperBean() {
		return (this._Table().equals(this.getClass().getSimpleName()));
    }
    
    public Map<String,String> extractReportParameter() {
    	return new HashMap();
    }
    
    static Map<String, List> listCache = new HashMap<String, List>();
    public static List listCache(String sql) {
    	List lst = listCache.get(sql);
    	if (lst==null) {
    		lst = DBClient.getList(sql);
    		listCache.put(sql, lst);
    	}
        return lst;
    }

    static Map<String, Object> objCache = new HashMap<String, Object>();
    public static Object objCache(String sql) {
    	Object lst = objCache.get(sql);
    	if (lst==null) {
    		lst = DBClient.getFirstRecord(sql);
    		objCache.put(sql, lst);
    	}
        return lst;
    }

}
