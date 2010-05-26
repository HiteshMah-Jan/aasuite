package component;

import java.util.Date;

import javax.swing.JLabel;

import template.screen.AbstractTemplatePanel.FieldCompose;
import util.BeanUtil;
import util.DataUtil;
import util.DateUtil;
import util.PanelUtil;

public class LabelPallete extends JLabel  implements component.IGetText {
    FieldCompose field;
    Date date;
    int maxChar;
    String txt;

	public String getTxt() {
		return txt;
	}

	public void setTxt(String arg0) {
		this.txt = arg0;
		if (arg0!=null && maxChar>0 && maxChar<arg0.length()) {
			arg0 = BeanUtil.concat(arg0.substring(0, maxChar),"...");
		}
        if (this.field.field.getType()==double.class) {
        	double d = PanelUtil.getDoubleValue(arg0);
        	super.setText(DataUtil.getCurrencyFormat(d));
        }
        else {
			super.setText(arg0);
        }
	}

	public void setDoubleTxt(double d) {
		this.txt = BeanUtil.concat(d,"");
		super.setText(DataUtil.getCurrencyFormat(d));
	}

	public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        setText(DateUtil.formatDate(date));
    }
    
    
    public boolean isEmpty() {
        if (txt==null) return true;
        return txt.isEmpty();
    }

    public FieldCompose getFieldCompose() {
        return field;
    }

    public void setFieldCompose(FieldCompose field) {
        this.field = field;
        try {
            this.maxChar = this.field.display.maxChar();
        }
        catch (Exception e) {
        }
    }
    
    public Object getValue() {
        return txt;
    }
}
