/*
 * DataUtil.java
 *
 * Created on Oct 2, 2007, 7:26:25 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;

import javax.swing.JFileChooser;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import springbean.AAAConfig;
import bean.admin.AppConfig;

/**
 * 
 * @author Budoy Entokwa
 */
public class DataUtil {
	public static boolean isBetween(double start, double end, double i) {
		return (start<i && end>i); 
	}
	
	public static boolean isIntersecting(double start, double end, double istart, double iend) {
		boolean first = isBetween(start, end, istart);
		boolean second = isBetween(start, end, iend);
//		check if same
		if (start==istart && end==iend) {
			return true;
		}
//		check if anyone is between
		if (first || second) {
			return true;
		}
//		check if both are less
		if (istart<=start && iend<=start) {
			return false;
		}
//		check if both are greater
		if (istart>=end && iend>=end) {
			return false;
		}
//		the whole thing is between, everything intersects
		return true;
	}

	public static List<String> splitString(String str, String delim) {
		String[] strArr = str.split(delim);
		List<String> lst = new ArrayList<String>();
		for (String tmp : strArr) {
			lst.add(tmp);
		}
		return lst;
	}

	public static List<String> splitString(String str, String delim, int size) {
		List<String> lst = splitString(str, delim);
		for (int i = lst.size() - 1; i < size; i++) {
			lst.add("");
		}
		return lst;
	}

	public static List<Double> splitNumbers(String str, int size) {
		List<Double> lst = new ArrayList<Double>();
		if (str == null || str.length() == 0) {
			for (int i = 0; i < size; i++) {
				lst.add(0.0);
			}
		} else {
			StringBuffer tmp = new StringBuffer();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (Character.isDigit(c) || c == '.') {
					// might be number
					tmp.append(c);
				} else {
					if (tmp.length() > 0) {
						double d = getDoubleValue(tmp.toString());
						lst.add(d);
					}
					tmp.setLength(0);
				}
			}
			if (tmp.length() > 0) {
				double d = getDoubleValue(tmp.toString());
				lst.add(d);
			}
		}
		for (int i = lst.size() - 1; i < size; i++) {
			lst.add(0.0);
		}
		return lst;
	}

	public static Double getFirstNumber(String str) {
		if (str == null || str.length() == 0) {
			return 0.0;
		} else {
			StringBuffer tmp = new StringBuffer();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (Character.isDigit(c) || c == '.') {
					// might be number
					tmp.append(c);
				} else {
					if (tmp.length() > 0) {
						double d = getDoubleValue(tmp.toString());
						return d;
					}
				}
			}
			if (tmp.length() > 0) {
				double d = getDoubleValue(tmp.toString());
				return d;
			}
		}
		return 0.0;
	}

	public static List<String> splitAlpha(String str, int size) {
		List<String> lst = new ArrayList<String>();
		if (str == null || str.length() == 0) {
			for (int i = 0; i < size; i++) {
				lst.add("");
			}
		} else {
			StringBuffer tmp = new StringBuffer();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (Character.isLetter(c)) {
					tmp.append(c);
				} else {
					if (tmp.length() > 0) {
						lst.add(tmp.toString());
					}
					tmp.setLength(0);
				}
			}
			if (tmp.length() > 0) {
				lst.add(tmp.toString());
			}
		}
		for (int i = lst.size() - 1; i < size; i++) {
			lst.add("");
		}
		return lst;
	}

	public static List<String> splitString(String str, int i1, int i2, int i3,
			int i4, int i5) {
		List<String> lst = new ArrayList<String>();
		if (str == null) {
			lst.add("");
			lst.add("");
			lst.add("");
			lst.add("");
			lst.add("");
		} else {
			lst.add(getValueAt(str, 0, i1));
			lst.add(getValueAt(str, i1, i2));
			lst.add(getValueAt(str, i1 + i2, i3));
			lst.add(getValueAt(str, i1 + i2 + i3, i4));
			lst.add(getValueAt(str, i1 + i2 + i3 + i4, i5));
		}
		return lst;
	}

	public static List<String> splitString(String str, int i1, int i2, int i3,
			int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
		List<String> lst = new ArrayList<String>();
		if (str == null) {
			lst.add("");
			lst.add("");
			lst.add("");
			lst.add("");
			lst.add("");
			lst.add("");
			lst.add("");
			lst.add("");
			lst.add("");
			lst.add("");
		} else {
			lst.add(getValueAt(str, 0, i1));
			lst.add(getValueAt(str, i1, i2));
			lst.add(getValueAt(str, i1 + i2, i3));
			lst.add(getValueAt(str, i1 + i2 + i3, i4));
			lst.add(getValueAt(str, i1 + i2 + i3 + i4, i5));
			lst.add(getValueAt(str, i1 + i2 + i3 + i4 + i5, i6));
			lst.add(getValueAt(str, i1 + i2 + i3 + i4 + i5 + i6, i7));
			lst.add(getValueAt(str, i1 + i2 + i3 + i4 + i5 + i6 + i7, i8));
			lst.add(getValueAt(str, i1 + i2 + i3 + i4 + i5 + i6 + i7 + i8, i9));
			lst.add(getValueAt(str, i1 + i2 + i3 + i4 + i5 + i6 + i7 + i8 + i9,
					i10));
		}
		return lst;
	}

	public static List<String> splitStringAddSlant(String str) {
		String delim = "\n";
		StringTokenizer st = new StringTokenizer(str, delim);
		List<String> lst = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			if (tmp.startsWith("/")) {
				String ts = lst.get(lst.size() - 1);
				ts += BeanUtil.concat("\n",tmp);
				lst.set(lst.size() - 1, ts);
			} else {
				lst.add(tmp);
			}
		}
		return lst;
	}

	public static String removeNewLine(String str) {
		return str.replace('\n', ' ');
	}

	public static int getIntValue(Object obj) {
		try {
			return Integer.parseInt(obj.toString().trim());
		} catch (Exception e) {
			return -1;
		}
	}

	public static double getDoubleValue(Object obj) {
		try {
			return Double.parseDouble(obj.toString().trim());
		} catch (Exception e) {
			return -1;
		}
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static String getValueAt(List lst, int index) {
		if (lst == null || lst.size() == 0) {
			return null;
		}
		try {
			return lst.get(index).toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String getValueAt(String str, int offset, int length) {
		if (str == null || str.trim().length() == 0) {
			return null;
		}
		try {
			if (str.length() < offset) {
				return "";
			} else {
				if (str.length() < offset + length) {
					return str.substring(offset);
				} else {
					return str.substring(offset, offset + length);
				}
			}
		} catch (Exception e) {
		}
		return "";
	}

	public static double getFormattedNumber(double d, String format) {
		DecimalFormat dec = new DecimalFormat(format);
		String str = dec.format(round(d));
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
		}
		return d;
	}

	public static double getMoneyFormat(double d) {
		return getFormattedNumber(d, "###0.00");
	}

	static DecimalFormat decCurr = new DecimalFormat("##,##0.00");

	public static String getCurrencyFormat(double d) {
		return decCurr.format(round(d));
	}

	public static String getCurrencyFormat(String str) {
		double d = getDoubleValue(str);
		return decCurr.format(round(d));
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
//		Log.out(DataUtil.isBetween(1000, 2000, 1001));
//		Log.out(DataUtil.isBetween(1000, 2000, 1000));
//		Log.out(DataUtil.isBetween(1000, 2000, 999));
//		Log.out(DataUtil.isBetween(1000, 2000, 2001));
		
		Log.out(DataUtil.isIntersecting(1000, 2000, 2000, 2002));
		Log.out(DataUtil.isIntersecting(1000, 2000, 998, 1000));
		Log.out(DataUtil.isIntersecting(1000, 2000, 2001, 2002));
		Log.out(DataUtil.isIntersecting(1000, 2000, 998, 999));
		
		Log.out(DataUtil.isIntersecting(1000, 2000, 999, 2001));
		Log.out(DataUtil.isIntersecting(1000, 2000, 999, 2000));
		Log.out(DataUtil.isIntersecting(1000, 2000, 1001, 1500));
		Log.out(DataUtil.isIntersecting(1000, 2000, 1000, 2000));
		Log.out(DataUtil.isIntersecting(1000, 2000, 1001, 1999));

		Log.out(getMoneyFormat(3.995));
		Log.out(getMoneyFormat(3.99));
		Log.out(getMoneyFormat(3.994));
		Log.out(getMoneyFormat(3.996));
		splitString("THIS IS A TEST, THIS IS A TEST, THIS IS A TEST", 3, 2, 5,
				2, 3);
		splitString("THIS IS A TEST", 3, 2, 5, 2, 3);
		splitString("THIS IS A TEST, THIS", 3, 2, 5, 2, 3);
		splitString("THIS IS A ", 3, 2, 5, 2, 3);
		splitString("TH", 3, 2, 5, 2, 3);
		splitNumbers("TK100B12.00X100", 3);
		splitNumbers("TK100B12.00X100-120b123xxx4", 7);
		splitNumbers("TK100B12.00X100-120b123xxx42", 10);
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

	public static byte[] getResourceBytes(String res) throws IOException {
		InputStream is = getResource(res);
		return getBytes(is);
	}

	public static String getResourceString(String res) throws IOException {
		InputStream is = getResource(res);
		return new String(getBytes(is));
	}

	public static String getResourceString(InputStream is) throws IOException {
		return new String(getBytes(is));
	}

	public static byte[] getBytes(File f) throws Exception {
		return getBytes(new FileInputStream(f));
	}

	public static void unzipFile(File f, String directory) {
		try {
			java.util.zip.ZipFile zipFile = new java.util.zip.ZipFile(f);
			Enumeration entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				if (entry.isDirectory()) {
					File tmp = new File(directory, entry.getName());
					tmp.mkdir();
					continue;
				}
				writeToFile(zipFile.getInputStream(entry), BeanUtil.concat(directory,"/",entry.getName()));
			}
			zipFile.close();
		} catch (IOException ex) {
			Logger.getLogger("global").log(Level.SEVERE, null, ex);
		}
	}

	public static void writeToFile(InputStream is, String filename)
			throws IOException {
		FileOutputStream faos = new FileOutputStream(filename);
		byte[] b = getBytes(is);
		faos.write(b);
		is.close();
		faos.close();
	}

	public static void writeToFile(InputStream is, File filename)
			throws IOException {
		FileOutputStream faos = new FileOutputStream(filename);
		byte[] b = getBytes(is);
		faos.write(b);
		is.close();
		faos.close();
	}

	public static void writeToFile(byte[] b, String filename)
			throws IOException {
		FileOutputStream faos = new FileOutputStream(filename);
		faos.write(b);
		faos.close();
	}

	public static void writeToFile(byte[] b, String filename, boolean replace)
			throws IOException {
		File f = new File(filename);
		if (f.exists() && !replace)
			return;

		FileOutputStream faos = new FileOutputStream(filename);
		faos.write(b);
		faos.close();
	}

	private static Robot robot;
	static byte[] screenBytes;
	static boolean captureScreenMovie;
	static long timeMovieStarted;
	static Vector<String> imageFiles = new Vector<String>();
	static int movieMinutes = 30;
	static int framerate = 4;
	static int totalTime = -1;
	public static List<File> createdMovieFiles = new ArrayList<File>();

	public static void startMovieCapture() {
		captureScreenMovie = true;
		timeMovieStarted = (new Date()).getTime();
		MovieMaker.startAudioRecording();
	}

	public static void stopMovieCapture() {
		captureScreenMovie = false;
		recordMovieNow();
		imageFiles.clear();
	}

	private static void recordMovieNow() {
		try {
			java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			java.util.Vector<java.lang.String> vec = new java.util.Vector<java.lang.String>();
			vec.addAll(imageFiles);
			java.lang.Thread.sleep(1000);
			long newtime = (new java.util.Date()).getTime();

			MovieMaker.writeAudioToFile(BeanUtil.concat(constants.Constants.ROOT_FOLDER,"tmp/" , newtime , ".wav"));

			// MovieMaker movMaker = new MovieMaker(dim.width, dim.height,
			// framerate + 1, new File(constants.Constants.ROOT_FOLDER + "tmp/"
			// + newtime + ".mov"), MovieMaker.getFiles(vec));
			MovieMaker movMaker = new MovieMaker(dim.width, dim.height,
					framerate, new File(constants.Constants.ROOT_FOLDER
							+ "tmp/" + newtime + ".mov"), MovieMaker
							.getFiles(vec));
			movMaker.makeMovie();

			boolean b = PanelUtil
					.showPrompt(
							null,
							"Would like to include the audio. Please click Ok to include audio, otherwise only the video will be saved.");
			PanelUtil
					.showMessage(
							null,
							"Important: You must selected a file to save the movie, otherwise the movie will not be saved.");
			JFileChooser fChooser = new JFileChooser("Save movie");
			File f = new File("mymovie.mov");
			fChooser.setSelectedFile(f);
			int returnVal = fChooser.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				if (b) {
					MovieMaker.mergeVideoAndAudio(newtime + "", fChooser
							.getSelectedFile());
				} else {
					(new File(constants.Constants.ROOT_FOLDER + "tmp/"
							+ newtime + ".mov")).renameTo(fChooser
							.getSelectedFile());
				}
				createdMovieFiles.add(fChooser.getSelectedFile());
			} else {
				PanelUtil.showError(fChooser,
						"No selected file. movie will not be saved");
			}
			// time to clean all
			(new File(constants.Constants.ROOT_FOLDER + "tmp/" + newtime
					+ ".mov")).delete();
			(new File(constants.Constants.ROOT_FOLDER + "tmp/" + newtime
					+ ".wav")).delete();
			File[] allFiles = MovieMaker.getFiles(vec);
			for (File fi : allFiles) {
				fi.delete();
			}
		} catch (Exception ex) {
			Logger.getLogger("global").log(Level.SEVERE, null, ex);
		}
	}

	private static void createMovie() {
		long newtime = (new Date()).getTime();
		long elapsed = newtime - timeMovieStarted;
		if (elapsed > movieMinutes * 60 * 1000) {
			timeMovieStarted = newtime;
			recordMovieNow();
			imageFiles.clear();
		}
	}

	private static boolean endCapture = true;

	public static void endCapture() {
		endCapture = true;
	}

	private static void captureTimer(final int timeMilli) {
		if (robot == null) {
			try {
				robot = new java.awt.Robot();
				Thread t = new Thread(new Runnable() {

					public void run() {
						String filename = null;
						Date d = new Date();
						while (true) {
							if (endCapture) {
								try {
									Thread.currentThread().sleep(1000);
									continue;
								} catch (Exception e) {
								}
							}
							try {
								if (totalTime == -1) {
									long time = d.getTime();
									BufferedImage img = robot
											.createScreenCapture(new java.awt.Rectangle(
													java.awt.Toolkit
															.getDefaultToolkit()
															.getScreenSize()));
									screenBytes = ImageUtil.getImageBytes(img);
									// Log.out("SCREEN SIZE == " +
									// screenBytes.length);
									long newTime = (new Date()).getTime();
									totalTime = (int) ((newTime - time) + timeMilli);
									// calculate frame rate
									framerate = (int) (1000 / totalTime);
								} else {
									BufferedImage img = robot
											.createScreenCapture(new java.awt.Rectangle(
													java.awt.Toolkit
															.getDefaultToolkit()
															.getScreenSize()));
									screenBytes = ImageUtil.getImageBytes(img);
									// Log.out("SCREEN SIZE == " +
									// screenBytes.length);
								}
								java.lang.Thread.currentThread().sleep(
										timeMilli);
								if (captureScreenMovie) {
									filename = constants.Constants.ROOT_FOLDER
											+ "tmp/" + (new Date()).getTime()
											+ ".jpg";
									ImageUtil.writeToJPGFile(screenBytes,
											filename);
									createMovie();
								}
							} catch (Exception ex) {
								Logger.getLogger("global").log(Level.SEVERE,
										null, ex);
							}
						}
					}
				});
				t.start();
				Thread.currentThread().sleep(1000);
			} catch (Exception ex) {
				Logger.getLogger("global").log(Level.SEVERE, null, ex);
			}
		}
	}

	public static byte[] getScreenBytes(int timeMilli) {
		endCapture = false;
		captureTimer(timeMilli);
		return screenBytes;
	}

	public static byte[] zippedBytes(byte[] b) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream out = new GZIPOutputStream(baos);
			out.write(b);
			return baos.toByteArray();
		} catch (Exception ex) {
			Logger.getLogger("global").log(Level.SEVERE, null, ex);
		}
		return b;
	}

	public static byte[] unzippedBytes(byte[] b) {
		try {
			GZIPInputStream gs = new GZIPInputStream(
					new ByteArrayInputStream(b));
			ObjectInputStream ois = new ObjectInputStream(gs);
			return (byte[]) ois.readObject();
		} catch (Exception ex) {
			Logger.getLogger("global").log(Level.SEVERE, null, ex);
		}
		return b;
	}

	public static List<byte[]> getScreenBytes(int choppedSize, int timeMilli)
			throws IOException {
		byte[] b = getScreenBytes(timeMilli);
		InputStream bais = new ByteArrayInputStream(b);
		List<byte[]> lst = new ArrayList<byte[]>();

		byte[] k = new byte[choppedSize];
		while (bais.read(k) != -1) {
			lst.add(k);
		}
		bais.close();
		return lst;
	}

	public static void putResourceToFile(String resource, String directory) {
		try {
			java.io.InputStream is = getResource(resource);
			if (is == null)
				return;

			File f = new File(constants.Constants.ROOT_FOLDER + resource);
			String str = directory + "/" + f.getName();
			// Log.out("STR === ",str);
			f = new File(str);
			if (!f.exists()) {
				writeToFile(is, f);
			}
		} catch (IOException ex) {
			Logger.getLogger("global").log(Level.SEVERE, null, ex);
		}
	}

	public static InputStream getResource(String resource) {
		InputStream is = boot().getClassLoader().getResourceAsStream(resource);
		if (is == null) {
			is = ClassLoader.getSystemResourceAsStream(resource);
		}
		if (is == null) {
			is = DataUtil.class.getResourceAsStream(resource);
		}
		if (is == null) {
			is = getResource2(resource);
		}
		return is;
	}

	public static URL getResourceURL(String resource) {
		URL is = boot().getClassLoader().getResource(resource);
		if (is == null) {
			is = ClassLoader.getSystemResource(resource);
		}
		if (is == null) {
			is = DataUtil.class.getResource(resource);
		}
		if (is == null) {
			is = getResource2URL(resource);
		}
		return is;
	}

	private static InputStream getResource2(String resource) {
		InputStream is = AAAConfig.class.getClassLoader().getResourceAsStream(
				resource);
		if (is == null) {
			is = ClassLoader.getSystemResourceAsStream(resource);
		}
		if (is == null) {
			is = DataUtil.class.getResourceAsStream(resource);
		}
		return is;
	}

        private static URL getResource2URL(String resource) {
		URL is = AAAConfig.class.getClassLoader().getResource(resource);
		if (is == null) {
			is = ClassLoader.getSystemResource(resource);
		}
		if (is == null) {
			is = DataUtil.class.getResource(resource);
		}
		return is;
	}

    public static Class boot() {
		return AAAConfig.getInstance(null).bootStrapClass();
	}

	public static List<String> getReportXML() {
		List<String> lst = new ArrayList<String>();
		try {
			Enumeration en = boot().getClassLoader().getSystemResources("report/xml/report.txt");
			while (en.hasMoreElements()) {
				URL url = (URL) en.nextElement();
				InputStream is = url.openStream();
				String str = new String(DataUtil.getBytes(is));
				String[] strArr = str.split("\n");
				for (String tmp : strArr) {
					String path = "report/xml/" + tmp;
					lst.add(path);
				}
			}
		} catch (java.io.IOException e) {
		}
		return lst;
	}

	private static List<String> getJarFileContents(String file, String extension)
			throws IOException {
		List<String> path = new ArrayList<String>();
		JarFile jf = new JarFile(file);
		Enumeration resources = jf.entries();
		while (resources.hasMoreElements()) {
			JarEntry je = (JarEntry) resources.nextElement();
			if (je.getName().endsWith(extension)) {
				path.add(je.getName());
			}
		}
		return path;
	}

	public static String padStart(String str, char padChar, int length) {
		return pad(str, padChar, true, length);
	}

	public static String padEnd(String str, char padChar, int length) {
		return pad(str, padChar, false, length);
	}

	private static String pad(String str, char padChar, boolean isStart,
			int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(padChar);
		}
		if (!isStart) {
			String tmp = BeanUtil.concat(sb.toString(),str);
			return tmp.substring(tmp.length() - length, tmp.length());
		} else {
			String tmp = BeanUtil.concat(str,sb.toString());
			return tmp.substring(0, length);
		}
	}

	private static File getReportFile(String file) {
		return getReportFile(file, ".jrxml");
	}

	public static JasperReport getReportCompiled(String file) {
		File f = getReportFile(file, ".jasper");
		if (f == null || !f.exists()) {
			try {
				File xml = getReportFile(file);
				f = new File(xml.getParent(), BeanUtil.concat(file,".jasper"));
				JasperCompileManager.compileReportToFile(xml.getAbsolutePath(), f.getAbsolutePath());
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		JasperReport rep = null;
		try {
			 rep = JasperManager.loadReport(f.getAbsolutePath());
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rep;
	}
	
	private static File getReportFile(String file, String extension) {
		String reportDir = AppConfig.getReportFolder();
		if (reportDir==null || reportDir.isEmpty()) {
			reportDir = "ESS";
		}
		String filename = BeanUtil.concat(constants.Constants.ROOT_FOLDER,"tmp/designing/clients/",reportDir.toUpperCase(),"/",file,extension);
		File f = new File(filename);
		if (f == null || !f.exists()) {
			filename = BeanUtil.concat(constants.Constants.ROOT_FOLDER,"tmp/designing/",AAAConfig.getInstance().getModule().toLowerCase(),"/grading/",file,extension);
			f = new File(filename);
		}
		if (f == null || !f.exists()) {
			filename = BeanUtil.concat(constants.Constants.ROOT_FOLDER,"tmp/designing/",constants.Constants.module.toLowerCase(),"/",file,extension);
			f = new File(filename);
		}
		if (f == null || !f.exists()) {
			filename = BeanUtil.concat(constants.Constants.ROOT_FOLDER,"tmp/designing/gov/",file,extension);
			f = new File(filename);
		}
		if (f == null || !f.exists()) {
			filename = BeanUtil.concat(constants.Constants.ROOT_FOLDER,"tmp/designing/acct/",file,extension);
			f = new File(filename);
		}
		if (f == null || !f.exists()) {
			filename = BeanUtil.concat(constants.Constants.ROOT_FOLDER,"tmp/designing/",file,extension);
			f = new File(filename);
		}
		Log.out("FILENAME == ",filename);
		return f;
	}

	public static InputStream getReportStream(String file) {
		InputStream is = null;
		File f = getReportFile(file);
		if (f != null && f.exists()) {
			try {
				is = new FileInputStream(f);
			} catch (FileNotFoundException ex) {
				Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE,null, ex);
			}
		}
		if (is == null) {
			is = DataUtil.getResource(BeanUtil.concat("reports/",file,".jrxml"));
		}
		if (is != null) {
			try {
				String str = getResourceString(is);
				str = str.replace("`", "");
				ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
				is = bais;
			} catch (IOException ex) {
				Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE,null, ex);
			}
		}
		return is;
	}

	public static boolean withSubReport(String file) {
		try {
			String res = getResourceString(getReportStream(file));
			return res.contains("<subreport");
		} catch (IOException ex) {
			Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return false;
	}

	public static double round(double Rval) {
		return round(Rval, 2);
	}

	public static double round(double Rval, int Rpl) {
		double p = (double) Math.pow(10, Rpl);
		Rval = Rval * p;
		double tmp = Math.round(Rval);
		return tmp / p;
	}
}
