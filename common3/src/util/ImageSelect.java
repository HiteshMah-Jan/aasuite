package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ImageMagicWand;
import util.ImageUtil;


public class ImageSelect extends JFrame {
	public static void main(String[] args) {
		ImageSelect sel = new ImageSelect();
		sel.pack();
		sel.setVisible(true);
	}
	
	ImageSelect() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout());
		this.setPreferredSize(new Dimension(250, 250));
		try {
			this.add(new ImagePanel((BufferedImage) ImageUtil.getImageFromStream(new FileInputStream("C:/stripe.gif"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static class ImagePanel extends JPanel {
//		EdgeFilter filter = new EdgeFilter();
		BufferedImage img, copy;
		
		ImagePanel(BufferedImage img) {
			try {
				this.img = img;
//				ImageUtil.transparentDarkPixel(this.img, 1);
				this.copy = ImageUtil.copy(this.img);
				this.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						clicked(arg0);
					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		protected void clicked(MouseEvent e) {
			this.copy = ImageUtil.copy(img);
			ImageMagicWand img = ImageMagicWand.getInstance();
			img.processMarkedRegion(copy, e.getPoint(), 45, Color.RED, true);
			Log.out("DIM ",img.getWidth(),":",img.getHeight()," - ",img.getX(),":",img.getY());
			this.repaint();
		}

		@Override
		public void paint(Graphics g) {
			g.drawImage(copy, 0, 0, null);
			g.dispose();
		}
	}
}
