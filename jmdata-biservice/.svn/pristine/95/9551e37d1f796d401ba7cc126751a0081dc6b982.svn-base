package org.jumao.bi.dao;

import java.util.List;
import java.util.Map;

import org.jumao.bi.component.BaseInfo;

public interface IBaseDao {
	/**
	 * 根据sql查询返回通用实体类列表
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<BaseInfo> getEntityListBySql(String sql) throws Exception;
	/**
	 * 根据sql查询返回列名值对的列表
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getMapListBySql(String sql) throws Exception;
	/**
	 * 根据sql查询返回Map列表
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getUtilListBySql(String sql) throws Exception;

	/**
	 * 根据sql查询数据并返回制定类型的值
	 * @param sql
	 * @param requiredType
	 * @return
	 * @throws Exception
	 */
	public <T> Object getObjectBySql(String sql,Class<T> requiredType) throws Exception;
}
