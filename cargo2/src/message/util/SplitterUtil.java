/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Charliemagne Mark
 */
public class SplitterUtil {
    public static List<String> getSplit(String str, String delimit) {
        List<String> lst = new ArrayList<String>();
        String[] arrStr = str.split(delimit);
        for (String string : arrStr) {
            lst.add(string);
        }
        return lst;
    }
    public static List<String> getSplit(String str) {
        return getSplit(str, "/");
    }
    public static String getSplit(String str, String delimit, int index) {
        String[] arrStr = str.split(delimit);
        return arrStr[index];
    }
    public static double getSplitNumber(String str, String delimit, int index) {
        String[] arrStr = str.split(delimit);
        try {
            return Double.parseDouble(arrStr[index]);
        }
        catch (Exception e) {
            return 0;
        }
    }
    public static String getLineFrom(String str, int index) {
        String[] arr = str.split("\r");
        if (arr.length>index) {
            return arr[index];
        }
        return "";
    }
    public static String getNextSplit(Iterator it) {
        String str = null;
        while (it.hasNext()) {
            str = (String) it.next();
            it.remove();
            return str;
        }
        return str;
    }

    public static String getNextSplitNonEmpty(Iterator it) {
        String str = null;
        while (it.hasNext()) {
            str = (String) it.next();
            it.remove();
            if (str!=null && !str.trim().isEmpty()) {
                return str;
            }
        }
        return str;
    }
    public static String clean(String str, String... strArr) {
        if (str==null || str.isEmpty()) return "";
        if (strArr==null || strArr.length==0) return str;
        for (String string : strArr) {
            str = str.replace(string, "");
        }
        return str;
    }
}
