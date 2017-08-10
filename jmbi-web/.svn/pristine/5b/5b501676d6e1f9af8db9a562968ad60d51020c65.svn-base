package com.jumore.jmbi.common.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import com.jumore.dove.common.log.LogHelper;

/**
 * 字符串工具类
 * 
 * @author zw
 */
public class StringUtil {

    public static final LogHelper Log_Helper = LogHelper.getLogger(StringUtil.class);

    /**
     * 将对象转换为整数
     */
    public static int strToInt(Object o) {
        String str = valueOf(o);
        return strToInt(str);
    }

    /**
     * 将字符窜转换为整数,如果转换过程中发生异常,则返回最小整数值
     * 
     * @param str
     * @return 如果转换过程中发生异常,则返回最小整数值
     */
    public static int strToInt(String str) {
        int i = Integer.MIN_VALUE;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            i = Integer.MIN_VALUE;
        }

        return i;
    }

    /**
     * 判断字符串是否为数字组成 2011.01.12 by sunyouhua
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * parseInteger:String转化Integer.
     * 
     * @author Administrator
     * @date 2017年6月2日 上午10:35:32
     * @param objValue
     * @return
     */
    public static Integer parseInteger(String objValue) {
        if (objValue == null) {
            return null;
        }
        try {
            return Integer.parseInt(objValue);
        } catch (Exception e) {
            Log_Helper.getBuilder().error(e.getMessage());
        }
        return null;
    }

    public static boolean isNull(String str) {
        return str == null || "".equals(str.trim()) ? true : false;
    }

    public static boolean isNotNull(String str) {
        return str == null || "".equals(str.trim()) ? false : true;
    }

    // 把对象转换成String
    public static String valueOf(Object obj) {
        return (obj == null || "".equals(obj) || "null".equals(obj)) ? "" : obj.toString().trim();
    }

    /**
     * 字符数字转化为int型数字
     * 
     * @param value
     * @return
     */
    public static int getInt(String value) {
        int i = 0;
        try {
            if (value != null) {
                value = value.trim();
                i = Integer.parseInt(value);
            }
        } catch (Exception e) {
            Log_Helper.getBuilder().error(e.getMessage());
        }
        return i;
    }

    /**
     * 转码 js encodeURIComponent 编码后数据 yzh 2015-03-27
     * 
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return new String(str.getBytes("ISO8859-1"), "UTF-8");
    }

    /**
     * 扩展并右对齐字符串，用指定字符填充左边。
     * 
     * <pre>
     * StringUtil.alignRight(null, *, *)     = null
     * StringUtil.alignRight("", 3, 'z')     = "zzz"
     * StringUtil.alignRight("bat", 3, 'z')  = "bat"
     * StringUtil.alignRight("bat", 5, 'z')  = "zzbat"
     * StringUtil.alignRight("bat", 1, 'z')  = "bat"
     * StringUtil.alignRight("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @param padChar 填充字符
     *
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignRight(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }

        int pads = size - str.length();

        if (pads <= 0) {
            return str;
        }

        return alignRight(str, size, String.valueOf(padChar));
    }

    /**
     * 扩展并右对齐字符串，用指定字符串填充左边。
     * 
     * <pre>
     * StringUtil.alignRight(null, *, *)      = null
     * StringUtil.alignRight("", 3, "z")      = "zzz"
     * StringUtil.alignRight("bat", 3, "yz")  = "bat"
     * StringUtil.alignRight("bat", 5, "yz")  = "yzbat"
     * StringUtil.alignRight("bat", 8, "yz")  = "yzyzybat"
     * StringUtil.alignRight("bat", 1, "yz")  = "bat"
     * StringUtil.alignRight("bat", -1, "yz") = "bat"
     * StringUtil.alignRight("bat", 5, null)  = "  bat"
     * StringUtil.alignRight("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @param padStr 填充字符串
     *
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignRight(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }

        if ((padStr == null) || (padStr.length() == 0)) {
            padStr = " ";
        }

        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return new String(padding).concat(str);
        }
    }


    /**
     * 取出百分号
     * 
     * @param str
     * @return
     */
    public static String filterPercent(String str) {
        if (str == null) {
            return null;
        }

        return str.replaceAll("%", "");

    }

}
