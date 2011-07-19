package util;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

public class JMenuToJTree {
	MutableTreeNode root = new DefaultMutableTreeNode("My Menu");
	JTree tree = new JTree(root);
	DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
	Map<Object, MutableTreeNode> map = new HashMap<Object, MutableTreeNode>();
	StringBuffer sb = new StringBuffer();

	public static JTree menuToJTree(JMenuBar bar) {
		JMenuToJTree mytree = new JMenuToJTree();
		mytree.loopMenu(bar, bar.getComponents());
		System.out.println(mytree.sb.toString());
		return mytree.tree;
	}
	
	private synchronized void loopMenu(Component parent, Component[] comps) {
		if (parent instanceof JMenuBar) {
			for (Component comp : comps) {
				MutableTreeNode node = new DefaultMutableTreeNode(((JMenu) comp).getText());
				model.insertNodeInto(node, root, root.getChildCount());
				map.put(comp, node);
				if (comp instanceof JMenu) {
					Component[] newcomps = ((JMenu) comp).getMenuComponents();
					loopMenu(comp, newcomps);
				}
			}
		}
		else {
			for (Component comp : comps) {
				if (comp instanceof JMenu) {
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(((JMenu)comp).getText());
					MutableTreeNode parentNode = getParentNode(comp);
					model.insertNodeInto(node, parentNode, parentNode.getChildCount());
					map.put(comp, node);

					Component[] newcomps = ((JMenu) comp).getMenuComponents();
					loopMenu(comp, newcomps);
				}
				else {
					JMenuItem item = (JMenuItem)comp;
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(item.getText());
					MutableTreeNode parentNode = getParentNode(comp);
					model.insertNodeInto(node, parentNode, parentNode.getChildCount());
					if (item.getName() != null) {
						sb.insert(0,"view."+item.getName()+".addActionListener(new ActionListener() {\n" +
									"    public void actionPerformed(ActionEvent e) {\n" +
									"        "+item.getName()+"();\n" +
									"    }\n" +
									"});\n");
						sb.append(	"private void "+item.getName()+"() {\n" +
									"}\n");
					}
					else {
						System.out.println(item.getText());
					}
				}
			}
		}
	}
	
	private MutableTreeNode getParentNode(Component comp) {
		JPopupMenu popUp = (JPopupMenu) comp.getParent();
		Container parent = (Container) popUp.getInvoker();
		
		MutableTreeNode node = map.get(parent);
		if (node == null) {
			node = root;
		}
		return node;
	}
}
