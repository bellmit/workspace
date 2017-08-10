package org.jumao.bi.utis;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/4.
 */
public class CalculationUtil {

    /**
     * 提供精确加法计算的add方法
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static String add(String value1, String value2) {
        try {
            Integer.parseInt(value1);
        } catch (NumberFormatException e) {
            return "0";
        }try {
            Integer.parseInt(value2);
        } catch (NumberFormatException e) {
            return "0";
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        BigDecimal add = b1.add(b2);
        return String.valueOf(add);
    }

    /**
     * 提供精确减法运算的sub方法
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static String sub(String value1, String value2) {
        try {
            Integer.parseInt(value1);
        } catch (NumberFormatException e) {
            return "0";
        }try {
            Integer.parseInt(value2);
        } catch (NumberFormatException e) {
            return "0";
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        BigDecimal sub = b1.subtract(b2);
        return String.valueOf(sub);
    }

    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static String mul(String value1, String value2) {
        try {
            Integer.parseInt(value1);
        } catch (NumberFormatException e) {
            return "0";
        }try {
            Integer.parseInt(value2);
        } catch (NumberFormatException e) {
            return "0";
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        BigDecimal multiply = b1.multiply(b2);
        return String.valueOf(multiply);
    }

    /**
     * 提供精确乘法运算的mul方法，并保留n位小数
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static String mul_dec(String value1, String value2, int dec) {
        try {
            Integer.parseInt(value1);
        } catch (NumberFormatException e) {
            return "0";
        }try {
            Integer.parseInt(value2);
        } catch (NumberFormatException e) {
            return "0";
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        BigDecimal multiply = b1.multiply(b2);
        String s = String.valueOf(multiply);
        if (s.equals("0")) {
            return s;
        } else {
            String[] split = s.split("\\.");
            s = split[0] + "." + split[1].substring(0, dec);
        }
        return s;
    }

    /**
     * 提供精确的除法运算方法div
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static String div(String value1, String value2, int scale) throws IllegalAccessException {
        try {
            Integer.parseInt(value1);
        } catch (NumberFormatException e) {
            return "0";
        }try {
            Integer.parseInt(value2);
        } catch (NumberFormatException e) {
            return "0";
        }
        //如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        BigDecimal divide = new BigDecimal(0);
        try {
            if (b1 != null && b2 != null && !b2.equals("0")) {
                divide = b1.divide(b2, scale, BigDecimal.ROUND_HALF_EVEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(divide);
    }

    /**
     * 提供精确的除法运算方法div，无scale
     *
     * @param value1 被除数
     * @param value2 除数
     * @return 两个参数的商
     * @throws IllegalAccessException
     */

    public static String div(String value1, String value2) {
        try {
            Integer.parseInt(value1);
        } catch (NumberFormatException e) {
            return "0";
        }try {
            Integer.parseInt(value2);
        } catch (NumberFormatException e) {
            return "0";
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        BigDecimal v = new BigDecimal(0);
        try {
            if (value1 != null && value2 != null && !value2.equals("0")) {
                v = b1.divide(b2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(v);
    }

    /**
     * 提供精确的除法运算方法div，无scale，保留几位小数
     *
     * @param value1 被除数
     * @param value2 除数
     * @param dec    保留几位小数
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static String div_dec(String value1, String value2, int dec) {
        try {
            Integer.parseInt(value1);
        } catch (NumberFormatException e) {
            return "0";
        }try {
            Integer.parseInt(value2);
        } catch (NumberFormatException e) {
            return "0";
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        BigDecimal v = new BigDecimal(0);
        try {
            if (value1 != null && value2 != null && !value2.equals("0")) {
                v = b1.divide(b2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = String.valueOf(v);
        if (s.equals("0")) {
            return s;
        } else {
            String[] split = s.split("\\.");
            s = split[0] + "." + split[1].substring(0, dec);
        }
        return s;
    }


    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(0.06 + 0.01);
        System.out.println(1.0 - 0.42);
        System.out.println(4.015 * 100);
        System.out.println(303.1 / 1000);
        System.out.println("-----------------------------------------");
        System.out.println(add("0.06", "0.01"));
        System.out.println(sub("1.0", "0.42"));

        System.out.println(mul("4.015", "100"));
//        System.out.println(String.valueOf(div("303.1","1000")));
        System.out.println(div("1000000000001038755.99", "10000"));
        System.out.println(div("88", "250", 3));
    }

}
