package org.jumao.bi.service.impl.enjyt;

import org.jumao.bi.entites.trade.operate.LineChart;
import org.jumao.bi.entites.trade.operate.TopLineChart;
import org.jumao.bi.utis.ClimeUtil;
import org.jumao.bi.utis.DesensitizationUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2017/6/14.
 */
public class EnJytCapaHelper {

    /**
     * pv、uv的公用y轴健壮性数据封装方法
     */
    public static LineChart getBasicSeri(int size, String[] xAxisData, Map<String, String> map, LineChart line) {
        BigDecimal[] seriesData = new BigDecimal[size];  //  组装y轴数据，首先根据x轴作为key取值，如果没有值给0
        int i = 0;
        for (String x : xAxisData) {

            if (map.containsKey(x) && !map.get(x).equals("null") && map.get(x) != null) {
                seriesData[i] = new BigDecimal(map.get(x));
            } else {
                seriesData[i] = BigDecimal.ZERO;
            }
            i++;
        }
        line.setSeriesData(seriesData);  //  y轴数据
        return line;
    }


    /**
     * 数据库中杠日期格式的y轴数据通用封装
     */
    public static LineChart getBasicSeri_(int size, String[] xAxisData, Map<String, String> map, LineChart threeCard) {
        BigDecimal[] seriesData = new BigDecimal[size];
        int i = 0;
        for (String x : xAxisData) {
            x = x.substring(0, 4) + "-" + x.substring(4, 6) + "-" + x.substring(6, x.length());
            if (map.containsKey(x) && !map.get(x).equals("--") && map.get(x) != null) {
                seriesData[i] = new BigDecimal(map.get(x));
            } else {
                seriesData[i] = BigDecimal.ZERO;
            }
            i++;
        }
        threeCard.setSeriesData(seriesData);  //  y轴数据
        return threeCard;
    }


    //  公用top10，x、y轴数据封装方法X
    public static TopLineChart getTopXY(Map<String, String> map, TopLineChart topLineChart, String flag) {
        int size = map.size();
        BigDecimal[] xAxisData = new BigDecimal[size];  //  组装x轴数据
        String[] seriesData = new String[size];  //  组装y轴数据
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {  //  循环数据库结果，value值给x轴，key名称给y轴
            xAxisData[i] = new BigDecimal(entry.getValue());
            if (flag.equals("enbaseareaMap")) {  //  根据code获取name
                seriesData[i] = enbaseareaMap.get(entry.getKey());  //  省名称不需要脱敏
            } else if (flag.equals("enlgcompanyMap")) {
                seriesData[i] = DesensitizationUtils.getDesStr(entry.getKey());
            } else if (flag.equals("wu")) {
                seriesData[i] = DesensitizationUtils.getDesStr(entry.getKey());
            }
            i++;
        }

        topLineChart.setxAxisData(xAxisData);  //  x轴数据
        topLineChart.setSeriesData(seriesData);  //  y轴数据
        return topLineChart;
    }

    /**
     * 封装聚运通通用占比map数据处理为echarts list
     */
    public static List<Map<String, String>> getProportion(Map<String, String> map) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put(entry.getKey(), entry.getValue());
            list.add(hashMap);
        }
        return list;
    }

    /**
     * 封装聚运通通用占比map数据处理为echarts list
     */
    public static List<Map<String, String>> getProportion_(Map<String, String> map) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Map<String, String> hashMap = new IdentityHashMap<String, String>();
            hashMap.put("name", entry.getKey());
            hashMap.put("value", entry.getValue());
//            hashMap.put(entry.getKey(), entry.getValue());
            list.add(hashMap);
        }
        return list;
    }

    /**
     * 封装聚运通热力图
     */
    public static List<Map<String, String>> getProportion_area(Map<String, String> reMap, Map<String, String> map_area, Map<String, String> map1) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map.Entry<String, String> entry : reMap.entrySet()) {
            Map<String, String> hashMap = new IdentityHashMap<String, String>();
            hashMap.put("name", entry.getKey());
            hashMap.put("value", entry.getValue());
            String total_area =  map_area.get(entry.getKey());
            hashMap.put("area", total_area);
//            hashMap.put(entry.getKey(), entry.getValue());
            list.add(hashMap);
        }
        return list;
    }

    /**
     * 支付开通地域分布、车辆分析  根据code获取对应名称
     */
    public static Map<String, String> getProvinceName(Map<String, String> map, String flag) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String keyName = "";
            if (flag.equals("enbaseareaMap")) {  //  地图list，name/value
                keyName = enbaseareaMap.get(entry.getKey());
            } else if (flag.equals("enbaseDataOptionMap")) {  //  车辆分析，饼图list，value/name
                keyName = enbaseDataOptionMap.get(entry.getKey());
            }else if (flag.equals("StorageTypeTop")){  //  仓库类型
                keyName = ClimeUtil.getPlatformV(entry.getKey());
            }
            hashMap.put(keyName, entry.getValue());
        }
        return hashMap;
    }

    /**
     * 热力图取名称，并去掉后缀
     */
    public static Map<String, String> getProvinceName_StorageHeat(Map<String, String> map, String flag) {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String keyName = "";
            if (flag.equals("enbaseareaMap")) {  //  地图list，name/value
                keyName = enbaseareaMap.get(entry.getKey());
//                keyName = subName(keyName);
            } else if (flag.equals("enbaseDataOptionMap")) {  //  车辆分析，饼图list，value/name
                keyName = enbaseDataOptionMap.get(entry.getKey());
            }else if (flag.equals("enStorageTypeTop")){  //  仓库类型
                keyName = ClimeUtil.getPlatformV(entry.getKey());
            }
            hashMap.put(keyName, entry.getValue());
        }
        return hashMap;
    }

    public static Map<String, String> washData(Map<String, String> map) {
        Iterator iter = map.keySet().iterator();
        while (iter.hasNext()) {  //  去掉脏数据
            String key = (String) iter.next();
            if (!(key.length() == 6) || key == "null" || key.equals("null")) {
                iter.remove();
            }
        }
        return map;
    }

    public static Map<String, String> washDataValueNull(Map<String, String> map) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (Map.Entry<String,String> entry : map.entrySet()){  //  去掉脏数据
            String key = entry.getKey();
            if (key != null && !key.equals("null")) {
                hashMap.put(key,entry.getValue());
            }
        }
        return hashMap;
    }

    /**
     * 饼图、热力地图 echarts格式处理
     */
    public static List<Map<String, String>> inversionMap(Map<String, String> map){
        /*Map<String, String> hashMap = new IdentityHashMap<String, String>();
        for (Map.Entry<String, String> entry : map.entrySet()){
            hashMap.put("value",entry.getValue());
            hashMap.put("name",entry.getKey());
//            hashMap.put(entry.getValue(),entry.getKey());
        }
        return hashMap;*/
        /*List<Bing> list = new LinkedList<Bing>();
        for (Map.Entry<String, String> entry : map.entrySet()){
            Bing bing = new Bing();
            bing.setValue(entry.getValue());
            bing.setName(entry.getKey());
            list.add(bing);
        }*/
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Map<String, String> hashMap = new LinkedHashMap<String, String>();
            hashMap.put("value", entry.getValue());
            hashMap.put("name", entry.getKey());
            list.add(hashMap);
        }
        return list;
    }

    /**
     * 饼图支付分析支付渠道占比
     */
    public static List<Map<String, String>> inversionMap_(Map<String, String> map){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Map<String, String> hashMap = new LinkedHashMap<String, String>();
            hashMap.put("value", entry.getKey());
            hashMap.put("name", entry.getValue());
            list.add(hashMap);
        }
        return list;
    }

    /**
     * 运力地图echarts格式处理
     */
    public static List<List<Map<String, String>>> plaMap(Map<String, String> map, String flag){
        List<List<Map<String, String>>> reList = new ArrayList<List<Map<String, String>>>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String key1 = key.substring(0, key.length() / 2);
            String key2 = key.substring(key.length() / 2, key.length());

            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            Map<String, String> nameMap = new LinkedHashMap<String, String>();

            Map<String, String> hashMap = new LinkedHashMap<String, String>();
            if (flag.equals("enairportsMap")){
                String name1 = enairportsMap.get(key1);
                String name2 = enairportsMap.get(key2);
                if (name1 == null || name2 == null) continue;
                nameMap.put("name", name1);
                hashMap.put("name", name2);
            }else if (flag.equals("enbaseareaMap")){
                String name1 = enbaseareaMap.get(key1.substring(0,3));
                String name2 = enbaseareaMap.get(key2.substring(0,3));
                if (name1 == null || name2 == null) continue;
//                name1 = subName(name1);
//                name2 = subName(name2);
                nameMap.put("name", name1);
                hashMap.put("name", name2);
            }else if (flag.equals("eninnerportsMap")){
                String name1 = eninnerportsMap.get(key1);
                String name2 = eninnerportsMap.get(key2);
                if (name1 == null || name2 == null) continue;
                nameMap.put("name", name1);
                hashMap.put("name", name2);
            }else if (flag.equals("enlgportsMap")){
                String name1 = enlgportsMap.get(key1);
                String name2 = enlgportsMap.get(key2);
                if (name1 == null || name2 == null) continue;
                name1 = name1.replace("大陆线","");
                name1 = name1.replace("线","");
                name1 = name1.replace("内陆港","");
                name1 = name1.replace("中国港澳台","香港");
                String s = name1.substring(name1.length() - 1, name1.length());
                if (s.equals("港")){name1 = name1.replace("港","");}
                name2 = name2.replace("大陆线","");
                name2 = name2.replace("线","");
                name2 = name2.replace("内陆港","");
                name2 = name2.replace("中国港澳台","香港");
                String s2 = name2.substring(name2.length() - 1, name2.length());
                if (s2.equals("港")){name2 = name2.replace("港","");}

                nameMap.put("name", name1);
                hashMap.put("name", name2);
            }
            hashMap.put("value", entry.getValue());
            list.add(nameMap);
            list.add(hashMap);
            reList.add(list);
        }
        return reList;
    }

    /**
     * 运力地图echarts格式处理
     */
    public static List<List<Map<String, String>>> plaMap_(Map<String, String> map, String flag){
        List<List<Map<String, String>>> reList = new ArrayList<List<Map<String, String>>>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();

            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            Map<String, String> nameMap = new LinkedHashMap<String, String>();

            Map<String, String> hashMap = new LinkedHashMap<String, String>();
            if (flag.equals("enbaseareaMap")){
                String name1 = enbaseareaMap.get(key);
                String name2 = name1;
                if (name1 == null || name2 == null) continue;
//                name1 = subName(name1);
//                name2 = subName(name2);
                nameMap.put("name", name1);
                hashMap.put("name", name2);
            }
            hashMap.put("value", entry.getValue());
            list.add(nameMap);
            list.add(hashMap);
            reList.add(list);
        }
        return reList;
    }

    public static String subName(String name){
        if (name.equals("广西壮族自治区") || name.equals("宁夏回族自治区") || name.equals("西藏自治区")){
            name = name.substring(0,2);
        }else if (name.equals("内蒙古自治区")){
            name = name.substring(0,3);
        }else {
            name = name.substring(0,name.length()-1);
        }
        return name;
    }

    /**
     * 仓库总数、可用面积、总面积
     */
    public static List<Map<String, String>> StorageArea(List<Map<String, Object>> list){
        List<Map<String, String>> list_ = new ArrayList<Map<String, String>>();
        HashMap<String, String> map_ = new HashMap<String, String>();
        Map<String, Object> map = list.get(0);
        Object total_warehouse = map.get("total_warehouse");
        if (total_warehouse != null){map_.put("仓库总数",total_warehouse.toString());}

        Object total_area = map.get("total_area");
        if (total_area != null){map_.put("总面积",total_area.toString());}

        Object available_area = map.get("available_area");
        if (available_area != null){ map_.put("可用面积",available_area.toString());}
        list_.add(map_);
        return list_;
    }

    /*   运力图基础属性缓存   */
    public static Map<String, String> baseareaMap = new HashMap<String, String>();
    public static Map<String, String> enbaseareaMap = new HashMap<String, String>();
    public static Map<String, String> airportsMap = new HashMap<String, String>();
    public static Map<String, String> enairportsMap = new HashMap<String, String>();
    public static Map<String, String> innerportsMap = new HashMap<String, String>();
    public static Map<String, String> eninnerportsMap = new HashMap<String, String>();
    public static Map<String, String> lgportsMap = new HashMap<String, String>();
    public static Map<String, String> enlgportsMap = new HashMap<String, String>();
    public static Map<String, String> lgcompanyMap = new HashMap<String, String>();
    public static Map<String, String> enlgcompanyMap = new HashMap<String, String>();
    public static Map<String, String> baseDataOptionMap = new HashMap<String, String>();
    public static Map<String, String> enbaseDataOptionMap = new HashMap<String, String>();


    /**
     * 运力图，将数据库返回格式处理为echarts格式
     * 8601386013  3065
     * {name:'北京'}, {name:'上海',value:95}
     */
    public static IdentityHashMap<String, Map<String, Integer>> placeTermini(Map<String, String> map, String flag) {
        IdentityHashMap<String, Map<String, Integer>> lists = new IdentityHashMap<String, Map<String, Integer>>();  //  所有运力线
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Map<String, Integer> line = new HashMap<String, Integer>();  //  单条运力线
            String key = entry.getKey();
            int length = key.length();
            String key1 = key.substring(0, length / 2);
            String key2 = key.substring(length / 2, length);
            Integer value = Integer.parseInt(entry.getValue());

            //  判断是那个基础表
            String name1 = "";
            String name2 = "";
            String name = "";
            if (flag.equals("baseareaMap")) {  //  公路,铁路
                name1 = baseareaMap.get(key1);
                if (name1 != null) {
                    name = new String(name1);
                    name2 = baseareaMap.get(key2);
                } else {
                    continue;
                }
            } else if (flag.equals("innerportsMap")) {  //  内河航运
                name1 = innerportsMap.get(key1);
                if (name1 != null) {
                    name = new String(name1);
                    name2 = innerportsMap.get(key2);
                } else {
                    continue;
                }
            } else if (flag.equals("lgportsMap")) { //  海运
                name1 = lgportsMap.get(key1);
                if (name1 != null) {
                    name = new String(name1);
                    name2 = lgportsMap.get(key2);
                } else {
                    continue;
                }
            } else if (flag.equals("airportsMap")) {  //  空运
                name1 = airportsMap.get(key1);
                if (name1 != null) {
                    name = new String(name1);
                    name2 = airportsMap.get(key2);
                } else {
                    continue;
                }
            }
            line.put(name2, value);
            lists.put(name, line);
        }
        return lists;
    }

    /**
     * 拼接运力图返回字符串
     */
    public static String getTrafficString(StringBuffer buff, IdentityHashMap<String, Map<String, Integer>> reMap) {
        String lists;
        for (Map.Entry<String, Map<String, Integer>> entry : reMap.entrySet()) {
            buff.append("[{name:'" + entry.getKey() + "'}, {name:'");
            Map<String, Integer> value = entry.getValue();
            Set entries = value.entrySet();
            if (entries != null) {
                Iterator iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry entryV = (Map.Entry) iterator.next();
                    String key = (String) entryV.getKey();
                    Integer valueI = (Integer) entryV.getValue();
                    buff.append(key + "',value:" + valueI + "}],");
                }
            }
        }
        lists = buff.toString();
        lists = lists.substring(0, lists.length() - 1) + "]";
        return lists;
    }


}
