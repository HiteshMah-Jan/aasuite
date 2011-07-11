/*
 * ChildTemplate2.java
 *
 * Created on September 8, 2008, 10:26 AM
 */

package template.screen;

import javax.swing.SwingUtilities;

import service.util.AbstractIBean;
import template.ChildRecord;
import util.PanelUtil;

/**
 *
 * @author  Entokwaa
 */
public class ChildTemplateDisplayForm extends ChildTemplateListPopup {
    @Override
    protected void setup(ChildRecord childAnnotation, AbstractIBean parent) {
        super.setup(childAnnotation, parent);
        this.removeAll();
        this.add(pnlMain);
        PanelUtil.updateColor(this);
        PanelUtil.updateColor(pnlMain);
    }
    
    public void refreshRecords(AbstractIBean parent) {
    	super.refreshRecords(parent);
    	SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (tblRecord.getRowCount()>0) {
					try {
				    	tblRecord.setRowSelectionInterval(0, 0);
					}
					catch (Exception e) {
					}
				}
			}
    	});
    }
    
    public void updateRecord() {
    }
}
