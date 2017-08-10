package org.jumao.bi.dao;

import java.util.List;

import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.commons.frameworks.jmframework.commutil.MRConstants;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CommonDaoImpl {
    @Autowired
    public IHBaseDao hbasedao;
    
    @Autowired
    public JdbcTemplate jdbcTemplate;
    
    public static final byte[] Column_Family_Name = Bytes.toBytes(MRConstants.TAB_CF);
    
    public <T> List<T> getSqlResult(String sql, Class<T> clazz) {
        List<T> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz));       
        return result;
    }

}
