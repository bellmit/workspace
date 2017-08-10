package org.jumao.bi.utis.enums;

import org.jumao.bi.utis.RespUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册分析-认证用户 概览折线图的 type
 */
public enum JytAUOType {

    SMRZ(0, "实名认证"), WTFSZRZ(1, "委托方三证认证"), GYSSZRZ(2, "供应商三证认证"),
    WTSSQ(3, "委托商授权"), GYSSQ(4, "供应商授权");

    private static Map<Integer, String> typeNameMap = new HashMap<Integer, String>();

    static {
        typeNameMap.put(SMRZ.getType(), SMRZ.getName());
        typeNameMap.put(WTFSZRZ.getType(), WTFSZRZ.getName());
        typeNameMap.put(GYSSZRZ.getType(), GYSSZRZ.getName());
        typeNameMap.put(WTSSQ.getType(), WTSSQ.getName());
        typeNameMap.put(GYSSQ.getType(), GYSSQ.getName());
    }


    private int type;
    private String name;

    private JytAUOType(int type, String name) {
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

    public static void main(String[] args) throws Exception {

        Method method = JytAUOType.class.getMethod("getNameByType", Integer.class);
        Object name = method.invoke(null, 1);
        System.out.println();
    }
}
