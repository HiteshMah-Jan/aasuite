package template.screen;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class TemplateTabSinglePage extends TemplateTabPage {
	@Override
	public Object getMainForm() {
	    useIndex = 0;
		super.getMainForm();
		myTab.removeAll();
		JPanel pnl = new JPanel(new BorderLayout(20,20));
		pnl.add(pnlMainScreen, BorderLayout.CENTER);
		pnl.add(pnlSearchPanel, BorderLayout.WEST);
		JPanel tmp = centerPanel(pnl);
		myTab.addTab("Main Form", tmp);
        myTab.addTab("Reports", pnlReportsPanel); // NOI18N
		return this;
	}

}
