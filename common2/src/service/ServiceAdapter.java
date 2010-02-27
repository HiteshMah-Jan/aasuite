/*
 * ServiceAdapter.java
 *
 * Created on Sep 16, 2007, 9:21:16 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import component.SpringCall;
import constants.Constants;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import springbean.AAAConfig;
import util.BeanUtil;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class ServiceAdapter {
    private static int USE_SERVLET = 0;
    private static int USE_WEBSERVICE = 1;

    public static ServiceAdapter getInstance() {
        return new ServiceAdapter();
    }

    public ReturnStruct call(ParamStruct param) {
        ReturnStruct ret = null;
        if (this.getUseService() == USE_WEBSERVICE) {
            byte[] b = BeanUtil.toByte(param);
            ServerService ws = new ServerService();
            Server server = ws.getServerPort();
            ret = (ReturnStruct) BeanUtil.toObject((byte[]) server.call(b));
        } else {
            if (AAAConfig.server) {
                try {
//                    System.out.println("CONNECT TO LOCAL.....");
                    String serviceName = param.getServiceName();
                    IService service = SpringCall.getService(serviceName);
                    ret = service.callService(param);
                } catch (Exception e) {
                    e.printStackTrace();
                    ret = new ReturnStruct();
                }
            } else {
                try {
//                    System.out.println("CONNECT TO SERVER.....\n"+param.getData());
                    URL hp = new URL(Constants.host);
                    URLConnection hpcon = hp.openConnection();
                    hpcon.setDoInput(true);
                    hpcon.setDoOutput(true);
                    hpcon.setReadTimeout(600000);
                    hpcon.setRequestProperty("Content-Type", "application/octet-stream");
                    hpcon.setRequestProperty("Content-Length", param.size()+"");

                    ParamStruct.write(hpcon.getOutputStream(), param);
                    ret = (ReturnStruct) ParamStruct.read(hpcon.getInputStream());
                    Logger.getLogger("global").log(Level.FINE, "SETUP OK - "+Constants.host);
                } catch (MalformedURLException ex) {
                    PanelUtil.showError(null, "URL NOT FOUND "+Constants.host+" TRY TO USE LOCAL.");
                } catch (IOException ex) {
                	ex.printStackTrace();
                    Logger.getLogger("global").log(Level.SEVERE, ex.getMessage()+" - "+Constants.host);
                }
            }
        }
        return ret;
    }

    public int getUseService() {
        //Need some manipulation
        return USE_SERVLET;
    }
}
