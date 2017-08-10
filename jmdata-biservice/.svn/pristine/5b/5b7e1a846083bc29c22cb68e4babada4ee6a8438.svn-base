package org.jumao.bi.service.impl.jzx;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.jzx.JzxOverviewDao;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.trade.operate.CakeListMapResponse;
import org.jumao.bi.entites.trade.operate.CakeResponse;
import org.jumao.bi.entites.trade.operate.TotalListResponse;
import org.jumao.bi.service.jzx.JzxOverviewSvc;
import org.jumao.bi.utis.CalculationUtil;
import org.jumao.bi.utis.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
@Service
public class JzxOverviewImpl implements JzxOverviewSvc {

    @Autowired
    private JzxOverviewDao jzxOverviewDao;
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 运营概览
     */
    public Response OperationOverview() {
        LogUtils.writeLogs(logger, "get OperationOverview ");
        TotalListResponse response = new TotalListResponse();  //  服务返回总对象

        try {
            settledEnterprise(response, "", "");  //  入驻企业
            financingEnterprise(response, "", "");  //  已服务企业
            settlingInstitution(response, "", "");  //  入驻服务机构
            shelvesFinancingProducts(response, "", "");  //  上架服务
            financingRequest(response, "", "");  //  项目订单
            contractedMoney(response, "", "");  //  合同金额
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end OperationOverview ");
        return Response.ok().entity(response).build();
    }

    /** 新增项目订单 */
    public Response newProjectOrder(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get newProjectOrder ");
        CakeListMapResponse response = new CakeListMapResponse();  //  服务返回总对象

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
            ArrayList<Map<String, String>> list = new ArrayList<>();
            Map<String, String> map = jzxOverviewDao.newProjectOrder(startDate, endDate);
            int total = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() == null || "".equals(entry.getKey()) || "null".equals(entry.getKey())) continue;
                LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("name", entry.getKey());
                hashMap.put("value", entry.getValue());
                list.add(hashMap);
                total += Integer.parseInt(entry.getValue());
            }
            response.setProportion(list);
            response.setUnit("笔");
            response.setTotal(String.valueOf(total)+" 笔");
            response.setTitel("新增项目订单");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end newProjectOrder ");
        return Response.ok().entity(response).build();
    }

    /** 市场营销订单  各行业占比 */
    public Response IndustryShare(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get industryShare ");
        CakeListMapResponse response = new CakeListMapResponse();  //  服务返回总对象

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
            ArrayList<Map<String, String>> list = new ArrayList<>();
            Map<String, String> map = jzxOverviewDao.industryShare(startDate, endDate);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() == null || "".equals(entry.getKey()) || "null".equals(entry.getKey())) continue;
                LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("name", entry.getKey());
                hashMap.put("value", entry.getValue());
                list.add(hashMap);
            }
            response.setProportion(list);
            response.setUnit("");
            response.setTotal("");
            response.setTitel("市场营销订单  各行业占比");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end industryShare ");
        return Response.ok().entity(response).build();
    }

    /** 市场营销订单  各细分服务类型占比 */
    public Response TypeRatio(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get TypeRatio ");
        CakeListMapResponse response = new CakeListMapResponse();  //  服务返回总对象

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
            ArrayList<Map<String, String>> list = new ArrayList<>();
            Map<String, String> map = jzxOverviewDao.typeRatio(startDate, endDate);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() == null || "".equals(entry.getKey()) || "null".equals(entry.getKey())) continue;
                LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("name", entry.getKey());
                hashMap.put("value", entry.getValue());
                list.add(hashMap);
            }
            response.setProportion(list);
            response.setUnit("");
            response.setTotal("");
            response.setTitel("市场营销订单  各细分服务类型占比");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end TypeRatio ");
        return Response.ok().entity(response).build();
    }

    /** 新增注册用户 */
    public Response newRegisteredUser(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get newRegisteredUser ");
        CakeListMapResponse response = new CakeListMapResponse();  //  服务返回总对象

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
            ArrayList<Map<String, String>> list = new ArrayList<>();
            Map<String, String> map = jzxOverviewDao.newRegisteredUser(startDate, endDate);
            int total = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() == null || "".equals(entry.getKey()) || "null".equals(entry.getKey())) continue;
                LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("name", entry.getKey());
                hashMap.put("value", entry.getValue());
                list.add(hashMap);
                total += Integer.parseInt(entry.getValue());
            }
            response.setProportion(list);
            response.setUnit("个");
            response.setTotal(String.valueOf(total)+" 个");
            response.setTitel("新增注册用户");
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end newRegisteredUser ");
        return Response.ok().entity(response).build();
    }


    private void contractedMoney(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jzxOverviewDao.contractedMoney(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name", "合同金额");
//        hashMap.put("value", CalculationUtil.div_dec(map.get("k"),"10000",2));
        hashMap.put("value", map.get("k"));
        hashMap.put("unit", "万元");
        list.add(hashMap);
        response.setData(list);
    }

    private void financingRequest(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jzxOverviewDao.financingRequest(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name", "项目订单");
        hashMap.put("value", map.get("k"));
        hashMap.put("unit", "笔");
        list.add(hashMap);
        response.setData(list);
    }

    private void shelvesFinancingProducts(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jzxOverviewDao.shelvesFinancingProducts(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name", "上架服务");
        hashMap.put("value", map.get("k"));
        hashMap.put("unit", "个");
        list.add(hashMap);
        response.setData(list);
    }

    private void settlingInstitution(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jzxOverviewDao.settlingInstitution(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name", "入驻服务机构");
        hashMap.put("value", map.get("k"));
        hashMap.put("unit", "家");
        list.add(hashMap);
        response.setData(list);
    }

    private void financingEnterprise(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jzxOverviewDao.financingEnterprise(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name", "已服务企业");
        hashMap.put("value", map.get("k"));
        hashMap.put("unit", "家");
        list.add(hashMap);
        response.setData(list);
    }

    private void settledEnterprise(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jzxOverviewDao.settledEnterprise(startDate, endDate);
        ArrayList<Map<String, String>> list = new ArrayList<>();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name", "入驻企业");
        hashMap.put("value", map.get("k"));
        hashMap.put("unit", "家");
        list.add(hashMap);
        response.setData(list);
    }

}
