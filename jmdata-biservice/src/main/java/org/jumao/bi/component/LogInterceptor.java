package org.jumao.bi.component;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
public class LogInterceptor implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
    	Logger logger = Logger.getLogger(invocation.getMethod().getDeclaringClass());
        logger.info("***** Method:"+invocation.getMethod().getName()+"() begin *****");
        Object object = invocation.proceed();
        logger.info("***** Method:"+invocation.getMethod().getName()+"() end *****");
        return object;
    }
    
}