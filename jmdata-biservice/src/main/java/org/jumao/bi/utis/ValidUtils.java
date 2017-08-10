package org.jumao.bi.utis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUtils {

    /**
     * isNumeric:判断是否是数字
     * 
     * @param str
     * @return
     */
	public static boolean isNumeric(String str) {
		if (null == str) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}

		return true;
	}

	/**
	 * isCorrectDateRange:判断是否是正确的日期参数.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isCorrectDateRange(String startDate, String endDate) {
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	     try {
			Date start = sdf.parse(startDate);
			Date end = sdf.parse(endDate);
			if (start.after(end)) {//开始日期大于结束日期
				return false;
			}
		} catch (ParseException e) {//出现异常直接返回false，输入参数错误
			
			return false;
		} 
	     
	     return true;
	}
	 
}
