package org.jumao.bi.utis.enums;

import org.jumao.bi.utis.constants.Key;

/**
 * 聚融通en 融资趋势分析
 */
public enum EnJrtFTType {

    RZSQ(0, "融资申请"), RZQYue(1, "融资签约"), RZQYe(2, "入驻企业"), SJCP(3, "上架产品"), ELSE(999, "其他");

    private int type;
    private String name;


    public static EnJrtFTType getByType(int type) {
        switch (type) {
            case Key.Num0:
                return RZSQ;

            case Key.Num1:
                return RZQYue;

            case Key.Num2:
                return RZQYe;

            case Key.Num3:
                return SJCP;

            default:
                return ELSE;
        }
    }

    private EnJrtFTType(int type, String name) {
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
        EnJrtFTType enJrtFTType = getByType(type);
        if (enJrtFTType.equals(ELSE)) {
            return null;
        }
        return enJrtFTType.getName();
    }


}
