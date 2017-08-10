package org.jumao.bi.service.test;
/**
 * ClassName:IPingSvc <br/>
 * @author   TK
 * @version  
 * @see 	 
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
 
 

/**
 * ping 测试服务连通性和健康检查
 * @version 1.0
 */
@Path(value = "/v1/ping")
public interface IPingSvc {
    
    @GET
    @Path("")
    public String ping();
    


}


