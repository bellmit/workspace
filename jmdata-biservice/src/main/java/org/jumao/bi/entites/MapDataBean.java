package org.jumao.bi.entites;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

public class MapDataBean implements Serializable {

    private static final long serialVersionUID = 528221897287474085L;
    private String            code;                                                   // 关区代码
    private String            name;                                                  // 关区名称
    private BigDecimal        value            = NumberUtils.createBigDecimal("100");
    private BigDecimal        count            = BigDecimal.ZERO;                    // 订单数
    private BigDecimal        amount           = BigDecimal.ZERO;                    // 货值
    private BigDecimal        partner          = BigDecimal.ZERO;                    // 拍档
    private BigDecimal        customer         = BigDecimal.ZERO;                    // 客户

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPartner() {
        return partner;
    }

    public void setPartner(BigDecimal partner) {
        this.partner = partner;
    }

    public BigDecimal getCustomer() {
        return customer;
    }

    public void setCustomer(BigDecimal customer) {
        this.customer = customer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
