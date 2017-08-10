package org.jumao.bi.dao.trade.impl;

import org.apache.log4j.Logger;
import org.jumao.bi.dao.trade.GoodsDao;
import org.jumao.bi.entites.trade.goods.PlatformCompVo;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by kartty on 2017/5/23.
 */
@Repository(GoodsDaoImpl.Goods_Dao)
public class GoodsDaoImpl extends GeneralBasicDao implements GoodsDao {

    private Logger logger = Logger.getLogger(GoodsDaoImpl.class);
    public static final String Goods_Dao = "goodsDao";


    /**
     * @param platform
     * @param startDate
     * @param endDate
     * @param accurateToHour
     * @return
     * @throws Exception
     */
    public List<TimeTotalVo> getNewlyIncrBy(String platform, String startDate, String endDate, boolean accurateToHour) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String tmGrpLen = getCrtTmGroupLength(accurateToHour);
        String sql = StringUtils.joinStr(
                "select ", tmGrpLen, " as ", Key.CREATE_TIME,
                " , count(distinct goods_id) as ", Key.TOTAL, " from ", Table.Trade_Goods, " where",
                " (create_time between '", startDate, "' and '", endDate, "') ",
                " group by ", Key.CREATE_TIME, " order by ", Key.CREATE_TIME, " asc");

        return getVoList(sql, TimeTotalVo.class);
    }


    /**
     * @param platform
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public List<GroupByVo> getIndustryPieBy(String platform, String startDate, String endDate) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = StringUtils.joinStr(
                "select industry_id as ", Key.Type,
                " , count(distinct goods_id) as ", Key.TOTAL, " from ", Table.Trade_Goods,
                " where create_time between '", startDate, "' and '", endDate, "' group by industry_id");

        return getVoList(sql, GroupByVo.class);
    }

    /**
     * @param industryId
     * @param platform
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public List<GroupByVo> getCategoryPieBy(String industryId, String platform, String startDate, String endDate) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = StringUtils.joinStr(
                "select goods_category_grade1_id as ", Key.Type,
                " , count(distinct goods_id) as ", Key.TOTAL, " from ", Table.Trade_Goods,
                " where industry_id = ", industryId,
                " and (create_time between '", startDate, "' and '", endDate, "') group by goods_category_grade1_id");

        return getVoList(sql, GroupByVo.class);
    }

    /**
     * @param industryId
     * @return
     */
    public List<GroupByVo> getCateIdNameVosBy(String industryId) {
        String sql = StringUtils.joinStr(
                "select goods_cate_id as ", Key.Type,
                " , goods_cate_name as ", Key.TYPE_NAME, " from ", Table.Trade_Goods_Category,
                " where industry_id = ", industryId);

        return getVoList(sql, GroupByVo.class);
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public List<GroupByVo> getIndustryAvgMoneyBy(String startDate, String endDate) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = StringUtils.joinStr(
                "select industry_id as ", Key.Type,
                " , sum(pay_money)/count(DISTINCT order_id) as ", Key.VAL_DOU, " from ", Table.Trade_Order,
                " where (create_time between '", startDate, "' and '", endDate, "') group by industry_id");

        return getVoList(sql, GroupByVo.class);
    }

    /**
     * 此处直接使用 20170707 格式比较
     * @param startDate
     * @param endDate
     * @return
     */
    public List<PlatformCompVo> getPlatformCompVosBy(String startDate, String endDate) {
        String sql = StringUtils.joinStr("select biz_code as ", Key.Platform,
                ", company_id as ", Key.COMPANY_ID, " from ", Table.User_Traces,
                " where biz_code like '%00' and (login_time between '", startDate, "000000' and '", endDate, "235959')");

        return getVoList(sql, PlatformCompVo.class);
    }

    /**
     * @param compIdSet
     * @return
     */
    public long getOrderCountsBy(Set<String> compIdSet, String startDate, String endDate) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String basicSql = StringUtils.joinStr("select count(DISTINCT c.comp_id) from ", Table.Trade_Order,
                " o, ", Table.Trade_Company, " c, ", Table.Trade_Center_User, " u where",
                " o.member_id=u.user_id and u.company_id=c.comp_id",
                " and (o.create_time between '", startDate, "' and '", endDate, "')",
                " and c.comp_id in (");

        StringBuilder sqlSb = new StringBuilder(basicSql);
        for (String ele : compIdSet) {
            sqlSb.append(ele).append(",");
        }
        String sql = sqlSb.substring(0, sqlSb.length() - 1) + ")";

        return jdbcTemplate.queryForObject(sql, Long.class);
    }


}
