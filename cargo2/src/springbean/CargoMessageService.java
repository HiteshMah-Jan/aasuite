package springbean;

import java.io.File;
import java.io.RandomAccessFile;

import message.FBL;
import message.FFA;
import message.FFM;
import message.FFR;
import message.FHL;
import message.FMA;
import message.FNA;
import message.FSA;
import message.FSR;
import message.FSU;
import message.FWB;
import service.IService;
import service.ParamStruct;
import service.ReturnStruct;
import util.Log;
import bean.admin.AppConfig;

public class CargoMessageService implements IService, Runnable {
	private static boolean serviceRunning = false;

	@Override
	public ReturnStruct callService(ParamStruct param) {
		Thread t = new Thread(new CargoMessageService());
		t.start();
		return null;
	}

	@Override
	public void run() {
		if (serviceRunning) {
			Log.out("CARGO MESSAGE SERVICE IS ALREADY RUNNING."," - ",constants.Constants.useDate);
		}
		else {
			Log.out("CARGO MESSAGE SERVICE CALLED."," - ",constants.Constants.useDate);
			try {
				process();
			} catch (InterruptedException e) {
				serviceRunning = false;
			}
		}
	}

	private void process() throws InterruptedException {
		serviceRunning = true;
		File root = getFolder(AppConfig.getTemporaryFolder());
		File messages = getFolder(root, "messages");
		File incoming = getFolder(messages, "incoming");
		File processed = getFolder(messages, "processed");
		File failed = getFolder(messages, "failed");
		for (int i=0; i<1; ) {
			File[] lst = incoming.listFiles();
			if (lst!=null && lst.length>0) {
				for (File f:lst) {
					Log.out("PROCESS FILE ",f.getName());
					Thread.currentThread().yield();
					String str = readFile(f);
					f.renameTo(new File(processed, f.getName()));
					new FBL(str).processMessage();
					new FFA(str).processMessage();
					new FFM(str).processMessage();
					new FFR(str).processMessage();
					new FHL(str).processMessage();
					new FMA(str).processMessage();
					new FNA(str).processMessage();
					new FSA(str).processMessage();
					new FSR(str).processMessage();
					new FSU(str).processMessage();
					new FWB(str).processMessage();
//					add more messages here
				}
			}
			else {
				System.out.println("EMPTY FOLDER.");
			}
			System.out.println("SLEEP CARGO MESSAGE.");
			Thread.currentThread().sleep(1000*60*1);
		}
	}

	private String readFile(File f) {
		if (f.exists()) {
			try {
				int length = (int) f.length();
				byte[] b = new byte[length];
				RandomAccessFile raf = new RandomAccessFile(f, "r");
				raf.read(b);
				raf.close();
				String s = new String(b);
				return s.trim();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private File getFolder(String f) {
		File tmp = new File(f);
		if (tmp==null || !tmp.exists()) {
			tmp.mkdir();
		}
		return tmp;
	}

	private File getFolder(File dir, String f) {
		File tmp = new File(dir, f);
		if (tmp==null || !tmp.exists()) {
			tmp.mkdir();
		}
		return tmp;
	}
}
