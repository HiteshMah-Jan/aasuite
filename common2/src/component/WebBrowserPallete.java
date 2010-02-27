/*
 * WebBrowser.java
 * 
 * Created on Jan 25, 2008, 4:22:17 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jdesktop.jdic.browser.WebBrowser;
import org.jdesktop.jdic.browser.WebBrowserEvent;
import org.jdesktop.jdic.browser.WebBrowserListener;
import service.util.AbstractIBean;

/**
 *
 * @author Entokwaa
 */
public class WebBrowserPallete extends JPanel implements WebBrowserListener {
    WebBrowser web;
    JButton lbl = new JButton("Web Browser");
    AbstractIBean bean;

    public AbstractIBean getBean() {
        return bean;
    }

    public void setBean(AbstractIBean bean) {
        this.bean = bean;
        if (bean==null) {
            web.setContent("");
        }
        else {
            web.setContent(bean.extractHTMLDocument());
        }
    }
    
    public WebBrowserPallete() {
        this.setLayout(new GridLayout());
        try {
            web = new WebBrowser();
            web.addWebBrowserListener(this);
            this.add(web);
        }
        catch (Exception e) {
            this.add(lbl);
        }
    }

    public void initializationCompleted(WebBrowserEvent arg0) {
    }

    public void downloadStarted(WebBrowserEvent arg0) {
    }

    public void downloadCompleted(WebBrowserEvent arg0) {
    }

    public void downloadProgress(WebBrowserEvent arg0) {
    }

    public void downloadError(WebBrowserEvent arg0) {
    }

    public void documentCompleted(WebBrowserEvent arg0) {
    }

    public void titleChange(WebBrowserEvent arg0) {
    }

    public void statusTextChange(WebBrowserEvent arg0) {
    }
}
