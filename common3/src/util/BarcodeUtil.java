/*
 * BarcodeUtil.java
 *
 * Created on Dec 26, 2007, 3:02:50 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Budoy Entokwa
 */
public class BarcodeUtil {

    private static final int firstLength = 0;

    public static Image getBarcode(String first) {
        Barcode b = new Barcode128();
        b.setCode(first);
        return b.createAwtImage(Color.black, Color.white);
    }

    public static String getPaddedBarcode(String first) {
        if (first == null) {
            return null;
        }
        first += "00000000000000000000";
        return first.substring(0, firstLength);
    }

    public static String readBarcode(Image img) {
        Barcode128 b = new Barcode128();
        return null;
    }

    public static String readFirst(Image img) {
        String str = readBarcode(img);
        return readFirst(str);
    }

    public static String readAny(Image img) {
        String str = readBarcode(img);
        return readAny(str);
    }

    public static String readFirst(String str) {
        if (str != null && str.length() >= firstLength) {
            return str.substring(0, firstLength);
        } else {
            return str;
        }
    }

    public static String readAny(String str) {
        if (str != null && str.length() >= firstLength) {
            return str.substring(firstLength);
        } else {
            return "";
        }
    }

    public static JLabel getBarcodeLabel(String barcode) {
        JLabel lbl = new JLabel(new ImageIcon(getBarcode(barcode)));
        return lbl;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Barcode");
        frame.setLayout(new GridLayout(1,1,50,50));
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));

        panel.add(getBarcodeLabel("4710088633880"));
        panel.add(getBarcodeLabel("08_10001"));
        panel.add(getBarcodeLabel("08_10002"));
        panel.add(getBarcodeLabel("08_10003"));
        panel.add(getBarcodeLabel("08_10004"));
        panel.add(getBarcodeLabel("08_10005"));
        panel.add(getBarcodeLabel("08_10006"));
        panel.add(getBarcodeLabel("08_10007"));
        panel.add(getBarcodeLabel("08_10008"));
        panel.add(getBarcodeLabel("08_10009"));
        panel.add(getBarcodeLabel("08_10010"));
        panel.add(getBarcodeLabel("GALERO"));
        panel.add(getBarcodeLabel("ESTABILLO"));
        panel.add(getBarcodeLabel("Entokwa"));
        panel.add(getBarcodeLabel("BALUYOT"));
        panel.add(getBarcodeLabel("ESPELINGOTNGOT"));
        JLabel lbl = new JLabel("Click here to save image.");
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {
                    JLabel lbl = (JLabel) e.getSource();
                    JPanel pnl = (JPanel) lbl.getParent();
                    JFileChooser chooser = new JFileChooser();
                    chooser.setSelectedFile(new File("barcode.jpg"));
                    chooser.showSaveDialog(pnl);
                    File file = chooser.getSelectedFile();
                    if (file == null) {
                        return;
                    }
                    int w = pnl.getWidth();
                    int h = pnl.getHeight();
                    BufferedImage image = (BufferedImage) pnl.createImage(w, h);
                    Graphics g = image.getGraphics();
                    if (g.getClipBounds() != null) {
                        g.setClip(0, 0, w, h);
                    }
                    pnl.paint(g);
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                        encoder.encode(image);
                        out.flush();
                        out.close();
                    } catch (Exception ioe) {
                        /* handle exception */
                    }
//                }
            }
        });
        panel.add(lbl);
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
