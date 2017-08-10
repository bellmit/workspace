package org.jumao.bi.dao.jyt;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface JytPayDao {


    /**
     * 支付分析PV趋势图
     * @return
     */
    public Map<String, String> payOnePv(String platformId, String startDate, String endDate);

    /**
     * 支付分析PV趋势图
     * @return
     */
    public Map<String, String> payOneUv(String platformId, String startDate, String endDate);

    /**
     * 支付开通数
     */
    Map<String,String> PayOpen(String startDate, String endDate);

    /**
     * 支付开通历史累计值
     */
    Map<String,Object> PayTotal();

    /**
     * 支付渠道占比
     */
    Map<String, String> channelProportion();

    /**
     * 支付分析支付开通地域分布
     */
    Map<String,String> payOpenClime(String startDate, String endDate);

    /**
     * 支付分析支付开通地域分布top
     */
    Map<String,String> payOpenClimeTop(String startDate, String endDate);

    /**
     * 运力排行
     */
    Map<String,String> TransportTop(String startDate, String endDate);



    /********************************************/
    /**
     * 缓存基础属性表
     */
    Map<String,String> cacheBaseArea();

    /**
     * 缓存基础属性表
     */
    Map<String,String> cacheLg_inner_ports();

    /**
     * 缓存基础属性表
     */
    Map<String,String> cacheLg_ports();

    /**
     * 缓存基础属性表
     */
    Map<String,String> cacheLg_air_ports();

    /**
     * 车辆分析
     */
    Map<String, String> CarAnalysis(String startDate, String endDate);

    /**
        运力图公路
     */
    Map<String, String> trafficHighway(String startDate, String endDate);

    Map<String,String> trafficRailway(String startDate, String endDate);

    Map<String,String> trafficInlandNavigation(String startDate, String endDate);

    Map<String,String> trafficOceanShipping(String startDate, String endDate);

    Map<String,String> trafficAirTransport(String startDate, String endDate);

    Map<String,String> TransportTopCache();

    Map<String,String> CarAnalysisCache();
    Map<String,String> CarAnalysisCache_();

    Map<String,String> StorageHeat(String startDate, String endDate);
    Map<String,String> StorageHeat_area(String startDate, String endDate);

    List<Map<String, Object>> StorageArea(String startDate, String endDate);

    Map<String,String> StorageAreaTop(String startDate, String endDate);

    Map<String,String> StorageCompanyTop(String startDate, String endDate);

    Map<String,String> StorageTypeTop(String startDate, String endDate);

    Map<String,String> trafficStorage(String startDate, String endDate);
}
