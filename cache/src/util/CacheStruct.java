/*
 * ParamStruct.java
 * 
 * Created on Sep 16, 2007, 11:26:25 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;

/**
 *
 * @author Budoy Entokwa
 */
public class CacheStruct implements Serializable {

    private String serviceName;
    private String key;
    private Object data;

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getKey() {
        return key;
    }

    public Object getData() {
        return data;
    }

    public static CacheStruct read(InputStream is) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(is);
            List lst = (List) ZipUtil.getUnzipObject((byte[]) ois.readObject());
            CacheStruct param = new CacheStruct();
            param.serviceName = lst.get(0).toString();
            param.key = lst.get(1).toString();
            if (lst.size() > 2) {
                param.data = lst.get(2);
            }
            return param;
        } catch (Exception ex) {
            Logger.getLogger(CacheStruct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
                is.close();
            } catch (Exception ex) {
                Logger.getLogger(CacheStruct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static void write(ServletOutputStream os, byte[] data) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            List lst = new ArrayList();
            if (data == null) {
                lst.add(null);
            } else {
                lst.add(ZipUtil.getObject(data));
            }
            oos.writeObject(ZipUtil.getZipBytes(lst));
        } catch (IOException ex) {
            Logger.getLogger(CacheStruct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.flush();
                oos.close();
                os.flush();
                os.close();
            } catch (Exception ex) {
                Logger.getLogger(CacheStruct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
