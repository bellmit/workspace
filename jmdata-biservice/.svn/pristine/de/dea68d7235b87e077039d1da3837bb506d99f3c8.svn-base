package org.jumao.bi.component;

import java.util.List;
import java.util.Map;

import org.jumao.bi.entites.ParamBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
public class ComponentContext {
	private List<String> transParam;//需要转换的参数
	private Map<String,Map<?,?>> untilNestedMap;//通用嵌套map
	private Map<String,?> untilMap; //通用map
	private ParamBean paramBean;//参数bean
	private Map<String,String> paramBeanMap;//参数map
	private String sql;//sql
	//private Boolean dealPlatIdFlag = false;//是否需要平台id转换到平台名称
	private Map<String,Class<?>> returnTypeMap;//返回类型
	public List<String> getTransParam() {
		return transParam;
	}
	public void setTransParam(List<String> transParam) {
		this.transParam = transParam;
	}

	public Map<String, Map<?, ?>> getUntilNestedMap() {
		return untilNestedMap;
	}

	public void setUntilNestedMap(Map<String, Map<?, ?>> untilNestedMap) {
		this.untilNestedMap = untilNestedMap;
	}

	public Map<String, ?> getUntilMap() {
		return untilMap;
	}

	public void setUntilMap(Map<String, ?> untilMap) {
		this.untilMap = untilMap;
	}

	public ParamBean getParamBean() {
		return paramBean;
	}
	public void setParamBean(ParamBean paramBean) {
		this.paramBean = paramBean;
	}

	public Map<String, String> getParamBeanMap() {
		return paramBeanMap;
	}

	public void setParamBeanMap(Map<String, String> paramBeanMap) {
		this.paramBeanMap = paramBeanMap;
	}

	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}

	public Map<String, Class<?>> getReturnTypeMap() {
		return returnTypeMap;
	}

	public void setReturnTypeMap(Map<String, Class<?>> returnTypeMap) {
		this.returnTypeMap = returnTypeMap;
	}
}
