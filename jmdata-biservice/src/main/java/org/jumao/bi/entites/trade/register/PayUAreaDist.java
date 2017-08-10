package org.jumao.bi.entites.trade.register;

/**
 * Created by kartty on 2017/4/28.
 */
public class PayUAreaDist {

    private Long total;
    private String areaName;
    private Long addressProvince;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(Long addressProvince) {
        this.addressProvince = addressProvince;
    }
}
