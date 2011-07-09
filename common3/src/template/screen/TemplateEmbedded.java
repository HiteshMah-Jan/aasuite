package template.screen;

import java.awt.GridLayout;

import template.screen.component.JTableReadOnly;


public class TemplateEmbedded extends AbstractTemplatePanel {
	@Override
	public Object getMainForm() {
        super.getMainForm();
        this.removeAll();
        this.setLayout(new GridLayout());
        this.add(super.pnlMainForm);
        return this;
	}

	@Override
	protected void bindToTable() {
		Class cls = ((JTableReadOnly)tblResult).beanClass;
        templateParser.bindTable(bindingGroup, list, tblResult, cls);
	}

	@Override
	public void searchNow(int moreCount) {
//		do nothing
	}
	
}
