package org.jumao.bi.utis.enums;

import org.jumao.bi.utis.constants.Key;

/**
 * 聚融通 融资趋势分析
 */
public enum JrtFTType {

    RZSQ(0, "融资申请笔数"), RSXQ(1, "融资需求笔数"), RZQYue(2, "融资签约笔数"), ELSE(999, "其他");

    private int type;
    private String name;


    public static JrtFTType getByType(int type) {
        switch (type) {
            case Key.Num0:
                return RZSQ;

            case Key.Num1:
                return RSXQ;

            case Key.Num2:
                return RZQYue;

            default:
                return ELSE;
        }
    }

    private JrtFTType(int type, String name) {
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
        JrtFTType jrtFTType = getByType(type);
        if (jrtFTType.equals(ELSE)) {
            return null;
        }
        return jrtFTType.getName();
    }

    public static void main(String[] args) {
        System.err.println(JrtFTType.getNameByType(6));
    }

}
