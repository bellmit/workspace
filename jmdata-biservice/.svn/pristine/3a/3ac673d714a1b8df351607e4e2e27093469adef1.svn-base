package org.jumao.bi.dao.enjyt.impl;

import org.jumao.bi.dao.enjyt.EnJytRegisterDao;
import org.jumao.bi.dao.trade.impl.GeneralBasicDao;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Repository(EnJytRegisterDaoImpl.EN_JYT_REGISTER_DAO)
public class EnJytRegisterDaoImpl extends GeneralBasicDao implements EnJytRegisterDao {

    public static final String EN_JYT_REGISTER_DAO = "enJytRegisterDao";


    @Override
    public List<TimeTotalVo> getNewlyIncrBy(String platform, String startDate, String endDate,
        boolean accurateToHour) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealStartDate(endDate);

        String sql = getSqlOfGroupByCrtTm(Table.En_Lg_Company_User, countAll, "",
                startDate, endDate, accurateToHour);

        return getVoList(sql, TimeTotalVo.class);
    }


    @Override
    public List<GroupByVo> getRegAreaDist(String startDate, String endDate) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealStartDate(endDate);
        String dateRange = getDateRangeBy(startDate, endDate, "");

        String sql = StringUtils.joinStr(
                "SELECT t.", Key.Type, ", a.area_name as ", Key.TYPE_NAME,
                ", t.", Key.TOTAL, ", t.", Key.Child_Type1, " from",

                " (SELECT count(id) as ", Key.TOTAL, ", strleft(CAST(area as string), 5) as ", Key.Type,
                ", identity as ", Key.Child_Type1,
                " FROM ", Table.En_Lg_Company, " WHERE `status` = 0 and area is not null and",
                dateRange, " GROUP BY ", Key.Type, ", ", Key.Child_Type1, ") t",

                " LEFT JOIN ", Table.En_Lg_Base_Area, " a ON t.", Key.Type, " = a.area_code");

        return getVoList(sql, GroupByVo.class);
    }


}
