package component;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.DataUtil;
import util.ImageUtil;

import com.pixelmed.dicom.Attribute;
import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.DicomInputStream;
import com.pixelmed.dicom.TagFromName;

public class DicomReader {
	public float location;
	public float thickness;
	private File dicom;
	
	public DicomReader(File dicom) {
		this.dicom = dicom;
	}
	
	public BufferedImage getDicomImage() {
		BufferedImage img = null;
		try {
			DicomInputStream d = new DicomInputStream(dicom);
			img = (BufferedImage) ImageUtil.getImageFromStream(d);
			img = (BufferedImage) ImageUtil.getTransparentImage(img, Color.BLACK);
			d.close();
			
			d = new DicomInputStream(dicom);
			AttributeList lst = new AttributeList();
			lst.read(d);
			Attribute att = lst.get(TagFromName.ImagePositionPatient);
			float[] fl = att.getFloatValues();
			location = fl[2];
			
			att = lst.get(TagFromName.SliceThickness);
			fl = att.getFloatValues();
			thickness = fl[0];
			d.close();
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

	public void writeToJPEG(File jpg) {
		try {
			ImageIO.write(getDicomImage(), "JPG", jpg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeToJPEG(File jpg, int transparentLevel) {
		try {
			BufferedImage img = getDicomImage();
			ImageUtil.transparentDarkPixel(img, transparentLevel);
			ImageIO.write(img, "JPG", jpg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DicomReader dicom = new DicomReader(new File("D:\\dicom\\VIX\\IM-0001-0001.dcm"));
		dicom.writeToJPEG(new File("C:\\trans1-dcm.jpg"), 2);
		dicom.writeToJPEG(new File("C:\\trans2-dcm.jpg"), 4);
		dicom.writeToJPEG(new File("C:\\trans3-dcm.jpg"), 6);
		dicom.writeToJPEG(new File("C:\\trans4-dcm.jpg"), 8);
	}
}
