package org.jumao.bi.service.impl.jyt.deal;

import org.apache.commons.collections.map.HashedMap;
import org.jumao.bi.component.BaseChartBuilder;
import org.jumao.bi.component.BaseDaoCallback;
import org.jumao.bi.component.BaseDataExtract;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.service.jyt.deal.ILogisticsType;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/6/16.
 */
public class LogisticsTypeImpl extends BaseDataExtract implements ILogisticsType{
    @Autowired
    private BaseDaoCallback baseDaoCallback;

    private BaseChartBuilder baseChartBuilder;

    public ComponentContext setCustomContext(String platform, String startDate, String endDate, String lineType,  String sql, Map colNameMap){
        ComponentContext context = SpringContextUtil.getBean(ComponentContext.class);
        List<String> transList = new ArrayList<String>();
        transList.add("platform");
        transList.add("startDate");
        transList.add("endDate");
        ParamBean param = null;
        transList.add("lineType");
        param = new ParamBean(platform,startDate,endDate,lineType);

        // 设置列名映射
        Map utilNestedMap = new HashedMap();
        utilNestedMap.put(ParamConst.Col_Name_Map,colNameMap);
        super.setContext(param, transList, sql, null,utilNestedMap,null,context);
        return context;
    }

    @Override
    public void dealOtherSqlParam(ParamBean param, String tranParam) throws ParseException {
        if ("lineType".equalsIgnoreCase(tranParam)){
            String itemIdValue = param.getItemId();
            if ("all".equalsIgnoreCase(itemIdValue)){
                param.setItemId("");
            }else{
                param.setItemId(" and line_type = '"+param.getItemId()+"'");
            }
        }
    }

    /**
     * 拼接sql
     * @param lineType
     * @param methodType
     * @return
     * @throws Exception
     */
    public String joinSql(String lineType,String methodType) throws Exception {
        StringBuilder sql = new StringBuilder();
        StringBuilder moneyOtherSql = new StringBuilder();
        moneyOtherSql.append("    from jmbi_lg_order "
                + "    where delete_flag='0' and active_flag='0'"
                + "    AND create_time >= ':startDate'"
                + "    AND create_time <= ':endDate'"
                + "    and line_type = '231'"
                + "    group by line_id "
                + ")");
        StringBuilder lineSql = new StringBuilder(",tmp_money_with_area as (select a.*,b.departure_area_id,b.destination_area_id from tmp_money a,jmbi_lg_line b where a.line_id = b.id) ");
        StringBuilder resultSql;
        StringBuilder selectSql;
        StringBuilder selectSql1 = new StringBuilder();
        StringBuilder selectSql2 = new StringBuilder();
        StringBuilder moneySelectSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();

        // 根据不同的lineType替换line_type
        int index = moneyOtherSql.indexOf(ServiceConst.Highway);
        moneyOtherSql = moneyOtherSql.replace(index,index+3,lineType);
        // 不同的方法查询条件和是否有合计不一样
        if ("getFreightFlow".equals(methodType)){
            moneySelectSql = new StringBuilder("with tmp_money as ( select round(sum(nvl(total_price,0))/10000,2) total_money,line_id");
            selectSql1 = new StringBuilder("select REGEXP_REPLACE(b.area_name,'[省市自治区回族维吾尔壮族特别行政]','') as itemName, "
                    + " REGEXP_REPLACE(c.area_name,'[省市自治区回族维吾尔壮族特别行政]','') as itemValue1,a.total_money as itemValue2");
            selectSql2 = new StringBuilder("select b.chinese_name as itemName,c.chinese_name as itemValue1,a.total_money as itemValue2");
            totalSql = new StringBuilder();
        } else if ("getTopLgTypeByRMB".equals(methodType)){
            moneySelectSql = new StringBuilder("with tmp_money as (select round(sum(nvl(total_price,0))/10000,2) total_money,"
                    + "sum(nvl(weight,0)) total_weight,sum(nvl(volume,0)) total_volume,line_id");
            selectSql1 = new StringBuilder(",result_list as (select concat(REGEXP_REPLACE(b.area_name,'[省市自治区回族维吾尔壮族特别行政]',''),"
                    + "'---',REGEXP_REPLACE(c.area_name,'[省市自治区回族维吾尔壮族特别行政]','')) itemName,"
                    + "0 itemValue1,round(a.total_weight,2) itemValue2,round(a.total_volume,2) itemValue3,round(a.total_money,2) itemValue4");
            selectSql2 = new StringBuilder(",result_list as (select concat(b.chinese_name,'---',c.chinese_name) itemName,"
                    + "0 itemValue1,round(a.total_weight,2) itemValue2,round(a.total_volume,2) itemValue3,round(a.total_money,2) itemValue4");
            totalSql = new StringBuilder("),union_tab as (select * from result_list union all "
                    + "select '合计',sum(t.itemValue1),sum(t.itemValue2),sum(t.itemValue3),sum(t.itemValue4) from result_list t)"
                    + "select * from union_tab t where t.itemValue4 is not null");
        }
        if (lineType.equals(ServiceConst.Highway)) {
            selectSql = selectSql1;
            resultSql = new StringBuilder(" from tmp_money_with_area a,jmbi_lg_base_area b,jmbi_lg_base_area c"
                    + " where cast(strleft(cast(a.departure_area_id as string),5) as bigint) = b.area_code"
                    + " and cast(strleft(cast(a.destination_area_id as string),5) as bigint) = c.area_code"
                    + " order by a.total_money desc limit 10 ");

        } else if (lineType.equals(ServiceConst.Railway)) {
            selectSql = selectSql1;
            resultSql = new StringBuilder(" from tmp_money_with_area a,jmbi_lg_base_area b,jmbi_lg_base_area c"
                    + " where cast(strleft(cast(a.departure_area_id as string),5) as bigint) = b.area_code"
                    + " and cast(strleft(cast(a.destination_area_id as string),5) as bigint) = c.area_code"
                    + " order by a.total_money desc limit 10 ");

        } else if (lineType.equals(ServiceConst.Water_Shipping)) {
            selectSql = selectSql2;
            resultSql = new StringBuilder(" from tmp_money_with_area a,jmbi_lg_inner_ports b,jmbi_lg_inner_ports c"
                    + " where strleft(cast(a.departure_area_id as string),4) = b.area_id"
                    + " and strleft(cast(a.destination_area_id as string),4) = c.area_id"
                    + " order by a.total_money desc limit 10");

        } else if (lineType.equals(ServiceConst.Sea_Shipping)) {
            selectSql = selectSql2;
            resultSql = new StringBuilder(" from tmp_money_with_area a,jmbi_lg_ports b,jmbi_lg_ports c"
                    + " where strleft(cast(a.departure_area_id as string),5) = b.area_id"
                    + " and strleft(cast(a.destination_area_id as string),5) = c.area_id"
                    + " order by a.total_money desc limit 10");

        } else if (lineType.equals(ServiceConst.Air_Transport)) {
            selectSql = selectSql2;
            resultSql = new StringBuilder(" from tmp_money_with_area a,jmbi_lg_air_ports b,jmbi_lg_air_ports c"
                    + " where strleft(cast(a.departure_area_id as string),3) = b.area_id"
                    + " and strleft(cast(a.destination_area_id as string),3) = c.area_id"
                    + " order by a.total_money desc limit 10");

        } else if (lineType.equals(ServiceConst.Ware_House)) {
            selectSql = selectSql1;
            resultSql = new StringBuilder(" from tmp_money_with_area a,jmbi_lg_base_area b,jmbi_lg_base_area c"
                    + " where cast(strleft(cast(a.departure_area_id as string),5) as bigint) = b.area_code"
                    + " and cast(strleft(cast(a.destination_area_id as string),5) as bigint) = c.area_code"
                    + " order by a.total_money desc limit 10 ");
            lineSql = new StringBuilder(",tmp_money_with_area as (select a.*,b.location_area_id as departure_area_id,b.location_area_id as destination_area_id from tmp_money a,jmbi_lg_warehouse b where a.line_id = b.id) ");

        } else {
            throw new Exception("lineType参数不对");
        }
        sql.append(moneySelectSql).append(moneyOtherSql).append(lineSql).append(selectSql).append(resultSql).append(totalSql);
        return sql.toString();
    }

    public Response getFreightFlow(String platform, String startDate, String endDate, String lineType) throws Exception {
        Map<String,String> colNameMap = new LinkedHashMap();
        String sql = joinSql(lineType,"getFreightFlow");
        colNameMap.put("itemName","departure_area");
        colNameMap.put("itemValue1","destination_area");
        colNameMap.put("itemValue2","total_money");
        baseChartBuilder = SpringContextUtil.getBean("flowChartBuilder");
        ComponentContext context = this.setCustomContext(platform, startDate, endDate, null, sql,colNameMap);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    public Response getTopLgTypeByRMB(String platform, String startDate, String endDate, String lineType) throws Exception {
        Map<String,String> colNameMap = new LinkedHashMap();
        String sql = joinSql(lineType,"getTopLgTypeByRMB");
        colNameMap.put("itemName","发送方向");
        colNameMap.put("itemValue1","产品类型(个)");
        colNameMap.put("itemValue2","重量(T)");
        colNameMap.put("itemValue3","体积(m^2)");
        colNameMap.put("itemValue4","金额(万元)");
        baseChartBuilder = SpringContextUtil.getBean("tableChartBuilder");
        ComponentContext context = this.setCustomContext(platform, startDate, endDate, lineType, sql,colNameMap);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    public Response getTopLgTypeByDollar(String platform, String startDate, String endDate, String lineType) throws Exception {
        return null;
    }
}
