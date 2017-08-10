package org.jumao.bi.constant;

import java.util.HashMap;
import java.util.Map;

public class MapConstants {

    public static Map<String, Long>   CHINA_MAP      = new HashMap<String, Long>();
    static {
        CHINA_MAP.put("北京", 0L);
        CHINA_MAP.put("天津", 0L);
        CHINA_MAP.put("上海", 0L);
        CHINA_MAP.put("重庆", 0L);
        CHINA_MAP.put("河北", 0L);
        CHINA_MAP.put("河南", 0L);
        CHINA_MAP.put("云南", 0L);
        CHINA_MAP.put("辽宁", 0L);
        CHINA_MAP.put("湖南", 0L);
        CHINA_MAP.put("安徽", 0L);
        CHINA_MAP.put("山东", 0L);
        CHINA_MAP.put("新疆", 0L);
        CHINA_MAP.put("浙江", 0L);
        CHINA_MAP.put("江西", 0L);
        CHINA_MAP.put("湖北", 0L);
        CHINA_MAP.put("广西", 0L);
        CHINA_MAP.put("甘肃", 0L);
        CHINA_MAP.put("山西", 0L);
        CHINA_MAP.put("内蒙古", 0L);
        CHINA_MAP.put("陕西", 0L);
        CHINA_MAP.put("吉林", 0L);
        CHINA_MAP.put("福建", 0L);
        CHINA_MAP.put("贵州", 0L);
        CHINA_MAP.put("广东", 0L);
        CHINA_MAP.put("青海", 0L);
        CHINA_MAP.put("西藏", 0L);
        CHINA_MAP.put("四川", 0L);
        CHINA_MAP.put("宁夏", 0L);
        CHINA_MAP.put("海南", 0L);
        CHINA_MAP.put("台湾", 0L);
        CHINA_MAP.put("香港", 0L);
        CHINA_MAP.put("澳门", 0L);
        CHINA_MAP.put("黑龙江", 0L);
        CHINA_MAP.put("江苏", 0L);
        CHINA_MAP.put("南海诸岛", 0L);
    }

    public static Map<String, String> STAR_LEVEL_MAP = new HashMap<String, String>();
    static {
        STAR_LEVEL_MAP.put("441", "一星");
        STAR_LEVEL_MAP.put("442", "二星");
        STAR_LEVEL_MAP.put("443", "三星");
        STAR_LEVEL_MAP.put("444", "四星");
        STAR_LEVEL_MAP.put("445", "五星");
    }

    public static Map<String, String> DEAL_MAP       = new HashMap<String, String>();
    static {
        DEAL_MAP.put("shipping_type_10", "买家自提");
        DEAL_MAP.put("shipping_type_20", "卖家配送");
        DEAL_MAP.put("clearing_type_10", "定金支付");
        DEAL_MAP.put("clearing_type_20", "担保支付");
        DEAL_MAP.put("clearing_type_30", "全款支付");
        DEAL_MAP.put("payment_type_10", "平台支付");
        DEAL_MAP.put("payment_type_20", "线下支付");
        DEAL_MAP.put("contract_type_10", "电子合同");
        DEAL_MAP.put("contract_type_20", "线下合同");
        DEAL_MAP.put("contract_type_30", "无合同");
        DEAL_MAP.put("pay_type_0", "线下支付");
        DEAL_MAP.put("pay_type_100", "直接支付");
        DEAL_MAP.put("pay_type_200", "担保支付");
        DEAL_MAP.put("pay_type_300", "订金交易-订金");
        DEAL_MAP.put("pay_type_301", "订金交易-尾款");
        DEAL_MAP.put("line_type_231", "公路");
        DEAL_MAP.put("line_type_232", "铁路");
        DEAL_MAP.put("line_type_233", "内河航运");
        DEAL_MAP.put("line_type_234", "海运");
        DEAL_MAP.put("line_type_235", "仓储");
        DEAL_MAP.put("line_type_236", "联运");
        DEAL_MAP.put("line_type_237", "空运");
        DEAL_MAP.put("platform_1001", "聚贸总站");
        DEAL_MAP.put("platform_1002", "聚贸化工");
        DEAL_MAP.put("platform_1003", "聚贸有色");
        DEAL_MAP.put("platform_1004", "聚运通");
        DEAL_MAP.put("platform_1007", "聚贸煤炭");
        DEAL_MAP.put("platform_1008", "聚贸钢铁");
        DEAL_MAP.put("platform_1009", "聚贸矿产");
        DEAL_MAP.put("platform_1010", "聚贸农产品");
        DEAL_MAP.put("platform_1011", "聚贸工业品");
        DEAL_MAP.put("platform_1012", "聚贸消费品");
        DEAL_MAP.put("platform_1013", "聚贸机械");
        DEAL_MAP.put("platform_1014", "聚贸食品");
        DEAL_MAP.put("platform_1015", "聚融通");
        DEAL_MAP.put("platform_1020", "聚贸通");
        DEAL_MAP.put("platform_1022", "聚贸石油");
        DEAL_MAP.put("order_type_1", "线上订单");
        DEAL_MAP.put("order_type_2", "线下订单");
    }
}
