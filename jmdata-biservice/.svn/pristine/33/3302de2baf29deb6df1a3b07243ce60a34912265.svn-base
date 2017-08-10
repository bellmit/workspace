package org.jumao.bi.service.ga;

import org.jumao.bi.entites.user.UserTraces;
import org.jumao.bi.utis.constants.Key;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * 接收从 ga 导出的数据并写入 hbase
 */
@Path("/v1/ga/")
public interface GaSvc {

    @POST
    @Path("/toHbase")
    public Response toHbase(
            @HeaderParam(Key.Date) String date,
            @HeaderParam(Key.Hour) String hour,
            InputStream input) throws Exception;



}
