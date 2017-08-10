package org.jumao.bi.utis.threads;

import org.apache.log4j.Logger;
import org.jumao.bi.dao.trade.GoodsDao;
import org.jumao.bi.entites.trade.goods.OrderCountsVo;
import org.jumao.bi.utis.LogUtils;

import java.util.Set;
import java.util.concurrent.Callable;

public class GetOrderCountsThread implements Callable<OrderCountsVo> {

    private Logger logger = Logger.getLogger(GetOrderCountsThread.class);

    private String industry;
    private GoodsDao goodsDao;
    private Set<String> compIds;
    private String startDate;
    private String endDate;

    /**
     * @param industry
     * @param goodsDao
     * @param compIds
     */
    public GetOrderCountsThread(String industry, GoodsDao goodsDao, Set<String> compIds, String startDate, String endDate) {
        this.industry = industry;
        this.goodsDao = goodsDao;
        this.compIds = compIds;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return
     * @throws Exception
     */
    public OrderCountsVo call() throws Exception {
        OrderCountsVo vo = new OrderCountsVo(industry, 0L);
        try {
            vo.setOrderCounts(goodsDao.getOrderCountsBy(compIds, startDate, endDate));
        } catch (Exception e) {
            LogUtils.writeLogs(logger, e.getMessage());
        }
        return vo;
    }


}
