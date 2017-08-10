package org.jumao.bi.utis;

import java.util.HashMap;
import java.util.Map;

public class PlatFormUtil {
	
	private static final Map<String, String> platformMap = new HashMap<String, String>();
	public static final Map<String, String> platformNameMap = new HashMap<String, String>();

	public static final String Jumore_Master = "1001";
	public static final String Chemical_Industry = "1002";
	public static final String Coloured = "1003";
	public static final String Coal = "1007";
	public static final String Steel = "1008";
	public static final String Mineral = "1009";
	public static final String Agriculture = "1010";
	public static final String Industrial = "1011";
	public static final String Consumable = "1012";
	public static final String Mechanics = "1013";
	public static final String Food = "1014";
	public static final String Oil = "1022";

	public static final String EN_Finance = "101501";
    
    static {
    	PlatFormUtil.platformMap.put("100700", "1");//煤炭
    	PlatFormUtil.platformMap.put("100300", "2");//有色
    	PlatFormUtil.platformMap.put("100900", "3");//矿产
    	PlatFormUtil.platformMap.put("101000", "4");//农产 品
    	PlatFormUtil.platformMap.put("100800", "5");//钢铁
    	PlatFormUtil.platformMap.put("101200", "6");//消费品
    	PlatFormUtil.platformMap.put("101400", "7");//食品
    	PlatFormUtil.platformMap.put("101100", "8");//工业品
    	PlatFormUtil.platformMap.put("101300", "9");//机械
    	PlatFormUtil.platformMap.put("100200", "10");//化工
    	PlatFormUtil.platformMap.put("102200", "11");//石油

		PlatFormUtil.platformMap.put("1", "煤炭");
		PlatFormUtil.platformMap.put("2", "有色");
		PlatFormUtil.platformMap.put("3", "矿产");
		PlatFormUtil.platformMap.put("4", "农产品");
		PlatFormUtil.platformMap.put("5", "钢铁");
		PlatFormUtil.platformMap.put("6", "消费品");
		PlatFormUtil.platformMap.put("7", "食品");
		PlatFormUtil.platformMap.put("8", "工业品");
		PlatFormUtil.platformMap.put("9", "机械");
		PlatFormUtil.platformMap.put("10", "化工");
		PlatFormUtil.platformMap.put("11", "石油");

		PlatFormUtil.platformMap.put("1007", "煤炭");
		PlatFormUtil.platformMap.put("1003", "有色");
		PlatFormUtil.platformMap.put("1009", "矿产");
		PlatFormUtil.platformMap.put("1010", "农产品");
		PlatFormUtil.platformMap.put("1008", "钢铁");
		PlatFormUtil.platformMap.put("1012", "消费品");
		PlatFormUtil.platformMap.put("1014", "食品");
		PlatFormUtil.platformMap.put("1011", "工业品");
		PlatFormUtil.platformMap.put("1013", "机械");
		PlatFormUtil.platformMap.put("1002", "化工");
		PlatFormUtil.platformMap.put("1022", "石油");
		PlatFormUtil.platformMap.put("1001", "聚贸总站");


		platformNameMap.put("1000", "认证中心");
		platformNameMap.put(Jumore_Master, "聚贸总站");
		platformNameMap.put(Chemical_Industry, "化工");
		platformNameMap.put(Coloured, "有色");
		platformNameMap.put("1004", "聚运通");
		platformNameMap.put("1005", "企业中心");
		platformNameMap.put("1006", "企业服务中心");
		platformNameMap.put(Coal, "煤炭");
		platformNameMap.put(Steel, "钢材");
		platformNameMap.put(Mineral, "矿产");
		platformNameMap.put(Agriculture, "农产品");
		platformNameMap.put(Industrial, "工业品");
		platformNameMap.put(Consumable, "消费品");
		platformNameMap.put(Mechanics, "机械");
		platformNameMap.put(Food, "食品");
		platformNameMap.put("1015", "聚融通");
		platformNameMap.put("1016", "大数聚");
		platformNameMap.put("1017", "聚认证");
		platformNameMap.put("1018", "聚智能");
		platformNameMap.put("1019", "聚博通");
		platformNameMap.put("1020", "聚贸通");
		platformNameMap.put("1021", "支付结算中心");
		platformNameMap.put(Oil, "石油");
		platformNameMap.put("1023", "国家馆");
		platformNameMap.put("1024", "省馆");
		platformNameMap.put("1025", "品牌馆");
		platformNameMap.put("1026", "云智库");
		platformNameMap.put("1027", "聚贸资讯");
		platformNameMap.put("1028", "个人支付");
		platformNameMap.put("1029", "聚文化");
		platformNameMap.put("1030", "云短信");
		platformNameMap.put("1031", "一带一路");
		platformNameMap.put("1032", "IM");
		platformNameMap.put("1033", "聚期权");
		platformNameMap.put("1034", "品质馆");
		platformNameMap.put("1035", "TUV馆");
		platformNameMap.put("1080", "聚保险");
    }

	public static String getPlatformV(String key){
    	return platformMap.get(key);
    }
}
