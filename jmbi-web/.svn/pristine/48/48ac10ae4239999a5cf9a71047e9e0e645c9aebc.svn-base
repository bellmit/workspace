/**
 * 
 */
package com.jumore.jmbi.common.util.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置项对象
 * 
 * @author:
 * @since:
 * @history:
 */
@Component("propertiesBean")
public class PropertiesBean {

	/**
	 * web项目调用：数据接口层的URL
	 */
	@Value("${bi.service.url}")
	private String biServiceUrl;

	/**
	 * 静态文件全域名
	 */
	@Value("${static.domain.url}")
	private String staticDomainUrl;

	@Value(value = "${web.image.path}")
	private String webImagePath;

	@Value(value = "${web.image.view.path}")
	private String webImageViewPath;

	@Value(value = "${web.domain}")
	private String webDomain;

	public String getWebImagePath() {
		return webImagePath;
	}

	public void setWebImagePath(String webImagePath) {
		this.webImagePath = webImagePath;
	}

	public String getWebImageViewPath() {
		return webImageViewPath;
	}

	public void setWebImageViewPath(String webImageViewPath) {
		this.webImageViewPath = webImageViewPath;
	}

	public String getWebDomain() {
		return webDomain;
	}

	public void setWebDomain(String webDomain) {
		this.webDomain = webDomain;
	}

	public String getStaticDomainUrl() {
		return staticDomainUrl;
	}

	public void setStaticDomainUrl(String staticDomainUrl) {
		this.staticDomainUrl = staticDomainUrl;
	}

	public String getBiServiceUrl() {
		return biServiceUrl;
	}

	public void setBiServiceUrl(String biServiceUrl) {
		this.biServiceUrl = biServiceUrl;
	}

}
