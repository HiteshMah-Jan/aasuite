/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sms;

import bean.admin.SMSMessageBean;

/**
 *
 * @author Charliemagne Mark
 */
public class HerbertSMSServer extends SMSServer {
    public static void main(String[] args) {
        SMSServer serv = new HerbertSMSServer("COM9");
        serv.start(1);
    }

    public HerbertSMSServer(String port) {
        super(port);
    }

    @Override
    public void callbackReadMessage(SMSMessageBean bean) {
        String msg = bean.message;
        String[] arr = msg.split(" ");
        String borrowerNumber = arr[0];
        String accessionNumber = arr[1];
        bean.code1 = borrowerNumber;
        bean.code2 = accessionNumber;
    }

}
