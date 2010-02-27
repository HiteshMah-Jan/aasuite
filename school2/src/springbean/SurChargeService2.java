/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springbean;

import java.util.Timer;

import service.ParamStruct;
import service.ReturnStruct;


/**
 * 
 * @author alex
 */
public class SurChargeService2 extends SurChargeService {
	static boolean serviceRunning2 = false;
	
	public int getHour() {
		return 4;
	}
	
	public ReturnStruct callService(ParamStruct param) {
		if (serviceRunning2) {
			System.out.println("SURCHARGE SERVICE 2 IS ALREADY RUNNING."+" - "+constants.Constants.useDate);
			return null;
		}
		if (AAAConfig.server) {
			System.out.println("SURCHARGE SERVICE 2 CALLED."+" - "+constants.Constants.useDate);
//			every hour
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(this, 1000*10, 1000 * 60 * 60);
			process();
			serviceRunning2 = true;
		}
		return null;
	}
}
