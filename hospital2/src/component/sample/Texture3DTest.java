package component.sample;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.*;

import com.pixelmed.dicom.DicomInputStream;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;
import javax.vecmath.*;

import util.DataUtil;
import util.ImageUtil;

import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.awt.image.WritableRaster;
import java.awt.image.DataBufferByte;
import java.awt.color.ColorSpace;
import java.awt.Transparency;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Texture3DTest extends Applet {

	public BranchGroup createSceneGraph() throws IOException {
		// Create the root of the branch graph
		BranchGroup objRoot = new BranchGroup();

		TransformGroup objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		//
		// Create a 3D texture
		//
		File f = new File("C:\\Arizona.jpg");
		BufferedImage img = (BufferedImage) ImageUtil.getImageFromStream(new FileInputStream(f));
		BufferedImage[] images = new BufferedImage[1];
		images[0] = img;
		ArrayBuilder arr = new ArrayBuilder(images);
		double[][][] darr = arr.getArray3D();
		ShapedTexture3D shape = new ShapedTexture3D(darr, (short)img.getWidth(), (short)img.getHeight(), (short)1);
		objTrans.addChild(shape);
		
		objRoot.addChild(objTrans);
		objRoot.compile();

		return objRoot;
	}

	public Texture3DTest() {
		setLayout(new BorderLayout());
		Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		add("Center", c);

		// Create a simple scene and attach it to the virtual universe
		BranchGroup scene = null;
		try {
			scene = createSceneGraph();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleUniverse u = new SimpleUniverse(c);

		// This will move the ViewPlatform back a bit so the
		// objects in the scene can be viewed.
		u.getViewingPlatform().setNominalViewingTransform();

		u.addBranchGraph(scene);
	}

	//
	// The following allows Texture3DTest to be run as an application
	// as well as an applet
	//
	public static void main(String[] args) {
		new MainFrame(new Texture3DTest(), 256, 256);
	}

}
