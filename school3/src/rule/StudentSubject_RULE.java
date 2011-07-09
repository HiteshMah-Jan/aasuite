/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rule;

import java.util.List;

import javax.swing.JComponent;

import util.DBClient;
import util.PanelUtil;
import util.ThreadPoolUtil;

import bean.person.StudentSubject;
import bean.reference.LockGrading;

/**
 *
 * @author Entokwaa
 */
public class StudentSubject_RULE extends Person_RULE {
    @Override
    public void onLoad() {
    }

    @Override
    public void runFocusLost(JComponent comp) {
    }

    @Override
    public void runOnClick(JComponent comp) {
//        if (comp.getName().equals("btnCalculateQ1")) {
//        	calculateQ1();
//        }
//        else if (comp.getName().equals("btnCalculateQ2")) {
//        	calculateQ2();
//        }
//        else if (comp.getName().equals("btnCalculateQ3")) {
//        	calculateQ3();
//        }
//        else if (comp.getName().equals("btnCalculateQ4")) {
//        	calculateQ4();
//        }
//        else if (comp.getName().equals("btnCalculateFinal")) {
//        	calculateFinal();
//        }
//        else if (comp.getName().equals("btnCalculateAll")) {
//        	calculateAll();
//        }
    }

	
	@Override
	public void onChangeRecord() {
		super.onChangeRecord();
		StudentSubject t = (StudentSubject) this.getBean();
		LockGrading s = LockGrading.extractGrading(t.schoolYear);
		if (s==null) return;
		if (s.isQ1Locked()) {
			disable("btnCalculateQ1");
		}
		if (s.isQ2Locked()) {
			disable("btnCalculateQ2");
		}
		if (s.isQ3Locked()) {
			disable("btnCalculateQ3");
		}
		if (s.isQ4Locked()) {
			disable("btnCalculateQ4");
		}
	}
}
