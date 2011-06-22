/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.accounting.GLPostingScript;
import bean.accounting.ManualGL;
import javax.swing.JComponent;

/**
 *
 * @author Entokwaa
 */
public class ManualGL_RULE extends BusinessRuleWrapper {
    @Override
    public void runFocusLost(JComponent comp) {
    }

    @Override
    public void runOnClick(JComponent comp) {
        if (comp.getName().equals("btnCreateGL")) {
            createGL();
        }
        else if (comp.getName().equals("btnViewGL")) {
            viewGL();
        }
    }

    private void createGL() {
        ManualGL manualGL = (ManualGL) this.getBean();
        if (manualGL.seq==null || manualGL.seq==0) {
            saveRecord();
        }
        if (!manualGL.isBalanced()) {
            showError("Not balanced");
        }
        GLPostingScript.post(manualGL);
    }

    private void viewGL() {
        ManualGL manualGL = (ManualGL) this.getBean();
        if (manualGL.seq==null || manualGL.seq==0) {
            saveRecord();
        }
        GLPostingScript.post(manualGL);
        springbean.IProcess.Process.getInstance().showGL(manualGL);
    }
}
