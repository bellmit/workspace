package org.jumao.bi.dao.trade;

import org.jumao.bi.entites.trade.register.DataSrcIncrTable;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;

import java.util.List;



public interface TradeRegisterDao {

    List<TimeTotalVo> getNewlyIncrBy(String table, String platform, String startDate, String endDate, boolean accurateToHour) throws Exception;

    List<GroupByVo> getDataSrcIncrBy(String table, String platform, String startDate, String endDate) throws Exception;

    List<DataSrcIncrTable> getDataSrcIncrTableBy(String platform, String startDate, String endDate,
                                                 boolean accurateToHour, int limit, int offset) throws Exception;

    List<TimeTotalVo> getPvOrUvBy(String platform, String startDate, String endDate, boolean isPv) throws Exception;

    List<TimeTotalVo> getCert3AuthBy(String startDate, String endDate, boolean accurateToHour) throws Exception;

    long getTotalUserCounts(String platform, String startDate, String endDate) throws Exception;

    List<TimeTotalVo> getAuthLetterBy(String startDate, String endDate, boolean accurateToHour) throws Exception;

    List<GroupByVo> getAuthUCert3HistoryBy(String platform);

    List<GroupByVo> getLicPercentPieBy(String platform, String startDate, String endDate) throws Exception;

    List<TimeTotalVo> getVisaTTVsBy(String platform, String startDate, String endDate, boolean accurateToHour) throws Exception;

    List<TimeTotalVo> getPayTTVsBy(String platform, String startDate, String endDate, boolean accurateToHour) throws Exception;

    long getVisaHisTotal(String platform);

    long getPayHisTotal(String platform);

    List<GroupByVo> getCert3AndAuthLetterAreaPieBy(String startDate, String endDate, int topEle) throws Exception;

    List<GroupByVo> getVisaOrPayAreaDistBy(String platform, String startDate, String endDate, int topEle, boolean isVisa) throws Exception;

    List<GroupByVo> getVisaAndPayHistoryPieBy(String platform);

    List<GroupByVo> getMsUserIncrPieBy(String platform, String startDate, String endDate) throws Exception;

}
