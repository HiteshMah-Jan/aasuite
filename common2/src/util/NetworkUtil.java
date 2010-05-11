/*
 * NetworkUtil.java
 *
 * Created on Feb 23, 2008, 6:17:53 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import service.ParamStruct;
import constants.Constants;
/**
 *
 * @author Entokwaa
 */
public class NetworkUtil {
    private static String macAddress;
    private static String hostName;
    private static String ipAddress;

    public static void main(String[] args) {
        System.out.println("HOST = "+getHostname());
        System.out.println("MAC = "+macAddress());
    }

    public static String macAddress() {
        if (macAddress!=null) return macAddress;
        StringBuffer sb = new StringBuffer();
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            for (int i = 0; mac!=null && i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        macAddress = sb.toString();
        return sb.toString();
    }

    public static String getIpAddress() {
        if (ipAddress!=null) return ipAddress;
        try {
            java.net.InetAddress addr = java.net.InetAddress.getLocalHost(); 
            ipAddress = addr.getHostAddress();
        } catch (Exception e) {
        }
        return ipAddress;
    }

    public static String getHostname() {
        if (hostName!=null) return hostName;
        try {
            java.net.InetAddress addr = java.net.InetAddress.getLocalHost(); 
            hostName = addr.getHostName();
        } catch (Exception e) {
        }
        return hostName;
    }
    
    public static Object requestCache(String key, Object data) {
    	List lst = new ArrayList();
    	lst.add(Constants.host);
    	lst.add(key);
    	if (data != null) {
    		lst.add(data);
    	}
    	return requestCache(lst);
    }
    
    public synchronized static Object requestCache(List lst) {
    	Object retlst = null;
        try {
            URL hp = new URL(Constants.cachehost);
            URLConnection hpcon = hp.openConnection();
            hpcon.setDoInput(true);
            hpcon.setDoOutput(true);
            hpcon.setReadTimeout(600000);
            hpcon.setRequestProperty("Content-Type", "application/octet-stream");

            if (lst.size() > 2) {
            	Object o = ZipUtil.getBytes(lst.get(2));
            	lst.set(2, o);
            }
            write(hpcon.getOutputStream(), lst);
            List l = (List) read(hpcon.getInputStream());
            retlst = l.get(0);
            if (retlst != null) {
            	retlst = ZipUtil.getObject((byte[])retlst);
            }
        } catch (MalformedURLException ex) {
            PanelUtil.showError(null, "URL NOT FOUND "+Constants.cachehost+" TRY TO USE LOCAL.");
        } catch (IOException ex) {
        	ex.printStackTrace();
            Logger.getLogger("global").log(Level.SEVERE, ex.getMessage()+" - "+Constants.host);
        }
        return retlst;
    }
    public static Object read(InputStream is) {
    	Object obj = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(is);
            obj = ZipUtil.getUnzipObject((byte[])ois.readObject());
        } catch (Exception ex) {
            Logger.getLogger(ParamStruct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
                is.close();
            } catch (Exception ex) {
                Logger.getLogger(ParamStruct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }
    public static void write(OutputStream os, Object param) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(ZipUtil.getZipBytes(param));
        } catch (IOException ex) {
            Logger.getLogger(ParamStruct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.flush();
                oos.close();
                os.flush();
                os.close();
            } catch (Exception ex) {
                Logger.getLogger(ParamStruct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
