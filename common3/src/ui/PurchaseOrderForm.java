package ui;

import component.TabPanelForm;
import constants.UserInfo;

public class PurchaseOrderForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Purchase Order";
    }

    @Override
    public String tabs() {
//    	request
//    	approve1
//    	approve2
//    	override
//    	print voucher
//    	receive
    	StringBuffer sb = new StringBuffer();
    	if (UserInfo.canRequestPO()) {
    		sb.append("PurchaseOrderRequestExt,");
    	}
    	if (UserInfo.canApprovePO()) {
    		sb.append("PurchaseOrderApprove1Ext,");
    	}
    	if (UserInfo.canApprovePO2()) {
    		sb.append("PurchaseOrderApprove2Ext,");
    	}
    	if (UserInfo.canOverridePO()) {
    		sb.append("PurchaseOrderOverrideExt,");
    	}
    	if (UserInfo.canPrintVoucher()) {
    		sb.append("PurchaseOrderVoucherExt,");
    	}
    	if (UserInfo.canReceivePO()) {
    		sb.append("PurchaseOrderReceiveExt,");
    	}
    	sb.append("Supplier,ProductEmployeeSupply,Product,ProductCategory");
        return sb.toString();
    }

}
