/*
 * NetworkUtil.java
 *
 * Created on Feb 23, 2008, 6:17:53 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

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
    	PerfUtil p = new PerfUtil("Performance of Cache...");
    	p.start();
        Log.out("HOST = ",getHostname());
        Log.out("MAC = ",macAddress());
        Constants.host = "local";
        Constants.cachehost = "http://www.mysoftlabs.com/cache.php";
        requestCache("testing", "testing");
        for (int i=0; i<100; i++) {
            Object obj1 = requestCache("testing", null);
            Log.info(obj1," - ",i);
            p.printSpan();
        }
        p.printSpanComplete();
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
    	if (Constants.cachehost == null || Constants.cachehost.length() < 10) {
    		return null;
    	}
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("host", Constants.host);
    	map.put("key", key);
    	if (data != null) {
        	map.put("hasData", "1");
        	map.put("data", data);
    	}
    	else {
        	map.put("hasData", "0");
    	}
    	Object retlst = null;
    	InputStream is = null;
        try {
    		ClientHttpRequest req = new ClientHttpRequest(Constants.cachehost);
        	is = req.post(map);
        	byte[] b = DataUtil.getBytes(is);
        	if (b.length > 10) {
            	retlst = ZipUtil.getObject(b);
        	}
        } catch (MalformedURLException ex) {
            PanelUtil.showError(null, "CACHE URL NOT FOUND ",Constants.cachehost," TRY TO USE LOCAL.");
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return retlst;
    }
}
