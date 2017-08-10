package org.jumao.bi.utis.enums;

import org.jumao.bi.utis.constants.Key;

/**
 * 聚融通（en） 流量分析  趋势分析 折线图
 */
public enum EnJrtNfSm {

    ORGANIC(0, "搜索引擎"), DIRECT(1, "直接访问"), REFERRAL(2, "外部链接"),
    ELSE(999, "其他");

    private int type;
    private String name;


    public static EnJrtNfSm getByType(int type) {
        switch (type) {
            case Key.Num0:
                return ORGANIC;

            case Key.Num1:
                return DIRECT;

            case Key.Num2:
                return REFERRAL;

            default:
                return ELSE;
        }
    }

    private EnJrtNfSm(int type, String name) {
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
        EnJrtNfSm enJrtFTType = getByType(type);
        if (enJrtFTType.equals(ELSE)) {
            return null;
        }
        return enJrtFTType.getName();
    }


}
