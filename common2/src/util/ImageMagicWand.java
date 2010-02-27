package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageMagicWand {
	List<Point> lstBound = new ArrayList();
	Polygon poly;
	Color boundColor;
	BufferedImage img;
	
	public static ImageMagicWand getInstance() {
		ImageMagicWand magic = new ImageMagicWand();
		return magic;
	}
	
	public int getWidth() {
		if (poly==null) return -1;
		return poly.getBounds().width;
	}
	
	public int getHeight() {
		if (poly==null) return -1;
		return poly.getBounds().height;
	}

	public int getX() {
		if (poly==null) return -1;
		return poly.getBounds().x;
	}

	public int getY() {
		if (poly==null) return -1;
		return poly.getBounds().y;
	}

	private void processRegion(BufferedImage img, Point p, int tolerance, Color boundColor) {
		this.img = img;
		this.boundColor = boundColor;
		int rgb = img.getRGB(p.x, p.y);
		Color c = new Color(rgb);
		lst.clear();
		markTopRegion(img, new Point(p.x, p.y-1), c, tolerance);
		markRightRegion(img, new Point(p.x+1, p.y), c, tolerance);
		markDownRegion(img, new Point(p.x+1, p.y+1), c, tolerance);
		markLeftRegion(img, new Point(p.x-1, p.y), c, tolerance);
		
		poly = new Polygon(getAllX(), getAllY(), lstBound.size());
		lst.clear();
	}
	
	public void processMarkedRegion(BufferedImage img, Point p, int tolerance, Color boundColor, boolean transparentOutside) {
		processRegion(img, p, tolerance, boundColor);
		Graphics g = img.getGraphics();
		g.setColor(boundColor);
		if (transparentOutside) {
			for (int x=0; x<img.getWidth(); x++) {
				for (int y=0; y<img.getHeight(); y++) {
					if (!poly.contains(x, y)) {
						img.setRGB(x, y, 0x8F1C1C);
					}
				}
			}
		}
		else {
			g.drawPolygon(poly);
		}
		g.dispose();
	}

	List<Point> lst = new ArrayList();

	private void markDownRegion(BufferedImage img, Point p, Color rgb, int tolerance) {
		if (lst.contains(p) || p.x<=1 || p.y<=1 || p.x>=img.getWidth()-1 || p.y>=img.getHeight()-1) {
			return;
		}
		lst.add(p);
		int x = p.x;
		int y = p.y;
//		System.out.println("COOR "+x+":"+y);
		try {
//			process top left
			if (!isToleranceOk(rgb, img.getRGB(x + 1, y + 1), tolerance)) {
				lstBound.add(new Point(x + 1, y + 1));
			}
			else {
				Point newp = new Point(x + 1, y + 1);
				markDownRegion(img, newp, rgb, tolerance);
			}
//			process top right
			if (!isToleranceOk(rgb, img.getRGB(x - 1, y + 1), tolerance)) {
				lstBound.add(new Point(x - 1, y + 1));
			}
			else {
				Point newp = new Point(x - 1, y + 1);
				markDownRegion(img, newp, rgb, tolerance);
			}
		}
		catch (Exception e) {
			System.out.println("POINT = "+p);
			e.printStackTrace();
		}
	}

	private void markLeftRegion(BufferedImage img, Point p, Color rgb, int tolerance) {
		if (lst.contains(p) || p.x<=1 || p.y<=1 || p.x>=img.getWidth()-1 || p.y>=img.getHeight()-1) {
			return;
		}
		lst.add(p);
		int x = p.x;
		int y = p.y;
//		System.out.println("COOR "+x+":"+y);
		try {
//			process top left
			if (!isToleranceOk(rgb, img.getRGB(x - 1, y + 1), tolerance)) {
				lstBound.add(new Point(x - 1, y + 1));
			}
			else {
				Point newp = new Point(x - 1, y + 1);
				markLeftRegion(img, newp, rgb, tolerance);
			}
//			process top right
			if (!isToleranceOk(rgb, img.getRGB(x - 1, y - 1), tolerance)) {
				lstBound.add(new Point(x - 1, y - 1));
			}
			else {
				Point newp = new Point(x - 1, y - 1);
				markLeftRegion(img, newp, rgb, tolerance);
			}
		}
		catch (Exception e) {
			System.out.println("POINT = "+p);
			e.printStackTrace();
		}
	}

	private void markRightRegion(BufferedImage img, Point p, Color rgb, int tolerance) {
		if (lst.contains(p) || p.x<=1 || p.y<=1 || p.x>=img.getWidth()-1 || p.y>=img.getHeight()-1) {
			return;
		}
		lst.add(p);
		int x = p.x;
		int y = p.y;
//		System.out.println("COOR "+x+":"+y);
		try {
//			process top left
			if (!isToleranceOk(rgb, img.getRGB(x + 1, y - 1), tolerance)) {
				lstBound.add(new Point(x + 1, y - 1));
			}
			else {
				Point newp = new Point(x + 1, y - 1);
				markRightRegion(img, newp, rgb, tolerance);
			}
//			process top right
			if (!isToleranceOk(rgb, img.getRGB(x + 1, y + 1), tolerance)) {
				lstBound.add(new Point(x + 1, y + 1));
			}
			else {
				Point newp = new Point(x + 1, y + 1);
				markRightRegion(img, newp, rgb, tolerance);
			}
		}
		catch (Exception e) {
			System.out.println("POINT = "+p);
			e.printStackTrace();
		}
	}

	private void markTopRegion(BufferedImage img, Point p, Color rgb, int tolerance) {
		if (lst.contains(p) || p.x<=1 || p.y<=1 || p.x>=img.getWidth()-1 || p.y>=img.getHeight()-1) {
			return;
		}
		lst.add(p);
		int x = p.x;
		int y = p.y;
//		System.out.println("COOR "+x+":"+y);
		try {
//			process top left
			if (!isToleranceOk(rgb, img.getRGB(x - 1, y - 1), tolerance)) {
				lstBound.add(new Point(x - 1, y - 1));
			}
			else {
				Point newp = new Point(x - 1, y - 1);
				markTopRegion(img, newp, rgb, tolerance);
			}
//			process top right
			if (!isToleranceOk(rgb, img.getRGB(x + 1, y - 1), tolerance)) {
				lstBound.add(new Point(x + 1, y - 1));
			}
			else {
				Point newp = new Point(x + 1, y - 1);
				markTopRegion(img, newp, rgb, tolerance);
			}
		}
		catch (Exception e) {
			System.out.println("POINT = "+p);
			e.printStackTrace();
		}
	}

	public boolean isToleranceOk(Color patternRGB, int rgb, int tolerance) {
		int red = ImageUtil.getRed(rgb);
		int blue = ImageUtil.getBlue(rgb);
		int green = ImageUtil.getGreen(rgb);

		int pred = patternRGB.getRed();
		int pblue = patternRGB.getBlue();
		int pgreen = patternRGB.getGreen();

		if (red + tolerance < pred || red - tolerance > pred) {
			return false;
		}
		if (blue + tolerance < pblue || blue - tolerance > pblue) {
			return false;
		}
		if (green + tolerance < pgreen || green - tolerance > pgreen) {
			return false;
		}
		return true;
	}

	private int[] getAllX() {
		int[] ret = new int[lstBound.size()];
		for (int i=0; i<lstBound.size(); i++) {
			ret[i] = lstBound.get(i).x;
		}
		return ret;
	}

	private int[] getAllY() {
		int[] ret = new int[lstBound.size()];
		for (int i=0; i<lstBound.size(); i++) {
			ret[i] = lstBound.get(i).y;
		}
		return ret;
	}
}
