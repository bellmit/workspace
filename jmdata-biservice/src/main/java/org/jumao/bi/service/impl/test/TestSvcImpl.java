package org.jumao.bi.service.impl.test;

import org.apache.commons.collections.map.HashedMap;
import org.jumao.bi.component.BaseDaoCallback;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.DataExtractAdapt;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.component.builder.TableCBD;
import org.jumao.bi.service.test.TestSvc;
import org.jumao.bi.utis.DateUtils;
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
 * Created by Administrator on 2017/7/7.
 */
public class TestSvcImpl extends DataExtractAdapt implements TestSvc {
    @Autowired
    private BaseDaoCallback baseDaoCallback;
    @Autowired
    private TableCBD baseChartBuilder;

    private ComponentContext setCustomContext(String platform, String startDate, String endDate, String lineType,
                                              String wareHouseType, String sql, Map colNameMap, String[] desensitizationCols){
        ComponentContext context = SpringContextUtil.getBean(ComponentContext.class);
        List<String> transList = new ArrayList<String>();
        transList.add("platform");
        transList.add("startDate");
        transList.add("endDate");

        Map<String,String> paramBeanMap = new HashMap();
        paramBeanMap.put(ParamConst.Param_Plat_Form,platform);
        paramBeanMap.put(ParamConst.Param_Start_Date,startDate);
        paramBeanMap.put(ParamConst.Param_End_Date,endDate);
        if (lineType!=null){
            transList.add("lineType");
            paramBeanMap.put("lineType",lineType);
        }else if (wareHouseType!=null){
            transList.add("wareHouseType");
            paramBeanMap.put("wareHouseType",wareHouseType);
        }


        Map utilNestedMap = new HashedMap();
        utilNestedMap.put(ParamConst.Col_Name_Map,colNameMap);
        Map utilMap = new HashMap();
        // 设置脱敏字段
        utilMap.put(ParamConst.Desensitization_Col,desensitizationCols);
        super.setContext(paramBeanMap, transList, sql,  utilMap,utilNestedMap,null,context);
        return context;
    }

    @Override
    public void dealOtherSqlParam(Map<String,String> paramBeanMap, String tranParam) throws ParseException {
        if ("lineType".equalsIgnoreCase(tranParam)){
            String itemIdValue = paramBeanMap.get("lineType");
            if ("all".equalsIgnoreCase(itemIdValue)){
                paramBeanMap.put("lineType","");
            }else{
                paramBeanMap.put("lineType"," and line_type = '"+itemIdValue+"'");
            }
        }else if ("wareHouseType".equalsIgnoreCase(tranParam)){
            String itemIdValue = paramBeanMap.get("wareHouseType");
            if ("all".equalsIgnoreCase(itemIdValue)){
                paramBeanMap.put("wareHouseType","");
            }else{
                paramBeanMap.put("wareHouseType"," and wareHouseType = '"+itemIdValue+"'");
            }
        }
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
        /*colNameMap.put("itemName","仓库");
        colNameMap.put("itemValue1","仓储数");
        colNameMap.put("itemValue2","订单数");
        colNameMap.put("itemValue3","人民币(万元)");*/
        String[] desensitizationCols = {"itemname"};
        ComponentContext context = this.setCustomContext(platform, startDate, endDate, null,wareHouseType, sql,colNameMap,desensitizationCols);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }


    public static void main(String[] args){
        System.out.println(DateUtils.getNextDay2("2017/07/14",1,"yyyy/MM/dd"));
    }
}
