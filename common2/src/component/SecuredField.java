package component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import template.screen.AbstractTemplatePanel.FieldCompose;
import util.BeanUtil;
import util.PanelUtil;

public class SecuredField extends JLabel implements IGetText {
	FieldCompose field;
	
	public SecuredField(final String[] duties, String label) {
		super(label);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PanelUtil.showMessage(null, "This field is secured.\nOnly login with appropriate duty codes can change this field.\nDuty codes ["+BeanUtil.concat(duties)+"].");
			}
		});
	}

	@Override
	public FieldCompose getFieldCompose() {
		return field;
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public void setFieldCompose(FieldCompose field) {
		this.field = field;
	}
}
