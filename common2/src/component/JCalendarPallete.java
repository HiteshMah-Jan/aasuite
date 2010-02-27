/*
 * JCalendarPallete.java
 *
 * Created on Aug 18, 2007, 9:29:35 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.freixas.jcalendar.JCalendarCombo;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import service.util.AbstractIBean;
import util.BeanUtil;
import util.DateUtil;
import util.PanelUtil;
import template.screen.AbstractTemplatePanel.FieldCompose;

/**
 *
 * @author Budoy Entokwa
 */
public class JCalendarPallete extends JCalendarCombo implements IHelp, IRule, IGetText {

    long time;
    boolean mandatory;
    boolean notLaterToday;
    String errorText;
    String field;

    public String getText() {
        if (getDate()==null) return "";
        return sdf.format(getDate());
    }
    
    public void setText(String str) {
        try {
            Date d = sdf.parse(str);
            setDate(d);
        }
        catch (Exception e) {
        }
    }
    
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
    
    public JCalendarPallete getMe() {
        return this;
    }

    public boolean withError() {
        return errorText!=null && !errorText.isEmpty();
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

    public boolean isNotLaterToday() {
        return notLaterToday;
    }

    public void setNotLaterToday(boolean notLaterToday) {
        this.notLaterToday = notLaterToday;
    }

    public long getTime() {
        time = this.getDate().getTime();
        return time;
    }

    public void setTime(long time) {
        if (this.getDate()!=null) this.getDate().setTime(time);
        this.time = time;
        this.setDate(new Date(time));
    }

    /** Creates a new instance of JCalendarComp */
    public JCalendarPallete() {
        super(JCalendarCombo.DISPLAY_DATE, false);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.setDateFormat(dateFormat);
        this.setEditable(true);
        this.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        lostFocus();
                    }
                });
                if (field!=null && !field.trim().isEmpty()) {
                    if (PanelUtil.inMainPanel(getMe())) {
                        JPanel pnl = PanelUtil.getActivePanel(getMe());
                        if (pnl instanceof AbstractPanel) {
                            AbstractIBean bean = (AbstractIBean) ((AbstractPanel) pnl).getBean();
                            BeanUtil.setPropertyValue(bean, field, getDate());
                        }
                    }
                    else {
                        JPanel pnl = PanelUtil.getMySubPanel(getMe());
                        if (pnl instanceof AbstractSubPanel) {
                            AbstractIBean bean = (AbstractIBean) ((AbstractSubPanel) pnl).getCurrentBean();
                            BeanUtil.setPropertyValue(bean, field, getDate());
                        }
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            runRuleManager();
                        }
                    });
                }
            }
        });

        editor.getEditorComponent().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                lostFocus();
                            }
                        });
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                runRuleManager();
                            }
                        });
                    }
                });
            }

            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                JTextField cbo = (JTextField) e.getSource();
                cbo.selectAll();
            }
        });
        editor.getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                IPanelNavigator.RecordNavigator.navigate(getMe(), e);
//                DynamicAccessForm.showDialog(getMe());
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runRuleManager();
            }
        });
//        DynamicAccessForm.initializeComponent(this);
    }

    protected void lostFocus() {
        this.setBackground(PanelUtil.OK_COLOR);

        int i = -1;
        JTextField cbo = (JTextField) editor.getEditorComponent();
        try {
            i = Integer.parseInt(cbo.getText());
        } catch (Exception exp) {
        }
        if (i >= 0) {
            Date d = constants.Constants.useDate;
            if (i>0) {
                d = DateUtil.addDay(DateUtil.getFirstDayOfMonth(), i-1);
            }
            getMe().setTime(d.getTime());
            return;
        }
        Date d = null;
        if (cbo.getText()!=null && !cbo.getText().isEmpty()) d = DateUtil.readDate(cbo.getText(), "yyyy-MM-dd");
        if (d==null && !PanelUtil.isEmpty(cbo.getText())) {
            cbo.setToolTipText("Invalid date.");
            PanelUtil.showMessageToScreen("Invalid date.");
            this.setBackground(PanelUtil.ERROR_COLOR);
            return;
        }
        if (isMandatory() && PanelUtil.isEmpty(cbo.getText())) {
            errorText = "Mandatory Field";
            PanelUtil.showMessageToScreen(errorText);
            this.setBackground(PanelUtil.ERROR_COLOR);
        } 
        else {
            if (notLaterToday && !PanelUtil.isNotLaterToday(getDate())) {
                errorText = "Must be later than today";
                PanelUtil.showMessageToScreen(errorText);
                this.setBackground(PanelUtil.ERROR_COLOR);
            }
        }
    }

    String helpName;

    public String getHelpName() {
        return helpName;
    }

    public void setHelpName(String helpName) {
        this.helpName = helpName;
    }

    public boolean isEmpty() {
        return PanelUtil.isEmpty(this);
    }

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public String getSqlDateFormat() {
        if (getDate() == null) {
            return "";
        }
        return sdf.format(getDate());
    }
    

    private void runRuleManager() {
        PalleteRuleManager.useRule(this);
    }
    
    private String allowedUserId;
    private String allowedGroup;
    private String allowedDuty;

    private String onClickRule;
    private String onValueChangeRule;
    private String onLostFocusRule;

    public String getAllowedDuty() {
        return allowedDuty;
    }

    public void setAllowedDuty(String allowedDuty) {
        this.allowedDuty = allowedDuty;
    }

    public String getAllowedGroup() {
        return allowedGroup;
    }

    public void setAllowedGroup(String allowedGroup) {
        this.allowedGroup = allowedGroup;
    }

    public String getAllowedUserId() {
        return allowedUserId;
    }

    public void setAllowedUserId(String allowedUserId) {
        this.allowedUserId = allowedUserId;
    }

    public String getRuleForAllowed() {
        return PalleteRuleManager.getRuleForAllowed(this);
    }
    
    public String getOnClickRule() {
        return onClickRule;
    }

    public void setOnClickRule(String onClickRule) {
        this.onClickRule = onClickRule;
    }

    public String getOnLostFocusRule() {
        return onLostFocusRule;
    }

    public void setOnLostFocusRule(String onLostFocusRule) {
        this.onLostFocusRule = onLostFocusRule;
    }

    public String getOnValueChangeRule() {
        return onValueChangeRule;
    }

    public void setOnValueChangeRule(String onValueChangeRule) {
        this.onValueChangeRule = onValueChangeRule;
    }    

    FieldCompose fieldCompose;
    public FieldCompose getFieldCompose() {
        return fieldCompose;
    }

    public void setFieldCompose(FieldCompose field) {
        this.fieldCompose = field;
    }

    public Object getValue() {
        return getDate();
    }
}
