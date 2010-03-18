package rule;

import javax.swing.JComponent;

import bean.UldNumber;

import ui.ULDBuildUpForm;
import util.PanelUtil;

public class BuildUpULDExt_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnBuildUpULD".equals(comp.getName())) {
			buildUp();
		}
	}

	private void buildUp() {
		UldNumber uld = (UldNumber) this.getBean();
		if (uld == null || uld.isEmptyKey()) {
			showError("Please select ULD.");
			return;
		}
		ULDBuildUpForm pnl = new ULDBuildUpForm();
		pnl.setUldNumber(uld);
		PanelUtil.showDialog(pnl, "Build Up");
	}

}
