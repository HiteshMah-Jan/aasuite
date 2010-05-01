package component;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.OrientedShape3D;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Point2f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import util.DataUtil;
import util.ImageUtil;

import com.pixelmed.dicom.Attribute;
import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.DicomInputStream;
import com.pixelmed.dicom.TagFromName;
import com.sun.j3d.utils.image.TextureLoader;
import component.sample.ImageKitTmp;
import component.sample.ImageUtilTmp;

/**
 * TexturedPlane creates a single plane with texture mapping.
 */

public class DicomTexturedSlice {
	int location, thickness;
	int width, height;
	static Point2f textCoor1 = new Point2f(0.0f, 1.0f);
	static Point2f textCoor2 = new Point2f(0.0f, 0.0f);
	static Point2f textCoor3 = new Point2f(1.0f, 0.0f);
	static Point2f textCoor4 = new Point2f(1.0f, 1.0f);
	
	static Point3f coor1 = new Point3f(-1.0f, 1.0f, 0);
	static Point3f coor2 = new Point3f(-1.0f, -1.0f, 0);
	static Point3f coor3 = new Point3f(1.0f, -1.0f, 0);
	static Point3f coor4 = new Point3f(1.0f, 1.0f, 0);
	
	File file;
	Vector<Shape3D> shapes = new Vector<Shape3D>();
	boolean complete;
	BufferedImage newImage = null;

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof DicomTexturedSlice) {
			return ((DicomTexturedSlice) arg0).location==location;
		}
		return false;
	}

	public boolean isComplete() {
		return complete;
	}

	public DicomTexturedSlice(File f) {
		this.file = f;
		extractLocation();
	}

	public void addSlices(TransformGroup trans) {
		Geometry geom = null;
		Shape3D shape = null;
		Appearance app = getAppearance();
		for (int i=0; i<1; i++) {
//		for (int i=0; i<thickness; i++) {
			geom = getGeometry(geom, i+location);
//			shape = new OrientedShape3D(geom, app, OrientedShape3D.ROTATE_ABOUT_AXIS, new Point3f(0,0,0));
			shape = new Shape3D(geom, app);
			trans.addChild(shape);
			shapes.add(shape);
		}
	}
	
	public void extractSlices(boolean useCompression) {
		extractDicomImage(useCompression);
		System.out.println("COMPLETE "+file.getName());
		complete = true;
	}
	
	private Geometry getGeometry(Geometry plane, int oldlocation) {
		float newloc = ((float)oldlocation)/1000f;
		coor1.z = newloc;
		coor2.z = newloc;
		coor3.z = newloc;
		coor4.z = newloc;
		System.out.println("LOCATION = "+oldlocation+":"+thickness);
		if (plane!=null) {
			((QuadArray)plane).setCoordinate(0, coor1);
			((QuadArray)plane).setCoordinate(1, coor2);
			((QuadArray)plane).setCoordinate(2, coor3);
			((QuadArray)plane).setCoordinate(3, coor4);
		}
		else {
			plane = new QuadArray(4, GeometryArray.COORDINATES|GeometryArray.TEXTURE_COORDINATE_2);

			((QuadArray)plane).setCoordinate(0, coor1);
			((QuadArray)plane).setCoordinate(1, coor2);
			((QuadArray)plane).setCoordinate(2, coor3);
			((QuadArray)plane).setCoordinate(3, coor4);

			((QuadArray)plane).setTextureCoordinate(0, textCoor1);
			((QuadArray)plane).setTextureCoordinate(1, textCoor2);
			((QuadArray)plane).setTextureCoordinate(2, textCoor3);
			((QuadArray)plane).setTextureCoordinate(3, textCoor4);
		}
		return plane;
	}

	private Appearance getAppearance() {
		Appearance appear = new Appearance();

		TextureLoader loader = new NewTextureLoader(newImage);
		ImageComponent2D loadedImage = loader.getImage();
		newImage.flush();
		
		Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, width, height);
		texture.setCapability(Texture2D.ALLOW_IMAGE_READ);
		texture.setCapability(Texture2D.ALLOW_IMAGE_WRITE);
		texture.setImage(0, loadedImage);
		texture.setEnable(true);
		texture.setMagFilter(Texture.BASE_LEVEL_LINEAR);
		texture.setMinFilter(Texture.BASE_LEVEL_LINEAR);
		texture.setBoundaryModeS(Texture.CLAMP);
		texture.setBoundaryModeT(Texture.CLAMP);
		
		appear.setTexture(texture);
		appear.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.NICEST, 1f));
//        
		LineAttributes lineAtt = new LineAttributes(thickness,LineAttributes.PATTERN_SOLID,false);
        appear.setLineAttributes(lineAtt);

		return appear;
	}
	
	public void filter(int transparentLevel) throws IOException {
		BufferedImage tmp = ImageUtil.copy(newImage);
		ImageUtil.transparentDarkPixel(tmp, transparentLevel);
		TextureLoader loader = new NewTextureLoader(tmp);
		ImageComponent2D image = loader.getImage();
		for (Shape3D shape:shapes) {
			shape.getAppearance().getTexture().setImage(0, image);
		}
		tmp.flush();
		tmp = null;
	}

	public boolean isUseCompression() {
		try {
			DicomInputStream d = new DicomInputStream(file);
			newImage = ImageUtil.getTransparentImageRemoveNoise((BufferedImage) ImageUtil.getImageFromStream(d), Color.BLACK);
			d.close();
			return ImageUtil.ImageKit.isUseCompression(newImage, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private void extractLocation() {
		try {
			DicomInputStream d = new DicomInputStream(file);
			AttributeList lst = new AttributeList();
			lst.read(d);
			Attribute att = lst.get(TagFromName.ImagePositionPatient);
			float[] fl = att.getFloatValues();
			location = (int) fl[2];
			
			att = lst.get(TagFromName.SliceThickness);
			fl = att.getFloatValues();
			thickness = (int) fl[0];
			
			d.close();
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	private void extractDicomImage(boolean useCompression) {
		try {
			if (newImage==null) {
				DicomInputStream d = new DicomInputStream(file);
				newImage = (BufferedImage) ImageUtil.getImageFromStream(d);
				if (useCompression) {
					System.out.println("USING COMPRESSION");
					newImage = ImageUtil.createCompressedImage(ImageUtil.getTransparentImageRemoveNoise(newImage, Color.BLACK), 1);
				}
				else {
					System.out.println("NOT USING COMPRESSION");
					newImage = ImageUtil.getTransparentImageRemoveNoise(newImage, Color.BLACK);
				}
				height = newImage.getHeight();
				width = newImage.getWidth();
				d.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
} // end of TexturedPlane class

