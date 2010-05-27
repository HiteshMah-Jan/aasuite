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
public class ACR extends AbstractSMSProcessor {
    //Bank payment ACR <Reference Number> <Amount>
    //Reservation for enrollment
    /**
     * 1. Send ACR to the mobile number of school or company
     * 2. System will store the message to SMS Message Bean
     *  a. Mobile number will be checked according to Person mobile number
     * 3. Agent will check payment
     * 4. Agent will mark the registration once check from the bank
     */
    @Override
    public void run() {
        String message = this.bean.message;
        String[] arr = message.split(" ");
        String code = arr[0];
        String billType = arr[1];
        String refNumber = arr[2];
        String amt = arr[3];
        
        this.bean.code1 = code;
        this.bean.code3 = refNumber;
        //check if this has been sent already
        String sql = BeanUtil.concat("SELECT a FROM SMSMessageBean a WHERE a.phoneNumber='",this.bean.phoneNumber,"' AND a.code1='",this.bean.code1,"' AND a.code3='",this.bean.code3,"'");
        this.bean = (SMSMessageBean) DBClient.getFirstRecord(sql);
        this.bean.code2 = billType;
        this.bean.code4 = amt;
        this.bean.save();
        //this may acknowledge the message
//        if (AppConfig.isTrue("SMS ACRREPLY", true)) {
//            this.server.addtoReply(this.bean.phoneNumber, AppConfig.getValue("SMS", "ACR", "Bank payment received for reservation. Payment will be verified in 2 days. Thank you."));
//        }
    }
}