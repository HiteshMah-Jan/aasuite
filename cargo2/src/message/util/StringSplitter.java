/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message.util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import util.DateUtil;

/**
 *
 * @author Charliemagne Mark
 */
public class StringSplitter {
    private Iterator iter;
    private String gstr = "";
    String[] clean;

    @Override
    public String toString() {
        return gstr;
    }

    public StringSplitter(String str, String... clean) {
        this.gstr = str;
        this.clean = clean;
        List<String> lst = SplitterUtil.getSplit(str);
        iter = lst.iterator();
    }
    
    public void reset() {
        List<String> lst = SplitterUtil.getSplit(gstr);
        iter = lst.iterator();
    }
    
    public String getNextSplit() {
        return clean(SplitterUtil.getNextSplit(iter));
    }

    public double getNextSplitDouble() {
        String str = clean(SplitterUtil.getNextSplit(iter));
        try {
            return Double.parseDouble(str);
        }
        catch (Exception e) {
            return 0;
        }
    }

    public int getNextSplitInt() {
        String str = clean(SplitterUtil.getNextSplit(iter));
        try {
            return (int) Double.parseDouble(str);
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public String getNextSplitNonEmpty() {
        return clean(SplitterUtil.getNextSplitNonEmpty(iter));
    }
    
    public Date getNextSplitDate() {
        return DateUtil.readDateNoYear(clean(SplitterUtil.getNextSplitNonEmpty(iter)));
    }

    private final String clean(String str) {
        return SplitterUtil.clean(str, clean);
    }
}
