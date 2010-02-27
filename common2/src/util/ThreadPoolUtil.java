package util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtil {	
	static int maxThread = 10;
	static ExecutorService e = Executors.newFixedThreadPool(10);
	
	public static void execute(Runnable run) {
		e.execute(run);
	}
}
