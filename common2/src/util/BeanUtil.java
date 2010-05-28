/*
 * BeanUtil.java
 *
 * Created on Sep 8, 2007, 6:02:18 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.beansbinding.BeanProperty;
import org.springframework.beans.*;
import service.util.AbstractIBean;
import service.util.IBean;

/**
 *
 * @author Budoy Entokwa
 */
public class BeanUtil {

    public BeanUtil() {
    }

    public static String getPrintedBean(IBean bean) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(bean);
        StringBuffer sb = new StringBuffer();
        String fieldName = null;
        Object fieldValue = null;
        PropertyDescriptor[] descriptors = beanWrapper.getPropertyDescriptors();
        sb.append("BEAN [").append(bean.getClass().getSimpleName()).append("]\n");
        for (PropertyDescriptor propertyDescriptor : descriptors) {
            fieldName = propertyDescriptor.getName();
            try {
                if ("|HTMLDocument|class|column1|column2|column3|column4|column5|column6|column7|column8|column9|column10|comboDisplay|config|emptyKey|formattedTitle|formulaValue|imageList|keyVal|me|pictureHTML|tableColumns|".indexOf(BeanUtil.concat("|",fieldName,"|"))==-1) {
                    fieldValue = getPropertyValue(bean, fieldName);
                    sb.append(fieldName).append("\t=\t").append(fieldValue).append("\n");
                }
            }
            catch (Exception e) {
            }
        }
        return sb.toString();
    }

    public static IBean getBeanFromMap(Map map) {
        return null;
    }

    public static List getBeanList(IBean bean, List lst) {
        if (lst == null) {
            return null;
        }
        List<IBean> retList = new ArrayList<IBean>();
        for (int i = 0; i < lst.size(); i++) {
            java.util.Map map = (java.util.Map) lst.get(i);
            retList.add(getBeanFromMap(bean, map));
        }
        return retList;
    }

    public static IBean getBeanFromMap(IBean beanType, Map map) {
        try {
            service.util.IBean tmpBean = beanType.getClass().newInstance();
            BeanWrapper beanWrapper = new BeanWrapperImpl(tmpBean);

            java.util.Iterator iter = map.keySet().iterator();
            while (iter.hasNext()) {
                java.lang.String key = (java.lang.String) iter.next();
                beanWrapper.setPropertyValue(getProperName(key), map.get(key));
            }
            return tmpBean;
        } catch (InstantiationException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Object getKeyValue(IBean bean) {
        try {
            return getPropertyValue(bean, bean._Key());
        } catch (Exception e) {
            Logger.getLogger("global").log(Level.SEVERE, null, e);
            return null;
        }
    }

    public static Object getPropertyValue(IBean bean, Object... propertyName) {
        return BeanProperty.create(BeanUtil.concat(propertyName)).getValue(bean);
    }

    public static double getDoubleValue(IBean bean, String... propertyName) {
        return Double.parseDouble(BeanProperty.create(BeanUtil.concat(propertyName)).getValue(bean).toString());
    }

    public static Class getPropertyType(IBean bean, String... propertyName) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(bean);
        if (beanWrapper.getPropertyDescriptor(BeanUtil.concat(propertyName)) != null) {
            return beanWrapper.getPropertyDescriptor(BeanUtil.concat(propertyName)).getPropertyType();
        } else {
            return String.class;
        }
    }

    public static void setPropertyValue(IBean bean, String propertyName, Object value) {
        if (!bean.canSet(propertyName)) return;
        BeanWrapper beanWrapper = new BeanWrapperImpl(bean);
        Class cls = getPropertyType(bean, propertyName);
        if (cls==Integer.class || cls==int.class) {
            beanWrapper.setPropertyValue(propertyName, Integer.parseInt(value.toString()));
        }
        else {
            beanWrapper.setPropertyValue(propertyName, value);
        }
    }

    public static void setShowPropertyValue(IBean bean, String propertyName, Object value) {
//        Log.out(propertyName,"-",value);
        setPropertyValue(bean, propertyName, value);
    }

    private static Map mapName = new HashMap();

    @SuppressWarnings(value = "unchecked")
    private static String getProperName(String name) {
        //this is not very fast so we need to cache
        if (mapName.get(name) != null) {
            return (String) mapName.get(name);
        }
        if (name != null && name.indexOf("_") != -1) {
            StringBuffer sb = new StringBuffer();
            String[] strArr = name.split("_");
            String str = null;
            for (int i = 0; i < strArr.length; i++) {
                if (i == 0) {
                    sb.append(strArr[i]);
                } else {
                    str = strArr[i];
                    sb.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1));
                }
            }
            mapName.put(name, sb.toString());
            return sb.toString();
        }
        return name;
    }

    public static String getPropertyName(String name, IBean bean) {
        BeanWrapper wrapper = new BeanWrapperImpl(bean);
        String properName = getProperName(name);
        Method[] methods = bean.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String methodName = method.getName();
            if (BeanUtil.concat("get",properName).equalsIgnoreCase(methodName)) {
                methodName = methodName.substring("get".length());
                methodName = BeanUtil.concat(Character.toLowerCase(methodName.charAt(0)),methodName.substring(1));
                return methodName;
            }
        }
        return properName;
    }

    public static Object toObject(byte[] bytes) {
        Object object = null;
        try {
            object = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(bytes)).readObject();
        } catch (java.lang.Exception e) {
            Logger.getLogger("global").log(Level.SEVERE, null, e);
        }
        return object;
    }

    public static byte[] toByte(Object value) {
        java.io.ObjectOutputStream oStream = null;
        try {
            //Need to decode to string
            java.io.ByteArrayOutputStream bStream = new java.io.ByteArrayOutputStream();
            oStream = new java.io.ObjectOutputStream(bStream);
            oStream.writeObject(value);
            byte[] byteVal = bStream.toByteArray();
            return byteVal;
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        } finally {
            try {
                oStream.close();
            } catch (IOException ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static void copyBean(IBean source, IBean dest) {
        if (source!=null && dest!=null) {
        	BeanUtils.copyProperties(source, dest);
        }
    }

    public static void removeBeanId(AbstractIBean source) {
        String key = source._Key();
        BeanUtil.setPropertyValue(source, key, null);
    }

    public static void addCollection(Collection dest, Collection source) {
        for (Object obj : source) {
            if (!dest.contains(obj)) {
                dest.add(obj);
            }
        }
    }

    public static byte[] getBytes(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return str.getBytes();
    }
    
    public static String getString(byte[] bytes) {
        if (bytes==null) return "";
        return new String(bytes);
    }

    public static boolean isExtension(IBean bean) {
        return bean.getClass().getSimpleName().toUpperCase().endsWith("EXT");
    }
    
    public static IBean getTrueIBean(IBean bean) {
        if (isExtension(bean)) {
            IBean newbean = (IBean) getBeanInstance(bean.getClass());
            copyBean(bean, newbean);
        }
        return bean;
    }
    
    public static Object getBeanInstance(Class cls) {
        try {
            if (cls == null) {
                return null;
            }
            Class clsTmp = getEntityClass(cls);
            Object obj = clsTmp.newInstance();
            return obj;
        } catch (InstantiationException ex) {
            Logger.getLogger(BeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BeanUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Class getEntityClass(Class cls) {
        if (cls==null) return null;
        String name = cls.getSimpleName();
        Class clsTmp = cls;
        while (name.toUpperCase().endsWith("EXT")) {
            name = clsTmp.getSuperclass().getSimpleName();
            clsTmp = clsTmp.getSuperclass();
        }
        return clsTmp;
    }

    public static String getEntityName(Class cls) {
        return getEntityClass(cls).getSimpleName();
    }
    
    public static String concat(Object... strArr) {
    	if (strArr.length == 1) {
    		return strArr[0].toString();
    	}
        StringBuffer sb = new StringBuffer();
        for (Object string : strArr) {
        	if (string==null || string.toString().isEmpty()) {
                sb.append(" ");
        	}
        	else {
                sb.append(string);
        		
        	}
        }
        return sb.toString();
    }

    public static String concatStr(String... strArr) {
    	if (strArr.length == 1) {
    		return strArr[0].toString();
    	}
        StringBuffer sb = new StringBuffer();
        for (Object string : strArr) {
            sb.append(string);
        }
        return sb.toString();
    }
    
    public static String concatFromTo(AbstractIBean b, String fieldName, int start, int end) {
        StringBuffer sb = new StringBuffer();
        for (int i=start; i<end; i++) {
            sb.append(BeanUtil.getPropertyValue(b, BeanUtil.concat(fieldName,i))).append(",");
        }
        return sb.toString();
    }

    public static double getSum(List<Object> lst, String field) {
        double d = 0;
        for (Object bean : lst) {
            try {
                d += (Double) BeanUtil.getPropertyValue((IBean)bean, field);
            }
            catch (Exception e) {                
            }
        }
        return d;
    }
}
