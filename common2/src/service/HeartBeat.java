package service;

import service.util.CallService;
import constants.Constants;

public class HeartBeat implements IService {
	public static boolean isAlive() {
		ReturnStruct ret = CallService.callService("", 1, HeartBeat.class.getName());
		if (ret==null || ret.getData()==null) {
			return false;
		}
		boolean b = (Boolean) ret.getData();
		return b;
	}
	
	@Override
	public ReturnStruct callService(ParamStruct param) {
		ReturnStruct r = new ReturnStruct();
		r.setStatus(Constants.SUCCESS);
		r.setData(new Boolean(true));
		return r;
	}

}
