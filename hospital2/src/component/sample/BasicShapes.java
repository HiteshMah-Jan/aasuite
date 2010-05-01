package component.sample;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicShapes extends JPanel {

  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    int width = 100;
    int height = 100;

    BufferedImage bimage = g2d.getDeviceConfiguration().createCompatibleImage(
        width, height, Transparency.BITMASK);

  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Basic Shapes");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new BasicShapes());
    frame.setSize(350, 250);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}