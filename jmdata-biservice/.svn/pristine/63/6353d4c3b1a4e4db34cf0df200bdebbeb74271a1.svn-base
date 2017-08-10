package org.jumao.bi.utis.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册分析-认证用户 概览折线图的 type
 */
public enum AUOType {

    PV(0, "浏览量"), UV(1, "独立访客"), REGISTER(2, "注册会员"),
    THREE_CERT_AUTH(3, "三证审核"), AUTH_LETTER(4, "授权书");

    private static Map<Integer, String> typeNameMap = new HashMap<Integer, String>();

    static {
        typeNameMap.put(PV.getType(), PV.getName());
        typeNameMap.put(UV.getType(), UV.getName());
        typeNameMap.put(REGISTER.getType(), REGISTER.getName());
        typeNameMap.put(THREE_CERT_AUTH.getType(), THREE_CERT_AUTH.getName());
        typeNameMap.put(AUTH_LETTER.getType(), AUTH_LETTER.getName());
    }


    private int type;
    private String name;

    private AUOType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }



    public static String getNameByType(Integer type) {
        return typeNameMap.get(type);
    }

}
