package org.jumao.bi.service.impl.enjyt;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.enjyt.EnJytRegisterDao;
import org.jumao.bi.dao.jyt.JytRegIncrAndAuthDao;
import org.jumao.bi.dao.trade.TradeRegisterDao;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.service.enjyt.EnJytRegisterSvc;
import org.jumao.bi.service.impl.trade.register.ChartBasicService;
import org.jumao.bi.service.jyt.register.JytRegisterBasicSvc;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.constants.Key;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class EnJytRegisterSvcImpl extends ChartBasicService implements EnJytRegisterSvc {

    private Logger logger = Logger.getLogger(this.getClass());
    private Gson gson = new Gson();

    @Autowired
    private EnJytRegisterDao enJytRegisterDao;

    @Autowired
    private JytRegisterBasicSvc jytRegisterBasicSvc;

    private String entrustIdVal = "0";
    private String supplyIdVal = "1";

    /**
     */
    public Response getNewRegLineChart(String platform, String startDate, String endDate) throws Exception {
        return jytRegisterBasicSvc.getNewRegLineChart(platform, startDate, endDate, false);
    }


    /**
     */
    public Response getRegAreaDist(String platform, String startDate, String endDate) throws Exception {
        try {
            LogUtils.writeLogs(logger, logParamAndCheckDateRange(platform, startDate, endDate));

            List<GroupByVo> queryResults = enJytRegisterDao.getRegAreaDist(startDate, endDate);
            List<GroupByVo> areaTotalRegList = getAreaTotalRegList(queryResults);
            Map<String, String> typeNameMap = getTypeNameMapFrom(queryResults, false);

            return Response.ok().entity(getPieChartFromGroupByVo(areaTotalRegList, typeNameMap)).build();
        } catch (Exception e) {
            throw GeneralUtils.getWrapEx(e);
        }
    }

    private List<GroupByVo> getAreaTotalRegList(List<GroupByVo> queryResults) {
        Map<String, GroupByVo> areaGrpVoMap = new HashMap<String, GroupByVo>();

        for (GroupByVo ele : queryResults) {
            String type = ele.getType();
            String childType1 = ele.getChildType1();
            Long thisGrpTotal = ele.getTotal();
            GroupByVo existGrp = areaGrpVoMap.get(type);

            if (existGrp == null) {
                existGrp = new GroupByVo(type);
                areaGrpVoMap.put(type, existGrp);
            }

            long newTotal = existGrp.getTotal() + thisGrpTotal;
            existGrp.setTotal(newTotal);

            Map<String, String> extraKvMap= existGrp.getExtraKeyVal();
            extraKvMap.put(Key.TOTAL_NUM, String.valueOf(newTotal));

            if (entrustIdVal.equals(childType1)) {
                extraKvMap.put(Key.Entrust_Num, String.valueOf(thisGrpTotal));

            } else if (supplyIdVal.equals(childType1)) {
                extraKvMap.put(Key.Supply_Num, String.valueOf(thisGrpTotal));
            }
        }

        return new ArrayList<GroupByVo>(areaGrpVoMap.values());
    }


}
