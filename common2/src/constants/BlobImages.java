/*
 * BlobImages.java
 *
 * Created on Oct 31, 2007, 11:16:52 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package constants;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Budoy Entokwa
 */
public class BlobImages implements Serializable {

    List<byte[]> lst = new ArrayList<byte[]>();

    public void remove(int index) {
        if (lst == null || lst.size() == 0) {
            return;
        }
        if (index >= lst.size() || index < 0) {
            return;
        }
        lst.remove(index);
    }

    public List<byte[]> getLst() {
        return lst;
    }

    public void setLst(List<byte[]> lst) {
        this.lst = lst;
    }

    public void addImage(BufferedImage img) {
        byte[] b = getImageByte(img);
        lst.add(b);
    }

    public List<BufferedImage> getImageList() {
        List<BufferedImage> lstImages = new ArrayList<BufferedImage>();
        for (byte[] b : lst) {
            lstImages.add(getBufferedImage(b));
        }
        return lstImages;
    }

    public BufferedImage getImageAt(int i) {
        List<BufferedImage> tmplst = getImageList();
        if (i > tmplst.size() - 1) {
            return null;
        }
        return tmplst.get(i);
    }

    public int getSize() {
        return this.getImageList().size();
    }

    public void clear() {
        lst = new ArrayList<byte[]>();
    }

    public static byte[] getImageByte(BufferedImage img) {
        try {
            java.io.ByteArrayOutputStream bas = new java.io.ByteArrayOutputStream();
            javax.imageio.ImageIO.write(img, "jpg", bas);
            byte[] data = bas.toByteArray();
            Logger.getLogger("global").log(Level.INFO, "BYTE SIZE == " + data.length);
            return data;
        } catch (IOException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static BufferedImage getBufferedImage(byte[] b) {
        try {
            java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(b);
            return javax.imageio.ImageIO.read(bais);
        } catch (IOException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static BufferedImage getClipboardImage() {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable trans = clipboard.getContents(null);
            Image img = (Image) trans.getTransferData(DataFlavor.imageFlavor);
            java.awt.image.BufferedImage bimg = toBufferedImage(img);
            return bimg;
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent Pixels
        boolean hasAlpha = true;

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (Exception e) {
            Logger.getLogger("global").log(Level.SEVERE, null, e);
            // The system does not have a screen
        }

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            java.awt.image.BufferedImage img = getClipboardImage();
            byte[] b = getImageByte(img);
            img = getBufferedImage(b);
            b = getImageByte(img);
            img = getBufferedImage(b);
            File f = new java.io.File("D://pics.jpg");
            f.delete();
            javax.imageio.ImageIO.write(img, "jpg", f);
        } catch (IOException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }
}
