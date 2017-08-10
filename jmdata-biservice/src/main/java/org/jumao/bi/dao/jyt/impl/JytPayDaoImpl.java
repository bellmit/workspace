package org.jumao.bi.dao.jyt.impl;

import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.jyt.JytPayDao;
import org.jumao.bi.entites.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/6/14.
 */
@Repository("jytPayDaoImpl")
public class JytPayDaoImpl implements JytPayDao {

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
        String SQL = "SELECT company_id as k ,COUNT(*) as v FROM jmbi_lg_line " +
                "where create_time between '" + startDate + "' and  '" + endDate + "'  " +
                "GROUP BY k ORDER BY v DESC LIMIT 5 ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> cacheBaseArea() {
        String SQL = "select area_code as k,area_name as v from  jmbi_lg_base_area";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> cacheLg_inner_ports() {
        String SQL = "select area_id as k,chinese_name as v from  jmbi_lg_inner_ports";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> cacheLg_ports() {
        String SQL = "select area_id as k,chinese_name as v from  jmbi_lg_ports";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> cacheLg_air_ports() {
        String SQL = "select area_id as k,chinese_name as v from  jmbi_lg_air_ports";
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

    public Map<String, String> trafficHighway(String startDate, String endDate) {
        String SQL = "SELECT address as k ,COUNT(address) v FROM  (SELECT  line_no,  CONCAT( " +
                " STRLEFT(cast(departure_area_id as STRING), 5), " +
                " STRLEFT(cast(destination_area_id as STRING), 5)  ) address  " +
                " FROM  jmbi_lg_line  " +
                " WHERE line_type = '231' AND check_status='1' " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "'  ) t " +
                "GROUP BY k ORDER BY v DESC LIMIT 10;";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> trafficRailway(String startDate, String endDate) {
        String SQL = "SELECT address as k ,COUNT(address) v FROM  (SELECT  line_no,  CONCAT( " +
                " STRLEFT(cast(departure_area_id as STRING), 5), " +
                " STRLEFT(cast(destination_area_id as STRING), 5)  ) address  " +
                " FROM  jmbi_lg_line  " +
                " WHERE line_type = '232' AND check_status='1' " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "' ) t  " +
                "GROUP BY k ORDER BY v DESC LIMIT 10;";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> trafficInlandNavigation(String startDate, String endDate) {
        String SQL = "SELECT address as k ,COUNT(address) v FROM  (SELECT  line_no,  CONCAT( " +
                " STRLEFT(cast(departure_area_id as STRING), 4), " +
                " STRLEFT(cast(destination_area_id as STRING), 4)  ) address  " +
                " FROM  jmbi_lg_line  " +
                " WHERE line_type = '233' AND check_status='1'  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "' ) t " +
                "GROUP BY k ORDER BY v DESC LIMIT 10;";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> trafficOceanShipping(String startDate, String endDate) {
        String SQL = "SELECT address as k ,COUNT(address) v FROM  (SELECT  line_no,  CONCAT( " +
                " STRLEFT(cast(departure_area_id as STRING), 2), " +
                " STRLEFT(cast(destination_area_id as STRING), 2)  ) address  " +
                " FROM  jmbi_lg_line  " +
                " WHERE line_type = '234' AND check_status='1'  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "' ) t  " +
                "GROUP BY k ORDER BY v DESC LIMIT 10;";
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
        String SQL = "select id as k,company_name as v from  jmbi_lg_company;";
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
        String SQL = "SELECT STRLEFT(cast(location_area_id as STRING),  5) as k ,COUNT(*) as v  " +
                "FROM jmbi_lg_warehouse WHERE check_status = 1  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "'  " +
                "GROUP BY k ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> StorageHeat_area(String startDate, String endDate) {
        String SQL = "SELECT STRLEFT(cast(location_area_id as STRING),  5) as k ,SUM(total_area) v  " +
                "FROM jmbi_lg_warehouse WHERE check_status = 1  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "'  " +
                "GROUP BY k ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public List<Map<String, Object>> StorageArea(String startDate, String endDate) {
        String SQL = "SELECT SUM(counts) total_warehouse, SUM(total_area) total_area, SUM(available_area) available_area FROM  " +
                "(SELECT * FROM " +
                "  (SELECT COUNT(*) counts, STRLEFT(cast(location_area_id as STRING), 5) address,SUM(total_area) total_area,SUM(available_area) available_area " +
                "  FROM jmbi_lg_warehouse WHERE check_status = 1  " +
                "    AND create_time between '" + startDate + "' and  '" + endDate + "'  " +
                "GROUP BY address) t ) tt  ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(SQL);
        return list;
    }

    public Map<String, String> StorageAreaTop(String startDate, String endDate) {
        String SQL = "SELECT STRLEFT(cast(location_area_id as STRING), 5) k ,SUM(total_area) v " +
                "FROM jmbi_lg_warehouse WHERE check_status = 1  " +
                "AND create_time  between '" + startDate + "' and  '" + endDate + "'  " +
                "GROUP BY k ORDER BY v DESC LIMIT 5 ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> StorageCompanyTop(String startDate, String endDate) {
        String SQL = "SELECT company_id as k,SUM(total_area) as v FROM jmbi_lg_warehouse  " +
                "WHERE check_status=1  " +
                "AND create_time between '" + startDate + "' and  '" + endDate + "'  " +
                "GROUP BY k  ORDER BY v DESC LIMIT 5  ";
        Map<String, String> map = getForMap(SQL);
        return map;
    }

    public Map<String, String> StorageTypeTop(String startDate, String endDate) {
        String SQL = "SELECT a.warehouse_type k,SUM(a.total_area)  v FROM (SELECT  STRLEFT(cast(location_area_id as STRING), 5) area_address, warehouse_type, SUM(total_area) total_area FROM  " +
                " jmbi_lg_warehouse WHERE check_status = 1  AND create_time between '" + startDate + "' and  '" + endDate + "'  " + " GROUP BY area_address, warehouse_type) a  " +
                "INNER JOIN  " +
                "(SELECT  STRLEFT(cast(location_area_id as STRING), 5)  area_address, COUNT(*) counts, " +
                " SUM(total_area) total_area,SUM(available_area) available_area FROM jmbi_lg_warehouse  " +
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
