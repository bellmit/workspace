package com.jumore.jmbi.enums;

/** 
* 删除标准枚举类
* 
* @author: panweiqiang
* @since: 2016年6月29日  下午3:38:40
* @history:
*/
public enum DeleteFlagEnum {

    // 未删除
    NORMAL("0"),
    // 已删除
    DELETED("1");

    /**
     * 0：未删除，1：已删除
     */
    String code;

    DeleteFlagEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
