package org.jumao.bi.dao.jyt.impl;

import org.apache.log4j.Logger;
import org.jumao.bi.dao.jyt.JytOverviewDao;
import org.jumao.bi.dao.trade.impl.GeneralBasicDao;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.service.impl.trade.register.ChartServiceHelper;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.Verifier;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.jumao.bi.utis.enums.JytEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen qian
 */
@Repository(JytOverviewDaoImpl.Jyt_Overview_Dao)
public class JytOverviewDaoImpl extends GeneralBasicDao implements JytOverviewDao {

    private Logger logger = Logger.getLogger(JytOverviewDaoImpl.class);

    public static final String Jyt_Overview_Dao = "jytOverviewDao";

    @Autowired
    protected JdbcTemplate jdbcTemplate;


    @Override
    public long getNumbersBy(int itemId, JytEnum jytEnum, boolean isCn) {
        String sql = null;

        switch (jytEnum) {
            case GYSQYS:
                if (isCn) {
                    sql = StringUtils.joinStr("select count(*) from ",
                            Table.Lg_Company, " where identity = '1' and code_status = '2';");
                } else {
                    sql = StringUtils.joinStr("select count(*) from ",
                            Table.En_Lg_Company, " where identity = 1 and `status` = 0 AND delete_flag = 0;");
                }
                break;
            case WTFQYS:
                if (isCn) {
                    sql = StringUtils.joinStr("select count(*) from ",
                            Table.Lg_Company, " WHERE identity = '0' AND code_status = '2' AND is_virtual_company = 1;");
                } else {
                    sql = StringUtils.joinStr("select count(*) from ",
                            Table.En_Lg_Company, " WHERE identity = 0 AND `status` = 0 AND delete_flag = 0;");
                }
                break;
            case WTFGRS:
                sql = StringUtils.joinStr("select count(u.id) from ", Table.Lg_Base_Company_User,
                        " u, ", Table.Lg_Company, " c where u.company_id=c.id and c.identity='0' and c.code_status = '2' and c.is_virtual_company = 0;");
                break;
            case CKS:
                sql = StringUtils.joinStr("select count(*) from ",
                        isCn ? Table.Lg_Warehouse : Table.En_Lg_Warehouse, " where check_status=1;");
                break;
            case XLS:
                if (isCn) {
                    sql = StringUtils.joinStr("select count(*) from ",
                            Table.Lg_Line, " where check_status='1';");
                } else {
                    sql = StringUtils.joinStr("select count(*) from ",
                            Table.En_Lg_Line, " where check_status=1;");
                }
                break;
            case HZ:
                sql = StringUtils.joinStr("SELECT sum(v.cargo_value) total_cargo_value FROM ",
                        Table.LG_CARGO_VALUE, " v WHERE v.STATUS = 1 AND v.currency = '131' AND v.delete_flag = 0;");
                break;
            case DDJE:
                sql = StringUtils.joinStr("select sum(total_price) from ", Table.Lg_Order
                        , " where delete_flag='0' and active_flag='0';");
                break;
            case DDS:
                sql = StringUtils.joinStr("select count(*) from ",
                        Table.Lg_Order, " where delete_flag='0' and active_flag='0';");
                break;
            case SJS:
                sql = StringUtils.joinStr("select count(*) from ",
                        Table.Lg_Driver, " where check_status='2'");
                break;
            case CLS:
                sql = StringUtils.joinStr("select count(*) from ",
                        Table.Lg_Vehicle, " where check_status='2'");
                break;
            default:
                logger.error(StringUtils.joinStr(
                        GeneralUtils.getCurrMethod(), " get a undefined itemId:", itemId
                ));
                break;
        }

        if (!Verifier.isEffectiveStr(sql)) {
            return 0;
        }
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public List<TimeTotalVo> getLineChartByItemId(String itemIdStr, String startDate, String endDate,
                                                  boolean accurateToHour, boolean isCn) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        int itemId = Integer.parseInt(itemIdStr);
        String sql = null;
        JytEnum jytEnum = JytEnum.getByType(itemId);
        boolean changeToWanYuan = false;

        switch (jytEnum) {
            case GYSQYS:
                String table1 = isCn ? Table.Lg_Company : Table.En_Lg_Company;
                String firstCondi1 = isCn ?
                        "is_virtual_company = 1 AND code_status = '2' and identity = '1'" :
                        "`status` = 0 and identity = 1";
                sql = getSqlOfGroupByCrtTm(table1, countAll,
                        firstCondi1, startDate, endDate, accurateToHour);
                break;

            case WTFQYS:
                String table2 = isCn ? Table.Lg_Company : Table.En_Lg_Company;
                String firstCondi2 = isCn ?
                        "is_virtual_company = 1 AND code_status = '2' and identity = '0'" :
                        "`status` = 0 and identity = 0";
                sql = getSqlOfGroupByCrtTm(table2, countAll,
                        firstCondi2, startDate, endDate, accurateToHour);
                break;

            case WTFGRS:
                sql = getSqlOfGroupByCrtTm(Table.Lg_Company, countAll,
                        "is_virtual_company = 0 AND code_status = '2' and identity = '0'", startDate, endDate, accurateToHour);
                break;

            case CKS:
                String table3 = isCn ? Table.Lg_Warehouse : Table.En_Lg_Warehouse;
                sql = getSqlOfGroupByCrtTm(table3, countAll,
                        "check_status = 1", startDate, endDate, accurateToHour);
                break;
            case XLS:
                String table4 = isCn ? Table.Lg_Line : Table.En_Lg_Line;
                String firstCondi4 = isCn ? "check_status = '1'" : "check_status = 1";
                sql = getSqlOfGroupByCrtTm(table4, countAll,
                        firstCondi4, startDate, endDate, accurateToHour);
                break;
            case HZ:
                sql = getSqlOfHz(startDate, endDate, accurateToHour, isCn);
                changeToWanYuan = true;
                break;
            case DDJE:
                sql = getSqlOfGroupByCrtTm(Table.Lg_Order, "sum(total_price)",
                        "delete_flag = '0' and active_flag = '0'", startDate, endDate, accurateToHour);
                changeToWanYuan = true;
                break;
            case DDS:
                sql = getSqlOfGroupByCrtTm(Table.Lg_Order, countAll,
                        "delete_flag = '0' and active_flag = '0'", startDate, endDate, accurateToHour);
                break;
            case SJS:
                sql = getSqlOfGroupByCrtTm(Table.Lg_Driver, countAll,
                        "check_status = '2'", startDate, endDate, accurateToHour);
                break;
            case CLS:
                sql = getSqlOfGroupByCrtTm(Table.Lg_Vehicle, countAll,
                        "check_status = '2'", startDate, endDate, accurateToHour);
                break;
            default:
                logger.error(StringUtils.joinStr(
                        GeneralUtils.getCurrMethod(), " get a undefined itemId:", itemIdStr
                ));
                break;
        }

        if (!Verifier.isEffectiveStr(sql)) {
            return new ArrayList<TimeTotalVo>();
        }
        return getVoList(sql, TimeTotalVo.class);
    }

    private String getSqlOfHz(String startDate, String endDate, boolean accurateToHour, boolean isCn) {
        String tmGrpLen = getCrtTmGroupLength(accurateToHour);
        return StringUtils.joinStr(
                "select sum(v.cargo_value) as ", Key.TOTAL, ", t.", Key.CREATE_TIME, " from",

                " (select order_no, ", tmGrpLen, " as ", Key.CREATE_TIME,
                " from ", Table.Lg_Order, " where delete_flag = '0' and active_flag = '0'",
                " and (create_time between '", startDate, "' and '", endDate, "')) t",

                " inner join",
                " (select order_no, cargo_value from ", Table.LG_CARGO_VALUE,
                " where status = 1 and currency = '131' and delete_flag = 0) v",

                " on t.order_no = v.order_no group by t.", Key.CREATE_TIME);
    }


    @Override
    public List<GroupByVo> getTransportLineBy(String startDate, String endDate, boolean isCn) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

//        String sql = StringUtils.joinStr(
//                "select line_type as ", Key.Type,
//                ", count(*) as ", Key.TOTAL, " from ", Table.Lg_Line,
//                " where check_status = '1' and",
//                " (create_time between '", startDate, "' and '", endDate, "') group by line_type");
        String table = isCn ? Table.Lg_Line : Table.En_Lg_Line;
        String firstCondi = isCn ? "check_status = '1'" : "check_status = 1";

        String sql = getSqlOfTypeAndTotal(Key.Line_Type_, "", countAll,
                table, firstCondi, startDate, endDate, Key.Line_Type_, 0);

        return getVoList(sql, GroupByVo.class);
    }


    @Override
    public List<GroupByVo> getRequireTop5BarChartBy(String startDate, String endDate, int topLimit, boolean isCn) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

//        String sql = StringUtils.joinStr(
//                "select company_id as ", Key.Type, ", company_name as ", Key.TYPE_NAME,
//                ", count(*) as ", Key.TOTAL, " from ", Table.Lg_Needs,
//                " where check_status = '1' and",
//                " (create_time between '", startDate, "' and '", endDate, "')",
//                " group by company_id, company_name order by ", Key.TOTAL, " desc limit ", topLimit);

        String grpTarget = StringUtils.joinStr(Key.Company_Id_, ", ", Key.Company_Name_);
        String table = isCn ? Table.Lg_Needs : Table.En_Lg_Needs;
        String firstCondi = isCn ? "check_status = '1'" : "check_status = 1";

        String sql = getSqlOfTypeAndTotal(Key.Company_Id_, Key.Company_Name_, countAll,
                table, firstCondi, startDate, endDate, grpTarget, topLimit);

        return getVoList(sql, GroupByVo.class);
    }

}
