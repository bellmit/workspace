package org.jumao.bi.service.impl.enjyt;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.enjyt.EnJytPayDao;
import org.jumao.bi.dao.jyt.JytPayDao;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.jyt.pay.ListResponse;
import org.jumao.bi.entites.jyt.pay.OperateXyCharts;
import org.jumao.bi.entites.jyt.pay.platResponse;
import org.jumao.bi.entites.trade.operate.LineChart;
import org.jumao.bi.entites.trade.operate.TopLineChart;
import org.jumao.bi.entites.trade.operate.TotalResponse;
import org.jumao.bi.service.enjyt.EnJytCapaSvc;
import org.jumao.bi.service.impl.trade.operate.OperationSvcHelper;
import org.jumao.bi.utis.LogUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
@Service
public class EnJytCapaSvcImpl implements EnJytCapaSvc, InitializingBean, ServletContextAware {

    @Autowired
    private EnJytPayDao jytPayDao;
    private Logger logger = Logger.getLogger(this.getClass());

    public Map<String, Map<String, String>> cache() {
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        Map<String, String> enbaseareaMap = jytPayDao.cacheBaseArea();  //  公路-231,铁路-232  仓储-235
        Map<String, String> enairportsMap = jytPayDao.cacheLg_air_ports();  //  空运-237
//        Map<String, String> eninnerportsMap = jytPayDao.cacheLg_inner_ports();  //  Air Freight 航空货运
        Map<String, String> enlgportsMap = jytPayDao.cacheLg_ports();  //  海运
        map.put("enbaseareaMap", enbaseareaMap);
        map.put("enairportsMap", enairportsMap);
//        map.put("eninnerportsMap", eninnerportsMap);
        map.put("enlgportsMap", enlgportsMap);
        return map;
    }

    public Response trafficAll(String startDate, String endDate) {
        LogUtils.writeLogs(logger, "get trafficAll ");
        platResponse response = new platResponse();

        try {
            Map<String, String> map = jytPayDao.airFreight(startDate, endDate);  //  公路
            List<List<Map<String, String>>> listHighway = EnJytCapaHelper.plaMap(map, "enbaseareaMap");
            Map<String, String> map1 = jytPayDao.railTransport(startDate, endDate);  //  铁路
            List<List<Map<String, String>>> listRailway = EnJytCapaHelper.plaMap(map1, "enbaseareaMap");
            Map<String, String> map2 = jytPayDao.landTransport(startDate, endDate);  //  查询并组装运力排行
            List<List<Map<String, String>>> listNavigation = EnJytCapaHelper.plaMap(map2, "enbaseareaMap");
            Map<String, String> map3 = jytPayDao.warehouseTransport(startDate, endDate);  //  空运
            List<List<Map<String, String>>> listAirTransport = EnJytCapaHelper.plaMap_(map3, "enbaseareaMap");

            List<List<Map<String, String>>> listAll = new ArrayList<>();
            listAll.addAll(listHighway);
            listAll.addAll(listRailway);
            listAll.addAll(listNavigation);
            listAll.addAll(listAirTransport);
            response.setProportion(listAll);
            response.setUnit("次");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end trafficAll ");
        return Response.ok().entity(response).build();
    }

    /**
     * AIR FREIGHT
     */
    public Response airFreight(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get airFreight ");
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
            Map<String, String> map = jytPayDao.airFreight(startDate, endDate);  //  查询并组装运力排行
            response.setProportion(EnJytCapaHelper.plaMap(map,"enbaseareaMap"));
            response.setUnit("次");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end airFreight ");
        return Response.ok().entity(response).build();
    }

    /**
     * RAIL TRANSPORT
     */
    public Response railTransport(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get RAILTRANSPORT ");
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
            Map<String, String> map = jytPayDao.railTransport(startDate, endDate);  //  查询并组装运力排行
            response.setProportion(EnJytCapaHelper.plaMap(map,"enbaseareaMap"));
            response.setUnit("次");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end RAILTRANSPORT ");
        return Response.ok().entity(response).build();
    }

    /**
     * land Transport
     */
    public Response landTransport(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get landTransport ");
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
            Map<String, String> map = jytPayDao.landTransport(startDate, endDate);  //  查询并组装运力排行
//            response.setProportion(EnJytCapaHelper.plaMap(map,"innerportsMap"));
            response.setProportion(EnJytCapaHelper.plaMap(map,"enbaseareaMap"));
            response.setUnit("次");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end landTransport ");
        return Response.ok().entity(response).build();
    }

    /**
     * warehouse Transport
     */
    public Response warehouseTransport(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get warehouseTransport ");
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
            Map<String, String> map = jytPayDao.warehouseTransport(startDate, endDate);  //  查询并组装运力排行
            response.setProportion(EnJytCapaHelper.plaMap_(map,"enbaseareaMap"));
//            response.setProportion(EnJytCapaHelper.plaMap(map,"baseareaMap"));
            response.setUnit("次");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end warehouseTransport ");
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
            EnJytCapaHelper.getTopXY(map, response, "wu");
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
            Map<String, String> reMap = EnJytCapaHelper.getProvinceName(map, "baseDataOptionMap");  //  获取名称
            Map<String, String> data = EnJytCapaHelper.washDataValueNull(reMap);  //  去除脏数据
//            Map<String, String> mapdata = EnJytCapaHelper.inversionMap(data);  //  倒置map key/value  对应echarts
//            List<Map<String, String>> list = EnJytCapaHelper.getProportion(mapdata);  //  得到echarts  list格式
            List<Map<String, String>> list = EnJytCapaHelper.inversionMap(data);
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
            Map<String, String> map1 = EnJytCapaHelper.washData(map);  //  去掉脏数据
//            Map<String, String> reMap = EnJytCapaHelper.getProvinceName(map1, "baseareaMap");  //  获取名称
            Map<String, String> reMap = EnJytCapaHelper.getProvinceName_StorageHeat(map1, "enbaseareaMap");  //  获取名称
            Map<String, String> areaMap = EnJytCapaHelper.getProvinceName_StorageHeat(map_area, "enbaseareaMap");  //  area获取名称
            response.setProportion(EnJytCapaHelper.getProportion_area(reMap, areaMap, map1));
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
            List<Map<String, String>> list = EnJytCapaHelper.StorageArea(list1);
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
            EnJytCapaHelper.getTopXY(map, response, "enbaseareaMap");
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
            Map<String, String> reMap = EnJytCapaHelper.getProvinceName(map, "StorageTypeTop");  //  获取名称
            Map<String, String> data = EnJytCapaHelper.washDataValueNull(reMap);  //  去除脏数据
            List<Map<String, String>> list = EnJytCapaHelper.inversionMap(data);
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
            EnJytCapaHelper.getTopXY(map, response, "enlgcompanyMap");
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
        response.setPayOpen(EnJytCapaHelper.getBasicSeri_(size, xAxisData, map, ItemTotal));
    }

    private void setUv(String startDate, String endDate, int size, String[] xAxisData, OperateXyCharts response, String platformId) {
        Map<String, String> map = jytPayDao.payOneUv(platformId, startDate, endDate);

        LineChart ItemTotal = new LineChart();
        ItemTotal.setTitle("独立访客UV趋势情况");
        ItemTotal.setSeriesName("独立访客UV");
        ItemTotal.setUnit("个");
        response.setUv(EnJytCapaHelper.getBasicSeri(size, xAxisData, map, ItemTotal));
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
        response.setPv(EnJytCapaHelper.getBasicSeri(size, xAxisData, map, ItemTotal));
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
            EnJytCapaHelper.enbaseareaMap = map.get("enbaseareaMap");
            EnJytCapaHelper.enairportsMap = map.get("enairportsMap");
            EnJytCapaHelper.eninnerportsMap = map.get("eninnerportsMap");
            EnJytCapaHelper.enlgportsMap = map.get("enlgportsMap");

            Map<String, String> enlgcompanyMap = TransportTopCache();
            EnJytCapaHelper.enlgcompanyMap = enlgcompanyMap;

            Map<String, String> enbaseDataOptionMap = CarAnalysisCache();
            EnJytCapaHelper.enbaseDataOptionMap = enbaseDataOptionMap;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("JytPaymentSvc.cache error系统初始化查询所有用户出错");
        }
    }


}
