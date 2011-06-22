/*
 * TreeTableExample0.java
 * 
 * Created on Jan 12, 2008, 4:49:11 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component.jtreetable;

import component.JTreeTablePallete;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * A TreeTable example, showing a JTreeTable, operating on the local file
 * system.
 *
 * @version %I% %G%
 *
 * @author Philip Milne
 */

public class TreeTableExample0
{
    public static void main(String[] args) {
	new TreeTableExample0();
    }

    public TreeTableExample0() {
	JFrame frame = new JFrame("TreeTable");  
	JTreeTablePallete treeTable = new JTreeTablePallete(new FileSystemModel());

	frame.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent we) {
		System.exit(0);
	    }
	});

	frame.getContentPane().add(new JScrollPane(treeTable));  
	frame.pack();
	frame.show();
    }
}

