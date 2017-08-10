package com.jumore.jmbi.common.util;

import java.util.Map;

import com.jumore.dove.plugin.Page;
import com.jumore.dove.util.ParamMap;
import com.jumore.jmbi.common.util.convertor.BeanConvertor;
import com.jumore.jmbi.model.param.BaseParam;
import com.jumore.jmbi.model.param.PageQueryParam;

/**
 * 参数类型转换类
 * 
 * @author: panweiqiang
 * @since: 2016年7月15日 上午11:06:04
 * @history:
 */
public class ParamUtil {

	/**
	 * 功能说明：获取分页参数：：按照bootstrap table的{起始行数、每页数量}进行分页
	 * 
	 * @param entityType
	 * @param pageQueryParam
	 * @return Page
	 */
	public static <T> Page<T> getPage(Class<T> entityType,
			PageQueryParam pageQueryParam) {
		Page<T> page = new Page<T>();
		page.setPageSize(pageQueryParam.getLimit());
		page.setCurrentResult(pageQueryParam.getStart() - 1);
		return page;
	}

	/**
	 * 功能说明：获取分页参数：：按照正常的{第几页、每页数量}进行分页
	 * 
	 * @param entityType
	 * @param pageQueryParam
	 * @return Page
	 */
	public static <T> Page<T> getPageByOffset(Class<T> entityType,
			PageQueryParam pageQueryParam) {
		Page<T> page = new Page<T>();
		page.setPageSize(pageQueryParam.getLimit());
		int current = 0;
		if (null!= pageQueryParam.getOffset()) {//数据库，limit起始行
			current = pageQueryParam.getOffset() * page.getPageSize();
		}
		page.setCurrentResult(current);
		return page;
	}

	/**
	 * 功能说明：从页面参数中获取查询参数
	 * 
	 * @param entity
	 * @return ParamMap
	 */
	public static ParamMap getParamMap(Object entity) {
		ParamMap paramMap = new ParamMap();
		Map<?, ?> map = BeanConvertor.convertBean(entity, Map.class);
		paramMap.putAll(map);
		if (entity instanceof BaseParam) {
			BaseParam sortQueryParam = (BaseParam) entity;
			setOrderMap(paramMap, sortQueryParam);
		}
		return paramMap;
	}

	/**
	 * 设置查询排序
	 * 
	 * @param paramMap
	 * @param baseParam
	 */
	public static void setOrderMap(ParamMap paramMap, BaseParam baseParam) {
		if (baseParam.getSortMap() != null) {
			for (Map.Entry<String, BaseParam.SortType> entry : baseParam
					.getSortMap().entrySet()) {
				paramMap.addOrder(entry.getKey(), entry.getValue().name());
			}
		}
	}
}
