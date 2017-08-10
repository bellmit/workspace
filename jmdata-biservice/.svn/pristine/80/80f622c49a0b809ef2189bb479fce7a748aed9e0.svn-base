package org.jumao.bi.dao.jrt.impl;

import org.jumao.bi.dao.jrt.JrtOverviewDao;
import org.jumao.bi.dao.jyt.JytPayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
@Repository("JytOverviewDaoImpl")
public class JrtOverviewDaoImpl implements JrtOverviewDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, String> settledEnterprise(String startDate, String endDate) {
        String SQL = "SELECT COUNT(*) k FROM jmbi_jrt_company a WHERE a.identity = 0 AND a.code_status = 2 AND a.delete_flag = 0" ;
        if (!startDate.equals("")){
            SQL += " and a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' ";
        }
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> financingEnterprise(String startDate, String endDate) {
        String SQL = "SELECT count(*) k FROM jmbi_jrt_company a  " +
                " WHERE EXISTS ( SELECT  1 FROM jmbi_jrt_apply x " +
                "  WHERE a.id = x.company_id AND x. STATUS = 4 ) AND a.identity = 0 AND a.code_status = 2 AND a.delete_flag = 0" ;
        if (!startDate.equals("")){
            SQL += " and a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' ";
        }
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> settlingInstitution(String startDate, String endDate) {
        String SQL = "SELECT COUNT(*) k FROM jmbi_jrt_company a WHERE a.identity = 1 AND a.code_status = 2 AND a.delete_flag = 0 ";
        if (!startDate.equals("")){
            SQL += " and a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' ";
        }
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> shelvesFinancingProducts(String startDate, String endDate) {
        String SQL = "SELECT COUNT(*) k FROM jmbi_jrt_product a WHERE a.`status` = 2 AND a.is_shelf = 1 AND a.delete_flag = 0 ";
        if (!startDate.equals("")){
            SQL += " and a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' ";
        }
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> financingRequest(String startDate, String endDate) {
        String SQL = "SELECT COUNT(1) k FROM jmbi_jrt_apply a WHERE a.delete_flag = 0 ";
        if (!startDate.equals("")){
            SQL += " and a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' ";
        }
        Map<String, String> map = getMap(SQL);
        return map;
    }

    public Map<String, String> contractedMoney(String startDate, String endDate) {
        String SQL = "SELECT SUM(amount_app) k FROM jmbi_jrt_apply a WHERE a.`status` = 4 AND a.delete_flag = 0 ";
        if (!startDate.equals("")){
            SQL += " and a.create_time BETWEEN '"+startDate+"' and '"+endDate+"' ";
        }
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
