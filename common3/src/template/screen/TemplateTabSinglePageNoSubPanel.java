package template.screen;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class TemplateTabSinglePageNoSubPanel extends TemplateTabPage {
	@Override
	public Object getMainForm() {
	    useIndex = 0;
		super.getMainForm();
		myTab.removeAll();

		JPanel pnl = new JPanel(new BorderLayout(20,20));
		pnl.add(pnlSearchPanel, BorderLayout.CENTER);

		JPanel pnl2 = new JPanel(new BorderLayout(20,20));
		pnl2.add(super.pnlTop, BorderLayout.CENTER);
		pnl2.add(super.pnlButton, BorderLayout.SOUTH);
		
		JPanel pnl3 = new JPanel(new BorderLayout(20,20));
		pnl3.add(pnl, BorderLayout.CENTER);
		pnl3.add(pnl2, BorderLayout.SOUTH);

		JPanel tmp = centerPanel(pnl3);
		myTab.addTab("Main Form", tmp);
        myTab.addTab("Reports", pnlReportsPanel); // NOI18N
		return this;
	}
}
