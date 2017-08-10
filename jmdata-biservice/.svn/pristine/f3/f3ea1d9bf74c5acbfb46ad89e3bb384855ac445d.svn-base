package org.jumao.bi.utis;

import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.jumao.bi.utis.constants.Key;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static final String New_Line           = "\r\n";

    public static final String Password_Stop_Word = "#";

    public static final String Utf8               = "utf8";

    public static final String Single_Quote       = "'";
    public static final String Less      = "<";
    public static final String Greater      = ">";

    /**
     * Avoid to transfer 'null' to front-end
     */
    public static String replaceNullWithBlank(String str) {
        return str == null ? "" : str;
    }

    public static String withBlankStr(String str) {
        return Verifier.isEffectiveStr(str) ? str : "";
    }

    /**
     * @param objs
     * @return
     */
    public static String joinStr(Object... objs) {
        if (objs == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : objs) {
            sb.append(obj);
        }
        return sb.toString();
    }

    /**
     * @param str
     * @return
     */
    public static String kickBlank(String str) {
        if (str == null) {
            return "";
        }
        return str.replace(" ", "");
    }

    /**
     * @param str
     * @return
     * @throws Exception
     */
    public static byte[] getUtf8Bytes(String str) throws Exception {
        return str.getBytes(Utf8);
    }

    /**
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String getUtf8Str(byte[] bytes) throws Exception {
        return new String(bytes, Utf8);
    }

    /**
     * getSqlCondition:得到sql过滤条件.
     * 
     * @author Administrator
     * @date 2017年6月13日 上午11:03:03
     * @param alias
     * @param fieldName
     * @param value
     * @param compareOp
     * @return
     */
    public static String getSqlCondition(String alias, String fieldName, String value, CompareOp compareOp) {
        StringBuffer subSql = new StringBuffer(" and ");
        String operator = "";
        switch (compareOp) {
            case LESS:
                operator = Less;
                break;
            case LESS_OR_EQUAL:
                operator = "<=";
                break;
            case EQUAL:
                operator = "=";
                break;
            case NOT_EQUAL:
                operator = "!=";
                break;
            case GREATER_OR_EQUAL:
                operator = ">=";
                break;
            case GREATER:
                operator = Greater;
                break;
            default:
                break;
        }
        if (fieldName.indexOf("time") != -1) {
            if (operator.indexOf(Greater) != -1) {
                value = value + DateUtils.FIRST_TIME;
            }
            if (operator.indexOf(Less) != -1) {
                value = value + DateUtils.LAST_TIME;
            }

        }
        if (isNotBlank(alias)) {
            subSql.append(alias).append(".");
        }
        subSql.append(fieldName).append(operator).append(Single_Quote).append(value).append(Single_Quote);
        return subSql.toString();
    }
    
    /**
     * formatDate:将yyyyMMdd转换为yyyy-MM-dd.
     * 
     * @author Administrator
     * @date 2017年6月13日 下午1:32:31
     * @param date
     * @return
     */
    public static String formatDate(String date) {
        if (StringUtils.isNotBlank(date)) {
            date = StringUtils.left(date, Key.Num4) + "-" + StringUtils.substring(date, Key.Num4, Key.Num6) + "-"
                    + StringUtils.right(date, Key.Num2);
        }
        return date;
    }
}