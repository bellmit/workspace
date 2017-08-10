package org.jumao.bi.dao.trade.operate.impl;

import org.jumao.bi.dao.trade.operate.IMasOperationOverviewDao;
import org.jumao.bi.utis.PlatFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/28.
 */
@Repository("masOperationOverviewDaoImpl")
public class MasOperationOverviewDaoImpl implements IMasOperationOverviewDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getTopTotal(String platform) {
        platform = platform.substring(0,4);
        String SQL = "SELECT COUNT(DISTINCT id) AS nums FROM jmbi_uc_jumore_user WHERE platform = '1005'  " +  //  where  create_time <= '"+nowDate+"'
                    " union all " +
                    " SELECT COUNT(DISTINCT shop_id) as nums  FROM jmbi_trade_shop_operate WHERE  operate_type=0   " +  //  delete_flag = 0 and    create_time <= '"+nowDate+"'
                    " union all " +
                    " select count(distinct goods_id) as nums  from  jmbi_trade_goods "+ //  where industry_id= "+platform_+" AND   create_time <= '"+nowDate+"'
                    " union all " +
                    " select count(distinct order_id) as nums  from jmbi_trade_order  where   order_state in(20,30,40,50,70) " +  //  industry_id= "+platform_+"  AND
                    " union all " +
                    " SELECT COUNT(c.payment_id) as nums  FROM jmbi_trade_payment c,jmbi_trade_order o  WHERE c.order_id=o.order_id AND c.pay_flag=1 " +  //  industry_id= "+platform_+" AND is_delete = 0 and
                    " union all " +
                    " SELECT SUM(c.pay_money) as nums  FROM jmbi_trade_payment c,jmbi_trade_order o WHERE c.order_id=o.order_id AND c.pay_flag=1; ";  //  industry_id= "+platform_+" AND
        return jdbcTemplate.queryForList(SQL);
    }

    public Map<String, String> getForMap(String SQL) {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(SQL);
        Map<String, String> resMap = new HashMap();
        for (Map<String,Object> map : mapList){
            String key = String.valueOf(map.get("k"));
            String val = String.valueOf(map.get("v"));
            resMap.put(key,val);
        }
        return resMap;
    }

    public int queryForInt(String SQL) {
        return jdbcTemplate.queryForInt( SQL);
    }

    public static void main(String[] args) {
        int size = new MasOperationOverviewDaoImpl().jdbcTemplate.queryForInt("");
    }
}
