/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import service.util.AbstractIBean;
import template.screen.AbstractTemplatePanel.FieldCompose;
import util.BeanUtil;

/**
 *
 * @author Charliemagne Mark
 */
public class DisplayWrapper {
    private Display display;
    private FieldCompose f;
    private String prefix;
    private String useWidth;
    private AbstractIBean bean;
    public String innerText;

    public static DisplayWrapper getInstance(FieldCompose f) {
        DisplayWrapper d = new DisplayWrapper();
        d.f = f;
        d.display = f.display;
        return d;
    }
    public static DisplayWrapper getInstance(FieldCompose f, AbstractIBean bean) {
        DisplayWrapper d = new DisplayWrapper();
        d.f = f;
        d.display = f.display;
        d.bean = bean;
        return d;
    }
    public static DisplayWrapper getInstance(FieldCompose f, String prefix) {
        DisplayWrapper d = new DisplayWrapper();
        d.f = f;
        d.display = f.display;
        d.prefix = prefix;
        return d;
    }
    public static DisplayWrapper getInstance(FieldCompose f, String prefix, String useWidth) {
        DisplayWrapper d = new DisplayWrapper();
        d.f = f;
        d.display = f.display;
        d.prefix = prefix;
        d.useWidth = useWidth;
        return d;
    }
    private String name;
    private String label;  //if no value, then use the column name
    private String searchLabel;  //if no value, then use the column name
    private String tooltip;
    private String button;
    private String type;    //can be Label, Text, TextArea, Combo, PopSearch
    private int labelWidth;
    private String width;
    private int height;
    private String defaultValue;
    private String upCase;
    private String sqlCombo;
    private String[] modelCombo;
    private String linkto;
    private Class linktoBean;
    private String[] linktoColumns;
    private String linktoBeanAddWhere;
    private boolean mandatory;
    private boolean hidden;
    private String users;
    private String groups;
    private String duties;
    private int gridLabelWidth;
    private int gridFieldWidth;
    private boolean addInfoOnly;
    private int startCount;
    private int endCount;
    private boolean overrideMandatory;
    private boolean withHelp;
    private String[] mergeFields;
    private boolean hideLabel;
    private String dataType;

    public String getInnerText() {
        StringBuffer sb = new StringBuffer();
        if (innerText==null && display.type().toLowerCase().contains("combo")) {
            sb.append("<option value=\"\"></option>");
            if (display.type().equalsIgnoreCase("numbercombo")) {
                for (int i = display.startCount(); i <= display.endCount(); i++) {
                    sb.append("<option value=\"").append(i).append("\">").append(i).append("</option>");
                }
            }
            else {
                if (display.sqlCombo()!=null && !display.sqlCombo().trim().isEmpty()) {
                    List<AbstractIBean> lst = util.DBClient.getList(display.sqlCombo());
                    String key = null;
                    String title = null;
                    for (AbstractIBean ibean : lst) {
                        key = ibean.keyVal().toString();
                        title = ibean.toString();
                        sb.append("<option value=\"").append(key).append("\">").append(title).append("</option>");
                    }
                }
                else {
                    String string = "";
                    for (int i = 0; i < display.modelCombo().length; i++) {
                        string = display.modelCombo()[i];
                        sb.append("<option value=\"").append(string).append("\">").append(string).append("</option>");
                    }
                }
            }
            return sb.toString();
        }
        return innerText;
    }

    public String getDataType() {
        return f.field.getType().getSimpleName();
    }

    public boolean isAddInfoOnly() {
        return display.addInfoOnly();
    }

    public String getButton() {
        return display.button();
    }

    public String getDefaultValue() {
        if (bean!=null) {
            Object obj = util.BeanUtil.getPropertyValue(bean, display.name());
            if (obj == null) return "";
            if (obj instanceof Date) {
                return util.DateUtil.formatDate((Date) obj, "yyyy-MM-dd");
            }
            else if (obj instanceof Boolean && display.type().equalsIgnoreCase("checkbox")) {
                return ((Boolean) obj).booleanValue()?"checked":"";
            }
            return obj.toString();
        }
        return display.defaultValue();
    }

    public String[] getDuties() {
        return display.duties();
    }

    public int getEndCount() {
        return display.endCount();
    }

    public int getGridFieldWidth() {
        return display.gridFieldWidth();
    }

    public int getGridLabelWidth() {
        return display.gridLabelWidth();
    }

    public String getGroups() {
        return display.groups();
    }

    public int getHeight() {
        return display.height();
    }

    public boolean isHidden() {
        return display.hidden();
    }

    public boolean isHideLabel() {
        return display.hideLabel();
    }

    public String getLabel() {
        return util.PanelUtil.getLabel(f);
    }

    public int getLabelWidth() {
        return display.labelWidth();
    }

    public String getLinkto() {
        return display.linkto();
    }

    public String getLinktoBean() {
        return display.linktoBean().getSimpleName();
    }

    public String getLinktoBeanAddWhere() {
        return display.linktoBeanAddWhere();
    }

    public String[] getLinktoColumns() {
        return display.linktoColumns();
    }

    public boolean isMandatory() {
        return display.mandatory();
    }

    public String[] getMergeFields() {
        return display.mergeFields();
    }

    public String[] getModelCombo() {
        return display.modelCombo();
    }

    public String getName() {
        if (prefix!=null) {
            return BeanUtil.concat(prefix,"_",f.field.getName());
        }
        return f.field.getName();
    }

    public boolean isOverrideMandatory() {
        return display.overrideMandatory();
    }

    public String getSearchLabel() {
        return util.PanelUtil.getSearchLabel(f);
    }

    public String getSqlCombo() {
        return display.sqlCombo();
    }

    public int getStartCount() {
        return display.startCount();
    }

    public String getTooltip() {
        return display.tooltip();
    }

    public String getType() {
        return display.type();
    }

    public String getUpCase() {
        if (display.upCase()) {
            return "text-transform:uppercase;";
        }
        return "";
    }

    public String getUsers() {
        return display.users();
    }

    public String getWidth() {
        if (useWidth!=null) {
            return useWidth;
        }
        else {
            if (display.width()==-1) {
                return "100%";
            }
            else {
                if (display.width()==200 && (display.type().equalsIgnoreCase("textint") || display.type().equalsIgnoreCase("textdouble"))) {
                    return "100%";
                }
                else if (display.type().startsWith("textarea")) {
                    return "100%";
                }
                return BeanUtil.concat(display.width());
            }
        }
    }

    public boolean isWithHelp() {
        return display.withHelp();
    }
    
    public String getSize() {
        Column col = f.field.getAnnotation(Column.class);
        if (col!=null && col.length()>0 && col.length()<20) {
            return BeanUtil.concat(col.length());
        }
        if (f.field.getType()==int.class || f.field.getType()==double.class) {
            return "10";
        }
        return "";
    }
}
