/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charliemagne Mark
 */
public class Log {
    public static void info(Object... message) {
        Logger.getLogger("global").log(Level.INFO, BeanUtil.concat(BeanUtil.concat(message)));
    }
    public static void warning(Object... message) {
        Logger.getLogger("global").log(Level.WARNING, BeanUtil.concat(BeanUtil.concat(message)));
    }
    public static void severe(Exception e, Object... message) {
        Logger.getLogger("global").log(Level.SEVERE, BeanUtil.concat(BeanUtil.concat(message)), e);
    }
    public static void severe(Object... message) {
        Logger.getLogger("global").log(Level.SEVERE, BeanUtil.concat(BeanUtil.concat(message)));
    }
    public static void out(Object... message) {
    	info(message);
    }
}
