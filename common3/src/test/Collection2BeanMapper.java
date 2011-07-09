/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import service.util.IBean;
import util.BeanUtil;
import util.Log;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
//note: we need to have List<List<Object>>
public class Collection2BeanMapper {
    public static Collection2BeanMapper getInstance() {
        return new Collection2BeanMapper();
    }
    
    public List<List> getMapping(List<List> coll, String... beanMap) {
        List<List> retList = new ArrayList<List>();
        for (List list : coll) {
            List lst = new ArrayList();
            retList.add(lst);
            int start = 0;
            for (int i = 0; i < beanMap.length; i++) {
                String string = beanMap[i];
                lst.add(mapBean(start, list, string));
                start += getArrayLength(string);
            }
        }
        return retList;
    }
    
    private Object mapBean(int start, List lstRow, String mapping) {
        try {
            String[] beanMap = getArray(mapping);
            String beanClass = getBean(mapping);
            Object bean = PanelUtil.getBeanClass(beanClass).newInstance();
            for (int i = 0; i < beanMap.length; i++) {
                String field = "";
                Object value = null;
                try {
                    field = beanMap[i];
                    value = lstRow.get(start+i);
                    if (value!=null && value instanceof String) {
                        value = value.toString().toUpperCase();
                    }
                    BeanUtil.setPropertyValue((IBean)bean, field, value);
                }
                catch (Exception e) {
                	Log.out("...MAPPING EXCEPTION ","-",field,":",value);
                }
            }
            return bean;
        } catch (Exception ex) {
            Log.out("EXCEPTION ",ex.getMessage());
        }
        return null;
    }

    private String getBean(String mapping) {
        return mapping.substring(0, mapping.indexOf(","));
    }

    private String[] getArray(String mapping) {
        StringTokenizer st = new StringTokenizer(mapping, ",");
        st.nextToken(); //remove the beanname
        String[] str = new String[st.countTokens()];
        for(int i=0; st.hasMoreTokens(); i++) {
            str[i] = st.nextToken();
        }
        return str;
    }
    
    private int getArrayLength(String mapping) {
        StringTokenizer st = new StringTokenizer(mapping, ",");
        return st.countTokens()-1;
    }

    public static Class[] getClassFromMapping(String[] mapping) {
        List<Class> lstCls = new ArrayList<Class>();
        Collection2BeanMapper collMapper = Collection2BeanMapper.getInstance();
        for (String string : mapping) {
            String bean = collMapper.getBean(string);
            String[] fieldArr = collMapper.getArray(string);
            
            try {
                Class cls = PanelUtil.getBeanClass(bean);
                for (String string1 : fieldArr) {
                    try {
                        Class fieldCls = cls.getField(string1).getType();
                        lstCls.add(fieldCls);
                    }
                    catch (Exception e) {
                        lstCls.add(String.class);
                        Log.out("FIELD EXCEPTION - ",string1);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        Class[] clsArr = new Class[lstCls.size()];
        for (int i = 0; i < clsArr.length; i++) {
            Class class1 = lstCls.get(i);
            clsArr[i] = class1;
        }
        return clsArr;
    }
}
