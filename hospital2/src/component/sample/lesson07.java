package component.sample;

import java.awt.Frame;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import util.DataUtil;
import util.ImageUtil;

import com.sun.j3d.utils.image.TextureLoader;

public class lesson07 extends Applet {
	SimpleUniverse simpleU;
	static boolean application = false; // by default, loaded as an applet

	public lesson07() {
	}

	public void init() {
		setLayout(new BorderLayout());
		Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		add("Center", c);
		simpleU = new SimpleUniverse(c);
		BranchGroup scene = createSceneGraph();
		ViewingPlatform vp = simpleU.getViewingPlatform();
		TransformGroup View_TransformGroup = vp.getMultiTransformGroup().getTransformGroup(0);
		Transform3D View_Transform3D = new Transform3D();
		View_TransformGroup.getTransform(View_Transform3D);
		View_Transform3D.setTranslation(new Vector3f(0, 0, 0));
		View_TransformGroup.setTransform(View_Transform3D);
		scene.compile();
		simpleU.addBranchGraph(scene); // add your SceneGraph to the
										// SimpleUniverse
	}

	public BranchGroup createSceneGraph() {
		BranchGroup objRoot = new BranchGroup();

		// this is the green polygon that is behind the fire polygon
		Appearance polygon1Appearance = new Appearance();
		Color3f color1 = new Color3f(1.0f, 1.0f, 1.0f);
		ColoringAttributes color1ca = new ColoringAttributes(color1, 1);
		polygon1Appearance.setColoringAttributes(color1ca);
		QuadArray polygon1 = new QuadArray(4, QuadArray.COORDINATES);
		polygon1.setCoordinate(0, new Point3f(-10f, -10f, -20f));
		polygon1.setCoordinate(1, new Point3f(10f, -10f, -20f));
		polygon1.setCoordinate(2, new Point3f(10f, 10f, -20f));
		polygon1.setCoordinate(3, new Point3f(-10f, 10f, -20f));
		objRoot.addChild(new Shape3D(polygon1, polygon1Appearance));

		QuadArray fire = new QuadArray(4, QuadArray.COORDINATES
				| GeometryArray.TEXTURE_COORDINATE_2);
		fire.setCoordinate(0, new Point3d(-2f, 0f, -10f));
		fire.setCoordinate(1, new Point3d(2f, 0f, -10f));
		fire.setCoordinate(2, new Point3d(2f, 4f, -10f));
		fire.setCoordinate(3, new Point3d(-2f, 4f, -10f));

		Appearance fireApp = new Appearance();

		// Transparency info
		TransparencyAttributes ta = new TransparencyAttributes();

		// type of Transparency, choices are: NONE, FASTEST, NICEST,
		// SCREEN_DOOR, or BLENDED
		ta.setTransparencyMode(ta.BLENDED);

		// the appearance's transparency in the range [0.0, 1.0] with 0.0 being
		// fully opaque and 1.0 being fully transparent
		ta.setTransparency(1f);
		fireApp.setTransparencyAttributes(ta);

		if (application == false) {
			try {
//				java.net.URL fire1a = new java.net.URL(getCodeBase(), "fire1a.gif");
				BufferedImage img = (BufferedImage) ImageUtil.getImageFromStream(new FileInputStream("C:\\fire1a.gif"));
				img = (BufferedImage) ImageUtil.getTransparentImage(img, Color.WHITE);
				Texture texImage0 = new TextureLoader(img, this).getTexture();
				fireApp.setTexture(texImage0);
			}
			catch (Exception ex) {
			}

		} else {
			if (application == true) { // loaded in application
				try {
					BufferedImage img = (BufferedImage) ImageUtil.getImageFromStream(new FileInputStream("C:\\fire1a.png"));
					img = (BufferedImage) ImageUtil.getTransparentImage(img, Color.WHITE);
					Texture texImage = new TextureLoader(img, this).getTexture();
					fireApp.setTexture(texImage);
				}
				catch (Exception e) {
				}
			}
		}

		fire.setTextureCoordinate(0, new Point2f(0.0f, 0.0f));
		fire.setTextureCoordinate(1, new Point2f(1.0f, 0.0f));
		fire.setTextureCoordinate(2, new Point2f(1.0f, 1.0f));
		fire.setTextureCoordinate(3, new Point2f(0.0f, 1.0f));
		objRoot.addChild(new Shape3D(fire, fireApp));

		return objRoot;
	}

	public void destroy() {
		simpleU.removeAllLocales();
	}

	public static void main(String[] args) {
		application = true;
		Frame frame = new MainFrame(new lesson07(), 500, 500);
	}
}