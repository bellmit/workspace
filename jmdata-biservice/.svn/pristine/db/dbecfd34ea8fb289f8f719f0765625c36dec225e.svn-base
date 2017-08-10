package org.jumao.bi.service.impl.trade.operate;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.trade.operate.IMasOperationOverviewDao;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.trade.operate.LineChart;
import org.jumao.bi.entites.trade.operate.MasBasicResponse;
import org.jumao.bi.entites.trade.operate.CakeResponse;
import org.jumao.bi.entites.trade.operate.TotalResponse;
import org.jumao.bi.service.trade.operate.IMasOperationSvc;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.PlatFormUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/26.
 */
public class MasOperationSvcImpl implements IMasOperationSvc {

    @Autowired
    private IMasOperationOverviewDao operationOverviewDao;
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 运营总览顶部6个统计图  http://localhost:8080/v1/masoperate/total
     */
    public Response getTotal(String platform) {
        LogUtils.writeLogs(logger, "get getTotal  start.");

        TotalResponse response = new TotalResponse();

        try {
            List<Map<String, Object>> topTotal = operationOverviewDao.getTopTotal(platform);
            MasOperationSvcHelper.buildTotalChart(topTotal, response);
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end getTotal .");
        return Response.ok().entity(response).build();
    }

    /**
     * 查询开通店铺数等5个基本指标
     */
    public Response getBasic(String startDate, String endDate) {
        LogUtils.writeLogs(logger, "get getBasic for from " + startDate + " to " + endDate);

        MasBasicResponse response = new MasBasicResponse();  //  服务返回总对象

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
            setOpenStoreTotal(_startDate, _endDate, size, xAxisData, response);  //  查询并组装开通店铺数实体
            setItemTotal(_startDate, _endDate, size, xAxisData, response);  //  查询并组装发布商品数实体
            setOrderTotal(_startDate, _endDate, size, xAxisData, response);  //  查询并组装订单数实体
            setPayTotal(_startDate, _endDate, size, xAxisData, response);  //  查询并组装付款笔数实体
            setTranTotal(_startDate, _endDate, size, xAxisData, response);  //  查询并组装金额实体
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
     * 查询发布商品数 行业 占比
     */
    public Response getItemProportion(String startDate, String endDate) {
        LogUtils.writeLogs(logger, "get getItemProportion for from " + startDate + " to " + endDate);
        CakeResponse response = new CakeResponse();  //  服务返回总对象

        String _startDate = "";  /* 处理时间格式 */
        String _endDate = "";
        try {
            _startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(startDate));
            _endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {  /* 组装返回体 */
            setItemProportion(_startDate, _endDate, response);  //  查询并组装发布商品数行业占比
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end getItemProportion for from " + startDate + " to " + endDate);
        return Response.ok().entity(response).build();
    }

    /**
     * 订单笔数 行业 占比
     */
    public Response getOrderProportion(String startDate, String endDate) {
        LogUtils.writeLogs(logger, "get getOrderProportion for from " + startDate + " to " + endDate);
        CakeResponse response = new CakeResponse();  //  服务返回总对象

        String _startDate = "";  /* 处理时间格式 */
        String _endDate = "";
        try {
            _startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(startDate));
            _endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {  /* 组装返回体 */
            setOrderProportion(_startDate, _endDate, response);  //  查询并组装订单笔数 行业 占比
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end getOrderProportion for from " + startDate + " to " + endDate);
        return Response.ok().entity(response).build();
    }

    /**
     * 订单金额 行业 占比
     */
    public Response getOrderMoneyProportion(String startDate, String endDate) {
        LogUtils.writeLogs(logger, "get getOrderMoneyProportion for from " + startDate + " to " + endDate);
        CakeResponse response = new CakeResponse();  //  服务返回总对象

        String _startDate = "";  /* 处理时间格式 */
        String _endDate = "";
        try {
            _startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(startDate));
            _endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(endDate)) + " 23:59:59";
        } catch (ParseException e) {  //  请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        try {  /* 组装返回体 */
            setOrderMoneyProportion(_startDate, _endDate, response);  //  查询并组装订单金额 行业 占比
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end getOrderMoneyProportion for from " + startDate + " to " + endDate);
        return Response.ok().entity(response).build();
    }


    private void setItemProportion(String _startDate, String _endDate, CakeResponse response) {

        StringBuffer buffer = new StringBuffer(); //  行业发布商品总数
        StringBuffer buffer_ = new StringBuffer(); //  子平台发布商品数

        buffer.append(" SELECT COUNT(DISTINCT goods_id) FROM jmbi_trade_goods  WHERE industry_id IS NOT NULL AND  " +
                "create_time between '" + _startDate + "' AND '" + _endDate + "' ");
        String SQL = buffer.toString();
        int total = operationOverviewDao.queryForInt(SQL);

        buffer_.append("SELECT  g.industry_id as k, count(DISTINCT g.goods_id) as v  FROM jmbi_trade_goods g " +
                "WHERE g.create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "GROUP BY  k;");
        String SQL_ = buffer_.toString();
        Map<String, String> map_ = operationOverviewDao.getForMap(SQL_);

        response.setUnit("个");
        response.setProportion(getProportion(total, map_, response));
    }

    private void setOrderProportion(String _startDate, String _endDate, CakeResponse response) {

        StringBuffer buffer = new StringBuffer(); //  行业订单笔数总数
        StringBuffer buffer_ = new StringBuffer(); //  子平台订单笔数

        buffer.append("SELECT COUNT(DISTINCT order_id) FROM jmbi_trade_order WHERE industry_id IS NOT NULL AND  " +
                "create_time between '" + _startDate + "' AND '" + _endDate + "' ");
        String SQL = buffer.toString();
        int total = operationOverviewDao.queryForInt(SQL);

        buffer_.append("SELECT industry_id as k,COUNT(DISTINCT order_id) as v FROM jmbi_trade_order  " +
                "WHERE create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "GROUP BY k");
        String SQL_ = buffer_.toString();
        Map<String, String> map_ = operationOverviewDao.getForMap(SQL_);

        response.setUnit("笔");
        response.setProportion(getProportion(total, map_, response));
    }

    private void setOrderMoneyProportion(String _startDate, String _endDate, CakeResponse response) {

        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer_ = new StringBuffer();

        /*buffer.append("SELECT SUM(pay_amt) FROM jmbi_ep_trade WHERE STATUS  IN (0,1,2,3,4) AND  industry_id IS NOT NULL AND  " +
                "create_time between '" + _startDate + "' AND '" + _endDate + "' ");*/
        buffer.append("SELECT SUM(pay_money) FROM jmbi_trade_order WHERE order_state IN (20,30,40,50,70) and  " +
                "create_time between '" + _startDate + "' AND '" + _endDate + "' ");
        String SQL = buffer.toString();
        int total = operationOverviewDao.queryForInt(SQL);

        /*buffer_.append("SELECT industry_id as k, SUM(pay_amt) as v FROM jmbi_ep_trade WHERE STATUS  IN (0,1,2,3,4) AND " +
                "create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "GROUP BY k");*/
        buffer_.append("SELECT industry_id  as k,SUM(pay_money) as v  FROM jmbi_trade_order WHERE order_state IN (20,30,40,50,70) and " +
                "create_time between '" + _startDate + "' AND '" + _endDate + "' " +
                "GROUP BY k");
        String SQL_ = buffer_.toString();
        Map<String, String> map_ = operationOverviewDao.getForMap(SQL_);

        response.setUnit("万元");
        response.setProportion(getProportion_(total, map_, response));
    }


    /**
     * 封装通用占比数据处理
     */
    private List<Map<String, Object>> getProportion(int total, Map<String, String> map_, CakeResponse response) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int total_ = total;
        for (Map.Entry<String, String> entry : map_.entrySet()) {
            String name = PlatFormUtil.getPlatformV(entry.getKey());
            if (name == null || name.equals("")) {
                total_ -= Integer.parseInt(entry.getValue().split("\\.")[0]);
                if (total_ < 1) total_ = 0;
                response.setTotal(String.valueOf(total_));
                continue;
            } else {
                response.setTotal(String.valueOf(total_));
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(name, entry.getValue());
            list.add(map);
        }
        if (list.size() == 0)response.setTotal("0");

        /*for (Map.Entry<String, String> entry : map_.entrySet()) {
            String name = PlatFormUtil.getPlatformV(entry.getKey());
            if (name != null && name.equals("")) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(name, entry.getValue());
                list.add(map);
            }
        }
        Double sum = new Double(0);
        for (Map<String, Object> map : list){
            for (Map.Entry<String, Object> entry : map.entrySet()){
                sum += Double.valueOf(entry.getValue().toString());
            }
        }
        response.setTotal(sum.toString());*/

        return list;
    }
    /**
     * 封装通用占比数据处理
     */
    private List<Map<String, Object>> getProportion_(int total, Map<String, String> map_, CakeResponse response) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int total_ = total;
        for (Map.Entry<String, String> entry : map_.entrySet()) {
            String name = PlatFormUtil.getPlatformV(entry.getKey());
            if (name == null || name.equals("")) {
                total_ -= Integer.parseInt(entry.getValue().split("\\.")[0]);
                if (total_ < 1) total_ = 0;
                response.setTotal(String.valueOf(total_));
                continue;
            } else {
                response.setTotal(String.valueOf(total_));
            }
            Map<String, Object> map = new HashMap<String, Object>();
            String value = NumberUtils.createBigDecimal(entry.getValue()).divide(BigDecimal.valueOf(10000), 2, BigDecimal.ROUND_HALF_EVEN).toString();
            map.put(name, value);
            list.add(map);
        }
        if (list.size() == 0){response.setTotal("0");}else {
            String value = NumberUtils.createBigDecimal(Integer.toString(total_)).divide(BigDecimal.valueOf(10000), 2, BigDecimal.ROUND_HALF_EVEN).toString();
            response.setTotal(value);
        }

        /*for (Map.Entry<String, String> entry : map_.entrySet()) {
            String name = PlatFormUtil.getPlatformV(entry.getKey());
            if (name != null && name.equals("")) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(name, entry.getValue());
                list.add(map);
            }
        }
        Double sum = new Double(0);
        for (Map<String, Object> map : list){
            for (Map.Entry<String, Object> entry : map.entrySet()){
                sum += Double.valueOf(entry.getValue().toString());
            }
        }
        response.setTotal(sum.toString());*/

        return list;
    }

    /**
     * 根据x轴健壮性封装y轴数据
     * @param size
     * @param xAxisData
     * @param map
     * @param threeCard
     * @return
     */
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
     * 根据x轴健壮性封装y轴数据
     * @param size
     * @param xAxisData
     * @param map
     * @param threeCard
     * @return
     */
    private LineChart getBasicSeri_(int size, String[] xAxisData, Map<String, String> map, LineChart threeCard) {
        BigDecimal[] seriesData = new BigDecimal[size];
        int i = 0;
        for (String x : xAxisData) {
            x = x.substring(0, 4) + "-" + x.substring(4, 6) + "-" + x.substring(6, x.length());
            if (map.containsKey(x) && !map.get(x).equals("--") && map.get(x) != null) {
                String value = NumberUtils.createBigDecimal(map.get(x)).divide(BigDecimal.valueOf(10000), 2, BigDecimal.ROUND_HALF_EVEN).toString();
                seriesData[i] = new BigDecimal(value);
            } else {
                seriesData[i] = BigDecimal.ZERO;
            }
            i++;
        }
        threeCard.setSeriesData(seriesData);  //  y轴数据
        return threeCard;
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
    private void setOpenStoreTotal(String _startDate, String _endDate, int size, String[] xAxisData, MasBasicResponse response) {
        //  获得开通店铺数y轴数据
        StringBuffer buffer = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(s.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(s.create_time, 10) ");
        }
        buffer.append(" as k,count(distinct s.shop_id) as v " +
                "from  jmbi_trade_shop_operate s  where operate_type=0 and create_time between '" + _startDate + "' and  '" + _endDate + "'  " +
                "group by  k order by k;");
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
    private void setItemTotal(String _startDate, String _endDate, int size, String[] xAxisData, MasBasicResponse response) {
        //  获得发布商品数y轴数据
        StringBuffer buffer = new StringBuffer();
        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(s.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(s.create_time, 10) ");
        }
        buffer.append(" as k,count(DISTINCT s.goods_id) as v  from  jmbi_trade_goods s  where ");
        buffer.append(" create_time between '" + _startDate + "' and  '" + _endDate + "' " +  //  delete_flag = 0 and
                "group by  k order by k;");
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
    private void setOrderTotal(String _startDate, String _endDate, int size, String[] xAxisData, MasBasicResponse response) {
        //  获得查询订单数y轴数据
        StringBuffer buffer = new StringBuffer();
        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(s.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(s.create_time, 10) ");
        }
        buffer.append(" as k,count(distinct s.order_id) as v  from  jmbi_trade_order s  where ");
        buffer.append(" create_time between '" + _startDate + "' and  '" + _endDate + "' " +  //  delete_flag = 0 and
                "group by  k order by k;");
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
    private void setPayTotal(String _startDate, String _endDate, int size, String[] xAxisData, MasBasicResponse response) {
        //  获得查询付款笔数y轴数据
        StringBuffer buffer = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(c.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(c.create_time, 10) ");
        }
        buffer.append(" AS k, COUNT(DISTINCT payment_id) v  FROM jmbi_trade_payment c,jmbi_trade_order o  " +
                "WHERE c.order_id=o.order_id AND c.pay_flag=1 AND " +
                "c.create_time between '" + _startDate + "' and  '" + _endDate + "' " +
                "group by  k order by k");
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
    private void setTranTotal(String _startDate, String _endDate, int size, String[] xAxisData, MasBasicResponse response) {
        //  获得查询金额y轴数据
        StringBuffer buffer = new StringBuffer();

        if (_startDate.equals(_endDate.substring(0, 10))) {
            buffer.append("select STRLEFT(c.create_time, 13) ");
        } else {
            buffer.append("select STRLEFT(c.create_time, 10) ");
        }
        buffer.append(" as k, SUM(c.pay_money) as v " +
                "FROM jmbi_trade_payment c,jmbi_trade_order o  " +
                "WHERE c.order_id=o.order_id AND c.pay_flag=1 " +
                "and c.create_time between '" + _startDate + "' and  '" + _endDate + "' " +
                "group by  k order by k ");
        String SQL = buffer.toString();
        Map<String, String> map = operationOverviewDao.getForMap(SQL);

        //  组装tranTotal  查询金额行对象
        LineChart tranTotal = new LineChart();
        tranTotal.setTitle("金额趋势情况");
        tranTotal.setSeriesName("金额");
        tranTotal.setUnit("万元");
        response.setTranTotal(getBasicSeri_(size, xAxisData, map, tranTotal));
    }


}
