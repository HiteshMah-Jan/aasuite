/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui.cashier;

import java.util.List;

import bean.accounting.CashierDailyBooklet;
import bean.accounting.Invoice;
import bean.extension.OtherPaymentReferenceAccount1Ext;

import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author Alex Miranda
 */
public class OtherPaymentAccount1 extends OtherPayment {
	protected void printOR() {
	    bean.accounting.OtherPayment pay = (bean.accounting.OtherPayment) pnlOtherPayment.getBean();
	    if (pay.seq!=null && pay.seq>0 && pay.orNumber!=null && !pay.orNumber.isEmpty()) {
	        pay.printOR("Official Receipt");
	    }
	    else {
	        if (pay.personId==0 && (pay.payer==null || pay.payer.trim().isEmpty())) {
	            PanelUtil.showError(btnPrintOr, "Please provide student or payor.");
	            return;
	        }
	        acceptCheck(pay);
	        String or = PanelUtil.showPromptDefaultMessage(btnPrintOr, "Print OR, please check if OR number is correct.", booklet.extractNextOR("N")+"");
	        if (or==null || or.isEmpty()) return;


			int i = Integer.parseInt(or);
			if (i<booklet.startOR || i>booklet.endOR) {
				if (!PanelUtil.showPrompt(this, "OR number out of series, would you like to continue?")) {
					return;
				}
			}
//			check or if exist in invoice
			Invoice inv = (Invoice) DBClient.getFirstRecord("SELECT a FROM Invoice a WHERE a.orNumber="+or+" AND a.orType='N'");
			if (inv!=null && !inv.isEmptyKey()) {
				PanelUtil.showError(this, "OR# ["+or+"] is already used, please check");
				return;
			}
	        
//	    pay.extractInvoice();
	        pay.orNumber = or;
	        pay.orType = "N";
	        pay.orDate = constants.Constants.useDate;
	        pay.save();
	        pay.printOR("Official Receipt");
	        pnlOtherPayment.setBean(pay);
	        drawer.updateDrawer();
	    }
	}

	@Override
    protected String getBeanName() {
        return "OtherPaymentAccount1Ext";
    }

	@Override
	protected void search() {
	    List lst = null;
	    String txt = txtSearch.getText();
	    if (txt==null || txt.trim().isEmpty()) {
	        lst = DBClient.getList("SELECT a FROM OtherPayment a WHERE a.orType='N' ORDER BY a.seq DESC");
	    }
	    else {
	        lst = DBClient.getList("SELECT a FROM OtherPayment a WHERE a.orType='N' AND a.payer LIKE '%"+txt+"%' OR a.studentNumber = '"+txt+"' ORDER BY a.seq DESC");
	    }
	    beanSoldBooks.setList(lst);
	}
	
	protected void showAddReference() {
	    PanelUtil.popupBeanTemplate(OtherPaymentReferenceAccount1Ext.class, "Other Payment Reference", true);
	}
}
