package rule;

import bean.accounting.PaymentPlan;
import bean.admin.AppConfig;

import javax.swing.*;
import java.util.List;

import util.PanelUtil;
import util.DBClient;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Mar 31, 2009
 * Time: 8:41:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class PaymentPlan_RULE extends BusinessRuleWrapper {

    public void runFocusLost(JComponent comp) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void runOnClick(JComponent comp) {
        if ("btnForwardToYear".equals(comp.getName())) {
            forwardPlan();
        }
    }

    private void forwardPlan() {
        PaymentPlan plan = (PaymentPlan) this.getBean();
        String useYear = plan.useYear;
        List years = AppConfig.getSchoolYears();
        String syear = (String) PanelUtil.showPromptMessage(this.usedComp, "Forward to Year", AppConfig.getSchoolYear(), years, AppConfig.getSchoolYear());
        if (syear.equals(useYear)) {
            PanelUtil.showError(this.usedComp, "Cannot use same year.");
        }
        else {
            List<PaymentPlan> plans = DBClient.getList("SELECT a FROM PaymentPlan a WHERE a.useYear='",useYear,"'");
            for (PaymentPlan p : plans) {
                p.seq = null;
                p.useYear = syear;
            }
            DBClient.persistBean((List)plans);
            this.refreshRecords();
            PanelUtil.showMessage(this.usedComp, "Plan forwarded successfully.");
        }
    }
}
