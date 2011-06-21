/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import javax.swing.JComponent;

import util.PanelUtil;
import bean.admin.AppConfig;
import bean.reference.LockGrading;

/**
 *
 * @author Entokwaa
 */
public class LockGrading_RULE extends BusinessRuleWrapper {

	@Override
	public void runFocusLost(JComponent comp) {
	}

	@Override
	public void runOnClick(JComponent comp) {
		if ("btnLockQ1".equals(comp.getName())) {
			lockQ1();
		}
		else if ("btnLockQ2".equals(comp.getName())) {
			lockQ2();
		}
		else if ("btnLockQ3".equals(comp.getName())) {
			lockQ3();
		}
		else if ("btnLockQ4".equals(comp.getName())) {
			lockQ4();
		}
		else if ("btnReleaseLock".equals(comp.getName())) {
			releaseLock();
		}
	}

	private void lockQ4() {
		if (AppConfig.isTrimester()) {
			PanelUtil.showMessage(null, "This system is configured for trimester, you cannot use this button.");
		}
		else {
			LockGrading l = (LockGrading) this.getBean();
			l.lockQ4(true);
			l.save();
			redisplayRecord();
		}
	}

	private void lockQ3() {
		LockGrading l = (LockGrading) this.getBean();
		l.lockQ3(true);
		l.save();
		redisplayRecord();
	}

	private void lockQ2() {
		LockGrading l = (LockGrading) this.getBean();
		l.lockQ2(true);
		l.save();
		redisplayRecord();
	}

	private void releaseLock() {
		LockGrading l = (LockGrading) this.getBean();
		l.lockAll(false);
		l.save();
		redisplayRecord();
	}

	private void lockQ1() {
		boolean b = PanelUtil.showPrompt(null, "Locking Q1 will lock Grading, Schedule, Section and Subject Components.\nAre you sure you want to continue?");
		if (b) {
			LockGrading l = (LockGrading) this.getBean();
			l.lockQ1(true);
			l.save();
			redisplayRecord();
		}
	}
}
