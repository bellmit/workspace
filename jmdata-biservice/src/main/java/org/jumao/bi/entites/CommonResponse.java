package org.jumao.bi.entites;

import java.io.Serializable;

import org.jumao.bi.constant.ServiceConst;

public class CommonResponse implements Serializable{


	private static final long serialVersionUID = 6572025575377048011L;
	
	private ResponseResult status;

	public ResponseResult getStatus() {
		return status;
	}

	public void setStatus(ResponseResult status) {
		this.status = status;
	}
	
	public void success() {
	    this.status = new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG);
	}

	public void fail(String message) {
        this.status = new ResponseResult(ServiceConst.SERVER_ERROR_CODE, message);
    }
	
	public void invalidRequest(String message) {
        this.status = new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, message);
    }
}
