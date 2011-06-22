package template.screen;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class TemplateNoFormWithSearch extends TemplateSearchOnly {
    @Override
    public Object getMainForm() {
        super.getMainForm();
        pnlChildTab.setVisible(false);
        
        JPanel tmp = new JPanel();
        tmp.setLayout(new BorderLayout());
        tmp.add(pnlCriteria, BorderLayout.NORTH);
        tmp.add(pnlResults, BorderLayout.CENTER);
        tmp.add(pnlButton, BorderLayout.SOUTH);
        
        this.removeAll();
        this.setLayout(new GridLayout());
        this.add(tmp);
        return this;
    }
}
