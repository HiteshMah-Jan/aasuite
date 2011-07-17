package template.screen;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import util.PanelUtil;

public class TemplateTabSinglePageLeftRightNoSearchResult extends TemplateTabPage {
	@Override
	public Object getMainForm() {
	    useIndex = 0;
		super.getMainForm();
		myTab.removeAll();

		JPanel pnl = new JPanel(new BorderLayout(20,20));
		pnl.add(super.pnlCriteria, BorderLayout.NORTH);
		pnl.add(getSuperTabChildren(), BorderLayout.CENTER);
		pnl.add(super.pnlButton, BorderLayout.SOUTH);
		
		JPanel tmp = centerPanel(pnl);
		myTab.addTab("Main Form", pnl);
        myTab.addTab("Reports", pnlReportsPanel); // NOI18N
		return this;
	}

    public JPanel centerPanel(JPanel pnl) {
		JPanel tmp = new JPanel(new GridLayout());
		tmp.add(pnl);
		return tmp;
    }
}
