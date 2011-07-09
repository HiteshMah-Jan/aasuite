/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import bean.admin.UpdateSQLScript;
import java.util.List;
import javax.swing.JComponent;
import util.DBClient;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class UpdateSQLScript_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
      
    }

    @Override
    public void runOnClick(JComponent comp) {
        if (comp.getName().equals("btnRunScript")) {
            runScript();
        }
        else if (comp.getName().equals("btnRunCreateTable")) {
            runCreateTable();
        }
        else if (comp.getName().equals("btnRunAllScript")) {
            runAllScript();
        }
    }

    private void runAllScript() {
        List<UpdateSQLScript> lst = DBClient.getList("SELECT a FROM UpdateSQLScript a ORDER BY a.seq");
        for (UpdateSQLScript script : lst) {
            if (!script.script.isEmpty()) {
                //run script now
                String[] strArr = script.script.split(";");
                for (String string : strArr) {
                    if(!string.isEmpty()) DBClient.runSQLNative(string);
                }
            }
        }
        PanelUtil.showMessage(null, "Run script completed.");
    }

    private void runCreateTable() {
        springbean.AAAConfig config = springbean.AAAConfig.getInstance();
        config.callSetup();
    }

    private void runScript() {
        UpdateSQLScript script = (UpdateSQLScript) getBean();
        if (!script.script.isEmpty()) {
            //run script now
            String[] strArr = script.script.split(";");
            for (String string : strArr) {
                if(!string.isEmpty()) DBClient.runSQLNative(string);
            }
            PanelUtil.showMessage(null, "Run script completed.");
        }
    }
}
