package org.jumao.bi.service.impl.jzx;

import org.jumao.bi.component.BaseDaoCallback;
import org.jumao.bi.component.ComponentContext;
import org.jumao.bi.component.DataExtractAdapt;
import org.jumao.bi.component.ParamConst;
import org.jumao.bi.component.builder.LineCBD;
import org.jumao.bi.service.jzx.JzxBusinessTrendSvc;
import org.jumao.bi.utis.DateUtils;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13.
 */
public class JzxBusinessTrendImpl extends DataExtractAdapt implements JzxBusinessTrendSvc {
    @Autowired
    private BaseDaoCallback baseDaoCallback;
    @Autowired
    private LineCBD baseChartBuilder;

    /**
     * 客户端设置环境变量
     * @param platform
     * @param startDate
     * @param endDate
     * @param unitName
     * @param legendName
     * @param dateType
     * @param sql
     * @return
     */
    public ComponentContext setCustomContext(String platform, String startDate, String endDate,
                                              String unitName, String legendName, String dateType,String sql){
        ComponentContext context = SpringContextUtil.getBean(ComponentContext.class);
        // 需转换的参数
        List<String> transList = new ArrayList<String>();
        transList.add(ParamConst.Param_Plat_Form);
        transList.add(ParamConst.Param_Start_Date);
        transList.add(ParamConst.Param_End_Date);
        transList.add(ParamConst.Param_Date_Type);
        // 参数值map
        Map<String,String> paramBeanMap = new HashMap();
        paramBeanMap.put(ParamConst.Param_Plat_Form,platform);
        paramBeanMap.put(ParamConst.Param_Start_Date,startDate);
        paramBeanMap.put(ParamConst.Param_End_Date,endDate);
        paramBeanMap.put(ParamConst.Param_Date_Type,dateType);

        Map utilMap = new HashMap();
        // 设置单位
        utilMap.put(ParamConst.Unit_Name,unitName);
        // 图例
        utilMap.put(ParamConst.Legend_Name,legendName);
        super.setContext(paramBeanMap, transList, sql,  utilMap,null,null,context);
        return context;
    }

    /**
     * 补全没有数据的时间
     * @param dataInfos
     * @param dateList
     */
    public void compleDateData(List<Map<String, Object>> dataInfos, List<String> dateList) {
        for(int i=0;i<dateList.size();i++){
            String currentDate = dateList.get(i);
            boolean isExist = false;
            for(Map dataInfo: dataInfos){
                String rdate = dataInfo.get(ParamConst.Col_Name_Item_Name).toString();
                if (rdate.compareTo(currentDate)==0){
                    isExist = true;
                    break;
                }else if(rdate.compareTo(currentDate)>0){
                    break;
                }
            }
            // 如果不存在增加
            if (!isExist){
                Map map = new HashMap();
                map.put(ParamConst.Col_Name_Item_Name,currentDate);
                map.put(ParamConst.Col_Name_Item_Value,0);
                dataInfos.add(i,map);
            }
        }
    }

    @Override
    public void dealOtherResultData(List<Map<String, Object>> dataInfos,ComponentContext context) {
        String dateType = context.getParamBeanMap().get(ParamConst.Param_Date_Type);
        // 补全开始日期和结束日期中间没有数据的日期
        String beginDate = context.getParamBeanMap().get(ParamConst.Param_Start_Date);
        String endDate = context.getParamBeanMap().get(ParamConst.Param_End_Date);
        beginDate = DateUtils.str2OtherStr(beginDate,DateUtils.DAY_FORMAT,DateUtils.DAY_CN_FORMAT);
        endDate = DateUtils.str2OtherStr(endDate,DateUtils.DAY_FORMAT,DateUtils.DAY_CN_FORMAT);
        if ("D".equalsIgnoreCase(dateType)){
            List<String> dateList = DateUtils.getDateList(beginDate,endDate,DateUtils.DAY_CN_FORMAT);
            compleDateData(dataInfos,dateList);
        } else if ("W".equalsIgnoreCase(dateType)){
            List<String> weekList = DateUtils.getWeekList(beginDate,endDate,DateUtils.DAY_CN_FORMAT);
            compleDateData(dataInfos,weekList);
        }  else if ("M".equalsIgnoreCase(dateType)){
            List<String> monList = DateUtils.getMonList(beginDate,endDate,DateUtils.DAY_CN_FORMAT);
            compleDateData(dataInfos,monList);
        }
    }

    /**
     * 拼接sql
     * @param dateType
     * @param methodType
     * @return
     * @throws Exception
     */
    public String joinSql(String dateType, String methodType) throws Exception {
        StringBuilder sql = new StringBuilder();
        StringBuilder twoDateTabSql = null;
        StringBuilder dateTabHandleSql = new StringBuilder(
                ",    date_tab_handle AS(SELECT "+
                        "        if (begin_date > ':startDate', begin_date, ':startDate') begin_date, "+
                        "            if (end_date > ':endDate', ':endDate', end_date) end_date,:pay_amt "+
                        "        FROM two_date_tab t WHERE t.create_time >= ':startDate' "+
                        "        AND t.create_time <= ':endDate') "
        );
        StringBuilder dateFormateTabSql = new StringBuilder(
                ",   date_formate_tab AS(SELECT from_unixtime(unix_timestamp(begin_date), 'yyyy/MM/dd') begin_date, "+
                        "        from_unixtime(unix_timestamp(end_date), 'yyyy/MM/dd') end_date,:pay_amt FROM date_tab_handle t) "
        );
        StringBuilder lastSelectSql = new StringBuilder(
                "SELECT concat(begin_date, ' - ', end_date) item_name, "+
                        "    count(1) item_value "+
                        "FROM date_formate_tab "+
                        "GROUP BY concat(begin_date, ' - ', end_date) "+
                        "ORDER BY concat(begin_date, ' - ', end_date)"
        );
        switch (dateType){
            case "D":
                twoDateTabSql = new StringBuilder(
                        "with two_date_tab as( "+
                                "    select trunc(t.create_time, 'J') begin_date,:pay_amt from jmbi_jzx_demand t where t.delete_flag = 0 and create_time IS NOT NULL" +
                                " and t.create_time >= ':startDate' and t.create_time <= ':endDate' "+
                                ")"
                );
                dateTabHandleSql = new StringBuilder("");
                dateFormateTabSql = new StringBuilder(
                        ", date_formate_tab as( "+
                                "    select from_unixtime(unix_timestamp(begin_date), 'yyyy/MM/dd') begin_date,:pay_amt from two_date_tab t "+
                                ") "
                );
                lastSelectSql = new StringBuilder(
                        "select begin_date item_name, count(1) item_value from date_formate_tab group by begin_date order by begin_date"
                );
                break;
            case "W":
                twoDateTabSql = new StringBuilder(
                        "WITH two_date_tab AS "+
                                "    (SELECT trunc(t.create_time, 'D') begin_date, "+
                                "        days_add(trunc(t.create_time, 'D'), 6) end_date, "+
                                "        create_time,:pay_amt FROM jmbi_jzx_demand t WHERE t.delete_flag = 0 AND create_time IS NOT NULL AND t.create_time >= ':startDate' "+
                                "        AND t.create_time <= ':endDate') "
                );
                break;
            case "M":
                twoDateTabSql = new StringBuilder(
                        "WITH two_date_tab AS "+
                                "    (SELECT trunc(t.create_time, 'MON') begin_date, "+
                                "        days_sub(add_months(trunc(t.create_time, 'MON'), 1), 1) end_date, "+
                                "        create_time,:pay_amt FROM jmbi_jzx_demand t WHERE t.delete_flag = 0 AND create_time IS NOT NULL AND t.create_time >= ':startDate' "+
                                "        AND t.create_time <= ':endDate') "
                );
                break;
            default:
                throw new Exception("dateType参数不对");
        }
        sql.append(twoDateTabSql).append(dateTabHandleSql).append(dateFormateTabSql).append(lastSelectSql);
        // 不同方法进行表的替换
        String resultSql = null;
        switch (methodType){
            case "getIntentionOrder":
                resultSql = sql.toString().replaceAll(":pay_amt","''");
                break;
            case "getProjectOrder":
                resultSql = sql.toString().replaceAll("jmbi_jzx_demand","jmbi_jzx_project")
                        .replaceAll(":pay_amt","''");
                break;
            case "getContractAmount":
                resultSql = sql.toString().replaceAll("jmbi_jzx_demand","jmbi_jzx_project")
                        .replaceAll("count\\(1\\)","round(sum(nvl(pay_amt,0))/10000,2)")
                        .replaceAll(":pay_amt","pay_amt");
                break;
            case "getNewUsers":
                resultSql = sql.toString().replaceAll("jmbi_jzx_demand","jmbi_uc_jumore_user")
                        .replaceAll("create_time","register_time")
                        .replaceAll(":pay_amt","''")
                        .replaceAll("t.delete_flag = 0","t.platform = '1019'");
                break;
            default:
                throw new Exception("methodType参数不对");
        }
        return resultSql;
    }

    @Override
    public Response getIntentionOrder(String platform, String startDate, String endDate, String dateType) throws Exception {
        // baseChartBuilder = SpringContextUtil.getBean("lineCBD");
        String sql = joinSql(dateType,"getIntentionOrder");
        ComponentContext context = this.setCustomContext(platform, startDate, endDate, ParamConst.Unit_Bi, "意向单", dateType, sql);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    @Override
    public Response getProjectOrder(String platform, String startDate, String endDate, String dateType) throws Exception {
        String sql = joinSql(dateType,"getProjectOrder");
        ComponentContext context = this.setCustomContext(platform, startDate, endDate, ParamConst.Unit_Bi, "项目订单", dateType, sql);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    @Override
    public Response getContractAmount(String platform, String startDate, String endDate, String dateType) throws Exception {
        String sql = joinSql(dateType,"getContractAmount");
        ComponentContext context = this.setCustomContext(platform, startDate, endDate, ParamConst.Unit_Million_Yuan, "合同金额", dateType, sql);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }

    @Override
    public Response getNewUsers(String platform, String startDate, String endDate, String dateType) throws Exception {
        String sql = joinSql(dateType,"getNewUsers");
        ComponentContext context = this.setCustomContext(platform, startDate, endDate, ParamConst.Unit_Bi, "新增用户数", dateType, sql);
        return extractDataAndBuildChart(baseDaoCallback,baseChartBuilder,context);
    }
}
