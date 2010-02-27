/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import java.io.File;

import javax.swing.JComponent;

import springbean.AttendanceService;
import ui.action.AttendanceScannerAction;
import util.PanelUtil;

/**
 *
 * @author Entokwaa
 */
public class AttendanceExt_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnScanner".equals(comp.getName())) {
            displayScanner();
        }
        else if ("btnUploadFile".equals(comp.getName())) {
            uploadFile();
        }
    }

    private void uploadFile() {
		File f = PanelUtil.showOpenFile("Upload Attendance", "", null);
		if (f!=null) {
			AttendanceService.runFile(f);
		}
	}

	private void displayScanner() {
        AttendanceScannerAction.showScanner();
    }
}
