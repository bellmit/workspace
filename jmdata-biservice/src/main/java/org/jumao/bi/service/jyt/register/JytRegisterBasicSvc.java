package org.jumao.bi.service.jyt.register;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.enjyt.EnJytRegisterDao;
import org.jumao.bi.dao.enjyt.impl.EnJytRegisterDaoImpl;
import org.jumao.bi.dao.trade.TradeRegisterDao;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 */
@Repository(JytRegisterBasicSvc.JYT_REGISTER_BASIC_SVC)
public class JytRegisterBasicSvc extends ChartBasicService {

    public static final String JYT_REGISTER_BASIC_SVC = "jytRegisterBasicSvc";

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private TradeRegisterDao tradeRegisterDao;

    @Autowired
    private EnJytRegisterDao enJytRegisterDao;



    public Response getNewRegLineChart(String platform, String startDate, String endDate, boolean isCn) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            boolean accurateToHour = checkAccurateToHour(startDate, endDate);
            List<TimeTotalVo> list = null;
            if (isCn) {
                list = tradeRegisterDao.getNewlyIncrBy(
                        Table.Lg_Base_Company_User, platform, startDate, endDate, accurateToHour);
            } else {
                list = enJytRegisterDao.getNewlyIncrBy(platform, startDate, endDate, accurateToHour);
            }
            return Response.ok().entity(
                    getLineChartFrom(list, CN.Reg_Newlyincr, startDate, endDate, Key.Num0, true, accurateToHour)).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw GeneralUtils.getWrapEx(e);
        }
    }

}
