package org.jumao.bi.dao.enjzx.impl;

import org.jumao.bi.dao.enjzx.EnJzxOverviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
@Repository("EnJzxOverviewDaoImpl")
public class EnJzxOverviewDaoImpl implements EnJzxOverviewDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, String> settledEnterprise(String startDate, String endDate) {
        String SQL = "SELECT COUNT(0) k FROM jmbi_en_jzx_company WHERE delete_flag = 0 AND provider_flag=0" ;
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> financingEnterprise(String startDate, String endDate) {
        String SQL = "SELECT COUNT(DISTINCT d.company_id) k FROM  " +
                " jmbi_en_jzx_project p LEFT JOIN jmbi_en_jzx_demand d ON p.demand_id = d.id\n" +
                "WHERE  p.delete_flag = 0 " ;
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> settlingInstitution(String startDate, String endDate) {
        String SQL = "SELECT COUNT(0) k  FROM jmbi_en_jzx_company WHERE delete_flag = 0 AND provider_flag = 1 ";
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> shelvesFinancingProducts(String startDate, String endDate) {
        String SQL = "SELECT COUNT(0) k FROM jmbi_jzx_service WHERE delete_flag = 0 AND `status` = 1 ";
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> financingRequest(String startDate, String endDate) {
        String SQL = "SELECT COUNT(0) k FROM jmbi_en_jzx_project WHERE delete_flag = 0 ";
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> contractedMoney(String startDate, String endDate) {
        String SQL = "SELECT SUM(budget_moeny) / 10000 as k " +
                "FROM jmbi_en_jzx_project " +
                "WHERE delete_flag = 0 " +
                "AND budget_type = 2 " +
                "AND budget_type IS NOT NULL " +
                "GROUP BY budget_type ";
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
        String SQL = "SELECT strleft(d.cate_name,instr(d.cate_name,'|') -1) as k, " +
                " COUNT(p.id) AS v " +
                "FROM jmbi_en_jzx_project p " +
                "LEFT JOIN jmbi_en_jzx_demand d ON p.demand_id = d.id " +
                "WHERE p.delete_flag = 0  " +
                "AND p.create_time BETWEEN '"+startDate+"' and '"+endDate+"' " +
                "GROUP BY k  ";
        Map<String, String> forMap = getForMap(SQL);
        return forMap;
    }

    public Map<String, String> industryShare(String startDate, String endDate, String flag) {
        String SQL = "SELECT d.industry as k, COUNT(p.id) as v " +
                "FROM  jmbi_en_jzx_project p " +
                "LEFT JOIN jmbi_en_jzx_demand d ON p.demand_id = d.id " +
                "WHERE p.delete_flag = 0 " +
//                "AND strleft(d.cate_name,instr(d.cate_name,'|')-1) = 'Business Managent Service' " +
                "AND strleft(d.cate_name,instr(d.cate_name,'|')-1) = '"+ flag +"' " +
                "AND p.create_time BETWEEN '"+startDate+"' and '"+endDate+"' " +
                "GROUP BY k;";
        Map<String, String> forMap = getForMap(SQL);
        return forMap;
    }

    public Map<String, String> typeRatio(String startDate, String endDate, String flag) {
        String SQL = "SELECT substr(d.cate_name,instr(d.cate_name,'|')+1,20) AS k, " +
                " COUNT(p.id) AS v " +
                "FROM jmbi_en_jzx_project p " +
                "LEFT JOIN jmbi_en_jzx_demand d ON p.demand_id = d.id " +
                "WHERE p.delete_flag = 0 " +
                "AND strleft(d.cate_name,instr(d.cate_name,'|')-1) =  '"+ flag +"' " +
                "AND p.create_time BETWEEN '"+startDate+"' and '"+endDate+"' " +
                "GROUP BY k ";
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
