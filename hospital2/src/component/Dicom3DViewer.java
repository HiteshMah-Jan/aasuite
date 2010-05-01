package component;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GraphicsContext3D;
import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.ModelClip;
import javax.media.j3d.Raster;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector4d;

import util.ImageUtil;
import util.PanelUtil;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Dicom3DViewer extends JPanel {
	float size = .4f;
	File[] files;
	File folder;
	BranchGroup scene;
	private TransformGroup trans;
	static Executor exec = Executors.newFixedThreadPool(10);
	Vector<DicomTexturedSlice> vec = new Vector();
	public CapturingCanvas3D canvas;
	public SimpleUniverse univ;
	BranchGroup clipBranch;

	MouseRotate myMouseRotate = new MouseRotate();
	MouseTranslate myMouseTranslate = new MouseTranslate();
	MouseZoom myMouseZoom = new MouseZoom();
	Transform3D myTrans3D;

	public Dicom3DViewer(File folder) {
		if (!folder.isDirectory()) {
			this.folder = folder.getParentFile();
		} else {
			this.folder = folder;
		}
		files = this.folder.listFiles();

		setLayout(new BorderLayout());

		canvas = new CapturingCanvas3D(SimpleUniverse.getPreferredConfiguration());
		add(canvas, BorderLayout.CENTER);

		univ = new SimpleUniverse(canvas);
		univ.getViewingPlatform().setNominalViewingTransform();

		scene = createSceneGraph();
		addLight(scene);
		clipBranch = setupClip();
		disableClip();
		scene.compile();
		clipBranch.compile();
		univ.addBranchGraph(scene);
		univ.addBranchGraph(clipBranch);
		show();
	}

	private BranchGroup setupClip() {
		BranchGroup clipBranch = new BranchGroup();
		clipBranch.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		clipBranch.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		clipBranch.setCapability(BranchGroup.ALLOW_BOUNDS_WRITE);
		clipBranch.setCapability(BranchGroup.ALLOW_DETACH);
		TransformGroup clipTrans = new TransformGroup();
		clipTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		clipTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		clipBranch.addChild(clipTrans);

		// Create the rotate behavior node
		MouseRotate clipMouseRotate = new MouseRotate(clipTrans);
		clipMouseRotate.setTransformGroup(clipTrans);
		clipMouseRotate.setSchedulingBounds(new BoundingSphere());
		clipBranch.addChild(clipMouseRotate);

		// Create the zoom behavior node
		MouseZoom clipMouseZoom = new MouseZoom(clipTrans);
		clipMouseZoom.setTransformGroup(clipTrans);
		clipMouseZoom.setSchedulingBounds(new BoundingSphere());
		clipBranch.addChild(clipMouseZoom);

		// Create Model Clip
		ModelClip mc = new ModelClip();
		boolean enables[] = { false, false, false, false, false, false };
		Vector4d eqn = new Vector4d(0.1, 0.0, 0.0, 0.0);
		mc.setEnables(enables);
		mc.setPlane(1, eqn);
		mc.setEnable(1, true);
		mc.setInfluencingBounds(new BoundingSphere());
		clipTrans.addChild(mc);
		return clipBranch;
	}
	
	private void addLight(BranchGroup scene) {
		BoundingSphere worldBounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);

		// Set the light color and its influencing bounds
		AmbientLight light = new AmbientLight();
		light.setEnable(true);
		light.setColor(new Color3f(.3f, .3f, .3f));
		// light.setDirection( new Vector3f( 1.0f, 0.0f, 0.0f ) );
		light.setCapability(AmbientLight.ALLOW_STATE_WRITE);
		light.setCapability(AmbientLight.ALLOW_COLOR_WRITE);
		light.setInfluencingBounds(worldBounds);
		scene.addChild(light);
	}

	private BranchGroup createSceneGraph() {
		// Make a scene graph branch
		BranchGroup branch = new BranchGroup();
		branch.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		branch.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		branch.setCapability(BranchGroup.ALLOW_BOUNDS_WRITE);
		branch.setCapability(BranchGroup.ALLOW_DETACH);

		Background background = new Background();
		background.setColor(0.0f, 0.0f, 0.0f);
		background.setApplicationBounds(new BoundingSphere());
		branch.addChild(background);

		trans = new TransformGroup();
		trans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		myTrans3D = new Transform3D();
		myTrans3D.setScale(new Vector3d(1.0, 1.0, 1.0));
		trans.setTransform(myTrans3D);

		// Make a shape
		// PolygonAttributes poly = new PolygonAttributes();
		// poly.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		// Appearance myAppear = new Appearance();
		// myAppear.setPolygonAttributes(poly);
		// //
		// ColorCube demo = new ColorCube(size);
		// demo.setAppearance(myAppear);
		// trans.addChild(demo);
		PanelUtil.showWaitFrame();
		// this is just a test of compression
		DicomTexturedSlice testSlice = new DicomTexturedSlice(files[0]);
		boolean useCompression = testSlice.isUseCompression();

		for (int i = 0; files != null && i < files.length; i++) {
			AddSliceRunnable r = new AddSliceRunnable(files[i], useCompression);
			DicomTexturedSlice slice = r.getSlice();
			if (!vec.contains(slice)) {
				vec.add(slice);
				exec.execute(r);
			}
		}

		for (int i = 0; i < 10000; i++) {
			try {
				Thread.currentThread().sleep(100);
				// check if all completed
				boolean complete = true;
				for (DicomTexturedSlice slice : vec) {
					if (!slice.isComplete()) {
						complete = false;
						break;
					}
				}
				if (complete) {
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int k = 0; k < vec.size(); k++) {
			DicomTexturedSlice slice = vec.get(k);
			if (k == 0) {
				// adjust the screen
				myTrans3D = new Transform3D();
				myTrans3D.setScale(new Vector3d(1.0, 1.0, slice.thickness));
				trans.setTransform(myTrans3D);
			}
			slice.addSlices(trans);
		}
		PanelUtil.hideWaitFrame();
		branch.addChild(trans);

//		MouseRotate myMouseRotate = new MouseRotate();
		myMouseRotate.setTransformGroup(trans);
		myMouseRotate.setSchedulingBounds(new BoundingSphere());
		branch.addChild(myMouseRotate);

//		MouseTranslate myMouseTranslate = new MouseTranslate();
		myMouseTranslate.setTransformGroup(trans);
		myMouseTranslate.setSchedulingBounds(new BoundingSphere());
		branch.addChild(myMouseTranslate);

//		MouseZoom myMouseZoom = new MouseZoom();
		myMouseZoom.setTransformGroup(trans);
		myMouseZoom.setSchedulingBounds(new BoundingSphere());
		branch.addChild(myMouseZoom);

		// branch.compile();
		return branch;
	}

	public static void main(String[] args) {
		// Texture3DTest.main(args);
		File folder = new File("D:\\dicom\\VIX\\");
		PanelUtil.displayToFrame(new Dicom3DViewer(folder));
	}

	public void makeTransparentLevel(final int transparentLevel) {
		for (DicomTexturedSlice slice : vec) {
			Runnable r = new FilterSliceRunnable(slice, transparentLevel);
			exec.execute(r);
		}
	}

	private class AddSliceRunnable implements Runnable {
		File f;
		DicomTexturedSlice dicom;
		boolean useCompression;

		AddSliceRunnable(File f, boolean useCompression) {
			this.f = f;
			this.useCompression = useCompression;
		}

		DicomTexturedSlice getSlice() {
			dicom = new DicomTexturedSlice(f);
			return dicom;
		}

		@Override
		public void run() {
			dicom.extractSlices(useCompression);
		}
	}

	private class FilterSliceRunnable implements Runnable {
		DicomTexturedSlice dicom;
		int transparentLevel;

		FilterSliceRunnable(DicomTexturedSlice dicom, int transparentLevel) {
			this.dicom = dicom;
			this.transparentLevel = transparentLevel;
		}

		@Override
		public void run() {
			try {
				dicom.filter(transparentLevel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("FILTER DICOM");
		}
	}

	int degVal = 45;
	int xdeg = 0;
	int ydeg = 0;
	int thick = 1;
	double zoom = 1;

	private void transform() {
		Transform3D newTrans3D = new Transform3D();
		newTrans3D.rotX(Math.toRadians(0)); //center
		if (ydeg==0) newTrans3D.rotX(Math.toRadians(xdeg)); //top and down only
		if (xdeg==0) newTrans3D.rotY(Math.toRadians(ydeg)); //left and right only
		newTrans3D.setScale(new Vector3d(1.0 * zoom, 1.0 * zoom, thick * zoom));

		trans.setTransform(newTrans3D);
	}

	public void rotateTop() {
		ydeg = 0;
		xdeg = degVal*-1;
		transform();
	}
	public void rotateDown() {
		ydeg = 0;
		xdeg = degVal;
		transform();
	}

	public void rotateLeft() {
		xdeg = 0;
		ydeg = degVal*-1;
		transform();
	}
	public void rotateRight() {
		xdeg = 0;
		ydeg = degVal;
		transform();
	}

	public void rotateCenter() {
		xdeg = 0;
		ydeg = 0;
		transform();
	}

	public void showThickness(int i) {
		thick = i;
		transform();
	}
	public void showZoom(double d) {
		zoom = d;
		transform();
	}
	
	GridBagConstraints con = new GridBagConstraints();
	public void captureScreens(JPanel pnl, int width) {
		try {
			Robot robot = new Robot();
			BufferedImage img = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			img = ImageUtil.getSubImage(img, canvas);
			img = ImageUtil.resize(img, width, width);

			con.gridy++;
			con.ipadx = con.ipady = 2;
			JLabel lbl = new JLabel();
			lbl.setSize(width, width);
			ImageIcon icon = new ImageIcon();
			icon.setImage(img);
			lbl.setIcon(icon);
			pnl.add(lbl, con);
			pnl.updateUI();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}


	public static class CapturingCanvas3D extends Canvas3D {
		private boolean capture;
		BufferedImage img;
		int width = 200;
		JPanel pnl;
		GridBagConstraints con = new GridBagConstraints();

		private Canvas3D getMe() {
			return this;
		}
		
		public CapturingCanvas3D(GraphicsConfiguration gc) {
			super(gc);
		}

	}


	public void enableClip() {
		myMouseRotate.setEnable(false);
		myMouseTranslate.setEnable(false);
		myMouseZoom.setEnable(false);

		clipBranch.detach();
		clipBranch.removeAllChildren();
		clipBranch = setupClip();
		univ.addBranchGraph(clipBranch);
	}

	public void disableClip() {
		myMouseRotate.setEnable(true);
		myMouseTranslate.setEnable(true);
		myMouseZoom.setEnable(true);
		
		clipBranch.detach();
		clipBranch.removeAllChildren();
	}

} // end of class Hello3d
