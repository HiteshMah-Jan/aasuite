/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import bean.admin.AppConfig;
import component.JComboBoxPallete;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.ELProperty;
import service.util.AbstractIBean;
import util.BeanUtil;

/**
 *
 * @author Entokwaa
 */
public class ComboRenderer extends AbstractComponentRenderer {
    JComboBoxPallete f;
    
    public void refresh() {
        if (!this.field.display.sqlCombo().isEmpty()) {
            try {
                List tmp = AbstractIBean.list(this.field.display.sqlCombo());
                f.getList().clear();
                f.getList().addAll(tmp);
            }
            catch (Exception e) {
            }
        }
    }
    
    private final List getListWithEmpty() {
        List lst = getList();
        if (lst!=null && lst.size()>0) {
            try {
                Object bean = lst.get(0);
                Object newObject = bean.getClass().newInstance();
                lst.add(0, newObject);
            }
            catch (Exception e) {
                lst.add(0, "");
            }
        }
        return lst;
    }
    
    protected List getList() {
        List lst = new ArrayList();
        if (!this.field.display.sqlCombo().isEmpty()) {
            try {
                List tmp = AbstractIBean.list(this.field.display.sqlCombo());
                if (tmp!=null) {
                    lst.addAll(tmp);
                }
            }
            catch (Exception e) {
            }
        }
        else {
            String[] arr = this.field.display.modelCombo();

            StringBuffer sb = new StringBuffer();
            for (String string : arr) {
                sb.append(string).append(",");
            }
            String simpleName = this.panel.getCurrentClass().getSimpleName();
            String fieldName = this.field.field.getName();
//            Logger.getLogger("global").log(Level.INFO, simpleName,":",fieldName,":",sb.toString(),":",arr);
            try {
                if (AppConfig.isDynamicCombo()) {
//                    String str = AppConfig.getValue("COMBO", simpleName,".",fieldName, sb.toString());
//                    arr = str.split(",");
                }
                else {
                    if (sb!=null && sb.toString()!=null) arr = sb.toString().split(",");
                }
                if (arr!=null) {
                    for (String string : arr) {
                        if (string==null || string.isEmpty()) continue;
                        lst.add(new DummyBean(string));
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lst;
    }
    
    @Override
    public Object getRenderedField() {
        if (f!=null) return f;
        f = new JComboBoxPallete();
        List lst = new ArrayList();
        String name = this.field.field.getName();
        f.setName(name);
        f.setToolTipText(this.field.display.name());
        if (tbl!=null) f.setMandatory(this.field.display.mandatory());
        setSize(f);
        lst.addAll(getListWithEmpty());
        f.setList(lst);
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lst, f);
        bindingGroup.addBinding(jComboBoxBinding);

        bindMe("text", false, f);
        return f;
    }

    @Override
    public void lostFocus() {
        Object value = f.getCode();
        String name = this.field.field.getName();
        if (this.field.field.getType()==int.class) {
            value = f.getIntCode();
        }
        if (tbl instanceof JTable) {
            AbstractIBean bean = (AbstractIBean) ELProperty.create("${selectedElement}").getValue(tbl);
            BeanProperty.create(name).setValue(bean, value);
            bean.changeValue(name, "xXx");
            bean.changeValue(name, value);
            bean.changeValue(name, value);
//            ELProperty.create("${selectedElement.",name,"}").setValue(tbl, value);
        }
        else {
            try {
                ELProperty.create(BeanUtil.concat("${",name,"}")).setValue(tbl, value);
            }
            catch (Exception e) {
            }
        }
    }

    public static class DummyBean extends AbstractIBean {
        public String text;
        public String other;
        public double dvalue;
        
        public DummyBean(String code) {
            this.text = code;
        }

        public DummyBean(String code, String other, double dvalue) {
            this.text = code;
            this.other = other;
            this.dvalue = dvalue;
        }

        public String getOther() {
			return other;
		}

		public void setOther(String other) {
			this.other = other;
		}

		public double getDvalue() {
			return dvalue;
		}

		public void setDvalue(double dvalue) {
			this.dvalue = dvalue;
		}

		public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
        
        @Override
        public Object keyVal() {
            return text;
        }

        @Override
        public String popupSearch(String criteria) {
            return "";
        }

        @Override
        public String _Key() {
            return "text";
        }

		@Override
		public String toString() {
			if (other==null) return text;
			if (dvalue>0) {
				return BeanUtil.concat(text," - ",other," [",dvalue,"]");
			}
			return BeanUtil.concat(text," - ",other);
		}
    }
}