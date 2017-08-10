package org.jumao.bi.utis.enums;

import org.jumao.bi.utis.constants.Key;

/**
 * 聚融通（en） 流量分析  趋势分析 折线图
 */
public enum EnJrtNfLine {

    PV(0, "浏览量", ""), UV(1, "独立访客", ""), AVG_SESSION_DUR(2, "平均停留时间", "分钟"), AVG_PV(3, "访问深度", "页"),
    NEW_USERS(4, "新访客", ""), BOUNCE_RATE(5, "跳出率", "%"), SESSIONS(6, "访问次数", ""), OLD_USERS(7, "老访客", ""),
    ELSE(999, "其他", "");

    private int type;
    private String name;
    private String unit;


    public static EnJrtNfLine getByType(int type) {
        switch (type) {
            case Key.Num0:
                return PV;

            case Key.Num1:
                return UV;

            case Key.Num2:
                return AVG_SESSION_DUR;

            case Key.Num3:
                return AVG_PV;

            case Key.Num4:
                return NEW_USERS;

            case Key.Num5:
                return BOUNCE_RATE;

            case Key.Num6:
                return SESSIONS;

            case Key.Num7:
                return OLD_USERS;

            default:
                return ELSE;
        }
    }

    private EnJrtNfLine(int type, String name, String unit) {
        this.type = type;
        this.name = name;
        this.unit = unit;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public static String getNameByType(Integer type) {
        EnJrtNfLine enJrtFTType = getByType(type);
        if (enJrtFTType.equals(ELSE)) {
            return null;
        }
        return enJrtFTType.getName();
    }


}
