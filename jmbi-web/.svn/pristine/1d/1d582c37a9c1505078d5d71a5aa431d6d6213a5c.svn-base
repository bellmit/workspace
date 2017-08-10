package com.jumore.jmbi.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumore.dove.common.BusinessException;
import com.jumore.dove.common.log.LogHelper;
import com.jumore.jmbi.common.exceptions.BizException;
import com.jumore.jmbi.common.exceptions.RemoteGenericException;
import com.jumore.jmbi.common.exceptions.ValidationException;
import com.jumore.jmbi.common.requests.CommResponse;

/**
 * 异常处理切面
 * 
 * @author: panweiqiang
 * @since: 2016年7月4日 下午1:48:15
 * @history:
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    private static final LogHelper Log_Helper = LogHelper.getLogger(ExceptionAdvice.class);

    /**
     * 远程调用异常
     *
     * @param e
     * @return
     * @history
     */
    @ExceptionHandler(RemoteGenericException.class)
    public CommResponse handleRemoteGenericException(RemoteGenericException e) {
        Log_Helper.getBuilder().error("发生业务异常", e);
        return CommResponse.failure(e);
    }

    /**
     * 校验异常
     *
     * @param e
     * @return
     * @history
     */
    @ExceptionHandler(ValidationException.class)
    public CommResponse handleValidationException(ValidationException e) {
        Log_Helper.getBuilder().warn("校验失败", e);
        return CommResponse.failure(e);
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     * @history
     */
    @ExceptionHandler(BizException.class)
    public CommResponse handleBizException(BizException e) {
        Log_Helper.getBuilder().warn("业务异常", e);
        return CommResponse.failure(e);
    }

    /**
     * 特殊字符校验异常
     *
     * @param e
     * @return
     * @history
     */
    @ExceptionHandler(BusinessException.class)
    public CommResponse handleBizException(BusinessException e) {
        Log_Helper.getBuilder().warn("特殊字符校验异常", e);
        return CommResponse.failure("输入内容包含非法字符,请重新输入!");
    }

    /**
     * 未知异常
     *
     * @param e
     * @return
     * @history
     */
    @ExceptionHandler(Exception.class)
    public CommResponse handleException(Exception e) {
        Log_Helper.getBuilder().error("服务运行异常", e);
        return CommResponse.failure(e);
    }

}
