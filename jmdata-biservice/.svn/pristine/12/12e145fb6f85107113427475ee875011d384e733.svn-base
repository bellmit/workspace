package org.jumao.bi.dao.enjrt.impl;

import com.google.gson.JsonArray;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.enjrt.EnJrtFinancingDao;
import org.jumao.bi.dao.trade.impl.GeneralBasicDao;
import org.jumao.bi.entites.jrt.financing.EnFiApplyPo;
import org.jumao.bi.entites.jrt.financing.EnFiCompanyPo;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartServiceHelper;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.Verifier;
import org.jumao.bi.utis.comparator.EnFiCompanyPoComp;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.jumao.bi.utis.enums.EnJrtFTType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository(EnJrtFinancingDaoImpl.EN_Jrt_Financing_Dao)
public class EnJrtFinancingDaoImpl extends GeneralBasicDao implements EnJrtFinancingDao {

    private Logger logger = Logger.getLogger(EnJrtFinancingDaoImpl.class);

    public static final String EN_Jrt_Financing_Dao = "enJrtFinancingDao";

    private EnFiCompanyPoComp enFiCompanyPoComp = new EnFiCompanyPoComp();

    @Autowired
    protected JdbcTemplate jdbcTemplate;


    @Override
    public List<TimeTotalVo> getTrendLineChartBy(String platform, String startDate, String endDate, boolean accurateToHour, int type) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = null;
        EnJrtFTType enJrtEnum = EnJrtFTType.getByType(type);

        switch (enJrtEnum) {
            case RZSQ:
                sql = getSqlOfGroupByCrtTm(Table.En_Jrt_Apply, countAll,
                        "delete_flag = 0", startDate, endDate, accurateToHour);
                break;
            case RZQYue:
                sql = getSqlOfGroupByCrtTm(Table.En_Jrt_Apply, countAll,
                        "delete_flag = 0 and status = 3", startDate, endDate, accurateToHour);
                break;
            case RZQYe:
                sql = getSqlOfGroupByCrtTm(Table.En_Jrt_Base_Company, countAll,
                        "identity = '0' and code_status = '2' and delete_flag = '0'", startDate, endDate, accurateToHour);
                break;

            case SJCP:
                sql = getSqlOfGroupByCrtTm(Table.En_Jrt_Product, countAll,
                        "`status` = 4 and delete_flag = 0", startDate, endDate, accurateToHour);
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
    public List<GroupByVo> getGlobalAreaDistBy(String startDate, String endDate, int type) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = null;
        EnJrtFTType enJrtEnum = EnJrtFTType.getByType(type);

        switch (enJrtEnum) {
            case RZSQ:
                sql = getAreaDistSqlForRZSQ(startDate, endDate);
                break;
            case RZQYe:
                sql = getAreaDistSqlForRZQYe(startDate, endDate);
                break;

            default:
                logger.error(StringUtils.joinStr(
                        GeneralUtils.getCurrMethod(), " get a undefined type:", type
                ));
                break;
        }

        if (!Verifier.isEffectiveStr(sql)) {
            return new ArrayList<GroupByVo>();
        }
        return getVoList(sql, GroupByVo.class);
    }


    private String getAreaDistSqlForRZSQ(String startDate, String endDate) {
        String dateRange = getDateRangeBy(startDate, endDate, "bc.");

        return StringUtils.joinStr("select ae.area_name as ", Key.TYPE_NAME,
                ", cast(ae.area_code as int) as ", Key.Type,", count(distinct ja.apply_no) as ", Key.TOTAL,
                " from ", Table.En_Jrt_Base_Company, " bc, ", Table.Public_Area_En, " ae, ", Table.En_Jrt_Apply, " ja",
                " where cast(bc.country as string) = ae.area_code",
                " and cast(ja.company_id as string) = bc.id",
                " and ja.delete_flag = 0",
                " and bc.delete_flag = '0'",
                " and bc.code_status = '2' and",
                dateRange,
                " group by ae.area_code, ae.area_name");
    }

    @Override
    public JsonArray getApplyDetail(long areaId, String startDate, String endDate, int limit, int offset) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);
        String dateRange = getDateRangeBy(startDate, endDate, "ja.");

        String sql = StringUtils.joinStr("select ja.create_time, ja.apply_no,",
                " ja.company_name, ja.product_name, ja.org_company_name, ja.amount",
                " from ", Table.En_Jrt_Apply, " ja, ", Table.En_Jrt_Base_Company, " bc",
                " where bc.country = ", areaId,
                " and ja.country = ", areaId,
                " and cast(ja.company_id as string) = bc.id",
                " and ja.delete_flag = 0",
                " and bc.delete_flag = '0'",
                " and bc.code_status = '2' and",
                dateRange,
                getOrderAndLimitAndOffsetCondi(limit, offset, "ja."));

        return getJsonArray(sql, EnFiApplyPo.class);
    }

    private String getAreaDistSqlForRZQYe(String startDate, String endDate) {
        String dateRange = getDateRangeBy(startDate, endDate, "bc.");

        return StringUtils.joinStr("select ae.area_name as ", Key.TYPE_NAME,
                ", cast(ae.area_code as int) as ", Key.Type,", count(distinct bc.id) as ", Key.TOTAL,
                " from ", Table.En_Jrt_Base_Company, " bc, ", Table.Public_Area_En, " ae, ", Table.En_Jrt_Apply, " ja",
                " where cast(bc.country as string) = ae.area_code",
                " and bc.country = ja.country",
                " and bc.identity = '0'",
                " and bc.code_status = '2'",
                " and bc.delete_flag = '0'",
                " and ja.reply_amount is not null and",
                dateRange,
                " group by ae.area_code, ae.area_name");
    }


    @Override
    public List<EnFiCompanyPo> getCompanyDetail(long areaId, String startDate, String endDate, int limit, int offset) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String dateRange = getDateRangeBy(startDate, endDate, "bc.");

        String sql = StringUtils.joinStr("select min(ja.create_time) as create_time, ja.company_id as id, ja.company_name,",
                " sum(ja.amount) as amount, count(ja.id) as apply_counts, sum(ja.reply_amount) as reply_amount",
                " from ", Table.En_Jrt_Base_Company, " bc, ", Table.Public_Area_En, " ae, ", Table.En_Jrt_Apply, " ja",
                " where bc.country = ", areaId,
                " and ja.country = ", areaId,
                " and bc.identity = '0'",
                " and bc.code_status = '2'",
                " and bc.delete_flag = '0'",
                " and ja.reply_amount is not null and",
                dateRange,
                " group by ja.company_id, ja.company_name");
//        getOrderAndLimitAndOffsetCondi(limit, offset, "ja.")

        List<EnFiCompanyPo> list = getVoList(sql, EnFiCompanyPo.class);
        for (EnFiCompanyPo ele : list) {
            ele.setAmount(Double.parseDouble(
                    ChartServiceHelper.numDivideSth(ele.getAmount(), Key.Num10000)
            ));
            ele.setReply_amount(Double.parseDouble(
                    ChartServiceHelper.numDivideSth(ele.getReply_amount(), Key.Num10000)
            ));
        }
        Collections.sort(list, enFiCompanyPoComp);
        return list;
    }


    @Override
    public long getApplyTotalCounts(long areaId, String startDate, String endDate) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);
        String dateRange = getDateRangeBy(startDate, endDate, "ja.");

        String sql = StringUtils.joinStr("select count(ja.id)",
                " from ", Table.En_Jrt_Apply, " ja, ", Table.En_Jrt_Base_Company, " bc",
                " where ja.delete_flag = 0",
                " and cast(ja.company_id as string) = bc.id and",
                dateRange,
                " and bc.country = ", areaId);

        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public long getCompanyTotalCounts(long areaId, String startDate, String endDate) {
//        startDate = dealStartDate(startDate);
//        endDate = dealEndDate(endDate);
        return 0;
    }


}
