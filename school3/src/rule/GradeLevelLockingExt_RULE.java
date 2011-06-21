/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import javax.swing.JComponent;

import util.DBClient;

import bean.reference.GradeLevel;

/**
 *
 * @author Charliemagne Mark
 */
public class GradeLevelLockingExt_RULE extends GradeLevel_RULE {
    @Override
    public void runOnClick(JComponent comp) {
    	if ("btnLock".equals(comp.getName())) {
    		lock();
    	}
    	else if ("btnRelease".equals(comp.getName())) {
    		releaseLock();
    	}
    	else if ("btnReleaseAll".equals(comp.getName())) {
    		releaseAllLock();
    	}
    }

	private void releaseLock() {
		GradeLevel l = (GradeLevel) this.getBean();
		l.locked = false;
		l.save();
		refreshRecords();
	}

	private void releaseAllLock() {
		DBClient.runSQLNative("UPDATE GradeLevel SET locked=0");
		refreshRecords();
	}

	private void lock() {
		GradeLevel l = (GradeLevel) this.getBean();
		l.locked = true;
		l.save();
		refreshRecords();
	}
}
