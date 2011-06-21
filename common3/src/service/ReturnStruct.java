/*
 * ReturnStruct.java
 * 
 * Created on Sep 16, 2007, 11:26:34 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


/**
 *
 * @author Budoy Entokwa
 */
public class ReturnStruct extends ParamStruct {
	public ReturnStruct() {
        setStatus(constants.Constants.SUCCESS);
	}
	
	public static ReturnStruct createInstance(Object data) {
        ReturnStruct ret = new ReturnStruct();
        ret.setData(data);
        return ret;
    }
}
