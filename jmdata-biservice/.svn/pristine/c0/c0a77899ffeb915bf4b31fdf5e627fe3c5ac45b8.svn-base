package org.jumao.bi.dao.trade.impl;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.trade.TradeRegisterDao;
import org.jumao.bi.entites.trade.register.DataSrcIncrTable;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository(TradeRegisterDaoImpl.Register_Dao)
public class TradeRegisterDaoImpl extends GeneralBasicDao implements TradeRegisterDao {

    private Logger logger = Logger.getLogger(TradeRegisterDaoImpl.class);
    public static final String Register_Dao = "tradeRegisterDao";
    private String Trade_Company_Count_Target = "DISTINCT comp_id";


    /**
     * @param platform 若是 platform不等于 GeneralBasicDao.allThePlatform，表示只取 子平台 的注册数据；
     *                 若是 platform 等于 GeneralBasicDao.allThePlatform，表示 交易全平台 的 11 个服务平台，如：有色，加上 总站。
     */
    public List<TimeTotalVo> getNewlyIncrBy(String table, String platform, String startDate,
                                            String endDate, boolean accurateToHour) throws Exception {
        platform = checkSpecialPlatformId(platform);
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String tmGrpLen = getCrtTmGroupLength(accurateToHour);
        String sql = StringUtils.joinStr(
                "select ", tmGrpLen, " as ", Key.CREATE_TIME,
                ", count(distinct id) as ", Key.TOTAL, " from ", table, " where ",
                platform == null ? getCondiForAllPlatform() : "platform = '" + platform + "'",
                " and (create_time between '", startDate, "' and '", endDate, "') ",
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
    public List<GroupByVo> getDataSrcIncrBy(String table, String platform, String startDate, String endDate) throws Exception {
        platform = checkSpecialPlatformId(platform);
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = StringUtils.joinStr(
                "select data_source_type as ", Key.Type,
                ", count(distinct id) as ", Key.TOTAL, " from ", table, " where ",
                platform == null ? getCondiForAllPlatform() : "platform = '" + platform + "'",
                " and (create_time between '", startDate, "' and '", endDate, "') group by data_source_type");

        return getVoList(sql, GroupByVo.class);
    }


    /**
     * @param platform
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public List<GroupByVo> getMsUserIncrPieBy(String platform, String startDate, String endDate) throws Exception {
        platform = checkSpecialPlatformId(platform);
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = StringUtils.joinStr(
                "select CAST(platform as int) as ", Key.Type, ", count(distinct id) as ", Key.TOTAL,
                " from ", Table.Uc_Jumore_User, " where ",
                platform == null ? getCondiForAllPlatform() : "platform = '" + platform + "'",
                " and (create_time between '", startDate, "' and '", endDate, "') group by platform");

        return getVoList(sql, GroupByVo.class);
    }


    /**
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public long getTotalUserCounts(String platform, String startDate, String endDate) throws Exception {
        platform = checkSpecialPlatformId(platform);
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = StringUtils.joinStr("select count(*) from ", Table.Uc_Jumore_User, " where ",
                platform == null ? getCondiForAllPlatform() : "platform = '" + platform + "'");
        return jdbcTemplate.queryForObject(sql, Long.class);
    }


    /**
     * 此处 sql 和 getSqlOfGroupByCrtTm(...) 区别较大
     */
    public List<DataSrcIncrTable> getDataSrcIncrTableBy(String platform, String startDate, String endDate, boolean accurateToHour, int limit, int offset) throws Exception {
        platform = checkSpecialPlatformId(platform);
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String tmGrpLen = getCrtTmGroupLength(accurateToHour);
        String sql = StringUtils.joinStr("select ", tmGrpLen, " as ",
                Key.CREATE_TIME, ", data_source_type as ", Key.Type,
                ", count(distinct id) as ", Key.TOTAL, " from ", Table.Uc_Jumore_User, " where ",
                platform == null ? getCondiForAllPlatform() : "platform = '" + platform + "'",
                " and (create_time between '", startDate, "' and '", endDate, "')",
                " group by ", Key.CREATE_TIME, ", data_source_type",
                " order by ", Key.CREATE_TIME, " desc");
        //" limit ", limit, " offset ", offset

        return getVoList(sql, DataSrcIncrTable.class);
    }


    /**
     * @param platform 需要6位
     * @param startDate e.g. 20170707
     * @param endDate e.g. 20170708
     */
    public List<TimeTotalVo> getPvOrUvBy(String platform, String startDate, String endDate, boolean ispv) throws Exception {
        platform = dealPlatform(platform, Len6);
        String v = ispv ? "pv" : "uv";

        String sql = StringUtils.joinStr(
                "select create_date as ", Key.CREATE_TIME,
                ", ", v, " as ", Key.TOTAL_STR, " from ", Table.Baidu_Daily_Stat,
                " where platform_id = '", platform, "' and",
                " (create_date between '", startDate, "' and '", endDate, "') ",
                " order by ", Key.CREATE_TIME, " asc");

        List<TimeTotalVo> list = getVoList(sql, TimeTotalVo.class);
        for (TimeTotalVo ele : list) {
            String totalStr = ele.getTotalStr();
            if (NumberUtils.isDigits(totalStr)) {
                ele.setTotal(Long.parseLong(totalStr));
            } else {
                ele.setTotal(0L);
            }
        }
        return list;
    }

    /**
     * 三证审核(认证审核)通过占比
     * 企业不区分 platform
     */
    public List<TimeTotalVo> getCert3AuthBy(String startDate, String endDate, boolean accurateToHour) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String firstCondi = "valid_status = 2 and delete_flag = 0";
        String sql = getSqlOfGroupByCrtTm(Table.Trade_Company, Trade_Company_Count_Target, firstCondi, startDate, endDate, accurateToHour);
        return getVoList(sql, TimeTotalVo.class);
    }


    /**
     * 授权书
     */
    public List<TimeTotalVo> getAuthLetterBy(String startDate, String endDate, boolean accurateToHour) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String firstCondi = "license_file is not null and delete_flag = 0";
        String sql = getSqlOfGroupByCrtTm(Table.Trade_Company, Trade_Company_Count_Target, firstCondi, startDate, endDate, accurateToHour);
        return getVoList(sql, TimeTotalVo.class);
    }

    /**
     * @param platform
     * @return
     */
    public List<GroupByVo> getAuthUCert3HistoryBy(String platform) {
        platform = dealPlatform(platform, Len4);

        String sql = StringUtils.joinStr(
                "select comp_category as ", Key.Type,
                ", count(distinct comp_id) as ", Key.TOTAL, " from ", Table.Trade_Company,
                " where delete_flag = 0 and valid_status = 2 group by comp_category");

        return getVoList(sql, GroupByVo.class);
    }

    /**
     * @param platform
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public List<GroupByVo> getLicPercentPieBy(String platform, String startDate, String endDate) throws Exception {
        platform = dealPlatform(platform, Len4);
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        //select lic_type, count(DISTINCT id) from uc_company
        //where create_time xx group by lic_type
        String sql = StringUtils.joinStr(
                "select lic_type as ", Key.Type, ", count(distinct id) as ", Key.TOTAL,
                " from ", Table.Uc_Company,
                " where delete_flag = 0 and (create_time between '", startDate, "' and '", endDate, "')",
                " group by lic_type");

        return getVoList(sql, GroupByVo.class);
    }

    /**
     * @param startDate
     * @param endDate
     * @param topEle
     * @return
     * @throws Exception
     */
    public List<GroupByVo> getCert3AndAuthLetterAreaPieBy(String startDate, String endDate, int topEle) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String firstCondi = "c.valid_status = 2 and c.license_file is not null";
        String sql = getSqlOfGroupByTradeCompayArea(firstCondi, startDate, endDate, topEle);

        List<GroupByVo> list = getVoList(sql, GroupByVo.class);
        Collections.sort(list, groupByVoComp);
        return list;
    }

    /**
     * 签章开通数的 TimeTotalVo
     */
    public List<TimeTotalVo> getVisaTTVsBy(String platform, String startDate, String endDate, boolean accurateToHour) throws Exception {
        platform = dealPlatform(platform, Len4);
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String firstCondi = "signature_status = 1 and delete_flag = 0";
        String sql = getSqlOfGroupByCrtTm(Table.Trade_Company, Trade_Company_Count_Target, firstCondi, startDate, endDate, accurateToHour);
        return getVoList(sql, TimeTotalVo.class);
    }

    /**
     * @param platform
     * @param startDate
     * @param endDate
     * @param accurateToHour
     * @return
     * @throws Exception
     */
    public List<TimeTotalVo> getPayTTVsBy(String platform, String startDate, String endDate, boolean accurateToHour) throws Exception {
        platform = dealPlatform(platform, Len4);
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String firstCondi = "pay_status = 1 and delete_flag = 0";
        String sql = getSqlOfGroupByCrtTm(Table.Trade_Company, Trade_Company_Count_Target, firstCondi, startDate, endDate, accurateToHour);
        return getVoList(sql, TimeTotalVo.class);
    }

    /**
     * @param platform
     * @return
     */
    public long getVisaHisTotal(String platform) {
        platform = dealPlatform(platform, Len4);

        String sql = StringUtils.joinStr(
                "select count(DISTINCT comp_id) from ", Table.Trade_Company,
                " where delete_flag = 0 and signature_status = 1");

        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    /**
     * @param platform
     * @return
     */
    public long getPayHisTotal(String platform) {
        platform = dealPlatform(platform, Len4);

        String sql = StringUtils.joinStr(
                "select count(DISTINCT comp_id) from ", Table.Trade_Company,
                " where delete_flag = 0 and pay_status = 1");

        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    /**
     * @param platform
     * @return
     */
    public List<GroupByVo> getVisaAndPayHistoryPieBy(String platform) {
        platform = dealPlatform(platform, Len4);

        String sql = StringUtils.joinStr("select ",
                "CAST(c.pay_channel_no as int) as ", Key.Type,
                ", c.pay_channel_name as ", Key.TYPE_NAME,
                ", count(distinct t.company_id) as ", Key.TOTAL,
                " from ", Table.Ep_User_Channel, " t, ", Table.Epay_Channel, " c",
                " where t.channel_no = c.pay_channel_no",
                " group by c.pay_channel_no, c.pay_channel_name");

        return getVoList(sql, GroupByVo.class);
    }

    /**
     * @param platform
     * @param startDate
     * @param endDate
     * @param topEle
     * @param isVisa
     * @return
     * @throws Exception
     */
    public List<GroupByVo> getVisaOrPayAreaDistBy(String platform, String startDate, String endDate,
                                                  int topEle, boolean isVisa) throws Exception {
        platform = dealPlatform(platform, Len4);
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String visaOrPayCondi = null;
        if (isVisa) {
            visaOrPayCondi = "c.signature_status = 1";
        } else {
            visaOrPayCondi = "c.pay_status = 1";
        }

        String sql = getSqlOfGroupByTradeCompayArea(visaOrPayCondi, startDate, endDate, topEle);

        List<GroupByVo> list = getVoList(sql, GroupByVo.class);
        Collections.sort(list, groupByVoComp);
        return list;
    }




}
