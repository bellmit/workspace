package com.jumore.jmbi.enums.message;

/**
 * 站内消息类型
 * 
 * @author: luochao
 * @since: 2016年7月19日 下午7:55:26
 * @history:
 */
public enum MessageInsideTypeEnum {
    /** 帐户相关 */
    USER_INFO("1", "帐户消息"),

    /** 操作相关 */
    OPERATION("2", "操作消息");

    String code; // 状态编号
    String name; // 状态名称

    MessageInsideTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * getName:得到名称.
     * 
     * @author Administrator
     * @date 2017年6月5日 下午1:13:03
     * @param code
     * @return
     */
    public static String getName(String code) {
        for (MessageInsideTypeEnum orderStatusEnum : MessageInsideTypeEnum.values()) {
            if (orderStatusEnum.getCode().equals(code)) {
                return orderStatusEnum.getName();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
