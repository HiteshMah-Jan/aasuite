package component.sample;

import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
 
import javax.media.j3d.Appearance;
import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent3D;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture3D;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector4f;
 
/**
 *
 * @author darwinjob
 *
 */
public class ShapedTexture3D extends Shape3D {
 
	private double[][][] double3d;
	private short xDim;
	private short yDim;
	private short zDim;
 
	//WHITE:
	private Color3f color = new Color3f(1,1,1);
 
	private final Vector4f X_PLANE = new Vector4f(1,0,0,0);
	private final Vector4f Y_PLANE = new Vector4f(0,1,0,0);
	private final Vector4f Z_PLANE = new Vector4f(0,0,1,0);
	private double cal_min;
	private double cal_max;
	private double clampedMin;
	private double clampedMax;
 
	/**
	 *
	 * @param double3d - 3D data array
	 * @param xDim
	 * @param yDim
	 * @param zDim
	 */
	public ShapedTexture3D(double[][][] double3d, short xDim, short yDim, short zDim) {
 
		this.double3d = double3d;
 
		this.xDim = xDim;
		this.yDim = yDim;
		this.zDim = zDim;
 
		prepareAppearance();
 
		findMinAndMaxValues();
		clampedMin = cal_min;
		clampedMax = cal_max;
 
		clamp();
 
	}
 
	private void prepareAppearance() {
 
        PolygonAttributes p = new PolygonAttributes();
        p.setCullFace(PolygonAttributes.CULL_NONE);
        Material m = new Material();
        m.setLightingEnable(false);
        m.setCapability(Material.ALLOW_COMPONENT_WRITE);
 
        TexCoordGeneration tg = new TexCoordGeneration();
        tg.setFormat(TexCoordGeneration.TEXTURE_COORDINATE_3);
        tg.setPlaneS(X_PLANE);
        tg.setPlaneT(Y_PLANE);
        tg.setPlaneR(Z_PLANE);
 
        Appearance a = new Appearance();
 
        a.setTexCoordGeneration(tg);
        a.setMaterial(m);
        a.setPolygonAttributes(p);
        a.setTransparencyAttributes(
        		new TransparencyAttributes(TransparencyAttributes.NICEST, 0.5f));
 
        a.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
 
        this.setAppearance(a);
 
	}
 
	private void findMinAndMaxValues() {
 
		cal_min = Double.MAX_VALUE;
		cal_max = Double.MIN_VALUE;
		for (int k = 0; k < zDim; k++) {
			for (int j = 0; j < yDim; j++) {
				for (int i = 0; i < xDim; i++) {
					if (double3d[i][j][k] > cal_max) cal_max = double3d[i][j][k];
					if (double3d[i][j][k] < cal_min) cal_min = double3d[i][j][k];
				}
			}
		}
 
	}
 
 
	private void clamp() {
 
		ImageComponent3D pArray = new ImageComponent3D(ImageComponent.FORMAT_RGBA, xDim, yDim, zDim);
 
		ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_sRGB);
 
		ComponentColorModel colorModel =
			new ComponentColorModel(colorSpace, true, false,
					Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);
 
		WritableRaster raster =
			colorModel.createCompatibleWritableRaster(xDim, yDim);
 
		BufferedImage bImage =
			new BufferedImage(colorModel, raster, false, null);
 
		byte[] byteData = ((DataBufferByte)raster.getDataBuffer()).getData();
 
		//COLORS: [0;255] 0 - black, 255 - white
		//TRANSP: [0;255] 0 - fully transparent, 255 - opaque
		final Vector4f color4f = new Vector4f(color.x, color.y, color.z, 1);
		for (int z = 0; z < zDim; z++) {
			int index = 0;
			for (int  y = 0; y < yDim ; y++) {
				for (int x = 0; x < xDim; x++) {
 
					double data = double3d[x][y][z];
					if (data < clampedMin) data = clampedMin;
					if (data > clampedMax) data = clampedMax;
					double scale = (data - clampedMin)/(clampedMax - clampedMin);
 
					Vector4f v = new Vector4f(color4f);
					v.scale((float)scale);
 
					//R
					byteData[index++] = (byte)(255*v.x);
					//G
					byteData[index++] = (byte)(255*v.y);
					//B
					byteData[index++] = (byte)(255*v.z);
					//transparency
					byteData[index++] = (byte)(255*v.w);
 
				}
			}
 
			pArray.set(z, bImage);
 
		}
 
		Texture3D texture3D = new Texture3D(Texture.BASE_LEVEL, Texture.RGBA, xDim, yDim, zDim);
		texture3D.setImage(0, pArray);
		texture3D.setEnable(true);
		texture3D.setMinFilter(Texture.BASE_LEVEL_LINEAR);
		texture3D.setMagFilter(Texture.BASE_LEVEL_LINEAR);
		texture3D.setBoundaryModeS(Texture.CLAMP);
		texture3D.setBoundaryModeT(Texture.CLAMP);
		texture3D.setBoundaryModeR(Texture.CLAMP);
 
		this.getAppearance().setTexture(texture3D);
 
		this.removeAllGeometries();
		generateGeometryX(xDim);// + 1);
		generateGeometryY(yDim);// + 1);
		generateGeometryZ(zDim);// + 1);
 
 
	}
 
	/**
	 * Generates geometry along X
	 *
	 * @param num - number of planes to generate
	 */
	private void generateGeometryX(int num) {
 
		float gridSpacing = 1f/num;
 
		for (float x = 0; x <= 1; x+=gridSpacing) {
 
        	Point3f[] genCoords = new Point3f[4];
 
        	genCoords[0] = new Point3f(x,	0,	0);
        	genCoords[1] = new Point3f(x,	1,	0);
        	genCoords[2] = new Point3f(x,	1,	1);
        	genCoords[3] = new Point3f(x,	0,	1);
 
        	QuadArray genSquare = new QuadArray(4, QuadArray.COORDINATES);
        	genSquare.setCoordinates(0, genCoords);
 
        	this.addGeometry(genSquare);
 
        }
	}
 
	/**
	 * Generates geometry along Y
	 *
	 * @param num - number of planes to generate
	 */
	private void generateGeometryY(int num) {
 
		float gridSpacing = 1f/num;
 
		for (float y = 0; y <= 1; y+=gridSpacing) {
 
        	Point3f[] genCoords = new Point3f[4];
 
        	genCoords[0] = new Point3f(0,	y,	0);
        	genCoords[1] = new Point3f(1,	y,	0);
        	genCoords[2] = new Point3f(1,	y,	1);
        	genCoords[3] = new Point3f(0,	y,	1);
 
        	QuadArray genSquare = new QuadArray(4, QuadArray.COORDINATES);
        	genSquare.setCoordinates(0, genCoords);
 
        	this.addGeometry(genSquare);
 
        }
	}
 
	/**
	 * Generates geometry along Z
	 *
	 * @param num - number of planes to generate
	 */
	private void generateGeometryZ(int num) {
 
		float gridSpacing = 1f/num;
 
		for (float z = 0; z <= 1; z+=gridSpacing) {
 
        	Point3f[] genCoords = new Point3f[4];
 
        	genCoords[0] = new Point3f(0,	0,	z);
        	genCoords[1] = new Point3f(1,	0,	z);
        	genCoords[2] = new Point3f(1,	1,	z);
        	genCoords[3] = new Point3f(0,	1,	z);
 
        	QuadArray genSquare = new QuadArray(4, QuadArray.COORDINATES);
        	genSquare.setCoordinates(0, genCoords);
 
        	this.addGeometry(genSquare);
 
        }
	}
 
	public void setClampedMin(double clampedMin) {
		this.clampedMin = clampedMin;
		clamp();
	}
 
	public double getClampedMin() {
		return clampedMin;
	}
 
	public double getClampedMax() {
		return clampedMax;
	}
 
	public void setClampedMax(double clampedMax) {
		this.clampedMax = clampedMax;
		clamp();
	}
 
	public static void main(String[] args) {
 
	}
 
}
