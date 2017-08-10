package org.jumao.bi.service.impl.ga;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.ga.GaDao;
import org.jumao.bi.service.ga.GaSvc;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 */
public class GaSvcImpl implements GaSvc {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private GaDao gaDao;


    /**
     *
     * @param date like '20170202'
     * @param hour like '09' or '21'
     * @param input
     * @return
     * @throws Exception
     */
    @Override
    public Response toHbase(String date, String hour, InputStream input) throws Exception {
        String jsonStr = null;
        try {
            LogUtils.writeLogs(logger, StringUtils.joinStr(
                    "date:", date, ", hour:", hour, ", GaSvcImpl:", GeneralUtils.getCurrMethod()));

            jsonStr = IOUtils.toString(input, Key.UTF8);
            gaDao.parseJsonToHbase(date + hour, jsonStr);
            return Response.ok().entity("success").build();
        } catch (Exception e) {
            logger.error(StringUtils.joinStr("can't pasrse jsonStr:", jsonStr));
            throw GeneralUtils.getWrapEx(e);
        }
    }



}
