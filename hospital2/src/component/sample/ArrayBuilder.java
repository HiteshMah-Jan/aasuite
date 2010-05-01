package component.sample;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * 
 * @author darwinjob
 * 
 */
public class ArrayBuilder {

	/*
	 * We don't really need double in this example, we use it for compatibility
	 * with the example above.
	 */
	private double[][][] array3D;

	/**
	 * 
	 * @param bufferedImages
	 */
	public ArrayBuilder(BufferedImage[] bufferedImages) {

		// Width and height of images are equal for all images, so we take the
		// first - [0]
		final int WIDTH = bufferedImages[0].getWidth();// X dimension
		final int HEIGHT = bufferedImages[0].getHeight();// Y dimension
		final int NUMBER_OF_IMAGES = bufferedImages.length;// Z dimension

		array3D = new double[WIDTH][HEIGHT][NUMBER_OF_IMAGES];

		for (int k = 0; k < NUMBER_OF_IMAGES; k++) {
			BufferedImage bi = bufferedImages[k];
			for (int j = 0; j < HEIGHT; j++) {
				for (int i = 0; i < WIDTH; i++) {
					Color color = new Color(bi.getRGB(i, j));
					/*
					 * For gray scale images (which is most likely the case for
					 * PET, MRI and CT) R=G=B, so we take red.
					 */
					array3D[i][j][k] = color.getRed();
				}
			}
		}

	}

	/**
	 * 
	 * @return
	 */
	public double[][][] getArray3D() {
		return array3D;
	}

}