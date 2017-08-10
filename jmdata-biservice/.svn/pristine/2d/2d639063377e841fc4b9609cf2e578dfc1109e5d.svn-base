package org.jumao.bi.utis.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 签章支付 概览折线图的 type
 */
public enum VUOType {

    PV(0, "浏览量"), UV(1, "独立访客"), VISA(2, "签章开通数"),
    PAY(3, "支付开通数");

    private static Map<Integer, String> typeNameMap = new HashMap<Integer, String>();

    static {
        typeNameMap.put(PV.getType(), PV.getName());
        typeNameMap.put(UV.getType(), UV.getName());
        typeNameMap.put(VISA.getType(), VISA.getName());
        typeNameMap.put(PAY.getType(), PAY.getName());
    }


    private int type;
    private String name;

    private VUOType(int type, String name) {
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
