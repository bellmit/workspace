package org.jumao.bi.service.impl.trade.operate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.trade.operate.IOperationOrderDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.operate.OrderInfo;
import org.jumao.bi.entites.operate.OrderPayResponse;
import org.jumao.bi.entites.operate.OrderTypeResponse;
import org.jumao.bi.service.trade.operate.IOperationOrderSvc;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.PlatFormUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/3.
 */
public class OperationOrderSvcImpl implements IOperationOrderSvc {

	@Autowired
    private IOperationOrderDao operationOrderDao;
    private Logger logger = Logger.getLogger(this.getClass());
    private static final String Date_Form_One = "yyyy-MM-dd";
    private static final String Date_Form_Two = "yyyyMMdd";
    
    
    @Override
    public Response getOrderType(String platform, String startDate, String endDate){
    	LogUtils.writeLogs(logger, "get order type start.");

        OrderTypeResponse response = new OrderTypeResponse();
        String newStartDate;
        String newEndDate;
        try {
        	newStartDate = new SimpleDateFormat(Date_Form_One).format(new SimpleDateFormat(Date_Form_Two).parse(startDate));
        	newEndDate = new SimpleDateFormat(Date_Form_One).format(new SimpleDateFormat(Date_Form_Two).parse(endDate));
        } catch (ParseException e) {// 请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }
        
        String newPlatform = PlatFormUtil.getPlatformV(platform);
        if (newPlatform==null) {
        	newPlatform = "0";
        }
        try {
        	List<OrderInfo> orderInfos = operationOrderDao.getOrderType(new ParamBean(newPlatform, newStartDate, newEndDate));
            OperationOrderSvcHelper.buildOrderPieChart(orderInfos,response);
        } catch (Exception e) {//内部错误
        	response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }
        
        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));

        LogUtils.writeLogs(logger, "end order type.");
        return Response.ok().entity(response).build();
        
    }

    @Override
	public Response getOrderPay(String platform, String startDate, String endDate) {
		// TODO Auto-generated method stub
		LogUtils.writeLogs(logger, "get order pay start.");

		OrderPayResponse response = new OrderPayResponse();
        String newStartDate;
        String newEndDate;
        try {
        	newStartDate = new SimpleDateFormat(Date_Form_One).format(new SimpleDateFormat(Date_Form_Two).parse(startDate));
        	newEndDate = new SimpleDateFormat(Date_Form_One).format(new SimpleDateFormat(Date_Form_Two).parse(endDate));
        } catch (ParseException e) {// 请求日期格式错误
            response.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }
        
        String newPlatform = PlatFormUtil.getPlatformV(platform);
        if (newPlatform==null) {
        	newPlatform = "0";
        }
        try {
        	List<OrderInfo> orderInfos = operationOrderDao.getOrderPay(new ParamBean(newPlatform, newStartDate, newEndDate));
            OperationOrderSvcHelper.buildOrderLineChart(orderInfos, response);
        } catch (Exception e) {//内部错误
        	response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, e.getMessage()));
            return Response.serverError().entity(response).build();
        }
        
        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        
        LogUtils.writeLogs(logger, "end order pay.");
        return Response.ok().entity(response).build();
	}
}
