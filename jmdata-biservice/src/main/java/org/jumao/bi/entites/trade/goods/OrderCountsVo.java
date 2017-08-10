package org.jumao.bi.entites.trade.goods;

public class OrderCountsVo {

    private String industry;
    private long orderCounts;

    public OrderCountsVo(String industry, long orderCounts) {
        this.industry = industry;
        this.orderCounts = orderCounts;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public long getOrderCounts() {
        return orderCounts;
    }

    public void setOrderCounts(long orderCounts) {
        this.orderCounts = orderCounts;
    }
}
