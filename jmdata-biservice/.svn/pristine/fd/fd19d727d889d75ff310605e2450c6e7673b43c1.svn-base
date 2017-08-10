package org.jumao.bi.entites.trade.register.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 按数据源或类型 group，并取一个数量
 */
public class GroupByVo {

    private String type;
    private String childType1;
    private String typeName;//按具体业务可选则性的查询
    private Long total = 0L;
    private Double valD = 0D;

    private Map<String, String> extraKeyVal = new HashMap<String, String>();//比如 type 为 china 时，pv、uv 等多种数据可存在这里

    public GroupByVo() {
    }

    public GroupByVo(String type) {
        this.type = type;
    }

    public GroupByVo(String type, String typeName, Long total) {
        this.type = type;
        this.typeName = typeName;
        this.total = total;
    }

    public GroupByVo(String type, String typeName, Long total, Double valD) {
        this.type = type;
        this.typeName = typeName;
        this.total = total;
        this.valD = valD;
    }

    public Map<String, String> getExtraKeyVal() {
        return extraKeyVal;
    }

    public void setExtraKeyVal(Map<String, String> extraKeyVal) {
        this.extraKeyVal = extraKeyVal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChildType1() {
        return childType1;
    }

    public void setChildType1(String childType1) {
        this.childType1 = childType1;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Double getValD() {
        return valD;
    }

    public void setValD(Double valD) {
        this.valD = valD;
    }
}
