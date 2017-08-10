package org.jumao.bi.utis.enums;

import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;

/**
 * 聚融通（en） 流量分析 概览
 */
public enum EnJrtNfOv {


    PV(1, Key.Pv, "", CN.Pv),
    UV(2, Key.Uv, "", CN.Uv),
    NEW_USERS(3, Key.New_Users_, "%", CN.New_Users),
    OLD_USERS(4, Key.Old_Users_, "%", CN.Old_Users),
    BOUNCE_RATE(5, Key.Bounce_Rate_, "%", CN.Bounce_Rate),
    AVG_SESSION_DUR(6, Key.Avg_Session_Dur_, "", CN.Avg_Session_Dur),
    ELSE(999, Key.Else, "", CN.Else);

    private int id;
    private String name;
    private String unit;
    private String cnName;


    EnJrtNfOv(int id, String name, String unit, String cnName) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.cnName = cnName;
    }

    public static EnJrtNfOv getEnumByName(String name) {
        switch (name) {
            case Key.Pv: return PV;
            case Key.Uv: return UV;
            case Key.Bounce_Rate_: return BOUNCE_RATE;
            case Key.Avg_Session_Dur_: return AVG_SESSION_DUR;
            case Key.New_Users_: return NEW_USERS;
            case Key.Old_Users_: return OLD_USERS;
        }
        return ELSE;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
