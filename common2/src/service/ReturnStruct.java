/*
 * ReturnStruct.java
 * 
 * Created on Sep 16, 2007, 11:26:34 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import constants.Constants;

/**
 *
 * @author Budoy Entokwa
 */
public class ReturnStruct extends ParamStruct {

    private int status = Constants.FAIL;
    private Object data;

    public static ReturnStruct createInstance(Object data) {
        ReturnStruct ret = new ReturnStruct();
        ret.status = constants.Constants.SUCCESS;
        ret.data = data;
        return ret;
    }
    
    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
