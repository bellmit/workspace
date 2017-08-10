package org.jumao.bi.service.impl.jyt.payment;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.jyt.JytPayDao;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.jyt.pay.*;
import org.jumao.bi.entites.trade.operate.*;
import org.jumao.bi.service.impl.trade.operate.OperationSvcHelper;
import org.jumao.bi.service.jyt.payment.JytPaymentSvc;
import org.jumao.bi.utis.LogUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/6/14.
 */
@Service
public class JytPaymentSvcImpl implements JytPaymentSvc, InitializingBean, ServletContextAware {

    @Autowired
    private JytPayDao jytPayDao;
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 运营分析支付开通历史累计图1 主逻辑
     */
    public Response payOne(String platformId, String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get payOne for from " + _startDate + " to " + _endDate);
        OperateXyCharts response = new OperateXyCharts();  //  服务返回总对象

        String[] xAxisData = new String[0];  //  获得x轴数据
        try {
            xAxisData = OperationSvcHelper.setXAxis(_startDate, _endDate);
        } catch (Exception e) {  //  时间格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }
        int size = xAxisData.length;  //  y轴的个数

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {  /* 组装图表总返回体 */
            setPv(_startDate, _endDate, size, xAxisData, response, platformId);  //  查询并组装pv
            setUv(_startDate, _endDate, size, xAxisData, response, platformId);  //  查询并组装uv
            setPayOpen(startDate, endDate, size, xAxisData, response);  //  查询并组装支付开通数
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        OperationSvcHelper.handleXAxis(xAxisData);
        response.setxAxisData(xAxisData);

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end payOne for from " + startDate + " to " + endDate);
        return Response.ok().entity(response).build();
    }

    /**
     * 运营分析支付开通历史累计数
     */
    public Response payTotal() {
        LogUtils.writeLogs(logger, "get payTotal ");
        TotalResponse response = new TotalResponse();  //  服务返回总对象

        try {  /* 组装图表总返回体 */
            Map<String, Object> map = jytPayDao.PayTotal();
            response.setData(map);  //  查询并组装支付开通历史累计数
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end payTotal ");
        return Response.ok().entity(response).build();
    }

    /**
     * 支付分析支付渠道占比
     */
    public Response channelProportion() {
        LogUtils.writeLogs(logger, "get channelProportion ");
        ListResponse response = new ListResponse();  //  服务返回总对象

        try {  /* 组装返回体 */
            Map<String, String> map = jytPayDao.channelProportion();//  查询并组装发布商品数行业占比
//            List<Map<String, String>> list = JytPayHelper.getProportion(map);
            response.setProportion(JytPayHelper.inversionMap_(map));
            response.setUnit("个");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end channelProportion ");
        return Response.ok().entity(response).build();
    }

    /**
     * 支付分析支付开通地域分布
     */
    public Response payOpenClime(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get payOpenClime ");
        ListResponse response = new ListResponse();  //  服务返回总对象

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {  /* 组装图表总返回体 */
            Map<String, String> map = jytPayDao.payOpenClime(startDate, endDate);
            Map<String, String> map1 = JytPayHelper.washData(map);  //  去掉脏数据
            Map<String, String> reMap = JytPayHelper.getProvinceName(map1, "baseareaMap");  //  获取名称
//            List<Map<String, String>> list = JytPayHelper.getProportion(reMap);
            List<Map<String, String>> list = JytPayHelper.inversionMap(reMap);
            response.setProportion(list);
            response.setUnit("个");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end payOpenClime ");
        return Response.ok().entity(response).build();
    }

    /**
     * 支付分析支付开通地域分布top
     */
    public Response payOpenClimeTop(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get payOpenClimeTop ");
        TopLineChart response = new TopLineChart();  //  服务返回总对象

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {
            Map<String, String> map = jytPayDao.payOpenClimeTop(startDate, endDate);  //  查询并组装支付开通地域分布top
            Map<String, String> reMap = JytPayHelper.washData(map);
            JytPayHelper.getTopXY(reMap, response, "baseareaMap");
            response.setSeriesName("支付开通");
            response.setUnit("个");
            response.setTitle("支付开通Top10");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end payOpenClimeTop ");
        return Response.ok().entity(response).build();
    }

    public Map<String, Map<String, String>> cache() {
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        Map<String, String> baseareaMap = jytPayDao.cacheBaseArea();  //  公路-231,铁路-232  仓储-235
        Map<String, String> airportsMap = jytPayDao.cacheLg_air_ports();  //  空运-237
        Map<String, String> innerportsMap = jytPayDao.cacheLg_inner_ports();  //  内河航运-233
        Map<String, String> lgportsMap = jytPayDao.cacheLg_ports();  //  海运
        map.put("baseareaMap", baseareaMap);
        map.put("airportsMap", airportsMap);
        map.put("innerportsMap", innerportsMap);
        map.put("lgportsMap", lgportsMap);
        return map;
    }

    public Response trafficAll(String startDate, String endDate) {
        LogUtils.writeLogs(logger, "get trafficAll ");
        platResponse response = new platResponse();

        try {
            Map<String, String> map = jytPayDao.trafficHighway(startDate, endDate);  //  公路
            List<List<Map<String, String>>> listHighway = JytPayHelper.plaMap(map, "baseareaMap");
            Map<String, String> map1 = jytPayDao.trafficRailway(startDate, endDate);  //  铁路
            List<List<Map<String, String>>> listRailway = JytPayHelper.plaMap(map1, "baseareaMap");
            Map<String, String> map2 = jytPayDao.trafficInlandNavigation(startDate, endDate);  //  查询并组装运力排行
            List<List<Map<String, String>>> listNavigation = JytPayHelper.plaMap(map2, "innerportsMap");
            Map<String, String> map3 = jytPayDao.trafficAirTransport(startDate, endDate);  //  空运
            List<List<Map<String, String>>> listAirTransport = JytPayHelper.plaMap(map3, "airportsMap");

            List<List<Map<String, String>>> listAll = new ArrayList<List<Map<String, String>>>();
            listAll.addAll(listHighway);
            listAll.addAll(listRailway);
            listAll.addAll(listNavigation);
            listAll.addAll(listAirTransport);
            response.setProportion(listAll);
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end trafficAll ");
        return Response.ok().entity(response).build();
    }

    /**
     * 运力图公路
     */
    public Response trafficHighway(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get trafficHighway ");
        platResponse response = new platResponse();

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {
            Map<String, String> map = jytPayDao.trafficHighway(startDate, endDate);  //  查询并组装运力排行
            response.setProportion(JytPayHelper.plaMap(map,"baseareaMap"));
            response.setUnit("次");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end trafficAirTransport ");
        return Response.ok().entity(response).build();
    }

    /**
     * 运力图铁路
     */
    public Response trafficRailway(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get trafficRailway ");
        platResponse response = new platResponse();

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {
            Map<String, String> map = jytPayDao.trafficRailway(startDate, endDate);  //  查询并组装运力排行
            response.setProportion(JytPayHelper.plaMap(map,"baseareaMap"));
            response.setUnit("次");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end trafficAirTransport ");
        return Response.ok().entity(response).build();
    }

    /**
     * 运力图内河航运
     */
    public Response trafficInlandNavigation(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get trafficInlandNavigation ");
        platResponse response = new platResponse();

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {
            Map<String, String> map = jytPayDao.trafficInlandNavigation(startDate, endDate);  //  查询并组装运力排行
            response.setProportion(JytPayHelper.plaMap(map,"innerportsMap"));
            response.setUnit("次");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end trafficAirTransport ");
        return Response.ok().entity(response).build();
    }

    /**
     * 运力图海运
     */
    public Response trafficOceanShipping(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get trafficOceanShipping ");
        platResponse response = new platResponse();

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {
            Map<String, String> map = jytPayDao.trafficOceanShipping(startDate, endDate);  //  查询并组装运力排行
            response.setProportion(JytPayHelper.plaMap(map,"lgportsMap"));
            response.setUnit("次");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end trafficAirTransport ");
        return Response.ok().entity(response).build();
    }

    /**
     * 运力图空运
     */
    public Response trafficAirTransport(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get trafficAirTransport ");
        platResponse response = new platResponse();

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {
            Map<String, String> map = jytPayDao.trafficAirTransport(startDate, endDate);  //  查询并组装运力排行
            response.setProportion(JytPayHelper.plaMap(map,"airportsMap"));
            response.setUnit("次");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end trafficAirTransport ");
        return Response.ok().entity(response).build();
    }

    /**
     * 运力图仓库
     */
    public Response trafficStorage(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get trafficStorage ");
        platResponse response = new platResponse();

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {
            Map<String, String> map = jytPayDao.trafficStorage(startDate, endDate);  //  查询并组装运力排行
            response.setProportion(JytPayHelper.plaMap_(map,"baseareaMap"));
            response.setUnit("次");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end trafficStorage ");
        return Response.ok().entity(response).build();
    }

    public Map<String, String> TransportTopCache() {
        Map<String, String> lgcompanyMap = jytPayDao.TransportTopCache();  //  公路-231,铁路-232  仓储-235
        return lgcompanyMap;
    }

    /**
     * 运力排行
     */
    public Response TransportTop(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get TransportTop ");
        TopLineChart response = new TopLineChart();  //  服务返回总对象

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {
            Map<String, String> map = jytPayDao.TransportTop(startDate, endDate);  //  查询并组装运力排行
            JytPayHelper.getTopXY(map, response, "lgcompanyMap");
            response.setTitle("运力排行Top5");
            response.setUnit("个");
            response.setSeriesName("公司名称");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end TransportTop ");
        return Response.ok().entity(response).build();
    }

    /**
     * 车辆分析  饼图list  value/name
     */
    public Response CarAnalysis(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get CarAnalysis ");
        ListResponse response = new ListResponse();  //  服务返回总对象

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {  /* 组装返回体 */
            Map<String, String> map = jytPayDao.CarAnalysis(startDate, endDate);
            Map<String, String> reMap = JytPayHelper.getProvinceName(map, "baseDataOptionMap");  //  获取名称
            Map<String, String> data = JytPayHelper.washDataValueNull(reMap);  //  去除脏数据
//            Map<String, String> mapdata = JytPayHelper.inversionMap(data);  //  倒置map key/value  对应echarts
//            List<Map<String, String>> list = JytPayHelper.getProportion(mapdata);  //  得到echarts  list格式
            List<Map<String, String>> list = JytPayHelper.inversionMap(data);
            response.setProportion(list);
            response.setUnit("辆");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end CarAnalysis ");
        return Response.ok().entity(response).build();
    }

    public Map<String, String> CarAnalysisCache() {
        Map<String, String> lgcompanyMap = jytPayDao.CarAnalysisCache();
        Map<String, String> lgcompanyMap_ = jytPayDao.CarAnalysisCache_();
        lgcompanyMap.putAll(lgcompanyMap_);
        return lgcompanyMap;
    }

    public Response StorageHeat(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get StorageHeat ");
        ListResponse response = new ListResponse();  //  服务返回总对象

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {  /* 组装图表总返回体 */
            Map<String, String> map = jytPayDao.StorageHeat(startDate, endDate);
            Map<String, String> map_area = jytPayDao.StorageHeat_area(startDate, endDate);
            Map<String, String> map1 = JytPayHelper.washData(map);  //  去掉脏数据
//            Map<String, String> reMap = JytPayHelper.getProvinceName(map1, "baseareaMap");  //  获取名称
            Map<String, String> reMap = JytPayHelper.getProvinceName_StorageHeat(map1, "baseareaMap");  //  获取名称
            Map<String, String> areaMap = JytPayHelper.getProvinceName_StorageHeat(map_area, "baseareaMap");  //  area获取名称
            response.setProportion(JytPayHelper.getProportion_area(reMap, areaMap, map1));
            response.setUnit("个");
//            response.setUnit_area("平方米");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end StorageHeat ");
        return Response.ok().entity(response).build();
    }

    public Response StorageArea(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get StorageArea ");
        ListResponse response = new ListResponse();  //  服务返回总对象

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {  /* 组装图表总返回体 */
            List<Map<String, Object>> list1 = jytPayDao.StorageArea(startDate, endDate);
            List<Map<String, String>> list = JytPayHelper.StorageArea(list1);
            response.setProportion(list);
            response.setUnit("平方米");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end StorageArea ");
        return Response.ok().entity(response).build();
    }

    /**
     * 仓库面积top5
     */
    public Response StorageAreaTop(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get StorageAreaTop ");
        TopLineChart response = new TopLineChart();  //  服务返回总对象

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {
            Map<String, String> map = jytPayDao.StorageAreaTop(startDate, endDate);
            JytPayHelper.getTopXY(map, response, "baseareaMap");
            response.setUnit("平方米");
            response.setTitle("仓库面积top5");
            response.setSeriesName("仓库面积");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end StorageAreaTop ");
        return Response.ok().entity(response).build();
    }

    public Response StorageTypeTop(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get StorageTypeTop ");
        ListResponse response = new ListResponse();  //  服务返回总对象

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {  /* 组装返回体 */
            Map<String, String> map = jytPayDao.StorageTypeTop(startDate, endDate);
            Map<String, String> reMap = JytPayHelper.getProvinceName(map, "StorageTypeTop");  //  获取名称
            Map<String, String> data = JytPayHelper.washDataValueNull(reMap);  //  去除脏数据
            List<Map<String, String>> list = JytPayHelper.inversionMap(data);
            response.setProportion(list);
            response.setUnit("平方米");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end StorageTypeTop ");
        return Response.ok().entity(response).build();
    }

    /**
     * 企业排名top5
     */
    public Response StorageCompanyTop(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get StorageCompanyTop ");
        TopLineChart response = new TopLineChart();  //  服务返回总对象

        String startDate = "";  /* 处理时间格式 */
        String endDate = "";
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_startDate));
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(_endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {
            Map<String, String> map = jytPayDao.StorageCompanyTop(startDate, endDate);
            JytPayHelper.getTopXY(map, response, "lgcompanyMap");
            response.setUnit("平方米");
            response.setTitle("仓库面积top5");
            response.setSeriesName("仓库面积");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end StorageCompanyTop ");
        return Response.ok().entity(response).build();
    }

    private void setPayOpen(String startDate, String endDate, int size, String[] xAxisData, OperateXyCharts response) {
        Map<String, String> map = jytPayDao.PayOpen(startDate, endDate);

        LineChart ItemTotal = new LineChart();
        ItemTotal.setTitle("支付开通数趋势情况");
        ItemTotal.setSeriesName("支付开通数");
        ItemTotal.setUnit("个");
        response.setPayOpen(JytPayHelper.getBasicSeri_(size, xAxisData, map, ItemTotal));
    }

    private void setUv(String startDate, String endDate, int size, String[] xAxisData, OperateXyCharts response, String platformId) {
        Map<String, String> map = jytPayDao.payOneUv(platformId, startDate, endDate);

        LineChart ItemTotal = new LineChart();
        ItemTotal.setTitle("独立访客UV趋势情况");
        ItemTotal.setSeriesName("独立访客UV");
        ItemTotal.setUnit("个");
        response.setUv(JytPayHelper.getBasicSeri(size, xAxisData, map, ItemTotal));
    }

    /**
     * 封装行对象
     */
    public void setPv(String startDate, String endDate, int size, String[] xAxisData, OperateXyCharts response, String platformId) {
        Map<String, String> map = jytPayDao.payOnePv(platformId, startDate, endDate);

        LineChart ItemTotal = new LineChart();
        ItemTotal.setTitle("浏览量PV趋势情况");
        ItemTotal.setSeriesName("浏览量PV");
        ItemTotal.setUnit("次");
        response.setPv(JytPayHelper.getBasicSeri(size, xAxisData, map, ItemTotal));
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("null", "123");
        for (Map.Entry<String, String> entry : map.entrySet()) {  //  去掉脏数据
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            if (!(entry.getKey().length() == 5)) {
                map.remove(entry.getKey());
            }
        }
        System.out.println();
    }


    public void afterPropertiesSet() throws Exception {
    }

    /**
     * 随tomcat启动加载运力图基础属性
     */
    public void setServletContext(ServletContext servletContext) {
        try {
            Map<String, Map<String, String>> map = cache();
            JytPayHelper.baseareaMap = map.get("baseareaMap");
            JytPayHelper.airportsMap = map.get("airportsMap");
            JytPayHelper.innerportsMap = map.get("innerportsMap");
            JytPayHelper.lgportsMap = map.get("lgportsMap");

            Map<String, String> lgcompanyMap = TransportTopCache();
            JytPayHelper.lgcompanyMap = lgcompanyMap;

            Map<String, String> baseDataOptionMap = CarAnalysisCache();
            JytPayHelper.baseDataOptionMap = baseDataOptionMap;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("JytPaymentSvc.cache error系统初始化查询所有用户出错");
        }
    }


}
