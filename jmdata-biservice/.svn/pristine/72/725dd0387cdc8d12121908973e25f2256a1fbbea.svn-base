package org.jumao.bi.dao.enjrt;


import com.google.gson.JsonArray;
import org.jumao.bi.entites.jrt.financing.EnFiCompanyPo;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;

import java.util.List;

public interface EnJrtFinancingDao {

    List<TimeTotalVo> getTrendLineChartBy(String platform, String startDate, String endDate, boolean accurateToHour, int type) throws Exception;

    List<GroupByVo> getGlobalAreaDistBy(String startDate, String endDate, int type) throws Exception;

    JsonArray getApplyDetail(long areaId, String startDate, String endDate, int limit, int offset) throws Exception;

    long getApplyTotalCounts(long areaId, String startDate, String endDate) throws Exception;

    List<EnFiCompanyPo> getCompanyDetail(long areaId, String startDate, String endDate, int limit, int offset) throws Exception;

    long getCompanyTotalCounts(long areaId, String startDate, String endDate);



}
