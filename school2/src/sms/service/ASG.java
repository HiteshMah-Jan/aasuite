/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sms.service;

import bean.admin.AppConfig;
import bean.admin.SMSMessageBean;
import util.BeanUtil;
import util.DBClient;

/**
 *
 * @author Charliemagne Mark
 */
public class ASG extends AbstractSMSProcessor {
    //Assignment ASG
    /**
     * 1. Send ACG to the mobile number of school or company
     * 2. System will store the message to SMS Message Bean
     *  a. Mobile number will be checked according to Person mobile number
     * 3. System will send assignment
     */
    @Override
    public void run() {
        String message = this.bean.message;
        String[] arr = message.split(" ");
        String code = arr[0];
        String email = arr[1];
        
        this.bean.code1 = code;
        this.bean.code2 = email;
        //check if this has been sent already
        String sql = BeanUtil.concat("SELECT a FROM SMSMessageBean a WHERE a.phoneNumber='",this.bean.phoneNumber,"' AND a.code1='",this.bean.code1,"'");
        this.bean = (SMSMessageBean) DBClient.getFirstRecord(sql);
        this.bean.save();
        //this may acknowledge the message
//        if (AppConfig.isTrue("SMS ASGREPLY", true)) {
//            this.server.addtoReply(this.bean.phoneNumber, getAssignment(this.bean.phoneNumber, this.bean.code2));
//        }
    }
    
    protected String getAssignment(String phoneNumber, String email) {
        //this must extract data from the assignment
        //send to phone and send to emails
        return "Fully Paid. Thank you.";
    }
}
       