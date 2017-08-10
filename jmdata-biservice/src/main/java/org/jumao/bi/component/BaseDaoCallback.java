package org.jumao.bi.component;

import java.util.List;
import java.util.Map;

import org.jumao.bi.dao.IBaseDao;
public interface BaseDaoCallback {
	/**
	 * 根据sql查询出BaseInfo的列表
	 * @param baseDao
	 * @param sql
	 * @return
	 */
	public List<BaseInfo> getListBySql(IBaseDao baseDao,String sql) throws Exception;
	/**
	 * 根据sql查询返回列名值对的列表
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getMapListBySql(IBaseDao baseDao,String sql) throws Exception;
	/**
	 * 根据多个sql组成的map，组合成以key作为itemName,sql查出的值作为itemValue的BaseInfo列表
	 * @param baseDao
	 * @param sqlMap
	 * @return
	 */
	public List<BaseInfo> getListBySqlMap(IBaseDao baseDao, Map<String,String> sqlMap) throws Exception;
}
