/*
 * FileSystemModel.java
 * 
 * Created on Jan 12, 2008, 4:49:45 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.jtreetable;

import java.io.File;
import javax.swing.table.TableModel;

import util.BeanUtil;


/**
 * FileSystemModel is a TreeTableModel representing a hierarchical file 
 * system. Nodes in the FileSystemModel are FileNodes which, when they 
 * are directory nodes, cache their children to avoid repeatedly querying 
 * the real file system. 
 * 
 * @version %I% %G%
 *
 * @author Philip Milne
 * @author Scott Violet
 */

public class SampleJTreeTableModel extends AbstractTreeTableModel implements TreeTableModel {
    TableModel model;
    
    public SampleJTreeTableModel(TableModel model) { 
	super(new FileNode(new File(File.separator))); 
        this.model = model;
    }
    public int getChildCount(Object node) { 
	return 4;
    }

    public Object getChild(Object node, int i) { 
	return "Sample"; 
    }

    public int getColumnCount() {
        if (model!=null) return model.getColumnCount();
	return 4;
    }

    public String getColumnName(int column) {
        if (model!=null) return model.getColumnName(column);
	return BeanUtil.concat("Column ",column);
    }

    public Object getValueAt(Object node, int column) {
	return "Sample"; 
    }
}


