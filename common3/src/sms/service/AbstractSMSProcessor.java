/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sms.service;

import bean.admin.SMSMessageBean;
import sms.SMSServer;
import util.BeanUtil;
import util.Log;

/**
 *
 * @author Charliemagne Mark
 */
public abstract class AbstractSMSProcessor {
    protected SMSServer server;
    protected SMSMessageBean bean;
    public abstract void run();
    
    public static AbstractSMSProcessor getInstance(SMSServer server, String code, SMSMessageBean bean) {
        AbstractSMSProcessor process = null;
        try {
            process = (AbstractSMSProcessor) Class.forName(BeanUtil.concat("sms.service.",code)).newInstance();
            process.bean =  bean;
            process.server = server;
        }
        catch(Exception e) {
            Log.severe(e.getMessage());
        }
        return process;
    }
    
    protected void returnMessage(String message) {
        
    }
}
