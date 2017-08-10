package org.jumao.bi.service.impl.trade.operate;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.trade.operate.IOperationConvFunnelDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.operate.ConvFunnelInfo;
import org.jumao.bi.entites.operate.ConvFunnelResponse;
import org.jumao.bi.service.trade.operate.IOperationConvFunnelSvc;
import org.jumao.bi.utis.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/8.
 */
public class OperationConvFunnelSvcImpl implements IOperationConvFunnelSvc {

	@Autowired
    private IOperationConvFunnelDao operationConvFunnelDao;
    private Logger logger = Logger.getLogger(this.getClass());
    /**
     * 获取漏斗转换数据
     */
    public Response getConvFunnel(String platform, String startDate, String endDate) throws Exception{
    	LogUtils.writeLogs(logger, "getCovFunnel start.");

        ConvFunnelResponse response = new ConvFunnelResponse();
        
//        String nStartDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(startDate));
//        String nEndDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(endDate));
//        
//        String nPlatform = PlatFormUtil.getPlatformV(platform);
    	List<ConvFunnelInfo> convFunnelInfos = operationConvFunnelDao.getConvFunnel(new ParamBean(platform, startDate, endDate));
        OperationConvFunnelSvcHelper.buildFunnelChart(convFunnelInfos, response);
        
        response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));

        LogUtils.writeLogs(logger, "end getCovFunnel.");
        return Response.ok().entity(response).build();
        
    }

}
