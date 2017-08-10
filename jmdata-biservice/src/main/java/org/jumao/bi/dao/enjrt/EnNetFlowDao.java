package org.jumao.bi.dao.enjrt;

import com.google.gson.JsonArray;
import org.jumao.bi.entites.en.EnBasicVisitPo;
import org.jumao.bi.entites.en.TotalPvUvVo;
import org.jumao.bi.entites.jrt.financing.EnNetflowOverview;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;

import java.util.List;

/**
 */
public interface EnNetFlowDao {

    List<EnNetflowOverview> getEnNetflowOverview(String platform, String startDate, String endDate);

    List<TimeTotalVo> getTrendBy(String platform, String startDate, String endDate, boolean accurateToHour, int type);

    List<EnBasicVisitPo> getEnBasicVisitPos(String platform, String startDate, String endDate, String orderTarget, String orderType);

    List<GroupByVo> getNetflowPvTopXBy(String platform, String startDate, String endDate, int topEle);

    List<GroupByVo> getNetflowBounceTopXBy(String platform, String startDate, String endDate, int top_ele5);

    List<GroupByVo> getGlobalAreaDistPvBy(String platform, String startDate, String endDate);

    TotalPvUvVo getTotalPvUvBy(String platform, String startDate, String endDate);

    JsonArray getGlobalAreaDistTableDataBy(String platform, String startDate, String endDate, String pv,
                                           int limit, int offset, String orderType, TotalPvUvVo totalPvUvBy);

    List<GroupByVo> getSourceMediumUvBy(String platform, String startDate, String endDate);

    List<TimeTotalVo> getSourceTypeBy(String platform, String startDate, String endDate, boolean accurateToHour, int type);

    JsonArray getSourceTypeTableDataBy(String platform, String startDate, String endDate, String orderTarget, String orderType);

    List<GroupByVo> getEntrancesPageTop10By(String platform, String startDate, String endDate, int top_ele10);

    List<GroupByVo> getVisitedPageTop10By(String platform, String startDate, String endDate, int top_ele10);

    List<GroupByVo> getMediumTop10By(String platform, String startDate, String endDate, int top_ele10);


}
