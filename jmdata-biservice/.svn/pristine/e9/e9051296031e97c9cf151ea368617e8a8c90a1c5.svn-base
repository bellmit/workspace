package org.jumao.bi.utis.enums;

import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;

/**
 * @author chen qian
 */
public enum JytEnum {

    GYSQYS(1, "供应商企业数", "个"), WTFQYS(2, "委托方企业数", "个"), WTFGRS(3, "委托方个人数", "个"),
    CKS(4, "仓库数", "个"), XLS(5, "线路数", "条"), HZ(6, "货值", CN.Wan_Yuan), DDJE(7, "订单金额", CN.Wan_Yuan),
    DDS(8, "订单数", "个"), SJS(9, "司机数", "个"), CLS(10, "车辆数", "辆"), ELSE(999, "其他", "");


    private int type;
    private String cnName;
    private String unit;

    JytEnum(int type, String cnName, String unit) {
        this.type = type;
        this.cnName = cnName;
        this.unit = unit;
    }

    public boolean equal(JytEnum jytEnum) {
        if (this.getType() == jytEnum.getType()) {
            return true;
        } else {
            return false;
        }
    }

    public static JytEnum getByType(int type) {
        switch (type) {
            case Key.Num1:
                return GYSQYS;

            case Key.Num2:
                return WTFQYS;

            case Key.Num3:
                return WTFGRS;

            case Key.Num4:
                return CKS;

            case Key.Num5:
                return XLS;

            case Key.Num6:
                return HZ;

            case Key.Num7:
                return DDJE;

            case Key.Num8:
                return DDS;

            case Key.Num9:
                return SJS;

            case Key.Num10:
                return CLS;

            default:
                return ELSE;
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
