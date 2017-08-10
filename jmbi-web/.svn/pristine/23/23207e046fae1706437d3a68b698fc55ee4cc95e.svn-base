/**
 * 
 */
package com.jumore.jmbi.common.exceptions;

import com.jumore.jmbi.enums.BaseExceptionEnum;

/**
 * 项目异常基类
 * 
 * @author: luochao
 * @since: 2016年7月4日 下午7:05:09
 * @history:
 */
public class BaseException extends RuntimeException {

    /** 序列号 */
    private static final long serialVersionUID = -758729270479446205L;

    /** 错误编码 */
    private String            errorCode;

    /** 错误信息 */
    private String            errorMessage;

    /**
     * 错误参数,使用<code>String.format()</code>的方式替换errorMessage中的特殊字符
     * <p>
     * %s:字符串
     * </p>
     * <p>
     * %b:布尔类型，如true
     * </p>
     * <p>
     * %d:整数类型(十进制)
     * </p>
     * <p>
     * %f:浮点类型
     * </p>
     * <p>
     * %tF%n:年-月-日格式日期
     * </p>
     * <p>
     * </p>
     */
    private Object[]          args;

    /**
     * Creates a new instance of BaseException
     * 
     * @param errorCode
     * @param errorMessage
     * @param args
     */
    public BaseException(String errorCode, String errorMessage, Object... args) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.args = args;
    }

    /**
     * Creates a new instance of BaseException
     * 
     * @param errorCode
     * @param errorMessage
     * @param cause
     * @param args
     */
    public BaseException(String errorCode, String errorMessage, Throwable cause, Object... args) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.args = args;
    }

    /**
     * Creates a new instance of BaseException
     * 
     * @param exceptionEnum
     * @param args
     */
    public BaseException(BaseExceptionEnum exceptionEnum, Object... args) {
        this.errorCode = exceptionEnum.getCode();
        this.errorMessage = exceptionEnum.getMsg();
        this.args = args;
    }

    /**
     * Creates a new instance of BaseException
     * 
     * @param exceptionEnum
     * @param cause
     * @param args
     */
    public BaseException(BaseExceptionEnum exceptionEnum, Throwable cause, Object... args) {
        super(cause);
        this.errorCode = exceptionEnum.getCode();
        this.errorMessage = exceptionEnum.getMsg();
        this.args = args;
    }

    public String getMessage() {
        return String.format(errorMessage, args);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

}
