/*
 * DateUtil.java
 *
 * Created on Oct 2, 2007, 7:15:45 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import bean.reference.EventHoliday;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.util.AbstractIBean;

/**
 *
 * @author Budoy Entokwa
 */
public class DateUtil {

    public static SimpleDateFormat sdfDDMMMYY = new SimpleDateFormat("ddMMMyy");
    public static SimpleDateFormat sdfYYYYMMDD = new SimpleDateFormat("yyyyMMdd");

    public static boolean isBetween(Date myDate, Date fromDate, Date toDate) {
        if (myDate == null) {
            return false;
        }
        if (toDate == null && myDate.getTime() >= fromDate.getTime()) {
            return false;
        }
        if (fromDate == null && myDate.getTime() <= toDate.getTime()) {
            return false;
        }
        return (myDate.getTime() >= fromDate.getTime() && myDate.getTime() <= toDate.getTime());
    }

    public static Date getFirstDayOfMonth() {
        String day1 = DateUtil.formatDate(constants.Constants.useDate, "yyyyMM01");
        return DateUtil.readDate(day1, "yyyyMMdd");
    }

    public static Date getFirstDayOfYear() {
        String day1 = DateUtil.formatDate(constants.Constants.useDate, "yyyy0101");
        return DateUtil.readDate(day1, "yyyyMMdd");
    }

    public static Date getEndDayOfYear() {
        String day1 = DateUtil.formatDate(constants.Constants.useDate, "yyyy1231");
        return DateUtil.readDate(day1, "yyyyMMdd");
    }
    
    public static Date getDateOfMonth(int month, int day) {
        String year = DateUtil.formatDate(constants.Constants.useDate, "yyyy-");
        return DateUtil.readDate(BeanUtil.concat(year,month,"-",day), "yyyy-MM-dd");
    }

    public static Date getDateOfMonth(int day) {
        String yearMonth = DateUtil.formatDate(constants.Constants.useDate, "yyyy-MM-");
        return DateUtil.readDate(BeanUtil.concat(yearMonth,day), "yyyy-MM-dd");
    }

    public static Date addMonth(Date d, int months) {
        int month = DateUtil.getMonth(d);
        return DateUtil.readDate(BeanUtil.concat(DateUtil.formatDate(d, "yyyy-dd-"),(month + months)), "yyyy-dd-MM");
    }

    public static Date getEndOfMonth() {
        return getEndOfMonth(constants.Constants.useDate);
    }

    public static Date getEndOfLastMonth() {
        return addDay(readDate("1"), -1);
    }

    public static int getMonth(Date d) {
        String m = DateUtil.formatDate(d, "MM");
        try {
            return Integer.parseInt(m);
        } catch (Exception e) {
        }
        return 1;
    }

    public static int getMonth() {
        String m = DateUtil.formatDate(constants.Constants.useDate, "MM");
        try {
            return Integer.parseInt(m);
        } catch (Exception e) {
        }
        return 1;
    }

    public static String getMonthName() {
        return DateUtil.formatDate(constants.Constants.useDate, "MMM");
    }

    public static int getWeekday(Date d) {
        String m = DateUtil.formatDate(d, "d");
        try {
            return Integer.parseInt(m);
        } catch (Exception e) {
        }
        return 1;
    }

    public static int getWeekday() {
        String m = DateUtil.formatDate(constants.Constants.useDate, "d");
        try {
            return Integer.parseInt(m);
        } catch (Exception e) {
        }
        return 1;
    }

    public static String getWeekdayName() {
        return DateUtil.formatDate(constants.Constants.useDate, "ddd");
    }

    public static String formatTime(String time) {
        if (time.contains(":")) {
            time = time.replace(":", "");
        }
        int val = DataUtil.getFirstNumber(time).intValue();
        if (val < 10) {
            return "000" + val;
        }
        if (val < 100) {
            return "00" + val;
        }
        if (val < 1000) {
            return "0" + val;
        }
        return val + "";
    }

    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat form = new SimpleDateFormat(format);
        return form.format(date);
    }

    public static String getTime() {
        return formatDate(new Date(), "HHmm");
    }

    public static int getIntTime() {
        String str = formatDate(new Date(), "HHmm");
        return Integer.parseInt(str);
    }

    public static int getIntDate() {
        String str = formatDate(constants.Constants.useDate, "yyyyMMdd");
        return Integer.parseInt(str);
    }

    public static int getIntDate(Date d) {
        String str = formatDate(d, "yyyyMMdd");
        return Integer.parseInt(str);
    }

    public static String getTimeSecond() {
        return formatDate(new Date(), "HHmmss");
    }

    public static String getMonthName(String toString) {
        try {
            java.text.SimpleDateFormat form = new java.text.SimpleDateFormat("M");
            Date d = form.parse(toString);

            java.text.SimpleDateFormat form2 = new java.text.SimpleDateFormat("MMM");
            return form2.format(d);
        } catch (ParseException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return toString;
    }

    public static Date removeTime(Date date) {
        try {
            java.text.SimpleDateFormat form = new java.text.SimpleDateFormat("yyyyMMdd");
            java.lang.String str = form.format(date);
            return form.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String formatDateToSql(Date date) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        return form.format(date);
    }

    public static String formatDateToSql(Date date, int plusDay) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        return form.format(addDay(date, plusDay));
    }

    public static String formatDate(Date date, SimpleDateFormat sdf) {
        return sdf.format(date).toUpperCase();
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return sdfDDMMMYY.format(date).toUpperCase();
    }

    public static Date readDate(String dateStr, String form) {
        try {
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(form);
            return format.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Date readDate(String dateStr) {
        try {
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
            if (dateStr.length() == 4) {
                dateStr = BeanUtil.concat(formatDate(constants.Constants.useDate, "yyyy"),dateStr);
            } else if (dateStr.length() == 2) {
                dateStr = BeanUtil.concat(formatDate(constants.Constants.useDate, "yyyyMM"),dateStr);
            } else if (dateStr.length() == 1) {
                dateStr = BeanUtil.concat(formatDate(constants.Constants.useDate, "yyyyMM"),"0",dateStr);
            }
            return format.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<Date> getAllDates(String dayOfWeek) {
        List<Date> lst = new ArrayList<Date>();
        String year = formatDate(constants.Constants.useDate, "yyyy");
        Date firstDayOfYear = readDate(BeanUtil.concat(year,"0101"));
        Date lastDayOfYear = readDate(BeanUtil.concat(year,"1231"));

        long timeLast = lastDayOfYear.getTime();
        long timeFirst = firstDayOfYear.getTime();
        while (timeLast >= timeFirst) {
            String day = DateUtil.formatDate(firstDayOfYear, "EEEE").toUpperCase();
            if (day.startsWith(dayOfWeek.toUpperCase())) {
                lst.add(firstDayOfYear);
            }
            firstDayOfYear = addDay(firstDayOfYear, 1);
            timeFirst = firstDayOfYear.getTime();
        }
        return lst;
    }

    public static Date readDateNoYear(String dateStr) {
        try {
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("ddMMMyyyy");
            return format.parse(BeanUtil.concat(dateStr,"      ").substring(0, 5) + formatDate(constants.Constants.useDate, "yyyy"));
        } catch (ParseException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean isDateNoYear(String dateStr) {
        try {
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("ddMMMyyyy");
            format.parse(BeanUtil.concat(dateStr,"       ").substring(0, 5) + formatDate(constants.Constants.useDate, "yyyy"));
            return true;
        } catch (ParseException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Date readDateNoYearNoMonth(String dateStr) {
        try {
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("ddMMMyyyy");
            return format.parse(BeanUtil.concat(dateStr,formatDate(constants.Constants.useDate, "MMyyyy")));
        } catch (ParseException ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Date addDay(Date from, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Date from = readDate("20070801", "yyyyMMdd");
        Date to = readDate("20070901", "yyyyMMdd");

        Log.out("COUNT DAYS === ",countDaySpan(from, to));
    }

    public static int countDaySpan(Date from, Date to) {
        if (from == null || to == null) {
            return 0;
        }
        long fromTime = from.getTime();
        long toTime = to.getTime();
        long spanTime = toTime - fromTime;
        return (int) (spanTime / (1000 * 60 * 60 * 24));
    }

    public static Date getEndOfMonth(Date startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return readDate(BeanUtil.concat(getYear(),"-",getMonth(startDate),"-",maxDay), "yyyy-MM-dd");
    }

    public static class Days {

        static final SimpleDateFormat sdfday = new SimpleDateFormat("EEE");

        public static boolean isMonday(Date d) {
            return sdfday.format(d).toUpperCase().startsWith("MON");
        }

        public static boolean isTuesday(Date d) {
            return sdfday.format(d).toUpperCase().startsWith("TUE");
        }

        public static boolean isWednesday(Date d) {
            return sdfday.format(d).toUpperCase().startsWith("WED");
        }

        public static boolean isThursday(Date d) {
            return sdfday.format(d).toUpperCase().startsWith("THU");
        }

        public static boolean isFriday(Date d) {
            return sdfday.format(d).toUpperCase().startsWith("FRI");
        }

        public static boolean isSaturday(Date d) {
            return sdfday.format(d).toUpperCase().startsWith("SAT");
        }

        public static boolean isSunday(Date d) {
            return sdfday.format(d).toUpperCase().startsWith("SUN");
        }
    }

    public static boolean isTimezoneLate(String origin, Date date, String time) {
        return false;
    }

    public static int getDayOfMonth() {
        int d = Integer.parseInt(formatDate(constants.Constants.useDate, "d"));
        return d;
    }

    public static int getYear() {
        return getYear(constants.Constants.useDate);
    }

    public static int getYear(Date date) {
        int d = Integer.parseInt(formatDate(date, "yyyy"));
        return d;
    }

    public static boolean isHoliday(Date date) {
        EventHoliday hol = (EventHoliday) AbstractIBean.firstRecord("SELECT a FROM EventHoliday a WHERE a.eventDate='" , DateUtil.formatDateToSql(date) , "'");
        if (hol == null || hol.getEventDate() == null) {
            return false;
        }
        return true;
    }

    public static int getSpanYears(Date fromDate) {
        if (fromDate == null) {
            return 0;
        }
        int oldd = Integer.parseInt(formatDate(fromDate, "yyyy"));
        int newd = Integer.parseInt(formatDate(constants.Constants.useDate, "yyyy"));
        return newd - oldd;
    }

    public static int getSpanMonth(Date fromDate) {
        if (fromDate == null) {
            return 0;
        }
        int oldd = Integer.parseInt(formatDate(fromDate, "MM"));
        int newd = Integer.parseInt(formatDate(constants.Constants.useDate, "MM"));
        return newd - oldd;
    }

    public static int getSpanDays(Date fromDate) {
        if (fromDate == null) {
            return 0;
        }
        int oldd = Integer.parseInt(formatDate(fromDate, "d"));
        int newd = Integer.parseInt(formatDate(constants.Constants.useDate, "d"));
        return newd - oldd;
    }

    public static String getSQLDayName(String field) {
        //note: this must create the sql using any database, use only for native sql
        //this is for MySQL only
        return BeanUtil.concat(" DAYNAME(",field,") ");
    }

    public static String getSQLMonthName(String field) {
        //note: this must create the sql using any database, use only for native sql
        //this is for MySQL only
        return BeanUtil.concat(" MONTHNAME(",field,") ");
    }

    public static String getSQLYear(String field) {
        //note: this must create the sql using any database, use only for native sql
        //this is for MySQL only
        return BeanUtil.concat(" YEAR(",field,") ");
    }

    public static int getHour() {
        int hour = Integer.parseInt(DateUtil.formatDate(new Date(), "HH"));
        return hour;
    }

    public static double getTotalHours(String start, String end) {
        try {
            double shours = DataUtil.getIntValue(start.substring(0, 2)) + DataUtil.getIntValue(start.substring(2)) / 60;
            double ehours = DataUtil.getIntValue(end.substring(0, 2)) + DataUtil.getIntValue(end.substring(2)) / 60;
            return DataUtil.getDoubleValue(ehours - shours);
        } catch (Exception e) {
        }
        return 0;
    }

    public static double getNightHours(String end) {
        try {
            String start = "2200";
            double shours = DataUtil.getIntValue(start.substring(0, 2)) + DataUtil.getIntValue(start.substring(2)) / 60;
            double ehours = DataUtil.getIntValue(end.substring(0, 2)) + DataUtil.getIntValue(end.substring(2)) / 60;
            return DataUtil.getDoubleValue(ehours - shours);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return 0;
    }

    public static boolean sameDate(Date date1, Date date2) {
        String s1 = formatDate(date1);
        String s2 = formatDate(date2);
        return s1.equals(s2);
    }
}
