package org.jumao.bi.service.impl.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.jumao.bi.service.test.IPingSvc;

public class PingSvcImpl implements IPingSvc {

	@Context
	MessageContext messageContext;
	
	
    /**
     * BI service连通性测试接口
     * @see org.jumao.bi.service.test.IPingSvc#ping()
     */
	public String ping() {

		HttpServletRequest httpServletRequest = messageContext
				.getHttpServletRequest();

		StringBuilder responseBody = new StringBuilder();

		responseBody.append("BI service is working on machine \""
				+ getHostName() + "\" on " + new Date());
		@SuppressWarnings("unchecked")
		Enumeration<String> headerNames = httpServletRequest.getHeaderNames();

		responseBody
				.append("<br>\n<br>\nHeaders are passed in this http request are listed below:");
		while (headerNames.hasMoreElements()) {
			String headername = (String) headerNames.nextElement();
			String headerval = httpServletRequest.getHeader(headername);
			responseBody.append("\n<br>" + headername + ": " + headerval);
		}

		return responseBody.toString();

	}

	private String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException uhe) {
			return "UNKNOWN";
		}
	}
}
