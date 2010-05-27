/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.Log;

/**
 *
 * @author alex
 */
public class ServerCall {
    public static void main(String[] str) {
        new ServerCall().multiCall();
    }
    
    public void multiCall() {
        addCall("test.ServerCall","multiCall1", "");
        addCall("test.ServerCall","multiCall2", "");
        addCall("test.ServerCall","multiCall3", "");
        List lst1 = serveCall(lst);
        for (Object object : lst1) {
            Log.out("RETURN === ",object);
        }
    }
    
    public List serveCall(List<mycall> lst) {
        List retLst = new ArrayList();
        for (mycall c : lst) {
            try {
                Object obj = Class.forName(c.cls).newInstance();
                Object param = c.obj;
                Method m = getMethod(c.method);
                int size = m.getParameterTypes().length;
                retLst.add(m.invoke(obj, param));
            } catch (Exception ex) {
                Logger.getLogger(ServerCall.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retLst;
    }
    
    private Method getMethod(String method) {
        Method[] mets = this.getClass().getMethods();
        for (Method m : mets) {
            if (method.equals(m.getName())) return m;
        }
        return null;
    }
    
    List<mycall> lst = new ArrayList<mycall>();
    public void addCall(String cls, String method, Object param) {
        lst.add(new mycall(cls, method, param));
    }
    
    public String multiCall1(Object obj) {
        Log.out("multicall1");
        return "Test OK";
    }
    public int multiCall2(Object obj) {
        Log.out("multicall2");
        return 2;
    }
    public double multiCall3(Object obj) {
        Log.out("multicall3");
        return 2.5;
    }
    
    static class mycall {
        String cls;
        String method;
        Object obj;
        mycall(String cls, String method, Object... obj) {
            this.cls = cls;
            this.method = method;
            this.obj = obj;
        }
    }
}
