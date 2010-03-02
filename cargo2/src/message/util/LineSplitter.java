/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Charliemagne Mark
 */
public class LineSplitter {
    String message;
    List<String> strLines = new ArrayList<String>();
    String[] clean;
    int offset = 0;

    @Override
    public String toString() {
        return message;
    }
    
    public LineSplitter(String str, String... clean) {
        this.message = str;
        this.clean = clean;
        String[] lines = str.split("\n");
        StringBuffer sb = new StringBuffer();
        for (String string : lines) {
            if (!string.startsWith("/")) {
                sb.append("\n");
            }
            else {
                sb.append("\r");
            }
            sb.append(string);
        }
        
        lines = sb.toString().trim().split("\n");
        for (String string : lines) {
            strLines.add(string);
        }
    }
    public void reset() {
        offset=0;
    }
    public int getLineCount() {
        return strLines.size();
    }
    public String getNextLine() {
        if (strLines.size()>offset) {
            return SplitterUtil.clean(strLines.get(offset++));
        }
        return "";
    }
    public String getPrevLine() {
        //note this will not go back
        if (strLines.size()>offset && offset>0) {
            return SplitterUtil.clean(strLines.get(offset-1));
        }
        return "";
    }
    public boolean hasNextLine() {
        return strLines.size()>offset;
    }
    public String getLine(int index) {
        if (strLines.size()>index) {
            return SplitterUtil.clean(strLines.get(index));
        }
        return "";
    }
    
    public List<String> getLines(String start) {
        List<String> strArr = new ArrayList<String>();
        for (String string : strLines) {
            if (string.startsWith(start)) {
                strArr.add(SplitterUtil.clean(string));
            }
        }
        return strArr;
    }
    public String getLine(String start) {
        for (String string : strLines) {
            if (string.startsWith(start)) {
                return SplitterUtil.clean(string);
            }
        }
        return "";
    }
    
    public StringSplitter getStringSplitter(String str) {
        return new StringSplitter(str, clean);
    }

    public SubstringUtil getSubstringUtil(String str) {
        return new SubstringUtil(str, clean);
    }
}
