/**
 * 
 */
package com.jumore.jmbi.common.util;

import java.text.DecimalFormat;

/**
 * 金额转换类
 * 
 * @author: luochao
 * @since: 2016年8月24日 下午3:18:18
 * @history:
 */
public class MoneyUtil {

    /**
     * double转String，保留小心点后两位有效数字（四舍五入）
     *
     * @param money 金额
     * @return
     * @history
     */
    public static String doubleToString(Double money) {
        if (money == null) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(money);
    }

    /**
     * 判断Double是否是整数 整数返回整数
     *
     * @return
     * @create 2016年9月23日 下午5:09:52 linhaijian
     * @history
     */
    public static Object isDouble(Double value) {
        if (value % 1 == 0) {
            return value.longValue();
        }

        return value;
    }

    /**
     * 返回数值小数点前整数的长度
     *
     * @return int 长度
     * @create 2016年9月23日 下午4:19:24 linhaijian
     * @history
     */
    public static int getLength(String money) {
        if (money == null) {
            return -1;
        }
        String[] moneys = money.split("\\.");
        return moneys != null ? moneys[0].length() : -1;
    }
}
