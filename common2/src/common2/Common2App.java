/*
 * Common2App.java
 */

package common2;

import java.awt.Color;
import java.awt.Dimension; 
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import util.DBClient;
import util.DateUtil;
import util.PanelUtil;
import bean.admin.AppConfig;
import bean.admin.AppMenu;

import component.reportdesigner.ReportDesigner;

/**
 * The main class of the application.
 */
public class Common2App extends SingleFrameApplication {
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        Common2View view = new Common2View(this);
        final JFrame frame = Common2View.mainView.getFrame();
        frame.setUndecorated(true);
        show(view);
        Common2View.mainView.getFrame().setTitle(constants.Constants.appTitle);
        springbean.AAAConfig config = springbean.AAAConfig.getInstance();
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    frame.setExtendedState(Frame.MAXIMIZED_BOTH);
                    frame.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Common2App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t.start();
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of Common2App
     */
    public static Common2App getApplication() {
        return Application.getInstance(Common2App.class);
    }

    static SplashForm form;
    public static void showSplash() {
        
    }
    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        springbean.AAAConfig.getInstance();
        AppConfig.extractLoadingImage();
        Thread t = new Thread(new Runnable() {
            public void run() {
                form = new SplashForm();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension labelSize = form.getPreferredSize();
                form.setLocation(screenSize.width/2 - (labelSize.width/2), screenSize.height/2 - (labelSize.height/2));
                form.setVisible(true);
            }
        });
        t.start();
        //check if AppMenu is present
        String panelColor = AppConfig.extractPanelBackground();
        String labelColor = AppConfig.extractLabelColor();
        try {
            String[] arr1 = panelColor.split(" ");
            String[] arr2 = labelColor.split(" ");
            Color c1 = PanelUtil.getColor(arr1);
            Color c2 = PanelUtil.getColor(arr2);
            constants.Constants.panelBackground = c1;
            constants.Constants.labelColor = c2;
        }
        catch (Exception e) {
        }
        List lst = AppConfig.lstMenu;
        if (lst==null || lst.isEmpty()) {
//            config.setupDB();
            new AppMenu().runSetup();
//            PanelUtil.showMessage(null, "Complete setup of menu. Please restart application.");
        }
        ReportDesigner.setupReportResources();
        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
        }
        launch(Common2App.class, args);
        t = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Common2App.class.getName()).log(Level.SEVERE, null, ex);
                }
                form.setVisible(false);
            }
        });
        t.start();
        Thread t2 = new Thread(new HeartBeat());
        t2.start();
    }
    
    private static class HeartBeat implements Runnable {
		@Override
		public void run() {
			for (int i=0; i<1000;) {
				try {
					Thread.currentThread().sleep(1000*10);
					boolean b = service.HeartBeat.isAlive();
					if (!b) {
						Thread.currentThread().sleep(1000*5);
						b = service.HeartBeat.isAlive();
						if (!b) {
							System.out.println("CONNECTION PROBLEM == "+DateUtil.getTime());
							if (AppConfig.alwaysCheckConnection()) {
								PanelUtil.showMessage(null, "PLEASE CHECK CONNECTION!!!");
							}
						}
					}
					else {
//						System.out.println("HEARTBEAT OK == "+DateUtil.getTime());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    }
}
