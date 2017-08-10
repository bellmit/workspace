package com.jumore.jmbi.enums;

/** 
* 缓存类别枚举
* @author: panweiqiang
* @since: 2016年7月5日  下午6:21:42
* @history:
*/
public enum CacheTypeEnum {

    // 数据选项码表：包括：客户来源、线上客户来源、客户类型、客户等级、关联省馆、企业会员等级、经营范围、审核状态、公司类型等
    BASE_DATA_OPTION("base_data_option"),
    // 分级数据字典表
    BASE_DATA_LEVEL("base_data_level"),
    // 地区表
    AREA("area"),
    // 商品类别表
    GOODS_CATEGORY("base_goods_category"),
    // 港口表
    PORTS("lg_ports"),
    // 内河港口表
    INNERPORTS("lg_inner_ports"),
    // 待加入竞拍的需求id列表，如果15分钟内没有供应商报价，则需求会推送到前台进行竞拍
    WAIT_TO_BID_NEEDS_IDS("wait_to_bid_needs_ids");

    /**
     * 缓存的key值
     */
    private String cacheType;

    /**
     * 表示一个的缓存key
     * @param key
     */
    CacheTypeEnum(String cacheType) {
        this.cacheType = cacheType;
    }

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

}
