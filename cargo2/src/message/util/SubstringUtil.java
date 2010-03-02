/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message.util;

/**
 *
 * @author Charliemagne Mark
 */
public class SubstringUtil {
    private String str = "";
    private int totalLength;
    String[] clean;

    @Override
    public String toString() {
        return str;
    }
    
    public SubstringUtil(String str, String... clean) {
        this.str = str;
        totalLength = 0;
        this.clean = clean;
    }
    
    public String getNextSubstring(int length) {
        if (str==null || str.trim().isEmpty()) return "";
        String ret = "";
        if (str.length() >= totalLength+length) {
            ret = str.substring(totalLength, totalLength+length);
        }
        else if (totalLength<=str.length()) {
            ret = str.substring(totalLength, str.length());
        }
        totalLength += length;
        return clean(ret);
    }
    public double getNextDouble() {
        if (str==null || str.trim().isEmpty()) return 0;
        StringBuffer sb = new StringBuffer();
        for (int i = totalLength; i < str.length(); i++) {
            char c = str.charAt(i);
            totalLength = i;
            if (Character.isDigit(c) || c=='.') {
                sb.append(c);
            }
            else {
                break;
            }
        }
        if (sb.length()>0) {
            return Double.parseDouble(sb.toString());
        }
        return 0;
    }
    public int getNextInt() {
        if (str==null || str.trim().isEmpty()) return 0;
        StringBuffer sb = new StringBuffer();
        for (int i = totalLength; i < str.length(); i++) {
            char c = str.charAt(i);
            totalLength = i;
            if (Character.isDigit(c)) {
                sb.append(c);
            }
            else {
                break;
            }
        }
        if (sb.length()>0) {
            return Integer.parseInt(sb.toString());
        }
        return 0;
    }
    public String getNextNonNumeric() {
        if (str==null || str.trim().isEmpty()) return "";
        StringBuffer sb = new StringBuffer();
        for (int i = totalLength; i < str.length(); i++) {
            char c = str.charAt(i);
            totalLength = i;
            if (!Character.isDigit(c)) {
                sb.append(c);
            }
            else {
                break;
            }
        }
        return clean(sb.toString());
    }
    
    private final String clean(String str) {
        return SplitterUtil.clean(str, clean);
    }
}
