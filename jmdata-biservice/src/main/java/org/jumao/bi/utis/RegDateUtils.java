package org.jumao.bi.utis;

import org.jumao.bi.utis.exceptions.ParamErrorException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *  @Author Kartty
 *  @Date 2014-11-21
 *  @version 2.0
 */
public class RegDateUtils {

    public static String DATE_STRING = "date string";

    private static SimpleDateFormat FULL_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat NO_SEC_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat NO_MIN_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH");
    private static SimpleDateFormat NO_HOUR_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat NO_DAY_FORMAT = new SimpleDateFormat("yyyy-MM");

    private static SimpleDateFormat NO_MIN_NO_ = new SimpleDateFormat("yyyyMMddHH");
    private static SimpleDateFormat NO_HOUR_NO_ = new SimpleDateFormat("yyyyMMdd");

    public static String formatToFullF(Timestamp timestamp) {
        return FULL_FORMAT.format(timestamp);
    }

    public static String formatToFullF(Date date) {
        return FULL_FORMAT.format(date);

    }

    /**
     * @return
     * @throws Exception
     */
    public static String changeToFFStr(String dateTimeStr) throws Exception {
        try {
            return FULL_FORMAT.format(FULL_FORMAT.parse(dateTimeStr));
        } catch (Exception e) {
            throw new ParamErrorException(DATE_STRING, dateTimeStr, "not in format  yyyy-MM-dd HH:mm:ss");
        }
    }
    /**
     * @return
     * @throws Exception
     */
    public static Date parseToFFDate(String dateTimeStr) throws Exception {
        try {
            return FULL_FORMAT.parse(dateTimeStr);
        } catch (Exception e) {
            throw new ParamErrorException(DATE_STRING, dateTimeStr, "not in format  yyyy-MM-dd HH:mm:ss");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    /**
     * @return
     * @throws Exception
     */
    public static String formatToNoSecF(Date date) {
        return NO_SEC_FORMAT.format(date);
    }
    /**
     * @return
     * @throws Exception
     */
    public static String formatToNoSecF(Timestamp timestamp) {
        return NO_SEC_FORMAT.format(timestamp);
    }
    /**
     * @return
     * @throws Exception
     */
    public static String changeToNoSecF(String dateTimeStr) throws Exception {
        return NO_SEC_FORMAT.format(NO_SEC_FORMAT.parse(dateTimeStr));
    }
    /**
     * @return
     * @throws Exception
     */
    public static Date parseToNoSecF(String dateTimeStr) throws Exception {
        try {
            return NO_SEC_FORMAT.parse(dateTimeStr);
        } catch (Exception e) {
            throw new ParamErrorException(DATE_STRING, dateTimeStr, "not in format  yyyy-MM-dd HH:mm");
        }
    }


    /////////////////////////////////////////////////////////////////////////////////

    /**
     * @return
     * @throws Exception
     */
    public static String changeToNoHourF(String dateTimeStr) throws Exception {
        return NO_HOUR_FORMAT.format(NO_HOUR_FORMAT.parse(dateTimeStr));
    }
    /**
     * @return
     * @throws Exception
     */
    public static String formatToNoHourF(Date date) throws Exception {
        return NO_HOUR_FORMAT.format(date);
    }
    /**
     * @return
     * @throws Exception
     */
    public static Date parseToNoHourF(String dateTimeStr) throws Exception {
        try {
            return NO_HOUR_FORMAT.parse(dateTimeStr);
        } catch (Exception e) {
            throw new ParamErrorException(DATE_STRING, dateTimeStr, "not in format  yyyy-MM-dd");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    /**
     * @return
     * @throws Exception
     */
    public static String changeToNoDayF(String dateTimeStr) throws Exception {
        return NO_DAY_FORMAT.format(NO_DAY_FORMAT.parse(dateTimeStr));
    }
    /**
     * @return
     * @throws Exception
     */
    public static String formatToNoDayF(Date date) throws Exception {
        return NO_DAY_FORMAT.format(date);
    }
    /**
     * @return
     * @throws Exception
     */
    public static Date parseToNoDayF(String dateTimeStr) throws Exception {
        try {
            return NO_DAY_FORMAT.parse(dateTimeStr);
        } catch (Exception e) {
            throw new ParamErrorException(DATE_STRING, dateTimeStr, "not in format  yyyy-MM-dd");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    /**
     * @return
     * @throws Exception
     */
    public static String changeToNoHourAnd_(String dateTimeStr) throws Exception {
        return NO_HOUR_NO_.format(NO_HOUR_NO_.parse(dateTimeStr));
    }
    /**
     * @return
     * @throws Exception
     */
    public static String formatToNoHourAnd_(Date date) throws Exception {
        return NO_HOUR_NO_.format(date);
    }
    /**
     * @return
     * @throws Exception
     */
    public static Date parseToNoHourAnd_(String dateTimeStr) throws Exception {
        try {
            return NO_HOUR_NO_.parse(dateTimeStr);
        } catch (Exception e) {
            throw new ParamErrorException(DATE_STRING, dateTimeStr, "not in format  yyyyMMdd");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    /**
     * @return
     * @throws Exception
     */
    public static String changeToNoMinAnd_(String dateTimeStr) throws Exception {
        return NO_MIN_NO_.format(NO_MIN_NO_.parse(dateTimeStr));
    }
    /**
     * @return
     * @throws Exception
     */
    public static String formatToNoMinAnd_(Date date) throws Exception {
        return NO_MIN_NO_.format(date);
    }
    /**
     * @return
     * @throws Exception
     */
    public static Date parseToNoMinAnd_(String dateTimeStr) throws Exception {
        try {
            return NO_MIN_NO_.parse(dateTimeStr);
        } catch (Exception e) {
            throw new ParamErrorException(DATE_STRING, dateTimeStr, "not in format  yyyyMMdd");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * @return
     * @throws Exception
     */
    public static Date parseStartWithFF(String dateTimeStr) throws Exception {
        Date date = null;
        try {
            date = FULL_FORMAT.parse(dateTimeStr);
        } catch (Exception e0) {
            date = parseStartWithNoSecF(dateTimeStr);
        }
        return date;
    }

    /**
     * @return
     * @throws Exception
     */
    public static Date parseStartWithNoSecF(String dateTimeStr) throws Exception {
        Date date = null;
        try {
            date = NO_SEC_FORMAT.parse(dateTimeStr);
        } catch (Exception e) {
            try {
                date = NO_MIN_FORMAT.parse(dateTimeStr);
            } catch (Exception e2) {
                try {
                    date = NO_HOUR_FORMAT.parse(dateTimeStr);
                } catch (Exception e3) {
                    throw new ParamErrorException(DATE_STRING, dateTimeStr, "not an effictive date string");
                }
            }
        }
        return date;
    }

}