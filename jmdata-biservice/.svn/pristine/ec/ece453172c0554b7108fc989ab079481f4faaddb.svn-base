package org.jumao.bi.component;

import org.apache.log4j.Logger;
import org.jumao.bi.component.builder.AbstractChartBuilder;
import org.jumao.bi.dao.AbstractBaseDao;
import org.jumao.bi.utis.DesensitizationUtils;
import org.jumao.bi.utis.PlatFormUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 不依赖ParamBean类，采用map存放参数值
 * 不依赖BaseInfo类，采用map存放查询返回的数据
 * 其它业务逻辑还是使用父类的
 * 适配数据抽取并组装图同时参数个数较多的情况
 * Created by Administrator on 2017/6/27.
 */
public class DataExtractAdapt extends BaseDataExtract {

    @Autowired
    private AbstractBaseDao baseDao;

    private Logger logger = Logger.getLogger(this.getClass());

    public void setContext(Map<String,String> paramBeanMap,List<String> transList,
                           String sql,Map<String,?> utilMap,Map<String,Map<?,?>> utilNestedMap,
                           Map<String,Class<?>> returnTypeMap, ComponentContext context){
        context.setTransParam(transList);
        context.setParamBeanMap(paramBeanMap);
        context.setSql(sql);
        // 空值生成一个默认对象，防止后面报错或者重新判断空值
        context.setUntilMap(utilMap==null?new HashMap():utilMap);
        context.setUntilNestedMap(utilNestedMap==null?new HashMap<String,Map<?,?>>():utilNestedMap);
        context.setReturnTypeMap(returnTypeMap==null?new HashMap<String,Class<?>>():returnTypeMap);
    }

    @Override
    public String dealSqlWithParam(ComponentContext context) throws Exception {
        return dealSqlWithParam(context.getSql(),context.getParamBeanMap(),context.getTransParam());
    }

    /**
     * 参数替换
     * @param sql
     * @param paramBeanMap
     * @param transList
     * @return
     * @throws Exception
     */
    private String dealSqlWithParam(String sql, Map<String,String> paramBeanMap, List<String> transList) throws Exception{
        this.dealSqlParam(paramBeanMap,transList);
        String newSql = sql;
        for (String item:transList){
            newSql = newSql.replaceAll(":"+item, paramBeanMap.get(item));
        }
        return newSql;
    }

    /**
     * 参数处理
     * @param paramBeanMap
     * @param transList
     * @throws ParseException
     */
    private void dealSqlParam(Map<String,String> paramBeanMap, List<String> transList) throws ParseException {
        for (String tranParam : transList){
            if (ParamConst.Param_Plat_Form.equalsIgnoreCase(tranParam)){
                String platForm = PlatFormUtil.getPlatformV(paramBeanMap.get(ParamConst.Param_Plat_Form));
                if (platForm==null) {
                    platForm = "0";
                }
                paramBeanMap.put(ParamConst.Param_Plat_Form,platForm);
            } else if(ParamConst.Param_Start_Date.equalsIgnoreCase(tranParam)){
                String startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd")
                        .parse(paramBeanMap.get(ParamConst.Param_Start_Date)));
                paramBeanMap.put(ParamConst.Param_Start_Date,startDate);
                //throw new ParseException(tranParam, 0);
            } else if(ParamConst.Param_End_Date.equalsIgnoreCase(tranParam)){
                String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd")
                        .parse(paramBeanMap.get(ParamConst.Param_End_Date)));
                paramBeanMap.put(ParamConst.Param_End_Date,endDate);
            } else {
                dealOtherSqlParam(paramBeanMap, tranParam);
            }
        }
    }

    /**
     * 其它参数处理，实现类处理
     * @param paramBeanMap
     * @param tranParam
     * @throws ParseException
     */
    public void dealOtherSqlParam(Map<String,String> paramBeanMap, String tranParam) throws ParseException{

    }

    /**
     * 处理数据库返回数据
     * @param dataInfos
     */
    public  void dealReslutDataByMap(List<Map<String,Object>> dataInfos,ComponentContext context){
        //是否需要转换platId
        Boolean transPlatIdFlag = (Boolean)context.getUntilMap().get(ParamConst.Trans_Plat_Id_Flag);
        if (transPlatIdFlag!=null&&transPlatIdFlag){
            // 转换行业id为行业名称
            for (Map dataInfo:dataInfos){
                String industryId = dataInfo.get(ParamConst.Col_Name_Industry_Name).toString();
                String industryName = PlatFormUtil.getPlatformV(industryId);
                dataInfo.put(ParamConst.Col_Name_Industry_Name,industryName);
            }
        }

        dealOtherResultData(dataInfos,context);
    }

    /**
     * 额外的处理数据库返回记录
     * @param dataInfos
     */
    public void dealOtherResultData(List<Map<String,Object>> dataInfos,ComponentContext context){

    }

    /**
     * 字段的脱敏处理
     * @param dataInfos
     */
    public void desensitizationDataByMap(List<Map<String,Object>> dataInfos,String[] desensitizationCols) throws Exception {
        for (String col:desensitizationCols){
            for (Map dataInfo:dataInfos){
                String value = dataInfo.get(col).toString();
                String transedValue = DesensitizationUtils.getDesStr(value);
                dataInfo.put(col,transedValue);
            }
        }
    }

    /**
     * 以AbstractChartBuilder为构图组件的抽取数据方法
     * @param callBack
     * @param absCharBuilder
     * @param context
     * @return
     * @throws Exception
     */
    public Response extractDataAndBuildChart(BaseDaoCallback callBack,
                                             AbstractChartBuilder absCharBuilder,
                                             ComponentContext context) throws Exception {
        String realSql = dealSqlWithParam(context);
        logger.info("query sql: "+realSql);
        Response response = Response.ok().build();
        response = realDoExtractAndBuild(realSql,callBack,absCharBuilder,context);
        return response;
    }

    public Response realDoExtractAndBuild(String realSql, BaseDaoCallback callBack, AbstractChartBuilder absChartBuilder,
                                          ComponentContext context) throws Exception {
        List<Map<String,Object>> dataInfos = callBack.getMapListBySql(baseDao, realSql);
        /*//是否需要转换platId
        Boolean transPlatIdFlag = (Boolean)context.getUntilMap().get(ParamConst.Trans_Plat_Id_Flag);
        if (transPlatIdFlag!=null&&transPlatIdFlag){
            dealReslutDataByMap(dataInfos);
        }*/
        dealReslutDataByMap(dataInfos,context);
        //是否需要脱敏处理
        String[] desensitizationCols = (String[]) context.getUntilMap().get(ParamConst.Desensitization_Col);
        if (desensitizationCols!=null){
            desensitizationDataByMap(dataInfos,desensitizationCols);
        }
        // 不要使用Autowired注入，否则原型没有用
			/*BaseResponse response = SpringContextUtil.getBean(BaseResponse.class);
			baseChartBuilder.buildData(baseInfos,response,context);
			return buildResonse(response);*/
        // BaseResponse类的变化太大，采用map适应变化
        Map responseMap = super.initResponseMap();
        absChartBuilder.buildData(dataInfos,responseMap,context);
        return  Response.ok().entity(responseMap).build();
    }
}
