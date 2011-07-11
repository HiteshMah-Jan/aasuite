import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;


public class FrameV3 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameV3 frame = new FrameV3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameV3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMyMenu = new JMenu("My Menu");
		menuBar.add(mnMyMenu);
		
		JMenuItem mntmNewItem = new JMenuItem("New Item");
		mnMyMenu.add(mntmNewItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(desktopPane);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setResizable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setClosable(true);
		internalFrame.setBounds(36, 34, 146, 148);
		desktopPane.add(internalFrame);
		
		JInternalFrame internalFrame_1 = new JInternalFrame("New JInternalFrame");
		internalFrame_1.setClosable(true);
		internalFrame_1.setIconifiable(true);
		internalFrame_1.setMaximizable(true);
		internalFrame_1.setResizable(true);
		internalFrame_1.setBounds(208, 54, 172, 168);
		desktopPane.add(internalFrame_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, null, null, null));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
		internalFrame_1.setVisible(true);
		internalFrame.setVisible(true);
	}
}
