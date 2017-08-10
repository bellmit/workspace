package org.jumao.bi.dao.trade;


import org.jumao.bi.entites.trade.goods.PlatformCompVo;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;

import java.util.List;
import java.util.Set;

public interface GoodsDao {

    List<TimeTotalVo> getNewlyIncrBy(String platform, String startDate, String endDate, boolean accurateToHour) throws Exception;

    List<GroupByVo> getIndustryPieBy(String platform, String startDate, String endDate) throws Exception;

    List<GroupByVo> getCategoryPieBy(String industryId, String platform, String startDate, String endDate) throws Exception;

    List<GroupByVo> getCateIdNameVosBy(String industryId);

    List<GroupByVo> getIndustryAvgMoneyBy(String startDate, String endDate) throws Exception;

    List<PlatformCompVo> getPlatformCompVosBy(String startDate, String endDate);

    long getOrderCountsBy(Set<String> compIdSet, String startDate, String endDate) throws Exception;

    String dealPlatform(String platform, int needLen);
}
