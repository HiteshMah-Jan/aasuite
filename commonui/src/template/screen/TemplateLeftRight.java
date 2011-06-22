package template.screen;

import util.PanelUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: Apr 1, 2009
 * Time: 6:39:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class TemplateLeftRight extends TemplateTabPage {
    @Override
    public Object getMainForm() {
        JPanel pnl = (JPanel) super.getMainForm();
        pnlMainScreen.removeAll();
        pnlMainScreen.setLayout(new GridLayout());
        JPanel tmp = new JPanel(new BorderLayout());
        tmp.add(pnlTop, BorderLayout.CENTER);
        tmp.add(pnlButton, BorderLayout.SOUTH);
        PanelUtil.updateColor(tmp);
        pnlMainScreen.add(tmp);
        pnlMainScreen.add(pnlTab);
        return pnl;
    }
}
