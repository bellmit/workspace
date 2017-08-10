package org.jumao.bi.dao.jrt;


import com.google.gson.JsonArray;
import org.codehaus.jettison.json.JSONArray;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;

import java.util.List;

public interface JrtFinancingDao {

    List<TimeTotalVo> getTrendLineChartBy(String platform, String startDate, String endDate, boolean accurateToHour, int type) throws Exception;

    JsonArray getApplyDetail(int limit, int offset);

    long getApplyTotalCounts();

    JsonArray getRequiredDetail(int limit, int offset);

    long getRequiredTotalCounts();

    JsonArray getSignedDetail(int limit, int offset);

    long getSignedTotalCounts();

}
