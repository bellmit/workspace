package org.jumao.bi.component;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;
/**
 * 抛出异常的统一拦截，打印异常信息并抛出给InvokeFaultExceptionMapper统一返回给前段
 * @author Administrator
 *
 */
public class ExceptionAdvisor implements ThrowsAdvice {
	/**
	 * 异常抛出之后的处理
	 * @param method
	 * @param args
	 * @param target
	 * @param ex
	 * @throws Throwable
	 */
	public void afterThrowing(Method method, Object[] args, Object target,  
            Exception ex) throws Throwable  {  
        // 在后台中输出错误异常异常信息，然后再抛出  
        Logger log = Logger.getLogger(target.getClass());  
        log.info("**************************************************************");  
        log.info("Error happened in class: " + target.getClass().getName());  
        log.info("Error happened in method: " + method.getName());  
            for (int i = 0; i < args.length; i++)  
            {  
                log.info("arg[" + i + "]: " + args[i]);  
            }  
        log.info("Exception class: " + ex.getClass().getName());  
        log.info("ex.getMessage():" + ex.getMessage());
        log.error("",ex);
        log.info("**************************************************************");  
        throw ex;
	}
}
