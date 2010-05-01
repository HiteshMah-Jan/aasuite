package component.sample;

import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.View;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Point3d;
 
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
 
/**
 *
 * @author darwinjob
 *
 */
public class Canvas {
 
	private Canvas3D canvas;
	public static final Bounds INFINITE_BOUNDS = new BoundingSphere(new Point3d(), Double.MAX_VALUE);
 
	public Canvas() throws NumberFormatException, IOException {
 
		GraphicsConfiguration graphicsConfiguration = SimpleUniverse.getPreferredConfiguration();
 
    	try {
    		canvas = new Canvas3D(graphicsConfiguration);
    	}
    	catch (java.lang.IllegalArgumentException e) {
    		System.out.println(e);
    	}
 
    	Viewer viewer = new Viewer(canvas);
		ViewingPlatform viewingPlatform = new ViewingPlatform();
		viewer.setViewingPlatform(viewingPlatform);
		viewer.getView().setFrontClipPolicy(View.VIRTUAL_EYE);
		viewer.getView().setTransparencySortingPolicy(View.TRANSPARENCY_SORT_GEOMETRY);
 
		OrbitBehavior orbitBehavior = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL);
		orbitBehavior.setSchedulingBounds(INFINITE_BOUNDS);
		orbitBehavior.setProportionalZoom(true);
		orbitBehavior.setRotationCenter(new Point3d(0.5, 0.5, 0.5));
 
       	viewingPlatform.setViewPlatformBehavior(orbitBehavior);
       	viewingPlatform.setNominalViewingTransform();
 
		VirtualUniverse virtualUniverse = new VirtualUniverse();
		Locale locale = new Locale(virtualUniverse);
 
		BranchGroup root = new BranchGroup();
 
       	//////////////////////////////////////////////////////////
		//Repeats jpeg file 32 times to make z dimension
		//XXX This is for testing only, you have to provide your data here
		File f = new File("C:\\trans1-dcm.jpg");
		BufferedImage bis[] = new BufferedImage[32];
		for (int i=0; i < bis.length; i++) bis[i] = ImageIO.read(f);
		ArrayBuilder ab = new ArrayBuilder(bis);
		//////////////////////////////////////////////////////////
 
		ShapedTexture3D st3d = new ShapedTexture3D(ab.getArray3D(),
				(short)bis[0].getWidth(), (short)bis[0].getHeight(), (short)bis.length);
		System.out.println("The range: " + st3d.getClampedMin() + " " + st3d.getClampedMax());
		/*
		 * thresholding, play with it!!
		 */
		//st3d.setClampedMin(100);
		//st3d.setClampedMax(200);
		root.addChild(st3d);
 
		Background background = new Background();
		background.setApplicationBounds(INFINITE_BOUNDS);
		background.setColor(1,1,1);
		root.addChild(background);
 
		locale.addBranchGraph(root);
		locale.addBranchGraph(viewingPlatform);
 
	}
 
	public Canvas3D getCanvas() {
		return canvas;
	}
}
