package org.jumao.bi.utis;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.jumao.bi.entites.CompanyNumBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 *
 * @author zw
 */
public class DateUtils {

    private static final Logger logger                    = LoggerFactory.getLogger(DateUtils.class);

    /** 时间格式化 */
    public final static String  TIME_FORMAT               = "yyyy-MM-dd HH:mm:ss";
    /** 日期格式化 */
    public final static String  DAY_FORMAT                = "yyyy-MM-dd";
    public final static String  DAY_US_FORMAT             = "MM/dd/yyyy";
    public final static String  DAY_CN_FORMAT             = "yyyy/MM/dd";
    public final static String  DAY_FORMAT_YYYYMMDD       = "yyyyMMdd";

    public final static String  DAY_FORMAT_YYYYMM         = "yyyyMM";

    public final static String  MONTH_FORMAT              = "yyyy-MM";
    public final static String  MONTH_US_FORMAT           = "MM/yyyy";

    /**
     * 时分秒
     */
    public final static String  HOUR_MINUTE_SECOND_FORMAT = "HH:mm:ss";

    /**
     * 时分秒
     */
    public final static String  HOUR_MINUTE_FORMAT        = " HH:mm";

    public final static String  LAST_TIME                 = " 23:59:59";

    public final static String  FIRST_TIME                = " 00:00:00";

    public static final String  SINGLE_QUOTE              = "'";

    public static String getShowDate(Calendar cal) {
        if (cal == null) {
            return "";
        }
        int year = cal.get(Calendar.YEAR);
        int mouth = cal.get(Calendar.MONTH) + 1;
        String mouthStr = mouth > 9 ? mouth + "" : "0" + mouth;
        String yearStr = (year + "").substring(2);
        return yearStr + mouthStr;
    }

    /**
     * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
     */
    public static String getDateTime() {
        try {
            return new SimpleDateFormat(TIME_FORMAT).format(Calendar.getInstance().getTime());
        } catch (Exception e) {
            logger.debug("DateUtils.getDateTime():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd 的日期字符串形式返回
     */
    public static String getDate() {
        try {
            return new SimpleDateFormat(DAY_FORMAT).format(Calendar.getInstance().getTime());
        } catch (Exception e) {
            logger.debug("DateUtils.getCreate_time():" + e.getMessage());
            return "";
        }
    }

    /**
     * 公共的显示时间
     */
    @SuppressWarnings("deprecation")
    public static String formateDateString(Date date) {
        if (null == date) {
            return "";
        }
        Date now = new Date();
        if (now.getDate() - date.getDate() >= 1) {
            return dateToString(date, DAY_FORMAT + HOUR_MINUTE_FORMAT);
        }
        if (date.getTime() + 24 * 60 * 60 * 1000 < now.getTime()) {
            return dateToString(date, DAY_FORMAT + HOUR_MINUTE_FORMAT);
        } else if (date.getTime() + 60 * 60 * 1000 < now.getTime()) {
            return dateToString(date, HOUR_MINUTE_FORMAT.trim());
        } else if (date.getTime() + 60 * 1000 < now.getTime()) {

            return (now.getTime() - date.getTime()) / (60 * 1000) + "分钟前";
        } else {
            return (now.getTime() - date.getTime()) / 1000 + "秒前";
        }
    }

    /**
     * 公共的显示时间
     */
    @SuppressWarnings("deprecation")
    public static String formateDateStringLanuage(Date date, String lan) {
        if (null == date) {
            return "";
        }
        Date now = new Date();
        if (now.getDate() - date.getDate() >= 1) {
            return dateToString(date, "yyy-MM-dd HH:mm");
        }
        if (date.getTime() + 24 * 60 * 60 * 1000 < now.getTime()) {
            return dateToString(date, "yyy-MM-dd HH:mm");
        } else if (date.getTime() + 60 * 60 * 1000 < now.getTime()) {
            return dateToString(date, "HH:mm");
        } else if (date.getTime() + 60 * 1000 < now.getTime()) {
            if ("en".equals(lan)) {
                return (now.getTime() - date.getTime()) / (60 * 1000) + "minutes ago";
            }
            return (now.getTime() - date.getTime()) / (60 * 1000) + "分钟前";
        } else {
            if ("en".equals(lan)) {
                return (now.getTime() - date.getTime()) / 1000 + "seconds ago";
            }
            return (now.getTime() - date.getTime()) / 1000 + "秒前";
        }
    }

    /**
     * 取得当月第一天，如：2010-03-01
     */
    public static String getFirstDayOfCurMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat(DAY_FORMAT).format(c.getTime());
    }

    /**
     * 根据指定日期获取当月第一天
     * @param dateStr
     * @param format
     * @return
     */
    public static String getFirstDayOfMonth(String dateStr,String format) {
        Date date = stringToDate(format,dateStr);
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dateToString(cal.getTime(),format);
    }

    /**
     * 取得当月最后一天，如：2010-03-31
     */
    public static String getLastDayOfCurMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat(DAY_FORMAT).format(c.getTime());
    }

    /**
     * 根据指定日期获取当月最后一天
     * @param dateStr
     * @param format
     * @return
     */
    public static String getLastDayOfMonth(String dateStr,String format) {
        Date date = stringToDate(format,dateStr);
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateToString(cal.getTime(),format);
    }

    /**
     * 字符串转date
     *
     * @param format 格式化模式
     * @param date 时间
     * @return
     * @history
     */
    public static Date stringToDate(SimpleDateFormat format, String date) {
        Date newDate = null;
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            logger.error("转换时间异常", e);
        }
        return newDate;
    }

    /**
     * 时间字符串和格式生成date
     * @param format
     * @param date
     * @return
     */
    public static Date stringToDate(String format, String date) {
        Date newDate = null;
        try {
            newDate = new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            logger.error("转换时间异常", e);
        }
        return newDate;
    }

    /**
     * 取得格式化日期
     */
    public static String getFormatDate() {
        return new SimpleDateFormat(DAY_FORMAT).format(new Date());
    }

    /**
     * 取得格式化日期
     */
    public static String getFormatDate(String format) {
        if (format == null) {
            format = DAY_FORMAT;
        }
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 获得今天是礼拜几
     *
     * @return
     */
    public static int getDayOfWeek() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week = c.get(Calendar.DAY_OF_WEEK) - 1;
        return week;
    }

    /**
     * 获取对应日期是星期几
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week = c.get(Calendar.DAY_OF_WEEK) - 1;
        return week;
    }

    /**
     * Date 对象转换成对应格式的 字符串
     *
     * @param date
     * @param format 如："yyyy-MM-dd HH:mm:ss"，默认："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (date == null)
            return "";
        SimpleDateFormat formater = new SimpleDateFormat(isNull(format) ? TIME_FORMAT : format.trim());
        return formater.format(date);
    }

    public static String dateToString(Date date) {
        return dateToString(date, TIME_FORMAT);
    }

    public static String dateToDateString(Date date) {
        return dateToString(date, DAY_FORMAT);
    }

    // 日期返回为：2016年09月18日
    public static String dateToDateStringInChina(Date date) {
        String dateStr = dateToString(date, DAY_FORMAT);
        dateStr = dateStr.replaceFirst("-", "年");
        dateStr = dateStr.replaceFirst("-", "月");
        dateStr += "日";
        return dateStr;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        str = str != null ? str.trim() : str;
        return str == null || "".equals(str) ? true : false;
    }

    /**
     * Date 对象转换成对应格式的 字符串
     *
     * @param dateSource 默认："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date timeStrToDate(String dateSource) {
        if (isNull(dateSource))
            return null;
        return timeStrToDate(dateSource, null);
    }

    /**
     * Date 对象转换成对应格式的 字符串
     *
     * @param dateSource
     * @param format 如："yyyy-MM-dd HH:mm:ss"，默认："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date timeStrToDate(String dateSource, String format) {
        if (isNull(dateSource))
            return null;
        SimpleDateFormat formater = new SimpleDateFormat(isNull(format) ? TIME_FORMAT : format.trim().replace(".", "-").replace("/", "-"));
        try {
            return formater.parse(dateSource);
        } catch (ParseException e) {
            logger.error("", e);
            return null;
        }
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     *
     * @param nowdate
     * @param delay 小于 0，过去多小天，大于0 未来多小天
     * @param dateFormat
     * @return
     */
    public static String getNextDay(String nowdate, int delay, String dateFormat) {
        try {
            String mdate = "";
            Date d = timeStrToDate(nowdate, dateFormat);
            long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = new SimpleDateFormat(DAY_FORMAT).format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getNextDay2(String nowdate, int delay, String dateFormat) {
        try {
            String mdate = "";
            //Date d = timeStrToDate(nowdate, dateFormat);
            Date d = parseDate(nowdate, dateFormat);
            long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = new SimpleDateFormat(dateFormat).format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 时间字符串格式化成另一种格式
     * @param dateStr
     * @param format1
     * @param format2
     * @return
     */
    public static String str2OtherStr(String dateStr,String format1,String format2){
        try {
            String mdate = "";
            //Date d = timeStrToDate(nowdate, dateFormat);
            Date d = parseDate(dateStr, format1);
            mdate = new SimpleDateFormat(format2).format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     *
     * @param nowdate
     * @param delay 小于 0，过去多小天，大于0 未来多小天
     * @return
     */
    public static String getNextDay(String nowdate, int delay, String inDateFormat, String outDateFormat) {
        try {
            String mdate = "";
            Date d = timeStrToDate(nowdate, inDateFormat);
            long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = new SimpleDateFormat(outDateFormat).format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 的到一个月，延后或者提交，delay为前移或后延的月数
     *
     * @param nowdate 要处理的时间
     * @param delay 基数
     * @return
     * @history
     */
    public static String getNextMonth(String nowdate, int delay) {
        Date date = null;
        try {
            date = new SimpleDateFormat(MONTH_FORMAT).parse(nowdate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, delay);
            return new SimpleDateFormat(MONTH_FORMAT).format(calendar.getTime());
        } catch (ParseException e) {
            logger.error("时间转换失败", e);
        }
        return null;
    }

    /**
     * 返回本周
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String[] getWeekPresent() {
        String today = dateToString(new Date(), DAY_FORMAT);
        int i = getDayOfWeek();
        if (i == 0) {
            i = 7;
        }
        String beforeDay = getNextDay(today, 1 - i, DAY_FORMAT);
        String afterDay = getNextDay(today, 7 - i, DAY_FORMAT);
        return new String[] { beforeDay + " 00:00:00", afterDay + " 23:59:59" };
    }

    /**
     * 返回下周
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String[] getWeekNext() {
        String today = dateToString(new Date(), DAY_FORMAT);
        int i = getDayOfWeek();
        if (i == 0) {
            i = 7;
        }
        String beforeDay = getNextDay(today, 8 - i, DAY_FORMAT);
        String afterDay = getNextDay(today, 14 - i, DAY_FORMAT);
        return new String[] { beforeDay + " 00:00:00", afterDay + " 23:59:59" };
    }

    /**
     * 返回上周
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String[] getWeekLast() {
        String today = dateToString(new Date(), DAY_FORMAT);
        int i = getDayOfWeek();
        if (i == 0) {
            i = 7;
        }
        String beforeDay = getNextDay(today, -6 - i, DAY_FORMAT);
        String afterDay = getNextDay(today, -i, DAY_FORMAT);
        return new String[] { beforeDay + " 00:00:00", afterDay + " 23:59:59" };
    }

    /**
     * 两个时间相差距离多少分钟
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long 返回值为：分钟数
     */
    public static long getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat(TIME_FORMAT);
        long min = 0;
        try {
            Date d1 = df.parse(str1);
            Date d2 = df.parse(str2);
            long diff = d1.getTime() - d2.getTime();
            min = diff / (1000 * 60);
        } catch (Exception e) {
            logger.error("", e);
        }
        return min;
    }

    /**
     * 两个时间相差距离多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long 返回值为：秒数
     */
    public static long getDistanceSecond(String str1, String str2) {
        DateFormat df = new SimpleDateFormat(TIME_FORMAT);
        long s = 0;
        try {
            Date d1 = df.parse(str1);
            Date d2 = df.parse(str2);
            long diff = d1.getTime() - d2.getTime();
            s = diff / 1000;
        } catch (Exception e) {
            logger.error("", e);
        }
        return s;
    }

    /**
     * 判断2个时间大小
     *
     * @param DATE1 时间参数 1 格式：1990-01-01 12:00:00
     * @param DATE2 时间参数 2 格式：2009-01-01 12:00:00
     */
    public static int compare_date(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat(TIME_FORMAT);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                // System.out.println("dt1 在dt2后");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                // System.out.println("dt1在dt2前");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            logger.error("", exception);
        }
        return 0;
    }

    /**
     * 判断2个时间大小
     *
     * @param DATE1 时间参数 1 格式：1990-01-01 12:00:00
     * @param DATE2 时间参数 2 格式：2009-01-01 12:00:00
     */
    public static int compare_date(String DATE1, String DATE2, String format) {

        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                // System.out.println("dt1 在dt2后");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                // System.out.println("dt1在dt2前");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            logger.error("", exception);
        }
        return 0;
    }

    /**
     * 时间加
     *
     * @param DATE date
     * @param Minute 分钟为单位
     */
    public static Date dateAddition(Date DATE, Long Minute) {
        Date date = new Date();
        try {
            date = new Date(DATE.getTime() + Minute * 60 * 1000);
        } catch (Exception exception) {
            logger.error("", exception);
        }
        return date;
    }

    /**
     * 时间减
     *
     * @param DATE
     * @param Minute 分钟为单位
     */
    public static Date dateReduction(Date DATE, Long Minute) {
        Date date = new Date();
        try {
            date = new Date(DATE.getTime() - Minute * 60 * 1000);
        } catch (Exception exception) {
            logger.error("", exception);
        }
        return date;
    }

    /**
     * 当前时间加dayNum天数;
     * 
     * @create 2016年7月25日 下午8:06:54 zhuwei
     * @history
     */
    public static Date datePlus(Long dayNum) {
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return stringToDate(df, df.format(new Date(d.getTime() + dayNum * 24 * 60 * 60 * 1000)));
    }

    public static Date parseDate(String date) {
        if (date == null) {
            return null;
        }

        DateFormat fmt = date.length() <= 10 ? new SimpleDateFormat(DAY_FORMAT) : new SimpleDateFormat(TIME_FORMAT);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            logger.error("", e);
            return null;
        }
    }

    public static Date parseDate(String date, String dateFormat) {
        if (date == null) {
            return null;
        }

        DateFormat fmt = new SimpleDateFormat(dateFormat);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            logger.error("", e);
            return null;
        }
    }

    /**
     * 取得当前日期是多少周
     * 
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date, int delay) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setMinimalDaysInFirstWeek(1);
        c.setTime(date);
        if (delay != 0) {
            c.add(Calendar.WEEK_OF_YEAR, delay);
        }
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取某时间年
     *
     * @param date 时间
     * @return
     * @history
     */
    public static int getYear(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.YEAR);
    }

    /**
     * 获取某时间+多少周以后的年
     *
     * @param date 时间
     * @param delay 周数
     * @return
     * @history
     */
    public static int getYear(Date date, int delay) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.WEEK_OF_YEAR, delay);
        return instance.get(Calendar.YEAR);
    }

    /**
     * 获取某时间的年，第几周
     *
     * @param date
     * @return
     * @history
     */
    public static String getWeekAndYear(Date date, int delay) {
        int week = getWeekOfYear(date, delay);
        int year1 = getYear(date);
        int year2 = getYear(date, delay);
        if (year1 != year2) {
            year1 = year2;
        }
        return Integer.toString(year1) + "-" + Integer.toString(week);
    }

    /**
     * 得到某年某周的第一天
     * 
     * @param year
     * @param week
     * @return
     */
    public static String getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);
        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     * 
     * @param year
     * @param week
     * @return
     */
    public static String getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);
        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得指定日期所在周的第一天
     * 
     * @param date
     * @return
     */
    public static String getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DATE, c.getActualMinimum(Calendar.DAY_OF_WEEK) - dayOfWeek);
        return new SimpleDateFormat(DAY_FORMAT).format(c.getTime());

    }

    /**
     * 取得指定日期所在周的最后一天
     * 
     * @param date
     * @return
     */
    public static String getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_WEEK) - dayOfWeek);
        return new SimpleDateFormat(DAY_FORMAT).format(c.getTime());
    }

    /**
     * 日期加上当前时间的时分秒
     * 
     * @param date ,
     * @return
     * @create 2016年9月3日 下午4:41:22 linhaijian
     * @history
     */
    public static Date getDateAddTime(Date date, String hourMinuteSecond) {
        if (hourMinuteSecond == null) {
            hourMinuteSecond = getFormatDate(DateUtils.HOUR_MINUTE_SECOND_FORMAT);// 得到当前时间的时分秒
        }
        String dateStr = dateToString(date, DateUtils.DAY_FORMAT);// 转换日期为字符串
        String dateTime = dateStr + " " + hourMinuteSecond;// 日期加上时分秒
        return timeStrToDate(dateTime);
    }

    /**
     * 日期转换成美国日期格式"MM/dd/yyyy"
     * 
     * @param dateStr
     * @return
     */
    public static String convertToUSDate(String dateStr) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(DAY_FORMAT);
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String dateResult = "";
        try {
            dateResult = sdf2.format(sdf1.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        return dateResult;
    }

    /**
     * 将日期字符串转换为新的日期格式
     * 
     * @param dateStr 日期
     * @param inFormat ：原格式
     * @param outFormat ：最终格式
     * @return
     */
    public static String convertFormat(String dateStr, String inFormat, String outFormat) {
        if (isNull(dateStr))
            return "";
        SimpleDateFormat informater = new SimpleDateFormat(isNull(inFormat) ? TIME_FORMAT : inFormat.trim().replace("" + ".", "-"));
        SimpleDateFormat outformater = new SimpleDateFormat(isNull(outFormat) ? TIME_FORMAT : outFormat.trim().replace(".", "-"));
        try {
            return outformater.format(informater.parse(dateStr));
        } catch (ParseException e) {
            logger.error("", e);
            return "";
        }
    }

    /**
     * 获取日期所在周的每一天：
     * 
     * 
     * 
     * @param date
     * @return
     */
    public static Map<String, CompanyNumBean> getAnyDayOfWeek(Map<String, CompanyNumBean> map, Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(date);
        SimpleDateFormat fromat = new SimpleDateFormat(DAY_FORMAT_YYYYMMDD);//
        //

        String dayStr = "";// 日期
        String dayLable = "";// 日期显示文字
        for (int i = 0; i < 7; i++) {
            c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + i);
            dayStr = fromat.format(c.getTime());
            if (i == 0) {
                dayLable = "周日";
            } else if (i == 1) {
                dayLable = "周一";
            } else if (i == 2) {
                dayLable = "周二";
            } else if (i == 3) {
                dayLable = "周三";
            } else if (i == 4) {
                dayLable = "周四";
            } else if (i == 5) {
                dayLable = "周五";
            } else if (i == 6) {
                dayLable = "周六";
            }
            CompanyNumBean bean = new CompanyNumBean();
            bean.setIndex(i + 1);
            bean.setDataLable(dayLable);
            bean.setDate(dayStr);
            bean.setTotalNum(new BigDecimal(0));
            map.put(dayStr, bean);
            // System.out.println(dayStr+","+dayLable+"||");
        }
        return map;
    }

    /**
     * 获取日期所在月的每一天：
     * 
     * 
     * 
     * @param date
     * @return
     */
    public static Map<String, CompanyNumBean> getAnyDayOfMonth(Map<String, CompanyNumBean> map, Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(date);
        SimpleDateFormat fromat = new SimpleDateFormat(DAY_FORMAT_YYYYMMDD);//
        //
        int dayMax = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        String dayStr = "";// 日期
        String dayLable = "";// 日期显示文字
        for (int i = 0; i < dayMax; i++) {
            c.set(Calendar.DAY_OF_MONTH, 1 + i);
            dayStr = fromat.format(c.getTime());
            //
            dayLable = (1 + i) + "日";

            CompanyNumBean bean = new CompanyNumBean();
            bean.setIndex(i + 1);
            bean.setDataLable(dayLable);
            bean.setDate(dayStr);
            bean.setTotalNum(new BigDecimal(0));
            map.put(dayStr, bean);
            System.out.println(dayStr + "," + dayLable + "||");
        }
        return map;
    }

    /**
     * 获取日期所在季度的每一个月：
     * 
     * 
     * 
     * @param date
     * @return
     */
    public static Map<String, CompanyNumBean> getAnyMonthOfQuarter(Map<String, CompanyNumBean> map, Date date) {
        int quarter = getSeason(date);// 第几季度
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(date);
        SimpleDateFormat fromat = new SimpleDateFormat(DAY_FORMAT_YYYYMM);// 格式化成：年月
        //
        String dayStr = "";// 日期
        String dayLable = "";// 日期显示文字
        for (int i = 1; i < 4; i++) {
            int month = (quarter - 1) * 3 + i;
            c.set(Calendar.MONTH, month - 1);
            dayStr = fromat.format(c.getTime());
            //
            dayLable = month + "月";
            CompanyNumBean bean = new CompanyNumBean();
            bean.setIndex(i);
            bean.setDataLable(dayLable);
            bean.setDate(dayStr);
            bean.setTotalNum(new BigDecimal(0));
            map.put(dayStr, bean);
            System.out.println(dayStr + "," + dayLable + "||");
        }
        return map;
    }

    /**
     * 获取日期所在年的每一个月：
     * 
     * 
     * 
     * @param date
     * @return
     */
    public static Map<String, CompanyNumBean> getAnyMonthOfYear(Map<String, CompanyNumBean> map, Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(date);
        SimpleDateFormat fromat = new SimpleDateFormat(DAY_FORMAT_YYYYMM);// 格式化成：年月
        //
        String dayStr = "";// 日期
        String dayLable = "";// 日期显示文字
        for (int i = 1; i < 13; i++) {
            int month = i;
            c.set(Calendar.MONTH, month - 1);
            dayStr = fromat.format(c.getTime());
            //
            dayLable = month + "月";
            CompanyNumBean bean = new CompanyNumBean();
            bean.setIndex(i);
            bean.setDataLable(dayLable);
            bean.setDate(dayStr);
            bean.setTotalNum(new BigDecimal(0));
            map.put(dayStr, bean);
            System.out.println(dayStr + "," + dayLable + "||");
        }
        return map;
    }

    /**
     * 
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     * 
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 获取昨天日期的int值：
     * 
     * 
     * 
     * @return 20170102
     */
    public static int getYesterdayInt() {
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat fromat = new SimpleDateFormat(DAY_FORMAT_YYYYMMDD);// 格式化成：年月日
        //
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        int yesterday = Integer.parseInt(fromat.format(cal.getTime()));
        return yesterday;
    }

    /**
     * 获取今天日期的String值：
     * 
     * 
     * 
     * @return 20170102
     */
    public static String getToday() {
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat fromat = new SimpleDateFormat(DAY_FORMAT_YYYYMMDD);// 格式化成：年月日
        return fromat.format(cal.getTime());
    }

    /**
     * 根据一个日期字符串返回该周的最后一天的日期字符串
     * @param date
     * @param format
     * @return
     */
    public static String getWeekEndDate(String date,String format) {
        int weekDay = getDayOfWeek(DateUtils.stringToDate(format,date));
        int delay = 0;
        if (weekDay!=0){
            delay = 7-weekDay;
        }
        String weekEndDate = getNextDay2(date,delay,format);
        return weekEndDate;
    }

    /**
     * 获取指定字符串日期之间的天列表
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
    public static List<String> getDateList(String startDate, String endDate, String format) {
        String currentDate = startDate;
        List<String> dateList = new ArrayList<String>();
        for(int i=0;currentDate.compareTo(endDate)<=0;i++){
            dateList.add(currentDate);
            currentDate = DateUtils.getNextDay2(currentDate,1,format);
        }
        return dateList;
    }

    /**
     * 获取指定字符串日期之间的周列表
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
    public static List<String> getWeekList(String startDate, String endDate, String format) {
        String currentWeekStartDate = startDate;
        String currentWeekEndDate = DateUtils.getWeekEndDate(currentWeekStartDate,format);
        List<String> weekList = new ArrayList<String>();
        for(int i=0;currentWeekStartDate.compareTo(endDate)<=0;i++){
            if (endDate.compareTo(currentWeekEndDate)<=0) {
                currentWeekEndDate = endDate;
            }
            String weekStr = currentWeekStartDate + " - " + currentWeekEndDate;
            weekList.add(weekStr);
            currentWeekStartDate = DateUtils.getNextDay2(currentWeekEndDate,1,format);
            currentWeekEndDate = DateUtils.getNextDay2(currentWeekEndDate,7,format);
        }
        return weekList;
    }

    /**
     * 获取指定字符串日期之间的月列表
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
    public static List<String> getMonList(String startDate, String endDate, String format) {
        String currentMonStartDate = startDate;
        String currentMonEndDate = DateUtils.getLastDayOfMonth(startDate,format);
        List<String> weekList = new ArrayList<String>();
        for(int i=0;currentMonStartDate.compareTo(endDate)<=0;i++){
            if (endDate.compareTo(currentMonEndDate)<=0) {
                currentMonEndDate = endDate;
            }
            String weekStr = currentMonStartDate + " - " + currentMonEndDate;
            weekList.add(weekStr);
            currentMonStartDate = DateUtils.getNextDay2(currentMonEndDate,1,format);
            currentMonEndDate = DateUtils.getLastDayOfMonth(currentMonStartDate,format);
        }
        return weekList;
    }

    public static void main(String[] args) throws ParseException {
        /*getAnyDayOfMonth(new HashMap<String, CompanyNumBean>(), new Date());
        String weekEndDate = getWeekEndDate("20170701",DAY_FORMAT_YYYYMMDD);
        System.out.println(weekEndDate);*/
        List<String> monList = getMonList("2017/01/22","2017/07/20","yyyy/MM/dd");
        System.out.println(monList);
        String dateStr = getFirstDayOfMonth("2017/01/01","yyyy/MM/dd");
        System.out.println(dateStr);
        // System.out.println(DateUtils.getFirstDayOfWeek(2017, 10));
        // System.out.println(DateUtils.getLastDayOfWeek(2017, 10));
        // System.out.println(DateUtils.getWeekOfYear(new Date(), 0));
        // System.out.println(DateUtils.getWeekAndYear(new Date(), 0));
    }
}
