/*
 * LinkLabelValuePallete.java
 *
 * Created on May 7, 2008, 6:24:21 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import javax.swing.JLabel;
import javax.swing.JTextField;
import service.util.AbstractIBean;
import util.BeanUtil;
import util.Log;

/**
 *
 * @author Entokwaa
 */
public class LinkLabelValuePallete extends JLabel {

    private String code = "1";
    private String entityName = "bean.Person";
    private AbstractIBean bean;

    private String changeLabel1Field;
    private JLabel changeLabel1;
    private String changeLabel2Field;
    private JLabel changeLabel2;
    private String changeLabel3Field;
    private JLabel changeLabel3;
    private String changeLabel4Field;
    private JLabel changeLabel4;
    private String changeLabel5Field;
    private JLabel changeLabel5;

    public LinkLabelValuePallete() {
        super.setText("<<VALUE>>");
        super.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        super.setForeground(new java.awt.Color(0, 0, 153));
        super.setFocusable(false);
    }

    public JLabel getChangeLabel1() {
        return changeLabel1;
    }

    public void setChangeLabel1(JLabel changeLabel1) {
        this.changeLabel1 = changeLabel1;
        this.changeLabel1.setForeground(this.getForeground());
    }

    public String getChangeLabel1Field() {
        return changeLabel1Field;
    }

    public void setChangeLabel1Field(String changeLabel1Field) {
        this.changeLabel1Field = changeLabel1Field;
    }

    public JLabel getChangeLabel2() {
        return changeLabel2;
    }

    public void setChangeLabel2(JLabel changeLabel2) {
        this.changeLabel2 = changeLabel2;
        this.changeLabel2.setForeground(this.getForeground());
    }

    public String getChangeLabel2Field() {
        return changeLabel2Field;
    }

    public void setChangeLabel2Field(String changeLabel2Field) {
        this.changeLabel2Field = changeLabel2Field;
    }

    public JLabel getChangeLabel3() {
        return changeLabel3;
    }

    public void setChangeLabel3(JLabel changeLabel3) {
        this.changeLabel3 = changeLabel3;
        this.changeLabel3.setForeground(this.getForeground());
    }

    public String getChangeLabel3Field() {
        return changeLabel3Field;
    }

    public void setChangeLabel3Field(String changeLabel3Field) {
        this.changeLabel3Field = changeLabel3Field;
    }

    public JLabel getChangeLabel4() {
        return changeLabel4;
    }

    public void setChangeLabel4(JLabel changeLabel4) {
        this.changeLabel4 = changeLabel4;
        this.changeLabel4.setForeground(this.getForeground());
    }

    public String getChangeLabel4Field() {
        return changeLabel4Field;
    }

    public void setChangeLabel4Field(String changeLabel4Field) {
        this.changeLabel4Field = changeLabel4Field;
    }

    public JLabel getChangeLabel5() {
        return changeLabel5;
    }

    public void setChangeLabel5(JLabel changeLabel5) {
        this.changeLabel5 = changeLabel5;
        this.changeLabel5.setForeground(this.getForeground());
    }

    public String getChangeLabel5Field() {
        return changeLabel5Field;
    }

    public void setChangeLabel5Field(String changeLabel5Field) {
        this.changeLabel5Field = changeLabel5Field;
    }

//    @Override
    public String getCode() {
        return code;
    }

//    @Override
    public void setCode(String text) {
        Log.info("CODE === ",text);
        this.code = text;
        if (entityName!=null && !entityName.isEmpty()) {
            try {
                if (code==null || code.isEmpty() || code.equals("0")) {
                    super.setText("...");
                }
                else {
                    bean = AbstractIBean.extractObject(entityName, code);
                    super.setText(bean.getComboDisplay());
                    if (changeLabel1Field!=null && changeLabel1!=null) {
                        changeLabel1.setText(BeanUtil.getPropertyValue(bean, changeLabel1Field).toString());
                    }
                    if (changeLabel2Field!=null && changeLabel2!=null) {
                        changeLabel2.setText(BeanUtil.getPropertyValue(bean, changeLabel2Field).toString());
                    }
                    if (changeLabel3Field!=null && changeLabel3!=null) {
                        changeLabel3.setText(BeanUtil.getPropertyValue(bean, changeLabel3Field).toString());
                    }
                    if (changeLabel4Field!=null && changeLabel4!=null) {
                        changeLabel4.setText(BeanUtil.getPropertyValue(bean, changeLabel4Field).toString());
                    }
                    if (changeLabel5Field!=null && changeLabel5!=null) {
                        changeLabel5.setText(BeanUtil.getPropertyValue(bean, changeLabel5Field).toString());
                    }
                }
            } catch (Exception ex) {
//                ex.printStackTrace();
                super.setText(text);
            }
        }
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public AbstractIBean getBean() {
        return bean;
    }

    public void setBean(AbstractIBean bean) {
        this.bean = bean;
    }
    
}
