/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.TimerTask;
import service.util.CallService;

/**
 *
 * @author asmiranda
 */
public abstract class ServiceWrapper extends TimerTask implements IService {
    public ReturnStruct callMe(int command, Object data) {
        return CallService.callService(data, command, getClass().getName());
    }
}
