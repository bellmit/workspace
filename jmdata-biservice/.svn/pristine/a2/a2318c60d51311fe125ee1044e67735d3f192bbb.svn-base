package org.jumao.bi.provider;

import java.text.ParseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.utis.LogUtils;

@Provider
public class InvokeFaultExceptionMapper implements ExceptionMapper<Throwable> {
    private Logger   logger = Logger.getLogger(this.getClass());
    /**
     * 构造异常状态的Response对象
     */
    public Response toResponse(Throwable ex) {
//        StackTraceElement[] trace = new StackTraceElement[1];
//        trace[0] = ex.getStackTrace()[0];
//        ex.setStackTrace(trace);
//        ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
//        rb.type("application/json;charset=UTF-8");
//        rb.entity(ex);
//        rb.language(Locale.SIMPLIFIED_CHINESE);
//        Response r = rb.build();
        CommonResponse rs = new CommonResponse();
        String fullStackTrace = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(ex);
        //判断400和500错误  by zxw 20170508
        if(ex instanceof ParseException){
        	//rs.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE,  ex.toString()));
            rs.setStatus(new ResponseResult(ServiceConst.INVADLID_REQUEST_CODE,  fullStackTrace));
        } else {
        	//rs.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE,  ex.toString()));
            rs.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE,  fullStackTrace));
        }
        
        LogUtils.writeLogs(logger, ex.getStackTrace().toString());
        return Response.ok().entity(rs).build();
    }

}
