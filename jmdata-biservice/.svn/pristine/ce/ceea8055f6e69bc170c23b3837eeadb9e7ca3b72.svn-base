package org.jumao.bi.dao.trade.operate.impl;

import org.jumao.bi.dao.trade.operate.IOperationOverviewDao;
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
@Repository("operationOverviewDaoImpl")
public class OperationOverviewDaoImpl implements IOperationOverviewDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getTopTotal(String platform, String nowDate) {
        String platform_ = PlatFormUtil.getPlatformV(platform+"00");
        String SQL = "";
        if (platform_ != null){
            SQL = "select count(distinct id) as nums from jmbi_uc_jumore_user  where platform = '1005' " +  //  where  create_time <= '"+nowDate+"'
                    " union all " +
                    " SELECT COUNT(DISTINCT shop_id) as nums  FROM jmbi_trade_shop_operate WHERE  operate_type=0   " +  //  delete_flag = 0 and    create_time <= '"+nowDate+"'
                    " union all " +
                    " select count(distinct goods_id) as nums  from  jmbi_trade_goods where industry_id= "+platform_+ //  AND   create_time <= '"+nowDate+"'
                    " union all " +
                    " select count(distinct order_id) as nums  from jmbi_trade_order  where industry_id= "+platform_+"  AND  order_state in(20,30,40,50,70) " +
                    " union all " +
                    " select count(c.payment_id) as nums  from jmbi_trade_payment c,jmbi_trade_order o where c.order_id=o.order_id and c.pay_flag=1 and  o.industry_id= "+platform_+" "+  //  is_delete = 0 and
                    " union all " +
                    " select sum(c.pay_money) as nums  from jmbi_trade_payment c,jmbi_trade_order o  where c.order_id=o.order_id and c.pay_flag=1 and  o.industry_id= "+platform_+" ";
        }else {
            SQL = "select count(distinct id) as nums from jmbi_uc_jumore_user  where platform = '1005' " + //  platform = '" + platform + "' and
                " union all " +
                " SELECT COUNT(DISTINCT shop_id) as nums  FROM jmbi_trade_shop_operate WHERE  operate_type=0  " +
                " union all " +
                " select count(distinct goods_id) as nums  from  jmbi_trade_goods " +
                " union all " +
                " select count(distinct order_id) as nums  from jmbi_trade_order  where  order_state in(20,30,40,50,70) " +
                " union all " +
                " select count(c.payment_id) as nums  from jmbi_trade_payment c,jmbi_trade_order o where c.order_id=o.order_id and c.pay_flag=1 " +
                " union all " +
                " select sum(c.pay_money) as nums  from jmbi_trade_payment c,jmbi_trade_order o  where c.order_id=o.order_id and c.pay_flag=1  ";
        }
        return jdbcTemplate.queryForList(SQL);
    }

    public Map<String, String> getForMap(String SQL) {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(SQL);
        Map<String, String> resMap = new HashMap();
        for (Map<String,Object> map : mapList){
            String key = String.valueOf(map.get("time_"));
            String val = String.valueOf(map.get("nums"));
            resMap.put(key,val);
        }
        return resMap;
    }

    public List<Map<String, String>> getForListMap(String SQL) {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(SQL);
        Map<String, String> pv = new HashMap();
        Map<String, String> uv = new HashMap();
        Map<String, String> ips = new HashMap();
        Map<String, String> ave = new HashMap();
        Map<String, String> avg = new HashMap();

        for (Map<String,Object> map : mapList){
            String pvKey = String.valueOf(map.get("time_"));
            String pvVal = String.valueOf(map.get("pv"));
            pv.put(pvKey,pvVal);
            String uvKey = String.valueOf(map.get("time_"));
            String uvVal = String.valueOf(map.get("uv"));
            uv.put(uvKey,uvVal);
            String ipsKey = String.valueOf(map.get("time_"));
            String ipsVal = String.valueOf(map.get("ips"));
            ips.put(ipsKey,ipsVal);
            String aveKey = String.valueOf(map.get("time_"));
            String aveVal = String.valueOf(map.get("ave"));
            ave.put(aveKey,aveVal);
            String avgKey = String.valueOf(map.get("time_"));
            String avgVal = String.valueOf(map.get("avg"));
            avg.put(avgKey,avgVal);
        }

        List<Map<String, String>> resuList = new ArrayList();
        resuList.add(pv);
        resuList.add(uv);
        resuList.add(ips);
        resuList.add(ave);
        resuList.add(avg);

        return resuList;
    }
}
