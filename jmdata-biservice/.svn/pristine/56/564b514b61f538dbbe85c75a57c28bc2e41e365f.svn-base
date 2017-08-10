package org.jumao.bi.dao.jzx.impl;

import org.jumao.bi.dao.jzx.JzxOverviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
@Repository("JzxOverviewDaoImpl")
public class JzxOverviewDaoImpl implements JzxOverviewDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, String> settledEnterprise(String startDate, String endDate) {
        String SQL = "SELECT COUNT(0) k FROM jmbi_jzx_company WHERE delete_flag = 0 AND provider_flag=0" ;
        /*if (!startDate.equals("")){
            SQL += " and a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' ";
        }*/
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> financingEnterprise(String startDate, String endDate) {
        String SQL = "SELECT COUNT(DISTINCT d.company_id) k FROM jmbi_jzx_project p  " +
                "LEFT JOIN jmbi_jzx_demand d ON p.demand_id = d.id WHERE p.delete_flag = 0 " ;
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> settlingInstitution(String startDate, String endDate) {
        String SQL = "SELECT COUNT(0) k FROM jmbi_jzx_company WHERE delete_flag = 0 AND provider_flag = 1 ";
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> shelvesFinancingProducts(String startDate, String endDate) {
        String SQL = "SELECT COUNT(0) k FROM jmbi_jzx_service WHERE delete_flag = 0 AND `status` = 1 ";
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> financingRequest(String startDate, String endDate) {
        String SQL = "SELECT COUNT(0) k FROM jmbi_jzx_project WHERE delete_flag = 0 ";
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> contractedMoney(String startDate, String endDate) {
        String SQL = "SELECT SUM(pay_amt)/10000 k FROM jmbi_jzx_project WHERE delete_flag = 0 ";
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> financingContract(String startDate, String endDate) {
        String SQL = "SELECT COUNT(1) k FROM jmbi_jrt_apply a WHERE a.delete_flag = 0 AND a.status = 4 ";
        if (!startDate.equals("")){
            SQL += " and a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' ";
        }
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> register(String startDate, String endDate) {
        String SQL = "SELECT COUNT(*) k FROM jmbi_jrt_company a WHERE a.identity = 0  ";
        if (!startDate.equals("")){
            SQL += " and a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' ";
        }
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> authentication(String startDate, String endDate) {
        String SQL = "SELECT COUNT(*) k FROM jmbi_jrt_company a WHERE " +
                " a.identity = 0 AND a.code_status = 2 AND a.warrant_status = 2 AND a.delete_flag = 0 ";
        if (!startDate.equals("")){
            SQL += " and a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' ";
        }
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> submitDemand(String startDate, String endDate) {
        String SQL = "SELECT COUNT(1) k FROM jmbi_jrt_company a WHERE a.code_status = 2 " +
                "AND a.delete_flag = 0 AND a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' " +
                "AND EXISTS ( SELECT 1 FROM jmbi_jrt_req X WHERE a.id = x.company_id AND x.delete_flag = 0 ) ";
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> submitApply(String startDate, String endDate) {
        String SQL = "SELECT COUNT(1) k FROM jmbi_jrt_company a WHERE a.code_status = 2 AND a.delete_flag = 0 " +
                "AND a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' " +
                "AND EXISTS ( SELECT 1 FROM jmbi_jrt_apply X WHERE a.id = x.company_id AND x.delete_flag = 0 ) ";
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> signSuccess(String startDate, String endDate) {
        String SQL = "SELECT COUNT(1) k FROM jmbi_jrt_company a WHERE a.code_status = 2 AND a.delete_flag = 0 " +
                "AND a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' " +
                "AND EXISTS ( SELECT 1 FROM jmbi_jrt_apply X WHERE " +
                "  a.id = x.company_id AND x.delete_flag = 0 AND x. STATUS = 4 ) ";
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> newProjectOrder(String startDate, String endDate) {
        String SQL = "SELECT strleft(d.cate_name,18) k, " +
                "COUNT(p.id) AS v " +
                "FROM jmbi_jzx_project p " +
                "LEFT JOIN jmbi_jzx_demand d ON p.demand_id = d.id " +
                "WHERE  p.delete_flag = 0  " +
                "and p.create_time BETWEEN '"+startDate+"' and '"+endDate+"' " +
                "GROUP BY  k ";
        Map<String, String> forMap = getForMap(SQL);
        return forMap;
    }

    @Override
    public Map<String, String> industryShare(String startDate, String endDate) {
        String SQL = "SELECT d.industry k, COUNT(p.id) v " +
                "FROM jmbi_jzx_project p " +
                "LEFT JOIN jmbi_jzx_demand d ON p.demand_id = d.id " +
                "WHERE p.delete_flag = 0 " +
                "AND strleft(d.cate_name,18) = '企业服务管理' " +
                "and p.create_time BETWEEN '"+startDate+"' and '"+endDate+"' " +
                "GROUP BY k  order by v;";
        Map<String, String> forMap = getForMap(SQL);
        return forMap;
    }

    @Override
    public Map<String, String> typeRatio(String startDate, String endDate) {
        String SQL = "SELECT strright(d.cate_name,12) k, COUNT(p.id) v " +
                "FROM jmbi_jzx_project p LEFT JOIN jmbi_jzx_demand d ON p.demand_id = d.id " +
                "WHERE p.delete_flag = 0 AND strleft(d.cate_name,18) = '企业服务管理' " +
                "and p.create_time BETWEEN '"+startDate+"' and '"+endDate+"' " +
                "GROUP BY k ";
        Map<String, String> forMap = getForMap(SQL);
        return forMap;
    }

    @Override
    public Map<String, String> newRegisteredUser(String startDate, String endDate) {
        String SQL = "SELECT CASE WHEN user_type = 0 THEN '企业用户' " +
                "  WHEN user_type = 1 THEN '平台用户（管理员）' " +
                " WHEN user_type = 8 THEN '咨询需求方' " +
                " WHEN user_type = 9 THEN '咨询服务商' " +
                " ELSE '其他' END AS k,  COUNT(0) AS v " +
                "FROM jmbi_uc_jumore_user " +
                "WHERE platform = '1019' " +
                "AND register_time BETWEEN '"+startDate+"' and '"+endDate+"' " +
                "GROUP BY  k ";
        Map<String, String> forMap = getForMap(SQL);
        return forMap;
    }


    public Map<String, String> getMap(String SQL) {
        Map<String, Object> forMap = jdbcTemplate.queryForMap(SQL);
        Map<String, String> resMap = new LinkedHashMap<String, String>();
        for (Map.Entry<String, Object> entry : forMap.entrySet()){
            String key = entry.getKey();
            String value = "";
            if (entry.getValue() == null){
                value = "0";
            }else {
                value = entry.getValue().toString();
            }
            resMap.put(key, value);
        }
        return resMap;
    }

    public Map<String, String> getForMap(String SQL) {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(SQL);
        Map<String, String> resMap = new LinkedHashMap<String, String>();
        for (Map<String, Object> map : mapList) {
            String key = String.valueOf(map.get("k"));
            String val = String.valueOf(map.get("v"));
            resMap.put(key, val);
        }
        return resMap;
    }
}
