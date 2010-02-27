/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.Date;

/**
 *
 * @author alex
 */
public class PerfUtil {
    long realStart;
    long start;
    long end;
    String str;
    
    public PerfUtil(String str) {
        this.str = str;
    }

    public void start() {
        start = new Date().getTime();
        realStart = start;
    }
    
    public void printSpan() {
        end = new Date().getTime();
        System.out.println(str+" SPAN == "+(end-start));
    }

    public void printSpanComplete() {
        end = new Date().getTime();
        long time = end-realStart;
        double minutes = (time*1.0)/(1000*60);
        System.out.println(str+" COMPLETE == "+time+" IN MINUTES "+minutes+" VAL=="+DataUtil.getMoneyFormat(minutes));
    }

    public String spanMessage() {
        end = new Date().getTime();
        return str+" SPAN == "+(end-start);
    }

    public String spanMessageComplete() {
        end = new Date().getTime();
        return str+" COMPLETE == "+((end-realStart)/1000)+" seconds";
    }

    public void reset(String str) {
        this.str = str;
        start = new Date().getTime();
    }
}
