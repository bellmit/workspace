package org.jumao.bi.utis.exceptions;


import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


@SuppressWarnings("serial")
public class ParamErrorException extends Exception {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private String paramName = "";
	private Object paramVal = "";
	private String reason = "";
	
	private Object[] nameValArr;
	
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public Object getParamVal() {
		return paramVal;
	}
	public void setParamVal(Object paramVal) {
		this.paramVal = paramVal;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	public ParamErrorException() {
	}
	
	public ParamErrorException(Object... nameValArr) {
		this.nameValArr = nameValArr;
	}
	
	public ParamErrorException(String paramName, Object paramVal) {
		this.paramName = paramName;
		this.paramVal = paramVal;
	}

	/**
	 * @param paramName
	 * @param paramVal
	 * @param reason
	 */
	public ParamErrorException(String paramName, Object paramVal, String reason) {
		this.paramName = paramName;
		this.paramVal = paramVal;
		this.reason = reason;
	}

	/**
	 * @return
	 */
	public String toString() {
		JSONObject toStr = new JSONObject();
		try {
			if (nameValArr == null) {
				String val = paramVal == null ? "null" : paramVal.toString();
				toStr.put("paramName", paramName).put("paramVal", val).put("reason", reason);
			} else {
				int len = nameValArr.length;
				if (len != 0 && len % 2 == 0) {
					toStr.put("keyIsParamName", "valIsParamVal");
					for (int i = 0; i < len; i += 2) {
						toStr.put(nameValArr[i].toString(), nameValArr[i + 1].toString());
					}
				}
			}
		} catch (JSONException e) {
			logger.error("" ,e);
		}
		return toStr.toString();
	}

	@Override
	public String getMessage() {
		return toString();
	}
}
