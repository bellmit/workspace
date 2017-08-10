package com.jumore.jmbi.common.requests;

import com.jumore.jmbi.common.exceptions.BaseException;

public class CommResponseUtil {

    /** 
     * 功能说明：根据错误类别返回错误码
     * 
     * @param e
     * @return 
     * Integer
     */
    public static Integer getStatusCode(BaseException e) {
        return StatusCodeEnum.INTERNAL_SERVER_ERROR.getCode();
    }
}
