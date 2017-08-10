package com.jumore.jmbi.common.util.validation;

import java.util.Map;

/**
 * 校验结果类
 *
 * @author: luochao
 * @since: 2016年7月4日 下午2:43:22
 * @history:
 */
public class ValidationResult {

    // 校验结果是否有错，默认校验通过
    private boolean             hasErrors = false;

    // 校验错误信息
    private Map<String, String> errorMsg;

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * getMessage:得到错误信息.
     * 
     * @author Administrator
     * @date 2017年6月2日 下午4:41:32
     * @return
     */
    public String getMessage() {
        if (errorMsg == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (String key : errorMsg.keySet()) {
            sb.append(errorMsg.get(key));
            sb.append(";");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg=" + errorMsg + "]";
    }

}
