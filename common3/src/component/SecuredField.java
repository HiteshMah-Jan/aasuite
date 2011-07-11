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
			public void mouseClicked(MouseEvent arg0) {
				PanelUtil.showMessage(null, "This field is secured.\nOnly login with appropriate duty codes can change this field.\nDuty codes [",BeanUtil.concat(duties),"].");
			}
		});
	}

	public FieldCompose getFieldCompose() {
		return field;
	}

	public Object getValue() {
		return null;
	}

	public boolean isEmpty() {
		return true;
	}

	public void setFieldCompose(FieldCompose field) {
		this.field = field;
	}
}
