package org.jumao.bi.dao.enjrt.impl;

import com.google.gson.JsonArray;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.enjrt.EnNetFlowDao;
import org.jumao.bi.dao.trade.impl.GeneralBasicDao;
import org.jumao.bi.entites.en.EnBasicVisitPo;
import org.jumao.bi.entites.en.EnCountryDistVo;
import org.jumao.bi.entites.en.EnNetFlowSourceVo;
import org.jumao.bi.entites.en.TotalPvUvVo;
import org.jumao.bi.entites.jrt.financing.EnNetflowOverview;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartServiceHelper;
import org.jumao.bi.utis.EnPlatformutils;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.Verifier;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.jumao.bi.utis.enums.EnJrtNfLine;
import org.jumao.bi.utis.enums.EnJrtNfSm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Repository(EnNetFlowDaoImpl.EN_NET_FLOW_DAO)
public class EnNetFlowDaoImpl extends GeneralBasicDao implements EnNetFlowDao {

    public static final String EN_NET_FLOW_DAO = "enNetFlowDao";

    private Logger logger = Logger.getLogger(EnNetFlowDaoImpl.class);

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public List<EnNetflowOverview> getEnNetflowOverview(String platform, String startDate, String endDate) {
        platform = dealPlatform(platform, Len6);

        String sql = StringUtils.joinStr("select sum(pv) as pv, sum(uv) as uv,",
                " avg(bounce_rate) as bounce_rate, avg(avg_session_dur) as avg_session_dur,",
                " sum(users) as users, sum(new_users) new_users",
                " from ", Table.Ga_Basic_Visit,
                " where platform_id = '", platform, "' and",
                " (create_time between '", startDate, "' and '", endDate, "')");

        return getVoList(sql, EnNetflowOverview.class);
    }


    @Override
    public List<TimeTotalVo> getTrendBy(String platform, String startDate, String endDate, boolean accurateToHour, int type) {
        String sql = null;
        EnJrtNfLine enJrtEnum = EnJrtNfLine.getByType(type);
        boolean isNoHourLen8 = true;
        String firstCondi = getPlatformIdCondi(platform);

        switch (enJrtEnum) {
            case PV:
                sql = getSqlOfGroupByCrtTm(Table.Ga_Basic_Visit, getSumSql(Key.Pv),
                        firstCondi, startDate, endDate, accurateToHour, isNoHourLen8);
                break;
            case UV:
                sql = getSqlOfGroupByCrtTm(Table.Ga_Basic_Visit, getSumSql(Key.Uv),
                        firstCondi, startDate, endDate, accurateToHour, isNoHourLen8);
                break;
            case AVG_SESSION_DUR:
                sql = getSqlOfGroupByCrtTm(Table.Ga_Basic_Visit, getAvgSql(Key.Avg_Session_Dur_),
                        firstCondi, startDate, endDate, accurateToHour, isNoHourLen8);
                break;

            case AVG_PV:
                String avgUvtarget = StringUtils.joinStr(Key.Pv, "/", Key.Users);
                sql = getSqlOfGroupByCrtTm(Table.Ga_Basic_Visit, getAvgSql(avgUvtarget),
                        firstCondi, startDate, endDate, accurateToHour, isNoHourLen8);
                break;

            case NEW_USERS:
                sql = getSqlOfGroupByCrtTm(Table.Ga_Basic_Visit, getSumSql(Key.New_Users_),
                        firstCondi, startDate, endDate, accurateToHour, isNoHourLen8);
                break;
            case BOUNCE_RATE:
                sql = getSqlOfGroupByCrtTm(Table.Ga_Basic_Visit, getAvgSql(Key.Bounce_Rate_),
                        firstCondi, startDate, endDate, accurateToHour, isNoHourLen8);
                break;
            case SESSIONS:
                sql = getSqlOfGroupByCrtTm(Table.Ga_Basic_Visit, getSumSql(Key.Sessions),
                        firstCondi, startDate, endDate, accurateToHour, isNoHourLen8);
                break;

            case OLD_USERS:
                sql = getSqlOfGroupByCrtTm(Table.Ga_Basic_Visit, getSumSql(Key.Users+"-"+Key.New_Users_),
                        firstCondi, startDate, endDate, accurateToHour, isNoHourLen8);
                break;

            default:
                logger.error(StringUtils.joinStr(
                        GeneralUtils.getCurrMethod(), " get a undefined type:", type
                ));
                break;
        }

        if (!Verifier.isEffectiveStr(sql)) {
            return new ArrayList<TimeTotalVo>();
        }
        return getVoList(sql, TimeTotalVo.class);
    }


    @Override
    public List<EnBasicVisitPo> getEnBasicVisitPos(String platform, String startDate, String endDate,
                                                   String orderTarget, String orderType) {
        //platform = dealPlatform(platform, Len6);
        String dateRange = getDateRangeBy(startDate, endDate, "");

        String sql = StringUtils.joinStr("select platform_id as name, sum(pv) as pv, sum(sessions) as sessions,",
                " sum(uv) as uv, avg(bounce_rate) as bounce_rate_d, avg(avg_session_dur) as avg_session_dur",
                " from ", Table.Ga_Basic_Visit,
                " where ", dateRange,
                " group by platform_id order by ", orderTarget, " ", checkOrderType(orderType));

        List<EnBasicVisitPo> list = getVoList(sql, EnBasicVisitPo.class);

        for (EnBasicVisitPo ele : list) {
            String platformId = ele.getName();
            ele.setName(EnPlatformutils.idEnNameMap.getOrDefault(platformId, platformId));

            ele.setBounce_rate(
                    ChartServiceHelper.getDoublePercent(ele.getBounce_rate_d())
            );
            ele.setBounce_rate_d(null);

            ele.setAvg_session_dur(
                    Double.parseDouble(
                            ChartServiceHelper.siSheWuRuAfterDot(
                                    String.valueOf(ele.getAvg_session_dur())
                            )
                    )
            );
        }

        return list;
    }


    @Override
    public List<GroupByVo> getNetflowPvTopXBy(String platform, String startDate, String endDate, int topEle) {
        platform = dealPlatform(platform, Len6);
        String firstCondi = getPlatformIdCondi(platform);

        String sql = getSqlOfTypeAndTotal(Key.Landing_Page_Path_, Key.Landing_Page_Path_, getSumSql(Key.Uv),
                Table.Ga_Entrances_Page, firstCondi, startDate, endDate, Key.Landing_Page_Path_, topEle);

        return getVoList(sql, GroupByVo.class);
    }


    @Override
    public List<GroupByVo> getNetflowBounceTopXBy(String platform, String startDate, String endDate, int topEle) {
        platform = dealPlatform(platform, Len6);
        String firstCondi = getPlatformIdCondi(platform);

        String sql = getSqlOfTypeAndTotal(Key.Landing_Page_Path_, Key.Landing_Page_Path_, getSumSql(Key.Uv+"*"+Key.Bounce_Rate_),
                Table.Ga_Entrances_Page, firstCondi, startDate, endDate, Key.Landing_Page_Path_, topEle);

        return getVoList(sql, GroupByVo.class);
    }


    @Override
    public List<GroupByVo> getGlobalAreaDistPvBy(String platform, String startDate, String endDate) {
        platform = dealPlatform(platform, Len6);
        String firstCondi = getPlatformIdCondi(platform);

        String sql = getSqlOfTypeAndTotal(Key.Country, Key.Country, getSumSql(Key.Pv),
                Table.Ga_Country, firstCondi, startDate, endDate, Key.Country, No_Limit);

        return getVoList(sql, GroupByVo.class);
    }

    @Override
    public TotalPvUvVo getTotalPvUvBy(String platform, String startDate, String endDate) {
        String dateRange = getDateRangeBy(startDate, endDate, "");

        platform = dealPlatform(platform, Len6);
        String firstCondi = getPlatformIdCondi(platform);

        String sql = StringUtils.joinStr("select count(distinct country) as totalCounts, sum(pv) as pv, sum(uv) as uv from ",
                Table.Ga_Country, " where ", firstCondi, " and ", dateRange);

        return getVoList(sql, TotalPvUvVo.class).get(0);
    }


    @Override
    public JsonArray getGlobalAreaDistTableDataBy(String platform, String startDate, String endDate,
        String orderTarget, int limit, int offset, String orderType, TotalPvUvVo totalPvUvvo) {
        String dateRange = getDateRangeBy(startDate, endDate, "");

        platform = dealPlatform(platform, Len6);
        String firstCondi = getPlatformIdCondi(platform);

        String sql = StringUtils.joinStr("select country, sum(pv) as pv, sum(uv) as uv,",
                " avg(bounce_rate) as bounce_rate from ", Table.Ga_Country,
                " where ", firstCondi, " and ", dateRange,
                " group by country order by ", orderTarget, " ", checkOrderType(orderType),
                " limit ", limit, " offset ", offset);

        List<EnCountryDistVo> list = getVoList(sql, EnCountryDistVo.class);

        for (EnCountryDistVo ele : list) {
            Double pv = ele.getPv(); if (pv == null) pv = 0D;
            Double uv = ele.getUv(); if (uv == null) uv = 0D;

            Double tpv = totalPvUvvo.getPv(); if (tpv == null) tpv = 1D;
            Double tuv = totalPvUvvo.getUv(); if (tuv == null) tuv = 1D;

            ele.setPv_percent(ChartServiceHelper.getDoublePercent(pv / tpv * 100));
            ele.setUv_percent(ChartServiceHelper.getDoublePercent(uv / tuv * 100));

            ele.setBounce_rate(ChartServiceHelper.getDoublePercent(ele.getBounce_rate()));
        }

        return gson.toJsonTree(list).getAsJsonArray();
    }


    @Override
    public List<GroupByVo> getSourceMediumUvBy(String platform, String startDate, String endDate) {
        platform = dealPlatform(platform, Len6);
        String firstCondi = getPlatformIdCondi(platform);

        String sql = getSqlOfTypeAndTotal(Key.Type, Key.Type, getSumSql(Key.Uv),
                Table.Ga_Source_Medium, firstCondi, startDate, endDate, Key.Type, No_Limit);

        return getVoList(sql, GroupByVo.class);
    }


    @Override
    public List<TimeTotalVo> getSourceTypeBy(String platform, String startDate, String endDate,
                                             boolean accurateToHour, int type) {
        String sql = null;
        EnJrtNfSm enJrtEnum = EnJrtNfSm.getByType(type);
        boolean isNoHourLen8 = true;
        StringBuilder firstCondiSb = new StringBuilder(getPlatformIdCondi(platform));

        switch (enJrtEnum) {
            case ORGANIC:
                firstCondiSb.append(" and type = '").append(Key.Organic).append("'");
                sql = getSqlOfGroupByCrtTm(Table.Ga_Source_Medium, getSumSql(Key.Uv),
                        firstCondiSb.toString(), startDate, endDate, accurateToHour, isNoHourLen8);
                break;

            case DIRECT:
                firstCondiSb.append(" and type = '").append(Key.None).append("'");
                sql = getSqlOfGroupByCrtTm(Table.Ga_Source_Medium, getSumSql(Key.Uv),
                        firstCondiSb.toString(), startDate, endDate, accurateToHour, isNoHourLen8);
                break;

            case REFERRAL:
                firstCondiSb.append(" and type = '").append(Key.Referral).append("'");
                sql = getSqlOfGroupByCrtTm(Table.Ga_Source_Medium, getSumSql(Key.Uv),
                        firstCondiSb.toString(), startDate, endDate, accurateToHour, isNoHourLen8);
                break;

            default:
                logger.error(StringUtils.joinStr(
                        GeneralUtils.getCurrMethod(), " get a undefined type:", type
                ));
                break;
        }

        if (!Verifier.isEffectiveStr(sql)) {
            return new ArrayList<TimeTotalVo>();
        }
        return getVoList(sql, TimeTotalVo.class);
    }


    @Override
    public JsonArray getSourceTypeTableDataBy(String platform, String startDate, String endDate,
                                              String orderTarget, String orderType) {
        platform = dealPlatform(platform, Len6);
        String firstCondi = getPlatformIdCondi(platform);

        String sql = StringUtils.joinStr("select type, sum(pv) as pv, sum(uv) as uv,",
                " avg(bounce_rate) as bounce_rate from ", Table.Ga_Source_Medium,
                " where ", firstCondi,
                " group by type order by ", orderTarget, " ", checkOrderType(orderType));

        List<EnNetFlowSourceVo> list = getVoList(sql, EnNetFlowSourceVo.class);
        int totalPv = 0;
        int totalUv = 0;
        double totalBounceRate = 0D;

        for (EnNetFlowSourceVo ele : list) {
            totalPv += ele.getPv();
            totalUv += ele.getUv();
            totalBounceRate += Double.parseDouble(ele.getBounce_rate());

            ele.setType(ChartServiceHelper.sourceTypeNameMap.getOrDefault(ele.getType(), ChartServiceHelper.ELSE_NAME));
            ele.setBounce_rate(ChartServiceHelper.getDoublePercent(ele.getBounce_rate()));
        }

        totalBounceRate = totalBounceRate / list.size();
        String totalBrStr = ChartServiceHelper.getDoublePercent(String.valueOf(totalBounceRate));
        list.add(new EnNetFlowSourceVo(CN.Curr_Total, totalPv, totalUv, totalBrStr));

        return gson.toJsonTree(list).getAsJsonArray();
    }


    @Override
    public List<GroupByVo> getEntrancesPageTop10By(String platform, String startDate, String endDate, int topEle) {
        platform = dealPlatform(platform, Len6);
        String firstCondi = getPlatformIdCondi(platform);

        String sql = getSqlOfTypeAndTotal(Key.Landing_Page_Path_, "", getSumSql(Key.Pv),
                Table.Ga_Entrances_Page, firstCondi, startDate, endDate, Key.Landing_Page_Path_, topEle);

        return getVoList(sql, GroupByVo.class);
    }


    @Override
    public List<GroupByVo> getVisitedPageTop10By(String platform, String startDate, String endDate, int topEle) {
        platform = dealPlatform(platform, Len6);
        String firstCondi = getPlatformIdCondi(platform);
        String grpTarget = StringUtils.joinStr(Key.Hostname, ", ", Key.Page_Path_);
        String typeSql = StringUtils.joinStr("concat(", grpTarget, ")");

        String sql = getSqlOfTypeAndTotal(typeSql, "", getSumSql(Key.Pv),
                Table.Ga_Visited_Page, firstCondi, startDate, endDate, grpTarget, topEle);

        return getVoList(sql, GroupByVo.class);
    }


    @Override
    public List<GroupByVo> getMediumTop10By(String platform, String startDate, String endDate, int topEle) {
        platform = dealPlatform(platform, Len6);
        String firstCondi = StringUtils.joinStr(getPlatformIdCondi(platform), " and type in ('",
                Key.Referral, "', '", Key.Organic, "')");

        String sql = getSqlOfTypeAndTotal(Key.Source, "", getSumSql(Key.Pv),
                Table.Ga_Source_Medium, firstCondi, startDate, endDate, Key.Source, topEle);

        return getVoList(sql, GroupByVo.class);
    }

}
