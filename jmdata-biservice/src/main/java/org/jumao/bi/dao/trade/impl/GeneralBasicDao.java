package org.jumao.bi.dao.trade.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RegCalUtils;
import org.jumao.bi.utis.RegDateUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.Verifier;
import org.jumao.bi.utis.comparator.GroupByVoComp;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by kartty on 2017/5/4.
 */
public class GeneralBasicDao extends GeneralDaoHelper {

    private Logger logger = Logger.getLogger(GeneralBasicDao.class);

    protected GroupByVoComp groupByVoComp = new GroupByVoComp();

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public static final int Len4 = 4;
    public static final int Len6 = 6;
    public static final int Num86399 = 86399;
    public static final int No_Limit = 0;
    protected String allThePlatform = "0"; //0 表示交易全平台（全站）
    protected String msPlatformId = "1001"; //master station 总站 id
    protected String jytPlatformId = "1004";

    protected String countStarChar = "*";
    protected String countAll = "count(*)";

    protected JsonParser jsonParser = new JsonParser();
    protected Gson gson = new Gson();


    protected String checkSpecialPlatformId(String platform) {
        platform = dealPlatform(platform, Len4);
        if (msPlatformId.equals(platform) || jytPlatformId.equals(platform)) {
            return platform;
        } else {
            return null;
        }

        //前端太忙，后端做适配，以后若要取单个子平台的数据，则用新接口
//        if (Verifier.isEffectiveStr(platform)) {
//            if (allThePlatform.equals(platform)) {
//                platform = null;
//
//            } else {
//                platform = dealPlatform(platform, Len4);
//            }
//        } else {
//            platform = null;
//        }
//        return platform;
    }


    public static final int Jumore_Master = 1001;
    public static final int Chemical_Industry = 1002;
    public static final int Coloured = 1003;
    public static final int Coal = 1007;
    public static final int Steel = 1008;
    public static final int Mineral = 1009;
    public static final int Agriculture = 1010;
    public static final int Industrial = 1011;
    public static final int Consumable = 1012;
    public static final int Mechanics = 1013;
    public static final int Food = 1014;
    public static final int Oil = 1022;

    private String sqlSep = "','";

    /**
     * 需求有变动
     * //@return  string: platform in ('123','321')
     * @return  string: platform = '1005'
     */
    protected String getCondiForAllPlatform() {
        //return StringUtils.joinStr("platform in ('", Jumore_Master,
                //sqlSep, Coloured, sqlSep, Coal, sqlSep, Steel, sqlSep, Mineral, sqlSep, Agriculture,
                //sqlSep, Industrial, sqlSep, Consumable, sqlSep, Mechanics, sqlSep, Food, "')");
        return StringUtils.joinStr("platform = '1005'");
    }
//            聚贸总站
//    聚贸有色
//            聚贸煤炭
//    聚贸钢铁
//            聚贸矿产
//    聚贸农产品
//            聚贸工业品
//    聚贸机械
//            聚贸消费品
//    聚贸食品

    /**
     * 不足 6 位用 0 补全，此时表示中文版，英文版 01 结尾
     * @param platform
     * @param needLen
     * @return
     */
    public String dealPlatform(String platform, int needLen) {
        if (allThePlatform.equals(platform)) {
            return platform;
        }
        int len = platform.length();
        if (len == needLen) {
            return platform;

        } else if (len < needLen) {
            int diff = needLen - len;
            StringBuilder sb = new StringBuilder(platform);
            for (int i = 0; i < diff; i++) {
                sb.append("0");
            }
            platform = sb.toString();

        } else {
            platform = platform.substring(0, needLen);
        }
        LogUtils.writeLogs(logger, StringUtils.joinStr(GeneralUtils.getCurrMethod(),
                " transform new platform:", platform));
        return platform;
    }

    protected String dealStartDate(String startDate) throws Exception {
        if (Verifier.isEffectiveStr(startDate)) {
            return RegDateUtils.formatToFullF(RegDateUtils.parseToNoHourAnd_(startDate));
        } else {
            return startDate;
        }
    }

    protected String dealEndDate(String endDate) throws Exception {
        if (Verifier.isEffectiveStr(endDate)) {
            Calendar c = RegCalUtils.getCalendarBy(RegDateUtils.parseToNoHourAnd_(endDate));
            c.add(Calendar.SECOND, Num86399);//加上 24*60*60 -1 秒
            return RegDateUtils.formatToFullF(c.getTime());
        } else {
            return endDate;
        }
    }


    protected <T> List<T> getVoList(String sql, Class<T> clazz) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz));
    }


    protected <T> JsonArray getJsonArray(String sql, Class<T> clazz) {
        List<T> list = getVoList(sql, clazz);
        return gson.toJsonTree(list).getAsJsonArray();
    }


    /**
     *
     * @param accurateToHour
     * @param isNoHourLen8  true:20170102, false:2017-01-02
     * @return
     */
    protected String getCrtTmGroupLength(boolean accurateToHour, boolean isNoHourLen8) {
        int tmLen = 0;
        if (accurateToHour) {
            tmLen = 13;
            if (isNoHourLen8) tmLen -= 3;
        } else {
            tmLen = 10;
            if (isNoHourLen8) tmLen -= 2;
        }
        return StringUtils.joinStr("strleft(CAST(create_time AS string), ", tmLen, ")");
    }

    /**
     * isNoHourLen8 默认 false，一般 “2017-01-02”结构，除非是来自 百度、谷歌分析的数据
     */
    protected String getCrtTmGroupLength(boolean accurateToHour) {
        return getCrtTmGroupLength(accurateToHour, false);
    }


    protected String getSqlOfGroupByCrtTm(String table, String numTarget, String firstCondi,
        String startDate, String endDate, boolean accurateToHour, boolean isNoHourLen8) {
        String tmGrpLen = getCrtTmGroupLength(accurateToHour, isNoHourLen8);
        String countSql = getNumTargetSql(numTarget);
        String dateRange = getDateRangeBy(startDate, endDate, "");

        return StringUtils.joinStr(
                "select ", tmGrpLen, " as ", Key.CREATE_TIME,
                ", ", countSql, " as ", Key.TOTAL, " from ", table,
                " where ", checkFirstCondi(firstCondi),
                dateRange,
                " group by ", Key.CREATE_TIME,
                " order by ", Key.CREATE_TIME, " asc");
    }


    protected String getSqlOfGroupByCrtTm(String table, String numTarget, String firstCondi,
                                          String startDate, String endDate, boolean accurateToHour) {
        return getSqlOfGroupByCrtTm(table, numTarget, firstCondi, startDate, endDate, accurateToHour, false);
    }

    /**
     * @param firstCondi  like name = 'xx'
     * @param startDate  must be parsed already
     * @param endDate  must be parsed already
     */
    protected String getSqlOfTypeAndTotal(String typeCol, String nameCol, String numTarget,
        String table, String firstCondi, String startDate, String endDate, String grpTarget, int topLimit) {

        String typeSql = "";
        if (Verifier.isEffectiveStr(typeCol)) {
            typeSql = StringUtils.joinStr(typeCol, " as ", Key.Type, ", ");
        }

        String nameSql = "";
        if (Verifier.isEffectiveStr(nameCol)) {
            nameSql = StringUtils.joinStr(nameCol, " as ", Key.TYPE_NAME, ", ");
        }

        String totalSql = getNumTargetSql(numTarget);
        String dateRange = getDateRangeBy(startDate, endDate, "");

        String topLimitSql = "";
        if (topLimit > 0) {
            topLimitSql = StringUtils.joinStr(" order by ", Key.TOTAL, " desc limit ", topLimit);
        }

        return StringUtils.joinStr(
                "select ", typeSql, nameSql,
                totalSql, " as ", Key.TOTAL, " from ", table,
                " where ", checkFirstCondi(firstCondi),
                dateRange,
                " group by ", grpTarget, topLimitSql);
    }

    /**
     * @param startDate
     * @param endDate
     * @param tableAlia  like 'a.' or empty
     * @return 不包含 “and”， 两头都有空格
     */
    protected String getDateRangeBy(String startDate, String endDate, String tableAlia) {
        return Verifier.isEfficStrs(startDate, endDate) ?
                StringUtils.joinStr(" (", tableAlia, "create_time between '", startDate, "' and '", endDate, "') ")
                : StringUtils.joinStr(" ", tableAlia, "create_time is not null ");
    }


    protected String getSqlOfGroupByTradeCompayArea(String firstCondi, String startDate, String endDate, int topEle) {
        return StringUtils.joinStr(
                "SELECT t.", Key.Type, ", a.area_name as ", Key.TYPE_NAME, ", t.", Key.TOTAL,
                " FROM (SELECT count(DISTINCT c.comp_id) as ", Key.TOTAL, ", c.address_province as ", Key.Type,
                " FROM ", Table.Trade_Company, " c WHERE ", checkFirstCondi(firstCondi), " c.delete_flag = 0 and c.address_province is not null and",
                " (c.create_time between '", startDate, "' and '", endDate, "')",
                " GROUP BY c.address_province",
                topEle > 0 ? StringUtils.joinStr(" order by ", Key.TOTAL, " desc limit ", topEle) : "",
                " ) t LEFT JOIN ", Table.Public_Area_Cn, " a ON t.", Key.Type, " = a.area_code");
    }


    protected List<GroupByVo> getGroupByVoFromLgCompany(String firstCondi, String startDate, String endDate, int topLimit) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = getSqlOfGroupByLgCompayArea(firstCondi, startDate, endDate, topLimit);

        List<GroupByVo> list = getVoList(sql, GroupByVo.class);
        Collections.sort(list, groupByVoComp);
        return list;
    }

    protected String getSqlOfGroupByLgCompayArea(String firstCondi, String startDate, String endDate, int topLimit) {
        return StringUtils.joinStr(
                "SELECT t.", Key.Type, ", a.area_name as ", Key.TYPE_NAME, ", t.", Key.TOTAL,
                " FROM (SELECT count(id) as ", Key.TOTAL, ", CAST(strleft(CAST(area AS string), 5) As int) as ", Key.Type,
                " FROM ", Table.Lg_Company, " c WHERE ", checkFirstCondi(firstCondi), " area is not null and",
                " (create_time between '", startDate, "' and '", endDate, "')",
                " GROUP BY ", Key.Type,
                topLimit > 0 ? StringUtils.joinStr(" order by ", Key.TOTAL, " desc limit ", topLimit) : "",
                " ) t LEFT JOIN ", Table.Public_Area_Cn, " a ON t.", Key.Type, " = a.area_code");
    }


    protected String getOrderAndLimitAndOffsetCondi(int limit, int offset, String tableAlia) {
        return StringUtils.joinStr(" order by ", tableAlia, "create_time asc limit ", limit, " offset ", offset);
    }

    protected String getSumSql(String target) {
        return StringUtils.joinStr("sum(", target, ")");
    }

    protected String getAvgSql(String target) {
        return StringUtils.joinStr("avg(", target, ")");
    }

    protected String getPlatformIdCondi(String platform) {
        if (allThePlatform.equals(platform)) {
            return StringUtils.joinStr(Key.Platform_Id_, " is not null");
        } else {
            return StringUtils.joinStr(Key.Platform_Id_, "='", platform, "'");
        }
    }

    protected String checkOrderType(String orderType) {
        if (Key.Desc.equals(orderType)) {
            return Key.Desc;
        } else {
            return Key.Asc;
        }
    }

}
