package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Series implements Serializable {

    private static final long serialVersionUID = -7005415733993060788L;
    private List<BigDecimal>  data;
    private String            type;
    private String            name;

    public List<BigDecimal> getData() {
        return data;
    }

    public void setData(List<BigDecimal> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
