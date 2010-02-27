/*
 * ButtonPopupPallete.java
 *
 * Created on Dec 6, 2007, 7:14:39 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public class ButtonPopupPallete extends JButton {

    private String linkPanel;
    private IGetText linkFor;
    private String title;

    public IGetText getLinkFor() {
        return linkFor;
    }

    public void setLinkFor(IGetText linkFor) {
        this.linkFor = linkFor;
    }

    public String getLinkPanel() {
        return linkPanel;
    }

    public void setLinkPanel(String linkPanel) {
        this.linkPanel = linkPanel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /** Creates a new instance of ButtonPopupPallete */
    public ButtonPopupPallete() {
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPopup(getTitle(), getLinkPanel());
            }
        });
    }

    @SuppressWarnings(value = "unchecked")
    private void showPopup(String name, String clsStr) {
        if (pnl == null) {
            try {
                Class cls = Class.forName(PanelUtil.getClassPath(clsStr));
                pnl = (JPanel) cls.newInstance();
                pnl.updateUI();

                panel = this.getParentPanel(this.getParent());
            } catch (Exception ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
        dialog = panel.getDialog();
        dialog.setLayout(new GridLayout());
        if (PanelUtil.isEmpty(linkFor)) {
            dialog.setTitle(name);
        } else {
            dialog.setTitle(name + " - " + linkFor.getText());
        }
        dialog.getContentPane().removeAll();
        dialog.getContentPane().add(pnl);
        dialog.pack();
        pnl.updateUI();
        if (pnl instanceof IPopupPanel) {
            callPopup();
            IPopupPanel popup = (IPopupPanel) pnl;
            String title = popup.getDialogTitle(name, linkFor);
            dialog.setTitle(title);
        }
        dialog.setVisible(true);
    }

    private void callPopup() {
        IPopupPanel pop = (IPopupPanel) pnl;
        pop.callPopup(panel, linkFor, title, this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    private AbstractPanel getParentPanel(Container contParent) {
        Container cont = contParent.getParent();
        if (cont instanceof AbstractPanel) {
            return (AbstractPanel) cont;
        } else {
            return getParentPanel(cont);
        }
    }

    private JDialog dialog;
    private AbstractPanel panel;
    private JPanel pnl;
}
