/*
 * CallService.java
 * 
 * Created on Sep 23, 2007, 10:16:24 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.util;

import service.ParamStruct;
import service.ReturnStruct;
import service.ServiceAdapter;
import util.NetworkUtil;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class CallService {

    public static ReturnStruct callService(Object data, int command, String serviceName) {
    	PanelUtil.displayWaitMessage("Please wait...");
    	ParamStruct param = new ParamStruct();
		param.setActionCommand(command);
		param.setServiceName(serviceName);
		param.setData(data);
		ReturnStruct ret = ServiceAdapter.getInstance().call(param);
		PanelUtil.hideWaitMessage();
		return ret;
    }

    public static ReturnStruct callService(Object data, String sql, int command, String serviceName) {
    	PanelUtil.displayWaitMessage("Please wait...");
		ParamStruct param = new ParamStruct();
        param.setStation(NetworkUtil.getHostname());
        param.setMacAddress(NetworkUtil.macAddress());
		param.setActionCommand(command);
		param.setServiceName(serviceName);
		param.setData(data);
		param.setHelperSQL(sql);
		ReturnStruct ret = ServiceAdapter.getInstance().call(param);
		PanelUtil.hideWaitMessage();
		return ret;
    }
}
