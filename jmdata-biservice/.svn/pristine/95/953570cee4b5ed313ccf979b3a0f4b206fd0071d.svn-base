package org.jumao.bi.component;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.AbstractBaseDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.utis.DesensitizationUtils;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.PlatFormUtil;
import org.jumao.bi.utis.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BaseDataExtract implements IDataExtract{
	
	@Autowired
    private AbstractBaseDao baseDao;
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 处理sql传入参数
	 */
	public void dealSqlParam(ParamBean param,List<String> transList) throws ParseException{
		for (String tranParam : transList){
			if ("platform".equalsIgnoreCase(tranParam)){
				String platForm = PlatFormUtil.getPlatformV(param.getPlatform());
		        if (platForm==null) {
		        	platForm = "0"; 
		        }
		        param.setPlatform(platForm);
			} else if("startDate".equalsIgnoreCase(tranParam)){
				String startDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(param.getStartDate()));
				param.setStartDate(startDate);	
				//throw new ParseException(tranParam, 0);
			} else if("endDate".equalsIgnoreCase(tranParam)){
				String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(param.getEndDate()));
				param.setEndDate(endDate);
			} else {
				dealOtherSqlParam(param, tranParam);
			}
		}
	}

	/**
	 * 其它参数处理，实现类处理
	 * @param param
	 * @param tranParam
	 * @throws ParseException
	 */
	public void dealOtherSqlParam(ParamBean param, String tranParam) throws ParseException{

	}
	/**
	 * 替换sql里的占位变量为具体的值
	 * @param sql
	 * @param param
	 * @param transList
	 * @return
	 * @throws Exception
	 */
	public String dealSqlWithParam(String sql,ParamBean param,List<String> transList) throws Exception{
		this.dealSqlParam(param,transList);
		String newSql = sql;
		// 不在这些参数里的其他参数统一替换成itemId的值
		for (String item:transList){
			if ("platform".equalsIgnoreCase(item)||"startDate".equalsIgnoreCase(item)||"endDate".equalsIgnoreCase(item)){
				//
			}else{
				newSql = newSql.replaceAll(":"+item, param.getItemId());
			}
		}
		return newSql.replaceAll(":platform", param.getPlatform())
		.replaceAll(":startDate", param.getStartDate())
		.replaceAll(":endDate", param.getEndDate())
		.replaceAll(":itemId", param.getItemId());
	}

	public String dealSqlWithParam(ComponentContext context) throws Exception{
		return dealSqlWithParam(context.getSql(),context.getParamBean(),context.getTransParam());
	}

	@Deprecated
	private Response buildResonse(BaseResponse response){
		response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
		return Response.ok().entity(response).build();
	}

	protected Map initResponseMap(){
		Map responseMap = new LinkedHashMap();
		Map successMap = new HashMap();
		successMap.put("code","200");
		successMap.put("message","Successful!");
		responseMap.put("status",successMap);
		return responseMap;
	}
	/**
	 * 处理数据库返回数据
	 * @param baseInfos
	 */
	public  void dealReslutData(List<BaseInfo> baseInfos){
		// 转换行业id为行业名称
		for (BaseInfo baseInfo:baseInfos){
			String industryId = baseInfo.getItemName();
			String industryName = PlatFormUtil.getPlatformV(industryId);
			baseInfo.setItemName(industryName);
		}
	}

	/**
	 * 字段的脱敏处理
	 * @param baseInfos
	 */
	public void desensitizationData(List<BaseInfo> baseInfos,String[] desensitizationCols) throws Exception {
		for (String col:desensitizationCols){
			// 反射执行get和set方法
			Method getMethod = BaseInfo.class.getDeclaredMethod(GeneralUtils.getGetterMethodName(col));
			Method setMethod = BaseInfo.class.getDeclaredMethod(GeneralUtils.getSetterMethodName(col),String.class);
			for (BaseInfo baseInfo:baseInfos){
				String value = (String)getMethod.invoke(baseInfo);
				String transedValue = DesensitizationUtils.getDesStr(value);
				setMethod.invoke(baseInfo,transedValue);
			}
		}

	}

	/**
	 * 设置环境
	 * @param param
	 * @param sql
	 * @param returnTypeMap
	 */
	public void setContext(ParamBean param,List<String> transList,
			String sql,Map<String,?> utilMap,Map<String,Map<?,?>> utilNestedMap,Map<String,Class<?>> returnTypeMap, ComponentContext context){
    	context.setTransParam(transList);
    	context.setParamBean(param);
    	context.setSql(sql);
    	// 空值生成一个默认对象，防止后面报错或者重新判断空值
    	context.setUntilMap(utilMap==null?new HashMap():utilMap);
    	context.setUntilNestedMap(utilNestedMap==null?new HashMap<String,Map<?,?>>():utilNestedMap);
    	context.setReturnTypeMap(returnTypeMap==null?new HashMap<String,Class<?>>():returnTypeMap);
	}

	public Response realDoExtractAndBuild(String realSql,
										  BaseDaoCallback callBack,
										  BaseChartBuilder baseChartBuilder,
										  ComponentContext context) throws Exception {
		List<BaseInfo> baseInfos = callBack.getListBySql(baseDao, realSql);
		//是否需要转换platId
		Boolean transPlatIdFlag = (Boolean)context.getUntilMap().get(ParamConst.Trans_Plat_Id_Flag);
		if (transPlatIdFlag!=null&&transPlatIdFlag){
			dealReslutData(baseInfos);
		}
		//是否需要脱敏处理
		String[] desensitizationCols = (String[]) context.getUntilMap().get(ParamConst.Desensitization_Col);
		if (desensitizationCols!=null){
			desensitizationData(baseInfos,desensitizationCols);
		}
		// 不要使用Autowired注入，否则原型没有用
			/*BaseResponse response = SpringContextUtil.getBean(BaseResponse.class);
			baseChartBuilder.buildData(baseInfos,response,context);
			return buildResonse(response);*/
		// BaseResponse类的变化太大，采用map适应变化
		Map responseMap = initResponseMap();
		baseChartBuilder.buildData(baseInfos,responseMap,context);
		return  Response.ok().entity(responseMap).build();
	}

	/**
	 * 新增入口，判断构建类的类型
	 * @param callBack
	 * @param context
	 * @return
	 * @throws Exception
	 */
	/*public Response doJob(BaseDaoCallback callBack,ComponentContext context) throws Exception {
		String chartBuilderType = context.getUntilMap().get(ParamConst.Chart_Builder_Type).toString();
		Object charBuilder = SpringContextUtil.getBean(chartBuilderType);
		Response response = null;
		if (charBuilder instanceof BaseChartBuilder){
			BaseChartBuilder baseCharBuilder = (BaseChartBuilder)charBuilder;
			response =  extractDataAndBuildChart(callBack,baseCharBuilder,context);
		}else if (charBuilder instanceof AbstractChartBuilder){
			AbstractChartBuilder absCharBuilder = (AbstractChartBuilder)charBuilder;
			response = extractDataAndBuildChart(callBack,absCharBuilder,context);
		}
		return response;
	}*/

	/**
	 * 钩子
	 * @param callBack
	 * @param absCharBuilder
	 * @param context
	 * @return
	 * @throws Exception
	 */
	/*public  Response extractDataAndBuildChart(BaseDaoCallback callBack,
											  AbstractChartBuilder absCharBuilder,
											  ComponentContext context) throws Exception{
		return null;
	}*/

	@Override
	public  Response extractDataAndBuildChart(BaseDaoCallback callBack,
			BaseChartBuilder baseChartBuilder, ComponentContext context) throws Exception {
		// String realSql = dealSqlWithParam(context.getSql(),context.getParamBean(),context.getTransParam());
		String realSql = dealSqlWithParam(context);
		logger.info("query sql: "+realSql);
		Response response = Response.ok().build();
		response = realDoExtractAndBuild(realSql,callBack,baseChartBuilder,context);
		return response;
	}
	
	@Override
	public  Response extractDataAndBuildChartBySqlMap(BaseDaoCallback callBack, Map<String,String> sqlMap,
			BaseChartBuilder baseChartBuilder, ComponentContext context) throws Exception{
		Map<String,String> realSqlMap = new HashMap<String,String>();
		for (Entry<String, String> entry : sqlMap.entrySet()) {
			// String realSql = dealSqlWithParam(entry.getValue(),context.getParamBean(),context.getTransParam());
            context.setSql(entry.getValue());
			String realSql = dealSqlWithParam(context);
			realSqlMap.put(entry.getKey(), realSql);
			logger.info("query sql: "+realSql);
		}
		List<BaseInfo> baseInfos = callBack.getListBySqlMap(baseDao, realSqlMap);
        /*BaseResponse response = SpringContextUtil.getBean(BaseResponse.class);
        baseChartBuilder.buildData(baseInfos,response,context);
        return buildResonse(response);*/
		Map responseMap = initResponseMap();
		baseChartBuilder.buildData(baseInfos,responseMap,context);
		return Response.ok().entity(responseMap).build();
	}
}
