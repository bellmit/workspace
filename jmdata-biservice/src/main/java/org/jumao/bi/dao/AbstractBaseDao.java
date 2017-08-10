package org.jumao.bi.dao;

import java.util.List;
import java.util.Map;

import org.jumao.bi.component.BaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("abstractBaseDao")
public class AbstractBaseDao implements IBaseDao {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	@Override
	public List<BaseInfo> getEntityListBySql(String sql) throws Exception{
		RowMapper<BaseInfo> rowMapper = new BeanPropertyRowMapper<BaseInfo>(BaseInfo.class);
        List<BaseInfo> baseInfos = jdbcTemplate.query(sql, rowMapper);
        return baseInfos;
	}

	@Override
	public List<Map<String, Object>> getMapListBySql(String sql) throws Exception {
		List<Map<String,Object>> resultList = jdbcTemplate.query(sql,new ColumnMapRowMapper());
		return resultList;
	}

	@Override
	public List<Map<String, Object>> getUtilListBySql(String sql)
			throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForList(sql);
	}
	
	@Override
	public <T> Object getObjectBySql(String sql,Class<T> requiredType) throws Exception{		
        return jdbcTemplate.queryForObject(sql, requiredType);
	}
}
