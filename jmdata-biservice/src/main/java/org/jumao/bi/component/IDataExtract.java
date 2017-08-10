package org.jumao.bi.component;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.jumao.bi.entites.ParamBean;

public interface IDataExtract {

	public void dealSqlParam(ParamBean param, List<String> transList)
			throws ParseException;

	public Response extractDataAndBuildChart(BaseDaoCallback callBack,
			BaseChartBuilder baseChartBuilder, ComponentContext context) throws Exception;
	
	public Response extractDataAndBuildChartBySqlMap(BaseDaoCallback callBack, Map<String,String> sqlMap,
			BaseChartBuilder baseChartBuilder, ComponentContext context) throws Exception;

}
