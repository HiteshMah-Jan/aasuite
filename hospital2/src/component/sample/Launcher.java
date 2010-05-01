package component.sample;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.io.IOException;
 
import javax.swing.JPanel;
import javax.swing.JFrame;
 
/**
 *
 * @author darwinjob
 *
 */
public class Launcher extends JFrame {
 
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Launcher thisClass = new Launcher();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
 
	/**
	 * This is the default constructor
	 */
	public Launcher() {
		super();
		initialize();
	}
 
	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 400);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}
 
	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
 
			try {
				Canvas s = new Canvas();
				jContentPane.add(s.getCanvas(), BorderLayout.CENTER);
 
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
 
		}
		return jContentPane;
	}
 
}
