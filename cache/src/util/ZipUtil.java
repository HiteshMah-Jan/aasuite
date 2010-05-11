package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZipUtil {

    public static Object getObject(byte[] b) {
        Object object = null;
        try {
            object = new java.io.ObjectInputStream(new ByteArrayInputStream(b)).readObject();
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return object;
    }

    public static Object getUnzipObject(byte[] b) {
        Object object = null;
        try {
            object = new java.io.ObjectInputStream(new ByteArrayInputStream(unzip(b))).readObject();
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return object;
    }

    public static byte[] getBytes(Object obj) {
        byte[] data = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
            bos.close();
            data = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static byte[] getBytes(InputStream is) throws IOException {
        byte[] b = new byte[100000];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int countRead = is.read(b);
        while (countRead > 0) {
            baos.write(b, 0, countRead);
            countRead = is.read(b);
        }
        b = baos.toByteArray();
        baos.close();
        return b;
    }

    public static byte[] getZipBytes(Object obj) {
        return zip(getBytes(obj));
    }

    public static byte[] zip(byte[] input) {
        Deflater compressor = new Deflater();
        compressor.setLevel(Deflater.BEST_COMPRESSION);
        compressor.setInput(input);
        compressor.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);
        byte[] buf = new byte[1024];
        while (!compressor.finished()) {
            int count = compressor.deflate(buf);
            bos.write(buf, 0, count);
        }
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] b = bos.toByteArray();
        System.out.println("SIZE INPUT:ZIP == " + input.length + ":" + b.length);
        return b;
    }

    private static byte[] unzip(byte[] compressedData) {
        Inflater decompressor = new Inflater();
        decompressor.setInput(compressedData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(
                compressedData.length);
        byte[] buf = new byte[1024];
        while (!decompressor.finished()) {
            try {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            } catch (DataFormatException e) {
                e.printStackTrace();
            }
        }
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
}
