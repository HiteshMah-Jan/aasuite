/*
 * SpringCall.java
 * 
 * Created on Oct 30, 2007, 6:36:45 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import service.IService;
import util.Log;
import util.PanelUtil;

/**
 *
 * @author Budoy Entokwa
 */
public abstract class SpringCall {
	public static Object getBean(String beanName) {
		try {
			return Class.forName(beanName).newInstance();
		}
		catch (Exception e) {
            Log.severe("CLASS NOT FOUND ",e.getMessage());
			return null;
		}
	}
	
	public abstract Object call(Object obj);
    
	public static SpringCall getSpringCallBean(Class cls) {
		SpringCall call = (SpringCall) getBean(cls.getName());
		if (call==null) {
            try {
                call = (SpringCall) cls.newInstance();
            } catch (Exception ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
		}
		return call;
	}

	public static FormValidator getValidator(Class cls) {
		FormValidator call = (FormValidator) getBean(cls.getName());
		return call;
	}

	public static IService getService(String serviceName) {
		IService call = (IService) getBean(serviceName);
		if (call == null) {
            try {
                call = (IService) Class.forName(serviceName).newInstance();
            } catch (Exception ex) {
                Logger.getLogger("global").log(Level.SEVERE, ex.getMessage());
            }
		}
		return call;
	}
	
	public static JPanel getPanel(String panel) {
		String cls = PanelUtil.getClassPath(panel);
		JPanel pnl = (JPanel) getBean(cls);
		if (pnl == null) {
            try {
                pnl = (JPanel) Class.forName(cls).newInstance();
            } catch (Exception ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
		}
		return pnl;
	}
	
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
		//Iterate a Map
		Map<String, String> map = new HashMap<String, String>();
		map.put("test", "testvalue");
		for (String str : map.keySet()) {
			Log.out("STR == ",map.get(str));
		}	
		
		test:
			for (int i=0; i<10; i++) {
				Log.out("test i = ",i);
				for (int j=0; j<5; j++) {
					Log.out("test j = ",j);
					if (j==3) {
						break test;
					}
				}
			}
		System.out.println("TEST");
    }

}
