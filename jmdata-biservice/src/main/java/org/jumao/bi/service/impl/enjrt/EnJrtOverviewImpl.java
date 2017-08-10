package org.jumao.bi.service.impl.enjrt;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.jumao.bi.service.enjrt.EnJrtOverviewSvc;
import org.jumao.bi.utis.CalculationUtil;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
@Service
public class EnJrtOverviewImpl implements EnJrtOverviewSvc {

    private static final String Create_Time = "create_time";
    private static final String A_Alias = "a";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 运营概览
     */
    public Response operationOverview() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        StringBuffer sql = new StringBuffer("");
        // 入驻企业
        settledEnterprise(list, sql, null, null);
        // 融资企业
        sql.setLength(0);
        financingEnterprise(list, sql, null, null);
        // 入驻机构
        sql.setLength(0);
        sql.append("SELECT count(*) value,'入驻机构' name,'家' unit").append(" FROM jmbi_en_jrt_base_company a")
                .append(" WHERE a.identity = '1' AND a.code_status = '2' AND a.delete_flag = '0'");
        list.add(jdbcTemplate.queryForMap(sql.toString()));
        // 上架融资产品
        sql.setLength(0);
        shelvesFinancingProducts(list, sql, null, null);
        // 融资申请
        sql.setLength(0);
        financingRequest(list, sql, null, null);
        // 签约金额
        sql.setLength(0);
        contractedMoney(list, sql, null, null);
        sql.setLength(0);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", list);
        return Response.ok().entity(data).build();
    }

    public Response serviceDataOverview(String _startDate, String _endDate) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        StringBuffer sql = new StringBuffer("");
        // 入驻企业
        settledEnterprise(list, sql, _startDate, _endDate);
        // 融资企业
        sql.setLength(0);
        financingEnterprise(list, sql, _startDate, _endDate);
        // 上架融资产品
        sql.setLength(0);
        shelvesFinancingProducts(list, sql, _startDate, _endDate);
        // 融资申请
        sql.setLength(0);
        financingRequest(list, sql, _startDate, _endDate);
        // 融资签约
        sql.setLength(0);
        sql.append("SELECT count(1) value,'融资签约' name,'笔' unit").append(" FROM jmbi_en_jrt_apply a")
                .append(" WHERE a.`status` = 3 AND a.delete_flag = 0").append(getSubsql(_startDate, _endDate));
        list.add(jdbcTemplate.queryForMap(sql.toString()));
        // 签约金额
        sql.setLength(0);
        contractedMoney(list, sql, _startDate, _endDate);
        sql.setLength(0);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", list);
        return Response.ok().entity(data).build();
    }

    public Response registrationConversion(String _startDate, String _endDate) {
        String subSql = " FROM jmbi_en_jrt_base_company a WHERE a.identity = '0' AND a.delete_flag = '0' ";
        subSql += getSubsql(_startDate, _endDate);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        // 注册
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) value,'注册' name").append(subSql);
        list.add(jdbcTemplate.queryForMap(sql.toString()));
        // 提交公司信息
        sql.setLength(0);
        sql.append("SELECT count(*) value,'提交公司信息' name").append(subSql).append(" AND a.code_status = '2'");
        list.add(jdbcTemplate.queryForMap(sql.toString()));
        // 提交融资申请
        sql.setLength(0);
        sql.append("SELECT count(*) value,'提交融资申请' name").append(subSql).append(" AND a.code_status = '2'")
                .append(" AND EXISTS (SELECT 1 FROM jmbi_en_jrt_apply x WHERE a.id = x.company_id AND x.delete_flag = 0)");
        list.add(jdbcTemplate.queryForMap(sql.toString()));
        // 签约完成
        sql.setLength(0);
        sql.append("SELECT count(*) value,'签约完成' name").append(subSql).append(" AND a.code_status = '2' ")
                .append(" AND EXISTS (SELECT 1 FROM jmbi_en_jrt_apply x WHERE a.id = x.company_id AND x. STATUS = 3)");
        list.add(jdbcTemplate.queryForMap(sql.toString()));
        // 计算转化率
        int i = Key.Num0;
        for (Map<String, Object> map : list) {
            if (i != Key.Num0) {
                map.put("Conv", getConv(map.get("value").toString(), list.get(i - 1).get("value").toString()));
            } else {
                map.put("Conv", "");
            }
            i++;
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", list);
        return Response.ok().entity(data).build();
    }


    private String getSubsql(String startDate, String endDate) {
        String subSql = "";
        if (StringUtils.isNotBlank(startDate)) {
            subSql += StringUtils.getSqlCondition(A_Alias, Create_Time, StringUtils.formatDate(startDate), CompareOp.GREATER_OR_EQUAL);
        }
        if (StringUtils.isNotBlank(endDate)) {
            subSql += StringUtils.getSqlCondition(A_Alias, Create_Time, StringUtils.formatDate(endDate), CompareOp.LESS_OR_EQUAL);
        }
        return subSql;
    }

    private void settledEnterprise(List<Map<String, Object>> list, StringBuffer sql, String startDate, String endDate) {
        sql.append("SELECT count(*) value,'入驻企业' name,'家' unit").append(" FROM jmbi_en_jrt_base_company a")
                .append(" WHERE a.identity = '0' AND a.code_status = '2' AND a.delete_flag = '0'").append(getSubsql(startDate, endDate));
        list.add(jdbcTemplate.queryForMap(sql.toString()));
    }

    private void financingEnterprise(List<Map<String, Object>> list, StringBuffer sql, String startDate, String endDate) {
        sql.append("SELECT count(*) value,'融资企业' name,'家' unit")
                .append(" FROM jmbi_en_jrt_base_company a")
                .append(" WHERE EXISTS (SELECT 1 FROM jmbi_en_jrt_apply x WHERE a.id = x.company_id AND x. STATUS = 3) AND a.identity = '0' AND a.code_status = '2' AND a.delete_flag = '0'")
                .append(getSubsql(startDate, endDate));
        list.add(jdbcTemplate.queryForMap(sql.toString()));
    }

    private void shelvesFinancingProducts(List<Map<String, Object>> list, StringBuffer sql, String startDate, String endDate) {
        sql.append("SELECT count(*) value,'上架融资产品' name,'个' unit").append(" FROM jmbi_en_jrt_product a")
                .append(" WHERE a.`status` = 4 AND a.delete_flag = 0").append(getSubsql(startDate, endDate));
        list.add(jdbcTemplate.queryForMap(sql.toString()));
    }

    private void financingRequest(List<Map<String, Object>> list, StringBuffer sql, String startDate, String endDate) {
        sql.append("SELECT count(*) value,'融资申请' name,'笔' unit").append(" FROM jmbi_en_jrt_apply a").append(" WHERE a.delete_flag = 0")
                .append(getSubsql(startDate, endDate));
        list.add(jdbcTemplate.queryForMap(sql.toString()));
    }

    private void contractedMoney(List<Map<String, Object>> list, StringBuffer sql, String startDate, String endDate) {
        sql.append("SELECT round(IFNULL(sum(a.amount),0.00)/10000.000,2) value,'签约金额' name,'万美元' unit").append(" FROM jmbi_en_jrt_apply a")
                .append(" WHERE a.`status` = 3 AND a.delete_flag = 0").append(getSubsql(startDate, endDate));
        list.add(jdbcTemplate.queryForMap(sql.toString()));
    }

    private String getConv(String v1, String v2) {
        String s = null; // 得到转化率
        try {
            if ("0".equals(v1) || "0".equals(v2)) {
                s = "0";
            } else {
                s = CalculationUtil.div(NumberUtils.createBigDecimal(v1).multiply(NumberUtils.createBigDecimal("100")).toString(), v2, 2);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return s;
    }

}
