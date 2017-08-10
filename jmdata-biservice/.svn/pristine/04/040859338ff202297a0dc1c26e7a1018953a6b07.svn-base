package org.jumao.bi.service.impl.jyt.deal;

import org.apache.commons.collections.map.HashedMap;
import org.jumao.bi.component.BaseDaoCallback;
import org.jumao.bi.component.BaseDataExtract;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.component.TableChartBuilder;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.service.jyt.deal.ILogisticsProvidersAnalysis;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15.
 */
public class LogisticsProvidersAnalysisImpl extends BaseDataExtract implements ILogisticsProvidersAnalysis {
    @Autowired
    private BaseDaoCallback baseDaoCallback;
    @Autowired
    private TableChartBuilder baseChartBuilder;

    private ComponentContext setCustomContext(String platform, String startDate, String endDate, String lineType,
                                              String wareHouseType, String sql,Map colNameMap,String[] desensitizationCols){
        ComponentContext context = SpringContextUtil.getBean(ComponentContext.class);
        List<String> transList = new ArrayList<String>();
        transList.add("platform");
        transList.add("startDate");
        transList.add("endDate");
        ParamBean param = null;
        if (lineType!=null){
            transList.add("lineType");
            param = new ParamBean(platform,startDate,endDate,lineType);
        }else if (wareHouseType!=null){
            transList.add("wareHouseType");
            param = new ParamBean(platform,startDate,endDate,wareHouseType);
        }

        Map utilNestedMap = new HashedMap();
        utilNestedMap.put(ParamConst.Col_Name_Map,colNameMap);
        Map utilMap = new HashMap();
        // 设置脱敏字段
        utilMap.put(ParamConst.Desensitization_Col,desensitizationCols);
        super.setContext(param, transList, sql,  utilMap,utilNestedMap,null,context);
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
        }else if ("wareHouseType".equalsIgnoreCase(tranParam)){
            String itemIdValue = param.getItemId();
            if ("all".equalsIgnoreCase(itemIdValue)){
                param.setItemId("");
            }else{
                param.setItemId(" and warehouse_type = "+param.getItemId());
            }
        }
    }

    @Override
    public Response getTopLogisticsTypeByRMB(String platform, String startDate, String endDate, String lineType) throws Exception {
        String sql = "with tmp_lg_order as ("
                +"  select count(*) order_num,round(sum(nvl(total_price,0))/10000,2) total_money,seller_company_id company_id "
                +"  from jmbi_lg_order "
                +"  where order_type != '0' and active_flag = '0' :itemId"
                +"  AND create_time >= ':startDate' AND create_time <= ':endDate' "
                +"  group by seller_company_id"
                +"),tmp_lg_company_2_order as ("
                +"  select c.id,c.company_name,c.service_level,t.order_num,t.total_money"
                +"  from tmp_lg_order t,jmbi_lg_company c where t.company_id = c.id"
                +"),tmp_lg_cnt as ("
                +"  select a.id id,count(1) as line_count "
                +"  from tmp_lg_company_2_order a,jmbi_lg_line b "
                +"  where a.id = b.company_id and b.check_status ='1' group by a.id"
                +"),result_list_tmp as (select a.company_name as itemName, cast(a.service_level as string) as itemValue1,"
                +"b.line_count as itemValue2,a.order_num as itemValue3,round(a.total_money,2) as itemValue4"
                +" from tmp_lg_company_2_order a,tmp_lg_cnt b where a.id = b.id)"
                +",result_list as (select * from result_list_tmp order by itemValue4 desc limit 10)"
                +",union_tab as (select * from result_list union all"
                +" select '合计','',sum(t.itemValue2),sum(t.itemValue3),sum(t.itemValue4) from result_list t)"
                +"select * from union_tab t where t.itemValue4 is not null";
        Map<String,String> colNameMap = new LinkedHashMap();
        colNameMap.put("itemName","物流商名称");
        colNameMap.put("itemValue1","等级");
        colNameMap.put("itemValue2","运力");
        colNameMap.put("itemValue3","订单数");
        colNameMap.put("itemValue4","金额(万元)");
        String[] desensitizationCols = {"itemName"};
        ComponentContext context = this.setCustomContext(platform, startDate, endDate, lineType,null, sql,colNameMap,desensitizationCols);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    @Override
    public Response getTopLogisticsTypeByDollar(String platform, String startDate, String endDate, String lineType) throws Exception {
        return null;
    }

    @Override
    public Response getTopWarehousingByRMB(String platform, String startDate, String endDate, String wareHouseType) throws Exception {
        String sql = "with tmp_lg_order as ("
                +"  select count(*) order_num,round(sum(nvl(total_price,0))/10000,2) total_money,seller_company_id company_id "
                +"  from jmbi_lg_order "
                +"  where order_type != '0' and active_flag = '0' and line_type='235'"
                +"  AND create_time >= ':startDate' AND create_time <= ':endDate' "
                +"  group by seller_company_id"
                +"),tmp_lg_company_2_order as ("
                +"  select c.id,c.company_name,c.service_level,t.order_num,t.total_money"
                +"  from tmp_lg_order t,jmbi_lg_company c where t.company_id = c.id"
                +"),tmp_lg_cnt as ("
                +"  select a.id id,count(1) as warehourse_count "
                +"  from tmp_lg_company_2_order a,jmbi_lg_warehouse b "
                +"  where a.id = b.company_id and b.check_status =1 :wareHouseType group by a.id"
                +"),result_list_tmp as (select a.company_name as itemName, "
                +"b.warehourse_count as itemValue1,a.order_num as itemValue2,round(a.total_money,2) as itemValue3"
                +" from tmp_lg_company_2_order a,tmp_lg_cnt b where a.id = b.id)"
                +",result_list as (select * from result_list_tmp order by itemValue3 desc limit 10)"
                +",union_tab as (select * from result_list limit 10 union all"
                +" select '合计',sum(t.itemValue1),sum(t.itemValue2),sum(t.itemValue3) from result_list t)"
                +"select * from union_tab t where t.itemValue3 is not null";
        Map<String,String> colNameMap = new LinkedHashMap();
        colNameMap.put("itemName","仓库");
        colNameMap.put("itemValue1","仓储数");
        colNameMap.put("itemValue2","订单数");
        colNameMap.put("itemValue3","人民币(万元)");
        String[] desensitizationCols = {"itemName"};
        ComponentContext context = this.setCustomContext(platform, startDate, endDate, null,wareHouseType, sql,colNameMap,desensitizationCols);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    @Override
    public Response getTopWarehousingByDollar(String platform, String startDate, String endDate, String wareHouseType) throws Exception {
        return null;
    }
}
