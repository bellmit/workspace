package org.jumao.bi.dao.jrt.impl;

import com.google.gson.JsonArray;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.jrt.JrtFinancingDao;
import org.jumao.bi.dao.trade.impl.GeneralBasicDao;
import org.jumao.bi.entites.jrt.financing.FiApplyPo;
import org.jumao.bi.entites.jrt.financing.FiRequiredPo;
import org.jumao.bi.entites.jrt.financing.FiSignedPo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.Verifier;
import org.jumao.bi.utis.constants.Table;
import org.jumao.bi.utis.enums.JrtFTType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(JrtFinancingDaoImpl.Jrt_Financing_Dao)
public class JrtFinancingDaoImpl extends GeneralBasicDao implements JrtFinancingDao {

    private Logger logger = Logger.getLogger(JrtFinancingDaoImpl.class);

    public static final String Jrt_Financing_Dao = "jrtFinancingDao";

    @Autowired
    protected JdbcTemplate jdbcTemplate;



    @Override
    public List<TimeTotalVo> getTrendLineChartBy(String platform, String startDate, String endDate, boolean accurateToHour, int type) throws Exception {
        if (Verifier.isEfficStrs(startDate, endDate)) {
            startDate = dealStartDate(startDate);
            endDate = dealEndDate(endDate);
        }

        String sql = null;
        JrtFTType jrtEnum = JrtFTType.getByType(type);

        switch (jrtEnum) {
            case RZSQ:
                sql = getSqlOfGroupByCrtTm(Table.Jrt_Apply, countAll,
                        "delete_flag = 0", startDate, endDate, accurateToHour);
                break;
            case RSXQ:
                sql = getSqlOfGroupByCrtTm(Table.Jrt_Req, countAll,
                        "delete_flag = 0", startDate, endDate, accurateToHour);
                break;
            case RZQYue:
                sql = getSqlOfGroupByCrtTm(Table.Jrt_Apply, countAll,
                        "delete_flag = 0 and status = 4", startDate, endDate, accurateToHour);
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
    public JsonArray getApplyDetail(int limit, int offset) {
        String sql = StringUtils.joinStr("select create_time, apply_no, company_name, product_name,",
                " org_company_name, amount_app, month_app from ", Table.Jrt_Apply, " where delete_flag = 0",
                getOrderAndLimitAndOffsetCondi(limit, offset, ""));

        return getJsonArray(sql, FiApplyPo.class);
    }

    @Override
    public long getApplyTotalCounts() {
        String sql = StringUtils.joinStr("select count(*) from ", Table.Jrt_Apply,
                " where delete_flag = 0");

        return jdbcTemplate.queryForObject(sql, Long.class);
    }


    @Override
    public JsonArray getRequiredDetail(int limit, int offset) {
        String sql = StringUtils.joinStr("select create_time," +
                " req_no, name, company_name, amount_app, month_app from ",
                Table.Jrt_Req, " WHERE delete_flag = 0",
                getOrderAndLimitAndOffsetCondi(limit, offset, ""));

        return getJsonArray(sql, FiRequiredPo.class);
    }

    @Override
    public long getRequiredTotalCounts() {
        String sql = StringUtils.joinStr("select count(*) from ",
                Table.Jrt_Req, " WHERE delete_flag = 0");

        return jdbcTemplate.queryForObject(sql, Long.class);
    }


    @Override
    public JsonArray getSignedDetail(int limit, int offset) {
        String sql = StringUtils.joinStr("select create_time,",
                " loan_no, contract_no, company_name, product_name, org_company_name,",
                " reply_amount, replay_term from ", Table.Jrt_Apply,
                " where status = 4 and delete_flag = 0",
                getOrderAndLimitAndOffsetCondi(limit, offset, ""));

        return getJsonArray(sql, FiSignedPo.class);
    }

    @Override
    public long getSignedTotalCounts() {
        String sql = StringUtils.joinStr("select count(*) from ", Table.Jrt_Apply,
                " where status = 4 and delete_flag = 0");

        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
