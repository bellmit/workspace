package org.jumao.bi.dao.jyt;

import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;

import java.util.List;

/**
 * @author chen qian
 */
public interface JytRegIncrAndAuthDao {


    List<GroupByVo> getRegAreaDist(String startDate, String endDate, int topLimit) throws Exception;

    List<GroupByVo> getNewRegPieChartBy(String startDate, String endDate, int topLimit) throws Exception;

    List<TimeTotalVo> getSmrzBy(String startDate, String endDate, boolean accurateToHour) throws Exception;

    List<TimeTotalVo> getWtfszrzBy(String startDate, String endDate, boolean accurateToHour) throws Exception;

    List<TimeTotalVo> getGysszrzBy(String startDate, String endDate, boolean accurateToHour) throws Exception;

    List<TimeTotalVo> getWtssqBy(String startDate, String endDate, boolean accurateToHour) throws Exception;

    List<TimeTotalVo> getGyssqBy(String startDate, String endDate, boolean accurateToHour) throws Exception;

    long getEntrustHisTotal();

    long getSupplyHisTotal();

    List<GroupByVo> getEntrustAndSupplyHistoryPieBy();

    List<GroupByVo> getSupplyAreaDistBy(String startDate, String endDate, int topLimit) throws Exception;

    List<GroupByVo> getEntrustAreaDistBy(String startDate, String endDate, int topLimit) throws Exception;

    List<GroupByVo> getDriverAreaDistBy(String startDate, String endDate, int topLimit);
}
