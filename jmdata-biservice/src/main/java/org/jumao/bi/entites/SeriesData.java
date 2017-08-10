package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

public class SeriesData implements Serializable, Comparator<SeriesData> {

    private static final long serialVersionUID = -7005415733993060788L;
    private BigDecimal        value            = BigDecimal.ZERO;
    private String            name;
    private String            code;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int compare(SeriesData o1, SeriesData o2) {
        return o2.getValue().compareTo(o1.getValue());
    }
}
