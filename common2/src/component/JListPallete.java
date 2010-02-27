/*
 * JListPallete.java
 * 
 * Created on Oct 18, 2007, 8:04:57 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import service.util.AbstractIBean;
import template.screen.AbstractTemplatePanel.FieldCompose;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class JListPallete extends JList implements IGetText, IHelp {
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private boolean mandatory;
    private String text;
    private String code;
    private List list;
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List getList() {
        return list;
    }

    @SuppressWarnings("unchecked")
	public void setList(List list) {
        this.list = list;
    }

	private String errorText;
	private boolean alreadyInserted;

	public boolean withError() {
		if (mandatory && PanelUtil.isEmpty(this)) {
			errorText = "Mandatory Field";
		}
		//check other things here
		return !PanelUtil.isEmpty(errorText);
	}
	
    public String getErrorText() {
        return errorText;
    }
			
    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

	public JListPallete() {
	}
	
    public String getCode() {
		if (list!=null) {
			int index = this.getSelectedIndex();
			if (list.get(index) instanceof AbstractIBean) {
				AbstractIBean bean = (AbstractIBean) list.get(index);
				return bean.keyVal()==null?"":bean.keyVal().toString();
			}
		}
		String txt = this.getText();
		code = PanelUtil.getGenericCode(txt);
        return code;
    }

    public void setCode(String code) {
		if (!PanelUtil.isEmpty(list) && !alreadyInserted && !mandatory) {
            try {
				alreadyInserted = true;
                java.lang.Object obj = list.get(0);
                java.lang.Object ins = obj.getClass().newInstance();
				if (!list.contains(obj)) list.add(0, ins);
            } catch (InstantiationException ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
		}
		if (list!=null) {
			for (int i=0; i<list.size(); i++) {
				Object obj = list.get(i);
				if (obj!=null && obj instanceof AbstractIBean) {
					AbstractIBean bean = (AbstractIBean) obj;
					String scode = bean.keyVal()==null?"":bean.keyVal().toString();
					if (scode.equals(code)) {
						this.setSelectedIndex(i);
					}
				}
				if (obj==null && PanelUtil.isEmpty(code)) {
					this.setSelectedIndex(0);
					break;
				}
			}
		}
		else {
			for (int i=0; i<this.getComponentCount(); i++) {
				Object obj = this.getComponent(i);
				if (obj!=null) {
					String scode = PanelUtil.getGenericCode(obj.toString());
					if (scode.equals(code)) {
						this.setSelectedIndex(i);
					}
				}
				if (obj==null && PanelUtil.isEmpty(code)) {
					this.setSelectedIndex(0);
					break;
				}
			}
		}
        String oldCode = this.code;
        this.code = code;
        changeSupport.firePropertyChange("code", oldCode, code);
    }
	
    public int getIntCode() {
		return (int)this.getDoubleCode();
	}

    public double getDoubleCode() {
		try {
			return Double.parseDouble(this.getCode());
		}
		catch (Exception e) {
			return 0;
		}
	}
	
    @Override
	public String getText() {
		Object obj = this.getSelectedValue();
		if (obj==null) return "";
		return this.getSelectedValue().toString();
    }

    @Override
	public void setText(String text) {
		for (int i=0; i<this.getComponentCount(); i++) {
			Object obj = this.getComponent(i);
			if (obj.toString().equals(text)) {
				this.setSelectedIndex(i);
			}
		}
        String oldText = this.text;
        this.text = text;
        changeSupport.firePropertyChange("text", oldText, text);
    }
	
	String helpName;

    public String getHelpName() {
        return helpName;
    }

    public void setHelpName(String helpName) {
        this.helpName = helpName;
    }

	public List<String> getAllSelection() {
		if (list==null) {
			System.out.println("LIST not defined at designed time");
			return null;
		}
		List<String> lst = new ArrayList<String>();
		int[] iArr = this.getSelectedIndices();
		for (int index : iArr) {
			AbstractIBean bean = (AbstractIBean) this.list.get(index);
			lst.add(bean.keyVal()==null?"":bean.keyVal().toString());
		}
		return lst;
	}

	public List<AbstractIBean> getAllBeanSelection() {
		if (list==null) {
			System.out.println("LIST not defined at designed time");
			return null;
		}
		List<AbstractIBean> lst = new ArrayList<AbstractIBean>();
		int[] iArr = this.getSelectedIndices();
		for (int index : iArr) {
			AbstractIBean bean = (AbstractIBean) this.list.get(index);
			lst.add(bean);
		}
		return lst;
	}

    @Override
	public boolean isEmpty() {
        return PanelUtil.isEmpty(this);
    }

    FieldCompose fieldCompose;
    public FieldCompose getFieldCompose() {
        return fieldCompose;
    }

    public void setFieldCompose(FieldCompose field) {
        this.fieldCompose = field;
    }

    public Object getValue() {
        return getText();
    }
}
