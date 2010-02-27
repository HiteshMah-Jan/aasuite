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
public class BUK extends AbstractSMSProcessor {
    //Book reservation BUK <Accession Number>
    /**
     * 1. Send BUK to the mobile number of school or company
     * 2. System will store the message to SMS Message Bean
     *  a. Mobile number will be checked according to Person mobile number
     * 3. System will check availability of book
     */
    @Override
    public void run() {
        String message = this.bean.message;
        String[] arr = message.split(" ");
        String code = arr[0];
        String accessionNumber = arr[1];
        
        this.bean.code1 = code;
        this.bean.code2 = accessionNumber;
        //check if this has been sent already
        String sql = "SELECT a FROM SMSMessageBean a WHERE a.phoneNumber='"+this.bean.phoneNumber+"' AND a.code1='"+this.bean.code1+"' AND a.code2='"+this.bean.code2+"'";
        this.bean = (SMSMessageBean) DBClient.getFirstRecord(sql);
        this.bean.save();
        //this may acknowledge the message
//        if (AppConfig.isTrue("SMS BUKREPLY", true)) {
//            this.server.addtoReply(this.bean.phoneNumber, checkBookAvailability(accessionNumber));
//        }
    }
    
    protected String checkBookAvailability(String accessionNumber) {
        return "Book reserved - "+accessionNumber;
    }
}
