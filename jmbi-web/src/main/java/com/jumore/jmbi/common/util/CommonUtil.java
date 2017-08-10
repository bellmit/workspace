package com.jumore.jmbi.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.jumore.dove.common.log.LogHelper;

/**
 * 通用工具类
 *
 * @author chenjunchi
 * @since 2016-06-23
 */
public class CommonUtil {

    private static final LogHelper Log_Helper = LogHelper.getLogger(CommonUtil.class);
    private static final String    Err_Msg    = "处理特殊字符时异常：";

    /**
     * @param num 目标数值的最大值(不包含num)---[0,num)
     * @param figures 位数
     * @return 返回0到num之间figures位的随机数(前面补零) 注：不支持位数截取
     */
    public static String getRandomNum(int num, int figures) {

        if (num < 0) {
            return null;
        }

        // 生成 [0,num) 之间的随机数(整数)
        Random random = new Random();
        String ret = String.valueOf(random.nextInt(num));

        // 位数不够的场合，前面补零
        return getNumByFigures(ret, figures);
    }

    /**
     *
     * @param num 待转换数值(字符型)
     * @param figures 位数
     * @return 返回figures位的字符串(数字前面补零)
     */
    public static String getNumByFigures(String num, int figures) {

        if (StringUtils.isEmpty(num) || figures < 1) {
            return null;
        }

        // 位数不够的场合，前面补零
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < figures - num.length(); i++) {
            buf.append("0");
        }

        return buf.toString() + num;

    }

    public static String getClientIP(HttpServletRequest request) {

        if (request == null) {
            return null;
        }

        String s = request.getHeader("X-Forwarded-For");

        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = request.getHeader("Proxy-Client-IP");
        }

        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = request.getHeader("WL-Proxy-Client-IP");
        }

        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = request.getHeader("HTTP_CLIENT_IP");
        }

        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = request.getRemoteAddr();
        }
        if ("127.0.0.1".equals(s) || "0:0:0:0:0:0:0:1".equals(s)) {
            try {
                s = InetAddress.getLocalHost().getHostAddress();

            } catch (UnknownHostException unknownhostexception) {
                Log_Helper.getBuilder().error("", unknownhostexception);
            }
        }
        return s;
    }

    /**
     * 针对特殊字符（'\r\n\）的处理
     * 
     * @param model
     */
    public static void exchangeStringProperties(Object model) {
        if (model == null) {
            return;
        }
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String type = field.getGenericType().toString();
            if ("class java.lang.String".equals(type)) {
                String value = null;
                try {
                    Method m = model.getClass().getMethod("get" + name);
                    value = (String) m.invoke(model);
                    value = HtmlUtils.htmlEscape(value);
                    field.set(model, value);
                } catch (NoSuchMethodException e) {
                    Log_Helper.getBuilder().error(Err_Msg, e);
                } catch (IllegalAccessException e) {
                    Log_Helper.getBuilder().error(Err_Msg, e);
                } catch (InvocationTargetException e) {
                    Log_Helper.getBuilder().error(Err_Msg, e);
                }
            }
        }
    }

    /**
     * 针对特殊字符（'\r\n\）的处理
     * 
     * @param model
     */
    public static void exchangeStrings(List<?> model) {
        if (model == null) {
            return;
        }
        for (Object object : model) {
            exchangeStringProperties(object);
        }
    }
}
