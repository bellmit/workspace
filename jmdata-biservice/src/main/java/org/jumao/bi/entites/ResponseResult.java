/**
 * Project Name:tizrs-service
 * File Name:ProcessStatus.java
 * Package Name:org.zjenterprise.app.entitis
 * Date:2015年7月20日上午11:39:34
 * Copyright (c) 2015, ZJEnterPrise All Rights Reserved.
 *
*/

package org.jumao.bi.entites;

import java.io.Serializable;

/**
 * ClassName:ProcessStatus <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年7月20日 上午11:39:34 <br/>
 * @author   TK
 * @version  
 * @see 	 
 */
public class ResponseResult  implements Serializable {
	
	private static final long serialVersionUID = 4930253990259046509L;
	private String code; //结果编码
	private String message;//信息描述
	
	public ResponseResult() {
	}
	
	public ResponseResult(String code, String message) {
		this.code = code;
		this.message = message;
	}
	/**
	 * code.
	 *
	 * @return  the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * code.
	 *
	 * @param   code    the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * message.
	 *
	 * @return  the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * message.
	 *
	 * @param   message    the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

