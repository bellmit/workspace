package org.jumao.bi.service.impl.trade.operate;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.trade.operate.IOperationOverviewDao;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.trade.operate.*;
import org.jumao.bi.service.trade.operate.IOperationSvc;
import org.jumao.bi.utis.DesensitizationUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.MapUtil;
import org.jumao.bi.utis.PlatFormUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
public class OperationSvcImpl implements IOperationSvc {

    @Autowired
    private IOperationOverviewDao operationOverviewDao;
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 运营总览顶部6个统计图  http://localhost:8080/v1/operate/total
     */
    public Response getTotal(String platform) {
        LogUtils.writeLogs(logger, "get getTotal  start.");

        TotalResponse response = new TotalResponse();
        String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        platform = platform.substring(0, 4);

        try {
            List<Map<String, Object>> topTotal = operationOverviewDao.getTopTotal(platform, nowDate);
            OperationSvcHelper.buildTotalChart(nowDate, topTotal, response);
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end getTotal .");
        return Response.ok().entity(response).build();
    }

    /**
     * 查询pv、uv等基本指标
     */
    public Response getBasic(String platformId, String startDate, String endDate) {
        LogUtils.writeLogs(logger, "get getBasic for from " + startDate + " to " + endDate);

        OperateResponse response = new OperateResponse();  //  服务返回总对象
        String platform = platformId.substring(0, 4);
        String platform_ = PlatFormUtil.getPlatformV(platform+"00");

        String _startDate = "";  /* 处理时间格式 */
        String _endDate = "";
        try {
            _startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(startDate));
            _endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        String[] xAxisData = new String[0];  //  获得x轴数据
        try {
            xAxisData = OperationSvcHelper.setXAxis(startDate, endDate);
        } catch (Exception e) {  //  时间格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }
        int size = xAxisData.length;  //  y轴的个数

        try {  /* 组装返回体 */
            setPvUvIpAvetimeavgPagesExit(startDate, endDate, size, xAxisData, response, platformId); //  组装并设置pv、uv等6个基本指标，platformId直传
            setThreeCard(_startDate, _endDate, size, xAxisData, response);  //  查询并组装三证审核实体
            setOpenStoreTotal(_startDate, _endDate, size, xAxisData, response);  //  查询并组装开通店铺数实体
            setItemTotal(_startDate, _endDate, size, xAxisData, platform_, response);  //  查询并组装发布商品数实体
            setOrderTotal(_startDate, _endDate, size, xAxisData, platform_, response);  //  查询并组装订单数实体
            setPayTotal(_startDate, _endDate, size, xAxisData, platform_, response);  //  查询并组装付款笔数实体  截取直传平台id
            setTranTotal(_startDate, _endDate, size, xAxisData, platform_, response);  //  查询并组装金额实体
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        OperationSvcHelper.handleXAxis(xAxisData);
        response.setxAxisData(xAxisData);

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end getBasic for from " + startDate + " to " + endDate);
        return Response.ok().entity(response).build();
    }

    /**
     * 查询买家转化率等指标
     */
    public Response getBuyer(String platform, String startDate, String endDate) {
        LogUtils.writeLogs(logger, "get getBuyer for from " + startDate + " to " + endDate);
        BuyerConvResponse response = new BuyerConvResponse();  //  服务返回总对象

        String industry_id = PlatFormUtil.getPlatformV(platform);

        String _startDate = "";  /* 处理时间格式 */
        String _endDate = "";
        try {
            _startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(startDate));
            _endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        String[] xAxisData = new String[0];  //  获得x轴数据
        try {
            xAxisData = OperationSvcHelper.setXAxis(startDate, endDate);
        } catch (Exception e) {  //  时间格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }
        int size = xAxisData.length;  //  y轴的个数


        try {  /* 组装返回体 */
            setAuthConv(_startDate, _endDate, size, xAxisData, response);  //  查询并组装认证转化率实体
            setReleaseDemandConv(_startDate, _endDate, size, xAxisData, industry_id, response);  //  查询并组装发布需求转化率实体
            setOrderConv(_startDate, _endDate, size, xAxisData, industry_id, response);  //  查询并组装下单转化率实体
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        OperationSvcHelper.handleXAxis(xAxisData);
        response.setxAxisData(xAxisData);

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end getBuyer for from " + startDate + " to " + endDate);
        return Response.ok().entity(response).build();
    }

    /**
     * 查询卖家转化率等指标
     */
    public Response getSeller(String platform, String startDate, String endDate) {
        LogUtils.writeLogs(logger, "get getSeller for from " + startDate + " to " + endDate);
        SellerConvResponse response = new SellerConvResponse();  //  服务返回总对象

        String _startDate = "";  /* 处理时间格式 */
        String _endDate = "";
        try {
            _startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(startDate));
            _endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        String[] xAxisData = new String[0];  //  获得x轴数据
        try {
            xAxisData = OperationSvcHelper.setXAxis(startDate, endDate);
        } catch (Exception e) {  //  时间格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }
        int size = xAxisData.length;  //  y轴的个数

        try {  /* 组装返回体 */
            setSellerAuthConv(_startDate, _endDate, size, xAxisData, response);  //  查询并组装卖家认证转化率实体
            setOpenStoreConv(_startDate, _endDate, size, xAxisData, response);  //  查询并组装卖家店铺开通转化率实体
            setItemConv(_startDate, _endDate, size, xAxisData, platform, response);  //  查询并组装发布商品转化率实体
            setDealConv(_startDate, _endDate, size, xAxisData, platform, response);  //  查询并组装成交转化率实体
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        OperationSvcHelper.handleXAxis(xAxisData);
        response.setxAxisData(xAxisData);

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end getSeller for from " + startDate + " to " + endDate);
        return Response.ok().entity(response).build();
    }

    /**
     * 查询关联公司top5、子账户开通top5等指标
     */
    public Response getTop(String startDate, String endDate) {
        LogUtils.writeLogs(logger, "get getTop for from " + startDate + " to " + endDate);
        TopCharts response = new TopCharts();  //  服务返回总对象

        String _startDate = "";
        String _endDate = "";
        try {
            _startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(startDate));
            _endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }
        try {
            //  组装返回体
            setAffiliateCompanyChart(_startDate, _endDate, response);  //  查询并组装关联公司top5实体
            setSubAccountOpeningChart(_startDate, _endDate, response);  //  查询并组装子账号开通top5实体
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end getTop for from " + startDate + " to " + endDate);
        return Response.ok().entity(response).build();
    }

    private void setSubAccountOpeningChart(String startDate, String endDate, TopCharts response) {
        //  获得查询子账号开通top5 x、y轴数据
        String SQL = "select company_name as time_ , count(DISTINCT user_id)  as nums   " +
                "from jmbi_trade_center_user where  user_kind<>3 and create_time between '" + startDate + "' AND '" + endDate + "' " + // delete_flag = 0 and
                "and  company_id in (select company_id from jmbi_trade_center_user where user_kind=3)  " +
                " and create_time between '" + startDate + "' AND '" + endDate + "' " +
                "group by company_id, company_name  order by nums desc  " +
                "limit  5;";
        Map<String, String> map = operationOverviewDao.getForMap(SQL);
        map = MapUtil.sortByValueDesc(map);

        //  组装 affiliateCompanyChart  查询关联公司top5行对象
        TopLineChart subAccountOpeningChart = new TopLineChart();
        subAccountOpeningChart.setTitle("子账号开通top5排名趋势");
        subAccountOpeningChart.setSeriesName("子账号开通");
        subAccountOpeningChart.setUnit("个");
        response.setSubAccountOpening(getTopY(map, subAccountOpeningChart));  //  封装行对象关联公司top5的x、y轴数据
    }

    private void setAffiliateCompanyChart(String startDate, String endDate, TopCharts response) {
        //  获得查询关联公司top5 x、y轴数据
        String SQL = "SELECT c.comp_name as time_ , COUNT(DISTINCT s.sub_comp_id) as nums  " +
                "FROM jmbi_trade_sub_comp s, jmbi_trade_company c  " +
                "WHERE   c.create_time between '" + startDate + "' AND '" + endDate + "' " +  //  s.delete_flag = 0 and c.delete_flag = 0 and
                " AND c.comp_id=s.comp_id   GROUP BY c.comp_id, c.comp_name order by nums desc  " +
                "limit  5;";
        Map<String, String> map = operationOverviewDao.getForMap(SQL);
        map = MapUtil.sortByValueDesc(map);

        //  组装 affiliateCompanyChart  查询关联公司top5行对象
        TopLineChart affiliateCompanyChart = new TopLineChart();
        affiliateCompanyChart.setTitle("关联公司top5排名趋势");
        affiliateCompanyChart.setSeriesName("关联公司");
        affiliateCompanyChart.setUnit("个");
        response.setAffiliateCompanyChart(getTopY(map, affiliateCompanyChart));  //  封装行对象关联公司top5的x、y轴数据
    }

    //  公用top5，x、y轴数据封装方法
    private TopLineChart getTopY(Map<String, String> map, TopLineChart affiliateCompanyChart) {
        int size = map.size();
        BigDecimal[] xAxisData = new BigDecimal[size];  //  组装x轴数据
        String[] seriesData = new String[size];  //  组装y轴数据
        int i = 0;
        /*if (map.size() == 0) {  //  没有数据时置空置0，否则显示错误
            for (i = 0; i < 5; i++) {
                xAxisData[i] = BigDecimal.ZERO;
                seriesData[i] = "";
            }
        } else {*/
        for (Map.Entry<String, String> entry : map.entrySet()) {  //  有5个数据的时候
            if (!entry.getKey().equals("null") && entry.getKey() != null) {  //  如果有值
                xAxisData[i] = new BigDecimal(entry.getValue());
                seriesData[i] = DesensitizationUtils.getDesStr(entry.getKey());  //  脱敏
            } else {  //  无值置空
                xAxisData[i] = BigDecimal.ZERO;
                seriesData[i] = "";
            }
            i++;
        }
            /*for (i = 0; i < 5; i++) {  //  有数据但不满5个的时候，置空补全
                if (seriesData[i] == null || seriesData[i].equals("") || seriesData[i].equals("null")) {
                    xAxisData[i] = BigDecimal.ZERO;
                    seriesData[i] = "";
                }
            }
        }*/

        affiliateCompanyChart.setxAxisData(xAxisData);  //  x轴数据
        affiliateCompanyChart.setSeriesData(seriesData);  //  y轴数据
        return affiliateCompanyChart;
    }

    private void setDealConv(String _startDate, String _endDate, int size, String[] xAxisData, String platform, SellerConvResponse response) {
        String industry_id = PlatFormUtil.getPlatformV(platform);
        //  获得查询卖家成交转化率y轴分子数据
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer_ = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(c.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(c.create_time, 10) ");
        }
        String sel = buffer.toString();
        buffer.append(" AS time_,count( DISTINCT c.comp_id) as nums  " +
                "FROM jmbi_trade_goods g,jmbi_trade_company c,jmbi_trade_order o ,jmbi_trade_order_detail d WHERE  ");
        if (industry_id != null) {
            buffer.append(" g.industry_id = " + industry_id + " and ");
        }
        buffer.append(" c.valid_status=2 AND c.shop_status=1  " +   //  g.delete_flag = 0 and c.delete_flag = 0 and o.delete_flag = 0 and d.delete_flag = 0 and
                "  AND o.order_id=d.order_id   " +
                "  AND d.goods_id=g.goods_id " +
                "  AND g.company_id=c.comp_id " +
                "  AND o.order_state IN (20,30,40,50,70) " +
                "AND c.create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "group by time_ order by time_;");
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  获得查询卖家成交转化率y轴分母数据
        buffer_.append(sel).append(" AS time_,count( DISTINCT c.comp_id) as nums   " +
                " FROM jmbi_trade_goods g,jmbi_trade_company c WHERE ");
        if (industry_id != null) {
            buffer_.append(" g.industry_id = " + industry_id + " and ");
        }
        buffer_.append("  g.company_id=c.comp_id AND c.valid_status=2 AND c.shop_status=1  " +  //  g.delete_flag = 0 and c.delete_flag = 0 and
                "AND c.create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "group by time_ order by time_;");
        String SQL_ = buffer_.toString();
        Map<String, String> map_ = operationOverviewDao.getForMap(SQL_);

        //  组装 dealConv  查询成交转化率行对象
        LineChart dealConv = new LineChart();
        dealConv.setTitle("成交转化率趋势情况");
        dealConv.setSeriesName("成交转化率");
        dealConv.setUnit("%");
        response.setDealConv(getConv(size, xAxisData, map, map_, dealConv));
    }

    private void setItemConv(String _startDate, String _endDate, int size, String[] xAxisData, String platform, SellerConvResponse response) {
        String industry_id = PlatFormUtil.getPlatformV(platform);
        //  获得查询卖家发布商品转化率y轴分子数据
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer_ = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(c.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(c.create_time, 10) ");
        }
        String sel = buffer.toString();
        buffer.append(" AS time_,count( DISTINCT c.comp_id) as nums  " +
                "FROM jmbi_trade_goods g,jmbi_trade_company c  " +
                "WHERE ");
        if (industry_id != null) {
            buffer.append(" g.industry_id = " + industry_id + " and ");
        }
        buffer.append("  g.company_id=c.comp_id  " +  //  c.delete_flag = 0 and g.delete_flag = 0 and
                "AND c.valid_status=2   AND c.shop_status=1 AND c.create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "group by time_ order by time_;");
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  获得查询卖家发布商品转化率y轴分母数据
        buffer_.append(sel).append(" AS time_,count( DISTINCT c.comp_id) as nums  " +
                "FROM jmbi_trade_company c " +
                "WHERE   c.shop_status=1 AND c.valid_status=2 AND  c.create_time between '" + _startDate + "' AND '" + _endDate + "' " +  //  c.delete_flag = 0 and
                "group by time_ order by time_;");
        String SQL_ = buffer_.toString();
        Map<String, String> map_ = operationOverviewDao.getForMap(SQL_);

        //  组装 openStoreConv  查询发布商品转化率行对象
        LineChart itemConv = new LineChart();
        itemConv.setTitle("发布商品转化率趋势情况");
        itemConv.setSeriesName("发布商品转化率");
        itemConv.setUnit("%");
        response.setItemConv(getConv(size, xAxisData, map, map_, itemConv));
    }

    private void setOpenStoreConv(String _startDate, String _endDate, int size, String[] xAxisData, SellerConvResponse response) {
        //  获得查询卖家店铺开通转化率y轴分子数据
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer_ = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(c.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(c.create_time, 10) ");
        }
        String sel = buffer.toString();
        buffer.append(" AS time_,count( DISTINCT c.comp_id) as nums FROM jmbi_trade_company c  " +
                "WHERE  c.shop_status=1 AND c.valid_status=2 AND create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "group by time_ order by time_;");  //  delete_flag = 0 and
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  获得查询卖家店铺开通转化率y轴分母数据
        buffer_.append(sel).append(" AS time_,count( DISTINCT c.comp_id) as nums from jmbi_trade_company c " +
                "where  c.valid_status=2 and create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "group by time_ order by time_;");  //  delete_flag = 0 and
        String SQL_ = buffer_.toString();
        Map<String, String> map_ = operationOverviewDao.getForMap(SQL_);

        //  组装 openStoreConv  查询店铺开通转化率行对象
        LineChart openStoreConv = new LineChart();
        openStoreConv.setTitle("店铺开通转化率趋势情况");
        openStoreConv.setSeriesName("店铺开通转化率");
        openStoreConv.setUnit("%");
        response.setOpenStoreConv(getConv(size, xAxisData, map, map_, openStoreConv));
    }

    private void setSellerAuthConv(String _startDate, String _endDate, int size, String[] xAxisData, SellerConvResponse response) {
        //  获得查询卖家认证转化率y轴分子数据
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer_ = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(c.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(c.create_time, 10) ");
        }
        String sel = buffer.toString();
        buffer.append(" AS time_, count( DISTINCT c.comp_id)  as nums   from jmbi_trade_company c  " +
                "where   c.valid_status=2 and create_time between '" + _startDate + "' AND '" + _endDate + "' " + // delete_flag = 0  and
                "group by time_ order by time_;");
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  获得查询认证转化率y轴分母数据
        buffer_.append(sel).append(" AS time_, count( DISTINCT c.comp_id) as nums  FROM jmbi_trade_company c  " +
                "WHERE  create_time between '" + _startDate + "' AND '" + _endDate + "' " +  //  delete_flag = 0  and
                "group by time_ order by time_;");
        String SQL_ = buffer_.toString();
        Map<String, String> map_ = operationOverviewDao.getForMap(SQL_);

        //  组装 authConv  查询认证转化率行对象
        LineChart sellerAuthConv = new LineChart();
        sellerAuthConv.setTitle("认证转化率趋势情况");
        sellerAuthConv.setSeriesName("认证转化率");
        sellerAuthConv.setUnit("%");
        response.setAuthConv(getConv(size, xAxisData, map, map_, sellerAuthConv));
    }

    private void setOrderConv(String _startDate, String _endDate, int size, String[] xAxisData, String industry_id, BuyerConvResponse response) {
        //  获得查询下单转化率y轴数据
        StringBuffer buffer = new StringBuffer();
        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(c.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(c.create_time, 10) ");
        }

        buffer.append(" AS time_, COUNT( DISTINCT c.comp_id)  as nums  " +
                "FROM   jmbi_trade_order t, jmbi_trade_company c, jmbi_trade_center_user u, jmbi_trade_demand_main m " +
                "WHERE ");
        if (industry_id != null) {
            buffer.append(" t.industry_id = " + industry_id + " and ");
        }
        buffer.append("t.order_state IN (20, 30, 40, 50, 70) " +
                //"and t.delete_flag = 0  and c.delete_flag = 0  and u.delete_flag = 0  and m.delete_flag = 0 " +
                "AND t.member_id = u.user_id AND c.comp_id = u.company_id and c.valid_status=2 and m.company_id=c.comp_id and m.demand_code=1 " +
                "and c.create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "group by time_ order by time_;");
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  获得查询下单转化率y轴分母数据
        StringBuffer buffer_ = new StringBuffer();
        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer_.append("select STRLEFT(m.create_time, 13) ");
        } else {
            buffer_.append("select STRLEFT(m.create_time, 10) ");
        }
        buffer_.append(" AS time_, COUNT( DISTINCT comp_id)  as nums  " +
                "from jmbi_trade_company m  where  valid_status=2 and   create_time between '" + _startDate + "' AND '" + _endDate + " '"+  //  delete_flag = 0  and
                "group by time_ order by time_;");
        String SQL_ = buffer_.toString();
        Map<String, String> map_ = operationOverviewDao.getForMap(SQL_);

        //  组装 orderConv  查询下单转化率行对象
        LineChart orderConv = new LineChart();
        orderConv.setTitle("下单转化率趋势情况");
        orderConv.setSeriesName("下单转化率");
        orderConv.setUnit("%");
        response.setOrderConv(getConv(size, xAxisData, map, map_, orderConv));
    }

    private void setReleaseDemandConv(String _startDate, String _endDate, int size, String[] xAxisData, String industry_id, BuyerConvResponse response) {
        //  获得查询发布需求转化率y轴分子数据
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer_ = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(c.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(c.create_time, 10) ");
        }
        String sel = buffer.toString();
        buffer.append(" AS time_, COUNT( DISTINCT c.comp_id)  as nums " +
                " FROM jmbi_trade_demand_main m, jmbi_trade_company c " +
                " where   c.valid_status=2 and c.create_time between '" + _startDate + "' AND '" + _endDate + "' ");
        if (industry_id != null) {
            buffer.append(" and m.industry_code = " + industry_id);
        }
        buffer.append(" and m.demand_code=1 and m.company_id=c.comp_id " +
                " group by time_  order by time_;");  //   c.delete_flag = 0 and m.delete_flag = 0 and
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  获得查询发布需求转化率y轴分母数据
        buffer_ = buffer_.append(sel).append(" AS time_, COUNT( DISTINCT c.comp_id)  as nums from jmbi_trade_company c " +
                "where valid_status=2 and create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "group by time_  order by time_;");  //   delete_flag = 0 and
        String SQL_ = buffer_.toString();
        Map<String, String> map_ = operationOverviewDao.getForMap(SQL_);

        //  组装 releaseDemandConv  查询发布需求转化率行对象
        LineChart releaseDemandConv = new LineChart();
        releaseDemandConv.setTitle("发布需求转化率趋势情况");
        releaseDemandConv.setSeriesName("发布需求转化率");
        releaseDemandConv.setUnit("%");
        response.setReleaseDemandConv(getConv(size, xAxisData, map, map_, releaseDemandConv));
    }

    private void setAuthConv(String _startDate, String _endDate, int size, String[] xAxisData, BuyerConvResponse response) {
        //  获得查询认证转化率y轴分子数据
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer_ = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(create_time, 10) ");
        }
        String sel = buffer.toString();
        buffer.append(" AS time_, COUNT( DISTINCT c.comp_id)  as nums " +
                "FROM jmbi_trade_company c WHERE c.valid_status=2 AND create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "GROUP BY time_ order by time_;");  //   c.delete_flag = 0 and
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  获得查询认证转化率y轴分母数据
        buffer_ = buffer_.append(sel).append(" AS time_,COUNT( DISTINCT c.comp_id) as nums " +
                "FROM jmbi_trade_company c  WHERE create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "GROUP BY time_ order by time_;");  //   c.delete_flag = 0 and
        String SQL_ = buffer_.toString();
        Map<String, String> map_ = operationOverviewDao.getForMap(SQL_);

        //  组装 authConv  查询认证转化率行对象
        LineChart authConv = new LineChart();
        authConv.setTitle("认证转化率趋势情况");
        authConv.setSeriesName("认证转化率");
        authConv.setUnit("%");
        response.setAuthConv(getConv(size, xAxisData, map, map_, authConv));
    }


    /**
     * 封装通用转换率y轴数据，返回设置到行对象中
     *
     * @param size
     * @param xAxisData
     * @param map
     * @param map_
     * @param lineChart
     * @return
     */
    private LineChart getConv(int size, String[] xAxisData, Map<String, String> map, Map<String, String> map_, LineChart lineChart) {
        BigDecimal[] seriesData = new BigDecimal[size];
        int i = 0;
        for (String x : xAxisData) {
            x = x.substring(0, 4) + "-" + x.substring(4, 6) + "-" + x.substring(6, x.length());
            BigDecimal member = new BigDecimal("0");  //  分子
            BigDecimal denomin = new BigDecimal("1");  //  分母
            Double authConvInt = Double.valueOf(0);  //  转化率
            if (map.containsKey(x) && map.get(x) != null && !map.get(x).equals("")) {
                member = new BigDecimal(Double.valueOf(map.get(x)));
            } else {
                seriesData[i] = BigDecimal.ZERO;
                i++;
                continue;
            }
            if (map_.containsKey(x) && map_.get(x) != null && !map_.get(x).equals("")) {
                denomin = new BigDecimal(Double.valueOf(map_.get(x)));
            }
            authConvInt = member.divide(denomin, 4, BigDecimal.ROUND_HALF_UP).doubleValue();  //  BigDecimal计算
            String s = String.valueOf(authConvInt * 100);  //  转化为百分比
            if (s.length() > 5) {  //  保留两位小数
                s = String.valueOf(s).substring(0, 5);
            }
            seriesData[i] = new BigDecimal(s);
            i++;
        }
        seriesData = OperationSvcHelper.setCenv(seriesData);
        lineChart.setSeriesData(seriesData);  //  y轴数据
        return lineChart;
    }


    private LineChart getBasicSeri(int size, String[] xAxisData, Map<String, String> map, LineChart threeCard) {
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

    /**
     * 查询并组装三证审核实体
     */
    private void setThreeCard(String _startDate, String _endDate, int size, String[] xAxisData, OperateResponse response) {
        //  获得查询三证审核y轴数据
        StringBuffer buffer = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(s.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(s.create_time, 10) ");
        }
        buffer.append(" as time_,count(DISTINCT s.comp_id) as nums " +
                "from  jmbi_trade_company s  where  s.valid_status=2 and  create_time between '" + _startDate + "' and  '" + _endDate + "' " + //  delete_flag = 0 and
                "group by  time_ order by time_;");

        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  组装 threeCard  查询三证审核行对象
        LineChart threeCard = new LineChart();
        threeCard.setTitle("三证审核趋势情况");
        threeCard.setSeriesName("三证审核");
        threeCard.setUnit("个");
        response.setThreeCard(getBasicSeri(size, xAxisData, map, threeCard));
    }


    /**
     * 查询并组装开通店铺数实体
     *
     * @param _startDate 开始日期
     * @param _endDate   结束日期
     * @param size       x轴长度
     * @param xAxisData  x轴数据
     * @return 开通店铺数实体
     */
    private void setOpenStoreTotal(String _startDate, String _endDate, int size, String[] xAxisData, OperateResponse response) {
        //  获得开通店铺数y轴数据
        StringBuffer buffer = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(s.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(s.create_time, 10) ");
        }
        buffer.append(" as time_,count(distinct s.shop_id) as nums " +
                "from  jmbi_trade_shop_operate s  where operate_type=0 and create_time between '" + _startDate + "' and  '" + _endDate + "'  " +  //   delete_flag = 0 and
                "group by  time_ order by time_;");
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  组装OpenStoreTotal  开通店铺数行对象
        LineChart openStoreTotal = new LineChart();
        openStoreTotal.setTitle("开通店铺数趋势情况");
        openStoreTotal.setSeriesName("开通店铺数");
        openStoreTotal.setUnit("个");
        response.setOpenStoreTotal(getBasicSeri(size, xAxisData, map, openStoreTotal));
    }

    /**
     * 查询并组装发布商品数实体
     *
     * @param _startDate 开始日期
     * @param _endDate   结束日期
     * @param size       x轴长度
     * @param xAxisData  x轴数据
     * @return 发布商品数实体
     */
    private void setItemTotal(String _startDate, String _endDate, int size, String[] xAxisData, String industry_id, OperateResponse response) {
        //  获得发布商品数y轴数据
        StringBuffer buffer = new StringBuffer();
        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(s.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(s.create_time, 10) ");
        }
        buffer.append(" as time_,count(DISTINCT s.goods_id) as nums  from  jmbi_trade_goods s  where");
        if (industry_id != null) {
            buffer.append(" industry_id = " + industry_id + " and ");
        }
        buffer.append(" create_time between '" + _startDate + "' and  '" + _endDate + "' " +  //  delete_flag = 0 and
                "group by  time_ order by time_;");
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  组装ItemTotal  发布商品数行对象
        LineChart ItemTotal = new LineChart();
        ItemTotal.setTitle("发布商品数趋势情况");
        ItemTotal.setSeriesName("发布商品数");
        ItemTotal.setUnit("件");
        response.setItemTotal(getBasicSeri(size, xAxisData, map, ItemTotal));
    }

    /**
     * 查询并组装查询订单数实体
     *
     * @param _startDate 开始日期
     * @param _endDate   结束日期
     * @param size       x轴长度
     * @param xAxisData  x轴数据
     * @return 订单数实体
     */
    private void setOrderTotal(String _startDate, String _endDate, int size, String[] xAxisData, String industry_id, OperateResponse response) {
        //  获得查询订单数y轴数据
        StringBuffer buffer = new StringBuffer();
        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(s.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(s.create_time, 10) ");
        }
        buffer.append(" as time_,count(distinct s.order_id) as nums  from  " +
                "jmbi_trade_order s  where ");
        if (industry_id != null) {
            buffer.append(" industry_id = " + industry_id + " and ");
        }
        buffer.append(" create_time between '" + _startDate + "' and  '" + _endDate + "' " +  //  delete_flag = 0 and
                "group by  time_ order by time_;");
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  组装orderTotal  查询订单数行对象
        LineChart orderTotal = new LineChart();
        orderTotal.setTitle("订单数趋势情况");
        orderTotal.setSeriesName("订单数");
        orderTotal.setUnit("笔");
        response.setOrderTotal(getBasicSeri(size, xAxisData, map, orderTotal));
    }

    /**
     * 查询并组装查询付款笔数实体
     *
     * @param _startDate 开始日期
     * @param _endDate   结束日期
     * @param size       x轴长度
     * @param xAxisData  x轴数据
     * @return 付款笔数实体
     */
    private void setPayTotal(String _startDate, String _endDate, int size, String[] xAxisData, String industry_id, OperateResponse response) {  //  industry_id 1020
        //  获得查询付款笔数y轴数据
        StringBuffer buffer = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(c.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(c.create_time, 10) ");
        }
        buffer.append(" as time_,count(DISTINCT c.payment_id) as nums  from  jmbi_trade_payment c,jmbi_trade_order o " +
                "where c.order_id=o.order_id and c.pay_flag=1 AND ");
        if (industry_id != null) {
            buffer.append(" o.industry_id = " +industry_id+" and ");
        }
        buffer.append("c.create_time between '" + _startDate + "' and  '" + _endDate + "' " +
                "group by  time_ order by time_");
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  组装payTotal  付款笔数行对象
        LineChart payTotal = new LineChart();
        payTotal.setTitle("付款笔数趋势情况");
        payTotal.setSeriesName("付款笔数");
        payTotal.setUnit("笔");
        response.setPayTotal(getBasicSeri(size, xAxisData, map, payTotal));
    }

    /**
     * 查询并组装查询金额实体
     *
     * @param _startDate 开始日期
     * @param _endDate   结束日期
     * @param size       x轴长度
     * @param xAxisData  x轴数据
     * @return 金额实体
     */
    private void setTranTotal(String _startDate, String _endDate, int size, String[] xAxisData, String industry_id, OperateResponse response) {
        //  获得查询金额y轴数据
        StringBuffer buffer = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(c.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(c.create_time, 10) ");
        }
        buffer.append(" as time_,sum(c.pay_money) as nums  from  jmbi_trade_payment c,jmbi_trade_order o  " +
                " where c.order_id=o.order_id  and c.pay_flag=1 and ");
        if (industry_id != null) {
            buffer.append(" o.industry_id = " +industry_id+" and ");
        }
        buffer.append(" c.create_time between '" + _startDate + "' and  '" + _endDate + "' " +
                " group by  time_ order by time_; ");
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);
        Map<String, String> map_ = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = NumberUtils.createBigDecimal(entry.getValue()).divide(BigDecimal.valueOf(10000), 2, BigDecimal.ROUND_HALF_EVEN).toString();
            if (value.equals("0.00")) value = "0";
            map_.put(entry.getKey(),value);
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        //  组装tranTotal  查询金额行对象
        LineChart tranTotal = new LineChart();
        tranTotal.setTitle("金额趋势情况");
        tranTotal.setSeriesName("金额");
        tranTotal.setUnit("万元");
        response.setTranTotal(getBasicSeri(size, xAxisData, map_, tranTotal));
    }

    /**
     * 查询并设置浏览量等指标到返回体中
     *
     * @return
     */
    private Map<String, Object> setPvUvIpAvetimeavgPagesExit(String startDate, String endDate, int size, String[] xAxisData, OperateResponse response, String platformid) {
        //  获得查询浏览量y轴数据
        String SQL = "select create_date as time_,sum(cast(b.pv as int)) as pv,sum(cast(b.uv as int))as uv " +
//                ",sum(cast(b.ips as int)) as ips,sum(cast(b.stay_time as int))as ave,sum(cast(b.avg_visit_pages as decimal(10,2))) as avg " +
                "from jmbi_baidu_daily_stat b where platform_id = '" + platformid + "' and  b.create_date between '" + startDate + "' and  '" + endDate + "' " +
                "group by  time_ order by time_;";
        List<Map<String, String>> listMap = operationOverviewDao.getForListMap(SQL);

        LineChart pv = getPv(size, xAxisData, listMap);
        LineChart uv = getUv(size, xAxisData, listMap);
//        LineChart ips = getIps(size, xAxisData, listMap);
//        LineChart ave = getAve(size, xAxisData, listMap);
//        LineChart avg = getAvg(size, xAxisData, listMap);
//        LineChart exit = getExit(startDate, endDate, size, xAxisData, platformid);
        response.setPv(pv);
        response.setUv(uv);
//        response.setIps(ips);
//        response.setAve(ave);
//        response.setAvg(avg);
//        response.setExit(exit);

        Map<String, Object> resuMap = new HashMap<String, Object>();
        return resuMap;
    }

    private LineChart getExit(String startDate, String endDate, int size, String[] xAxisData, String platformid) {
        String SQL = "select create_date as time_,exits as nums " +
                "from jmbi_baidu_daily_stat b where platform_id = '" + platformid + "' and   b.create_date between '" + startDate + "' and  '" + endDate + "' ";
        Map<String, String> exitMap = operationOverviewDao.getForMap(SQL);
        //  组装 exit  跳出率行对象
        LineChart exit = new LineChart();
        exit.setTitle("跳出率趋势情况");
        exit.setSeriesName("跳出率");
        exit.setUnit("%");
        BigDecimal[] seriesData = new BigDecimal[size];  //  组装y轴数据，首先根据x轴作为key取值，如果没有值给0
        int i = 0;
        for (String x : xAxisData) {

            if (exitMap.containsKey(x) && !exitMap.get(x).equals("--") && exitMap.get(x) != null) {
                seriesData[i] = new BigDecimal(exitMap.get(x));
            } else {
                seriesData[i] = BigDecimal.ZERO;
            }
            i++;
        }
        exit.setSeriesData(seriesData);  //  y轴数据
        return exit;
    }

    private LineChart getAvg(int size, String[] xAxisData, List<Map<String, String>> listMap) {
        //  组装 avg  平均访问页数行对象
        LineChart avg = new LineChart();
        avg.setTitle("平均访问页数趋势情况");
        avg.setSeriesName("平均访问页数");
        avg.setUnit("次");
        Map<String, String> avgMap = listMap.get(4);
        return getBasicSeriPvUv(size, xAxisData, avg, avgMap);
    }

    //  pv、uv等12个基本指标的公用y轴数据封装方法
    private LineChart getBasicSeriPvUv(int size, String[] xAxisData, LineChart avg, Map<String, String> avgMap) {
        BigDecimal[] seriesData = new BigDecimal[size];  //  组装y轴数据，首先根据x轴作为key取值，如果没有值给0
        int i = 0;
        for (String x : xAxisData) {

            if (avgMap.containsKey(x) && !avgMap.get(x).equals("null") && avgMap.get(x) != null) {
                seriesData[i] = new BigDecimal(avgMap.get(x));
            } else {
                seriesData[i] = BigDecimal.ZERO;
            }
            i++;
        }
        avg.setSeriesData(seriesData);  //  y轴数据
        return avg;
    }

    private LineChart getAve(int size, String[] xAxisData, List<Map<String, String>> listMap) {
        //  组装 ave  平均停留时长行对象
        LineChart ave = new LineChart();
        ave.setTitle("平均停留时长趋势情况");
        ave.setSeriesName("平均停留时长");
        ave.setUnit("秒");
        Map<String, String> map = listMap.get(3);
        return getBasicSeriPvUv(size, xAxisData, ave, map);
    }

    private LineChart getIps(int size, String[] xAxisData, List<Map<String, String>> listMap) {
        //  组装 ips  查询访问ip数行对象
        LineChart ips = new LineChart();
        ips.setTitle("访问ip数趋势情况");
        ips.setSeriesName("访问ip数");
        ips.setUnit("个");
        Map<String, String> map = listMap.get(2);
        return getBasicSeriPvUv(size, xAxisData, ips, map);
    }

    private LineChart getUv(int size, String[] xAxisData, List<Map<String, String>> listMap) {
        //  组装 uv  查询独立访客行对象
        LineChart uv = new LineChart();
        uv.setTitle("访客数UV趋势情况");
        uv.setSeriesName("访客数UV");
        uv.setUnit("个");
        int i = 0;
        Map<String, String> map = listMap.get(1);
        return getBasicSeriPvUv(size, xAxisData, uv, map);
    }

    private LineChart getPv(int size, String[] xAxisData, List<Map<String, String>> listMap) {
        //  组装 pv  查询浏览量行对象
        LineChart pv = new LineChart();
        pv.setTitle("浏览量PV趋势情况");
        pv.setSeriesName("浏览量PV");
        pv.setUnit("次");
        Map<String, String> map = listMap.get(0);
        return getBasicSeriPvUv(size, xAxisData, pv, map);
    }


}
