/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sms.service;

import bean.admin.AppConfig;
import bean.admin.SMSMessageBean;
import util.DBClient;

/**
 *
 * @author Charliemagne Mark
 */
public class ACB extends AbstractSMSProcessor {
    //Bank payment ACB <Billing Type> <Reference Number> <Amount>
    //Billing type can be enroll or admission or anything
    /**
     * 1. Send ACB to the mobile number of school or company
     * 2. System will store the message to SMS Message Bean
     *  a. Mobile number will be checked according to Person mobile number
     * 3. Agent will check payment
     * 4. Agent will mark the billing type once check from the bank
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
        String sql = "SELECT a FROM SMSMessageBean a WHERE a.phoneNumber='"+this.bean.phoneNumber+"' AND a.code1='"+this.bean.code1+"' AND a.code3='"+this.bean.code3+"'";
        this.bean = (SMSMessageBean) DBClient.getFirstRecord(sql);
        this.bean.code2 = billType;
        this.bean.code4 = amt;
        this.bean.save();
        //this may acknowledge the message
//        if (AppConfig.isTrue("SMS ACBREPLY", true)) {
//            this.server.addtoReply(this.bean.phoneNumber, AppConfig.getValue("SMS", "ACB", "Bank payment received. Payment will be verified in 2 days. Thank you."));
//        }
    }

}
