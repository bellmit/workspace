package com.jumore.jmbi.result;

import java.io.Serializable;

/**
 * 响应结果通用DTO
 *
 * @author chenjunchi
 * @since 2016-06-23
 */
public class ResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = -6356642290113294018L;

    public enum ResponseCode {
        SUCCESS, // 成功
        ERROR, // 业务错误
        EXCEPTION, // 异常
        UNSUPPORT, // 不支持该业务
        NO_DATA, // 无数据
        ILLEGAL_ARG // 参数校验错误
    }

    /**
     * 响应码
     */
    private ResponseCode code = ResponseCode.SUCCESS;

    /**
     * 响应码描述
     */
    private String       desc = "调用成功";

    /**
     * 返回数据
     */
    private T            result;

    public ResponseDTO() {
    }

    public ResponseDTO(ResponseCode code) {
        this.code = code;
    }

    public ResponseDTO(ResponseCode code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Creates a new instance of ResponseDTO
     * 
     * @param code
     * @param desc
     * @param result
     */
    public ResponseDTO(ResponseCode code, String desc, T result) {
        this.code = code;
        this.desc = desc;
        this.result = result;
    }

    public ResponseDTO(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return ResponseCode.SUCCESS.equals(code);
    }

    public boolean isError() {
        return ResponseCode.ERROR.equals(code);
    }

    public boolean isException() {
        return ResponseCode.EXCEPTION.equals(code);
    }

    public boolean isUnsupport() {
        return ResponseCode.UNSUPPORT.equals(code);
    }

    public boolean isNoData() {
        return ResponseCode.NO_DATA.equals(code);
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String toString() {
        return "ResponseDTO{" + "code=" + code + ", desc='" + desc + '\'' + ", result=" + result + '}';
    }
}
