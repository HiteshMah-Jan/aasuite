/*
 * ParamStruct.java
 * 
 * Created on Sep 16, 2007, 11:26:25 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import constants.Constants;

import util.ZipUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class ParamStruct implements Serializable {

    private String serviceName;
    private Object data;
    private int actionCommand;
    private String helperSQL;
    private String station;
    private String macAddress;
    private int status = Constants.FAIL;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getHelperSQL() {
        return helperSQL;
    }

    public void setHelperSQL(String helperSQL) {
        this.helperSQL = helperSQL;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getActionCommand() {
        return actionCommand;
    }

    public void setActionCommand(int actionCommand) {
        this.actionCommand = actionCommand;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public int size() {
        ObjectOutputStream os = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            os = new ObjectOutputStream(baos);
            os.writeObject(this);
            int size = baos.toByteArray().length;
            return size;
        } catch (IOException ex) {
            Logger.getLogger(ParamStruct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                baos.flush();
                baos.close();
                os.flush();
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(ParamStruct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public static ParamStruct read(InputStream is) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(is);
            ParamStruct param = (ParamStruct) ZipUtil.getUnzipObject((byte[])ois.readObject());
            return param;
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
        return null;
    }
    
    public static void write(OutputStream os, ParamStruct param) {
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
