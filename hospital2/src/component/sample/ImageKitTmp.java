package component.sample;

import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.imageio.stream.*;
 
public class ImageKitTmp {
    //quality means jpeg output, if quality is < 0 ==> use default quality
    public static void write(BufferedImage image, float quality, OutputStream out) throws IOException {
        Iterator writers = ImageIO.getImageWritersBySuffix("jpeg");
        if (!writers.hasNext())
            throw new IllegalStateException("No writers found");
        ImageWriter writer = (ImageWriter) writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(out);
        writer.setOutput(ios);
        ImageWriteParam param = writer.getDefaultWriteParam();
        if (quality >= 0) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);
        }
        writer.write(null, new IIOImage(image, null, null), param);
    }
 
    public static BufferedImage read(byte[] bytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static BufferedImage compress(BufferedImage image, float quality) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream(50000);
            write(image, quality, out);
            return ImageIO.read(new ByteArrayInputStream(out.toByteArray()));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
