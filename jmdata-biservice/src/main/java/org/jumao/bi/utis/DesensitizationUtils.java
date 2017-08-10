package org.jumao.bi.utis;

import org.apache.commons.lang.StringUtils;

public class DesensitizationUtils {
    
    private static final String Star_Str = "***";
    private static final int Five_Num = 5;

	/**
	 * 获取脱敏名称
	 * 
	 * @param sourceName
	 * @return
	 */
	public static String getDesStr(String sourceName) {

		if (StringUtils.isBlank(sourceName)) {
			return "";
		}
		int strLength = sourceName.length();
		if (strLength > Five_Num) {
			return sourceName.substring(0, 2) + Star_Str
					+ sourceName.substring(strLength - 2, strLength);
		} else if (strLength <= Five_Num && strLength > 2) {
			return sourceName.substring(0, 2) + Star_Str;
		} else {
			return sourceName;
		}

	}


}
