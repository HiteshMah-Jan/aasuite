package util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bean.admin.AppConfig;

public class ThreadPoolUtil {	
	static int maxThread = AppConfig.getMaxThread();
	static ExecutorService e = Executors.newFixedThreadPool(maxThread);
	
	public static void execute(Runnable run) {
		e.execute(run);
	}
}
