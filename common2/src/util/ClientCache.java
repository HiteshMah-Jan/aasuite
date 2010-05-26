package util;

import java.util.HashMap;
import java.util.Map;

public class ClientCache {
    private static Map cache = new HashMap();

    public static Object getCache(Object key) {
    	if (cache.containsKey(key)) {
    		Log.out("**CACHE EXTRACT === ",key);
    		return cache.get(key);
    	}
    	return null;
    }
    
    public static Object resetCache(Object key, Object value) {
		Log.out("** CACHE RESET === ",key);
    	cache.put(key, value);
    	return value;
    }
}
