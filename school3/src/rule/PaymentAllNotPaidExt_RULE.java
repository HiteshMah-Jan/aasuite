/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import constants.UserInfo;

/**
 *
 * @author alex
 */
public class PaymentAllNotPaidExt_RULE extends Payment_RULE {

    @Override
    public void onNewRecord() {
        super.onNewRecord();
        getComponent("approvalStatus1").setEnabled(false);
        getComponent("approvalStatus2").setEnabled(false);
        if (UserInfo.canApproveTuitionDiscount()) {
            getComponent("approvalStatus1").setEnabled(true);
        }
        if (UserInfo.canApproveSurchargeDiscount()) {
            getComponent("approvalStatus2").setEnabled(true);
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        getComponent("approvalStatus1").setEnabled(false);
        getComponent("approvalStatus2").setEnabled(false);
        if (UserInfo.canApproveTuitionDiscount()) {
            getComponent("approvalStatus1").setEnabled(true);
        }
        if (UserInfo.canApproveSurchargeDiscount()) {
            getComponent("approvalStatus2").setEnabled(true);
        }
    }

}
