package util;

import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ServerCache {
	private static boolean started = false;
	
    private static Map cache = new Hashtable();

    public static Object getCache_old(Object key) {
    	if (cache.containsKey(key)) {
    		Log.out("**SERVER CACHE EXTRACT === ",key);
    		return cache.get(key);
    	}
    	return null;
    }
    
    public static synchronized Object resetCache_old(Object key, Object value) {
    	if (!started) {
    		started = true;
//    		do the threading here, every hour need to clear the cache.
    	    TimerTask cleaner  = new RunCleaner();
    	    Timer timer = new Timer();
//    	    this is per hour
    	    timer.scheduleAtFixedRate(cleaner, 1, 1000*60*60);
    	}
		Log.out("** SERVER CACHE RESET === ",key);
    	cache.put(key, value);
    	return value;
    }
    
    public static synchronized void cleanCache() {
		cache.clear();
    }
    
    private static class RunCleaner extends TimerTask {
		@Override
		public void run() {
			System.out.println("\n\n\n\n\n\n\nCLEANING SERVER CACHE\n\n\n\n\n\n");
			cleanCache();
		}
    }
}
