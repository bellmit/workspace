package org.jumao.bi.dao.enjyt.impl;

import org.jumao.bi.dao.enjyt.EnJytPayDao;
import org.jumao.bi.dao.jyt.JytPayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
@Repository("EnJytPayDaoImpl")
public class EnJytPayDaoImpl implements EnJytPayDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, String> payOnePv(String platformId, String startDate, String endDate) {
        String SQL = "select create_date as k,sum(cast(b.pv as int)) as v " +
                "from jmbi_baidu_daily_stat b where platform_id = '" + platformId + "' and  b.create_date between '" + startDate + "' and  '" + endDate + "' " +
                "group by  k order by k;";
        return getForMap(SQL);
    }

    public Map<String, String> payOneUv(String platformId, String startDate, String endDate) {
        String SQL = "select create_date as k,sum(cast(b.uv as int))as v " +
                "from jmbi_baidu_daily_stat b where platform_id = '" + platformId + "' and  b.create_date between '" + startDate + "' and  '" + endDate + "' " +
                "group by  k order by k;";
        return getForMap(SQL);
    }

    public Map<String, String> PayOpen(String startDate, String endDate) {

        /*String _startDate = "";  *//* 处理时间格式 *//*
        String _endDate = "";
        try {
            _startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(startDate));
            _endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        StringBuffer buffer = new StringBuffer();
        if (startDate.equals(endDate)){
            buffer.append("select STRLEFT(create_time, 13) ");
            endDate += " 23:59:59";
        }else {
            buffer.append("SELECT STRLEFT(create_time, 10) ");
        }

        buffer.append(" k, count(1) v " +
                "FROM jmbi_lg_company WHERE  pay_mark=1  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "' " +
                "GROUP BY k order BY k ");
        String SQL = buffer.toString();
        return getForMap(SQL);
    }

    public Map<String, Object> PayTotal() {
        String SQL = " select count(*) as total from jmbi_lg_company where pay_mark=1 " ;
//                " AND create_time between '" + startDate + "' and  '" + endDate + "'   " ;
        Map<String, Object> map = jdbcTemplate.queryForMap(SQL);
        return map;
    }

    public Map<String, String> channelProportion() {
        String SQL = "SELECT  COUNT(DISTINCT t.company_id) as k, c.PAY_CHANNEL_NAME as v " +
                "FROM jmbi_ep_user_channel t, jmbi_ep_user_account a, jmbi_epay_channel c " +
                "WHERE t.channel_no = c.PAY_CHANNEL_NO  AND t.company_id = a.company_id  " +
//                "AND create_time between '" + startDate + "' and  '" + endDate + "'  " +
                "AND a.industry_id = 1004 GROUP BY c.PAY_CHANNEL_NO , c.PAY_CHANNEL_NAME ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    /**
     * 支付分析支付开通地域分布
     */
    public Map<String, String> payOpenClime(String startDate, String endDate) {
        String SQL = "SELECT areas as k, COUNT(*) as v " +
                "FROM (SELECT company_name, STRLEFT(cast(area as string),5) areas  " +
                "  FROM  jmbi_lg_company WHERE pay_mark = 1 " +
                "AND code_status = '2' " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "') t  " +
                "GROUP BY k  ;";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> payOpenClimeTop(String startDate, String endDate) {
        String SQL = "SELECT areas as k,COUNT(*) as v FROM (SELECT  " +
                "company_name, STRLEFT(cast(AREA as string),  5) areas  " +
                "FROM jmbi_lg_company WHERE pay_mark = 1 AND code_status = '2'  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "') t  " +
                "GROUP BY k ORDER BY v DESC LIMIT 10 ;";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> TransportTop(String startDate, String endDate) {
        String SQL = "SELECT company_name k, COUNT(*) v  " +
                "FROM jmbi_en_lg_line  " +
                "WHERE create_time between '" + startDate + "' and  '" + endDate + "'" +
                "GROUP BY k  " +
                "ORDER BY v DESC LIMIT 5 ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> cacheBaseArea() {
        String SQL = "select area_code as k,area_name as v from  jmbi_en_lg_base_area";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> cacheLg_inner_ports() {
        String SQL = "select area_id as k,chinese_name as v from  jmbi_en_lg_inner_ports";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> cacheLg_ports() {
        String SQL = "select area_id as k,chinese_name as v from  jmbi_en_lg_ports";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> cacheLg_air_ports() {
        String SQL = "select area_id as k,chinese_name as v from  jmbi_en_lg_air_ports";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> CarAnalysis(String startDate, String endDate) {
        String SQL = "SELECT vehicle_models as k,COUNT(*) as v  FROM jmbi_lg_vehicle WHERE check_status ='2' " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "'  " +
                "GROUP BY k  ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> airFreight(String startDate, String endDate) {
        String SQL = "SELECT address as k ,COUNT(address) v FROM  (SELECT  line_no,  CONCAT( " +
                " STRLEFT(cast(departure_area_id as STRING), 5), " +
                " STRLEFT(cast(destination_area_id as STRING), 5)  ) address  " +
                " FROM  jmbi_en_lg_line  " +
                " WHERE line_type = '121' AND check_status=1 " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "'  ) t " +
                "GROUP BY k ORDER BY v DESC LIMIT 10;";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> railTransport(String startDate, String endDate) {
        String SQL = "SELECT address as k ,COUNT(address) v FROM  (SELECT  line_no,  CONCAT( " +
                " STRLEFT(cast(departure_area_id as STRING), 5), " +
                " STRLEFT(cast(destination_area_id as STRING), 5)  ) address  " +
                " FROM  jmbi_en_lg_line  " +
                " WHERE line_type = '122' AND check_status=1 " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "' ) t  " +
                "GROUP BY k ORDER BY v DESC LIMIT 10;";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> landTransport(String startDate, String endDate) {
        String SQL = "SELECT address as k ,COUNT(address) v FROM  (SELECT  line_no,  CONCAT( " +
                " STRLEFT(cast(departure_area_id as STRING), 4), " +
                " STRLEFT(cast(destination_area_id as STRING), 4)  ) address  " +
                " FROM  jmbi_en_lg_line  " +
                " WHERE line_type = '123' AND check_status=1  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "' ) t " +
                "GROUP BY k ORDER BY v DESC LIMIT 10;";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> warehouseTransport(String startDate, String endDate) {
        String SQL = "SELECT address k, COUNT(address) v FROM (  " +
                "SELECT warehouse_no, STRLEFT(cast(location_area_id as STRING), 6) address FROM jmbi_en_lg_warehouse  " +
                "WHERE check_status = 1  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "' ) t  " +
                "GROUP BY k  ORDER BY v DESC LIMIT 10  ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> trafficAirTransport(String startDate, String endDate) {
        String SQL = "SELECT address as k ,COUNT(address) v FROM  (SELECT  line_no,  CONCAT( " +
                " STRLEFT(cast(departure_area_id as STRING), 3), " +
                " STRLEFT(cast(destination_area_id as STRING), 3)  ) address  " +
                " FROM  jmbi_lg_line  " +
                " WHERE line_type = '237' AND check_status='1' " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "' ) t  " +
                "GROUP BY k ORDER BY v DESC LIMIT 10;";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> TransportTopCache() {
        String SQL = "select id as k,company_name as v from  jmbi_en_lg_company;";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> CarAnalysisCache() {
        String SQL = "SELECT option_code as k,option_name as v FROM jmbi_lg_base_data_option WHERE  classify_code = 'vehicle_models' ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    @Override
    public Map<String, String> CarAnalysisCache_() {
        String SQL = "SELECT option_code AS k,option_name AS v FROM jmbi_lg_base_data_option WHERE  classify_code = 'vehicle_type' ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> StorageHeat(String startDate, String endDate) {
        String SQL = "SELECT STRLEFT(cast(location_area_id as STRING),  6) as k ,COUNT(*) as v  " +
                "FROM jmbi_en_lg_warehouse WHERE check_status = 1  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "'  " +
                "GROUP BY k ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> StorageHeat_area(String startDate, String endDate) {
        String SQL = "SELECT STRLEFT(cast(location_area_id as STRING),  6) as k ,SUM(total_area) v  " +
                "FROM jmbi_en_lg_warehouse WHERE check_status = 1  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "'  " +
                "GROUP BY k ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public List<Map<String, Object>> StorageArea(String startDate, String endDate) {
        String SQL = "SELECT SUM(counts) total_warehouse, SUM(total_area) total_area, SUM(available_area) available_area  " +
                "FROM   (SELECT COUNT(*) counts, STRLEFT(CAST(location_area_id AS STRING), 5) address, " +
                "SUM(total_area) total_area,SUM(available_area) available_area   FROM jmbi_en_lg_warehouse WHERE check_status = 1    " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "'  " + "  GROUP BY address)  tt; ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(SQL);
        return list;
    }

    public Map<String, String> StorageAreaTop(String startDate, String endDate) {
        String SQL = "SELECT STRLEFT(cast(location_area_id as STRING), 6) k ,SUM(total_area) v " +
                "FROM jmbi_en_lg_warehouse WHERE check_status = 1  " +
                "AND create_time  between '" + startDate + "' and  '" + endDate + "'  " +
                "GROUP BY k ORDER BY v DESC LIMIT 5 ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> StorageCompanyTop(String startDate, String endDate) {
        String SQL = "select k,v from (" +
                "SELECT company_id, company_name as k,SUM(total_area) as v FROM jmbi_en_lg_warehouse  " +
                "WHERE check_status=1  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "'  " +
                "GROUP BY company_id,k) t   ORDER BY v DESC LIMIT 5  ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> StorageTypeTop(String startDate, String endDate) {
        String SQL = "SELECT a.warehouse_type k,SUM(a.total_area)  v FROM (SELECT  STRLEFT(cast(location_area_id as STRING), 6) area_address, warehouse_type, SUM(total_area) total_area FROM  " +
                " jmbi_en_lg_warehouse WHERE check_status = 1  AND create_time between '" + startDate + "' and  '" + endDate + "'  " + " GROUP BY area_address, warehouse_type) a  " +
                "INNER JOIN  " +
                "(SELECT  STRLEFT(cast(location_area_id as STRING), 6)  area_address, COUNT(*) counts, " +
                " SUM(total_area) total_area,SUM(available_area) available_area FROM jmbi_en_lg_warehouse  " +
                "  WHERE check_status = 1 AND create_time between '" + startDate + "' and  '" + endDate + "'  " + " GROUP BY area_address ORDER BY total_area DESC LIMIT 5) b  " +
                "ON a.area_address=b.area_address  " +
                "GROUP BY a.warehouse_type ORDER BY  v";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> trafficStorage(String startDate, String endDate) {
        String SQL = "SELECT address k, COUNT(address) v FROM (  " +
                "SELECT warehouse_no, STRLEFT(cast(location_area_id as STRING), 5) address FROM jmbi_lg_warehouse  " +
                "WHERE check_status = 1  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "' ) t  " +
                "GROUP BY k  ORDER BY v DESC LIMIT 10 ;";
        Map<String, String> map = getForMap(SQL);
        return map;
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
