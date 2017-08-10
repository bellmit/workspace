package org.jumao.bi.dao.jyt.impl;

/**
 * @author chen qian
 */

import org.jumao.bi.dao.jyt.JytRegIncrAndAuthDao;
import org.jumao.bi.dao.trade.impl.GeneralBasicDao;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.CN;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.jumao.bi.utis.constants.Table.Lg_Company;

@Repository(JytRegIncrAndAuthDaoImpl.Jyt_Reg_Incr_And_Auth_Dao)
public class JytRegIncrAndAuthDaoImpl extends GeneralBasicDao implements JytRegIncrAndAuthDao {

    public static final String Jyt_Reg_Incr_And_Auth_Dao = "jytRegIncrAndAuthDao";


    @Override
    public List<GroupByVo> getRegAreaDist(String startDate, String endDate, int topLimit) throws Exception {
        String firstCondi = "code_status = '2'";
        return getGroupByVoFromLgCompany(firstCondi, startDate, endDate, topLimit);
    }

    @Override
    public List<GroupByVo> getNewRegPieChartBy(String startDate, String endDate, int topLimit) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = StringUtils.joinStr(
                "select platform as ", Key.Type,
                ", count(id) as ", Key.TOTAL, " from ", Table.Lg_Base_Company_User,
                " where",
                " (create_time between '", startDate, "' and '", endDate, "') group by ", Key.Type,
                topLimit > 0 ? StringUtils.joinStr(" order by ", Key.TOTAL, " desc limit ", topLimit) : "");

        return getVoList(sql, GroupByVo.class);
    }

    @Override
    public List<TimeTotalVo> getSmrzBy(String startDate, String endDate, boolean accurateToHour) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String firstCondif = "id_card_review_status = 2";
        String sql = getSqlOfGroupByCrtTm(Table.Lg_Base_Company_User, countStarChar, firstCondif, startDate, endDate, accurateToHour);
        return getVoList(sql, TimeTotalVo.class);
    }

    @Override
    public List<TimeTotalVo> getWtfszrzBy(String startDate, String endDate, boolean accurateToHour) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String firstCondif = "is_virtual_company = 1 AND code_status = '2' AND identity = '0' and delete_flag = '0'";
        String sql = getSqlOfGroupByCrtTm(Lg_Company, countStarChar, firstCondif, startDate, endDate, accurateToHour);
        return getVoList(sql, TimeTotalVo.class);
    }

    @Override
    public List<TimeTotalVo> getGysszrzBy(String startDate, String endDate, boolean accurateToHour) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String firstCondif = "is_virtual_company = 1 AND code_status = '2' AND identity = '1' and delete_flag = '0'";
        String sql = getSqlOfGroupByCrtTm(Lg_Company, countStarChar, firstCondif, startDate, endDate, accurateToHour);
        return getVoList(sql, TimeTotalVo.class);
    }

    @Override
    public List<TimeTotalVo> getWtssqBy(String startDate, String endDate, boolean accurateToHour) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String firstCondif = "is_virtual_company = 1 AND code_status = '2' AND identity = '0' AND authorization_review_status =2 and delete_flag = '0'";
        String sql = getSqlOfGroupByCrtTm(Lg_Company, countStarChar, firstCondif, startDate, endDate, accurateToHour);
        return getVoList(sql, TimeTotalVo.class);
    }

    @Override
    public List<TimeTotalVo> getGyssqBy(String startDate, String endDate, boolean accurateToHour) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String firstCondif = "is_virtual_company = 1 AND code_status = '2' AND identity = '1' AND authorization_review_status =2 and delete_flag = '0'";
        String sql = getSqlOfGroupByCrtTm(Lg_Company, countStarChar, firstCondif, startDate, endDate, accurateToHour);
        return getVoList(sql, TimeTotalVo.class);
    }

    @Override
    public long getEntrustHisTotal() {
        String sql = StringUtils.joinStr(
                "SELECT COUNT(*) FROM ", Lg_Company,
                " WHERE identity = '0' AND code_status = '2'");// and delete_flag = '0'

        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public long getSupplyHisTotal() {
        String sql = StringUtils.joinStr(
                "SELECT COUNT(*) FROM ", Lg_Company,
                " WHERE identity = '1' AND code_status = '2'");// and delete_flag = '0'

        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public List<GroupByVo> getEntrustAndSupplyHistoryPieBy() {
        List<GroupByVo> resList = new ArrayList<GroupByVo>();

        String sqlSupply = StringUtils.joinStr("SELECT COUNT(*) FROM ", Table.Lg_Company,
                " WHERE identity = '1' AND code_status = '2'");// and delete_flag = '0'
        long countSupply = jdbcTemplate.queryForObject(sqlSupply, Long.class);

        resList.add(new GroupByVo(String.valueOf(Key.Num0), CN.Supplier, countSupply, 0D));


        String sqlEntrustC = StringUtils.joinStr("SELECT COUNT(*) FROM ", Table.Lg_Company,
                " WHERE identity = '0' AND code_status = '2' AND is_virtual_company = 1");
        long countEntrustC = jdbcTemplate.queryForObject(sqlEntrustC, Long.class);

        resList.add(new GroupByVo(String.valueOf(Key.Num1), CN.Entrust_Company, countEntrustC, 0D));


        String sqlEntrustP = StringUtils.joinStr("SELECT COUNT(u.id) FROM ", Table.Lg_Base_Company_User,
                " u, ", Table.Lg_Company, " c WHERE",
                " u.company_id = c.id AND c.identity = '0'",
                " AND c.code_status = '2' AND c.is_virtual_company = 0");
        long countEntrustP = jdbcTemplate.queryForObject(sqlEntrustP, Long.class);

        resList.add(new GroupByVo(String.valueOf(Key.Num2), CN.Entrust_Person, countEntrustP, 0D));


        String sqlDriver = StringUtils.joinStr("SELECT COUNT(*) FROM ", Table.Lg_Driver,
                " WHERE check_status = '2'");
        long countDriver = jdbcTemplate.queryForObject(sqlDriver, Long.class);

        resList.add(new GroupByVo(String.valueOf(Key.Num3), CN.Driver, countDriver, 0D));


        return resList;
    }

    @Override
    public List<GroupByVo> getSupplyAreaDistBy(String startDate, String endDate, int topLimit) throws Exception {
        String firstCondi = "identity = '1' AND code_status = '2'";
        return getGroupByVoFromLgCompany(firstCondi, startDate, endDate, topLimit);
    }

    @Override
    public List<GroupByVo> getEntrustAreaDistBy(String startDate, String endDate, int topLimit) throws Exception {
        String firstCondi = "identity = '0' AND code_status = '2'";
        return getGroupByVoFromLgCompany(firstCondi, startDate, endDate, topLimit);
    }

    @Override
    public List<GroupByVo> getDriverAreaDistBy(String startDate, String endDate, int topLimit) {
        return null;
    }


}
