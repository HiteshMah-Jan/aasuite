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
    public static void info(String message) {
        Logger.getLogger("global").log(Level.INFO, "INFO: "+message);
    }
    public static void warning(String message) {
        Logger.getLogger("global").log(Level.WARNING, "WARNING: "+message);
    }
    public static void severe(String message, Exception e) {
        Logger.getLogger("global").log(Level.SEVERE, "SEVERE: "+message, e);
    }
    public static void severe(String message) {
        Logger.getLogger("global").log(Level.SEVERE, "SEVERE: "+message);
    }

}
