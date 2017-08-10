package org.jumao.bi.service.impl.jrt.overview;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.jrt.JrtOverviewDao;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.trade.operate.*;
import org.jumao.bi.service.jrt.JrtOverviewSvc;
import org.jumao.bi.utis.CalculationUtil;
import org.jumao.bi.utis.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/6/14.
 */
@Service
public class JrtOverviewImpl implements JrtOverviewSvc {

    @Autowired
    private JrtOverviewDao jrtOverviewDao;
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 运营概览
     */
    public Response OperationOverview() {
        LogUtils.writeLogs(logger, "get OperationOverview ");
        TotalListResponse response = new TotalListResponse();  //  服务返回总对象

        try {
            settledEnterprise(response,"","");  //  入驻企业
            financingEnterprise(response,"融资签约企业","","");  //  融资签约企业
            settlingInstitution(response,"","");  //  入驻机构
            shelvesFinancingProducts(response, "上架融资产品","","");  //  上架融资产品
            financingRequest(response,"","");  //  融资申请
            contractedMoney(response,"","");  //  签约金额
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end OperationOverview ");
        return Response.ok().entity(response).build();
    }

    public Response ServiceDataOverview(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get ServiceDataOverview ");
        TotalListResponse response = new TotalListResponse();  //  服务返回总对象

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
            settledEnterprise(response, startDate, endDate);  //  入驻企业
            shelvesFinancingProducts(response, "上架产品", startDate, endDate);  //  上架产品
            financingEnterprise(response,"签约企业", startDate, endDate);  //  签约企业
            financingRequest(response, startDate, endDate);  //  融资申请
            financingContract(response, startDate, endDate);  //  融资签约
            contractedMoney(response, startDate, endDate);  //  签约金额
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end ServiceDataOverview ");
        return Response.ok().entity(response).build();
    }

    public Response RegistrationConversion(String _startDate, String _endDate) {
        LogUtils.writeLogs(logger, "get RegistrationConversion ");
        TotalListResponse response = new TotalListResponse();  //  服务返回总对象

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
            register(response, startDate, endDate);  //  注册
            authentication(response, startDate, endDate);  //  认证或加入公司
            submitDemand(response, startDate, endDate);  //  提交融资需求
            submitApply(response, startDate, endDate);  //  提交融资申请
            signSuccess(response, startDate, endDate);  //  签约完成
        } catch (Exception e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }

        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        LogUtils.writeLogs(logger, "end RegistrationConversion ");
        return Response.ok().entity(response).build();
    }

    private void signSuccess(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.signSuccess(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        String v1 = map.get("k");
        String v2 = list.get(3).get("value");
        hashMap.put("name","签约完成");
        hashMap.put("value",map.get("k"));
        if (v1.equals("0") || v2.equals("0")){
            hashMap.put("Conv","0");
        }else {
            hashMap.put("Conv",getConv(v1, v2));
        }
        list.add(hashMap);
        response.setData(list);
    }

    private void submitApply(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.submitApply(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        String v1 = map.get("k");
        String v2 = list.get(2).get("value");
        hashMap.put("name","提交融资申请");
        hashMap.put("value",v1);
        if (v1.equals("0") || v2.equals("0")){
            hashMap.put("Conv","0");
        }else {
            hashMap.put("Conv",getConv(v1, v2));
        }
        list.add(hashMap);
        response.setData(list);
    }

    private void submitDemand(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.submitDemand(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        String v1 = map.get("k");
        String v2 = list.get(1).get("value");
        hashMap.put("name","提交融资需求");
        hashMap.put("value",v1);
        if (v1.equals("0") || v2.equals("0")){
            hashMap.put("Conv","0");
        }else {
            hashMap.put("Conv",getConv(v1, v2));
        }
        list.add(hashMap);
        response.setData(list);
    }

    private void authentication(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.authentication(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        String v1 = map.get("k");
        String v2 = list.get(0).get("value");
        hashMap.put("name","认证或加入公司");
        hashMap.put("value",v1);
        if (v1.equals("0") || v2.equals("0")){
            hashMap.put("Conv","0");
        }else {
            hashMap.put("Conv",getConv(v1, v2));
        }
        list.add(hashMap);
        response.setData(list);
    }

    private void register(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.register(startDate, endDate);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name","注册");
        hashMap.put("value",map.get("k"));
        hashMap.put("Conv","");
        list.add(hashMap);
        response.setData(list);
    }

    private String getConv(String v1, String v2) {
        String s = null;  //  得到转化率
        try {
            s = CalculationUtil.div(v1, v2, 5);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        s = CalculationUtil.mul_dec(s,"100", 2);  //  转化成百分比
        return s;
    }

    private void financingContract(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.financingContract(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name","融资签约");
        hashMap.put("value",map.get("k"));
        hashMap.put("unit","笔");
        list.add(hashMap);
        response.setData(list);
    }

    private void contractedMoney(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.contractedMoney(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name", "签约金额");
//        hashMap.put("value", CalculationUtil.div_dec(map.get("k"),"10000",2));
        hashMap.put("value", map.get("k"));
        hashMap.put("unit","万元");
        list.add(hashMap);
        response.setData(list);
    }

    private void financingRequest(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.financingRequest(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name","融资申请");
        hashMap.put("value",map.get("k"));
        hashMap.put("unit","笔");
        list.add(hashMap);
        response.setData(list);
    }

    private void shelvesFinancingProducts(TotalListResponse response, String name, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.shelvesFinancingProducts(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name",name);
        hashMap.put("value",map.get("k"));
        hashMap.put("unit","个");
        list.add(hashMap);
        response.setData(list);
    }

    private void settlingInstitution(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.settlingInstitution(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name","入驻机构");
        hashMap.put("value",map.get("k"));
        hashMap.put("unit","家");
        list.add(hashMap);
        response.setData(list);
    }

    private void financingEnterprise(TotalListResponse response, String name, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.financingEnterprise(startDate, endDate);
        List<Map<String, String>> list = response.getData();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name",name);
        hashMap.put("value",map.get("k"));
        hashMap.put("unit","家");
        list.add(hashMap);
        response.setData(list);
    }

    private void settledEnterprise(TotalListResponse response, String startDate, String endDate) {
        Map<String, String> map = jrtOverviewDao.settledEnterprise(startDate, endDate);
        ArrayList<Map<String, String>> list = new ArrayList<>();
        Map<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("name","入驻企业");
        hashMap.put("value",map.get("k"));
        hashMap.put("unit","家");
        list.add(hashMap);
        response.setData(list);
    }

}
