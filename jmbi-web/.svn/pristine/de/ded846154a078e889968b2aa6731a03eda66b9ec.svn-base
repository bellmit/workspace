package com.jumore.jmbi.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.jumore.dove.common.log.LogHelper;
import com.jumore.jmbi.result.ResponseDTO;

@Activate(group = Constants.PROVIDER)
/**
 * dubbox异常处理封装类
 * @author huangwq 
 *
 */
public class ExceptionFilter implements Filter {
    private static final LogHelper Log_Helper = LogHelper.getLogger(ExceptionFilter.class);

    /**
     * invoke.
     * 
     * @see com.alibaba.dubbo.rpc.Filter#invoke(com.alibaba.dubbo.rpc.Invoker,
     *      com.alibaba.dubbo.rpc.Invocation)
     */
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = invoker.invoke(invocation);
        if (result.hasException()) {
            Throwable e = result.getException();
            Log_Helper.getBuilder().error(e.getMessage(), e);
            result = new RpcResult(new ResponseDTO<>(ResponseDTO.ResponseCode.ERROR, e.getMessage()));
        }
        return result;
    }

}
