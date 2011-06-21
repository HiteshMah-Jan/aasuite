package rule;


import javax.swing.JComponent;

import bean.accounting.payroll.AlphaList;
import bean.admin.AppConfig;
import springbean.PayrollService;
import util.DateUtil;

public class AlphaList_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnGenerate".equals(comp.getName())) {
            generate();
        }
    }

    private void generate() {
        AlphaList bean = (AlphaList) getBean();
        if (bean.isEmptyKey()) {
            bean.lineOfBusiness = AppConfig.getCompanyLineOfBusiness();
            bean.rdoCode = AppConfig.getCompanyRDOCode();
            bean.registeredAddress = AppConfig.getCompanyAddress();
            bean.telephoneNumber = AppConfig.getCompanyTelNo();
            bean.tin = AppConfig.getCompanyTIN();
            bean.withholdingAgentCategory = AppConfig.getCompanyWTaxAgentCategory();
            bean.withholdingAgentName = AppConfig.getCompanyWTaxAgent();
            bean.zipCode = AppConfig.getCompanyZipCode();
            bean.year = DateUtil.getYear();
            saveRecord();
        }
        if (showPrompt("Would you like to create the employee list and summary for year ",bean.year)) {
            new PayrollService().createAlphaList(bean);
        }
        redisplayRecord();
    }
}
