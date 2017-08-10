package org.jumao.bi.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jumao.bi.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository("baseDaoCallback")
public class BaseDaoCallbackImpl implements BaseDaoCallback {
	
	@Override
	public List<BaseInfo> getListBySql(IBaseDao baseDao, String sql) throws Exception{
		List<BaseInfo> baseInfoList = new ArrayList<BaseInfo>();
		baseInfoList = baseDao.getEntityListBySql(sql);
		return baseInfoList;
	}

	@Override
	public List<Map<String, Object>> getMapListBySql(IBaseDao baseDao,String sql) throws Exception {
		return baseDao.getMapListBySql(sql);
	}

	@Override
	public List<BaseInfo> getListBySqlMap(IBaseDao baseDao, Map<String,String> sqlMap) throws Exception{
		List<BaseInfo> baseInfoList = new ArrayList<BaseInfo>();
		for (Map.Entry<String, String> entry : sqlMap.entrySet()){
			String itemName = entry.getKey();
			String sql = entry.getValue();
			String result = baseDao.getObjectBySql(sql, String.class).toString();
			this.collectItem(itemName,result,baseInfoList);
		}

		return baseInfoList;
	}
	/**
	 * 收集列表里的数据项
	 * @param itemName
	 * @param itemValue
	 * @param list
	 */
	public void collectItem(String itemName,String itemValue,List<BaseInfo> list){
		BaseInfo info = new BaseInfo();
    	info.setItemName(itemName);
    	info.setItemValue(itemValue);
    	list.add(info);
    }

}
