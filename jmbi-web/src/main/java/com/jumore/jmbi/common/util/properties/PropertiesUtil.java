/**
 * 
 */
package com.jumore.jmbi.common.util.properties;

import org.apache.commons.lang.StringUtils;

import com.jumore.dove.util.SpringContextHelper;

/**
 * 配置项工具类
 * 
 * @author:
 * @since:
 * @history:
 */
public class PropertiesUtil {
	/***/
	public static PropertiesBean bean = SpringContextHelper
			.getBean(PropertiesBean.class);

	// /**获取图片上传服务类路径*/
	// public static String getImgUploadAddress() {
	// return bean.getImgUploadAddress();
	// }
	//
	// /**获取图片查看服务类路径*/
	// public static String getImgViewAddress() {
	// return bean.getImgViewAddress();
	// }

	/**
	 * 功能说明：根据图片的不完整地址获取图片的完整地址(完整地址=fileViewAddress+不完整地址)
	 * 
	 * @param url
	 * @return String
	 */
	public static String getFileViewUrl(String url) {
		if (StringUtils.isEmpty(url)) {
			return url;
		}
		if (url.startsWith("http://")) {
			return url;
		}
		return getWebImageViewPath() + url;
	}

	public static String getWebImagePath() {
		return bean.getWebImagePath();
	}

	public static String getWebImageViewPath() {
		return bean.getWebImageViewPath();
	}

	public static String getWebDomain() {
		return bean.getWebDomain();
	}

	public static String getBiServiceUrl() {
		return bean.getBiServiceUrl();
	}
}
