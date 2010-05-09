package util;

import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ServerCache {
	private static boolean started = false;
	
    private static Map cache = new Hashtable();

    public static Object getCache(Object key) {
    	if (cache.containsKey(key)) {
    		System.out.println("**SERVER CACHE EXTRACT === "+key);
    		return cache.get(key);
    	}
    	return null;
    }
    
    public static synchronized Object resetCache(Object key, Object value) {
    	if (!started) {
    		started = true;
//    		do the threading here, every hour need to clear the cache.
    	    TimerTask cleaner  = new RunCleaner();
    	    Timer timer = new Timer();
//    	    this is per hour
    	    timer.scheduleAtFixedRate(cleaner, 1, 1000*60*60);
    	}
		System.out.println("** SERVER CACHE RESET === "+key);
    	cache.put(key, value);
    	return value;
    }
    
    private static class RunCleaner extends TimerTask {
		@Override
		public void run() {
			System.out.println("\n\n\n\n\n\n\nCLEANING SERVER CACHE\n\n\n\n\n\n");
			cache.clear();
		}
    }
}
