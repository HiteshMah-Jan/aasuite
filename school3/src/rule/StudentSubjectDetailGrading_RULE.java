package rule;

import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import bean.person.StudentSubjectDetailGrading;

import template.screen.component.JTableReadOnly;
import template.screen.component.JTableReadOnly.NumberRenderer;

public class StudentSubjectDetailGrading_RULE extends BusinessRuleWrapper {

	@Override
	public void setTable(JTableReadOnly tbl) {
		super.setTable(tbl);
		tbl.setNewRenderer("Integer", IntegerWithColorRenderer.class);
		tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				calculate();
			}
		});
		tbl.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent arg0) {
				calculate();
			}
		});
		tbl.getColumnModel().addColumnModelListener(new TableColumnModelListener() {
			public void columnAdded(TableColumnModelEvent e) {
			}
			public void columnMarginChanged(ChangeEvent e) {
			}
			public void columnMoved(TableColumnModelEvent e) {
			}
			public void columnRemoved(TableColumnModelEvent e) {
			}
			public void columnSelectionChanged(ListSelectionEvent e) {
				calculate();
			}
		});
	}

	protected void calculate() {
		StudentSubjectDetailGrading sub = (StudentSubjectDetailGrading) getBean();
		if (sub!=null && oldObj!=null && oldObj instanceof StudentSubjectDetailGrading) {
			sub = (StudentSubjectDetailGrading) oldObj;
			sub.recalculateGrade();
		}
	}
	
	@Override
	public void runFocusLost(JComponent comp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void runOnClick(JComponent comp) {
		// TODO Auto-generated method stub

	}

    public static class IntegerWithColorRenderer extends NumberRenderer {
        DecimalFormat formatter;
        public IntegerWithColorRenderer() {
            super();
        }
        public void setValue(Object value) {
        	if (value==null || value.toString().trim().length()==0) {
        		value = "0";
        	}
            super.setValue(value);
            if (value.toString().equals("0")) {
            	setForeground(Color.RED);
            }
            else if (value.toString().equals("-1")) {
            	setForeground(Color.DARK_GRAY);
            }
        }
        
    }
}
