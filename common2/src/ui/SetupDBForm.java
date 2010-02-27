package ui;

import test.XMLTester;

import javax.swing.*;
import javax.persistence.Column;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.lang.reflect.Field;

import util.DBClient;
import util.PanelUtil;
import service.util.AbstractIBean;
import bean.admin.AppConfig;
import springbean.AAAConfig;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Apr 4, 2009
 * Time: 12:40:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetupDBForm extends JPanel {
    public SetupDBForm() {
        this.setLayout(new GridLayout(0, 4, 5, 5));
    	JLabel lbl = new JLabel("SETTING UP DB. Please wait...");
    	lbl.setHorizontalAlignment(JLabel.CENTER);
    	this.add(lbl);
        AAAConfig.getInstance().setupDB(lbl);
        
//        java.util.List<String> beans = XMLTester.getAllBeanNames();
//        for (String bean : beans) {
//            JButton btn = new JButton(bean);
//            btn.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    JButton btn = (JButton) e.getSource();
//                    AAAConfig.getInstance().runTableAlter(btn.getText());
//                }
//            });
//            this.add(btn);
//        }
    }
}
