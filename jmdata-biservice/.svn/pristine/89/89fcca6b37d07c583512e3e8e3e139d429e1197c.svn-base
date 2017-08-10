package org.jumao.bi.dao.jyt;

import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.utis.enums.JytEnum;

import java.util.List;

/**
 * @author chen qian
 */
public interface JytOverviewDao {

    long getNumbersBy(int itemId, JytEnum jytEnum, boolean isCn);

    List<TimeTotalVo> getLineChartByItemId(String itemId, String startDate, String endDate, boolean accurateToHour, boolean isCn) throws Exception;

    List<GroupByVo> getTransportLineBy(String startDate, String endDate, boolean isCn) throws Exception;

    List<GroupByVo> getRequireTop5BarChartBy(String startDate, String endDate, int toEle, boolean isCn) throws Exception;


}
