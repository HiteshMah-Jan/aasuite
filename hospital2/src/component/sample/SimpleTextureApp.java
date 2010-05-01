package component.sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.ModelClip;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector4d;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * SimpleTextureApp creates a single plane with texture mapping.
 */
public class SimpleTextureApp extends JPanel {
    TransformGroup objTransform;
    Transform3D t;
    BufferedImage img;
    TexCoord2f q1 = new TexCoord2f(0.0f, 1.0f);
    TexCoord2f q2 = new TexCoord2f(0.0f, 0.0f);
    TexCoord2f q3 = new TexCoord2f(1.0f, 0.0f);
    TexCoord2f q4 = new TexCoord2f(1.0f, 1.0f);

    private void add(int location, TransformGroup objTransform) {
        float realLoc = (location*1f)/1000f;

        QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2);

        Point3f p = new Point3f(-1.0f, 1.0f, realLoc);
        plane.setCoordinate(0, p);
        p.set(-1.0f, -1.0f, realLoc);
        plane.setCoordinate(1, p);
        p.set(1.0f, -1.0f, realLoc);
        plane.setCoordinate(2, p);
        p.set(1.0f, 1.0f, realLoc);
        plane.setCoordinate(3, p);

        plane.setTextureCoordinate(0, 0, q1);
        plane.setTextureCoordinate(0, 1, q2);
        plane.setTextureCoordinate(0, 2, q3);
        plane.setTextureCoordinate(0, 3, q4);

        Appearance appear = new Appearance();

        TextureLoader loader = new TextureLoader(img);
        ImageComponent2D image = loader.getImage();

        // can't use parameterless constuctor
        Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, image.getWidth(), image.getHeight());
        texture.setImage(0, image);

        appear.setTexture(texture);

        appear.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.NICEST, 0.5f));

        Shape3D planeObj = new Shape3D(plane, appear);
        objTransform.addChild(planeObj);
    }

    BranchGroup createScene() {
        BranchGroup objRoot = new BranchGroup();
        
//		BoundingSphere bounds = new BoundingSphere();
//		ModelClip mc = new ModelClip();
//	    mc.setPlane(0, new Vector4d(1, 0, 0, -0.1));
//	    mc.setPlane(1, new Vector4d(0,1,0,-0.1));
//		mc.setEnable(0, true);
//		mc.setEnable(1, true);
//		mc.setInfluencingBounds(bounds);

        objTransform = new TransformGroup();
        objTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//        objTransform.addChild(mc);
        objRoot.addChild(objTransform);
        try {
            img = ImageKitTmp.compress(ImageUtilTmp.getTransparentImageRemoveNoise(ImageIO.read(new File("C:/stripe.gif")), Color.WHITE), 1);
//            img = ImageIO.read(new File("C:/stripe.gif"));
        } catch (IOException ex) {
            Logger.getLogger(SimpleTextureApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < 50; i++) {
            add(i, objTransform);
        }

        Background background = new Background();
        background.setColor(0.0f, 0.0f, 0.0f);
        background.setApplicationBounds(new BoundingSphere());
        objRoot.addChild(background);

        MouseRotate myMouseRotate = new MouseRotate();
        myMouseRotate.setTransformGroup(objTransform);
        myMouseRotate.setSchedulingBounds(new BoundingSphere());
        objRoot.addChild(myMouseRotate);

        MouseTranslate myMouseTranslate = new MouseTranslate();
        myMouseTranslate.setTransformGroup(objTransform);
        myMouseTranslate.setSchedulingBounds(new BoundingSphere());
        objRoot.addChild(myMouseTranslate);

        MouseZoom myMouseZoom = new MouseZoom();
        myMouseZoom.setTransformGroup(objTransform);
        myMouseZoom.setSchedulingBounds(new BoundingSphere());
        objRoot.addChild(myMouseZoom);

        return objRoot;
    }

    public SimpleTextureApp() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);

        canvas3D.setStereoEnable(false);

        SimpleUniverse u = new SimpleUniverse(canvas3D);

        // This will move the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        u.getViewingPlatform().setNominalViewingTransform();

        u.addBranchGraph(createScene());
    }

    public static void main(String argv[]) {
        System.out.print("SimpleTextureApp.java \n- ");
        System.out.println("The simpliest example of using texture mapping.\n");
        System.out.println("This is a simple example progam from The Java 3D API Tutorial.");
        System.out.println("The Java 3D Tutorial is available on the web at:");
        System.out.println("http://java.sun.com/products/java-media/3D/collateral ");

        final SimpleTextureApp sim = new SimpleTextureApp();
        sim.setPreferredSize(new Dimension(250, 250));
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(sim, BorderLayout.CENTER);
        
        JPanel pnl = new JPanel(new FlowLayout());
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sim.reset();
			}
        });
        pnl.add(btnReset);
        frame.add(pnl, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

	protected void reset() {
		Transform3D newt = new Transform3D();
		newt.rotZ(Math.toRadians(0));
		newt.rotY(Math.toRadians(-45));
		newt.setScale(.5);
		objTransform.setTransform(newt);
	}
}

