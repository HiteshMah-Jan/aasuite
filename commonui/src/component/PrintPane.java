/*
 * PrintPane.java
 *
 * Created on Dec 24, 2007, 9:13:00 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author Budoy Entokwa
 */
public class PrintPane extends JTextPane implements Printable {

    public PrintPane() {
        HTMLEditorKit kit = new HTMLEditorKit();
        setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("table { border-width: 0px 0px 0px 0px; border-spacing: 1px; border-style: outset outset outset outset; border-color: gray gray gray gray; border-collapse: separate; background-color: white;}");
        styleSheet.addRule("th { border-width: thin thin thin thin; padding: 0px 0px 0px 0px; border-style: groove groove groove groove; border-color: black black black black; background-color: white; -moz-border-radius: 0px 0px 0px 0px;}");
        styleSheet.addRule("td { border-width: thin thin thin thin; padding: 0px 0px 0px 0px; border-style: groove groove groove groove; border-color: black black black black; background-color: white; -moz-border-radius: 0px 0px 0px 0px;}");
        Document doc = kit.createDefaultDocument();
        setDocument(doc);
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE; 
        } else {
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            // Turn off double buffering
            this.paint(g2d);
            // Turn double buffering back on
            return PAGE_EXISTS;
        }
    }
}
